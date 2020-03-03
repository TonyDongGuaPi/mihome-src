package com.xiaomi.stat.c;

import android.content.Context;
import com.xiaomi.stat.ak;
import com.xiaomi.stat.b;
import com.xiaomi.stat.d.k;
import java.util.HashMap;
import java.util.Map;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23034a = "ClientConfigMoniter";
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static Context g = ak.a();
    private static HashMap<Integer, Integer> h = new HashMap<>();

    static {
        h.put(1, 1);
        h.put(2, 2);
        h.put(3, 4);
        h.put(4, 8);
        h.put(5, 16);
    }

    public static int a(String str) {
        int i = 0;
        try {
            for (Map.Entry next : h.entrySet()) {
                int intValue = ((Integer) next.getKey()).intValue();
                int intValue2 = ((Integer) next.getValue()).intValue();
                if (a(intValue, str)) {
                    i |= intValue2;
                }
            }
        } catch (Exception e2) {
            k.d(f23034a, "getClientConfiguration exception", e2);
        }
        return i;
    }

    private static boolean a(int i, String str) {
        boolean z;
        switch (i) {
            case 1:
                z = b.u();
                break;
            case 2:
                z = b.d(str);
                break;
            case 3:
                z = k.a();
                break;
            case 5:
                try {
                    z = b.g();
                    break;
                } catch (Exception e2) {
                    k.d(f23034a, "checkSetting exception", e2);
                    return false;
                }
            default:
                return false;
        }
        return z;
    }
}
