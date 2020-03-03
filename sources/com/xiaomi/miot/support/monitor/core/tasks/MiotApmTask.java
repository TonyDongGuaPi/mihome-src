package com.xiaomi.miot.support.monitor.core.tasks;

import java.util.HashMap;
import java.util.Map;

public class MiotApmTask {
    public static final int A = 65536;
    public static final int B = 131072;
    public static final int C = 524288;
    public static final int D = 1048576;
    public static final int E = 4194304;
    public static final int F = 8388608;
    public static final String[] G = {"fps", d, "activity", "net", f, g, i, "func", j, "webview", k};
    private static Map<String, Integer> H = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f11474a = "argus_apm_sdk_config.json";
    public static final String b = "activity";
    public static final String c = "net";
    public static final String d = "memory";
    public static final String e = "fps";
    public static final String f = "appstart";
    public static final String g = "fileinfo";
    public static final String h = "anr";
    public static final String i = "processinfo";
    public static final String j = "block";
    public static final String k = "watchdog";
    public static final String l = "func";
    public static final String m = "webview";
    public static final String n = "leak";
    public static final int o = 1;
    public static final int p = 2;
    public static final int q = 4;
    public static final int r = 8;
    public static final int s = 32;
    public static final int t = 256;
    public static final int u = 512;
    public static final int v = 1024;
    public static final int w = 4096;
    public static final int x = 8192;
    public static final int y = 16384;
    public static final int z = 32768;

    public static Map<String, Integer> a() {
        if (H == null) {
            H = new HashMap();
            H.put("activity", 512);
            H.put(f, 256);
            H.put("fps", 32);
            H.put(d, 1024);
            H.put("net", 4096);
            H.put(g, 8192);
            H.put(h, 16384);
            H.put(i, 131072);
            H.put("func", 524288);
            H.put(j, 1048576);
            H.put("webview", 4194304);
            H.put(k, 8388608);
        }
        return H;
    }
}
