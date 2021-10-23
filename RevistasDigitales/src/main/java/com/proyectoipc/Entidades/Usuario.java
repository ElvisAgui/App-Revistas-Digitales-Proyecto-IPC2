
package com.proyectoipc.Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elvis_agui
 */
public class Usuario {
    
    private String usuario;
    private String password;
    private Integer tipoCuenta;
    private String hobbie;
    private String descripcion;
    private List<Etiqueta> etiquetas= new ArrayList<>();
    private String foto;
    private String type;

    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Integer getRol() {
        return tipoCuenta;
    }

    public void setRol(Integer rol) {
        this.tipoCuenta = rol;
    }

    public Integer getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(Integer tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getHobbie() {
        return hobbie;
    }

    public void setHobbie(String hobbie) {
        this.hobbie = hobbie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
