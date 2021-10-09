import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-editor-home',
  templateUrl: './editor-home.component.html',
  styleUrls: ['./editor-home.component.css']
})
export class EditorHomeComponent implements OnInit {

  publicarRevis = "Publicar Revista";
  editarPerfil = "Editar Perfil";
  actualizarEd = "Actulizar Edicion";
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  public publicarRevista(){
    this.router.navigate(['publicarRevista']);
  }

  public listRevistasClick(){
    this.router.navigate(['Mis-Revistas']);
  }



}
