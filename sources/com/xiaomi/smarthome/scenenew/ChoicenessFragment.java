package com.xiaomi.smarthome.scenenew;

import android.os.Bundle;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.operation.js_sdk.CommonWebViewFragment;
import com.xiaomi.smarthome.stat.STAT;

public class ChoicenessFragment extends CommonWebViewFragment {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21724a = "ChoicenessFragment";
    private long b = 0;

    public static ChoicenessFragment a(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("arg_url", str);
        bundle.putString("arg_title", str2);
        bundle.putBoolean("arg_use_title_bar", false);
        ChoicenessFragment choicenessFragment = new ChoicenessFragment();
        choicenessFragment.setArguments(bundle);
        return choicenessFragment;
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        getWebView().setFixHorizontalSwipe(true);
    }

    public void onResume() {
        super.onResume();
        if (this.mPageSelected) {
            a(true);
        }
    }

    public void onPause() {
        super.onPause();
        if (this.mPageSelected) {
            a(false);
        }
    }

    public void onPageDeselected() {
        super.onPageDeselected();
        LogUtil.a(f21724a, "onPageDeselected");
        a(false);
    }

    public void onPageSelected() {
        super.onPageSelected();
        LogUtil.a(f21724a, "onPageSelected");
        a(true);
    }

    private void a(boolean z) {
        if (z) {
            this.b = STAT.c.g(0);
        } else if (this.b > 0) {
            STAT.c.g(this.b);
            this.b = 0;
        }
    }
}
