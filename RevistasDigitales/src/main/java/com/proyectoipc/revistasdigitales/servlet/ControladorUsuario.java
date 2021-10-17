package com.proyectoipc.revistasdigitales.servlet;

import com.proyectoipc.Entidades.Usuario;
import com.proyectoipc.SQL.UsuarioSQL;
import com.proyectoipc.comvert.UsuarioConvert;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elvis_agui
 */
public class ControladorUsuario extends HttpServlet {

    private UsuarioSQL usuarioSQL;
  

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        usuarioSQL = new UsuarioSQL();
        UsuarioConvert usuarioConver = new UsuarioConvert(Usuario.class);
        Usuario usuario = usuarioConver.fromJson(lector(request));
        if (this.usuarioSQL.login(usuario)) {
            usuario = this.usuarioSQL.logUsuario();
            response.getWriter().append(usuarioConver.toJson(usuario));
        } else {
            if (this.usuarioSQL.RegistrarUsuario()) {
                response.getWriter().append(usuarioConver.toJson(usuario));
            } else {
                response.getWriter().append(usuarioConver.toJson(null));
            }

        }

    }
    
    private String lector(HttpServletRequest request) throws IOException{
        BufferedReader reader = request.getReader();
        String body = "";
        String line = reader.readLine();
        while (line != null) {
            body = body + line;
            line = reader.readLine();
        }
        
        return body;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
