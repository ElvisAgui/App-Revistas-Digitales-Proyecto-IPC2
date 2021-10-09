import { PermisosRevistaComponent } from './Editor/permisos-revista/permisos-revista.component';
import { ListRevistaComponent } from './Editor/list-revista/list-revista.component';
import { AutenticacionService } from './services/autenticacion.service';
import { PublicRevistaComponent } from './Editor/public-revista/public-revista.component';
import { EditorHomeComponent } from './Editor/editor-home/editor-home.component';
import { RegistroComponent } from './General/registro/registro.component';
import { InicioComponent } from './General/inicio/inicio.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes, CanActivate } from '@angular/router';
import { EdicionComponent } from './Editor/edicion/edicion.component';

const routes: Routes = [
  {
    path: '',
    component: InicioComponent,
  },
  {
    path: 'Registro',
    component: RegistroComponent,
  },
  {
    path: 'home-editor',
    component: EditorHomeComponent,
    canActivate: [AutenticacionService],
  },
  {
    path: 'publicarRevista',
    component: PublicRevistaComponent,
    canActivate: [AutenticacionService],
  },
  {
    path: 'Mis-Revistas',
    component: ListRevistaComponent,
    canActivate: [AutenticacionService],
  },
  {
    path: 'Permiso-Revistas',
    component: PermisosRevistaComponent,
    canActivate: [AutenticacionService],
  },
  {
    path: 'Edicion',
    component: EdicionComponent,
    canActivate: [AutenticacionService],
  },
  {
    path: '**',
    component: InicioComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
