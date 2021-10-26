package com.proyectoipc.reports.entidades;

import java.util.List;

/**
 *
 * @author elvis_agui
 */
public class Reaccion {

    private String revista;
    private int total;
    private List<MeGusta> meGusta;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }
    
     public List<MeGusta> getMeGusta() {
        return meGusta;
    }

    public void setMeGusta(List<MeGusta> meGusta) {
        this.meGusta = meGusta;
    }

    @Override
    public String toString() {
        return "Reaccion{" + "revista=" + revista + ", total=" + total + ", meGusta=" + meGusta + '}';
    }
    
    

}
