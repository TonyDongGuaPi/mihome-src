package com.xiaomi.smarthome.ad.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.ad.api.AdType;

public class Advertisement implements Parcelable {
    public static final Parcelable.Creator<Advertisement> CREATOR = new Parcelable.Creator<Advertisement>() {
        /* renamed from: a */
        public Advertisement createFromParcel(Parcel parcel) {
            return new Advertisement(parcel);
        }

        /* renamed from: a */
        public Advertisement[] newArray(int i) {
            return new Advertisement[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private long f13638a;
    private String b;
    private String c;
    @AdType.Type
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private long j;
    private long k;
    private boolean l;
    private int m;

    public int describeContents() {
        return 0;
    }

    public long a() {
        return this.f13638a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public long h() {
        return this.j;
    }

    public long i() {
        return this.k;
    }

    public boolean j() {
        return this.l;
    }

    public int k() {
        return this.m;
    }

    public String l() {
        return this.h;
    }

    public String m() {
        return this.i;
    }

    public Advertisement() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.smarthome.ad.api.Advertisement a(org.json.JSONObject r4) {
        /*
            com.xiaomi.smarthome.ad.api.Advertisement r0 = new com.xiaomi.smarthome.ad.api.Advertisement
            r0.<init>()
            java.lang.String r1 = "ad_id"
            long r1 = r4.optLong(r1)
            r0.f13638a = r1
            java.lang.String r1 = "name"
            java.lang.String r1 = r4.optString(r1)
            r0.b = r1
            java.lang.String r1 = "intro"
            java.lang.String r1 = r4.optString(r1)
            r0.c = r1
            java.lang.String r1 = "ad_copy"
            java.lang.String r1 = r4.optString(r1)
            r0.g = r1
            java.lang.String r1 = "pics"
            java.lang.String r2 = ""
            java.lang.String r1 = r4.optString(r1, r2)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x003d
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0039 }
            r2.<init>(r1)     // Catch:{ JSONException -> 0x0039 }
            goto L_0x003e
        L_0x0039:
            r1 = move-exception
            r1.printStackTrace()
        L_0x003d:
            r2 = 0
        L_0x003e:
            r1 = 0
            if (r2 == 0) goto L_0x004d
            int r3 = r2.length()
            if (r3 <= 0) goto L_0x004d
            java.lang.String r2 = r2.optString(r1)
            r0.e = r2
        L_0x004d:
            java.lang.String r2 = "redirect"
            java.lang.String r2 = r4.optString(r2)
            r0.f = r2
            java.lang.String r2 = "start_time"
            long r2 = r4.optLong(r2)
            r0.j = r2
            java.lang.String r2 = "end_time"
            long r2 = r4.optLong(r2)
            r0.k = r2
            java.lang.String r2 = "is_permanent"
            int r2 = r4.optInt(r2)
            r3 = 1
            if (r2 != r3) goto L_0x006f
            r1 = 1
        L_0x006f:
            r0.l = r1
            java.lang.String r1 = "show_rules"
            int r1 = r4.optInt(r1)
            r0.m = r1
            java.lang.String r1 = "ad_title"
            java.lang.String r1 = r4.optString(r1)
            r0.h = r1
            java.lang.String r1 = "ad_subtitle"
            java.lang.String r1 = r4.optString(r1)
            r0.i = r1
            java.lang.String r1 = "type"
            int r4 = r4.optInt(r1)
            switch(r4) {
                case 0: goto L_0x00a2;
                case 1: goto L_0x009d;
                case 2: goto L_0x0098;
                case 3: goto L_0x0093;
                default: goto L_0x0092;
            }
        L_0x0092:
            goto L_0x00a6
        L_0x0093:
            java.lang.String r4 = "bottomPop"
            r0.d = r4
            goto L_0x00a6
        L_0x0098:
            java.lang.String r4 = "pop"
            r0.d = r4
            goto L_0x00a6
        L_0x009d:
            java.lang.String r4 = "notice"
            r0.d = r4
            goto L_0x00a6
        L_0x00a2:
            java.lang.String r4 = "banner"
            r0.d = r4
        L_0x00a6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.ad.api.Advertisement.a(org.json.JSONObject):com.xiaomi.smarthome.ad.api.Advertisement");
    }

    protected Advertisement(Parcel parcel) {
        this.f13638a = parcel.readLong();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.j = parcel.readLong();
        this.k = parcel.readLong();
        this.l = parcel.readByte() != 0;
        this.m = parcel.readInt();
        this.h = parcel.readString();
        this.i = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f13638a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeLong(this.j);
        parcel.writeLong(this.k);
        parcel.writeByte(this.l ^ true ? (byte) 1 : 0);
        parcel.writeInt(this.m);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
    }
}
