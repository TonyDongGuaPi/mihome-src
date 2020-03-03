package com.youpin.weex.app.common;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.xiaomi.youpin.common.AppIdManager;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.common.util.TitleBarUtil;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.StoreApiProvider;
import com.xiaomi.youpin.youpin_common.UserAgent;
import com.youpin.weex.app.util.OpenUtils;
import java.util.HashMap;

public class WeexConstant {

    /* renamed from: a  reason: collision with root package name */
    public static HashMap<String, Object> f2515a = new HashMap<>();

    private WeexConstant() {
    }

    public static void a(Context context) {
        if (f2515a.size() <= 1) {
            f2515a.put("OsName", AppInfo.d());
            f2515a.put("OsVersion", AppInfo.e());
            f2515a.put("AppVersion", AppInfo.f());
            f2515a.put("DeviceModel", AppInfo.h());
            f2515a.put("DeviceId", AppIdManager.a().c());
            f2515a.put("WeexEngineVersion", OpenUtils.f);
            f2515a.put("MiotWeexSDKVersion", OpenUtils.e);
            f2515a.put("IMEI", AppIdManager.a().b());
            f2515a.put("IDFA", "");
            f2515a.put("Scheme", "");
            f2515a.put("StatusBarHeight", Integer.valueOf(TitleBarUtil.a(context)));
            StoreApiProvider b = StoreApiManager.a().b();
            f2515a.put("UserAgent", UserAgent.d());
            f2515a.put("AppName", AppInfo.c());
            f2515a.put(TbsCoreSettings.TBS_SETTINGS_APP_KEY, b.d());
            f2515a.put("DebugMode", Boolean.valueOf(b.f()));
            f2515a.put("Staging", Boolean.valueOf(b.f()));
            f2515a.put("SupportCustomerServiceChat", true);
            f2515a.put("haveXiaomiSDK", true);
            a((Window) null);
        }
    }

    public static void a(Window window) {
        int i;
        int i2;
        int i3;
        int i4;
        if (AppInfo.a() != null) {
            if (window != null) {
                View decorView = window.getDecorView();
                if (2 == AppInfo.a().getResources().getConfiguration().orientation) {
                    View findViewById = decorView.findViewById(16908290);
                    if (findViewById != null) {
                        i4 = findViewById.getWidth();
                        i3 = findViewById.getHeight();
                    } else {
                        return;
                    }
                } else {
                    Rect rect = new Rect();
                    decorView.getWindowVisibleDisplayFrame(rect);
                    i3 = rect.bottom;
                    i4 = rect.right;
                }
                int i5 = i4;
                i2 = i3;
                i = i5;
            } else {
                WindowManager windowManager = (WindowManager) AppInfo.a().getSystemService("window");
                if (windowManager != null) {
                    Display defaultDisplay = windowManager.getDefaultDisplay();
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    if (Build.VERSION.SDK_INT >= 17) {
                        defaultDisplay.getRealMetrics(displayMetrics);
                    } else {
                        defaultDisplay.getMetrics(displayMetrics);
                    }
                    i = displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels;
                    i2 = displayMetrics.heightPixels > displayMetrics.widthPixels ? displayMetrics.heightPixels : displayMetrics.widthPixels;
                } else {
                    return;
                }
            }
            f2515a.put("RealDisplayWidth", Integer.valueOf(i));
            f2515a.put("RealDisplayHeight", Integer.valueOf(i2));
        }
    }
}
