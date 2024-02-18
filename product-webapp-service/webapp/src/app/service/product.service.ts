import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from 'src/app/model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private cartItems: Product[] = [];
  convertJsonToFormData(jsonData: any): FormData {
    const formData = new FormData();
    
    for (const [key, value] of Object.entries(jsonData)) {
      if (value instanceof Blob) {
        formData.append(key, value);
      } else {
        formData.append(key, String(value));
      }
    }
  
    return formData;
  }

  // private baseUrl = 'http://localhost:8082/product'; //backend URL

  constructor(private http: HttpClient) { }

  addProduct(product: Product) {
    const username=sessionStorage.getItem('username');
    return this.http.post(`http://localhost:9999/product/addProduct/${username}`, product);
  }

  viewAllProducts(): Observable<any>{
  // viewAllProducts(): Observable<any> { //like sir
    // viewAllProducts(): Observable<Product[]> {
    return this.http.get(`http://localhost:9999/product/viewAll`);
  }

  getProductByProductId(productId: string): Observable<any> {
    console.log("From product service"+ productId)
    return this.http.get(`http://localhost:9999/product/${productId}`);
  }

  getProductByProductName(productName: string): Observable<any> {
    return this.http.get(`http://localhost:9999/product/name/${productName}`);
  }

  // updateProduct(product: Product): Observable<Product> {
  //   return this.http.put<Product>(`${this.baseUrl}/update`, product);
  // }

  // deleteProductById(productId: string): Observable<string> {
  //   return this.http.delete<string>(`${this.baseUrl}/deleteproduct/${productId}`);
  // }



  //cart
  addToCart(product: Product) {
    this.cartItems.push(product);
  }

  // Remove from Cart
  removeFromCart(product: Product) {
    const index = this.cartItems.findIndex(item => item.productId === product.productId);
    if (index !== -1) {
      this.cartItems.splice(index, 1);
    }
  }

  // Get Cart Items
  getCartItems(): Product[] {
    return this.cartItems;
  }
  

}