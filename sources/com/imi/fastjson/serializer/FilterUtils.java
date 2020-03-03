package com.imi.fastjson.serializer;

import java.util.List;

public class FilterUtils {
    public static Object a(JSONSerializer jSONSerializer, Object obj, String str, Object obj2) {
        List<ValueFilter> e = jSONSerializer.e();
        if (e != null) {
            for (ValueFilter a2 : e) {
                obj2 = a2.a(obj, str, obj2);
            }
        }
        return obj2;
    }

    public static String b(JSONSerializer jSONSerializer, Object obj, String str, Object obj2) {
        List<NameFilter> k = jSONSerializer.k();
        if (k != null) {
            for (NameFilter a2 : k) {
                str = a2.a(obj, str, obj2);
            }
        }
        return str;
    }

    public static boolean a(JSONSerializer jSONSerializer, Object obj, String str) {
        List<PropertyPreFilter> m = jSONSerializer.m();
        if (m == null) {
            return true;
        }
        for (PropertyPreFilter a2 : m) {
            if (!a2.a(jSONSerializer, obj, str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(JSONSerializer jSONSerializer, Object obj, String str, Object obj2) {
        List<PropertyFilter> o = jSONSerializer.o();
        if (o == null) {
            return true;
        }
        for (PropertyFilter a2 : o) {
            if (!a2.a(obj, str, obj2)) {
                return false;
            }
        }
        return true;
    }
}
