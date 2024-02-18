import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { ProductDescriptionService } from 'src/app/service/product-description.service';
import { ProductService } from 'src/app/service/product.service';
import { Product } from 'src/app/model/product';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  product: Product;
  cartItems: Product[] = [];

  constructor(private route: ActivatedRoute, private productService: ProductService,private router:Router) { }

  ngOnInit(): void {
    this.cartItems = this.productService.getCartItems();
  }

  addToCart(product: Product) {
    this.cartItems.push(product);
  }

  removeFromCart(product: Product) {
    const index = this.cartItems.findIndex(item => item.productId === product.productId);
    if (index !== -1) {
      this.cartItems.splice(index, 1);
    }
  }

  getCartItems(): Product[] {
    return this.cartItems;
  }


  routeToPayment(){
    sessionStorage.setItem('cart',JSON.stringify(this.cartItems));
    this.router.navigate(['/payment/cart/']);
  }

 
}

