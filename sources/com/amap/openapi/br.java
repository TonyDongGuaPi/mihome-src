package com.amap.openapi;

import android.content.Context;
import com.amap.location.common.log.ALLog;
import com.amap.location.common.model.AmapLoc;
import com.amap.location.common.util.c;
import com.amap.location.offline.OfflineConfig;
import com.amap.location.offline.upload.a;

class br {
    private static AmapLoc a(OfflineConfig offlineConfig, AmapLoc amapLoc) {
        double[] a2;
        if (amapLoc == null || offlineConfig.h != 4) {
            return amapLoc;
        }
        if (offlineConfig.v == null || (a2 = offlineConfig.v.a(new double[]{amapLoc.d(), amapLoc.c()})) == null || a2.length < 2) {
            return null;
        }
        AmapLoc amapLoc2 = new AmapLoc();
        amapLoc2.b(a2[0]);
        amapLoc2.a(a2[1]);
        return amapLoc2;
    }

    public static void a(Context context, OfflineConfig offlineConfig, bs bsVar, bu buVar, AmapLoc amapLoc) {
        AmapLoc a2;
        if (amapLoc != null && amapLoc.R()) {
            boolean a3 = a(amapLoc);
            if (a3 && bsVar.i != null) {
                AmapLoc a4 = a(offlineConfig, bsVar.i);
                if (a4 != null) {
                    double a5 = (double) c.a(amapLoc, a4);
                    if (a5 > 300.0d) {
                        ALLog.d("@_18_3_@", "@_18_3_1_@" + a5);
                        by.a(context).b(bsVar);
                        a.a(100038, ("cellCorrect:" + a5).getBytes());
                    }
                }
            } else if (!a3 && buVar.f != null && (a2 = a(offlineConfig, buVar.f)) != null) {
                double a6 = (double) c.a(amapLoc, a2);
                if (a6 > 100.0d) {
                    ALLog.d("@_18_3_@", "@_18_3_2_@" + a6);
                    by.a(context).a(buVar, amapLoc);
                    a.a(100038, ("wifiCorrect:" + a6).getBytes());
                }
            }
        }
    }

    public static void a(Context context, bs bsVar) {
        by.a(context).a(bsVar);
    }

    public static void a(Context context, bu buVar) {
        by.a(context).a(buVar);
    }

    private static boolean a(AmapLoc amapLoc) {
        String o = amapLoc.o();
        return "3".equals(o) || "4".equals(o) || "9".equals(o);
    }
}
