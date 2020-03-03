package com.mipay.common.data;

import java.io.Serializable;
import java.util.TreeMap;

public class h {
    protected TreeMap<String, Object> b;

    public h() {
        this.b = new TreeMap<>();
    }

    public h(String str, Object obj) {
        this();
        a(str, obj);
    }

    private Object i(String str) {
        return this.b.get(str);
    }

    public double a(String str, double d) {
        Object i = i(str);
        try {
            return i instanceof Number ? ((Number) i).doubleValue() : Double.parseDouble((String) i);
        } catch (Exception unused) {
            return d;
        }
    }

    public int a(String str, int i) {
        Object i2 = i(str);
        try {
            return i2 instanceof Number ? ((Number) i2).intValue() : Integer.parseInt((String) i2);
        } catch (Exception unused) {
            return i;
        }
    }

    public long a(String str, long j) {
        Object i = i(str);
        try {
            return i instanceof Number ? ((Number) i).longValue() : Long.parseLong((String) i);
        } catch (Exception unused) {
            return j;
        }
    }

    public h a(String str, Object obj) {
        if (obj == null) {
            obj = "";
        }
        this.b.put(str, obj);
        return this;
    }

    public <T> T a(String str) {
        try {
            return this.b.get(str);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public String a(String str, String str2) {
        Object i = i(str);
        return i == null ? str2 : i.toString();
    }

    public boolean a(String str, boolean z) {
        Object i = i(str);
        if (i.equals(Boolean.FALSE)) {
            return false;
        }
        boolean z2 = i instanceof String;
        if (z2 && ((String) i).equalsIgnoreCase("false")) {
            return false;
        }
        if (i.equals(Boolean.TRUE)) {
            return true;
        }
        if (!z2 || !((String) i).equalsIgnoreCase("true")) {
            return z;
        }
        return true;
    }

    public boolean b() {
        return this.b.isEmpty();
    }

    public boolean b(String str) {
        return a(str, false);
    }

    public double c(String str) {
        return a(str, 0.0d);
    }

    public long d(String str) {
        return a(str, 0);
    }

    public int e(String str) {
        return a(str, 0);
    }

    public String f(String str) {
        return a(str, (String) null);
    }

    public <T extends Serializable> T g(String str) {
        T i = i(str);
        if (i == null) {
            return null;
        }
        try {
            return (Serializable) i;
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public boolean h(String str) {
        return this.b.containsKey(str);
    }

    public String toString() {
        return this.b.toString();
    }
}
