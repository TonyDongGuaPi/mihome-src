package com.xiaomi.smarthome.library.http;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;

public class Request {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19106a = "GET";
    public static final String b = "POST";
    private String c;
    private String d;
    private Map<String, String> e;
    private List<KeyValuePair> f;
    private RequestBody g;

    public Request(Builder builder) {
        this.c = builder.f19107a;
        this.d = builder.b;
        this.e = builder.d;
        this.f = builder.e;
        this.g = builder.c;
    }

    public String a() {
        return this.d;
    }

    public Map<String, String> b() {
        return this.e;
    }

    public String c() {
        if (!TextUtils.isEmpty(this.c)) {
            return this.c;
        }
        throw new IllegalArgumentException("method == null");
    }

    public List<KeyValuePair> d() {
        return this.f;
    }

    public RequestBody e() {
        return this.g;
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f19107a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public RequestBody c;
        /* access modifiers changed from: private */
        public Map<String, String> d = new HashMap();
        /* access modifiers changed from: private */
        public List<KeyValuePair> e = new ArrayList(8);

        public Builder a(String str) {
            if (str != null) {
                this.f19107a = str;
                return this;
            }
            throw new IllegalArgumentException("method == null");
        }

        public Builder b(String str) {
            if (str != null) {
                this.b = str;
                return this;
            }
            throw new IllegalArgumentException("url == null");
        }

        public Builder a(RequestBody requestBody) {
            if (requestBody != null) {
                this.c = requestBody;
                return this;
            }
            throw new IllegalArgumentException("body == null");
        }

        public Builder a(Map<String, String> map) {
            if (map != null) {
                this.d = map;
                return this;
            }
            throw new IllegalArgumentException("headers == null");
        }

        public Builder a(List<KeyValuePair> list) {
            if (list != null) {
                this.e = list;
                return this;
            }
            throw new IllegalArgumentException("queries == null");
        }

        public Builder b(Map<String, String> map) {
            if (map != null) {
                this.e = new ArrayList();
                for (String next : map.keySet()) {
                    this.e.add(new KeyValuePair(next, map.get(next)));
                }
                return this;
            }
            throw new IllegalArgumentException("queries == null");
        }

        public Request a() {
            return new Request(this);
        }
    }
}
