import { Etiqueta } from "./etiqueta";

export class Revista {
  titulo!: String;
  categoria!: String;
  categoriaNueva!: String;
  descripcion!: String;
  tipoRevista!: String;
  precio!: String;
  editor!: String;
  etiquetaNueva!:String;
  etiquetas: Etiqueta[] = [];
  suscripcion!:boolean;
  reaccionar! :boolean;
  comentar!   :boolean;
  fecha!: String;
 
  constructor(titulo: String, categoria: String, categoriaNueva: String, descripcion: String, precio: String, tipoRevista: String, editor:String,etiquetaNueva: String, etiquetas: Etiqueta[], suscripcion: boolean, reaccionar: boolean, comentar: boolean){
    this.titulo = titulo;
    this.categoria = categoria;
    this.categoriaNueva = categoriaNueva;
    this.descripcion = categoria;
    this.tipoRevista = tipoRevista;
    this.precio = precio;
    this.descripcion = descripcion;
    this.editor = editor;
    this.etiquetaNueva = etiquetaNueva;
    this.etiquetas = etiquetas;
    this.suscripcion = suscripcion;
    this.reaccionar = reaccionar;
    this.comentar = comentar;
  }

}
