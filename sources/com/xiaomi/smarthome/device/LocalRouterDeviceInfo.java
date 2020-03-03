package com.xiaomi.smarthome.device;

import android.annotation.SuppressLint;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Base64;
import com.miuipub.internal.hybrid.SignUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.RemoteRouterMitvApi;
import com.xiaomi.smarthome.framework.api.RouterRemoteApi;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.Cipher;
import org.json.JSONObject;

public class LocalRouterDeviceInfo {

    /* renamed from: a  reason: collision with root package name */
    private static LocalRouterDeviceInfo f14870a;
    /* access modifiers changed from: private */
    public LinkedList<RouterRemoteApi.WifiDetail> b = new LinkedList<>();
    /* access modifiers changed from: private */
    public KeyPair c = null;
    /* access modifiers changed from: private */
    public AtomicBoolean d = new AtomicBoolean(false);

    public static LocalRouterDeviceInfo a() {
        if (f14870a == null) {
            f14870a = new LocalRouterDeviceInfo();
        }
        return f14870a;
    }

    private LocalRouterDeviceInfo() {
    }

    public boolean a(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        WifiInfo connectionInfo = ((WifiManager) SHApplication.getAppContext().getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo == null) {
            return false;
        }
        String ssid = connectionInfo.getSSID();
        String bssid = connectionInfo.getBSSID();
        if (!(bssid == null || str4 == null)) {
            String[] split = str4.split(":");
            if (bssid.equalsIgnoreCase(str4)) {
                return true;
            }
            if (split.length == 6) {
                String format = String.format("%1$s:%2$s:%3$s:%4$s:%5$s:%6$x", new Object[]{split[0], split[1], split[2], split[3], split[4], Integer.valueOf(Integer.valueOf(split[5], 16).intValue() + 1)});
                String format2 = String.format("%1$s:%2$s:%3$s:%4$s:%5$s:%6$x", new Object[]{split[0], split[1], split[2], split[3], split[4], Integer.valueOf(Integer.valueOf(split[5], 16).intValue() - 1)});
                if (bssid.equalsIgnoreCase(format) || bssid.equalsIgnoreCase(format2) || bssid.equalsIgnoreCase(str4)) {
                    return true;
                }
            }
        }
        if (ssid == null || str3 == null || !WifiSettingUtils.a(ssid.replaceAll("([-_])?(2.4|5)G", ""), str3.replaceAll("([-_])?(2.4|5)G", ""))) {
            return false;
        }
        return true;
    }

