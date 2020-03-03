package com.xiaomi.jr.mipay.codepay.validate;

import com.xiaomi.jr.mipay.codepay.R;

public class ValidateType {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10937a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;

    public static int a(int i) {
        if (i == 1) {
            return R.string.jr_mipay_counter_password_title;
        }
        if (i == 4) {
            return R.string.jr_mipay_counter_confirm_title;
        }
        return -1;
    }

    public static FooterViewType b(int i) {
        if (i == 1) {
            return FooterViewType.PASSWORD;
        }
        return FooterViewType.BUTTON;
    }
}
