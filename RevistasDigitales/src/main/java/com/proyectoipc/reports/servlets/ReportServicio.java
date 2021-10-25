package com.proyectoipc.reports.servlets;

import com.proyectoipc.reports.entidades.Comentario;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
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

}
