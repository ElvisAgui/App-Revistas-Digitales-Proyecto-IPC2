package com.proyectoipc.revistasdigitales.servlet;

import com.proyectoipc.Entidades.Usuario;
import com.proyectoipc.SQL.EtiquetaSQL;
import com.proyectoipc.SQL.UsuarioSQL;
import com.proyectoipc.comvert.UsuarioConvert;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
public class ControladorUsuario extends HttpServlet {

    private UsuarioSQL usuarioSQL;
    private EtiquetaSQL etiquetaSQL;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        usuarioSQL = new UsuarioSQL();
        String usuario = request.getParameter("usuario");
        if (usuario != null) {
            Usuario temp = new Usuario();
            temp.setUsuario(usuario);
            this.usuarioSQL.exist(temp, true);
            System.out.println(temp.getFoto() + "  " + temp.getType());
            this.showImage(response, temp.getFoto(), temp.getType());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        usuarioSQL = new UsuarioSQL();
        this.etiquetaSQL = new EtiquetaSQL();
        UsuarioConvert usuarioConver = new UsuarioConvert(Usuario.class);
        Usuario usuario = usuarioConver.fromJson(lector(request));
        if (this.usuarioSQL.login(usuario)) {
            usuario = this.usuarioSQL.logUsuario();
            this.usuarioSQL.generalPerfil(usuario, true);
            this.etiquetaSQL.etiquetasUsuario(usuario);
            response.getWriter().append(usuarioConver.toJson(usuario));
        } else {
            if (this.usuarioSQL.RegistrarUsuario()) {
                response.getWriter().append(usuarioConver.toJson(usuario));
            } else {
                response.getWriter().append(usuarioConver.toJson(null));
            }

        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String esImage = request.getParameter("esImage");
        usuarioSQL = new UsuarioSQL();
        if (esImage != null) {
            String revistaNom = "Foto.jpg";
            int version = this.numVersion();
            Part file = request.getPart("datafile");
            String fileName = file.getHeader("Content-type");
//        String nombreArchivo = file.getSubmittedFileName();
            String path = this.getServletConfig().getServletContext().getRealPath("/archivo");
            File directorio = new File(path);
            if (!directorio.exists()) {
                directorio.mkdir();
            }
            file.write(path + "/" + version + revistaNom);
            File archivo = new File(path + "/" + version + revistaNom);
            this.usuarioSQL.guardarFoto(esImage, archivo.toString(), fileName);
        } else {
            UsuarioConvert usuarioConver = new UsuarioConvert(Usuario.class);
            Usuario usuario = usuarioConver.fromJson(lector(request));
            this.usuarioSQL.generalPerfil(usuario, false);
        }

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

    private void showImage(HttpServletResponse response, String path, String type)
            throws ServletException, IOException {
        try (BufferedInputStream fileStream = new BufferedInputStream(new FileInputStream(path))) {
            response.setContentType(type);
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        }

    }

    private int numVersion() {
        return (int) (Math.random() * 999);
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
