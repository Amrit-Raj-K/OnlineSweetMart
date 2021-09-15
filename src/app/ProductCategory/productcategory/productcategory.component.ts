import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';


@Component({
  selector: 'app-productcategory',
  templateUrl: './productcategory.component.html',
  styleUrls: ['./productcategory.component.css']
})
export class ProductcategoryComponent implements OnInit {
  products: Product[] = [];
            // for add to cart
  quantity: any = [];
  product: Product = new Product();
  searchProduct: string = "";             //for filter pipe
  message = '';
  errorMessage: Product = new Product;     //for validation


  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit(): void {
    this.getProducts();
  }

  logout() {
    this.router.navigate(['/logoutcomponent']);
  }

  getProducts() {
    this.productService.getProductList().subscribe(data => {                                               //this method getProductList will return observable object
      this.products = data,
        console.log(data)
    }, error => {
      console.log(error);
    }
    );
  }





  


  updateQuantity(value: any,i:any){

     this.product.name = this.products[i].name;
     this.product.description = this.products[i].description;
     this.product.categoryId = this.products[i].categoryId;
     console.log(this.product);

     this.productService.UpdateProduct(this.product).subscribe(data => {
     }, error => console.log(error));
   }






}