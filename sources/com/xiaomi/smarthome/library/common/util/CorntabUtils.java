package com.xiaomi.smarthome.library.common.util;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.widget.TimePicker;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.scene.timer.CommonTimer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class CorntabUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final long f18663a = 86400000;
    public static final String b = " ";
    public static final int c = 0;
    public static final int d = 0;
    public static final int e = 1;
    public static final int f = 127;
    public static final int g = 2;
    public static final int h = 62;
    public static final int i = 3;
    public static final int j = 65;
    public static final int k = 4;
    public static final int l = 5;
    public static final int m = 127;
    public static final int n = 6;
    public static final int o = 127;
    public static final String p = "cn_workday";
    public static final String q = "cn_freeday";

    public static class CorntabParam implements Parcelable, Cloneable {
        public static final Parcelable.Creator<CorntabParam> CREATOR = new Parcelable.Creator<CorntabParam>() {
            /* renamed from: a */
            public CorntabParam createFromParcel(Parcel parcel) {
                return new CorntabParam(parcel);
            }

            /* renamed from: a */
            public CorntabParam[] newArray(int i) {
                return new CorntabParam[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public int f18664a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public boolean[] g = new boolean[7];
        public String h = "";

        public int describeContents() {
            return 0;
        }

        public CorntabParam() {
            Calendar instance = Calendar.getInstance();
            this.d = instance.get(5);
            this.e = instance.get(2) + 1;
            this.c = instance.get(11);
            this.b = instance.get(12);
            this.f18664a = instance.get(13);
            this.f = instance.get(1);
            Arrays.fill(this.g, false);
            this.h = "";
        }

        public String toString() {
            return "CorntabParam----" + this.f + "year," + this.e + "month," + this.d + "day," + this.c + "hour," + this.b + "minute," + this.f18664a + "second";
        }

        public boolean a() {
            return a(new CorntabParam()) < 0;
        }

        public int a(CorntabParam corntabParam) {
            return d(corntabParam);
        }

        public int b(CorntabParam corntabParam) {
            if (d() == 0) {
                return d(corntabParam);
            }
            return c(corntabParam);
        }

        private int c(CorntabParam corntabParam) {
            if (this.c > corntabParam.c) {
                return 1;
            }
            if (this.c < corntabParam.c) {
                return -1;
            }
            if (this.b > corntabParam.b) {
                return 1;
            }
            if (this.b < corntabParam.b) {
                return -1;
            }
            if (this.f18664a > corntabParam.f18664a) {
                return 1;
            }
            if (this.f18664a < corntabParam.f18664a) {
                return -1;
            }
            return 0;
        }

        private int d(CorntabParam corntabParam) {
            if (this.f > corntabParam.f) {
                return 1;
            }
            if (this.f < corntabParam.f) {
                return -1;
            }
            if (this.e > corntabParam.e) {
                return 1;
            }
            if (this.e < corntabParam.e) {
                return -1;
            }
            if (this.d > corntabParam.d) {
                return 1;
            }
            if (this.d < corntabParam.d) {
                return -1;
            }
            if (this.c > corntabParam.c) {
                return 1;
            }
            if (this.c < corntabParam.c) {
                return -1;
            }
            if (this.b > corntabParam.b) {
                return 1;
            }
            if (this.b < corntabParam.b) {
                return -1;
            }
            if (this.f18664a > corntabParam.f18664a) {
                return 1;
            }
            if (this.f18664a < corntabParam.f18664a) {
                return -1;
            }
            return 0;
        }

        public String b() {
            return this.f + "-" + CommonTimer.a(this.e) + "-" + CommonTimer.a(this.d);
        }

        public String a(Activity activity) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(LanguageUtil.a(ServerCompact.c(activity.getApplicationContext()), "-"));
            Calendar instance = Calendar.getInstance();
            instance.set(this.f, this.e - 1, this.d);
            return simpleDateFormat.format(instance.getTime());
        }

        protected CorntabParam(Parcel parcel) {
            this.f18664a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.createBooleanArray();
            this.h = parcel.readString();
        }

        public Object clone() {
            try {
                CorntabParam corntabParam = (CorntabParam) super.clone();
                try {
                    corntabParam.f18664a = this.f18664a;
                    corntabParam.b = this.b;
                    corntabParam.c = this.c;
                    corntabParam.d = this.d;
                    corntabParam.e = this.e;
                    corntabParam.f = this.f;
                    corntabParam.g = new boolean[7];
                    System.arraycopy(this.g, 0, corntabParam.g, 0, this.g.length);
                    corntabParam.h = this.h;
                    return corntabParam;
                } catch (CloneNotSupportedException unused) {
                    return corntabParam;
                }
            } catch (CloneNotSupportedException unused2) {
                return null;
            }
        }

        public String c() {
            if (this.b < 10) {
                return "0" + this.b;
            }
            return "" + this.b;
        }

        public int d() {
            if (this.d != -1) {
                return 0;
            }
            int i = 0;
            for (int i2 = 0; i2 < this.g.length; i2++) {
                if (this.g[i2]) {
                    i |= 1 << i2;
                }
            }
            if (i == 62) {
                return 2;
            }
            if (i == 65) {
                return 3;
            }
            if (i != 127) {
                return 4;
            }
            if (CorntabUtils.q.equals(this.h)) {
                return 6;
            }
            if (CorntabUtils.p.equals(this.h)) {
                return 5;
            }
            return 1;
        }

        public void a(int i) {
            if (i == 0) {
                Calendar instance = Calendar.getInstance();
                this.d = instance.get(5);
                this.e = instance.get(2) + 1;
                Arrays.fill(this.g, false);
                return;
            }
            this.d = -1;
            this.e = -1;
            for (int i2 = 0; i2 < this.g.length; i2++) {
                this.g[i2] = ((1 << i2) & i) > 0;
            }
        }

        public void a(int i, boolean z) {
            this.d = -1;
            this.e = -1;
            this.g[i] = z;
        }

        public void b(int i) {
            if (this.d == -1 && this.e == -1) {
                int length = i % this.g.length;
                int i2 = 0;
                int i3 = 0;
                for (int length2 = this.g.length - 1; i3 < length2; length2--) {
                    boolean z = this.g[i3];
                    this.g[i3] = this.g[length2];
                    this.g[length2] = z;
                    i3++;
                }
                for (int i4 = length - 1; i2 < i4; i4--) {
                    boolean z2 = this.g[i2];
                    this.g[i2] = this.g[i4];
                    this.g[i4] = z2;
                    i2++;
                }
                for (int length3 = this.g.length - 1; length < length3; length3--) {
                    boolean z3 = this.g[length];
                    this.g[length] = this.g[length3];
                    this.g[length3] = z3;
                    length++;
                }
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof CorntabParam)) {
                return false;
            }
            CorntabParam corntabParam = (CorntabParam) obj;
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.g.length; i3++) {
                if (this.g[i3]) {
                    i |= 1 << i3;
                }
                if (corntabParam.g[i3]) {
                    i2 |= 1 << i3;
                }
            }
            if (this.b == corntabParam.b && this.c == corntabParam.c && this.d == corntabParam.d && this.e == corntabParam.e && this.f == corntabParam.f && this.f18664a == corntabParam.f18664a && i == i2 && this.h.equals(corntabParam.h)) {
                return true;
            }
            return false;
        }

        public boolean a(Object obj) {
            if (!(obj instanceof CorntabParam)) {
                return false;
            }
            CorntabParam corntabParam = (CorntabParam) obj;
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.g.length; i3++) {
                if (this.g[i3]) {
                    i |= 1 << i3;
                }
                if (corntabParam.g[i3]) {
                    i2 |= 1 << i3;
                }
            }
            if (this.b == corntabParam.b && this.c == corntabParam.c && this.d == corntabParam.d && this.e == corntabParam.e && this.f == corntabParam.f && i == i2 && this.h.equals(corntabParam.h)) {
                return true;
            }
            return false;
        }

        public String a(Context context) {
            StringBuilder sb = new StringBuilder();
            Calendar instance = Calendar.getInstance();
            Calendar instance2 = Calendar.getInstance();
            instance2.set(instance.get(1), this.e - 1, this.d);
            if (!DateUtils.a(instance, instance2)) {
                if (DateUtils.a(instance2, 1)) {
                    sb.append(context.getString(R.string.plug_timer_tomorrow));
                    sb.append(" ");
                } else if (DateUtils.b(instance2, 1)) {
                    sb.append(context.getString(R.string.plug_timer_yesterday));
                    sb.append(" ");
                }
            }
            sb.append(CommonTimer.a(this.c, this.b));
            return sb.toString();
        }

        public String b(Context context) {
            StringBuilder sb = new StringBuilder();
            Calendar instance = Calendar.getInstance();
            Calendar instance2 = Calendar.getInstance();
            instance2.set(instance.get(1), this.e - 1, this.d);
            if (!DateUtils.a(instance, instance2)) {
                if (DateUtils.a(instance2, 1)) {
                    sb.append(context.getString(R.string.plug_timer_tomorrow));
                } else if (DateUtils.a(instance2, 2)) {
                    sb.append(context.getString(R.string.plug_timer_after_tommorrow));
                }
            }
            return sb.toString();
        }

        public String c(Context context) {
            int i = 0;
            for (int i2 = 0; i2 < this.g.length; i2++) {
                if (this.g[i2]) {
                    i |= 1 << i2;
                }
            }
            if (i == 0) {
                Calendar instance = Calendar.getInstance();
                Calendar instance2 = Calendar.getInstance();
                instance2.set(instance.get(1), this.e - 1, this.d);
                if (DateUtils.a(instance, instance2)) {
                    return context.getString(R.string.plug_timer_today);
                }
                if (DateUtils.a(instance2, 1)) {
                    return context.getString(R.string.plug_timer_tomorrow);
                }
                if (DateUtils.b(instance2, 1)) {
                    return context.getString(R.string.plug_timer_yesterday);
                }
                return context.getString(R.string.plug_timer_month_day, new Object[]{this.e + "", this.d + ""});
            } else if (i == 62) {
                return context.getString(R.string.plug_timer_workday);
            } else {
                if (i == 65) {
                    return context.getString(R.string.plug_timer_weekend);
                }
                if (i != 127) {
                    String[] stringArray = context.getResources().getStringArray(R.array.weekday_short);
                    StringBuilder sb = new StringBuilder();
                    if (stringArray.length != this.g.length) {
                        LogUtil.b("CorntabUtils", "dayList.length!=weeks.length");
                    }
                    int i3 = 0;
                    while (i3 < stringArray.length && i3 < this.g.length) {
                        if (this.g[i3]) {
                            sb.append(stringArray[i3]);
                            sb.append(", ");
                        }
                        i3++;
                    }
                    return sb.substring(0, sb.length() - 2);
                } else if (CorntabUtils.p.equals(this.h)) {
                    return context.getString(R.string.plug_timer_statutory_workingday);
                } else {
                    if (CorntabUtils.q.equals(this.h)) {
                        return context.getString(R.string.plug_timer_statutory_holidays);
                    }
                    return context.getString(R.string.plug_timer_everyday);
                }
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f18664a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeBooleanArray(this.g);
            parcel.writeString(this.h);
        }
    }

    public static String a(CorntabParam corntabParam, Context context) {
        boolean z;
        boolean z2;
        StringBuilder sb = new StringBuilder();
        sb.append(context.getString(R.string.intelligent_plug_everytime));
        int i2 = 0;
        while (true) {
            if (i2 >= corntabParam.g.length) {
                z = true;
                break;
            } else if (!corntabParam.g[i2]) {
                z = false;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            return context.getString(R.string.intelligent_plug_everyday);
        }
        int i3 = 0;
        while (true) {
            if (i3 >= corntabParam.g.length) {
                z2 = true;
                break;
            } else if ((!corntabParam.g[i3] || !(i3 == 0 || i3 == 6)) && (corntabParam.g[i3] || i3 > 5 || i3 < 1)) {
                i3++;
            }
        }
        z2 = false;
        if (z2) {
            return context.getString(R.string.intelligent_plug_workday);
        }
        boolean z3 = false;
        for (int i4 = 0; i4 < corntabParam.g.length; i4++) {
            if (corntabParam.g[i4]) {
                if (z3) {
                    sb.append(context.getString(R.string.intelligent_plug_dunhao));
                }
                switch (i4) {
                    case 0:
                        sb.append(context.getString(R.string.intelligent_plug_seven));
                        break;
                    case 1:
                        sb.append(context.getString(R.string.intelligent_plug_one));
                        break;
                    case 2:
                        sb.append(context.getString(R.string.intelligent_plug_two));
                        break;
                    case 3:
                        sb.append(context.getString(R.string.intelligent_plug_three));
                        break;
                    case 4:
                        sb.append(context.getString(R.string.intelligent_plug_four));
                        break;
                    case 5:
                        sb.append(context.getString(R.string.intelligent_plug_five));
                        break;
                    case 6:
                        sb.append(context.getString(R.string.intelligent_plug_six));
                        break;
                }
                z3 = true;
            }
        }
        return sb.toString();
    }

    public static CorntabParam a(String str) {
        String[] split;
        int i2;
        if (TextUtils.isEmpty(str) || (split = str.split(" ")) == null) {
            return null;
        }
        if (split.length == 6) {
            return b(str);
        }
        if (split.length == 7) {
            return c(str);
        }
        if (split.length != 5) {
            return null;
        }
        CorntabParam corntabParam = new CorntabParam();
        for (int i3 = 0; i3 < split.length; i3++) {
            try {
                i2 = Integer.valueOf(split[i3]).intValue();
            } catch (NumberFormatException unused) {
                i2 = -1;
            }
            switch (i3) {
                case 0:
                    corntabParam.b = i2;
                    break;
                case 1:
                    corntabParam.c = i2;
                    break;
                case 2:
                    corntabParam.d = i2;
                    break;
                case 3:
                    corntabParam.e = i2;
                    break;
                case 4:
                    boolean[] zArr = new boolean[7];
                    if (split[i3].equals("*") && corntabParam.d == -1 && corntabParam.e == -1) {
                        Arrays.fill(zArr, true);
                    } else {
                        Arrays.fill(zArr, false);
                        try {
                            String[] split2 = split[i3].split(",");
                            if (split2 != null) {
                                for (String trim : split2) {
                                    zArr[Integer.valueOf(trim.trim()).intValue()] = true;
                                }
                            }
                        } catch (NumberFormatException unused2) {
                        }
                    }
                    corntabParam.g = zArr;
                    break;
            }
        }
        return corntabParam;
    }

    public static CorntabParam b(String str) {
        int i2;
        String[] split = str.split(" ");
        CorntabParam corntabParam = new CorntabParam();
        corntabParam.f18664a = 0;
        corntabParam.f = Calendar.getInstance().get(1);
        for (int i3 = 0; i3 < split.length; i3++) {
            try {
                i2 = Integer.valueOf(split[i3]).intValue();
            } catch (NumberFormatException unused) {
                i2 = -1;
            }
            switch (i3) {
                case 0:
                    corntabParam.b = i2;
                    break;
                case 1:
                    corntabParam.c = i2;
                    break;
                case 2:
                    corntabParam.d = i2;
                    break;
                case 3:
                    corntabParam.e = i2;
                    break;
                case 4:
                    boolean[] zArr = new boolean[7];
                    if (split[i3].equals("*") && corntabParam.d == -1 && corntabParam.e == -1) {
                        Arrays.fill(zArr, true);
                    } else {
                        Arrays.fill(zArr, false);
                        try {
                            String[] split2 = split[i3].split(",");
                            if (split2 != null) {
                                for (String trim : split2) {
                                    zArr[Integer.valueOf(trim.trim()).intValue()] = true;
                                }
                            }
                        } catch (NumberFormatException unused2) {
                        }
                    }
                    corntabParam.g = zArr;
                    break;
                case 5:
                    corntabParam.f = i2;
                    break;
            }
        }
        return corntabParam;
    }

    public static CorntabParam c(String str) {
        int i2;
        String[] split = str.split(" ");
        CorntabParam corntabParam = new CorntabParam();
        corntabParam.f18664a = 0;
        corntabParam.f = Calendar.getInstance().get(1);
        for (int i3 = 0; i3 < split.length; i3++) {
            try {
                i2 = Integer.valueOf(split[i3]).intValue();
            } catch (NumberFormatException unused) {
                i2 = -1;
            }
            switch (i3) {
                case 0:
                    corntabParam.b = i2;
                    break;
                case 1:
                    corntabParam.b = i2;
                    break;
                case 2:
                    corntabParam.c = i2;
                    break;
                case 3:
                    corntabParam.d = i2;
                    break;
                case 4:
                    corntabParam.e = i2;
                    break;
                case 5:
                    boolean[] zArr = new boolean[7];
                    if (split[i3].equals("*") && corntabParam.d == -1 && corntabParam.e == -1) {
                        Arrays.fill(zArr, true);
                    } else {
                        Arrays.fill(zArr, false);
                        try {
                            String[] split2 = split[i3].split(",");
                            if (split2 != null) {
                                for (String trim : split2) {
                                    zArr[Integer.valueOf(trim.trim()).intValue()] = true;
                                }
                            }
                        } catch (NumberFormatException unused2) {
                        }
                    }
                    corntabParam.g = zArr;
                    break;
                case 6:
                    corntabParam.f = i2;
                    break;
            }
        }
        return corntabParam;
    }

    public static CorntabParam d(String str) {
        String[] split = str.split(" ");
        if (split == null) {
            return null;
        }
        CorntabParam corntabParam = new CorntabParam();
        if (split.length < 7) {
            split = (corntabParam.f18664a + " " + str + " " + corntabParam.f).split(" ");
        }
        for (int i2 = 0; i2 < split.length; i2++) {
            int i3 = -1;
            try {
                i3 = Integer.valueOf(split[i2]).intValue();
            } catch (NumberFormatException unused) {
            }
            switch (i2) {
                case 0:
                    corntabParam.f18664a = i3;
                    break;
                case 1:
                    corntabParam.b = i3;
                    break;
                case 2:
                    corntabParam.c = i3;
                    break;
                case 3:
                    corntabParam.d = i3;
                    break;
                case 4:
                    corntabParam.e = i3;
                    break;
                case 5:
                    boolean[] zArr = new boolean[7];
                    Arrays.fill(zArr, false);
                    try {
                        String[] split2 = split[i2].split(",");
                        if (split2 != null) {
                            for (String trim : split2) {
                                zArr[Integer.valueOf(trim.trim()).intValue()] = true;
                            }
                        }
                    } catch (NumberFormatException unused2) {
                    }
                    corntabParam.g = zArr;
                    break;
                case 6:
                    corntabParam.f = i3;
                    break;
            }
        }
        return corntabParam;
    }

    public static String a(CorntabParam corntabParam) {
        StringBuilder sb = new StringBuilder();
        sb.append(corntabParam.f18664a);
        sb.append(" ");
        sb.append(corntabParam.b);
        sb.append(" ");
        sb.append(corntabParam.c);
        sb.append(" ");
        if (corntabParam.d == -1 || corntabParam.e == -1) {
            sb.append("* ");
            sb.append("* ");
        } else {
            sb.append(corntabParam.d);
            sb.append(" ");
            sb.append(corntabParam.e);
            sb.append(" ");
        }
        if (corntabParam.d == -1 || corntabParam.e == -1) {
            boolean z = false;
            for (int i2 = 0; i2 < 7; i2++) {
                if (corntabParam.g[i2]) {
                    if (z) {
                        sb.append(",");
                    }
                    sb.append(i2);
                    z = true;
                }
            }
        } else {
            sb.append("*");
        }
        if (corntabParam.d() == 0) {
            if (corntabParam.f <= Calendar.getInstance().get(1)) {
                corntabParam.f = Calendar.getInstance().get(1);
            }
            sb.append(" ");
            sb.append(corntabParam.f);
        } else {
            sb.append(" ");
            sb.append("*");
        }
        return sb.toString();
    }

    public static String b(CorntabParam corntabParam) {
        StringBuilder sb = new StringBuilder();
        sb.append(corntabParam.b);
        sb.append(" ");
        sb.append(corntabParam.c);
        sb.append(" ");
        if (corntabParam.d == -1 || corntabParam.e == -1) {
            sb.append("* ");
            sb.append("* ");
        } else {
            sb.append(corntabParam.d);
            sb.append(" ");
            sb.append(corntabParam.e);
            sb.append(" ");
        }
        if (corntabParam.d == -1 || corntabParam.e == -1) {
            boolean z = false;
            for (int i2 = 0; i2 < 7; i2++) {
                if (corntabParam.g[i2]) {
                    if (z) {
                        sb.append(",");
                    }
                    sb.append(i2);
                    z = true;
                }
            }
        } else {
            sb.append("*");
        }
        return sb.toString();
    }

    public static String a(int i2, int i3, boolean[] zArr) {
        Calendar instance = Calendar.getInstance();
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        StringBuilder sb = new StringBuilder();
        sb.append(i5);
        sb.append(" ");
        sb.append(i4);
        sb.append(" ");
        if (zArr == null || zArr.length < 7) {
            if (i2 < i4 || (i2 == i4 && i3 < i5)) {
                instance.setTimeInMillis(instance.getTimeInMillis() + 86400000);
            }
            int i6 = instance.get(5);
            int i7 = instance.get(2);
            sb.append(i6);
            sb.append(" ");
            sb.append(i7);
            sb.append(" ");
        } else {
            sb.append("* ");
            sb.append("* ");
        }
        if (zArr != null && zArr.length >= 7) {
            boolean z = false;
            for (int i8 = 0; i8 < 7; i8++) {
                if (zArr[i8]) {
                    if (z) {
                        sb.append(". ");
                    }
                    sb.append(i8);
                    z = true;
                }
            }
        }
        return sb.toString();
    }

    public static void a(int i2, int i3, CorntabParam corntabParam) {
        Calendar instance = Calendar.getInstance();
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        if (corntabParam.d == -1) {
            corntabParam.c = i2;
            corntabParam.b = i3;
            return;
        }
        if (i2 < i4 || (i2 == i4 && i3 < i5)) {
            instance.setTimeInMillis(instance.getTimeInMillis() + 86400000);
        }
        corntabParam.d = instance.get(5);
        corntabParam.e = instance.get(2) + 1;
        corntabParam.c = i2;
        corntabParam.b = i3;
    }

    public static boolean c(CorntabParam corntabParam) {
        if (corntabParam.d == -1) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        int i2 = instance.get(11);
        int i3 = instance.get(12);
        int i4 = instance.get(5);
        int i5 = instance.get(2) + 1;
        if (i5 > corntabParam.e) {
            return true;
        }
        if (i5 != corntabParam.e) {
            return false;
        }
        if (i4 > corntabParam.d) {
            return true;
        }
        if (i4 != corntabParam.d) {
            return false;
        }
        if (i2 > corntabParam.c) {
            return true;
        }
        if (i2 != corntabParam.c || i3 <= corntabParam.b) {
            return false;
        }
        return true;
    }

    public static CorntabParam a(TimeZone timeZone, CorntabParam corntabParam) {
        return b(timeZone, new GregorianCalendar().getTimeZone(), corntabParam);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.smarthome.library.common.util.CorntabUtils.CorntabParam a(java.util.TimeZone r8, java.util.TimeZone r9, com.xiaomi.smarthome.library.common.util.CorntabUtils.CorntabParam r10) {
        /*
            long r0 = java.lang.System.currentTimeMillis()
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MINUTES
            int r8 = r8.getOffset(r0)
            long r3 = (long) r8
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r2 = r2.convert(r3, r8)
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MINUTES
            int r9 = r9.getOffset(r0)
            long r0 = (long) r9
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r8 = r8.convert(r0, r9)
            long r2 = r2 - r8
            int r8 = (int) r2
            boolean[] r9 = r10.g
            int r0 = r9.length
            r1 = 0
            r2 = 0
        L_0x0025:
            r3 = 1
            if (r2 >= r0) goto L_0x0031
            boolean r4 = r9[r2]
            if (r4 == 0) goto L_0x002e
            r9 = 0
            goto L_0x0032
        L_0x002e:
            int r2 = r2 + 1
            goto L_0x0025
        L_0x0031:
            r9 = 1
        L_0x0032:
            java.util.Calendar r0 = java.util.Calendar.getInstance()
            int r2 = r10.e
            int r2 = r2 - r3
            r4 = 2
            r0.set(r4, r2)
            int r2 = r10.d
            r5 = 5
            r0.set(r5, r2)
            int r2 = r10.c
            r6 = 11
            r0.set(r6, r2)
            int r2 = r10.b
            r7 = 12
            r0.set(r7, r2)
            int r2 = r8 / 60
            r0.add(r6, r2)
            int r2 = r8 % 60
            r0.add(r7, r2)
            java.lang.Object r2 = r10.clone()
            com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r2 = (com.xiaomi.smarthome.library.common.util.CorntabUtils.CorntabParam) r2
            if (r9 == 0) goto L_0x007d
            int r8 = r0.get(r4)
            int r8 = r8 + r3
            r2.e = r8
            int r8 = r0.get(r5)
            r2.d = r8
            int r8 = r0.get(r6)
            r2.c = r8
            int r8 = r0.get(r7)
            r2.b = r8
            goto L_0x00bf
        L_0x007d:
            int r9 = r10.c
            int r9 = r9 * 60
            int r3 = r10.b
            int r9 = r9 + r3
            int r9 = r9 + r8
            r8 = 7
            if (r9 >= 0) goto L_0x009e
            boolean[] r9 = new boolean[r8]
        L_0x008a:
            boolean[] r3 = r10.g
            int r3 = r3.length
            if (r1 >= r3) goto L_0x009b
            int r3 = r1 + 6
            int r3 = r3 % r8
            boolean[] r4 = r10.g
            boolean r4 = r4[r1]
            r9[r3] = r4
            int r1 = r1 + 1
            goto L_0x008a
        L_0x009b:
            r2.g = r9
            goto L_0x00bf
        L_0x009e:
            r3 = 1440(0x5a0, float:2.018E-42)
            if (r9 < r3) goto L_0x00b8
            boolean[] r8 = new boolean[r8]
        L_0x00a4:
            boolean[] r9 = r10.g
            int r9 = r9.length
            if (r1 >= r9) goto L_0x00b5
            int r9 = r1 + 1
            int r3 = r9 % 7
            boolean[] r4 = r10.g
            boolean r1 = r4[r1]
            r8[r3] = r1
            r1 = r9
            goto L_0x00a4
        L_0x00b5:
            r2.g = r8
            goto L_0x00bf
        L_0x00b8:
            java.lang.Object r8 = r10.clone()
            r2 = r8
            com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r2 = (com.xiaomi.smarthome.library.common.util.CorntabUtils.CorntabParam) r2
        L_0x00bf:
            int r8 = r0.get(r6)
            r2.c = r8
            int r8 = r0.get(r7)
            r2.b = r8
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.CorntabUtils.a(java.util.TimeZone, java.util.TimeZone, com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam):com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.smarthome.library.common.util.CorntabUtils.CorntabParam b(java.util.TimeZone r8, java.util.TimeZone r9, com.xiaomi.smarthome.library.common.util.CorntabUtils.CorntabParam r10) {
        /*
            long r0 = java.lang.System.currentTimeMillis()
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MINUTES
            int r8 = r8.getOffset(r0)
            long r3 = (long) r8
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r2 = r2.convert(r3, r8)
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MINUTES
            int r9 = r9.getOffset(r0)
            long r0 = (long) r9
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r8 = r8.convert(r0, r9)
            long r2 = r2 - r8
            int r8 = (int) r2
            boolean[] r9 = r10.g
            int r0 = r9.length
            r1 = 0
            r2 = 0
        L_0x0025:
            r3 = 1
            if (r2 >= r0) goto L_0x0031
            boolean r4 = r9[r2]
            if (r4 == 0) goto L_0x002e
            r9 = 0
            goto L_0x0032
        L_0x002e:
            int r2 = r2 + 1
            goto L_0x0025
        L_0x0031:
            r9 = 1
        L_0x0032:
            java.util.Calendar r0 = java.util.Calendar.getInstance()
            int r2 = r10.f
            r0.set(r3, r2)
            int r2 = r10.e
            int r2 = r2 - r3
            r4 = 2
            r0.set(r4, r2)
            int r2 = r10.d
            r5 = 5
            r0.set(r5, r2)
            int r2 = r10.c
            r6 = 11
            r0.set(r6, r2)
            int r2 = r10.b
            r7 = 12
            r0.set(r7, r2)
            int r2 = r8 / 60
            r0.add(r6, r2)
            int r2 = r8 % 60
            r0.add(r7, r2)
            java.lang.Object r2 = r10.clone()
            com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r2 = (com.xiaomi.smarthome.library.common.util.CorntabUtils.CorntabParam) r2
            if (r9 == 0) goto L_0x0088
            int r8 = r0.get(r3)
            r2.f = r8
            int r8 = r0.get(r4)
            int r8 = r8 + r3
            r2.e = r8
            int r8 = r0.get(r5)
            r2.d = r8
            int r8 = r0.get(r6)
            r2.c = r8
            int r8 = r0.get(r7)
            r2.b = r8
            goto L_0x00ca
        L_0x0088:
            int r9 = r10.c
            int r9 = r9 * 60
            int r3 = r10.b
            int r9 = r9 + r3
            int r9 = r9 + r8
            r8 = 7
            if (r9 >= 0) goto L_0x00a9
            boolean[] r9 = new boolean[r8]
        L_0x0095:
            boolean[] r3 = r10.g
            int r3 = r3.length
            if (r1 >= r3) goto L_0x00a6
            int r3 = r1 + 6
            int r3 = r3 % r8
            boolean[] r4 = r10.g
            boolean r4 = r4[r1]
            r9[r3] = r4
            int r1 = r1 + 1
            goto L_0x0095
        L_0x00a6:
            r2.g = r9
            goto L_0x00ca
        L_0x00a9:
            r3 = 1440(0x5a0, float:2.018E-42)
            if (r9 < r3) goto L_0x00c3
            boolean[] r8 = new boolean[r8]
        L_0x00af:
            boolean[] r9 = r10.g
            int r9 = r9.length
            if (r1 >= r9) goto L_0x00c0
            int r9 = r1 + 1
            int r3 = r9 % 7
            boolean[] r4 = r10.g
            boolean r1 = r4[r1]
            r8[r3] = r1
            r1 = r9
            goto L_0x00af
        L_0x00c0:
            r2.g = r8
            goto L_0x00ca
        L_0x00c3:
            java.lang.Object r8 = r10.clone()
            r2 = r8
            com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r2 = (com.xiaomi.smarthome.library.common.util.CorntabUtils.CorntabParam) r2
        L_0x00ca:
            int r8 = r0.get(r6)
            r2.c = r8
            int r8 = r0.get(r7)
            r2.b = r8
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.CorntabUtils.b(java.util.TimeZone, java.util.TimeZone, com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam):com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam");
    }

    private static int a(int i2, boolean z) {
        if (z) {
            return (i2 + 1) % 7;
        }
        if (i2 == 0) {
            return 6;
        }
        return i2 - 1;
    }

    public static String d(CorntabParam corntabParam) {
        return (corntabParam.e + 1) + "月" + corntabParam.d + "日" + corntabParam.c + "点" + corntabParam.b + "分," + Arrays.toString(corntabParam.g);
    }

    public static int a() {
        TimeZone.getDefault();
        TimeZone.getDefault();
        TimeZone.getAvailableIDs();
        return 0;
    }

    public static CorntabParam a(TimePicker timePicker, boolean[] zArr, boolean z) {
        Calendar instance = Calendar.getInstance();
        CorntabParam corntabParam = new CorntabParam();
        int intValue = timePicker.getCurrentHour().intValue();
        int intValue2 = timePicker.getCurrentMinute().intValue();
        int i2 = instance.get(11);
        int i3 = 0;
        boolean z2 = intValue < i2 || (intValue == i2 && intValue2 <= instance.get(12));
        instance.set(11, intValue);
        instance.set(12, intValue2);
        instance.set(13, 0);
        Calendar instance2 = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
        if (instance2 != null) {
            instance2.setTimeInMillis(instance.getTimeInMillis());
        } else {
            instance2 = instance;
        }
        if (z2) {
            instance2.add(5, 1);
        }
        if (z) {
            corntabParam.c = instance2.get(11);
            corntabParam.b = instance2.get(12);
            for (int i4 = 0; i4 < zArr.length; i4++) {
                corntabParam.a(i4, zArr[i4 % zArr.length]);
            }
            long currentTimeMillis = System.currentTimeMillis();
            int convert = (instance.get(11) * 60) + instance.get(12) + ((int) (TimeUnit.MINUTES.convert((long) TimeZone.getTimeZone("Asia/Shanghai").getOffset(currentTimeMillis), TimeUnit.MILLISECONDS) - TimeUnit.MINUTES.convert((long) TimeZone.getDefault().getOffset(currentTimeMillis), TimeUnit.MILLISECONDS)));
            boolean[] zArr2 = new boolean[7];
            if (convert < 0) {
                while (i3 < corntabParam.g.length) {
                    zArr2[(i3 + 6) % 7] = corntabParam.g[i3];
                    i3++;
                }
                corntabParam.g = zArr2;
            } else if (convert >= 1440) {
                while (i3 < corntabParam.g.length) {
                    int i5 = i3 + 1;
                    zArr2[i5 % 7] = corntabParam.g[i3];
                    i3 = i5;
                }
                corntabParam.g = zArr2;
            }
        } else {
            corntabParam.a(0);
            if (corntabParam.d == -1) {
                corntabParam.c = instance2.get(11);
                corntabParam.b = instance2.get(12);
            } else {
                corntabParam.d = instance2.get(5);
                corntabParam.e = instance2.get(2) + 1;
                corntabParam.c = instance2.get(11);
                corntabParam.b = instance2.get(12);
            }
        }
        return corntabParam;
    }

    public static TimeZone b() {
        return TimeZone.getTimeZone("Asia/Shanghai");
    }
}
