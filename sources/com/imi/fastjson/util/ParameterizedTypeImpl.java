package com.imi.fastjson.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ParameterizedTypeImpl implements ParameterizedType {

    /* renamed from: a  reason: collision with root package name */
    private final Type[] f6193a;
    private final Type b;
    private final Type c;

    public ParameterizedTypeImpl(Type[] typeArr, Type type, Type type2) {
        this.f6193a = typeArr;
        this.b = type;
        this.c = type2;
    }

    public Type[] getActualTypeArguments() {
        return this.f6193a;
    }

    public Type getOwnerType() {
        return this.b;
    }

    public Type getRawType() {
        return this.c;
    }
}
