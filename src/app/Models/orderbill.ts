import { SweetOrder } from "./sweet-order";

export class OrderBill {
    orderBillId: number;
    createdDate: Date;
    totalCost: number;
    listSweetOrder: SweetOrder;

    constructor( orderBillId: number, createdDate: Date,totalCost: number,listSweetOrder:SweetOrder){
this.orderBillId=orderBillId;
this.createdDate=createdDate;
this.totalCost=totalCost;
this.listSweetOrder=listSweetOrder;
 }



}