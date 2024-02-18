import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserchatService {

  constructor(private http: HttpClient) { }

  getUser(){
    const queryParams = sessionStorage.getItem('username');
    return this.http.get(`http://localhost:9999/user/${queryParams}`)
  }

  getAllUserNames(): Observable<string[]> {
    return this.http.get<string[]>('http://localhost:9999/user/getallnames'); // Replace with your backend API URL for all user names
  }
}

