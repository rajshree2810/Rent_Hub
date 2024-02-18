import { HttpClient, HttpEvent, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http:HttpClient) { }

  getUser():Observable<any>{
    const queryParams = sessionStorage.getItem('username');
    return this.http.get(`http://localhost:9999/user/${queryParams}`)
  }


  saveProfile(profileForm:any):Observable<any>{
    return this.http.put("http://localhost:9999/user/updateprofile",profileForm);
  }

  saveBankProfile(bankForm:any):Observable<any>{
    let headers = new HttpHeaders();
    headers = headers.set('userId',sessionStorage.getItem('username') as string);
    return this.http.put("http://localhost:9999/user/updatebankdetail",bankForm,{headers:headers});
  }

  // setProfileImageUrl(userData: User): Observable<any> {
  //   const formData: FormData = new FormData();
  //   formData.append('file', file);
  //   formData.append('userId', sessionStorage.getItem('username') as string);
  //   console.log("Username"+ sessionStorage.getItem('username'));
  //   const headers = {
  //     'username': sessionStorage.getItem('username'),
  //     'image':formData
  //   }


  //   return this.http.put("http://localhost:8065/api/v1/addprofileimage",formData);
  // }

  setProfileImageUrl(userData: any): Observable<any> {
    //userData.userId=sessionStorage.getItem('username') as string;
    console.log("From profile service" + userData.imageUrl)
    console.log(userData.userId)
    return this.http.put("http://localhost:9999/user/addprofileimage",userData);
  }


  getProfileImage():Observable<any>{
    return this.http.get("");
  }
}
