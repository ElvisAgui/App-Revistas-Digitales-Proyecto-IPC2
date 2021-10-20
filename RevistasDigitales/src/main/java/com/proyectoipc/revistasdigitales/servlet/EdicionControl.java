
package com.proyectoipc.revistasdigitales.servlet;

import com.google.gson.Gson;
import com.proyectoipc.SQL.EdicionSQL;
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
        if (titulo != null) {
            Gson s = new Gson();
            this.edicionSQL = new EdicionSQL();
            response.getWriter().append(s.toJson(this.edicionSQL.ediciones(titulo)));
        }
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
