package com.proyectoipc.SQL;

import com.proyectoipc.Entidades.Categoria;
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
        }

        return categorias;
    }
}
