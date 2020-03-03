package com.amap.location.common.model;

import java.util.Hashtable;

public class Adjacent {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4580a = "btm";
    public static final String b = "top";
    public static final String c = "left";
    public static final String d = "right";
    public static final String e = "even";
    public static final String f = "odd";
    public static final String g = "topleft";
    public static final String h = "topright";
    public static final String i = "btmleft";
    public static final String j = "btmright";

    public static class Borders {

        /* renamed from: a  reason: collision with root package name */
        public static final Hashtable<String, Hashtable<String, String>> f4581a = new Hashtable<>();
        private static final String b = "028b";
        private static final String c = "0145hjnp";
        private static final String d = "prxz";
        private static final String e = "bcfguvyz";

        static {
            Hashtable hashtable = new Hashtable();
            hashtable.put(Adjacent.e, e);
            hashtable.put(Adjacent.f, d);
            Hashtable hashtable2 = new Hashtable();
            hashtable2.put(Adjacent.e, c);
            hashtable2.put(Adjacent.f, b);
            Hashtable hashtable3 = new Hashtable();
            hashtable3.put(Adjacent.e, d);
            hashtable3.put(Adjacent.f, e);
            Hashtable hashtable4 = new Hashtable();
            hashtable4.put(Adjacent.e, b);
            hashtable4.put(Adjacent.f, c);
            f4581a.put("top", hashtable);
            f4581a.put(Adjacent.f4580a, hashtable2);
            f4581a.put("right", hashtable3);
            f4581a.put("left", hashtable4);
        }
    }

    public static class Neighbors {

        /* renamed from: a  reason: collision with root package name */
        public static final Hashtable<String, Hashtable<String, String>> f4582a = new Hashtable<>();
        private static final String b = "14365h7k9dcfesgujnmqp0r2twvyx8zb";
        private static final String c = "p0r21436x8zb9dcf5h7kjnmqesgutwvy";
        private static final String d = "238967debc01fg45kmstqrwxuvhjyznp";
        private static final String e = "bc01fg45238967deuvhjyznpkmstqrwx";

        static {
            Hashtable hashtable = new Hashtable();
            hashtable.put(Adjacent.e, e);
            hashtable.put(Adjacent.f, c);
            Hashtable hashtable2 = new Hashtable();
            hashtable2.put(Adjacent.e, d);
            hashtable2.put(Adjacent.f, b);
            Hashtable hashtable3 = new Hashtable();
            hashtable3.put(Adjacent.e, c);
            hashtable3.put(Adjacent.f, e);
            Hashtable hashtable4 = new Hashtable();
            hashtable4.put(Adjacent.e, b);
            hashtable4.put(Adjacent.f, d);
            f4582a.put("top", hashtable);
            f4582a.put(Adjacent.f4580a, hashtable2);
            f4582a.put("right", hashtable3);
            f4582a.put("left", hashtable4);
        }
    }
}
