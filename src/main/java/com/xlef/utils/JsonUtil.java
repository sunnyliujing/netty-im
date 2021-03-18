package com.xlef.utils;

import com.google.gson.Gson;

public final class JsonUtil {
    private static final Gson gson = new Gson();

    private JsonUtil(){

    }

    public static <T> T fromJson(String jsonStr,Class<T> clazz){
        return gson.fromJson(jsonStr,clazz);
    }

    public static String toJson(Object object){
        return gson.toJson(object);
    }
}
