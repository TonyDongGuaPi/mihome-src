package com.xiaomi.smarthome.auth.bindaccount;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class ThirdAccountAuthWebActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ThirdAccountAuthWebActivity f13880a;

    @UiThread
    public ThirdAccountAuthWebActivity_ViewBinding(ThirdAccountAuthWebActivity thirdAccountAuthWebActivity) {
        this(thirdAccountAuthWebActivity, thirdAccountAuthWebActivity.getWindow().getDecorView());
    }

    @UiThread
    public ThirdAccountAuthWebActivity_ViewBinding(ThirdAccountAuthWebActivity thirdAccountAuthWebActivity, View view) {
        this.f13880a = thirdAccountAuthWebActivity;
        thirdAccountAuthWebActivity.mActionBarBack = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mActionBarBack'", ImageView.class);
        thirdAccountAuthWebActivity.mTitleView = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleView'", TextView.class);
        thirdAccountAuthWebActivity.mProgressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.loading_progress, "field 'mProgressBar'", ProgressBar.class);
        thirdAccountAuthWebActivity.mWebView = (WebView) Utils.findRequiredViewAsType(view, R.id.webview, "field 'mWebView'", WebView.class);
    }

    @CallSuper
    public void unbind() {
        ThirdAccountAuthWebActivity thirdAccountAuthWebActivity = this.f13880a;
        if (thirdAccountAuthWebActivity != null) {
            this.f13880a = null;
            thirdAccountAuthWebActivity.mActionBarBack = null;
            thirdAccountAuthWebActivity.mTitleView = null;
            thirdAccountAuthWebActivity.mProgressBar = null;
            thirdAccountAuthWebActivity.mWebView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
