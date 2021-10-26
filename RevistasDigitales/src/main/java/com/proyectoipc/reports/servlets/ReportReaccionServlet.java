package com.proyectoipc.reports.servlets;

import com.proyectoipc.reports.entidades.MeGusta;
import com.proyectoipc.reports.entidades.Reaccion;
import com.proyectoipc.reports.entidades.ReportConsultEditor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author elvis_agui
 */
public class ReportReaccionServlet extends HttpServlet {

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
            this.reportServicio = new ReportServicio();
            this.reportConulta = new ReportConsultEditor();
            response.setContentType("application/pdf");
            if (fechaF.equalsIgnoreCase("") || fechaI.equalsIgnoreCase("")) {
                if (descargar != null) {
                    response.setHeader("Content-disposition", "attachment; filename=ReporteComentario.pdf");
                }
                for (Reaccion reaccione : this.reportConulta.reacciones(usuario, revista)) {
                    System.out.println(reaccione.toString());
                    for (MeGusta meGusta : reaccione.getMeGusta()) {
                        System.out.println("entre a detalles");
                        System.out.println(meGusta.toString());
                    }
                }
                this.reportServicio.printReporMeGusta(response.getOutputStream(), this.reportConulta.reacciones(usuario, revista));
            } else {
                 if (descargar != null) {
                    response.setHeader("Content-disposition", "attachment; filename=ReporteComentarios.pdf");
                }
                this.reportServicio.printReporMeGusta(response.getOutputStream(), this.reportConulta.reacciones(usuario, revista, fechaI, fechaF));
            }
        } catch (JRException ex) {
            System.out.println("JRException " + ex.getMessage());
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
