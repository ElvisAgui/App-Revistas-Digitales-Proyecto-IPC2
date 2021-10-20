import { RevistaService } from './../../services/revista.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NvarServiceService } from 'src/app/services/nvar-service.service';
import { Revista } from 'src/modelo/Revista/revista';
import Swal from 'sweetalert2'


@Component({
  selector: 'app-permisos-revista',
  templateUrl: './permisos-revista.component.html',
  styleUrls: ['./permisos-revista.component.css']
})
export class PermisosRevistaComponent implements OnInit {
  
  revista!: Revista;
  permisos!: FormGroup;
  permitir = "Permitir";
  bloquear = "Boquear";

  constructor(private navar: NvarServiceService, private router: Router,private formBuilder: FormBuilder, private control:RevistaService) {
    this.revista = this.navar.revista;
   }

  ngOnInit(): void {
    this.permisos = this.formBuilder.group({
      suscripcion: [null],
      comentar: [null],
      reaccionar: [null]
    });
  }

  public actulizarPermisos(){
    if (this.permisos.value.suscripcion != null ||this.permisos.value.comentar != null || this.permisos.value.reaccionar != null ) {
      this.cambiarPrmisos();
      this.control.actulizarPermisos(this.revista).subscribe(
        (data)=>{
        this.popAfirmation();
      },(error)=>{
        this.popErro();
      });
    }else{
      this.popNoSeActuliza();
    }
  }

  public cambiarPrmisos(){
    this.cambiarComentar();
    this.cambiarSuscirpcion();
    this.cambiarReaccionar();    
  }

  public cambiarSuscirpcion(){
    if (this.permisos.value.suscripcion != null) {
      if (this.permisos.value.suscripcion === this.permitir) {
        this.revista.suscripcion = true;
      }else{
        this.revista.suscripcion = false;
      }
    }
  }


  public cambiarComentar(){
    if (this.permisos.value.comentar != null) {
      if (this.permisos.value.comentar === this.permitir) {
        this.revista.comentar = true;
      }else{
        this.revista.comentar = false;
      }
    }
  }

  public cambiarReaccionar(){
    if (this.permisos.value.reaccionar != null) {
      if (this.permisos.value.reaccionar === this.permitir) {
        this.revista.reaccionar = true;
      }else{
        this.revista.reaccionar = false;
      }
    }
  }

  public popErro() {
    Swal.fire('Error', 'Algo fallo en el Servidor', 'error');
  }

  public popAfirmation(){
    Swal.fire(
      'Permisos Actulizados',
      'proceso completado  con Exito',
      'success'
    )
  }

  public popNoSeActuliza(){
    Swal.fire(
      'Ningun Modificado',
      'Los permisos no se actulizaron',
      'question'
    )
  }


  

}
