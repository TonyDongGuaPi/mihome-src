package com.xiaomi.smarthome.device;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.Device;
import java.util.ArrayList;
import java.util.Iterator;

public class MitvDeviceManager {

    /* renamed from: a  reason: collision with root package name */
    public static MitvDeviceManager f14902a = null;
    static final String b = "mitv_device_key_1";
    static final String c = ",";
    ArrayList<String> d = new ArrayList<>();
    SharedPreferences e;
    ArrayList<MiTVDevice> f = new ArrayList<>();
    volatile boolean g;
    volatile boolean h;

    public void a(boolean z) {
    }

    public void c() {
    }

    public void a(MiTVDevice miTVDevice) {
        this.f.add(miTVDevice);
    }

    public void a() {
        this.f.clear();
    }

    public boolean a(String str) {
        if (this.d == null) {
            return false;
        }
        return this.d.contains(str);
    }

    public static synchronized MitvDeviceManager b() {
        MitvDeviceManager mitvDeviceManager;
        synchronized (MitvDeviceManager.class) {
            if (f14902a == null) {
                f14902a = new MitvDeviceManager();
            }
            mitvDeviceManager = f14902a;
        }
        return mitvDeviceManager;
    }

    private MitvDeviceManager() {
        String[] split;
        this.g = false;
        this.h = false;
        try {
            this.e = PreferenceManager.getDefaultSharedPreferences(CommonApplication.getAppContext());
            String string = this.e.getString(b, (String) null);
            if (!TextUtils.isEmpty(string) && (split = string.split(",")) != null) {
                for (String add : split) {
                    this.d.add(add);
                }
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public String d() {
        if (this.d == null || this.d.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.d.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(",");
        }
        return sb.toString();
    }

    public void b(String str) {
        if (!this.d.contains(str)) {
            this.d.add(str);
            this.g = true;
            this.e.edit().putString(b, d()).apply();
            c();
        }
    }

    public void c(String str) {
        if (this.d.contains(str)) {
            this.d.remove(str);
            this.g = true;
            this.e.edit().putString(b, d()).apply();
            c();
        }
    }

    public void e() {
        this.d.clear();
        this.g = false;
        this.h = false;
        this.e.edit().putString(b, d()).apply();
    }

    public ArrayList<MiTVDevice> f() {
        ArrayList<MiTVDevice> arrayList = new ArrayList<>();
        Iterator<MiTVDevice> it = this.f.iterator();
        while (it.hasNext()) {
            MiTVDevice next = it.next();
            if (next.d()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static void a(Context context, Device device) {
        if ((device instanceof MiTVDevice) && !device.isBinded() && ((MiTVDevice) device).d() && device.isOnline) {
            device.bindDevice(context, new Device.IBindDeviceCallback() {
                public void a() {
                }

                public void a(int i) {
                }

                public void b() {
                }

                public void c() {
                }

                public void d() {
                }
            });
        }
    }
}
