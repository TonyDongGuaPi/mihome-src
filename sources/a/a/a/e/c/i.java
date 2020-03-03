package a.a.a.e.c;

import android.os.Parcel;
import android.os.Parcelable;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class i extends g {
    public static final Parcelable.Creator<i> CREATOR = new a();
    public List<b> s;
    public String t;

    public class a implements Parcelable.Creator<i> {
        /* renamed from: a */
        public i createFromParcel(Parcel parcel) {
            return new i(parcel);
        }

        /* renamed from: a */
        public i[] newArray(int i) {
            return new i[i];
        }
    }

    public static class b implements Parcelable {
        public static final Parcelable.Creator<b> CREATOR = new a();

        /* renamed from: a  reason: collision with root package name */
        public String f381a;
        public String b;

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

        public b() {
        }

        public b(Parcel parcel) {
            this.f381a = parcel.readString();
            this.b = parcel.readString();
        }

        public String a() {
            return this.f381a;
        }

        public void a(String str) {
            this.f381a = str;
        }

        public String b() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public int describeContents() {
            return 0;
        }

        public String toString() {
            return this.b;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f381a);
            parcel.writeString(this.b);
        }
    }

    public i(Parcel parcel) {
        super(parcel);
        this.s = parcel.createTypedArrayList(b.CREATOR);
        this.t = parcel.readString();
    }

    public i(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void a(String str) {
        super.a(str);
        try {
            this.s = new ArrayList();
            JSONObject jSONObject = new JSONObject(str);
            this.t = jSONObject.getString("qid");
            JSONArray jSONArray = jSONObject.getJSONArray("vr");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                String[] split = jSONArray.getString(i).split(PaymentOptionsDecoder.pipeSeparator);
                if (split.length == 2) {
                    b bVar = new b();
                    bVar.a(split[0]);
                    bVar.b(split[1]);
                    this.s.add(bVar);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<b> d() {
        return this.s;
    }

    public int describeContents() {
        return 0;
    }

    public String s() {
        return this.t;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.s);
        parcel.writeString(this.t);
    }
}
