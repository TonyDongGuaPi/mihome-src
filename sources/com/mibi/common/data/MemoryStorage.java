package com.mibi.common.data;

import android.os.Parcel;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStorage {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7530a = "MEMORY_STORAGE_DEFAULT_GROUP";
    private ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> b = new ConcurrentHashMap<>();

    private MemoryStorage() {
    }

    public static MemoryStorage a() {
        return new MemoryStorage();
    }

    public MemoryStorage a(String str) {
        return a(f7530a, str);
    }

    public MemoryStorage a(String str, String str2) {
        ConcurrentHashMap concurrentHashMap = this.b.get(str);
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(str2);
        }
        return this;
    }

    public MemoryStorage b(String str) {
        this.b.remove(str);
        return this;
    }

    /* access modifiers changed from: package-private */
    public MemoryStorage a(MemoryStorage memoryStorage) {
        this.b = a(memoryStorage.b);
        return this;
    }

    public boolean c(String str) {
        return b(f7530a, str);
    }

    public boolean b(String str, String str2) {
        ConcurrentHashMap concurrentHashMap = this.b.get(str);
        return (concurrentHashMap == null || concurrentHashMap.get(str2) == null) ? false : true;
    }

    public MemoryStorage a(String str, Object obj) {
        return a(f7530a, str, obj);
    }

    public MemoryStorage a(String str, String str2, Object obj) {
        if (obj == null) {
            obj = "";
        }
        ConcurrentHashMap concurrentHashMap = this.b.get(str);
        if (concurrentHashMap == null) {
            concurrentHashMap = new ConcurrentHashMap();
            this.b.put(str, concurrentHashMap);
        }
        concurrentHashMap.put(str2, obj);
        return this;
    }

    private Object j(String str, String str2) {
        ConcurrentHashMap concurrentHashMap = this.b.get(str);
        if (concurrentHashMap == null) {
            return null;
        }
        return concurrentHashMap.get(str2);
    }

    public <T> T c(String str, String str2) {
        try {
            return j(str, str2);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public boolean d(String str) {
        return d(f7530a, str);
    }

    public boolean d(String str, String str2) {
        return a(str, str2, false);
    }

    public boolean a(String str, String str2, boolean z) {
        Object j = j(str, str2);
        if (j == null) {
            return z;
        }
        if (j.equals(Boolean.FALSE)) {
            return false;
        }
        boolean z2 = j instanceof String;
        if (z2 && ((String) j).equalsIgnoreCase("false")) {
            return false;
        }
        if (j.equals(Boolean.TRUE)) {
            return true;
        }
        if (!z2 || !((String) j).equalsIgnoreCase("true")) {
            return z;
        }
        return true;
    }

    public double e(String str) {
        return e(f7530a, str);
    }

    public double e(String str, String str2) {
        return a(str, str2, 0.0d);
    }

    public double a(String str, String str2, double d) {
        Object j = j(str, str2);
        if (j == null) {
            return d;
        }
        try {
            if (j instanceof Number) {
                return ((Number) j).doubleValue();
            }
            return Double.parseDouble((String) j);
        } catch (Exception unused) {
            return d;
        }
    }

    public long f(String str) {
        return f(f7530a, str);
    }

    public long f(String str, String str2) {
        return a(str, str2, 0);
    }

    public long a(String str, String str2, long j) {
        Object j2 = j(str, str2);
        if (j2 == null) {
            return j;
        }
        try {
            if (j2 instanceof Number) {
                return ((Number) j2).longValue();
            }
            return Long.parseLong((String) j2);
        } catch (Exception unused) {
            return j;
        }
    }

    public int g(String str) {
        return g(f7530a, str);
    }

    public int g(String str, String str2) {
        return a(str, str2, 0);
    }

    public int a(String str, String str2, int i) {
        Object j = j(str, str2);
        if (j == null) {
            return i;
        }
        try {
            if (j instanceof Number) {
                return ((Number) j).intValue();
            }
            return Integer.parseInt((String) j);
        } catch (Exception unused) {
            return i;
        }
    }

    public String h(String str) {
        return h(f7530a, str);
    }

    public String h(String str, String str2) {
        return a(str, str2, (String) null);
    }

    public String a(String str, String str2, String str3) {
        Object j = j(str, str2);
        return j == null ? str3 : j.toString();
    }

    public <T extends Serializable> T i(String str) {
        return i(f7530a, str);
    }

    public <T extends Serializable> T i(String str, String str2) {
        T j = j(str, str2);
        if (j == null) {
            return null;
        }
        try {
            return (Serializable) j;
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public void a(Parcel parcel) {
        parcel.writeSerializable(a(this.b));
    }

    public void b(Parcel parcel) {
        this.b = (ConcurrentHashMap) parcel.readSerializable();
    }

    private ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> a(ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> concurrentHashMap) {
        if (concurrentHashMap == null) {
            return null;
        }
        ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> concurrentHashMap2 = new ConcurrentHashMap<>();
        for (String next : concurrentHashMap.keySet()) {
            ConcurrentHashMap concurrentHashMap3 = concurrentHashMap.get(next);
            for (String str : concurrentHashMap3.keySet()) {
                Object obj = concurrentHashMap3.get(str);
                if (obj instanceof Serializable) {
                    ConcurrentHashMap concurrentHashMap4 = concurrentHashMap2.get(next);
                    if (concurrentHashMap4 == null) {
                        concurrentHashMap4 = new ConcurrentHashMap();
                        concurrentHashMap2.put(next, concurrentHashMap4);
                    }
                    concurrentHashMap4.put(str, obj);
                }
            }
        }
        return concurrentHashMap2;
    }
}
