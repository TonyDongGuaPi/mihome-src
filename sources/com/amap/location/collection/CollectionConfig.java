package com.amap.location.collection;

import android.text.TextUtils;
import com.amap.location.common.HeaderConfig;
import com.lidroid.xutils.bitmap.BitmapGlobalConfig;

public class CollectionConfig {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f4559a = false;
    private static final String b = "CollectionConfig";
    private String c;
    private boolean d = false;
    private final FpsCollectorConfig e = new FpsCollectorConfig();
    private final TrackCollectorConfig f = new TrackCollectorConfig();
    private final UploadConfig g = new UploadConfig();

    public static class FpsCollectorConfig {

        /* renamed from: a  reason: collision with root package name */
        private boolean f4560a = true;
        private boolean b = true;
        private boolean c = true;
        private boolean d = true;
        private int e = 20000;

        public void a(int i) {
            if (i < 3000) {
                i = 3000;
            }
            this.e = i;
        }

        public void a(boolean z) {
            this.f4560a = z;
        }

        public boolean a() {
            return this.f4560a;
        }

        public void b(boolean z) {
            this.b = z;
        }

        public boolean b() {
            return this.b;
        }

        public void c(boolean z) {
            this.c = z;
        }

        public boolean c() {
            return this.c;
        }

        public void d(boolean z) {
            this.d = z;
        }

        public boolean d() {
            return this.d;
        }

        public int e() {
            return this.e;
        }
    }

    public static class TrackCollectorConfig {

        /* renamed from: a  reason: collision with root package name */
        public volatile byte f4561a = -1;
        private boolean b = true;
        private boolean c = false;

        public byte a() {
            return this.f4561a;
        }

        public void a(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    this.f4561a = Byte.parseByte(str);
                } catch (Exception unused) {
                }
            }
        }

        public void a(boolean z) {
            this.b = z;
        }

        public void b(boolean z) {
            this.c = z;
        }

        public boolean b() {
            return this.b;
        }

        public boolean c() {
            return this.c;
        }
    }

    public static class UploadConfig {

        /* renamed from: a  reason: collision with root package name */
        private boolean f4562a = true;
        private boolean b = false;
        private int c = BitmapGlobalConfig.b;
        private int d = 307200;
        private int e = 5;

        public void a(int i) {
            if (i > 20971520) {
                i = 20971520;
            }
            this.c = i;
        }

        public void a(boolean z) {
            this.f4562a = z;
        }

        public boolean a() {
            return this.f4562a;
        }

        public void b(int i) {
            if (i > 614400) {
                i = 614400;
            }
            this.d = i;
        }

        public void b(boolean z) {
            this.b = z;
        }

        public boolean b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public void c(int i) {
            if (i > 5) {
                i = 5;
            } else if (i < 0) {
                i = 0;
            }
            this.e = i;
        }

        public int d() {
            return this.d;
        }

        public int e() {
            return this.e;
        }
    }

    public byte a() {
        return HeaderConfig.a();
    }

    public void a(byte b2) {
        HeaderConfig.a(b2);
    }

    public void a(String str) {
        HeaderConfig.a(str);
    }

    public void a(boolean z) {
        this.d = z;
    }

    public String b() {
        return HeaderConfig.b() == null ? "" : HeaderConfig.b();
    }

    public void b(String str) {
        HeaderConfig.b(str);
    }

    public String c() {
        return HeaderConfig.c() == null ? "" : HeaderConfig.c();
    }

    public void c(String str) {
        HeaderConfig.c(str);
    }

    public String d() {
        return HeaderConfig.d() == null ? "" : HeaderConfig.d();
    }

    public void d(String str) {
        this.c = str;
    }

    public String e() {
        return this.c == null ? "" : this.c;
    }

    public boolean f() {
        return this.d;
    }

    public FpsCollectorConfig g() {
        return this.e;
    }

    public TrackCollectorConfig h() {
        return this.f;
    }

    public UploadConfig i() {
        return this.g;
    }
}
