package com.xiaomi.smarthome.library.common.widget.nestscroll.internal;

import android.text.TextUtils;
import android.util.SparseIntArray;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.miio.areainfo.AreaInfo;
import com.xiaomi.smarthome.miio.areainfo.AreaInfoManager;

public class WeatherHeaderUtil {
    public static final int A = 505;
    public static final int B = 506;
    public static final int C = 507;
    public static final int D = 508;
    private static final int E = 1;
    private static final int F = 2;
    private static final int G = 3;
    private static final int H = 4;
    private static final int I = 5;
    private static final int J = 6;
    private static final int K = -1;
    private static final int L = -10241306;
    private static final int M = -4138523;
    private static final int N = -10588761;
    private static final int O = -9095549;
    private static SparseIntArray P = new SparseIntArray();
    private static SparseIntArray Q = new SparseIntArray();

    /* renamed from: a  reason: collision with root package name */
    public static final int f19034a = 0;
    public static final int b = 100;
    public static final int c = 200;
    public static final int d = 300;
    public static final int e = 400;
    public static final int f = 500;
    public static final int g = 600;
    public static final int h = 700;
    public static final int i = 800;
    public static final int j = 401;
    public static final int k = 402;
    public static final int l = 403;
    public static final int m = 404;
    public static final int n = 405;
    public static final int o = 406;
    public static final int p = 407;
    public static final int q = 408;
    public static final int r = 409;
    public static final int s = 410;
    public static final int t = 411;
    public static final int u = 412;
    public static final int v = 413;
    public static final int w = 501;
    public static final int x = 502;
    public static final int y = 503;
    public static final int z = 504;

    public static int a(int i2) {
        switch (i2) {
            case 401:
                return 1;
            case 402:
                return 1;
            case 403:
                return 2;
            case 404:
                return 1;
            case 405:
                return 3;
            case 406:
                return 2;
            case 407:
                return 1;
            case 408:
                return 1;
            case 409:
                return 1;
            case 410:
                return 4;
            case 411:
                return 3;
            case 412:
                return 5;
            case 413:
                return 4;
            default:
                switch (i2) {
                    case 501:
                        return 1;
                    case 502:
                        return 2;
                    case 503:
                        return 1;
                    case 504:
                        return 3;
                    case 505:
                        return 2;
                    case 506:
                        return 1;
                    case 507:
                        return 4;
                    case 508:
                        return 3;
                    default:
                        return 0;
                }
        }
    }

    public static int[] a(String str, String str2) {
        if (P == null || P.size() == 0 || Q == null || Q.size() == 0) {
            b();
        }
        int[] d2 = d(str);
        int[] iArr = new int[9];
        iArr[0] = a(str2);
        iArr[2] = a();
        iArr[3] = b(str2);
        iArr[4] = c(str2);
        iArr[5] = b(d2[0], str2);
        iArr[6] = a(d2[0], str2);
        iArr[7] = d2[0];
        iArr[8] = d2[1];
        if (d2 == null) {
            iArr[1] = R.drawable.transparent;
            return iArr;
        }
        iArr[1] = Q.get(d2[0], R.drawable.transparent);
        return iArr;
    }

    private static int a(int i2, String str) {
        if (i2 == 300) {
            return b(i2, str);
        }
        if (i2 == 500 || i2 == 400) {
            return 0;
        }
        return b(i2, str);
    }

    private static int b(int i2, String str) {
        if (i2 == 300) {
            return e(str);
        }
        if (i2 == 500) {
            return f(str);
        }
        if (i2 == 400) {
            return g(str);
        }
        return 50;
    }

