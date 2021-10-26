package com.proyectoipc.reports.entidades;

import com.proyectoipc.conexionSQL.Conexion;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import com.proyectoipc.reports.entidades.GananciaEdit;

/**
 *
 * @author elvis_agui
 */
public class ReportConsultEditor extends ReportsConsult {

    public List<Comentario> Comentarios(String revista, String usuario) {
        List<Comentario> comentarios = new ArrayList<>();
        String consulta;
        try {
            conexion = Conexion.getConexion();
            if (revista.equalsIgnoreCase("")) {
                consulta = "SELECT * FROM comentario JOIN revista ON (comentario.revista = revista.titulo) WHERE revista.editor=?";
                query = conexion.prepareStatement(consulta);
                query.setString(1, usuario);
            } else {
                consulta = "SELECT * FROM comentario JOIN revista ON (comentario.revista = revista.titulo) WHERE comentario.revista=? AND revista.editor=?";
                query = conexion.prepareStatement(consulta);
                query.setString(1, revista);
                query.setString(2, usuario);
            }
            result = query.executeQuery();
            while (result.next()) {
                Comentario comentario = new Comentario();
                comentario.setContenido(result.getString("contenido"));
                comentario.setUsuario(result.getString("usuario"));
                comentario.setRevista(result.getString("revista"));
                comentario.setFecha(result.getDate("fecha"));
                if (comentario.getContenido() != null && !this.repiteContenido(comentarios, comentario.getContenido())) {
                    comentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            System.out.println("erro en listar las etiquetas");
        } finally {
            this.cierre();
        }
        return comentarios;
    }

    public List<Comentario> ComentaT(String revista) {
        List<Comentario> comentarios = new ArrayList<>();
        String consulta;
        try {
            conexion = Conexion.getConexion();

            consulta = "SELECT * FROM comentario JOIN revista ON (comentario.revista = revista.titulo) WHERE comentario.revista=?";
            query = conexion.prepareStatement(consulta);
            query.setString(1, revista);
            result = query.executeQuery();
            while (result.next()) {
                Comentario comentario = new Comentario();
                comentario.setContenido(result.getString("contenido"));
                comentario.setUsuario(result.getString("usuario"));
                comentario.setRevista(result.getString("revista"));
                comentario.setFecha(result.getDate("fecha"));
                if (comentario.getContenido() != null && !this.repiteContenido(comentarios, comentario.getContenido())) {
                    comentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            System.out.println("erro en listar las etiquetas");
        } finally {
            this.cierre();
        }
        return comentarios;
    }

    public boolean repiteContenido(List<Comentario> Comentarios, String comentario) {
        boolean reptido = false;
        for (Comentario Comentario1 : Comentarios) {
            if (Comentario1.getContenido().equalsIgnoreCase(comentario)) {
                reptido = true;
                break;
            }
        }
        return reptido;
    }

    public List<Comentario> Comentarios(String fechaI, String fechaF, String revista, String usuario) {
        List<Comentario> comentarios = new ArrayList<>();
        String consulta;
        try {
            conexion = Conexion.getConexion();
            if (revista.equalsIgnoreCase("")) {
                consulta = "SELECT * FROM comentario JOIN revista ON (comentario.revista = revista.titulo) WHERE comentario.fecha BETWEEN ? AND ? AND revista.editor=?";
                query = conexion.prepareStatement(consulta);
                query.setDate(1, this.getFechaSF(fechaI));
                query.setDate(2, this.getFechaSF(fechaF));
                query.setString(3, usuario);
            } else {
                consulta = "SELECT * FROM comentario JOIN revista ON (comentario.revista = revista.titulo) WHERE  comentario.fecha BETWEEN ? AND ? AND comentario.revista=? AND revista.editor=?";
                query = conexion.prepareStatement(consulta);
                query.setDate(1, this.getFechaSF(fechaI));
                query.setDate(2, this.getFechaSF(fechaF));
                query.setString(3, revista);
                query.setString(4, usuario);
            }
            result = query.executeQuery();
            while (result.next()) {
                Comentario comentario = new Comentario();
                comentario.setContenido(result.getString("contenido"));
                comentario.setUsuario(result.getString("usuario"));
                comentario.setRevista(result.getString("revista"));
                comentario.setFecha(result.getDate("fecha"));
                comentarios.add(comentario);
                if (comentario.getContenido() != null && !this.repiteContenido(comentarios, comentario.getContenido())) {
                    comentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            System.out.println("erro en Lista Reporte Comentarios Fecja" + e.getMessage());
        } catch (ParseException ex) {
            System.out.println("erroe en pareceo fecha reporte Comentrio Fecha" + ex.getMessage());
        } finally {
            this.cierre();
        }
        return comentarios;
    }

    private List<Reaccion> reacciones(String editor) {
        List<Reaccion> reacciones = new ArrayList<>();
        String consulta = "SELECT revista, COUNT( revista ) AS total FROM  revista JOIN reaccion ON (reaccion.revista = revista.titulo)  WHERE revista.editor=? GROUP BY revista ORDER BY total DESC";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, editor);
            result = query.executeQuery();
            while (result.next()) {
                Reaccion temp = new Reaccion();
                temp.setRevista(result.getString("revista"));
                temp.setTotal(result.getInt("total"));
                reacciones.add(temp);
            }
        } catch (SQLException e) {
            System.out.println("en listar revista mas gustadas " + e.getMessage());
        }
        return reacciones;
    }

    private List<GananciaEdit> GananciaEdit(String editor) {
        List<GananciaEdit> ganancia = new ArrayList<>();
        String consulta = "SELECT revista, COUNT( revista ) AS total FROM  suscripcion JOIN revista ON (suscripcion.revista = revista.titulo)  WHERE revista.editor=? GROUP BY revista ORDER BY total DESC";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, editor);
            result = query.executeQuery();
            while (result.next()) {
                GananciaEdit temp = new GananciaEdit();
                temp.setRevista(result.getString("revista"));
                ganancia.add(temp);
            }
        } catch (SQLException e) {
            System.out.println("en listar revista mas gustadas " + e.getMessage());
        }
        return ganancia;

    }

    private List<GananciaEdit> GananciaEditT() {
        List<GananciaEdit> ganancia = new ArrayList<>();
        String consulta = "SELECT revista, COUNT( revista ) AS total FROM  suscripcion JOIN revista ON (suscripcion.revista = revista.titulo) GROUP BY revista ORDER BY total DESC";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            result = query.executeQuery();
            while (result.next()) {
                GananciaEdit temp = new GananciaEdit();
                temp.setRevista(result.getString("revista"));
                ganancia.add(temp);
            }
        } catch (SQLException e) {
            System.out.println("en listar revista mas gustadas " + e.getMessage());
        }
        return ganancia;

    }

    private List<Comentarios> ComentarioT() {
        List<Comentarios> comentarios = new ArrayList<>();
        String consulta = "SELECT revista, COUNT( revista ) AS total FROM  comentario JOIN revista ON (comentario.revista = revista.titulo) GROUP BY revista ORDER BY total DESC";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            result = query.executeQuery();
            while (result.next()) {
                Comentarios temp = new Comentarios();
                temp.setRevista(result.getString("revista"));
                temp.setTotal(result.getInt("total"));
                comentarios.add(temp);
            }
        } catch (SQLException e) {
            System.out.println("en listar revista mas gustadas " + e.getMessage());
        }
        return comentarios;
    }

