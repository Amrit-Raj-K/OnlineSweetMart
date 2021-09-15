import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from 'src/app/login/login.component';
import { AboutComponent } from './about/about.component';
import { AdminComponent } from './admin/admin.component';
import { CartupdateComponent } from './Cart/cartupdate.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { CustomerComponent } from './customer/customer/customer.component';
import { ProfileComponent } from './customer/profile/profile.component';
import { RegisterComponent } from './customer/register/register.component';
import { AdminCanActivateGuardService } from './Guards/Admin-can-activate-guard-service';
import { UnsavedChangesGuard } from './Guards/unsaved-changes.guard';
import { UserCanActivateGuardService } from './Guards/User-can-activate-guard-service';
import { HomeComponent } from './home/home.component';
import { OrderbillComponent } from './orderbill/orderbill.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AddProductComponent } from './ProductCategory/add-product/add-product.component';
import { MaintainProductComponent } from './ProductCategory/maintain-product/maintain-product.component';
import { ProductcategoryComponent } from './ProductCategory/productcategory/productcategory.component';
import { UpdateProductComponent } from './ProductCategory/update-product/update-product.component';
import { AddsweetComponent } from './sweetitem/addsweet/addsweet.component';
import { DeletesweetComponent } from './sweetitem/deletesweet/deletesweet.component';
import { SweetitemComponent } from './sweetitem/sweetitem.component';
import { UpdatesweetComponent } from './sweetitem/updatesweet/updatesweet.component';
import { AddorderComponent } from './sweetorder/addorder/addorder.component';
import { ListordersComponent } from './sweetorder/listorders/listorders.component';
import { ShoworderComponent } from './sweetorder/showorder/showorder.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path:'home', component: HomeComponent},
  { path:'login', component: LoginComponent},
  { path:'about', component: AboutComponent},
  { path:'contact', component: ContactUsComponent},
  { path:'sweetupdate', component: UpdatesweetComponent,
  canActivate: [ AdminCanActivateGuardService]
},
  { path:'sweet-add', component: AddsweetComponent,
  canActivate: [ AdminCanActivateGuardService]
},
  { path:'deletesweet', component: DeletesweetComponent,
  canActivate: [ AdminCanActivateGuardService]
},
  { path:'addproduct', component: AddProductComponent,
  canActivate: [ AdminCanActivateGuardService]
},
  { path:'maintain', component:MaintainProductComponent,
  canActivate: [ AdminCanActivateGuardService]
},
  { path:'update', component: UpdateProductComponent,
  canActivate: [ AdminCanActivateGuardService]
},
  { path:'category', component: ProductcategoryComponent},
  { path:'showorder', component:ListordersComponent,
  canDeactivate: [UnsavedChangesGuard],
},
  { path:'showallorders', component: ShoworderComponent,
  canActivate: [ UserCanActivateGuardService]
},
  { path:'addorder', component: AddorderComponent,
  canActivate: [ UserCanActivateGuardService]
},
  { path:'register', component: RegisterComponent},
  { path:'sweet', component: SweetitemComponent},
  { path:'admin', component: AdminComponent,
  canActivate: [ AdminCanActivateGuardService]
},
{ path:'showprofile', component: CustomerComponent},
{ path:'editprofile', component: ProfileComponent},
{ path:'cartupdate', component: CartupdateComponent,
canActivate: [ UserCanActivateGuardService]
},
{ path:'orderbill', component: OrderbillComponent},
  //Used to show the page not found if any of the incorrect route address is selected
{path: '404', component: PageNotFoundComponent},
{path: '**', redirectTo: '/404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {


 }
