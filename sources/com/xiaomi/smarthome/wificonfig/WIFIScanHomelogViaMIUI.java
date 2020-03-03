package com.xiaomi.smarthome.wificonfig;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.mi.blockcanary.internal.BlockInfo;
import com.xiaomi.metok.geofencing.IGeoFencing;
import com.xiaomi.metoknlp.geofencing.GeoFencingServiceWrapper;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.wificonfig.WifiScanServices;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;

public class WIFIScanHomelogViaMIUI implements WifiScanServices.WIFIScanMonitor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22918a = "WIFIScanHomelogViaMIUI";
    private static final String e = "com.xiaomi.metok.GeoFencingService";
    /* access modifiers changed from: private */
    public IGeoFencing b;
    private ServiceConnection c;
    private Context d;
    /* access modifiers changed from: private */
    public JSONArray f;
    /* access modifiers changed from: private */
    public String g;
    private GeoFencingServiceWrapper h;
    /* access modifiers changed from: private */
    public volatile boolean i = false;
    private boolean j = false;
    private TelephonyManager k;
    private PackageManager l;

    public void a() {
        e();
    }

    private void e() {
        try {
            if (this.d.getPackageManager().getPackageInfo("com.xiaomi.metoknlp", 0) != null) {
                this.j = true;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            this.j = false;
        } catch (Exception e3) {
            e3.printStackTrace();
            this.j = false;
        }
        if (this.j) {
            this.h = new GeoFencingServiceWrapper(this.d);
            this.i = true;
        } else {
            Intent intent = new Intent();
            intent.setAction(e);
            intent.setPackage("com.xiaomi.metok");
            this.c = new ServiceConnection() {
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    IGeoFencing unused = WIFIScanHomelogViaMIUI.this.b = IGeoFencing.Stub.asInterface(iBinder);
                    if (WIFIScanHomelogViaMIUI.this.f != null && WIFIScanHomelogViaMIUI.this.g != null) {
                        WIFIScanHomelogViaMIUI.this.b(WIFIScanHomelogViaMIUI.this.f, WIFIScanHomelogViaMIUI.this.g);
                    }
                }

                public void onServiceDisconnected(ComponentName componentName) {
                    boolean unused = WIFIScanHomelogViaMIUI.this.i = false;
                    IGeoFencing unused2 = WIFIScanHomelogViaMIUI.this.b = null;
                }
            };
            try {
                this.i = this.d.bindService(intent, this.c, 1);
            } catch (Exception unused) {
                this.i = false;
            }
        }
        String str = f22918a;
        MyLog.a(str, "bindService " + this.i);
    }

    public void b() {
        if (!d()) {
            this.d = null;
            return;
        }
        this.k = null;
        if (!this.j) {
            this.d.unbindService(this.c);
        }
        this.d = null;
    }

    public void a(Context context) {
        this.d = context;
    }

    public boolean c() {
        return !d();
    }

    public void a(JSONArray jSONArray, String str) {
        if (TextUtils.isEmpty(str)) {
            this.f = jSONArray;
            this.g = str;
            return;
        }
        this.f = null;
        this.g = null;
        String str2 = f22918a;
        MyLog.a(str2, "mGeoFencingServiceWrapper setLocationListener " + str + " " + jSONArray.toString());
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            arrayList.add(jSONArray.optString(i2));
        }
        try {
            List<String> b2 = this.h.b(this.d, str);
            String str3 = f22918a;
            StringBuilder sb = new StringBuilder();
            sb.append("mGeoFencingServiceWrapper getLocationSsids ");
            sb.append(str);
            sb.append(BlockInfo.c);
            sb.append(b2 == null ? "0" : b2.toString());
            MyLog.a(str3, sb.toString());
            if (a((List<String>) arrayList, b2)) {
                return;
            }
            if (arrayList.size() == 0) {
                if (HostSetting.g) {
                    ToastUtil.a((CharSequence) "reset loc");
                }
                this.h.a(this.d, str);
                return;
            }
            if (HostSetting.g) {
                ToastUtil.a((CharSequence) jSONArray.toString());
            }
            this.h.a(this.d, (List<String>) arrayList, str);
        } catch (Exception unused) {
        }
    }

    public void b(JSONArray jSONArray, String str) {
        if (d()) {
            if (jSONArray == null) {
                jSONArray = new JSONArray();
            }
            if (this.j) {
                a(jSONArray, str);
            } else if (this.b == null || TextUtils.isEmpty(str)) {
                this.f = jSONArray;
                this.g = str;
            } else {
                this.f = null;
                this.g = null;
                String str2 = f22918a;
                MyLog.a(str2, "IGeoFencing setLocationListener " + str + " " + jSONArray.toString());
                ArrayList arrayList = new ArrayList(jSONArray.length());
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    arrayList.add(jSONArray.optString(i2));
                }
                try {
                    List<String> locationSsids = this.b.getLocationSsids(str);
                    String str3 = f22918a;
                    StringBuilder sb = new StringBuilder();
                    sb.append("IGeoFencing getLocationSsids ");
                    sb.append(str);
                    sb.append(BlockInfo.c);
                    sb.append(locationSsids == null ? "0" : locationSsids.toString());
                    MyLog.a(str3, sb.toString());
                    if (a((List<String>) arrayList, locationSsids)) {
                        return;
                    }
                    if (arrayList.size() == 0) {
                        if (HostSetting.g) {
                            ToastUtil.a((CharSequence) "reset loc");
                        }
                        this.b.unsetLocationListener(str);
                        return;
                    }
                    if (HostSetting.g) {
                        ToastUtil.a((CharSequence) jSONArray.toString());
                    }
                    this.b.setLocationListener(arrayList, str);
                } catch (Exception unused) {
                }
            }
        }
    }

    private boolean a(List<String> list, List<String> list2) {
        if (list == null || list2 == null) {
            return false;
        }
        Collections.sort(list);
        Collections.sort(list2);
        return list.equals(list2);
    }

    public boolean d() {
        PackageInfo packageInfo;
        if (!this.i) {
            return false;
        }
        if (this.k == null) {
            this.k = (TelephonyManager) this.d.getSystemService("phone");
        }
        if (this.k.getSimState() != 5) {
            return false;
        }
        if (this.l == null) {
            this.l = this.d.getPackageManager();
        }
        if (!this.j) {
            try {
                packageInfo = this.l.getPackageInfo("com.xiaomi.metok", 0);
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
                packageInfo = null;
            }
            if (packageInfo == null || packageInfo.versionCode < 19) {
                return false;
            }
        }
        if (CommonUtils.m()) {
            return false;
        }
        return true;
    }
}
