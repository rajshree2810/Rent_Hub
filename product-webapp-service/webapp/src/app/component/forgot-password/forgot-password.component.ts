import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/model/User';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit{
  forgotForm:FormGroup;
  newForm:FormGroup;
  forgotuser:any;
  email:string;
  @Input() userId:string='';
  @Input() state:boolean;

  ngOnInit(): void {
      this.state=this.route.snapshot.params["state"];
      this.userId=this.route.snapshot.params["userId"];
      this.newForm.get('userId')?.setValue(this.userId);
      console.log(this.state)
      console.log(this.userId)
  }


  constructor(private formbuilder:FormBuilder,private auth:AuthenticationService,private route:ActivatedRoute,private router:Router){
    this.forgotForm=this.formbuilder.group({
      email:['',[Validators.required,Validators.email]]
    })

    this.newForm=this.formbuilder.group({
      userId:['',Validators.required],
      password:['',Validators.required],
      confirmpassword:['',Validators.required]
    })

    this.forgotuser=new User();
    this.email=''
    this.state=true;

  }

  setValue(){
    this.email=this.forgotForm.get('email')?.value;
    return this.email;
  }

  forgotUserMail(forgotForm:any){
    this.forgotuser=this.auth.forgotUserMail(this.setValue()).subscribe(resp=>{
      console.log(forgotForm.value);
      this.forgotuser=resp;
      console.log(this.forgotuser);
      this.router.navigate(['/login']);
      alert('Mail sent to registered id')
    },err=>{alert('Email does not exist in database')})
  }


  forgetUser(newForm:any){
    if(this.checkForSamePassword(newForm)){
      console.log(newForm.value);
      console.log(this.newForm.value)
      this.auth.forgotUser(this.newForm.value).subscribe(resp=>{
        alert('Password changed');
        this.router.navigate(['/login']);
      })
    }else{
      alert('Password should match');
    }
  }

  checkForSamePassword(newForm:any){
    return this.newForm.get('password')?.value==this.newForm.get('confirmpassword')?.value;
  }



}
