
package com.proyectoipc.revistasdigitales.servlet;

import com.google.gson.Gson;
import com.proyectoipc.Entidades.Reaccion;
import com.proyectoipc.SQL.ReaccionSQL;
import com.proyectoipc.comvert.ReaccionConver;
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
public class ReaccionControl extends HttpServlet {
    
    private Reaccion reaccion;
    private ReaccionSQL reaccionSQL;
    private ReaccionConver convert;

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String revista = request.getParameter("revista");
        Gson s = new Gson();
        this.reaccionSQL = new ReaccionSQL();
        if (revista != null) {
            response.getWriter().append(s.toJson(this.reaccionSQL.reacciones(revista)));  
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.reaccionSQL = new ReaccionSQL();
        this.convert = new ReaccionConver(Reaccion.class);
        this.reaccion = this.convert.fromJson(lector(request));
        this.reaccionSQL.guardarReacion(reaccion);
        
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
