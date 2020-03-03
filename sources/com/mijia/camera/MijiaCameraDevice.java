package com.mijia.camera;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.coloros.mcssdk.mode.CommandMessage;
import com.mijia.camera.Utils.Util;
import com.mijia.camera.nas.NASManager;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.alarm.AlarmManager;
import com.mijia.model.alarm.AlarmManagerV2;
import com.mijia.model.alarm.AlarmNetUtils;
import com.mijia.model.alarm.FDSFileManager;
import com.mijia.model.alarmcloud.AlarmCloudManager;
import com.mijia.model.local.LocalFileManager;
import com.mijia.model.local.LocalSettings;
import com.mijia.model.property.CameraPropertyBase;
import com.mijia.model.property.PropertyManger;
import com.mijia.model.sdcard.DownloadSdCardManager;
import com.mijia.model.sdcard.SdcardManager;
import com.mijia.model.sdcard.SdcardManagerEx;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.plugin.DeviceConstant;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MijiaCameraDevice extends CameraDevice {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7889a = "MijiaCameraDevice";
    private static final int f = -30;
    private static final int g = -85;
    private static ArrayList<MijiaCameraDevice> u = new ArrayList<>();
    private LocalFileManager h;
    private SdcardManager i;
    private SdcardManagerEx j;
    private LocalSettings k;
    private DownloadSdCardManager l;
    private CameraPropertyBase m;
    private AlarmManager n;
    private AlarmManagerV2 o;
    private FDSFileManager p;
    private NASManager q;
    private PropertyManger r;
    private boolean s;
    private AlarmCloudManager t;

    public synchronized AlarmCloudManager a() {
        if (this.t == null) {
            this.t = new AlarmCloudManager(this);
        }
        return this.t;
    }

    public synchronized LocalFileManager b() {
        if (this.h == null) {
            this.h = new LocalFileManager(this);
        }
        return this.h;
    }

    public synchronized SdcardManager c() {
        if (this.i == null) {
            this.i = new SdcardManager(this);
        }
        return this.i;
    }

    public synchronized SdcardManagerEx d() {
        if (this.j == null) {
            this.j = new SdcardManagerEx(this);
        }
        return this.j;
    }

    public synchronized LocalSettings e() {
        if (this.k == null) {
            this.k = new LocalSettings(getDid(), r(), getModel());
        }
        return this.k;
    }

    public synchronized CameraPropertyBase f() {
        return this.m;
    }

    public synchronized PropertyManger g() {
        if (this.r == null) {
            this.r = new PropertyManger(this);
        }
        return this.r;
    }

    public synchronized DownloadSdCardManager h() {
        if (this.l == null) {
            this.l = new DownloadSdCardManager(this);
        }
        return this.l;
    }

    public synchronized AlarmManager i() {
        if (this.n == null) {
            this.n = new AlarmManager(this);
        }
        return this.n;
    }

    public synchronized AlarmManagerV2 j() {
        if (this.o == null) {
            this.o = new AlarmManagerV2(this);
        }
        return this.o;
    }

    public synchronized FDSFileManager k() {
        if (this.p == null) {
            this.p = new FDSFileManager(this);
        }
        return this.p;
    }

    public synchronized NASManager l() {
        if (this.q == null) {
            this.q = new NASManager(this);
        }
        return this.q;
    }

    public static synchronized MijiaCameraDevice a(DeviceStat deviceStat) {
        synchronized (MijiaCameraDevice.class) {
            Iterator<MijiaCameraDevice> it = u.iterator();
            while (it.hasNext()) {
                MijiaCameraDevice next = it.next();
                if (deviceStat.did.equals(next.getDid())) {
                    next.mDeviceStat = deviceStat;
                    return next;
                }
            }
            MijiaCameraDevice mijiaCameraDevice = new MijiaCameraDevice(deviceStat);
            u.add(mijiaCameraDevice);
            return mijiaCameraDevice;
        }
    }

    public static synchronized MijiaCameraDevice a(String str) {
        synchronized (MijiaCameraDevice.class) {
            Iterator<MijiaCameraDevice> it = u.iterator();
            while (it.hasNext()) {
                MijiaCameraDevice next = it.next();
                if (str.equals(next.getDid())) {
                    return next;
                }
            }
            return null;
        }
    }

    public boolean m() {
        return (this.mDeviceStat == null || this.mDeviceStat.isSetPinCode == 0) ? false : true;
    }

    public boolean n() {
        return this.s;
    }

    public void a(boolean z) {
        this.s = z;
    }

    public boolean o() {
        return DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(getModel());
    }

    public String p() {
        try {
            String str = this.mDeviceStat.extrainfo;
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            try {
                return new JSONObject(str).optString("fw_version");
            } catch (JSONException e) {
                LogUtil.b(f7889a, "getDeviceVersion JSONException:" + e.getLocalizedMessage());
                return "";
            }
        } catch (Exception e2) {
            LogUtil.b(f7889a, "getDeviceVersion Exception:" + e2.getLocalizedMessage());
            return "";
        }
    }

    public MijiaCameraDevice(DeviceStat deviceStat) {
        super(deviceStat);
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = false;
        this.t = null;
        this.m = new CameraProperties(this);
        String p2 = p();
        LogUtil.a(Tag.b, "deviceVersion:" + p2);
        if (!TextUtils.isEmpty(p2) && ((("chuangmi.camera.ipc009".equals(deviceStat.model) || "chuangmi.camera.ipc019".equals(deviceStat.model)) && Util.b(p2, "3.5.1_0399") > 0) || DeviceConstant.CHUANGMI_CAMERA_021.equals(deviceStat.model))) {
            this.s = true;
        } else if (!TextUtils.isEmpty(p2) && "mijia.camera.v3".equals(deviceStat.model) && Util.c(p2, "3.5.1_0070") > 0) {
            this.s = true;
        } else if (DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(deviceStat.model)) {
            this.s = true;
        } else {
            this.s = false;
        }
    }

    public int q() {
        if (this.mDeviceStat.rssi >= -30) {
            return 100;
        }
        if (this.mDeviceStat.rssi <= g) {
            return 0;
        }
        return ((Math.abs(g) + this.mDeviceStat.rssi) * 100) / 55;
    }

    public String r() {
        return this.e;
    }

    public void updateDeviceStatus() {
        super.updateDeviceStatus();
        String p2 = p();
        if (!TextUtils.isEmpty(p2) && this.mDeviceStat != null && ((("chuangmi.camera.ipc009".equals(this.mDeviceStat.model) || "chuangmi.camera.ipc019".equals(this.mDeviceStat.model)) && Util.b(p2, "3.5.1_0399") > 0) || DeviceConstant.CHUANGMI_CAMERA_021.equals(this.mDeviceStat.model))) {
            this.s = true;
        } else if (!TextUtils.isEmpty(p2) && this.mDeviceStat != null && "mijia.camera.v3".equals(this.mDeviceStat.model) && Util.c(p2, "3.5.1_0070") > 0) {
            this.s = true;
        } else if (this.mDeviceStat == null || !DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(this.mDeviceStat.model)) {
            this.s = false;
        } else {
            this.s = true;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|(2:4|5)|6|7|(2:9|(3:11|(2:15|(1:17)(1:18))|19))|23|24) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x003e */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0044 A[Catch:{ JSONException -> 0x008e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r7, final com.xiaomi.smarthome.device.api.Callback<com.xiaomi.smarthome.device.api.FirmwareUpdateResult> r8) {
        /*
            r6 = this;
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            java.lang.String r0 = "did"
            com.xiaomi.smarthome.device.api.DeviceStat r1 = r6.deviceStat()     // Catch:{ JSONException -> 0x008e }
            java.lang.String r1 = r1.did     // Catch:{ JSONException -> 0x008e }
            r3.put(r0, r1)     // Catch:{ JSONException -> 0x008e }
            java.lang.String r0 = "platform"
            java.lang.String r1 = "android"
            r3.put(r0, r1)     // Catch:{ JSONException -> 0x008e }
            if (r7 == 0) goto L_0x003e
            android.content.pm.PackageManager r0 = r7.getPackageManager()     // Catch:{ NameNotFoundException -> 0x003e }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x003e }
            r1 = 0
            android.content.pm.PackageInfo r7 = r0.getPackageInfo(r7, r1)     // Catch:{ NameNotFoundException -> 0x003e }
            java.lang.String r0 = "app_level"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x003e }
            r1.<init>()     // Catch:{ NameNotFoundException -> 0x003e }
            java.lang.String r2 = ""
            r1.append(r2)     // Catch:{ NameNotFoundException -> 0x003e }
            int r7 = r7.versionCode     // Catch:{ NameNotFoundException -> 0x003e }
            r1.append(r7)     // Catch:{ NameNotFoundException -> 0x003e }
            java.lang.String r7 = r1.toString()     // Catch:{ NameNotFoundException -> 0x003e }
            r3.put(r0, r7)     // Catch:{ NameNotFoundException -> 0x003e }
        L_0x003e:
            com.xiaomi.smarthome.device.api.DeviceStat r7 = r6.deviceStat()     // Catch:{ JSONException -> 0x008e }
            if (r7 == 0) goto L_0x00ab
            com.xiaomi.smarthome.device.api.DeviceStat r7 = r6.deviceStat()     // Catch:{ JSONException -> 0x008e }
            java.lang.String r7 = r7.model     // Catch:{ JSONException -> 0x008e }
            boolean r0 = android.text.TextUtils.isEmpty(r7)     // Catch:{ JSONException -> 0x008e }
            if (r0 != 0) goto L_0x00ab
            java.lang.String r0 = "chuangmi.camera.ipc009"
            boolean r0 = r7.equals(r0)     // Catch:{ JSONException -> 0x008e }
            if (r0 != 0) goto L_0x0086
            java.lang.String r0 = "chuangmi.camera.ipc019"
            boolean r0 = r7.equals(r0)     // Catch:{ JSONException -> 0x008e }
            if (r0 != 0) goto L_0x0086
            java.lang.String r0 = "chuangmi.camera.ipc021"
            boolean r7 = r7.equals(r0)     // Catch:{ JSONException -> 0x008e }
            if (r7 == 0) goto L_0x0069
            goto L_0x0086
        L_0x0069:
            java.lang.String r7 = "plugin_level"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x008e }
            r0.<init>()     // Catch:{ JSONException -> 0x008e }
            java.lang.String r1 = ""
            r0.append(r1)     // Catch:{ JSONException -> 0x008e }
            com.xiaomi.smarthome.device.api.DeviceStat r1 = r6.deviceStat()     // Catch:{ JSONException -> 0x008e }
            java.lang.String r1 = r1.version     // Catch:{ JSONException -> 0x008e }
            r0.append(r1)     // Catch:{ JSONException -> 0x008e }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x008e }
            r3.put(r7, r0)     // Catch:{ JSONException -> 0x008e }
            goto L_0x00ab
        L_0x0086:
            java.lang.String r7 = "plugin_level"
            java.lang.String r0 = "96"
            r3.put(r7, r0)     // Catch:{ JSONException -> 0x008e }
            goto L_0x00ab
        L_0x008e:
            r7 = move-exception
            if (r8 == 0) goto L_0x00ab
            r0 = -9999(0xffffffffffffd8f1, float:NaN)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ""
            r1.append(r2)
            java.lang.String r7 = r7.getLocalizedMessage()
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r8.onFailure(r0, r7)
        L_0x00ab:
            com.xiaomi.smarthome.device.api.XmPluginHostApi r0 = com.xiaomi.smarthome.device.api.XmPluginHostApi.instance()
            com.xiaomi.smarthome.device.api.DeviceStat r7 = r6.deviceStat()
            java.lang.String r1 = r7.model
            java.lang.String r2 = "/v2/device/check_device_version"
            com.mijia.camera.MijiaCameraDevice$1 r4 = new com.mijia.camera.MijiaCameraDevice$1
            r4.<init>(r8)
            com.xiaomi.smarthome.device.api.Parser<org.json.JSONObject> r5 = com.xiaomi.smarthome.device.api.Parser.DEFAULT_PARSER
            r0.callSmartHomeApi((java.lang.String) r1, (java.lang.String) r2, (org.json.JSONObject) r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mijia.camera.MijiaCameraDevice.a(android.content.Context, com.xiaomi.smarthome.device.api.Callback):void");
    }

    @Deprecated
    public void a(final Callback<Integer[]> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", deviceStat().did);
            jSONObject.put("uid", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        XmPluginHostApi.instance().callSmartHomeApi(getModel(), "/home/checkversion", jSONObject, new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject.optBoolean("updating")) {
                    int optInt = jSONObject.optInt("ota_progress");
                    callback.onSuccess(new Integer[]{1, Integer.valueOf(optInt)});
                    return;
                }
                callback.onSuccess(new Integer[]{0, 0});
            }

            public void onFailure(int i, String str) {
                callback.onFailure(i, str);
            }
        }, Parser.DEFAULT_PARSER);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|(2:4|5)|6|7|(2:9|(3:11|(2:15|(1:17)(1:18))|19))|23|24) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x003e */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0044 A[Catch:{ JSONException -> 0x008e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.content.Context r7, final com.xiaomi.smarthome.device.api.Callback<java.lang.Integer[]> r8) {
        /*
            r6 = this;
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            java.lang.String r0 = "did"
            com.xiaomi.smarthome.device.api.DeviceStat r1 = r6.deviceStat()     // Catch:{ JSONException -> 0x008e }
            java.lang.String r1 = r1.did     // Catch:{ JSONException -> 0x008e }
            r3.put(r0, r1)     // Catch:{ JSONException -> 0x008e }
            java.lang.String r0 = "platform"
            java.lang.String r1 = "android"
            r3.put(r0, r1)     // Catch:{ JSONException -> 0x008e }
            if (r7 == 0) goto L_0x003e
            android.content.pm.PackageManager r0 = r7.getPackageManager()     // Catch:{ NameNotFoundException -> 0x003e }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x003e }
            r1 = 0
            android.content.pm.PackageInfo r7 = r0.getPackageInfo(r7, r1)     // Catch:{ NameNotFoundException -> 0x003e }
            java.lang.String r0 = "app_level"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x003e }
            r1.<init>()     // Catch:{ NameNotFoundException -> 0x003e }
            java.lang.String r2 = ""
            r1.append(r2)     // Catch:{ NameNotFoundException -> 0x003e }
            int r7 = r7.versionCode     // Catch:{ NameNotFoundException -> 0x003e }
            r1.append(r7)     // Catch:{ NameNotFoundException -> 0x003e }
            java.lang.String r7 = r1.toString()     // Catch:{ NameNotFoundException -> 0x003e }
            r3.put(r0, r7)     // Catch:{ NameNotFoundException -> 0x003e }
        L_0x003e:
            com.xiaomi.smarthome.device.api.DeviceStat r7 = r6.deviceStat()     // Catch:{ JSONException -> 0x008e }
            if (r7 == 0) goto L_0x00ab
            com.xiaomi.smarthome.device.api.DeviceStat r7 = r6.deviceStat()     // Catch:{ JSONException -> 0x008e }
            java.lang.String r7 = r7.model     // Catch:{ JSONException -> 0x008e }
            boolean r0 = android.text.TextUtils.isEmpty(r7)     // Catch:{ JSONException -> 0x008e }
            if (r0 != 0) goto L_0x00ab
            java.lang.String r0 = "chuangmi.camera.ipc009"
            boolean r0 = r7.equals(r0)     // Catch:{ JSONException -> 0x008e }
            if (r0 != 0) goto L_0x0086
            java.lang.String r0 = "chuangmi.camera.ipc019"
            boolean r0 = r7.equals(r0)     // Catch:{ JSONException -> 0x008e }
            if (r0 != 0) goto L_0x0086
            java.lang.String r0 = "chuangmi.camera.ipc021"
            boolean r7 = r7.equals(r0)     // Catch:{ JSONException -> 0x008e }
            if (r7 == 0) goto L_0x0069
            goto L_0x0086
        L_0x0069:
            java.lang.String r7 = "plugin_level"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x008e }
            r0.<init>()     // Catch:{ JSONException -> 0x008e }
            java.lang.String r1 = ""
            r0.append(r1)     // Catch:{ JSONException -> 0x008e }
            com.xiaomi.smarthome.device.api.DeviceStat r1 = r6.deviceStat()     // Catch:{ JSONException -> 0x008e }
            java.lang.String r1 = r1.version     // Catch:{ JSONException -> 0x008e }
            r0.append(r1)     // Catch:{ JSONException -> 0x008e }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x008e }
            r3.put(r7, r0)     // Catch:{ JSONException -> 0x008e }
            goto L_0x00ab
        L_0x0086:
            java.lang.String r7 = "plugin_level"
            java.lang.String r0 = "96"
            r3.put(r7, r0)     // Catch:{ JSONException -> 0x008e }
            goto L_0x00ab
        L_0x008e:
            r7 = move-exception
            if (r8 == 0) goto L_0x00ab
            r0 = -9999(0xffffffffffffd8f1, float:NaN)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ""
            r1.append(r2)
            java.lang.String r7 = r7.getLocalizedMessage()
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r8.onFailure(r0, r7)
        L_0x00ab:
            com.xiaomi.smarthome.device.api.XmPluginHostApi r0 = com.xiaomi.smarthome.device.api.XmPluginHostApi.instance()
            com.xiaomi.smarthome.device.api.DeviceStat r7 = r6.deviceStat()
            java.lang.String r1 = r7.model
            java.lang.String r2 = "/v2/device/check_device_version"
            com.mijia.camera.MijiaCameraDevice$3 r4 = new com.mijia.camera.MijiaCameraDevice$3
            r4.<init>(r8)
            com.xiaomi.smarthome.device.api.Parser<org.json.JSONObject> r5 = com.xiaomi.smarthome.device.api.Parser.DEFAULT_PARSER
            r0.callSmartHomeApi((java.lang.String) r1, (java.lang.String) r2, (org.json.JSONObject) r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mijia.camera.MijiaCameraDevice.b(android.content.Context, com.xiaomi.smarthome.device.api.Callback):void");
    }

    @Deprecated
    public void b(final Callback<JSONObject> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", deviceStat().did);
            jSONObject.put("uid", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        XmPluginHostApi.instance().callSmartHomeApi(getModel(), "/home/checkversion", jSONObject, new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (callback != null) {
                    callback.onSuccess(jSONObject);
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|(2:4|5)|6|7|(2:9|(3:11|(2:15|(1:17)(1:18))|19))|23|24) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x003e */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0044 A[Catch:{ JSONException -> 0x008e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(android.content.Context r7, final com.xiaomi.smarthome.device.api.Callback<org.json.JSONObject> r8) {
        /*
            r6 = this;
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            java.lang.String r0 = "did"
            com.xiaomi.smarthome.device.api.DeviceStat r1 = r6.deviceStat()     // Catch:{ JSONException -> 0x008e }
            java.lang.String r1 = r1.did     // Catch:{ JSONException -> 0x008e }
            r3.put(r0, r1)     // Catch:{ JSONException -> 0x008e }
            java.lang.String r0 = "platform"
            java.lang.String r1 = "android"
            r3.put(r0, r1)     // Catch:{ JSONException -> 0x008e }
            if (r7 == 0) goto L_0x003e
            android.content.pm.PackageManager r0 = r7.getPackageManager()     // Catch:{ NameNotFoundException -> 0x003e }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x003e }
            r1 = 0
            android.content.pm.PackageInfo r7 = r0.getPackageInfo(r7, r1)     // Catch:{ NameNotFoundException -> 0x003e }
            java.lang.String r0 = "app_level"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x003e }
            r1.<init>()     // Catch:{ NameNotFoundException -> 0x003e }
            java.lang.String r2 = ""
            r1.append(r2)     // Catch:{ NameNotFoundException -> 0x003e }
            int r7 = r7.versionCode     // Catch:{ NameNotFoundException -> 0x003e }
            r1.append(r7)     // Catch:{ NameNotFoundException -> 0x003e }
            java.lang.String r7 = r1.toString()     // Catch:{ NameNotFoundException -> 0x003e }
            r3.put(r0, r7)     // Catch:{ NameNotFoundException -> 0x003e }
        L_0x003e:
            com.xiaomi.smarthome.device.api.DeviceStat r7 = r6.deviceStat()     // Catch:{ JSONException -> 0x008e }
            if (r7 == 0) goto L_0x00ab
            com.xiaomi.smarthome.device.api.DeviceStat r7 = r6.deviceStat()     // Catch:{ JSONException -> 0x008e }
            java.lang.String r7 = r7.model     // Catch:{ JSONException -> 0x008e }
            boolean r0 = android.text.TextUtils.isEmpty(r7)     // Catch:{ JSONException -> 0x008e }
            if (r0 != 0) goto L_0x00ab
            java.lang.String r0 = "chuangmi.camera.ipc009"
            boolean r0 = r7.equals(r0)     // Catch:{ JSONException -> 0x008e }
            if (r0 != 0) goto L_0x0086
            java.lang.String r0 = "chuangmi.camera.ipc019"
            boolean r0 = r7.equals(r0)     // Catch:{ JSONException -> 0x008e }
            if (r0 != 0) goto L_0x0086
            java.lang.String r0 = "chuangmi.camera.ipc021"
            boolean r7 = r7.equals(r0)     // Catch:{ JSONException -> 0x008e }
            if (r7 == 0) goto L_0x0069
            goto L_0x0086
        L_0x0069:
            java.lang.String r7 = "plugin_level"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x008e }
            r0.<init>()     // Catch:{ JSONException -> 0x008e }
            java.lang.String r1 = ""
            r0.append(r1)     // Catch:{ JSONException -> 0x008e }
            com.xiaomi.smarthome.device.api.DeviceStat r1 = r6.deviceStat()     // Catch:{ JSONException -> 0x008e }
            java.lang.String r1 = r1.version     // Catch:{ JSONException -> 0x008e }
            r0.append(r1)     // Catch:{ JSONException -> 0x008e }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x008e }
            r3.put(r7, r0)     // Catch:{ JSONException -> 0x008e }
            goto L_0x00ab
        L_0x0086:
            java.lang.String r7 = "plugin_level"
            java.lang.String r0 = "96"
            r3.put(r7, r0)     // Catch:{ JSONException -> 0x008e }
            goto L_0x00ab
        L_0x008e:
            r7 = move-exception
            if (r8 == 0) goto L_0x00ab
            r0 = -9999(0xffffffffffffd8f1, float:NaN)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ""
            r1.append(r2)
            java.lang.String r7 = r7.getLocalizedMessage()
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r8.onFailure(r0, r7)
        L_0x00ab:
            com.xiaomi.smarthome.device.api.XmPluginHostApi r0 = com.xiaomi.smarthome.device.api.XmPluginHostApi.instance()
            com.xiaomi.smarthome.device.api.DeviceStat r7 = r6.deviceStat()
            java.lang.String r1 = r7.model
            java.lang.String r2 = "/v2/device/check_device_version"
            com.mijia.camera.MijiaCameraDevice$5 r4 = new com.mijia.camera.MijiaCameraDevice$5
            r4.<init>(r8)
            com.xiaomi.smarthome.device.api.Parser<org.json.JSONObject> r5 = com.xiaomi.smarthome.device.api.Parser.DEFAULT_PARSER
            r0.callSmartHomeApi((java.lang.String) r1, (java.lang.String) r2, (org.json.JSONObject) r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mijia.camera.MijiaCameraDevice.c(android.content.Context, com.xiaomi.smarthome.device.api.Callback):void");
    }

    public void a(boolean z, final Callback<Void> callback) {
        callMethod("set_improve_program", new JSONArray().put(z ? "on" : "off"), new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void c(final Callback<Void> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", -90);
            jSONObject.put("ts", System.currentTimeMillis() / 1000);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callMethod("miIO.event", jSONObject, new Callback<JSONObject>() {
            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
                SDKLog.e(Tag.f, "reStartTutk error " + str);
            }

            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void d(final Callback<Void> callback) {
        callMethod("restart_device", new JSONArray(), new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public static void s() {
        u.clear();
    }

    public void a(String str, Callback<Boolean> callback) {
        if (XmPluginHostApi.instance().getApiLevel() >= 58) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            try {
                jSONObject.put("did", this.mDeviceStat.did);
                jSONObject.put("region", "CN");
                JSONObject jSONObject2 = new JSONObject();
                jSONArray.put(str);
                jSONObject2.put("fileIds", jSONArray);
                jSONObject.put("fileIds", jSONObject2);
                XmPluginHostApi.instance().callSmartHomeApi(getModel(), "business.smartcamera.", AlarmNetUtils.z, "POST", jSONObject, callback, new Parser<Boolean>() {
                    /* renamed from: a */
                    public Boolean parse(String str) throws JSONException {
                        Log.d("cmkj", "getOnlyPeopleStatus = " + str);
                        JSONObject jSONObject = new JSONObject(str);
                        new JSONArray();
                        boolean z = false;
                        JSONArray jSONArray = jSONObject.getJSONObject("data").getJSONArray("detectionTags").getJSONObject(0).getJSONArray(CommandMessage.TYPE_TAGS);
                        if (jSONArray.length() > 0 && jSONArray.getString(0).equals("people")) {
                            z = true;
                        }
                        Log.d("cmkj", "getOnlyPeopleStatus = " + z);
                        return Boolean.valueOf(z);
                    }
                });
            } catch (JSONException unused) {
                callback.onFailure(-10000, (String) null);
            }
        }
    }

    public void e(Callback<Boolean> callback) {
        if (XmPluginHostApi.instance().getApiLevel() >= 58) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("did", this.mDeviceStat.did);
                jSONObject.put("region", "CN");
                XmPluginHostApi.instance().callSmartHomeApi(getModel(), "business.smartcamera.", AlarmNetUtils.k, "GET", jSONObject, callback, new Parser<Boolean>() {
                    /* renamed from: a */
                    public Boolean parse(String str) throws JSONException {
                        return Boolean.valueOf(new JSONObject(str).getJSONObject("data").getBoolean("pedestrianDetectionPushSwitch"));
                    }
                });
            } catch (JSONException unused) {
                callback.onFailure(-10000, (String) null);
            }
        } else {
            callback.onFailure(-1, "api level low");
        }
    }

    public void b(boolean z, Callback<String> callback) {
        if (XmPluginHostApi.instance().getApiLevel() >= 58) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("did", this.mDeviceStat.did);
                jSONObject.put("region", "CN");
                jSONObject.put("open", z);
                XmPluginHostApi.instance().callSmartHomeApi(getModel(), "business.smartcamera.", AlarmNetUtils.p, "POST", jSONObject, callback, new Parser<String>() {
                    /* renamed from: a */
                    public String parse(String str) throws JSONException {
                        return new JSONObject(str).optString("url");
                    }
                });
            } catch (JSONException unused) {
                if (callback != null) {
                    callback.onFailure(-10000, (String) null);
                }
            }
        } else if (callback != null) {
            callback.onFailure(-1, "api level low");
        }
    }

    public void a(String str, String str2, boolean z, final Callback<String> callback, boolean z2) {
        if (XmPluginHostApi.instance().getApiLevel() >= 58) {
            JSONObject jSONObject = new JSONObject();
            new JSONArray();
            try {
                jSONObject.put("did", getDid());
                if (z2) {
                    jSONObject.put("fileId", Base64.encodeToString(new JSONObject().put("storeId", str2).toString().getBytes(), 0));
                } else {
                    jSONObject.put("fileId", Base64.encodeToString(new JSONObject().put("Id", str).put("key", str2).toString().getBytes(), 0));
                }
                jSONObject.put("model", getModel());
                jSONObject.put("isVisible", z);
                XmPluginHostApi.instance().callSmartHomeApi(getModel(), "business.smartcamera.", AlarmNetUtils.C, "POST", jSONObject, new Callback<String>() {
                    /* renamed from: a */
                    public void onSuccess(String str) {
                        callback.onSuccess(str);
                    }

                    public void onFailure(int i, String str) {
                        callback.onFailure(i, str);
                    }
                }, new Parser<String>() {
                    /* renamed from: a */
                    public String parse(String str) throws JSONException {
                        return str;
                    }
                });
            } catch (JSONException unused) {
                callback.onFailure(-10000, (String) null);
            }
        }
    }
}
