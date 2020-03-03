package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Strings;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import miuipub.reflect.Field;

public class ASN1GeneralizedTime extends ASN1Primitive {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f14403a;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static ASN1GeneralizedTime a(Object obj) {
        if (obj == null || (obj instanceof ASN1GeneralizedTime)) {
            return (ASN1GeneralizedTime) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1GeneralizedTime) b((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1GeneralizedTime a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof ASN1GeneralizedTime)) {
            return a((Object) g);
        }
        return new ASN1GeneralizedTime(((ASN1OctetString) g).d());
    }

    public ASN1GeneralizedTime(String str) {
        this.f14403a = Strings.d(str);
        try {
            d();
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid date string: " + e.getMessage());
        }
    }

    public ASN1GeneralizedTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, Field.f3009a));
        this.f14403a = Strings.d(simpleDateFormat.format(date));
    }

    public ASN1GeneralizedTime(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, Field.f3009a));
        this.f14403a = Strings.d(simpleDateFormat.format(date));
    }

    ASN1GeneralizedTime(byte[] bArr) {
        this.f14403a = bArr;
    }

    public String b() {
        return Strings.b(this.f14403a);
    }

    public String c() {
        String b = Strings.b(this.f14403a);
        if (b.charAt(b.length() - 1) == 'Z') {
            return b.substring(0, b.length() - 1) + "GMT+00:00";
        }
        int length = b.length() - 5;
        char charAt = b.charAt(length);
        if (charAt == '-' || charAt == '+') {
            StringBuilder sb = new StringBuilder();
            sb.append(b.substring(0, length));
            sb.append("GMT");
            int i = length + 3;
            sb.append(b.substring(length, i));
            sb.append(":");
            sb.append(b.substring(i));
            return sb.toString();
        }
        int length2 = b.length() - 3;
        char charAt2 = b.charAt(length2);
        if (charAt2 == '-' || charAt2 == '+') {
            return b.substring(0, length2) + "GMT" + b.substring(length2) + ":00";
        }
        return b + f();
    }

    private String f() {
        String str = "+";
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        if (rawOffset < 0) {
            str = "-";
            rawOffset = -rawOffset;
        }
        int i = rawOffset / 3600000;
        int i2 = (rawOffset - (((i * 60) * 60) * 1000)) / 60000;
        try {
            if (timeZone.useDaylightTime() && timeZone.inDaylightTime(d())) {
                i += str.equals("+") ? 1 : -1;
            }
        } catch (ParseException unused) {
        }
        return "GMT" + str + a(i) + ":" + a(i2);
    }

    private String a(int i) {
        if (i >= 10) {
            return Integer.toString(i);
        }
        return "0" + i;
    }

    public Date d() throws ParseException {
        SimpleDateFormat simpleDateFormat;
        SimpleDateFormat simpleDateFormat2;
        SimpleDateFormat simpleDateFormat3;
        String b = Strings.b(this.f14403a);
        if (b.endsWith(Field.f3009a)) {
            if (g()) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSS'Z'");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, Field.f3009a));
        } else if (b.indexOf(45) > 0 || b.indexOf(43) > 0) {
            b = c();
            if (g()) {
                simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss.SSSz");
            } else {
                simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmssz");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, Field.f3009a));
        } else {
            if (g()) {
                simpleDateFormat3 = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
            } else {
                simpleDateFormat3 = new SimpleDateFormat("yyyyMMddHHmmss");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, TimeZone.getDefault().getID()));
        }
        if (g()) {
            String substring = b.substring(14);
            int i = 1;
            while (i < substring.length() && '0' <= (r7 = substring.charAt(i)) && r7 <= '9') {
                i++;
            }
            int i2 = i - 1;
            if (i2 > 3) {
                b = b.substring(0, 14) + (substring.substring(0, 4) + substring.substring(i));
            } else if (i2 == 1) {
                b = b.substring(0, 14) + (substring.substring(0, i) + "00" + substring.substring(i));
            } else if (i2 == 2) {
                b = b.substring(0, 14) + (substring.substring(0, i) + "0" + substring.substring(i));
            }
        }
        return simpleDateFormat.parse(b);
    }

    private boolean g() {
        for (int i = 0; i != this.f14403a.length; i++) {
            if (this.f14403a[i] == 46 && i == 14) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        int length = this.f14403a.length;
        return StreamUtil.a(length) + 1 + length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(24, this.f14403a);
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1GeneralizedTime)) {
            return false;
        }
        return Arrays.a(this.f14403a, ((ASN1GeneralizedTime) aSN1Primitive).f14403a);
    }

    public int hashCode() {
        return Arrays.a(this.f14403a);
    }
}
