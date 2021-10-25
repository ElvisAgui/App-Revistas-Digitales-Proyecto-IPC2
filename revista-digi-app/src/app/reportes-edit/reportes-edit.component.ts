import { ReportsService } from './../services/reports.service';
import { FileService } from './../services/file.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Revista } from 'src/modelo/Revista/revista';

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


  constructor(private formBuilder: FormBuilder, private file: FileService, private reports: ReportsService) {
    this.file.getListaRevistas().subscribe((revistas: Revista[]) => {
      this.revistas = revistas;
    });
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      revista: [""],
    });
  }

  public comentraiosSide() {
    this.titulo = 'Reporte de Comentarios';
    this.hideComentario = false;
  }

  public showReporteComentrio(){
   this.reportComentrio = this.reports.ReportsComentarioPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
   this.hideComentario = true;
  }

  public DesReporteComentrio(){
    const revi =  this.reports.ReportsDESComentarioPDF(this.loginForm.value.revista, this.fechaI, this.fechaF);
    window.location.href=""+revi
  }
  public modificarFechaF(event: Event) {
    this.fechaF = (<HTMLInputElement>event.target).value;
  }

  public modificarFechaI(event: Event) {
    this.fechaI = (<HTMLInputElement>event.target).value;
  }
}
