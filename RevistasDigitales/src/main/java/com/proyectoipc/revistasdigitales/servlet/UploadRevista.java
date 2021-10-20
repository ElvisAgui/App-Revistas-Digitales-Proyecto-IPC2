package com.proyectoipc.revistasdigitales.servlet;

import com.google.gson.Gson;
import com.proyectoipc.Entidades.Revista;
import com.proyectoipc.SQL.CategoriaSQL;
import com.proyectoipc.SQL.EtiquetaSQL;
import com.proyectoipc.SQL.RevistaSQL;
import com.proyectoipc.comvert.RevistaConvert;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author elvis_agui
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 100,
        maxFileSize = 1024 * 1024 * 1000,
        maxRequestSize = 1024 * 1024 * 1000
)
public class UploadRevista extends HttpServlet {

    private Revista revista;
    private RevistaSQL revistaSQL;
    private EtiquetaSQL etiqueta;
    private CategoriaSQL categoriaSQL;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String editor = request.getParameter("editor");
        Gson s = new Gson();
        this.revistaSQL = new RevistaSQL();
        this.etiqueta = new EtiquetaSQL();
        if (editor != null) {
            List<Revista> revistas = this.revistaSQL.revistas(true, editor);
            for (Revista revista1 : revistas) {
                this.etiqueta.etiquetasRevista(revista1);
            }
            response.getWriter().append(s.toJson(revistas));
        } else {
            List<Revista> revistas = this.revistaSQL.revistas(false, "");
            for (Revista revista1 : revistas) {
                this.etiqueta.etiquetasRevista(revista1);
            }
            response.getWriter().append(s.toJson(revistas));
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RevistaConvert revistaConver = new RevistaConvert(Revista.class);
        this.revista = revistaConver.fromJson(lector(request));

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String esEdicion = request.getParameter("esEdicion");
        String revistaNom = "Revista.pdf";
        int version = this.numVersion();
        this.etiqueta = new EtiquetaSQL();
        this.revistaSQL = new RevistaSQL();
        this.categoriaSQL = new CategoriaSQL();
        Part file = request.getPart("datafile");
        String fileName = file.getHeader("Content-type");
//        String nombreArchivo = file.getSubmittedFileName();
        String path = this.getServletConfig().getServletContext().getRealPath("/archivo");
        File directorio = new File(path);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
        file.write(path + "/" + version +revistaNom);
        File archivo = new File(path + "/" + version + revistaNom);
        if (esEdicion != null) {
            this.revistaSQL.guardarEdicion(revista, archivo.toString(), fileName);
        } else {
            this.categoriaSQL.guardarCategoria(revista);
            this.etiqueta.nuevaEtiqueta(revista);
            this.revistaSQL.guardarRevista(revista);
            this.etiqueta.guardarEtiquetas(revista);
            this.revistaSQL.guardarEdicion(revista, archivo.toString(), fileName);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

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

    private int numVersion() {
        return (int) (Math.random() * 999);
    }

}
