package com.xiaomi.smarthome.core.server.internal.device.api;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class RpcErrorDescription {
    private static ArrayList<RpcErrorDescription> c = new ArrayList<>();
    private static Map<String, Long> d = new ConcurrentHashMap();
    private static List<String> e = new ArrayList();
    private static List<String> f = new ArrayList();

    /* renamed from: a  reason: collision with root package name */
    private int f14574a;
    private String b;

    public static int a(int i, String str) {
        if (i == -2000 || i == -2001 || i == -2002 || i == -2003 || i == -30001 || i == -2 || i == -10) {
            return 0;
        }
        return i;
    }

    static {
        c.add(new RpcErrorDescription(-99990000, "ip is empty"));
        c.add(new RpcErrorDescription(-99991000, "Account type not supported!"));
        c.add(new RpcErrorDescription(-99991001, "netRequest is null"));
        c.add(new RpcErrorDescription(-99991002, "not loggedin"));
        c.add(new RpcErrorDescription(-99991003, "uid or serviceToken is null!"));
        c.add(new RpcErrorDescription(-99991004, "pair == null"));
        c.add(new RpcErrorDescription(-99991005, "request == null"));
        c.add(new RpcErrorDescription(-99991006, "net request failure"));
        c.add(new RpcErrorDescription(-99991007, "Unable to resolve host"));
        c.add(new RpcErrorDescription(-99992000, "unknown error"));
        e.add("lumi.acpartner.v2");
        e.add("lumi.gateway.v3");
        e.add("lumi.plug.v1");
        e.add("lumi.acpartner.v3");
        e.add("lumi.acpartner.v1");
        e.add("lumi.ctrl_86plug.v1");
        e.add("lumi.ctrl_86plug.aq1");
        e.add("lumi.curtain.v1");
        e.add("isa.camera.df3");
        e.add("isa.camera.qf3");
        e.add("isa.camera.isc5");
        e.add("isa.camera.isc5c1");
        e.add("isa.camera.hl5");
        e.add("nwt.derh.wdh318efw1");
    }

    public RpcErrorDescription(int i, String str) {
        this.f14574a = i;
        this.b = str;
    }

    public int a() {
        return this.f14574a;
    }

    public void a(int i) {
        this.f14574a = i;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public static int b(String str) {
        for (int i = 0; i < c.size(); i++) {
            if (!TextUtils.isEmpty(str) && str.startsWith(c.get(i).b())) {
                return c.get(i).a();
            }
        }
        return -99992000;
    }

    public static int c(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return new JSONObject(str).optInt("code");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00e1, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.Class<com.xiaomi.smarthome.core.server.internal.device.api.RpcErrorDescription> r0 = com.xiaomi.smarthome.core.server.internal.device.api.RpcErrorDescription.class
            monitor-enter(r0)
            com.xiaomi.smarthome.core.server.internal.device.DeviceManager r1 = com.xiaomi.smarthome.core.server.internal.device.DeviceManager.a()     // Catch:{ all -> 0x00e2 }
            com.xiaomi.smarthome.core.entity.device.Device r1 = r1.a((java.lang.String) r5)     // Catch:{ all -> 0x00e2 }
            r2 = 1
            if (r1 == 0) goto L_0x00e0
            java.util.List<java.lang.String> r3 = e     // Catch:{ all -> 0x00e2 }
            java.lang.String r1 = r1.l()     // Catch:{ all -> 0x00e2 }
            boolean r1 = r3.contains(r1)     // Catch:{ all -> 0x00e2 }
            if (r1 == 0) goto L_0x001c
            goto L_0x00e0
        L_0x001c:
            java.lang.String r1 = ""
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0044 }
            r3.<init>(r7)     // Catch:{ JSONException -> 0x0044 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0044 }
            r7.<init>()     // Catch:{ JSONException -> 0x0044 }
            java.lang.String r4 = "method"
            java.lang.String r4 = r3.optString(r4)     // Catch:{ JSONException -> 0x0044 }
            r7.append(r4)     // Catch:{ JSONException -> 0x0044 }
            java.lang.String r4 = ","
            r7.append(r4)     // Catch:{ JSONException -> 0x0044 }
            java.lang.String r4 = "params"
            java.lang.String r3 = r3.optString(r4)     // Catch:{ JSONException -> 0x0044 }
            r7.append(r3)     // Catch:{ JSONException -> 0x0044 }
            java.lang.String r7 = r7.toString()     // Catch:{ JSONException -> 0x0044 }
            goto L_0x0049
        L_0x0044:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x00e2 }
            r7 = r1
        L_0x0049:
            boolean r1 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x00e2 }
            if (r1 == 0) goto L_0x0051
            monitor-exit(r0)     // Catch:{ all -> 0x00e2 }
            return r2
        L_0x0051:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e2 }
            r1.<init>()     // Catch:{ all -> 0x00e2 }
            r1.append(r5)     // Catch:{ all -> 0x00e2 }
            java.lang.String r5 = ","
            r1.append(r5)     // Catch:{ all -> 0x00e2 }
            r1.append(r6)     // Catch:{ all -> 0x00e2 }
            java.lang.String r5 = ","
            r1.append(r5)     // Catch:{ all -> 0x00e2 }
            r1.append(r7)     // Catch:{ all -> 0x00e2 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x00e2 }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00e2 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x00e2 }
            java.lang.String r7 = "checkRpcRequst"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e2 }
            r1.<init>()     // Catch:{ all -> 0x00e2 }
            java.lang.String r3 = "request: "
            r1.append(r3)     // Catch:{ all -> 0x00e2 }
            r1.append(r5)     // Catch:{ all -> 0x00e2 }
            java.lang.String r3 = ", time = "
            r1.append(r3)     // Catch:{ all -> 0x00e2 }
            r1.append(r6)     // Catch:{ all -> 0x00e2 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00e2 }
            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r7, r1)     // Catch:{ all -> 0x00e2 }
            java.util.Map<java.lang.String, java.lang.Long> r7 = d     // Catch:{ all -> 0x00e2 }
            java.util.Set r7 = r7.entrySet()     // Catch:{ all -> 0x00e2 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x00e2 }
        L_0x009d:
            boolean r1 = r7.hasNext()     // Catch:{ all -> 0x00e2 }
            if (r1 == 0) goto L_0x00d9
            java.lang.Object r1 = r7.next()     // Catch:{ all -> 0x00e2 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x00e2 }
            java.lang.Object r3 = r1.getValue()     // Catch:{ all -> 0x00e2 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ all -> 0x00e2 }
            java.lang.Object r4 = r1.getKey()     // Catch:{ all -> 0x00e2 }
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ all -> 0x00e2 }
            boolean r4 = android.text.TextUtils.equals(r5, r4)     // Catch:{ all -> 0x00e2 }
            if (r4 == 0) goto L_0x00c9
            boolean r5 = a((java.lang.Long) r6, (java.lang.Long) r3)     // Catch:{ all -> 0x00e2 }
            if (r5 == 0) goto L_0x00c6
            r1.setValue(r6)     // Catch:{ all -> 0x00e2 }
            monitor-exit(r0)     // Catch:{ all -> 0x00e2 }
            return r2
        L_0x00c6:
            r5 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x00e2 }
            return r5
        L_0x00c9:
            boolean r3 = a((java.lang.Long) r6, (java.lang.Long) r3)     // Catch:{ all -> 0x00e2 }
            if (r3 == 0) goto L_0x009d
            java.util.Map<java.lang.String, java.lang.Long> r3 = d     // Catch:{ all -> 0x00e2 }
            java.lang.Object r1 = r1.getKey()     // Catch:{ all -> 0x00e2 }
            r3.remove(r1)     // Catch:{ all -> 0x00e2 }
            goto L_0x009d
        L_0x00d9:
            java.util.Map<java.lang.String, java.lang.Long> r7 = d     // Catch:{ all -> 0x00e2 }
            r7.put(r5, r6)     // Catch:{ all -> 0x00e2 }
            monitor-exit(r0)     // Catch:{ all -> 0x00e2 }
            return r2
        L_0x00e0:
            monitor-exit(r0)     // Catch:{ all -> 0x00e2 }
            return r2
        L_0x00e2:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00e2 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.api.RpcErrorDescription.a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    private static boolean a(Long l, Long l2) {
        return l.longValue() - l2.longValue() >= 1000;
    }

    public static void d(String str) {
        synchronized (RpcErrorDescription.class) {
            if (!f(str)) {
                f.add(str);
            }
        }
    }

    public static void e(String str) {
        synchronized (RpcErrorDescription.class) {
            if (f(str)) {
                f.remove(str);
            }
        }
    }

    public static boolean f(String str) {
        boolean contains;
        synchronized (RpcErrorDescription.class) {
            contains = f.contains(str);
        }
        return contains;
    }
}
