import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Cart } from '../Models/cart';



@Injectable({
  providedIn: 'root'
})
export class CartService {
  private basePAth: string ="http://localhost:8099";
  constructor(private http:HttpClient) { }


  getCart(): Observable<Cart[]>{
    return this.http.get<Cart[]>(this.basePAth+"/cart/show").pipe(
    retry(1),
    catchError(this.errorHandler)
  );
      }

    getCartId(id:number):Observable<Cart>{
      return this.http.get<Cart>(this.basePAth+"/cart/show/"+id);
    }

    updatecart(id:number,cart:Cart): Observable<Cart>{
      "/cart/update/{cartId}"
        return this.http.put<Cart>(this.basePAth+"/cart/update/"+id,cart).pipe(
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

function cart<T>(arg0: string, cart: any) {
  throw new Error('Function not implemented.');
}
