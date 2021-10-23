import { Comentario } from './../../../modelo/Revista/comentario';
import { Suscripcion } from './../../../modelo/Usuario/suscripcion';
import { SuscripService } from './../../services/suscrip.service';
import { Usuario } from './../../../modelo/Usuario/Usuario.model';
import { NvarServiceService } from 'src/app/services/nvar-service.service';
import { Revista } from './../../../modelo/Revista/revista';
import { FileService } from './../../services/file.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-list-revista',
  templateUrl: './list-revista.component.html',
  styleUrls: ['./list-revista.component.css']
})
export class ListRevistaComponent implements OnInit {
  
  revistas: Revista[] = [];
  usuario!: Usuario;
  suscripciones: Suscripcion[]= [];
  esEditor = false;
  

  constructor(private router: Router, private file: FileService, private navar: NvarServiceService, private suscrSer: SuscripService) { 
    this.usuario = this.navar.usuario;
    if (this.navar.usuario.tipoCuenta === 1 ) {
      this.esEditor = true;
      this.file.getListaRevistas().subscribe((revistas: Revista[]) => {
        this.revistas = revistas;
        this.navar.totalRevistas = this.revistas.length;
      });
    }else if(this.navar.usuario.tipoCuenta === 2){
      this.suscrSer.getListaRevistas().subscribe((suscripction: Suscripcion[])=>{
          this.suscripciones = suscripction;
          console.log(suscripction);
          console.log(this.suscripciones);
      }); 
    }
    this.revistas = this.navar.revistas;
   
  }

  ngOnInit(): void {
  }

  public prmisosClick(revista: Revista){
    this.navar.revista = revista;
    this.router.navigate(['Permiso-Revistas']);
  }

  public generalClick(revista: Revista){
    this.navar.revista = revista;
    this.router.navigate(['Modidificacion-General']);

  }

  public edicionClick(revista: Revista){
    this.navar.revista = revista;
    this.router.navigate(['Edicion']);
    
  }

  public edicionesClick(revista: Revista){
    this.navar.revista = revista;
    this.router.navigate(['Ediciones']);
    
  }

  public comunidad(suscripcion: Suscripcion){
    this.navar.suscripcion = suscripcion;
    this.router.navigate(['comunidad']);
  }


  public pagar(suscipcion: Suscripcion){
    console.log('entre a pagar ');
    console.log(this.navar.revistas);
    this.navar.revistas.forEach(revista => {
      if (suscipcion.revista === revista.titulo) {
        this.navar.revista = revista;
      }
    });
    this.router.navigate(['suscripcion']);
  }

  public listarEdiciones(suscipcion: Suscripcion){
    this.navar.suscripcion = suscipcion;
    this.router.navigate(['Ediciones']);
  }
  

  

  public popAfirmation() {
    Swal.fire('Comentario Registrado', 'proceso completado', 'success');
  }
 
  

}
