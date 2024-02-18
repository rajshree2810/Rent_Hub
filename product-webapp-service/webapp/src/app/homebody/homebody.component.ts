import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from 'src/app/service/product.service';
import { Product } from 'src/app/model/product';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'app-homebody',
  templateUrl: './homebody.component.html',
  styleUrls: ['./homebody.component.css']
})
export class HomebodyComponent implements OnInit{
  equipmentForm: FormGroup = new FormGroup({});
  isAddedToCart = false;
  searchClicked = false;
  tempProduct: Product;

  pId ?: string  = "" ;
  pName ?: string = "";
  productDetails: Product[] = [];
  constructor(private formBuilder: FormBuilder,private route: ActivatedRoute,
    private productService: ProductService, private router : Router) {}

  ngOnInit() {
    this.equipmentForm = this.formBuilder.group({
      equipment: ['', Validators.required],
      fromDate: ['', Validators.required],
      toDate: ['', Validators.required]
    });
}
onSubmit() {
  if (this.equipmentForm.valid) {
    // Here you can access the form data using this.equipmentForm.value
    console.log(this.equipmentForm.value);
    // You can perform any further actions with the form data, such as sending it to the server.
  }
}

 public getProductByProductId(){
    this.pId = (document.getElementById("tempProductId") as HTMLInputElement).value;
    this.productService.getProductByProductId(this.pId).subscribe(
      (resp) =>{
        this.tempProduct = resp;
        console.log(resp);
        this.searchClicked = true;
      },(error: HttpErrorResponse) =>{
        console.log(error);
      }
    );
  }


  

}
