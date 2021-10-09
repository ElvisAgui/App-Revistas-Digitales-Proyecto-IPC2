import { Etiqueta } from './../../modelo/etiqueta';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EtiquetaSerService {
  readonly API_URL = "http://localhost:8080/RevistasDigitales/";

  constructor(private httpClient: HttpClient) {   }

  public getEtiquetas(): Observable<Etiqueta[]> {
    return this.httpClient.get<Etiqueta[]>(this.API_URL + "EtiquetaServlet");
  }
}
 