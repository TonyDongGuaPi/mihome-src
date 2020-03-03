package org.xutils.http;

import android.os.Parcelable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.LogUtil;

final class RequestParamsHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final ClassLoader f10771a = String.class.getClassLoader();

    interface ParseKVListener {
        void a(String str, Object obj);
    }

    private RequestParamsHelper() {
    }

    static void a(Object obj, Class<?> cls, ParseKVListener parseKVListener) {
        ClassLoader classLoader;
        if (obj != null && cls != null && cls != RequestParams.class && cls != Object.class && (classLoader = cls.getClassLoader()) != null && classLoader != f10771a) {
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (!Modifier.isTransient(field.getModifiers()) && field.getType() != Parcelable.Creator.class) {
                        field.setAccessible(true);
                        try {
                            String name = field.getName();
                            Object obj2 = field.get(obj);
                            if (obj2 != null) {
                                parseKVListener.a(name, obj2);
                            }
                        } catch (IllegalAccessException e) {
                            LogUtil.b(e.getMessage(), e);
                        }
                    }
                }
            }
            a(obj, cls.getSuperclass(), parseKVListener);
        }
    }

    static Object a(Object obj) throws JSONException {
        if (obj == null) {
            return null;
        }
        Class<?> cls = obj.getClass();
        if (cls.isArray()) {
            JSONArray jSONArray = new JSONArray();
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                jSONArray.put(a(Array.get(obj, i)));
            }
            return jSONArray;
        } else if (obj instanceof List) {
            JSONArray jSONArray2 = new JSONArray();
            for (Object a2 : (List) obj) {
                jSONArray2.put(a(a2));
            }
            return jSONArray2;
        } else if (obj instanceof Map) {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (!(key == null || value == null)) {
                    jSONObject.put(String.valueOf(key), a(value));
                }
            }
            return jSONObject;
        } else {
            ClassLoader classLoader = cls.getClassLoader();
            if (classLoader == null || classLoader == f10771a) {
                return obj;
            }
            final JSONObject jSONObject2 = new JSONObject();
            a(obj, cls, new ParseKVListener() {
                public void a(String str, Object obj) {
                    try {
                        jSONObject2.put(str, RequestParamsHelper.a(obj));
                    } catch (JSONException e) {
                        throw new IllegalArgumentException("parse RequestParams to json failed", e);
                    }
                }
            });
            return jSONObject2;
        }
    }
}
