import { Etiqueta } from "./etiqueta";

export class Revista {
  titulo!: String;
  categoria!: String;
  categoriaNueva!: String;
  descripcion!: String;
  tipoRevista!: String;
  precio!: String;
  editor!: String;
  etiquetas: Etiqueta[] = [];
 
  constructor(titulo: String, categoria: String, categoriaNueva: String, descripcion: String, precio: String, tipoRevista: String, editor:String, etiquetas: Etiqueta[]){
    this.titulo = titulo;
    this.categoria = categoria;
    this.categoriaNueva = categoriaNueva;
    this.descripcion = categoria;
    this.tipoRevista = tipoRevista;
    this.precio = precio;
    this.descripcion = descripcion;
    this.editor = editor;
    this.etiquetas = etiquetas;
  }

}
