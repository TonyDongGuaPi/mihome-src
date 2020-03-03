package a.a.a.e.c;

import a.a.a.e.e.c;
import android.os.Parcel;
import android.os.Parcelable;
import in.cashify.otex.R;
import org.json.JSONObject;

public class l extends g {
    public static final Parcelable.Creator<l> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    public int f383a;
    public int b;
    public int s;
    public String t;
    public String u;

    public class a implements Parcelable.Creator<l> {
        /* renamed from: a */
        public l createFromParcel(Parcel parcel) {
            return new l(parcel);
        }

        /* renamed from: a */
        public l[] newArray(int i) {
            return new l[i];
        }
    }

    public l(Parcel parcel) {
        super(parcel);
        this.f383a = parcel.readInt();
        this.b = parcel.readInt();
        this.s = parcel.readInt();
    }

    public l(JSONObject jSONObject) {
        super(jSONObject);
        a(R.drawable.ic_volume);
        c(R.drawable.ic_volume);
        d(R.drawable.ic_volume_s);
    }

    public a.a.a.e.a a() {
        return c.a(this);
    }

    public void a(String str) {
        super.a(str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f383a = jSONObject.optInt("ssd", 1);
            this.b = jSONObject.optInt("sas", 3);
            this.s = jSONObject.optInt("sa", 100);
            this.t = jSONObject.optString("am", "");
            this.u = jSONObject.optString("pam", "");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String b() {
        return this.t;
    }

    public String c() {
        return this.u;
    }

    public int d() {
        return this.f383a * 1000;
    }

    public int describeContents() {
        return 0;
    }

    public int s() {
        return this.b;
    }

    public int t() {
        return this.s;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f383a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.s);
    }
}
