
import { Component, OnInit,HostListener, ElementRef } from '@angular/core';
import { FormGroup,FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Cart } from 'src/app/Models/cart';
import { Customer } from 'src/app/Models/customer';
import { ProductCategory } from 'src/app/Models/product-category';
import { Sweet } from 'src/app/Models/sweet';
import { SweetOrder } from 'src/app/Models/sweet-order';
import { CustomerService } from 'src/app/Services/customer.service';
import { SweetorderService } from 'src/app/Services/sweetorder.service';



@Component({
  selector: 'app-addorder',
  templateUrl: './addorder.component.html',
  styleUrls: ['./addorder.component.css']
})
export class AddorderComponent implements OnInit {

  constructor(private fb: FormBuilder,private router: Router,private service:SweetorderService,private cservice:CustomerService) { }

 isShow: boolean;
topPosToStartShowing = 100;

  public sweetorderForm: FormGroup;
  public response:any;
   

  name:string=String(sessionStorage.getItem("name"))

  today = new Date().toLocaleDateString();

  

  ngOnInit(): void {

    let res:any;
    this.cservice.getCustomer(this.name).subscribe(data=>{this.sweetorderForm.patchValue(data)
      
      console.log("response here",data);
      this.sweetorderForm=this.fb.group({

        sweetOrderId:[3,Validators.required],
        createdDate:['08-Sep-2021',Validators.required],
        itemid:[data[0].cart.sweetItems.sweetItemId,Validators.required],
       
    
       customerId:[data[0].customerId,Validators.required],
       username:[data[0].username,Validators.required],
       password:[data[0].password,Validators.required],
       city: [data[0].city, Validators.required],

       contactNumber:[data[0].contactNumber, Validators.required],
       zip:[data[0].zip, Validators.required],
       customername :[data[0].customername, Validators.required],
       type:[data[0].type,Validators.required],
    
        cartId:[data[0].cart.cartId, Validators.required],
        grandTotal: [data[0].cart.grandTotal, Validators.required],
        total :[data[0].cart.total, Validators.required],
        sweetItemCount:[ data[0].cart.sweetItemCount, Validators.required],
        
        sweetItemId : [data[0].cart.sweetItems.sweetItemId, Validators.required],
        sweetItemName: [data[0].cart.sweetItems.sweetItemName, Validators.required], 
        price:[data[0].cart.sweetItems.price, Validators.required],
        image:[data[0].cart.sweetItems.image, Validators.required],
    
        categoryId: [data[0].cart.sweetItems.category.categoryId, Validators.required],
        name: [data[0].cart.sweetItems.category.name, Validators.required],
        description: [data[0].cart.sweetItems.category.description, Validators.required],
      });
    });
 

    
  
    
  this.sweetorderForm=this.fb.group({

    sweetOrderId:['3',Validators.required],
    createdDate:['',Validators.required],
    itemid:['',Validators.required],
   

   customerId:['',Validators.required],
   username:['',Validators.required],
   password:['',Validators.required],
   city: ['', Validators.required],

   contactNumber:['', Validators.required],
   zip:['', Validators.required],
   customername :['', Validators.required],
   type:['',Validators.required],

    cartId: ['', Validators.required],
    grandTotal: ['', Validators.required],
    total : [ '', Validators.required],
    sweetItemCount:[ '', Validators.required],
    
    sweetItemId : ['', Validators.required],
    sweetItemName: ['', Validators.required],
    price: ['', Validators.required],
    image:['', Validators.required],

    categoryId: ['', Validators.required],
    name: ['', Validators.required],
    description: ['', Validators.required],

    
  });
  }


  addsweetOrder()
  {
    if(this.sweetorderForm.invalid)
    {
      return;
    }

    let productCategory:ProductCategory = new ProductCategory(this.sweetorderForm.controls.categoryId.value, 
      this.sweetorderForm.controls.name.value, this.sweetorderForm.controls.description.value);
     
       let sweetItems:Sweet = new Sweet(this.sweetorderForm.controls.sweetItemId.value, 
        this.sweetorderForm.controls.sweetItemName.value,
         this.sweetorderForm.controls.price.value, this.sweetorderForm.controls.image.value,productCategory);


      let cart: Cart = new Cart(this.sweetorderForm.controls.cartId.value,this.sweetorderForm.controls.grandTotal.value,  
        this.sweetorderForm.controls.total.value,this.sweetorderForm.controls.sweetItemCount.value,sweetItems);

   
        let customer:Customer = new Customer(this.sweetorderForm.controls.customerId.value, 
          this.sweetorderForm.controls.username.value, 
          this.sweetorderForm.controls.password.value,this.sweetorderForm.controls.city.value,this.sweetorderForm.controls.contactNumber.value,  
          this.sweetorderForm.controls.zip.value,this.sweetorderForm.controls.customername.value,cart ,this.sweetorderForm.controls.type.value);

          let sweetorder:SweetOrder=new SweetOrder(this.sweetorderForm.controls.sweetOrderId.value,this.sweetorderForm.controls.createdDate.value,this.sweetorderForm.controls.itemid.value,customer);
          

          
         
        
          
console.log("item id "+this.sweetorderForm.controls.itemid.value)
          this.service.addSweetOrder(sweetorder)
          .subscribe(data => {
            this.router.navigate(['showorder']);
          },
          error => {
            alert('error1 :' +error);
            
          });

        
  }

  back()
  {
    this.router.navigate(['cartupdate']);
  }

  @HostListener('window:scroll')
  checkScroll() {
      
    // window의 scroll top
    // Both window.pageYOffset and document.documentElement.scrollTop returns the same result in all the cases. window.pageYOffset is not supported below IE 9.

    const scrollPosition = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;

    console.log('[scroll]', scrollPosition);
    
    if (scrollPosition >= this.topPosToStartShowing) {
      this.isShow = true;
    } else {
      this.isShow = false;
    }
  }

  // TODO: Cross browsing
  gotoTop() {
    window.scroll({ 
      top: 0, 
      left: 0, 
      behavior: 'smooth' 
    });
  }
}

