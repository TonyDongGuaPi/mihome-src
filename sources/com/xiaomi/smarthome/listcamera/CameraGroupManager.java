package com.xiaomi.smarthome.listcamera;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.api.UserConfigApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.MyLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraGroupManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19232a = "CameraGroupManager";
    private static final int b = 1;
    private static final int c = 3;
    private static final String d = "camera_list_prefs";
    private static final String e = "camera_list_order";
    private static final String f = "timestamp";
    private static final String g = "all_camera_show_view_type";
    private static CameraGroupManager h;
    private SharedPreferences i;
    /* access modifiers changed from: private */
    public int j = 0;
    private ArrayList<GroupInfo> k = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<GroupInfo> l = new ArrayList<>();
    private ArrayList<GroupInfo> m = new ArrayList<>();
    /* access modifiers changed from: private */
    public Handler n = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 3) {
                CameraGroupManager.this.l.clear();
                CameraGroupManager.this.l.addAll((ArrayList) message.obj);
            }
        }
    };

    public void b() {
    }

    public static CameraGroupManager a() {
        if (h == null) {
            h = new CameraGroupManager();
        }
        return h;
    }

    private CameraGroupManager() {
        Context appContext = SHApplication.getAppContext();
        this.i = appContext.getSharedPreferences("camera_group_" + CoreApi.a().s(), 0);
    }

    public static class GroupInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f19240a;
        public boolean b = false;
        public boolean c = false;
        public boolean d = false;
        public List<DeviceControlInfo> e = new ArrayList();

        public static class DeviceControlInfo {

            /* renamed from: a  reason: collision with root package name */
            public String f19241a;
            public List<String> b;
        }

        public static GroupInfo a(JSONObject jSONObject) {
            GroupInfo groupInfo = new GroupInfo();
            groupInfo.f19240a = jSONObject.optString("did");
            groupInfo.d = jSONObject.optBoolean("expanded");
            JSONArray optJSONArray = jSONObject.optJSONArray("group_items");
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                DeviceControlInfo deviceControlInfo = new DeviceControlInfo();
                deviceControlInfo.f19241a = optJSONObject.optString("did");
                if (SmartHomeDeviceManager.a().n(deviceControlInfo.f19241a) != null) {
                    deviceControlInfo.b = new ArrayList();
                    if (optJSONObject.has("show_op")) {
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("show_op");
                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                            deviceControlInfo.b.add(optJSONArray2.optString(i2));
                        }
                    }
                    groupInfo.e.add(deviceControlInfo);
                }
            }
            return groupInfo;
        }

        /* access modifiers changed from: package-private */
        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("did", this.f19240a);
                jSONObject.put("expanded", this.d);
                JSONArray jSONArray = new JSONArray();
                for (DeviceControlInfo next : this.e) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("did", next.f19241a);
                    JSONArray jSONArray2 = new JSONArray();
                    if (next.b != null) {
                        for (int i = 0; i < next.b.size(); i++) {
                            jSONArray2.put(next.b.get(i));
                        }
                        jSONObject2.put("show_op", jSONArray2);
                    }
                    jSONArray.put(jSONObject2);
                }
                jSONObject.put("group_items", jSONArray);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }
    }

    /* access modifiers changed from: private */
    public String i() {
        if (!CoreApi.a().q()) {
            return d;
        }
        return CoreApi.a().s() + JSMethod.NOT_SET + d;
    }

    public List<GroupInfo> c() {
        return this.k;
    }

    public List<GroupInfo> d() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<GroupInfo> it = this.k.iterator();
        while (it.hasNext()) {
            GroupInfo next = it.next();
            linkedHashMap.put(next.f19240a, next);
        }
        Iterator<GroupInfo> it2 = this.l.iterator();
        while (it2.hasNext()) {
            if (!linkedHashMap.containsKey(it2.next().f19240a)) {
                it2.remove();
            }
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        Iterator<GroupInfo> it3 = this.l.iterator();
        while (it3.hasNext()) {
            GroupInfo next2 = it3.next();
            linkedHashMap2.put(next2.f19240a, next2);
        }
        Iterator<GroupInfo> it4 = this.k.iterator();
        while (it4.hasNext()) {
            GroupInfo next3 = it4.next();
            if (!linkedHashMap2.containsKey(next3.f19240a)) {
                this.l.add(next3);
            }
        }
        LogUtil.a(f19232a, "mCacheGroupInfoList size:" + this.l.size() + " mGroupInfoList size:" + this.k.size());
        return this.l;
    }

    public GroupInfo a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Iterator<GroupInfo> it = this.k.iterator();
        while (it.hasNext()) {
            GroupInfo next = it.next();
            if (str.equals(next.f19240a)) {
                return next;
            }
        }
        return null;
    }

    public void e() {
        this.k.clear();
        SharedPreferences.Editor edit = this.i.edit();
        edit.putString(i(), "");
        edit.commit();
    }

    public int f() {
        this.j = this.i.getInt(g, this.j);
        return this.j;
    }

    public void a(int i2, final AsyncCallback<Void, Error> asyncCallback) {
        this.j = i2;
        SharedPreferences.Editor edit = this.i.edit();
        edit.putInt(g, this.j);
        edit.commit();
        asyncCallback.onSuccess(null);
        a((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
            }

            public void onFailure(Error error) {
                asyncCallback.onFailure(error);
                int unused = CameraGroupManager.this.j = (CameraGroupManager.this.j + 1) % 2;
            }
        });
    }

    public void a(int i2, boolean z) {
        GroupInfo groupInfo = c().get(i2);
        if (groupInfo != null) {
            groupInfo.d = z;
        }
        a((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
            }

            public void onFailure(Error error) {
            }
        });
    }

    public void a(List<Device> list) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        Iterator<GroupInfo> it = this.k.iterator();
        while (it.hasNext()) {
            GroupInfo next = it.next();
            hashMap.put(next.f19240a, next);
        }
        for (Device next2 : list) {
            if (hashMap.containsKey(next2.did)) {
                arrayList.add(hashMap.remove(next2.did));
            }
        }
        if (!hashMap.isEmpty()) {
            arrayList.addAll(hashMap.values());
        }
    }

    public void a(String str, List<Device> list, final AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        for (Device device : list) {
            GroupInfo.DeviceControlInfo deviceControlInfo = new GroupInfo.DeviceControlInfo();
            deviceControlInfo.f19241a = device.did;
            arrayList.add(deviceControlInfo);
        }
        Iterator<GroupInfo> it = this.k.iterator();
        while (it.hasNext()) {
            GroupInfo next = it.next();
            if (next.f19240a.equals(str)) {
                next.e = arrayList;
            }
        }
        a((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                asyncCallback.onSuccess(voidR);
            }

            public void onFailure(Error error) {
                asyncCallback.onFailure(error);
            }
        });
    }

    public void b(List<Device> list) {
        final HashMap hashMap = new HashMap();
        hashMap.clear();
        for (int i2 = 0; i2 < list.size(); i2++) {
            hashMap.put(list.get(i2).did, Integer.valueOf(i2));
        }
        Collections.sort(this.k, new Comparator<GroupInfo>() {
            /* renamed from: a */
            public int compare(GroupInfo groupInfo, GroupInfo groupInfo2) {
                if (!hashMap.containsKey(groupInfo.f19240a) || !hashMap.containsKey(groupInfo2.f19240a)) {
                    return hashMap.containsKey(groupInfo.f19240a) ? 1 : -1;
                }
                return ((Integer) hashMap.get(groupInfo.f19240a)).intValue() - ((Integer) hashMap.get(groupInfo2.f19240a)).intValue();
            }
        });
    }

    public boolean c(List<Device> list) {
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < list.size(); i2++) {
            hashMap.put(list.get(i2).did, Integer.valueOf(i2));
        }
        int i3 = 0;
        boolean z = false;
        while (i3 < this.k.size()) {
            GroupInfo groupInfo = this.k.get(i3);
            if (groupInfo == null || !hashMap.containsKey(groupInfo.f19240a)) {
                this.m.add(this.k.remove(i3));
                i3--;
                z = true;
            } else {
                hashMap.remove(this.k.get(i3).f19240a);
            }
            i3++;
        }
        if (hashMap.size() != 0) {
            for (String str : hashMap.keySet()) {
                GroupInfo groupInfo2 = null;
                int i4 = 0;
                while (true) {
                    if (i4 >= this.m.size()) {
                        break;
                    } else if (this.m.get(i4).f19240a.equals(str)) {
                        groupInfo2 = this.m.remove(i4);
                        break;
                    } else {
                        i4++;
                    }
                }
                if (groupInfo2 == null) {
                    groupInfo2 = new GroupInfo();
                    groupInfo2.f19240a = str;
                }
                Device n2 = SmartHomeDeviceManager.a().n(groupInfo2.f19240a);
                if (n2 != null) {
                    if (CameraDeviceOpManager.a().f().containsKey(n2.model)) {
                        this.k.add(0, groupInfo2);
                    } else {
                        this.k.add(groupInfo2);
                    }
                }
            }
            z = true;
        }
        Log.e("AllCameraGroup", "" + this.l.size() + ", " + this.k.size());
        return z;
    }

    /* access modifiers changed from: package-private */
    public List<GroupInfo> b(String str) {
        GroupInfo a2;
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.optLong("timestamp");
            this.j = jSONObject.optInt("show_view_type");
            SharedPreferences.Editor edit = this.i.edit();
            edit.putInt(g, this.j);
            edit.commit();
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (!(optJSONObject == null || (a2 = GroupInfo.a(optJSONObject)) == null)) {
                    arrayList.add(a2);
                }
            }
            return arrayList;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void g() {
        String string = this.i.getString(i(), "");
        if (!TextUtils.isEmpty(string)) {
            this.n.sendMessage(this.n.obtainMessage(3, b(string)));
        }
    }

    public void c(String str) {
        SharedPreferences.Editor edit = this.i.edit();
        edit.putString(i(), str);
        edit.commit();
    }

    public void a(AsyncCallback<Void, Error> asyncCallback) {
        if (!CameraInfoRefreshManager.a().b() && this.k.size() != 0) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            Iterator<GroupInfo> it = this.k.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().a());
            }
            try {
                jSONObject.put("data", jSONArray);
                jSONObject.put("show_view_type", f());
                a(jSONObject.toString(), asyncCallback);
                MyLog.a(1, "save data " + jSONObject.toString());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void a(final String str, final AsyncCallback<Void, Error> asyncCallback) {
        UserConfig userConfig = new UserConfig();
        userConfig.B = 0;
        userConfig.C = UserConfig.g;
        userConfig.D = new ArrayList<>();
        userConfig.D.add(new UserConfig.UserConfigAttr(i(), str));
        UserConfigApi.a().a(SHApplication.getAppContext(), userConfig, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                CameraGroupManager.this.c(str);
                asyncCallback.onSuccess(null);
            }

            public void onFailure(Error error) {
                asyncCallback.onFailure(error);
            }
        });
    }

    public void b(final AsyncCallback<Void, Error> asyncCallback) {
        UserConfigApi.a().a(SHApplication.getAppContext(), 0, new String[]{UserConfig.g}, (AsyncCallback<ArrayList<UserConfig>, Error>) new AsyncCallback<ArrayList<UserConfig>, Error>() {
            /* renamed from: a */
            public void onSuccess(ArrayList<UserConfig> arrayList) {
                UserConfig userConfig;
                if (arrayList != null && arrayList.size() > 0 && (userConfig = arrayList.get(0)) != null && userConfig.D != null && userConfig.D.size() > 0 && TextUtils.equals(CameraGroupManager.this.i(), userConfig.D.get(0).f16437a)) {
                    String str = userConfig.D.get(0).b;
                    List<GroupInfo> b2 = CameraGroupManager.this.b(str);
                    CameraGroupManager.this.c(str);
                    CameraGroupManager.this.n.sendMessage(CameraGroupManager.this.n.obtainMessage(3, b2));
                }
                asyncCallback.onSuccess(null);
            }

            public void onFailure(Error error) {
                asyncCallback.onFailure(error);
            }
        });
    }

    public int h() {
        List<GroupInfo> c2 = a().c();
        if (c2 == null) {
            return 0;
        }
        int size = c2.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (d(c2.get(i2).f19240a)) {
                return i2;
            }
        }
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r1 = com.xiaomi.smarthome.frame.core.CoreApi.a().d(r0.model);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0016, code lost:
        r4 = com.xiaomi.smarthome.listcamera.CameraFrameManager.a().a(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean d(java.lang.String r4) {
        /*
            r3 = this;
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            com.xiaomi.smarthome.device.Device r0 = r0.b((java.lang.String) r4)
            if (r0 == 0) goto L_0x0034
            com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r2 = r0.model
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r1 = r1.d((java.lang.String) r2)
            if (r1 == 0) goto L_0x0034
            com.xiaomi.smarthome.listcamera.CameraFrameManager r2 = com.xiaomi.smarthome.listcamera.CameraFrameManager.a()
            com.xiaomi.smarthome.listcamera.CameraFrameManager$FrameContext r4 = r2.a((java.lang.String) r4)
            if (r4 == 0) goto L_0x0034
            boolean r2 = r0.isOnline
            if (r2 == 0) goto L_0x0034
            int r0 = r0.isSetPinCode
            if (r0 != 0) goto L_0x0034
            boolean r4 = r4.i
            if (r4 != 0) goto L_0x0034
            boolean r4 = r1.l()
            if (r4 == 0) goto L_0x0034
            r4 = 1
            goto L_0x0035
        L_0x0034:
            r4 = 0
        L_0x0035:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.listcamera.CameraGroupManager.d(java.lang.String):boolean");
    }
}
