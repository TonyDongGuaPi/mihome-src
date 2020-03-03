package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;

public class JavaObjectDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final JavaObjectDeserializer f6116a = new JavaObjectDeserializer();

    public int a() {
        return 12;
    }

    /* JADX WARNING: type inference failed for: r2v6, types: [T, java.lang.Object[]] */
    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (!(type instanceof GenericArrayType)) {
            return defaultJSONParser.b(obj);
        }
        Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
        if (genericComponentType instanceof TypeVariable) {
            genericComponentType = ((TypeVariable) genericComponentType).getBounds()[0];
        }
        ArrayList arrayList = new ArrayList();
        defaultJSONParser.a(genericComponentType, (Collection) arrayList);
        if (!(genericComponentType instanceof Class)) {
            return arrayList.toArray();
        }
        ? r2 = (Object[]) Array.newInstance((Class) genericComponentType, arrayList.size());
        arrayList.toArray(r2);
        return r2;
    }
}
