
package com.proyectoipc.conexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author elvis_agui
 */
public class Conexion {
    public static String URL = "jdbc:mysql://localhost:3306/Revistas_Digitales";
    public static String USER = "admin3";
    public static String PASS = "admin3P";
    public static Connection conexion = null;
    
    
    public static Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            System.err.println("erro en conexion sql");
        } catch (ClassNotFoundException ex) {
            System.err.println("erro en conexion calas name");

        }
        return conexion;

    }
    
    public static void getCloseConexion(){
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException ex) {
            }
            
        }
    }
}
