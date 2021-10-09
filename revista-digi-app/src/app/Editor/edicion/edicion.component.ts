import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-edicion',
  templateUrl: './edicion.component.html',
  styleUrls: ['./edicion.component.css']
})
export class EdicionComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }


  public popAfirmation(){
    Swal.fire(
      'Edicion Atualizada',
      'proceso completado  con Exito',
      'success'
    )
    this.router.navigate(['home-editor']);
  }
}
