package com.xiaomi.smarthome.device;

import android.net.wifi.ScanResult;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthomedevice.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class ApDeviceSearch extends DeviceSearch<MiioDeviceV2> {
    private static final String m = "ApDeviceSearch";

    /* renamed from: a  reason: collision with root package name */
    protected List<MiioDeviceV2> f14749a = Collections.synchronizedList(new ArrayList());
    protected List<MiioDeviceV2> b = Collections.synchronizedList(new ArrayList());
    protected HashMap<String, MiioDeviceV2> c = new HashMap<>();
    protected Handler d = new Handler(Looper.getMainLooper());
    protected boolean e = false;
    protected boolean f = false;
    protected boolean g = false;

    /* access modifiers changed from: package-private */
    public void a() {
    }

    public void a(Collection<? extends Device> collection, DeviceSearch.SearchDeviceListener searchDeviceListener) {
    }

    public int g() {
        return 7;
    }

    /* access modifiers changed from: protected */
    public void a(Collection<? extends Device> collection, DeviceSearch.REMOTESTATE remotestate) {
        if (remotestate == DeviceSearch.REMOTESTATE.REMOTE_SUCCESS) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Device device : collection) {
                if ((device instanceof MiioDeviceV2) && device.pid == g()) {
                    if (device.isBinded()) {
                        device.userId = CoreApi.a().s();
                        arrayList.add((MiioDeviceV2) device);
                    } else {
                        arrayList2.add((MiioDeviceV2) device);
                    }
                }
            }
            arrayList.addAll(arrayList2);
            a((List<MiioDeviceV2>) arrayList);
        } else if (remotestate == DeviceSearch.REMOTESTATE.REMOTE_FAILED) {
            a((List<MiioDeviceV2>) null);
        } else if (remotestate == DeviceSearch.REMOTESTATE.REMOTE_NOT_LOGIN) {
            a((List<MiioDeviceV2>) null);
        }
    }

    public void b(Collection<? extends Device> collection, DeviceSearch.REMOTESTATE remotestate) {
        super.b(collection, remotestate);
        if (this.j) {
            a(collection, remotestate);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(List<MiioDeviceV2> list) {
        if (list != null) {
            this.b.clear();
            this.b.addAll(list);
        }
        this.e = false;
        a();
    }

    public synchronized void b() {
        this.f14749a.clear();
        this.f14749a.addAll(this.b);
    }

    public void c() {
        i();
    }

    private void i() {
        if (!this.e) {
            this.e = true;
            this.f = false;
            this.g = false;
        }
    }

    public synchronized List<MiioDeviceV2> d() {
        for (int i = 0; i < this.f14749a.size(); i++) {
            Device device = this.f14749a.get(i);
            ScanResult a2 = ApDeviceManager.a().a(device.mac);
            device.canAuth = false;
            if (a2 != null) {
                device.isOnline = true;
                try {
                    JSONObject jSONObject = new JSONObject(device.extra);
                    jSONObject.put(DeviceTagInterface.e, a2.SSID);
                    jSONObject.put("bssid", a2.BSSID);
                    device.extra = jSONObject.toString();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                device.desc = CommonApplication.getAppContext().getString(R.string.list_device_online);
            } else {
                device.isOnline = false;
                device.desc = CommonApplication.getAppContext().getString(R.string.list_device_offline);
            }
        }
        return this.f14749a;
    }

    public synchronized void e() {
        this.b.clear();
        this.f14749a.clear();
    }

    public void a(Device device) {
        if ((device instanceof MiioDeviceV2) && !(device instanceof RouterDevice) && device.pid == g()) {
            Iterator<MiioDeviceV2> it = this.b.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Device next = it.next();
                if (device.did != null && next.did != null && device.did.equalsIgnoreCase(next.did)) {
                    this.b.remove(next);
                    break;
                }
            }
            this.b.add((MiioDeviceV2) device);
        }
    }

    public void b(Device device) {
        for (Device next : this.b) {
            if (device.did != null && next.did != null && device.did.equalsIgnoreCase(next.did)) {
                this.b.remove(next);
                return;
            }
        }
    }

    public void f() {
        this.c = null;
    }
}
