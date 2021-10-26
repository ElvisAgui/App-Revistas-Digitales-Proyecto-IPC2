
package com.proyectoipc.reports.entidades;

import java.util.List;

/**
 *
 * @author elvis_agui
 */
public class Comentarios {
    private String revista;
    private int total;
    private List<Comentario> comentarios;

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    
    
    
    
}