    public void a(final AsyncResponseCallback<RouterRemoteApi.WifiDetail> asyncResponseCallback) {
        String ssid;
        WifiInfo connectionInfo = ((WifiManager) SHApplication.getAppContext().getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        if (!(connectionInfo == null || (ssid = connectionInfo.getSSID()) == null)) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                RouterRemoteApi.WifiDetail wifiDetail = (RouterRemoteApi.WifiDetail) it.next();
                Iterator<RouterRemoteApi.WifiInfo> it2 = wifiDetail.f16425a.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (WifiSettingUtils.a(ssid, it2.next().c)) {
                            if (asyncResponseCallback != null) {
                                asyncResponseCallback.a(wifiDetail);
                                return;
                            }
                            return;
                        }
                    }
                }
            }
        }
        RemoteRouterMitvApi.a().a(SHApplication.getAppContext(), (AsyncResponseCallback<RemoteRouterMitvApi.MiRouterInfo>) new AsyncResponseCallback<RemoteRouterMitvApi.MiRouterInfo>() {
            public void a(RemoteRouterMitvApi.MiRouterInfo miRouterInfo) {
                if (miRouterInfo != null) {
                    LocalRouterDeviceInfo.this.a(miRouterInfo.b, (AsyncResponseCallback<RouterRemoteApi.WifiDetail>) asyncResponseCallback);
                } else if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(-1);
                }
            }

            public void a(int i) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(i);
                }
            }

            public void a(int i, Object obj) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(i);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    @SuppressLint({"StaticFieldLeak"})
    public void a(final String str, final AsyncResponseCallback<RouterRemoteApi.WifiDetail> asyncResponseCallback) {
        AsyncTaskUtils.a(new AsyncTask<Object, Object, Object>() {
            /* access modifiers changed from: protected */
            public Object doInBackground(Object... objArr) {
                if (LocalRouterDeviceInfo.this.c == null) {
                    return null;
                }
                DeviceApi.getInstance().getRouterLocalUrl(str, Base64.encodeToString(LocalRouterDeviceInfo.this.c.getPublic().getEncoded(), 2), "1:1:1:1:1", new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (jSONObject.optInt("code", -1) == 0) {
                            LocalRouterDeviceInfo.this.a(LocalRouterDeviceInfo.this.c, LocalRouterDeviceInfo.this.a(LocalRouterDeviceInfo.this.c, jSONObject.optJSONObject("data").optString("url", "")), (AsyncResponseCallback<RouterRemoteApi.WifiDetail>) asyncResponseCallback);
                        } else if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(-1);
                        }
                    }

                    public void onFailure(Error error) {
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(-1);
                        }
                    }
                });
                return null;
            }
        }, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void a(final KeyPair keyPair, String str, final AsyncResponseCallback<RouterRemoteApi.WifiDetail> asyncResponseCallback) {
        if (!TextUtils.isEmpty(str) && keyPair != null) {
            RemoteRouterMitvApi.a().a(str, (AsyncResponseCallback<String>) new AsyncResponseCallback<String>() {
                public void a(String str) {
                    try {
                        JSONObject jSONObject = new JSONObject(LocalRouterDeviceInfo.this.a(keyPair, new JSONObject(str).optString("info", "")));
                        RouterRemoteApi.WifiDetail wifiDetail = new RouterRemoteApi.WifiDetail();
                        ArrayList<RouterRemoteApi.WifiInfo> arrayList = new ArrayList<>();
                        RouterRemoteApi.WifiInfo wifiInfo = new RouterRemoteApi.WifiInfo();
                        wifiInfo.c = jSONObject.optString("2gssid");
                        wifiInfo.e = jSONObject.optString("2gpwd");
                        arrayList.add(wifiInfo);
                        RouterRemoteApi.WifiInfo wifiInfo2 = new RouterRemoteApi.WifiInfo();
                        wifiInfo2.c = jSONObject.optString("5gssid");
                        wifiInfo2.e = jSONObject.optString("5gpwd");
                        arrayList.add(wifiInfo2);
                        wifiDetail.f16425a = arrayList;
                        LocalRouterDeviceInfo.this.b.addFirst(wifiDetail);
                        if (LocalRouterDeviceInfo.this.b.size() > 3) {
                            LocalRouterDeviceInfo.this.b.removeLast();
                        }
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(wifiDetail);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(-1);
                        }
                    }
                }

                public void a(int i) {
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(-1);
                    }
                }

                public void a(int i, Object obj) {
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(-1);
                    }
                }
            });
        } else if (asyncResponseCallback != null) {
            asyncResponseCallback.a(-1);
        }
    }

    /* access modifiers changed from: private */
    public String a(KeyPair keyPair, String str) {
        try {
            Cipher instance = Cipher.getInstance(SignUtils.b);
            instance.init(2, keyPair.getPrivate());
            return new String(instance.doFinal(Base64.decode(str, 2)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    public void b() {
        if (this.c == null && !this.d.getAndSet(true)) {
            AsyncTaskUtils.a(new AsyncTask<Object, Object, Object>() {
                /* access modifiers changed from: protected */
                public Object doInBackground(Object... objArr) {
                    try {
                        KeyPairGenerator instance = KeyPairGenerator.getInstance(SignUtils.f8267a);
                        instance.initialize(2048);
                        instance.genKeyPair();
                        KeyPair unused = LocalRouterDeviceInfo.this.c = instance.generateKeyPair();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        LocalRouterDeviceInfo.this.d.set(true);
                        throw th;
                    }
                    LocalRouterDeviceInfo.this.d.set(true);
                    return null;
                }
            }, new Object[0]);
        }
    }
}
