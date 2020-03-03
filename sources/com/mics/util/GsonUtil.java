package com.mics.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.lang.reflect.Type;

public class GsonUtil {
    public static <T> T a(String str, Class<T> cls) {
        try {
            return new Gson().fromJson(str, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T a(JsonElement jsonElement, Class<T> cls) {
        try {
            return new Gson().fromJson(jsonElement, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T a(String str, Type type) {
        try {
            return new Gson().fromJson(str, type);
        } catch (Exception e) {
            Logger.a("json = %s", str);
            e.printStackTrace();
            return null;
        }
    }

    public static String a(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return new Gson().toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
