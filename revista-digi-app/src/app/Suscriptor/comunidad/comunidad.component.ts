import { Reaccion } from '../../../modelo/Revista/reaccion';
import { Suscripcion } from '../../../modelo/Usuario/suscripcion';
import { Component, OnInit } from '@angular/core';
import { Comentario } from 'src/modelo/Revista/comentario';
import { Revista } from 'src/modelo/Revista/revista';
import Swal from 'sweetalert2';
import { NvarServiceService } from '../../services/nvar-service.service';
import { SuscripService } from '../../services/suscrip.service';

@Component({
  selector: 'app-comunidad',
  templateUrl: './comunidad.component.html',
  styleUrls: ['./comunidad.component.css']
})
export class ComunidadComponent implements OnInit {

  comentario = "";
  fecha = "";
  coment!:Comentario;
  comentarios:Comentario[] = [];
  revist!:String;
  revistas: Revista[] = [];
  suscription!:Suscripcion;
  revista!: Revista;
  mostrarComentario = false;
  reaccion!:Reaccion;
  reacciones: Reaccion[] = [];
  yaGusta = false;

    constructor(private suscrSer: SuscripService, private navar: NvarServiceService) { 
    this.revistas = this.navar.revistas;
    this.suscription = this.navar.suscripcion;
    this.revistas.forEach(revistaOb => {
      if (this.suscription.revista === revistaOb.titulo) {
         this.revist = revistaOb.titulo;
         this.revista = revistaOb;  
      }
    });
    this.suscrSer.getListaComentarios().subscribe((comentarios: Comentario[])=>{
      this.comentarios = comentarios;
      console.log(comentarios);
    });
    this.suscrSer.getListaReacciones().subscribe((reacciones: Reaccion[])=>{
        this.reacciones = reacciones;
        this.reacciones.forEach(reaccion => {
          if (reaccion.usuario === this.navar.usuario.usuario && reaccion.usuario === this.revist) {
            this.yaGusta = true;
          }
        });
    });
  }

  ngOnInit(): void {
  }

  public async comentar(){
    const { value: text } = await Swal.fire({
      input: 'textarea',
      inputLabel: 'Message',
      inputPlaceholder: 'Type your message here...',
      inputAttributes: {
        'aria-label': 'Type your message here'
      },
      showCancelButton: true
    })
    
    if (text) {
      this.comentario = text;
      //enviar comentario xd
      
      this.coment = new Comentario(this.comentario, this.navar.usuario.usuario, this.revist ,this.navar.fecha);
      console.log(this.coment);
      this.suscrSer.crearComentario(this.coment).subscribe((data)=>{
        this.popAfirmation();
      })
    }
  }



  public popAfirmation() {
    Swal.fire('Comentario Registrado', 'proceso completado', 'success');
  }
  public popReaccionn() {
    Swal.fire('Genial que guen gusto xd', 'proceso completado', 'success');
  }
  public popEror() {
    Swal.fire('Error', 'proceso incompleto', 'error');
  }

  public mostrarComentari(){
    this.mostrarComentario = true;
  }

  public mostrarMeGusta(){
    this.mostrarComentario = false;
  }

  public meGusta(){
    this.reaccion = new Reaccion(this.navar.usuario.usuario, this.revist,this.navar.fecha);
    this.suscrSer.crearReaccion(this.reaccion).subscribe((data)=>{
      this.popReaccionn();
    },
    (error)=>{
      this.popEror();
    })
    console.log(this.reaccion);
  }

  public ver(contenido:String){
    Swal.fire({
      title: '<strong>Comentario</u></strong>',
      icon: 'info',
      html:
        contenido,
      showCloseButton: true,
      showCancelButton: true,
      focusConfirm: false,
      confirmButtonText:
        '<i class="fa fa-thumbs-up"></i> Great!',
      confirmButtonAriaLabel: 'Thumbs up, great!',
      cancelButtonText:
        '<i class="fa fa-thumbs-down"></i>',
      cancelButtonAriaLabel: 'Thumbs down'
    })
  }
 

}
