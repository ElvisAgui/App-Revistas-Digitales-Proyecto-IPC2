import { Usuario } from './../../modelo/Usuario/Usuario.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  readonly API_URL = "http://localhost:8080/RevistasDigitales/";

  constructor(private httpClient: HttpClient) {}


  public crearUsuario(usuarioN: Usuario): Observable<Usuario> {
    return this.httpClient  .post<Usuario>(this.API_URL + "ControladorUsuario", usuarioN);
  }
}
