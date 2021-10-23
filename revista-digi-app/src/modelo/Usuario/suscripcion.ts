export class Suscripcion {

    usuario: String;
    fecha_pago: String;
    fecha_vencimiento: number;
    revista: String;
    totalPago: number;
    gananciaEditor: String;
    gananciaAdmin: String;
    activo!:boolean;
 
    constructor(usuario: String ,fecha_pago: String, fecha_vencimiento: number, revista: String, totalPago:number, gananciaEditor: String, gananciaAdmin:String){
        this.usuario = usuario;
        this.fecha_pago = fecha_pago;
        this.fecha_vencimiento = fecha_vencimiento;
        this.revista = revista;
        this.totalPago = totalPago;
        this.gananciaEditor=gananciaEditor;
        this.gananciaAdmin = gananciaAdmin;
    }
}


