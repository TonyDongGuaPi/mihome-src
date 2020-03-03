package com.taobao.weex.wson;

import com.taobao.weex.utils.WXLogUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WsonUtils {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(9124670712196631433L, "com/taobao/weex/wson/WsonUtils", 11);
        $jacocoData = a2;
        return a2;
    }

    public WsonUtils() {
        $jacocoInit()[0] = true;
    }

    public static final Object parseWson(byte[] bArr) {
        boolean[] $jacocoInit = $jacocoInit();
        if (bArr != null) {
            $jacocoInit[1] = true;
            try {
                Object parse = Wson.parse(bArr);
                $jacocoInit[3] = true;
                return parse;
            } catch (Exception e) {
                $jacocoInit[4] = true;
                WXLogUtils.e("weex wson parse error ", (Throwable) e);
                $jacocoInit[5] = true;
                return null;
            }
        } else {
            $jacocoInit[2] = true;
            return null;
        }
    }

    public static final byte[] toWson(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj != null) {
            $jacocoInit[6] = true;
            try {
                byte[] wson = Wson.toWson(obj);
                $jacocoInit[8] = true;
                return wson;
            } catch (Exception e) {
                $jacocoInit[9] = true;
                WXLogUtils.e("weex wson to wson error ", (Throwable) e);
                $jacocoInit[10] = true;
                return null;
            }
        } else {
            $jacocoInit[7] = true;
            return null;
        }
    }
}
