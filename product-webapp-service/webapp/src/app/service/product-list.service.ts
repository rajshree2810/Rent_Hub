import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { productList } from '../model/ProductList';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductListService {
  private apiUrl = 'http://localhost:9999/';

  constructor(private http: HttpClient) { }

  getAllproduct():Observable<any>{
    return this.http.get<any>(`http://localhost:9999/product/viewall`);
  }

  checkProductExists(productName: string): Observable<{ exists: boolean }> {
    const url = `${this.apiUrl}/exists/${productName}`;
    return this.http.get<{ exists: boolean }>(url);
  }

  // getProductsByName(productName: string): Observable<productList[]> {
  //   const url = `${this.apiUrl}/search/${productName}`;
  //   return this.http.get<productList[]>(url);
  // }

  getProductsByName(productName: string): Observable<any> {
    return this.http.get(`http://localhost:9999/product/productName/` + productName);
  }

  getProductsByCategory(category: string): Observable<any> {
    return this.http.get('http://localhost:9999/product/category/' + category);
  }

  getAllCategory():Observable<any>{
    return this.http.get('http://localhost:9999/product/listcategories');
  }

  filterCategory(category:string):Observable<any>{
    console.log(category);
    return this.http.get('http://localhost:9999/product/filterCategory',{headers:{
      categoryheader:category
    }})
  }

  filterAmountLessThan(amount:string):Observable<any>{
    return this.http.get(`http://localhost:9999/product/filterbyamountlessthan/${amount}`);
  }

  filterAmountMoreThan(amount:string):Observable<any>{
    return this.http.get(`http://localhost:9999/product/filterbyamountmorethan/${amount}`);
  }

  

}