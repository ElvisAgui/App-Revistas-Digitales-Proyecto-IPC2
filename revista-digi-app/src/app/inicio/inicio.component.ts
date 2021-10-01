import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  
  loginForm!: FormGroup;
  
  constructor(private formBuilder: FormBuilder) {
    
  }

  
  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      usuario: [null, Validators.required],
      password: [null, Validators.required],
    });
  }

  
}
