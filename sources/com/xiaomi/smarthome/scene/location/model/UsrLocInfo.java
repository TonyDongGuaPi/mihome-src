package com.xiaomi.smarthome.scene.location.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UsrLocInfo implements Parcelable, Comparable<UsrLocInfo> {
    public static final Parcelable.Creator<UsrLocInfo> CREATOR = new Parcelable.Creator<UsrLocInfo>() {
        /* renamed from: a */
        public UsrLocInfo createFromParcel(Parcel parcel) {
            return new UsrLocInfo(parcel);
        }

        /* renamed from: a */
        public UsrLocInfo[] newArray(int i) {
            return new UsrLocInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final String f21611a = "template";
    public static final String b = "home";
    public static final String c = "office";
    private int d;
    private String e;
    private List<String> f;
    private Map<String, Object> g;

    public int describeContents() {
        return 0;
    }

    public static UsrLocInfo a(JSONObject jSONObject) {
        JSONArray optJSONArray;
        if (jSONObject == null) {
            return null;
        }
        Iterator<String> keys = jSONObject.keys();
        HashMap hashMap = new HashMap();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                hashMap.put(next, jSONObject.get(next));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        UsrLocInfo usrLocInfo = new UsrLocInfo();
        usrLocInfo.a(jSONObject.optInt("id"));
        hashMap.remove("id");
        usrLocInfo.a(jSONObject.optString("intro"));
        hashMap.remove("intro");
        ArrayList arrayList = new ArrayList();
        if (!jSONObject.isNull("wifi") && (optJSONArray = jSONObject.optJSONArray("wifi")) != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null && !optJSONObject.isNull(DeviceTagInterface.e)) {
                    arrayList.add(optJSONObject.optString(DeviceTagInterface.e));
                }
            }
        }
        hashMap.remove("wifi");
        usrLocInfo.a((List<String>) arrayList);
        usrLocInfo.a((Map<String, Object>) hashMap);
        return usrLocInfo;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0059 A[Catch:{ JSONException -> 0x007d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject a() {
        /*
            r6 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "id"
            int r2 = r6.d     // Catch:{ JSONException -> 0x007d }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x007d }
            java.lang.String r1 = "intro"
            java.lang.String r2 = r6.e     // Catch:{ JSONException -> 0x007d }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x007d }
            java.util.List<java.lang.String> r1 = r6.f     // Catch:{ JSONException -> 0x007d }
            if (r1 == 0) goto L_0x004b
            java.util.List<java.lang.String> r1 = r6.f     // Catch:{ JSONException -> 0x007d }
            int r1 = r1.size()     // Catch:{ JSONException -> 0x007d }
            if (r1 != 0) goto L_0x0020
            goto L_0x004b
        L_0x0020:
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ JSONException -> 0x007d }
            r1.<init>()     // Catch:{ JSONException -> 0x007d }
            java.util.List<java.lang.String> r2 = r6.f     // Catch:{ JSONException -> 0x007d }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ JSONException -> 0x007d }
        L_0x002b:
            boolean r3 = r2.hasNext()     // Catch:{ JSONException -> 0x007d }
            if (r3 == 0) goto L_0x0045
            java.lang.Object r3 = r2.next()     // Catch:{ JSONException -> 0x007d }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ JSONException -> 0x007d }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x007d }
            r4.<init>()     // Catch:{ JSONException -> 0x007d }
            java.lang.String r5 = "ssid"
            r4.put(r5, r3)     // Catch:{ JSONException -> 0x007d }
            r1.put(r4)     // Catch:{ JSONException -> 0x007d }
            goto L_0x002b
        L_0x0045:
            java.lang.String r2 = "wifi"
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x007d }
            goto L_0x0055
        L_0x004b:
            java.lang.String r1 = "wifi"
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ JSONException -> 0x007d }
            r2.<init>()     // Catch:{ JSONException -> 0x007d }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x007d }
        L_0x0055:
            java.util.Map<java.lang.String, java.lang.Object> r1 = r6.g     // Catch:{ JSONException -> 0x007d }
            if (r1 == 0) goto L_0x0081
            java.util.Map<java.lang.String, java.lang.Object> r1 = r6.g     // Catch:{ JSONException -> 0x007d }
            java.util.Set r1 = r1.entrySet()     // Catch:{ JSONException -> 0x007d }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ JSONException -> 0x007d }
        L_0x0063:
            boolean r2 = r1.hasNext()     // Catch:{ JSONException -> 0x007d }
            if (r2 == 0) goto L_0x0081
            java.lang.Object r2 = r1.next()     // Catch:{ JSONException -> 0x007d }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ JSONException -> 0x007d }
            java.lang.Object r3 = r2.getKey()     // Catch:{ JSONException -> 0x007d }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ JSONException -> 0x007d }
            java.lang.Object r2 = r2.getValue()     // Catch:{ JSONException -> 0x007d }
            r0.put(r3, r2)     // Catch:{ JSONException -> 0x007d }
            goto L_0x0063
        L_0x007d:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0081:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.location.model.UsrLocInfo.a():org.json.JSONObject");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.d);
        parcel.writeString(this.e);
        parcel.writeStringList(this.f);
        parcel.writeInt(this.g.size());
        for (Map.Entry next : this.g.entrySet()) {
            parcel.writeString((String) next.getKey());
            parcel.writeValue(next.getValue());
        }
    }

    public UsrLocInfo() {
    }

    protected UsrLocInfo(Parcel parcel) {
        this.d = parcel.readInt();
        this.e = parcel.readString();
        this.f = parcel.createStringArrayList();
        int readInt = parcel.readInt();
        this.g = new HashMap(readInt);
        for (int i = 0; i < readInt; i++) {
            this.g.put(parcel.readString(), parcel.readValue(Object.class.getClassLoader()));
        }
    }

    public int b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public List<String> d() {
        return this.f;
    }

    public Map<String, Object> e() {
        return this.g;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(List<String> list) {
        this.f = list;
    }

    public void a(Map<String, Object> map) {
        this.g = map;
    }

    public static UsrLocInfo f() {
        UsrLocInfo usrLocInfo = new UsrLocInfo();
        usrLocInfo.a(SHApplication.getAppContext().getString(R.string.home));
        HashMap hashMap = new HashMap();
        hashMap.put("template", "home");
        usrLocInfo.a((Map<String, Object>) hashMap);
        return usrLocInfo;
    }

    public static UsrLocInfo g() {
        UsrLocInfo usrLocInfo = new UsrLocInfo();
        usrLocInfo.a(SHApplication.getAppContext().getString(R.string.home));
        HashMap hashMap = new HashMap();
        hashMap.put("template", "office");
        usrLocInfo.a((Map<String, Object>) hashMap);
        return usrLocInfo;
    }

    public boolean h() {
        if (this.g == null || this.g.isEmpty() || !this.g.containsKey("template")) {
            return false;
        }
        return "home".equals(this.g.get("template"));
    }

    public boolean i() {
        if (this.g == null || this.g.isEmpty() || !this.g.containsKey("template")) {
            return false;
        }
        return "office".equals(this.g.get("template"));
    }

    /* renamed from: a */
    public int compareTo(UsrLocInfo usrLocInfo) {
        if (h()) {
            return 1;
        }
        if (usrLocInfo.h()) {
            return -1;
        }
        if (i()) {
            return 1;
        }
        if (usrLocInfo.i()) {
            return -1;
        }
        if (b() > usrLocInfo.d) {
            return 1;
        }
        if (b() < usrLocInfo.b()) {
            return -1;
        }
        return 0;
    }
}
