package com.taobao.weex.yp_compoment.pullrefresh.youpinptr.utils;

import android.util.Log;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class PtrCLog {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_WARNING = 3;
    private static int sLevel = 0;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4916815946815322744L, "com/taobao/weex/yp_compoment/pullrefresh/youpinptr/utils/PtrCLog", 57);
        $jacocoData = a2;
        return a2;
    }

    public PtrCLog() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[56] = true;
    }

    public static void setLogLevel(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        sLevel = i;
        $jacocoInit[1] = true;
    }

    public static void v(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 0) {
            $jacocoInit[2] = true;
            return;
        }
        Log.v(str, str2);
        $jacocoInit[3] = true;
    }

    public static void v(String str, String str2, Throwable th) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 0) {
            $jacocoInit[4] = true;
            return;
        }
        Log.v(str, str2, th);
        $jacocoInit[5] = true;
    }

    public static void v(String str, String str2, Object... objArr) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 0) {
            $jacocoInit[6] = true;
            return;
        }
        if (objArr.length <= 0) {
            $jacocoInit[7] = true;
        } else {
            $jacocoInit[8] = true;
            str2 = String.format(str2, objArr);
            $jacocoInit[9] = true;
        }
        Log.v(str, str2);
        $jacocoInit[10] = true;
    }

    public static void d(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 1) {
            $jacocoInit[11] = true;
            return;
        }
        Log.d(str, str2);
        $jacocoInit[12] = true;
    }

    public static void d(String str, String str2, Object... objArr) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 1) {
            $jacocoInit[13] = true;
            return;
        }
        if (objArr.length <= 0) {
            $jacocoInit[14] = true;
        } else {
            $jacocoInit[15] = true;
            str2 = String.format(str2, objArr);
            $jacocoInit[16] = true;
        }
        Log.d(str, str2);
        $jacocoInit[17] = true;
    }

    public static void d(String str, String str2, Throwable th) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 1) {
            $jacocoInit[18] = true;
            return;
        }
        Log.d(str, str2, th);
        $jacocoInit[19] = true;
    }

    public static void i(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 2) {
            $jacocoInit[20] = true;
            return;
        }
        Log.i(str, str2);
        $jacocoInit[21] = true;
    }

    public static void i(String str, String str2, Object... objArr) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 2) {
            $jacocoInit[22] = true;
            return;
        }
        if (objArr.length <= 0) {
            $jacocoInit[23] = true;
        } else {
            $jacocoInit[24] = true;
            str2 = String.format(str2, objArr);
            $jacocoInit[25] = true;
        }
        Log.i(str, str2);
        $jacocoInit[26] = true;
    }

    public static void i(String str, String str2, Throwable th) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 2) {
            $jacocoInit[27] = true;
            return;
        }
        Log.i(str, str2, th);
        $jacocoInit[28] = true;
    }

    public static void w(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 3) {
            $jacocoInit[29] = true;
            return;
        }
        Log.w(str, str2);
        $jacocoInit[30] = true;
    }

    public static void w(String str, String str2, Object... objArr) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 3) {
            $jacocoInit[31] = true;
            return;
        }
        if (objArr.length <= 0) {
            $jacocoInit[32] = true;
        } else {
            $jacocoInit[33] = true;
            str2 = String.format(str2, objArr);
            $jacocoInit[34] = true;
        }
        Log.w(str, str2);
        $jacocoInit[35] = true;
    }

    public static void w(String str, String str2, Throwable th) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 3) {
            $jacocoInit[36] = true;
            return;
        }
        Log.w(str, str2, th);
        $jacocoInit[37] = true;
    }

    public static void e(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 4) {
            $jacocoInit[38] = true;
            return;
        }
        Log.e(str, str2);
        $jacocoInit[39] = true;
    }

    public static void e(String str, String str2, Object... objArr) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 4) {
            $jacocoInit[40] = true;
            return;
        }
        if (objArr.length <= 0) {
            $jacocoInit[41] = true;
        } else {
            $jacocoInit[42] = true;
            str2 = String.format(str2, objArr);
            $jacocoInit[43] = true;
        }
        Log.e(str, str2);
        $jacocoInit[44] = true;
    }

    public static void e(String str, String str2, Throwable th) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 4) {
            $jacocoInit[45] = true;
            return;
        }
        Log.e(str, str2, th);
        $jacocoInit[46] = true;
    }

    public static void f(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 5) {
            $jacocoInit[47] = true;
            return;
        }
        Log.wtf(str, str2);
        $jacocoInit[48] = true;
    }

    public static void f(String str, String str2, Object... objArr) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 5) {
            $jacocoInit[49] = true;
            return;
        }
        if (objArr.length <= 0) {
            $jacocoInit[50] = true;
        } else {
            $jacocoInit[51] = true;
            str2 = String.format(str2, objArr);
            $jacocoInit[52] = true;
        }
        Log.wtf(str, str2);
        $jacocoInit[53] = true;
    }

    public static void f(String str, String str2, Throwable th) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sLevel > 5) {
            $jacocoInit[54] = true;
            return;
        }
        Log.wtf(str, str2, th);
        $jacocoInit[55] = true;
    }
}
