import { Usuario } from './../../../modelo/Usuario/Usuario.model';
import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css'],
})
export class RegistroComponent implements OnInit {
  registroForm!: FormGroup;
  usuarioNuevo!: Usuario;
  editor = 'Editor';
  lector = 'Lector';

  constructor(
    private formBuilder: FormBuilder,
    private service: LoginService
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
            usuario: null,
            password: null,
            tipoCuenta: null,
          });
        },
        (erro: any) => {
          //imprimir erro
        }
      );
    }
  }
}
