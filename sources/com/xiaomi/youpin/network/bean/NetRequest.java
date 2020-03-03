package com.xiaomi.youpin.network.bean;

import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NetRequest {

    /* renamed from: a  reason: collision with root package name */
    private int f23656a;
    private int b;
    private String c;
    private boolean d;
    private List<KeyValuePair> e;
    private List<KeyValuePair> f;
    private String g;
    private boolean h;

    public NetRequest(Builder builder) {
        this.f23656a = builder.f23657a;
        this.c = builder.b;
        this.d = builder.c;
        this.e = builder.d;
        this.f = builder.e;
        this.b = builder.f;
        this.h = builder.g;
        this.g = builder.h;
    }

    public int a() {
        return this.f23656a;
    }

    public void a(int i) {
        this.f23656a = i;
    }

    public String b() {
        return this.c;
    }

    public List<KeyValuePair> c() {
        return this.e;
    }

    public List<KeyValuePair> d() {
        return this.f;
    }

    public int e() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public boolean f() {
        return this.h;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public String g() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public String toString() {
        return "NetRequest{mMethod=" + this.f23656a + ", mBusinessType=" + this.b + ", mPath='" + this.c + Operators.SINGLE_QUOTE + ", mHeaders=" + this.e + ", mQueryParams=" + this.f + ", mRequestBody='" + this.g + Operators.SINGLE_QUOTE + ", mIsHandleUnAuth=" + this.h + Operators.BLOCK_END;
    }

    public boolean h() {
        return this.d;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f23657a = 2;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public boolean c = true;
        /* access modifiers changed from: private */
        public List<KeyValuePair> d = new ArrayList(8);
        /* access modifiers changed from: private */
        public List<KeyValuePair> e = new ArrayList(8);
        /* access modifiers changed from: private */
        public int f = 1;
        /* access modifiers changed from: private */
        public boolean g = true;
        /* access modifiers changed from: private */
        public String h;

        public Builder a(int i) {
            this.f23657a = i;
            return this;
        }

        public Builder a(String str) {
            if (str != null) {
                this.b = str;
                return this;
            }
            throw new IllegalArgumentException("url == null");
        }

        public Builder a(Map<String, String> map) {
            if (map == null) {
                return this;
            }
            if (this.d == null) {
                this.d = new ArrayList();
            }
            for (String next : map.keySet()) {
                this.d.add(new KeyValuePair(next, map.get(next)));
            }
            return this;
        }

        public Builder a(String str, String str2) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            this.d.add(new KeyValuePair(str, str2));
            return this;
        }

        public Builder a(List<KeyValuePair> list) {
            if (list != null) {
                this.d = list;
                return this;
            }
            throw new IllegalArgumentException("headers == null");
        }

        public Builder b(List<KeyValuePair> list) {
            if (list != null) {
                this.e = list;
                return this;
            }
            throw new IllegalArgumentException("queries == null");
        }

        public Builder b(Map<String, String> map) {
            if (map == null) {
                return this;
            }
            if (this.e == null) {
                this.e = new ArrayList();
            }
            for (String next : map.keySet()) {
                this.e.add(new KeyValuePair(next, map.get(next)));
            }
            return this;
        }

        public Builder b(int i) {
            this.f = i;
            return this;
        }

        public Builder a(boolean z) {
            this.g = z;
            return this;
        }

        public Builder b(String str) {
            this.h = str;
            return this;
        }

        public Builder b(boolean z) {
            this.c = z;
            return this;
        }

        public NetRequest a() {
            return new NetRequest(this);
        }
    }
}
