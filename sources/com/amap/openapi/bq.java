package com.amap.openapi;

import android.util.Log;
import com.amap.location.common.log.ALLog;
import com.amap.location.common.model.AmapLoc;
import com.amap.location.security.Core;

class bq {
    private static AmapLoc a(AmapLoc amapLoc, bu buVar, int i) {
        String str;
        if (buVar.e.length() <= 0) {
            return null;
        }
        String sb = buVar.e.toString();
        if (amapLoc == null) {
            str = null;
        } else {
            str = amapLoc.c() + "," + amapLoc.d();
        }
        try {
            String gwl = Core.gwl(sb, buVar.f4643a, i, str);
            if (gwl == null) {
                return null;
            }
            String[] split = gwl.split(",");
            AmapLoc amapLoc2 = new AmapLoc();
            amapLoc2.a(System.currentTimeMillis());
            amapLoc2.b(0);
            amapLoc2.d(AmapLoc.h);
            amapLoc2.a(Double.parseDouble(split[0]));
            amapLoc2.b(Double.parseDouble(split[1]));
            amapLoc2.b((float) Integer.parseInt(split[2]));
            return amapLoc2;
        } catch (Throwable th) {
            ALLog.d("@_18_2_@", "@_18_2_2_@" + Log.getStackTraceString(th));
            return null;
        }
    }

    private static AmapLoc a(bs bsVar) {
        if (!bsVar.f4641a || bsVar.e <= 60) {
            return null;
        }
        try {
            String gcl = Core.gcl(bsVar.c, bsVar.b, bsVar.d);
            if (gcl == null) {
                return null;
            }
            String[] split = gcl.split(",");
            AmapLoc amapLoc = new AmapLoc();
            amapLoc.a(System.currentTimeMillis());
            amapLoc.b(0);
            amapLoc.d("file");
            amapLoc.a(Double.parseDouble(split[0]));
            amapLoc.b(Double.parseDouble(split[1]));
            amapLoc.b((float) Integer.parseInt(split[2]));
            return amapLoc;
        } catch (Throwable th) {
            ALLog.d("@_18_2_@", "@_18_2_1_@" + Log.getStackTraceString(th));
            return null;
        }
    }

    static AmapLoc a(bs bsVar, bu buVar, int i) {
        AmapLoc a2 = a(bsVar);
        AmapLoc a3 = a(a2, buVar, i);
        bsVar.i = a2;
        buVar.f = a3;
        if (a3 != null) {
            return a3;
        }
        if (a2 != null) {
            return a2;
        }
        return null;
    }
}
