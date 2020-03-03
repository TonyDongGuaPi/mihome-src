package com.imi.fastjson;

import com.imi.fastjson.annotation.JSONField;
import com.imi.fastjson.parser.ParserConfig;
import com.imi.fastjson.util.TypeUtils;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class JSONObject extends JSON implements JSONAware, Serializable, Cloneable, InvocationHandler, Map<String, Object> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f6080a = 16;
    private static final long serialVersionUID = 1;
    private final Map<String, Object> map;

    public JSONObject() {
        this(16, false);
    }

    public JSONObject(Map<String, Object> map2) {
        this.map = map2;
    }

    public JSONObject(boolean z) {
        this(16, z);
    }

    public JSONObject(int i) {
        this(i, false);
    }

    public JSONObject(int i, boolean z) {
        if (z) {
            this.map = new LinkedHashMap(i);
        } else {
            this.map = new HashMap(i);
        }
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    public Object get(Object obj) {
        return this.map.get(obj);
    }

    public JSONObject getJSONObject(String str) {
        Object obj = this.map.get(str);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        return (JSONObject) toJSON(obj);
    }

    public JSONArray getJSONArray(String str) {
        Object obj = this.map.get(str);
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        return (JSONArray) toJSON(obj);
    }

    public <T> T getObject(String str, Class<T> cls) {
        return TypeUtils.a(this.map.get(str), cls);
    }

    public Boolean getBoolean(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return TypeUtils.o(obj);
    }

    public byte[] getBytes(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return TypeUtils.n(obj);
    }

    public boolean getBooleanValue(String str) {
        Object obj = get(str);
        if (obj == null) {
            return false;
        }
        return TypeUtils.o(obj).booleanValue();
    }

    public Byte getByte(String str) {
        return TypeUtils.b(get(str));
    }

    public byte getByteValue(String str) {
        Object obj = get(str);
        if (obj == null) {
            return 0;
        }
        return TypeUtils.b(obj).byteValue();
    }

    public Short getShort(String str) {
        return TypeUtils.d(get(str));
    }

    public short getShortValue(String str) {
        Object obj = get(str);
        if (obj == null) {
            return 0;
        }
        return TypeUtils.d(obj).shortValue();
    }

    public Integer getInteger(String str) {
        return TypeUtils.m(get(str));
    }

    public int getIntValue(String str) {
        Object obj = get(str);
        if (obj == null) {
            return 0;
        }
        return TypeUtils.m(obj).intValue();
    }

    public Long getLong(String str) {
        return TypeUtils.l(get(str));
    }

    public long getLongValue(String str) {
        Object obj = get(str);
        if (obj == null) {
            return 0;
        }
        return TypeUtils.l(obj).longValue();
    }

    public Float getFloat(String str) {
        return TypeUtils.g(get(str));
    }

    public float getFloatValue(String str) {
        Object obj = get(str);
        if (obj == null) {
            return 0.0f;
        }
        return TypeUtils.g(obj).floatValue();
    }

    public Double getDouble(String str) {
        return TypeUtils.h(get(str));
    }

    public double getDoubleValue(String str) {
        Object obj = get(str);
        if (obj == null) {
            return 0.0d;
        }
        return TypeUtils.h(obj).doubleValue();
    }

    public BigDecimal getBigDecimal(String str) {
        return TypeUtils.e(get(str));
    }

    public BigInteger getBigInteger(String str) {
        return TypeUtils.f(get(str));
    }

    public String getString(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public Date getDate(String str) {
        return TypeUtils.i(get(str));
    }

    public java.sql.Date getSqlDate(String str) {
        return TypeUtils.j(get(str));
    }

    public Timestamp getTimestamp(String str) {
        return TypeUtils.k(get(str));
    }

    public Object put(String str, Object obj) {
        return this.map.put(str, obj);
    }

    public void putAll(Map<? extends String, ? extends Object> map2) {
        this.map.putAll(map2);
    }

    public void clear() {
        this.map.clear();
    }

    public Object remove(Object obj) {
        return this.map.remove(obj);
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    public Collection<Object> values() {
        return this.map.values();
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    public Object clone() {
        return new JSONObject((Map<String, Object>) new HashMap(this.map));
    }

    public boolean equals(Object obj) {
        return this.map.equals(obj);
    }

    public int hashCode() {
        return this.map.hashCode();
    }

    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        Class[] parameterTypes = method.getParameterTypes();
        String str = null;
        if (parameterTypes.length == 1) {
            if (method.getReturnType() == Void.TYPE) {
                JSONField jSONField = (JSONField) method.getAnnotation(JSONField.class);
                String name = (jSONField == null || jSONField.name().length() == 0) ? null : jSONField.name();
                if (name == null) {
                    String name2 = method.getName();
                    if (name2.startsWith("set")) {
                        String substring = name2.substring(3);
                        if (substring.length() != 0) {
                            name = Character.toLowerCase(substring.charAt(0)) + substring.substring(1);
                        } else {
                            throw new JSONException("illegal setter");
                        }
                    } else {
                        throw new JSONException("illegal setter");
                    }
                }
                this.map.put(name, objArr[0]);
                return null;
            }
            throw new JSONException("illegal setter");
        } else if (parameterTypes.length != 0) {
            throw new UnsupportedOperationException(method.toGenericString());
        } else if (method.getReturnType() != Void.TYPE) {
            JSONField jSONField2 = (JSONField) method.getAnnotation(JSONField.class);
            if (!(jSONField2 == null || jSONField2.name().length() == 0)) {
                str = jSONField2.name();
            }
            if (str == null) {
                String name3 = method.getName();
                if (name3.startsWith("get")) {
                    String substring2 = name3.substring(3);
                    if (substring2.length() != 0) {
                        str = Character.toLowerCase(substring2.charAt(0)) + substring2.substring(1);
                    } else {
                        throw new JSONException("illegal getter");
                    }
                } else if (name3.startsWith("is")) {
                    String substring3 = name3.substring(2);
                    if (substring3.length() != 0) {
                        str = Character.toLowerCase(substring3.charAt(0)) + substring3.substring(1);
                    } else {
                        throw new JSONException("illegal getter");
                    }
                } else {
                    throw new JSONException("illegal getter");
                }
            }
            return TypeUtils.a(this.map.get(str), method.getGenericReturnType(), ParserConfig.b());
        } else {
            throw new JSONException("illegal getter");
        }
    }
}