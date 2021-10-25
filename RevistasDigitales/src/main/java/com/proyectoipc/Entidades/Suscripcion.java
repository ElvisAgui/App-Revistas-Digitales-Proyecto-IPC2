package com.proyectoipc.Entidades;

/**
 *
 * @author elvis_agui
 */
public class Suscripcion {

    private String usuario;
    private String fecha_pago;
    private double fecha_vencimiento;
    private String revista;
    private double totalPago;
    private double gananciaEditor;
    private double gananciaAdmin;
    private boolean activo;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public double getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(double fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(double totalPago) {
        this.totalPago = totalPago;
    }

    public double getGananciaEditor() {
        return gananciaEditor;
    }

    public void setGananciaEditor(double gananciaEditor) {
        this.gananciaEditor = gananciaEditor;
    }

    public double getGananciaAdmin() {
        return gananciaAdmin;
    }

    public void setGananciaAdmin(double gananciaAdmin) {
        this.gananciaAdmin = gananciaAdmin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    

    public void calcularValores(double precioGloval) {
        if (this.fecha_vencimiento > 0 && this.totalPago != 0) {
            precioGloval = (precioGloval/100);
            this.gananciaEditor = (this.totalPago * this.fecha_vencimiento) - (this.totalPago*precioGloval*this.fecha_vencimiento);
            this.gananciaAdmin = (this.totalPago * precioGloval*fecha_vencimiento);
        } else {
            this.gananciaAdmin = 0;
            this.gananciaEditor = 0;
        }
    }

    @Override
    public String toString() {
        return "Suscripcion{" + "usuario=" + usuario + ", fecha_pago=" + fecha_pago + ", fecha_vencimiento=" + fecha_vencimiento + ", revista=" + revista + ", totalPago=" + totalPago + ", gananciaEditor=" + gananciaEditor + ", gananciaAdmin=" + gananciaAdmin + '}';
    }

}
