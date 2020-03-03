package com.xiaomi.smarthome.newui.roomenv.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RoomEnvData implements Parcelable {
    public static final Parcelable.Creator<RoomEnvData> CREATOR = new Parcelable.Creator<RoomEnvData>() {
        /* renamed from: a */
        public RoomEnvData createFromParcel(Parcel parcel) {
            return new RoomEnvData(parcel);
        }

        /* renamed from: a */
        public RoomEnvData[] newArray(int i) {
            return new RoomEnvData[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private List<RoomEnvDataItem> f20706a;
    private String b;
    private String c;

    public int describeContents() {
        return 0;
    }

    public static RoomEnvData a(JSONObject jSONObject) {
        RoomEnvDataItem a2;
        if (jSONObject == null) {
            return null;
        }
        RoomEnvData roomEnvData = new RoomEnvData();
        String optString = jSONObject.optString("home_id");
        if (!TextUtils.isEmpty(optString)) {
            roomEnvData.b(optString);
        }
        roomEnvData.a(jSONObject.optString("room_id"));
        JSONArray optJSONArray = jSONObject.optJSONArray("desc_list");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (!(optJSONObject == null || (a2 = RoomEnvDataItem.a(optJSONObject)) == null)) {
                    arrayList.add(a2);
                }
            }
            roomEnvData.a((List<RoomEnvDataItem>) arrayList);
        }
        return roomEnvData;
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("home_id", d());
        jSONObject.put("room_id", b());
        List<RoomEnvDataItem> c2 = c();
        if (c2 != null && !c2.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < c2.size(); i++) {
                RoomEnvDataItem roomEnvDataItem = c2.get(i);
                if (roomEnvDataItem != null) {
                    jSONArray.put(roomEnvDataItem.a());
                }
            }
            jSONObject.put("desc_list", jSONArray);
        }
        return jSONObject;
    }

    public String b() {
        return this.b;
    }

    public List<RoomEnvDataItem> c() {
        return this.f20706a;
    }

    public void a(List<RoomEnvDataItem> list) {
        this.f20706a = list;
    }

    public void a(String str) {
        this.b = str;
    }

    public RoomEnvData() {
    }

    public String d() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f20706a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }

    protected RoomEnvData(Parcel parcel) {
        this.f20706a = parcel.createTypedArrayList(RoomEnvDataItem.CREATOR);
        this.b = parcel.readString();
        this.c = parcel.readString();
    }

    public String e() {
        List<RoomEnvDataItem> list = this.f20706a;
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            RoomEnvDataItem roomEnvDataItem = list.get(i);
            if (roomEnvDataItem != null) {
                if (TextUtils.equals(roomEnvDataItem.f(), RoomEnvDataItem.b) || TextUtils.equals(roomEnvDataItem.f(), RoomEnvDataItem.b)) {
                    RoomEnvDataItem roomEnvDataItem2 = (RoomEnvDataItem) linkedHashMap.get(roomEnvDataItem.f());
                    if (roomEnvDataItem2 == null) {
                        linkedHashMap.put(roomEnvDataItem.f(), roomEnvDataItem);
                    } else if (roomEnvDataItem2.c() < roomEnvDataItem.c()) {
                        if (Build.VERSION.SDK_INT >= 24) {
                            linkedHashMap.replace(roomEnvDataItem.f(), roomEnvDataItem2, roomEnvDataItem);
                        } else {
                            linkedHashMap.put(roomEnvDataItem.f(), roomEnvDataItem);
                        }
                    }
                } else {
                    linkedHashMap.put(roomEnvDataItem.f(), roomEnvDataItem);
                }
            }
        }
        for (Map.Entry value : linkedHashMap.entrySet()) {
            stringBuffer.append(((RoomEnvDataItem) value.getValue()).h() + " | ");
        }
        if (stringBuffer.length() < 2) {
            return "";
        }
        return stringBuffer.substring(0, stringBuffer.length() - 2);
    }
}
