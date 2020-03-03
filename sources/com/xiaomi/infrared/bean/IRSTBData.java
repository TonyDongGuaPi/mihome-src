package com.xiaomi.infrared.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.infrared.utils.CommUtil;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class IRSTBData implements Parcelable {
    public static final Parcelable.Creator<IRSTBData> CREATOR = new Parcelable.Creator<IRSTBData>() {
        /* renamed from: a */
        public IRSTBData createFromParcel(Parcel parcel) {
            return new IRSTBData(parcel);
        }

        /* renamed from: a */
        public IRSTBData[] newArray(int i) {
            return new IRSTBData[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f10226a = 1;
    public static final int b = 0;
    private int c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.f;
    }

    public void a(String str) {
        this.f = str;
    }

    public String b() {
        return this.g;
    }

    public void b(String str) {
        this.g = str;
    }

    public String c() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public int e() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public String f() {
        return this.h;
    }

    public void e(String str) {
        this.h = str;
    }

    public IRSTBData() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
    }

    protected IRSTBData(Parcel parcel) {
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
    }

    public static ArrayList<IRSTBData> a(JSONArray jSONArray) {
        ArrayList<IRSTBData> arrayList = new ArrayList<>();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                IRSTBData iRSTBData = new IRSTBData();
                arrayList.add(iRSTBData);
                iRSTBData.c = optJSONObject.optInt("SpType");
                iRSTBData.d = CommUtil.a(optJSONObject, "Id");
                iRSTBData.e = CommUtil.a(optJSONObject, "SpId");
                iRSTBData.f = CommUtil.a(optJSONObject, "AreaId");
                iRSTBData.g = CommUtil.a(optJSONObject, "CityId");
                iRSTBData.h = CommUtil.a(optJSONObject, "Name");
                i++;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return arrayList;
    }
}
