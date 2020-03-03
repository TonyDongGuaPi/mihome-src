package com.xiaomi.smarthome;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.library.common.widget.SmartBarView;
import com.xiaomi.smarthome.library.common.widget.SmartCircleView;

public class ShareActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ShareActivity f13420a;

    @UiThread
    public ShareActivity_ViewBinding(ShareActivity shareActivity) {
        this(shareActivity, shareActivity.getWindow().getDecorView());
    }

    @UiThread
    public ShareActivity_ViewBinding(ShareActivity shareActivity, View view) {
        this.f13420a = shareActivity;
        shareActivity.mShareRootView = Utils.findRequiredView(view, R.id.share_root, "field 'mShareRootView'");
        shareActivity.mSmartCircleView = (SmartCircleView) Utils.findRequiredViewAsType(view, R.id.smart_score_circle, "field 'mSmartCircleView'", SmartCircleView.class);
        shareActivity.mSmartSocreCenterView = (TextView) Utils.findRequiredViewAsType(view, R.id.smart_score_center, "field 'mSmartSocreCenterView'", TextView.class);
        shareActivity.mSmartScoreView = (TextView) Utils.findRequiredViewAsType(view, R.id.smart_score, "field 'mSmartScoreView'", TextView.class);
        shareActivity.mExceedScoreView = (TextView) Utils.findRequiredViewAsType(view, R.id.exceed_score, "field 'mExceedScoreView'", TextView.class);
        shareActivity.mSafeScoreTitleView = (TextView) Utils.findRequiredViewAsType(view, R.id.safe_score_title, "field 'mSafeScoreTitleView'", TextView.class);
        shareActivity.mSafeScoreBarView = (SmartBarView) Utils.findRequiredViewAsType(view, R.id.safe_score_bar, "field 'mSafeScoreBarView'", SmartBarView.class);
        shareActivity.mActiveScoreTitleView = (TextView) Utils.findRequiredViewAsType(view, R.id.active_score_title, "field 'mActiveScoreTitleView'", TextView.class);
        shareActivity.mActiveScoreBarView = (SmartBarView) Utils.findRequiredViewAsType(view, R.id.active_score_bar, "field 'mActiveScoreBarView'", SmartBarView.class);
        shareActivity.mSenceScoreTitleView = (TextView) Utils.findRequiredViewAsType(view, R.id.sence_score_title, "field 'mSenceScoreTitleView'", TextView.class);
        shareActivity.mSenceScoreBarView = (SmartBarView) Utils.findRequiredViewAsType(view, R.id.sence_score_bar, "field 'mSenceScoreBarView'", SmartBarView.class);
    }

    @CallSuper
    public void unbind() {
        ShareActivity shareActivity = this.f13420a;
        if (shareActivity != null) {
            this.f13420a = null;
            shareActivity.mShareRootView = null;
            shareActivity.mSmartCircleView = null;
            shareActivity.mSmartSocreCenterView = null;
            shareActivity.mSmartScoreView = null;
            shareActivity.mExceedScoreView = null;
            shareActivity.mSafeScoreTitleView = null;
            shareActivity.mSafeScoreBarView = null;
            shareActivity.mActiveScoreTitleView = null;
            shareActivity.mActiveScoreBarView = null;
            shareActivity.mSenceScoreTitleView = null;
            shareActivity.mSenceScoreBarView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
