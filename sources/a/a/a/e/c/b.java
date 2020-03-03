package a.a.a.e.c;

import android.os.Parcel;
import android.os.Parcelable;
import in.cashify.otex.R;
import org.json.JSONObject;

public class b extends g {
    public static final Parcelable.Creator<b> CREATOR = new a();

    public class a implements Parcelable.Creator<b> {
        /* renamed from: a */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        /* renamed from: a */
        public b[] newArray(int i) {
            return new b[i];
        }
    }

    public b(Parcel parcel) {
        super(parcel);
    }

    public b(JSONObject jSONObject) {
        super(jSONObject);
        a(R.drawable.ic_battery);
        c(R.drawable.ic_battery);
        d(R.drawable.ic_battery_s);
    }

    public a.a.a.e.a a() {
        return a.a.a.e.b.a.a(this);
    }

    public int describeContents() {
        return 0;
    }
}
