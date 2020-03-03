package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.miui.tsmclient.util.StringUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.hr;
import com.xiaomi.push.hs;
import com.xiaomi.push.hy;
import com.xiaomi.push.in;
import com.xiaomi.push.iy;
import com.xiaomi.push.t;
import com.xiaomi.push.y;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class bf {

    /* renamed from: a  reason: collision with root package name */
    private static String f12904a = f325a.format(Long.valueOf(System.currentTimeMillis()));

    /* renamed from: a  reason: collision with other field name */
    private static SimpleDateFormat f325a = new SimpleDateFormat(StringUtils.EXPECT_DATE_FORMAT);

    /* renamed from: a  reason: collision with other field name */
    private static AtomicLong f326a = new AtomicLong(0);

    public static synchronized String a() {
        String str;
        synchronized (bf.class) {
            String format = f325a.format(Long.valueOf(System.currentTimeMillis()));
            if (!TextUtils.equals(f12904a, format)) {
                f326a.set(0);
                f12904a = format;
            }
            str = format + "-" + f326a.incrementAndGet();
        }
        return str;
    }

    public static ArrayList<in> a(List<hs> list, String str, String str2, int i) {
        String str3;
        if (list == null) {
            str3 = "requests can not be null in TinyDataHelper.transToThriftObj().";
        } else if (list.size() == 0) {
            str3 = "requests.length is 0 in TinyDataHelper.transToThriftObj().";
        } else {
            ArrayList<in> arrayList = new ArrayList<>();
            hr hrVar = new hr();
            int i2 = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                hs hsVar = list.get(i3);
                if (hsVar != null) {
                    int length = iy.a(hsVar).length;
                    if (length > i) {
                        b.d("TinyData is too big, ignore upload request item:" + hsVar.d());
                    } else {
                        if (i2 + length > i) {
                            in inVar = new in("-1", false);
                            inVar.d(str);
                            inVar.b(str2);
                            inVar.c(hy.UploadTinyData.f114a);
                            inVar.a(y.a(iy.a(hrVar)));
                            arrayList.add(inVar);
                            hrVar = new hr();
                            i2 = 0;
                        }
                        hrVar.a(hsVar);
                        i2 += length;
                    }
                }
            }
            if (hrVar.a() != 0) {
                in inVar2 = new in("-1", false);
                inVar2.d(str);
                inVar2.b(str2);
                inVar2.c(hy.UploadTinyData.f114a);
                inVar2.a(y.a(iy.a(hrVar)));
                arrayList.add(inVar2);
            }
            return arrayList;
        }
        b.d(str3);
        return null;
    }

    public static void a(Context context, String str, String str2, long j, String str3) {
        hs hsVar = new hs();
        hsVar.d(str);
        hsVar.c(str2);
        hsVar.a(j);
        hsVar.b(str3);
        hsVar.a("push_sdk_channel");
        hsVar.g(context.getPackageName());
        hsVar.e(context.getPackageName());
        hsVar.a(true);
        hsVar.b(System.currentTimeMillis());
        hsVar.f(a());
        bg.a(context, hsVar);
    }

    public static boolean a(hs hsVar, boolean z) {
        String str;
        if (hsVar == null) {
            str = "item is null, verfiy ClientUploadDataItem failed.";
        } else if (!z && TextUtils.isEmpty(hsVar.f96a)) {
            str = "item.channel is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (TextUtils.isEmpty(hsVar.d)) {
            str = "item.category is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (TextUtils.isEmpty(hsVar.c)) {
            str = "item.name is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (!com.xiaomi.push.bf.d(hsVar.d)) {
            str = "item.category can only contain ascii char, verfiy ClientUploadDataItem failed.";
        } else if (!com.xiaomi.push.bf.d(hsVar.c)) {
            str = "item.name can only contain ascii char, verfiy ClientUploadDataItem failed.";
        } else if (hsVar.f100b == null || hsVar.f100b.length() <= 10240) {
            return false;
        } else {
            str = "item.data is too large(" + hsVar.f100b.length() + "), max size for data is " + 10240 + " , verfiy ClientUploadDataItem failed.";
        }
        b.a(str);
        return true;
    }

    public static boolean a(String str) {
        return !t.d() || Constants.t.equals(str);
    }
}
