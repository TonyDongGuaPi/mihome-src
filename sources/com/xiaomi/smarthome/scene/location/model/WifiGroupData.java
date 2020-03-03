package com.xiaomi.smarthome.scene.location.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WifiGroupData implements Parcelable {
    public static final Parcelable.Creator<WifiGroupData> CREATOR = new Parcelable.Creator<WifiGroupData>() {
        /* renamed from: a */
        public WifiGroupData createFromParcel(Parcel parcel) {
            return new WifiGroupData(parcel);
        }

        /* renamed from: a */
        public WifiGroupData[] newArray(int i) {
            return new WifiGroupData[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f21618a;
    private List<String> b;

    public int describeContents() {
        return 0;
    }

    public static WifiGroupData a(JSONObject jSONObject) {
        JSONArray optJSONArray;
        WifiGroupData wifiGroupData = new WifiGroupData();
        if (!jSONObject.isNull("name")) {
            wifiGroupData.a(jSONObject.optString("name"));
        }
        ArrayList arrayList = new ArrayList();
        if (!jSONObject.isNull("list") && (optJSONArray = jSONObject.optJSONArray("list")) != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(optJSONArray.optJSONObject(i).optString(DeviceTagInterface.e));
            }
        }
        wifiGroupData.a((List<String>) arrayList);
        return wifiGroupData;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.f21618a);
            JSONArray jSONArray = new JSONArray();
            if (this.b != null) {
                for (int i = 0; i < this.b.size(); i++) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(DeviceTagInterface.e, this.b.get(i));
                    jSONArray.put(jSONObject2);
                }
            }
            jSONObject.put("list", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String b() {
        return this.f21618a;
    }

    public List<String> c() {
        return this.b;
    }

    public void a(String str) {
        this.f21618a = str;
    }

    public void a(List<String> list) {
        this.b = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f21618a);
        parcel.writeStringList(this.b);
    }

    public WifiGroupData() {
    }

    protected WifiGroupData(Parcel parcel) {
        this.f21618a = parcel.readString();
        this.b = parcel.createStringArrayList();
    }
}
