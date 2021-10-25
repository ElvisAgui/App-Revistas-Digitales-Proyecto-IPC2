import { Router } from '@angular/router';
import { SuscripService } from 'src/app/services/suscrip.service';
import { Component, OnInit } from '@angular/core';
import { Reaccion } from 'src/modelo/Revista/reaccion';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-home-admin',
  templateUrl: './home-admin.component.html',
  styleUrls: ['./home-admin.component.css']
})
export class HomeAdminComponent implements OnInit {

  ocultarGlobal = false;
  porcentajeGlobal = "";

  constructor(private suscribSev: SuscripService, private router: Router) { }

  ngOnInit(): void {
  }


  public actulizarGlobal(){
    //al servidor
    this.suscribSev.actulizarGlobal(new Reaccion('sf','jfd','jfsl'), this.porcentajeGlobal).subscribe((data)=>{
      this.popAfirmation();
    });

  }

  public actulizarPorcentajeGlobal(event: Event){
    this.porcentajeGlobal=(<HTMLInputElement>event.target).value;
    console.log(this.porcentajeGlobal);

  }

  public oculatar(){
    this.ocultarGlobal = !this.ocultarGlobal;
  }

  public costoPorDia(){
    this.router.navigate(['Mis-Revistas']);
  }

  public popAfirmation() {
    Swal.fire('Porcentaje Aculizado', 'proceso completo', 'success');
  }
}
