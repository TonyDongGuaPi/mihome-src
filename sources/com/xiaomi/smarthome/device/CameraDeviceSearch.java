package com.xiaomi.smarthome.device;

import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.miio.camera.match.CameraDevice;
import com.xiaomi.smarthome.miio.camera.match.SearchCameraDevice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CameraDeviceSearch extends DeviceSearch<CameraDevice> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14764a = 1;
    /* access modifiers changed from: private */
    public HashMap<String, CameraDevice> b = null;
    /* access modifiers changed from: private */
    public List<CameraDevice> c = Collections.synchronizedList(new ArrayList());
    /* access modifiers changed from: private */
    public List<CameraDevice> d = Collections.synchronizedList(new ArrayList());

    public int g() {
        return 1;
    }

    private void a(Collection<? extends Device> collection, DeviceSearch.REMOTESTATE remotestate) {
        if (remotestate == DeviceSearch.REMOTESTATE.REMOTE_SUCCESS) {
            ArrayList arrayList = new ArrayList();
            for (Device device : collection) {
                if (device instanceof CameraDevice) {
                    arrayList.add((CameraDevice) device);
                }
            }
            this.c.clear();
            this.c.addAll(arrayList);
        } else if (remotestate == DeviceSearch.REMOTESTATE.REMOTE_FAILED) {
            this.h = true;
            this.i = false;
        } else {
            DeviceSearch.REMOTESTATE remotestate2 = DeviceSearch.REMOTESTATE.REMOTE_NOT_LOGIN;
        }
    }

    public void b(Collection<? extends Device> collection, DeviceSearch.REMOTESTATE remotestate) {
        super.b(collection, remotestate);
        if (this.j) {
            a(collection, remotestate);
        }
    }

    public void c() {
        this.h = false;
    }

    public List<CameraDevice> d() {
        return this.d;
    }

    public void b(Device device) {
        this.d.remove(device);
        this.c.remove(device);
    }

    public void e() {
        this.d.clear();
        this.c.clear();
    }

    public void f() {
        this.b = null;
    }

    public void a(Collection<? extends Device> collection, final DeviceSearch.SearchDeviceListener searchDeviceListener) {
        if (CoreApi.a().q()) {
            ArrayList<Device> arrayList = new ArrayList<>();
            for (Device device : collection) {
                if ((device instanceof CameraDevice) && !device.isBinded()) {
                    arrayList.add((CameraDevice) device);
                }
            }
            if (this.b == null) {
                this.b = new HashMap<>();
            }
            for (Map.Entry<String, CameraDevice> value : this.b.entrySet()) {
                CameraDevice cameraDevice = (CameraDevice) value.getValue();
            }
            for (Device device2 : arrayList) {
                if ((device2 instanceof CameraDevice) && device2.resetFlag == 1) {
                    this.b.remove(device2.did);
                }
            }
            List<T> a2 = a(this.b, arrayList);
            if (a2.size() > 0) {
                this.c.clear();
                this.c.addAll(this.d);
                for (T t : a2) {
                    Iterator<CameraDevice> it = this.c.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        CameraDevice next = it.next();
                        if (next.did.equalsIgnoreCase(t.did)) {
                            this.c.remove(next);
                            break;
                        }
                    }
                    t.userId = WifiUtil.a(CommonApplication.getAppContext());
                    this.c.add(t);
                    t.isNew = true;
                }
                searchDeviceListener.a(a2);
                this.b = b(arrayList);
                return;
            }
            return;
        }
        SearchCameraDevice.getInstance().startSearch(new SearchCameraDevice.DeviceListener() {
            public void onDevices(ArrayList<CameraDevice> arrayList) {
                if (CameraDeviceSearch.this.b == null) {
                    HashMap unused = CameraDeviceSearch.this.b = CameraDeviceSearch.this.b(arrayList);
                }
                List<T> a2 = CameraDeviceSearch.this.a(CameraDeviceSearch.this.b, arrayList);
                if (a2.size() > 0) {
                    CameraDeviceSearch.this.c.clear();
                    CameraDeviceSearch.this.c.addAll(CameraDeviceSearch.this.d);
                    for (T t : a2) {
                        Iterator it = CameraDeviceSearch.this.c.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            CameraDevice cameraDevice = (CameraDevice) it.next();
                            if (cameraDevice.did.equalsIgnoreCase(t.did)) {
                                CameraDeviceSearch.this.c.remove(cameraDevice);
                                break;
                            }
                        }
                        t.userId = WifiUtil.a(CommonApplication.getAppContext());
                        CameraDeviceSearch.this.c.add(t);
                        t.isNew = true;
                    }
                    searchDeviceListener.a(a2);
                    HashMap unused2 = CameraDeviceSearch.this.b = CameraDeviceSearch.this.b(arrayList);
                }
            }
        });
    }

    public void b() {
        this.d.clear();
        this.d.addAll(this.c);
    }

    public void a(Device device) {
        if ((device instanceof CameraDevice) && device.pid == g()) {
            Iterator<CameraDevice> it = this.c.iterator();
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
            this.c.add((CameraDevice) device);
        }
    }
}
