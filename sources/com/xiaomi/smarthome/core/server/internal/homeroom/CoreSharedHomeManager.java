package com.xiaomi.smarthome.core.server.internal.homeroom;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.NetHandle;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api;
import com.xiaomi.smarthome.core.server.internal.device.api.DeviceListApi;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.file.FileUtils;
import com.xiaomi.smarthome.frame.log.CoreLogUtilGrey;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.MD5;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CoreSharedHomeManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f14577a = "CoreSharedHomeManager";
    private static final int b = 200;
    private static volatile CoreSharedHomeManager c = null;
    private static final String g = "shared_home_devices_list_data_";
    /* access modifiers changed from: private */
    public Map<String, HomeDeviceInfo> d = new ConcurrentHashMap();
    private Home e;
    /* access modifiers changed from: private */
    public Set<Home> f = new HashSet();

    private CoreSharedHomeManager() {
        f();
    }

    public static CoreSharedHomeManager a() {
        if (c == null) {
            synchronized (CoreSharedHomeManager.class) {
                if (c == null) {
                    c = new CoreSharedHomeManager();
                }
            }
        }
        return c;
    }

    public void a(Home home) {
        String str = f14577a;
        LogUtilGrey.a(str, "setCurrentHome " + home);
        this.e = home;
        synchronized (this) {
            if (home != null) {
                try {
                    if (!this.f.contains(home)) {
                        this.f.add(home);
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        }
        e();
    }

    public void b() {
        this.d = new ConcurrentHashMap();
        this.e = null;
        this.f = new HashSet();
    }

    public List<String> a(String str) {
        HomeDeviceInfo homeDeviceInfo = this.d.get(str);
        if (homeDeviceInfo == null) {
            return null;
        }
        return homeDeviceInfo.f();
    }

    public HomeDeviceInfo b(String str) {
        return this.d.get(str);
    }

    public List<String> a(String str, String str2) {
        List<Room> d2;
        HomeDeviceInfo homeDeviceInfo = this.d.get(str);
        if (homeDeviceInfo == null || homeDeviceInfo.b == null || (d2 = homeDeviceInfo.b.d()) == null || d2.isEmpty()) {
            return null;
        }
        for (int i = 0; i < d2.size(); i++) {
            Room room = d2.get(i);
            if (room != null) {
                return room.h();
            }
        }
        return null;
    }

    public Home c() {
        return this.e;
    }

    public synchronized void b(Home home) {
        if (home != null) {
            if (!this.f.contains(home)) {
                this.f.add(home);
            }
        }
    }

    public NetHandle a(final NetCallback<ArrayList<Device>, NetError> netCallback) {
        final Home home = this.e;
        if (home == null || home.o()) {
            if (netCallback != null) {
                a(home, (ArrayList<Device>) null, netCallback);
            }
            return new NetHandle((Call) null);
        }
        String str = f14577a;
        CoreLogUtilGrey.a(str, "updateSharedHomeDeviceList " + home.j());
        return a(home.n(), Long.parseLong(home.j()), 200, (NetCallback<ArrayList<Device>, NetError>) new NetCallback<ArrayList<Device>, NetError>() {
            /* renamed from: a */
            public void b(ArrayList<Device> arrayList) {
            }

            /* renamed from: b */
            public void a(ArrayList<Device> arrayList) {
                CoreSharedHomeManager.this.a(home, arrayList, (NetCallback<ArrayList<Device>, NetError>) netCallback);
            }

            public void a(NetError netError) {
                if (netCallback != null) {
                    netCallback.a(netError);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(Home home, ArrayList<Device> arrayList, NetCallback<ArrayList<Device>, NetError> netCallback) {
        HomeDeviceInfo homeDeviceInfo;
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null) {
            arrayList2.addAll(arrayList);
        }
        for (Home home2 : new HashSet(this.f)) {
            if (!(home2 == null || home2.equals(home) || (homeDeviceInfo = this.d.get(home2.j())) == null || homeDeviceInfo.d() == null)) {
                arrayList2.addAll(homeDeviceInfo.d());
            }
        }
        if (netCallback != null) {
            netCallback.a(arrayList2);
        }
    }

    public NetHandle a(long j, long j2, NetCallback<ArrayList<Device>, NetError> netCallback) {
        return a(j, j2, 200, netCallback);
    }

    private NetHandle a(long j, long j2, int i, NetCallback<ArrayList<Device>, NetError> netCallback) {
        long j3 = j2;
        CoreLogUtilGrey.a(f14577a, "updateSharedHomeDeviceList in");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("home_owner", j);
            jSONObject.put("home_id", j3);
            try {
                jSONObject.put("limit", i);
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            int i2 = i;
            e.printStackTrace();
            NetHandle.NetHandleWrap netHandleWrap = new NetHandle.NetHandleWrap();
            String str = f14577a;
            CoreLogUtilGrey.a(str, "request /v2/home/home_device_list homeId=" + j3);
            final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            final NetHandle.NetHandleWrap netHandleWrap2 = netHandleWrap;
            final NetCallback<ArrayList<Device>, NetError> netCallback2 = netCallback;
            final long j4 = j2;
            final long j5 = j;
            final int i3 = i;
            netHandleWrap.a(SmartHomeRc4Api.a().a(DeviceListApi.a(jSONObject, "/v2/home/home_device_list"), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                /* renamed from: a */
                public void b(NetResult netResult) {
                }

                /* renamed from: b */
                public void a(NetResult netResult) {
                    if (netHandleWrap2.b()) {
                        if (netCallback2 != null) {
                            netCallback2.a(new NetError(-1, "cancelled"));
                        }
                    } else if (netResult == null || TextUtils.isEmpty(netResult.c)) {
                        CoreSharedHomeManager.this.d.remove(String.valueOf(j4));
                        if (netCallback2 != null) {
                            netCallback2.a(new NetError(ErrorCode.ERROR_RESPONSE_JSON_FAIL.getCode(), "empty result"));
                        }
                        CoreLogUtilGrey.a(CoreSharedHomeManager.f14577a, "updateSharedHomeDeviceList empty network response");
                    } else {
                        try {
                            JSONObject optJSONObject = new JSONObject(netResult.c).optJSONObject("result");
                            HomeDeviceInfo homeDeviceInfo = (HomeDeviceInfo) CoreSharedHomeManager.this.d.get(String.valueOf(j4));
                            int i = 0;
                            if (homeDeviceInfo == null) {
                                homeDeviceInfo = HomeDeviceInfo.a(optJSONObject);
                                CoreSharedHomeManager.this.d.put(String.valueOf(j4), homeDeviceInfo);
                            } else {
                                HomeDeviceInfo a2 = HomeDeviceInfo.a(optJSONObject);
                                if (atomicBoolean.getAndSet(false)) {
                                    CoreSharedHomeManager.this.d.put(String.valueOf(j4), a2);
                                    homeDeviceInfo = a2;
                                } else {
                                    homeDeviceInfo.a(a2);
                                }
                            }
                            CoreSharedHomeManager.this.f.add(homeDeviceInfo.b);
                            atomicBoolean.set(false);
                            homeDeviceInfo.a(CoreApi.a().s());
                            String d2 = CoreSharedHomeManager.f14577a;
                            StringBuilder sb = new StringBuilder();
                            sb.append("home_device_list current size=");
                            sb.append(homeDeviceInfo.d() == null ? 0 : homeDeviceInfo.d().size());
                            CoreLogUtilGrey.a(d2, sb.toString());
                            if (optJSONObject.isNull("has_more") || !optJSONObject.optBoolean("has_more") || optJSONObject.isNull("max_did")) {
                                CoreSharedHomeManager.this.a(homeDeviceInfo);
                                String d3 = CoreSharedHomeManager.f14577a;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("updateSharedHomeDeviceList home_device_list total size=");
                                if (homeDeviceInfo.d() != null) {
                                    i = homeDeviceInfo.d().size();
                                }
                                sb2.append(i);
                                CoreLogUtilGrey.a(d3, sb2.toString());
                                if (netCallback2 != null) {
                                    netCallback2.a((ArrayList) homeDeviceInfo.d());
                                }
                                CoreSharedHomeManager.this.e();
                                return;
                            }
                            try {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("home_owner", j5);
                                jSONObject.put("home_id", j4);
                                jSONObject.put("limit", i3);
                                jSONObject.put("start_did", optJSONObject.optString("max_did"));
                                netHandleWrap2.a(SmartHomeRc4Api.a().a(DeviceListApi.a(jSONObject, "/v2/home/home_device_list"), (NetCallback<NetResult, NetError>) this));
                            } catch (Exception | JSONException unused) {
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            String d4 = CoreSharedHomeManager.f14577a;
                            CoreLogUtilGrey.a(d4, "updateSharedHomeDeviceList exception1:" + e2.getMessage());
                            if (netCallback2 != null) {
                                netCallback2.a(new NetError(-1, e2.getMessage()));
                            }
                        }
                    }
                }

                public void a(NetError netError) {
                    String str;
                    if (netCallback2 != null) {
                        netCallback2.a(netError);
                    }
                    String d2 = CoreSharedHomeManager.f14577a;
                    StringBuilder sb = new StringBuilder();
                    sb.append("updateSharedHomeDeviceList onFailure ");
                    if (netError == null) {
                        str = null;
                    } else {
                        str = netError.a() + "," + netError.b();
                    }
                    sb.append(str);
                    CoreLogUtilGrey.a(d2, sb.toString());
                }
            }));
            return netHandleWrap;
        }
        NetHandle.NetHandleWrap netHandleWrap3 = new NetHandle.NetHandleWrap();
        String str2 = f14577a;
        CoreLogUtilGrey.a(str2, "request /v2/home/home_device_list homeId=" + j3);
        final AtomicBoolean atomicBoolean2 = new AtomicBoolean(true);
        final NetHandle.NetHandleWrap netHandleWrap22 = netHandleWrap3;
        final NetCallback<ArrayList<Device>, NetError> netCallback22 = netCallback;
        final long j42 = j2;
        final long j52 = j;
        final int i32 = i;
        netHandleWrap3.a(SmartHomeRc4Api.a().a(DeviceListApi.a(jSONObject, "/v2/home/home_device_list"), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                if (netHandleWrap22.b()) {
                    if (netCallback22 != null) {
                        netCallback22.a(new NetError(-1, "cancelled"));
                    }
                } else if (netResult == null || TextUtils.isEmpty(netResult.c)) {
                    CoreSharedHomeManager.this.d.remove(String.valueOf(j42));
                    if (netCallback22 != null) {
                        netCallback22.a(new NetError(ErrorCode.ERROR_RESPONSE_JSON_FAIL.getCode(), "empty result"));
                    }
                    CoreLogUtilGrey.a(CoreSharedHomeManager.f14577a, "updateSharedHomeDeviceList empty network response");
                } else {
                    try {
                        JSONObject optJSONObject = new JSONObject(netResult.c).optJSONObject("result");
                        HomeDeviceInfo homeDeviceInfo = (HomeDeviceInfo) CoreSharedHomeManager.this.d.get(String.valueOf(j42));
                        int i = 0;
                        if (homeDeviceInfo == null) {
                            homeDeviceInfo = HomeDeviceInfo.a(optJSONObject);
                            CoreSharedHomeManager.this.d.put(String.valueOf(j42), homeDeviceInfo);
                        } else {
                            HomeDeviceInfo a2 = HomeDeviceInfo.a(optJSONObject);
                            if (atomicBoolean2.getAndSet(false)) {
                                CoreSharedHomeManager.this.d.put(String.valueOf(j42), a2);
                                homeDeviceInfo = a2;
                            } else {
                                homeDeviceInfo.a(a2);
                            }
                        }
                        CoreSharedHomeManager.this.f.add(homeDeviceInfo.b);
                        atomicBoolean2.set(false);
                        homeDeviceInfo.a(CoreApi.a().s());
                        String d2 = CoreSharedHomeManager.f14577a;
                        StringBuilder sb = new StringBuilder();
                        sb.append("home_device_list current size=");
                        sb.append(homeDeviceInfo.d() == null ? 0 : homeDeviceInfo.d().size());
                        CoreLogUtilGrey.a(d2, sb.toString());
                        if (optJSONObject.isNull("has_more") || !optJSONObject.optBoolean("has_more") || optJSONObject.isNull("max_did")) {
                            CoreSharedHomeManager.this.a(homeDeviceInfo);
                            String d3 = CoreSharedHomeManager.f14577a;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("updateSharedHomeDeviceList home_device_list total size=");
                            if (homeDeviceInfo.d() != null) {
                                i = homeDeviceInfo.d().size();
                            }
                            sb2.append(i);
                            CoreLogUtilGrey.a(d3, sb2.toString());
                            if (netCallback22 != null) {
                                netCallback22.a((ArrayList) homeDeviceInfo.d());
                            }
                            CoreSharedHomeManager.this.e();
                            return;
                        }
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("home_owner", j52);
                            jSONObject.put("home_id", j42);
                            jSONObject.put("limit", i32);
                            jSONObject.put("start_did", optJSONObject.optString("max_did"));
                            netHandleWrap22.a(SmartHomeRc4Api.a().a(DeviceListApi.a(jSONObject, "/v2/home/home_device_list"), (NetCallback<NetResult, NetError>) this));
                        } catch (Exception | JSONException unused) {
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        String d4 = CoreSharedHomeManager.f14577a;
                        CoreLogUtilGrey.a(d4, "updateSharedHomeDeviceList exception1:" + e2.getMessage());
                        if (netCallback22 != null) {
                            netCallback22.a(new NetError(-1, e2.getMessage()));
                        }
                    }
                }
            }

            public void a(NetError netError) {
                String str;
                if (netCallback22 != null) {
                    netCallback22.a(netError);
                }
                String d2 = CoreSharedHomeManager.f14577a;
                StringBuilder sb = new StringBuilder();
                sb.append("updateSharedHomeDeviceList onFailure ");
                if (netError == null) {
                    str = null;
                } else {
                    str = netError.a() + "," + netError.b();
                }
                sb.append(str);
                CoreLogUtilGrey.a(d2, sb.toString());
            }
        }));
        return netHandleWrap3;
    }

    /* access modifiers changed from: private */
    public void a(HomeDeviceInfo homeDeviceInfo) {
        if (homeDeviceInfo != null && homeDeviceInfo.d() != null && !homeDeviceInfo.d().isEmpty()) {
            try {
                List<Device> d2 = homeDeviceInfo.d();
                ArrayList arrayList = new ArrayList();
                int i = 0;
                for (int i2 = 0; i2 < d2.size(); i2++) {
                    Device device = d2.get(i2);
                    if (device != null) {
                        if (!TextUtils.isEmpty(device.A())) {
                            arrayList.add(device);
                        }
                    }
                }
                if (!arrayList.isEmpty()) {
                    Map<String, Device> e2 = homeDeviceInfo.e();
                    while (i < arrayList.size()) {
                        Device device2 = (Device) arrayList.get(i);
                        if (device2 != null) {
                            if (e2.get(device2.A()) != null) {
                                arrayList.remove(i);
                                i--;
                            }
                        }
                        i++;
                    }
                    if (!arrayList.isEmpty()) {
                        CoreLogUtilGrey.a(f14577a, "filterInvalidDevices found " + arrayList.size() + " invalid sub-devices");
                        d2.removeAll(arrayList);
                        homeDeviceInfo.b(d2);
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                CoreLogUtilGrey.a(f14577a, "filterInvalidDevices exception " + e3.getMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        try {
            FileUtils.b(h(), g());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void f() {
        try {
            String l = FileUtils.l(g());
            if (!TextUtils.isEmpty(l)) {
                c(l);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String g() {
        return CoreService.getAppContext().getFilesDir() + File.separator + "shared_home" + File.separator + g + MD5.a(CoreApi.a().s());
    }

    private void c(String str) throws JSONException {
        if (GlobalSetting.q) {
            String str2 = f14577a;
            CoreLogUtilGrey.a(str2, "deSerializeFromString in:" + str);
        }
        JSONObject jSONObject = new JSONObject(str);
        if (TextUtils.equals(jSONObject.optString("uid"), CoreApi.a().s())) {
            if (!jSONObject.isNull("current_home")) {
                this.e = Home.a(jSONObject.optJSONObject("current_home"));
            }
            jSONObject.optLong("ts");
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray != null && optJSONArray.length() != 0) {
                double length = (double) optJSONArray.length();
                Double.isNaN(length);
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap((int) (length * 1.5d), 0.75f);
                for (int i = 0; i < optJSONArray.length(); i++) {
                    HomeDeviceInfo a2 = HomeDeviceInfo.a(optJSONArray.optJSONObject(i));
                    if (!(a2 == null || a2.b == null)) {
                        String str3 = f14577a;
                        StringBuilder sb = new StringBuilder();
                        sb.append("deSerializeFromString ");
                        sb.append(a2.c().j());
                        sb.append(":");
                        sb.append(a2.c().k());
                        sb.append(":");
                        sb.append(a2.d() == null ? 0 : a2.d().size());
                        sb.append(" devices");
                        CoreLogUtilGrey.a(str3, sb.toString());
                        concurrentHashMap.put(a2.b.j(), a2);
                    }
                }
                this.d = concurrentHashMap;
            }
        }
    }

    private String h() throws JSONException {
        Map<String, HomeDeviceInfo> map = this.d;
        JSONObject jSONObject = null;
        if (map == null || map.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (HomeDeviceInfo next : map.values()) {
            if (next != null) {
                jSONArray.put(next.a());
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("data", jSONArray);
        jSONObject2.put("uid", CoreApi.a().s());
        jSONObject2.put("ts", System.currentTimeMillis());
        if (this.e != null) {
            jSONObject = this.e.p();
        }
        jSONObject2.put("current_home", jSONObject);
        return jSONObject2.toString();
    }
}
