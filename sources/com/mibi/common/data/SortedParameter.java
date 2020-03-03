package com.mibi.common.data;

import java.io.Serializable;
import java.util.TreeMap;

public class SortedParameter {
    protected TreeMap<String, Object> c;

    public SortedParameter() {
        this.c = new TreeMap<>();
    }

    public SortedParameter(String str, Object obj) {
        this();
        a(str, obj);
    }

    public SortedParameter a(String str, Object obj) {
        if (obj == null) {
            obj = "";
        }
        this.c.put(str, obj);
        return this;
    }

    public SortedParameter a(SortedParameter sortedParameter) {
        if (sortedParameter != null) {
            this.c.putAll(sortedParameter.c);
        }
        return this;
    }

    private Object i(String str) {
        return this.c.get(str);
    }

    public TreeMap<String, Object> a() {
        return this.c;
    }

    public <T> T a(String str) {
        try {
            return this.c.get(str);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public boolean b(String str) {
        return a(str, false);
    }

    public boolean a(String str, boolean z) {
        Object i = i(str);
        if (i == null) {
            return z;
        }
        try {
            if (i.equals(Boolean.FALSE)) {
                return false;
            }
            if ((i instanceof String) && ((String) i).equalsIgnoreCase("false")) {
                return false;
            }
            if (i.equals(Boolean.TRUE)) {
                return true;
            }
            if (!(i instanceof String) || !((String) i).equalsIgnoreCase("true")) {
                return z;
            }
            return true;
        } catch (Exception unused) {
            return z;
        }
    }

    public double c(String str) {
        return a(str, 0.0d);
    }

    public double a(String str, double d) {
        Object i = i(str);
        try {
            if (i instanceof Number) {
                return ((Number) i).doubleValue();
            }
            return Double.parseDouble((String) i);
        } catch (Exception unused) {
            return d;
        }
    }

    public long d(String str) {
        return a(str, 0);
    }

    public long a(String str, long j) {
        Object i = i(str);
        try {
            if (i instanceof Number) {
                return ((Number) i).longValue();
            }
            return Long.parseLong((String) i);
        } catch (Exception unused) {
            return j;
        }
    }

    public int e(String str) {
        return a(str, 0);
    }

    public int a(String str, int i) {
        Object i2 = i(str);
        try {
            if (i2 instanceof Number) {
                return ((Number) i2).intValue();
            }
            return Integer.parseInt((String) i2);
        } catch (Exception unused) {
            return i;
        }
    }

    public String f(String str) {
        return a(str, (String) null);
    }

    public String a(String str, String str2) {
        Object i = i(str);
        return i == null ? str2 : i.toString();
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
        return this.c.containsKey(str);
    }

    public boolean b() {
        return this.c.isEmpty();
    }

    public String toString() {
        return this.c.toString();
    }
}
