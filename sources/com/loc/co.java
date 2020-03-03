package com.loc;

import android.content.Context;
import com.amap.location.offline.OfflineConfig;
import java.lang.reflect.Method;

public final class co implements OfflineConfig.ICoordinateConverter {

    /* renamed from: a  reason: collision with root package name */
    Context f6536a = null;
    Object b = null;

    public co(Context context) {
        this.f6536a = context;
    }

    private double[] b(double[] dArr) {
        try {
            if (this.b == null) {
                this.b = Class.forName("com.amap.api.location.CoordinateConverter").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{this.f6536a});
            }
            if (cm.a(dArr[0], dArr[1])) {
                Object[] objArr = {"GPS"};
                Object newInstance = Class.forName("com.amap.api.location.DPoint").getConstructor(new Class[]{Double.TYPE, Double.TYPE}).newInstance(new Object[]{Double.valueOf(dArr[0]), Double.valueOf(dArr[1])});
                Method declaredMethod = Class.forName("com.amap.api.location.CoordinateConverter$CoordType").getDeclaredMethod("valueOf", new Class[]{String.class});
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                Object invoke = declaredMethod.invoke((Object) null, objArr);
                ck.a(this.b, "coord", newInstance);
                ck.a(this.b, "from", invoke);
                Object a2 = ck.a(this.b, "convert", new Object[0]);
                dArr[0] = ((Double) ck.a(a2, "getLatitude", new Object[0])).doubleValue();
                dArr[1] = ((Double) ck.a(a2, "getLongitude", new Object[0])).doubleValue();
            }
        } catch (Throwable th) {
            cm.a(th, "OfflineCoordinateConverter", "wgsToGcj");
        }
        return dArr;
    }

    public final double[] a(double[] dArr) {
        if (this.f6536a == null || dArr == null || dArr.length != 2) {
            return null;
        }
        return b(dArr);
    }
}
