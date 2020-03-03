package com.xiaomi.smarthome.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.Task;
import com.xiaomi.smarthome.miio.device.GeneralAPDevice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GeneralAPDeviceSearch extends DeviceSearch<Device> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14853a = "general_ap_device";
    private static final String b = "key.show_general_ap";
    private static final String c = "key.desc";
    private static GeneralAPDeviceSearch f;
    private HashMap<String, Device> d = new HashMap<>();
    private HashMap<String, Boolean> e = new HashMap<>();
    /* access modifiers changed from: private */
    public SharedPreferences g;

    public void a(Collection<? extends Device> collection, DeviceSearch.SearchDeviceListener searchDeviceListener) {
    }

    public void e() {
    }

    public void a(Device device) {
        super.a(device);
        this.d.put(device.model, device);
    }

    public static GeneralAPDeviceSearch a() {
        if (f == null) {
            synchronized (GeneralAPDeviceSearch.class) {
                if (f == null) {
                    f = new GeneralAPDeviceSearch();
                }
            }
        }
        return f;
    }

    public void b(Device device) {
        a(false, device.model);
        CoreApi.a().a(device.model, (CoreApi.DeletePluginCallback) null);
    }

    public void b(Collection<? extends Device> collection, DeviceSearch.REMOTESTATE remotestate) {
        PluginRecord d2;
        PluginDeviceInfo c2;
        PluginDeviceInfo c3;
        super.b(collection, remotestate);
        if (collection != null) {
            for (Device device : collection) {
                if (device.model == "fimi.camera.c1") {
                    this.d.put(device.model, device);
                } else {
                    PluginRecord d3 = CoreApi.a().d(device.model);
                    if (!(d3 == null || (c3 = d3.c()) == null || c3.e() != 7)) {
                        this.d.put(device.model, device);
                    }
                }
            }
            List<PluginPackageInfo> R = CoreApi.a().R();
            if (R != null) {
                for (PluginPackageInfo m : R) {
                    List<String> m2 = m.m();
                    if (m2 != null) {
                        for (String next : m2) {
                            if (!this.d.containsKey(next) && (d2 = CoreApi.a().d(next)) != null && (c2 = d2.c()) != null && 7 == c2.e()) {
                                a((Device) b(next));
                                a(true, next);
                            }
                        }
                    }
                }
            }
        }
    }

    private GeneralAPDeviceSearch() {
        i();
        j();
    }

    private void i() {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(CommonApplication.getAppContext());
        IntentFilter intentFilter = new IntentFilter("action_on_login_success");
        intentFilter.addAction("action_on_logout");
        instance.registerReceiver(new AccountReceiver(), intentFilter);
    }

    private class AccountReceiver extends BroadcastReceiver {
        private AccountReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("action_on_login_success".equals(action)) {
                GeneralAPDeviceSearch.this.j();
            } else if ("action_on_logout".equals(action)) {
                SharedPreferences unused = GeneralAPDeviceSearch.this.g = null;
            }
        }
    }

    public boolean a(String str) {
        Boolean bool;
        if (this.e == null || (bool = this.e.get(str)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: private */
    public void j() {
        Task.a((Task) new Task() {
            public void a() {
                if (CoreApi.a().q()) {
                    String s = CoreApi.a().s();
                    GeneralAPDeviceSearch generalAPDeviceSearch = GeneralAPDeviceSearch.this;
                    Context appContext = CommonApplication.getAppContext();
                    SharedPreferences unused = generalAPDeviceSearch.g = appContext.getSharedPreferences(GeneralAPDeviceSearch.f14853a + s, 0);
                } else {
                    SharedPreferences unused2 = GeneralAPDeviceSearch.this.g = null;
                }
                if (GeneralAPDeviceSearch.this.g != null) {
                    GeneralAPDeviceSearch.this.k();
                }
            }
        }, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public GeneralAPDevice b(String str) {
        return new GeneralAPDevice(str);
    }

    public boolean c(String str) {
        return CoreApi.a().d(str) != null;
    }

    /* access modifiers changed from: private */
    public void k() {
        if (this.d != null) {
            for (String next : this.d.keySet()) {
                this.d.get(next).desc = d(next);
            }
        }
    }

    public void a(boolean z, String str) {
        if (this.g != null) {
            SharePrefsManager.a(this.g, b + str, z);
            this.e.put(str, Boolean.valueOf(z));
            if (!z) {
                SmartHomeDeviceManager.a().c();
            }
        }
    }

    public void a(String str, String str2) {
        BluetoothLog.c(String.format("saveDesc (%s)", new Object[]{str2}));
        SharedPreferences sharedPreferences = this.g;
        SharePrefsManager.a(sharedPreferences, c + str, str2);
    }

    public String d(String str) {
        SharedPreferences sharedPreferences = this.g;
        String b2 = SharePrefsManager.b(sharedPreferences, c + str, "");
        BluetoothLog.c(String.format("getDesc (%s)", new Object[]{b2}));
        return b2;
    }

    public void c() {
        this.h = true;
    }

    public List<Device> d() {
        PluginRecord d2;
        if (this.e == null) {
            return Collections.EMPTY_LIST;
        }
        if (!CoreApi.a().q()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        for (String next : this.e.keySet()) {
            if (this.e.get(next).booleanValue() && (d2 = CoreApi.a().d(next)) != null) {
                Device device = this.d.get(next);
                device.name = d2.p();
                arrayList.add(device);
            }
        }
        return arrayList;
    }
}
