import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-editor-home',
  templateUrl: './editor-home.component.html',
  styleUrls: ['./editor-home.component.css']
})
export class EditorHomeComponent implements OnInit {

  publicarRevis = "Publicar Revista";
  editarPerfil = "Editar Perfil";
  actualizarEd = "Actulizar Edicion";
  constructor() { }

  ngOnInit(): void {
  }

}
