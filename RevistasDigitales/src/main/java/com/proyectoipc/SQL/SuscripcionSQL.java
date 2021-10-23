package com.proyectoipc.SQL;

import com.proyectoipc.Entidades.Suscripcion;
import com.proyectoipc.conexionSQL.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 *
 * @author elvis_agui
 */
public class SuscripcionSQL {

    private PreparedStatement query = null;
    private ResultSet result = null;
    private Connection conexion = null;

    public void insertarSiscripcion(Suscripcion suscripcion) {
        String consulta = "INSERT INTO suscripcion(usuario, ganancia_Sus, ganancia_Adm, revista, fecha_pago, fecha_vencimiento, pagado) VALUES (?,?,?,?,?,?,?)";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, suscripcion.getUsuario());
            query.setDouble(2, suscripcion.getGananciaEditor());
            query.setDouble(3, suscripcion.getGananciaAdmin());
            query.setString(4, suscripcion.getRevista());
            query.setDate(5, this.getFecha(suscripcion.getFecha_pago()));
            query.setDate(6, this.getFecha(suscripcion.getFecha_pago(), (int) (suscripcion.getFecha_vencimiento())));
            query.setBoolean(7, true);
            query.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error insertar EDICION " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("erroe en parse fecha" + ex.getMessage());
        } finally {
            cierre();
        }
    }

    public List<Suscripcion> suscripciones(String usuario, String fechaActula) {
        List<Suscripcion> suscripciones = new ArrayList<>();
        Date date = null;
        try {
            String consulta = "SELECT * FROM suscripcion WHERE usuario=?";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, usuario);
            result = query.executeQuery();
            while (result.next()) {   
                Suscripcion temp = new Suscripcion();
                temp.setRevista(result.getString("revista"));
                temp.setUsuario(usuario);
                date = result.getDate("fecha_vencimiento");
                temp.setActivo(this.activoAunt(date, this.getFecha(fechaActula))); 
                suscripciones.add(temp);
            }
        } catch (SQLException e) {
            System.out.println("error en listar suscripciones"+ e.getMessage());
        } catch (ParseException ex) {
            System.out.println("erroe n parceo fecha actual xdxd "+ ex.getMessage());
        } finally {
            cierre();
        }
        return suscripciones;
    }
    
    public boolean activoAunt(Date fechaVenci, Date fechaActual){
        if (fechaActual.before(fechaVenci)) {
            return true;
        }else{
            return false;
        }
       
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

    private Date getFecha(String localDate, int meces) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date fecha = null;
        java.util.Date nFecha = formato.parse(localDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nFecha);
        calendar.add(Calendar.MONTH, meces);
        fecha = new java.sql.Date(calendar.getTime().getTime());;
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
