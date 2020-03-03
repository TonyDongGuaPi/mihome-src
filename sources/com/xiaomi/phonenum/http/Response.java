package com.xiaomi.phonenum.http;

import java.util.List;
import java.util.Map;

public class Response {

    /* renamed from: a  reason: collision with root package name */
    public final int f12561a;
    public final String b;
    public final Map<String, List<String>> c;
    public final String d;
    public final String e;
    public long f;

    public Response(Builder builder) {
        this.f12561a = builder.b;
        this.b = builder.c;
        this.c = builder.f12562a;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
    }

    public String toString() {
        return "{code:" + this.f12561a + ", body:" + this.b + "}";
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        Map<String, List<String>> f12562a;
        int b;
        String c;
        String d;
        String e;
        long f = 0;

        public Builder() {
        }

        public Builder(Response response) {
            this.b = response.f12561a;
            this.c = response.b;
            this.f12562a = response.c;
            this.d = response.d;
            this.e = response.e;
            this.f = response.f;
        }

        public Builder a(Map<String, List<String>> map) {
            this.f12562a = map;
            return this;
        }

        public Builder a(String str) {
            this.c = str;
            return this;
        }

        public Builder a(int i) {
            this.b = i;
            return this;
        }

        public Builder b(String str) {
            this.d = str;
            return this;
        }

        public Builder c(String str) {
            this.e = str;
            return this;
        }

        public Builder a(long j) {
            this.f = j;
            return this;
        }

        public Response a() {
            return new Response(this);
        }
    }
}
