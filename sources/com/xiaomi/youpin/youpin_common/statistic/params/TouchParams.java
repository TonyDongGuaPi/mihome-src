package com.xiaomi.youpin.youpin_common.statistic.params;

import org.json.JSONObject;

public class TouchParams {

    /* renamed from: a  reason: collision with root package name */
    public String f23817a;
    public String b;
    public String c;
    public String d;
    public JSONObject e;
    public String f;
    public String g;

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        TouchParams f23818a = new TouchParams();

        public Builder a(String str) {
            this.f23818a.f23817a = str;
            return this;
        }

        public Builder b(String str) {
            this.f23818a.b = str;
            return this;
        }

        public Builder c(String str) {
            this.f23818a.c = str;
            return this;
        }

        public Builder d(String str) {
            this.f23818a.d = str;
            return this;
        }

        public Builder a(JSONObject jSONObject) {
            this.f23818a.e = jSONObject;
            return this;
        }

        public Builder e(String str) {
            this.f23818a.f = str;
            return this;
        }

        public Builder f(String str) {
            this.f23818a.g = str;
            return this;
        }

        public TouchParams a() {
            return this.f23818a;
        }
    }
}
