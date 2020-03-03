package org.apache.commons.cli;

public class PatternOptionBuilder {

    /* renamed from: a  reason: collision with root package name */
    public static final Class f3196a;
    public static final Class b;
    public static final Class c;
    public static final Class d;
    public static final Class e;
    public static final Class f;
    public static final Class g;
    public static final Class h;
    public static final Class i;
    static Class j;
    static Class k;
    static Class l;
    static Class m;
    static Class n;
    static Class o;
    static Class p;
    static Class q;
    static Class r;

    public static boolean b(char c2) {
        return c2 == '@' || c2 == ':' || c2 == '%' || c2 == '+' || c2 == '#' || c2 == '<' || c2 == '>' || c2 == '*' || c2 == '/' || c2 == '!';
    }

    static {
        Class cls;
        Class cls2;
        Class cls3;
        Class cls4;
        Class cls5;
        Class cls6;
        Class cls7;
        Class cls8;
        Class cls9;
        if (j == null) {
            cls = b("java.lang.String");
            j = cls;
        } else {
            cls = j;
        }
        f3196a = cls;
        if (k == null) {
            cls2 = b("java.lang.Object");
            k = cls2;
        } else {
            cls2 = k;
        }
        b = cls2;
        if (l == null) {
            cls3 = b("java.lang.Number");
            l = cls3;
        } else {
            cls3 = l;
        }
        c = cls3;
        if (m == null) {
            cls4 = b("java.util.Date");
            m = cls4;
        } else {
            cls4 = m;
        }
        d = cls4;
        if (n == null) {
            cls5 = b("java.lang.Class");
            n = cls5;
        } else {
            cls5 = n;
        }
        e = cls5;
        if (o == null) {
            cls6 = b("java.io.FileInputStream");
            o = cls6;
        } else {
            cls6 = o;
        }
        f = cls6;
        if (p == null) {
            cls7 = b("java.io.File");
            p = cls7;
        } else {
            cls7 = p;
        }
        g = cls7;
        if (q == null) {
            cls8 = b("[Ljava.io.File;");
            q = cls8;
        } else {
            cls8 = q;
        }
        h = cls8;
        if (r == null) {
            cls9 = b("java.net.URL");
            r = cls9;
        } else {
            cls9 = r;
        }
        i = cls9;
    }

    static Class b(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError().initCause(e2);
        }
    }

    public static Object a(char c2) {
        if (c2 == '#') {
            return d;
        }
        if (c2 == '%') {
            return c;
        }
        if (c2 == '/') {
            return i;
        }
        if (c2 == ':') {
            return f3196a;
        }
        if (c2 == '<') {
            return f;
        }
        if (c2 == '>') {
            return g;
        }
        if (c2 == '@') {
            return b;
        }
        switch (c2) {
            case '*':
                return h;
            case '+':
                return e;
            default:
                return null;
        }
    }

    public static Options a(String str) {
        Options options = new Options();
        boolean z = false;
        Object obj = null;
        int i2 = 0;
        char c2 = ' ';
        boolean z2 = false;
        while (true) {
            boolean z3 = true;
            if (i2 >= str.length()) {
                break;
            }
            char charAt = str.charAt(i2);
            if (!b(charAt)) {
                if (c2 != ' ') {
                    if (obj == null) {
                        z3 = false;
                    }
                    OptionBuilder.a(z3);
                    OptionBuilder.b(z2);
                    OptionBuilder.a(obj);
                    options.addOption(OptionBuilder.b(c2));
                    obj = null;
                    z2 = false;
                }
                c2 = charAt;
            } else if (charAt == '!') {
                z2 = true;
            } else {
                obj = a(charAt);
            }
            i2++;
        }
        if (c2 != ' ') {
            if (obj != null) {
                z = true;
            }
            OptionBuilder.a(z);
            OptionBuilder.b(z2);
            OptionBuilder.a(obj);
            options.addOption(OptionBuilder.b(c2));
        }
        return options;
    }
}
