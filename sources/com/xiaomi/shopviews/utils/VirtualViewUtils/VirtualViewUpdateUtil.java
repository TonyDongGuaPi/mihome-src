package com.xiaomi.shopviews.utils.VirtualViewUtils;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.base.utils.BackgroundThreadHandler;
import com.xiaomi.base.utils.Utils;
import com.xiaomi.shopviews.WidgetApplication;
import java.io.File;

public class VirtualViewUpdateUtil {

    /* renamed from: a  reason: collision with root package name */
    public static String f13228a = "";
    public static String b = "virtualview";

    public interface VirtualViewCallBack {
        void a();
    }

    public static void a(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            f13228a = filesDir.getAbsolutePath() + "/" + b;
            File file = new File(f13228a);
            if (!file.exists()) {
                file.mkdir();
            }
        }
    }

    public static void a(final String str, final String str2, final VirtualViewCallBack virtualViewCallBack) {
        a(WidgetApplication.f13060a);
        if (!TextUtils.isEmpty(f13228a) && !TextUtils.isEmpty(str) && !Utils.Preference.d(WidgetApplication.f13060a, b, "").equals(str2)) {
            BackgroundThreadHandler.a(new Runnable() {
                public void run() {
                    String str = VirtualViewUpdateUtil.f13228a + "/" + VirtualViewUpdateUtil.b;
                    if (VirtualViewPatchUtil.a(str, str + ".zip")) {
                        if (Utils.Files.b(str + ".zip", VirtualViewUpdateUtil.f13228a)) {
                            if (virtualViewCallBack != null) {
                                virtualViewCallBack.a();
                            }
                            Utils.Preference.c(WidgetApplication.f13060a, VirtualViewUpdateUtil.b, str2);
                        }
                    }
                }
            });
        }
    }
}
