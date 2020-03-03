package com.xiaomi.smarthome.smartconfig;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.miui.whetstone.WhetstoneManager;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.api.LocalDeviceApi;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.devicelistswitch.model.DeviceListSwitchManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.common.util.CheckStatusHandlerTask;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.MiioManager;
import com.xiaomi.smarthome.miio.WifiSetting;
import com.xiaomi.smarthome.miio.camera.match.CameraDevice;
import com.xiaomi.smarthome.smartconfig.step.ConfigStep;
import com.xiaomi.smarthome.wificonfig.WifiDeviceFinder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceFinder {
    private static final int b = 102;
    private static final int c = 103;
    private static final int d = 104;
    private static final int e = 105;
    private static final int f = 106;
    private static final int g = 107;
    private static final int h = 50000;
    private static final int i = 5000;
    private static DeviceFinder r;

    /* renamed from: a  reason: collision with root package name */
    boolean f22274a = false;
    private Map<String, Boolean> j = new HashMap();
    /* access modifiers changed from: private */
    public HashMap<String, String> k = new HashMap<>();
    /* access modifiers changed from: private */
    public List<Device> l = new ArrayList();
    /* access modifiers changed from: private */
    public ScanResult m;
    private ConfigStep.DeviceFinderCallback n;
    /* access modifiers changed from: private */
    public CheckStatusHandlerTask o;
    private long p = 0;
    /* access modifiers changed from: private */
    public String q;
    /* access modifiers changed from: private */
    public AsyncResponseCallback<String> s = new AsyncResponseCallback<String>() {
        public void a(String str) {
            DeviceFinder.this.l();
            DeviceFinder.this.k.remove(str);
            int j = SmartHomeDeviceHelper.a().j();
            if (j != -1) {
                WifiSetting.startConnectWifi(j, (WifiManager) SHApplication.getApplication().getSystemService("wifi"));
            }
        }

        public void a(int i) {
            DeviceFinder.this.k();
        }

        public void a(int i, Object obj) {
            DeviceFinder.this.k();
        }
    };
    /* access modifiers changed from: private */
    public Handler t = new Handler(SHApplication.getAppContext().getMainLooper()) {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 102:
                    break;
                case 103:
                    SmartHomeDeviceManager.a().c();
                    return;
                case 104:
                    DeviceFinder.this.a((Device) DeviceFinder.this.l.get(0), (AsyncResponseCallback<String>) DeviceFinder.this.s);
                    return;
                case 105:
                    DeviceFinder.this.a((Device) DeviceFinder.this.l.get(0), (AsyncResponseCallback<String>) DeviceFinder.this.s);
                    return;
                case 106:
                    MiioLocalAPI.a();
                    try {
                        WhetstoneManager.wifiSmartConfigStop();
                        break;
                    } catch (Exception | NoSuchMethodError unused) {
                        break;
                    }
                case 107:
                    DeviceFinder.this.a(message.arg1);
                    return;
                default:
                    return;
            }
            DeviceFinder.this.t.removeMessages(102);
            Log.e("SmartConfig", "Find Device Time out size:" + DeviceFinder.this.l.size());
            if (DeviceFinder.this.o != null) {
                DeviceFinder.this.o.b();
            }
            boolean z = true;
            if (DeviceFinder.this.l.size() == 0) {
                DeviceFinder.this.f22274a = false;
                DeviceFinder.this.m();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("kuailian_result", false);
                    jSONObject.put("reason", "find 0 device");
                    CoreApi.a().a(StatType.EVENT, "kuailian_result", jSONObject.toString(), (String) null, false);
                } catch (JSONException unused2) {
                }
                MyLog.f("kuailian_result false, find 0 device");
                DeviceFinder.this.d();
            } else {
                Device device = (Device) DeviceFinder.this.l.get(0);
                if (device.isBinded()) {
                    Log.e("SmartConfig", "onSuccess add device did:" + device);
                    device.setOwner(true);
                    MiioManager.a().a(device);
                    SmartHomeDeviceManager.a().b(device);
                    SmartHomeDeviceManager.a().c();
                    DeviceFinder.this.s.a(device.did);
                } else {
                    Log.e("SmartConfig", "startBindDevice did:" + device);
                    DeviceFinder.this.a(device, (AsyncResponseCallback<String>) DeviceFinder.this.s);
                }
                if (WifiDeviceFinder.d().e() == null) {
                    WifiDeviceFinder.d().a(SHApplication.getAppContext());
                    WifiDeviceFinder.d().a();
                }
                WifiDeviceFinder.a(DeviceFinder.this.m);
            }
            long currentTimeMillis = System.currentTimeMillis();
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("isApConnection", false);
                if (DeviceFinder.this.l == null) {
                    z = false;
                }
                jSONObject2.put("isSingleDevice", z);
                jSONObject2.put("device_model", "");
                jSONObject2.put("connection_count", DeviceFinder.this.l.size());
                jSONObject2.put("bssid", WifiUtil.b(SHApplication.getAppContext()));
                jSONObject2.put(DeviceTagInterface.e, WifiUtil.c(SHApplication.getAppContext()));
                CoreApi.a().a(StatType.TIME, "get_connection_statistic", Long.toString(currentTimeMillis), jSONObject2.toString(), false);
            } catch (JSONException unused3) {
            }
        }
    };

    public interface DeviceFinderCallback2 extends ConfigStep.DeviceFinderCallback {
        void a(int i);
    }

    public interface IDeviceLooper {
        void a(Context context, String[] strArr, String str, String str2, String str3, AsyncCallback<List<Device>, Error> asyncCallback);
    }

    public static DeviceFinder a() {
        if (r == null) {
            r = new DeviceFinder();
        }
        return r;
    }

    public void a(ConfigStep.DeviceFinderCallback deviceFinderCallback, String str, String str2, String str3, IDeviceLooper iDeviceLooper) {
        if (!this.f22274a) {
            this.l.clear();
            this.f22274a = true;
            this.q = str;
            this.n = deviceFinderCallback;
            this.p = System.currentTimeMillis();
            this.t.sendEmptyMessageDelayed(102, 50000);
            final List<DeviceSearch<?>> b2 = SmartHomeDeviceManager.a().b();
            for (DeviceSearch<?> f2 : b2) {
                f2.f();
            }
            if (this.o != null) {
                this.o.b();
            }
            this.o = new CheckStatusHandlerTask(false);
            final IDeviceLooper iDeviceLooper2 = iDeviceLooper;
            final String str4 = str2;
            final String str5 = str3;
            final ConfigStep.DeviceFinderCallback deviceFinderCallback2 = deviceFinderCallback;
            this.o.a((CheckStatusHandlerTask.MyRunnable) new CheckStatusHandlerTask.MyRunnable() {
                public void a(Handler handler) {
                    BluetoothMyLogger.d(String.format("getNewDevice: ", new Object[0]));
                    iDeviceLooper2.a(SHApplication.getAppContext(), (String[]) null, DeviceFinder.this.m != null ? DeviceFinder.this.m.BSSID : null, str4, str5, new AsyncCallback<List<Device>, Error>() {
                        /* renamed from: a */
                        public void onSuccess(List<Device> list) {
                            Object[] objArr = new Object[1];
                            objArr[0] = Integer.valueOf(list != null ? list.size() : 0);
                            BluetoothMyLogger.d(String.format("onSuccess size = %d", objArr));
                            if (list != null) {
                                for (Device next : list) {
                                    BluetoothMyLogger.d(String.format(">>> name = %s, did = %s, model = %s", new Object[]{next.name, next.did, next.model}));
                                }
                            }
                            for (final DeviceSearch deviceSearch : b2) {
                                deviceSearch.a((Collection<? extends Device>) list, (DeviceSearch.SearchDeviceListener) new DeviceSearch.SearchDeviceListener() {
                                    public <T> void a(List<T> list) {
                                        BluetoothMyLogger.d(String.format("%s onSearchDeviceSuccess, size = %d", new Object[]{deviceSearch, Integer.valueOf(list.size())}));
                                        int size = DeviceFinder.this.l.size();
                                        ArrayList arrayList = new ArrayList();
                                        Iterator<T> it = list.iterator();
                                        while (true) {
                                            Device device = null;
                                            if (!it.hasNext()) {
                                                break;
                                            }
                                            Device device2 = (Device) it.next();
                                            if (device2.model.equalsIgnoreCase(DeviceFinder.this.q)) {
                                                if (DeviceFinder.this.l.size() > 0) {
                                                    Iterator it2 = DeviceFinder.this.l.iterator();
                                                    while (true) {
                                                        if (it2.hasNext()) {
                                                            if (((Device) it2.next()).did.equalsIgnoreCase(device2.did)) {
                                                                device = device2;
                                                                break;
                                                            }
                                                        } else {
                                                            break;
                                                        }
                                                    }
                                                }
                                                if (device == null) {
                                                    DeviceFinder.this.l.add(device2);
                                                    device2.scrollTo = true;
                                                    device2.isNew = true;
                                                    SmartHomeDeviceManager.a().p(device2.did);
                                                }
                                                arrayList.add(device2.did);
                                            }
                                        }
                                        DeviceListSwitchManager.a().a((List<String>) arrayList, (AsyncCallback<Void, Error>) null);
                                        if (size != DeviceFinder.this.l.size()) {
                                            Message obtainMessage = DeviceFinder.this.t.obtainMessage();
                                            obtainMessage.what = 106;
                                            obtainMessage.arg1 = DeviceFinder.this.l.size();
                                            DeviceFinder.this.t.sendMessage(obtainMessage);
                                        }
                                    }
                                });
                            }
                        }

                        public void onFailure(Error error) {
                            BluetoothMyLogger.d(String.format("onFailure: %s", new Object[]{error}));
                            if (deviceFinderCallback2 != null && (deviceFinderCallback2 instanceof DeviceFinderCallback2)) {
                                ((DeviceFinderCallback2) deviceFinderCallback2).a(error.a());
                            }
                        }
                    });
                    DeviceFinder.this.o.c();
                }
            }, 5000);
            this.o.a();
        }
    }

    public void a(ConfigStep.DeviceFinderCallback deviceFinderCallback, ScanResult scanResult, String str, String str2, String str3) {
        this.m = scanResult;
        SmartHomeDeviceManager.a().q(str);
        a(deviceFinderCallback, this.m == null ? null : this.m.BSSID, str, str2, str3);
    }

    public void a(ConfigStep.DeviceFinderCallback deviceFinderCallback, String str, String str2, String str3, String str4) {
        if (!this.f22274a) {
            this.l.clear();
            this.f22274a = true;
            this.n = deviceFinderCallback;
            this.p = System.currentTimeMillis();
            this.t.sendEmptyMessageDelayed(102, 50000);
            Log.e("SmartConfig", "Start Find Device");
            final List<DeviceSearch<?>> b2 = SmartHomeDeviceManager.a().b();
            for (DeviceSearch<?> f2 : b2) {
                f2.f();
            }
            if (this.o != null) {
                this.o.b();
            }
            this.o = new CheckStatusHandlerTask(false);
            final String str5 = str;
            final String str6 = str4;
            final String str7 = str2;
            this.o.a((CheckStatusHandlerTask.MyRunnable) new CheckStatusHandlerTask.MyRunnable() {
                public void a(Handler handler) {
                    String str = str5;
                    String str2 = str6;
                    DeviceApi.getInstance().getNewDevice(SHApplication.getAppContext(), str7, false, str, SmartConfigDataProvider.a().b(), SmartConfigDataProvider.a().c(), str2, new AsyncCallback<List<Device>, Error>() {
                        /* renamed from: a */
                        public void onSuccess(List<Device> list) {
                            for (DeviceSearch a2 : b2) {
                                a2.a((Collection<? extends Device>) list, (DeviceSearch.SearchDeviceListener) new DeviceSearch.SearchDeviceListener() {
                                    public <T> void a(List<T> list) {
                                        int size = DeviceFinder.this.l.size();
                                        Iterator<T> it = list.iterator();
                                        while (true) {
                                            Device device = null;
                                            if (!it.hasNext()) {
                                                break;
                                            }
                                            Device device2 = (Device) it.next();
                                            if (DeviceFinder.this.l.size() > 0) {
                                                Iterator it2 = DeviceFinder.this.l.iterator();
                                                while (true) {
                                                    if (it2.hasNext()) {
                                                        if (((Device) it2.next()).did.equalsIgnoreCase(device2.did)) {
                                                            device = device2;
                                                            break;
                                                        }
                                                    } else {
                                                        break;
                                                    }
                                                }
                                            }
                                            if (device == null) {
                                                DeviceFinder.this.l.add(device2);
                                                device2.scrollTo = true;
                                                device2.isNew = true;
                                            }
                                            SmartHomeDeviceManager.a().p(device2.did);
                                        }
                                        DeviceListSwitchManager.a().b(DeviceFinder.this.l, (AsyncCallback<Void, Error>) null);
                                        if (size != DeviceFinder.this.l.size()) {
                                            Message obtainMessage = DeviceFinder.this.t.obtainMessage();
                                            if (DeviceFinder.this.l != null) {
                                                obtainMessage.what = 106;
                                            } else {
                                                obtainMessage.what = 103;
                                            }
                                            obtainMessage.arg1 = DeviceFinder.this.l.size();
                                            DeviceFinder.this.t.sendMessage(obtainMessage);
                                        }
                                    }
                                });
                            }
                        }

                        public void onFailure(Error error) {
                            if (error.a() == -6) {
                                Message obtainMessage = DeviceFinder.this.t.obtainMessage();
                                obtainMessage.what = 107;
                                obtainMessage.arg1 = error.a();
                                DeviceFinder.this.t.sendMessage(obtainMessage);
                            }
                        }
                    });
                    DeviceFinder.this.o.c();
                }
            }, 5000);
            this.o.a();
        }
    }

    public void b() {
        if (this.f22274a) {
            this.f22274a = false;
            this.t.removeMessages(102);
            MiioLocalAPI.a();
            this.o.b();
        }
    }

    /* access modifiers changed from: private */
    public void a(final Device device, final AsyncResponseCallback<String> asyncResponseCallback) {
        if (!(device instanceof CameraDevice)) {
            if (this.k.containsKey(device.did)) {
                device.token = this.k.get(device.did);
                b(device, (AsyncResponseCallback<String>) null);
                return;
            }
            LocalDeviceApi.getInstance().fetchTokenByIp(device.ip, new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    device.token = str;
                    DeviceFinder.this.b(device, asyncResponseCallback);
                }

                public void onFailure(Error error) {
                    DeviceFinder.this.o();
                    asyncResponseCallback.a(-1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b(final Device device, AsyncResponseCallback<String> asyncResponseCallback) {
        if ((device instanceof CameraDevice) && asyncResponseCallback != null) {
            asyncResponseCallback.a(-1);
        }
        MiioManager.a().a(device, (AsyncCallback<Integer, Error>) new AsyncCallback<Integer, Error>() {
            /* renamed from: a */
            public void onSuccess(Integer num) {
                if (num.intValue() == 1) {
                    device.setOwner(true);
                    DeviceFinder.this.s.a(device.did);
                    MiioManager.a().a(device);
                    return;
                }
                DeviceFinder.this.s.a(-1);
            }

            public void onFailure(Error error) {
                DeviceFinder.this.k();
            }
        });
    }

    public void c() {
        this.n = null;
    }

    /* access modifiers changed from: private */
    public void k() {
        n();
        a(this.l, true);
        this.f22274a = false;
    }

    /* access modifiers changed from: private */
    public void l() {
        this.f22274a = false;
        a(this.l, true);
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        if (this.n != null && (this.n instanceof DeviceFinderCallback2)) {
            ((DeviceFinderCallback2) this.n).a(i2);
        } else if (i2 == -6) {
            Toast.makeText(SHApplication.getAppContext(), SHApplication.getAppContext().getString(R.string.have_bind_by_other), 0).show();
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        if (this.n != null) {
            this.n.a();
            this.n = null;
            return;
        }
        Device k2 = DeviceFactory.k(DeviceFactory.a(this.m));
        if (k2 != null) {
            Toast.makeText(SHApplication.getAppContext(), String.format(SHApplication.getAppContext().getString(R.string.smart_config_toast_connect_failed), new Object[]{k2.name}), 0).show();
        }
        SmartHomeDeviceManager.a().s();
    }

    private void a(List<Device> list, boolean z) {
        if (this.l != null && this.l.size() != 0) {
            SmartConfigDataProvider.a().b(SmartConfigDataProvider.w, this.l.get(0));
            if (list != null && list.size() > 0) {
                SmartHomeDeviceManager.a().b(list.get(0));
            }
            if (this.n != null) {
                this.n.a(this.l);
                this.n = null;
                return;
            }
            Toast.makeText(SHApplication.getAppContext(), String.format(SHApplication.getAppContext().getString(R.string.smart_config_toast_connect_success), new Object[]{this.l.get(0).name}), 0).show();
            SmartHomeDeviceManager.a().s();
        } else if (this.n != null) {
            this.n.a(this.l);
            this.n = null;
        }
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
    }

    public void d() {
        if (!TextUtils.isEmpty(WifiUtil.b(SHApplication.getAppContext()))) {
            this.j.put(WifiUtil.b(SHApplication.getAppContext()), false);
        }
    }

    public void a(String str, String str2) {
        this.k.put(str, str2);
    }

    public String a(String str) {
        return this.k.get(str);
    }

    public void b(String str) {
        this.k.remove(str);
    }

    public boolean e() {
        return this.f22274a;
    }

    public long f() {
        return this.p;
    }

    public void a(ConfigStep.DeviceFinderCallback deviceFinderCallback) {
        this.n = deviceFinderCallback;
    }

    public void g() {
        this.p = 0;
        this.m = null;
        SmartHomeDeviceManager.a().q((String) null);
    }

    public String h() {
        return SmartHomeDeviceManager.a().y();
    }

    public String i() {
        return SmartHomeDeviceManager.a().y();
    }

    public void j() {
        SmartHomeDeviceManager.a().z();
    }

    public void c(String str) {
        SmartHomeDeviceManager.a().p(str);
    }
}
