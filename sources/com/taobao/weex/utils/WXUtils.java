package com.taobao.weex.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.common.Constants;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXUtils {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final int HUNDRED = 100;
    public static final char PERCENT = '%';
    static final LruCache<String, Integer> sCache = new LruCache<>(64);
    private static final long sInterval = (System.currentTimeMillis() - SystemClock.uptimeMillis());

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1519786146814705302L, "com/taobao/weex/utils/WXUtils", 283);
        $jacocoData = a2;
        return a2;
    }

    public WXUtils() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[281] = true;
        $jacocoInit[282] = true;
    }

    public static boolean isUiThread() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            $jacocoInit[1] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[2] = true;
        }
        $jacocoInit[3] = true;
        return z;
    }

    public static boolean isUndefined(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean isNaN = Float.isNaN(f);
        $jacocoInit[4] = true;
        return isNaN;
    }

    public static float getFloatByViewport(Object obj, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[5] = true;
            return Float.NaN;
        }
        String trim = obj.toString().trim();
        $jacocoInit[6] = true;
        if ("auto".equals(trim)) {
            $jacocoInit[7] = true;
        } else {
            $jacocoInit[8] = true;
            if (Constants.Name.UNDEFINED.equals(trim)) {
                $jacocoInit[9] = true;
            } else {
                $jacocoInit[10] = true;
                if (TextUtils.isEmpty(trim)) {
                    $jacocoInit[11] = true;
                } else if (trim.endsWith("wx")) {
                    try {
                        $jacocoInit[13] = true;
                        float transferWx = transferWx(trim, i);
                        $jacocoInit[14] = true;
                        return transferWx;
                    } catch (NumberFormatException e) {
                        $jacocoInit[15] = true;
                        WXLogUtils.e("Argument format error! value is " + obj, (Throwable) e);
                        $jacocoInit[16] = true;
                        $jacocoInit[32] = true;
                        return Float.NaN;
                    } catch (Exception e2) {
                        $jacocoInit[17] = true;
                        WXLogUtils.e("Argument error! value is " + obj, (Throwable) e2);
                        $jacocoInit[18] = true;
                        $jacocoInit[32] = true;
                        return Float.NaN;
                    }
                } else if (!trim.endsWith("px")) {
                    $jacocoInit[19] = true;
                    try {
                        float parseFloat = Float.parseFloat(trim);
                        $jacocoInit[27] = true;
                        return parseFloat;
                    } catch (NumberFormatException e3) {
                        $jacocoInit[28] = true;
                        WXLogUtils.e("Argument format error! value is " + obj, (Throwable) e3);
                        $jacocoInit[29] = true;
                        $jacocoInit[32] = true;
                        return Float.NaN;
                    } catch (Exception e4) {
                        $jacocoInit[30] = true;
                        WXLogUtils.e("Argument error! value is " + obj, (Throwable) e4);
                        $jacocoInit[31] = true;
                        $jacocoInit[32] = true;
                        return Float.NaN;
                    }
                } else {
                    try {
                        $jacocoInit[20] = true;
                        String substring = trim.substring(0, trim.indexOf("px"));
                        $jacocoInit[21] = true;
                        float parseFloat2 = Float.parseFloat(substring);
                        $jacocoInit[22] = true;
                        return parseFloat2;
                    } catch (NumberFormatException e5) {
                        $jacocoInit[23] = true;
                        WXLogUtils.e("Argument format error! value is " + obj, (Throwable) e5);
                        $jacocoInit[24] = true;
                        $jacocoInit[32] = true;
                        return Float.NaN;
                    } catch (Exception e6) {
                        $jacocoInit[25] = true;
                        WXLogUtils.e("Argument error! value is " + obj, (Throwable) e6);
                        $jacocoInit[26] = true;
                        $jacocoInit[32] = true;
                        return Float.NaN;
                    }
                }
            }
        }
        WXLogUtils.e("Argument Warning ! value is " + trim + "And default Value:" + Float.NaN);
        $jacocoInit[12] = true;
        return Float.NaN;
    }

    public static float getFloat(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        float floatValue = getFloat(obj, Float.valueOf(Float.NaN)).floatValue();
        $jacocoInit[33] = true;
        return floatValue;
    }

    public static Float getFloat(Object obj, @Nullable Float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[34] = true;
            return f;
        }
        String trim = obj.toString().trim();
        $jacocoInit[35] = true;
        if ("auto".equals(trim)) {
            $jacocoInit[36] = true;
        } else {
            $jacocoInit[37] = true;
            if (Constants.Name.UNDEFINED.equals(trim)) {
                $jacocoInit[38] = true;
            } else {
                $jacocoInit[39] = true;
                if (TextUtils.isEmpty(trim)) {
                    $jacocoInit[40] = true;
                } else if (trim.endsWith("wx")) {
                    try {
                        $jacocoInit[42] = true;
                        Float valueOf = Float.valueOf(transferWx(trim, 750));
                        $jacocoInit[43] = true;
                        return valueOf;
                    } catch (NumberFormatException e) {
                        $jacocoInit[44] = true;
                        WXLogUtils.e("Argument format error! value is " + obj, (Throwable) e);
                        $jacocoInit[45] = true;
                        $jacocoInit[61] = true;
                        return f;
                    } catch (Exception e2) {
                        $jacocoInit[46] = true;
                        WXLogUtils.e("Argument error! value is " + obj, (Throwable) e2);
                        $jacocoInit[47] = true;
                        $jacocoInit[61] = true;
                        return f;
                    }
                } else if (!trim.endsWith("px")) {
                    $jacocoInit[48] = true;
                    try {
                        Float valueOf2 = Float.valueOf(Float.parseFloat(trim));
                        $jacocoInit[56] = true;
                        return valueOf2;
                    } catch (NumberFormatException e3) {
                        $jacocoInit[57] = true;
                        WXLogUtils.e("Argument format error! value is " + obj, (Throwable) e3);
                        $jacocoInit[58] = true;
                        $jacocoInit[61] = true;
                        return f;
                    } catch (Exception e4) {
                        $jacocoInit[59] = true;
                        WXLogUtils.e("Argument error! value is " + obj, (Throwable) e4);
                        $jacocoInit[60] = true;
                        $jacocoInit[61] = true;
                        return f;
                    }
                } else {
                    try {
                        $jacocoInit[49] = true;
                        String substring = trim.substring(0, trim.indexOf("px"));
                        $jacocoInit[50] = true;
                        Float valueOf3 = Float.valueOf(Float.parseFloat(substring));
                        $jacocoInit[51] = true;
                        return valueOf3;
                    } catch (NumberFormatException e5) {
                        $jacocoInit[52] = true;
                        WXLogUtils.e("Argument format error! value is " + obj, (Throwable) e5);
                        $jacocoInit[53] = true;
                        $jacocoInit[61] = true;
                        return f;
                    } catch (Exception e6) {
                        $jacocoInit[54] = true;
                        WXLogUtils.e("Argument error! value is " + obj, (Throwable) e6);
                        $jacocoInit[55] = true;
                        $jacocoInit[61] = true;
                        return f;
                    }
                }
            }
        }
        WXLogUtils.e("Argument Warning ! value is " + trim + "And default Value:" + Float.NaN);
        $jacocoInit[41] = true;
        return f;
    }

    private static float transferWx(String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[62] = true;
            return 0.0f;
        }
        $jacocoInit[63] = true;
        if (!str.endsWith("wx")) {
            $jacocoInit[64] = true;
        } else {
            $jacocoInit[65] = true;
            str = str.substring(0, str.indexOf("wx"));
            $jacocoInit[66] = true;
        }
        Float valueOf = Float.valueOf(Float.parseFloat(str));
        $jacocoInit[67] = true;
        float parseFloat = Float.parseFloat(WXEnvironment.getConfig().get("scale"));
        $jacocoInit[68] = true;
        float floatValue = ((parseFloat * valueOf.floatValue()) * ((float) i)) / ((float) WXViewUtils.getScreenWidth());
        $jacocoInit[69] = true;
        return floatValue;
    }

    public static float fastGetFloat(String str, int i) {
        boolean z;
        int i2;
        boolean[] $jacocoInit = $jacocoInit();
        float f = 0.0f;
        if (!TextUtils.isEmpty(str)) {
            $jacocoInit[70] = true;
            int i3 = 0;
            if (str.charAt(0) == '-') {
                $jacocoInit[71] = true;
                i2 = 1;
                z = false;
            } else {
                if (str.charAt(0) != '+') {
                    $jacocoInit[72] = true;
                    i2 = 0;
                } else {
                    $jacocoInit[73] = true;
                    i2 = 1;
                }
                z = true;
            }
            $jacocoInit[74] = true;
            while (true) {
                if (i2 >= str.length()) {
                    $jacocoInit[75] = true;
                    break;
                }
                char charAt = str.charAt(i2);
                if (charAt < '0') {
                    $jacocoInit[76] = true;
                    break;
                } else if (charAt > '9') {
                    $jacocoInit[77] = true;
                    break;
                } else {
                    f = ((f * 10.0f) + ((float) charAt)) - 48.0f;
                    i2++;
                    $jacocoInit[78] = true;
                }
            }
            if (i2 >= str.length()) {
                $jacocoInit[79] = true;
            } else {
                $jacocoInit[80] = true;
                if (str.charAt(i2) != '.') {
                    $jacocoInit[81] = true;
                } else {
                    int i4 = i2 + 1;
                    $jacocoInit[82] = true;
                    int i5 = 10;
                    while (true) {
                        if (i4 >= str.length()) {
                            $jacocoInit[83] = true;
                            break;
                        } else if (i3 >= i) {
                            $jacocoInit[84] = true;
                            break;
                        } else {
                            $jacocoInit[85] = true;
                            char charAt2 = str.charAt(i4);
                            if (charAt2 < '0') {
                                $jacocoInit[86] = true;
                                break;
                            } else if (charAt2 > '9') {
                                $jacocoInit[87] = true;
                                break;
                            } else {
                                f += ((float) (charAt2 - '0')) / ((float) i5);
                                i5 *= 10;
                                i4++;
                                i3++;
                                $jacocoInit[88] = true;
                            }
                        }
                    }
                }
            }
            if (z) {
                $jacocoInit[89] = true;
            } else {
                f *= -1.0f;
                $jacocoInit[90] = true;
            }
            $jacocoInit[91] = true;
            return f;
        }
        $jacocoInit[92] = true;
        return 0.0f;
    }

    public static float fastGetFloat(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        float fastGetFloat = fastGetFloat(str, Integer.MAX_VALUE);
        $jacocoInit[93] = true;
        return fastGetFloat;
    }

    public static int parseInt(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            if (TextUtils.isEmpty(str)) {
                $jacocoInit[94] = true;
            } else if (str.contains(".")) {
                $jacocoInit[95] = true;
            } else {
                $jacocoInit[96] = true;
                int parseInt = Integer.parseInt(str);
                $jacocoInit[97] = true;
                return parseInt;
            }
            $jacocoInit[98] = true;
        } catch (NumberFormatException e) {
            $jacocoInit[99] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[100] = true;
            } else {
                $jacocoInit[101] = true;
                WXLogUtils.e(WXLogUtils.getStackTrace(e));
                $jacocoInit[102] = true;
            }
        }
        $jacocoInit[103] = true;
        return 0;
    }

    public static int parseInt(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        int parseInt = parseInt(String.valueOf(obj));
        $jacocoInit[104] = true;
        return parseInt;
    }

    public static float parseFloat(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        float parseFloat = parseFloat(String.valueOf(obj));
        $jacocoInit[105] = true;
        return parseFloat;
    }

    public static float parseFloat(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            if (TextUtils.isEmpty(str)) {
                $jacocoInit[106] = true;
            } else if (TextUtils.equals(str, "null")) {
                $jacocoInit[107] = true;
            } else {
                $jacocoInit[108] = true;
                float parseFloat = Float.parseFloat(str);
                $jacocoInit[109] = true;
                return parseFloat;
            }
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[110] = true;
            } else {
                $jacocoInit[111] = true;
                WXLogUtils.e("WXUtils parseFloat illegal value is " + str);
                $jacocoInit[112] = true;
            }
            $jacocoInit[113] = true;
        } catch (NumberFormatException e) {
            $jacocoInit[114] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[115] = true;
            } else {
                $jacocoInit[116] = true;
                WXLogUtils.e(WXLogUtils.getStackTrace(e));
                $jacocoInit[117] = true;
            }
        }
        $jacocoInit[118] = true;
        return 0.0f;
    }

    public static int getInt(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        int intValue = getInteger(obj, 0).intValue();
        $jacocoInit[119] = true;
        return intValue;
    }

    /* JADX WARNING: Removed duplicated region for block: B:91:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0229  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Integer getInteger(@android.support.annotation.Nullable java.lang.Object r8, @android.support.annotation.Nullable java.lang.Integer r9) {
        /*
            boolean[] r0 = $jacocoInit()
            r1 = 1
            if (r8 != 0) goto L_0x000c
            r8 = 120(0x78, float:1.68E-43)
            r0[r8] = r1
            return r9
        L_0x000c:
            java.lang.String r2 = r8.toString()
            java.lang.String r2 = r2.trim()
            r3 = 121(0x79, float:1.7E-43)
            r0[r3] = r1
            android.support.v4.util.LruCache<java.lang.String, java.lang.Integer> r3 = sCache
            java.lang.Object r3 = r3.get(r2)
            java.lang.Integer r3 = (java.lang.Integer) r3
            if (r3 == 0) goto L_0x0027
            r8 = 122(0x7a, float:1.71E-43)
            r0[r8] = r1
            return r3
        L_0x0027:
            java.lang.String r3 = ""
            r4 = 123(0x7b, float:1.72E-43)
            r0[r4] = r1
            int r4 = r2.length()
            r5 = 2
            if (r4 >= r5) goto L_0x0039
            r4 = 124(0x7c, float:1.74E-43)
            r0[r4] = r1
            goto L_0x004e
        L_0x0039:
            r3 = 125(0x7d, float:1.75E-43)
            r0[r3] = r1
            int r3 = r2.length()
            int r3 = r3 - r5
            int r4 = r2.length()
            java.lang.String r3 = r2.substring(r3, r4)
            r4 = 126(0x7e, float:1.77E-43)
            r0[r4] = r1
        L_0x004e:
            java.lang.String r4 = "wx"
            boolean r4 = android.text.TextUtils.equals(r4, r3)
            if (r4 == 0) goto L_0x00db
            r3 = 127(0x7f, float:1.78E-43)
            r0[r3] = r1
            boolean r3 = com.taobao.weex.WXEnvironment.isApkDebugable()
            if (r3 != 0) goto L_0x0065
            r3 = 128(0x80, float:1.794E-43)
            r0[r3] = r1
            goto L_0x008a
        L_0x0065:
            r3 = 129(0x81, float:1.81E-43)
            r0[r3] = r1
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "the value of "
            r3.append(r4)
            r3.append(r8)
            java.lang.String r4 = " use wx unit, which will be not supported soon after."
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r4 = 130(0x82, float:1.82E-43)
            r0[r4] = r1
            com.taobao.weex.utils.WXLogUtils.w(r3)
            r3 = 131(0x83, float:1.84E-43)
            r0[r3] = r1     // Catch:{ NumberFormatException -> 0x009d, Exception -> 0x009b }
        L_0x008a:
            r3 = 750(0x2ee, float:1.051E-42)
            float r3 = transferWx(r2, r3)     // Catch:{ NumberFormatException -> 0x009d, Exception -> 0x009b }
            int r3 = (int) r3     // Catch:{ NumberFormatException -> 0x009d, Exception -> 0x009b }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ NumberFormatException -> 0x009d, Exception -> 0x009b }
            r8 = 132(0x84, float:1.85E-43)
            r0[r8] = r1
            goto L_0x021e
        L_0x009b:
            r3 = move-exception
            goto L_0x009f
        L_0x009d:
            r3 = move-exception
            goto L_0x00bc
        L_0x009f:
            r4 = 135(0x87, float:1.89E-43)
            r0[r4] = r1
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Argument error! value is "
            r4.append(r5)
            r4.append(r8)
            java.lang.String r8 = r4.toString()
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r8, (java.lang.Throwable) r3)
            r8 = 136(0x88, float:1.9E-43)
            r0[r8] = r1
            goto L_0x00d8
        L_0x00bc:
            r4 = 133(0x85, float:1.86E-43)
            r0[r4] = r1
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Argument format error! value is "
            r4.append(r5)
            r4.append(r8)
            java.lang.String r8 = r4.toString()
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r8, (java.lang.Throwable) r3)
            r8 = 134(0x86, float:1.88E-43)
            r0[r8] = r1
        L_0x00d8:
            r3 = r9
            goto L_0x021e
        L_0x00db:
            java.lang.String r4 = "px"
            boolean r3 = android.text.TextUtils.equals(r4, r3)
            if (r3 != 0) goto L_0x018a
            r3 = 137(0x89, float:1.92E-43)
            r0[r3] = r1
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            if (r3 != 0) goto L_0x011c
            r3 = 150(0x96, float:2.1E-43)
            r0[r3] = r1     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            java.lang.String r3 = "."
            boolean r3 = r2.contains(r3)     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            if (r3 == 0) goto L_0x010b
            r3 = 151(0x97, float:2.12E-43)
            r0[r3] = r1     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            float r3 = parseFloat((java.lang.String) r2)     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            int r3 = (int) r3     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            r4 = 152(0x98, float:2.13E-43)
            r0[r4] = r1     // Catch:{ NumberFormatException -> 0x011a, Exception -> 0x0118 }
            goto L_0x0144
        L_0x010b:
            int r3 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            r4 = 153(0x99, float:2.14E-43)
            r0[r4] = r1     // Catch:{ NumberFormatException -> 0x011a, Exception -> 0x0118 }
            goto L_0x0144
        L_0x0118:
            r4 = move-exception
            goto L_0x014c
        L_0x011a:
            r4 = move-exception
            goto L_0x016c
        L_0x011c:
            boolean r3 = com.taobao.weex.WXEnvironment.isApkDebugable()     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            if (r3 != 0) goto L_0x0127
            r3 = 154(0x9a, float:2.16E-43)
            r0[r3] = r1     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            goto L_0x0143
        L_0x0127:
            r3 = 155(0x9b, float:2.17E-43)
            r0[r3] = r1     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            r3.<init>()     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            java.lang.String r4 = "Argument value is null, df is"
            r3.append(r4)     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            r3.append(r9)     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            java.lang.String r3 = r3.toString()     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            com.taobao.weex.utils.WXLogUtils.e(r3)     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
            r3 = 156(0x9c, float:2.19E-43)
            r0[r3] = r1     // Catch:{ NumberFormatException -> 0x016a, Exception -> 0x014a }
        L_0x0143:
            r3 = r9
        L_0x0144:
            r8 = 157(0x9d, float:2.2E-43)
            r0[r8] = r1
            goto L_0x021e
        L_0x014a:
            r4 = move-exception
            r3 = r9
        L_0x014c:
            r5 = 160(0xa0, float:2.24E-43)
            r0[r5] = r1
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Argument error! value is "
            r5.append(r6)
            r5.append(r8)
            java.lang.String r8 = r5.toString()
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r8, (java.lang.Throwable) r4)
            r8 = 161(0xa1, float:2.26E-43)
            r0[r8] = r1
            goto L_0x021e
        L_0x016a:
            r4 = move-exception
            r3 = r9
        L_0x016c:
            r5 = 158(0x9e, float:2.21E-43)
            r0[r5] = r1
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Argument format error! value is "
            r5.append(r6)
            r5.append(r8)
            java.lang.String r8 = r5.toString()
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r8, (java.lang.Throwable) r4)
            r8 = 159(0x9f, float:2.23E-43)
            r0[r8] = r1
            goto L_0x021e
        L_0x018a:
            r3 = 138(0x8a, float:1.93E-43)
            r0[r3] = r1     // Catch:{ NumberFormatException -> 0x01ff, Exception -> 0x01e0 }
            r3 = 0
            int r4 = r2.length()     // Catch:{ NumberFormatException -> 0x01ff, Exception -> 0x01e0 }
            int r4 = r4 - r5
            java.lang.String r3 = r2.substring(r3, r4)     // Catch:{ NumberFormatException -> 0x01ff, Exception -> 0x01e0 }
            r4 = 139(0x8b, float:1.95E-43)
            r0[r4] = r1     // Catch:{ NumberFormatException -> 0x01ff, Exception -> 0x01e0 }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ NumberFormatException -> 0x01ff, Exception -> 0x01e0 }
            if (r4 == 0) goto L_0x01a7
            r4 = 140(0x8c, float:1.96E-43)
            r0[r4] = r1     // Catch:{ NumberFormatException -> 0x01ff, Exception -> 0x01e0 }
            goto L_0x01b3
        L_0x01a7:
            java.lang.String r4 = "."
            boolean r4 = r3.contains(r4)     // Catch:{ NumberFormatException -> 0x01ff, Exception -> 0x01e0 }
            if (r4 != 0) goto L_0x01ca
            r4 = 141(0x8d, float:1.98E-43)
            r0[r4] = r1     // Catch:{ NumberFormatException -> 0x01ff, Exception -> 0x01e0 }
        L_0x01b3:
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x01ff, Exception -> 0x01e0 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ NumberFormatException -> 0x01ff, Exception -> 0x01e0 }
            r4 = 144(0x90, float:2.02E-43)
            r0[r4] = r1     // Catch:{ NumberFormatException -> 0x01c5, Exception -> 0x01c0 }
            goto L_0x01db
        L_0x01c0:
            r4 = move-exception
            r7 = r4
            r4 = r3
            r3 = r7
            goto L_0x01e2
        L_0x01c5:
            r4 = move-exception
            r7 = r4
            r4 = r3
            r3 = r7
            goto L_0x0201
        L_0x01ca:
            r4 = 142(0x8e, float:1.99E-43)
            r0[r4] = r1     // Catch:{ NumberFormatException -> 0x01ff, Exception -> 0x01e0 }
            float r3 = parseFloat((java.lang.String) r3)     // Catch:{ NumberFormatException -> 0x01ff, Exception -> 0x01e0 }
            int r3 = (int) r3     // Catch:{ NumberFormatException -> 0x01ff, Exception -> 0x01e0 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ NumberFormatException -> 0x01ff, Exception -> 0x01e0 }
            r4 = 143(0x8f, float:2.0E-43)
            r0[r4] = r1     // Catch:{ NumberFormatException -> 0x01c5, Exception -> 0x01c0 }
        L_0x01db:
            r8 = 145(0x91, float:2.03E-43)
            r0[r8] = r1
            goto L_0x021e
        L_0x01e0:
            r3 = move-exception
            r4 = r9
        L_0x01e2:
            r5 = 148(0x94, float:2.07E-43)
            r0[r5] = r1
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Argument error! value is "
            r5.append(r6)
            r5.append(r8)
            java.lang.String r8 = r5.toString()
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r8, (java.lang.Throwable) r3)
            r8 = 149(0x95, float:2.09E-43)
            r0[r8] = r1
            goto L_0x021d
        L_0x01ff:
            r3 = move-exception
            r4 = r9
        L_0x0201:
            r5 = 146(0x92, float:2.05E-43)
            r0[r5] = r1
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Argument format error! value is "
            r5.append(r6)
            r5.append(r8)
            java.lang.String r8 = r5.toString()
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r8, (java.lang.Throwable) r3)
            r8 = 147(0x93, float:2.06E-43)
            r0[r8] = r1
        L_0x021d:
            r3 = r4
        L_0x021e:
            boolean r8 = r3.equals(r9)
            if (r8 == 0) goto L_0x0229
            r8 = 162(0xa2, float:2.27E-43)
            r0[r8] = r1
            goto L_0x0236
        L_0x0229:
            r8 = 163(0xa3, float:2.28E-43)
            r0[r8] = r1
            android.support.v4.util.LruCache<java.lang.String, java.lang.Integer> r8 = sCache
            r8.put(r2, r3)
            r8 = 164(0xa4, float:2.3E-43)
            r0[r8] = r1
        L_0x0236:
            r8 = 165(0xa5, float:2.31E-43)
            r0[r8] = r1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.utils.WXUtils.getInteger(java.lang.Object, java.lang.Integer):java.lang.Integer");
    }

    @Deprecated
    public static long getLong(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[166] = true;
            return 0;
        }
        $jacocoInit[167] = true;
        String trim = obj.toString().trim();
        $jacocoInit[168] = true;
        if (trim.endsWith("wx")) {
            $jacocoInit[169] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[170] = true;
            } else {
                $jacocoInit[171] = true;
                WXLogUtils.w("the value of " + obj + " use wx unit, which will be not supported soon after.");
                try {
                    $jacocoInit[172] = true;
                } catch (NumberFormatException e) {
                    $jacocoInit[174] = true;
                    WXLogUtils.e("Argument format error! value is " + obj, (Throwable) e);
                    $jacocoInit[175] = true;
                    $jacocoInit[191] = true;
                    return 0;
                } catch (Exception e2) {
                    $jacocoInit[176] = true;
                    WXLogUtils.e("Argument error! value is " + obj, (Throwable) e2);
                    $jacocoInit[177] = true;
                    $jacocoInit[191] = true;
                    return 0;
                }
            }
            long transferWx = (long) transferWx(trim, 750);
            $jacocoInit[173] = true;
            return transferWx;
        } else if (!trim.endsWith("px")) {
            $jacocoInit[178] = true;
            try {
                long parseLong = Long.parseLong(trim);
                $jacocoInit[186] = true;
                return parseLong;
            } catch (NumberFormatException e3) {
                $jacocoInit[187] = true;
                WXLogUtils.e("Argument format error! value is " + obj, (Throwable) e3);
                $jacocoInit[188] = true;
                $jacocoInit[191] = true;
                return 0;
            } catch (Exception e4) {
                $jacocoInit[189] = true;
                WXLogUtils.e("Argument error! value is " + obj, (Throwable) e4);
                $jacocoInit[190] = true;
                $jacocoInit[191] = true;
                return 0;
            }
        } else {
            try {
                $jacocoInit[179] = true;
                String substring = trim.substring(0, trim.indexOf("px"));
                $jacocoInit[180] = true;
                long parseLong2 = Long.parseLong(substring);
                $jacocoInit[181] = true;
                return parseLong2;
            } catch (NumberFormatException e5) {
                $jacocoInit[182] = true;
                WXLogUtils.e("Argument format error! value is " + obj, (Throwable) e5);
                $jacocoInit[183] = true;
                $jacocoInit[191] = true;
                return 0;
            } catch (Exception e6) {
                $jacocoInit[184] = true;
                WXLogUtils.e("Argument error! value is " + obj, (Throwable) e6);
                $jacocoInit[185] = true;
                $jacocoInit[191] = true;
                return 0;
            }
        }
    }

    @Deprecated
    public static double getDouble(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[192] = true;
            return 0.0d;
        }
        $jacocoInit[193] = true;
        String trim = obj.toString().trim();
        $jacocoInit[194] = true;
        if (trim.endsWith("wx")) {
            $jacocoInit[195] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[196] = true;
            } else {
                $jacocoInit[197] = true;
                WXLogUtils.w("the value of " + obj + " use wx unit, which will be not supported soon after.");
                try {
                    $jacocoInit[198] = true;
                } catch (NumberFormatException e) {
                    $jacocoInit[200] = true;
                    WXLogUtils.e("Argument format error! value is " + obj, (Throwable) e);
                    $jacocoInit[201] = true;
                    $jacocoInit[217] = true;
                    return 0.0d;
                } catch (Exception e2) {
                    $jacocoInit[202] = true;
                    WXLogUtils.e("Argument error! value is " + obj, (Throwable) e2);
                    $jacocoInit[203] = true;
                    $jacocoInit[217] = true;
                    return 0.0d;
                }
            }
            double transferWx = (double) transferWx(trim, 750);
            $jacocoInit[199] = true;
            return transferWx;
        } else if (!trim.endsWith("px")) {
            $jacocoInit[204] = true;
            try {
                double parseDouble = Double.parseDouble(trim);
                $jacocoInit[212] = true;
                return parseDouble;
            } catch (NumberFormatException e3) {
                $jacocoInit[213] = true;
                WXLogUtils.e("Argument format error! value is " + obj, (Throwable) e3);
                $jacocoInit[214] = true;
                $jacocoInit[217] = true;
                return 0.0d;
            } catch (Exception e4) {
                $jacocoInit[215] = true;
                WXLogUtils.e("Argument error! value is " + obj, (Throwable) e4);
                $jacocoInit[216] = true;
                $jacocoInit[217] = true;
                return 0.0d;
            }
        } else {
            try {
                $jacocoInit[205] = true;
                String substring = trim.substring(0, trim.indexOf("px"));
                $jacocoInit[206] = true;
                double parseDouble2 = Double.parseDouble(substring);
                $jacocoInit[207] = true;
                return parseDouble2;
            } catch (NumberFormatException e5) {
                $jacocoInit[208] = true;
                WXLogUtils.e("Argument format error! value is " + obj, (Throwable) e5);
                $jacocoInit[209] = true;
                $jacocoInit[217] = true;
                return 0.0d;
            } catch (Exception e6) {
                $jacocoInit[210] = true;
                WXLogUtils.e("Argument error! value is " + obj, (Throwable) e6);
                $jacocoInit[211] = true;
                $jacocoInit[217] = true;
                return 0.0d;
            }
        }
    }

    public static boolean isTabletDevice() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = false;
        try {
            if ((WXEnvironment.getApplication().getResources().getConfiguration().screenLayout & 15) >= 3) {
                $jacocoInit[218] = true;
                z = true;
            } else {
                $jacocoInit[219] = true;
            }
            $jacocoInit[220] = true;
            return z;
        } catch (Exception unused) {
            $jacocoInit[221] = true;
            return false;
        }
    }

    public static Boolean getBoolean(@Nullable Object obj, @Nullable Boolean bool) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[222] = true;
            return bool;
        } else if (TextUtils.equals("false", obj.toString())) {
            $jacocoInit[223] = true;
            $jacocoInit[224] = true;
            return false;
        } else if (TextUtils.equals("true", obj.toString())) {
            $jacocoInit[225] = true;
            $jacocoInit[226] = true;
            return true;
        } else {
            $jacocoInit[227] = true;
            return bool;
        }
    }

    public static long getAvailMemory(Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[228] = true;
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        $jacocoInit[229] = true;
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        $jacocoInit[230] = true;
        WXLogUtils.w("app AvailMemory ---->>>" + (memoryInfo.availMem / 1048576));
        long j = memoryInfo.availMem / 1048576;
        $jacocoInit[231] = true;
        return j;
    }

    public static String getString(@Nullable Object obj, @Nullable String str) {
        String str2;
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[232] = true;
            return str;
        }
        if (obj instanceof String) {
            str2 = (String) obj;
            $jacocoInit[233] = true;
        } else {
            str2 = obj.toString();
            $jacocoInit[234] = true;
        }
        $jacocoInit[235] = true;
        return str2;
    }

    public static int parseUnitOrPercent(String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        int lastIndexOf = str.lastIndexOf(37);
        if (lastIndexOf != -1) {
            $jacocoInit[236] = true;
            int parsePercent = parsePercent(str.substring(0, lastIndexOf), i);
            $jacocoInit[237] = true;
            return parsePercent;
        }
        int parseInt = parseInt(str);
        $jacocoInit[238] = true;
        return parseInt;
    }

    private static int parsePercent(String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        int parseFloat = (int) ((Float.parseFloat(str) / 100.0f) * ((float) i));
        $jacocoInit[239] = true;
        return parseFloat;
    }

    public static String getBundleBanner(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[240] = true;
        int indexOf = str.indexOf("/*!");
        if (indexOf == -1) {
            $jacocoInit[241] = true;
            return null;
        }
        int length = indexOf + "/*!".length();
        $jacocoInit[242] = true;
        int indexLineBreak = indexLineBreak(str, length);
        if (indexLineBreak == -1) {
            $jacocoInit[243] = true;
            return null;
        }
        String substring = str.substring(length, indexLineBreak);
        $jacocoInit[244] = true;
        int parseInt = Integer.parseInt(substring);
        $jacocoInit[245] = true;
        int i = indexLineBreak + 1;
        String substring2 = str.substring(i, parseInt + i);
        $jacocoInit[246] = true;
        int lastIndexOf = substring2.lastIndexOf("!*/");
        if (lastIndexOf == -1) {
            $jacocoInit[247] = true;
            return null;
        }
        int i2 = 0;
        String substring3 = substring2.substring(0, lastIndexOf);
        $jacocoInit[248] = true;
        StringBuilder sb = new StringBuilder();
        $jacocoInit[249] = true;
        String[] splitLineBreak = splitLineBreak(substring3);
        int length2 = splitLineBreak.length;
        $jacocoInit[250] = true;
        while (i2 < length2) {
            String str2 = splitLineBreak[i2];
            $jacocoInit[251] = true;
            sb.append(str2.replaceFirst("\\*", ""));
            i2++;
            $jacocoInit[252] = true;
        }
        String sb2 = sb.toString();
        $jacocoInit[253] = true;
        return sb2;
    }

    private static int indexLineBreak(String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[254] = true;
        int indexOf = str.indexOf("\r", i);
        if (indexOf != -1) {
            $jacocoInit[255] = true;
        } else {
            $jacocoInit[256] = true;
            indexOf = str.indexOf("\n", i);
            $jacocoInit[257] = true;
        }
        if (indexOf != -1) {
            $jacocoInit[258] = true;
        } else {
            $jacocoInit[259] = true;
            indexOf = str.indexOf("\r\n", i);
            $jacocoInit[260] = true;
        }
        $jacocoInit[261] = true;
        return indexOf;
    }

    private static String[] splitLineBreak(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[262] = true;
        String[] split = str.split("\r");
        if (split.length != 1) {
            $jacocoInit[263] = true;
        } else {
            $jacocoInit[264] = true;
            split = str.split("\n");
            $jacocoInit[265] = true;
        }
        if (split.length != 1) {
            $jacocoInit[266] = true;
        } else {
            $jacocoInit[267] = true;
            split = str.split("\r\n");
            $jacocoInit[268] = true;
        }
        $jacocoInit[269] = true;
        return split;
    }

    public static int getNumberInt(Object obj, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[270] = true;
            return i;
        } else if (!(obj instanceof Number)) {
            $jacocoInit[271] = true;
            try {
                String obj2 = obj.toString();
                $jacocoInit[274] = true;
                if (obj2.indexOf(46) < 0) {
                    $jacocoInit[275] = true;
                    int parseInt = Integer.parseInt(obj2);
                    $jacocoInit[278] = true;
                    return parseInt;
                }
                $jacocoInit[276] = true;
                int parseFloat = (int) Float.parseFloat(obj.toString());
                $jacocoInit[277] = true;
                return parseFloat;
            } catch (Exception unused) {
                $jacocoInit[279] = true;
                return i;
            }
        } else {
            $jacocoInit[272] = true;
            int intValue = ((Number) obj).intValue();
            $jacocoInit[273] = true;
            return intValue;
        }
    }

    public static long getFixUnixTime() {
        boolean[] $jacocoInit = $jacocoInit();
        long uptimeMillis = sInterval + SystemClock.uptimeMillis();
        $jacocoInit[280] = true;
        return uptimeMillis;
    }
}
