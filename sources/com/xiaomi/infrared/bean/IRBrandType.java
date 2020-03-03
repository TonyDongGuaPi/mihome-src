package com.xiaomi.infrared.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.xiaomi.infrared.utils.CharacterParser;
import com.xiaomi.infrared.utils.CommUtil;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class IRBrandType implements Parcelable, Comparable<IRBrandType> {
    public static final Parcelable.Creator<IRBrandType> CREATOR = new Parcelable.Creator<IRBrandType>() {
        /* renamed from: a */
        public IRBrandType createFromParcel(Parcel parcel) {
            return new IRBrandType(parcel);
        }

        /* renamed from: a */
        public IRBrandType[] newArray(int i) {
            return new IRBrandType[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f10223a;
    private String b;
    private String c;
    private String d;
    private String e;
    private boolean f;
    private CharacterParser g;

    public int describeContents() {
        return 0;
    }

    public IRBrandType() {
    }

    public boolean a() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public String b() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public String c() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String d() {
        return this.f10223a;
    }

    public void c(String str) {
        this.f10223a = str;
    }

    public String e() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public void e(String str) {
        this.e = str;
    }

    public String f() {
        return this.e;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f10223a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeByte(this.f ? (byte) 1 : 0);
    }

    protected IRBrandType(Parcel parcel) {
        this.f10223a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readByte() != 0;
    }

    /* renamed from: a */
    public int compareTo(@NonNull IRBrandType iRBrandType) {
        char charAt = f().toUpperCase().charAt(0);
        char charAt2 = iRBrandType.f().toUpperCase().charAt(0);
        if (charAt < 'A' || charAt > 'Z') {
            return 1;
        }
        if (charAt2 < 'A' || charAt2 > 'Z') {
            return -1;
        }
        return f().compareTo(iRBrandType.f());
    }

    public static ArrayList<IRBrandType> a(JSONArray jSONArray) {
        ArrayList<IRBrandType> arrayList = new ArrayList<>();
        try {
            boolean a2 = CommUtil.a();
            for (int i = 0; i < jSONArray.length(); i++) {
                IRBrandType iRBrandType = new IRBrandType();
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                iRBrandType.b = CommUtil.a(optJSONObject, "id");
                iRBrandType.f10223a = CommUtil.a(optJSONObject, "name");
                iRBrandType.d = CommUtil.a(optJSONObject, "en_name");
                iRBrandType.c = CommUtil.a(optJSONObject, "pinyin");
                String str = a2 ? iRBrandType.c : iRBrandType.d;
                String upperCase = (str == null || str.length() < 1) ? "#" : str.substring(0, 1).toUpperCase();
                if (upperCase.matches("[A-Z]")) {
                    iRBrandType.e(upperCase);
                } else {
                    iRBrandType.e("#");
                }
                arrayList.add(iRBrandType);
            }
        } catch (Throwable unused) {
        }
        return arrayList;
    }
}
