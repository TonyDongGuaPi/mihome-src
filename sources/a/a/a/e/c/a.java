package a.a.a.e.c;

import android.os.Parcel;
import android.os.Parcelable;
import in.cashify.otex.R;
import org.json.JSONObject;

public class a extends g {
    public static final Parcelable.Creator<a> CREATOR = new C0003a();

    /* renamed from: a.a.a.e.c.a$a  reason: collision with other inner class name */
    public class C0003a implements Parcelable.Creator<a> {
        /* renamed from: a */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        /* renamed from: a */
        public a[] newArray(int i) {
            return new a[i];
        }
    }

    public a(Parcel parcel) {
        super(parcel);
    }

    public a(JSONObject jSONObject) {
        super(jSONObject);
        a(R.drawable.ic_audio);
        c(R.drawable.ic_audio);
        d(R.drawable.ic_audio_s);
    }

    public a.a.a.e.a a() {
        return a.a.a.e.e.a.a(this);
    }

    public int describeContents() {
        return 0;
    }
}
