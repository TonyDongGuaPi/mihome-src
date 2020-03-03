package org.apache.commons.cli;

public final class OptionBuilder {

    /* renamed from: a  reason: collision with root package name */
    private static String f3194a = null;
    private static String b = null;
    private static String c = null;
    private static boolean d = false;
    private static int e = -1;
    private static Object f;
    private static boolean g;
    private static char h;
    private static OptionBuilder i = new OptionBuilder();

    private OptionBuilder() {
    }

    private static void h() {
        b = null;
        c = "arg";
        f3194a = null;
        f = null;
        d = false;
        e = -1;
        g = false;
        h = 0;
    }

    public static OptionBuilder a(String str) {
        f3194a = str;
        return i;
    }

    public static OptionBuilder a() {
        e = 1;
        return i;
    }

    public static OptionBuilder a(boolean z) {
        e = z ? 1 : -1;
        return i;
    }

    public static OptionBuilder b(String str) {
        c = str;
        return i;
    }

    public static OptionBuilder b() {
        d = true;
        return i;
    }

    public static OptionBuilder a(char c2) {
        h = c2;
        return i;
    }

    public static OptionBuilder c() {
        h = '=';
        return i;
    }

    public static OptionBuilder b(boolean z) {
        d = z;
        return i;
    }

    public static OptionBuilder d() {
        e = -2;
        return i;
    }

    public static OptionBuilder a(int i2) {
        e = i2;
        return i;
    }

    public static OptionBuilder e() {
        e = 1;
        g = true;
        return i;
    }

    public static OptionBuilder f() {
        e = -2;
        g = true;
        return i;
    }

    public static OptionBuilder b(int i2) {
        e = i2;
        g = true;
        return i;
    }

    public static OptionBuilder a(Object obj) {
        f = obj;
        return i;
    }

    public static OptionBuilder c(String str) {
        b = str;
        return i;
    }

    public static Option b(char c2) throws IllegalArgumentException {
        return d(String.valueOf(c2));
    }

    public static Option g() throws IllegalArgumentException {
        if (f3194a != null) {
            return d((String) null);
        }
        h();
        throw new IllegalArgumentException("must specify longopt");
    }

    public static Option d(String str) throws IllegalArgumentException {
        try {
            Option option = new Option(str, b);
            option.setLongOpt(f3194a);
            option.setRequired(d);
            option.setOptionalArg(g);
            option.setArgs(e);
            option.setType(f);
            option.setValueSeparator(h);
            option.setArgName(c);
            return option;
        } finally {
            h();
        }
    }
}
