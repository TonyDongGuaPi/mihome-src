package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.page.PagerListener;
import com.xiaomi.smarthome.stat.STAT;

public abstract class TabFragment extends BaseFragment implements PagerListener {

    /* renamed from: a  reason: collision with root package name */
    protected View f16923a;
    private boolean b;
    private long c = 0;

    /* access modifiers changed from: protected */
    public final void a(String str, boolean z) {
        if (z) {
            this.c = STAT.b.a(this, str);
        } else if (this.c > 0) {
            STAT.b.a(this, this.c, str);
            this.c = 0;
        }
        if (z && this.f16923a != null) {
            this.f16923a.requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public boolean titleBarSettled() {
        if (this.b) {
            return true;
        }
        this.b = true;
        return false;
    }

    public void onDestroyView() {
        ViewParent parent;
        super.onDestroyView();
        if (this.f16923a != null && (parent = this.f16923a.getParent()) != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.f16923a);
        }
    }

    public void refreshTitleBar() {
        TitleBarUtil.a((Activity) getActivity());
    }
}
