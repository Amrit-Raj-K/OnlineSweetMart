import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {  Router } from '@angular/router';
import { ProductCategory } from 'src/app/Models/product-category';
import { Sweet } from 'src/app/Models/sweet';
import { ProductcategoryService } from 'src/app/Services/productcategory.service';
import { SweetService } from 'src/app/Services/sweet.service';

@Component({
  selector: 'app-updatesweet',
  templateUrl: './updatesweet.component.html',
  styleUrls: ['./updatesweet.component.css']
})
export class UpdatesweetComponent implements OnInit {



      public sweetForm: FormGroup;
id:number =Number(localStorage.getItem("editSweetId"))
      constructor(private fb: FormBuilder, private service: SweetService, private router: Router,private Categoryservice: ProductcategoryService) {
      }
      
      ngOnInit() {
     
    //form group for  the sweet Items
        this.sweetForm = this.fb.group({
          sweetItemId: [this.id, Validators.required],
          sweetItemName: ['', [Validators.required,Validators.pattern("^[a-z A-Z]*$")]],
          price: ['', [Validators.required,Validators.pattern("^[0-9]+")]],
          categoryId: ['', Validators.required],
          name: ['', Validators.required],
          description: ['', Validators.required],
        image:['', Validators.required],
        });
    
        this.service.getSweetId(+localStorage.getItem("editSweetId"))
          .subscribe( data => {
            this.sweetForm.patchValue(data);
          });
          this.Categoryservice.getCateoryId(+localStorage.getItem("editCategoryId"))
          .subscribe( data => {
           
            this.sweetForm.patchValue(data);
          });
       
      }
    
      updateSweet() {
    
        if (this.sweetForm.invalid) {
          return;
        }  // for passing the variables through the constructor
        let productCategory:ProductCategory = new ProductCategory(this.sweetForm.controls.categoryId.value, 
          this.sweetForm.controls.name.value, this.sweetForm.controls.description.value);
    
        let sweet:Sweet = new Sweet(this.sweetForm.controls.sweetItemId.value, 
          this.sweetForm.controls.sweetItemName.value, 
          this.sweetForm.controls.price.value, this.sweetForm.controls.image.value,productCategory);
       
    // Update method for the sweet
        this.service.updateSweet(this.id,sweet)
          
          .subscribe(
            data => {
              this.router.navigate(['deletesweet']);
            },
            error => {
              alert(error);
            });
      }

}
