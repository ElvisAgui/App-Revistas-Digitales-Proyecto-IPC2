import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NvarServiceService } from './nvar-service.service';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {

  readonly API_URL = "http://localhost:8080/RevistasDigitales/";

  constructor(private httpClient: HttpClient, private nvar:NvarServiceService) { }


  public ReportsComentarioPDF(revista: string, fechaI: String, fechaF: String): string {
    return this.API_URL+"ReprotsComentarioServlet?revista="+revista+"&usuario="+this.nvar.usuario.usuario+"&fechaI="+fechaI+"&fechaF="+fechaF;
  }

  public ReportsDESComentarioPDF(revista: string, fechaI: String, fechaF: String): string {
    return this.API_URL+"ReprotsComentarioServlet?revista="+revista+"&usuario="+this.nvar.usuario.usuario+"&fechaI="+fechaI+"&fechaF="+fechaF+"&descarga=true";
  }
}
