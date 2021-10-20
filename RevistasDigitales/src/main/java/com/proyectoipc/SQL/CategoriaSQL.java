package com.proyectoipc.SQL;

import com.proyectoipc.Entidades.Categoria;
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
public class CategoriaSQL {

    private PreparedStatement query = null;
    private ResultSet result = null;
    private Connection conexion = null;

    public List<Categoria> categorias() {
        List<Categoria> categorias = new ArrayList<>();
        String consulta = "SELECT * FROM categoria";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            result = query.executeQuery();
            while (result.next()) {
                Categoria temp = new Categoria();
                temp.setCategoria(result.getString("nombre_Categoria"));
                categorias.add(temp);
            }
        } catch (SQLException e) {
            System.out.println("erro en listar CATEGORIAS");
        } finally {
            cierre();
        }
        return categorias;
    }

    /**
     * guarda la categoria de la revista
     *
     * @param revista
     */
    public void guardarCategoria(Revista revista) {
        if (null != revista.getCategoriaNueva()) {
            revista.setCategoria(revista.getCategoriaNueva());
            String consulta = "INSERT INTO categoria(nombre_Categoria) VALUES (?)";
            try {
                conexion = Conexion.getConexion();
                query = conexion.prepareStatement(consulta);
                query.setString(1, revista.getCategoria());
                query.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error insertar categoria nueva " + ex.getMessage());
            } finally {
                cierre();
            }
        }
    }

    public void actualizarCategoriaRevista(Revista revista) {
        String consulta = "UPDATE revista SET  categoria=?, precio=? WHERE titulo=? ";
         try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, revista.getCategoria());
            query.setDouble(2, revista.getPrecio());
            query.setString(3, revista.getTitulo());
            query.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Fallo en actulizar los general "+ ex.getMessage());
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
