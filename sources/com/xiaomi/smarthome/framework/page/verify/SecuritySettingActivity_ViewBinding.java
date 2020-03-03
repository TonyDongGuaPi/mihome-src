package com.xiaomi.smarthome.framework.page.verify;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;

public class SecuritySettingActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SecuritySettingActivity f17065a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;
    private View i;
    private View j;

    @UiThread
    public SecuritySettingActivity_ViewBinding(SecuritySettingActivity securitySettingActivity) {
        this(securitySettingActivity, securitySettingActivity.getWindow().getDecorView());
    }

    @UiThread
    public SecuritySettingActivity_ViewBinding(final SecuritySettingActivity securitySettingActivity, View view) {
        this.f17065a = securitySettingActivity;
        securitySettingActivity.vTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'vTitle'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.xiaomi_sm_open_password, "field 'vPasswordOpen' and method 'onClickPasswordOpen'");
        securitySettingActivity.vPasswordOpen = findRequiredView;
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                securitySettingActivity.onClickPasswordOpen();
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.xiaomi_sm_close_password, "field 'vPasswordClose' and method 'onClickPasswordClose'");
        securitySettingActivity.vPasswordClose = findRequiredView2;
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                securitySettingActivity.onClickPasswordClose();
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.xiaomi_sm_change_password, "field 'vPasswordChange' and method 'onClickPasswordChange'");
        securitySettingActivity.vPasswordChange = findRequiredView3;
        this.d = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                securitySettingActivity.onClickPasswordChange();
            }
        });
        securitySettingActivity.vPasswordChangeTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.xiaomi_sm_change_password_title, "field 'vPasswordChangeTitle'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.xiaomi_sm_show_secure_pin, "field 'vShowSecurePin' and method 'onClickSecurePin'");
        securitySettingActivity.vShowSecurePin = findRequiredView4;
        this.e = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                securitySettingActivity.onClickSecurePin();
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.xiaomi_sm_show_secure_pin_switch, "field 'vSecurePinSwitch' and method 'onClickSecurePin'");
        securitySettingActivity.vSecurePinSwitch = (SwitchButton) Utils.castView(findRequiredView5, R.id.xiaomi_sm_show_secure_pin_switch, "field 'vSecurePinSwitch'", SwitchButton.class);
        this.f = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                securitySettingActivity.onClickSecurePin();
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.xiaomi_sm_change_secure_pin, "field 'vChangeSecurePin' and method 'onClickChangeSecurePin'");
        securitySettingActivity.vChangeSecurePin = findRequiredView6;
        this.g = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                securitySettingActivity.onClickChangeSecurePin();
            }
        });
        securitySettingActivity.vFingerPrintTitle = Utils.findRequiredView(view, R.id.xiaomi_sm_fingerprint_title, "field 'vFingerPrintTitle'");
        View findRequiredView7 = Utils.findRequiredView(view, R.id.xiaomi_sm_fingerprint_setting, "field 'vFingerPrintSetting' and method 'onClickFingerPrintSetting'");
        securitySettingActivity.vFingerPrintSetting = findRequiredView7;
        this.h = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                securitySettingActivity.onClickFingerPrintSetting();
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.xiaomi_sm_fingerprint_setting_switch, "field 'vFingerPrintSwitch' and method 'onClickFingerPrintSetting'");
        securitySettingActivity.vFingerPrintSwitch = (SwitchButton) Utils.castView(findRequiredView8, R.id.xiaomi_sm_fingerprint_setting_switch, "field 'vFingerPrintSwitch'", SwitchButton.class);
        this.i = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                securitySettingActivity.onClickFingerPrintSetting();
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "method 'onClickBack'");
        this.j = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                securitySettingActivity.onClickBack();
            }
        });
    }

    @CallSuper
    public void unbind() {
        SecuritySettingActivity securitySettingActivity = this.f17065a;
        if (securitySettingActivity != null) {
            this.f17065a = null;
            securitySettingActivity.vTitle = null;
            securitySettingActivity.vPasswordOpen = null;
            securitySettingActivity.vPasswordClose = null;
            securitySettingActivity.vPasswordChange = null;
            securitySettingActivity.vPasswordChangeTitle = null;
            securitySettingActivity.vShowSecurePin = null;
            securitySettingActivity.vSecurePinSwitch = null;
            securitySettingActivity.vChangeSecurePin = null;
            securitySettingActivity.vFingerPrintTitle = null;
            securitySettingActivity.vFingerPrintSetting = null;
            securitySettingActivity.vFingerPrintSwitch = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            this.c.setOnClickListener((View.OnClickListener) null);
            this.c = null;
            this.d.setOnClickListener((View.OnClickListener) null);
            this.d = null;
            this.e.setOnClickListener((View.OnClickListener) null);
            this.e = null;
            this.f.setOnClickListener((View.OnClickListener) null);
            this.f = null;
            this.g.setOnClickListener((View.OnClickListener) null);
            this.g = null;
            this.h.setOnClickListener((View.OnClickListener) null);
            this.h = null;
            this.i.setOnClickListener((View.OnClickListener) null);
            this.i = null;
            this.j.setOnClickListener((View.OnClickListener) null);
            this.j = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
