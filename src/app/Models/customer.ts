import { Cart } from "./cart";



export class Customer {

    customerId: number;
        username:string; //email
        password:string;
        city:string;
        contactNumber: string;
        zip: string;
        customername: string;
        
    cart: Cart;
    type: String;


   constructor(customerId: number, username: string, password: string, city:string,
    contactNumber:string,zip:string,customername:string ,cart: Cart,  type: String) {
        this.customerId = customerId;
        this.username = username;
        this.password=password;
        this.city=city;
        this.contactNumber=contactNumber;
        this.zip=zip;
        this.customername=customername;
        
        this.type=type;
        this.cart=cart;
       
    }
}
