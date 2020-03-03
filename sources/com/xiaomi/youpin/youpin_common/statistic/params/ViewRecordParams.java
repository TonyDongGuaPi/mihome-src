package com.xiaomi.youpin.youpin_common.statistic.params;

import org.json.JSONObject;

public class ViewRecordParams {

    /* renamed from: a  reason: collision with root package name */
    public String f23819a;
    public String b;
    public String c;
    public String d;
    public JSONObject e;
    public int f;
    public String g;

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        ViewRecordParams f23820a = new ViewRecordParams();

        public Builder a(String str) {
            this.f23820a.f23819a = str;
            return this;
        }

        public Builder b(String str) {
            this.f23820a.b = str;
            return this;
        }

        public Builder c(String str) {
            this.f23820a.c = str;
            return this;
        }

        public Builder d(String str) {
            this.f23820a.d = str;
            return this;
        }

        public Builder a(JSONObject jSONObject) {
            this.f23820a.e = jSONObject;
            return this;
        }

        public Builder a(int i) {
            this.f23820a.f = i;
            return this;
        }

        public Builder e(String str) {
            this.f23820a.g = str;
            return this;
        }

        public ViewRecordParams a() {
            return this.f23820a;
        }
    }
}
