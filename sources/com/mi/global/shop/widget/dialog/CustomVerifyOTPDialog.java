package com.mi.global.shop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.util.MiToast;

public class CustomVerifyOTPDialog extends Dialog implements View.OnClickListener {
    private static final long e = 60000;

    /* renamed from: a  reason: collision with root package name */
    private Context f7204a;
    private String b;
    private OnclickListener c;
    /* access modifiers changed from: private */
    public CountDownTimer d;
    @BindView(2131493021)
    CustomButtonView mCancelBtn;
    @BindView(2131493023)
    CustomButtonView mCloseBtn;
    @BindView(2131493024)
    CustomButtonView mConfirmBtn;
    @BindView(2131494226)
    CustomTextView mLoanAgreeDetailView;
    @BindView(2131494241)
    CustomEditTextView mOtpNumView;
    @BindView(2131494242)
    CustomButtonView mOtpSendView;
    @BindView(2131494245)
    CustomTextView mPhoneView;

    public interface OnclickListener {
        void a();

        void a(String str);
    }

    public CustomVerifyOTPDialog(Context context, OnclickListener onclickListener) {
        super(context, R.style.Dialog);
        this.f7204a = context;
        this.c = onclickListener;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.shop_custom_verify_otp_dialog);
        a();
    }

    public void a() {
        ButterKnife.bind((Dialog) this);
        setCanceledOnTouchOutside(false);
        this.mCloseBtn.setOnClickListener(this);
        this.mOtpSendView.setOnClickListener(this);
        this.mLoanAgreeDetailView.setOnClickListener(this);
        this.mCancelBtn.setOnClickListener(this);
        this.mConfirmBtn.setOnClickListener(this);
        if (!TextUtils.isEmpty(this.b)) {
            this.mPhoneView.setText(this.b);
        }
    }

    public void a(String str) {
        this.b = str;
    }

    private void f() {
        if (this.d != null) {
            this.d.cancel();
        }
        b();
        this.d = new CountDownTimer(60000, 1000) {
            public void onFinish() {
                CustomVerifyOTPDialog.this.c();
                CountDownTimer unused = CustomVerifyOTPDialog.this.d = null;
            }

            public void onTick(long j) {
                CustomVerifyOTPDialog.this.a(j);
            }
        }.start();
        if (this.c != null) {
            this.c.a();
        }
    }

    public void b() {
        this.mOtpSendView.setBackgroundResource(R.drawable.shop_cardless_plan_item_bg);
        this.mOtpSendView.setTextColor(this.f7204a.getResources().getColor(R.color.home_footer_text_color));
        this.mOtpSendView.setEnabled(false);
    }

    public void c() {
        this.mOtpSendView.setText(this.f7204a.getString(R.string.cardless_emi_otp_down_time_resend_));
        this.mOtpSendView.setBackgroundResource(R.drawable.shop_cardless_plan_select_item_bg);
        this.mOtpSendView.setTextColor(this.f7204a.getResources().getColor(R.color.orange_red));
        this.mOtpSendView.setEnabled(true);
    }

    public void a(long j) {
        CustomButtonView customButtonView = this.mOtpSendView;
        String string = this.f7204a.getString(R.string.cardless_emi_otp_down_time_resend);
        customButtonView.setText(String.format(string, new Object[]{"" + (j / 1000)}));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_close) {
            e();
        } else if (id == R.id.tv_otp_send) {
            f();
        } else if (id == R.id.btn_cancel) {
            e();
        } else if (id == R.id.btn_confirm) {
            String obj = this.mOtpNumView.getText().toString();
            if (TextUtils.isEmpty(obj) || !TextUtils.isDigitsOnly(obj)) {
                MiToast.a((Context) ShopApp.g(), (CharSequence) this.f7204a.getString(R.string.cardless_emi_otp_input_tips), 0);
                return;
            }
            g();
            if (this.c != null) {
                this.c.a(this.mOtpNumView.getText().toString());
            }
        }
    }

    private void g() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.f7204a.getSystemService("input_method");
        if (inputMethodManager.isActive() && getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
    }

    public void d() {
        if (BaseActivity.isActivityAlive(this.f7204a)) {
            show();
            f();
        }
    }

    public void e() {
        if (BaseActivity.isActivityAlive(this.f7204a)) {
            dismiss();
        }
        if (this.d != null) {
            this.d.cancel();
            this.d = null;
        }
    }
}
