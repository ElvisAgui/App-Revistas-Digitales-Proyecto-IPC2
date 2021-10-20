import { Categoria } from './../../../modelo/Revista/categoria';
import { EtiquetaSerService } from './../../services/etiqueta-ser.service';
import { Etiqueta } from './../../../modelo/Revista/etiqueta';
import { Component, OnInit } from '@angular/core';
import { NvarServiceService } from 'src/app/services/nvar-service.service';
import { Revista } from 'src/modelo/Revista/revista';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-actulizar-revista',
  templateUrl: './actulizar-revista.component.html',
  styleUrls: ['./actulizar-revista.component.css']
})
export class ActulizarRevistaComponent implements OnInit {
  
  generalR!: FormGroup;
  revista!:Revista;
  titulo!:String;
  etiquetas:Etiqueta[]=[];
  categorias:Categoria[]=[];
  validPre = false; 
  
  constructor(private navar: NvarServiceService, private etiquetaSer: EtiquetaSerService, private formBuilder: FormBuilder,
    ) {
    this.revista = this.navar.revista;
    this.titulo = this.revista.titulo;
    this.etiquetaSer.getEtiquetas().subscribe((etiquetas: Etiqueta[]) => {
      this.etiquetas = etiquetas;
      this.etiquetasActivas();

    });
    this.etiquetaSer.getCategorias().subscribe((categorias: Categoria[]) => {
      this.categorias = categorias;
    });
    
   }

  ngOnInit(): void {
    this.generalR = this.formBuilder.group({
      categoria: [null],
      precio: [this.revista.precio],
    });

    

    
  }

  public actulizarGeneral(){
    if (this.generalR.value.categoria != null) {
      this.revista.categoria = this.generalR.value.categoria;
    }
    if (this.generalR.value.precio != null && this.generalR.value.precio >=0) {
      this.revista.precio = this.generalR.value.precio;
      this.validPre = true;
    }else{
      this.validPre = false;
    }
    if (this.validPre) {
      this.etiquetaSer.actulizarGeneal(this.revista).subscribe((data)=>{
          this.popAfirmation();  
      },
      (error)=>{
          this.popErro();
      });

    }else{
      this.popDatosInvalidos();
    }
  }

  onchange() {
    console.log(this.etiquetas);
  }

  public etiquetasActivas(){
    this.etiquetas.forEach(element => {
      this.revista.etiquetas.forEach(elementinter => {
        if (element.etiqueta === elementinter.etiqueta) {
          element.seleccionado=true; 
        }
      });
    });
  }

  public actulizarEtiquetas(){
    this.revista.etiquetas = this.etiquetas;
     this.etiquetaSer.actulizarEtiquetas(this.revista).subscribe((data)=>{
        this.popAfirmation();
     },
     (error)=>{
      this.popErro();
  });
  }

  public popAfirmation() {
    Swal.fire('Actulizacion exitosa', 'proceso completado', 'success');
  }

  public popErro() {
    Swal.fire('Error', 'Algo fallo en el Servidor', 'error');
  }

  public popDatosInvalidos() {
    Swal.fire('Error', 'Algun valor incorrecto', 'error');
  }

}
