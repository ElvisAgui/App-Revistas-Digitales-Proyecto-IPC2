
package com.proyectoipc.reports.entidades;

import java.util.List;

/**
 *
 * @author elvis_agui
 */
public class GananciaEdit {
    
    private String revista;
    private double total;
    private List<Ganancia> ganancia;

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total += total;
    }

    public List<Ganancia> getMeGusta() {
        return ganancia;
    }

    public void setMeGusta(List<Ganancia> meGusta) {
        this.ganancia = meGusta;
    }
    
    
    
    
    
}
