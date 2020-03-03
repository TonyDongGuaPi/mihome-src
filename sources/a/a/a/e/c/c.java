package a.a.a.e.c;

import a.a.a.e.b.b;
import android.os.Parcel;
import android.os.Parcelable;
import in.cashify.otex.R;
import org.json.JSONObject;

public class c extends g {
    public static final Parcelable.Creator<c> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    public int f377a;

    public class a implements Parcelable.Creator<c> {
        /* renamed from: a */
        public c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }

        /* renamed from: a */
        public c[] newArray(int i) {
            return new c[i];
        }
    }

    public c(Parcel parcel) {
        super(parcel);
        this.f377a = parcel.readInt();
    }

    public c(JSONObject jSONObject) {
        super(jSONObject);
        a(R.drawable.ic_bluetooth);
        c(R.drawable.ic_bluetooth);
        d(R.drawable.ic_bluetooth_s);
    }

    public a.a.a.e.a a() {
        return b.a(this);
    }

    public void a(String str) {
        try {
            this.f377a = new JSONObject(str).optInt("mi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean b() {
        return this.f377a == 1;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f377a);
    }
}
