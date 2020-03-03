package org.json.a.a;

import com.taobao.weex.el.parse.Operators;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class c {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f3642a = new a();
    private Map b;

    private static final class a {
        /* access modifiers changed from: protected */
        public final Object clone() {
            return this;
        }

        public boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public String toString() {
            return "null";
        }

        private a() {
        }
    }

    public c() {
        this.b = new HashMap();
    }

    public c(e eVar) {
        this();
        if (eVar.c() == '{') {
            while (true) {
                char c = eVar.c();
                if (c == 0) {
                    throw eVar.a("A JSONObject text must end with '}'");
                } else if (c != '}') {
                    eVar.a();
                    String obj = eVar.d().toString();
                    char c2 = eVar.c();
                    if (c2 == '=') {
                        if (eVar.b() != '>') {
                            eVar.a();
                        }
                    } else if (c2 != ':') {
                        throw eVar.a("Expected a ':' after a key");
                    }
                    a(obj, eVar.d());
                    char c3 = eVar.c();
                    if (c3 == ',' || c3 == ';') {
                        if (eVar.c() != '}') {
                            eVar.a();
                        } else {
                            return;
                        }
                    } else if (c3 != '}') {
                        throw eVar.a("Expected a ',' or '}'");
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
        } else {
            throw eVar.a("A JSONObject text must begin with '{'");
        }
    }

    public c(Map map) {
        this.b = map == null ? new HashMap() : map;
    }

    public c(String str) {
        this(new e(str));
    }

    public Object a(String str) {
        Object c = c(str);
        if (c != null) {
            return c;
        }
        throw new b("JSONObject[" + d(str) + "] not found.");
    }

    public boolean b(String str) {
        return this.b.containsKey(str);
    }

    public Iterator a() {
        return this.b.keySet().iterator();
    }

    public static String a(Number number) {
        if (number != null) {
            a((Object) number);
            String obj = number.toString();
            if (obj.indexOf(46) <= 0 || obj.indexOf(101) >= 0 || obj.indexOf(69) >= 0) {
                return obj;
            }
            while (obj.endsWith("0")) {
                obj = obj.substring(0, obj.length() - 1);
            }
            return obj.endsWith(".") ? obj.substring(0, obj.length() - 1) : obj;
        }
        throw new b("Null pointer");
    }

    public Object c(String str) {
        if (str == null) {
            return null;
        }
        return this.b.get(str);
    }

    public c a(String str, Object obj) {
        if (str != null) {
            if (obj != null) {
                a(obj);
                this.b.put(str, obj);
            } else {
                e(str);
            }
            return this;
        }
        throw new b("Null key.");
    }

    public static String d(String str) {
        if (str == null || str.length() == 0) {
            return "\"\"";
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length + 4);
        stringBuffer.append('\"');
        int i = 0;
        char c = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt != '\"') {
                if (charAt != '/') {
                    if (charAt != '\\') {
                        switch (charAt) {
                            case 8:
                                stringBuffer.append("\\b");
                                break;
                            case 9:
                                stringBuffer.append("\\t");
                                break;
                            case 10:
                                stringBuffer.append("\\n");
                                break;
                            default:
                                switch (charAt) {
                                    case 12:
                                        stringBuffer.append("\\f");
                                        break;
                                    case 13:
                                        stringBuffer.append("\\r");
                                        break;
                                    default:
                                        if (charAt >= ' ' && ((charAt < 128 || charAt >= 160) && (charAt < 8192 || charAt >= 8448))) {
                                            stringBuffer.append(charAt);
                                            break;
                                        } else {
                                            String str2 = Constant.DEFAULT_CVN2 + Integer.toHexString(charAt);
                                            stringBuffer.append("\\u" + str2.substring(str2.length() - 4));
                                            break;
                                        }
                                        break;
                                }
                        }
                    }
                } else {
                    if (c == '<') {
                        stringBuffer.append(IOUtils.b);
                    }
                    stringBuffer.append(charAt);
                }
                i++;
                c = charAt;
            }
            stringBuffer.append(IOUtils.b);
            stringBuffer.append(charAt);
            i++;
            c = charAt;
        }
        stringBuffer.append('\"');
        return stringBuffer.toString();
    }

    public Object e(String str) {
        return this.b.remove(str);
    }

    static void a(Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            if (d.isInfinite() || d.isNaN()) {
                throw new b("JSON does not allow non-finite numbers.");
            }
        } else if (obj instanceof Float) {
            Float f = (Float) obj;
            if (f.isInfinite() || f.isNaN()) {
                throw new b("JSON does not allow non-finite numbers.");
            }
        }
    }

    public String toString() {
        try {
            Iterator a2 = a();
            StringBuffer stringBuffer = new StringBuffer(Operators.BLOCK_START_STR);
            while (a2.hasNext()) {
                if (stringBuffer.length() > 1) {
                    stringBuffer.append(',');
                }
                Object next = a2.next();
                stringBuffer.append(d(next.toString()));
                stringBuffer.append(Operators.CONDITION_IF_MIDDLE);
                stringBuffer.append(b(this.b.get(next)));
            }
            stringBuffer.append(Operators.BLOCK_END);
            return stringBuffer.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    static String b(Object obj) {
        if (obj == null || obj.equals((Object) null)) {
            return "null";
        }
        if (obj instanceof Number) {
            return a((Number) obj);
        }
        if ((obj instanceof Boolean) || (obj instanceof c) || (obj instanceof a)) {
            return obj.toString();
        }
        if (obj instanceof Map) {
            return new c((Map) obj).toString();
        }
        if (obj instanceof Collection) {
            return new a((Collection) obj).toString();
        }
        if (obj.getClass().isArray()) {
            return new a(obj).toString();
        }
        return d(obj.toString());
    }
}
