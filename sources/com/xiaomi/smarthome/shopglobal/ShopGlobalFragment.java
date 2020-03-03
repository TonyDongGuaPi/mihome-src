package com.xiaomi.smarthome.shopglobal;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.ui.HomeFragmentNew;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseFragmentInterface;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.page.PagerListener;
import com.xiaomi.smarthome.stat.STAT;

public class ShopGlobalFragment extends HomeFragmentNew implements BaseFragmentInterface, PagerListener {
    private static final String k = "ShopGlobalFragment";
    private long l = 0;
    private volatile boolean m = true;

    public boolean onBackPressed() {
        return false;
    }

    public void onPageSelected() {
    }

    public ShopGlobalFragment() {
        try {
            LogUtil.a(k, "ShopGlobalFragment new");
            if (ShopApp.h() == null) {
                ShopGlobalHelper.a(true);
            }
            String str = ServerCompact.j(getContext()) ? LocaleHelper.e : "";
            if (!TextUtils.isEmpty(str)) {
                LocaleHelper.a(str);
            }
            setArguments(new Bundle());
        } catch (Exception e) {
            e.printStackTrace();
            String str2 = k;
            LogUtilGrey.a(str2, "ShopGlobalFragment exception " + e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public final void a(String str, boolean z) {
        if (z) {
            this.l = STAT.b.a(this, str);
        } else if (this.l > 0) {
            STAT.b.a(this, this.l, str);
            this.l = 0;
        }
    }

    public FragmentActivity getValidActivity() {
        FragmentActivity activity = super.getActivity();
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) {
            return activity;
        }
        return null;
    }

    public boolean isValid() {
        return !this.m;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.m = false;
        String str = k;
        LogUtil.a(str, "onCreate " + this.m);
    }

    public void onDestroy() {
        this.m = true;
        String str = k;
        LogUtil.a(str, "onDestroy " + this.m);
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        String str = k;
        LogUtil.a(str, "onResume " + this.m);
    }

    public void onPause() {
        super.onPause();
        String str = k;
        LogUtil.a(str, "onPause " + this.m);
    }

    public void onStart() {
        super.onStart();
        String str = k;
        LogUtil.a(str, "onStart " + this.m);
    }

    public void onStop() {
        super.onStop();
        String str = k;
        LogUtil.a(str, "onStop " + this.m);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        String str = k;
        LogUtil.a(str, "onAttach " + this.m);
    }

    public void onDetach() {
        super.onDetach();
        String str = k;
        LogUtil.a(str, "onDetach " + this.m);
    }

    public void refreshTitleBar() {
        TitleBarUtil.a((Activity) getContext());
    }

    public void a(boolean z) {
        a((String) null, z);
        ShopApp.b(z);
    }
}