    public List<Comentarios> ComentairoTotal() {
        List<Comentarios> comentarios = this.ComentarioT();
          for (Comentarios comentario : comentarios) {
            comentario.setComentarios(this.ComentaT(comentario.getRevista()));
        }
        return comentarios;
    }

    public List<GananciaEdit> GananciaEdit(String editor, String revista) {
        List<GananciaEdit> ganancia = this.GananciaEdit(editor);
        if (revista.equals("")) {
            for (GananciaEdit gananciaEdit : ganancia) {
                gananciaEdit.setMeGusta(this.Ganancias(gananciaEdit.getRevista()));
                for (Ganancia gananci : gananciaEdit.getMeGusta()) {
                    gananciaEdit.setTotal(gananci.getGanancia());
                }
            }

        } else {
            if (!revista.equals("")) {
                for (GananciaEdit gananciaEdit : ganancia) {
                    if (gananciaEdit.getRevista().equalsIgnoreCase(revista)) {
                        gananciaEdit.setMeGusta(this.Ganancias(gananciaEdit.getRevista()));
                        for (Ganancia gananci : gananciaEdit.getMeGusta()) {
                            gananciaEdit.setTotal(gananci.getGanancia());
                        }
                        break;
                    }
                }
            }
        }

        return ganancia;
    }

