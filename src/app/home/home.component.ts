import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';




@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [NgbCarouselConfig]
})
export class HomeComponent implements OnInit{
  showNavigationArrows = true;
  showNavigationIndicators = true;


  constructor(config: NgbCarouselConfig,private router: Router) {
    // customize default values of carousels used by this component tree
    config.showNavigationArrows = true;
    config.showNavigationIndicators = true;
  }
  ngOnInit(): void {
  }

  navigateToLogin(){
   if(sessionStorage.getItem("username")!=null){
     this.router.navigate(['sweet']);
   }
   else{
     this.router.navigate(['login']);
   }
  }

}
