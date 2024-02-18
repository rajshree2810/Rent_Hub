import { User } from "./User";

export class Product {
    // productImage: File;
    productId: string;
    productName?: string;
    productPrice?: string;
    productBrand?: string;
    productDescription?: string;
    sellerEmail?: string;
    startDate?:  Date ;
    endDate?:Date;
    productCategory?: string;
    productImageUrl?: string;
    sellerDetails:User;

    constructor(){
      this.sellerDetails=new User();
      this.productId='';
      this.productName='';
      this.productPrice='';
      this.productBrand='';
      this.productDescription='';
    }
  }
  