    private static int e(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt == -1 || parseInt <= 50 || parseInt <= 100 || parseInt <= 150) {
                return 50;
            }
            if (parseInt <= 200) {
                return 40;
            }
            return parseInt <= 300 ? 30 : 50;
        } catch (Exception unused) {
            return 50;
        }
    }

    private static int f(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt == -1 || parseInt <= 50) {
                return 40;
            }
            if (parseInt <= 100) {
                return 50;
            }
            if (parseInt > 150 && parseInt > 200) {
                return parseInt <= 300 ? 30 : 5;
            }
            return 40;
        } catch (Exception unused) {
            return 40;
        }
    }

    private static int g(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt == -1 || parseInt <= 50) {
                return 40;
            }
            if (parseInt <= 100 || parseInt <= 150) {
                return 60;
            }
            if (parseInt <= 200) {
                return 40;
            }
            return parseInt <= 300 ? 30 : 30;
        } catch (Exception unused) {
            return 40;
        }
    }

    public static int a() {
        int c2 = c();
        if (c2 == -1) {
            return R.drawable.weather_nestscroll_bg;
        }
        switch (c2) {
            case 1:
                return R.drawable.weather_nestscroll_bg_beijing;
            case 2:
                return R.drawable.weather_nestscroll_bg_shanghai;
            case 3:
                return R.drawable.weather_nestscroll_bg_guangzhou;
            case 4:
                return R.drawable.weather_nestscroll_bg_shenzhen;
            case 5:
                return R.drawable.weather_nestscroll_bg_nanjing;
            case 6:
                return R.drawable.weather_nestscroll_bg_chengdu;
            default:
                return R.drawable.weather_nestscroll_bg;
        }
    }

    private static int c() {
        AreaInfo I2 = AreaInfoManager.a().I();
        if (I2 == null) {
            return -1;
        }
        return h(I2.j() + I2.k());
    }

    private static int h(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (str.contains("北京") || str.toLowerCase().contains("beijing")) {
            return 1;
        }
        if (str.contains("上海") || str.toLowerCase().contains("shanghai")) {
            return 2;
        }
        if (str.contains("广州") || str.toLowerCase().contains("guangzhou")) {
            return 3;
        }
        if (str.contains("深圳") || str.toLowerCase().contains("shenzhen")) {
            return 4;
        }
        if (str.contains("南京") || str.toLowerCase().contains("nanjing")) {
            return 5;
        }
        if (str.contains("成都") || str.toLowerCase().contains("chengdu")) {
            return 6;
        }
        return -1;
    }

    static void b() {
        Q.append(0, R.drawable.transparent);
        Q.append(100, R.drawable.transparent);
        Q.append(200, R.drawable.transparent);
        Q.append(300, R.drawable.weather_cloudy);
        Q.append(400, R.drawable.transparent);
        Q.append(500, R.drawable.transparent);
        Q.append(600, R.drawable.transparent);
        Q.append(700, R.drawable.transparent);
        Q.append(800, R.drawable.transparent);
        P.append(0, L);
        P.append(100, L);
        P.append(200, M);
        P.append(300, L);
        P.append(400, M);
        P.append(600, N);
        P.append(700, O);
        P.append(800, M);
        P.append(401, M);
        P.append(402, M);
        P.append(403, M);
        P.append(404, M);
        P.append(405, M);
        P.append(406, M);
        P.append(407, M);
        P.append(408, M);
        P.append(409, M);
        P.append(410, M);
        P.append(411, M);
        P.append(412, M);
        P.append(413, M);
        P.append(501, M);
        P.append(502, M);
        P.append(503, M);
        P.append(504, M);
        P.append(505, M);
        P.append(506, M);
        P.append(507, M);
        P.append(508, M);
    }

    public static int a(String str) {
        int i2 = R.color.location_weather_50;
        try {
            Integer valueOf = Integer.valueOf(Integer.parseInt(str));
            if (valueOf.intValue() != -1 && valueOf.intValue() > 50) {
                i2 = valueOf.intValue() <= 100 ? R.color.location_weather_100 : valueOf.intValue() <= 150 ? R.color.location_weather_150 : valueOf.intValue() <= 200 ? R.color.location_weather_200 : valueOf.intValue() <= 300 ? R.color.location_weather_300 : R.color.location_weather_x;
            }
            return SHApplication.getAppContext().getResources().getColor(i2);
        } catch (Exception unused) {
            return SHApplication.getAppContext().getResources().getColor(R.color.location_weather_50);
        }
    }

    public static int b(String str) {
        try {
            Integer valueOf = Integer.valueOf(Integer.parseInt(str));
            if (valueOf.intValue() == -1 || valueOf.intValue() <= 50 || valueOf.intValue() <= 100 || valueOf.intValue() <= 150 || valueOf.intValue() <= 200) {
                return 10;
            }
            int intValue = valueOf.intValue();
            return 5;
        } catch (Exception unused) {
            return 10;
        }
    }

    public static int c(String str) {
        try {
            Integer valueOf = Integer.valueOf(Integer.parseInt(str));
            if (valueOf.intValue() == -1 || valueOf.intValue() <= 50) {
                return R.string.air_50;
            }
            if (valueOf.intValue() <= 100) {
                return R.string.air_100;
            }
            if (valueOf.intValue() <= 150) {
                return R.string.air_150;
            }
            if (valueOf.intValue() <= 200) {
                return R.string.air_200;
            }
            return valueOf.intValue() <= 300 ? R.string.air_300 : R.string.air_x;
        } catch (Exception unused) {
            return R.string.area_air_quality;
        }
    }

    public static int[] d(String str) {
        int[] iArr = {100, 100};
        if (TextUtils.isEmpty(str)) {
            return iArr;
        }
        if (TextUtils.equals(str, "晴")) {
            iArr[0] = 100;
            iArr[1] = 100;
        } else if (TextUtils.equals(str, "阴")) {
            iArr[0] = 200;
            iArr[1] = 200;
        } else if (TextUtils.equals(str, "多云")) {
            iArr[0] = 300;
            iArr[1] = 300;
        } else if (TextUtils.equals(str, "雷阵雨")) {
            iArr[0] = 400;
            iArr[1] = 401;
        } else if (TextUtils.equals(str, "小雨")) {
            iArr[0] = 400;
            iArr[1] = 402;
        } else if (TextUtils.equals(str, "中雨")) {
            iArr[0] = 400;
            iArr[1] = 403;
        } else if (TextUtils.equals(str, "小到中雨")) {
            iArr[0] = 400;
            iArr[1] = 404;
        } else if (TextUtils.equals(str, "大雨")) {
            iArr[0] = 400;
            iArr[1] = 405;
        } else if (TextUtils.equals(str, "中到大雨")) {
            iArr[0] = 400;
            iArr[1] = 406;
        } else if (TextUtils.equals(str, "阵雨")) {
            iArr[0] = 400;
            iArr[1] = 407;
        } else if (TextUtils.equals(str, "雨夹雪")) {
            iArr[0] = 400;
            iArr[1] = 408;
        } else if (TextUtils.equals(str, "冻雨")) {
            iArr[0] = 400;
            iArr[1] = 409;
        } else if (TextUtils.equals(str, "暴雨")) {
            iArr[0] = 400;
            iArr[1] = 410;
        } else if (TextUtils.equals(str, "大到暴雨")) {
            iArr[0] = 400;
            iArr[1] = 411;
        } else if (TextUtils.equals(str, "大暴雨")) {
            iArr[0] = 400;
            iArr[1] = 412;
        } else if (TextUtils.equals(str, "暴雨到大暴雨")) {
            iArr[0] = 400;
            iArr[1] = 413;
        } else if (TextUtils.equals(str, "小雪")) {
            iArr[0] = 500;
            iArr[1] = 501;
        } else if (TextUtils.equals(str, "中雪")) {
            iArr[0] = 500;
            iArr[1] = 502;
        } else if (TextUtils.equals(str, "小到中雪")) {
            iArr[0] = 500;
            iArr[1] = 503;
        } else if (TextUtils.equals(str, "大雪")) {
            iArr[0] = 500;
            iArr[1] = 504;
        } else if (TextUtils.equals(str, "中到大雪")) {
            iArr[0] = 500;
            iArr[1] = 505;
        } else if (TextUtils.equals(str, "阵雪")) {
            iArr[0] = 500;
            iArr[1] = 506;
        } else if (TextUtils.equals(str, "暴雪")) {
            iArr[0] = 500;
            iArr[1] = 507;
        } else if (TextUtils.equals(str, "大到暴雪")) {
            iArr[0] = 500;
            iArr[1] = 508;
        } else if (TextUtils.equals(str, "雾")) {
            iArr[0] = 600;
            iArr[1] = 600;
        } else if (TextUtils.equals(str, "霾")) {
            iArr[0] = 600;
            iArr[1] = 600;
        } else if (TextUtils.equals(str, "浮尘")) {
            iArr[0] = 700;
            iArr[1] = 700;
        } else if (TextUtils.equals(str, "扬沙")) {
            iArr[0] = 700;
            iArr[1] = 700;
        } else if (TextUtils.equals(str, "强沙尘暴")) {
            iArr[0] = 700;
            iArr[1] = 700;
        } else if (TextUtils.equals(str, "沙尘")) {
            iArr[0] = 700;
            iArr[1] = 700;
        } else if (TextUtils.equals(str, "冰雹")) {
            iArr[0] = 800;
            iArr[1] = 800;
        } else {
            iArr[0] = 0;
            iArr[1] = 0;
        }
        return iArr;
    }
}
