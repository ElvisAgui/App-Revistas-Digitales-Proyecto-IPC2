import { ReportsService } from './../services/reports.service';
import { FileService } from './../services/file.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Revista } from 'src/modelo/Revista/revista';
import { NvarServiceService } from '../services/nvar-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-reportes-edit',
  templateUrl: './reportes-edit.component.html',
  styleUrls: ['./reportes-edit.component.css'],
})
export class ReportesEditComponent implements OnInit {
  titulo = '';
  hideComentario = true;
  fechaF = '';
  fechaI = '';
  loginForm!: FormGroup;
  revistas: Revista[] = [];
  reportComentrio!:String;
  tipoReporte!:String;
  esEditor = false;


  constructor(private formBuilder: FormBuilder, private navrService: NvarServiceService, private file: FileService, private reports: ReportsService) {
   
      if (this.navrService.usuario.tipoCuenta === 3) {
        this.esEditor = true;
        this.file.getListaRevistasT().subscribe((revistas: Revista[]) => {
          this.revistas = revistas;
        });
      }else{
        this.file.getListaRevistas().subscribe((revistas: Revista[]) => {
          this.revistas = revistas;
        });
      }
     
  }

  ngOnInit(): void {
    this.tipoReporte = this.navrService.tipoReporte;
    this.loginForm = this.formBuilder.group({
      revista: [""],
      tipoReporte:[null, Validators.required]
    });
  }

 

  public showReporteComentrio(){
    if (this.loginForm.valid) {
      if (this.loginForm.value.tipoReporte === 'Comentarios') {
        this.reportComentrio = this.reports.ReportsComentarioPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
      }
      if (this.loginForm.value.tipoReporte === 'Revista mas Gustada') {
        this.reportComentrio = this.reports.ReportsReccionesPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
      }
      if (this.loginForm.value.tipoReporte === 'Ganancias') {
        this.reportComentrio = this.reports.ReportsGananciaPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
      }
      if (this.loginForm.value.tipoReporte === 'Suscripciones') {
        this.reportComentrio = this.reports.ReportsSucripPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
      }
      if (this.loginForm.value.tipoReporte === 'ganancias') {
        this.reportComentrio = this.reports.ReportsGananciaTPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
      }
      if (this.loginForm.value.tipoReporte === '5 revistas mas Populares') {
        this.reportComentrio = this.reports.ReportsSucripTPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
      }
      if (this.loginForm.value.tipoReporte === '5 revistas mas Comentada') {
        this.reportComentrio = this.reports.ReportsComentarioTPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
      }
    }else{
      this.popErro();
    }
  }

  public DesReporteComentrio(){
    if (this.loginForm.value.tipoReporte === 'Comentarios') {
      const revi =  this.reports.ReportsDESComentarioPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
      window.location.href=""+revi
    }
    if (this.loginForm.value.tipoReporte === 'Revista mas Gustada') {
      const revi =  this.reports.ReportsDESReaccionesPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
      window.location.href=""+revi
    }
    if (this.loginForm.value.tipoReporte === 'Ganancias') {
      const revi =  this.reports.ReportsDESGacnanciaPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
      window.location.href=""+revi
    }
    if (this.loginForm.value.tipoReporte === 'Suscripciones') {
      const revi =  this.reports.ReportsDESSuscipPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
      window.location.href=""+revi
    }

    if (this.loginForm.value.tipoReporte === 'ganancias') {
      const revi =  this.reports.ReportsDESGacnanciaTPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
      window.location.href=""+revi
    }
    if (this.loginForm.value.tipoReporte === '5 revistas mas Populares') {
      const revi =  this.reports.ReportsDESSuscipTPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
      window.location.href=""+revi
    }
    if (this.loginForm.value.tipoReporte === '5 revistas mas Comentada') {
      const revi =  this.reports.ReportsDESComentarioTPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
      window.location.href=""+revi
    }

   
  }
  public modificarFechaF(event: Event) {
    this.fechaF = (<HTMLInputElement>event.target).value;
  }

  public modificarFechaI(event: Event) {
    this.fechaI = (<HTMLInputElement>event.target).value;
  }

  public popErro(){
    Swal.fire(
      'Error',
      'Algun valor incorrecto',
      'error'
    )
  }
  
}
