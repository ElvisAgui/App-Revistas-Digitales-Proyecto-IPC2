package com.proyectoipc.SQL;

import com.proyectoipc.Entidades.Usuario;
import com.proyectoipc.SQL.encript.Encript;
import com.proyectoipc.conexionSQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author elvis_agui
 */
public class UsuarioSQL {

    private PreparedStatement query = null;
    private ResultSet result = null;
    private Connection conexion = null;
    private final Encript encripPass = new Encript();
    private Usuario usuario;
    private String newPassword;

    public boolean RegistrarUsuario() {
        boolean registrado = true;
        this.newPassword = encripPass.ecnode(this.usuario.getPassword());
        try {
            String consulta = "INSERT INTO usuario(contraseña, nombre, rol) VALUES (?,?,?)";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, this.newPassword);
            query.setString(2, this.usuario.getUsuario());
            query.setInt(3, this.usuario.getRol());
            query.executeUpdate();
        } catch (SQLException ex) {
            registrado = false;
            System.out.println("Error en registrar usuarioNuevo " + ex.getMessage());
        } finally {
            cierre();
        }

        return registrado;
    }

    public boolean login(Usuario usuario) {
        this.usuario = usuario;
        if (this.usuario.getRol() == -1) {
            return true;
        } else {
            return false;
        }
    }

    public Usuario logUsuario() {
        Usuario usuarioLogi = null;
        this.newPassword = encripPass.ecnode(this.usuario.getPassword());
        String consulta = "SELECT * FROM usuario WHERE nombre = ? AND contraseña = ?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, this.usuario.getUsuario());
            query.setString(2, this.newPassword);
            result = query.executeQuery();
            while (result.next()) {
                usuarioLogi = new Usuario();
                usuarioLogi.setPassword(result.getString("contraseña"));
                usuarioLogi.setUsuario(result.getString("nombre"));
                usuarioLogi.setRol(result.getInt("rol"));
            }
        } catch (SQLException ex) {
            System.out.println("erro en log usuario");
        } finally {
            cierre();
        }

        return usuarioLogi;
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
