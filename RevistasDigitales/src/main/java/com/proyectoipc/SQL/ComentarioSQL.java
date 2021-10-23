package com.proyectoipc.SQL;

import com.proyectoipc.Entidades.Comentario;
import com.proyectoipc.conexionSQL.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elvis_agui
 */
public class ComentarioSQL {

    private PreparedStatement query = null;
    private ResultSet result = null;
    private Connection conexion = null;

    public void guardarComentario(Comentario comentario) {
        String consulta = "INSERT INTO comentario(usuario, contenido, revista, fecha) VALUES (?,?,?,?)";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, comentario.getUsuario());
            query.setString(2, comentario.getContenido());
            query.setString(3, comentario.getRevista());
            query.setDate(4, this.getFecha(comentario.getFecha()));
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error en insertar comentario " + e.getMessage());
        } catch (ParseException ex) {
            System.out.println("error en parceo " + ex.getMessage());
        } finally {
            cierre();
        }
    }

    public List<Comentario> comentarios(String titulo) {
        List<Comentario> comentarios = new ArrayList<>();
        String consulta = "SELECT * FROM comentario WHERE revista =?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, titulo);
            result = query.executeQuery();
            while (result.next()) {
                Comentario temp = new Comentario();
                temp.setContenido(result.getString("contenido"));
                temp.setUsuario(result.getString("usuario"));
                temp.setRevista(result.getString("revista"));
                temp.setFechaD(result.getDate("fecha"));
                comentarios.add(temp);
            }
        } catch (SQLException e) {
            System.out.println("erro en listar las comentarios " + e.getMessage());
        } finally {
            this.cierre();
        }
        return comentarios;
    }

    /**
     * transforma la fecha para ser ingresada en la base de datos
     *
     * @param localDate
     * @return
     * @throws ParseException
     */
    private Date getFecha(String localDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        java.util.Date nFecha = formato.parse(localDate);
        fecha = new java.sql.Date(nFecha.getTime());
        return fecha;
    }

    private void cierre() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar sql  db");
            }
        }
        if (result != null) {
            try {
                result.close();
            } catch (SQLException ex) {
                System.out.println("error al cerrar resul");
            }
        }
        if (query != null) {
            try {
                query.close();
            } catch (SQLException ex) {
                System.out.println("error al cerrar query");
            }
        }

    }
}
