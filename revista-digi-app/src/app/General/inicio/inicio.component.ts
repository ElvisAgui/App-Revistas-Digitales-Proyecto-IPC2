import { LoginService } from './../../services/login.service';
import { Usuario } from './../../../modelo/Usuario/Usuario.model';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'
  import { NvarServiceService } from 'src/app/services/nvar-service.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css'],
})
export class InicioComponent implements OnInit {
  loginForm!: FormGroup;
  usuario!: Usuario;
  static autenticado= false  ;
 
  constructor(private formBuilder: FormBuilder, private router: Router, private service: LoginService, private navrService: NvarServiceService) {}
  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      usuario: [null, Validators.required],
      password: [null, Validators.required],
      tipoCuenta:[-1],
    });
  }

  public registrarse(){
    this.router.navigate(['Registro']);
  }

  public crearUsuarioNuevo() {
    if (this.loginForm.valid) {
      this.service.crearUsuario(this.loginForm.value).subscribe(
        (created: Usuario) => {
          this.loginForm.reset({
            "usuario": null,
            "password" : null,
          });
            this.navrService.usuario = created;
            this.verificacion(created);
        },
        (erro: any) => {
          
        }
      );
      this.router.navigate(['home-editor']);
    }
  }

  public verificacion(usuario: Usuario){
    if ( usuario != null) {
      InicioComponent.autenticado=true;
      this.popAfirmation();
      this.router.navigate(['home-editor']);
    }else{
      InicioComponent.autenticado=false;
      this.popErro();
      
    }
  }

  public popAfirmation(){
    Swal.fire(
      'BIENVENIDO',
      'Revistas Digitales',
      'success'
    )
  }

  public popErro(){
    Swal.fire(
      'Contraseña o Usuario incorrector',
      'Registrate',
      'error'
    )
  }
}
