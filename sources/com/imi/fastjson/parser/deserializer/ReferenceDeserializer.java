package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

public class ReferenceDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final ReferenceDeserializer f6122a = new ReferenceDeserializer();

    public int a() {
        return 12;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Object a2 = defaultJSONParser.a(parameterizedType.getActualTypeArguments()[0]);
        Type rawType = parameterizedType.getRawType();
        if (rawType == AtomicReference.class) {
            return new AtomicReference(a2);
        }
        if (rawType == WeakReference.class) {
            return new WeakReference(a2);
        }
        if (rawType == SoftReference.class) {
            return new SoftReference(a2);
        }
        throw new UnsupportedOperationException(rawType.toString());
    }
}
