import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
//import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';

// import { LoginComponent } from './login/login.component';
// import { RegisterComponent } from './register/register.component';
import { HeaderComponent } from './header/header.component';
import { HomebodyComponent } from './homebody/homebody.component';
import { SellbodyComponent } from './sellbody/sellbody.component';
//import { AddproductComponent } from './addproduct/addproduct.component';
//import { NgModule } from '@angular/core';
import { BlogFormComponent } from './blog-form/blog-form.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PaymentComponent } from './payment/payment.component';
import { RegisterComponent } from './component/register/register/register.component';
import { LoginComponent } from './component/login/login.component';
//import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { ChatComponent } from './chat/chat.component';
import { ChatMainComponent } from './chatmain/chatmain.component';
import { UserChatComponent } from './userchat/userchat.component';
import {MatSelectModule} from '@angular/material/select';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableModule } from '@angular/material/table';
import { MatListModule } from '@angular/material/list';
import {MatInputModule} from '@angular/material/input';
import {MatExpansionModule} from '@angular/material/expansion';
import { ProfilepageComponent } from './component/profilepage/profilepage.component';
import { ProductHistory } from './model/ProductHistory';
import { AddProductComponent } from './component/add-product/add-product.component';
import { ProductHistoryComponent } from './component/product-history/product-history.component';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductDescriptionComponent } from './component/product-description/product-description.component';
import { CommonModule } from '@angular/common';
import { CartComponent } from './cart/cart.component';
import { MakepaymentComponent } from './component/makepayment/makepayment.component';
//import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
@NgModule({
  declarations: [
    AppComponent,
    
    FooterComponent,
    HeaderComponent,
    HomebodyComponent,
    SellbodyComponent,
    BlogFormComponent,
    PaymentComponent,
    RegisterComponent,
    LoginComponent,
    ForgotPasswordComponent,
    ChatComponent,
    ChatMainComponent,
    UserChatComponent,
    ProfilepageComponent,
    ProductHistoryComponent,
    AddProductComponent,
    ProductListComponent,
    ProductDescriptionComponent,
    CartComponent,
    MakepaymentComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
   
    MatButtonModule,
    MatExpansionModule,
    MatListModule,
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatSelectModule,
    MatTableModule,
    CommonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
