package a.a.a.e.c;

import a.a.a.e.b.c;
import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.stat.a.l;
import com.xiaomi.youpin.hawkeye.upload.UploadConstants;
import in.cashify.otex.ExchangeManager;
import in.cashify.otex.R;
import org.json.JSONObject;

public class d extends g {
    public static final Parcelable.Creator<d> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    public double f378a = 50.0d;
    public int b = 65;

    public class a implements Parcelable.Creator<d> {
        /* renamed from: a */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }

        /* renamed from: a */
        public d[] newArray(int i) {
            return new d[i];
        }
    }

    public d(Parcel parcel) {
        super(parcel);
        this.b = parcel.readInt();
        this.f378a = parcel.readDouble();
    }

    public d(JSONObject jSONObject) {
        super(jSONObject);
        c(d());
        d(d());
    }

    public a.a.a.e.a a() {
        return c.a(this);
    }

    public void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f378a = (double) jSONObject.optInt(UploadConstants.i);
            this.b = jSONObject.optInt(l.a.x);
        } catch (Exception unused) {
        }
    }

    public double b() {
        return this.f378a;
    }

    public int c() {
        return this.b;
    }

    public final int d() {
        return f().equals(ExchangeManager.h.BACK_CAMERA.a()) ? R.drawable.ic_back_camera_s : R.drawable.ic_front_cam_s;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.b);
        parcel.writeDouble(this.f378a);
    }
}
