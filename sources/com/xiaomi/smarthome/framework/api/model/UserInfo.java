package com.xiaomi.smarthome.framework.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.family.FamilyMemberData;
import org.json.JSONObject;

public class UserInfo implements Parcelable {
    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        /* renamed from: a */
        public UserInfo createFromParcel(Parcel parcel) {
            return new UserInfo(parcel);
        }

        /* renamed from: a */
        public UserInfo[] newArray(int i) {
            return new UserInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f16462a;
    public String b;
    public String c;
    public String d;
    public String e;
    public long f;
    public long g;

    public int describeContents() {
        return 0;
    }

    public UserInfo() {
    }

    public static UserInfo a(JSONObject jSONObject) {
        UserInfo userInfo = new UserInfo();
        if (!jSONObject.isNull("userId")) {
            userInfo.f16462a = jSONObject.optString("userId");
        } else if (!jSONObject.isNull("userid")) {
            userInfo.f16462a = jSONObject.optString("userid");
        }
        userInfo.e = jSONObject.optString(FamilyMemberData.d);
        userInfo.b = jSONObject.optString("userName");
        userInfo.c = jSONObject.optString("icon");
        return userInfo;
    }

    public UserInfo(Parcel parcel) {
        a(parcel);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, Parcel parcel) {
        if (str == null) {
            parcel.writeString("");
        } else {
            parcel.writeString(str);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Parcel parcel) {
        this.f16462a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readLong();
        this.g = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        a(this.f16462a, parcel);
        a(this.b, parcel);
        a(this.c, parcel);
        a(this.d, parcel);
        a(this.e, parcel);
        parcel.writeLong(this.f);
        parcel.writeLong(this.g);
    }
}
