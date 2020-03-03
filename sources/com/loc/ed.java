package com.loc;

import com.xiaomi.smarthome.auth.AuthCode;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.util.Locale;
import javax.jmdns.impl.constants.DNSRecordClass;
import org.json.JSONObject;

public final class ed {

    /* renamed from: a  reason: collision with root package name */
    public int f6578a = 0;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public int f = 0;
    public int g = 0;
    public int h = 0;
    public int i = 0;
    public int j = AuthCode.n;
    public int k = 0;
    public short l = 0;
    public long m = 0;
    public boolean n = false;
    public int o = DNSRecordClass.CLASS_MASK;
    public boolean p = true;

    public ed(int i2, boolean z) {
        this.k = i2;
        this.n = z;
    }

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.k);
            jSONObject.put("registered", this.n);
            jSONObject.put("mcc", this.f6578a);
            jSONObject.put("mnc", this.b);
            jSONObject.put("lac", this.c);
            jSONObject.put("cid", this.d);
            jSONObject.put("sid", this.g);
            jSONObject.put("nid", this.h);
            jSONObject.put("bid", this.i);
            jSONObject.put(DTransferConstants.n, this.j);
            jSONObject.put("pci", this.o);
        } catch (Throwable th) {
            es.a(th, "cgi", "toJson");
        }
        return jSONObject;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof ed)) {
            ed edVar = (ed) obj;
            switch (edVar.k) {
                case 1:
                    if (this.k == 1 && edVar.c == this.c && edVar.d == this.d && edVar.b == this.b) {
                        return true;
                    }
                    break;
                case 2:
                    return this.k == 2 && edVar.i == this.i && edVar.h == this.h && edVar.g == this.g;
                case 3:
                    return this.k == 3 && edVar.c == this.c && edVar.d == this.d && edVar.b == this.b;
                case 4:
                    return this.k == 4 && edVar.c == this.c && edVar.d == this.d && edVar.b == this.b;
                default:
                    return false;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        int i2;
        int hashCode2 = String.valueOf(this.k).hashCode();
        if (this.k == 2) {
            hashCode = String.valueOf(this.i).hashCode() + String.valueOf(this.h).hashCode();
            i2 = this.g;
        } else {
            hashCode = String.valueOf(this.c).hashCode() + String.valueOf(this.d).hashCode();
            i2 = this.b;
        }
        return hashCode2 + hashCode + String.valueOf(i2).hashCode();
    }

    public final String toString() {
        Object[] objArr;
        String str;
        Locale locale;
        switch (this.k) {
            case 1:
                locale = Locale.CHINA;
                str = "GSM lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b";
                objArr = new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n)};
                break;
            case 2:
                locale = Locale.CHINA;
                str = "CDMA bid=%d, nid=%d, sid=%d, valid=%b, sig=%d, age=%d, reg=%b";
                objArr = new Object[]{Integer.valueOf(this.i), Integer.valueOf(this.h), Integer.valueOf(this.g), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n)};
                break;
            case 3:
                locale = Locale.CHINA;
                str = "LTE lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b, pci=%d";
                objArr = new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n), Integer.valueOf(this.o)};
                break;
            case 4:
                locale = Locale.CHINA;
                str = "WCDMA lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b, pci=%d";
                objArr = new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n), Integer.valueOf(this.o)};
                break;
            default:
                return "unknown";
        }
        return String.format(locale, str, objArr);
    }
}
