import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Sweet } from 'src/app/Models/sweet';
import { SweetOrder } from 'src/app/Models/sweet-order';
import { SweetService } from 'src/app/Services/sweet.service';
import { SweetorderService } from 'src/app/Services/sweetorder.service';
 
@Component({
  selector: 'app-showorder',
  templateUrl: './showorder.component.html',
  styleUrls: ['./showorder.component.css']
})
export class ShoworderComponent implements OnInit {
 
  
  public sweets:Sweet;
  public Orders:SweetOrder[]=[];
  public errorMsg;
  public arraylist:any=[];
  public keys:any;
  names=sessionStorage.getItem("name");
public id;
  constructor(private _orderservice:SweetorderService,private route: ActivatedRoute, private sweet:SweetService,private route2: Router) { 
  
  }
 
  ngOnInit(){
   
 
    this._orderservice.getSweetOrder(this.names)
    .subscribe(data=> {
      
      this.Orders=data;console.log(JSON.stringify(this.Orders))
   
    
     
for (var _i = 0; _i < this.Orders.length; _i++) {
  this.id= this.Orders[_i].itemId;
  this.sweet.getSweetIds(this.id).subscribe(response=>{this.sweets=response;
    
    
    this.keys=Object.keys( this.sweets) 
    this.arraylist.push({ name:this.sweets});
    
       },
 
    
    error=>this.errorMsg=console.error());
} 
          
    
    }, 
    
    error=>this.errorMsg=console.error()); 
for (var _i = 0; _i < this.Orders.length; _i++) {
  this.id= this.Orders[_i].itemId;
  this.sweet.getSweetIds(this.id).subscribe(data=>
    {this.sweets=data
    
    
    },
    error=>this.errorMsg=console.error());
}

  }

  help()
  {
    this.route2.navigate(['contact']);
  }
  Sweet()
  {
     this.route2.navigate(['sweet'])
  }
 
}