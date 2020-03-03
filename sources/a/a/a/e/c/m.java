package a.a.a.e.c;

import a.a.a.e.d.c;
import android.os.Parcel;
import android.os.Parcelable;
import in.cashify.otex.R;
import org.json.JSONObject;

public class m extends g {
    public static final Parcelable.Creator<m> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    public int f384a;

    public class a implements Parcelable.Creator<m> {
        /* renamed from: a */
        public m createFromParcel(Parcel parcel) {
            return new m(parcel);
        }

        /* renamed from: a */
        public m[] newArray(int i) {
            return new m[i];
        }
    }

    public m(Parcel parcel) {
        super(parcel);
        this.f384a = parcel.readInt();
    }

    public m(JSONObject jSONObject) {
        super(jSONObject);
        a(R.drawable.ic_display);
        c(R.drawable.ic_display);
        d(R.drawable.ic_display_test_s);
    }

    public a.a.a.e.a a() {
        return c.a(this);
    }

    public void a(String str) {
        super.a(str);
        try {
            this.f384a = new JSONObject(str).optInt("tit", 10);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int b() {
        return this.f384a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f384a);
    }
}
