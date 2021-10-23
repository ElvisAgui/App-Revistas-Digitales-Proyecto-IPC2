export class Reaccion {
  usuario: String;
  revista: String;
  fecha: String;
  FechaD!: String;

  constructor(usuario: String, revista: String, fecha: String) {
    this.usuario = usuario;
    this.revista = revista;
    this.fecha = fecha;
  }
}
