package a.a.a.e.c;

import android.os.Parcel;
import android.os.Parcelable;
import in.cashify.otex.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class f extends i implements Parcelable {
    public static final Parcelable.Creator<f> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    public long f379a;
    public int[] b;

    public class a implements Parcelable.Creator<f> {
        /* renamed from: a */
        public f createFromParcel(Parcel parcel) {
            return new f(parcel);
        }

        /* renamed from: a */
        public f[] newArray(int i) {
            return new f[i];
        }
    }

    public f(Parcel parcel) {
        super(parcel);
        this.f379a = parcel.readLong();
        this.b = parcel.createIntArray();
    }

    public f(JSONObject jSONObject) {
        super(jSONObject);
        a(R.drawable.ic_dead_pixel);
        c(R.drawable.ic_dead_pixel);
        d(R.drawable.ic_dead_pixel_s);
    }

    public a.a.a.e.a a() {
        return a.a.a.e.d.a.a(this);
    }

    public void a(String str) {
        super.a(str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f379a = jSONObject.optLong("bdm", 1000);
            JSONArray optJSONArray = jSONObject.optJSONArray("bca");
            if (optJSONArray != null) {
                this.b = new int[optJSONArray.length()];
                for (int i = 0; i < optJSONArray.length(); i++) {
                    this.b[i] = optJSONArray.getInt(i);
                }
                return;
            }
            this.b = new int[]{-1, -16777216};
        } catch (Throwable unused) {
            this.f379a = 1000;
            this.b = new int[]{-1, -16777216};
        }
    }

    public long b() {
        return this.f379a;
    }

    public int[] c() {
        return this.b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.f379a);
        parcel.writeIntArray(this.b);
    }
}
