package com.xiaomi.smarthome.auth;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;

public class MihomeOAuthUI_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private MihomeOAuthUI f13837a;

    @UiThread
    public MihomeOAuthUI_ViewBinding(MihomeOAuthUI mihomeOAuthUI) {
        this(mihomeOAuthUI, mihomeOAuthUI.getWindow().getDecorView());
    }

    @UiThread
    public MihomeOAuthUI_ViewBinding(MihomeOAuthUI mihomeOAuthUI, View view) {
        this.f13837a = mihomeOAuthUI;
        mihomeOAuthUI.deviceNameText = (TextView) Utils.findRequiredViewAsType(view, R.id.device_name, "field 'deviceNameText'", TextView.class);
        mihomeOAuthUI.acceptStatusText = (TextView) Utils.findRequiredViewAsType(view, R.id.accept_status, "field 'acceptStatusText'", TextView.class);
        mihomeOAuthUI.imageIcon = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.icon, "field 'imageIcon'", SimpleDraweeView.class);
        mihomeOAuthUI.leftBtn = Utils.findRequiredView(view, R.id.back_wechat1, "field 'leftBtn'");
        mihomeOAuthUI.rightBtn = Utils.findRequiredView(view, R.id.stay_mijia, "field 'rightBtn'");
        mihomeOAuthUI.oneBtn = Utils.findRequiredView(view, R.id.back_wechat2, "field 'oneBtn'");
        mihomeOAuthUI.twoBtns = Utils.findRequiredView(view, R.id.buttons, "field 'twoBtns'");
        mihomeOAuthUI.loadingText = Utils.findRequiredView(view, R.id.loading_status, "field 'loadingText'");
    }

    @CallSuper
    public void unbind() {
        MihomeOAuthUI mihomeOAuthUI = this.f13837a;
        if (mihomeOAuthUI != null) {
            this.f13837a = null;
            mihomeOAuthUI.deviceNameText = null;
            mihomeOAuthUI.acceptStatusText = null;
            mihomeOAuthUI.imageIcon = null;
            mihomeOAuthUI.leftBtn = null;
            mihomeOAuthUI.rightBtn = null;
            mihomeOAuthUI.oneBtn = null;
            mihomeOAuthUI.twoBtns = null;
            mihomeOAuthUI.loadingText = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
