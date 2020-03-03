package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSONException;
import com.imi.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class CollectionDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final CollectionDeserializer f6103a = new CollectionDeserializer();

    public int a() {
        return 14;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T t;
        Type type2;
        if (defaultJSONParser.n().d() == 8) {
            defaultJSONParser.n().a(16);
            return null;
        }
        Class<?> a2 = a(type);
        if (a2 == AbstractCollection.class) {
            t = new ArrayList();
        } else if (a2.isAssignableFrom(HashSet.class)) {
            t = new HashSet();
        } else if (a2.isAssignableFrom(LinkedHashSet.class)) {
            t = new LinkedHashSet();
        } else if (a2.isAssignableFrom(TreeSet.class)) {
            t = new TreeSet();
        } else if (a2.isAssignableFrom(ArrayList.class)) {
            t = new ArrayList();
        } else {
            try {
                t = (Collection) a2.newInstance();
            } catch (Exception unused) {
                throw new JSONException("create instane error, class " + a2.getName());
            }
        }
        if (type instanceof ParameterizedType) {
            type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        } else {
            type2 = Object.class;
        }
        defaultJSONParser.a(type2, (Collection) t, obj);
        return t;
    }

    public Class<?> a(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return a(((ParameterizedType) type).getRawType());
        }
        throw new JSONException("TODO");
    }
}
