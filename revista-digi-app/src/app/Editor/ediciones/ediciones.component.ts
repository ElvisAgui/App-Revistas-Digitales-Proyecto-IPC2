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
  titulo!:String;
  mostrar = false;


  constructor(private router: Router, private revista: RevistaService, private navar: NvarServiceService) {
      this.revista.getEdiciones().subscribe((ediciones:Edicion[])=>{
        this.ediciones = ediciones;
        this.titulo = navar.revista.titulo;

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


  public goToLink(){
   this.mostrar = !this.mostrar;
  }

  public ocultar(){
    this.mostrar = false;
   }

   public mostrarR(edicion:Edicion){
    this.revistaPdf = this.revista.downloadImage(edicion);
    this.mostrar = true;
   }

  onNavigate(){ 
    this.router.navigateByUrl(""+this.revistaPdf); 
    window.location.href=""+this.revistaPdf}
   

}
