package org.slf4j;

import com.taobao.weex.el.parse.Operators;
import java.util.Map;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticMDCBinder;
import org.slf4j.spi.MDCAdapter;

public class MDC {

    /* renamed from: a  reason: collision with root package name */
    static final String f4171a = "http://www.slf4j.org/codes.html#null_MDCA";
    static final String b = "http://www.slf4j.org/codes.html#no_static_mdc_binder";
    static MDCAdapter c;

    private MDC() {
    }

    static {
        try {
            c = StaticMDCBinder.f4178a.a();
        } catch (NoClassDefFoundError e) {
            String message = e.getMessage();
            if (!(message == null || message.indexOf("org/slf4j/impl/StaticMDCBinder") == -1)) {
                Util.a("Failed to load class \"org.slf4j.impl.StaticMDCBinder\".");
                Util.a("See http://www.slf4j.org/codes.html#no_static_mdc_binder for further details.");
            }
            throw e;
        } catch (Exception e2) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not bind with an instance of class [");
            stringBuffer.append(StaticMDCBinder.f4178a.b());
            stringBuffer.append(Operators.ARRAY_END_STR);
            Util.a(stringBuffer.toString(), e2);
        }
    }

    public static void a(String str, String str2) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("key parameter cannot be null");
        } else if (c != null) {
            c.a(str, str2);
        } else {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
    }

    public static String a(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("key parameter cannot be null");
        } else if (c != null) {
            return c.a(str);
        } else {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
    }

    public static void b(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("key parameter cannot be null");
        } else if (c != null) {
            c.b(str);
        } else {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
    }

    public static void a() {
        if (c != null) {
            c.a();
            return;
        }
        throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
    }

    public static Map b() {
        if (c != null) {
            return c.c();
        }
        throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
    }

    public static void a(Map map) {
        if (c != null) {
            c.a(map);
            return;
        }
        throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
    }

    public static MDCAdapter c() {
        return c;
    }
}
