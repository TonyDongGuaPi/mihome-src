package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Strings;
import com.xiaomi.smarthome.framework.api.UserConfig;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import miuipub.reflect.Field;

public class ASN1UTCTime extends ASN1Primitive {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f14416a;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static ASN1UTCTime a(Object obj) {
        if (obj == null || (obj instanceof ASN1UTCTime)) {
            return (ASN1UTCTime) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1UTCTime) b((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1UTCTime a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof ASN1UTCTime)) {
            return a((Object) g);
        }
        return new ASN1UTCTime(((ASN1OctetString) g).d());
    }

    public ASN1UTCTime(String str) {
        this.f14416a = Strings.d(str);
        try {
            b();
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid date string: " + e.getMessage());
        }
    }

    public ASN1UTCTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, Field.f3009a));
        this.f14416a = Strings.d(simpleDateFormat.format(date));
    }

    public ASN1UTCTime(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, Field.f3009a));
        this.f14416a = Strings.d(simpleDateFormat.format(date));
    }

    ASN1UTCTime(byte[] bArr) {
        this.f14416a = bArr;
    }

    public Date b() throws ParseException {
        return new SimpleDateFormat("yyMMddHHmmssz").parse(d());
    }

    public Date c() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, Field.f3009a));
        return simpleDateFormat.parse(f());
    }

    public String d() {
        String b = Strings.b(this.f14416a);
        if (b.indexOf(45) >= 0 || b.indexOf(43) >= 0) {
            int indexOf = b.indexOf(45);
            if (indexOf < 0) {
                indexOf = b.indexOf(43);
            }
            if (indexOf == b.length() - 3) {
                b = b + "00";
            }
            if (indexOf == 10) {
                return b.substring(0, 10) + "00GMT" + b.substring(10, 13) + ":" + b.substring(13, 15);
            }
            return b.substring(0, 12) + "GMT" + b.substring(12, 15) + ":" + b.substring(15, 17);
        } else if (b.length() == 11) {
            return b.substring(0, 10) + "00GMT+00:00";
        } else {
            return b.substring(0, 12) + "GMT+00:00";
        }
    }

    public String f() {
        String d = d();
        if (d.charAt(0) < '5') {
            return UserConfig.g + d;
        }
        return "19" + d;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        int length = this.f14416a.length;
        return StreamUtil.a(length) + 1 + length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.b(23);
        int length = this.f14416a.length;
        aSN1OutputStream.a(length);
        for (int i = 0; i != length; i++) {
            aSN1OutputStream.b(this.f14416a[i]);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1UTCTime)) {
            return false;
        }
        return Arrays.a(this.f14416a, ((ASN1UTCTime) aSN1Primitive).f14416a);
    }

    public int hashCode() {
        return Arrays.a(this.f14416a);
    }

    public String toString() {
        return Strings.b(this.f14416a);
    }
}
