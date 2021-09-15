import { Component } from '@angular/core';

import { CustomerService } from 'src/app/Services/login.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'OnlineSweetMart';
  private roles: string;
  isLoggedIn = false;
  showAdminBoard = false;
showUserBoard=false;
  username?: string;

  constructor(private customerService: CustomerService) {
    this.isLoggedIn = (sessionStorage.getItem("username") != null);
   }
 
  ngOnInit(): void {  
    if (this.isLoggedIn) {
     
      const user = sessionStorage.getItem("name");
      this.username = user;
      this.roles = sessionStorage.getItem("role");

      this.showAdminBoard = this.roles.includes('admin');
      this.showUserBoard=this.roles.includes('user');

    } 
  }

  logout(): void {
    var cfrm=confirm("Do you want to LOGOUT");
    if(cfrm){
      if (sessionStorage.getItem("username") != null) {
        sessionStorage.removeItem("username");
        sessionStorage.removeItem("role");
        sessionStorage.removeItem("name");
        sessionStorage.removeItem("customerId");
    }
   
    this.isLoggedIn= false;
  }
}
}