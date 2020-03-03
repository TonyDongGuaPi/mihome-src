package a.a.a.e.c;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import org.json.JSONObject;

public class g implements Parcelable {
    public static final Parcelable.Creator<g> CREATOR = new a();
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    @IdRes
    public int p;
    @IdRes
    public int q;
    @IdRes
    public int r;

    public class a implements Parcelable.Creator<g> {
        /* renamed from: a */
        public g createFromParcel(Parcel parcel) {
            return new g(parcel);
        }

        /* renamed from: a */
        public g[] newArray(int i) {
            return new g[i];
        }
    }

    public g(Parcel parcel) {
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.g = parcel.readInt();
        this.f = parcel.readInt();
        this.h = parcel.readInt();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.o = parcel.readString();
        this.p = parcel.readInt();
        this.q = parcel.readInt();
        this.r = parcel.readInt();
    }

    public g(JSONObject jSONObject) {
        this.i = jSONObject.getString("tid");
        this.j = jSONObject.optString(TtmlNode.TAG_TT);
        this.k = jSONObject.optString("tm");
        this.l = jSONObject.optString("ht");
        this.c = jSONObject.optInt("tp");
        this.f = jSONObject.optInt("epb");
        this.n = jSONObject.optString("pbt");
        this.g = jSONObject.optInt("enb");
        this.m = jSONObject.optString("nbt");
        this.d = jSONObject.optInt("td", 3);
        this.e = jSONObject.optInt("trd", 2);
        this.o = jSONObject.optString("ed");
        a(this.o);
    }

    public a.a.a.e.a a() {
        return new a.a.a.e.a();
    }

    public void a(int i2) {
        this.p = i2;
    }

    public void a(String str) {
    }

    public void b(int i2) {
        this.h = i2;
    }

    public void c(int i2) {
        this.q = i2;
    }

    public void d(int i2) {
        this.r = i2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean e() {
        return !TextUtils.isEmpty(f());
    }

    public String f() {
        return this.i;
    }

    public int g() {
        return this.c;
    }

    public long h() {
        return (long) (this.d * 1000);
    }

    public long i() {
        return (long) (this.e * 1000);
    }

    public String j() {
        return this.k;
    }

    public String k() {
        return this.l;
    }

    public String l() {
        return this.j;
    }

    public String m() {
        return this.n;
    }

    public String n() {
        return this.m;
    }

    public boolean o() {
        return this.f == 1;
    }

    public int p() {
        return this.h;
    }

    public int q() {
        return this.q;
    }

    public int r() {
        return this.r;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.g);
        parcel.writeInt(this.f);
        parcel.writeInt(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeInt(this.p);
        parcel.writeInt(this.q);
        parcel.writeInt(this.r);
    }
}
