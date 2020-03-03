package com.xiaomi.smarthome.mibrain.roomsetting;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mibigkoo.convenientbanner.ConvenientBanner;
import com.xiaomi.smarthome.R;

public class XiaoAiTutorialActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private XiaoAiTutorialActivity f10700a;

    @UiThread
    public XiaoAiTutorialActivity_ViewBinding(XiaoAiTutorialActivity xiaoAiTutorialActivity) {
        this(xiaoAiTutorialActivity, xiaoAiTutorialActivity.getWindow().getDecorView());
    }

    @UiThread
    public XiaoAiTutorialActivity_ViewBinding(XiaoAiTutorialActivity xiaoAiTutorialActivity, View view) {
        this.f10700a = xiaoAiTutorialActivity;
        xiaoAiTutorialActivity.banner = (ConvenientBanner) Utils.findRequiredViewAsType(view, R.id.banner, "field 'banner'", ConvenientBanner.class);
        xiaoAiTutorialActivity.close = Utils.findRequiredView(view, R.id.close, "field 'close'");
    }

    @CallSuper
    public void unbind() {
        XiaoAiTutorialActivity xiaoAiTutorialActivity = this.f10700a;
        if (xiaoAiTutorialActivity != null) {
            this.f10700a = null;
            xiaoAiTutorialActivity.banner = null;
            xiaoAiTutorialActivity.close = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
