package com.xiaomi.jr.hybrid;

import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FeatureUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<Class<?>, FeatureInfo> f10844a = new ConcurrentHashMap();

    public enum Mode {
        SYNC,
        ASYNC
    }

    private static class FeatureInfo {

        /* renamed from: a  reason: collision with root package name */
        String f10846a;
        Map<String, ActionInfo> b;

        private FeatureInfo() {
        }
    }

    private static class ActionInfo {

        /* renamed from: a  reason: collision with root package name */
        Method f10845a;
        Action b;

        private ActionInfo() {
        }
    }

    public static Response a(Object obj, Request request) {
        Response response;
        Method a2 = a((Class) obj.getClass(), request.b());
        if (a2 != null) {
            try {
                response = (Response) a2.invoke(obj, new Object[]{request});
            } catch (Exception e) {
                response = new Response(200, "perform action fail: " + e.getMessage());
            }
        } else {
            response = new Response(205, "no such action");
        }
        if (response.a() != 0) {
            request.a().a(response.c());
        }
        return response;
    }

    public static String a(Class cls) {
        FeatureInfo b = b(cls);
        if (b != null) {
            return b.f10846a;
        }
        return null;
    }

    public static Method a(Class cls, String str) {
        ActionInfo d = d(cls, str);
        if (d != null) {
            return d.f10845a;
        }
        return null;
    }

    public static Mode b(Class cls, String str) {
        ActionInfo d = d(cls, str);
        if (d != null) {
            return d.b.mode();
        }
        return null;
    }

    public static Class c(Class cls, String str) {
        ActionInfo d = d(cls, str);
        if (d != null) {
            return d.b.paramClazz();
        }
        return null;
    }

    private static FeatureInfo b(Class cls) {
        FeatureInfo featureInfo = f10844a.get(cls);
        if (featureInfo == null) {
            featureInfo = new FeatureInfo();
            Annotation annotation = cls.getAnnotation(Feature.class);
            if (annotation == null) {
                return null;
            }
            featureInfo.f10846a = ((Feature) annotation).value();
            featureInfo.b = new ConcurrentHashMap();
            f10844a.put(cls, featureInfo);
        }
        return featureInfo;
    }

    public static ActionInfo d(Class cls, String str) {
        FeatureInfo b = b(cls);
        if (b == null) {
            return null;
        }
        ActionInfo actionInfo = b.b.get(str);
        if (actionInfo == null) {
            actionInfo = new ActionInfo();
            try {
                actionInfo.f10845a = cls.getMethod(str, new Class[]{Request.class});
                Annotation annotation = actionInfo.f10845a.getAnnotation(Action.class);
                if (annotation == null) {
                    return null;
                }
                actionInfo.b = (Action) annotation;
                b.b.put(str, actionInfo);
            } catch (NoSuchMethodException unused) {
                return null;
            }
        }
        return actionInfo;
    }
}
