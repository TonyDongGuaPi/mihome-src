package com.mi.global.shop.widget.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.CustomTextView;

public class CustomVerifyOTPDialog_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private CustomVerifyOTPDialog f7206a;

    @UiThread
    public CustomVerifyOTPDialog_ViewBinding(CustomVerifyOTPDialog customVerifyOTPDialog) {
        this(customVerifyOTPDialog, customVerifyOTPDialog.getWindow().getDecorView());
    }

    @UiThread
    public CustomVerifyOTPDialog_ViewBinding(CustomVerifyOTPDialog customVerifyOTPDialog, View view) {
        this.f7206a = customVerifyOTPDialog;
        customVerifyOTPDialog.mCloseBtn = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.btn_close, "field 'mCloseBtn'", CustomButtonView.class);
        customVerifyOTPDialog.mPhoneView = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_phone, "field 'mPhoneView'", CustomTextView.class);
        customVerifyOTPDialog.mOtpNumView = (CustomEditTextView) Utils.findRequiredViewAsType(view, R.id.tv_otp_num, "field 'mOtpNumView'", CustomEditTextView.class);
        customVerifyOTPDialog.mOtpSendView = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.tv_otp_send, "field 'mOtpSendView'", CustomButtonView.class);
        customVerifyOTPDialog.mLoanAgreeDetailView = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_loan_agree_detail, "field 'mLoanAgreeDetailView'", CustomTextView.class);
        customVerifyOTPDialog.mCancelBtn = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.btn_cancel, "field 'mCancelBtn'", CustomButtonView.class);
        customVerifyOTPDialog.mConfirmBtn = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.btn_confirm, "field 'mConfirmBtn'", CustomButtonView.class);
    }

    @CallSuper
    public void unbind() {
        CustomVerifyOTPDialog customVerifyOTPDialog = this.f7206a;
        if (customVerifyOTPDialog != null) {
            this.f7206a = null;
            customVerifyOTPDialog.mCloseBtn = null;
            customVerifyOTPDialog.mPhoneView = null;
            customVerifyOTPDialog.mOtpNumView = null;
            customVerifyOTPDialog.mOtpSendView = null;
            customVerifyOTPDialog.mLoanAgreeDetailView = null;
            customVerifyOTPDialog.mCancelBtn = null;
            customVerifyOTPDialog.mConfirmBtn = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
