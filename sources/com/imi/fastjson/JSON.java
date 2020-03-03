package com.imi.fastjson;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.Feature;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.parser.ParserConfig;
import com.imi.fastjson.parser.deserializer.FieldDeserializer;
import com.imi.fastjson.serializer.JSONSerializer;
import com.imi.fastjson.serializer.NameFilter;
import com.imi.fastjson.serializer.PropertyFilter;
import com.imi.fastjson.serializer.PropertyPreFilter;
import com.imi.fastjson.serializer.SerializeConfig;
import com.imi.fastjson.serializer.SerializeFilter;
import com.imi.fastjson.serializer.SerializeWriter;
import com.imi.fastjson.serializer.SerializerFeature;
import com.imi.fastjson.serializer.ValueFilter;
import com.imi.fastjson.util.FieldInfo;
import com.imi.fastjson.util.IOUtils;
import com.imi.fastjson.util.ThreadLocalCache;
import com.imi.fastjson.util.TypeUtils;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class JSON implements JSONAware, JSONStreamAware {
    public static int DEFAULT_GENERATE_FEATURE = ((((SerializerFeature.QuoteFieldNames.getMask() | 0) | SerializerFeature.SkipTransientField.getMask()) | SerializerFeature.WriteEnumUsingToString.getMask()) | SerializerFeature.SortField.getMask());
    public static int DEFAULT_PARSER_FEATURE = ((((((((Feature.AutoCloseSource.getMask() | 0) | Feature.InternFieldNames.getMask()) | Feature.UseBigDecimal.getMask()) | Feature.AllowUnQuotedFieldNames.getMask()) | Feature.AllowSingleQuotes.getMask()) | Feature.AllowArbitraryCommas.getMask()) | Feature.SortFeidFastMatch.getMask()) | Feature.IgnoreNotMatch.getMask());
    public static String DEFAULT_TYPE_KEY = "@type";
    public static String DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String VERSION = "1.1.34-android";

    public static final Object parse(String str) {
        return parse(str, DEFAULT_PARSER_FEATURE);
    }

    public static final Object parse(String str, int i) {
        if (str == null) {
            return null;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.b(), i);
        Object l = defaultJSONParser.l();
        handleResovleTask(defaultJSONParser, l);
        defaultJSONParser.close();
        return l;
    }

    public static final Object parse(byte[] bArr, Feature... featureArr) {
        return parse(bArr, 0, bArr.length, Charset.forName("UTF-8").newDecoder(), featureArr);
    }

    public static final Object parse(byte[] bArr, int i, int i2, CharsetDecoder charsetDecoder, Feature... featureArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        int i3 = DEFAULT_PARSER_FEATURE;
        for (Feature config : featureArr) {
            i3 = Feature.config(i3, config, true);
        }
        return parse(bArr, i, i2, charsetDecoder, i3);
    }

    public static final Object parse(byte[] bArr, int i, int i2, CharsetDecoder charsetDecoder, int i3) {
        charsetDecoder.reset();
        double d = (double) i2;
        double maxCharsPerByte = (double) charsetDecoder.maxCharsPerByte();
        Double.isNaN(d);
        Double.isNaN(maxCharsPerByte);
        char[] a2 = ThreadLocalCache.a((int) (d * maxCharsPerByte));
        ByteBuffer wrap = ByteBuffer.wrap(bArr, i, i2);
        CharBuffer wrap2 = CharBuffer.wrap(a2);
        IOUtils.a(charsetDecoder, wrap, wrap2);
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(a2, wrap2.position(), ParserConfig.b(), i3);
        Object l = defaultJSONParser.l();
        handleResovleTask(defaultJSONParser, l);
        defaultJSONParser.close();
        return l;
    }

    public static final Object parse(String str, Feature... featureArr) {
        int i = DEFAULT_PARSER_FEATURE;
        for (Feature config : featureArr) {
            i = Feature.config(i, config, true);
        }
        return parse(str, i);
    }

    public static final JSONObject parseObject(String str, Feature... featureArr) {
        return (JSONObject) parse(str, featureArr);
    }

    public static final JSONObject parseObject(String str) {
        Object parse = parse(str);
        if (parse instanceof JSONObject) {
            return (JSONObject) parse;
        }
        return (JSONObject) toJSON(parse);
    }

    public static final <T> T parseObject(String str, TypeReference<T> typeReference, Feature... featureArr) {
        return parseObject(str, typeReference.getType(), ParserConfig.b(), DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static final <T> T parseObject(String str, Class<T> cls, Feature... featureArr) {
        return parseObject(str, cls, ParserConfig.b(), DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static final <T> T parseObject(String str, Type type, Feature... featureArr) {
        return parseObject(str, type, ParserConfig.b(), DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static final <T> T parseObject(String str, Type type, int i, Feature... featureArr) {
        if (str == null) {
            return null;
        }
        for (Feature config : featureArr) {
            i = Feature.config(i, config, true);
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.b(), i);
        T a2 = defaultJSONParser.a(type);
        handleResovleTask(defaultJSONParser, a2);
        defaultJSONParser.close();
        return a2;
    }

    public static final <T> T parseObject(String str, Type type, ParserConfig parserConfig, int i, Feature... featureArr) {
        if (str == null) {
            return null;
        }
        for (Feature config : featureArr) {
            i = Feature.config(i, config, true);
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, parserConfig, i);
        T a2 = defaultJSONParser.a(type);
        handleResovleTask(defaultJSONParser, a2);
        defaultJSONParser.close();
        return a2;
    }

    public static <T> int handleResovleTask(DefaultJSONParser defaultJSONParser, T t) {
        Object obj;
        int size = defaultJSONParser.i().size();
        for (int i = 0; i < size; i++) {
            DefaultJSONParser.ResolveTask resolveTask = defaultJSONParser.i().get(i);
            FieldDeserializer c = resolveTask.c();
            Object obj2 = null;
            if (resolveTask.d() != null) {
                obj2 = resolveTask.d().b();
            }
            String b = resolveTask.b();
            if (b.startsWith(Operators.DOLLAR_STR)) {
                obj = defaultJSONParser.b(b);
            } else {
                obj = resolveTask.a().b();
            }
            c.a(obj2, obj);
        }
        return size;
    }

    public static final <T> T parseObject(byte[] bArr, Type type, Feature... featureArr) {
        return parseObject(bArr, 0, bArr.length, Charset.forName("UTF-8").newDecoder(), type, featureArr);
    }

    public static final <T> T parseObject(byte[] bArr, int i, int i2, CharsetDecoder charsetDecoder, Type type, Feature... featureArr) {
        charsetDecoder.reset();
        double d = (double) i2;
        double maxCharsPerByte = (double) charsetDecoder.maxCharsPerByte();
        Double.isNaN(d);
        Double.isNaN(maxCharsPerByte);
        char[] a2 = ThreadLocalCache.a((int) (d * maxCharsPerByte));
        ByteBuffer wrap = ByteBuffer.wrap(bArr, i, i2);
        CharBuffer wrap2 = CharBuffer.wrap(a2);
        IOUtils.a(charsetDecoder, wrap, wrap2);
        return parseObject(a2, wrap2.position(), type, featureArr);
    }

    public static final <T> T parseObject(char[] cArr, int i, Type type, Feature... featureArr) {
        if (cArr == null || cArr.length == 0) {
            return null;
        }
        int i2 = DEFAULT_PARSER_FEATURE;
        for (Feature config : featureArr) {
            i2 = Feature.config(i2, config, true);
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(cArr, i, ParserConfig.b(), i2);
        T a2 = defaultJSONParser.a(type);
        handleResovleTask(defaultJSONParser, a2);
        defaultJSONParser.close();
        return a2;
    }

    public static final <T> T parseObject(String str, Class<T> cls) {
        return parseObject(str, cls, new Feature[0]);
    }

    public static final JSONArray parseArray(String str) {
        JSONArray jSONArray = null;
        if (str == null) {
            return null;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.b());
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 8) {
            n.a();
        } else if (n.d() != 20) {
            jSONArray = new JSONArray();
            defaultJSONParser.b((Collection) jSONArray);
            handleResovleTask(defaultJSONParser, jSONArray);
        }
        defaultJSONParser.close();
        return jSONArray;
    }

    public static final <T> List<T> parseArray(String str, Class<T> cls) {
        ArrayList arrayList = null;
        if (str == null) {
            return null;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.b());
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 8) {
            n.a();
        } else {
            arrayList = new ArrayList();
            defaultJSONParser.a((Class<?>) cls, (Collection) arrayList);
            handleResovleTask(defaultJSONParser, arrayList);
        }
        defaultJSONParser.close();
        return arrayList;
    }

    public static final List<Object> parseArray(String str, Type[] typeArr) {
        List<Object> list = null;
        if (str == null) {
            return null;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.b());
        Object[] a2 = defaultJSONParser.a(typeArr);
        if (a2 != null) {
            list = Arrays.asList(a2);
        }
        handleResovleTask(defaultJSONParser, list);
        defaultJSONParser.close();
        return list;
    }

    public static final String toJSONString(Object obj) {
        return toJSONString(obj, new SerializerFeature[0]);
    }

    public static final String toJSONString(Object obj, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter();
        try {
            JSONSerializer jSONSerializer = new JSONSerializer(serializeWriter);
            for (SerializerFeature a2 : serializerFeatureArr) {
                jSONSerializer.a(a2, true);
            }
            jSONSerializer.d(obj);
            return serializeWriter.toString();
        } finally {
            serializeWriter.close();
        }
    }

    public static final String toJSONStringWithDateFormat(Object obj, String str, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter();
        try {
            JSONSerializer jSONSerializer = new JSONSerializer(serializeWriter);
            for (SerializerFeature a2 : serializerFeatureArr) {
                jSONSerializer.a(a2, true);
            }
            jSONSerializer.a(SerializerFeature.WriteDateUseDateFormat, true);
            if (str != null) {
                jSONSerializer.a(str);
            }
            jSONSerializer.d(obj);
            return serializeWriter.toString();
        } finally {
            serializeWriter.close();
        }
    }

    public static final String toJSONString(Object obj, SerializeFilter serializeFilter, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter();
        try {
            JSONSerializer jSONSerializer = new JSONSerializer(serializeWriter);
            for (SerializerFeature a2 : serializerFeatureArr) {
                jSONSerializer.a(a2, true);
            }
            jSONSerializer.a(SerializerFeature.WriteDateUseDateFormat, true);
            if (serializeFilter != null) {
                if (serializeFilter instanceof PropertyPreFilter) {
                    jSONSerializer.l().add((PropertyPreFilter) serializeFilter);
                }
                if (serializeFilter instanceof NameFilter) {
                    jSONSerializer.j().add((NameFilter) serializeFilter);
                }
                if (serializeFilter instanceof ValueFilter) {
                    jSONSerializer.d().add((ValueFilter) serializeFilter);
                }
                if (serializeFilter instanceof PropertyFilter) {
                    jSONSerializer.n().add((PropertyFilter) serializeFilter);
                }
            }
            jSONSerializer.d(obj);
            return serializeWriter.toString();
        } finally {
            serializeWriter.close();
        }
    }

    public static final byte[] toJSONBytes(Object obj, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter();
        try {
            JSONSerializer jSONSerializer = new JSONSerializer(serializeWriter);
            for (SerializerFeature a2 : serializerFeatureArr) {
                jSONSerializer.a(a2, true);
            }
            jSONSerializer.d(obj);
            return serializeWriter.a("UTF-8");
        } finally {
            serializeWriter.close();
        }
    }

    public static final String toJSONString(Object obj, SerializeConfig serializeConfig, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter();
        try {
            JSONSerializer jSONSerializer = new JSONSerializer(serializeWriter, serializeConfig);
            for (SerializerFeature a2 : serializerFeatureArr) {
                jSONSerializer.a(a2, true);
            }
            jSONSerializer.d(obj);
            return serializeWriter.toString();
        } finally {
            serializeWriter.close();
        }
    }

    public static final String toJSONStringZ(Object obj, SerializeConfig serializeConfig, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter(serializerFeatureArr);
        try {
            new JSONSerializer(serializeWriter, serializeConfig).d(obj);
            return serializeWriter.toString();
        } finally {
            serializeWriter.close();
        }
    }

    public static final byte[] toJSONBytes(Object obj, SerializeConfig serializeConfig, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter();
        try {
            JSONSerializer jSONSerializer = new JSONSerializer(serializeWriter, serializeConfig);
            for (SerializerFeature a2 : serializerFeatureArr) {
                jSONSerializer.a(a2, true);
            }
            jSONSerializer.d(obj);
            return serializeWriter.a("UTF-8");
        } finally {
            serializeWriter.close();
        }
    }

    public static final String toJSONString(Object obj, boolean z) {
        if (!z) {
            return toJSONString(obj);
        }
        return toJSONString(obj, SerializerFeature.PrettyFormat);
    }

    public static final void writeJSONStringTo(Object obj, Writer writer, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter(writer);
        try {
            JSONSerializer jSONSerializer = new JSONSerializer(serializeWriter);
            for (SerializerFeature a2 : serializerFeatureArr) {
                jSONSerializer.a(a2, true);
            }
            jSONSerializer.d(obj);
        } finally {
            serializeWriter.close();
        }
    }

    public String toString() {
        return toJSONString();
    }

    public String toJSONString() {
        SerializeWriter serializeWriter = new SerializeWriter();
        try {
            new JSONSerializer(serializeWriter).d(this);
            return serializeWriter.toString();
        } finally {
            serializeWriter.close();
        }
    }

    public void writeJSONString(Appendable appendable) {
        SerializeWriter serializeWriter = new SerializeWriter();
        try {
            new JSONSerializer(serializeWriter).d(this);
            appendable.append(serializeWriter.toString());
            serializeWriter.close();
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        } catch (Throwable th) {
            serializeWriter.close();
            throw th;
        }
    }

    public static final Object toJSON(Object obj) {
        return toJSON(obj, ParserConfig.b());
    }

    public static final Object toJSON(Object obj, ParserConfig parserConfig) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof JSON) {
            return (JSON) obj;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            JSONObject jSONObject = new JSONObject(map.size());
            for (Map.Entry entry : map.entrySet()) {
                jSONObject.put(TypeUtils.a(entry.getKey()), toJSON(entry.getValue()));
            }
            return jSONObject;
        } else if (obj instanceof Collection) {
            Collection<Object> collection = (Collection) obj;
            JSONArray jSONArray = new JSONArray(collection.size());
            for (Object json : collection) {
                jSONArray.add(toJSON(json));
            }
            return jSONArray;
        } else {
            Class<?> cls = obj.getClass();
            if (cls.isEnum()) {
                return ((Enum) obj).name();
            }
            if (cls.isArray()) {
                int length = Array.getLength(obj);
                JSONArray jSONArray2 = new JSONArray(length);
                for (int i = 0; i < length; i++) {
                    jSONArray2.add(toJSON(Array.get(obj, i)));
                }
                return jSONArray2;
            } else if (parserConfig.a(cls)) {
                return obj;
            } else {
                try {
                    List<FieldInfo> a2 = TypeUtils.a(cls, (Map<String, String>) null);
                    JSONObject jSONObject2 = new JSONObject(a2.size());
                    for (FieldInfo next : a2) {
                        jSONObject2.put(next.d(), toJSON(next.a(obj)));
                    }
                    return jSONObject2;
                } catch (Exception e) {
                    throw new JSONException("toJSON error", e);
                }
            }
        }
    }

    public static final <T> T toJavaObject(JSON json, Class<T> cls) {
        return TypeUtils.a((Object) json, cls, ParserConfig.b());
    }
}
