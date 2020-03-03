package com.xiaomi.smarthome.framework.plugin.rn.utils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import java.util.List;

public class JSONUtil {
    public static Object a(String str, Class cls) {
        try {
            return JSON.parseObject(str, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List b(String str, Class cls) {
        try {
            return JSON.parseArray(str, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(Object obj) {
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> String b(T t) {
        try {
            return new Gson().toJsonTree(t).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
