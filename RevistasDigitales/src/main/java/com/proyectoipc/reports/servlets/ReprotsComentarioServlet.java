package com.proyectoipc.reports.servlets;

import com.proyectoipc.reports.entidades.ReportConsultEditor;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author elvis_agui
 */
public class ReprotsComentarioServlet extends HttpServlet {

    private ReportServicio reportServicio;
    private ReportConsultEditor reportConulta;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

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
                    this.reportServicio.printReportComentM(response.getOutputStream(), this.reportConulta.ComentairoTotal());
                } 
            } else {
                this.reportServicio = new ReportServicio();
                this.reportConulta = new ReportConsultEditor();
                response.setContentType("application/pdf");
                if (fechaF.equalsIgnoreCase("") || fechaI.equalsIgnoreCase("")) {
                    if (descargar != null) {
                        response.setHeader("Content-disposition", "attachment; filename=ReporteComentario.pdf");
                    }
                    this.reportServicio.printReportComentario(response.getOutputStream(), this.reportConulta.Comentarios(revista, usuario));
                } else {
                    if (descargar != null) {
                        response.setHeader("Content-disposition", "attachment; filename=ReporteComentarios.pdf");
                    }
                    this.reportServicio.printReportComentario(response.getOutputStream(), this.reportConulta.Comentarios(fechaI, fechaF, revista, usuario));
                }
            }

        } catch (JRException ex) {
            System.out.println("JRException " + ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
