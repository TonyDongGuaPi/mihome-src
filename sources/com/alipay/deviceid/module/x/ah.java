package com.alipay.deviceid.module.x;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class ah {

    /* renamed from: a  reason: collision with root package name */
    static List<al> f870a;

    static {
        ArrayList arrayList = new ArrayList();
        f870a = arrayList;
        arrayList.add(new ao());
        f870a.add(new ag());
        f870a.add(new af());
        f870a.add(new ak());
        f870a.add(new an());
        f870a.add(new ae());
        f870a.add(new ad());
        f870a.add(new aj());
    }

    public static final Object a(String str, Type type) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith(Operators.ARRAY_START_STR) && trim.endsWith(Operators.ARRAY_END_STR)) {
            return a((Object) new aa(trim), type);
        }
        if (!trim.startsWith(Operators.BLOCK_START_STR) || !trim.endsWith("}")) {
            return a((Object) trim, type);
        }
        return a((Object) new ab(trim), type);
    }

    public static final <T> T a(Object obj, Type type) {
        T a2;
        for (al next : f870a) {
            if (next.a(ap.a(type)) && (a2 = next.a(obj, type)) != null) {
                return a2;
            }
        }
        return null;
    }
}
