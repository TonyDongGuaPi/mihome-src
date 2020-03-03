package a.a.a.e.c;

import a.a.a.e.b.d;
import android.os.Parcel;
import android.os.Parcelable;
import in.cashify.otex.R;
import org.json.JSONObject;

public class h extends g {
    public static final Parcelable.Creator<h> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    public int f380a;

    public class a implements Parcelable.Creator<h> {
        /* renamed from: a */
        public h createFromParcel(Parcel parcel) {
            return new h(parcel);
        }

        /* renamed from: a */
        public h[] newArray(int i) {
            return new h[i];
        }
    }

    public h(Parcel parcel) {
        super(parcel);
        this.f380a = parcel.readInt();
    }

    public h(JSONObject jSONObject) {
        super(jSONObject);
        a(R.drawable.ic_gps);
        c(R.drawable.ic_gps);
        d(R.drawable.ic_gps_s);
    }

    public a.a.a.e.a a() {
        return d.a(this);
    }

    public void a(String str) {
        try {
            this.f380a = new JSONObject(str).optInt("mi");
        } catch (Throwable unused) {
        }
    }

    public boolean b() {
        return this.f380a == 1;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f380a);
    }
}
