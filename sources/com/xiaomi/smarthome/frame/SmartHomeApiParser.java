package com.xiaomi.smarthome.frame;

import com.xiaomi.smarthome.core.entity.net.NetResult;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmartHomeApiParser {

    /* renamed from: a  reason: collision with root package name */
    private static SmartHomeApiParser f16001a;
    private static Object b = new Object();
    private ExecutorService c = Executors.newCachedThreadPool();

    private SmartHomeApiParser() {
    }

    public static SmartHomeApiParser a() {
        if (f16001a == null) {
            synchronized (b) {
                if (f16001a == null) {
                    f16001a = new SmartHomeApiParser();
                }
            }
        }
        return f16001a;
    }

    public <R> void a(NetResult netResult, JsonParser<R> jsonParser, AsyncCallback<R, Error> asyncCallback) {
        a(netResult, jsonParser, asyncCallback, false);
    }

    public <R> void a(NetResult netResult, JsonParser<R> jsonParser, AsyncCallback<R, Error> asyncCallback, boolean z) {
        if (netResult != null) {
            final NetResult netResult2 = netResult;
            final boolean z2 = z;
            final JsonParser<R> jsonParser2 = jsonParser;
            final AsyncCallback<R, Error> asyncCallback2 = asyncCallback;
            this.c.submit(new Runnable() {
                /* JADX WARNING: Removed duplicated region for block: B:12:0x0034 A[Catch:{ Exception -> 0x008e }] */
                /* JADX WARNING: Removed duplicated region for block: B:20:0x0054 A[Catch:{ Exception -> 0x008e }] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r5 = this;
                        org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x008e }
                        com.xiaomi.smarthome.core.entity.net.NetResult r1 = r3     // Catch:{ Exception -> 0x008e }
                        java.lang.String r1 = r1.c     // Catch:{ Exception -> 0x008e }
                        r0.<init>(r1)     // Catch:{ Exception -> 0x008e }
                        java.lang.String r1 = "code"
                        int r1 = r0.optInt(r1)     // Catch:{ Exception -> 0x008e }
                        com.xiaomi.smarthome.frame.ErrorCode r2 = com.xiaomi.smarthome.frame.ErrorCode.valueof(r1)     // Catch:{ Exception -> 0x008e }
                        com.xiaomi.smarthome.frame.ErrorCode r3 = com.xiaomi.smarthome.frame.ErrorCode.SUCCESS     // Catch:{ Exception -> 0x008e }
                        if (r2 != r3) goto L_0x006f
                        java.lang.String r1 = "result"
                        org.json.JSONObject r1 = r0.optJSONObject(r1)     // Catch:{ Exception -> 0x008e }
                        if (r1 == 0) goto L_0x002a
                        boolean r2 = r4     // Catch:{ Exception -> 0x008e }
                        if (r2 == 0) goto L_0x0024
                        goto L_0x002a
                    L_0x0024:
                        com.xiaomi.smarthome.library.safejson.JSONObjectSafe r0 = new com.xiaomi.smarthome.library.safejson.JSONObjectSafe     // Catch:{ Exception -> 0x008e }
                        r0.<init>((org.json.JSONObject) r1)     // Catch:{ Exception -> 0x008e }
                        goto L_0x0030
                    L_0x002a:
                        com.xiaomi.smarthome.library.safejson.JSONObjectSafe r1 = new com.xiaomi.smarthome.library.safejson.JSONObjectSafe     // Catch:{ Exception -> 0x008e }
                        r1.<init>((org.json.JSONObject) r0)     // Catch:{ Exception -> 0x008e }
                        r0 = r1
                    L_0x0030:
                        com.xiaomi.smarthome.frame.JsonParser r1 = r5     // Catch:{ Exception -> 0x008e }
                        if (r1 == 0) goto L_0x0054
                        com.xiaomi.smarthome.frame.JsonParser r1 = r5     // Catch:{ Exception -> 0x008e }
                        java.lang.Object r0 = r1.parse(r0)     // Catch:{ Exception -> 0x008e }
                        com.xiaomi.smarthome.core.entity.net.NetResult r1 = r3     // Catch:{ Exception -> 0x008e }
                        boolean r1 = r1.b     // Catch:{ Exception -> 0x008e }
                        if (r1 != 0) goto L_0x004a
                        com.xiaomi.smarthome.frame.AsyncCallback r1 = r6     // Catch:{ Exception -> 0x008e }
                        if (r1 == 0) goto L_0x00aa
                        com.xiaomi.smarthome.frame.AsyncCallback r1 = r6     // Catch:{ Exception -> 0x008e }
                        r1.sendSuccessMessage(r0)     // Catch:{ Exception -> 0x008e }
                        goto L_0x00aa
                    L_0x004a:
                        com.xiaomi.smarthome.frame.AsyncCallback r1 = r6     // Catch:{ Exception -> 0x008e }
                        if (r1 == 0) goto L_0x00aa
                        com.xiaomi.smarthome.frame.AsyncCallback r1 = r6     // Catch:{ Exception -> 0x008e }
                        r1.sendCacheMessage(r0)     // Catch:{ Exception -> 0x008e }
                        goto L_0x00aa
                    L_0x0054:
                        com.xiaomi.smarthome.core.entity.net.NetResult r0 = r3     // Catch:{ Exception -> 0x008e }
                        boolean r0 = r0.b     // Catch:{ Exception -> 0x008e }
                        r1 = 0
                        if (r0 != 0) goto L_0x0065
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r6     // Catch:{ Exception -> 0x008e }
                        if (r0 == 0) goto L_0x00aa
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r6     // Catch:{ Exception -> 0x008e }
                        r0.sendSuccessMessage(r1)     // Catch:{ Exception -> 0x008e }
                        goto L_0x00aa
                    L_0x0065:
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r6     // Catch:{ Exception -> 0x008e }
                        if (r0 == 0) goto L_0x00aa
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r6     // Catch:{ Exception -> 0x008e }
                        r0.sendCacheMessage(r1)     // Catch:{ Exception -> 0x008e }
                        goto L_0x00aa
                    L_0x006f:
                        com.xiaomi.smarthome.core.entity.net.NetResult r2 = r3     // Catch:{ Exception -> 0x008e }
                        boolean r2 = r2.b     // Catch:{ Exception -> 0x008e }
                        if (r2 != 0) goto L_0x00aa
                        com.xiaomi.smarthome.frame.AsyncCallback r2 = r6     // Catch:{ Exception -> 0x008e }
                        if (r2 == 0) goto L_0x00aa
                        java.lang.String r2 = "message"
                        java.lang.String r0 = r0.optString(r2)     // Catch:{ Exception -> 0x008e }
                        com.xiaomi.smarthome.frame.AsyncCallback r2 = r6     // Catch:{ Exception -> 0x008e }
                        com.xiaomi.smarthome.frame.Error r3 = new com.xiaomi.smarthome.frame.Error     // Catch:{ Exception -> 0x008e }
                        com.xiaomi.smarthome.core.entity.net.NetResult r4 = r3     // Catch:{ Exception -> 0x008e }
                        java.lang.String r4 = r4.c     // Catch:{ Exception -> 0x008e }
                        r3.<init>(r1, r0, r4)     // Catch:{ Exception -> 0x008e }
                        r2.sendFailureMessage(r3)     // Catch:{ Exception -> 0x008e }
                        goto L_0x00aa
                    L_0x008e:
                        com.xiaomi.smarthome.core.entity.net.NetResult r0 = r3
                        boolean r0 = r0.b
                        if (r0 != 0) goto L_0x00aa
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r6
                        if (r0 == 0) goto L_0x00aa
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r6
                        com.xiaomi.smarthome.frame.Error r1 = new com.xiaomi.smarthome.frame.Error
                        com.xiaomi.smarthome.frame.ErrorCode r2 = com.xiaomi.smarthome.frame.ErrorCode.INVALID
                        int r2 = r2.getCode()
                        java.lang.String r3 = "wrong response format"
                        r1.<init>(r2, r3)
                        r0.sendFailureMessage(r1)
                    L_0x00aa:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.SmartHomeApiParser.AnonymousClass1.run():void");
                }
            });
        } else if (asyncCallback != null) {
            asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
        }
    }
}
