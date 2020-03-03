package org.json.alipay;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private ArrayList f3644a;

    public a() {
        this.f3644a = new ArrayList();
    }

    public a(Object obj) {
        this();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                this.f3644a.add(Array.get(obj, i));
            }
            return;
        }
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }

    public a(String str) {
        this(new c(str));
    }

    public a(Collection collection) {
        this.f3644a = collection == null ? new ArrayList() : new ArrayList(collection);
    }

    public a(c cVar) {
        this();
        char c;
        ArrayList arrayList;
        Object d;
        char c2 = cVar.c();
        if (c2 == '[') {
            c = Operators.ARRAY_END;
        } else if (c2 == '(') {
            c = Operators.BRACKET_END;
        } else {
            throw cVar.a("A JSONArray text must start with '['");
        }
        if (cVar.c() != ']') {
            do {
                cVar.a();
                if (cVar.c() == ',') {
                    cVar.a();
                    arrayList = this.f3644a;
                    d = null;
                } else {
                    cVar.a();
                    arrayList = this.f3644a;
                    d = cVar.d();
                }
                arrayList.add(d);
                char c3 = cVar.c();
                if (c3 != ')') {
                    if (c3 != ',' && c3 != ';') {
                        if (c3 != ']') {
                            throw cVar.a("Expected a ',' or ']'");
                        }
                    }
                }
                if (c != c3) {
                    throw cVar.a("Expected a '" + new Character(c) + "'");
                }
                return;
            } while (cVar.c() != ']');
        }
    }

    private String a(String str) {
        int size = this.f3644a.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(b.a(this.f3644a.get(i)));
        }
        return stringBuffer.toString();
    }

    public final int a() {
        return this.f3644a.size();
    }

    public final Object a(int i) {
        Object obj = (i < 0 || i >= this.f3644a.size()) ? null : this.f3644a.get(i);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONArray[" + i + "] not found.");
    }

    public String toString() {
        try {
            return Operators.ARRAY_START_STR + a(",") + Operators.ARRAY_END;
        } catch (Exception unused) {
            return null;
        }
    }
}
