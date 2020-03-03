package com.xiaomi.smarthome.core.server.internal.homeroom;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.facebook.react.modules.appstate.AppStateModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Home implements Parcelable {
    public static final Parcelable.Creator<Home> CREATOR = new Parcelable.Creator<Home>() {
        /* renamed from: a */
        public Home createFromParcel(Parcel parcel) {
            return new Home(parcel);
        }

        /* renamed from: a */
        public Home[] newArray(int i) {
            return new Home[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static int f14580a = 1;
    public static int b = 0;
    public static final String c = "latitude";
    public static final String d = "longitude";
    public static final String e = "city_id";
    private static final String f = "1";
    private String g;
    private String h;
    private String i;
    private List<String> j;
    private final String k;
    private String l;
    private String m;
    private String n;
    private int o;
    private List<String> p;
    private List<Room> q;
    private String r;
    private String s;
    private String t;
    private long u;

    public int describeContents() {
        return 0;
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f14581a = "";
        /* access modifiers changed from: private */
        public String b = "";
        /* access modifiers changed from: private */
        public String c = "";
        /* access modifiers changed from: private */
        public String d = "";
        /* access modifiers changed from: private */
        public String e = "";
        /* access modifiers changed from: private */
        public String f = "";
        /* access modifiers changed from: private */
        public String g = "";
        /* access modifiers changed from: private */
        public int h = 0;
        /* access modifiers changed from: private */
        public List<String> i = new UnduplicateList();
        /* access modifiers changed from: private */
        public List<Room> j = new ArrayList();
        /* access modifiers changed from: private */
        public String k = "0";
        /* access modifiers changed from: private */
        public String l = "0";
        /* access modifiers changed from: private */
        public String m = "0";
        /* access modifiers changed from: private */
        public long n;

        public static class UnduplicateList<String> extends ArrayList<String> {
            public boolean add(String string) {
                return contains(string) || super.add(string);
            }
        }

        public Builder a(String str) {
            this.f14581a = str;
            return this;
        }

        public Builder b(String str) {
            this.b = str;
            return this;
        }

        public Builder c(String str) {
            this.c = str;
            return this;
        }

        public Builder d(String str) {
            this.d = str;
            return this;
        }

        public Builder e(String str) {
            this.e = str;
            return this;
        }

        public Builder f(String str) {
            this.f = str;
            return this;
        }

        public Builder g(String str) {
            this.g = str;
            return this;
        }

        public Builder a(int i2) {
            this.h = i2;
            return this;
        }

        public Builder a(List<String> list) {
            this.i = list;
            return this;
        }

        public Builder b(List<Room> list) {
            this.j = list;
            return this;
        }

        public Builder h(String str) {
            this.m = str;
            return this;
        }

        public Builder i(String str) {
            this.k = str;
            return this;
        }

        public Builder j(String str) {
            this.l = str;
            return this;
        }

        public Builder a(long j2) {
            this.n = j2;
            return this;
        }

        public Home a() {
            return new Home(this);
        }
    }

    private Home(Builder builder) {
        this.r = "0";
        this.s = "0";
        this.t = "0";
        this.u = 0;
        this.g = builder.f14581a;
        this.h = builder.b;
        this.i = builder.c;
        this.k = builder.d;
        this.j = "1".equals(this.k) ? new ArrayList() : null;
        this.l = builder.e;
        this.m = builder.f;
        this.n = builder.g;
        this.o = builder.h;
        this.p = builder.i;
        this.q = builder.j;
        this.t = builder.m;
        this.r = builder.k;
        this.s = builder.l;
        this.u = builder.n;
    }

    public void a(String str) {
        this.r = str;
    }

    public void b(String str) {
        this.s = str;
    }

    public void c(String str) {
        this.t = str;
    }

    public String a() {
        return this.t;
    }

    public String b() {
        return this.r;
    }

    public String c() {
        return this.s;
    }

    public List<Room> d() {
        return this.q;
    }

    public void a(List<Room> list) {
        this.q = list;
    }

    public String e() {
        return this.l;
    }

    public void d(String str) {
        this.l = str;
    }

    public String f() {
        return this.g;
    }

    public void e(String str) {
        this.g = str;
    }

    public String g() {
        return this.h;
    }

    public void f(String str) {
        this.h = str;
    }

    public String h() {
        return this.i;
    }

    public void g(String str) {
        this.i = str;
    }

    public List<String> i() {
        return this.j;
    }

    public String j() {
        return this.m;
    }

    public void h(String str) {
        this.m = str;
    }

    public String k() {
        return this.n;
    }

    public void i(String str) {
        this.n = str;
    }

    public int l() {
        return this.o;
    }

    public void a(int i2) {
        this.o = i2;
    }

    public List<String> m() {
        if (this.j == null || this.j.size() == 0) {
            return this.p;
        }
        if (this.p == null) {
            return this.j;
        }
        ArrayList arrayList = new ArrayList(this.j.size() + this.p.size());
        arrayList.addAll(this.p);
        arrayList.addAll(this.j);
        return arrayList;
    }

    public void b(List<String> list) {
        this.p = list;
    }

    public long n() {
        return this.u;
    }

    public void a(long j2) {
        this.u = j2;
    }

    public boolean o() {
        return this.o == b;
    }

    public static Home a(JSONObject jSONObject) {
        JSONArray optJSONArray;
        JSONArray optJSONArray2;
        Builder builder = new Builder();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull("bssid")) {
                    builder.a(jSONObject.optString("bssid"));
                }
                if (!jSONObject.isNull("desc")) {
                    builder.b(jSONObject.optString("desc"));
                }
                if (!jSONObject.isNull("id")) {
                    builder.f(jSONObject.optString("id"));
                }
                if (!jSONObject.isNull("name")) {
                    builder.g(jSONObject.optString("name"));
                }
                if (!jSONObject.isNull("shareflag")) {
                    builder.a(jSONObject.optInt("shareflag"));
                }
                if (!jSONObject.isNull("uid")) {
                    builder.a(jSONObject.optLong("uid"));
                }
                if (!jSONObject.isNull("icon")) {
                    builder.c(jSONObject.optString("icon"));
                }
                if (!jSONObject.isNull("status")) {
                    builder.d(jSONObject.optString("status"));
                }
                if (!jSONObject.isNull("latitude")) {
                    String unused = builder.k = jSONObject.optString("latitude");
                }
                if (!jSONObject.isNull("longitude")) {
                    String unused2 = builder.l = jSONObject.optString("longitude");
                }
                if (!jSONObject.isNull("city_id")) {
                    String unused3 = builder.m = jSONObject.optString("city_id");
                }
                if (!jSONObject.isNull("status")) {
                    String unused4 = builder.d = jSONObject.optString("status");
                }
                if (!jSONObject.isNull("dids") && (optJSONArray2 = jSONObject.optJSONArray("dids")) != null) {
                    Builder.UnduplicateList unduplicateList = new Builder.UnduplicateList();
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        if (!TextUtils.isEmpty(optJSONArray2.optString(i2))) {
                            unduplicateList.add(optJSONArray2.optString(i2));
                        }
                    }
                    builder.a((List<String>) unduplicateList);
                    unduplicateList.trimToSize();
                }
                if (!jSONObject.isNull("roomlist")) {
                    ArrayList arrayList = new ArrayList();
                    JSONArray optJSONArray3 = jSONObject.optJSONArray("roomlist");
                    for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                        arrayList.add(Room.a(optJSONArray3.optJSONObject(i3)));
                    }
                    builder.b((List<Room>) arrayList);
                    arrayList.trimToSize();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        Home a2 = builder.a();
        try {
            if (!jSONObject.isNull("status_list") && (optJSONArray = jSONObject.optJSONArray("status_list")) != null) {
                ArrayList arrayList2 = new ArrayList();
                for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                    String optString = optJSONArray.optString(i4);
                    if (!TextUtils.isEmpty(optString)) {
                        arrayList2.add(optString);
                    }
                }
                a2.j = arrayList2;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return a2;
    }

    public static boolean j(String str) {
        return !TextUtils.isEmpty(str) && !TextUtils.equals(str, "0");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Home home = (Home) obj;
        if (this.m != null) {
            return this.m.equals(home.m);
        }
        if (home.m == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.m != null) {
            return this.m.hashCode();
        }
        return 0;
    }

    public static boolean a(String str, String str2, String str3, String str4) {
        if (!j(str) || !j(str2) || !j(str3) || !j(str4)) {
            return false;
        }
        return Math.abs(Double.parseDouble(str) - Double.parseDouble(str3)) < 0.001d && Math.abs(Double.parseDouble(str2) - Double.parseDouble(str4)) < 0.001d;
    }

    public JSONObject p() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(AppStateModule.APP_STATE_BACKGROUND, this.l);
            jSONObject.put("bssid", this.g);
            jSONObject.put("city_id", this.t);
            jSONObject.put("desc", this.h);
            jSONObject.put("icon", this.i);
            jSONObject.put("id", this.m);
            jSONObject.put("latitude", this.r);
            jSONObject.put("longitude", this.s);
            jSONObject.put("name", this.n);
            jSONObject.put("shareflag", this.o);
            jSONObject.put("uid", this.u);
            if (this.p != null) {
                JSONArray jSONArray = new JSONArray();
                for (int i2 = 0; i2 < this.p.size(); i2++) {
                    jSONArray.put(this.p.get(i2));
                }
                jSONObject.put("dids", jSONArray);
            }
            if (!TextUtils.isEmpty(this.k)) {
                jSONObject.put("status", this.k);
            }
            if (this.j != null && !this.j.isEmpty()) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i3 = 0; i3 < this.j.size(); i3++) {
                    jSONArray2.put(this.j.get(i3));
                }
                jSONObject.put("status_list", jSONArray2);
            }
            if (this.q != null) {
                JSONArray jSONArray3 = new JSONArray();
                for (int i4 = 0; i4 < this.q.size(); i4++) {
                    jSONArray3.put(this.q.get(i4).i());
                }
                jSONObject.put("roomlist", jSONArray3);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeStringList(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeInt(this.o);
        parcel.writeStringList(this.p);
        parcel.writeTypedList(this.q);
        parcel.writeString(this.r);
        parcel.writeString(this.s);
        parcel.writeString(this.t);
        parcel.writeLong(this.u);
    }

    protected Home(Parcel parcel) {
        this.r = "0";
        this.s = "0";
        this.t = "0";
        this.u = 0;
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.createStringArrayList();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.o = parcel.readInt();
        this.p = parcel.createStringArrayList();
        this.q = parcel.createTypedArrayList(Room.CREATOR);
        this.r = parcel.readString();
        this.s = parcel.readString();
        this.t = parcel.readString();
        this.u = parcel.readLong();
    }

    public void a(Home home) {
        b(home);
    }

    private void b(Home home) {
        c(home);
        d(home);
    }

    private void c(Home home) {
        if (home != null && home.m() != null && !home.m().isEmpty()) {
            if (this.p == null) {
                this.p = home.p;
            } else {
                this.p.addAll(home.p);
            }
        }
    }

    private void d(Home home) {
        if (home != null && home.d() != null && !home.d().isEmpty()) {
            if (d() == null) {
                a(home.d());
                return;
            }
            List<Room> d2 = home.d();
            List<Room> d3 = d();
            for (int i2 = 0; i2 < d2.size(); i2++) {
                Room room = d2.get(i2);
                if (room != null) {
                    int indexOf = d3.indexOf(room);
                    if (indexOf == -1) {
                        d3.add(room);
                    } else {
                        d3.get(indexOf).b(room);
                    }
                }
            }
        }
    }
}
