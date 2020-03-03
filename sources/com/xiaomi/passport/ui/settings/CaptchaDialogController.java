package com.xiaomi.passport.ui.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.settings.CaptchaView;

public class CaptchaDialogController implements CaptchaView.OnCaptchaSwitchChange {
    private AlertDialog captchaDialog;
    /* access modifiers changed from: private */
    public final CaptchaInterface captchaInterface;
    /* access modifiers changed from: private */
    public CaptchaView captchaView;
    private final Activity mActivity;

    public interface CaptchaInterface {
        void onCaptchaFinished();

        void onCaptchaRequired();

        void tryCaptcha(String str, String str2);
    }

    public void update(boolean z) {
        if (this.captchaDialog != null) {
            this.captchaDialog.setTitle(z ? R.string.passport_input_voice_hint : R.string.passport_input_captcha_hint);
        }
    }

    public CaptchaDialogController(Activity activity, CaptchaInterface captchaInterface2) {
        this.mActivity = activity;
        this.captchaInterface = captchaInterface2;
    }

    public boolean shouldForceNewCaptcha() {
        return this.captchaDialog != null && !this.captchaDialog.isShowing();
    }

    /* access modifiers changed from: private */
    public String getCaptchaCode() {
        if (this.captchaView != null) {
            return this.captchaView.getCaptchaCode();
        }
        return null;
    }

    public CaptchaInterface getCaptchaInterface() {
        return this.captchaInterface;
    }

    /* access modifiers changed from: package-private */
    public void dismiss() {
        if (this.captchaDialog != null) {
            this.captchaDialog.dismiss();
        }
    }

    public String getIck() {
        if (this.captchaView != null) {
            return this.captchaView.getCaptchaIck();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void triggerNewCaptchaTask(String str, String str2) {
        if (this.mActivity != null && !this.mActivity.isFinishing()) {
            if (this.captchaDialog == null || !this.captchaDialog.isShowing()) {
                this.captchaView = new CaptchaView(this.mActivity);
                this.captchaView.setOnCaptchaSwitchChange(this);
                this.captchaView.downloadCaptcha(str, str2);
                this.captchaDialog = new AlertDialog.Builder(this.mActivity).setTitle(R.string.passport_input_captcha_hint).setView(this.captchaView).setPositiveButton(17039370, (DialogInterface.OnClickListener) null).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).show();
                this.captchaDialog.getButton(-1).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        String access$000 = CaptchaDialogController.this.getCaptchaCode();
                        if (!TextUtils.isEmpty(access$000)) {
                            CaptchaDialogController.this.captchaInterface.tryCaptcha(access$000, CaptchaDialogController.this.captchaView.getCaptchaIck());
                        }
                    }
                });
            } else if (this.captchaView != null) {
                this.captchaView.onCaptchaError();
            }
        }
    }
}
