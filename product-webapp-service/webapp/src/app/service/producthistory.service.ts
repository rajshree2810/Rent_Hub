import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProducthistoryService {

  constructor(private http:HttpClient) { }


  buyedOrder():Observable<any>{
    const username=sessionStorage.getItem('username');
    return this.http.get(`http://localhost:9999/producthistory/trackorders/${username}`);
  }

  soldOrder():Observable<any>{
    const username=sessionStorage.getItem('username');
    return this.http.get(`http://localhost:9999/producthistory/soldorders/${username}`);
  }

  deleteOrder(transaction_id:String):Observable<any>{
    return this.http.delete(`http://localhost:9999/producthistory/deleteorder/${transaction_id}`)
  }
}
