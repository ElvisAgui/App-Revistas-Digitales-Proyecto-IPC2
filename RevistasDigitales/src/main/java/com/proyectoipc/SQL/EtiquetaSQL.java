package com.proyectoipc.SQL;

import com.proyectoipc.Entidades.Etiqueta;
import com.proyectoipc.Entidades.Revista;
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
public class EtiquetaSQL {

    private PreparedStatement query = null;
    private ResultSet result = null;
    private Connection conexion = null;

    public List<Etiqueta> etiquetas() {
        List<Etiqueta> etiquetas = new ArrayList<>();
        String consulta = "SELECT * FROM etiqueta";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            result = query.executeQuery();
            while (result.next()) {
                Etiqueta temp = new Etiqueta();
                temp.setEtiqueta(result.getString("nombre_Etiqueta"));
                temp.setSeleccionado(false);
                etiquetas.add(temp);
            }
        } catch (SQLException e) {
            System.out.println("erro en listar las etiquetas");
        }

        return etiquetas;
    }

    public void guardarEtiquetas(Revista revista) {
        for (Etiqueta etiqueta : revista.getEtiquetas()) {
            if (etiqueta.isSeleccionado()) {
                this.guardarIndividua(etiqueta, revista.getTitulo());
            }
        }
    }

    private void guardarIndividua(Etiqueta etiqueta, String titulo) {
        String consulta = "INSERT INTO revist_Etiqueta(etiqueta, revista) VALUES (?,?)";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, etiqueta.getEtiqueta());
            query.setString(2, titulo);
            query.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error insertar etiqueta de revista " + ex.getMessage());
        } finally {
            cierre();
        }
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
