package com.xiaomi.smarthome;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class ReportWebViewAct_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ReportWebViewAct f13410a;

    @UiThread
    public ReportWebViewAct_ViewBinding(ReportWebViewAct reportWebViewAct) {
        this(reportWebViewAct, reportWebViewAct.getWindow().getDecorView());
    }

    @UiThread
    public ReportWebViewAct_ViewBinding(ReportWebViewAct reportWebViewAct, View view) {
        this.f13410a = reportWebViewAct;
        reportWebViewAct.mActionBarBack = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mActionBarBack'", ImageView.class);
        reportWebViewAct.mTitleView = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleView'", TextView.class);
        reportWebViewAct.mShareView = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_right_iv_setting_btn, "field 'mShareView'", ImageView.class);
        reportWebViewAct.mProgressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.loading_progress, "field 'mProgressBar'", ProgressBar.class);
        reportWebViewAct.mWebView = (WebView) Utils.findRequiredViewAsType(view, R.id.webview, "field 'mWebView'", WebView.class);
    }

    @CallSuper
    public void unbind() {
        ReportWebViewAct reportWebViewAct = this.f13410a;
        if (reportWebViewAct != null) {
            this.f13410a = null;
            reportWebViewAct.mActionBarBack = null;
            reportWebViewAct.mTitleView = null;
            reportWebViewAct.mShareView = null;
            reportWebViewAct.mProgressBar = null;
            reportWebViewAct.mWebView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
