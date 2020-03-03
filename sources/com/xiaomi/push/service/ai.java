package com.xiaomi.push.service;

import android.util.Pair;
import com.xiaomi.push.ad;
import com.xiaomi.push.hu;
import com.xiaomi.push.hv;
import com.xiaomi.push.hx;
import com.xiaomi.push.hz;
import com.xiaomi.push.il;
import com.xiaomi.push.im;
import java.util.ArrayList;
import java.util.List;

public class ai {
    public static int a(ah ahVar, hu huVar) {
        String a2 = a(huVar);
        int i = 0;
        switch (aj.f12877a[huVar.ordinal()]) {
            case 1:
                i = 1;
                break;
        }
        return ahVar.f276a.getInt(a2, i);
    }

    private static String a(hu huVar) {
        return "oc_version_" + huVar.a();
    }

    private static List<Pair<Integer, Object>> a(List<hz> list, boolean z) {
        Pair pair;
        if (ad.a(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (hz next : list) {
            int a2 = next.a();
            hv a3 = hv.a(next.b());
            if (a3 != null) {
                if (!z || !next.f118a) {
                    switch (aj.b[a3.ordinal()]) {
                        case 1:
                            pair = new Pair(Integer.valueOf(a2), Integer.valueOf(next.c()));
                            break;
                        case 2:
                            pair = new Pair(Integer.valueOf(a2), Long.valueOf(next.a()));
                            break;
                        case 3:
                            pair = new Pair(Integer.valueOf(a2), next.a());
                            break;
                        case 4:
                            pair = new Pair(Integer.valueOf(a2), Boolean.valueOf(next.g()));
                            break;
                        default:
                            pair = null;
                            break;
                    }
                    arrayList.add(pair);
                } else {
                    arrayList.add(new Pair(Integer.valueOf(a2), (Object) null));
                }
            }
        }
        return arrayList;
    }

    public static void a(ah ahVar, hu huVar, int i) {
        ahVar.f276a.edit().putInt(a(huVar), i).commit();
    }

    public static void a(ah ahVar, il ilVar) {
        ahVar.b(a(ilVar.a(), true));
        ahVar.b();
    }

    public static void a(ah ahVar, im imVar) {
        for (hx next : imVar.a()) {
            if (next.a() > a(ahVar, next.a())) {
                a(ahVar, next.a(), next.a());
                ahVar.a(a(next.f112a, false));
            }
        }
        ahVar.b();
    }
}
