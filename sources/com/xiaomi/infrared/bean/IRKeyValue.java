package com.xiaomi.infrared.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.infrared.utils.CommUtil;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class IRKeyValue implements Parcelable {
    public static final Parcelable.Creator<IRKeyValue> CREATOR = new Parcelable.Creator<IRKeyValue>() {
        /* renamed from: a */
        public IRKeyValue createFromParcel(Parcel parcel) {
            return new IRKeyValue(parcel);
        }

        /* renamed from: a */
        public IRKeyValue[] newArray(int i) {
            return new IRKeyValue[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f10224a;
    private String b;
    private IRType c;
    private String d;
    private String e;
    private String f;
    private int g;
    private String h;

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public IRKeyValue() {
    }

    public IRType b() {
        return this.c;
    }

    public void a(IRType iRType) {
        this.c = iRType;
    }

    public String c() {
        return this.f10224a;
    }

    public void b(String str) {
        this.f10224a = str;
    }

    public String d() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    public String e() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public String f() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public String g() {
        return this.f;
    }

    public void f(String str) {
        this.f = str;
    }

    public int h() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public int i() {
        if (this.f == null || this.f.length() <= 800) {
            return 1;
        }
        int length = this.f.length() / 800;
        return this.f.length() % 800 > 0 ? length + 1 : length;
    }

    public String toString() {
        return "IRKeyValue{mKey='" + this.d + Operators.SINGLE_QUOTE + ", mHead='" + this.e + Operators.SINGLE_QUOTE + ", mValue='" + this.f + Operators.SINGLE_QUOTE + ", mFreq=" + this.g + ", displayName='" + this.h + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f10224a);
        parcel.writeString(this.b);
        parcel.writeInt(this.c == null ? -1 : this.c.ordinal());
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeInt(this.g);
        parcel.writeString(this.h);
    }

    protected IRKeyValue(Parcel parcel) {
        IRType iRType;
        this.f10224a = parcel.readString();
        this.b = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt == -1) {
            iRType = null;
        } else {
            iRType = IRType.values()[readInt];
        }
        this.c = iRType;
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readInt();
        this.h = parcel.readString();
    }

    public static ArrayList<IRKeyValue> a(JSONArray jSONArray) {
        ArrayList<IRKeyValue> arrayList = new ArrayList<>();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                IRKeyValue iRKeyValue = new IRKeyValue();
                iRKeyValue.h = CommUtil.a(optJSONObject, "display_name");
                iRKeyValue.d = CommUtil.a(optJSONObject, "name");
                iRKeyValue.b = CommUtil.a(optJSONObject, "id");
                arrayList.add(iRKeyValue);
                i++;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }
}
