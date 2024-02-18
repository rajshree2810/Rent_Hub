export class PaymentDetail{
    productName:String;
    amount:number;
    email:String;
    phone:String;

    constructor(){
        this.productName='';
        this.amount=0;
        this.email='';
        this.phone='';
    }
}