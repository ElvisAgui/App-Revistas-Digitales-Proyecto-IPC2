export class Revista {
  titulo!: String;
  categoria!: String;
  categoriaNueva!: String;
  descripcion!: String;
  tipoRevista!: String;
  precio!: String;
  editor!: String;
 
  constructor(titulo: String, categoria: String, categoriaNueva: String, descripcion: String, precio: String, tipoRevista: String, editor:String){
    this.titulo = titulo;
    this.categoria = categoria;
    this.categoriaNueva = categoriaNueva;
    this.descripcion = categoria;
    this.tipoRevista = tipoRevista;
    this.precio = precio;
    this.descripcion = descripcion;
    this.editor = editor;
  }

}
