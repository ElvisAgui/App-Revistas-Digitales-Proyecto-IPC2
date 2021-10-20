import { NvarServiceService } from 'src/app/services/nvar-service.service';
import { Revista } from './../../../modelo/Revista/revista';
import { FileService } from './../../services/file.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-revista',
  templateUrl: './list-revista.component.html',
  styleUrls: ['./list-revista.component.css']
})
export class ListRevistaComponent implements OnInit {
  
  revistas: Revista[] = [];

  constructor(private router: Router, private file: FileService, private navar: NvarServiceService) { 
    this.file.getListaRevistas().subscribe((revistas: Revista[]) => {
      this.revistas = revistas;
      this.navar.totalRevistas = this.revistas.length;
    });
  }

  ngOnInit(): void {
  }

  public prmisosClick(revista: Revista){
    this.navar.revista = revista;
    this.router.navigate(['Permiso-Revistas']);
  }

  public generalClick(revista: Revista){
    this.navar.revista = revista;
    this.router.navigate(['Modidificacion-General']);

  }

  public edicionClick(revista: Revista){
    this.navar.revista = revista;
    this.router.navigate(['Edicion']);
    
  }

  public edicionesClick(revista: Revista){
    this.navar.revista = revista;
    this.router.navigate(['Ediciones']);
    
  }
}
