package com.xiaomi.smarthome.core.server.internal.device.api;

import com.xiaomi.smarthome.frame.JsonParser;
import org.json.JSONObject;

public interface DeviceListParser<T> extends JsonParser<T> {
    String a();

    boolean b();

    public static abstract class DeviceListParserBase<T> implements DeviceListParser<T> {

        /* renamed from: a  reason: collision with root package name */
        private String f14564a = "";
        private boolean b;

        public String a() {
            return this.f14564a;
        }

        public boolean b() {
            return this.b;
        }

        public void a(String str) {
            if (str != null && this.f14564a.compareTo(str) < 0) {
                this.f14564a = str;
            }
        }

        public void a(JSONObject jSONObject) {
            this.b = jSONObject.optBoolean("has_more");
        }
    }
}
