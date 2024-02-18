import { BankDetail } from "./BankDetail";

export class User{
    userId:String;
    password:String;
    firstName:String;
    lastName:String;
    email:String;
    address:String;
    description:String;
    imageUrl:String;
    gender:String;
    phone:String;
    dateofbirth:String;
    bankDetail:BankDetail;

    constructor(){
        this.userId='';
        this.password='';
        this.firstName='';
        this.lastName='';
        this.email='';
        this.address='';
        this.description='';
        this.imageUrl='';
        this.gender='';
        this.phone='';
        this.dateofbirth='';
        this.bankDetail=new BankDetail();
    }
}