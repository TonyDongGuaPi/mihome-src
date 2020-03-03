package org.slf4j;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import org.slf4j.helpers.SubstituteLoggerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticLoggerBinder;

public final class LoggerFactory {

    /* renamed from: a  reason: collision with root package name */
    static final String f4170a = "http://www.slf4j.org/codes.html#StaticLoggerBinder";
    static final String b = "http://www.slf4j.org/codes.html#multiple_bindings";
    static final String c = "http://www.slf4j.org/codes.html#null_LF";
    static final String d = "http://www.slf4j.org/codes.html#version_mismatch";
    static final String e = "http://www.slf4j.org/codes.html#substituteLogger";
    static final String f = "http://www.slf4j.org/codes.html#unsuccessfulInit";
    static final String g = "org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit";
    static final int h = 0;
    static final int i = 1;
    static final int j = 2;
    static final int k = 3;
    static final int l = 1;
    static final int m = 2;
    static int n;
    static int o;
    static SubstituteLoggerFactory p = new SubstituteLoggerFactory();
    static Class q;
    private static final String[] r = {"1.5.5", "1.5.6", "1.5.7", "1.5.8", "1.5.9", "1.5.10", "1.5.11"};
    private static String s = "org/slf4j/impl/StaticLoggerBinder.class";

    private LoggerFactory() {
    }

    static void a() {
        n = 0;
        o = 0;
        p = new SubstituteLoggerFactory();
    }

    private static final void c() {
        d();
        f();
        g();
    }

    private static final void d() {
        try {
            h();
            n = 3;
            e();
        } catch (NoClassDefFoundError e2) {
            n = 2;
            String message = e2.getMessage();
            if (!(message == null || message.indexOf("org/slf4j/impl/StaticLoggerBinder") == -1)) {
                Util.a("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
                Util.a("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
            }
            throw e2;
        } catch (Exception e3) {
            n = 2;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Failed to instantiate logger [");
            stringBuffer.append(h().getLoggerFactoryClassStr());
            stringBuffer.append(Operators.ARRAY_END_STR);
            Util.a(stringBuffer.toString(), e3);
        }
    }

    private static final void e() {
        List a2 = p.a();
        if (a2.size() != 0) {
            Util.a("The following loggers will not work becasue they were created");
            Util.a("during the default configuration phase of the underlying logging system.");
            Util.a("See also http://www.slf4j.org/codes.html#substituteLogger");
            for (int i2 = 0; i2 < a2.size(); i2++) {
                Util.a((String) a2.get(i2));
            }
        }
    }

    private static final void f() {
        try {
            String str = StaticLoggerBinder.REQUESTED_API_VERSION;
            boolean z = false;
            for (String startsWith : r) {
                if (str.startsWith(startsWith)) {
                    z = true;
                }
            }
            if (!z) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("The requested version ");
                stringBuffer.append(str);
                stringBuffer.append(" by your slf4j binding is not compatible with ");
                stringBuffer.append(Arrays.asList(r).toString());
                Util.a(stringBuffer.toString());
                Util.a("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
            }
        } catch (NoSuchFieldError unused) {
        } catch (Throwable th) {
            Util.a("Unexpected problem occured during version sanity check", th);
        }
    }

    static Class b(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(e2.getMessage());
        }
    }

    private static void g() {
        Class cls;
        try {
            if (q == null) {
                cls = b("org.slf4j.LoggerFactory");
                q = cls;
            } else {
                cls = q;
            }
            ClassLoader classLoader = cls.getClassLoader();
            if (classLoader != null) {
                Enumeration<URL> resources = classLoader.getResources(s);
                ArrayList arrayList = new ArrayList();
                while (resources.hasMoreElements()) {
                    arrayList.add(resources.nextElement());
                }
                if (arrayList.size() > 1) {
                    Util.a("Class path contains multiple SLF4J bindings.");
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Found binding in [");
                        stringBuffer.append(arrayList.get(i2));
                        stringBuffer.append(Operators.ARRAY_END_STR);
                        Util.a(stringBuffer.toString());
                    }
                    Util.a("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
                }
            }
        } catch (IOException e2) {
            Util.a("Error getting resources from path", e2);
        }
    }

    private static final StaticLoggerBinder h() {
        if (o == 1) {
            return StaticLoggerBinder.SINGLETON;
        }
        if (o == 2) {
            return StaticLoggerBinder.getSingleton();
        }
        try {
            StaticLoggerBinder singleton = StaticLoggerBinder.getSingleton();
            o = 2;
            return singleton;
        } catch (NoSuchMethodError unused) {
            o = 1;
            return StaticLoggerBinder.SINGLETON;
        }
    }

    public static Logger a(String str) {
        return b().getLogger(str);
    }

    public static Logger a(Class cls) {
        return a(cls.getName());
    }

    public static ILoggerFactory b() {
        if (n == 0) {
            n = 1;
            c();
        }
        switch (n) {
            case 1:
                return p;
            case 2:
                throw new IllegalStateException(g);
            case 3:
                return h().getLoggerFactory();
            default:
                throw new IllegalStateException("Unreachable code");
        }
    }
}
