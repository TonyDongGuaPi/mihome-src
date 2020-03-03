package com.xiaomi.smarthome.framework.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

public class SceneCommand implements Parcelable {
    public static final Parcelable.Creator<SceneCommand> CREATOR = new Parcelable.Creator<SceneCommand>() {
        /* renamed from: a */
        public SceneCommand createFromParcel(Parcel parcel) {
            SceneCommand sceneCommand = new SceneCommand();
            sceneCommand.f16456a = parcel.readLong();
            sceneCommand.b = parcel.readLong();
            sceneCommand.c = parcel.readString();
            sceneCommand.d = parcel.readString();
            sceneCommand.e = parcel.readString();
            sceneCommand.f = parcel.readString();
            sceneCommand.g = parcel.readLong();
            sceneCommand.h = parcel.readString();
            return sceneCommand;
        }

        /* renamed from: a */
        public SceneCommand[] newArray(int i) {
            return new SceneCommand[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public long f16456a;
    public long b;
    public String c;
    public String d;
    public String e;
    public String f;
    public long g;
    public String h;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f16456a);
        parcel.writeLong(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeLong(this.g);
        parcel.writeString(this.h);
    }

    public static SceneCommand a(JSONObject jSONObject) {
        SceneCommand sceneCommand = new SceneCommand();
        sceneCommand.g = jSONObject.optLong("delay");
        sceneCommand.f = jSONObject.optString("body");
        return sceneCommand;
    }
}
