package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;

final class i implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f11554a;
    final /* synthetic */ Context b;
    final /* synthetic */ d c;

    i(String str, Context context, d dVar) {
        this.f11554a = str;
        this.b = context;
        this.c = dVar;
    }

    public void run() {
        if (!TextUtils.isEmpty(this.f11554a)) {
            String str = "";
            String[] split = this.f11554a.split(Constants.J);
            int length = split.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str2 = split[i];
                if (!TextUtils.isEmpty(str2) && str2.startsWith("token:")) {
                    str = str2.substring(str2.indexOf(":") + 1);
                    break;
                }
                i++;
            }
            if (!TextUtils.isEmpty(str)) {
                b.a("ASSEMBLE_PUSH : receive correct token");
                h.d(this.b, this.c, str);
                h.a(this.b);
                return;
            }
            b.a("ASSEMBLE_PUSH : receive incorrect token");
        }
    }
}
