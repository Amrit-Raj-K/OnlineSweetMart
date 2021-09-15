import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder,  FormGroup,  Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Cart } from '../Models/cart';

import { ProductCategory } from '../Models/product-category';
import { Sweet } from '../Models/sweet';
import { CartService } from '../Services/cart.service';
import { CustomerService } from '../Services/customer.service';
import { ProductcategoryService } from '../Services/productcategory.service';
import { SweetService } from '../Services/sweet.service';



@Component({
  selector: 'app-cartupdate',
  templateUrl: './cartupdate.component.html',
  styleUrls: ['./cartupdate.component.css']
})
export class CartupdateComponent implements OnInit {

  public cartForm:FormGroup;
public data;
public sweetItemCount:number;
public total:number;
public price:number;

 
customerId= Number(sessionStorage.getItem('customerId').toString());
cartId=Number(sessionStorage.getItem('cartId').toString());
  quantity: number;
  totalp:number;
  constructor(private fb: FormBuilder, private service: CartService,private customerservice: CustomerService,private Categoryservice:ProductcategoryService,private sweetservice:SweetService, private router: Router) { }

  keyFunc(x) { // appending the updated value to the variable
   var pr=this.cartForm.controls.price.value
        this.quantity = x.target.value ;
        this.totalp=this.quantity*pr; 
    this.cartForm.get('total').setValue(this.totalp)
  }






  ngOnInit(): void {
  
    this.cartForm = this.fb.group({
     
      cartId: [this.cartId, Validators.required],
      grandTotal: ['', Validators.required],
      total : [' ', Validators.required],
      sweetItemCount:[ '', Validators.required],
      
      sweetItemId: ['', Validators.required],
      sweetItemName: ['', Validators.required],
      price: ['', Validators.required],
      image:['', Validators.required],

      categoryId: ['', Validators.required],
      name: ['', Validators.required],
      description: ['', Validators.required],
    
      
    });
    this.sweetservice.getSweetId(+localStorage.getItem("forcartupdateid"))
    .subscribe( data1 => {

      this.cartForm.patchValue(data1);
        });    
    this.Categoryservice.getCateoryId(+localStorage.getItem("editCategoryId"))
    .subscribe( data2 => {

       this.cartForm.patchValue(data2);
   
    });
 
    this.service.getCartId(this.cartId)
     .subscribe( data => {    
      this.cartForm.patchValue(data);

    }); 


this.total=this.data.sweetItemCount
console.log(this.data)


    }
    updateCart() {

      if (this.cartForm.invalid) {
        return;
      }

      let productCategory:ProductCategory = new ProductCategory(this.cartForm.controls.categoryId.value, 
        this.cartForm.controls.name.value, this.cartForm.controls.description.value);

      let sweet:Sweet = new Sweet(this.cartForm.controls.sweetItemId.value, 
        this.cartForm.controls.sweetItemName.value, 
        this.cartForm.controls.price.value, this.cartForm.controls.image.value,productCategory);
   
  let cart: Cart = new Cart(this.cartForm.controls.cartId.value,this.cartForm.controls.grandTotal.value,  
    this.cartForm.controls.total.value,this.cartForm.controls.sweetItemCount.value,sweet);  


    this.service.updatecart(this.cartId,cart)
 
    .subscribe(
    
      data => { 
        this.router.navigate(['addorder']);
      },
      error => {
        console.log('error in update cart :' +error);
     
      });
}

}