import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-maintain-products',
  templateUrl: './maintain-product.component.html',
  styleUrls: ['./maintain-product.component.css']
})
export class MaintainProductComponent implements OnInit {
  products: Product[] =[];

  searchProduct: string = "";
  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit(): void {
    this.getProducts();
  }


  private getProducts() {
    this.productService.getProductList().subscribe(data => {
       this.products = data,
        console.log(data) },
        error => {
          console.log(error)
        }
        );
  }


  updateProduct(categoryId: number) {
    localStorage.removeItem("categoryId");
    localStorage.setItem("categoryId",categoryId.toString());    
    this.router.navigate(['/update']);
  }


  deleteProduct(categoryId: number) {
    this.productService.deleteProduct(categoryId).subscribe(data => {
      console.log(data),
        alert("Delete this product?");
      this.getProducts();
    }, error => {console.log(error)})
  }





}