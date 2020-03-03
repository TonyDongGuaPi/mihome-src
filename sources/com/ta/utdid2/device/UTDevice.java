package com.ta.utdid2.device;

import android.content.Context;
import com.ta.utdid2.a.a.f;

public class UTDevice {
    @Deprecated
    public static String getUtdid(Context context) {
        return a(context);
    }

    @Deprecated
    public static String getUtdidForUpdate(Context context) {
        return b(context);
    }

    private static String a(Context context) {
        a b = b.b(context);
        return (b == null || f.isEmpty(b.getUtdid())) ? "ffffffffffffffffffffffff" : b.getUtdid();
    }

    private static String b(Context context) {
        String d = c.a(context).d();
        return (d == null || f.isEmpty(d)) ? "ffffffffffffffffffffffff" : d;
    }
}
