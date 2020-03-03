package com.xiaomi.youpin.youpin_common.statistic.params;

import java.util.Map;
import org.json.JSONArray;

public class RecordParams {

    /* renamed from: a  reason: collision with root package name */
    public String f23815a;
    public String b;
    public Map<String, Object> c;
    public JSONArray d;
    public String e;
    public String f;
    public Object g;
    public String h;
    public String i;
    public int j;

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        RecordParams f23816a = new RecordParams();

        public Builder a(String str) {
            this.f23816a.f23815a = str;
            return this;
        }

        public Builder b(String str) {
            this.f23816a.b = str;
            return this;
        }

        public Builder a(Map<String, Object> map) {
            this.f23816a.c = map;
            return this;
        }

        public Builder a(JSONArray jSONArray) {
            this.f23816a.d = jSONArray;
            return this;
        }

        public Builder c(String str) {
            this.f23816a.e = str;
            return this;
        }

        public Builder d(String str) {
            this.f23816a.f = str;
            return this;
        }

        public Builder a(Object obj) {
            this.f23816a.g = obj;
            return this;
        }

        public Builder e(String str) {
            this.f23816a.h = str;
            return this;
        }

        public Builder f(String str) {
            this.f23816a.i = str;
            return this;
        }

        public Builder a(int i) {
            this.f23816a.j = i;
            return this;
        }

        public RecordParams a() {
            return this.f23816a;
        }
    }
}
