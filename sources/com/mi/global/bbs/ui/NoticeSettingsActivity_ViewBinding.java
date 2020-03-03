package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.SettingsItemContainerView;

public class NoticeSettingsActivity_ViewBinding implements Unbinder {
    private NoticeSettingsActivity target;

    @UiThread
    public NoticeSettingsActivity_ViewBinding(NoticeSettingsActivity noticeSettingsActivity) {
        this(noticeSettingsActivity, noticeSettingsActivity.getWindow().getDecorView());
    }

    @UiThread
    public NoticeSettingsActivity_ViewBinding(NoticeSettingsActivity noticeSettingsActivity, View view) {
        this.target = noticeSettingsActivity;
        noticeSettingsActivity.mSettingsContainer = (SettingsItemContainerView) Utils.findRequiredViewAsType(view, R.id.mSettingsContainer, "field 'mSettingsContainer'", SettingsItemContainerView.class);
    }

    @CallSuper
    public void unbind() {
        NoticeSettingsActivity noticeSettingsActivity = this.target;
        if (noticeSettingsActivity != null) {
            this.target = null;
            noticeSettingsActivity.mSettingsContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
