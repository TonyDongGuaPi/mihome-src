package com.xiaomi.smarthome.newui.topwidget;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;

public class TopWidgetData implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    public String f20710a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j = "";
    public String k = "";
    public boolean l = false;

    public TopWidgetData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.f20710a = str2 + JSMethod.NOT_SET + str5;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.h = str7;
        this.i = str8;
    }

    public TopWidgetData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z) {
        this.f20710a = str2 + JSMethod.NOT_SET + str5;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.h = str7;
        this.i = str8;
        this.l = z;
    }

    /* renamed from: a */
    public TopWidgetData clone() {
        try {
            return (TopWidgetData) super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    public String toString() {
        return "TopWidgetData{key='" + this.f20710a + Operators.SINGLE_QUOTE + ", model='" + this.b + Operators.SINGLE_QUOTE + ", did='" + this.c + Operators.SINGLE_QUOTE + ", deviceName='" + this.d + Operators.SINGLE_QUOTE + ", roomName='" + this.e + Operators.SINGLE_QUOTE + ", propKey='" + this.f + Operators.SINGLE_QUOTE + ", propName='" + this.g + Operators.SINGLE_QUOTE + ", propValue='" + this.h + Operators.SINGLE_QUOTE + ", propUnit='" + this.i + Operators.SINGLE_QUOTE + ", propDesc='" + this.j + Operators.SINGLE_QUOTE + ", propStatus='" + this.k + Operators.SINGLE_QUOTE + ", isChosen=" + this.l + Operators.BLOCK_END;
    }
}
