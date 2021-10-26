
package com.proyectoipc.reports.entidades;

import java.util.Date;

/**
 *
 * @author elvis_agui
 */
public class Ganancia {
    
    private String suscriptor;
    private Date fechI;
    private double ganancia;
    private double gastos;
    private Date fechaF;

    public String getSuscriptor() {
        return suscriptor;
    }

    public void setSuscriptor(String suscriptor) {
        this.suscriptor = suscriptor;
    }

    public Date getFechI() {
        return fechI;
    }

    public void setFechI(Date fechI) {
        this.fechI = fechI;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    public Date getFechaF() {
        return fechaF;
    }

    public void setFechaF(Date fechaF) {
        this.fechaF = fechaF;
    }
    
    
}
