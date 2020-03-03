package com.mijia.camera.nas;

import android.os.Parcel;
import android.os.Parcelable;
import com.mijia.app.AppCode;
import org.json.JSONException;
import org.json.JSONObject;

public class NASInfo implements Parcelable {
    public static final Parcelable.Creator<NASInfo> CREATOR = new Parcelable.Creator<NASInfo>() {
        /* renamed from: a */
        public NASInfo createFromParcel(Parcel parcel) {
            return new NASInfo(parcel);
        }

        /* renamed from: a */
        public NASInfo[] newArray(int i) {
            return new NASInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f7931a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    private int e = 300;
    private int f = AppCode.o;
    private long g = 0;
    private int h = 1;
    private int i;
    private NASServer j;

    public int describeContents() {
        return 0;
    }

    protected NASInfo(Parcel parcel) {
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.readLong();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = (NASServer) parcel.readParcelable(NASServer.class.getClassLoader());
    }

    public NASInfo() {
    }

    public NASInfo(NASServer nASServer) {
        this.j = nASServer;
    }

    public void a(NASServer nASServer) {
        this.j = nASServer;
    }

    public NASServer a() {
        return this.j;
    }

    public String b() {
        return this.j == null ? "" : this.j.d();
    }

    public void a(long j2) {
        this.g = j2;
    }

    public long c() {
        return this.g;
    }

    public int d() {
        return this.e;
    }

    public void a(int i2) {
        this.e = i2;
    }

    public int e() {
        return this.f;
    }

    public void b(int i2) {
        this.f = i2;
    }

    public int f() {
        return this.h;
    }

    public void c(int i2) {
        this.h = i2;
    }

    public void d(int i2) {
        this.i = i2;
    }

    public int g() {
        return this.i;
    }

    public static NASInfo a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        NASInfo nASInfo = new NASInfo();
        nASInfo.b(jSONObject.optInt("video_retention_time"));
        nASInfo.a(jSONObject.optInt("sync_interval"));
        nASInfo.c(jSONObject.optInt("state"));
        nASInfo.a((long) jSONObject.optInt("last_sync_time"));
        nASInfo.d(jSONObject.optInt("error_code"));
        nASInfo.a(NASServer.a(jSONObject.optJSONObject("share")));
        return nASInfo;
    }

    public JSONObject h() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("state", this.h);
            jSONObject.put("sync_interval", this.e);
            jSONObject.put("video_retention_time", this.f);
            jSONObject.put("share", this.j.i());
            return jSONObject;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeLong(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeParcelable(this.j, i2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("mSycTime:");
        sb.append(this.e);
        sb.append(" mCycleTime:");
        sb.append(this.f);
        sb.append(" mState:");
        sb.append(this.h);
        sb.append(" mError:");
        sb.append(this.i);
        if (this.j != null) {
            sb.append(" mServer getName");
            sb.append(this.j.a());
            sb.append(" mServer getPwd:");
            sb.append(this.j.h());
            sb.append(" mServer getAddr:");
            sb.append(this.j.b());
            sb.append(" mServer getDir:");
            sb.append(this.j.d());
            sb.append(" mServer getDirName:");
            sb.append(this.j.e());
            sb.append(" mServer getUserName:");
            sb.append(this.j.g());
            sb.append(" mServer getType:");
            sb.append(this.j.f());
        }
        return sb.toString();
    }
}
