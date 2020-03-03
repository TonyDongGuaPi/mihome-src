package miuipub.net.http;

import java.io.InputStream;
import java.util.Map;

public interface Cache {
    Entry a(String str);

    void a();

    void a(String str, boolean z);

    boolean a(String str, Entry entry);

    void b();

    void b(String str);

    public static class Entry {

        /* renamed from: a  reason: collision with root package name */
        public InputStream f2973a;
        public long b;
        public String c;
        public String d;
        public String e;
        public long f;
        public long g;
        public long h;
        public Map<String, String> i;

        public boolean a() {
            return this.g < System.currentTimeMillis();
        }

        public boolean b() {
            return this.h < System.currentTimeMillis();
        }
    }
}
