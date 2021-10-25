import { ReportesEditComponent } from './reportes-edit/reportes-edit.component';
import { HomeAdminComponent } from './home-admin/home-admin.component';
import { ComunidadComponent } from './Suscriptor/comunidad/comunidad.component';
import { HomePrincipalComponent } from './Suscriptor/home-principal/home-principal.component';
import { SuscripHomeComponent } from './Suscriptor/suscrip-home/suscrip-home.component';
import { EdicionesComponent } from './Editor/ediciones/ediciones.component';
import { ActulizarRevistaComponent } from './Editor/actulizar-revista/actulizar-revista.component';
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
import { SuscripcionComponent } from './Suscriptor/suscripcion/suscripcion.component';

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
    path: 'Modidificacion-General',
    component: ActulizarRevistaComponent,
    canActivate: [AutenticacionService],
  },
  {
    path: 'Ediciones',
    component: EdicionesComponent,
    canActivate: [AutenticacionService],
  },
  {
    path: 'Perfil-Usuario',
    component: SuscripHomeComponent,
    canActivate: [AutenticacionService],
  },
  {
    path: 'suscripcion',
    component: SuscripcionComponent,
    canActivate: [AutenticacionService],
  },
  {
    path: 'home-Usuario',
    component: HomePrincipalComponent,
    canActivate: [AutenticacionService],
  },
  {
    path: 'comunidad',
    component: ComunidadComponent,
    canActivate: [AutenticacionService],
  },
  {
    path: 'home-Admin',
    component: HomeAdminComponent,
    canActivate: [AutenticacionService],
  },
  {
    path: 'Reportes-Editor',
    component: ReportesEditComponent,
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
