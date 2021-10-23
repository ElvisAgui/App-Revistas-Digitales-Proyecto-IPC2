import { FileService } from './../../services/file.service';
import { LoginService } from './../../services/login.service';
import { Usuario } from './../../../modelo/Usuario/Usuario.model';
import { NvarServiceService } from 'src/app/services/nvar-service.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Etiqueta } from 'src/modelo/Revista/etiqueta';
import { EtiquetaSerService } from 'src/app/services/etiqueta-ser.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-suscrip-home',
  templateUrl: './suscrip-home.component.html',
  styleUrls: ['./suscrip-home.component.css'],
})
export class SuscripHomeComponent implements OnInit {

  usuario!: Usuario;
  description!: FormGroup;
  etiquetaNueva = '';
  etiquetas: Etiqueta[] = [];
  etiqueta!: Etiqueta; 
  imagefil= false;
  fondoIma= false;
  selectedFile: File | null = null;
  fotoPerfil!:String;


  constructor(
    private router: Router,
    private nvar: NvarServiceService,
    private formBuilder: FormBuilder,
    private etiquetaSer: EtiquetaSerService,
    private servUs: LoginService,
    private file: FileService
  ) {
    this.usuario = this.nvar.usuario;
    this.etiquetaSer.getEtiquetas().subscribe((etiquetas: Etiqueta[]) => {
      this.etiquetas = etiquetas;
      this.etiquetasActivas();
    }); 
    if (this.usuario.foto != null) {
      this.fotoPerfil = file.downloadImage(this.usuario);
      console.log(this.fotoPerfil);
      this.fondoIma= true;
    }
  }

  ngOnInit(): void {
    this.description = this.formBuilder.group({
      descripcion: [this.usuario.descripcion],
      hobbit: [this.usuario.hobbie],
    });
  }

  public modificarEtiquetaNueva(event: Event) {
    this.etiquetaNueva = (<HTMLInputElement>event.target).value;
    console.log(this.etiquetaNueva);
  }

  public actulizarG() {
    if (
      this.description.value.descripcion != null &&
      this.description.value.hobbit != null
    ) {
      // actulizar
      this.usuario.descripcion = this.description.value.descripcion;
      this.usuario.hobbie = this.description.value.hobbit;
      this.servUs.perfil(this.usuario).subscribe((data)=>{
        this.popAfirmation();
      }, 
      (error)=>{
        this.popErro();
      });
    } else {
      //dastos invalidps
      this.popDatosInvalidos();
    }
  }


  public fileUploadInAngular(event: Event){
    const files = (event.target as  HTMLInputElement).files;
    if (files != null) {
      this.selectedFile = files.item(0);
      console.log(this.selectedFile);
    }
  }

  public cambiarFoto(){
    if (this.selectedFile != null) {
      this.file.fileUploaImagePerfil(this.selectedFile, this.usuario.usuario).subscribe((data)=>{
        this.popAfirmation();
      },
      (error)=>{
        this.popErro();
      });
      
    }
    this.selectedFile = null;
    this.imagefil= !this.imagefil;
  }



  public newEtiqueta(){
    this.etiqueta = new Etiqueta(this.etiquetaNueva,false,"41");
    if (this.etiqueta.etiqueta == null) {
      this.popDatosInvalidos();
    }else{
      this.etiquetaSer.etiquetaNueva(this.etiqueta).subscribe((data)=>{
        this.popAfirmation();
        this.etiquetaSer.getEtiquetas().subscribe((etiquetas: Etiqueta[]) => {
          this.etiquetas = etiquetas;
        });
      }, 
      (error)=>{
        this.popErro();
      });
    }
  }

  public etiquetasActivas(){
    this.etiquetas.forEach(element => {
      this.usuario.etiquetas.forEach(elementinter => {
        if (element.etiqueta === elementinter.etiqueta) {
          element.seleccionado=true; 
        }
      });
    });
  }

  public actulizarEtiquetas(){
    this.usuario.etiquetas = this.etiquetas;
    this.etiquetaSer.actulizarEtiquetasUsuario(this.usuario).subscribe((data) =>{
      this.popAfirmation();
    },
    (data) =>{
      this.popErro();
    });
  
  }

  onchange() {
    console.log(this.etiquetas);
  }

  public popAfirmation() {
    Swal.fire('Actulizado', 'proceso completado', 'success');
  }


  public popErro() {
    Swal.fire('Error', 'Algo fallo en el Servidor', 'error');
  }

  public popDatosInvalidos() {
    Swal.fire('Error', 'Algun valor incorrecto', 'error');
  }
}
