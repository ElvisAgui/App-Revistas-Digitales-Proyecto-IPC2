
package com.proyectoipc.SQL;

import com.proyectoipc.Entidades.Etiqueta;
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
    
    public List<Etiqueta> etiquetas(){
        List<Etiqueta> etiquetas= new ArrayList<>();
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
}
