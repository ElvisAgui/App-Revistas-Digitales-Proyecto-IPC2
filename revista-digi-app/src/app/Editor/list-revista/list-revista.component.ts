import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-revista',
  templateUrl: './list-revista.component.html',
  styleUrls: ['./list-revista.component.css']
})
export class ListRevistaComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  public PrmisosClick(){
    this.router.navigate(['Permiso-Revistas']);
  }

  public edicionClick(){
    this.router.navigate(['Edicion']);
  }
}
