package com.xiaomi.qrcode;

import android.util.Log;
import java.util.Arrays;
import java.util.Comparator;
import miuipub.reflect.Field;

public class QrCodeParser {

    /* renamed from: a  reason: collision with root package name */
    private static final Comparator<? super ValueSetter> f12974a = new Comparator<ValueSetter>() {
        /* renamed from: a */
        public int compare(ValueSetter valueSetter, ValueSetter valueSetter2) {
            return valueSetter.c - valueSetter2.c;
        }
    };
    private static final String b = "QrCodeParser";
    private final String c;
    private QrCodeType d;
    private final ValueSetter e;
    private final ValueSetter f;
    private final ValueSetter g;
    private final ValueSetter h;
    private final ValueSetter i;

    public QrCodeParser(String str) {
        this(str, (String) null);
    }

    public QrCodeParser(String str, String str2) {
        this.e = new ValueSetter("$D:");
        this.f = new ValueSetter("$M:");
        this.g = new ValueSetter("$S:");
        this.h = new ValueSetter("$A:");
        this.i = new ValueSetter("$I:");
        this.f.a(str2);
        a(str);
        this.c = str;
    }

    public String a() {
        return this.e.b;
    }

    public QrCodeType b() {
        return this.d;
    }

    public String c() {
        return this.f.b;
    }

    public String d() {
        return this.g.b;
    }

    public String e() {
        return this.h.b;
    }

    public String f() {
        return this.i.b;
    }

    private void a(String str) {
        if (str == null) {
            return;
        }
        if (!str.startsWith("G") || str.length() <= 2) {
            Log.i(b, " no recongise start G");
            return;
        }
        String substring = str.substring("G".length());
        String[] split = substring.split("%Z");
        if (split.length == 2) {
            a(split[0], this.f, this.e, this.g);
            a(split[1], this.h, this.i);
            if (this.h.b == null || this.i.b == null || this.e.b == null || this.f.b == null) {
                Log.i(b, "must not by no $A:" + this.h.b + " $I:" + this.i.b + " $D:" + this.e.b + " $M:" + this.f.b);
                return;
            }
            this.d = QrCodeType.ZIGBEE_30;
            return;
        }
        String[] split2 = substring.split("%N");
        if (split2.length == 2) {
            a(split2[0], this.f, this.e, this.g);
            a(split2[1], this.h, this.i);
            if (this.h.b == null || this.i.b == null || this.e.b == null || this.f.b == null) {
                Log.i(b, "must not by no $A:" + this.h.b + " $I:" + this.i.b + " $D:" + this.e.b + " $M:" + this.f.b);
                return;
            }
            this.d = QrCodeType.NBIOT;
            return;
        }
        String[] split3 = substring.split("%W");
        if (split3.length == 2) {
            a(split3[0], this.f, this.e, this.g);
            a(split3[1], this.h, this.i);
            if (this.h.b == null || this.i.b == null || this.e.b == null || this.f.b == null) {
                Log.i(b, "must not by no $A:" + this.h.b + " $I:" + this.i.b + " $D:" + this.e.b + " $M:" + this.f.b);
                return;
            }
            this.d = QrCodeType.WIFI;
            return;
        }
        Log.i(b, " no recongise any %Z %N %W");
    }

    private void a(String str, ValueSetter... valueSetterArr) {
        if (str != null) {
            int i2 = 0;
            for (ValueSetter valueSetter : valueSetterArr) {
                valueSetter.a(str.indexOf(valueSetter.f12975a));
            }
            Arrays.sort(valueSetterArr, f12974a);
            int length = valueSetterArr.length;
            while (i2 < length) {
                ValueSetter valueSetter2 = valueSetterArr[i2];
                valueSetter2.a(str.substring(valueSetter2.c + valueSetter2.f12975a.length(), i2 == length + -1 ? str.length() : valueSetterArr[i2 + 1].c));
                i2++;
            }
        }
    }

    public String toString() {
        return this.c;
    }

    public enum QrCodeType {
        WIFI("W"),
        NBIOT("N"),
        ZIGBEE_30(Field.f3009a);
        
        public final String type;

        private QrCodeType(String str) {
            this.type = str;
        }
    }

    public static class ValueSetter {

        /* renamed from: a  reason: collision with root package name */
        final String f12975a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public int c;

        public ValueSetter(String str) {
            this.f12975a = str;
        }

        public ValueSetter a(int i) {
            this.c = i;
            return this;
        }

        public void a(String str) {
            this.b = str;
        }
    }
}
