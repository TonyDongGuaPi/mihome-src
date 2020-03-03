package org.slf4j;

import com.taobao.weex.el.parse.Operators;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticMarkerBinder;

public class MarkerFactory {

    /* renamed from: a  reason: collision with root package name */
    static IMarkerFactory f4172a;

    private MarkerFactory() {
    }

    static {
        try {
            f4172a = StaticMarkerBinder.f4179a.a();
        } catch (Exception e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not instantiate instance of class [");
            stringBuffer.append(StaticMarkerBinder.f4179a.b());
            stringBuffer.append(Operators.ARRAY_END_STR);
            Util.a(stringBuffer.toString(), e);
        }
    }

    public static Marker a(String str) {
        return f4172a.a(str);
    }

    public static Marker b(String str) {
        return f4172a.d(str);
    }

    public static IMarkerFactory a() {
        return f4172a;
    }
}
