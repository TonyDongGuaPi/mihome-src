package com.xiaomi.smarthome.shop.pay;

import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.alipay.sdk.util.l;

public class AlipayResult {

    /* renamed from: a  reason: collision with root package name */
    private String f22182a;
    private String b;
    private String c;

    public AlipayResult(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(i.b)) {
                if (str2.startsWith(l.f1135a)) {
                    this.f22182a = a(str2, l.f1135a);
                }
                if (str2.startsWith("result")) {
                    this.b = a(str2, "result");
                }
                if (str2.startsWith(l.b)) {
                    this.c = a(str2, l.b);
                }
            }
        }
    }

    public String toString() {
        return "resultStatus={" + this.f22182a + "};memo={" + this.c + "};result={" + this.b + "}";
    }

    private String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf("}"));
    }

    public String a() {
        return this.f22182a;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.b;
    }
}
