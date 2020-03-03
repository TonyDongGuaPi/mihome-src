package com.xiaomi.smarthome.bbs;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import com.mi.global.bbs.ui.CommunityMainFragment;
import com.xiaomi.smarthome.framework.page.BaseFragmentInterface;
import com.xiaomi.smarthome.miio.page.PagerListener;
import com.xiaomi.smarthome.stat.STAT;

public class SmarthomeBBSFragment extends CommunityMainFragment implements BaseFragmentInterface, PagerListener {

    /* renamed from: a  reason: collision with root package name */
    private volatile boolean f13923a = true;
    private long b = 0;

    public void onPageSelected() {
    }

    public void refreshTitleBar() {
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f13923a = false;
    }

    public void onDestroy() {
        this.f13923a = true;
        super.onDestroy();
    }

    public boolean onBackPressed() {
        try {
            if (isActionMenuShown()) {
                hideActionMenu();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getActivity().finish();
        return true;
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
        return !this.f13923a;
    }

    public void a(boolean z) {
        a((String) null, z);
    }

    /* access modifiers changed from: protected */
    public final void a(String str, boolean z) {
        if (z) {
            this.b = STAT.b.a(this, str);
        } else if (this.b > 0) {
            STAT.b.a(this, this.b, str);
            this.b = 0;
        }
    }
}
