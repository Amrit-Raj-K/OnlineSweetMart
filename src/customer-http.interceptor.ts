import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class CustomerHttpInterceptor implements HttpInterceptor {


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let requestUrl: string = request.url;
   // alert(requestUrl);    
    if ((requestUrl.endsWith('type'))) {
        return next.handle(request);
    } 
    if(requestUrl.endsWith('/customer/save'))
    {
      return next.handle(request);
  }    
    
    else {
    // alert("Adding Token");
        let token = sessionStorage.getItem('username');// gets the jwt token from the request
       // To Modify the request we need to clone it. The HttpRequest.clone method allows us to modify the specific properties of the request while copying others
        let modified_req = request.clone({ setHeaders: { 'Authorization': `Bearer ${token}` } });
        //alert(`Bearer ${token}`);
        return next.handle(modified_req);
    }
  }
}
