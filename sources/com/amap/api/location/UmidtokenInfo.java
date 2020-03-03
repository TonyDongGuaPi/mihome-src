package com.amap.api.location;

import android.content.Context;
import android.os.Handler;
import com.loc.es;
import com.loc.x;

public class UmidtokenInfo {

    /* renamed from: a  reason: collision with root package name */
    static Handler f1257a = new Handler();
    static String b = null;
    static boolean c = true;
    /* access modifiers changed from: private */
    public static AMapLocationClient d;
    private static long e = 30000;

    static class a implements AMapLocationListener {
        a() {
        }

        public final void onLocationChanged(AMapLocation aMapLocation) {
            try {
                if (UmidtokenInfo.d != null) {
                    UmidtokenInfo.f1257a.removeCallbacksAndMessages((Object) null);
                    UmidtokenInfo.d.onDestroy();
                }
            } catch (Throwable th) {
                es.a(th, "UmidListener", "onLocationChanged");
            }
        }
    }

    public static String getUmidtoken() {
        return b;
    }

    public static void setLocAble(boolean z) {
        c = z;
    }

    public static synchronized void setUmidtoken(Context context, String str) {
        synchronized (UmidtokenInfo.class) {
            try {
                b = str;
                x.a(str);
                if (d == null && c) {
                    a aVar = new a();
                    d = new AMapLocationClient(context);
                    AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
                    aMapLocationClientOption.setOnceLocation(true);
                    aMapLocationClientOption.setNeedAddress(false);
                    d.setLocationOption(aMapLocationClientOption);
                    d.setLocationListener(aVar);
                    d.startLocation();
                    f1257a.postDelayed(new Runnable() {
                        public final void run() {
                            try {
                                if (UmidtokenInfo.d != null) {
                                    UmidtokenInfo.d.onDestroy();
                                }
                            } catch (Throwable th) {
                                es.a(th, "UmidListener", "postDelayed");
                            }
                        }
                    }, 30000);
                }
            } catch (Throwable th) {
                es.a(th, "UmidListener", "setUmidtoken");
            }
        }
    }
}
