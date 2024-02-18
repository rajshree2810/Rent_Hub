import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Chat } from '../model/chat';
import { ChatService } from '../services/chat.service';
import { UserchatService } from '../service/userchat.service'; // Update the import

@Component({
  selector: 'app-main',
  templateUrl: './chatmain.component.html',
  styleUrls: ['./chatmain.component.css']
})
export class ChatMainComponent implements OnInit {
  public alluser: any[] = []; // Change to an array type
  check = sessionStorage.getItem('username');
  chatId: any = 0;
  chatObj: Chat = new Chat();
  public chatData: any = [];

  constructor(
    private router: Router,
    private userchatService: UserchatService, // Inject UserChatService here
    private chatService: ChatService
  ) { }

  ngOnInit(): void {
    let all = setInterval(() => {
      this.userchatService.getAllUserNames().subscribe((data) => { // Use the UserChatService method here
        this.alluser = data;
      });
    }, 1000);
  }


  goToChat(username: any) {
    this.chatService.getChatByFirstUserNameAndSecondUserName(username, sessionStorage.getItem("username")).subscribe(
      (data) => {
        this.chatId = data.chatId;
        sessionStorage.setItem("chatId", this.chatId);

        sessionStorage.setItem("gotochat", "false");
        this.router.navigateByUrl('/chat');
      },
      (error) => {
        if (error.status == 404) {
          this.chatObj.firstUserName = sessionStorage.getItem("username");
          this.chatObj.secondUserName = username;
          this.chatService.createChatRoom(this.chatObj).subscribe(
            (data) => {
              this.chatData = data;
              this.chatId = this.chatData.chatId;
              sessionStorage.setItem("chatId", this.chatData.chatId);

              sessionStorage.setItem("gotochat", "false");
              this.router.navigateByUrl('/chat');
            })
        } else {

        }
      });

  }

}
