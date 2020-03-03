package com.alipay.a.a;

import com.alipay.a.b.a;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.alipay.b;

public final class e {

    /* renamed from: a  reason: collision with root package name */
    static List<i> f809a;

    static {
        ArrayList arrayList = new ArrayList();
        f809a = arrayList;
        arrayList.add(new l());
        f809a.add(new d());
        f809a.add(new c());
        f809a.add(new h());
        f809a.add(new k());
        f809a.add(new b());
        f809a.add(new a());
        f809a.add(new g());
    }

    public static final <T> T a(Object obj, Type type) {
        T a2;
        for (i next : f809a) {
            if (next.a(a.a(type)) && (a2 = next.a(obj, type)) != null) {
                return a2;
            }
        }
        return null;
    }

    public static final Object a(String str, Type type) {
        Object bVar;
        if (str == null || str.length() == 0) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith(Operators.ARRAY_START_STR) && trim.endsWith(Operators.ARRAY_END_STR)) {
            bVar = new org.json.alipay.a(trim);
        } else if (!trim.startsWith(Operators.BLOCK_START_STR) || !trim.endsWith("}")) {
            return a((Object) trim, type);
        } else {
            bVar = new b(trim);
        }
        return a(bVar, type);
    }
}
