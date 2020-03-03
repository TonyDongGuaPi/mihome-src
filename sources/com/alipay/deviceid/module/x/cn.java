package com.alipay.deviceid.module.x;

import android.content.Context;
import android.util.Log;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class cn extends ch {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f912a = {"t", "data"};
    private Map<String, Object> b;
    /* access modifiers changed from: private */
    public String c;
    /* access modifiers changed from: private */
    public LinkedList<a> d;
    /* access modifiers changed from: private */
    public Thread e;

    class a {

        /* renamed from: a  reason: collision with root package name */
        String f915a = "";
        private Context c;

        public a(Context context) {
            this.c = context;
        }
    }

    public cn() {
        this((byte) 0);
    }

    private cn(byte b2) {
        this.c = "";
        this.d = new LinkedList<>();
        this.b = new HashMap();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        this.b.put("data", this.c);
        for (String str : f912a) {
            Object obj = this.b.get(str);
            if (obj != null && (obj instanceof String)) {
                try {
                    jSONObject.put(str, obj);
                } catch (JSONException e2) {
                    Log.e("RDSInfo", e2.toString());
                }
            } else if (obj != null && (obj instanceof ch)) {
                try {
                    jSONObject.put(str, ((ch) obj).a());
                } catch (JSONException unused) {
                }
            }
        }
        return jSONObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(android.content.Context r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.b     // Catch:{ all -> 0x004f }
            java.lang.String r1 = "t"
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x004f }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x004f }
            r0.put(r1, r2)     // Catch:{ all -> 0x004f }
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.b     // Catch:{ all -> 0x004f }
            java.lang.String r1 = "data"
            java.lang.String r2 = r4.c     // Catch:{ all -> 0x004f }
            r0.put(r1, r2)     // Catch:{ all -> 0x004f }
            java.util.LinkedList<com.alipay.deviceid.module.x.cn$a> r0 = r4.d     // Catch:{ all -> 0x004f }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x004f }
            if (r0 != 0) goto L_0x0024
            monitor-exit(r4)
            return
        L_0x0024:
            java.util.LinkedList<com.alipay.deviceid.module.x.cn$a> r0 = r4.d     // Catch:{ all -> 0x004f }
            com.alipay.deviceid.module.x.cn$a r1 = new com.alipay.deviceid.module.x.cn$a     // Catch:{ all -> 0x004f }
            r1.<init>(r5)     // Catch:{ all -> 0x004f }
            r0.addLast(r1)     // Catch:{ all -> 0x004f }
            java.lang.Thread r5 = r4.e     // Catch:{ all -> 0x004f }
            if (r5 != 0) goto L_0x004d
            java.lang.Thread r5 = new java.lang.Thread     // Catch:{ all -> 0x004f }
            com.alipay.deviceid.module.x.cn$1 r0 = new com.alipay.deviceid.module.x.cn$1     // Catch:{ all -> 0x004f }
            r0.<init>()     // Catch:{ all -> 0x004f }
            r5.<init>(r0)     // Catch:{ all -> 0x004f }
            r4.e = r5     // Catch:{ all -> 0x004f }
            java.lang.Thread r5 = r4.e     // Catch:{ all -> 0x004f }
            com.alipay.deviceid.module.x.cn$2 r0 = new com.alipay.deviceid.module.x.cn$2     // Catch:{ all -> 0x004f }
            r0.<init>()     // Catch:{ all -> 0x004f }
            r5.setUncaughtExceptionHandler(r0)     // Catch:{ all -> 0x004f }
            java.lang.Thread r5 = r4.e     // Catch:{ all -> 0x004f }
            r5.start()     // Catch:{ all -> 0x004f }
        L_0x004d:
            monitor-exit(r4)
            return
        L_0x004f:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.cn.a(android.content.Context):void");
    }
}
