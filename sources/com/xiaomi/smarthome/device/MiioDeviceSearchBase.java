package com.xiaomi.smarthome.device;

import android.os.Handler;
import android.os.Looper;
import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class MiioDeviceSearchBase extends DeviceSearch<MiioDeviceV2> {
    protected final List<MiioDeviceV2> b = Collections.synchronizedList(new ArrayList());
    protected List<MiioDeviceV2> c = Collections.synchronizedList(new ArrayList());
    protected HashMap<String, MiioDeviceV2> d = new HashMap<>();
    protected Handler e = new Handler(Looper.getMainLooper());
    protected boolean f = false;
    protected boolean g = false;
    protected boolean m = false;

    /* access modifiers changed from: package-private */
    public void a() {
    }

    public void a(Collection<? extends Device> collection, DeviceSearch.SearchDeviceListener searchDeviceListener) {
    }

    public int g() {
        return -1;
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
            this.c.clear();
            this.c.addAll(list);
        }
        this.f = false;
        a();
    }

    public void b() {
        synchronized (this.b) {
            this.b.clear();
            this.b.addAll(this.c);
        }
    }

    public void c() {
        i();
    }

    private void i() {
        if (!this.f) {
            this.f = true;
            this.g = false;
            this.m = false;
        }
    }

    public List<MiioDeviceV2> d() {
        return this.b;
    }

    public void e() {
        this.c.clear();
        synchronized (this.b) {
            this.b.clear();
        }
    }

    public void a(Device device) {
        if ((device instanceof MiioDeviceV2) && !(device instanceof RouterDevice) && device.pid == g()) {
            Iterator<MiioDeviceV2> it = this.c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Device next = it.next();
                if (device.did != null && next.did != null && device.did.equalsIgnoreCase(next.did)) {
                    this.c.remove(next);
                    break;
                }
            }
            this.c.add((MiioDeviceV2) device);
        }
    }

    public void b(Device device) {
        for (Device next : this.c) {
            if (device.did != null && next.did != null && device.did.equalsIgnoreCase(next.did)) {
                this.c.remove(next);
                return;
            }
        }
    }

    public void f() {
        this.d = null;
    }
}
