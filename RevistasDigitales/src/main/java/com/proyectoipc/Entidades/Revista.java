package com.proyectoipc.Entidades;

import javax.servlet.http.Part;

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
    private Integer precio;
    private String editor;

    public Revista() {

    }

    public Revista(String titulo, String categoria, String categoriaNueva, String descripcion, String tipoRevista, Integer precio, Part revista, String editor) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.categoriaNueva = categoriaNueva;
        this.descripcion = descripcion;
        this.tipoRevista = tipoRevista;
        this.precio = precio;
        this.editor = editor;
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

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Override
    public String toString() {
        return "Revista{" + "titulo=" + titulo + ", categoriaNueva=" + categoriaNueva + ", categoria=" + categoria + ", descripcion=" + descripcion + ", tipoRevista=" + tipoRevista + ", precio=" + precio + ", editor=" + editor + '}';
    }

}
