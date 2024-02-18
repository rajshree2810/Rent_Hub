import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
	headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
	providedIn: 'root'
})
export class OrderServiceService {

	constructor(private http: HttpClient) {

	}



	createOrder(paymentDetail: { productName: any, amount: number, email: any, phone: any }, productId: string[]): Observable<any> {

		return this.http.post("http://localhost:9999/ps/createOrder", {
			customerName: paymentDetail.productName,
			email: paymentDetail.email,
			phoneNumber: paymentDetail.phone,
			amount: `${paymentDetail.amount}`,
			customerId: sessionStorage.getItem('username'),
			productId: productId,
		}, httpOptions);
	}

	updateOrder():Observable<any>{
		return this.http.get('http://localhost:9999/ps/update');
	}


}
