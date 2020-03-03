package com.imi.fastjson.serializer;

import com.imi.fastjson.util.IdentityHashMap;
import java.io.File;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class SerializeConfig extends IdentityHashMap<Type, ObjectSerializer> {
    private static final SerializeConfig b = new SerializeConfig();

    public ObjectSerializer a(Class<?> cls) {
        return new JavaBeanSerializer(cls);
    }

    public static final SerializeConfig a() {
        return b;
    }

    public SerializeConfig() {
        this(1024);
    }

    public SerializeConfig(int i) {
        super(i);
        a(Boolean.class, BooleanSerializer.f6142a);
        a(Character.class, CharacterSerializer.f6147a);
        a(Byte.class, ByteSerializer.f6144a);
        a(Short.class, ShortSerializer.f6181a);
        a(Integer.class, IntegerSerializer.f6163a);
        a(Long.class, LongSerializer.f6172a);
        a(Float.class, FloatSerializer.f6159a);
        a(Double.class, DoubleSerializer.f6153a);
        a(BigDecimal.class, BigDecimalSerializer.f6139a);
        a(BigInteger.class, BigIntegerSerializer.f6140a);
        a(String.class, StringSerializer.f6184a);
        a(byte[].class, ByteArraySerializer.f6143a);
        a(short[].class, ShortArraySerializer.f6180a);
        a(int[].class, IntArraySerializer.f6162a);
        a(long[].class, LongArraySerializer.f6171a);
        a(float[].class, FloatArraySerializer.f6158a);
        a(double[].class, DoubleArraySerializer.f6152a);
        a(boolean[].class, BooleanArraySerializer.f6141a);
        a(char[].class, CharArraySerializer.f6146a);
        a(Object[].class, ObjectArraySerializer.f6174a);
        a(Class.class, ClassSerializer.f6148a);
        a(SimpleDateFormat.class, DateFormatSerializer.f6150a);
        a(Locale.class, ToStringSerializer.f6186a);
        a(TimeZone.class, TimeZoneSerializer.f6185a);
        a(UUID.class, ToStringSerializer.f6186a);
        a(InetAddress.class, InetAddressSerializer.f6160a);
        a(Inet4Address.class, InetAddressSerializer.f6160a);
        a(Inet6Address.class, InetAddressSerializer.f6160a);
        a(InetSocketAddress.class, InetSocketAddressSerializer.f6161a);
        a(File.class, FileSerializer.f6157a);
        a(URI.class, ToStringSerializer.f6186a);
        a(URL.class, ToStringSerializer.f6186a);
        a(Appendable.class, AppendableSerializer.f6132a);
        a(StringBuffer.class, AppendableSerializer.f6132a);
        a(StringBuilder.class, AppendableSerializer.f6132a);
        a(StringWriter.class, AppendableSerializer.f6132a);
        a(Pattern.class, PatternSerializer.f6175a);
        a(Charset.class, ToStringSerializer.f6186a);
        a(AtomicBoolean.class, AtomicBooleanSerializer.f6134a);
        a(AtomicInteger.class, AtomicIntegerSerializer.f6136a);
        a(AtomicLong.class, AtomicLongSerializer.f6138a);
        a(AtomicReference.class, ReferenceSerializer.f6176a);
        a(AtomicIntegerArray.class, AtomicIntegerArraySerializer.f6135a);
        a(AtomicLongArray.class, AtomicLongArraySerializer.f6137a);
        a(WeakReference.class, ReferenceSerializer.f6176a);
        a(SoftReference.class, ReferenceSerializer.f6176a);
    }
}
