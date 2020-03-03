package com.ximalaya.ting.android.xmpayordersdk;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;

public class PayFinishModel {

    /* renamed from: a  reason: collision with root package name */
    private int f2379a;
    private String b;

    public int a() {
        return this.f2379a;
    }

    public void a(int i) {
        this.f2379a = i;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public PayFinishModel(int i, String str) {
        this.f2379a = i;
        this.b = str;
    }

    public static PayFinishModel b(int i) {
        String str = IXmPayOrderListener.C.get(Integer.valueOf(i));
        if (TextUtils.isEmpty(str)) {
            str = "网络错误";
        }
        return new PayFinishModel(i, str);
    }

    public static PayFinishModel a(int i, String str) {
        String str2 = IXmPayOrderListener.C.get(Integer.valueOf(i));
        if (TextUtils.isEmpty(str)) {
            str = TextUtils.isEmpty(str2) ? "网络错误" : str2;
        }
        return new PayFinishModel(i, str);
    }

    public String toString() {
        return "PayFinishModel{code=" + this.f2379a + ", des='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
