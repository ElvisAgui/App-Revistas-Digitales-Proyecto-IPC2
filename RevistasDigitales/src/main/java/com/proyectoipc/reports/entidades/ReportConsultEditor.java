package com.proyectoipc.reports.entidades;

import com.proyectoipc.conexionSQL.Conexion;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elvis_agui
 */
public class ReportConsultEditor extends ReportsConsult {

    public List<Comentario> Comentarios(String revista, String usuario) {
        List<Comentario> comentarios = new ArrayList<>();
        String consulta;
        try {
            conexion = Conexion.getConexion();
            if (revista.equalsIgnoreCase("")) {
                consulta = "SELECT * FROM comentario JOIN revista ON (comentario.revista = revista.titulo) WHERE revista.editor=?";
                query = conexion.prepareStatement(consulta);
                query.setString(1, usuario);
            } else {
                consulta = "SELECT * FROM comentario JOIN revista ON (comentario.revista = revista.titulo) WHERE comentario.revista=? AND revista.editor=?";
                query = conexion.prepareStatement(consulta);
                query.setString(1, revista);
                query.setString(2, usuario);
            }
            result = query.executeQuery();
            while (result.next()) {
                Comentario comentario = new Comentario();
                comentario.setContenido(result.getString("contenido"));
                comentario.setUsuario(result.getString("usuario"));
                comentario.setRevista(result.getString("revista"));
                comentario.setFecha(result.getDate("fecha"));
                if (comentario.getContenido() != null && !this.repiteContenido(comentarios, comentario.getContenido())) {
                    comentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            System.out.println("erro en listar las etiquetas");
        } finally {
            this.cierre();
        }
        return comentarios;
    }

    public boolean repiteContenido(List<Comentario> Comentarios, String comentario) {
        boolean reptido = false;
        for (Comentario Comentario1 : Comentarios) {
            if (Comentario1.getContenido().equalsIgnoreCase(comentario)) {
                reptido = true;
                break;
            }
        }
        return reptido;
    }

    public List<Comentario> Comentarios(String fechaI, String fechaF, String revista, String usuario) {
        List<Comentario> comentarios = new ArrayList<>();
        String consulta;
        try {
            conexion = Conexion.getConexion();
            if (revista.equalsIgnoreCase("")) {
                consulta = "SELECT * FROM comentario JOIN revista ON (comentario.revista = revista.titulo) WHERE comentario.fecha BETWEEN ? AND ? AND revista.editor=?";
                query = conexion.prepareStatement(consulta);
                query.setDate(1, this.getFechaSF(fechaI));
                query.setDate(2, this.getFechaSF(fechaF));
                query.setString(3, usuario);
            } else {
                consulta = "SELECT * FROM comentario JOIN revista ON (comentario.revista = revista.titulo) WHERE  comentario.fecha BETWEEN ? AND ? AND comentario.revista=? AND revista.editor=?";
                query = conexion.prepareStatement(consulta);
                query.setDate(1, this.getFechaSF(fechaI));
                query.setDate(2, this.getFechaSF(fechaF));
                query.setString(3, revista);
                query.setString(4, usuario);
            }
            result = query.executeQuery();
            while (result.next()) {
                Comentario comentario = new Comentario();
                comentario.setContenido(result.getString("contenido"));
                comentario.setUsuario(result.getString("usuario"));
                comentario.setRevista(result.getString("revista"));
                comentario.setFecha(result.getDate("fecha"));
                comentarios.add(comentario);
                if (comentario.getContenido() != null && !this.repiteContenido(comentarios, comentario.getContenido())) {
                    comentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            System.out.println("erro en Lista Reporte Comentarios Fecja" + e.getMessage());
        } catch (ParseException ex) {
            System.out.println("erroe en pareceo fecha reporte Comentrio Fecha" + ex.getMessage());
        } finally {
            this.cierre();
        }
        return comentarios;
    }

}
