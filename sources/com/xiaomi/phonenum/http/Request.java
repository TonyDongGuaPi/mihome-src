package com.xiaomi.phonenum.http;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.xiaomi.phonenum.utils.MapUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Request {

    /* renamed from: a  reason: collision with root package name */
    public final String f12559a;
    public final URI b;
    public final Map<String, String> c;
    public final Map<String, String> d;
    public final boolean e;

    private Request(Builder builder) {
        this.b = builder.f12560a;
        this.f12559a = builder.f12560a.toString();
        this.c = builder.b;
        this.d = builder.c;
        this.e = builder.d;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        URI f12560a;
        Map<String, String> b;
        Map<String, String> c;
        boolean d = true;

        public Builder a(String str) {
            try {
                this.f12560a = new URI(str);
                return this;
            } catch (URISyntaxException unused) {
                throw new IllegalArgumentException("unexpected url: " + str);
            }
        }

        public Builder b(@Nullable String str) {
            String str2;
            if (TextUtils.isEmpty(str)) {
                return this;
            }
            URI uri = this.f12560a;
            String query = uri.getQuery();
            if (query == null) {
                str2 = str;
            } else {
                str2 = query + a.b + str;
            }
            try {
                this.f12560a = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), str2, uri.getFragment());
                return this;
            } catch (URISyntaxException unused) {
                throw new IllegalArgumentException("unexpected newQuery: " + str);
            }
        }

        public Builder a(@Nullable Map<String, String> map) {
            b(MapUtil.a(map));
            return this;
        }

        public Builder c(@Nullable String str) {
            if (this.b == null) {
                this.b = new HashMap();
            }
            this.b.put("User-Agent", str);
            return this;
        }

        public Builder b(@Nullable Map<String, String> map) {
            this.b = map;
            return this;
        }

        public Builder c(@Nullable Map<String, String> map) {
            this.c = map;
            return this;
        }

        public Builder a(boolean z) {
            this.d = z;
            return this;
        }

        public Request a() {
            return new Request(this);
        }
    }
}
