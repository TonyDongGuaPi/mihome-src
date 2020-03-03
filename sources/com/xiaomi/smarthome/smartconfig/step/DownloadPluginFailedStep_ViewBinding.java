package com.xiaomi.smarthome.smartconfig.step;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class DownloadPluginFailedStep_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private DownloadPluginFailedStep f22600a;

    @UiThread
    public DownloadPluginFailedStep_ViewBinding(DownloadPluginFailedStep downloadPluginFailedStep, View view) {
        this.f22600a = downloadPluginFailedStep;
        downloadPluginFailedStep.mIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_icon, "field 'mIcon'", ImageView.class);
        downloadPluginFailedStep.mMainTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_main_title, "field 'mMainTitle'", TextView.class);
        downloadPluginFailedStep.mSubMainTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_main_sub_title, "field 'mSubMainTitle'", TextView.class);
        downloadPluginFailedStep.mLeftBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.left_btn, "field 'mLeftBtn'", TextView.class);
        downloadPluginFailedStep.mRightBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.right_btn, "field 'mRightBtn'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        DownloadPluginFailedStep downloadPluginFailedStep = this.f22600a;
        if (downloadPluginFailedStep != null) {
            this.f22600a = null;
            downloadPluginFailedStep.mIcon = null;
            downloadPluginFailedStep.mMainTitle = null;
            downloadPluginFailedStep.mSubMainTitle = null;
            downloadPluginFailedStep.mLeftBtn = null;
            downloadPluginFailedStep.mRightBtn = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
