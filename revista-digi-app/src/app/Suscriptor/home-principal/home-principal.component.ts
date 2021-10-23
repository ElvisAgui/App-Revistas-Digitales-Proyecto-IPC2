import { Router } from '@angular/router';
import { Edicion } from 'src/modelo/Revista/edicion';
import { Usuario } from '../../../modelo/Usuario/Usuario.model';
import { Component, OnInit } from '@angular/core';
import { Revista } from 'src/modelo/Revista/revista';
import { FileService } from '../../services/file.service';
import { NvarServiceService } from '../../services/nvar-service.service';
import Swal from 'sweetalert2';
import { RevistaService } from '../../services/revista.service';
import { Suscripcion } from 'src/modelo/Usuario/suscripcion';
import { SuscripService } from 'src/app/services/suscrip.service';

@Component({
  selector: 'app-home-principal',
  templateUrl: './home-principal.component.html',
  styleUrls: ['./home-principal.component.css'],
})
export class HomePrincipalComponent implements OnInit {
  revistas: Revista[] = [];
  ediciones: Edicion[] = [];
  usuario!: Usuario;
  revistasM: Revista[] = [];
  revistasMAl: Revista[] = [];
  ocultar = false;
  fotoPerfil!:String;
  ocultarB = true; 
  suscripciones: Suscripcion[]= [];


  constructor(
    private file: FileService,
    private navar: NvarServiceService,
    private revista: RevistaService,
    private router: Router,
    private suscrSer: SuscripService
  ) {
    this.usuario = this.navar.usuario;
    this.file.getListaRevistasT().subscribe((revistas: Revista[]) => {
      this.revistas = revistas;
      this.navar.revistas = revistas;
      this.revistasRecomentadas();
    });
    if (this.usuario.foto != null) {
      this.fotoPerfil = file.downloadImage(this.usuario);
      console.log(this.fotoPerfil);
    }
    this.suscrSer.getListaRevistas().subscribe((suscripction: Suscripcion[])=>{
      this.suscripciones = suscripction;
  });
  }

  ngOnInit(): void {}

  public revistasRecomentadas() {
    this.revistas.forEach((revistaG) => {
      revistaG.etiquetas.forEach((etiquetaR) => {
        this.usuario.etiquetas.forEach((etiquetaUS) => {
          if (
            etiquetaR.etiqueta === etiquetaUS.etiqueta &&
            this.revistasM.indexOf(revistaG) === -1
          ) {
            this.revistasM.push(revistaG);
          }
        });
      });
    });
    if (this.revistas.length >= 0 && this.usuario.etiquetas.length >=0) {
      while (this.revistasMAl.length === 0) {
        this.revistasM.forEach((revista) => {
          let value = Math.round(Math.random() * this.revistasM.length-1);
          if (value >= 0) {
            if (this.revistasMAl.indexOf(this.revistasM[value]) === -1) {
              this.revistasMAl.push(this.revistasM[value]);
            }
            if (this.revistasMAl.indexOf(revista) === -1) {
              this.revistasMAl.push(revista);
              
            }
          }
        });
      }
    }
  }

  public valor(valor: number) {
    console.log('entre');
    if (valor >= 6) {
      this.ocultar = true;
    }
  }

  public sucripcion(revista: Revista){
    this.navar.revista = revista;
    this.router.navigate(['suscripcion']);
  }

  public bloqueo(revista: Revista){
    console.log(this.suscripciones);
    console.log("es null");
    this.suscripciones.forEach(suscipcion => {
      if (suscipcion.usuario === this.navar.usuario.usuario && revista.titulo === suscipcion.revista) {
         
        this.ocultarB = false;
      }
    });


  }

  public prev(revistaE: Revista) {
    this.navar.revista = revistaE;
    this.revista.getEdiciones().subscribe(
      (ediciones: Edicion[]) => {
        this.ediciones = ediciones;
        this.ediciones.forEach((edicion) => {
          revistaE.descripcion = edicion.descripcion;
          console.log(revistaE.descripcion);
          let miarray: String[]=[];
          revistaE.etiquetas.forEach(etiqueta => {
            miarray.push(etiqueta.etiqueta);
          });
    let contenido =
      '<div class=' +
      'card' +
      'style=' +
      'width: 18rem;' +
      '><div class=' +
      'card-header' +
      '>' +
      revistaE.titulo +
      '</div>' +
      '<div class=' +
      'card-body' +
      '>' +
      'descripcion: ' +
      revistaE.descripcion +
      '' +
      '<br>' +
      'categoria: ' +revistaE.categoria +
      '' +
      '<br>' +
      'Editor : ' +revistaE.editor+
      '</div>' +
      '<div class=' +
      'card-footer>' +
      ' </div>cantidad de me gusta: </div>';
    Swal.fire({
      title: contenido,
      icon: 'info',
      html:
        '<b>Etiquetas: </b>, ' + miarray.toString()+'',
      showCloseButton: true,
      showCancelButton: true,
      focusConfirm: false,
      confirmButtonText: '<i class="fa fa-thumbs-up"></i> Great!',
      confirmButtonAriaLabel: 'Thumbs up, great!',
      cancelButtonText: '<i class="fa fa-thumbs-down"></i>',
      cancelButtonAriaLabel: 'Thumbs down',
    });
        });
      },
      (error) => {
        // error en el servidor
      }
    );
    
  }
}
