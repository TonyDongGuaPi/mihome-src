package com.xiaomi.smarthome.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.DevelopSharePreManager;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.Task;
import com.xiaomi.smarthome.miio.device.TemporaryDevice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemporaryDeviceSearch extends DeviceSearch<Device> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14998a = "temporary_device";
    private static TemporaryDeviceSearch b;
    private Map<String, TemporaryDevice> c = new HashMap();
    /* access modifiers changed from: private */
    public SharedPreferences d;

    public void a(Collection<? extends Device> collection, DeviceSearch.SearchDeviceListener searchDeviceListener) {
    }

    public void e() {
    }

    private TemporaryDeviceSearch() {
        j();
        i();
    }

    public static TemporaryDeviceSearch a() {
        if (b == null) {
            synchronized (TemporaryDeviceSearch.class) {
                if (b == null) {
                    b = new TemporaryDeviceSearch();
                }
            }
        }
        return b;
    }

    public boolean a(String str) {
        if (this.d == null) {
            return false;
        }
        return SharePrefsManager.b(this.d, str, false);
    }

    public void a(String str, boolean z) {
        if (this.d != null) {
            SharePrefsManager.a(this.d, str, z);
            if (!z) {
                SmartHomeDeviceManager.a().c();
            }
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        Task.a((Task) new Task() {
            public void a() {
                if (CoreApi.a().q()) {
                    String s = CoreApi.a().s();
                    TemporaryDeviceSearch temporaryDeviceSearch = TemporaryDeviceSearch.this;
                    Context appContext = CommonApplication.getAppContext();
                    SharedPreferences unused = temporaryDeviceSearch.d = appContext.getSharedPreferences(TemporaryDeviceSearch.f14998a + s, 0);
                    return;
                }
                SharedPreferences unused2 = TemporaryDeviceSearch.this.d = null;
            }
        }, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public TemporaryDevice b(String str) {
        TemporaryDevice temporaryDevice = this.c.get(str);
        if (temporaryDevice != null) {
            return temporaryDevice;
        }
        TemporaryDevice temporaryDevice2 = new TemporaryDevice(str);
        this.c.put(str, temporaryDevice2);
        return temporaryDevice2;
    }

    public void c() {
        this.h = true;
    }

    public List<Device> d() {
        if (!CoreApi.a().q()) {
            return Collections.EMPTY_LIST;
        }
        if (!DevelopSharePreManager.a().d()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        for (Device next : this.c.values()) {
            if (a(next.model)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private boolean c(String str) {
        return CoreApi.a().d(str) != null;
    }

    private void j() {
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
                TemporaryDeviceSearch.this.i();
            } else if ("action_on_logout".equals(action)) {
                SharedPreferences unused = TemporaryDeviceSearch.this.d = null;
            }
        }
    }
}
