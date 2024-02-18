import { Component, OnInit } from '@angular/core';
import { ProductListService } from '../service/product-list.service';
import { productList } from '../model/ProductList';
import { Router, ActivatedRoute } from '@angular/router';
import { Product } from '../model/product';
import { ProductService } from '../service/product.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent {

  isAddedToCart = false;
  searchClicked = false;
  tempProduct: Product;
  productDetails: Product[] = [];
  searchedProductList:Array<Product>
  productName: string = '';
  pId ?: string  = "" ;
  pName ?: string = "";
  allProductCategories:Array<String>;
  categorySelected:number;
  filterAmountLessThan:string='0';

  //productDetails: Product[] = [];

  constructor(private route: ActivatedRoute,
    private productService: ProductService,
    private productListService:ProductListService, 
    private router : Router) {
      this.tempProduct = new Product;
      this.getAllProductCategories();
      this.categorySelected=0;
  }

  ngOnInit(): void {
    this.getProductDetails() ;
  }

  // public getProductDetails(): void {
  //   this.productService.viewAllProducts().subscribe(
  //     (product) => {
  //       this.productDetails = product;
  //     }, (error: HttpErrorResponse) => {
  //       console.log(error);
  //     });
  // }

  public getProductDetails(): void {
    this.productService.viewAllProducts().subscribe(
      (product) => {
        this.productDetails = product;
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }


  // routeToAddProduct(){
  //   this.router.navigate(['product-dis/:productName']);
  // }

  public getProductByProductName() {
    this.searchClicked = true;
    const productName = this.productName;
    console.log(productName);
    if (!productName) {
      console.log('Product name is empty');
      this.searchedProductList = null; // Clear previous search result
      window.location.reload();
    }
    
    this.productService.getProductByProductName(productName).subscribe(resp=>{
      this.searchedProductList=resp;
    },err=>{
      console.log("Error occured in fetching data");
    })

    // this.productService.getProductByProductName(productName).subscribe(
    //   (resp) => {
    //     this.searchedProduct = resp;
    //   },
    //   (error: HttpErrorResponse) => {
    //     console.log(error);
    //   }
    // );
  }

  getAllProductCategories(){
    this.productListService.getAllCategory().subscribe(resp=>{
      this.allProductCategories=resp;
      console.log(this.allProductCategories);
    })
  }

  filterByCategories(event:any){
    //event.target.checked
    if(event.target.checked){
      this.productListService.filterCategory(event.target.value).subscribe((resp:Array<Product>)=>{
        this.searchClicked = true;
        if(this.categorySelected==0)this.searchedProductList=[];
        resp.forEach(values=>{
          this.searchedProductList.push(values);
        })
        this.searchedProductList.sort().reverse();
        this.categorySelected++;
        console.log(resp);
      })
    }else{
      this.productListService.filterCategory(event.target.value).subscribe((resp:Array<Product>)=>{
        this.searchClicked = true;
        resp.forEach(values=>{
          this.searchedProductList=this.searchedProductList.filter(item=>item.productId!=values.productId)
        })
        console.log(this.searchedProductList);
        console.log("ubcheked")
      })
    }
  }

  filterByAmountLessThan(event:any){
    this.productListService.filterAmountLessThan(event.target.value).subscribe(resp=>{
      this.searchClicked=true;
      this.searchedProductList=[];
      this.searchedProductList=resp;
    })
  }
  filterByAmountMoreThan(event:any){
    this.productListService.filterAmountMoreThan(event.target.value).subscribe(resp=>{
      this.searchClicked=true;
      this.searchedProductList=[];
      this.searchedProductList=resp;
    })
  }

  clearSearch() {
    this.searchClicked = false;
    this.productName = '';
    this.searchedProductList = null;
  }

  routeToAddProduct(selectedProduct: Product) {
    if (selectedProduct) {
      this.router.navigate(['/product-dis', selectedProduct.productId]);
    }
  }
    

}



