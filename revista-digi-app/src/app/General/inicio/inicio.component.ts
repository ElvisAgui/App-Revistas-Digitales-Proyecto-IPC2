import { Usuario } from './../../../modelo/Usuario/Usuario.model';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css'],
})
export class InicioComponent implements OnInit {
  loginForm!: FormGroup;
  usuario!: Usuario;

  constructor(private formBuilder: FormBuilder) {}
  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      usuario: [null, Validators.required],
      password: [null, Validators.required],
    });
  }


  public crearUsuario(){
    if (this.loginForm.valid) {
      
    }
  }
}
