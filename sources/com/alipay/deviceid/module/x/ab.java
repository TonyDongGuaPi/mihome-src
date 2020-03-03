package com.alipay.deviceid.module.x;

import com.alipay.deviceid.module.rpc.json.JSONException;
import com.taobao.weex.el.parse.Operators;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ab {
    public static final Object b = new a((byte) 0);

    /* renamed from: a  reason: collision with root package name */
    public Map f868a;

    static final class a {
        /* access modifiers changed from: protected */
        public final Object clone() {
            return this;
        }

        public final boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public final String toString() {
            return "null";
        }

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }

    public ab() {
        this.f868a = new HashMap();
    }

    public ab(ac acVar) {
        this();
        if (acVar.c() == '{') {
            while (true) {
                char c = acVar.c();
                if (c == 0) {
                    throw acVar.a("A JSONObject text must end with '}'");
                } else if (c != '}') {
                    acVar.a();
                    String obj = acVar.d().toString();
                    char c2 = acVar.c();
                    if (c2 == '=') {
                        if (acVar.b() != '>') {
                            acVar.a();
                        }
                    } else if (c2 != ':') {
                        throw acVar.a("Expected a ':' after a key");
                    }
                    Object d = acVar.d();
                    if (obj != null) {
                        if (d != null) {
                            b(d);
                            this.f868a.put(obj, d);
                        } else {
                            this.f868a.remove(obj);
                        }
                        char c3 = acVar.c();
                        if (c3 == ',' || c3 == ';') {
                            if (acVar.c() != '}') {
                                acVar.a();
                            } else {
                                return;
                            }
                        } else if (c3 != '}') {
                            throw acVar.a("Expected a ',' or '}'");
                        } else {
                            return;
                        }
                    } else {
                        throw new JSONException("Null key.");
                    }
                } else {
                    return;
                }
            }
        } else {
            throw acVar.a("A JSONObject text must begin with '{'");
        }
    }

    public ab(Map map) {
        this.f868a = map == null ? new HashMap() : map;
    }

    public ab(String str) {
        this(new ac(str));
    }

    public final Iterator a() {
        return this.f868a.keySet().iterator();
    }

    public static String b(String str) {
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

    private static void b(Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            if (d.isInfinite() || d.isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        } else if (obj instanceof Float) {
            Float f = (Float) obj;
            if (f.isInfinite() || f.isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
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
                stringBuffer.append(b(next.toString()));
                stringBuffer.append(Operators.CONDITION_IF_MIDDLE);
                stringBuffer.append(a(this.f868a.get(next)));
            }
            stringBuffer.append(Operators.BLOCK_END);
            return stringBuffer.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    static String a(Object obj) {
        if (obj == null || obj.equals((Object) null)) {
            return "null";
        }
        if (obj instanceof Number) {
            Number number = (Number) obj;
            if (number != null) {
                b((Object) number);
                String obj2 = number.toString();
                if (obj2.indexOf(46) <= 0 || obj2.indexOf(101) >= 0 || obj2.indexOf(69) >= 0) {
                    return obj2;
                }
                while (obj2.endsWith("0")) {
                    obj2 = obj2.substring(0, obj2.length() - 1);
                }
                return obj2.endsWith(".") ? obj2.substring(0, obj2.length() - 1) : obj2;
            }
            throw new JSONException("Null pointer");
        } else if ((obj instanceof Boolean) || (obj instanceof ab) || (obj instanceof aa)) {
            return obj.toString();
        } else {
            if (obj instanceof Map) {
                return new ab((Map) obj).toString();
            }
            if (obj instanceof Collection) {
                return new aa((Collection) obj).toString();
            }
            if (obj.getClass().isArray()) {
                return new aa(obj).toString();
            }
            return b(obj.toString());
        }
    }

    public final Object a(String str) {
        Object obj;
        if (str == null) {
            obj = null;
        } else {
            obj = this.f868a.get(str);
        }
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONObject[" + b(str) + "] not found.");
    }
}
