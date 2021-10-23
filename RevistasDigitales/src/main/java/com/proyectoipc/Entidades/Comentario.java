package com.proyectoipc.Entidades;

import java.sql.Date;

/**
 *
 * @author elvis_agui
 */
public class Comentario {

    private String contenido;
    private String usuario;
    private String revista;
    private String fecha;
    private Date fechaD;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Date getFechaD() {
        return fechaD;
    }

    public void setFechaD(Date fechaD) {
        this.fechaD = fechaD;
    }

    @Override
    public String toString() {
        return "Comentario{" + "contenido=" + contenido + ", usuario=" + usuario + ", revista=" + revista + ", fecha=" + fecha + ", fechaD=" + fechaD + '}';
    }
    
    

    
}
