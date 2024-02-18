import { Component } from '@angular/core';
import { ProductService } from 'src/app/service/product.service';
import { Product } from 'src/app/model/product';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

declare var cloudinary: any; // Declare the cloudinary variable

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent {


  ngOnInit(): void {
  }

  // isActiveRoute(routePath: string): boolean {
  //   return this.router.isActive(routePath, true);
  // }

  // saveAdminForm: FormGroup

  product : Product ;
  errorMsg: string = "Error occured while submission.";

  constructor(private formBuilder: FormBuilder, private productService: ProductService,private router: Router) {

    this.product = new Product() ;

  }

 addProductDetails() 
  {
    this.productService.addProduct(this.product).subscribe(resp => {
      console.log(this.product);
      alert("Data Saved");
      this.product = new Product ;
      this.router.navigate(['/product-list'])
    }, error => {
      console.log(this.errorMsg)
    });
  }

  openWidget() {
    cloudinary.openUploadWidget({
      cloudName: 'do8hhiqv7',
      uploadPreset: 'fyhcsm1a',
      sources: ['local', 'url'],
      showAdvancedOptions: true,
      cropping: 'server',
      multiple: false,
      defaultSource: 'local',
      styles: {
        palette: {
          window: '#FFFFFF',
          sourceBg: '#FFFFFF',
          windowBorder: '#90a0b3',
          tabIcon: '#0078ff',
          inactiveTabIcon: '#69778A',
          menuIcons: '#0078ff',
          link: '#0078ff',
          action: '#0078ff',
          inProgress: '#0078ff',
          complete: '#0078ff',
          error: '#ff0000',
          textDark: '#000000',
          textLight: '#FFFFFF'
        },
        fonts: {
          default: null,
          "'Poppins', sans-serif": {
            url: 'https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap',
            active: true
          }
        }
      }
    }, (error: any, result: any) => {
      if (!error && result && result.event === 'success') {
        this.product.productImageUrl = result.info.secure_url; // Update product imageUrl
      }
    });

  // saveForm: FormGroup

  // constructor(private formBuilder: FormBuilder, private productService: ProductService) {
  //   this.saveForm = this.formBuilder.group({
  //     productId: ['', Validators.required],
  //     productName: ['', Validators.required],
  //     productPrice: ['', Validators.required],
  //     productBrand: ['', Validators.required],
  //     productDescription: ['', Validators.required],
  //     // productAvailableTime: ['', Validators.required],
  //     // productAddress: ['', Validators.required],
  //     // productImage: ['']
    
  //   })
  // }

  // onSubmit(saveForm: any){
  //   console.log(this.saveForm.value);
  //   const FormData = this.productService.convertJsonToFormData(this.saveForm.value);
  //   this.productService.addProduct(FormData).subscribe(resp=>console.log('data Saved'));
  //   //return this.http.post('http://localhost:8082/api/v1/addproduct', saveForm)
  // }


  // registerUser(arg0: any) {
  //   throw new Error('Method not implemented.');
  // }

  // product: Product = {} as Product;

//   constructor(private productService: ProductService) { }

  // onFileChange(event: Event) {
  //   const target = event.target as HTMLInputElement;
  //   if (target.files && target.files.length > 0) {
  //     this.product.productImage = target.files[0];
  //   }
  // }

  // onSubmit() {
  //   const formData = new FormData();
  //   formData.append('file', this.product.productImage);
  //   formData.append('product', JSON.stringify(this.product));

  //   this.productService.addProduct(formData).subscribe(
  //     (response) => {
  //       console.log('Product added successfully:', response);
  //       //can display a success message or update the UI accordingly
  //     },
  //     (error) => {
  //       console.error('Error adding product:', error);
  //       // Handle errors and display appropriate messages
  //     }
  //   );
  // }

  

}
}