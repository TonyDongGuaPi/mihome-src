package com.xiaomi.smarthome.framework.page;

import android.content.Intent;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;

public class PageUtil {

    public static class RefererInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f16892a;
        public String b;
    }

    public static RefererInfo a(Intent intent, String str, String str2, String str3) {
        String str4;
        RefererInfo refererInfo = new RefererInfo();
        if (intent == null) {
            return refererInfo;
        }
        if (TextUtils.isEmpty(str)) {
            if (TextUtils.isEmpty(str3)) {
                str4 = str2;
            } else {
                str4 = str2 + Operators.DOLLAR_STR + str3;
            }
        } else if (TextUtils.isEmpty(str3)) {
            str4 = str + "/" + str2;
        } else {
            str4 = str + "/" + str2 + Operators.DOLLAR_STR + str3;
        }
        intent.putExtra("single_referer_key", str2);
        intent.putExtra(CommonActivity.FULL_REFERER_KEY, str4);
        refererInfo.f16892a = str2;
        refererInfo.b = str4;
        return refererInfo;
    }
}
