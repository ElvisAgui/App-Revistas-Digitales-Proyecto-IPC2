/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoipc.revistasdigitales.servlet;

import com.google.gson.Gson;
import com.proyectoipc.SQL.EtiquetaSQL;
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
        
        
        
        
    }
    
    
    

}
