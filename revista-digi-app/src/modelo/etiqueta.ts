export class Etiqueta {

    etiqueta: String;
    seleccionado: boolean;


    constructor(etiqueta: String, seleccionado: boolean){
        this.etiqueta= etiqueta;
        this.seleccionado= seleccionado;
    }


    get getEtiqueta(){
        return this.etiqueta;
    }

    set setEtiqueta(etiqueta: String){
        this.etiqueta= etiqueta;
    }

    get getSeleccionado(){
        return this.seleccionado;
    }

    set setSeleccionado(seleccionado: boolean){
        this.seleccionado = seleccionado;
    }
}
