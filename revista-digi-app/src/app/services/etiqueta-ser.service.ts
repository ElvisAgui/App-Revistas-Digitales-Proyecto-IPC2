import { Categoria } from '../../modelo/Revista/categoria';
import { Etiqueta } from '../../modelo/Revista/etiqueta';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Revista } from 'src/modelo/Revista/revista';

@Injectable({
  providedIn: 'root'
})
export class EtiquetaSerService {
  readonly API_URL = "http://localhost:8080/RevistasDigitales/";

  constructor(private httpClient: HttpClient) {   }

  public getEtiquetas(): Observable<Etiqueta[]> {
    return this.httpClient.get<Etiqueta[]>(this.API_URL + "EtiquetaServlet");
  }


  public getCategorias(): Observable<Categoria[]> {
    return this.httpClient.get<Categoria[]>(this.API_URL + "CategoriaControl");
  }


  public actulizarGeneal(revista: Revista): Observable<void> {
    return this.httpClient.put<void>(this.API_URL+ "CategoriaControl", revista); 
  }

  public actulizarEtiquetas(revista: Revista): Observable<void> {
    return this.httpClient.put<void>(this.API_URL+ "EtiquetaServlet", revista); 
  }
}
 