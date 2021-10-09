import { EtiquetaSerService } from './../../services/etiqueta-ser.service';
import { Etiqueta } from './../../../modelo/etiqueta';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2'
import { Router } from '@angular/router';


@Component({
  selector: 'app-public-revista',
  templateUrl: './public-revista.component.html',
  styleUrls: ['./public-revista.component.css']
})
export class PublicRevistaComponent implements OnInit {
  publicRForm!: FormGroup;
  etiquetas: Etiqueta[] = [];

  constructor(private router: Router, private formBuilder: FormBuilder, private etiquetaSer: EtiquetaSerService) {
    this.etiquetaSer.getEtiquetas().subscribe((etiquetas: Etiqueta[])=>{
      this.etiquetas = etiquetas;
    });
  }

  ngOnInit(): void {
    this.publicRForm = this.formBuilder.group({
      tituloRevista:[null, Validators.required],
      categoria:[null, Validators.required],
      descripcion: [null, Validators.required],
      tipoRevista: [null, Validators.required],
      precioRevi:[null, Validators.required],
      revista:[null, Validators.required],
    });

  }

  public publicarRevista(){
    if (this.publicRForm.valid) {
      this.popAfirmation();
      this.router.navigate(['home-editor'])
    }else{
      this.popErro();
    }
  }

  onchange(){
    console.log(this.etiquetas);
  }

  public popAfirmation(){
    Swal.fire(
      'Revisata Publicada con Exito',
      'proceso completado',
      'success'
    )
  }

  public popErro(){
    Swal.fire(
      'Error',
      'Algun valor incorrecto',
      'error'
    )
  }

}
