import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { OrderBill } from '../Models/orderbill';



@Injectable({
  providedIn: 'root'
})
export class OrderbillService {

  private baseurl = "http://localhost:8099/orderbill"
  constructor(private httpClient: HttpClient) { }

  getOrderbill(): Observable<OrderBill[]>{
    return this.httpClient.get<OrderBill[]>(this.baseurl+'/get').pipe(
      retry(1),
      catchError(this.errorHandler)
    );
      }
  // getOrderbill(id: number): Observable<Orderbill>{
  //   return this.httpClient.get<Orderbill>(`${this.baseurl+'/'+id}`); //(`${this.baseurl}`);
  // }
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