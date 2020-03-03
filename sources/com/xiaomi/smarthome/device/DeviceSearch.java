package com.xiaomi.smarthome.device;

import com.xiaomi.smarthome.device.Device;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public abstract class DeviceSearch<T extends Device> {
    protected boolean h = false;
    protected boolean i = false;
    protected boolean j = false;
    Collection<? extends Device> k;
    REMOTESTATE l = REMOTESTATE.REMOTE_NOT_UPDATING;

    public enum REMOTESTATE {
        REMOTE_NOT_UPDATING,
        REMOTE_SUCCESS,
        REMOTE_FAILED,
        REMOTE_NOT_LOGIN
    }

    public interface SearchDeviceListener {
        <T> void a(List<T> list);
    }

    public void a(Device device) {
    }

    public abstract void a(Collection<? extends Device> collection, SearchDeviceListener searchDeviceListener);

    public void b() {
    }

    public void b(Device device) {
    }

    public abstract void c();

    public abstract List<T> d();

    public abstract void e();

    public void f() {
    }

    public int g() {
        return -1;
    }

    public void h() {
        this.l = REMOTESTATE.REMOTE_NOT_UPDATING;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public void b(Collection<? extends Device> collection, REMOTESTATE remotestate) {
        this.k = collection;
        this.l = remotestate;
    }

    public List<T> a(HashMap<String, T> hashMap, List<T> list) {
        ArrayList arrayList = new ArrayList();
        for (T t : list) {
            if (!hashMap.containsKey(t.did)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public HashMap<String, T> b(List<T> list) {
        HashMap<String, T> hashMap = new HashMap<>();
        for (T t : list) {
            hashMap.put(t.did, t);
        }
        return hashMap;
    }
}
