package com.xiaomi.infra.galaxy.fds.android.model;

import com.xiaomi.infra.galaxy.fds.android.util.Util;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.http.Header;

public class ObjectMetadata {

    /* renamed from: a  reason: collision with root package name */
    private static final Set<String> f10140a = new HashSet();
    private final Map<String, String> b = new HashMap();
    private final Map<String, String> c = new HashMap();

    static {
        f10140a.add("Last-Modified");
        f10140a.add("Content-MD5");
        f10140a.add("Content-Type");
        f10140a.add("Content-Length");
        f10140a.add("Content-Encoding");
        f10140a.add("Cache-Control");
    }

    public static ObjectMetadata a(Header[] headerArr) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        for (Header header : headerArr) {
            String name = header.getName();
            String value = header.getValue();
            if (name.startsWith("x-xiaomi-meta-")) {
                objectMetadata.a(name, value);
            } else if (f10140a.contains(name)) {
                objectMetadata.b(name, value);
            }
        }
        return objectMetadata;
    }

    public Map<String, String> a() {
        return this.b;
    }

    public void a(String str, String str2) {
        e(str);
        this.b.put(str, str2);
    }

    public long b() {
        String str = this.c.get("Content-Length");
        if (str != null) {
            return Long.parseLong(str);
        }
        return -1;
    }

    public void a(long j) {
        this.c.put("Content-Length", Long.toString(j));
    }

    public String c() {
        return this.c.get("Content-MD5");
    }

    public void a(String str) {
        this.c.put("Content-MD5", str);
    }

    public String d() {
        return this.c.get("Content-Type");
    }

    public void b(String str) {
        this.c.put("Content-Type", str);
    }

    public String e() {
        return this.c.get("Content-Encoding");
    }

    public void c(String str) {
        this.c.put("Content-Encoding", str);
    }

    public String f() {
        return this.c.get("Cache-Control");
    }

    public void d(String str) {
        this.c.put("Cache-Control", str);
    }

    public Date g() {
        String str = this.c.get("Last-Modified");
        if (str == null) {
            return null;
        }
        try {
            return Util.a(str);
        } catch (ParseException unused) {
            return null;
        }
    }

    public void a(Date date) {
        this.c.put("Last-Modified", Util.a(date));
    }

    public void b(String str, String str2) {
        e(str);
        this.c.put(str, str2);
    }

    public Map<String, String> h() {
        HashMap hashMap = new HashMap(this.c);
        hashMap.putAll(this.b);
        return hashMap;
    }

    private void e(String str) {
        boolean startsWith = str.startsWith("x-xiaomi-meta-");
        if (!startsWith) {
            Iterator<String> it = f10140a.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (str.equals(it.next())) {
                        startsWith = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (!startsWith) {
            throw new RuntimeException("Invalid metadata: " + str, (Throwable) null);
        }
    }
}
