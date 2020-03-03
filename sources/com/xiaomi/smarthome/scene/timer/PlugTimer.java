package com.xiaomi.smarthome.scene.timer;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import java.util.Calendar;

public final class PlugTimer implements Parcelable, Cloneable {
    public static final Parcelable.Creator<PlugTimer> CREATOR = new Parcelable.Creator<PlugTimer>() {
        /* renamed from: a */
        public PlugTimer createFromParcel(Parcel parcel) {
            return new PlugTimer(parcel);
        }

        /* renamed from: a */
        public PlugTimer[] newArray(int i) {
            return new PlugTimer[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public boolean f21674a;
    public boolean b;
    public boolean c;
    public boolean d;
    public CorntabUtils.CorntabParam e;
    public boolean f;
    public boolean g;
    public CorntabUtils.CorntabParam h;
    public int i;
    public String j;
    public String k;

    public static boolean a(int i2, int i3, int i4, int i5) {
        return (i4 * 60) + i5 >= (i2 * 60) + i3;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f21674a ? 1 : 0);
        parcel.writeInt(this.b ? 1 : 0);
        parcel.writeInt(this.c ? 1 : 0);
        parcel.writeInt(this.d ? 1 : 0);
        parcel.writeInt(this.e.f18664a);
        parcel.writeInt(this.e.b);
        parcel.writeInt(this.e.c);
        parcel.writeInt(this.e.d);
        parcel.writeInt(this.e.e);
        parcel.writeInt(this.e.f);
        parcel.writeString(this.e.h);
        parcel.writeBooleanArray(this.e.g);
        parcel.writeInt(this.f ? 1 : 0);
        parcel.writeInt(this.g ? 1 : 0);
        parcel.writeInt(this.h.f18664a);
        parcel.writeInt(this.h.b);
        parcel.writeInt(this.h.c);
        parcel.writeInt(this.h.d);
        parcel.writeInt(this.h.e);
        parcel.writeInt(this.h.f);
        parcel.writeString(this.h.h);
        parcel.writeBooleanArray(this.h.g);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
    }

    public PlugTimer(Parcel parcel) {
        boolean z = false;
        this.i = 0;
        this.f21674a = parcel.readInt() == 1;
        this.b = parcel.readInt() == 1;
        this.c = parcel.readInt() == 1;
        this.d = parcel.readInt() == 1;
        this.e = new CorntabUtils.CorntabParam();
        this.e.f18664a = parcel.readInt();
        this.e.b = parcel.readInt();
        this.e.c = parcel.readInt();
        this.e.d = parcel.readInt();
        this.e.e = parcel.readInt();
        this.e.f = parcel.readInt();
        this.e.h = parcel.readString();
        parcel.readBooleanArray(this.e.g);
        this.f = parcel.readInt() == 1;
        this.g = parcel.readInt() == 1 ? true : z;
        this.h = new CorntabUtils.CorntabParam();
        this.h.f18664a = parcel.readInt();
        this.h.b = parcel.readInt();
        this.h.c = parcel.readInt();
        this.h.d = parcel.readInt();
        this.h.e = parcel.readInt();
        this.h.f = parcel.readInt();
        this.h.h = parcel.readString();
        parcel.readBooleanArray(this.h.g);
        this.j = parcel.readString();
        this.k = parcel.readString();
    }

    public PlugTimer() {
        this.i = 0;
        this.f21674a = true;
        this.b = true;
        this.c = false;
        this.d = true;
        this.e = new CorntabUtils.CorntabParam();
        this.f = false;
        this.g = true;
        this.h = new CorntabUtils.CorntabParam();
        this.j = null;
        this.k = "0";
    }

    /* access modifiers changed from: protected */
    public Object clone() {
        try {
            PlugTimer plugTimer = (PlugTimer) super.clone();
            try {
                plugTimer.f21674a = this.f21674a;
                plugTimer.b = this.b;
                plugTimer.c = this.c;
                plugTimer.d = this.d;
                plugTimer.e = (CorntabUtils.CorntabParam) this.e.clone();
                plugTimer.f = this.f;
                plugTimer.g = this.g;
                plugTimer.h = (CorntabUtils.CorntabParam) this.h.clone();
                plugTimer.j = this.j;
                plugTimer.k = this.k;
                return plugTimer;
            } catch (CloneNotSupportedException unused) {
                return plugTimer;
            }
        } catch (CloneNotSupportedException unused2) {
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlugTimer)) {
            return false;
        }
        PlugTimer plugTimer = (PlugTimer) obj;
        if (this.f21674a == plugTimer.f21674a && this.b == plugTimer.b && this.c == plugTimer.c && this.d == plugTimer.d && this.e.equals(plugTimer.e) && this.f == plugTimer.f && this.g == plugTimer.g && this.h.equals(plugTimer.h)) {
            return true;
        }
        return false;
    }

    public static String a(Context context, CorntabUtils.CorntabParam corntabParam, CorntabUtils.CorntabParam corntabParam2, boolean z, boolean z2, int i2, int i3, boolean z3) {
        StringBuilder sb = new StringBuilder();
        Calendar.getInstance();
        if (z3) {
            if (corntabParam.d() == 0 && !a(i2, i3)) {
                sb.append(context.getString(R.string.plug_timer_tomorrow));
                sb.append(" ");
            }
        } else if (corntabParam2.d() == 0) {
            if (z) {
                if (a(corntabParam.c, corntabParam.b)) {
                    if (!a(corntabParam.c, corntabParam.b, i2, i3)) {
                        sb.append(context.getString(R.string.plug_timer_set_nextday));
                        sb.append(" ");
                    }
                } else if (a(corntabParam.c, corntabParam.b, i2, i3)) {
                    sb.append(context.getString(R.string.plug_timer_tomorrow));
                    sb.append(" ");
                } else {
                    sb.append(context.getString(R.string.plug_timer_set_nextday));
                    sb.append(" ");
                }
            } else if (!a(i2, i3)) {
                sb.append(context.getString(R.string.plug_timer_tomorrow));
                sb.append(" ");
            }
        } else if (z) {
            if (!a(corntabParam.c, corntabParam.b, i2, i3)) {
                sb.append(context.getString(R.string.plug_timer_set_nextday));
                sb.append(" ");
            }
        } else if (!a(i2, i3)) {
            sb.append(context.getString(R.string.plug_timer_tomorrow));
            sb.append(" ");
        }
        sb.append(a(i2));
        sb.append(":");
        sb.append(a(i3));
        return sb.toString();
    }

    public static boolean a(int i2, int i3) {
        Calendar instance = Calendar.getInstance();
        return (i2 * 60) + i3 > (instance.get(11) * 60) + instance.get(12);
    }

    public static String a(int i2) {
        if (i2 >= 10) {
            return Integer.toString(i2);
        }
        return "0" + i2;
    }
}
