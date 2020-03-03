package a.a.a.e.c;

import a.a.a.e.e.d;
import android.os.Parcel;
import android.os.Parcelable;
import in.cashify.otex.R;
import org.json.JSONObject;

public class o extends g {
    public static final Parcelable.Creator<o> CREATOR = new a();

    public class a implements Parcelable.Creator<o> {
        /* renamed from: a */
        public o createFromParcel(Parcel parcel) {
            return new o(parcel);
        }

        /* renamed from: a */
        public o[] newArray(int i) {
            return new o[i];
        }
    }

    public o(Parcel parcel) {
        super(parcel);
    }

    public o(JSONObject jSONObject) {
        super(jSONObject);
        c(R.drawable.ic_volume_button_1);
        d(R.drawable.ic_vol_button_s);
    }

    public a.a.a.e.a a() {
        return d.a(this);
    }

    public int describeContents() {
        return 0;
    }
}
