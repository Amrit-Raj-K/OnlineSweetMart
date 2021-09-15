
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';



import { Observable, throwError } from 'rxjs';
import { catchError,map,retry } from 'rxjs/operators';
import { Login } from '../login/login';
import { Customer } from '../Models/customer';



@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  customerId: any;
  
  constructor(private http: HttpClient){ }
  private basePath: string = "http://localhost:8099";

 


  //HTTP response is sequence of item that arrive asynchronously over time (http call-single item)
  //response which we getr back from http call is observable

  loginService(login:Login){
   //alert("loginService: "+JSON.stringify(login)); //display username,password
    return this.http.post(this.basePath+"/admin/type", login);
  }
  //fetch all the customer details
  getCustomers(): Observable<Customer[]>{
    
    return this.http.get<Customer[]>(this.basePath+"/customer/get").pipe(
      
      retry(1), 
      catchError(this.errorHandler)

    );
  }
  registerCustomer(customer:Customer): Observable<Object>{
 
    return this.http.post(this.basePath+"/customer/save",customer).pipe(
      retry(1),
      catchError(this.errorHandler)
    );
      } 



  //fetch particular customer details
  getCustomer(username: string): Observable<Customer[]>{
    return this.getCustomers()
    .pipe(map((customer: Customer[]) => customer.filter(c=>c.username === username)));
  }
/*-----------------------------------------------------------------------------------*/
 

getCustomerById(customerId:number):Observable<Customer>{
  return this.http.get<Customer>(`${this.basePath}/customer/customer/${customerId}`);
}



updateCustomer(id : number,customer:Customer):Observable<Customer>{

  return this.http.put<Customer>(this.basePath+"/customer/update/"+id, customer).pipe(
      
    retry(1), 
    catchError(this.errorHandler)

  ); 
}


  errorHandler(error){
    let errorMessage = '';
    
    if(error.error instanceof ErrorEvent){
      
      //client-side error

      errorMessage = error.error.message
    }
    else{

      //server-side error

      errorMessage =  `Error Code: ${error.status}\nMessage: ${error.message} `;
    }
    window.alert(errorMessage);

    return throwError(errorMessage);
  }
}
