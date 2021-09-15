import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from './product';

@Injectable({
  providedIn: 'root',
})

export class ProductService {

  private baseURL = 'http://localhost:8099/cat/';

  constructor(private httpClient: HttpClient) { }

  //List Products to Customers and admin
  getProductList(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(`${this.baseURL}` + 'get');
  }


  //add new Product (in Admin dashboard - grocery management)
  createProduct(product: Product): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}` + 'save', product);
  }

  //get product id (for Update a particular product)
  getProductById(id: number): Observable<Product> {
    return this.httpClient.get<Product>(`${this.baseURL}` + `/get`);
  }


  //Update a product(in admin- grocery management)
  UpdateProduct(product: Product): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}` + `update`, product);
  }

  //delete a product
  deleteProduct(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}delete/${id}`);
  }


}