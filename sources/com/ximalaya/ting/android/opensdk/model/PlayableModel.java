package com.ximalaya.ting.android.opensdk.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.live.schedule.Schedule;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.util.XiMaDataSupport;

public class PlayableModel extends XiMaDataSupport implements Parcelable {
    public static final Parcelable.Creator<PlayableModel> CREATOR = new Parcelable.Creator<PlayableModel>() {
        /* renamed from: a */
        public PlayableModel createFromParcel(Parcel parcel) {
            return new PlayableModel(parcel);
        }

        /* renamed from: a */
        public PlayableModel[] newArray(int i) {
            return new PlayableModel[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final String f2005a = "track";
    public static final String b = "radio";
    public static final String c = "schedule";
    public static final String d = "live_flv";
    public static final String e = "tts";
    public boolean f;
    public long g;
    public long h;
    public String i;
    @SerializedName(alternate = {"trackId"}, value = "id")
    private long j;
    private String k;
    private int l = -1;

    public int describeContents() {
        return 0;
    }

    public long a() {
        return this.j;
    }

    public void a(long j2) {
        this.j = j2;
    }

    public String b() {
        return this.k;
    }

    public void a(String str) {
        this.k = str;
    }

    public int c() {
        return this.l;
    }

    public void a(int i2) {
        this.l = i2;
    }

    public boolean d() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public long e() {
        return this.g;
    }

    public void b(long j2) {
        this.g = j2;
    }

    public long f() {
        return this.h;
    }

    public void c(long j2) {
        this.h = j2;
    }

    public String g() {
        return this.i;
    }

    public void b(String str) {
        this.i = str;
    }

    public int hashCode() {
        return 31 + ((int) (this.j ^ (this.j >>> 32)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        boolean z = (obj instanceof Track) && (this instanceof Track);
        boolean z2 = (obj instanceof Radio) && (this instanceof Radio);
        boolean z3 = (obj instanceof Schedule) && (this instanceof Schedule);
        boolean z4 = getClass() == obj.getClass();
        if (!z && !z2 && !z3 && !z4) {
            return false;
        }
        PlayableModel playableModel = (PlayableModel) obj;
        if (this.f != playableModel.f) {
            return false;
        }
        if (this.f) {
            return TextUtils.equals(this.i, playableModel.i);
        }
        if (this.j == playableModel.j) {
            return true;
        }
        return false;
    }

    public void a(Parcel parcel) {
        a(parcel.readLong());
        a(parcel.readString());
        a(parcel.readInt());
        a(parcel.readInt() != 0);
        b(parcel.readLong());
        c(parcel.readLong());
        b(parcel.readString());
    }

    public String toString() {
        return "PlayableModel{dataId=" + this.j + ", kind='" + this.k + Operators.SINGLE_QUOTE + ", lastPlayedMills=" + this.l + Operators.BLOCK_END;
    }

    public PlayableModel() {
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.j);
        parcel.writeString(this.k);
        parcel.writeInt(this.l);
        parcel.writeByte(this.f ? (byte) 1 : 0);
        parcel.writeLong(this.g);
        parcel.writeLong(this.h);
        parcel.writeString(this.i);
    }

    protected PlayableModel(Parcel parcel) {
        this.j = parcel.readLong();
        this.k = parcel.readString();
        this.l = parcel.readInt();
        this.f = parcel.readByte() != 0;
        this.g = parcel.readLong();
        this.h = parcel.readLong();
        this.i = parcel.readString();
    }
}
