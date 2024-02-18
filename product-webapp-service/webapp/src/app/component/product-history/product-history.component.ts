import { transition } from '@angular/animations';
import { Component, ElementRef, OnInit,ViewChild} from '@angular/core';
import { ProductHistory } from 'src/app/model/ProductHistory';
import { ProducthistoryService } from 'src/app/service/producthistory.service';

@Component({
  selector: 'app-product-history',
  templateUrl: './product-history.component.html',
  styleUrls: ['./product-history.component.css']
})
export class ProductHistoryComponent implements OnInit{
  orderList:Array<ProductHistory>
  soldorderList:Array<ProductHistory>
  buyedorderList:Array<ProductHistory>

  ngOnInit(): void {
      this.fetchBuyedOrders();
      this.fetchSoldOrders();
      this.showAllOrder();
  }

  constructor(private producthistoryservice:ProducthistoryService){
    this.orderList=[];
    this.soldorderList=[];
    this.buyedorderList=[];
    this.showAllOrder();
  }

  showAllOrder(){
    this.fetchBuyedOrders();
    this.fetchSoldOrders();
    this.orderList=this.buyedorderList;
    console.log(this.soldorderList)
    this.soldorderList.forEach(resp=>{
      this.orderList.push(resp);
    })
  }

  showBuyedOrder(){
    this.fetchBuyedOrders();
    console.log(this.buyedorderList);
    this.orderList=this.buyedorderList;
  }


  fetchBuyedOrders(){
    this.producthistoryservice.buyedOrder().subscribe(resp=>{
      this.buyedorderList=resp;
    })
  }

  showSoldOrder(){
    this.fetchSoldOrders();
    console.log("Selled order"+this.soldorderList);
    this.orderList=this.soldorderList;
  }

  fetchSoldOrders(){
    this.producthistoryservice.soldOrder().subscribe(resp=>{
      this.soldorderList=resp;
    })
  }


  deleteOrder(transaction_id:String){
    this.producthistoryservice.deleteOrder(transaction_id).subscribe(resp=>{
      console.log('Product deleted ' + resp);
      window.location.reload();
    })
  }
}
