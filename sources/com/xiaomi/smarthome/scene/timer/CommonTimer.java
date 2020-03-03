package com.xiaomi.smarthome.scene.timer;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;

public final class CommonTimer implements Parcelable, Cloneable {
    public static final Parcelable.Creator<CommonTimer> CREATOR = new Parcelable.Creator<CommonTimer>() {
        /* renamed from: a */
        public CommonTimer createFromParcel(Parcel parcel) {
            return new CommonTimer(parcel);
        }

        /* renamed from: a */
        public CommonTimer[] newArray(int i) {
            return new CommonTimer[i];
        }
    };
    public static final int n = 0;
    public static final int o = 1;
    public static final int p = -1;
    public static final String r = "on";
    public static final String s = "off";

    /* renamed from: a  reason: collision with root package name */
    public String f21632a;
    public String b;
    public boolean c;
    public boolean d;
    public boolean e;
    public String f;
    public String g;
    public CorntabUtils.CorntabParam h;
    public boolean i;
    public String j;
    public String k;
    public CorntabUtils.CorntabParam l;
    public String m;
    public int q;

    public int describeContents() {
        return 0;
    }

    public CommonTimer() {
        this.f21632a = null;
        this.c = true;
        this.d = true;
        this.e = false;
        this.f = null;
        this.g = null;
        this.h = new CorntabUtils.CorntabParam();
        this.i = false;
        this.j = null;
        this.k = null;
        this.l = new CorntabUtils.CorntabParam();
        this.b = null;
        this.q = 0;
        this.m = "0";
    }

    public static CommonTimer a(PlugTimer plugTimer, String str, String str2, String str3, String str4) {
        CommonTimer commonTimer = new CommonTimer();
        commonTimer.f21632a = plugTimer.j;
        commonTimer.d = plugTimer.b;
        commonTimer.i = plugTimer.f;
        commonTimer.l = plugTimer.h;
        commonTimer.e = plugTimer.c;
        commonTimer.h = plugTimer.e;
        commonTimer.b = "";
        commonTimer.j = str3;
        commonTimer.k = str4;
        commonTimer.f = str;
        commonTimer.g = str2;
        commonTimer.q = plugTimer.i;
        commonTimer.m = plugTimer.k;
        return commonTimer;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f21632a);
        parcel.writeByte(this.c ? (byte) 1 : 0);
        parcel.writeByte(this.d ? (byte) 1 : 0);
        parcel.writeByte(this.e ? (byte) 1 : 0);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeInt(this.h.f18664a);
        parcel.writeInt(this.h.b);
        parcel.writeInt(this.h.c);
        parcel.writeInt(this.h.d);
        parcel.writeInt(this.h.e);
        parcel.writeInt(this.h.f);
        parcel.writeString(this.h.h);
        parcel.writeBooleanArray(this.h.g);
        parcel.writeByte(this.i ? (byte) 1 : 0);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeInt(this.l.f18664a);
        parcel.writeInt(this.l.b);
        parcel.writeInt(this.l.c);
        parcel.writeInt(this.l.d);
        parcel.writeInt(this.l.e);
        parcel.writeInt(this.l.f);
        parcel.writeString(this.l.h);
        parcel.writeBooleanArray(this.l.g);
        parcel.writeString(this.b);
        parcel.writeInt(this.q);
        parcel.writeString(this.m);
    }

    public Object clone() {
        try {
            CommonTimer commonTimer = (CommonTimer) super.clone();
            try {
                commonTimer.f21632a = this.f21632a;
                commonTimer.d = this.d;
                commonTimer.e = this.e;
                commonTimer.f = this.f;
                commonTimer.g = this.g;
                commonTimer.h = (CorntabUtils.CorntabParam) this.h.clone();
                commonTimer.i = this.i;
                commonTimer.j = this.j;
                commonTimer.k = this.k;
                commonTimer.l = (CorntabUtils.CorntabParam) this.l.clone();
                commonTimer.b = this.b;
                commonTimer.q = this.q;
                commonTimer.m = this.m;
                return commonTimer;
            } catch (CloneNotSupportedException unused) {
                return commonTimer;
            }
        } catch (CloneNotSupportedException unused2) {
            return null;
        }
    }

    private CommonTimer(Parcel parcel) {
        this.f21632a = parcel.readString();
        boolean z = false;
        this.c = parcel.readByte() != 0;
        this.d = parcel.readByte() != 0;
        this.e = parcel.readByte() != 0;
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = new CorntabUtils.CorntabParam();
        this.h.f18664a = parcel.readInt();
        this.h.b = parcel.readInt();
        this.h.c = parcel.readInt();
        this.h.d = parcel.readInt();
        this.h.e = parcel.readInt();
        this.h.f = parcel.readInt();
        this.h.h = parcel.readString();
        parcel.readBooleanArray(this.h.g);
        this.i = parcel.readByte() != 0 ? true : z;
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = new CorntabUtils.CorntabParam();
        this.l.f18664a = parcel.readInt();
        this.l.b = parcel.readInt();
        this.l.c = parcel.readInt();
        this.l.d = parcel.readInt();
        this.l.e = parcel.readInt();
        this.l.f = parcel.readInt();
        this.l.h = parcel.readString();
        parcel.readBooleanArray(this.l.g);
        this.b = parcel.readString();
        this.q = parcel.readInt();
        this.m = parcel.readString();
    }

    public static String a(int i2, int i3) {
        return a(i2) + ":" + a(i3);
    }

    public static String a(int i2) {
        if (i2 >= 10) {
            return Integer.toString(i2);
        }
        return "0" + i2;
    }

    public static PlugTimer a(CommonTimer commonTimer) {
        PlugTimer plugTimer = new PlugTimer();
        plugTimer.j = commonTimer.f21632a;
        plugTimer.b = commonTimer.d;
        plugTimer.f21674a = true;
        plugTimer.f = commonTimer.i;
        plugTimer.h = commonTimer.l;
        plugTimer.c = commonTimer.e;
        plugTimer.e = commonTimer.h;
        plugTimer.g = false;
        plugTimer.d = false;
        plugTimer.i = commonTimer.q;
        plugTimer.k = commonTimer.m;
        return plugTimer;
    }

    public String a(String str, String str2) {
        if (!this.d || this.h.d() != 0) {
            return "";
        }
        if (this.e && !this.i) {
            return this.h.b() + " " + str;
        } else if (!this.e && this.i) {
            return this.l.b() + " " + str2;
        } else if (this.h.a()) {
            return this.l.b() + " " + str2;
        } else if (this.l.a()) {
            return this.h.b() + " " + str;
        } else {
            return this.h.b() + " " + str;
        }
    }

    public String a(Activity activity, String str, String str2) {
        if (!this.d || this.h.d() != 0) {
            return "";
        }
        if (this.e && !this.i) {
            return this.h.a(activity) + " " + str;
        } else if (!this.e && this.i) {
            return this.l.a(activity) + " " + str2;
        } else if (this.h.a()) {
            return this.l.a(activity) + " " + str2;
        } else if (this.l.a()) {
            return this.h.a(activity) + " " + str;
        } else {
            return this.h.a(activity) + " " + str;
        }
    }

    public String b(String str, String str2) {
        if (this.d || this.h.d() != 0) {
            return "";
        }
        if (this.e && !this.i) {
            return this.h.b() + " " + str;
        } else if (!this.e && this.i) {
            return this.l.b() + " " + str2;
        } else if (this.h.b(this.l) < 0) {
            return this.l.b() + " " + str2;
        } else {
            return this.h.b() + " " + str;
        }
    }
}
