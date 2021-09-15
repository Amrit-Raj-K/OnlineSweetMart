import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductCategory } from 'src/app/Models/product-category';
import { Sweet } from 'src/app/Models/sweet';
import { ProductcategoryService } from 'src/app/Services/productcategory.service';
import { SweetService } from 'src/app/Services/sweet.service';

@Component({
  selector: 'app-addsweet',
  templateUrl: './addsweet.component.html',
  styleUrls: ['./addsweet.component.css']
})
export class AddsweetComponent implements OnInit {

  public sweetForm: FormGroup;
  constructor(private fb: FormBuilder, private service: SweetService, private product: ProductcategoryService, private router: Router) {
  }


  public ProductCategory: ProductCategory[];

  ngOnInit() {
    this.product.getCategory().subscribe(data => {
      this.ProductCategory = data

    });


    this.sweetForm = this.fb.group({
      sweetItemId: ['1', Validators.required],
      sweetItemName: ['',  [Validators.required,Validators.pattern("^[a-z A-Z]*$")]],
      price: ['', [Validators.required,Validators.pattern("^[0-9]+")]],

      name: ['', Validators.required],

      image: ['', Validators.required],
    });


  }


  get name() {
    return this.sweetForm.get('name');
  }





  createSweet() {
    let sweet: Sweet = new Sweet(this.sweetForm.controls.sweetItemId.value,
      this.sweetForm.controls.sweetItemName.value,
      this.sweetForm.controls.price.value, this.sweetForm.controls.image.value, this.sweetForm.controls.name.value);


    this.service.addSweet(sweet)
      .subscribe(
        data => {
          this.sweetForm.markAsPristine();
          this.router.navigate(['deletesweet']);
        },
        error => {
          alert(error);
        });
  }
}
