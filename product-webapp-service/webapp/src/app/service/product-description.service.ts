import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../model/product';
import { ProductService } from './product.service';

@Injectable({
  providedIn: 'root'
})
export class ProductDescriptionService {
  
  private baseUrl = 'http://localhost:9999';

  constructor(private http: HttpClient) { }
  
  getProductByProductId(productId: string): Observable<Product> {
    return this.http.get<Product>(`${this.baseUrl}/product/${productId}`);
  }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/product/viewAll`);
  }
}