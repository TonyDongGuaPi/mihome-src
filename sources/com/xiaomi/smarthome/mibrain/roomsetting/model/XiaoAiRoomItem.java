package com.xiaomi.smarthome.mibrain.roomsetting.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XiaoAiRoomItem implements Parcelable {
    public static final Parcelable.Creator<XiaoAiRoomItem> CREATOR = new Parcelable.Creator<XiaoAiRoomItem>() {
        /* renamed from: a */
        public XiaoAiRoomItem createFromParcel(Parcel parcel) {
            return new XiaoAiRoomItem(parcel);
        }

        /* renamed from: a */
        public XiaoAiRoomItem[] newArray(int i) {
            return new XiaoAiRoomItem[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f10702a;
    private String b;
    private boolean c;
    private List<ControllableDevice> d;

    public int describeContents() {
        return 0;
    }

    public XiaoAiRoomItem() {
    }

    public String a() {
        return this.f10702a;
    }

    public String b() {
        return this.b;
    }

    public boolean a(XiaoAiVoiceCategory xiaoAiVoiceCategory) {
        if (xiaoAiVoiceCategory == null || this.d == null || this.d.isEmpty()) {
            return true;
        }
        return !CommonUtils.a((List<? extends Object>) xiaoAiVoiceCategory.e(), (List<? extends Object>) c());
    }

    public List<ControllableDevice> c() {
        return this.d;
    }

    public void a(String str) {
        this.f10702a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void a(List<ControllableDevice> list) {
        this.d = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f10702a);
        parcel.writeString(this.b);
        parcel.writeInt(this.c ? 1 : 0);
        parcel.writeTypedList(this.d);
    }

    protected XiaoAiRoomItem(Parcel parcel) {
        this.f10702a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readInt() != 1 ? false : true;
        this.d = parcel.createTypedArrayList(ControllableDevice.CREATOR);
    }

    public JSONObject d() throws JSONException {
        JSONObject c2;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("room_id", this.f10702a);
        jSONObject.put("intent", this.b);
        jSONObject.put("use_default", this.c);
        if (this.d != null) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.d.size(); i++) {
                ControllableDevice controllableDevice = this.d.get(i);
                if (!(controllableDevice == null || (c2 = controllableDevice.c()) == null)) {
                    jSONArray.put(c2);
                }
            }
            jSONObject.put("device_list", jSONArray);
        }
        return jSONObject;
    }

    public static XiaoAiRoomItem a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        XiaoAiRoomItem xiaoAiRoomItem = new XiaoAiRoomItem();
        xiaoAiRoomItem.a(jSONObject.optString("room_id"));
        xiaoAiRoomItem.b(jSONObject.optString("intent"));
        xiaoAiRoomItem.a(jSONObject.optBoolean("use_default"));
        JSONArray optJSONArray = jSONObject.optJSONArray("device_list");
        if (optJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                ControllableDevice a2 = ControllableDevice.a(optJSONArray.optJSONObject(i));
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
            xiaoAiRoomItem.a((List<ControllableDevice>) arrayList);
        }
        return xiaoAiRoomItem;
    }
}