    public List<GananciaEdit> GananciaEditT(String revista) {
        List<GananciaEdit> ganancia = this.GananciaEditT();
        if (revista.equals("")) {
            for (GananciaEdit gananciaEdit : ganancia) {
                gananciaEdit.setMeGusta(this.Ganancias(gananciaEdit.getRevista()));
                for (Ganancia gananci : gananciaEdit.getMeGusta()) {
                    gananciaEdit.setTotal(gananci.getGanancia());
                }
            }

        } else {
            if (!revista.equals("")) {
                for (GananciaEdit gananciaEdit : ganancia) {
                    if (gananciaEdit.getRevista().equalsIgnoreCase(revista)) {
                        gananciaEdit.setMeGusta(this.Ganancias(gananciaEdit.getRevista()));
                        for (Ganancia gananci : gananciaEdit.getMeGusta()) {
                            gananciaEdit.setTotal(gananci.getGanancia());
                        }
                        break;
                    }
                }
            }
        }

        return ganancia;
    }

    public List<GananciaEdit> GananciaEdit(String editor, String revista, String fechaI, String fechaF) throws ParseException {
        List<GananciaEdit> ganancia = this.GananciaEdit(editor);
        if (revista.equals("")) {
            for (GananciaEdit gananciaEdit : ganancia) {
                gananciaEdit.setMeGusta(this.Ganancias(gananciaEdit.getRevista(), fechaI, fechaF));
                for (Ganancia gananci : gananciaEdit.getMeGusta()) {
                    gananciaEdit.setTotal(gananci.getGanancia());
                }
            }

        } else {
            if (!revista.equals("")) {
                for (GananciaEdit gananciaEdit : ganancia) {
                    if (gananciaEdit.getRevista().equalsIgnoreCase(revista)) {
                        gananciaEdit.setMeGusta(this.Ganancias(gananciaEdit.getRevista(), fechaI, fechaF));
                        for (Ganancia gananci : gananciaEdit.getMeGusta()) {
                            gananciaEdit.setTotal(gananci.getGanancia());
                        }
                        break;
                    }
                }
            }
        }

        return ganancia;
    }

    public List<GananciaEdit> GananciaEditT(String revista, String fechaI, String fechaF) throws ParseException {
        List<GananciaEdit> ganancia = this.GananciaEditT();
        if (revista.equals("")) {
            for (GananciaEdit gananciaEdit : ganancia) {
                gananciaEdit.setMeGusta(this.Ganancias(gananciaEdit.getRevista(), fechaI, fechaF));
                for (Ganancia gananci : gananciaEdit.getMeGusta()) {
                    gananciaEdit.setTotal(gananci.getGanancia());
                }
            }

        } else {
            if (!revista.equals("")) {
                for (GananciaEdit gananciaEdit : ganancia) {
                    if (gananciaEdit.getRevista().equalsIgnoreCase(revista)) {
                        gananciaEdit.setMeGusta(this.Ganancias(gananciaEdit.getRevista(), fechaI, fechaF));
                        for (Ganancia gananci : gananciaEdit.getMeGusta()) {
                            gananciaEdit.setTotal(gananci.getGanancia());
                        }
                        break;
                    }
                }
            }
        }

        return ganancia;
    }

