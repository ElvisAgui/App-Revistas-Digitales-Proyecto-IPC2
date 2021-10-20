import { Usuario } from './../../modelo/Usuario/Usuario.model';
import { Injectable, OnInit } from '@angular/core';
import { Revista } from 'src/modelo/Revista/revista';

@Injectable({
  providedIn: 'root'
})
export class NvarServiceService implements OnInit {
  
  usuario!: Usuario;
  revista!: Revista;
  totalRevistas!: number;

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
