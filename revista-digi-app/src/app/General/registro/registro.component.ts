import { Usuario } from './../../../modelo/Usuario/Usuario.model';
import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NvarServiceService } from 'src/app/services/nvar-service.service';
import Swal from 'sweetalert2'
import { InicioComponent } from '../inicio/inicio.component';


@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css'],
})
export class RegistroComponent implements OnInit {
  registroForm!: FormGroup;
  usuario!: Usuario;
  editor = 'Editor';
  lector = 'Lector';

  constructor(
    private formBuilder: FormBuilder,
    private service: LoginService,
    private router: Router,
    private navrService: NvarServiceService
  ) {}
  ngOnInit(): void {
    this.registroForm = this.formBuilder.group({
      usuario: [null, Validators.required],
      password: [null, Validators.required],
      tipoCuenta: [null, Validators.required],
    });
  }

  public crearUsuarioNuevo() {
    if (this.registroForm.valid) {
      this.service.crearUsuario(this.registroForm.value).subscribe(
        (created: Usuario) => {
          this.registroForm.reset({
            "usuario": null,
            "password": null,
            "tipoCuenta": null,
          });
          this.navrService.usuario = created;
          this.verificacion(created);
        },
        (erro: any) => {
          //imprimir erro
        });
    }
  }

  public verificacion(usuario: Usuario){
    if ( usuario != null) {
      if (usuario.tipoCuenta === 1) {
        InicioComponent.autenticado=true;
        this.popAfirmation();
        this.router.navigate(['home-editor']);
      }else if (usuario.tipoCuenta === 2) {
        InicioComponent.autenticado=true;
        this.popAfirmation();
        this.router.navigate(['Perfil-Usuario']);
      } 
    }else{
      InicioComponent.autenticado=false;
      this.popErro();
      
    }
  }

  public popAfirmation(){
    Swal.fire(
      'BIENVENIDO',
      'Importante configurar Tu Perfil',
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
