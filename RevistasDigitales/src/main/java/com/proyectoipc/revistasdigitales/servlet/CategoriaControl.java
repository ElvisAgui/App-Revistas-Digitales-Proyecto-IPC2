package com.proyectoipc.revistasdigitales.servlet;



import com.google.gson.Gson;
import com.proyectoipc.Entidades.Revista;
import com.proyectoipc.SQL.CategoriaSQL;
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
public class CategoriaControl extends HttpServlet {

    private CategoriaSQL categoriaSQL;
    private Revista revista;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        categoriaSQL = new CategoriaSQL();
        Gson s = new Gson();
        response.getWriter().append(s.toJson(this.categoriaSQL.categorias()));
    }
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        categoriaSQL = new CategoriaSQL();
        RevistaConvert revistaConver = new RevistaConvert(Revista.class);
        this.revista = revistaConver.fromJson(lector(request));
        this.categoriaSQL.actualizarCategoriaRevista(revista);
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
