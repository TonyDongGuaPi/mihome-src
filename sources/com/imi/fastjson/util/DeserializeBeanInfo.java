package com.imi.fastjson.util;

import com.imi.fastjson.JSONException;
import com.imi.fastjson.annotation.JSONCreator;
import com.imi.fastjson.annotation.JSONField;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DeserializeBeanInfo {

    /* renamed from: a  reason: collision with root package name */
    private final Class<?> f6188a;
    private Constructor<?> b;
    private Constructor<?> c;
    private Method d;
    private final List<FieldInfo> e = new ArrayList();

    public DeserializeBeanInfo(Class<?> cls) {
        this.f6188a = cls;
    }

    public Class<?> a() {
        return this.f6188a;
    }

    public Constructor<?> b() {
        return this.b;
    }

    public void a(Constructor<?> constructor) {
        this.b = constructor;
    }

    public Constructor<?> c() {
        return this.c;
    }

    public void b(Constructor<?> constructor) {
        this.c = constructor;
    }

    public Method d() {
        return this.d;
    }

    public void a(Method method) {
        this.d = method;
    }

    public List<FieldInfo> e() {
        return this.e;
    }

    public FieldInfo a(String str) {
        for (FieldInfo next : this.e) {
            if (next.d().equals(str)) {
                return next;
            }
        }
        return null;
    }

    public boolean a(FieldInfo fieldInfo) {
        for (FieldInfo d2 : this.e) {
            if (d2.d().equals(fieldInfo.d())) {
                return false;
            }
        }
        this.e.add(fieldInfo);
        return true;
    }

    public static DeserializeBeanInfo a(Class<?> cls, Type type) {
        String substring;
        JSONField jSONField;
        JSONField jSONField2;
        JSONField jSONField3;
        Class<?> cls2 = cls;
        DeserializeBeanInfo deserializeBeanInfo = new DeserializeBeanInfo(cls2);
        Constructor<?> a2 = a(cls);
        if (a2 != null) {
            a2.setAccessible(true);
            deserializeBeanInfo.a(a2);
        } else if (a2 == null && !cls.isInterface() && !Modifier.isAbstract(cls.getModifiers())) {
            Constructor<?> b2 = b(cls);
            if (b2 != null) {
                b2.setAccessible(true);
                deserializeBeanInfo.b(b2);
                int i = 0;
                while (i < b2.getParameterTypes().length) {
                    Annotation[] annotationArr = b2.getParameterAnnotations()[i];
                    int length = annotationArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            jSONField3 = null;
                            break;
                        }
                        Annotation annotation = annotationArr[i2];
                        if (annotation instanceof JSONField) {
                            jSONField3 = (JSONField) annotation;
                            break;
                        }
                        i2++;
                    }
                    if (jSONField3 != null) {
                        deserializeBeanInfo.a(new FieldInfo(jSONField3.name(), cls, (Class<?>) b2.getParameterTypes()[i], b2.getGenericParameterTypes()[i], a(cls2, jSONField3.name())));
                        i++;
                    } else {
                        throw new JSONException("illegal json creator");
                    }
                }
                return deserializeBeanInfo;
            }
            Method c2 = c(cls);
            if (c2 != null) {
                c2.setAccessible(true);
                deserializeBeanInfo.a(c2);
                int i3 = 0;
                while (i3 < c2.getParameterTypes().length) {
                    Annotation[] annotationArr2 = c2.getParameterAnnotations()[i3];
                    int length2 = annotationArr2.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length2) {
                            jSONField2 = null;
                            break;
                        }
                        Annotation annotation2 = annotationArr2[i4];
                        if (annotation2 instanceof JSONField) {
                            jSONField2 = (JSONField) annotation2;
                            break;
                        }
                        i4++;
                    }
                    if (jSONField2 != null) {
                        deserializeBeanInfo.a(new FieldInfo(jSONField2.name(), cls, (Class<?>) c2.getParameterTypes()[i3], c2.getGenericParameterTypes()[i3], a(cls2, jSONField2.name())));
                        i3++;
                    } else {
                        throw new JSONException("illegal json creator");
                    }
                }
                return deserializeBeanInfo;
            }
            throw new JSONException("default constructor not found. " + cls2);
        }
        for (Method method : cls.getMethods()) {
            String name = method.getName();
            if (name.length() >= 4 && !Modifier.isStatic(method.getModifiers()) && ((method.getReturnType().equals(Void.TYPE) || method.getReturnType().equals(cls2)) && method.getParameterTypes().length == 1)) {
                JSONField jSONField4 = (JSONField) method.getAnnotation(JSONField.class);
                if (jSONField4 == null) {
                    jSONField4 = TypeUtils.a(cls2, method);
                }
                if (jSONField4 != null) {
                    if (jSONField4.deserialize()) {
                        if (jSONField4.name().length() != 0) {
                            deserializeBeanInfo.a(new FieldInfo(jSONField4.name(), method, (Field) null, cls, type));
                            method.setAccessible(true);
                        }
                    }
                }
                if (name.startsWith("set")) {
                    char charAt = name.charAt(3);
                    if (Character.isUpperCase(charAt)) {
                        substring = Character.toLowerCase(name.charAt(3)) + name.substring(4);
                    } else if (charAt == '_') {
                        substring = name.substring(4);
                    } else if (charAt == 'f') {
                        substring = name.substring(3);
                    }
                    String str = substring;
                    Field a3 = a(cls2, str);
                    if (a3 == null || (jSONField = (JSONField) a3.getAnnotation(JSONField.class)) == null || jSONField.name().length() == 0) {
                        deserializeBeanInfo.a(new FieldInfo(str, method, (Field) null, cls, type));
                        method.setAccessible(true);
                    } else {
                        deserializeBeanInfo.a(new FieldInfo(jSONField.name(), method, a3, cls, type));
                    }
                }
            }
        }
        for (Field field : cls.getFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                boolean z = false;
                for (FieldInfo d2 : deserializeBeanInfo.e()) {
                    if (d2.d().equals(field.getName())) {
                        z = true;
                    }
                }
                if (!z) {
                    String name2 = field.getName();
                    JSONField jSONField5 = (JSONField) field.getAnnotation(JSONField.class);
                    if (!(jSONField5 == null || jSONField5.name().length() == 0)) {
                        name2 = jSONField5.name();
                    }
                    deserializeBeanInfo.a(new FieldInfo(name2, (Method) null, field, cls, type));
                }
            }
        }
        for (Method method2 : cls.getMethods()) {
            String name3 = method2.getName();
            if (name3.length() >= 4 && !Modifier.isStatic(method2.getModifiers()) && name3.startsWith("get") && Character.isUpperCase(name3.charAt(3)) && method2.getParameterTypes().length == 0 && (Collection.class.isAssignableFrom(method2.getReturnType()) || Map.class.isAssignableFrom(method2.getReturnType()) || AtomicBoolean.class == method2.getReturnType() || AtomicInteger.class == method2.getReturnType() || AtomicLong.class == method2.getReturnType())) {
                String str2 = Character.toLowerCase(name3.charAt(3)) + name3.substring(4);
                if (deserializeBeanInfo.a(str2) == null) {
                    deserializeBeanInfo.a(new FieldInfo(str2, method2, (Field) null, cls, type));
                    method2.setAccessible(true);
                }
            }
        }
        return deserializeBeanInfo;
    }

    public static Field a(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Constructor<?> a(Class<?> cls) {
        Constructor<?> constructor = null;
        if (Modifier.isAbstract(cls.getModifiers())) {
            return null;
        }
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        int length = declaredConstructors.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Constructor<?> constructor2 = declaredConstructors[i];
            if (constructor2.getParameterTypes().length == 0) {
                constructor = constructor2;
                break;
            }
            i++;
        }
        if (constructor != null || !cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
            return constructor;
        }
        for (Constructor<?> constructor3 : cls.getDeclaredConstructors()) {
            if (constructor3.getParameterTypes().length == 1 && constructor3.getParameterTypes()[0].equals(cls.getDeclaringClass())) {
                return constructor3;
            }
        }
        return constructor;
    }

    public static Constructor<?> b(Class<?> cls) {
        for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
            if (((JSONCreator) constructor.getAnnotation(JSONCreator.class)) != null) {
                return constructor;
            }
        }
        return null;
    }

    public static Method c(Class<?> cls) {
        for (Method method : cls.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers()) && cls.isAssignableFrom(method.getReturnType()) && ((JSONCreator) method.getAnnotation(JSONCreator.class)) != null) {
                return method;
            }
        }
        return null;
    }
}
