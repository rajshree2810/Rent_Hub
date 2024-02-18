import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';
import { Credential} from '../model/Credential';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(private http:HttpClient) {

  }

  authenticateUser(credential:Credential):Observable<any>{
    this.saveUserName(credential);
    return this.http.post('http://localhost:9999/authentication/login',credential);
  }

  saveUserName(credential:Credential){
    sessionStorage.setItem('username',credential.userId as string);
  }

  getUserName(){
    return sessionStorage.getItem('username');
  }


  setBearerToken(token: string) {
    sessionStorage.setItem('bearerToken', token)
  }
  getBearerToken() {
    sessionStorage.getItem('bearerToken');
  }

  public isAuthenticated(): boolean {
    const token = sessionStorage.getItem('bearerToken');
    return token !== null;
  }

  registerUser(registerForm:any):Observable<any>{
    return this.http.post('http://localhost:9999/user/adduser',registerForm);
  }

  forgotUserMail(forgotForm:any):Observable<any>{
    let queryParams = new HttpParams();
    queryParams = queryParams.append("email",forgotForm);
    return this.http.get('http://localhost:9999/user/forgetpasswordmail',{params:queryParams});
  }

  forgotUser(newForm:any):Observable<any>{
    return this.http.put('http://localhost:9999/user/forgotpassword',newForm);
  }
}
