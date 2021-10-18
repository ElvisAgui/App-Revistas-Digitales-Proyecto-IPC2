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
        } finally {
            this.cierre();
        }

        return etiquetas;
    }

    public void etiquetasRevista(Revista revista) {
        String consulta = "SELECT * FROM revist_Etiqueta WHERE revista=?";
        List<Etiqueta> etiquetas = new ArrayList<>();
        revista.setEtiquetas(etiquetas);
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, revista.getTitulo());
            result = query.executeQuery();
            while (result.next()) {
                Etiqueta temp = new Etiqueta();
                temp.setId(result.getInt("id"));
                temp.setEtiqueta(result.getString("etiqueta"));
                temp.setSeleccionado(true);
                revista.getEtiquetas().add(temp);
            }
        } catch (SQLException e) {
            System.out.println("erro en listar las etiquetas de revistas "+ e.getMessage());
        } finally {
            this.cierre();
        }
    }

    public void nuevaEtiqueta(Revista revista) {
        if (revista.getEtiquetaNueva() != null && !revista.getEtiquetaNueva().equalsIgnoreCase("")) {
            Etiqueta etiqueta = new Etiqueta();
            etiqueta.setSeleccionado(true);
            etiqueta.setEtiqueta(revista.getEtiquetaNueva());
            String consulta = "INSERT INTO etiqueta(nombre_Etiqueta) VALUES (?)";
            try {
                conexion = Conexion.getConexion();
                query = conexion.prepareStatement(consulta);
                query.setString(1, revista.getEtiquetaNueva());
                query.executeUpdate();
                revista.getEtiquetas().add(etiqueta);
            } catch (SQLException ex) {
                System.out.println("Error insertar etiqueta nueva" + ex.getMessage());
            } finally {
                cierre();
            }
        }

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
