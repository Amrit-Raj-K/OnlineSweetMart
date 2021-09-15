import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { LoginComponent } from 'src/app/login/login.component';
import { CustomerHttpInterceptor } from 'src/customer-http.interceptor';
import { AppRoutingModule } from './app-routing.module';
import { AdminCanActivateGuardService } from './Guards/Admin-can-activate-guard-service';
import { UserCanActivateGuardService } from './Guards/User-can-activate-guard-service';
import { SweetitemComponent } from './sweetitem/sweetitem.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddsweetComponent } from './sweetitem/addsweet/addsweet.component';
import { DeletesweetComponent } from './sweetitem/deletesweet/deletesweet.component';
import { UpdatesweetComponent } from './sweetitem/updatesweet/updatesweet.component';
import { RegisterComponent } from './customer/register/register.component';
import { ProfileComponent } from './customer/profile/profile.component';
import { CustomerComponent } from './customer/customer/customer.component';
import { CartupdateComponent } from './Cart/cartupdate.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { OrderbillComponent } from './orderbill/orderbill.component';
import { SweetfilterPipe } from './sweetitem/sweetfilter.pipe';
import { ListordersComponent } from './sweetorder/listorders/listorders.component';
import { ShoworderComponent } from './sweetorder/showorder/showorder.component';
import { AddorderComponent } from './sweetorder/addorder/addorder.component';
import { SweetnullPipe } from './sweetitem/sweetnull.pipe';
import { ProductFilterPipe } from './ProductCategory/product-filter.pipe';
import { AddProductComponent } from './ProductCategory/add-product/add-product.component';
import { MaintainProductComponent } from './ProductCategory/maintain-product/maintain-product.component';
import { UpdateProductComponent } from './ProductCategory/update-product/update-product.component';
import { ProductcategoryComponent } from './ProductCategory/productcategory/productcategory.component';
import { AppComponent } from './app.component';
import { AdminComponent } from './admin/admin.component';
import { UnsavedChangesGuard } from './Guards/unsaved-changes.guard';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SweetitemComponent,
    AddsweetComponent,
    DeletesweetComponent,
    UpdatesweetComponent,
    ProfileComponent,
    CustomerComponent,
    RegisterComponent,
    CartupdateComponent,
    HomeComponent,
    AboutComponent,
    ContactUsComponent,
    OrderbillComponent,
    AddProductComponent,
    MaintainProductComponent,
    UpdateProductComponent,
    ProductFilterPipe,
    ProductcategoryComponent,
    SweetfilterPipe,
    ListordersComponent,
    ShoworderComponent,
    AddorderComponent,
    SweetnullPipe,
   AdminComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NgbModule 

  ],
  providers: [ 
    AdminCanActivateGuardService,   UserCanActivateGuardService,UnsavedChangesGuard,
    {provide: HTTP_INTERCEPTORS, useClass: CustomerHttpInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
