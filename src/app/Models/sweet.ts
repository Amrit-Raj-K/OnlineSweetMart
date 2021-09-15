import { ProductCategory } from "./product-category";

export class Sweet {
    // class for doing observable with data base
   
    sweetItemId :number;
    sweetItemName: string;
    price:number;
    image:String;
    category : ProductCategory

    constructor(sweetItemId:number, sweetItemName:string,price:number,image:String,category : ProductCategory){
        this.sweetItemId=sweetItemId;
        this.sweetItemName=sweetItemName;
        this.price=price;
        this.image=image;
        this.category=category;
    }
    get p(){
        return this.price;
    }

}
