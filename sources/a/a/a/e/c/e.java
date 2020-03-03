package a.a.a.e.c;

import android.os.Parcel;
import android.os.Parcelable;
import in.cashify.otex.R;
import in.cashify.otex.diagnose.semi.ChargerDiagnoseFragment;
import org.json.JSONObject;

public class e extends g {
    public static final Parcelable.Creator<e> CREATOR = new a();

    public class a implements Parcelable.Creator<e> {
        /* renamed from: a */
        public e createFromParcel(Parcel parcel) {
            return new e(parcel);
        }

        /* renamed from: a */
        public e[] newArray(int i) {
            return new e[i];
        }
    }

    public e(Parcel parcel) {
        super(parcel);
    }

    public e(JSONObject jSONObject) {
        super(jSONObject);
        a(R.drawable.ic_charger);
        c(R.drawable.ic_charger);
        d(R.drawable.ic_charger_s);
    }

    public a.a.a.e.a a() {
        return ChargerDiagnoseFragment.a(this);
    }

    public int describeContents() {
        return 0;
    }
}
