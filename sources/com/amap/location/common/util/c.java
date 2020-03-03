package com.amap.location.common.util;

import android.location.Location;
import com.amap.location.common.model.AmapLoc;

public class c {
    public static float a(AmapLoc amapLoc, AmapLoc amapLoc2) {
        return a(new double[]{amapLoc.d(), amapLoc.c(), amapLoc2.d(), amapLoc2.c()});
    }

    public static float a(double[] dArr) {
        if (dArr.length != 4) {
            return 0.0f;
        }
        float[] fArr = new float[1];
        Location.distanceBetween(dArr[0], dArr[1], dArr[2], dArr[3], fArr);
        return fArr[0];
    }
}
