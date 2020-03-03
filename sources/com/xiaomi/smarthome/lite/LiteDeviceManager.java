package com.xiaomi.smarthome.lite;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.api.UserConfigApi;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LiteDeviceManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19358a = "LiteDeviceManager";
    public static final String b = "grid_tag_info";
    public static final String c = "timestamp";
    private static LiteDeviceManager k;
    boolean d = false;
    boolean e = false;
    boolean f = false;
    long g;
    boolean h = false;
    List<LiteDeviceAbstract> i = new ArrayList();
    List<LiteDeviceAbstract> j = new ArrayList();
    /* access modifiers changed from: private */
    public ArrayList<DeviceGridGroup> l = new ArrayList<>();

    private LiteDeviceManager() {
    }

    public static synchronized LiteDeviceManager a() {
        LiteDeviceManager liteDeviceManager;
        synchronized (LiteDeviceManager.class) {
            if (k == null) {
                k = new LiteDeviceManager();
            }
            liteDeviceManager = k;
        }
        return liteDeviceManager;
    }

    public void a(List<LiteDeviceAbstract> list) {
        this.i = list;
    }

    public void b(List<LiteDeviceAbstract> list) {
        this.j = list;
    }

    public List<LiteDeviceAbstract> b() {
        return this.j;
    }

    public boolean c() {
        return this.h;
    }

    public List<LiteDeviceAbstract> d() {
        return this.i;
    }

    public void a(final Callback<Void> callback) {
        AsyncTaskUtils.a(new AsyncTask<Void, Void, List<LiteDeviceAbstract>>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public List<LiteDeviceAbstract> doInBackground(Void... voidArr) {
                return LiteDeviceManager.this.d(SmartHomeDeviceManager.a().k());
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(List<LiteDeviceAbstract> list) {
                LiteDeviceManager.a().a(list);
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }
        }, new Void[0]);
    }

    public void e() {
        this.e = false;
        this.d = false;
        this.l = new ArrayList<>();
        this.i = new ArrayList();
    }

    public List<LiteDeviceAbstract> c(List<Device> list) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            a(list.get(i2), arrayList, (String) null, (DeviceTagInterface) null);
        }
        return arrayList;
    }

    public synchronized List<LiteDeviceAbstract> d(List<Device> list) {
        UserConfig a2;
        if (!CoreApi.a().q()) {
            return new ArrayList();
        }
        if (!this.d && (a2 = UserConfigApi.a().a(0, "5")) != null) {
            this.d = true;
            this.l = a(a2);
        }
        DeviceTagInterface<Device> b2 = SmartHomeDeviceHelper.a().b();
        String u = b2.u();
        List<Device> r = b2.r();
        ArrayList arrayList = new ArrayList();
        if (r != null) {
            for (Device next : r) {
                if (next.isBinded()) {
                    arrayList.add(next);
                }
            }
        }
        if (list != null && list.size() > 0) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                Device device = list.get(i2);
                Device device2 = null;
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    Device device3 = (Device) arrayList.get(i3);
                    if (device3.did.equals(device.parentId)) {
                        device2 = device3;
                    }
                }
                if (device2 != null) {
                    arrayList.add(device);
                }
            }
        }
        if (TextUtils.isEmpty(b2.u()) && IRDeviceUtil.c()) {
            arrayList.add(IRDeviceUtil.b());
        }
        return a(e(arrayList), u, b2);
    }

    private static List<Device> e(List<Device> list) {
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Device device = list.get(i2);
            if (!hashMap.containsKey(device.did)) {
                hashMap.put(device.did, device);
            }
        }
        return new ArrayList(hashMap.values());
    }

    /* access modifiers changed from: package-private */
    public List<LiteDeviceAbstract> a(List<Device> list, String str, DeviceTagInterface deviceTagInterface) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (Device a2 : list) {
                a(a2, arrayList, str, deviceTagInterface);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (String next : b(str)) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                LiteDeviceAbstract liteDeviceAbstract = (LiteDeviceAbstract) it.next();
                if ((liteDeviceAbstract instanceof LiteDevice) && next.equals(((LiteDevice) liteDeviceAbstract).b)) {
                    arrayList2.add(liteDeviceAbstract);
                    arrayList.remove(liteDeviceAbstract);
                    break;
                }
            }
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (arrayList.get(i2) instanceof LiteDevice) {
                LiteDevice liteDevice = (LiteDevice) arrayList.get(i2);
                if (liteDevice.f19353a.isNew) {
                    arrayList2.add(0, liteDevice);
                }
            }
            arrayList2.add(arrayList.get(i2));
        }
        return arrayList2;
    }

    /* access modifiers changed from: package-private */
    public void a(Device device, List<LiteDeviceAbstract> list, String str, DeviceTagInterface deviceTagInterface) {
        String str2;
        String[] split;
        String str3;
        LiteUIConfig e2 = LiteUIConfigManager.a().e(device.model);
        if (e2 == null || e2.f19366a == null || e2.c == null) {
            list.add(new LiteDevice(device.name, device, device.did));
            return;
        }
        for (int i2 = 0; i2 < e2.c.size(); i2++) {
            LiteComConfig liteComConfig = e2.c.get(i2);
            String str4 = device.did;
            if (e2.c.size() > 1) {
                str4 = device.did + "__" + liteComConfig.f19352a;
            }
            if (e2.c.size() <= 1 || TextUtils.isEmpty(liteComConfig.b)) {
                str2 = device.name;
            } else {
                str2 = device.name;
                if (e2.f19366a.equals("lumi.ctrl_neutral2.v1") && (split = device.name.split("/")) != null && split.length >= 2) {
                    if (liteComConfig.f19352a.equals("ctrl_neutral2_left")) {
                        str3 = split[0];
                    } else {
                        str3 = split[1];
                    }
                    str2 = str3;
                }
            }
            list.add(new LiteDevice(str2, device, str4));
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str) && this.l != null) {
            Iterator<DeviceGridGroup> it = this.l.iterator();
            while (it.hasNext()) {
                DeviceGridGroup next = it.next();
                if (str.equals(next.f19362a)) {
                    this.l.remove(next);
                    this.f = true;
                    return;
                }
            }
            a(false);
        }
    }

    public void a(String str, String str2) {
        int indexOf;
        Iterator<DeviceGridGroup> it = this.l.iterator();
        while (it.hasNext()) {
            DeviceGridGroup next = it.next();
            if (TextUtils.equals(str, next.f19362a) && (indexOf = next.b.indexOf(str2)) >= 0 && indexOf < next.b.size()) {
                next.b.remove(indexOf);
            }
        }
    }

    public void b(String str, String str2) {
        if (!TextUtils.isEmpty(str) && this.l != null) {
            Iterator<DeviceGridGroup> it = this.l.iterator();
            while (it.hasNext()) {
                DeviceGridGroup next = it.next();
                if (str.equals(next.f19362a)) {
                    next.f19362a = str2;
                    this.f = true;
                    return;
                }
            }
            a(false);
        }
    }

    public void a(boolean z) {
        String u = SmartHomeDeviceHelper.a().b().u();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            arrayList.add(((LiteDevice) this.i.get(i2)).b);
        }
        a(u, (List<String>) arrayList);
        a(this.l, z);
    }

    public List<LiteDeviceAbstract> a(int i2, int i3) {
        this.i.add(i3, this.i.remove(i2));
        a(true);
        return this.i;
    }

    public List<LiteDeviceAbstract> b(int i2, int i3) {
        return this.i;
    }

    public static class DeviceGridGroup {

        /* renamed from: a  reason: collision with root package name */
        public String f19362a;
        public List<String> b;

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("tag", this.f19362a == null ? "" : this.f19362a);
                JSONArray jSONArray = new JSONArray();
                if (this.b != null) {
                    for (String put : this.b) {
                        jSONArray.put(put);
                    }
                }
                jSONObject.put("gridDids", jSONArray);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        public void a(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.f19362a = jSONObject.optString("tag");
                this.b = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("gridDids");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        String optString = optJSONArray.optString(i);
                        if (!TextUtils.isEmpty(optString)) {
                            this.b.add(optString);
                        }
                    }
                }
            }
        }
    }

    public List<String> b(String str) {
        if (str == null) {
            str = "";
        }
        if (this.l != null) {
            Iterator<DeviceGridGroup> it = this.l.iterator();
            while (it.hasNext()) {
                DeviceGridGroup next = it.next();
                if (TextUtils.isEmpty(str) && TextUtils.isEmpty(next.f19362a)) {
                    return next.b;
                }
                if (str.equals(next.f19362a)) {
                    return next.b;
                }
            }
        }
        return new ArrayList();
    }

    public synchronized void a(String str, List<String> list) {
        if (str == null) {
            str = "";
        }
        if (this.l == null) {
            this.l = new ArrayList<>();
        }
        Iterator<DeviceGridGroup> it = this.l.iterator();
        while (it.hasNext()) {
            DeviceGridGroup next = it.next();
            if (TextUtils.isEmpty(str) && TextUtils.isEmpty(next.f19362a)) {
                next.b = list;
                return;
            } else if (str.equals(next.f19362a)) {
                next.b = list;
                return;
            }
        }
        DeviceGridGroup deviceGridGroup = new DeviceGridGroup();
        deviceGridGroup.f19362a = str;
        deviceGridGroup.b = list;
        this.l.add(deviceGridGroup);
    }

    private ArrayList<DeviceGridGroup> a(JSONArray jSONArray) {
        int length;
        Log.d(f19358a, "parseGridTagsFromJSONArray:" + jSONArray.toString());
        ArrayList<DeviceGridGroup> arrayList = new ArrayList<>();
        if (jSONArray == null || (length = jSONArray.length()) == 0) {
            return arrayList;
        }
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                DeviceGridGroup deviceGridGroup = new DeviceGridGroup();
                deviceGridGroup.a(optJSONObject);
                arrayList.add(deviceGridGroup);
            }
        }
        return arrayList;
    }

    private synchronized JSONArray b(ArrayList<DeviceGridGroup> arrayList) {
        JSONArray jSONArray;
        jSONArray = new JSONArray();
        if (arrayList != null) {
            Iterator<DeviceGridGroup> it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().a());
            }
        }
        return jSONArray;
    }

    public ArrayList<DeviceGridGroup> a(UserConfig userConfig) {
        Log.d(f19358a, "handleUserConfigData");
        ArrayList<DeviceGridGroup> arrayList = new ArrayList<>();
        if (userConfig == null || userConfig.D == null) {
            return arrayList;
        }
        ArrayList<DeviceGridGroup> arrayList2 = arrayList;
        for (int i2 = 0; i2 < userConfig.D.size(); i2++) {
            UserConfig.UserConfigAttr userConfigAttr = userConfig.D.get(i2);
            if (!TextUtils.isEmpty(userConfigAttr.f16437a) && !TextUtils.isEmpty(userConfigAttr.b)) {
                if (b.equals(userConfigAttr.f16437a)) {
                    try {
                        arrayList2 = a(new JSONArray(userConfigAttr.b));
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                } else if ("timestamp".equals(userConfigAttr.f16437a)) {
                    try {
                        long longValue = Long.getLong(userConfigAttr.b).longValue();
                        this.h = longValue != this.g;
                        this.g = longValue;
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return arrayList2;
    }

    public void b(Callback<Void> callback) {
        if (!this.e) {
            c(callback);
        }
    }

    public void c(final Callback<Void> callback) {
        Log.d(f19358a, "loadFromServer");
        UserConfigApi.a().a((Context) CommonApplication.getApplication(), 0, new String[]{"5"}, (AsyncCallback<ArrayList<UserConfig>, Error>) new AsyncCallback<ArrayList<UserConfig>, Error>() {
            /* renamed from: a */
            public void onSuccess(ArrayList<UserConfig> arrayList) {
                UserConfig userConfig;
                LiteDeviceManager.this.d = true;
                LiteDeviceManager.this.e = true;
                if (arrayList == null || arrayList.size() <= 0) {
                    userConfig = null;
                } else {
                    userConfig = arrayList.get(0);
                    UserConfigApi.a().a(userConfig, (AsyncCallback<Void, Error>) null);
                }
                ArrayList unused = LiteDeviceManager.this.l = LiteDeviceManager.this.a(userConfig);
                if (HostSetting.g) {
                    for (int i = 0; i < LiteDeviceManager.this.l.size(); i++) {
                        DeviceGridGroup deviceGridGroup = (DeviceGridGroup) LiteDeviceManager.this.l.get(i);
                        if (deviceGridGroup != null) {
                            Log.d(LiteDeviceManager.f19358a, Arrays.toString(deviceGridGroup.b.toArray()));
                        }
                    }
                }
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }

            public void onFailure(Error error) {
                if (callback != null) {
                    callback.onFailure(error.a(), error.b());
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public UserConfig a(ArrayList<DeviceGridGroup> arrayList) {
        UserConfig userConfig = new UserConfig();
        userConfig.B = 0;
        userConfig.C = "5";
        userConfig.D = new ArrayList<>();
        JSONArray jSONArray = new JSONArray();
        if (arrayList != null) {
            Iterator<DeviceGridGroup> it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().a());
            }
        }
        userConfig.D.add(new UserConfig.UserConfigAttr(b, jSONArray.toString()));
        userConfig.D.add(new UserConfig.UserConfigAttr("timestamp", String.valueOf(System.currentTimeMillis() / 1000)));
        return userConfig;
    }

    public void a(ArrayList<DeviceGridGroup> arrayList, boolean z) {
        UserConfig a2 = a(arrayList);
        UserConfigApi.a().a(a2, (AsyncCallback<Void, Error>) null);
        if (z) {
            this.f = true;
        }
        if (!z && this.e && this.f) {
            UserConfigApi.a().a((Context) CommonApplication.getApplication(), a2, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                public void onFailure(Error error) {
                }

                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    LiteDeviceManager.this.f = false;
                }
            });
        }
    }
}
