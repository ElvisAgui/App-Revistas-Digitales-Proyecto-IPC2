package com.proyectoipc.Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elvis_agui
 */
public class Revista {

    private String titulo;
    private String categoria;
    private String categoriaNueva;
    private String descripcion;
    private String tipoRevista;
    private double precio;
    private String editor;
    private String etiquetaNueva;
    private List<Etiqueta> etiquetas= new ArrayList<>();
    private boolean suscripcion;
    private boolean reaccionar;
    private boolean comentar;
    private String fecha;
    private double precioGlobal;
    
    
    public Revista() {

    }

    

    public String getCategoriaNueva() {
        return categoriaNueva;
    }

    public void setCategoriaNueva(String categoriaNueva) {
        this.categoriaNueva = categoriaNueva;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoRevista() {
        return tipoRevista;
    }

    public void setTipoRevista(String tipoRevista) {
        this.tipoRevista = tipoRevista;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getEtiquetaNueva() {
        return etiquetaNueva;
    }

    public void setEtiquetaNueva(String etiquetaNueva) {
        this.etiquetaNueva = etiquetaNueva;
    }
    

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public boolean isSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(boolean suscripcion) {
        this.suscripcion = suscripcion;
    }

    public boolean isReaccionar() {
        return reaccionar;
    }

    public void setReaccionar(boolean reaccionar) {
        this.reaccionar = reaccionar;
    }

    public boolean isComentar() {
        return comentar;
    }

    public void setComentar(boolean comenar) {
        this.comentar = comenar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPrecioGlobal() {
        return precioGlobal;
    }

    public void setPrecioGlobal(double precioGlobal) {
        this.precioGlobal = precioGlobal;
    }
    
    

    @Override
    public String toString() {
        return "Revista{" + "titulo=" + titulo + ", categoria=" + categoria + ", categoriaNueva=" + categoriaNueva + ", descripcion=" + descripcion + ", tipoRevista=" + tipoRevista + ", precio=" + precio + ", editor=" + editor + ", etiquetaNueva=" + etiquetaNueva + ", etiquetas=" + etiquetas + ", suscripcion=" + suscripcion + ", reaccionar=" + reaccionar + ", comentar=" + comentar + ", fecha=" + fecha + '}';
    }
    
    
    
    

    

}
