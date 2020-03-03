package org.json.a.a;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private ArrayList f3641a;

    public a() {
        this.f3641a = new ArrayList();
    }

    public a(e eVar) {
        this();
        char c;
        char c2;
        char c3 = eVar.c();
        if (c3 == '[') {
            c = Operators.ARRAY_END;
        } else if (c3 == '(') {
            c = Operators.BRACKET_END;
        } else {
            throw eVar.a("A JSONArray text must start with '['");
        }
        if (eVar.c() != ']') {
            eVar.a();
            while (true) {
                if (eVar.c() == ',') {
                    eVar.a();
                    this.f3641a.add((Object) null);
                } else {
                    eVar.a();
                    this.f3641a.add(eVar.d());
                }
                c2 = eVar.c();
                if (c2 == ')') {
                    break;
                } else if (c2 == ',' || c2 == ';') {
                    if (eVar.c() != ']') {
                        eVar.a();
                    } else {
                        return;
                    }
                } else if (c2 != ']') {
                    throw eVar.a("Expected a ',' or ']'");
                }
            }
            if (c != c2) {
                throw eVar.a("Expected a '" + new Character(c) + "'");
            }
        }
    }

    public a(String str) {
        this(new e(str));
    }

    public a(Collection collection) {
        this.f3641a = collection == null ? new ArrayList() : new ArrayList(collection);
    }

    public a(Object obj) {
        this();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                a(Array.get(obj, i));
            }
            return;
        }
        throw new b("JSONArray initial value should be a string or collection or array.");
    }

    public Object a(int i) {
        Object b = b(i);
        if (b != null) {
            return b;
        }
        throw new b("JSONArray[" + i + "] not found.");
    }

    public String a(String str) {
        int a2 = a();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < a2; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(c.b(this.f3641a.get(i)));
        }
        return stringBuffer.toString();
    }

    public int a() {
        return this.f3641a.size();
    }

    public Object b(int i) {
        if (i < 0 || i >= a()) {
            return null;
        }
        return this.f3641a.get(i);
    }

    public a a(Object obj) {
        this.f3641a.add(obj);
        return this;
    }

    public String toString() {
        try {
            return Operators.ARRAY_START + a(",") + Operators.ARRAY_END;
        } catch (Exception unused) {
            return null;
        }
    }
}
