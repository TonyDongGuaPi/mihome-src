package com.xiaomi.smarthome.miio.message.p0.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class P0MessageList implements Parcelable {
    public static final Parcelable.Creator<P0MessageList> CREATOR = new Parcelable.Creator<P0MessageList>() {
        /* renamed from: a */
        public P0MessageList createFromParcel(Parcel parcel) {
            return new P0MessageList(parcel);
        }

        /* renamed from: a */
        public P0MessageList[] newArray(int i) {
            return new P0MessageList[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private List<P0Message> f13622a;
    private int b = 0;

    public int describeContents() {
        return 0;
    }

    public static P0MessageList a(JSONObject jSONObject) {
        JSONArray optJSONArray;
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("alertList")) == null) {
            return null;
        }
        P0MessageList p0MessageList = new P0MessageList();
        p0MessageList.b = jSONObject.optInt("count");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            P0Message a2 = P0Message.a(optJSONArray.optJSONObject(i));
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        p0MessageList.a((List<P0Message>) arrayList);
        return p0MessageList;
    }

    public List<P0Message> a() {
        return this.f13622a;
    }

    private void a(List<P0Message> list) {
        this.f13622a = list;
    }

    public P0MessageList() {
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f13622a);
        parcel.writeInt(this.b);
    }

    protected P0MessageList(Parcel parcel) {
        this.f13622a = parcel.createTypedArrayList(P0Message.CREATOR);
        this.b = parcel.readInt();
    }
}
