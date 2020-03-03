package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSONException;
import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.util.FieldInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class FieldDeserializer {

    /* renamed from: a  reason: collision with root package name */
    protected final FieldInfo f6107a;
    protected final Class<?> b;

    public int a() {
        return 0;
    }

    public abstract void a(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map);

    public FieldDeserializer(Class<?> cls, FieldInfo fieldInfo) {
        this.b = cls;
        this.f6107a = fieldInfo;
    }

    public Method b() {
        return this.f6107a.e();
    }

    public Field c() {
        return this.f6107a.f();
    }

    public Class<?> d() {
        return this.f6107a.b();
    }

    public Type e() {
        return this.f6107a.c();
    }

    public void a(Object obj, boolean z) {
        a(obj, (Object) Boolean.valueOf(z));
    }

    public void a(Object obj, int i) {
        a(obj, (Object) Integer.valueOf(i));
    }

    public void a(Object obj, long j) {
        a(obj, (Object) Long.valueOf(j));
    }

    public void a(Object obj, String str) {
        a(obj, (Object) str);
    }

    public void a(Object obj, Object obj2) {
        Method e = this.f6107a.e();
        if (e != null) {
            try {
                if (!this.f6107a.g()) {
                    e.invoke(obj, new Object[]{obj2});
                } else if (this.f6107a.b() == AtomicInteger.class) {
                    AtomicInteger atomicInteger = (AtomicInteger) e.invoke(obj, new Object[0]);
                    if (atomicInteger != null) {
                        atomicInteger.set(((AtomicInteger) obj2).get());
                    }
                } else if (this.f6107a.b() == AtomicLong.class) {
                    AtomicLong atomicLong = (AtomicLong) e.invoke(obj, new Object[0]);
                    if (atomicLong != null) {
                        atomicLong.set(((AtomicLong) obj2).get());
                    }
                } else if (this.f6107a.b() == AtomicBoolean.class) {
                    AtomicBoolean atomicBoolean = (AtomicBoolean) e.invoke(obj, new Object[0]);
                    if (atomicBoolean != null) {
                        atomicBoolean.set(((AtomicBoolean) obj2).get());
                    }
                } else if (Map.class.isAssignableFrom(e.getReturnType())) {
                    Map map = (Map) e.invoke(obj, new Object[0]);
                    if (map != null) {
                        map.putAll((Map) obj2);
                    }
                } else {
                    Collection collection = (Collection) e.invoke(obj, new Object[0]);
                    if (collection != null) {
                        collection.addAll((Collection) obj2);
                    }
                }
            } catch (Exception e2) {
                throw new JSONException("set property error, " + this.f6107a.d(), e2);
            }
        } else {
            Field f = this.f6107a.f();
            if (f != null) {
                try {
                    f.set(obj, obj2);
                } catch (Exception e3) {
                    throw new JSONException("set property error, " + this.f6107a.d(), e3);
                }
            }
        }
    }
}
