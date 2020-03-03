package com.xiaomi.mimc.json;

import com.google.code.microlog4android.format.PatternFormatter;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

public class JSONWriter {
    private static final int c = 200;

    /* renamed from: a  reason: collision with root package name */
    protected char f11204a = PatternFormatter.CLIENT_ID_CONVERSION_CHAR;
    protected Appendable b;
    private boolean d = false;
    private final JSONObject[] e = new JSONObject[200];
    private int f = 0;

    public JSONWriter(Appendable appendable) {
        this.b = appendable;
    }

    private JSONWriter b(String str) throws JSONException {
        if (str == null) {
            throw new JSONException("Null pointer");
        } else if (this.f11204a == 'o' || this.f11204a == 'a') {
            try {
                if (this.d && this.f11204a == 'a') {
                    this.b.append(',');
                }
                this.b.append(str);
                if (this.f11204a == 'o') {
                    this.f11204a = 'k';
                }
                this.d = true;
                return this;
            } catch (IOException e2) {
                throw new JSONException((Throwable) e2);
            }
        } else {
            throw new JSONException("Value out of sequence.");
        }
    }

    public JSONWriter a() throws JSONException {
        if (this.f11204a == 'i' || this.f11204a == 'o' || this.f11204a == 'a') {
            a((JSONObject) null);
            b(Operators.ARRAY_START_STR);
            this.d = false;
            return this;
        }
        throw new JSONException("Misplaced array.");
    }

    private JSONWriter a(char c2, char c3) throws JSONException {
        if (this.f11204a != c2) {
            throw new JSONException(c2 == 'a' ? "Misplaced endArray." : "Misplaced endObject.");
        }
        a(c2);
        try {
            this.b.append(c3);
            this.d = true;
            return this;
        } catch (IOException e2) {
            throw new JSONException((Throwable) e2);
        }
    }

    public JSONWriter b() throws JSONException {
        return a('a', Operators.ARRAY_END);
    }

    public JSONWriter c() throws JSONException {
        return a('k', Operators.BLOCK_END);
    }

    public JSONWriter a(String str) throws JSONException {
        if (str == null) {
            throw new JSONException("Null key.");
        } else if (this.f11204a == 'k') {
            try {
                JSONObject jSONObject = this.e[this.f - 1];
                if (!jSONObject.m(str)) {
                    jSONObject.b(str, true);
                    if (this.d) {
                        this.b.append(',');
                    }
                    this.b.append(JSONObject.B(str));
                    this.b.append(Operators.CONDITION_IF_MIDDLE);
                    this.d = false;
                    this.f11204a = 'o';
                    return this;
                }
                throw new JSONException("Duplicate key \"" + str + "\"");
            } catch (IOException e2) {
                throw new JSONException((Throwable) e2);
            }
        } else {
            throw new JSONException("Misplaced key.");
        }
    }

    public JSONWriter d() throws JSONException {
        if (this.f11204a == 'i') {
            this.f11204a = 'o';
        }
        if (this.f11204a == 'o' || this.f11204a == 'a') {
            b(Operators.BLOCK_START_STR);
            a(new JSONObject());
            this.d = false;
            return this;
        }
        throw new JSONException("Misplaced object.");
    }

    private void a(char c2) throws JSONException {
        if (this.f > 0) {
            char c3 = 'k';
            if ((this.e[this.f + -1] == null ? 'a' : 'k') == c2) {
                this.f--;
                if (this.f == 0) {
                    c3 = PatternFormatter.DATE_CONVERSION_CHAR;
                } else if (this.e[this.f - 1] == null) {
                    c3 = 'a';
                }
                this.f11204a = c3;
                return;
            }
            throw new JSONException("Nesting error.");
        }
        throw new JSONException("Nesting error.");
    }

    private void a(JSONObject jSONObject) throws JSONException {
        if (this.f < 200) {
            this.e[this.f] = jSONObject;
            this.f11204a = jSONObject == null ? 'a' : 'k';
            this.f++;
            return;
        }
        throw new JSONException("Nesting too deep.");
    }

    public static String a(Object obj) throws JSONException {
        if (obj == null || obj.equals((Object) null)) {
            return "null";
        }
        if (obj instanceof JSONString) {
            try {
                String a2 = ((JSONString) obj).a();
                if (a2 instanceof String) {
                    return a2;
                }
                throw new JSONException("Bad value from toJSONString: " + a2);
            } catch (Exception e2) {
                throw new JSONException((Throwable) e2);
            }
        } else if (obj instanceof Number) {
            String a3 = JSONObject.a((Number) obj);
            try {
                new BigDecimal(a3);
                return a3;
            } catch (NumberFormatException unused) {
                return JSONObject.B(a3);
            }
        } else if ((obj instanceof Boolean) || (obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj.toString();
        } else {
            if (obj instanceof Map) {
                return new JSONObject((Map<?, ?>) (Map) obj).toString();
            }
            if (obj instanceof Collection) {
                return new JSONArray((Collection<?>) (Collection) obj).toString();
            }
            if (obj.getClass().isArray()) {
                return new JSONArray(obj).toString();
            }
            if (obj instanceof Enum) {
                return JSONObject.B(((Enum) obj).name());
            }
            return JSONObject.B(obj.toString());
        }
    }

    public JSONWriter a(boolean z) throws JSONException {
        return b(z ? "true" : "false");
    }

    public JSONWriter a(double d2) throws JSONException {
        return b((Object) Double.valueOf(d2));
    }

    public JSONWriter a(long j) throws JSONException {
        return b(Long.toString(j));
    }

    public JSONWriter b(Object obj) throws JSONException {
        return b(a(obj));
    }
}
