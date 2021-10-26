package com.proyectoipc.reports.servlets;

import com.proyectoipc.reports.entidades.ReportConsultEditor;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author elvis_agui
 */
public class ReportsSuscripcionesServlet extends HttpServlet {

    private ReportServicio reportServicio;
    private ReportConsultEditor reportConulta;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String descargar = request.getParameter("descarga");
            String revista = request.getParameter("revista");
            String usuario = request.getParameter("usuario");
            String fechaI = request.getParameter("fechaI");
            String fechaF = request.getParameter("fechaF");
            System.out.println(revista + " " + usuario + " " + fechaI + " " + fechaF);
            if (usuario.equalsIgnoreCase("")) {
                this.reportServicio = new ReportServicio();
                this.reportConulta = new ReportConsultEditor();
                response.setContentType("application/pdf");
                if (fechaF.equalsIgnoreCase("") || fechaI.equalsIgnoreCase("")) {
                    if (descargar != null) {
                        response.setHeader("Content-disposition", "attachment; filename=ReporteComentario.pdf");
                    }
                    this.reportServicio.printRepoSuscrip(response.getOutputStream(), this.reportConulta.GananciaEditT(revista));
                } else {
                    if (descargar != null) {
                        response.setHeader("Content-disposition", "attachment; filename=ReporteComentarios.pdf");
                    }
                    try {
                        this.reportServicio.printRepoSuscrip(response.getOutputStream(), this.reportConulta.GananciaEditT(revista, fechaI, fechaF));
                    } catch (ParseException ex) {
                        System.out.println("parcei servlet");
                    }
                }

            } else {
                this.reportServicio = new ReportServicio();
                this.reportConulta = new ReportConsultEditor();
                response.setContentType("application/pdf");
                if (fechaF.equalsIgnoreCase("") || fechaI.equalsIgnoreCase("")) {
                    if (descargar != null) {
                        response.setHeader("Content-disposition", "attachment; filename=ReporteComentario.pdf");
                    }
                    this.reportServicio.printRepoSuscrip(response.getOutputStream(), this.reportConulta.GananciaEdit(usuario, revista));
                } else {
                    if (descargar != null) {
                        response.setHeader("Content-disposition", "attachment; filename=ReporteComentarios.pdf");
                    }
                    try {
                        this.reportServicio.printRepoSuscrip(response.getOutputStream(), this.reportConulta.GananciaEdit(usuario, revista, fechaI, fechaF));
                    } catch (ParseException ex) {
                        System.out.println("parcei servlet");
                    }
                }

            }

        } catch (JRException ex) {
            System.out.println("JRException " + ex.getMessage());
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
