import { NvarServiceService } from './nvar-service.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Revista } from 'src/modelo/Revista/revista';
import { Edicion } from 'src/modelo/Revista/edicion';

@Injectable({
  providedIn: 'root'
})
export class RevistaService {

  revista!: Revista;
  readonly API_URL = "http://localhost:8080/RevistasDigitales/";

  constructor(private httpClient: HttpClient, private nvar:NvarServiceService) { }

  
  public actulizarPermisos(revista: Revista): Observable<void>{
    return this.httpClient.put<void>(this.API_URL+"PermisosControl", revista); 
  }

  public getEdiciones(): Observable<Edicion[]>{
    this.revista = this.nvar.revista;
    return this.httpClient.get<Edicion[]>(this.API_URL+ "EdicionControl?titulo="+this.revista.titulo);
  }

}

