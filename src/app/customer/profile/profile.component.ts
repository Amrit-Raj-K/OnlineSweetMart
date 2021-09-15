import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { Cart } from 'src/app/Models/cart';

import { Customer } from 'src/app/Models/customer';
import { ProductCategory } from 'src/app/Models/product-category';
import { Sweet } from 'src/app/Models/sweet';

import { CustomerService } from 'src/app/Services/customer.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
 custname: string;
 custData:Customer;
  
  public customerForm: FormGroup;
  id : number = Number(sessionStorage.getItem("customerId"));//url update

  customerId= Number(sessionStorage.getItem('customerId').toString());//get method by id

  constructor(private fb: FormBuilder, private service: CustomerService , private router: Router) {
  }

  ngOnInit() {
    let res:any;
    this.service.getCustomerById(this.customerId)
    .subscribe( data => {    
     this.customerForm.patchValue(data)
    
    this.customerForm = this.fb.group({
      customerId: [data.customerId, Validators.required],
      username: [data.username, Validators.required],
      password: [data.password, Validators.required],
      city: [data.city, [Validators.required,Validators.pattern("^[a-z A-Z]*$"),Validators.minLength(4)]],

      contactNumber: [data.contactNumber, [Validators.required,Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      zip: [data.zip, [Validators.required,Validators.pattern("^[1-9]{1}[0-9]{5}")]],
      customername : [ data.customername, [Validators.required,Validators.pattern("^[a-z A-Z]*$"),Validators.minLength(3)]],     
     
      cartId: [data.cart.cartId, Validators.required],
      grandTotal: [data.cart.grandTotal, Validators.required],
        total : [data.cart.total, Validators.required],
        sweetItemCount:[data.cart.sweetItemCount, Validators.required],
      
      sweetItemId : [data.cart.sweetItems.sweetItemId, Validators.required],
        sweetItemName: [data.cart.sweetItems.sweetItemName, Validators.required], 
        price: [data.cart.sweetItems.price, Validators.required],
    
      image:[data.cart.sweetItems.image, Validators.required],
      type:[data.type, Validators.required],


      categoryId: [data.cart.sweetItems.category.categoryId, Validators.required],
      name: [data.cart.sweetItems.category.name, Validators.required],
      description: [data.cart.sweetItems.category.description, Validators.required],


    });
    });




   this.customerForm = this.fb.group({
      customerId: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      city: ['', [Validators.required,Validators.pattern("^[a-z A-Z]*$"),Validators.minLength(4)]],

      contactNumber: ['', [Validators.required,Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      zip: ['', [Validators.required,Validators.pattern("^[1-9]{1}[0-9]{5}")]],
      customername : [ '', [Validators.required,Validators.pattern("^[a-z A-Z]*$"),Validators.minLength(3)]],
     

      cartId: ['', Validators.required],
      grandTotal: ['', Validators.required],
      total : [ '', Validators.required],
      sweetItemCount:[ '', Validators.required],
      
      sweetItemId : ['', Validators.required],
      sweetItemName: ['', Validators.required],
      price: ['', Validators.required],
      image:['', Validators.required],
      type:[ '', Validators.required],
      
      categoryId: ['', Validators.required],
      name: ['', Validators.required],
      description: ['', Validators.required],


    });

   


    }
    
  updateCustomer() {

    if (this.customerForm.invalid) {
      return;
    }

  

   let productCategory:ProductCategory = new ProductCategory(this.customerForm.controls.categoryId.value, 
    this.customerForm.controls.name.value, this.customerForm.controls.description.value);

  let sweetItems:Sweet = new Sweet(this.customerForm.controls.sweetItemId.value, 
    this.customerForm.controls.sweetItemName.value, 
    this.customerForm.controls.price.value, this.customerForm.controls.image.value,productCategory);
     
    let cart: Cart = new Cart(this.customerForm.controls.cartId.value,this.customerForm.controls.grandTotal.value,  
      this.customerForm.controls.total.value,this.customerForm.controls.sweetItemCount.value,sweetItems);


    

    let customer:Customer = new Customer(this.customerForm.controls.customerId.value, 
      this.customerForm.controls.username.value, 
      this.customerForm.controls.password.value,this.customerForm.controls.city.value,this.customerForm.controls.contactNumber.value,  
      this.customerForm.controls.zip.value,this.customerForm.controls.customername.value,cart ,this.customerForm.controls.type.value);
      
    

    this.service.updateCustomer(this.id,customer)
      //.pipe(first())
      .subscribe(
      
        data => {
          this.router.navigate(['showprofile']);
        },
        error => {
          console.log('error in update customer:' +error);
        });
  }
}