import { Sweet } from "./sweet";


export class Cart {

    cartId : number;
    grandTotal : number;
    total: number;
    sweetItemCount:number;
    sweetItems : Sweet;
constructor(cartId : number, grandTotal : number, total: number,sweetItemCount:number,sweetItems : Sweet){
this.cartId=cartId;
this.grandTotal=grandTotal;
this.total=total;
this.sweetItemCount=sweetItemCount;
this.sweetItems=sweetItems;


}

}
