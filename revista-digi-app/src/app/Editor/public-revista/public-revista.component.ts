import { Revista } from './../../../modelo/Revista/revista';
import { FileService } from './../../services/file.service';
import { Categoria } from '../../../modelo/Revista/categoria';
import { EtiquetaSerService } from './../../services/etiqueta-ser.service';
import { Etiqueta } from '../../../modelo/Revista/etiqueta';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { NvarServiceService } from 'src/app/services/nvar-service.service';

@Component({
  selector: 'app-public-revista',
  templateUrl: './public-revista.component.html',
  styleUrls: ['./public-revista.component.css'],
})
export class PublicRevistaComponent implements OnInit {
  publicRForm!: FormGroup;
  etiquetas: Etiqueta[] = [];
  categorias: Categoria[] = [];
  selectedFile: File | null = null;
  
  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private etiquetaSer: EtiquetaSerService,
    private file: FileService,
    private navrService: NvarServiceService
  ) {
    this.etiquetaSer.getEtiquetas().subscribe((etiquetas: Etiqueta[]) => {
      this.etiquetas = etiquetas;
    });
    this.etiquetaSer.getCategorias().subscribe((categorias: Categoria[]) => {
      this.categorias = categorias;
    });
  }

  ngOnInit(): void {
    this.publicRForm = this.formBuilder.group({
      titulo: [null, Validators.required],
      categoria: [null],
      categoriaNueva: [null],
      descripcion: [null, Validators.required],
      tipoRevista: [null, Validators.required],
      precio: [0, Validators.required],
      editor:[this.navrService.usuario.usuario]
    });
  }

  public publicarRevista() {
    if (this.publicRForm.valid && this.selectedFile != null) {
      this.file.crearRevista(this.publicRForm.value).subscribe(
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
      this.file.fileUpload(this.selectedFile).subscribe((data) => {
        this.popAfirmation();
          console.log(this.publicRForm.value);
          this.router.navigate(['home-editor']);
      },
        (error) => {
          this.popDatosInvalidos();
        });
    }
  }

  
  public fileUploadInAngular(event: Event){
    const files = (event.target as  HTMLInputElement).files;
    if (files != null) {
      this.selectedFile = files.item(0);
      console.log(this.selectedFile);
    }
  }
  onchange() {
    console.log(this.etiquetas);
  }

  public popAfirmation() {
    Swal.fire('Revisata Publicada con Exito', 'proceso completado', 'success');
  }

  public popErro() {
    Swal.fire('Error', 'Algo fallo en el Servidor', 'error');
  }

  public popDatosInvalidos() {
    Swal.fire('Error', 'Algun valor incorrecto', 'error');
  }
}
