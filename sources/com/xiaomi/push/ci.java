package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.miui.tsmclient.util.StringUtils;
import com.xiaomi.stat.c.c;
import java.text.SimpleDateFormat;

public class ci {

    /* renamed from: a  reason: collision with root package name */
    private static SimpleDateFormat f12674a = new SimpleDateFormat(StringUtils.EXPECT_DATE_FORMAT);
    private static String b = f12674a.format(Long.valueOf(System.currentTimeMillis()));

    public static hs a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        hs hsVar = new hs();
        hsVar.d("category_push_stat");
        hsVar.a("push_sdk_stat_channel");
        hsVar.a(1);
        hsVar.b(str);
        hsVar.a(true);
        hsVar.b(System.currentTimeMillis());
        hsVar.g(br.a(context).a());
        hsVar.e(c.f23036a);
        hsVar.f("");
        hsVar.c("push_stat");
        return hsVar;
    }
}
