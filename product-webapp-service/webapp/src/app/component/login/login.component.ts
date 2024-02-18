import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/service/authentication.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm:FormGroup;

  constructor(private formbuilder:FormBuilder,private auth:AuthenticationService,private route:Router){
    this.loginForm=this.formbuilder.group({
      userId:['',Validators.required],
      password:['',Validators.required]
    })
  }

  loginUser(loginForm:FormGroup){
    this.auth.authenticateUser(this.loginForm.value).subscribe(resp=>{
      sessionStorage.setItem('bearerToken',resp['token']);
      this.route.navigate(['/home']).then(()=>{
        window.location.reload();
      });
    },err=>{
      alert('Invalid Credentials');
    })
  }
}
