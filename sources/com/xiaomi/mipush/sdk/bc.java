package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.ad;
import com.xiaomi.push.ai;
import com.xiaomi.push.bf;
import com.xiaomi.push.ht;
import com.xiaomi.push.in;
import com.xiaomi.push.service.ah;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class bc {
    public static void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        long j = sharedPreferences.getLong("last_sync_info", -1);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long a2 = (long) ah.a(context).a(ht.SyncInfoFrequency.a(), 1209600);
        if (j != -1) {
            if (Math.abs(currentTimeMillis - j) > a2) {
                a(context, true);
            } else {
                return;
            }
        }
        sharedPreferences.edit().putLong("last_sync_info", currentTimeMillis).commit();
    }

    public static void a(Context context, in inVar) {
        b.a("need to update local info with: " + inVar.a());
        String str = (String) inVar.a().get(Constants.h);
        if (str != null) {
            MiPushClient.x(context);
            String[] split = str.split("-");
            if (split.length == 2) {
                MiPushClient.i(context, split[0], split[1]);
                if (!"00:00".equals(split[0]) || !"00:00".equals(split[1])) {
                    b.a(context).a(false);
                } else {
                    b.a(context).a(true);
                }
            }
        }
        String str2 = (String) inVar.a().get(Constants.j);
        if (str2 != null) {
            MiPushClient.u(context);
            if (!"".equals(str2)) {
                for (String d : str2.split(",")) {
                    MiPushClient.d(context, d);
                }
            }
        }
        String str3 = (String) inVar.a().get(Constants.l);
        if (str3 != null) {
            MiPushClient.w(context);
            if (!"".equals(str3)) {
                for (String h : str3.split(",")) {
                    MiPushClient.h(context, h);
                }
            }
        }
        String str4 = (String) inVar.a().get(Constants.n);
        if (str4 != null) {
            MiPushClient.v(context);
            if (!"".equals(str4)) {
                for (String f : str4.split(",")) {
                    MiPushClient.f(context, f);
                }
            }
        }
    }

    public static void a(Context context, boolean z) {
        ai.a(context).a((Runnable) new bd(context, z));
    }

    /* access modifiers changed from: private */
    public static String c(List<String> list) {
        String a2 = bf.a(d(list));
        return (TextUtils.isEmpty(a2) || a2.length() <= 4) ? "" : a2.substring(0, 4).toLowerCase();
    }

    /* access modifiers changed from: private */
    public static String d(List<String> list) {
        if (ad.a(list)) {
            return "";
        }
        ArrayList<String> arrayList = new ArrayList<>(list);
        Collections.sort(arrayList, Collator.getInstance(Locale.CHINA));
        String str = "";
        for (String str2 : arrayList) {
            if (!TextUtils.isEmpty(str)) {
                str = str + ",";
            }
            str = str + str2;
        }
        return str;
    }
}
