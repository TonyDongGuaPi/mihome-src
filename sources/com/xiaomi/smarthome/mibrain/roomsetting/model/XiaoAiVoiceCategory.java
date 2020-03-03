package com.xiaomi.smarthome.mibrain.roomsetting.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XiaoAiVoiceCategory implements Parcelable {
    public static final Parcelable.Creator<XiaoAiVoiceCategory> CREATOR = new Parcelable.Creator<XiaoAiVoiceCategory>() {
        /* renamed from: a */
        public XiaoAiVoiceCategory createFromParcel(Parcel parcel) {
            return new XiaoAiVoiceCategory(parcel);
        }

        /* renamed from: a */
        public XiaoAiVoiceCategory[] newArray(int i) {
            return new XiaoAiVoiceCategory[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f10703a = 1;
    public static final int b = 2;
    private String c;
    private int d;
    private List<String> e;
    private List<ControllableDevice> f;

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public List<String> c() {
        return this.e;
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(List<String> list) {
        this.e = list;
    }

    public XiaoAiVoiceCategory() {
    }

    public JSONObject d() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(HomeManager.v, this.d);
        jSONObject.put("intent", this.c);
        if (this.e != null) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.e.size(); i++) {
                String str = this.e.get(i);
                if (!TextUtils.isEmpty(str)) {
                    jSONArray.put(str);
                }
            }
            jSONObject.put("desc", jSONArray);
        }
        return jSONObject;
    }

    public static XiaoAiVoiceCategory a(JSONObject jSONObject) {
        ControllableDevice a2;
        if (jSONObject == null) {
            return null;
        }
        XiaoAiVoiceCategory xiaoAiVoiceCategory = new XiaoAiVoiceCategory();
        xiaoAiVoiceCategory.a(jSONObject.optInt("option"));
        xiaoAiVoiceCategory.a(jSONObject.optString("intent"));
        JSONArray optJSONArray = jSONObject.optJSONArray("desc");
        if (optJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                String optString = optJSONArray.optString(i);
                if (!TextUtils.isEmpty(optString)) {
                    arrayList.add(optString);
                }
            }
            xiaoAiVoiceCategory.a((List<String>) arrayList);
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("devices");
        if (optJSONArray2 != null) {
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                JSONObject optJSONObject = optJSONArray2.optJSONObject(i2);
                if (!(optJSONObject == null || (a2 = ControllableDevice.a(optJSONObject)) == null || SmartHomeDeviceManager.a().b(a2.a()) == null)) {
                    arrayList2.add(a2);
                }
            }
            xiaoAiVoiceCategory.b(arrayList2);
        }
        return xiaoAiVoiceCategory;
    }

    public List<ControllableDevice> e() {
        return this.f;
    }

    public void b(List<ControllableDevice> list) {
        this.f = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeStringList(this.e);
        parcel.writeTypedList(this.f);
    }

    protected XiaoAiVoiceCategory(Parcel parcel) {
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = parcel.createStringArrayList();
        this.f = parcel.createTypedArrayList(ControllableDevice.CREATOR);
    }
}
