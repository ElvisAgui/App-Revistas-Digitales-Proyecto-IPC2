package com.proyectoipc.revistasdigitales.servlet;

import com.proyectoipc.Entidades.Revista;
import com.proyectoipc.SQL.RevistaSQL;
import com.proyectoipc.comvert.RevistaConvert;
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
public class PermisosControl extends HttpServlet {

    private RevistaSQL revistaSQL;

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

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.revistaSQL = new RevistaSQL();
        RevistaConvert revistaConver = new RevistaConvert(Revista.class);
        Revista revista = revistaConver.fromJson(lector(request));
        this.revistaSQL.ActulizarPermisos(revista);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

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
