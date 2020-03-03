package com.mijia.camera.nas;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class NASMakeDirInfo implements Parcelable {
    public static final Parcelable.Creator<NASMakeDirInfo> CREATOR = new Parcelable.Creator<NASMakeDirInfo>() {
        /* renamed from: a */
        public NASMakeDirInfo createFromParcel(Parcel parcel) {
            return new NASMakeDirInfo(parcel);
        }

        /* renamed from: a */
        public NASMakeDirInfo[] newArray(int i) {
            return new NASMakeDirInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public int f7932a;
    public String b;
    public long c;
    public String d;
    public String e = "";
    public String f;
    public String g;

    public int describeContents() {
        return 0;
    }

    public NASMakeDirInfo() {
    }

    protected NASMakeDirInfo(Parcel parcel) {
        this.f7932a = parcel.readInt();
        this.b = parcel.readString();
        this.c = parcel.readLong();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
    }

    public static NASMakeDirInfo a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        NASMakeDirInfo nASMakeDirInfo = new NASMakeDirInfo();
        nASMakeDirInfo.f7932a = jSONObject.optInt("type");
        nASMakeDirInfo.b = jSONObject.optString("group");
        nASMakeDirInfo.c = jSONObject.optLong("addr");
        nASMakeDirInfo.d = jSONObject.optString("name");
        nASMakeDirInfo.e = jSONObject.optString(SharePatchInfo.g);
        nASMakeDirInfo.f = jSONObject.optString("user");
        nASMakeDirInfo.g = jSONObject.optString("pass");
        return nASMakeDirInfo;
    }

    public JSONObject a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", this.f7932a);
            jSONObject.put("group", this.b);
            jSONObject.put("addr", this.c);
            jSONObject.put("name", this.d);
            jSONObject.put(SharePatchInfo.g, this.e);
            jSONObject.put("user", this.f);
            jSONObject.put("pass", this.g);
            return jSONObject;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f7932a);
        parcel.writeString(this.b);
        parcel.writeLong(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
    }
}
