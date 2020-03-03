package com.xiaomi.smarthome.framework.push.listener;

import android.text.TextUtils;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.push.PushListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;

public class DevicePushListener extends PushListener {

    /* renamed from: a  reason: collision with root package name */
    private static DevicePushListener f17672a;
    private Map<String, ArrayList<WeakReference<DevicePushCallback>>> b = new ConcurrentHashMap();

    public interface DevicePushCallback {
        void a(JSONArray jSONArray);
    }

    public static DevicePushListener a() {
        if (f17672a == null) {
            f17672a = new DevicePushListener();
        }
        return f17672a;
    }

    public boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.b("DevicePushMsg", "onReceiveMessage--> messageId is empty or messageBody is empty");
        } else {
            LogUtil.c("DevicePushMsg", "onReceiveMessage--> messageId: " + str + "    messageBody: " + str2);
        }
        a(str2);
        return true;
    }

    public boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.b("DevicePushMsg", "onReceiveNotifiedMessage--> messageId is empty or messageBody is empty");
        } else {
            LogUtil.c("DevicePushMsg", "onReceiveNotifiedMessage--> messageId: " + str + "    messageBody: " + str2);
        }
        a(str2);
        return true;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r5) {
        /*
            r4 = this;
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception | JSONException -> 0x00a9 }
            r0.<init>(r5)     // Catch:{ Exception | JSONException -> 0x00a9 }
            java.lang.String r5 = "subtype"
            java.lang.String r5 = r0.optString(r5)     // Catch:{ Exception | JSONException -> 0x00a9 }
            boolean r1 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception | JSONException -> 0x00a9 }
            if (r1 == 0) goto L_0x008b
            java.lang.String r5 = "subid"
            java.lang.String r5 = r0.optString(r5)     // Catch:{ Exception | JSONException -> 0x00a9 }
            java.lang.String r1 = "did"
            java.lang.String r1 = r0.optString(r1)     // Catch:{ Exception | JSONException -> 0x00a9 }
            java.lang.String r2 = "model"
            java.lang.String r2 = r0.optString(r2)     // Catch:{ Exception | JSONException -> 0x00a9 }
            java.lang.String r3 = "attrs"
            org.json.JSONArray r0 = r0.optJSONArray(r3)     // Catch:{ Exception | JSONException -> 0x00a9 }
            boolean r3 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception | JSONException -> 0x00a9 }
            if (r3 == 0) goto L_0x0083
            boolean r5 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception | JSONException -> 0x00a9 }
            if (r5 != 0) goto L_0x0082
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception | JSONException -> 0x00a9 }
            if (r5 != 0) goto L_0x0082
            if (r0 != 0) goto L_0x003e
            goto L_0x0082
        L_0x003e:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception | JSONException -> 0x00a9 }
            r5.<init>()     // Catch:{ Exception | JSONException -> 0x00a9 }
            r5.append(r1)     // Catch:{ Exception | JSONException -> 0x00a9 }
            java.lang.String r1 = "_"
            r5.append(r1)     // Catch:{ Exception | JSONException -> 0x00a9 }
            r5.append(r2)     // Catch:{ Exception | JSONException -> 0x00a9 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception | JSONException -> 0x00a9 }
            java.util.Map<java.lang.String, java.util.ArrayList<java.lang.ref.WeakReference<com.xiaomi.smarthome.framework.push.listener.DevicePushListener$DevicePushCallback>>> r1 = r4.b     // Catch:{ Exception | JSONException -> 0x00a9 }
            java.lang.Object r5 = r1.get(r5)     // Catch:{ Exception | JSONException -> 0x00a9 }
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch:{ Exception | JSONException -> 0x00a9 }
            if (r5 == 0) goto L_0x00a9
            monitor-enter(r5)     // Catch:{ Exception | JSONException -> 0x00a9 }
            int r1 = r5.size()     // Catch:{ all -> 0x007f }
            int r1 = r1 + -1
        L_0x0063:
            if (r1 < 0) goto L_0x007d
            java.lang.Object r2 = r5.get(r1)     // Catch:{ all -> 0x007f }
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2     // Catch:{ all -> 0x007f }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x007f }
            com.xiaomi.smarthome.framework.push.listener.DevicePushListener$DevicePushCallback r2 = (com.xiaomi.smarthome.framework.push.listener.DevicePushListener.DevicePushCallback) r2     // Catch:{ all -> 0x007f }
            if (r2 == 0) goto L_0x0077
            r2.a(r0)     // Catch:{ all -> 0x007f }
            goto L_0x007a
        L_0x0077:
            r5.remove(r1)     // Catch:{ all -> 0x007f }
        L_0x007a:
            int r1 = r1 + -1
            goto L_0x0063
        L_0x007d:
            monitor-exit(r5)     // Catch:{ all -> 0x007f }
            goto L_0x00a9
        L_0x007f:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x007f }
            throw r0     // Catch:{ Exception | JSONException -> 0x00a9 }
        L_0x0082:
            return
        L_0x0083:
            com.xiaomi.smarthome.devicesubscribe.DeviceSubscribeManager r3 = com.xiaomi.smarthome.devicesubscribe.DeviceSubscribeManager.a()     // Catch:{ Exception | JSONException -> 0x00a9 }
            r3.a((java.lang.String) r5, (java.lang.String) r1, (java.lang.String) r2, (org.json.JSONArray) r0)     // Catch:{ Exception | JSONException -> 0x00a9 }
            goto L_0x00a9
        L_0x008b:
            java.lang.String r1 = "routerChanged"
            boolean r5 = android.text.TextUtils.equals(r1, r5)     // Catch:{ Exception | JSONException -> 0x00a9 }
            if (r5 == 0) goto L_0x00a9
            android.os.Bundle r5 = new android.os.Bundle     // Catch:{ Exception | JSONException -> 0x00a9 }
            r5.<init>()     // Catch:{ Exception | JSONException -> 0x00a9 }
            java.lang.String r1 = "message_record"
            java.lang.String r0 = r0.toString()     // Catch:{ Exception | JSONException -> 0x00a9 }
            r5.putString(r1, r0)     // Catch:{ Exception | JSONException -> 0x00a9 }
            java.lang.Class<com.xiaomi.smarthome.miio.page.MessageCenterWifiChangePwdActivity> r0 = com.xiaomi.smarthome.miio.page.MessageCenterWifiChangePwdActivity.class
            r1 = 0
            r2 = 67108864(0x4000000, float:1.5046328E-36)
            com.xiaomi.smarthome.framework.openapi.OpenApi.a(r0, r5, r1, r2)     // Catch:{ Exception | JSONException -> 0x00a9 }
        L_0x00a9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.push.listener.DevicePushListener.a(java.lang.String):void");
    }

    public void a(Device device, DevicePushCallback devicePushCallback) {
        String str = device.did + JSMethod.NOT_SET + device.model;
        ArrayList arrayList = this.b.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.b.put(str, arrayList);
        }
        synchronized (arrayList) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                DevicePushCallback devicePushCallback2 = (DevicePushCallback) ((WeakReference) arrayList.get(size)).get();
                if (devicePushCallback2 == null) {
                    arrayList.remove(size);
                } else if (devicePushCallback2 == devicePushCallback) {
                    return;
                }
            }
            arrayList.add(new WeakReference(devicePushCallback));
        }
    }

    public void a(Device device) {
        this.b.remove(device.did + JSMethod.NOT_SET + device.model);
    }

    public void b(Device device, DevicePushCallback devicePushCallback) {
        String str = device.did + JSMethod.NOT_SET + device.model;
        ArrayList arrayList = this.b.get(str);
        if (arrayList != null) {
            synchronized (arrayList) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    DevicePushCallback devicePushCallback2 = (DevicePushCallback) ((WeakReference) arrayList.get(size)).get();
                    if (devicePushCallback2 == null || devicePushCallback2 == devicePushCallback) {
                        arrayList.remove(size);
                    }
                }
            }
            if (arrayList.size() == 0) {
                this.b.remove(str);
            }
        }
    }
}
