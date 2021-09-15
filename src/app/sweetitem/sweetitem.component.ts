import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Sweet } from '../Models/sweet';
import { SweetService } from '../Services/sweet.service';

@Component({
  selector: 'app-sweetitem',
  templateUrl: './sweetitem.component.html',
  styleUrls: ['./sweetitem.component.css']
})
export class SweetitemComponent implements OnInit {
  public sweet = [];       
  public errorMssg;
  searchProduct: string = "";




  constructor(private _sweetService: SweetService, private route: ActivatedRoute, private router: Router,) {

  }

  ngOnInit(): void {
    //Get method for the sweet
    this._sweetService.getSweet().subscribe(data => this.sweet = data, error => this.errorMssg = error);
  }
  ontoCart(sweet: Sweet): void {
    //setting up local storage for the cart and navigating to there
    localStorage.removeItem("forcartupdateid");
    localStorage.removeItem("editCategoryId");
    localStorage.setItem("forcartupdateid", sweet.sweetItemId.toString());
    localStorage.setItem("editCategoryId", sweet.category.categoryId.toString());
    this.router.navigate(['cartupdate']);
  }

  ImageClick() {
    //navigating or routing to the product category
    this.router.navigate(['category']);
  }
  starRating = 3.5;

}
