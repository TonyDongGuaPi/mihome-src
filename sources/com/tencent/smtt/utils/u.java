package com.tencent.smtt.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class u {

    /* renamed from: a  reason: collision with root package name */
    private b f9217a = null;
    private b b = null;

    class a {
        private String b;
        private long c;
        private long d;

        a(String str, long j, long j2) {
            this.b = str;
            this.c = j;
            this.d = j2;
        }

        /* access modifiers changed from: package-private */
        public long a() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public long b() {
            return this.d;
        }
    }

    class b {
        private Map<String, a> b = new HashMap();

        b(File file) {
            this.b.clear();
            a(file);
        }

        private void a(File file) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                for (File a2 : listFiles) {
                    a(a2);
                }
            } else if (file.isFile()) {
                a(file.getName(), file.length(), file.lastModified());
            }
        }

        private void a(String str, long j, long j2) {
            if (str != null && str.length() > 0 && j > 0 && j2 > 0) {
                a aVar = new a(str, j, j2);
                if (!this.b.containsKey(str)) {
                    this.b.put(str, aVar);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public Map<String, a> a() {
            return this.b;
        }
    }

    private boolean a(b bVar, b bVar2) {
        if (bVar == null || bVar.a() == null || bVar2 == null || bVar2.a() == null) {
            return false;
        }
        Map<String, a> a2 = bVar.a();
        Map<String, a> a3 = bVar2.a();
        for (Map.Entry next : a2.entrySet()) {
            String str = (String) next.getKey();
            a aVar = (a) next.getValue();
            if (a3.containsKey(str)) {
                a aVar2 = a3.get(str);
                if (aVar.a() == aVar2.a()) {
                    if (aVar.b() != aVar2.b()) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public void a(File file) {
        this.f9217a = new b(file);
    }

    public boolean a() {
        return this.b != null && this.f9217a != null && this.b.a().size() == this.f9217a.a().size() && a(this.f9217a, this.b);
    }

    public void b(File file) {
        this.b = new b(file);
    }
}
