import { HttpClient } from '@angular/common/http';
import { Component,HostListener,OnInit} from '@angular/core';
import { User } from 'src/app/model/User';
import { PaymentDetail } from 'src/app/model/PaymentDetail';
import { Product } from 'src/app/model/product';
import { OrderServiceService } from 'src/app/order-service.service';
import { ProfileService } from 'src/app/service/profile.service';
import { Router } from '@angular/router';

declare var Razorpay: any; 
@Component({
  selector: 'app-makepayment',
  templateUrl: './makepayment.component.html',
  styleUrls: ['./makepayment.component.css']
})
export class MakepaymentComponent implements OnInit{
  paymentDetail:PaymentDetail;
  buyedProduct:Array<Product>=new Array();
  productId:Array<string>=new Array();
  currentUser:User;

  constructor(private http:HttpClient,private router:Router,private paymentService:OrderServiceService,private userService:ProfileService){
    this.paymentDetail=new PaymentDetail();
    this.currentUser=new User();
    this.fetchUser();
  }

  ngOnInit(): void {
    this.fetchUser();
    this.paymentDetail.amount=0;
    for(var i=JSON.parse(sessionStorage.getItem('cart')).length;i>=0;i--){
      this.buyedProduct[i]=JSON.parse(sessionStorage.getItem('cart'))[i];
    }
    this.buyedProduct.forEach((val)=>{
      this.productId.push(val.productId);
      this.paymentDetail.amount=this.paymentDetail.amount + parseInt(val.productPrice);
      this.paymentDetail.productName= this.paymentDetail.productName + ", " + val.productName;
    })

    this.paymentDetail.email=this.currentUser.email;
    this.paymentDetail.phone=this.currentUser.phone;
    console.log(this.paymentDetail)
  }

  fetchUser(){
    this.userService.getUser().subscribe(resp=>{
      this.currentUser=resp;
      console.log(this.currentUser)
    })
  }

  paymentId: string | undefined;
  error: string | undefined;

  options = {
    "key": "",
    "amount": "",
    "name": "Pay To RentHub",
    "order_id": "",
    "handler": function (response: any) {
      var event = new CustomEvent("payment.success",
        {
          detail: response,
          bubbles: true,
          cancelable: true
        }
      );
      window.dispatchEvent(event);
    }
    ,
    "prefill": {
      "name": "",
      "email": "",
      "contact": ""
    },
    "notes": {
      "address": ""
    },
    "theme": {
      "color": "#3399cc"
    }
  };




  onSubmit(): void {
    this.paymentId = '';
    this.error = '';
    this.paymentService.createOrder(this.paymentDetail, this.productId).subscribe(
      data => {
        this.options.key = data.secretId;
        this.options.order_id = data.razorpayOrderId;
        this.options.amount = data.applicationFee; //paise
        this.options.prefill.name = this.currentUser.firstName as string;
        this.options.prefill.email = this.currentUser.email as string;
        this.options.prefill.contact = this.currentUser.phone as string;

        if (data.psName === 'razor2') {
          var rzp1 = new Razorpay(this.options);
          rzp1.open();
        } else {
          var rzp2 = new Razorpay(this.options);
          rzp2.open();
        }


        rzp1.on('payment.failed', (response: { error: { code: any; description: any; source: any; step: any; reason: any; metadata: { order_id: any; payment_id: any; }; }; }) => {

          console.log(response);
          console.log(response.error.code);
          console.log(response.error.description);
          console.log(response.error.source);
          console.log(response.error.step);
          console.log(response.error.reason);
          console.log(response.error.metadata.order_id);
          console.log(response.error.metadata.payment_id);
          this.error = response.error.reason;
        }
        );
      }
      ,
      err => {
        this.error = err.error.message;
      }
    );
    sessionStorage.removeItem('cart');
  }

  @HostListener('window:payment.success', ['$event'])
  onPaymentSuccess(event: { detail: any; }): void {
    console.log(event.detail);
    this.paymentService.updateOrder().subscribe(resp=>{})
    this.router.navigate(['/productHistory'])
  }
}
