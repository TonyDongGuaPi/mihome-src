package com.xiaomi.smarthome.voice.microaudio.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class MicroPushMsgInfo implements Parcelable {
    public static final Parcelable.Creator<MicroPushMsgInfo> CREATOR = new Parcelable.Creator<MicroPushMsgInfo>() {
        /* renamed from: a */
        public MicroPushMsgInfo createFromParcel(Parcel parcel) {
            return new MicroPushMsgInfo(parcel);
        }

        /* renamed from: a */
        public MicroPushMsgInfo[] newArray(int i) {
            return new MicroPushMsgInfo[i];
        }
    };
    public static final String n = "text";
    public static final String o = "multi-devices";
    public static final String p = "chat";
    public static final String q = "multicmd";
    public static final String r = "list";

    /* renamed from: a  reason: collision with root package name */
    public String f22808a;
    public String b;
    public String c;
    public String d;
    public long e;
    public int f;
    public String g;
    public int h;
    public String i;
    public List<String> j = new ArrayList();
    public int k = -1;
    public String l = "";
    public List<MultiCmdInfo> m = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public static class MultiCmdInfo {

        /* renamed from: a  reason: collision with root package name */
        public List<String> f22809a = new ArrayList();
        public String b;
        public int c;

        public static List<MultiCmdInfo> a(JSONArray jSONArray) {
            ArrayList arrayList = new ArrayList();
            if (jSONArray == null || jSONArray.length() == 0) {
                return arrayList;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                MultiCmdInfo multiCmdInfo = new MultiCmdInfo();
                JSONArray optJSONArray = optJSONObject.optJSONArray("devices");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        multiCmdInfo.f22809a.add(optJSONArray.opt(i2).toString());
                    }
                }
                multiCmdInfo.b = optJSONObject.optString("reply", "");
                multiCmdInfo.c = optJSONObject.optInt("result", -1);
                arrayList.add(multiCmdInfo);
            }
            return arrayList;
        }
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f22808a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeLong(this.e);
        parcel.writeInt(this.f);
        parcel.writeString(this.g);
        parcel.writeInt(this.h);
        parcel.writeString(this.i);
        parcel.writeStringList(this.j);
        parcel.writeInt(this.k);
        parcel.writeString(this.l);
        parcel.writeList(this.m);
    }

    public MicroPushMsgInfo() {
    }

    protected MicroPushMsgInfo(Parcel parcel) {
        this.f22808a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readLong();
        this.f = parcel.readInt();
        this.g = parcel.readString();
        this.h = parcel.readInt();
        this.i = parcel.readString();
        this.j = parcel.createStringArrayList();
        this.k = parcel.readInt();
        this.l = parcel.readString();
        this.m = new ArrayList();
        parcel.readList(this.m, MultiCmdInfo.class.getClassLoader());
    }
}
