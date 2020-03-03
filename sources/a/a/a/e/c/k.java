package a.a.a.e.c;

import a.a.a.e.e.b;
import android.os.Parcel;
import android.os.Parcelable;
import in.cashify.otex.R;
import org.json.JSONObject;

public class k extends g {
    public static final Parcelable.Creator<k> CREATOR = new a();

    public class a implements Parcelable.Creator<k> {
        /* renamed from: a */
        public k createFromParcel(Parcel parcel) {
            return new k(parcel);
        }

        /* renamed from: a */
        public k[] newArray(int i) {
            return new k[i];
        }
    }

    public k(Parcel parcel) {
        super(parcel);
    }

    public k(JSONObject jSONObject) {
        super(jSONObject);
        a(R.drawable.ic_proximity);
        c(R.drawable.ic_proximity);
        d(R.drawable.ic_proximity_s);
    }

    public a.a.a.e.a a() {
        return b.a(this);
    }

    public int describeContents() {
        return 0;
    }
}
