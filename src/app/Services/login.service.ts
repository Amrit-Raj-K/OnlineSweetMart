import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Login } from '../login/login';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  basePath:string = "http://localhost:8099/admin/";
  
  constructor(private http:HttpClient) { 
  }
  loginService(login:Login){
   // alert("loginService: "+JSON.stringify(login));
   alert("You are logged In")
    return this.http.post(this.basePath+"type", login);
  }
  

}
