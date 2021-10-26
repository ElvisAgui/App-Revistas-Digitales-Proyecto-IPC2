import { Revista } from 'src/modelo/Revista/revista';
import { Usuario } from './../../../modelo/Usuario/Usuario.model';
import { InicioComponent } from './../inicio/inicio.component';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NvarServiceService } from 'src/app/services/nvar-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  
  esEditor!: boolean;
  usuario!:Usuario;
  totalRevistas!: number;
  
  
  constructor(private router: Router, private navrService: NvarServiceService) { }
  
  ngOnInit(): void {
    this.usuario=this.navrService.usuario;
    if (this.navrService.usuario.tipoCuenta === 1 || this.navrService.usuario.tipoCuenta === 3) {
      this.esEditor= true;
    }else{
      this.esEditor=false;
    }
    this.totalRevistas = this.navrService.totalRevistas;
  }

  public exit(){
    InicioComponent.autenticado=false;
  }

  public homeClic(){
    if (this.usuario.tipoCuenta === 1) {
      this.router.navigate(['home-editor']);
    }else{
      if (this.usuario.tipoCuenta === 2) {
        this.router.navigate(['home-Usuario']);
      }else{
        if (this.usuario.tipoCuenta === 3) {
          this.router.navigate(['home-Admin']);
        }
      }
    } 
  }
  

  public misRevistasClick(){
    this.router.navigate(['Mis-Revistas']);
  }

  public PerfilClick(){
    this.router.navigate(['Perfil-Usuario']);
  }


  get getUsuario(){
    return this.usuario;
  }
  
  set setUsuario(usuario: Usuario){
    this.usuario= usuario;
  }

  public reportesClick(){
    if (this.navrService.usuario.tipoCuenta === 1) {
      this.router.navigate(['Reportes-Editor']);
    }
    if (this.navrService.usuario.tipoCuenta === 3) {
      this.router.navigate(['Reportes-Editor']);
    }
  }

 

}
