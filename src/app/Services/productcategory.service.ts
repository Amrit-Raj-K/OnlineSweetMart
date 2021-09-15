import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { ProductCategory } from '../Models/product-category';

@Injectable({
  providedIn: 'root'
})
export class ProductcategoryService {
  private _url: string ="http://localhost:8099/cat/";
  constructor(private http:HttpClient) { }

  
  getCategory(): Observable<ProductCategory[]>{
return this.http.get<ProductCategory[]>(this._url+"get").pipe(
  retry(1),
  catchError(this.errorHandler)
);
  }

  getCateoryId(id:number):Observable<ProductCategory>{
    return this.http.get<ProductCategory>(this._url+'get/'+id).pipe(
      retry(1),
      catchError(this.errorHandler)
    );
      }
      errorHandler(error){
        let errorMessage='';
    
        if (error.error instanceof ErrorEvent){
    // client side error
    errorMessage=error.error.message
        }
        else {
          //server side error
          errorMessage=`Error Code: ${error.status}\nMessage : ${error.message}`;
        }
        window.alert(errorMessage);
    return throwError(errorMessage);
      }
}
