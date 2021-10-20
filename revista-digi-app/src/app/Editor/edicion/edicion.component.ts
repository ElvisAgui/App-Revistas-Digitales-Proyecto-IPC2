import { FileService } from './../../services/file.service';
import { NvarServiceService } from 'src/app/services/nvar-service.service';
import { Revista } from './../../../modelo/Revista/revista';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-edicion',
  templateUrl: './edicion.component.html',
  styleUrls: ['./edicion.component.css']
})
export class EdicionComponent implements OnInit {
 
  revista!: Revista;
  selectedFile: File | null = null;
  valid = false;
  fecha = "";
  descripcion = "";

  constructor(private router: Router, private navar: NvarServiceService, private file: FileService) { 
    this.revista = this.navar.revista;
  }

  ngOnInit(): void {
  }

  public publicarRevista() {
    if (this.selectedFile != null && this.valid) {
      this.revista.fecha = this.fecha;
      this.revista.descripcion = this.descripcion;
      console.log(this.revista);
      this.file.crearRevista(this.revista).subscribe(
        (data) => {
          this.enviarRevista();
        },
        (error) => {
          this.popErro();
        }
      );
      
    } else {
      this.popErro();
    }
  }

  public enviarRevista(){
    if (this.selectedFile != null) {
      this.file.fileUploadEdicion(this.selectedFile).subscribe((data) => {
        this.popAfirmation();
        this.router.navigate(['home-editor']);
      },
        (error) => {
          this.popDatosInvalidos();
        });
    }
  }

  public popAfirmation(){
    Swal.fire(
      'Edicion Atualizada',
      'proceso completado  con Exito',
      'success'
    )
  }

  public fileUploadInAngular(event: Event){
    this.valido();
    const files = (event.target as  HTMLInputElement).files;
    if (files != null) {
      this.selectedFile = files.item(0);
      console.log(this.selectedFile);
    }
  }

  public valido(){
    if (this.selectedFile != null && this.fecha != "" && this.descripcion != "") {
      this.valid= true;
    }
  }

  public modificarFecha(event: Event){
    this.fecha = (<HTMLInputElement>event.target).value;
    this.valido();
  }

  public modificarDescripcion(event: Event){
    this.descripcion = (<HTMLInputElement>event.target).value;
    this.valido();
    
  }

  public popErro() {
    Swal.fire('Error', 'Algo fallo en el Servidor', 'error');
  }

  public popDatosInvalidos() {
    Swal.fire('Error', 'Algun valor incorrecto', 'error');
  }
}


