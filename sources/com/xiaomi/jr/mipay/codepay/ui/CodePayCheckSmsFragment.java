package com.xiaomi.jr.mipay.codepay.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.mipay.codepay.R;
import com.xiaomi.jr.mipay.codepay.api.ApiManager;
import com.xiaomi.jr.mipay.codepay.component.ProgressButton;
import com.xiaomi.jr.mipay.codepay.component.SmsCaptchaEditText;
import com.xiaomi.jr.mipay.codepay.data.DoPayResult;
import com.xiaomi.jr.mipay.codepay.data.TradeResult;
import com.xiaomi.jr.mipay.codepay.presenter.CodePayTrader;
import com.xiaomi.jr.mipay.codepay.util.CodePayConstants;
import com.xiaomi.jr.mipay.codepay.util.CodePayUtils;
import com.xiaomi.jr.mipay.common.http.MipayHttpCallback;
import com.xiaomi.jr.mipay.common.model.MipayResponse;
import com.xiaomi.jr.mipay.safekeyboard.SafeKeyboard;
import com.xiaomi.jr.mipay.safekeyboard.SafeKeyboardManager;
import com.xiaomi.jr.mipay.safekeyboard.SafeKeyboardView;
import com.xiaomi.jr.permission.PermissionManager;
import com.xiaomi.jr.permission.Request;

public class CodePayCheckSmsFragment extends BaseFragment {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10918a = "mipay.codepayCheckSms";
    private static final int i = 60;
    private TextView b;
    private SmsCaptchaEditText c;
    private Button d;
    /* access modifiers changed from: private */
    public ProgressButton e;
    private String f;
    private String g;
    private CodePayTrader h = new CodePayTrader();
    private int j;
    private Handler k = new Handler();
    private CodePayTrader.TradeCallback l = new CodePayTrader.TradeCallback() {
        public void a(int i, String str, DoPayResult doPayResult) {
            if (CodePayCheckSmsFragment.this.isAdded()) {
                if (i == 2010002) {
                    Utils.a(CodePayCheckSmsFragment.this.getActivity().getApplicationContext(), R.string.jr_mipay_check_sms_captcha_code_error);
                } else {
                    a(i, str);
                }
                a();
            }
        }

        public void a(int i, String str, TradeResult tradeResult) {
            if (CodePayCheckSmsFragment.this.isAdded()) {
                if (i != 200 || !TextUtils.equals(tradeResult.e, "TRADE_SUCCESS")) {
                    a(i, str);
                } else {
                    CodePayUtils.a(CodePayCheckSmsFragment.this, tradeResult.e());
                }
                a();
            }
        }

        private void a(int i, String str) {
            Context applicationContext = CodePayCheckSmsFragment.this.getActivity().getApplicationContext();
            Utils.b(applicationContext, Operators.ARRAY_START_STR + i + "] " + str);
        }

        private void a() {
            CodePayCheckSmsFragment.this.e.stopProgress();
        }
    };

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f = getArguments().getString(CodePayConstants.f);
        this.g = getArguments().getString("processId");
        if (TextUtils.isEmpty(this.f)) {
            throw new IllegalArgumentException("tailNum is null");
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.jr_mipay_check_pay_sms_captcha, viewGroup, false);
        this.b = (TextView) inflate.findViewById(R.id.sms_summary);
        this.c = (SmsCaptchaEditText) inflate.findViewById(R.id.sms_captcha);
        this.d = (Button) inflate.findViewById(R.id.resend);
        this.e = (ProgressButton) inflate.findViewById(R.id.confirm);
        this.e.setEnabled(false);
        SafeKeyboardView a2 = SafeKeyboardManager.a((Activity) getActivity(), SafeKeyboard.c);
        SafeKeyboardManager.a(a2);
        SafeKeyboardManager.a((View) this.c, a2);
        PermissionManager.a((Context) getActivity(), "android.permission.RECEIVE_SMS", (Request.Callback) new Request.Callback() {
            public void a() {
            }

            public /* synthetic */ void b() {
                Request.Callback.CC.$default$b(this);
            }

            public void a(String str) {
                CodePayCheckSmsFragment.this.d();
            }
        });
        return inflate;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.d.setText(R.string.jr_mipay_check_sms_captcha_resend);
        this.d.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CodePayCheckSmsFragment.this.b(view);
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CodePayCheckSmsFragment.this.a(view);
            }
        });
        this.b.setText(getString(R.string.jr_mipay_check_sms_captcha_summary, this.f));
        this.c.requestFocus();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        a();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        i();
    }

    public void onDestroy() {
        super.onDestroy();
        this.k.removeCallbacksAndMessages((Object) null);
    }

    private void a() {
        ApiManager.a().b(this.g).enqueue(new MipayHttpCallback<MipayResponse>((Fragment) null) {
            public void a(MipayResponse mipayResponse) {
            }
        });
        b();
    }

    private void b() {
        this.j = 0;
        this.d.setText(getString(R.string.jr_mipay_check_sms_captcha_wait, 60));
        this.d.setEnabled(false);
        h();
    }

    private void h() {
        this.k.postDelayed(new Runnable() {
            public final void run() {
                CodePayCheckSmsFragment.this.j();
            }
        }, 1000);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void j() {
        this.j++;
        this.d.setText(getString(R.string.jr_mipay_check_sms_captcha_wait, Integer.valueOf(60 - this.j)));
        if (this.j >= 60) {
            this.d.setText(R.string.jr_mipay_check_sms_captcha_resend);
            this.d.setEnabled(true);
            this.k.removeCallbacksAndMessages((Object) null);
            return;
        }
        h();
    }

    private void i() {
        String smsCaptcha = this.c.getSmsCaptcha();
        if (TextUtils.isEmpty(smsCaptcha)) {
            Utils.a(getActivity().getApplicationContext(), R.string.jr_mipay_check_sms_captcha_code_error);
            return;
        }
        this.e.startProgress();
        this.h.a(this.g, smsCaptcha, this.l);
    }
}
