package com.xiaomi.smarthome.framework.plugin.rn.utils;

import com.facebook.react.bridge.ReadableMap;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils;

public class MIOTReadableMap {

    /* renamed from: a  reason: collision with root package name */
    private ReadableMap f17505a;

    public MIOTReadableMap(ReadableMap readableMap) {
        this.f17505a = readableMap;
    }

    public boolean a(String str, boolean z) {
        try {
            return this.f17505a.getBoolean(str);
        } catch (Exception unused) {
            return z;
        }
    }

    public double a(String str, double d) {
        try {
            return this.f17505a.getDouble(str);
        } catch (Exception unused) {
            return d;
        }
    }

    public int a(String str, int i) {
        try {
            return this.f17505a.getInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    public String a(String str, String str2) {
        String a2;
        if (this.f17505a == null || !this.f17505a.hasKey(str)) {
            return str2;
        }
        try {
            switch (this.f17505a.getType(str)) {
                case Map:
                    a2 = MIOTUtils.a(this.f17505a.getMap(str));
                    break;
                case Null:
                    return str2;
                case Array:
                    a2 = this.f17505a.getArray(str).toString();
                    break;
                case Number:
                    try {
                        return this.f17505a.getDouble(str) + "";
                    } catch (Exception e) {
                        RnPluginLog.b(e.toString());
                        a2 = this.f17505a.getInt(str) + "";
                        break;
                    }
                case String:
                    a2 = this.f17505a.getString(str);
                    break;
                case Boolean:
                    a2 = this.f17505a.getBoolean(str2) + "";
                    break;
                default:
                    return str2;
            }
            return a2;
        } catch (Exception e2) {
            RnPluginLog.b(e2.toString());
            return str2;
        }
    }
}
