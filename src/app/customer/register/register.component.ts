import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Cart } from 'src/app/Models/cart';
import { Customer } from 'src/app/Models/customer';
import { ProductCategory } from 'src/app/Models/product-category';
import { Sweet } from 'src/app/Models/sweet';
import { CustomerService } from 'src/app/Services/customer.service';
import { SweetService } from 'src/app/Services/sweet.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  public registerForm: FormGroup;
  constructor(private fb: FormBuilder, private service: CustomerService , private sweet:SweetService, private router: Router) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      customerId: ['1', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      city: ['',[Validators.required, Validators.pattern("^[a-z A-Z]*$")]],

      contactNumber: ['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      zip: ['',[ Validators.required,Validators.pattern("^[1-9]{1}[0-9]{5}"),Validators.minLength(6),Validators.maxLength(6)]],
      customername : [ '', [Validators.required,Validators.pattern("^[a-z A-Z]*$")]],
     

      cartId: ['1', Validators.required],
      grandTotal:[ ,],
      total : [ ,],
      sweetItemCount:[ ,],
      
      sweetItemId : [,],
      sweetItemName: [,],
      price: [,],
      image:[,],
      type:['2', Validators.required],
      
      categoryId: [,],
      name: [,],
      description: [,],


    });

  }



  createCustomer() {


     if (this.registerForm.invalid) {
       return;
     }


   let productCategory:ProductCategory = new ProductCategory(this.registerForm.controls.categoryId.value, 
    this.registerForm.controls.name.value, this.registerForm.controls.description.value);

  let sweetItems:Sweet = new Sweet(this.registerForm.controls.sweetItemId.value, 
    this.registerForm.controls.sweetItemName.value, 
    this.registerForm.controls.price.value, this.registerForm.controls.image.value,productCategory);
     
    let cart: Cart = new Cart(this.registerForm.controls.cartId.value,this.registerForm.controls.grandTotal.value,  
      this.registerForm.controls.total.value,this.registerForm.controls.sweetItemCount.value,sweetItems);


    

    let customer:Customer = new Customer(this.registerForm.controls.customerId.value, 
      this.registerForm.controls.username.value, 
      this.registerForm.controls.password.value,this.registerForm.controls.city.value,this.registerForm.controls.contactNumber.value,  
      this.registerForm.controls.zip.value,this.registerForm.controls.customername.value,cart ,this.registerForm.controls.type.value);
      alert("before add")
      this.service.registerCustomer(customer)   
      //.pipe(first())
      .subscribe(
        data => { alert("Account is successfully Registered")
 
          this.router.navigate(['login']);
        },
        error => {
          alert(error);
        });     

      }
}
