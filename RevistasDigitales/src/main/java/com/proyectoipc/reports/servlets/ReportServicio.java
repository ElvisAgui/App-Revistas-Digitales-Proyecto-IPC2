package com.proyectoipc.reports.servlets;

import com.proyectoipc.reports.entidades.Comentario;
import com.proyectoipc.reports.entidades.Comentarios;
import com.proyectoipc.reports.entidades.GananciaEdit;
import com.proyectoipc.reports.entidades.Reaccion;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author elvis_agui
 */
public class ReportServicio {

    public void printReportComentario(OutputStream targetStream, List<Comentario> comentarios) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportsCompile/ComentarioReports.jasper");
        JRDataSource source = new JRBeanCollectionDataSource(comentarios);
        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);
        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }
    public void printReportComentM(OutputStream targetStream, List<Comentarios> comentarios) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportsCompile/MasComentadaPadre.jasper");
        JRDataSource source = new JRBeanCollectionDataSource(comentarios);
        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);
        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }

    public void printReporMeGusta(OutputStream targetStream, List<Reaccion> reacciones) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportsCompile/ReaccionesDilt.jasper");
        JRDataSource source = new JRBeanCollectionDataSource(reacciones);
        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);
        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }
    
     public void printRepoGanancia(OutputStream targetStream, List<GananciaEdit> ganancia) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportsCompile/GananciasDol.jasper");
        JRDataSource source = new JRBeanCollectionDataSource(ganancia);
        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);
        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }
     
     
     public void printRepoSuscrip(OutputStream targetStream, List<GananciaEdit> ganancia) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportsCompile/SuscripcionPadre.jasper");
        JRDataSource source = new JRBeanCollectionDataSource(ganancia);
        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);
        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }
     
    

}
