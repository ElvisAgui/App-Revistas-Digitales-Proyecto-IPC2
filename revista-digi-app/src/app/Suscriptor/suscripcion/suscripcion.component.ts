import { SuscripService } from './../../services/suscrip.service';
import { Suscripcion } from './../../../modelo/Usuario/suscripcion';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NvarServiceService } from 'src/app/services/nvar-service.service';
import { Revista } from 'src/modelo/Revista/revista';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-suscripcion',
  templateUrl: './suscripcion.component.html',
  styleUrls: ['./suscripcion.component.css']
})
export class SuscripcionComponent implements OnInit {
  suscripcionForm!: FormGroup;
  revista!:Revista;
  suscription!: Suscripcion;

  constructor(private formBuilder: FormBuilder, private navrService: NvarServiceService, private serveSuscrip: SuscripService
    ) {
      this.revista = this.navrService.revista;
      
     }

  ngOnInit(): void {
    this.suscripcionForm = this.formBuilder.group({
      usuario:[this.navrService.usuario.usuario],
      fecha_pago: [null, Validators.required],
      fecha_vencimiento:[null, Validators.required],
      password: [null, Validators.required],
    });
  }

  public suscripcion(){
    this.suscription = this.suscripcionForm.value;
    this.suscription.revista = this.revista.titulo;
    this.suscription.totalPago = this.revista.precio;
   this.serveSuscrip.crearSuscripcion(this.suscription).subscribe((data)  =>{
      this.popAfirmation();
   },(error)=>{
     this.popErro();
   });
     
    console.log(this.suscription);

  }

  public popAfirmation() {
    Swal.fire('Revisata Publicada con Exito', 'proceso completado', 'success');
  }

  public popErro() {
    Swal.fire('Error', 'Verifique, tal ves ya este suscrito', 'error');
  }


}
