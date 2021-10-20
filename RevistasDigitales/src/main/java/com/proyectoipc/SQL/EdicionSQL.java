package com.proyectoipc.SQL;

import com.proyectoipc.Entidades.Edicion;
import com.proyectoipc.conexionSQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elvis_agui
 */
public class EdicionSQL {

    private PreparedStatement query = null;
    private ResultSet result = null;
    private Connection conexion = null;
    
    
    public List<Edicion> ediciones(String titulo) {
    List<Edicion> ediciones = new ArrayList<>();
        String consulta = "SELECT * FROM edicion WHERE revista=?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, titulo);
            result = query.executeQuery();
            while (result.next()) {
                Edicion temp = new Edicion();
                temp.setCodigo(result.getInt("codigo"));
                temp.setFecha(result.getDate("fecha"));
                temp.setDescripcion(result.getString("descripcion"));
                temp.setRevista(result.getString("revista_pdf"));
                temp.setTipo(result.getString("content_type"));
                ediciones.add(temp);
            }
        } catch (SQLException e) {
            System.out.println("erro en listar las ediciones   "+ e.getMessage());
        } finally {
            this.cierre();
        }
        return ediciones;
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
