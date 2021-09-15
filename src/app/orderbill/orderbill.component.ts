import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from '../Models/cart';
import { Customer } from '../Models/customer';
import { OrderBill } from '../Models/orderbill';
import { Sweet } from '../Models/sweet';
import { SweetOrder } from '../Models/sweet-order';

@Component({
  selector: 'app-orderbill',
  templateUrl: './orderbill.component.html',
  styleUrls: ['./orderbill.component.css']
})
export class OrderbillComponent implements OnInit {

  public orderbill: OrderBill ;
  public orderBillId : OrderBill;
  public sweetorder : SweetOrder;
  public sweetitem : Sweet;
  public cart : Cart;
  public customer : Customer;
  public errorMssg : string;
  public id: number = 0;
  private baseurl = "http://localhost:8099/orderbill";

  constructor(
    private httpClient: HttpClient,
    private router: Router) { }

  ngOnInit(): void {
    this.getLatestbill(); 
  }

  getLatestbill(){
    this.httpClient.get<OrderBill[]>(this.baseurl+'/get').subscribe(
        response => {
          console.log("response 1 " +response[1].orderBillId);
          let i = 1;
          let max = response[0].orderBillId;
        for(i;i<response.length;i++){
            if(max < response[i].orderBillId)
              max = response[i].orderBillId;
              console.log("max = "+max);
        }this.id = max;
        this.getOrderbillById(this.id);
         
      }
    );
    
  }

  getOrderbillById(id:number){
    console.log(id);
    this.httpClient.get<OrderBill>(this.baseurl+'/get/'+id).subscribe(
      (res : OrderBill) => {
        console.log(res.listSweetOrder);
        this.orderbill=res;
        this.sweetorder=res.listSweetOrder;
        this.customer=res.listSweetOrder.customers;
        this.sweetitem = res.listSweetOrder.customers.cart.sweetItems;
        this.cart = res.listSweetOrder.customers.cart;
      }
    )
  }

  onClick(){
    this.router.navigate(['./sweet']);
  }
 
}
