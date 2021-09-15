import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { FormBuilder,  FormGroup } from '@angular/forms';
import { Coupon } from 'src/app/Models/coupon';
import { SweetorderService } from 'src/app/Services/sweetorder.service';
import { SweetOrder } from 'src/app/Models/sweet-order';
import { OrderBill } from 'src/app/Models/orderbill';



@Component({
  selector: 'app-listorders',
  templateUrl: './listorders.component.html',
  styleUrls: ['./listorders.component.css']
})
export class ListordersComponent implements OnInit {
  

  coupenCodeForm:FormGroup;
   Orders:SweetOrder;
  public errorMsg;
  names=sessionStorage.getItem("name");
  pId: string;
  coupenCodeValue: string;
  coupons:Coupon[];
  orderbill : OrderBill;
  orderbill1 : OrderBill[]=[];
  constructor(private _orderservice:SweetorderService,private route: ActivatedRoute, private route2: Router,private fb: FormBuilder) {
   
   }


  ngOnInit(){
    this.getSweetOrder();

    this.coupenCodeForm=this.fb.group({
      couponCode:[''],
    })
  }
  

  
  getSweetOrder()
  {  
    this._orderservice.getSweetOrderId().
    subscribe(data=>{this.Orders=data,
      this.orderbill = new OrderBill(0,this.Orders.createdDate,this.Orders.customers.cart.grandTotal,this.Orders);
      this.orderbill1.push(this.orderbill);
      },
      error=>this.errorMsg=console.error()
      ); 
  }

 

   // logOff user
   logout(): void {
    if (sessionStorage.getItem("username") != null) {
      sessionStorage.removeItem("username");
      this.route2.navigate(['/login']);
    }
  }

  deleteOrder(Orders:SweetOrder)
   {
    this._orderservice.deleteSweetOrder(Orders.sweetOrderId)
    .subscribe(data => {
      this.Orders 
      this.route2.navigate(['cartupdate']);
    });
  }


  SweetOrderDetails(orderId)
  {
    alert("Hello");
  }

  applyCoupenCode()
  {
    
    this.coupenCodeValue=this.coupenCodeForm.controls.couponCode.value;

    this._orderservice.getCoupon(this.coupenCodeValue)
    .subscribe(data=>this.coupons=data,
      error=>this.errorMsg=console.error());
  } 
  createOrderbill(orderbill){
    console.log(orderbill);
    this._orderservice.createOrderbill(orderbill).subscribe(data => {
      // console.log(this.orderbill);
      this.route2.navigate(['orderbill']);
    },
    error => console.log(error));
  }
  addOrderbill(){
    this.createOrderbill(this.orderbill1[0]);
  }

}