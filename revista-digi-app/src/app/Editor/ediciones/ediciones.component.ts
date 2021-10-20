import { Edicion } from './../../../modelo/Revista/edicion';
import { RevistaService } from './../../services/revista.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NvarServiceService } from 'src/app/services/nvar-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-ediciones',
  templateUrl: './ediciones.component.html',
  styleUrls: ['./ediciones.component.css']
})
export class EdicionesComponent implements OnInit {

  ediciones:Edicion[] = [];
  descripcion!: String;
  revistaPdf!: String;


  constructor(private router: Router, private revista: RevistaService, private navar: NvarServiceService) {
      this.revista.getEdiciones().subscribe((ediciones:Edicion[])=>{
        this.ediciones = ediciones;
        this.revistaPdf = "/home/elvis_agui/Sistema_Revistas_Digitales_Proyecto2_IPC2/RevistasDigitales/target/RevistasDigitales-1.0-SNAPSHOT/archivo/242retiro.pdf";
      },
      (error)=>{
        // error en el servidor
      });
   }

  ngOnInit(): void {
  }


  public mostrarDesc(edicion: Edicion){
    this.descripcion = edicion.descripcion;
    Swal.fire({
      title: '<strong><u>DESCRIPCION DE EDICION</u></strong>',
      icon: 'info',
      html:
        this.descripcion,
      cancelButtonText:
        '<i class="fa fa-thumbs-down"></i>',
      cancelButtonAriaLabel: 'OK'
    })
  }

  public mostrarPDF(edicion: Edicion){
    this.revistaPdf = edicion.revista;
    Swal.fire({
      imageUrl: '/home/elvis_agui/Sistema_Revistas_Digitales_Proyecto2_IPC2/RevistasDigitales/target/RevistasDigitales-1.0-SNAPSHOT/archivo/242retiro.pdf',
      imageHeight: 1500,
      imageAlt: 'A tall image'
    })
  }


  public goToLink(url: string){
    window.open(url+'/create-account');
  }

  onNavigate(){ 
    this.router.navigateByUrl('/home/elvis_agui/Sistema_Revistas_Digitales_Proyecto2_IPC2/RevistasDigitales/target/RevistasDigitales-1.0-SNAPSHOT/archivo/242retiro.pdf'); 
    window.location.href='/home/elvis_agui/Sistema_Revistas_Digitales_Proyecto2_IPC2/RevistasDigitales/target/RevistasDigitales-1.0-SNAPSHOT/archivo/242retiro.pdf'; }

}
