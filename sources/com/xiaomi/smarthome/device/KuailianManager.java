package com.xiaomi.smarthome.device;

import android.app.Activity;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.miui.whetstone.WhetstoneManager;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.api.LocalDeviceApi;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.common.util.CheckStatusHandlerTask;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.MiioManager;
import com.xiaomi.smarthome.miio.WifiSetting;
import com.xiaomi.smarthome.miio.camera.match.CameraDevice;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.wificonfig.BaseWifiSetting;
import com.xiaomi.smarthome.wificonfig.WifiDeviceFinder;
import com.xiaomi.smarthome.wificonfig.WifiSettingUap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KuailianManager {
    private static final int e = 101;
    private static final int f = 102;
    private static final int g = 103;
    private static final int h = 104;
    private static final int i = 105;
    private static final int j = 106;
    private static final int k = 70000;
    private static final int l = 70000;
    private static final int m = 5000;
    private static final int n = 2000;
    private static final int o = 30;
    private static KuailianManager r;
    private int A = 0;

    /* renamed from: a  reason: collision with root package name */
    List<Device> f14857a = new ArrayList();
    boolean b = false;
    boolean c = false;
    CheckStatusHandlerTask d;
    private Map<String, Boolean> p = new HashMap();
    /* access modifiers changed from: private */
    public HashMap<String, String> q = new HashMap<>();
    /* access modifiers changed from: private */
    public Handler s = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 102:
                    break;
                case 103:
                    if (KuailianManager.this.s.hasMessages(102)) {
                        int unused = KuailianManager.this.t = message.arg1;
                    }
                    SmartHomeDeviceManager.a().c();
                    return;
                case 104:
                    final Device device = KuailianManager.this.f14857a.get(0);
                    if (!KuailianManager.a(KuailianManager.this.f14857a.get(0))) {
                        KuailianManager.this.a(KuailianManager.this.f14857a.get(0), (AsyncResponseCallback<Void>) new AsyncResponseCallback<Void>() {
                            public void a(Void voidR) {
                                KuailianManager.this.g();
                                KuailianManager.this.q.remove(device.did);
                                int j = SmartHomeDeviceHelper.a().j();
                                if (j != -1) {
                                    WifiSetting.startConnectWifi(j, (WifiManager) SHApplication.getApplication().getSystemService("wifi"));
                                }
                            }

                            public void a(int i) {
                                KuailianManager.this.f();
                            }

                            public void a(int i, Object obj) {
                                KuailianManager.this.f();
                            }
                        });
                        return;
                    }
                    MiioManager.a().a(KuailianManager.this.f14857a.get(0), (AsyncCallback<Integer, Error>) new AsyncCallback<Integer, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Integer num) {
                            if (num.intValue() == 1) {
                                KuailianManager.this.g();
                                KuailianManager.this.q.remove(device.did);
                                int j = SmartHomeDeviceHelper.a().j();
                                if (j != -1) {
                                    WifiSetting.startConnectWifi(j, (WifiManager) SHApplication.getApplication().getSystemService("wifi"));
                                }
                                MiioManager.a().a(KuailianManager.this.f14857a.get(0));
                                return;
                            }
                            KuailianManager.this.h();
                            KuailianManager.this.s.sendEmptyMessage(105);
                            int unused = KuailianManager.this.y = 0;
                            boolean unused2 = KuailianManager.this.z = false;
                        }

                        public void onFailure(Error error) {
                            KuailianManager.this.f();
                        }
                    });
                    return;
                case 105:
                    if (KuailianManager.f(KuailianManager.this) >= 30 || KuailianManager.this.z) {
                        KuailianManager.this.i();
                        Miio.g("handleBindingSuccess, bingd");
                        KuailianManager.this.b = false;
                    } else {
                        KuailianManager.this.s.sendEmptyMessageDelayed(105, 2000);
                    }
                    MiioManager.a().a(KuailianManager.this.f14857a.get(0), (AsyncCallback<Integer, Error>) new AsyncCallback<Integer, Error>() {
                        public void onFailure(Error error) {
                        }

                        /* renamed from: a */
                        public void onSuccess(Integer num) {
                            if (num.intValue() == 1 && !KuailianManager.this.z) {
                                Miio.g("bindDevice miio devices" + num);
                                KuailianManager.this.s.removeMessages(105);
                                boolean unused = KuailianManager.this.z = true;
                                MiioManager.a().a(KuailianManager.this.f14857a.get(0));
                                KuailianManager.this.a(KuailianManager.this.f14857a, true);
                                int j = SmartHomeDeviceHelper.a().j();
                                if (j != -1) {
                                    WifiSetting.startConnectWifi(j, (WifiManager) SHApplication.getApplication().getSystemService("wifi"));
                                }
                            }
                        }
                    });
                    return;
                case 106:
                    int unused2 = KuailianManager.this.t = message.arg1;
                    SmartHomeDeviceManager.a().c();
                    MiioLocalAPI.a();
                    try {
                        WhetstoneManager.wifiSmartConfigStop();
                        break;
                    } catch (Exception | NoSuchMethodError unused3) {
                        break;
                    }
                default:
                    return;
            }
            KuailianManager.this.s.removeMessages(101);
            KuailianManager.this.s.removeMessages(102);
            if (KuailianManager.this.d != null) {
                KuailianManager.this.d.b();
            }
            if (KuailianManager.this.t == 0) {
                KuailianManager.this.i();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("kuailian_result", false);
                    jSONObject.put("reason", "find 0 device");
                    CoreApi.a().a(StatType.EVENT, "kuailian_result", jSONObject.toString(), (String) null, false);
                } catch (JSONException unused4) {
                }
                MyLog.f("kuailian_result false, find 0 device");
                KuailianManager.this.d();
            } else if (KuailianManager.this.c) {
                if (KuailianManager.this.f14857a.get(0).isBinded()) {
                    boolean unused5 = KuailianManager.this.z = true;
                    SmartHomeDeviceManager.a().b(KuailianManager.this.f14857a.get(0));
                    MiioManager.a().a(KuailianManager.this.f14857a.get(0));
                    KuailianManager.this.a(KuailianManager.this.f14857a, true);
                    int j = SmartHomeDeviceHelper.a().j();
                    if (j != -1) {
                        WifiSetting.startConnectWifi(j, (WifiManager) SHApplication.getApplication().getSystemService("wifi"));
                    }
                } else {
                    KuailianManager.this.e();
                }
                if (WifiDeviceFinder.d().e() == null) {
                    WifiDeviceFinder.d().a(SHApplication.getAppContext());
                    WifiDeviceFinder.d().a();
                }
                WifiDeviceFinder.a(KuailianManager.this.u);
            } else {
                KuailianManager.this.b = false;
                KuailianManager.this.a(KuailianManager.this.f14857a, true);
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("kuailian_result", true);
                    CoreApi.a().a(StatType.EVENT, "kuailian_result", jSONObject2.toString(), (String) null, false);
                } catch (JSONException unused6) {
                }
            }
            long currentTimeMillis = System.currentTimeMillis() - KuailianManager.this.k();
            try {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("isApConnection", KuailianManager.this.m());
                jSONObject3.put("isSingleDevice", KuailianManager.this.c);
                jSONObject3.put("device_model", KuailianManager.this.l());
                jSONObject3.put("connection_count", KuailianManager.this.t);
                jSONObject3.put("bssid", WifiUtil.b(SHApplication.getAppContext()));
                jSONObject3.put(DeviceTagInterface.e, WifiUtil.c(SHApplication.getAppContext()));
                JSONArray jSONArray = new JSONArray();
                if (!KuailianManager.this.c) {
                    for (Long longValue : KuailianManager.this.w) {
                        jSONArray.put(System.currentTimeMillis() - longValue.longValue());
                    }
                    if (KuailianManager.this.w.size() > 0) {
                        jSONObject3.put("conn_detail_time", jSONArray);
                    }
                }
                CoreApi.a().a(StatType.TIME, "get_connection_statistic", Long.toString(currentTimeMillis), jSONObject3.toString(), false);
            } catch (JSONException unused7) {
            }
        }
    };
    /* access modifiers changed from: private */
    public int t = 0;
    /* access modifiers changed from: private */
    public ScanResult u;
    private WeakReference<BaseWifiSetting> v;
    /* access modifiers changed from: private */
    public List<Long> w = new ArrayList();
    private long x = 0;
    /* access modifiers changed from: private */
    public int y = 0;
    /* access modifiers changed from: private */
    public boolean z = false;

    static /* synthetic */ int f(KuailianManager kuailianManager) {
        int i2 = kuailianManager.y;
        kuailianManager.y = i2 + 1;
        return i2;
    }

    public static KuailianManager a() {
        if (r == null) {
            r = new KuailianManager();
        }
        return r;
    }

    public void a(Activity activity, boolean z2, ScanResult scanResult, final String str) {
        this.f14857a.clear();
        this.b = true;
        if (scanResult != null) {
            this.c = true;
            this.u = scanResult;
        } else {
            this.c = z2;
        }
        this.v = new WeakReference<>((BaseWifiSetting) activity);
        this.w.clear();
        this.t = 0;
        this.s.sendEmptyMessage(101);
        this.s.sendEmptyMessageDelayed(102, 70000);
        final List<DeviceSearch<?>> b2 = SmartHomeDeviceManager.a().b();
        for (DeviceSearch<?> f2 : b2) {
            f2.f();
        }
        if (this.d != null) {
            this.d.b();
        }
        this.d = new CheckStatusHandlerTask(false);
        this.d.a((Runnable) new Runnable() {
            public void run() {
                LogUtilGrey.a("Search", "StopSearch");
            }
        }, 70000);
        this.A = 0;
        this.d.a((CheckStatusHandlerTask.MyRunnable) new CheckStatusHandlerTask.MyRunnable() {
            public void a(Handler handler) {
                DeviceApi.getInstance().getNewDevice(SHApplication.getAppContext(), str, false, KuailianManager.this.u != null ? KuailianManager.this.u.BSSID : null, (String) null, (String) null, (String) null, new AsyncCallback<List<Device>, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(List<Device> list) {
                        for (DeviceSearch a2 : b2) {
                            a2.a((Collection<? extends Device>) list, (DeviceSearch.SearchDeviceListener) new DeviceSearch.SearchDeviceListener() {
                                public <T> void a(List<T> list) {
                                    int size = KuailianManager.this.f14857a.size();
                                    for (T t : list) {
                                        Device device = null;
                                        if (KuailianManager.this.f14857a.size() > 0) {
                                            Iterator<Device> it = KuailianManager.this.f14857a.iterator();
                                            while (true) {
                                                if (it.hasNext()) {
                                                    if (it.next().did.equalsIgnoreCase(t.did)) {
                                                        device = t;
                                                        break;
                                                    }
                                                } else {
                                                    break;
                                                }
                                            }
                                        }
                                        if (device == null) {
                                            if (!KuailianManager.this.c && !KuailianManager.a((Device) t)) {
                                                KuailianManager.this.b((Device) t);
                                            }
                                            KuailianManager.this.f14857a.add(t);
                                            t.scrollTo = true;
                                            t.isNew = true;
                                            DeviceFinder.a().c(t.did);
                                            KuailianManager.this.w.add(Long.valueOf(System.currentTimeMillis()));
                                        }
                                    }
                                    if (size != KuailianManager.this.f14857a.size()) {
                                        Message obtainMessage = KuailianManager.this.s.obtainMessage();
                                        if (KuailianManager.this.c) {
                                            obtainMessage.what = 106;
                                        } else {
                                            obtainMessage.what = 103;
                                            KuailianManager.this.a(KuailianManager.this.f14857a);
                                        }
                                        obtainMessage.arg1 = KuailianManager.this.f14857a.size();
                                        KuailianManager.this.s.sendMessage(obtainMessage);
                                    }
                                }
                            });
                        }
                    }
                });
                KuailianManager.this.d.c();
            }
        }, 5000);
        this.d.a();
        this.x = System.currentTimeMillis();
    }

    public boolean a(String str) {
        if (this.p.containsKey(str)) {
            return this.p.get(str).booleanValue();
        }
        return true;
    }

    public void b() {
        if (this.b) {
            this.b = false;
            this.s.removeMessages(102);
            MiioLocalAPI.a();
            this.d.b();
        }
    }

    public boolean c() {
        return this.b;
    }

    /* access modifiers changed from: private */
    public void b(final Device device) {
        if (!(device instanceof CameraDevice)) {
            if (this.q.containsKey(device.did)) {
                device.token = this.q.get(device.did);
                a(device, (AsyncResponseCallback<Void>) null);
                return;
            }
            LocalDeviceApi.getInstance().fetchTokenByIp(device.ip, new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    device.token = str;
                    KuailianManager.this.a(device, (AsyncResponseCallback<Void>) null);
                }

                public void onFailure(Error error) {
                    KuailianManager.this.o();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.q.containsKey(this.f14857a.get(0).did)) {
            this.f14857a.get(0).token = this.q.get(this.f14857a.get(0).did);
            if (!TextUtils.isEmpty(this.f14857a.get(0).token)) {
                this.s.sendEmptyMessageDelayed(104, 0);
                return;
            }
        }
        final Handler handler = new Handler(Looper.getMainLooper());
        LocalDeviceApi.getInstance().fetchTokenByIp(this.f14857a.get(0).ip, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                KuailianManager.this.f14857a.get(0).token = str;
                KuailianManager.this.s.sendEmptyMessageDelayed(104, 0);
            }

            public void onFailure(Error error) {
                handler.post(new Runnable() {
                    public void run() {
                        KuailianManager.this.o();
                        KuailianManager.this.f();
                        KuailianManager.this.b = false;
                    }
                });
            }
        });
    }

    public static boolean a(Device device) {
        PluginRecord d2 = CoreApi.a().d(device.model);
        return d2 != null && d2.u();
    }

    /* access modifiers changed from: private */
    public void a(final Device device, final AsyncResponseCallback<Void> asyncResponseCallback) {
        if ((device instanceof CameraDevice) && asyncResponseCallback != null) {
            asyncResponseCallback.a(-1);
        }
        MiioManager.a().a(device, (AsyncResponseCallback<Void>) new AsyncResponseCallback<Void>() {
            public void a(Void voidR) {
                SmartHomeDeviceManager.a().b(device);
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(null);
                }
                MiioManager.a().a(device);
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
    public void f() {
        n();
        a(this.f14857a, true);
        this.b = false;
    }

    /* access modifiers changed from: private */
    public void g() {
        this.b = false;
        a(this.f14857a, true);
    }

    /* access modifiers changed from: private */
    public void h() {
        BaseWifiSetting baseWifiSetting;
        if (this.v != null && (baseWifiSetting = (BaseWifiSetting) this.v.get()) != null && !baseWifiSetting.isFinishing()) {
            baseWifiSetting.onDeviceConnectionSuccessBind();
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        BaseWifiSetting baseWifiSetting;
        if (this.v != null && (baseWifiSetting = (BaseWifiSetting) this.v.get()) != null && !baseWifiSetting.isFinishing()) {
            baseWifiSetting.onDeviceConnectionError();
        }
    }

    private void j() {
        BaseWifiSetting baseWifiSetting;
        if (this.v != null && (baseWifiSetting = (BaseWifiSetting) this.v.get()) != null && !baseWifiSetting.isFinishing()) {
            baseWifiSetting.onDeviceConnectionSuccess();
        }
    }

    /* access modifiers changed from: private */
    public void a(List<Device> list, boolean z2) {
        BaseWifiSetting baseWifiSetting;
        if (this.v != null && (baseWifiSetting = (BaseWifiSetting) this.v.get()) != null && !baseWifiSetting.isFinishing()) {
            baseWifiSetting.onDeviceConnectionSuccess(list, z2);
        }
    }

    /* access modifiers changed from: private */
    public long k() {
        BaseWifiSetting baseWifiSetting;
        if (this.v == null || (baseWifiSetting = (BaseWifiSetting) this.v.get()) == null || baseWifiSetting.isFinishing()) {
            return 0;
        }
        return baseWifiSetting.getStartTime();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = (com.xiaomi.smarthome.wificonfig.BaseWifiSetting) r2.v.get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String l() {
        /*
            r2 = this;
            java.lang.ref.WeakReference<com.xiaomi.smarthome.wificonfig.BaseWifiSetting> r0 = r2.v
            if (r0 == 0) goto L_0x0019
            java.lang.ref.WeakReference<com.xiaomi.smarthome.wificonfig.BaseWifiSetting> r0 = r2.v
            java.lang.Object r0 = r0.get()
            com.xiaomi.smarthome.wificonfig.BaseWifiSetting r0 = (com.xiaomi.smarthome.wificonfig.BaseWifiSetting) r0
            if (r0 == 0) goto L_0x0019
            boolean r1 = r0.isFinishing()
            if (r1 != 0) goto L_0x0019
            java.lang.String r0 = r0.getConnDeviceModel()
            return r0
        L_0x0019:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.KuailianManager.l():java.lang.String");
    }

    /* access modifiers changed from: private */
    public void a(List<Device> list) {
        BaseWifiSetting baseWifiSetting;
        if (this.v != null && (baseWifiSetting = (BaseWifiSetting) this.v.get()) != null && !baseWifiSetting.isFinishing()) {
            baseWifiSetting.onDeviceFindDevices(list.size());
            baseWifiSetting.onDeviceConnectionSuccess(list, false);
        }
    }

    /* access modifiers changed from: private */
    public boolean m() {
        BaseWifiSetting baseWifiSetting;
        if (this.v == null || (baseWifiSetting = (BaseWifiSetting) this.v.get()) == null) {
            return false;
        }
        return baseWifiSetting instanceof WifiSettingUap;
    }

    private void n() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("kuailian_result", false);
            jSONObject.put("reason", "network bind error");
            CoreApi.a().a(StatType.EVENT, "kuailian_result", jSONObject.toString(), (String) null, false);
        } catch (JSONException unused) {
        }
        Miio.g("kuailian_result false, network bind error");
        MyLog.f("kuailian_result false, network bind error");
    }

    /* access modifiers changed from: private */
    public void o() {
        CoreApi.a().a(StatType.TIME, "fetch_token_error", (String) null, (String) null, false);
        String str = "fetchToken onFailure ip = " + this.f14857a.get(0).ip + " did = " + this.f14857a.get(0).did;
        String b2 = WifiUtil.b(SHApplication.getAppContext());
        if (b2 != null) {
            str = str + " bssid = " + b2;
        }
        Miio.g(str);
        MyLog.f(str);
    }

    public void d() {
        if (!TextUtils.isEmpty(WifiUtil.b(SHApplication.getAppContext()))) {
            this.p.put(WifiUtil.b(SHApplication.getAppContext()), false);
        }
    }

    public void a(String str, String str2) {
        this.q.put(str, str2);
    }

    public String b(String str) {
        return this.q.get(str);
    }

    public void c(String str) {
        this.q.remove(str);
    }
}
