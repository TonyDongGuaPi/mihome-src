package in.cashify.otex;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.stat.a.l;
import org.json.JSONObject;

public class ExchangeSetup implements Parcelable {
    public static final Parcelable.Creator<ExchangeSetup> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    public static final String f2600a = "___otex_current";
    public static final String b = "___otex_other_gadget";
    public String c = f2600a;
    public int d;
    public String e;
    public double f;
    public int g;

    public class a implements Parcelable.Creator<ExchangeSetup> {
        /* renamed from: a */
        public ExchangeSetup createFromParcel(Parcel parcel) {
            return new ExchangeSetup(parcel);
        }

        /* renamed from: a */
        public ExchangeSetup[] newArray(int i) {
            return new ExchangeSetup[i];
        }
    }

    public ExchangeSetup() {
    }

    public ExchangeSetup(Parcel parcel) {
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = parcel.readString();
        this.f = parcel.readDouble();
        this.g = parcel.readInt();
    }

    public String a() {
        return this.c;
    }

    public void a(double d2) {
        this.f = d2;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(JSONObject jSONObject) {
        jSONObject.put("em", a());
        jSONObject.put(l.a.A, b());
        jSONObject.put("ndi", c());
        jSONObject.put("ndp", d());
        jSONObject.put("daid", e());
    }

    public int b() {
        return this.d;
    }

    public void b(int i) {
        this.g = i;
    }

    public void b(String str) {
        this.e = str;
    }

    public String c() {
        return this.e;
    }

    public double d() {
        return this.f;
    }

    public int describeContents() {
        return 0;
    }

    public int e() {
        return this.g;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeString(this.e);
        parcel.writeDouble(this.f);
        parcel.writeInt(this.g);
    }
}
