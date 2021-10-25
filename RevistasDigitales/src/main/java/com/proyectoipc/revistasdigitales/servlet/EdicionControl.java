package com.proyectoipc.revistasdigitales.servlet;

import com.google.gson.Gson;
import com.proyectoipc.SQL.EdicionSQL;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elvis_agui
 */
public class EdicionControl extends HttpServlet {

    private EdicionSQL edicionSQL;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String descarga = request.getParameter("descarga");
        String path = request.getParameter("paht");
        if (titulo != null) {
            Gson s = new Gson();
            this.edicionSQL = new EdicionSQL();
            response.getWriter().append(s.toJson(this.edicionSQL.ediciones(titulo)));
        } else if (path != null) {
            if (descarga != null) {
                showImage(response, path, true);

            } else {
                showImage(response, path, false);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void showImage(HttpServletResponse response, String path, boolean descarga)
            throws ServletException, IOException {
        try (BufferedInputStream fileStream = new BufferedInputStream(new FileInputStream(path))) {
            if (descarga) {
                response.setHeader("Content-disposition", "attachment; filename=Revista.pdf");
            }
            response.setContentType("application/pdf");
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
