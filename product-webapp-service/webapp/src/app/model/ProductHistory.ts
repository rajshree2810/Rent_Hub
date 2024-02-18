import { User } from "./User";
import { Product } from "./product";

export class ProductHistory{
    transaction_id:String;
    rentedProduct:Product
    customer:User;
    orderDate:string;
    deliveryDate:string;
    paymentId:string

    constructor(){
        this.transaction_id=''
        this.rentedProduct=new Product();
        this.customer=new User();
        this.orderDate='';
        this.deliveryDate='';
        this.paymentId='';
    }
}