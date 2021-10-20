export class Edicion {

    codigo!: number;
    fecha!: String;
    descripcion!: String;
    revista!: String;
    tipo!: String;


    constructor(codigo: number, fecha: String, descripcion: String, tipo:String){
        this.codigo = codigo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this. tipo = tipo;
    }
}
