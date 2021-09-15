import { Component, OnInit } from '@angular/core';

import { CustomerService } from 'src/app/Services/customer.service';


@Component({
  selector: 'app-customer',
  templateUrl:  './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
   
   
  errorMsg;
  customer ;
  names: string = sessionStorage.getItem("name");
  
  
  constructor(private _customerService: CustomerService) { 
      
       this._customerService.getCustomer(this.names)
        .subscribe(data => this.customer = data,
         error => this.errorMsg = error );
        }

        

       

  ngOnInit(): void {

    
  }


}
