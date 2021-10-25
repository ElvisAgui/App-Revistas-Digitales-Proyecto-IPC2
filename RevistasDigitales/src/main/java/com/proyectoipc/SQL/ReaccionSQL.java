package com.proyectoipc.SQL;

import com.proyectoipc.Entidades.Reaccion;
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
public class ReaccionSQL {

    private PreparedStatement query = null;
    private ResultSet result = null;
    private Connection conexion = null;

    public void guardarReacion(Reaccion reaccion) {
        String consulta = "INSERT INTO reaccion(usuario, revista, fecha) VALUES (?,?,?)";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, reaccion.getUsuario());
            query.setString(2, reaccion.getRevista());
            query.setDate(3, this.getFecha(reaccion.getFecha()));
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error en insertar reaccion " + e.getMessage());
        } catch (ParseException ex) {
            System.out.println("error en parceo " + ex.getMessage());
        } finally {
            cierre();
        }

    }

    public List<Reaccion> reacciones(String titulo) {
        List<Reaccion> Reacciones = new ArrayList<>();
        String consulta = "SELECT * FROM reaccion WHERE revista =?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, titulo);
            result = query.executeQuery();
            while (result.next()) {
                Reaccion temp = new Reaccion();
                temp.setUsuario(result.getString("usuario"));
                temp.setRevista(result.getString("revista"));
                temp.setFechaD(result.getDate("fecha"));
                Reacciones.add(temp);
            }
        } catch (SQLException e) {
            System.out.println("erro en listar las reaccions " + e.getMessage());
        } finally {
            this.cierre();
        }
        return Reacciones;
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
    
    public void actulizarPrecioGlobal(double precio){
         String consulta = "UPDATE usuario set costo_Global=?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setDouble(1, precio);
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error en actulizar precio global" + e.getMessage());
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
