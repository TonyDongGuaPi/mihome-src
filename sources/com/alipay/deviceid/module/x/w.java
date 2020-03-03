package com.alipay.deviceid.module.x;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class w {

    /* renamed from: a  reason: collision with root package name */
    private String f938a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public w(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.f938a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()));
        stringBuffer.append("," + this.f938a);
        stringBuffer.append("," + this.b);
        stringBuffer.append("," + this.c);
        stringBuffer.append("," + this.d);
        if (e.a(this.e) || this.e.length() < 20) {
            stringBuffer.append("," + this.e);
        } else {
            stringBuffer.append("," + this.e.substring(0, 20));
        }
        if (e.a(this.f) || this.f.length() < 20) {
            stringBuffer.append("," + this.f);
        } else {
            stringBuffer.append("," + this.f.substring(0, 20));
        }
        if (e.a(this.g) || this.g.length() < 20) {
            stringBuffer.append("," + this.g);
        } else {
            stringBuffer.append("," + this.g.substring(0, 20));
        }
        return stringBuffer.toString();
    }
}
