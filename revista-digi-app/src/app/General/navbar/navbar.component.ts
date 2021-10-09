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
  constructor(private router: Router, private navrService: NvarServiceService) { }
  
  ngOnInit(): void {
    this.usuario=this.navrService.usuario;
    if (this.navrService.usuario.tipoCuenta === 1) {
      this.esEditor= true;
    }else{
      this.esEditor=false;
    }
  }

  public exit(){
    InicioComponent.autenticado=false;
  }

  public homeClic(){
    this.router.navigate(['home-editor']);
  }

  public misRevistasClick(){
    this.router.navigate(['Mis-Revistas']);
  }


  get getUsuario(){
    return this.usuario;
  }
  
  set setUsuario(usuario: Usuario){
    this.usuario= usuario;
  }



}
