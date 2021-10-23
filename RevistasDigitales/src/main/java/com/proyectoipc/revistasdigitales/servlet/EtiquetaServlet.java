package com.proyectoipc.revistasdigitales.servlet;

import com.google.gson.Gson;
import com.proyectoipc.Entidades.Etiqueta;
import com.proyectoipc.Entidades.Revista;
import com.proyectoipc.Entidades.Usuario;
import com.proyectoipc.SQL.EtiquetaSQL;
import com.proyectoipc.SQL.UsuarioSQL;
import com.proyectoipc.comvert.EtiquetaConvert;
import com.proyectoipc.comvert.RevistaConvert;
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
public class EtiquetaServlet extends HttpServlet {

    private EtiquetaSQL etiquetaSQL;
    private Revista revista;
    private UsuarioSQL usuarioSQL;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.etiquetaSQL = new EtiquetaSQL();
        Gson s = new Gson();
        response.getWriter().append(s.toJson(etiquetaSQL.etiquetas()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EtiquetaConvert convert = new EtiquetaConvert(Etiqueta.class);
        Etiqueta etiqueta = convert.fromJson(lector(request));
        this.revista = new Revista();
        this.revista.setEtiquetaNueva(etiqueta.getEtiqueta());
        this.etiquetaSQL = new EtiquetaSQL();
        this.etiquetaSQL.nuevaEtiqueta(revista);

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String esUS = request.getParameter("esUs");
        if (esUS != null) {
            usuarioSQL = new UsuarioSQL();
            this.etiquetaSQL = new EtiquetaSQL();
            UsuarioConvert usuarioConver = new UsuarioConvert(Usuario.class);
            Usuario usuario = usuarioConver.fromJson(lector(request));
            this.etiquetaSQL.borrarEtiquetas(usuario.getUsuario(), true);
            this.etiquetaSQL.guardarEtiquetaUS(usuario);
        } else {
            this.etiquetaSQL = new EtiquetaSQL();
            RevistaConvert revistaConver = new RevistaConvert(Revista.class);
            this.revista = revistaConver.fromJson(lector(request));
            this.etiquetaSQL.borrarEtiquetas(this.revista.getTitulo(), false);
            this.etiquetaSQL.guardarEtiquetas(revista);
        }

    }

    private String lector(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        String body = "";
        String line = reader.readLine();
        while (line != null) {
            body = body + line;
            line = reader.readLine();
        }
        return body;
    }

}
