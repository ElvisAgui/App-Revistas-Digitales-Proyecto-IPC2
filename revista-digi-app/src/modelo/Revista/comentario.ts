export class Comentario {

      contenido:String;
      usuario:String;
      revista:String;
      fecha:String;
      fechaD!:String;

      constructor(contenido:String, usuario:String, revista:String,fecha:String ){
        this.contenido= contenido;
        this.usuario = usuario;
        this.revista = revista;
        this.fecha = fecha;
      }


}
