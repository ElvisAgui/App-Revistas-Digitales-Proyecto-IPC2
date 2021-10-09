import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { InicioComponent } from '../General/inicio/inicio.component';

@Injectable({
  providedIn: 'root'
})
export class AutenticacionService implements CanActivate{

  constructor(private router: Router) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){
    if (InicioComponent.autenticado) {
      return true;
    }else{
      this.router.navigate([""]);
      return false;
    }
  }

  
}
