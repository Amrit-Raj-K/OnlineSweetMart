import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

import { Observable, throwError } from 'rxjs';
import { retry, catchError, map, max } from 'rxjs/operators';
import { Login } from '../login/login';
import { SweetOrder } from '../Models/sweet-order';
import { Coupon } from '../Models/coupon';
import { OrderBill } from '../Models/orderbill';

@Injectable({
  providedIn: 'root'
})
export class SweetorderService {
  private basepath: string = "http://localhost:8099/"
  private _url: string = "/assets/data/coupon.json";

  constructor(private http: HttpClient) { }

  loginService(login: Login) {
    alert("loginService: " + JSON.stringify(login));
    return this.http.post(this.basepath + "admin/type", login);
  }

  //getall SweetOrders
  getSweetOrders(): Observable<SweetOrder[]> {
    return this.http.get<SweetOrder[]>(this.basepath + "api/get").pipe(
      retry(1),
      catchError(this.errorHandler)
    );
  }

  addSweetOrder(sweetorder: SweetOrder): Observable<Object> {
    return this.http.post(this.basepath + 'api/save', sweetorder).pipe(
      retry(1),
      catchError(this.errorHandler)
    );
  }

  getSweetOrderById(sweetOrderId: number): Observable<SweetOrder> {
    return this.http.get<SweetOrder>(this.basepath + 'api/get/' + sweetOrderId).pipe(
      retry(1),
      catchError(this.errorHandler)
    )
  }

  deleteSweetOrder(sweetOrderId: number): Observable<SweetOrder> {
    return this.http.delete<SweetOrder>(this.basepath + 'api/delete/' + sweetOrderId).pipe(
      retry(1),
      catchError(this.errorHandler)
    )
  }

  updateSweetOrder(sweetorder: SweetOrder): Observable<Object> {
    return this.http.put(this.basepath + "api/update", SweetOrder).pipe(
      retry(1),
      catchError(this.errorHandler)
    )
  }
  //fetch particular customer details
  getSweetOrder(username: string): Observable<SweetOrder[]> {
    return this.getSweetOrders()
      .pipe(map((sweetorder: SweetOrder[]) => sweetorder.filter(s => s.customers.username == username)))
  }

  getSweetOrderId(): Observable<SweetOrder> {
    return this.http.get<SweetOrder>(this.basepath + "api/getlargeid").pipe(
      retry(1),
      catchError(this.errorHandler)
    );
  }

  createOrderbill(orderbill: OrderBill): Observable<object> {
    return this.http.post(this.basepath + "orderbill/post", orderbill);
  }

  //ErrorHandler
  errorHandler(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      //client side error
      errorMessage = error.error.errorMessage
    }
    else {
      //server-side error
      errorMessage = `Error Code:${error.status}\nMessage:${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }

  getCoupons(): Observable<Coupon[]> {
    return this.http.get<Coupon[]>(this._url);
  }
  getCoupon(couponname: string): Observable<Coupon[]> {
    return this.getCoupons().pipe((map((coupon: Coupon[]) => coupon.filter(c => c.coupon == couponname))))
  }
}
