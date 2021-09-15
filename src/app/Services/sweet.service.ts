import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs'
import { retry, catchError } from 'rxjs/operators';
import { Sweet } from '../Models/sweet';

@Injectable({
  providedIn: 'root'
})
export class SweetService {
  // service class for observable using databse
private _url: string ="http://localhost:8099/api/sweetitem/";
  constructor(private http:HttpClient) { }

  
  getSweet(): Observable<Sweet[]>{
return this.http.get<Sweet[]>(this._url).pipe(
  retry(1),
  catchError(this.errorHandler)
);
  }

  getSweetId(id:number):Observable<Sweet>{
    return this.http.get<Sweet>(this._url+''+id).pipe(
      retry(1),
      catchError(this.errorHandler)
    );
      }
      getSweetIds(id:number):Observable<Sweet>{
        return this.http.get<Sweet>(this._url+''+id).pipe(
          retry(1),
          catchError(this.errorHandler)
        );
          }
 
  addSweet(sweet:Sweet): Observable<Object>{
 
    return this.http.post(this._url,sweet).pipe(
      retry(1),
      catchError(this.errorHandler)
    );
      } 
    
     deleteSweet(id:number): Observable<Object>{
return this.http.delete(this._url+id).pipe(
  retry(1),
  catchError(this.errorHandler)
);
  }

 updateSweet(id:number,sweet:Sweet): Observable<Sweet>{

    return this.http.put<Sweet>(this._url+id,sweet).pipe(
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
    