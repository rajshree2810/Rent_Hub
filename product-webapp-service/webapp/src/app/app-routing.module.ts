import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SellbodyComponent } from './sellbody/sellbody.component';
import { HomebodyComponent } from './homebody/homebody.component';
import {ProductListComponent} from './product-list/product-list.component'
//import { AddproductComponent } from './addproduct/addproduct.component';
import { BrowserModule } from '@angular/platform-browser';
import { BlogFormComponent } from './blog-form/blog-form.component';
import { RegisterComponent } from './component/register/register/register.component';
import { LoginComponent } from './component/login/login.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { ChatComponent } from './chat/chat.component';
import { UserChatComponent } from './userchat/userchat.component';
import { ChatMainComponent } from './chatmain/chatmain.component';
import { ProfilepageComponent } from './component/profilepage/profilepage.component';
import { ProductHistoryComponent } from './component/product-history/product-history.component';
import { AddProductComponent } from './component/add-product/add-product.component';
import { PaymentComponent } from './payment/payment.component';
import { LoggedInGuard } from './guards/logged-in.guard';
import { ProductDescriptionComponent } from './component/product-description/product-description.component';
import { CartComponent } from './cart/cart.component';
import { MakepaymentComponent } from './component/makepayment/makepayment.component';
const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  // Define your other routes here
  //{ path: '', redirectTo: '/sell', pathMatch: 'full' },
  {path:'home',component:HomebodyComponent},
  { path: 'sell', component: SellbodyComponent,canActivate:[LoggedInGuard]},
  { path: 'add-blog', component: BlogFormComponent,canActivate:[LoggedInGuard]},
  {path:'register',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  {path:'forgotpassword',component:ForgotPasswordComponent},
  {path:'forgotpassword/:state/:userId',component:ForgotPasswordComponent},
  {path:'profilepage',component:ProfilepageComponent,canActivate:[LoggedInGuard]},
  {path:'productHistory',component:ProductHistoryComponent,canActivate:[LoggedInGuard]},
  {path:'product',component:AddProductComponent,canActivate:[LoggedInGuard]},
  {path:'payment/pay',component:MakepaymentComponent,canActivate:[LoggedInGuard]},
  {path:'payment/cart',component:MakepaymentComponent,canActivate:[LoggedInGuard]},
  { path: 'chat', component: ChatComponent,canActivate:[LoggedInGuard]},
  { path: 'user', component: UserChatComponent,canActivate:[LoggedInGuard]},
  { path: 'main', component: ChatMainComponent,canActivate:[LoggedInGuard]},
  {path: 'product-list', component:ProductListComponent },
  //{path:'chat/:receiverId',component:ChatComponent}
  //{ path: 'add', component: AddproductComponent }
  {path: 'product-list', component:ProductListComponent },
  {path:'product-dis',component:ProductDescriptionComponent},
  {path:'product-dis/:productId',component:ProductDescriptionComponent},
  {path:'cart',component:CartComponent}
];
@NgModule({
  imports: [RouterModule.forRoot(routes),BrowserModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
