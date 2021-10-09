import { Usuario } from './../../modelo/Usuario/Usuario.model';
import { Injectable, OnInit } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NvarServiceService implements OnInit {
  usuario!: Usuario;
  constructor() { }


  ngOnInit(): void {
    
  }


  get getUsuario(){
    return this.usuario;
  }
  
  set setUsuario(usuario: Usuario){
    this.usuario = usuario;
  }
}
