import { Reaccion } from './../../modelo/Revista/reaccion';
import { Comentario } from './../../modelo/Revista/comentario';
import { Suscripcion } from './../../modelo/Usuario/suscripcion';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NvarServiceService } from './nvar-service.service';

@Injectable({
  providedIn: 'root'
})
export class SuscripService {
  usuario!:String;
  readonly API_URL = "http://localhost:8080/RevistasDigitales/";

  constructor(private httpClient: HttpClient,private navar: NvarServiceService) {}

   public crearSuscripcion(suscripcion: Suscripcion): Observable <void> {
    return this.httpClient.post<void>(this.API_URL+"SuscpcionControl", suscripcion);
  }

  public getListaRevistas(): Observable<Suscripcion[]>{
    this.usuario = this.navar.usuario.usuario;
    return this.httpClient.get<Suscripcion[]>(this.API_URL+ "SuscpcionControl?usuario="+this.usuario+"&fecha="+this.navar.fecha);
  }

  public crearComentario(comentario: Comentario): Observable <void> {
    return this.httpClient.post<void>(this.API_URL+"ComentarioControl", comentario);
  }

  

  public getListaComentarios(): Observable<Comentario[]>{
    return this.httpClient.get<Comentario[]>(this.API_URL+ "ComentarioControl?revista="+this.navar.suscripcion.revista);
  }

  public getListaReacciones(): Observable<Reaccion[]>{
    return this.httpClient.get<Reaccion[]>(this.API_URL+ "ReaccionControl?revista="+this.navar.suscripcion.revista);
  }
  
  public crearReaccion(reaccion: Reaccion): Observable <void> {
    return this.httpClient.post<void>(this.API_URL+"ReaccionControl", reaccion);
  }
}
