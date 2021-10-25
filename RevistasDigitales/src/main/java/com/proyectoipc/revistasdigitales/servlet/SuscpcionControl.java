package com.proyectoipc.revistasdigitales.servlet;

import com.google.gson.Gson;
import com.proyectoipc.Entidades.Suscripcion;
import com.proyectoipc.SQL.RevistaSQL;
import com.proyectoipc.SQL.SuscripcionSQL;
import com.proyectoipc.comvert.SuscripcionConver;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elvis_agui
 */
public class SuscpcionControl extends HttpServlet {

    private Suscripcion suscripcion;
    private SuscripcionSQL suscripSQL;
    private SuscripcionConver conver;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String fecha = request.getParameter("fecha");
        this.suscripSQL = new SuscripcionSQL();
        Gson s = new Gson();
        if (usuario != null & fecha != null) {
            response.getWriter().append(s.toJson(this.suscripSQL.suscripciones(usuario, fecha)));
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RevistaSQL revista = new RevistaSQL();
        this.conver = new SuscripcionConver(Suscripcion.class);
        this.suscripSQL = new SuscripcionSQL();
        this.suscripcion = conver.fromJson(lector(request));
        this.suscripcion.calcularValores(revista.precioGlobal());
        this.suscripSQL.insertarSiscripcion(suscripcion);

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
