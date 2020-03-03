package a.a.a.e.c;

import a.a.a.e.b.f;
import android.os.Parcel;
import android.os.Parcelable;
import in.cashify.otex.R;
import org.json.JSONObject;

public class p extends g {
    public static final Parcelable.Creator<p> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    public int f386a;

    public class a implements Parcelable.Creator<p> {
        /* renamed from: a */
        public p createFromParcel(Parcel parcel) {
            return new p(parcel);
        }

        /* renamed from: a */
        public p[] newArray(int i) {
            return new p[i];
        }
    }

    public p(Parcel parcel) {
        super(parcel);
        this.f386a = parcel.readInt();
    }

    public p(JSONObject jSONObject) {
        super(jSONObject);
        a(R.drawable.ic_wifi);
        c(R.drawable.ic_wifi);
        d(R.drawable.ic_wifi_s);
    }

    public a.a.a.e.a a() {
        return f.a(this);
    }

    public void a(String str) {
        super.a(str);
        try {
            this.f386a = new JSONObject(str).optInt("mi");
        } catch (Throwable unused) {
        }
    }

    public boolean b() {
        return this.f386a == 1;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f386a);
    }
}
