package com.proyectoipc.comvert;

import com.google.gson.Gson;

/**
 *
 * @author elvis_agui
 */
public abstract class Convert<T> {

    protected Gson gson;
    protected Class<T> typeConverter;

    public Convert(Class<T> typeConverter) {
        this.gson = new Gson();
        this.typeConverter = typeConverter;
    }

    public T fromJson(String json) {
        return gson.fromJson(json, typeConverter);
    }

    public String toJson(T object) {
        return gson.toJson(object, typeConverter);
    }

}
