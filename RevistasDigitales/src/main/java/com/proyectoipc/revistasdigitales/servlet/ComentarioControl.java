package com.proyectoipc.revistasdigitales.servlet;

import com.google.gson.Gson;
import com.proyectoipc.Entidades.Comentario;
import com.proyectoipc.SQL.ComentarioSQL;
import com.proyectoipc.comvert.ComentarioConver;
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
public class ComentarioControl extends HttpServlet {

    private Comentario comentario;
    private ComentarioSQL comentarioSQL;
    private ComentarioConver convert;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String revista = request.getParameter("revista");
        Gson s = new Gson();
        this.comentarioSQL = new ComentarioSQL();
        if (revista != null) {
            response.getWriter().append(s.toJson(this.comentarioSQL.comentarios(revista)));
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.comentarioSQL = new ComentarioSQL();
        this.convert = new ComentarioConver(Comentario.class);
        this.comentario = convert.fromJson(lector(request));
        this.comentarioSQL.guardarComentario(comentario);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
