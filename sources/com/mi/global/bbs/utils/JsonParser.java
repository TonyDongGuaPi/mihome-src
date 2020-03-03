package com.mi.global.bbs.utils;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.List;

public class JsonParser {
    public static <T> T parse(String str, Class<T> cls) {
        try {
            return new Gson().fromJson(str, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> parseList(String str, Type type) {
        try {
            return (List) new Gson().fromJson(str, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> String list2String(T t) {
        try {
            return new Gson().toJson((Object) t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
