package com.xiaomi.miot.support.monitor.report;

import com.xiaomi.miot.support.monitor.exceptions.MiotMonitorBaseException;
import org.json.JSONObject;

public interface IReport {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1487a = "0";
    public static final String b = "1";
    public static final String c = "2";
    public static final String d = "monitor background request:";

    public enum Func_type {
        BLOCK,
        LEAK,
        ACTIVITY,
        APP_START,
        FPS,
        MEMEORY,
        NET
    }

    void a(Func_type func_type, String str, MiotMonitorBaseException miotMonitorBaseException);

    void a(Func_type func_type, String str, String str2, JSONObject jSONObject);

    void a(Func_type func_type, String str, JSONObject jSONObject);
}
