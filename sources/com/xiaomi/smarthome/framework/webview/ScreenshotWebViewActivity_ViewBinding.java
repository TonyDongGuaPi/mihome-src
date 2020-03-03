package com.xiaomi.smarthome.framework.webview;

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

public class ScreenshotWebViewActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ScreenshotWebViewActivity f17890a;

    @UiThread
    public ScreenshotWebViewActivity_ViewBinding(ScreenshotWebViewActivity screenshotWebViewActivity) {
        this(screenshotWebViewActivity, screenshotWebViewActivity.getWindow().getDecorView());
    }

    @UiThread
    public ScreenshotWebViewActivity_ViewBinding(ScreenshotWebViewActivity screenshotWebViewActivity, View view) {
        this.f17890a = screenshotWebViewActivity;
        screenshotWebViewActivity.mActionBarBack = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mActionBarBack'", ImageView.class);
        screenshotWebViewActivity.mTitleView = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleView'", TextView.class);
        screenshotWebViewActivity.mProgressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.loading_progress, "field 'mProgressBar'", ProgressBar.class);
        screenshotWebViewActivity.mWebView = (WebView) Utils.findRequiredViewAsType(view, R.id.webview, "field 'mWebView'", WebView.class);
    }

    @CallSuper
    public void unbind() {
        ScreenshotWebViewActivity screenshotWebViewActivity = this.f17890a;
        if (screenshotWebViewActivity != null) {
            this.f17890a = null;
            screenshotWebViewActivity.mActionBarBack = null;
            screenshotWebViewActivity.mTitleView = null;
            screenshotWebViewActivity.mProgressBar = null;
            screenshotWebViewActivity.mWebView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
