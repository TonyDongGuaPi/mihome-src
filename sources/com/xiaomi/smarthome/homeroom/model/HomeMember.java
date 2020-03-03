package com.xiaomi.smarthome.homeroom.model;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import org.json.JSONObject;

public class HomeMember implements Parcelable {
    public static final Parcelable.Creator<HomeMember> CREATOR = new Parcelable.Creator<HomeMember>() {
        /* renamed from: a */
        public HomeMember createFromParcel(Parcel parcel) {
            return new HomeMember(parcel);
        }

        /* renamed from: a */
        public HomeMember[] newArray(int i) {
            return new HomeMember[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f18315a = -1;
    public static final int b = 0;
    public static final int c = 2;
    public static final int d = 10;
    private int e;
    private long f;
    private UserInfo g;

    public int describeContents() {
        return 0;
    }

    public HomeMember() {
    }

    protected HomeMember(Parcel parcel) {
        this.e = parcel.readInt();
        this.f = parcel.readLong();
        this.g = (UserInfo) parcel.readParcelable(UserInfo.class.getClassLoader());
    }

    public long a() {
        return this.f;
    }

    public void a(long j) {
        this.f = j;
    }

    public int b() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public UserInfo c() {
        return this.g;
    }

    public void a(UserInfo userInfo) {
        this.g = userInfo;
    }

    public String a(Resources resources) {
        if (this.e == -1) {
            return resources.getString(R.string.smarthome_share_expired);
        }
        if (this.e == 0) {
            return resources.getString(R.string.smarthome_to_user_status_waiting);
        }
        if (this.e == 2) {
            return resources.getString(R.string.family_member);
        }
        return this.e == 10 ? resources.getString(R.string.home_administrator) : "";
    }

    public int b(Resources resources) {
        if (this.e == 0) {
            return resources.getColor(R.color.class_text_36);
        }
        return resources.getColor(R.color.class_text_35);
    }

    public String c(Resources resources) {
        if (this.e == -1) {
            return resources.getString(R.string.family_member);
        }
        if (this.e == 0) {
            return resources.getString(R.string.family_member);
        }
        if (this.e == 2) {
            return resources.getString(R.string.family_member);
        }
        return this.e == 10 ? resources.getString(R.string.home_administrator) : "";
    }

    public static HomeMember a(JSONObject jSONObject) {
        HomeMember homeMember = new HomeMember();
        try {
            UserInfo userInfo = new UserInfo();
            if (!jSONObject.isNull("uid")) {
                homeMember.f = jSONObject.optLong("uid");
                userInfo.f16462a = jSONObject.optString("uid");
                userInfo.e = jSONObject.optString("uid");
                userInfo.b = jSONObject.optString("uid");
                homeMember.a(userInfo);
            }
            if (!jSONObject.isNull("permit_level")) {
                homeMember.e = jSONObject.optInt("permit_level");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return homeMember;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.e);
        parcel.writeLong(this.f);
        parcel.writeParcelable(this.g, i);
    }
}
