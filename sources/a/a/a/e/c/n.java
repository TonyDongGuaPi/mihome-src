package a.a.a.e.c;

import a.a.a.e.d.d;
import android.os.Parcel;
import android.os.Parcelable;
import in.cashify.otex.R;
import org.json.JSONObject;

public class n extends g {
    public static final Parcelable.Creator<n> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    public int f385a;

    public class a implements Parcelable.Creator<n> {
        /* renamed from: a */
        public n createFromParcel(Parcel parcel) {
            return new n(parcel);
        }

        /* renamed from: a */
        public n[] newArray(int i) {
            return new n[i];
        }
    }

    public n(Parcel parcel) {
        super(parcel);
        this.f385a = parcel.readInt();
    }

    public n(JSONObject jSONObject) {
        super(jSONObject);
        a(R.drawable.ic_vibration);
        c(R.drawable.ic_vibration);
        d(R.drawable.ic_vibration_s);
    }

    public a.a.a.e.a a() {
        return d.a(this);
    }

    public void a(String str) {
        super.a(str);
        try {
            this.f385a = new JSONObject(str).optInt("vc", 1);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int b() {
        return this.f385a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f385a);
    }
}
