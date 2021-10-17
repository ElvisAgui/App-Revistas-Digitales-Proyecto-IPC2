package com.proyectoipc.revistasdigitales.servlet;



import com.google.gson.Gson;
import com.proyectoipc.SQL.CategoriaSQL;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elvis_agui
 */
public class CategoriaControl extends HttpServlet {

    private CategoriaSQL categoriaSQL = new CategoriaSQL();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson s = new Gson();
        response.getWriter().append(s.toJson(this.categoriaSQL.categorias()));
    }
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
}
