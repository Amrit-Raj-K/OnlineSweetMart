import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

export class AdminCanActivateGuardService implements CanActivate{

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
   
        if((sessionStorage.getItem("username")==null)||(sessionStorage.getItem("role")=="user")){
            alert('Login as Admin to access this page.');
            return false;
        }else{
            return true;
        }
    }

}