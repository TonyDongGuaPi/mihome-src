package com.xiaomi.mishopsdk.utils;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.util.DensityUtil;
import com.xiaomi.shop2.util.SystemPropertiesProxy;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;

@Deprecated
public class NotchPhoneTitlebarHandler {
    private static final String TAG = "TitleBarTopPaddingHandler";
    public static final boolean sIsNotchPhone;
    private static int sNotchHeight = 0;

    static {
        boolean z = true;
        if (1 != SystemPropertiesProxy.getInt(ShopApp.instance, "ro.miui.notch", 0).intValue()) {
            z = false;
        }
        sIsNotchPhone = z;
    }

    public static int getNotchHeight() {
        int identifier;
        if (sNotchHeight == 0 && (identifier = ShopApp.instance.getResources().getIdentifier(PreferenceConstantsInOpenSdk.B, "dimen", "android")) > 0) {
            sNotchHeight = ShopApp.instance.getResources().getDimensionPixelSize(identifier);
        }
        return sNotchHeight;
    }

    public NotchPhoneTitlebarHandler(Context context) {
    }

    public void handleTitleBar(View view) {
        if (sIsNotchPhone) {
            int notchHeight = getNotchHeight();
            view.getLayoutParams().height = DensityUtil.dip2px(48.0f) + notchHeight;
            view.setPadding(0, notchHeight, 0, 0);
        }
    }

    public void handlePlaceHolderBg(View view) {
        if (sIsNotchPhone) {
            int notchHeight = getNotchHeight();
            view.getLayoutParams().height = DensityUtil.dip2px(48.0f) + notchHeight + DensityUtil.dip2px(27.0f);
        }
    }

    public void handleListViewPadding(AbsListView absListView) {
        if (sIsNotchPhone) {
            absListView.setPadding(0, DensityUtil.dip2px(48.0f) + getNotchHeight() + DensityUtil.dip2px(27.0f), 0, 0);
        }
    }

    public int getShopTitleBarHeight() {
        return sIsNotchPhone ? DensityUtil.dip2px(48.0f) + getNotchHeight() : DensityUtil.dip2px(68.0f);
    }
}
