package com.xiaomi.smarthome.homeroom.homedevicelist;

import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.homeroom.Home;
import com.xiaomi.smarthome.core.server.internal.homeroom.HomeDeviceInfo;
import com.xiaomi.smarthome.core.server.internal.homeroom.Room;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.homeroom.BaseDataLoadManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.util.MD5;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SharedHomeDeviceManager extends BaseDataLoadManager<HomeDeviceInfo> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f18252a = "sp_file_name_shared_home_device_manager";
    public static final String b = "home_member";
    private static final String c = "SharedHomeDeviceManager";
    private static SharedHomeDeviceManager e;
    /* access modifiers changed from: private */
    public Map<String, HomeDeviceInfo> d = new ConcurrentHashMap();

    /* access modifiers changed from: protected */
    public Intent a(boolean z) {
        return null;
    }

    private SharedHomeDeviceManager() {
    }

    public static SharedHomeDeviceManager b() {
        if (e == null) {
            synchronized (SharedHomeDeviceManager.class) {
                if (e == null) {
                    e = new SharedHomeDeviceManager();
                }
            }
        }
        return e;
    }

    public void c() {
        DeviceCountManager.a().c();
    }

    private HomeDeviceInfo c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        HomeDeviceInfo homeDeviceInfo = this.d.get(str);
        if (homeDeviceInfo == null && (homeDeviceInfo = d(str)) != null) {
            this.d.put(str, homeDeviceInfo);
        }
        return homeDeviceInfo;
    }

    private String f() {
        String s = CoreApi.a().s();
        if (TextUtils.isEmpty(s)) {
            return f18252a;
        }
        return "sp_file_name_shared_home_device_manager_" + MD5.a(s);
    }

    public void d() {
        Set<String> keySet = this.d.keySet();
        if (keySet != null) {
            try {
                for (String next : keySet) {
                    this.d.put(next, d(next));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public HomeDeviceInfo d(String str) {
        HomeDeviceInfo j = CoreApi.a().j(str);
        if (j != null) {
            a(j);
        }
        return j;
    }

    private void a(HomeDeviceInfo homeDeviceInfo) {
        if (homeDeviceInfo != null && homeDeviceInfo.c() != null) {
            Home c2 = homeDeviceInfo.c();
            com.xiaomi.smarthome.homeroom.model.Home j = HomeManager.a().j(c2.j());
            if (j != null) {
                j.b(c2.m());
                List<Room> d2 = c2.d();
                List<com.xiaomi.smarthome.homeroom.model.Room> d3 = j.d();
                if (d2 != null && d3 != null && !d2.isEmpty() && !d3.isEmpty()) {
                    for (int i = 0; i < d2.size(); i++) {
                        Room room = d2.get(i);
                        if (room != null) {
                            com.xiaomi.smarthome.homeroom.model.Room room2 = null;
                            int i2 = 0;
                            while (true) {
                                if (i2 < d3.size()) {
                                    com.xiaomi.smarthome.homeroom.model.Room room3 = d3.get(i2);
                                    if (room3 != null && TextUtils.equals(room3.d(), room.d())) {
                                        room2 = room3;
                                        break;
                                    }
                                    i2++;
                                } else {
                                    break;
                                }
                            }
                            if (room2 != null) {
                                room2.a(room.h());
                            }
                        }
                    }
                }
            }
        }
    }

    public void a(long j, final AsyncCallback asyncCallback) {
        final String valueOf = String.valueOf(j);
        long j2 = j;
        CoreApi.a().a(HomeManager.a().j(valueOf).o(), j2, (AsyncCallback<Object, Error>) new AsyncCallback<Object, Error>() {
            public void onSuccess(final Object obj) {
                SharedHomeDeviceManager.this.d.put(valueOf, SharedHomeDeviceManager.this.d(valueOf));
                SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IClientDeviceListener) new SmartHomeDeviceManager.IClientDeviceListener() {
                    public void a(int i, Device device) {
                    }

                    public void a(int i) {
                        SmartHomeDeviceManager.a().c((SmartHomeDeviceManager.IClientDeviceListener) this);
                        if (asyncCallback != null) {
                            SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                                public void run() {
                                    asyncCallback.onSuccess(obj);
                                }
                            }, 500);
                        }
                    }

                    public void b(int i) {
                        SmartHomeDeviceManager.a().c((SmartHomeDeviceManager.IClientDeviceListener) this);
                        if (asyncCallback != null) {
                            asyncCallback.onFailure(new Error(-1, "refresh device failed"));
                        }
                    }
                });
                SmartHomeDeviceManager.a().p();
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    public List<Device> a(String str) {
        HomeDeviceInfo c2 = c(str);
        if (c2 == null) {
            return new ArrayList();
        }
        List<String> f = c2.f();
        if (f == null || f.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < f.size(); i++) {
            Device b2 = SmartHomeDeviceManager.a().b(f.get(i));
            if (b2 != null) {
                arrayList.add(b2);
            }
        }
        return arrayList;
    }

    public List<String> b(String str) {
        ArrayList arrayList = new ArrayList();
        HomeDeviceInfo c2 = c(str);
        if (c2 == null) {
            return arrayList;
        }
        return c2.f();
    }

    public void e() {
        this.d = new ConcurrentHashMap();
    }
}
