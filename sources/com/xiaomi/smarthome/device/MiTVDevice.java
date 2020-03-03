package com.xiaomi.smarthome.device;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.MitvDeviceRecommendManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.RemoteRouterMitvApi;
import com.xiaomi.smarthome.framework.network.NetworkManager;
import com.xiaomi.smarthome.framework.statistic.StatUtil;
import com.xiaomi.smarthomedevice.R;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.aspectj.runtime.internal.AroundClosure;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MiTVDevice extends MiioDeviceV2 {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14876a = "MiTVDevice";
    static final String b = "http://%s:6095/controller?action=keyevent&keycode=%s";
    static final String c = "http://%s:6095/request?action=%s";
    static final String d = "http://%s:6095/account?action=getState";
    static final String e = "http://%s:6095/account?action=addNew&data=%s";
    static final String f = "http://%s:6095/controller?action=play&type=video&mediaid=0&ci=0&clientname=%s&title=%s&position=0&playlength=0&url=%s";
    static final String g = "http://%s:6095/controller?action=play&type=monitor&param=%s";
    static final String h = "pref_mitv";
    static final int i = 1;
    static final int j = 2;
    static int k = 1;
    Handler l = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    LogUtil.a(MiTVDevice.f14876a, "handleMessage time out");
                    MiTVDevice.this.location = Device.Location.REMOTE;
                    if (MiTVDevice.this.isOnline) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("device_status", "remote_online");
                        MobclickAgent.a(CommonApplication.getAppContext(), StatUtil.f17686a, (Map<String, String>) hashMap);
                        return;
                    }
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("device_status", "offline");
                    MobclickAgent.a(CommonApplication.getAppContext(), StatUtil.f17686a, (Map<String, String>) hashMap2);
                    return;
                case 2:
                    LogUtil.a(MiTVDevice.f14876a, "handleMessage ok");
                    MiTVDevice.this.location = Device.Location.LOCAL;
                    MiTVDevice.this.isOnline = true;
                    HashMap hashMap3 = new HashMap();
                    hashMap3.put("device_status", "local");
                    MobclickAgent.a(CommonApplication.getAppContext(), StatUtil.f17686a, (Map<String, String>) hashMap3);
                    return;
                default:
                    return;
            }
        }
    };
    public boolean m = false;
    MitvDeviceRecommendManager.MiTVRecommend n;
    int o;
    public MitvDeviceRecommendManager.MiTVRecommendItem p;
    boolean q = false;
    int r = 0;
    int s = 1;
    volatile boolean t = false;
    public String u = "";
    String v = "";
    int w;
    boolean x = false;

    public static class Captchacode {

        /* renamed from: a  reason: collision with root package name */
        public Bitmap f14884a;
        public String b;
    }

    public boolean a(MiioDeviceV2.DeviceCallback<Void> deviceCallback) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
    }

    public boolean b(MiioDeviceV2.DeviceCallback<Void> deviceCallback) {
        return false;
    }

    /* access modifiers changed from: protected */
    public JSONObject c() {
        return null;
    }

    public boolean canBeDeleted() {
        return true;
    }

    public void initialLocal() {
    }

    public String a() {
        String str = this.model;
        if (this.w <= 0) {
            return str;
        }
        if (this.w < 600) {
            return "xiaomi.tvbox.v" + this.w;
        }
        return "xiaomi.tv.v" + this.w;
    }

    public MiTVDevice() {
        this.canUseNotBind = true;
        this.canAuth = false;
    }

    public void parseExtra(String str) {
        super.parseExtra(str);
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.u = jSONObject.optString("active_mac");
                if (!TextUtils.isEmpty(this.u)) {
                    this.u = this.u.toLowerCase();
                }
                this.v = jSONObject.optString("fw_version");
                this.w = jSONObject.optInt("platform");
            } catch (Exception unused) {
            }
        }
    }

    public void b() {
        if (NetworkManager.a().d() == 1) {
            HashMap hashMap = new HashMap();
            hashMap.put(LogCategory.CATEGORY_NETWORK, "wifi");
            MobclickAgent.a(CommonApplication.getAppContext(), StatUtil.f17686a, (Map<String, String>) hashMap);
        } else {
            HashMap hashMap2 = new HashMap();
            hashMap2.put(LogCategory.CATEGORY_NETWORK, "nowifi");
            MobclickAgent.a(CommonApplication.getAppContext(), StatUtil.f17686a, (Map<String, String>) hashMap2);
        }
        a(this.ip);
    }

    public void a(final String str) {
        if (!this.t) {
            this.t = true;
            try {
                CommonApplication.getThreadExecutor().submit(new Runnable() {
                    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
                        r2.close();
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
                        return;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
                        return;
                     */
                    /* JADX WARNING: Failed to process nested try/catch */
                    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x007b */
                    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a0 A[SYNTHETIC, Splitter:B:26:0x00a0] */
                    /* JADX WARNING: Removed duplicated region for block: B:31:0x00aa A[SYNTHETIC, Splitter:B:31:0x00aa] */
                    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r6 = this;
                            r0 = 1
                            r1 = 0
                            r2 = 0
                            java.nio.channels.SocketChannel r3 = java.nio.channels.SocketChannel.open()     // Catch:{ Exception -> 0x007b }
                            r3.configureBlocking(r1)     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            java.net.InetSocketAddress r2 = new java.net.InetSocketAddress     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            java.lang.String r4 = r3     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            r5 = 6095(0x17cf, float:8.541E-42)
                            r2.<init>(r4, r5)     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            r3.connect(r2)     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            r2 = 0
                        L_0x0017:
                            r4 = 5
                            if (r2 >= r4) goto L_0x0028
                            boolean r5 = r3.finishConnect()     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            if (r5 != 0) goto L_0x0028
                            int r2 = r2 + 1
                            r4 = 50
                            java.lang.Thread.sleep(r4)     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            goto L_0x0017
                        L_0x0028:
                            if (r2 != r4) goto L_0x004a
                            java.lang.String r2 = com.xiaomi.smarthome.device.MiTVDevice.f14876a     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            r4.<init>()     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            java.lang.String r5 = "Connect to server failed2!"
                            r4.append(r5)     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            java.lang.String r5 = r3     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            r4.append(r5)     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r2, r4)     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            com.xiaomi.smarthome.device.MiTVDevice r2 = com.xiaomi.smarthome.device.MiTVDevice.this     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            android.os.Handler r2 = r2.l     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            r2.sendEmptyMessage(r0)     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            goto L_0x006a
                        L_0x004a:
                            java.lang.String r2 = com.xiaomi.smarthome.device.MiTVDevice.f14876a     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            r4.<init>()     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            java.lang.String r5 = "Connect to server success2"
                            r4.append(r5)     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            java.lang.String r5 = r3     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            r4.append(r5)     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r2, r4)     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            com.xiaomi.smarthome.device.MiTVDevice r2 = com.xiaomi.smarthome.device.MiTVDevice.this     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            android.os.Handler r2 = r2.l     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                            r4 = 2
                            r2.sendEmptyMessage(r4)     // Catch:{ Exception -> 0x0076, all -> 0x0074 }
                        L_0x006a:
                            com.xiaomi.smarthome.device.MiTVDevice r0 = com.xiaomi.smarthome.device.MiTVDevice.this
                            r0.t = r1
                            if (r3 == 0) goto L_0x00a3
                            r3.close()     // Catch:{ Exception -> 0x00a3 }
                            goto L_0x00a3
                        L_0x0074:
                            r0 = move-exception
                            goto L_0x00a4
                        L_0x0076:
                            r2 = r3
                            goto L_0x007b
                        L_0x0078:
                            r0 = move-exception
                            r3 = r2
                            goto L_0x00a4
                        L_0x007b:
                            java.lang.String r3 = com.xiaomi.smarthome.device.MiTVDevice.f14876a     // Catch:{ all -> 0x0078 }
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0078 }
                            r4.<init>()     // Catch:{ all -> 0x0078 }
                            java.lang.String r5 = "Connect to server success2"
                            r4.append(r5)     // Catch:{ all -> 0x0078 }
                            java.lang.String r5 = r3     // Catch:{ all -> 0x0078 }
                            r4.append(r5)     // Catch:{ all -> 0x0078 }
                            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0078 }
                            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r3, r4)     // Catch:{ all -> 0x0078 }
                            com.xiaomi.smarthome.device.MiTVDevice r3 = com.xiaomi.smarthome.device.MiTVDevice.this     // Catch:{ all -> 0x0078 }
                            android.os.Handler r3 = r3.l     // Catch:{ all -> 0x0078 }
                            r3.sendEmptyMessage(r0)     // Catch:{ all -> 0x0078 }
                            com.xiaomi.smarthome.device.MiTVDevice r0 = com.xiaomi.smarthome.device.MiTVDevice.this
                            r0.t = r1
                            if (r2 == 0) goto L_0x00a3
                            r2.close()     // Catch:{ Exception -> 0x00a3 }
                        L_0x00a3:
                            return
                        L_0x00a4:
                            com.xiaomi.smarthome.device.MiTVDevice r2 = com.xiaomi.smarthome.device.MiTVDevice.this
                            r2.t = r1
                            if (r3 == 0) goto L_0x00ad
                            r3.close()     // Catch:{ Exception -> 0x00ad }
                        L_0x00ad:
                            throw r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.MiTVDevice.AnonymousClass2.run():void");
                    }
                });
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public boolean isNoneClickableDevice() {
        return this.s == 1 && this.r == 3;
    }

    public void setLaunchParams(Intent intent) {
        super.setLaunchParams(intent);
        if (intent != null && this.location == Device.Location.LOCAL && MitvDeviceRecommendManager.a().e() != null) {
            intent.putExtra("newPictureFile", MitvDeviceRecommendManager.a().e());
            MitvDeviceRecommendManager.a().d();
        }
    }

    public CharSequence getStatusDescription(Context context) {
        int size;
        if (!this.x) {
            a(this.ip);
            this.x = true;
        }
        if (this.r == 3) {
            return context.getString(R.string.offline_device);
        }
        if (this.location == Device.Location.LOCAL && MitvDeviceRecommendManager.a().e() != null) {
            return context.getString(R.string.mitv_new_picture_title);
        }
        if (this.p == null) {
            if (this.q) {
                this.n = MitvDeviceRecommendManager.a().b(this);
            } else {
                this.q = true;
                this.n = MitvDeviceRecommendManager.a().a(this);
            }
            this.o = (int) (Math.random() * 100.0d);
            if (this.n != null && (size = this.n.c.size()) > 0) {
                try {
                    this.p = this.n.c.get(this.o % size);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return this.p != null ? this.p.e : "";
    }

    public boolean d() {
        return this.location == Device.Location.LOCAL;
    }

    private void b(String str, AsyncResponseCallback<JSONObject> asyncResponseCallback) {
        RemoteRouterMitvApi.a().a(CommonApplication.getAppContext(), this.did, this.ip, str, asyncResponseCallback);
    }

    public void a(String str, String str2, String str3, String str4, String str5, AsyncResponseCallback<JSONObject> asyncResponseCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str5)) {
                jSONObject.put("logintype", 2);
                jSONObject.put("vericode", str5);
            } else if (!TextUtils.isEmpty(str4)) {
                jSONObject.put("logintype", 1);
                jSONObject.put("ick", str3);
                jSONObject.put("captchacode", str4);
            } else {
                jSONObject.put("logintype", 0);
            }
            jSONObject.put("name", str);
            jSONObject.put("password", str2);
        } catch (JSONException unused) {
        }
        b(jSONObject.toString(), asyncResponseCallback);
    }

    public void a(final String str, final AsyncResponseCallback<Captchacode> asyncResponseCallback) {
        new Thread() {

            /* renamed from: com.xiaomi.smarthome.device.MiTVDevice$3$AjcClosure1 */
            public class AjcClosure1 extends AroundClosure {
                public AjcClosure1(Object[] objArr) {
                    super(objArr);
                }

                public Object run(Object[] objArr) {
                    Object[] objArr2 = this.state;
                    return AnonymousClass3.a((AnonymousClass3) objArr2[0], (URL) objArr2[1]);
                }
            }

            public void run() {
                try {
                    final Captchacode captchacode = new Captchacode();
                    HttpURLConnection httpURLConnection = (HttpURLConnection) TraceNetTrafficMonitor.b().b(new URL("https://account.xiaomi.com" + str));
                    captchacode.f14884a = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                    Map headerFields = httpURLConnection.getHeaderFields();
                    Iterator it = headerFields.keySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        String str = (String) it.next();
                        if ("Set-Cookie".equals(str)) {
                            List list = (List) headerFields.get(str);
                            if (list != null) {
                                Iterator it2 = list.iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        break;
                                    }
                                    String str2 = (String) it2.next();
                                    if (str2.startsWith("ick=")) {
                                        captchacode.b = str2.substring(4);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    MiTVDevice.this.l.post(new Runnable() {
                        public void run() {
                            if (asyncResponseCallback != null) {
                                asyncResponseCallback.a(captchacode);
                            }
                        }
                    });
                } catch (Exception unused) {
                    MiTVDevice.this.l.post(new Runnable() {
                        public void run() {
                            if (asyncResponseCallback != null) {
                                asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                            }
                        }
                    });
                }
            }

            static final URLConnection a(AnonymousClass3 r0, URL url) {
                return url.openConnection();
            }
        }.start();
    }

    public void logout(final AsyncResponseCallback<Void> asyncResponseCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            int i2 = k;
            k = i2 + 1;
            jSONObject.put("id", i2);
            jSONObject.put("method", "logout");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(0);
            jSONObject.put("params", jSONArray);
        } catch (JSONException unused) {
        }
        a(jSONObject, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(null);
                }
            }

            public void onFailure(Error error) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(error.a(), error.b());
                }
            }
        });
    }

    public boolean e() {
        if (!TextUtils.isEmpty(this.v) && this.v.compareTo("16777496") >= 0) {
            return true;
        }
        return false;
    }

    public void bindDevice(Context context, final Device.IBindDeviceCallback iBindDeviceCallback) {
        new MiTVDeviceLoginHelper().a(context, this, new AsyncResponseCallback<Void>() {
            public void a(Void voidR) {
                if (iBindDeviceCallback != null) {
                    iBindDeviceCallback.b();
                }
            }

            public void a(int i) {
                if (iBindDeviceCallback != null) {
                    iBindDeviceCallback.c();
                }
            }

            public void a(int i, Object obj) {
                if (iBindDeviceCallback != null) {
                    iBindDeviceCallback.c();
                }
            }
        });
    }

    public boolean isNoneOperatableDevice() {
        return isOwner() && !this.isOnline;
    }

    public boolean canRename() {
        return isOwner() && this.isOnline;
    }
}
