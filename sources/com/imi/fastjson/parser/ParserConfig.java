package com.imi.fastjson.parser;

import com.imi.fastjson.JSONArray;
import com.imi.fastjson.JSONObject;
import com.imi.fastjson.annotation.JSONType;
import com.imi.fastjson.parser.deserializer.ArrayDeserializer;
import com.imi.fastjson.parser.deserializer.ArrayListTypeFieldDeserializer;
import com.imi.fastjson.parser.deserializer.BigDecimalDeserializer;
import com.imi.fastjson.parser.deserializer.BigIntegerDeserializer;
import com.imi.fastjson.parser.deserializer.BooleanDeserializer;
import com.imi.fastjson.parser.deserializer.BooleanFieldDeserializer;
import com.imi.fastjson.parser.deserializer.CalendarDeserializer;
import com.imi.fastjson.parser.deserializer.CharArrayDeserializer;
import com.imi.fastjson.parser.deserializer.CharacterDeserializer;
import com.imi.fastjson.parser.deserializer.CharsetDeserializer;
import com.imi.fastjson.parser.deserializer.ClassDerializer;
import com.imi.fastjson.parser.deserializer.CollectionDeserializer;
import com.imi.fastjson.parser.deserializer.DateDeserializer;
import com.imi.fastjson.parser.deserializer.DateFormatDeserializer;
import com.imi.fastjson.parser.deserializer.DefaultFieldDeserializer;
import com.imi.fastjson.parser.deserializer.EnumDeserializer;
import com.imi.fastjson.parser.deserializer.FieldDeserializer;
import com.imi.fastjson.parser.deserializer.FileDeserializer;
import com.imi.fastjson.parser.deserializer.FloatDeserializer;
import com.imi.fastjson.parser.deserializer.InetAddressDeserializer;
import com.imi.fastjson.parser.deserializer.InetSocketAddressDeserializer;
import com.imi.fastjson.parser.deserializer.IntegerDeserializer;
import com.imi.fastjson.parser.deserializer.IntegerFieldDeserializer;
import com.imi.fastjson.parser.deserializer.JSONArrayDeserializer;
import com.imi.fastjson.parser.deserializer.JSONObjectDeserializer;
import com.imi.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.imi.fastjson.parser.deserializer.JavaObjectDeserializer;
import com.imi.fastjson.parser.deserializer.LocaleDeserializer;
import com.imi.fastjson.parser.deserializer.LongDeserializer;
import com.imi.fastjson.parser.deserializer.LongFieldDeserializer;
import com.imi.fastjson.parser.deserializer.MapDeserializer;
import com.imi.fastjson.parser.deserializer.NumberDeserializer;
import com.imi.fastjson.parser.deserializer.ObjectDeserializer;
import com.imi.fastjson.parser.deserializer.PatternDeserializer;
import com.imi.fastjson.parser.deserializer.ReferenceDeserializer;
import com.imi.fastjson.parser.deserializer.SqlDateDeserializer;
import com.imi.fastjson.parser.deserializer.StackTraceElementDeserializer;
import com.imi.fastjson.parser.deserializer.StringDeserializer;
import com.imi.fastjson.parser.deserializer.StringFieldDeserializer;
import com.imi.fastjson.parser.deserializer.ThrowableDeserializer;
import com.imi.fastjson.parser.deserializer.TimeDeserializer;
import com.imi.fastjson.parser.deserializer.TimeZoneDeserializer;
import com.imi.fastjson.parser.deserializer.TimestampDeserializer;
import com.imi.fastjson.parser.deserializer.URIDeserializer;
import com.imi.fastjson.parser.deserializer.URLDeserializer;
import com.imi.fastjson.parser.deserializer.UUIDDeserializer;
import com.imi.fastjson.util.FieldInfo;
import com.imi.fastjson.util.IdentityHashMap;
import com.taobao.weex.annotation.JSMethod;
import java.io.Closeable;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class ParserConfig {
    private static ParserConfig c = new ParserConfig();

    /* renamed from: a  reason: collision with root package name */
    protected final SymbolTable f6091a = new SymbolTable();
    private final Set<Class<?>> b = new HashSet();
    private final IdentityHashMap<Type, ObjectDeserializer> d = new IdentityHashMap<>();

    public static ParserConfig b() {
        return c;
    }

    public ParserConfig() {
        this.b.add(Boolean.TYPE);
        this.b.add(Boolean.class);
        this.b.add(Character.TYPE);
        this.b.add(Character.class);
        this.b.add(Byte.TYPE);
        this.b.add(Byte.class);
        this.b.add(Short.TYPE);
        this.b.add(Short.class);
        this.b.add(Integer.TYPE);
        this.b.add(Integer.class);
        this.b.add(Long.TYPE);
        this.b.add(Long.class);
        this.b.add(Float.TYPE);
        this.b.add(Float.class);
        this.b.add(Double.TYPE);
        this.b.add(Double.class);
        this.b.add(BigInteger.class);
        this.b.add(BigDecimal.class);
        this.b.add(String.class);
        this.b.add(Date.class);
        this.b.add(java.sql.Date.class);
        this.b.add(Time.class);
        this.b.add(Timestamp.class);
        this.d.a(SimpleDateFormat.class, DateFormatDeserializer.f6105a);
        this.d.a(Timestamp.class, TimestampDeserializer.f6128a);
        this.d.a(java.sql.Date.class, SqlDateDeserializer.f6123a);
        this.d.a(Time.class, TimeDeserializer.f6126a);
        this.d.a(Date.class, DateDeserializer.f6104a);
        this.d.a(Calendar.class, CalendarDeserializer.f6098a);
        this.d.a(JSONObject.class, JSONObjectDeserializer.f6114a);
        this.d.a(JSONArray.class, JSONArrayDeserializer.f6113a);
        this.d.a(Map.class, MapDeserializer.f6119a);
        this.d.a(HashMap.class, MapDeserializer.f6119a);
        this.d.a(LinkedHashMap.class, MapDeserializer.f6119a);
        this.d.a(TreeMap.class, MapDeserializer.f6119a);
        this.d.a(ConcurrentMap.class, MapDeserializer.f6119a);
        this.d.a(ConcurrentHashMap.class, MapDeserializer.f6119a);
        this.d.a(Collection.class, CollectionDeserializer.f6103a);
        this.d.a(List.class, CollectionDeserializer.f6103a);
        this.d.a(ArrayList.class, CollectionDeserializer.f6103a);
        this.d.a(Object.class, JavaObjectDeserializer.f6116a);
        this.d.a(String.class, StringDeserializer.f6125a);
        this.d.a(Character.TYPE, CharacterDeserializer.f6100a);
        this.d.a(Character.class, CharacterDeserializer.f6100a);
        this.d.a(Byte.TYPE, NumberDeserializer.f6120a);
        this.d.a(Byte.class, NumberDeserializer.f6120a);
        this.d.a(Short.TYPE, NumberDeserializer.f6120a);
        this.d.a(Short.class, NumberDeserializer.f6120a);
        this.d.a(Integer.TYPE, IntegerDeserializer.f6112a);
        this.d.a(Integer.class, IntegerDeserializer.f6112a);
        this.d.a(Long.TYPE, LongDeserializer.f6118a);
        this.d.a(Long.class, LongDeserializer.f6118a);
        this.d.a(BigInteger.class, BigIntegerDeserializer.f6096a);
        this.d.a(BigDecimal.class, BigDecimalDeserializer.f6095a);
        this.d.a(Float.TYPE, FloatDeserializer.f6109a);
        this.d.a(Float.class, FloatDeserializer.f6109a);
        this.d.a(Double.TYPE, NumberDeserializer.f6120a);
        this.d.a(Double.class, NumberDeserializer.f6120a);
        this.d.a(Boolean.TYPE, BooleanDeserializer.f6097a);
        this.d.a(Boolean.class, BooleanDeserializer.f6097a);
        this.d.a(Class.class, ClassDerializer.f6102a);
        this.d.a(char[].class, CharArrayDeserializer.f6099a);
        this.d.a(AtomicBoolean.class, BooleanDeserializer.f6097a);
        this.d.a(AtomicInteger.class, IntegerDeserializer.f6112a);
        this.d.a(AtomicLong.class, LongDeserializer.f6118a);
        this.d.a(AtomicReference.class, ReferenceDeserializer.f6122a);
        this.d.a(WeakReference.class, ReferenceDeserializer.f6122a);
        this.d.a(SoftReference.class, ReferenceDeserializer.f6122a);
        this.d.a(UUID.class, UUIDDeserializer.f6131a);
        this.d.a(TimeZone.class, TimeZoneDeserializer.f6127a);
        this.d.a(Locale.class, LocaleDeserializer.f6117a);
        this.d.a(InetAddress.class, InetAddressDeserializer.f6110a);
        this.d.a(Inet4Address.class, InetAddressDeserializer.f6110a);
        this.d.a(Inet6Address.class, InetAddressDeserializer.f6110a);
        this.d.a(InetSocketAddress.class, InetSocketAddressDeserializer.f6111a);
        this.d.a(File.class, FileDeserializer.f6108a);
        this.d.a(URI.class, URIDeserializer.f6129a);
        this.d.a(URL.class, URLDeserializer.f6130a);
        this.d.a(Pattern.class, PatternDeserializer.f6121a);
        this.d.a(Charset.class, CharsetDeserializer.f6101a);
        this.d.a(Number.class, NumberDeserializer.f6120a);
        this.d.a(AtomicIntegerArray.class, ArrayDeserializer.f6094a);
        this.d.a(AtomicLongArray.class, ArrayDeserializer.f6094a);
        this.d.a(StackTraceElement.class, StackTraceElementDeserializer.f6124a);
        this.d.a(Serializable.class, JavaObjectDeserializer.f6116a);
        this.d.a(Cloneable.class, JavaObjectDeserializer.f6116a);
        this.d.a(Comparable.class, JavaObjectDeserializer.f6116a);
        this.d.a(Closeable.class, JavaObjectDeserializer.f6116a);
    }

    public SymbolTable c() {
        return this.f6091a;
    }

    public IdentityHashMap<Type, ObjectDeserializer> d() {
        return this.d;
    }

    public ObjectDeserializer a(Type type) {
        ObjectDeserializer a2 = this.d.a(type);
        if (a2 != null) {
            return a2;
        }
        if (type instanceof Class) {
            return a((Class<?>) (Class) type, type);
        }
        if (!(type instanceof ParameterizedType)) {
            return JavaObjectDeserializer.f6116a;
        }
        Type rawType = ((ParameterizedType) type).getRawType();
        if (rawType instanceof Class) {
            return a((Class<?>) (Class) rawType, type);
        }
        return a(rawType);
    }

    public ObjectDeserializer a(Class<?> cls, Type type) {
        ObjectDeserializer objectDeserializer;
        Class<?> mappingTo;
        ObjectDeserializer a2 = this.d.a(type);
        if (a2 != null) {
            return a2;
        }
        if (type == null) {
            type = cls;
        }
        ObjectDeserializer a3 = this.d.a(type);
        if (a3 != null) {
            return a3;
        }
        JSONType jSONType = (JSONType) cls.getAnnotation(JSONType.class);
        if (jSONType != null && (mappingTo = jSONType.mappingTo()) != Void.class) {
            return a(mappingTo, (Type) mappingTo);
        }
        if ((type instanceof WildcardType) || (type instanceof TypeVariable) || (type instanceof ParameterizedType)) {
            a3 = this.d.a(cls);
        }
        if (a3 != null) {
            return a3;
        }
        ObjectDeserializer a4 = this.d.a(type);
        if (a4 != null) {
            return a4;
        }
        if (cls.isEnum()) {
            objectDeserializer = new EnumDeserializer(cls);
        } else if (cls.isArray()) {
            return ArrayDeserializer.f6094a;
        } else {
            if (cls == Set.class || cls == HashSet.class || cls == Collection.class || cls == List.class || cls == ArrayList.class) {
                objectDeserializer = CollectionDeserializer.f6103a;
            } else if (Collection.class.isAssignableFrom(cls)) {
                objectDeserializer = CollectionDeserializer.f6103a;
            } else if (Map.class.isAssignableFrom(cls)) {
                objectDeserializer = MapDeserializer.f6119a;
            } else if (Throwable.class.isAssignableFrom(cls)) {
                objectDeserializer = new ThrowableDeserializer(this, cls);
            } else {
                objectDeserializer = b(cls, type);
            }
        }
        a(type, objectDeserializer);
        return objectDeserializer;
    }

    public ObjectDeserializer b(Class<?> cls, Type type) {
        return new JavaBeanDeserializer(this, cls, type);
    }

    public FieldDeserializer a(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        Class<?> b2 = fieldInfo.b();
        if (b2 == Boolean.TYPE || b2 == Boolean.class) {
            return new BooleanFieldDeserializer(parserConfig, cls, fieldInfo);
        }
        if (b2 == Integer.TYPE || b2 == Integer.class) {
            return new IntegerFieldDeserializer(parserConfig, cls, fieldInfo);
        }
        if (b2 == Long.TYPE || b2 == Long.class) {
            return new LongFieldDeserializer(parserConfig, cls, fieldInfo);
        }
        if (b2 == String.class) {
            return new StringFieldDeserializer(parserConfig, cls, fieldInfo);
        }
        if (b2 == List.class || b2 == ArrayList.class) {
            return new ArrayListTypeFieldDeserializer(parserConfig, cls, fieldInfo);
        }
        return new DefaultFieldDeserializer(parserConfig, cls, fieldInfo);
    }

    public void a(Type type, ObjectDeserializer objectDeserializer) {
        this.d.a(type, objectDeserializer);
    }

    public ObjectDeserializer a(FieldInfo fieldInfo) {
        return a(fieldInfo.b(), fieldInfo.c());
    }

    public boolean a(Class<?> cls) {
        return this.b.contains(cls);
    }

    public static Field a(Class<?> cls, String str) {
        Field b2 = b(cls, str);
        if (b2 == null) {
            b2 = b(cls, JSMethod.NOT_SET + str);
        }
        if (b2 != null) {
            return b2;
        }
        return b(cls, "m_" + str);
    }

    private static Field b(Class<?> cls, String str) {
        for (Field field : cls.getDeclaredFields()) {
            if (str.equals(field.getName())) {
                return field;
            }
        }
        if (cls.getSuperclass() == null || cls.getSuperclass() == Object.class) {
            return null;
        }
        return a((Class<?>) cls.getSuperclass(), str);
    }

    public Map<String, FieldDeserializer> b(Class<?> cls) {
        ObjectDeserializer a2 = a((Type) cls);
        if (a2 instanceof JavaBeanDeserializer) {
            return ((JavaBeanDeserializer) a2).b();
        }
        return Collections.emptyMap();
    }
}
