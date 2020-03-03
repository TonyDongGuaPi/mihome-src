package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.push.em;
import com.xiaomi.push.service.am;
import java.util.HashMap;

class fm {
    public static void a(am.b bVar, String str, fu fuVar) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        em.c cVar = new em.c();
        if (!TextUtils.isEmpty(bVar.c)) {
            cVar.a(bVar.c);
        }
        if (!TextUtils.isEmpty(bVar.e)) {
            cVar.d(bVar.e);
        }
        if (!TextUtils.isEmpty(bVar.f)) {
            cVar.e(bVar.f);
        }
        cVar.b(bVar.f292a ? "1" : "0");
        cVar.c(!TextUtils.isEmpty(bVar.d) ? bVar.d : "XIAOMI-SASL");
        fn fnVar = new fn();
        fnVar.c(bVar.f293b);
        fnVar.a(Integer.parseInt(bVar.g));
        fnVar.b(bVar.f290a);
        fnVar.a(MIMCConstant.m, (String) null);
        fnVar.a(fnVar.h());
        b.a("[Slim]: bind id=" + fnVar.h());
        HashMap hashMap = new HashMap();
        hashMap.put("challenge", str);
        hashMap.put("token", bVar.c);
        hashMap.put("chid", bVar.g);
        hashMap.put("from", bVar.f293b);
        hashMap.put("id", fnVar.h());
        hashMap.put("to", "xiaomi.com");
        if (bVar.f292a) {
            str2 = "kick";
            str3 = "1";
        } else {
            str2 = "kick";
            str3 = "0";
        }
        hashMap.put(str2, str3);
        if (!TextUtils.isEmpty(bVar.e)) {
            str4 = "client_attrs";
            str5 = bVar.e;
        } else {
            str4 = "client_attrs";
            str5 = "";
        }
        hashMap.put(str4, str5);
        if (!TextUtils.isEmpty(bVar.f)) {
            str6 = "cloud_attrs";
            str7 = bVar.f;
        } else {
            str6 = "cloud_attrs";
            str7 = "";
        }
        hashMap.put(str6, str7);
        if (bVar.d.equals(MIMCConstant.A) || bVar.d.equals("XMPUSH-PASS")) {
            str8 = bd.a(bVar.d, (String) null, hashMap, bVar.h);
        } else {
            bVar.d.equals("XIAOMI-SASL");
            str8 = null;
        }
        cVar.f(str8);
        fnVar.a(cVar.c(), (String) null);
        fuVar.b(fnVar);
    }

    public static void a(String str, String str2, fu fuVar) {
        fn fnVar = new fn();
        fnVar.c(str2);
        fnVar.a(Integer.parseInt(str));
        fnVar.a(MIMCConstant.o, (String) null);
        fuVar.b(fnVar);
    }
}
