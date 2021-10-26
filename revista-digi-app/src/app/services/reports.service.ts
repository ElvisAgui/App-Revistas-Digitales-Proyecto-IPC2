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
  public ReportsComentarioTPDF(revista: string, fechaI: String, fechaF: String): string {
    return this.API_URL+"ReprotsComentarioServlet?revista="+revista+"&usuario="+"&fechaI="+fechaI+"&fechaF="+fechaF;
  }

  public ReportsDESComentarioTPDF(revista: string, fechaI: String, fechaF: String): string {
    return this.API_URL+"ReprotsComentarioServlet?revista="+revista+"&usuario="+"&fechaI="+fechaI+"&fechaF="+fechaF+"&descarga=true";
  }

  public ReportsReccionesPDF(revista: string, fechaI: String, fechaF: String): string {
    return this.API_URL+"ReportReaccionServlet?revista="+revista+"&usuario="+this.nvar.usuario.usuario+"&fechaI="+fechaI+"&fechaF="+fechaF;
  }

  public ReportsDESReaccionesPDF(revista: string, fechaI: String, fechaF: String): string {
    return this.API_URL+"ReportReaccionServlet?revista="+revista+"&usuario="+this.nvar.usuario.usuario+"&fechaI="+fechaI+"&fechaF="+fechaF+"&descarga=true";
  }

  public ReportsGananciaPDF(revista: string, fechaI: String, fechaF: String): string {
    return this.API_URL+"ReportsGananciaServlet?revista="+revista+"&usuario="+this.nvar.usuario.usuario+"&fechaI="+fechaI+"&fechaF="+fechaF;
  }

  public ReportsDESGacnanciaPDF(revista: string, fechaI: String, fechaF: String): string {
    return this.API_URL+"ReportsGananciaServlet?revista="+revista+"&usuario="+this.nvar.usuario.usuario+"&fechaI="+fechaI+"&fechaF="+fechaF+"&descarga=true";
  }

  public ReportsSucripPDF(revista: string, fechaI: String, fechaF: String): string {
    return this.API_URL+"ReportsSuscripcionesServlet?revista="+revista+"&usuario="+this.nvar.usuario.usuario+"&fechaI="+fechaI+"&fechaF="+fechaF;
  }

  public ReportsDESSuscipPDF(revista: string, fechaI: String, fechaF: String): string {
    return this.API_URL+"ReportsSuscripcionesServlet?revista="+revista+"&usuario="+"&fechaI="+fechaI+"&fechaF="+fechaF+"&descarga=true";
  }


  public ReportsGananciaTPDF(revista: string, fechaI: String, fechaF: String): string {
    return this.API_URL+"ReportsGananciaServlet?revista="+revista+"&usuario="+"&fechaI="+fechaI+"&fechaF="+fechaF;
  }

  public ReportsDESGacnanciaTPDF(revista: string, fechaI: String, fechaF: String): string {
    return this.API_URL+"ReportsGananciaServlet?revista="+revista+"&usuario="+"&fechaI="+fechaI+"&fechaF="+fechaF+"&descarga=true";
  }

  public ReportsSucripTPDF(revista: string, fechaI: String, fechaF: String): string {
    return this.API_URL+"ReportsSuscripcionesServlet?revista="+revista+"&usuario="+"&fechaI="+fechaI+"&fechaF="+fechaF;
  }

  public ReportsDESSuscipTPDF(revista: string, fechaI: String, fechaF: String): string {
    return this.API_URL+"ReportsSuscripcionesServlet?revista="+revista+"&usuario="+"&fechaI="+fechaI+"&fechaF="+fechaF+"&descarga=true";
  }



}
