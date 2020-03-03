package a.a.a;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.WorkerThread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

public class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new C0000a();

    /* renamed from: a  reason: collision with root package name */
    public String f369a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public double k;
    public double l;
    public int m;
    public String n;
    public int o;
    public String p;
    public List<String> q;

    /* renamed from: a.a.a.a$a  reason: collision with other inner class name */
    public class C0000a implements Parcelable.Creator<a> {
        /* renamed from: a */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        /* renamed from: a */
        public a[] newArray(int i) {
            return new a[i];
        }
    }

    public static class b {
        @WorkerThread
        public a a(Context context) {
            a aVar = new a((C0000a) null);
            String unused = aVar.f369a = Build.MANUFACTURER;
            String unused2 = aVar.b = Build.ID;
            String unused3 = aVar.c = Build.BRAND;
            String unused4 = aVar.d = Build.MODEL;
            String unused5 = aVar.e = Build.VERSION.RELEASE;
            String unused6 = aVar.f = d.i();
            String unused7 = aVar.g = System.getProperty("os.arch");
            String unused8 = aVar.h = String.valueOf(d.j());
            String unused9 = aVar.i = String.valueOf(d.k());
            String unused10 = aVar.j = String.valueOf(d.l());
            double unused11 = aVar.k = d.h();
            double unused12 = aVar.l = (double) d.a();
            String unused13 = aVar.n = d.b();
            int unused14 = aVar.m = aVar.n.equals("NA") ^ true ? 1 : 0;
            String unused15 = aVar.p = d.b(context);
            int unused16 = aVar.o = aVar.p.equals("NA") ^ true ? 1 : 0;
            List unused17 = aVar.q = new ArrayList();
            aVar.q.addAll(d.a(context, (String[]) null));
            return aVar;
        }
    }

    public a() {
    }

    public /* synthetic */ a(C0000a aVar) {
        this();
    }

    public a(Parcel parcel) {
        this.f369a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readDouble();
        this.l = parcel.readDouble();
        this.m = parcel.readInt();
        this.n = parcel.readString();
        this.o = parcel.readInt();
        this.p = parcel.readString();
        this.q = parcel.createStringArrayList();
    }

    public void a(JSONObject jSONObject) {
        jSONObject.put("odmf", this.f369a);
        jSONObject.put("odi", this.b);
        jSONObject.put("odb", this.c);
        jSONObject.put("odm", this.d);
        jSONObject.put("os", this.e);
        jSONObject.put("cps", this.f);
        jSONObject.put("parch", this.g);
        jSONObject.put("pcs", this.h);
        jSONObject.put("pc_cpu", this.i);
        jSONObject.put("pc_sys", this.j);
        jSONObject.put("ram", this.k);
        jSONObject.put("ds", this.l);
        jSONObject.put("idr", this.m);
        jSONObject.put("rsa", this.n);
        jSONObject.put("esa", this.p);
        jSONObject.put("ide", this.o);
        jSONObject.put("imei", d.a((Collection<String>) this.q));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f369a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeDouble(this.k);
        parcel.writeDouble(this.l);
        parcel.writeInt(this.m);
        parcel.writeString(this.n);
        parcel.writeInt(this.o);
        parcel.writeString(this.p);
        parcel.writeStringList(this.q);
    }
}
