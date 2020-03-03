package com.xiaomi.smarthome.ad.api;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdPosition implements Parcelable {
    public static final Parcelable.Creator<AdPosition> CREATOR = new Parcelable.Creator<AdPosition>() {
        /* renamed from: a */
        public AdPosition createFromParcel(Parcel parcel) {
            return new AdPosition(parcel);
        }

        /* renamed from: a */
        public AdPosition[] newArray(int i) {
            return new AdPosition[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f13636a;
    private List<Advertisement> b = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.f13636a;
    }

    public List<Advertisement> b() {
        return this.b;
    }

    public AdPosition() {
    }

    protected AdPosition(Parcel parcel) {
        this.f13636a = parcel.readString();
        parcel.readList(this.b, getClass().getClassLoader());
    }

    public static AdPosition a(JSONObject jSONObject) {
        AdPosition adPosition = new AdPosition();
        adPosition.f13636a = jSONObject.optString("oper_key");
        JSONArray optJSONArray = jSONObject.optJSONArray("ads");
        for (int i = 0; i < optJSONArray.length(); i++) {
            Advertisement a2 = Advertisement.a(optJSONArray.optJSONObject(i));
            if (a2 != null) {
                adPosition.b.add(a2);
            }
        }
        return adPosition;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13636a);
        parcel.writeList(this.b);
    }
}
