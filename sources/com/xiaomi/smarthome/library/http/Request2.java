package com.xiaomi.smarthome.library.http;

import android.text.TextUtils;
import com.xiaomi.smarthome.library.apache.http.NameValuePair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
public class Request2 {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19108a = "GET";
    public static final String b = "POST";
    private String c;
    private String d;
    private Map<String, String> e;
    private List<NameValuePair> f;

    public Request2(Builder builder) {
        this.c = builder.f19109a;
        this.d = builder.b;
        this.e = builder.c;
        this.f = builder.d;
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

    public List<NameValuePair> d() {
        return this.f;
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f19109a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public Map<String, String> c = new HashMap();
        /* access modifiers changed from: private */
        public List<NameValuePair> d = new ArrayList(8);

        public Builder a(String str) {
            if (str != null) {
                this.f19109a = str;
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

        public Builder a(Map<String, String> map) {
            if (map != null) {
                this.c = map;
                return this;
            }
            throw new IllegalArgumentException("headers == null");
        }

        public Builder a(List<NameValuePair> list) {
            if (list != null) {
                this.d = list;
                return this;
            }
            throw new IllegalArgumentException("queries == null");
        }

        public Request2 a() {
            return new Request2(this);
        }
    }
}
