package com.xiaomi.smarthome.scenenew.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.device.api.RecommendSceneItem;
import com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity;

public class DefaultSceneItemSet implements Parcelable {
    public static final Parcelable.Creator<SmartHomeSceneCreateEditActivity.DefaultSceneItemSet> CREATOR = new Parcelable.Creator<SmartHomeSceneCreateEditActivity.DefaultSceneItemSet>() {
        /* renamed from: a */
        public SmartHomeSceneCreateEditActivity.DefaultSceneItemSet createFromParcel(Parcel parcel) {
            return new SmartHomeSceneCreateEditActivity.DefaultSceneItemSet(parcel);
        }

        /* renamed from: a */
        public SmartHomeSceneCreateEditActivity.DefaultSceneItemSet[] newArray(int i) {
            return new SmartHomeSceneCreateEditActivity.DefaultSceneItemSet[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public Boolean f21982a;
    public String[] b;
    public RecommendSceneItem.Key[] c;
    public String d;
    public String e;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.f21982a);
        parcel.writeStringArray(this.b);
        if (this.c != null) {
            parcel.writeInt(this.c.length);
            for (RecommendSceneItem.Key writeToParcel : this.c) {
                writeToParcel.writeToParcel(parcel);
            }
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.d);
        parcel.writeString(this.e);
    }

    public DefaultSceneItemSet() {
    }

    public DefaultSceneItemSet(Parcel parcel) {
        this.f21982a = (Boolean) parcel.readValue(ClassLoader.getSystemClassLoader());
        if (this.f21982a == null) {
            this.f21982a = false;
        }
        this.b = parcel.createStringArray();
        int readInt = parcel.readInt();
        if (readInt != 0) {
            this.c = new RecommendSceneItem.Key[readInt];
            for (int i = 0; i < readInt; i++) {
                this.c[i] = new RecommendSceneItem.Key();
                this.c[i].readFromParcel(parcel);
            }
        }
        this.d = parcel.readString();
        this.e = parcel.readString();
    }
}
