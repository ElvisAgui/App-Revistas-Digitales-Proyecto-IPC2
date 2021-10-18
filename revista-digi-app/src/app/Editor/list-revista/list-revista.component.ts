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
  constructor(private router: Router, private file: FileService) { 
    this.file.getListaRevistas().subscribe((revistas: Revista[]) => {
      this.revistas = revistas;
    });
  }

  ngOnInit(): void {
  }

  public PrmisosClick(){
    this.router.navigate(['Permiso-Revistas']);
  }

  public edicionClick(){
    this.router.navigate(['Edicion']);
    console.log(this.revistas);
  }
}
