import { Customer } from "./customer";


export class SweetOrder {
    sweetOrderId:number;
    createdDate:Date;
    itemId:number;
   // username:string;
    
    customers:Customer;
    constructor( sweetOrderId:number,createdDate:Date, itemId:number,customers:Customer)
    {
        this.sweetOrderId=sweetOrderId;
       this.createdDate=createdDate;
       this.itemId=itemId;
       this.customers=customers;
    }
}
