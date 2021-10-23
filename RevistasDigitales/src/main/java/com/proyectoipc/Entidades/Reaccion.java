
package com.proyectoipc.Entidades;

import java.sql.Date;

/**
 *
 * @author elvis_agui
 */
public class Reaccion {
    private String usuario;
    private String revista;
    private String fecha;
    private Date FechaD;

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
        return FechaD;
    }

    public void setFechaD(Date FechaD) {
        this.FechaD = FechaD;
    }
    
    
   
    
}
