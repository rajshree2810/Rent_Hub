import { Component, HostListener } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { OrderServiceService } from './order-service.service';
import { AuthenticationService } from './service/authentication.service';

declare var Razorpay: any; 

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'webapp';
  // isLoggedIn: boolean = false;

  // form: any = {}; 
  // constructor(private http: HttpClient,
  //   private orderService:OrderServiceService,private authService: AuthenticationService) {
  //     this.checkLoginStatus();
  // }
  // checkLoginStatus(): void {
  //   this.isLoggedIn = this.authService.isAuthenticated();
  // }

  // ngOnInit() {
    

  // }

  // sayHello() {
  //   alert("Hello SG");
  // }

  // paymentId: string | undefined;
  // error: string | undefined;
  
  // options = {
  //   "key": "",
  //   "amount": "", 
  //   "name": "sumanth",
  //   "order_id":"",
  //   "handler": function (response: any){
  //     var event = new CustomEvent("payment.success", 
  //       {
  //         detail: response,
  //         bubbles: true,
  //         cancelable: true
  //       }
  //     );	  
  //     window.dispatchEvent(event);
  //   }
  //   ,
  //   "prefill": {
  //   "name": "",
  //   "email": "",
  //   "contact": ""
  //   },
  //   "notes": {
  //   "address": ""
  //   },
  //   "theme": {
  //   "color": "#3399cc"
  //   }
  //   };
  
  //   onSubmit(): void {
  //     this.paymentId = ''; 
  //     this.error = ''; 
  //     this.orderService.createOrder(this.form).subscribe(
  //     data => {
  //       this.options.key = data.secretId;
  //       this.options.order_id = data.razorpayOrderId;
  //       this.options.amount = data.applicationFee; //paise
  //       this.options.prefill.name = "sumanth";
  //       this.options.prefill.email = "sumanth@gmail.com";
  //       this.options.prefill.contact = "999999999";
        
  //       if(data.psName ==='razor2') {
  //         var rzp1 = new Razorpay(this.options);
  //         rzp1.open();
  //       } else {
  //         var rzp2 = new Razorpay(this.options);
  //         rzp2.open();
  //       }
       
                
  //       rzp1.on('payment.failed',  (response: { error: { code: any; description: any; source: any; step: any; reason: any; metadata: { order_id: any; payment_id: any; }; }; }) =>{    
          
  //         console.log(response);
  //         console.log(response.error.code);    
  //         console.log(response.error.description);    
  //         console.log(response.error.source);    
  //         console.log(response.error.step);    
  //         console.log(response.error.reason);    
  //         console.log(response.error.metadata.order_id);    
  //         console.log(response.error.metadata.payment_id);
  //         this.error = response.error.reason;
  //       }
  //       );
  //     }
  //     ,
  //     err => {
  //       this.error = err.error.message;
  //     }
  //     );
  //   }

  //   @HostListener('window:payment.success', ['$event']) 
  //   onPaymentSuccess(event: { detail: any; }): void {
  //      console.log(event.detail);
  //   }


}




