import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanDeactivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import {ListordersComponent} from 'src/app/sweetorder/listorders/listorders.component'


@Injectable({
  providedIn: 'root'
})
export class UnsavedChangesGuard implements CanDeactivate<ListordersComponent> {

  canDeactivate():boolean
  {
     return window.confirm('Are you sure you want to continue?')
  }
 
  
}
