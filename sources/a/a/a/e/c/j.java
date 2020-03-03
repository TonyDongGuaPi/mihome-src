package a.a.a.e.c;

import a.a.a.e.b.e;
import android.os.Parcel;
import android.os.Parcelable;
import in.cashify.otex.R;
import org.json.JSONObject;

public class j extends g {
    public static final Parcelable.Creator<j> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    public int f382a;

    public class a implements Parcelable.Creator<j> {
        /* renamed from: a */
        public j createFromParcel(Parcel parcel) {
            return new j(parcel);
        }

        /* renamed from: a */
        public j[] newArray(int i) {
            return new j[i];
        }
    }

    public j(Parcel parcel) {
        super(parcel);
        this.f382a = parcel.readInt();
    }

    public j(JSONObject jSONObject) {
        super(jSONObject);
        c(R.drawable.ic_mic1);
        d(R.drawable.ic_mic_s);
    }

    public a.a.a.e.a a() {
        return e.a(this);
    }

    public void a(String str) {
        try {
            this.f382a = new JSONObject(str).optInt("ma", 65);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int b() {
        return this.f382a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f382a);
    }
}
