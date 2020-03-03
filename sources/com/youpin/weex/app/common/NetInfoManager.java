package com.youpin.weex.app.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.bridge.JSCallback;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.youpin.log.LogUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class NetInfoManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f2495a = "none";
    public static final String b = "unknown";
    public static final String c = "cell";
    public static final String d = "wifi";
    public static final String e = "status";
    private static NetInfoManager f;
    private String g = "NetInfoManager";
    private ConnectivityBroadcastReceiver h;
    /* access modifiers changed from: private */
    public String i;
    private WeakReference<Context> j;
    /* access modifiers changed from: private */
    public JSCallback k;
    /* access modifiers changed from: private */
    public String l;

    private NetInfoManager(Context context) {
        this.j = new WeakReference<>(context);
    }

    public static NetInfoManager a(Context context) {
        if (f == null) {
            f = new NetInfoManager(context);
        }
        return f;
    }

    public void a(String str, JSCallback jSCallback) {
        if (this.h == null) {
            this.h = new ConnectivityBroadcastReceiver();
        }
        if (!this.h.a()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            ((Context) this.j.get()).registerReceiver(this.h, intentFilter);
            this.h.a(true);
            this.k = jSCallback;
            this.i = str;
            LogUtils.e(MiPushClient.f11511a, this.h.toString() + "\n" + this.k.toString() + "\n" + this.i);
        }
    }

    public void a(JSCallback jSCallback) {
        LogUtils.e(this.g, "unregisterReceiver");
        if (this.h == null) {
            ModuleUtils.b("monitor has not been initialized", jSCallback);
        } else if (this.h.a()) {
            ((Context) this.j.get()).unregisterReceiver(this.h);
            this.h.a(false);
            this.i = null;
            this.k = null;
            this.h = null;
            ModuleUtils.a("success", jSCallback);
        }
    }

    public void a(String str, ICallback iCallback) {
        a((Context) this.j.get(), str, iCallback);
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        a((Context) this.j.get(), str, new ICallback() {
            public void callback(Map map) {
                LogUtils.e("update", map.toString());
                String str = (String) map.get("status");
                if (!str.equalsIgnoreCase(NetInfoManager.this.l)) {
                    String unused = NetInfoManager.this.l = str;
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("status", (Object) NetInfoManager.this.l);
                        ModuleUtils.b(jSONObject, NetInfoManager.this.k);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void a(Context context, String str, ICallback iCallback) {
        HashMap hashMap = new HashMap();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            hashMap.put("status", "unknown");
            iCallback.callback(hashMap);
            return;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            hashMap.put("status", "none");
            iCallback.callback(hashMap);
        } else if (activeNetworkInfo.getType() == 1) {
            hashMap.put("status", "wifi");
            iCallback.callback(hashMap);
        } else if (activeNetworkInfo.getType() == 0) {
            hashMap.put("status", "cell");
            iCallback.callback(hashMap);
        }
    }

    private class ConnectivityBroadcastReceiver extends BroadcastReceiver {
        private boolean b;

        private ConnectivityBroadcastReceiver() {
            this.b = false;
        }

        public boolean a() {
            return this.b;
        }

        public void a(boolean z) {
            this.b = z;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                NetInfoManager.this.a(NetInfoManager.this.i);
            }
        }
    }
}
