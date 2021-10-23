import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InicioComponent } from './General/inicio/inicio.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegistroComponent } from './General/registro/registro.component';
import { NavbarComponent } from './General/navbar/navbar.component';
import { FooterComponent } from './General/footer/footer.component';
import { HttpClientModule } from '@angular/common/http';
import { EditorHomeComponent } from './Editor/editor-home/editor-home.component';
import { PublicRevistaComponent } from './Editor/public-revista/public-revista.component';
import { ListRevistaComponent } from './Editor/list-revista/list-revista.component';
import { ActulizarRevistaComponent } from './Editor/actulizar-revista/actulizar-revista.component';
import { EdicionComponent } from './Editor/edicion/edicion.component';
import { PermisosRevistaComponent } from './Editor/permisos-revista/permisos-revista.component';
import { EdicionesComponent } from './Editor/ediciones/ediciones.component';
import { SuscripHomeComponent } from './Suscriptor/suscrip-home/suscrip-home.component';
import { HomePrincipalComponent } from './Suscriptor/home-principal/home-principal.component';
import { SuscripcionComponent } from './Suscriptor/suscripcion/suscripcion.component';
import { ComunidadComponent } from './Suscriptor/comunidad/comunidad.component';
import { HomeAdminComponent } from './home-admin/home-admin.component';    
@NgModule({
  declarations: [
    AppComponent,
    InicioComponent,
    RegistroComponent,
    NavbarComponent,
    FooterComponent,
    EditorHomeComponent,
    PublicRevistaComponent,
    ListRevistaComponent,
    ActulizarRevistaComponent,
    EdicionComponent,
    PermisosRevistaComponent,
    EdicionesComponent,
    SuscripHomeComponent,
    HomePrincipalComponent, 
    SuscripcionComponent,
    ComunidadComponent,
    HomeAdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
