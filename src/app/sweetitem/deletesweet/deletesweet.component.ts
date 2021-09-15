import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Sweet } from 'src/app/Models/sweet';
import { SweetService } from 'src/app/Services/sweet.service';

@Component({
  selector: 'app-deletesweet',
  templateUrl: './deletesweet.component.html',
  styleUrls: ['./deletesweet.component.css']
})
export class DeletesweetComponent implements OnInit {
  public sweet= [];
  public errorMssg;
  searchProduct: string = "";
  constructor(private deleteSweet:SweetService,private router:Router) { }

  ngOnInit(): void {
    this.getSweet();
  }
  deleteSweets(id:number){
    var cfrm=confirm("Do you want to Delete");
    if(cfrm){
 
    this.deleteSweet.deleteSweet(id).subscribe(data=>{
      this.getSweet();
    });
  }
    }
    getSweet(){
    this.deleteSweet.getSweet().subscribe(data=>this.sweet=data,error=>this.errorMssg=error);
    }
    onBack(): void {
      this.router.navigate(['sweet']);
    }
add(){

  this.router.navigate(['sweet-add']);

}
    edit(sweet:Sweet){
      localStorage.removeItem("editSweetId");
      localStorage.removeItem("editCategoryId");
      localStorage.setItem("editSweetId", sweet.sweetItemId.toString());
      localStorage.setItem("editCategoryId", sweet.category.categoryId.toString());
      this.router.navigate(['sweetupdate']);
      }
}
