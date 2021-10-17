package com.proyectoipc.comvert;

import com.proyectoipc.Entidades.Etiqueta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elvis_agui
 */
public class EtiquetaConvert extends Convert<Etiqueta> {

    public EtiquetaConvert(Class<Etiqueta> typeConverter) {
        super(typeConverter);
    }

    public List etiquetConverTojson(ArrayList<Etiqueta> etiquetaObj) {
        List<String> etiquetas = new ArrayList<>();
        for (Etiqueta etiquetabjo : etiquetaObj) {
           etiquetas.add( this.toJson(etiquetabjo));
        }
        return etiquetas;
    }

}
