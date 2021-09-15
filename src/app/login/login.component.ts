import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../Services/login.service';
import { Login } from './login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  submitted: boolean = false;
  invalidLogin: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private router2: ActivatedRoute, private service:CustomerService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
   
    let login:Login = new Login(this.loginForm.controls.username.value, this.loginForm.controls.password.value); 

    this.service.loginService(login).subscribe(data => {
   
      //set the JWT token in the sessionstorage with name username
     sessionStorage.setItem("username", data["jwt"]);
     sessionStorage.setItem("role", data["role"]);
     sessionStorage.setItem("name", data["username"]);
     sessionStorage.setItem("customerId", data["userid"]);
     sessionStorage.setItem("cartId", data["cartId"]);
     setTimeout(()=>{window.location.reload();},100);
    if(data["role"]==="user"){
    this.router.navigate(['sweet']);
    //  this.router.navigateByUrl('\RefreshComponenet', { skipLocationChange: true }).then(() => {
    //   this.router.navigate(['sweet']);
  // }); 
    }
    else{
     this.router.navigate(['sweet']);
    //  this.router.navigateByUrl('\RefreshComponenet', { skipLocationChange: true }).then(() => {
    //   this.router.navigate(['sweet']);
  // }); 
    }
    },
    error => {
      alert('Invalid login/password entered');
    })
  }

 
  
}
