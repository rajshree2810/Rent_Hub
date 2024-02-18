import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Chat } from '../model/chat';
import { User } from '../model/User';
import { ChatService } from '../services/chat.service';
import { UserchatService } from '../service/userchat.service'; // Update the import
@Component({
  selector: 'app-user',
  templateUrl: './userchat.component.html',
  styleUrls: ['./userchat.component.css']
})
export class UserChatComponent implements OnInit {

  chatId: any = 0;
  registerForm: FormGroup;
  loginForm: FormGroup;
  successregister: boolean = false;
  registermsg = "";
  alert = "";
  loginmsg = "";
  alert2 = "";
  successlogin: boolean = false;
  public userObj: User = new User();
  public alluser: any = [];
  secondUsername = "";
  chatObj: Chat = new Chat();
  public chatData: any = [];
  check = "";
  loggedIn: boolean = false;
  loggedOut: boolean = true;
  chatbox: boolean = true;



  constructor(private router: Router,  private userchatService: UserchatService, private chatService: ChatService) {
    this.registerForm = new FormGroup({
      username: new FormControl("", [Validators.required])
    });
    this.loginForm = new FormGroup({
      username: new FormControl("", [Validators.required])
    });
  }

  ngOnInit(): void {
    let all = setInterval(() => {
      this.userchatService.getAllUserNames().subscribe((data) => { // Use the UserChatService method here
        this.alluser = data;
      });
    }, 1000);
  }


  goToChat(username: any) {
    this.chatService.getChatByFirstUserNameAndSecondUserName(username, sessionStorage.getItem("username")?? '').subscribe(
      (data) => {
        this.chatData = data;
        this.chatId = this.chatData[0].chatId;
        sessionStorage.setItem("chatId", this.chatId);
        this.router.navigateByUrl('/chat');
      },
      (error) => {
        if (error.status == 404) {
          this.chatObj.firstUserName = sessionStorage.getItem("username")?? '';
          this.chatObj.secondUserName = username;
          this.chatService.createChatRoom(this.chatObj).subscribe(
            (data) => {
              this.chatData = data;
              sessionStorage.setItem("chatId", this.chatData[0].chatId);
              // this.router.navigateByUrl('/chat');
              console.log("2")
            })
        } else {
          // this.router.navigateByUrl('/chat');
          console.log("3")
        }
      });

  }

}

