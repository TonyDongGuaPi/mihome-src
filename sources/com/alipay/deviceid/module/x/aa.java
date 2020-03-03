package com.alipay.deviceid.module.x;

import com.alipay.deviceid.module.rpc.json.JSONException;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class aa {

    /* renamed from: a  reason: collision with root package name */
    public ArrayList f867a;

    public aa() {
        this.f867a = new ArrayList();
    }

    public aa(ac acVar) {
        this();
        char c;
        char c2;
        char c3 = acVar.c();
        if (c3 == '[') {
            c = Operators.ARRAY_END;
        } else if (c3 == '(') {
            c = Operators.BRACKET_END;
        } else {
            throw acVar.a("A JSONArray text must start with '['");
        }
        if (acVar.c() != ']') {
            acVar.a();
            while (true) {
                if (acVar.c() == ',') {
                    acVar.a();
                    this.f867a.add((Object) null);
                } else {
                    acVar.a();
                    this.f867a.add(acVar.d());
                }
                c2 = acVar.c();
                if (c2 == ')') {
                    break;
                } else if (c2 == ',' || c2 == ';') {
                    if (acVar.c() != ']') {
                        acVar.a();
                    } else {
                        return;
                    }
                } else if (c2 != ']') {
                    throw acVar.a("Expected a ',' or ']'");
                }
            }
            if (c != c2) {
                throw acVar.a("Expected a '" + new Character(c) + "'");
            }
        }
    }

    public aa(String str) {
        this(new ac(str));
    }

    public aa(Collection collection) {
        this.f867a = collection == null ? new ArrayList() : new ArrayList(collection);
    }

    public aa(Object obj) {
        this();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                this.f867a.add(Array.get(obj, i));
            }
            return;
        }
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }

    public String toString() {
        try {
            return Operators.ARRAY_START_STR + a(",") + Operators.ARRAY_END;
        } catch (Exception unused) {
            return null;
        }
    }

    public final Object a(int i) {
        Object obj = (i < 0 || i >= this.f867a.size()) ? null : this.f867a.get(i);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONArray[" + i + "] not found.");
    }

    private String a(String str) {
        int size = this.f867a.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(ab.a(this.f867a.get(i)));
        }
        return stringBuffer.toString();
    }
}