    private List<Ganancia> Ganancias(String revista) {
        String consulta = "SELECT * FROM suscripcion WHERE revista=?";
        List<Ganancia> ganancias = new ArrayList<>();
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, revista);
            result = query.executeQuery();
            while (result.next()) {
                Ganancia ganancia = new Ganancia();
                ganancia.setFechI(result.getDate("fecha_pago"));
                ganancia.setFechaF(result.getDate("fecha_vencimiento"));
                ganancia.setSuscriptor(result.getString("fecha_vencimiento"));
                ganancia.setGanancia(result.getDouble("ganancia_Sus"));
                ganancia.setGastos(result.getDouble("ganancia_Sus"));
                ganancias.add(ganancia);
            }
        } catch (SQLException e) {
            System.out.println("error en lista de reacciones smple " + e.getMessage());

        }
        return ganancias;
    }

    private List<Ganancia> Ganancias(String revista, String fechaI, String fechaF) throws ParseException {
        String consulta = "SELECT * FROM suscripcion WHERE WHERE fecha BETWEEN ? AND ? AND revista=?";
        List<Ganancia> ganancias = new ArrayList<>();
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setDate(1, this.getFechaSF(fechaI));
            query.setDate(2, this.getFechaSF(fechaF));
            query.setString(3, revista);
            result = query.executeQuery();
            while (result.next()) {
                Ganancia ganancia = new Ganancia();
                ganancia.setFechI(result.getDate("fecha_pago"));
                ganancia.setFechaF(result.getDate("fecha_vencimiento"));
                ganancia.setSuscriptor(result.getString("fecha_vencimiento"));
                ganancia.setGanancia(result.getDouble("ganancia_Sus"));
                ganancia.setGastos(result.getDouble("ganancia_Sus"));
                ganancias.add(ganancia);
            }
        } catch (SQLException e) {
            System.out.println("error en lista de reacciones smple " + e.getMessage());

        }
        return ganancias;
    }

    public List<Reaccion> reacciones(String editor, String revista) {
        List<Reaccion> reacciones = this.reacciones(editor);
        if (revista.equals("")) {
            for (Reaccion reaccione : reacciones) {
                reaccione.setMeGusta(meGusta(reaccione.getRevista()));
            }

        } else {
            if (!revista.equals("")) {
                for (Reaccion reaccione : reacciones) {
                    if (reaccione.getRevista().equalsIgnoreCase(revista)) {
                        reaccione.setMeGusta(meGusta(revista));
                        break;
                    }
                }
            }
        }

        return reacciones;

    }

    public List<Reaccion> reacciones(String editor, String revista, String fechaI, String fechaF) {
        List<Reaccion> reacciones = this.reacciones(editor);
        if (reacciones.size() > 0 && revista.equals("")) {
            for (Reaccion reaccione : reacciones) {
                reaccione.setMeGusta(meGusta(reaccione.getRevista(), fechaI, fechaF));
            }

        } else {
            if (!revista.equals("")) {
                for (Reaccion reaccione : reacciones) {
                    if (reaccione.getRevista().equalsIgnoreCase(revista)) {
                        reaccione.setMeGusta(meGusta(revista, fechaI, fechaF));
                        break;
                    }
                }
            }
        }

        return reacciones;

    }

    private List<MeGusta> meGusta(String revista) {
        String consulta = "SELECT * FROM reaccion WHERE revista=?";
        List<MeGusta> meGusta = new ArrayList<>();
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, revista);
            result = query.executeQuery();
            while (result.next()) {
                MeGusta me = new MeGusta();
                me.setFecha(result.getDate("fecha"));
                me.setUsuario(result.getString("usuario"));
                meGusta.add(me);
            }
        } catch (SQLException e) {
            System.out.println("error en lista de reacciones smple " + e.getMessage());

        }
        return meGusta;
    }

    private List<MeGusta> meGusta(String revista, String fechaI, String fechaF) {
        String consulta = "SELECT * FROM reaccion WHERE fecha BETWEEN ? AND ? AND revista=?";
        List<MeGusta> meGusta = new ArrayList<>();
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setDate(1, this.getFechaSF(fechaI));
            query.setDate(2, this.getFechaSF(fechaF));
            query.setString(3, revista);
            result = query.executeQuery();
            while (result.next()) {
                MeGusta me = new MeGusta();
                me.setFecha(result.getDate("fecha"));
                me.setUsuario(result.getString("usuario"));
                meGusta.add(me);
            }
        } catch (SQLException e) {
            System.out.println("error en lista de reacciones smple " + e.getMessage());

        } catch (ParseException ex) {
            System.out.println("erro en parce de lista reaccion con fecha simple " + ex.getMessage());
        }
        return meGusta;
    }

}
