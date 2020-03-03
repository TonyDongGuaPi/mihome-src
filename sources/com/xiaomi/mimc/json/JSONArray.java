package com.xiaomi.mimc.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JSONArray implements Iterable<Object> {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<Object> f11199a;

    public JSONArray() {
        this.f11199a = new ArrayList<>();
    }

    public JSONArray(JSONTokener jSONTokener) throws JSONException {
        this();
        if (jSONTokener.e() == '[') {
            char e = jSONTokener.e();
            if (e == 0) {
                throw jSONTokener.b("Expected a ',' or ']'");
            } else if (e != ']') {
                jSONTokener.a();
                while (true) {
                    if (jSONTokener.e() == ',') {
                        jSONTokener.a();
                        this.f11199a.add(JSONObject.f11200a);
                    } else {
                        jSONTokener.a();
                        this.f11199a.add(jSONTokener.f());
                    }
                    char e2 = jSONTokener.e();
                    if (e2 == 0) {
                        throw jSONTokener.b("Expected a ',' or ']'");
                    } else if (e2 == ',') {
                        char e3 = jSONTokener.e();
                        if (e3 == 0) {
                            throw jSONTokener.b("Expected a ',' or ']'");
                        } else if (e3 != ']') {
                            jSONTokener.a();
                        } else {
                            return;
                        }
                    } else if (e2 != ']') {
                        throw jSONTokener.b("Expected a ',' or ']'");
                    } else {
                        return;
                    }
                }
            }
        } else {
            throw jSONTokener.b("A JSONArray text must start with '['");
        }
    }

    public JSONArray(String str) throws JSONException {
        this(new JSONTokener(str));
    }

    public JSONArray(Collection<?> collection) {
        if (collection == null) {
            this.f11199a = new ArrayList<>();
            return;
        }
        this.f11199a = new ArrayList<>(collection.size());
        for (Object e : collection) {
            this.f11199a.add(JSONObject.e((Object) e));
        }
    }

    public JSONArray(Object obj) throws JSONException {
        this();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            this.f11199a.ensureCapacity(length);
            for (int i = 0; i < length; i++) {
                a(JSONObject.e(Array.get(obj, i)));
            }
            return;
        }
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }

    public Iterator<Object> iterator() {
        return this.f11199a.iterator();
    }

    public Object a(int i) throws JSONException {
        Object n = n(i);
        if (n != null) {
            return n;
        }
        throw new JSONException("JSONArray[" + i + "] not found.");
    }

    public boolean b(int i) throws JSONException {
        Object a2 = a(i);
        if (a2.equals(Boolean.FALSE)) {
            return false;
        }
        boolean z = a2 instanceof String;
        if (z && ((String) a2).equalsIgnoreCase("false")) {
            return false;
        }
        if (a2.equals(Boolean.TRUE)) {
            return true;
        }
        if (z && ((String) a2).equalsIgnoreCase("true")) {
            return true;
        }
        throw new JSONException("JSONArray[" + i + "] is not a boolean.");
    }

    public double c(int i) throws JSONException {
        Object a2 = a(i);
        try {
            if (a2 instanceof Number) {
                return ((Number) a2).doubleValue();
            }
            return Double.parseDouble((String) a2);
        } catch (Exception e) {
            throw new JSONException("JSONArray[" + i + "] is not a number.", e);
        }
    }

    public float d(int i) throws JSONException {
        Object a2 = a(i);
        try {
            if (a2 instanceof Number) {
                return ((Number) a2).floatValue();
            }
            return Float.parseFloat(a2.toString());
        } catch (Exception e) {
            throw new JSONException("JSONArray[" + i + "] is not a number.", e);
        }
    }

    public Number e(int i) throws JSONException {
        Object a2 = a(i);
        try {
            if (a2 instanceof Number) {
                return (Number) a2;
            }
            return JSONObject.E(a2.toString());
        } catch (Exception e) {
            throw new JSONException("JSONArray[" + i + "] is not a number.", e);
        }
    }

    public <E extends Enum<E>> E a(Class<E> cls, int i) throws JSONException {
        E b = b(cls, i);
        if (b != null) {
            return b;
        }
        throw new JSONException("JSONArray[" + i + "] is not an enum of type " + JSONObject.B(cls.getSimpleName()) + ".");
    }

    public BigDecimal f(int i) throws JSONException {
        try {
            return new BigDecimal(a(i).toString());
        } catch (Exception e) {
            throw new JSONException("JSONArray[" + i + "] could not convert to BigDecimal.", e);
        }
    }

    public BigInteger g(int i) throws JSONException {
        try {
            return new BigInteger(a(i).toString());
        } catch (Exception e) {
            throw new JSONException("JSONArray[" + i + "] could not convert to BigInteger.", e);
        }
    }

    public int h(int i) throws JSONException {
        Object a2 = a(i);
        try {
            if (a2 instanceof Number) {
                return ((Number) a2).intValue();
            }
            return Integer.parseInt((String) a2);
        } catch (Exception e) {
            throw new JSONException("JSONArray[" + i + "] is not a number.", e);
        }
    }

    public JSONArray i(int i) throws JSONException {
        Object a2 = a(i);
        if (a2 instanceof JSONArray) {
            return (JSONArray) a2;
        }
        throw new JSONException("JSONArray[" + i + "] is not a JSONArray.");
    }

    public JSONObject j(int i) throws JSONException {
        Object a2 = a(i);
        if (a2 instanceof JSONObject) {
            return (JSONObject) a2;
        }
        throw new JSONException("JSONArray[" + i + "] is not a JSONObject.");
    }

    public long k(int i) throws JSONException {
        Object a2 = a(i);
        try {
            if (a2 instanceof Number) {
                return ((Number) a2).longValue();
            }
            return Long.parseLong((String) a2);
        } catch (Exception e) {
            throw new JSONException("JSONArray[" + i + "] is not a number.", e);
        }
    }

    public String l(int i) throws JSONException {
        Object a2 = a(i);
        if (a2 instanceof String) {
            return (String) a2;
        }
        throw new JSONException("JSONArray[" + i + "] not a string.");
    }

    public boolean m(int i) {
        return JSONObject.f11200a.equals(n(i));
    }

    public String a(String str) throws JSONException {
        int a2 = a();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a2; i++) {
            if (i > 0) {
                sb.append(str);
            }
            sb.append(JSONObject.d(this.f11199a.get(i)));
        }
        return sb.toString();
    }

    public int a() {
        return this.f11199a.size();
    }

    public Object n(int i) {
        if (i < 0 || i >= a()) {
            return null;
        }
        return this.f11199a.get(i);
    }

    public boolean o(int i) {
        return a(i, false);
    }

    public boolean a(int i, boolean z) {
        try {
            return b(i);
        } catch (Exception unused) {
            return z;
        }
    }

    public double p(int i) {
        return a(i, Double.NaN);
    }

    public double a(int i, double d) {
        Object n = n(i);
        if (JSONObject.f11200a.equals(n)) {
            return d;
        }
        if (n instanceof Number) {
            return ((Number) n).doubleValue();
        }
        if (!(n instanceof String)) {
            return d;
        }
        try {
            return Double.parseDouble((String) n);
        } catch (Exception unused) {
            return d;
        }
    }

    public float q(int i) {
        return a(i, Float.NaN);
    }

    public float a(int i, float f) {
        Object n = n(i);
        if (JSONObject.f11200a.equals(n)) {
            return f;
        }
        if (n instanceof Number) {
            return ((Number) n).floatValue();
        }
        if (!(n instanceof String)) {
            return f;
        }
        try {
            return Float.parseFloat((String) n);
        } catch (Exception unused) {
            return f;
        }
    }

    public int r(int i) {
        return a(i, 0);
    }

    public int a(int i, int i2) {
        Object n = n(i);
        if (JSONObject.f11200a.equals(n)) {
            return i2;
        }
        if (n instanceof Number) {
            return ((Number) n).intValue();
        }
        if (!(n instanceof String)) {
            return i2;
        }
        try {
            return new BigDecimal(n.toString()).intValue();
        } catch (Exception unused) {
            return i2;
        }
    }

    public <E extends Enum<E>> E b(Class<E> cls, int i) {
        return a(cls, i, (Enum) null);
    }

    public <E extends Enum<E>> E a(Class<E> cls, int i, E e) {
        try {
            E n = n(i);
            if (JSONObject.f11200a.equals(n)) {
                return e;
            }
            if (cls.isAssignableFrom(n.getClass())) {
                return (Enum) n;
            }
            return Enum.valueOf(cls, n.toString());
        } catch (IllegalArgumentException unused) {
            return e;
        } catch (NullPointerException unused2) {
            return e;
        }
    }

    public BigInteger a(int i, BigInteger bigInteger) {
        Object n = n(i);
        if (JSONObject.f11200a.equals(n)) {
            return bigInteger;
        }
        if (n instanceof BigInteger) {
            return (BigInteger) n;
        }
        if (n instanceof BigDecimal) {
            return ((BigDecimal) n).toBigInteger();
        }
        if ((n instanceof Double) || (n instanceof Float)) {
            return new BigDecimal(((Number) n).doubleValue()).toBigInteger();
        }
        if ((n instanceof Long) || (n instanceof Integer) || (n instanceof Short) || (n instanceof Byte)) {
            return BigInteger.valueOf(((Number) n).longValue());
        }
        try {
            String obj = n.toString();
            if (JSONObject.D(obj)) {
                return new BigDecimal(obj).toBigInteger();
            }
            return new BigInteger(obj);
        } catch (Exception unused) {
            return bigInteger;
        }
    }

    public BigDecimal a(int i, BigDecimal bigDecimal) {
        Object n = n(i);
        if (JSONObject.f11200a.equals(n)) {
            return bigDecimal;
        }
        if (n instanceof BigDecimal) {
            return (BigDecimal) n;
        }
        if (n instanceof BigInteger) {
            return new BigDecimal((BigInteger) n);
        }
        if ((n instanceof Double) || (n instanceof Float)) {
            return new BigDecimal(((Number) n).doubleValue());
        }
        if ((n instanceof Long) || (n instanceof Integer) || (n instanceof Short) || (n instanceof Byte)) {
            return new BigDecimal(((Number) n).longValue());
        }
        try {
            return new BigDecimal(n.toString());
        } catch (Exception unused) {
            return bigDecimal;
        }
    }

    public JSONArray s(int i) {
        Object n = n(i);
        if (n instanceof JSONArray) {
            return (JSONArray) n;
        }
        return null;
    }

    public JSONObject t(int i) {
        Object n = n(i);
        if (n instanceof JSONObject) {
            return (JSONObject) n;
        }
        return null;
    }

    public long u(int i) {
        return a(i, 0);
    }

    public long a(int i, long j) {
        Object n = n(i);
        if (JSONObject.f11200a.equals(n)) {
            return j;
        }
        if (n instanceof Number) {
            return ((Number) n).longValue();
        }
        if (!(n instanceof String)) {
            return j;
        }
        try {
            return new BigDecimal(n.toString()).longValue();
        } catch (Exception unused) {
            return j;
        }
    }

    public Number v(int i) {
        return a(i, (Number) null);
    }

    public Number a(int i, Number number) {
        Object n = n(i);
        if (JSONObject.f11200a.equals(n)) {
            return number;
        }
        if (n instanceof Number) {
            return (Number) n;
        }
        if (!(n instanceof String)) {
            return number;
        }
        try {
            return JSONObject.E((String) n);
        } catch (Exception unused) {
            return number;
        }
    }

    public String w(int i) {
        return a(i, "");
    }

    public String a(int i, String str) {
        Object n = n(i);
        return JSONObject.f11200a.equals(n) ? str : n.toString();
    }

    public JSONArray a(boolean z) {
        return a((Object) z ? Boolean.TRUE : Boolean.FALSE);
    }

    public JSONArray a(Collection<?> collection) {
        return a((Object) new JSONArray(collection));
    }

    public JSONArray a(double d) throws JSONException {
        return a((Object) Double.valueOf(d));
    }

    public JSONArray a(float f) throws JSONException {
        return a((Object) Float.valueOf(f));
    }

    public JSONArray x(int i) {
        return a((Object) Integer.valueOf(i));
    }

    public JSONArray a(long j) {
        return a((Object) Long.valueOf(j));
    }

    public JSONArray a(Map<?, ?> map) {
        return a((Object) new JSONObject(map));
    }

    public JSONArray a(Object obj) {
        JSONObject.c(obj);
        this.f11199a.add(obj);
        return this;
    }

    public JSONArray b(int i, boolean z) throws JSONException {
        return a(i, (Object) z ? Boolean.TRUE : Boolean.FALSE);
    }

    public JSONArray a(int i, Collection<?> collection) throws JSONException {
        return a(i, (Object) new JSONArray(collection));
    }

    public JSONArray b(int i, double d) throws JSONException {
        return a(i, (Object) Double.valueOf(d));
    }

    public JSONArray b(int i, float f) throws JSONException {
        return a(i, (Object) Float.valueOf(f));
    }

    public JSONArray b(int i, int i2) throws JSONException {
        return a(i, (Object) Integer.valueOf(i2));
    }

    public JSONArray b(int i, long j) throws JSONException {
        return a(i, (Object) Long.valueOf(j));
    }

    public JSONArray a(int i, Map<?, ?> map) throws JSONException {
        a(i, (Object) new JSONObject(map));
        return this;
    }

    public JSONArray a(int i, Object obj) throws JSONException {
        if (i < 0) {
            throw new JSONException("JSONArray[" + i + "] not found.");
        } else if (i < a()) {
            JSONObject.c(obj);
            this.f11199a.set(i, obj);
            return this;
        } else if (i == a()) {
            return a(obj);
        } else {
            this.f11199a.ensureCapacity(i + 1);
            while (i != a()) {
                this.f11199a.add(JSONObject.f11200a);
            }
            return a(obj);
        }
    }

    public Object b(String str) {
        return a(new JSONPointer(str));
    }

    public Object a(JSONPointer jSONPointer) {
        return jSONPointer.a((Object) this);
    }

    public Object c(String str) {
        return b(new JSONPointer(str));
    }

    public Object b(JSONPointer jSONPointer) {
        try {
            return jSONPointer.a((Object) this);
        } catch (JSONPointerException unused) {
            return null;
        }
    }

    public Object y(int i) {
        if (i < 0 || i >= a()) {
            return null;
        }
        return this.f11199a.remove(i);
    }

    public boolean b(Object obj) {
        if (!(obj instanceof JSONArray)) {
            return false;
        }
        int a2 = a();
        JSONArray jSONArray = (JSONArray) obj;
        if (a2 != jSONArray.a()) {
            return false;
        }
        for (int i = 0; i < a2; i++) {
            Object obj2 = this.f11199a.get(i);
            Object obj3 = jSONArray.f11199a.get(i);
            if (obj2 != obj3) {
                if (obj2 == null) {
                    return false;
                }
                if (obj2 instanceof JSONObject) {
                    if (!((JSONObject) obj2).b(obj3)) {
                        return false;
                    }
                } else if (obj2 instanceof JSONArray) {
                    if (!((JSONArray) obj2).b(obj3)) {
                        return false;
                    }
                } else if (!obj2.equals(obj3)) {
                    return false;
                }
            }
        }
        return true;
    }

    public JSONObject a(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.c() || c()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(jSONArray.a());
        for (int i = 0; i < jSONArray.a(); i++) {
            jSONObject.c(jSONArray.l(i), n(i));
        }
        return jSONObject;
    }

    public String toString() {
        try {
            return z(0);
        } catch (Exception unused) {
            return null;
        }
    }

    public String z(int i) throws JSONException {
        String obj;
        StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            obj = a((Writer) stringWriter, i, 0).toString();
        }
        return obj;
    }

    public Writer a(Writer writer) throws JSONException {
        return a(writer, 0, 0);
    }

    public Writer a(Writer writer, int i, int i2) throws JSONException {
        int i3;
        try {
            int a2 = a();
            writer.write(91);
            i3 = 0;
            if (a2 == 1) {
                JSONObject.a(writer, this.f11199a.get(0), i, i2);
            } else if (a2 != 0) {
                int i4 = i2 + i;
                boolean z = false;
                while (i3 < a2) {
                    if (z) {
                        writer.write(44);
                    }
                    if (i > 0) {
                        writer.write(10);
                    }
                    JSONObject.a(writer, i4);
                    JSONObject.a(writer, this.f11199a.get(i3), i, i4);
                    i3++;
                    z = true;
                }
                if (i > 0) {
                    writer.write(10);
                }
                JSONObject.a(writer, i2);
            }
            writer.write(93);
            return writer;
        } catch (Exception e) {
            throw new JSONException("Unable to write JSONArray value at index: " + i3, e);
        } catch (Exception e2) {
            throw new JSONException("Unable to write JSONArray value at index: 0", e2);
        } catch (IOException e3) {
            throw new JSONException((Throwable) e3);
        }
    }

    public List<Object> b() {
        ArrayList arrayList = new ArrayList(this.f11199a.size());
        Iterator<Object> it = this.f11199a.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next == null || JSONObject.f11200a.equals(next)) {
                arrayList.add((Object) null);
            } else if (next instanceof JSONArray) {
                arrayList.add(((JSONArray) next).b());
            } else if (next instanceof JSONObject) {
                arrayList.add(((JSONObject) next).g());
            } else {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public boolean c() {
        return this.f11199a.isEmpty();
    }
}
