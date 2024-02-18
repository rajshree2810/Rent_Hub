import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductDescriptionService } from 'src/app/service/product-description.service';
import { ProductService } from 'src/app/service/product.service';
import { Product } from 'src/app/model/product';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-product-description',
  templateUrl: './product-description.component.html',
  styleUrls: ['./product-description.component.css']
})
export class ProductDescriptionComponent implements OnInit{
  cartItems: Product[] = []; 
  product: Product;
  productId:string='';

  constructor(private route: ActivatedRoute, private productService: ProductService,private router:Router) {
    this.getProductByProductId();
  }

  ngOnInit(): void {
    this.productId=this.route.snapshot.params["productId"]
    console.log(this.productId);
    this.getProductByProductId();
  }
  public getProductByProductId(){
    this.productService.getProductByProductId(this.productId).subscribe(resp=>{
      this.product=resp;
      console.log(this.product);
    })
  }

  routeToPayment(){
    this.cartItems.push(this.product);
    sessionStorage.setItem('cart',JSON.stringify(this.cartItems));
    this.router.navigate(['/payment/pay']);
  }
  addToCart(product: Product) {
    this.cartItems.push(product); // Add the selected product to the cart
  }
  addToCartAndNavigateToCart() {
    this.productService.addToCart(this.product); // Add to cart
    this.router.navigate(['/cart']); // Navigate to cart page
  }

  removeFromCart(product: Product) {
    const index = this.cartItems.findIndex(item => item.productId === product.productId);
    if (index !== -1) {
      this.cartItems.splice(index, 1);
    }
  }


}

