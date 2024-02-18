import { Component } from '@angular/core';
import { EmailValidator, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/service/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm:FormGroup;
  constructor(private formbuilder:FormBuilder,private auth:AuthenticationService){
    this.registerForm=this.formbuilder.group({
      userId:['',Validators.required],
      password:['',Validators.required],
      firstName:['',Validators.compose([Validators.required, Validators.pattern("[a-zA-Z]+[a-zA-Z]")])],
      lastName:['',Validators.compose([Validators.required, Validators.pattern("[a-zA-Z]+[a-zA-Z]")])],
      email:['',[Validators.required,Validators.email]],
      phone:['',[Validators.required,Validators.maxLength(13),Validators.minLength(10),Validators.pattern("^((\\+91-?)|0)?[0-9]{10}")]],
    })
  }

  registerUser(registerForm:FormGroup){
    this.auth.registerUser(this.registerForm.value).subscribe(resp=>{
      alert("User saved");
      this.onReset();
    },err=>{
      alert("User already exist");
    })
  }

  onReset(){
    this.registerForm.reset();
  }

}
