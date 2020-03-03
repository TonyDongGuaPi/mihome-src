package com.imi.fastjson.serializer;

import com.imi.fastjson.JSONAware;
import com.imi.fastjson.JSONException;
import com.imi.fastjson.JSONStreamAware;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.cybergarage.http.HTTP;

public class JSONSerializer {

    /* renamed from: a  reason: collision with root package name */
    private final SerializeConfig f6165a;
    private final SerializeWriter b;
    private List<PropertyFilter> c;
    private List<ValueFilter> d;
    private List<NameFilter> e;
    private List<PropertyPreFilter> f;
    private int g;
    private String h;
    private String i;
    private DateFormat j;
    private IdentityHashMap<Object, SerialContext> k;
    private SerialContext l;

    public JSONSerializer() {
        this(new SerializeWriter(), SerializeConfig.a());
    }

    public JSONSerializer(SerializeWriter serializeWriter) {
        this(serializeWriter, SerializeConfig.a());
    }

    public JSONSerializer(SerializeConfig serializeConfig) {
        this(new SerializeWriter(), serializeConfig);
    }

    @Deprecated
    public JSONSerializer(JSONSerializerMap jSONSerializerMap) {
        this(new SerializeWriter(), jSONSerializerMap);
    }

    public JSONSerializer(SerializeWriter serializeWriter, SerializeConfig serializeConfig) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = HTTP.TAB;
        this.k = null;
        this.b = serializeWriter;
        this.f6165a = serializeConfig;
    }

    public String a() {
        if (this.j instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) this.j).toPattern();
        }
        return this.i;
    }

    public DateFormat b() {
        if (this.j == null && this.i != null) {
            this.j = new SimpleDateFormat(this.i);
        }
        return this.j;
    }

    public void a(DateFormat dateFormat) {
        this.j = dateFormat;
        if (this.i != null) {
            this.i = null;
        }
    }

    public void a(String str) {
        this.i = str;
        if (this.j != null) {
            this.j = null;
        }
    }

    public SerialContext c() {
        return this.l;
    }

    public void a(SerialContext serialContext) {
        this.l = serialContext;
    }

    public void a(SerialContext serialContext, Object obj, Object obj2) {
        if (!a(SerializerFeature.DisableCircularReferenceDetect)) {
            this.l = new SerialContext(serialContext, obj, obj2);
            if (this.k == null) {
                this.k = new IdentityHashMap<>();
            }
            this.k.put(obj, this.l);
        }
    }

    public final boolean a(Type type, Object obj) {
        if (!this.b.a(SerializerFeature.WriteClassName)) {
            return false;
        }
        if (type == null && a(SerializerFeature.NotWriteRootClassName)) {
            if (this.l.a() == null) {
                return false;
            }
        }
        return true;
    }

    public SerialContext a(Object obj) {
        if (this.k == null) {
            return null;
        }
        return this.k.get(obj);
    }

    public boolean b(Object obj) {
        if (this.k == null) {
            return false;
        }
        return this.k.containsKey(obj);
    }

    public void c(Object obj) {
        SerialContext c2 = c();
        if (obj == c2.b()) {
            this.b.write("{\"$ref\":\"@\"}");
            return;
        }
        SerialContext a2 = c2.a();
        if (a2 == null || obj != a2.b()) {
            while (c2.a() != null) {
                c2 = c2.a();
            }
            if (obj == c2.b()) {
                this.b.write("{\"$ref\":\"$\"}");
                return;
            }
            String d2 = a(obj).d();
            this.b.write("{\"$ref\":\"");
            this.b.write(d2);
            this.b.write("\"}");
            return;
        }
        this.b.write("{\"$ref\":\"..\"}");
    }

    public List<ValueFilter> d() {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        return this.d;
    }

    public List<ValueFilter> e() {
        return this.d;
    }

    public int f() {
        return this.g;
    }

    public void g() {
        this.g++;
    }

    public void h() {
        this.g--;
    }

    public void i() {
        this.b.a(10);
        for (int i2 = 0; i2 < this.g; i2++) {
            this.b.write(this.h);
        }
    }

    public List<NameFilter> j() {
        if (this.e == null) {
            this.e = new ArrayList();
        }
        return this.e;
    }

    public List<NameFilter> k() {
        return this.e;
    }

    public List<PropertyPreFilter> l() {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        return this.f;
    }

    public List<PropertyPreFilter> m() {
        return this.f;
    }

    public List<PropertyFilter> n() {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        return this.c;
    }

    public List<PropertyFilter> o() {
        return this.c;
    }

    public SerializeWriter p() {
        return this.b;
    }

    public String toString() {
        return this.b.toString();
    }

    public void a(SerializerFeature serializerFeature, boolean z) {
        this.b.a(serializerFeature, z);
    }

    public boolean a(SerializerFeature serializerFeature) {
        return this.b.a(serializerFeature);
    }

    public void q() {
        this.b.e();
    }

    public SerializeConfig r() {
        return this.f6165a;
    }

    public static final void a(Writer writer, Object obj) {
        SerializeWriter serializeWriter = new SerializeWriter();
        try {
            new JSONSerializer(serializeWriter).d(obj);
            serializeWriter.a(writer);
            serializeWriter.close();
        } catch (IOException e2) {
            throw new JSONException(e2.getMessage(), e2);
        } catch (Throwable th) {
            serializeWriter.close();
            throw th;
        }
    }

    public static final void a(SerializeWriter serializeWriter, Object obj) {
        new JSONSerializer(serializeWriter).d(obj);
    }

    public final void d(Object obj) {
        if (obj == null) {
            this.b.e();
            return;
        }
        try {
            a(obj.getClass()).a(this, obj, (Object) null, (Type) null);
        } catch (IOException e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }

    public final void a(Object obj, Object obj2) {
        a(obj, obj2, (Type) null);
    }

    public final void a(Object obj, Object obj2, Type type) {
        if (obj == null) {
            try {
                this.b.e();
            } catch (IOException e2) {
                throw new JSONException(e2.getMessage(), e2);
            }
        } else {
            a(obj.getClass()).a(this, obj, obj2, type);
        }
    }

    public final void a(Object obj, String str) {
        if (obj instanceof Date) {
            DateFormat b2 = b();
            if (b2 == null) {
                b2 = new SimpleDateFormat(str);
            }
            this.b.b(b2.format((Date) obj));
            return;
        }
        d(obj);
    }

    public final void b(String str) {
        StringSerializer.f6184a.a(this, str);
    }

    public ObjectSerializer a(Class<?> cls) {
        boolean z;
        ObjectSerializer objectSerializer = (ObjectSerializer) this.f6165a.a(cls);
        if (objectSerializer != null) {
            return objectSerializer;
        }
        if (Map.class.isAssignableFrom(cls)) {
            this.f6165a.a(cls, MapSerializer.f6173a);
        } else if (List.class.isAssignableFrom(cls)) {
            this.f6165a.a(cls, ListSerializer.f6170a);
        } else if (Collection.class.isAssignableFrom(cls)) {
            this.f6165a.a(cls, CollectionSerializer.f6149a);
        } else if (Date.class.isAssignableFrom(cls)) {
            this.f6165a.a(cls, DateSerializer.f6151a);
        } else if (JSONAware.class.isAssignableFrom(cls)) {
            this.f6165a.a(cls, JSONAwareSerializer.f6164a);
        } else if (JSONStreamAware.class.isAssignableFrom(cls)) {
            this.f6165a.a(cls, JSONStreamAwareSerializer.f6168a);
        } else if (cls.isEnum() || (cls.getSuperclass() != null && cls.getSuperclass().isEnum())) {
            this.f6165a.a(cls, EnumSerializer.f6154a);
        } else if (cls.isArray()) {
            Class<?> componentType = cls.getComponentType();
            this.f6165a.a(cls, new ArraySerializer(componentType, a(componentType)));
        } else if (Throwable.class.isAssignableFrom(cls)) {
            this.f6165a.a(cls, new ExceptionSerializer(cls));
        } else if (TimeZone.class.isAssignableFrom(cls)) {
            this.f6165a.a(cls, TimeZoneSerializer.f6185a);
        } else if (Charset.class.isAssignableFrom(cls)) {
            this.f6165a.a(cls, ToStringSerializer.f6186a);
        } else if (Enumeration.class.isAssignableFrom(cls)) {
            this.f6165a.a(cls, EnumerationSeriliazer.f6155a);
        } else if (Calendar.class.isAssignableFrom(cls)) {
            this.f6165a.a(cls, CalendarSerializer.f6145a);
        } else {
            Class[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            boolean z2 = false;
            int i2 = 0;
            while (true) {
                z = true;
                if (i2 >= length) {
                    break;
                }
                Class cls2 = interfaces[i2];
                if (cls2.getName().equals("net.sf.cglib.proxy.Factory")) {
                    z2 = true;
                    break;
                } else if (cls2.getName().equals("javassist.util.proxy.ProxyObject")) {
                    break;
                } else {
                    i2++;
                }
            }
            z = false;
            if (z2 || z) {
                ObjectSerializer a2 = a((Class<?>) cls.getSuperclass());
                this.f6165a.a(cls, a2);
                return a2;
            } else if (Proxy.isProxyClass(cls)) {
                this.f6165a.a(cls, this.f6165a.a(cls));
            } else {
                this.f6165a.a(cls, this.f6165a.a(cls));
            }
        }
        return (ObjectSerializer) this.f6165a.a(cls);
    }

    public void s() {
        this.b.close();
    }
}
