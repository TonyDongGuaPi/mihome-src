package com.imi.fastjson.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class FieldInfo implements Comparable<FieldInfo> {

    /* renamed from: a  reason: collision with root package name */
    private final String f6189a;
    private final Method b;
    private final Field c;
    private final Class<?> d;
    private final Type e;
    private final Class<?> f;
    private boolean g;

    public FieldInfo(String str, Class<?> cls, Class<?> cls2, Type type, Field field) {
        this.g = false;
        this.f6189a = str;
        this.f = cls;
        this.d = cls2;
        this.e = type;
        this.b = null;
        this.c = field;
        if (field != null) {
            field.setAccessible(true);
        }
    }

    public FieldInfo(String str, Method method, Field field) {
        this(str, method, field, (Class<?>) null, (Type) null);
    }

    public FieldInfo(String str, Method method, Field field, Class<?> cls, Type type) {
        Type type2;
        Class<?> cls2;
        Type a2;
        Type type3;
        this.g = false;
        this.f6189a = str;
        this.b = method;
        this.c = field;
        if (method != null) {
            method.setAccessible(true);
        }
        if (field != null) {
            field.setAccessible(true);
        }
        if (method != null) {
            if (method.getParameterTypes().length == 1) {
                cls2 = method.getParameterTypes()[0];
                type3 = method.getGenericParameterTypes()[0];
            } else {
                Class<?> returnType = method.getReturnType();
                Type genericReturnType = method.getGenericReturnType();
                this.g = true;
                cls2 = returnType;
                type3 = genericReturnType;
            }
            this.f = method.getDeclaringClass();
            type2 = type3;
        } else {
            cls2 = field.getType();
            type2 = field.getGenericType();
            this.f = field.getDeclaringClass();
        }
        if (cls == null || cls2 != Object.class || !(type2 instanceof TypeVariable) || (a2 = a(cls, (TypeVariable<?>) (TypeVariable) type2)) == null) {
            Type a3 = a(cls, type, type2);
            if (a3 != type2) {
                if (a3 instanceof ParameterizedType) {
                    cls2 = TypeUtils.a(a3);
                } else if (a3 instanceof Class) {
                    cls2 = TypeUtils.a(a3);
                }
            }
            this.e = a3;
            this.d = cls2;
            return;
        }
        this.d = TypeUtils.a(a2);
        this.e = a2;
    }

    public static Type a(Class<?> cls, Type type, Type type2) {
        boolean z;
        if (cls == null || type == null || !((z = type instanceof ParameterizedType))) {
            return type2;
        }
        if (type2 instanceof TypeVariable) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            TypeVariable typeVariable = (TypeVariable) type2;
            for (int i = 0; i < cls.getTypeParameters().length; i++) {
                if (cls.getTypeParameters()[i].getName().equals(typeVariable.getName())) {
                    return parameterizedType.getActualTypeArguments()[i];
                }
            }
        }
        if (type2 instanceof ParameterizedType) {
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            Type[] actualTypeArguments = parameterizedType2.getActualTypeArguments();
            boolean z2 = false;
            for (int i2 = 0; i2 < actualTypeArguments.length; i2++) {
                Type type3 = actualTypeArguments[i2];
                if (type3 instanceof TypeVariable) {
                    TypeVariable typeVariable2 = (TypeVariable) type3;
                    if (z) {
                        ParameterizedType parameterizedType3 = (ParameterizedType) type;
                        boolean z3 = z2;
                        for (int i3 = 0; i3 < cls.getTypeParameters().length; i3++) {
                            if (cls.getTypeParameters()[i3].getName().equals(typeVariable2.getName())) {
                                actualTypeArguments[i2] = parameterizedType3.getActualTypeArguments()[i3];
                                z3 = true;
                            }
                        }
                        z2 = z3;
                    }
                }
            }
            if (z2) {
                return new ParameterizedTypeImpl(actualTypeArguments, parameterizedType2.getOwnerType(), parameterizedType2.getRawType());
            }
        }
        return type2;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.reflect.TypeVariable, java.lang.reflect.TypeVariable<?>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Type a(java.lang.Class<?> r4, java.lang.reflect.TypeVariable<?> r5) {
        /*
            java.lang.reflect.GenericDeclaration r0 = r5.getGenericDeclaration()
        L_0x0004:
            java.lang.reflect.Type r4 = r4.getGenericSuperclass()
            r1 = 0
            if (r4 != 0) goto L_0x000c
            return r1
        L_0x000c:
            boolean r2 = r4 instanceof java.lang.reflect.ParameterizedType
            if (r2 == 0) goto L_0x0030
            r2 = r4
            java.lang.reflect.ParameterizedType r2 = (java.lang.reflect.ParameterizedType) r2
            java.lang.reflect.Type r3 = r2.getRawType()
            if (r3 != r0) goto L_0x0030
            java.lang.reflect.TypeVariable[] r4 = r0.getTypeParameters()
            java.lang.reflect.Type[] r0 = r2.getActualTypeArguments()
            r2 = 0
        L_0x0022:
            int r3 = r4.length
            if (r2 >= r3) goto L_0x002f
            r3 = r4[r2]
            if (r3 != r5) goto L_0x002c
            r4 = r0[r2]
            return r4
        L_0x002c:
            int r2 = r2 + 1
            goto L_0x0022
        L_0x002f:
            return r1
        L_0x0030:
            java.lang.Class r2 = com.imi.fastjson.util.TypeUtils.a((java.lang.reflect.Type) r4)
            if (r4 != 0) goto L_0x0037
            return r1
        L_0x0037:
            r4 = r2
            goto L_0x0004
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imi.fastjson.util.FieldInfo.a(java.lang.Class, java.lang.reflect.TypeVariable):java.lang.reflect.Type");
    }

    public String toString() {
        return this.f6189a;
    }

    public Class<?> a() {
        return this.f;
    }

    public Class<?> b() {
        return this.d;
    }

    public Type c() {
        return this.e;
    }

    public String d() {
        return this.f6189a;
    }

    public Method e() {
        return this.b;
    }

    public Field f() {
        return this.c;
    }

    /* renamed from: a */
    public int compareTo(FieldInfo fieldInfo) {
        return this.f6189a.compareTo(fieldInfo.f6189a);
    }

    public <T extends Annotation> T a(Class<T> cls) {
        T annotation = this.b != null ? this.b.getAnnotation(cls) : null;
        return (annotation != null || this.c == null) ? annotation : this.c.getAnnotation(cls);
    }

    public Object a(Object obj) throws IllegalAccessException, InvocationTargetException {
        if (this.b != null) {
            return this.b.invoke(obj, new Object[0]);
        }
        return this.c.get(obj);
    }

    public void a(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        if (this.b != null) {
            this.b.invoke(obj, new Object[]{obj2});
            return;
        }
        this.c.set(obj, obj2);
    }

    public void a(boolean z) throws SecurityException {
        if (this.b != null) {
            this.b.setAccessible(z);
        } else {
            this.c.setAccessible(z);
        }
    }

    public boolean g() {
        return this.g;
    }
}
