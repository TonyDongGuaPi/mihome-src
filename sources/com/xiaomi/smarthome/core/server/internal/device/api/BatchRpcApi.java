package com.xiaomi.smarthome.core.server.internal.device.api;

import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.device.BatchRpcParam;
import com.xiaomi.smarthome.core.server.internal.device.DeviceHandle;
import com.xiaomi.smarthome.core.server.internal.device.DeviceManager;
import com.xiaomi.smarthome.core.server.internal.device.LocalDeviceApiInternal;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BatchRpcApi {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Map<String, Object> f14549a = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<String, String> b = new ConcurrentHashMap();
    private AsyncResponseCallback<String> c;
    private long d = 0;

    public DeviceHandle a(List<BatchRpcParam> list, AsyncResponseCallback<String> asyncResponseCallback) {
        this.d = System.currentTimeMillis();
        ArrayList arrayList = null;
        if (list == null || list.size() == 0) {
            if (asyncResponseCallback != null) {
                asyncResponseCallback.a(0, "empty request");
            }
            return null;
        }
        ArrayList arrayList2 = null;
        for (int i = 0; i < list.size(); i++) {
            BatchRpcParam batchRpcParam = list.get(i);
            Device a2 = DeviceManager.a().a(batchRpcParam.f14497a);
            if (a2 != null) {
                if (WifiUtil.a(CoreService.getAppContext()) == null || !a2.Y()) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    if (!TextUtils.isEmpty(batchRpcParam.f14497a)) {
                        if (batchRpcParam.f14497a.startsWith("mitv.")) {
                            batchRpcParam.f14497a = URLEncoder.encode(batchRpcParam.f14497a);
                        }
                        arrayList.add(batchRpcParam);
                    }
                } else {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    arrayList2.add(batchRpcParam);
                }
                this.b.put(batchRpcParam.f14497a, batchRpcParam.f14497a);
            }
        }
        this.c = asyncResponseCallback;
        DeviceHandle c2 = c((List<BatchRpcParam>) arrayList);
        a((List<BatchRpcParam>) arrayList2);
        return c2;
    }

    private void a(List<BatchRpcParam> list) {
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                BatchRpcParam batchRpcParam = list.get(i);
                final Device a2 = DeviceManager.a().a(batchRpcParam.f14497a);
                if (a2 != null) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("method", batchRpcParam.b);
                        jSONObject.put("params", batchRpcParam.c);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    LocalDeviceApiInternal.a().b(a2.t(), a2.k(), a2.s(), jSONObject.toString(), new AsyncResponseCallback<String>() {
                        public void a(String str) {
                            Log.d("BatchRpcApi", "rpcAsyncRaw response:" + str);
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                if (jSONObject.optInt("code", -1) == 0) {
                                    BatchRpcApi.this.f14549a.put(a2.k(), jSONObject.opt("result"));
                                } else {
                                    BatchRpcApi.this.f14549a.put(a2.k(), false);
                                }
                            } catch (Exception e) {
                                BatchRpcApi.this.f14549a.put(a2.k(), false);
                                e.printStackTrace();
                            }
                            BatchRpcApi.this.b.remove(a2.k());
                            BatchRpcApi.this.a();
                        }

                        public void a(int i) {
                            Log.e("BatchRpcApi", "local rpc response:" + i);
                            BatchRpcApi.this.f14549a.put(a2.k(), false);
                            BatchRpcApi.this.b.remove(a2.k());
                            BatchRpcApi.this.a();
                        }

                        public void a(int i, Object obj) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("local rpc response:");
                            sb.append(i);
                            sb.append(", msg=");
                            sb.append(obj == null ? "" : obj.toString());
                            Log.e("BatchRpcApi", sb.toString());
                            BatchRpcApi.this.f14549a.put(a2.k(), false);
                            BatchRpcApi.this.b.remove(a2.k());
                            BatchRpcApi.this.a();
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
            r7 = this;
            monitor-enter(r7)
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.b     // Catch:{ all -> 0x003b }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x003b }
            if (r0 != 0) goto L_0x000b
            monitor-exit(r7)     // Catch:{ all -> 0x003b }
            return
        L_0x000b:
            com.xiaomi.smarthome.core.server.internal.device.api.AsyncResponseCallback<java.lang.String> r0 = r7.c     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x0039
            java.lang.String r0 = "BatchRpcApi"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x003b }
            r1.<init>()     // Catch:{ all -> 0x003b }
            java.lang.String r2 = ""
            r1.append(r2)     // Catch:{ all -> 0x003b }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x003b }
            long r4 = r7.d     // Catch:{ all -> 0x003b }
            r6 = 0
            long r2 = r2 - r4
            r1.append(r2)     // Catch:{ all -> 0x003b }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x003b }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x003b }
            com.xiaomi.smarthome.core.server.internal.device.api.AsyncResponseCallback<java.lang.String> r0 = r7.c     // Catch:{ all -> 0x003b }
            java.lang.String r1 = r7.b()     // Catch:{ all -> 0x003b }
            r0.a(r1)     // Catch:{ all -> 0x003b }
            r0 = 0
            r7.c = r0     // Catch:{ all -> 0x003b }
        L_0x0039:
            monitor-exit(r7)     // Catch:{ all -> 0x003b }
            return
        L_0x003b:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x003b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.api.BatchRpcApi.a():void");
    }

    private String b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", ErrorCode.SUCCESS);
            JSONObject jSONObject2 = new JSONObject();
            for (String next : this.f14549a.keySet()) {
                jSONObject2.put(next, this.f14549a.get(next));
            }
            jSONObject.put("result", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    private String b(List<BatchRpcParam> list) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            BatchRpcParam batchRpcParam = list.get(i);
            if (batchRpcParam != null && !TextUtils.isEmpty(batchRpcParam.f14497a)) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("did", batchRpcParam.f14497a);
                    jSONObject.put("method", batchRpcParam.b);
                    jSONObject.put("params", batchRpcParam.c);
                    if (!TextUtils.isEmpty(batchRpcParam.d)) {
                        jSONObject.put("sid", batchRpcParam.d);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jSONArray.put(jSONObject);
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("rpcs", jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject2.toString();
    }

    /* access modifiers changed from: private */
    public void a(List<BatchRpcParam> list, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = 0;
            if (!jSONObject.isNull("result")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("result");
                if (optJSONArray != null) {
                    HashSet hashSet = new HashSet();
                    while (i < optJSONArray.length()) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            int optInt = optJSONObject.optInt("index", -1);
                            if (optInt != -1) {
                                if (optInt < list.size()) {
                                    String str2 = list.get(optInt).f14497a;
                                    this.f14549a.put(str2, optJSONObject.opt("result"));
                                    this.b.remove(str2);
                                    hashSet.add(str2);
                                }
                            }
                        }
                        i++;
                    }
                    for (BatchRpcParam next : list) {
                        if (!hashSet.contains(next.f14497a)) {
                            this.f14549a.put(next.f14497a, "false");
                            this.b.remove(next.f14497a);
                        }
                    }
                    i = 1;
                }
            }
            if (i == 0) {
                for (BatchRpcParam next2 : list) {
                    this.f14549a.put(next2.f14497a, "true");
                    this.b.remove(next2.f14497a);
                }
            }
        } catch (JSONException e) {
            Log.e("BatchRpcApi", "batchrpc response:" + str);
            e.printStackTrace();
            for (BatchRpcParam next3 : list) {
                this.f14549a.put(next3.f14497a, "true");
                this.b.remove(next3.f14497a);
            }
        }
        a();
    }

    private DeviceHandle c(final List<BatchRpcParam> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        return DeviceApiInternal.a().a(b(list), (AsyncResponseCallback<String>) new AsyncResponseCallback<String>() {
            public void a(String str) {
                Log.d("BatchRpcApi", "batchRpcAsync response:" + str);
                BatchRpcApi.this.a((List<BatchRpcParam>) list, str);
            }

            public void a(int i) {
                Log.e("BatchRpcApi", "batchrpc fail response:" + i);
                for (BatchRpcParam batchRpcParam : list) {
                    BatchRpcApi.this.f14549a.put(batchRpcParam.f14497a, false);
                    BatchRpcApi.this.b.remove(batchRpcParam.f14497a);
                }
                BatchRpcApi.this.a();
            }

            public void a(int i, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append("batchrpc fail response:");
                sb.append(i);
                sb.append(", msg=");
                sb.append(obj == null ? "" : obj.toString());
                Log.e("BatchRpcApi", sb.toString());
                for (BatchRpcParam batchRpcParam : list) {
                    BatchRpcApi.this.f14549a.put(batchRpcParam.f14497a, false);
                    BatchRpcApi.this.b.remove(batchRpcParam.f14497a);
                }
                BatchRpcApi.this.a();
            }
        });
    }
}
