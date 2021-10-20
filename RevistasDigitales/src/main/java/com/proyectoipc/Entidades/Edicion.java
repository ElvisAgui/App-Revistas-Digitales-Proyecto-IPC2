
package com.proyectoipc.Entidades;

import java.sql.Date;

/**
 *
 * @author elvis_agui
 */
public class Edicion {
    private int codigo;
    private Date fecha;
    private String descripcion;
    private String revista;
    private String tipo;
    
    public Edicion(){
        
    }

    public Edicion(int codigo, Date fecha, String descripcion, String revista, String tipo) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.revista = revista;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    
}
