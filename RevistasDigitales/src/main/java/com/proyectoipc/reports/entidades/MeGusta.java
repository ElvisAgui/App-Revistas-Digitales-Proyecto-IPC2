
package com.proyectoipc.reports.entidades;

import java.util.Date;



/**
 *
 * @author elvis_agui
 */
public class MeGusta {
    private Date fecha;
    private String usuario;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "MeGusta{" + "fecha=" + fecha + ", usuario=" + usuario + '}';
    }


}
