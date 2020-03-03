package org.apache.commons.lang.text;

import java.util.Map;

public abstract class StrLookup {

    /* renamed from: a  reason: collision with root package name */
    private static final StrLookup f3403a = new MapStrLookup((Map) null);
    private static final StrLookup b;

    public abstract String a(String str);

    static {
        StrLookup strLookup;
        try {
            strLookup = new MapStrLookup(System.getProperties());
        } catch (SecurityException unused) {
            strLookup = f3403a;
        }
        b = strLookup;
    }

    public static StrLookup a() {
        return f3403a;
    }

    public static StrLookup b() {
        return b;
    }

    public static StrLookup a(Map map) {
        return new MapStrLookup(map);
    }

    protected StrLookup() {
    }

    static class MapStrLookup extends StrLookup {

        /* renamed from: a  reason: collision with root package name */
        private final Map f3404a;

        MapStrLookup(Map map) {
            this.f3404a = map;
        }

        public String a(String str) {
            Object obj;
            if (this.f3404a == null || (obj = this.f3404a.get(str)) == null) {
                return null;
            }
            return obj.toString();
        }
    }
}
