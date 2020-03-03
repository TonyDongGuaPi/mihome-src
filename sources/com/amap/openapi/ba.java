package com.amap.openapi;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import java.util.List;
import kotlin.jvm.internal.ShortCompanionObject;

public class ba {
    public static short a(@NonNull List<y> list, boolean z, List<ct> list2) {
        list.clear();
        short s = ShortCompanionObject.b;
        if (list2 != null) {
            double d = 0.0d;
            int i = 0;
            for (ct next : list2) {
                int b = next.b();
                float d2 = next.d();
                boolean a2 = next.a();
                if (b > 1 && b <= 32) {
                    if (a2 && ((double) d2) > 10.0d) {
                        double c = (double) next.c();
                        Double.isNaN(c);
                        d += c;
                        i++;
                    }
                    if (z) {
                        y yVar = new y();
                        yVar.f4749a = (byte) b;
                        yVar.b = (byte) Math.round(next.c());
                        yVar.c = (byte) Math.round(d2);
                        yVar.d = (short) Math.round(next.e());
                        yVar.e = a2 ? (byte) 1 : 0;
                        list.add(yVar);
                    }
                }
                if (i > 0) {
                    double d3 = (double) i;
                    Double.isNaN(d3);
                    s = (short) Math.round(((float) (d / d3)) * 100.0f);
                }
            }
        }
        return s;
    }

    public static void a(@NonNull v vVar, @NonNull Location location, long j, long j2) {
        vVar.b = j;
        vVar.f4746a = j2;
        vVar.c = (int) (location.getLongitude() * 1000000.0d);
        vVar.d = (int) (location.getLatitude() * 1000000.0d);
        vVar.e = (int) location.getAltitude();
        vVar.f = (int) location.getAccuracy();
        vVar.g = (int) location.getSpeed();
        vVar.h = (short) ((int) location.getBearing());
        Bundle extras = location.getExtras();
        vVar.i = 0;
        if (extras != null) {
            try {
                vVar.i = (byte) extras.getInt("satellites", 0);
            } catch (Exception unused) {
            }
        }
    }

    public static void a(@NonNull v vVar, short s, @NonNull Location location, long j, long j2) {
        vVar.j = s;
        a(vVar, location, j, j2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x001f A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r3, android.location.Location r4) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 18
            if (r0 < r2) goto L_0x000e
            boolean r3 = com.amap.openapi.az.a((android.location.Location) r4)
            if (r3 == 0) goto L_0x0020
            goto L_0x001f
        L_0x000e:
            java.lang.String r4 = android.os.Build.MODEL
            java.lang.String r0 = "sdk"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L_0x001f
            boolean r3 = com.amap.openapi.az.b(r3)
            if (r3 != 0) goto L_0x001f
            goto L_0x0020
        L_0x001f:
            r1 = 1
        L_0x0020:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.ba.a(android.content.Context, android.location.Location):boolean");
    }

    public static boolean a(Location location) {
        return location != null && "gps".equalsIgnoreCase(location.getProvider()) && location.getLatitude() > -90.0d && location.getLatitude() < 90.0d && location.getLongitude() > -180.0d && location.getLongitude() < 180.0d;
    }
}
