import { HttpClient } from '@angular/common/http';
import { Component, HostListener, OnInit} from '@angular/core';
import { OrderServiceService } from '../order-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../model/product';
import { User } from '../model/User';
import { ProfileService } from '../service/profile.service';
declare var Razorpay: any; 

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit{

  paymentDetail:{productName:any,amount:number,email:any,phone:any} 
  buyedProduct:Array<Product>
  productId:Array<string>=[];
  amount:number=0;
  currentUser:User;
  constructor(private http: HttpClient,
    private orderService:OrderServiceService,private router:Router,private userService:ProfileService) {
      this.buyedProduct=[];
      this.productId=[];
      //this.paymentDetail.amount=0;
      //this.fetchUser();
  }

  ngOnInit() {
    //this.productId.push(sessionStorage.getItem('cart'));
      for(var i=sessionStorage.getItem('cart').length;i>=0;i--){
        this.buyedProduct[i]=JSON.parse(sessionStorage.getItem('cart'))[i];
      }
      this.buyedProduct.forEach((val)=>{
        this.productId.push(val.productId);
        this.paymentDetail.amount=this.paymentDetail.amount + parseInt(val.productPrice);
        this.paymentDetail.productName= this.paymentDetail.productName + ", " + val.productName;
      })

      this.paymentDetail.email=this.currentUser.email;
      this.paymentDetail.phone=this.currentUser.phone;      
  }

  fetchUser(){
    this.userService.getUser().subscribe(resp=>{
      this.currentUser=resp;
    })
  }

  // fetchAmount(){
  //   this.buyedProduct.forEach((val)=>{
  //     this.amount=this.amount+parseFloat(val.productPrice);
  //     console.log(this.amount);
  //   })
  //   this.form.amount=this.amount;
  // }

  sayHello() {
    alert("Hello SG");
  }

  paymentId: string | undefined;
  error: string | undefined;
  
  options = {
    "key": "",
    "amount": "", 
    "name": "sumanth",
    "order_id":"",
    "handler": function (response: any){
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
      this.orderService.createOrder(this.paymentDetail,this.productId).subscribe(
        data => {
          this.options.key = data.secretId;
          this.options.order_id = data.razorpayOrderId;
          this.options.amount = data.applicationFee; //paise
          this.options.prefill.name = this.currentUser.firstName as string;
          this.options.prefill.email = this.currentUser.email as string;
          this.options.prefill.contact = this.currentUser.phone as string;
          
          if(data.psName ==='razor2') {
            var rzp1 = new Razorpay(this.options);
            rzp1.open();
          } else {
            var rzp2 = new Razorpay(this.options);
            rzp2.open();
          }
         
                  
          rzp1.on('payment.failed',  (response: { error: { code: any; description: any; source: any; step: any; reason: any; metadata: { order_id: any; payment_id: any; }; }; }) =>{    
            
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
    }
}





