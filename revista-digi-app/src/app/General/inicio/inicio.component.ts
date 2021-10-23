import { LoginService } from './../../services/login.service';
import { Usuario } from './../../../modelo/Usuario/Usuario.model';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { NvarServiceService } from 'src/app/services/nvar-service.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css'],
})
export class InicioComponent implements OnInit {
  loginForm!: FormGroup;
  usuario!: Usuario;
  static autenticado = false;
  fecha = "";

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private service: LoginService,
    private navrService: NvarServiceService
  ) {}
  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      usuario: [null, Validators.required],
      password: [null, Validators.required],
      tipoCuenta: [-1],
    });
  }

  public registrarse() {
    this.router.navigate(['Registro']);
  }
  
  /**
   * solicitud de pedir al servidor el usuario logeado
   */
  public crearUsuarioNuevo() {
    if (this.loginForm.valid && this.fecha != "") {
      this.navrService.fecha = this.fecha
      this.service.crearUsuario(this.loginForm.value).subscribe(
        (created: Usuario) => {
          this.loginForm.reset({
            usuario: null,
            password: null,
          });
          this.navrService.usuario = created;
          this.verificacion(created);
        },
        (erro: any) => {
          //pag errro
        }
      );
    }else{
      this.popErro();
    }
  }

  /**
   * verifica que el usuario exista y redirecciona al area de trabajo
   * @param usuario 
   */
  public verificacion(usuario: Usuario) {
    if (usuario != null && usuario.tipoCuenta === 1) {
      InicioComponent.autenticado = true;
      this.popAfirmation(usuario);
      this.router.navigate(['home-editor']);
    } else {
      if (usuario != null && usuario.tipoCuenta === 2) {
        InicioComponent.autenticado = true;
        this.popAfirmation(usuario);
        if (usuario.etiquetas.length === 0) {
          this.router.navigate(['Perfil-Usuario']);
        }else{
          this.router.navigate(['home-Usuario']);
        }
      } else {
        if (usuario != null && usuario.tipoCuenta === 3) {
          InicioComponent.autenticado = true;
          this.popAfirmation(usuario);
          this.router.navigate(['home-Admin']);
        }else{
          InicioComponent.autenticado = false;
          this.popErro();
        }
        
      }
    }
  }

  public popAfirmation(usuario: Usuario) {
    Swal.fire('BIENVENIDO', '' + usuario.usuario, 'success');
  }

  public popErro() {
    Swal.fire('Contraseña o Usuario incorrector', 'Registrate', 'error');
  }

  public modificarFecha(event: Event){
    this.fecha = (<HTMLInputElement>event.target).value;
    console.log(this.fecha);
  }
}
