export class Etiqueta {

    etiqueta: String;
    seleccionado: boolean;
    id: String;

    constructor(etiqueta: String, seleccionado: boolean, id: String){
        this.etiqueta= etiqueta;
        this.seleccionado= seleccionado;
        this.id =id;
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
