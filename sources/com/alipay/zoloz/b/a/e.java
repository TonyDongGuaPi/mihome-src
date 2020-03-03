package com.alipay.zoloz.b.a;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.a.a.a;
import org.json.a.a.c;

public class e {

    /* renamed from: a  reason: collision with root package name */
    static List<i> f1195a = new ArrayList();

    static {
        f1195a.add(new l());
        f1195a.add(new d());
        f1195a.add(new c());
        f1195a.add(new h());
        f1195a.add(new k());
        f1195a.add(new b());
        f1195a.add(new a());
        f1195a.add(new g());
    }

    public static final Object a(String str, Type type) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith(Operators.ARRAY_START_STR) && trim.endsWith(Operators.ARRAY_END_STR)) {
            return a((Object) new a(trim), type);
        }
        if (!trim.startsWith(Operators.BLOCK_START_STR) || !trim.endsWith("}")) {
            return a((Object) trim, type);
        }
        return a((Object) new c(trim), type);
    }

    public static final <T> T a(Object obj, Type type) {
        T a2;
        for (i next : f1195a) {
            if (next.a(com.alipay.zoloz.b.b.a.a(type)) && (a2 = next.a(obj, type)) != null) {
                return a2;
            }
        }
        return null;
    }
}
