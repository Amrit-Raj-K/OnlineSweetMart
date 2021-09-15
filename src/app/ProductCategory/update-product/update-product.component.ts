import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {
  categoryId: number;
  errorMessage: Product = new Product;
  product: Product = new Product();

  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {

    this.categoryId = +localStorage.getItem("categoryId")
    this.productService.getProductById(this.categoryId).subscribe(data => {
      this.product = data; console.log(data);
    },
      error => console.log(error));

  }

  onSubmit() {
   this.product.categoryId==+localStorage.getItem("categoryId");
    this.productService.UpdateProduct(this.product).subscribe(data => {
      this.navigateToProducts();
    }, error => console.log(error));
    alert("Sucessfully updated the Product Detail");
  }


  navigateToProducts() {
    this.router.navigate(['/maintain']);
  }
}