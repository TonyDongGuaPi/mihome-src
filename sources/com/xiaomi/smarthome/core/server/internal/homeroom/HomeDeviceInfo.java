package com.xiaomi.smarthome.core.server.internal.homeroom;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.server.internal.device.DeviceFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeDeviceInfo implements Parcelable {
    public static final Parcelable.Creator<HomeDeviceInfo> CREATOR = new Parcelable.Creator<HomeDeviceInfo>() {
        /* renamed from: a */
        public HomeDeviceInfo createFromParcel(Parcel parcel) {
            return new HomeDeviceInfo(parcel);
        }

        /* renamed from: a */
        public HomeDeviceInfo[] newArray(int i) {
            return new HomeDeviceInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    protected String f14582a;
    protected Home b;
    protected boolean c;
    protected String d;
    protected Map<String, Device> e = new ConcurrentHashMap();
    private List<Device> f;
    private List<String> g;

    public int describeContents() {
        return 0;
    }

    public void a(List<String> list) {
        this.g = list;
    }

    public static HomeDeviceInfo a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HomeDeviceInfo homeDeviceInfo = new HomeDeviceInfo();
        if (!jSONObject.isNull("home_info")) {
            homeDeviceInfo.b = Home.a(jSONObject.optJSONObject("home_info"));
        }
        if (!jSONObject.isNull(DeviceRequestsHelper.DEVICE_INFO_PARAM)) {
            homeDeviceInfo.b(a(jSONObject.optJSONArray(DeviceRequestsHelper.DEVICE_INFO_PARAM)));
        }
        if (!jSONObject.isNull("dids")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("dids");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String optString = optJSONArray.optString(i);
                    if (!TextUtils.isEmpty(optString)) {
                        arrayList.add(optString);
                    }
                }
                homeDeviceInfo.a((List<String>) arrayList);
            }
        } else if (homeDeviceInfo.d() != null && !homeDeviceInfo.d().isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            for (Device next : homeDeviceInfo.d()) {
                if (next != null && !TextUtils.isEmpty(next.k())) {
                    arrayList2.add(next.k());
                }
            }
            homeDeviceInfo.a((List<String>) arrayList2);
        }
        homeDeviceInfo.a(jSONObject.optBoolean("has_more", false));
        homeDeviceInfo.b(jSONObject.optString("max_did"));
        return homeDeviceInfo;
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.b != null) {
            jSONObject.put("home_info", this.b.p());
        }
        jSONObject.put("has_more", this.c);
        jSONObject.put("max_did", this.d);
        List<String> list = this.g;
        if (list != null && list.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                if (!TextUtils.isEmpty(str)) {
                    jSONArray.put(str);
                }
            }
            jSONObject.put("dids", jSONArray);
        }
        return jSONObject;
    }

    public static List<Device> a(JSONArray jSONArray) {
        Device a2;
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (!(optJSONObject == null || (a2 = DeviceFactory.a(optJSONObject)) == null)) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    public String b() {
        return this.f14582a;
    }

    public void a(String str) {
        this.f14582a = str;
    }

    public Home c() {
        return this.b;
    }

    public void a(Home home) {
        this.b = home;
    }

    public List<Device> d() {
        return this.f == null ? new ArrayList() : this.f;
    }

    public void b(List<Device> list) {
        this.f = list;
        this.e = new ConcurrentHashMap();
        d(list);
        i();
    }

    public Map<String, Device> e() {
        return this.e;
    }

    private void d(List<Device> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Device device = list.get(i);
                if (device != null) {
                    this.e.put(device.k(), device);
                }
            }
        }
    }

    public void c(List<Device> list) {
        if (list != null) {
            d(list);
            if (this.f == null) {
                this.f = new ArrayList(list);
                return;
            }
            this.f.addAll(list);
            i();
        }
    }

    public List<String> f() {
        return this.g == null ? new ArrayList() : this.g;
    }

    private void i() {
        List<Device> list = this.f;
        ArrayList arrayList = new ArrayList();
        if (list == null || list.isEmpty()) {
            this.g = arrayList;
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Device device = list.get(i);
            if (device != null) {
                arrayList.add(device.k());
            }
        }
        this.g = arrayList;
    }

    public boolean g() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public String h() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f14582a);
        parcel.writeParcelable(this.b, i);
        parcel.writeByte(this.c ? (byte) 1 : 0);
        parcel.writeString(this.d);
        parcel.writeStringList(this.g);
    }

    public HomeDeviceInfo() {
    }

    protected HomeDeviceInfo(Parcel parcel) {
        this.f14582a = parcel.readString();
        this.b = (Home) parcel.readParcelable(Home.class.getClassLoader());
        this.c = parcel.readByte() != 0;
        this.d = parcel.readString();
        this.g = parcel.createStringArrayList();
    }

    public void a(HomeDeviceInfo homeDeviceInfo) {
        if (homeDeviceInfo != null) {
            b(homeDeviceInfo);
            c(homeDeviceInfo);
        }
    }

    private void b(HomeDeviceInfo homeDeviceInfo) {
        if (homeDeviceInfo != null && homeDeviceInfo.f != null && !homeDeviceInfo.f.isEmpty()) {
            if (this.f == null) {
                b(homeDeviceInfo.f);
                return;
            }
            this.f.addAll(homeDeviceInfo.f);
            b(this.f);
        }
    }

    private void c(HomeDeviceInfo homeDeviceInfo) {
        if (homeDeviceInfo != null && homeDeviceInfo.b != null) {
            if (this.b == null) {
                a(homeDeviceInfo.b);
            } else {
                this.b.a(homeDeviceInfo.b);
            }
        }
    }
}
