package com.xiaomi.smarthome.frame.login.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;
import com.xiaomi.passport.uicontroller.PhoneLoginController;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.login.MjLoginRouter;
import com.xiaomi.smarthome.frame.login.logic.LoginHelper;
import com.xiaomi.smarthome.frame.login.ui.view.CaptchaDialog;
import com.xiaomi.smarthome.frame.login.util.LoginIntentUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.manager.callback.PhoneLoginBaseCallback;
import com.xiaomi.youpin.login.api.manager.callback.RegisterCallback;
import com.xiaomi.youpin.login.api.phone.LocalPhoneDetailInfo;
import com.xiaomi.youpin.login.api.stat.LoginType;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import java.util.Map;

public class LoginUserChooseActivity extends LoginBaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public LocalPhoneDetailInfo f16254a;
    private RegisterUserInfo b;
    private String c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public MLAlertDialog f;

    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return R.layout.smarthome_login_user_choose;
    }

    /* access modifiers changed from: protected */
    public void init() {
        String str;
        String str2;
        Intent intent = getIntent();
        this.f16254a = LoginIntentUtil.e(intent);
        if (this.f16254a == null) {
            this.b = LoginIntentUtil.f(intent);
            if (this.b == null) {
                ToastManager.a().a("data is null");
                finish();
                return;
            }
            this.d = LoginIntentUtil.g(intent);
            str2 = this.b.avatarAddress;
            this.e = this.b.userName;
            str = this.b.maskedUserId;
        } else {
            this.c = LoginIntentUtil.h(intent);
            this.d = this.f16254a.b.phone;
            str2 = this.f16254a.b.avatarAddress;
            this.e = this.f16254a.b.userName;
            str = this.f16254a.b.maskedUserId;
        }
        TextView textView = (TextView) findViewById(R.id.login_choose_username);
        TextView textView2 = (TextView) findViewById(R.id.login_choose_phone);
        FrameManager.b().g().a((View) (ImageView) findViewById(R.id.login_choose_profile), str2);
        Object[] objArr = new Object[1];
        if (!TextUtils.isEmpty(this.e)) {
            str = this.e;
        }
        objArr[0] = str;
        textView.setText(getString(R.string.login_choose_username, objArr));
        if (TextUtils.isEmpty(this.d)) {
            textView2.setVisibility(8);
        } else {
            textView2.setVisibility(0);
            textView2.setText(getString(R.string.login_choose_phone, new Object[]{this.d}));
        }
        findViewById(R.id.login_choose_sure).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginUserChooseActivity.this.a();
            }
        });
        findViewById(R.id.login_choose_new).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginUserChooseActivity.this.f == null) {
                    MLAlertDialog unused = LoginUserChooseActivity.this.f = new MLAlertDialog.Builder(LoginUserChooseActivity.this).b((CharSequence) LoginUserChooseActivity.this.getString(R.string.login_choose_hint_message, new Object[]{LoginUserChooseActivity.this.d, LoginUserChooseActivity.this.e})).a((CharSequence) LoginUserChooseActivity.this.getString(R.string.login_choose_positive), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            LoginUserChooseActivity.this.b();
                        }
                    }).b((CharSequence) LoginUserChooseActivity.this.getString(R.string.cancel), (DialogInterface.OnClickListener) null).b();
                }
                LoginUserChooseActivity.this.f.show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        if (!NetworkUtils.a()) {
            ToastManager.a().a((int) R.string.login_network_not_available);
        } else if (this.f16254a == null) {
            this.vLoadingDialog.setMessage(getString(R.string.login_passport_login_waiting));
            this.vLoadingDialog.show();
            this.mLoginManager.a(this.d, this.b.ticketToken, e());
        } else if (TextUtils.isEmpty(this.c)) {
            this.vLoadingDialog.setMessage(getString(R.string.login_passport_login_waiting));
            this.vLoadingDialog.show();
            this.mPhoneQueryManager.a(this.f16254a.f23476a, c());
        } else {
            MiLoginApi.a(getApplicationContext());
            this.vLoadingDialog.setMessage(getString(R.string.login_passport_login_waiting));
            this.vLoadingDialog.show();
            this.mLoginManager.a(this.f16254a.f23476a, this.c, e());
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (!NetworkUtils.a()) {
            ToastManager.a().a((int) R.string.login_network_not_available);
        } else if (this.f16254a == null) {
            this.vLoadingDialog.setMessage(getString(R.string.login_passport_login_waiting));
            this.vLoadingDialog.show();
            this.mLoginManager.a(this.d, this.b.ticketToken, d());
        } else {
            MiLoginApi.a(getApplicationContext());
            this.vLoadingDialog.setMessage(getString(R.string.login_passport_login_waiting));
            this.vLoadingDialog.show();
            this.mLoginManager.a(this.f16254a.f23476a, d());
        }
    }

    /* access modifiers changed from: private */
    public PhoneLoginController.SendPhoneTicketCallback c() {
        return new PhoneLoginController.SendPhoneTicketCallback() {
            public void onSentSuccess(int i) {
                LoginUserChooseActivity.this.onLoginFinish();
                ToastManager.a().a((int) R.string.login_send_ticket_success);
                MjLoginRouter.d((Context) LoginUserChooseActivity.this, LoginUserChooseActivity.this.f16254a);
            }

            public void onNeedCaptchaCode(String str) {
                if (LoginUserChooseActivity.this.vLoadingDialog != null && LoginUserChooseActivity.this.vLoadingDialog.isShowing()) {
                    LoginUserChooseActivity.this.vLoadingDialog.dismiss();
                }
                if (!LoginUserChooseActivity.this.isFinishing()) {
                    if (LoginUserChooseActivity.this.vCaptchaDialog.a()) {
                        LoginUserChooseActivity.this.vCaptchaDialog.c();
                    }
                    LoginUserChooseActivity.this.vCaptchaDialog.a(str);
                    LoginUserChooseActivity.this.vCaptchaDialog.a((CaptchaDialog.OnCaptchaSureClickListener) new CaptchaDialog.OnCaptchaSureClickListener() {
                        public void a(String str, String str2) {
                            LoginUserChooseActivity.this.vLoadingDialog.setMessage(LoginUserChooseActivity.this.getString(R.string.login_send_ticket_loading));
                            LoginUserChooseActivity.this.vLoadingDialog.show();
                            LoginUserChooseActivity.this.mPhoneQueryManager.a(LoginUserChooseActivity.this.f16254a.f23476a, LoginUserChooseActivity.this.c());
                        }

                        public void a() {
                            LoginUserChooseActivity.this.onLoginFinish();
                        }
                    });
                }
            }

            public void onActivatorTokenExpired() {
                LoginUserChooseActivity.this.onLoginFinish();
                ToastManager.a().a(LoginUserChooseActivity.this.mContext.getString(R.string.login_send_ticket_fail_token_expired));
                LoginUserChooseActivity.this.processTokenExpired();
                LoginUserChooseActivity.this.finish();
            }

            public void onSMSReachLimit() {
                if (!LoginUserChooseActivity.this.isFinishing()) {
                    LoginUserChooseActivity.this.onLoginFinish();
                    new MLAlertDialog.Builder(LoginUserChooseActivity.this).b((CharSequence) LoginUserChooseActivity.this.getString(R.string.login_send_ticket_fail_sms_reach_limit)).a((int) R.string.login_send_ticket_fail_sms_reach_limit_positive, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MjLoginRouter.a((Context) LoginUserChooseActivity.this, (LocalPhoneDetailInfo) null);
                        }
                    }).b((int) R.string.ok_button, (DialogInterface.OnClickListener) null).b().show();
                }
            }

            public void onPhoneNumInvalid() {
                LoginUserChooseActivity.this.onLoginFinish();
                ToastManager.a().a((int) R.string.login_send_ticket_fail_phone_format_wrong);
                LoginUserChooseActivity.this.finish();
            }

            public void onSentFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                LoginUserChooseActivity.this.onLoginFinish();
                ToastManager a2 = ToastManager.a();
                a2.a(LoginUserChooseActivity.this.mContext.getString(R.string.login_send_ticket_fail) + Operators.BRACKET_START_STR + errorCode + Operators.BRACKET_END_STR + str);
                if (LoginUserChooseActivity.this.f16254a != null) {
                    LoginUserChooseActivity.this.processTokenExpired();
                    LoginUserChooseActivity.this.finish();
                }
            }
        };
    }

    private RegisterCallback d() {
        return new RegisterCallback() {
            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginUserChooseActivity.this.onLoginFinish();
                LoginUserChooseActivity.this.processLoginSuccess(9);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                LoginBaseActivity.logLoginFailEvent(LoginType.e, i, str, map);
                LoginUserChooseActivity.this.onLoginFinish();
                if (i == -5100) {
                    ToastManager.a().a(LoginUserChooseActivity.this.mContext.getString(R.string.login_phone_login_fail_format_invalid));
                    LoginUserChooseActivity.this.finish();
                } else if (i == -5201) {
                    ToastManager.a().a(LoginUserChooseActivity.this.mContext.getString(R.string.login_phone_register_limit));
                    if (LoginUserChooseActivity.this.f16254a != null) {
                        LoginUserChooseActivity.this.processTokenExpired();
                        LoginUserChooseActivity.this.finish();
                    }
                } else if (i == -5203) {
                    LoginUserChooseActivity.this.onLoginFinish();
                    ToastManager.a().a(LoginUserChooseActivity.this.mContext.getString(R.string.login_phone_token_expired));
                    LoginUserChooseActivity.this.processTokenExpired();
                    LoginUserChooseActivity.this.finish();
                } else {
                    ToastManager.a().a(LoginHelper.a(LoginUserChooseActivity.this.getString(R.string.login_fail_patch_installed), i, str));
                    if (LoginUserChooseActivity.this.f16254a != null) {
                        LoginUserChooseActivity.this.processTokenExpired();
                        LoginUserChooseActivity.this.finish();
                    }
                }
                LoginUserChooseActivity.this.processLoginFail();
            }
        };
    }

    private PhoneLoginBaseCallback e() {
        return new PhoneLoginBaseCallback() {
            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginUserChooseActivity.this.onLoginFinish();
                LoginUserChooseActivity.this.processLoginSuccess(9);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                LoginBaseActivity.logLoginFailEvent(LoginType.f, i, str, map);
                LoginUserChooseActivity.this.onLoginFinish();
                if (i == -5100) {
                    ToastManager.a().a(LoginUserChooseActivity.this.mContext.getString(R.string.login_phone_login_fail_format_invalid));
                    LoginUserChooseActivity.this.finish();
                } else if (i == -5102) {
                    ToastManager.a().a(LoginUserChooseActivity.this.mContext.getString(R.string.login_phone_token_expired));
                    LoginUserChooseActivity.this.processTokenExpired();
                    LoginUserChooseActivity.this.finish();
                } else {
                    ToastManager.a().a(LoginHelper.a(LoginUserChooseActivity.this.getString(R.string.login_fail_patch_installed), i, str));
                    if (LoginUserChooseActivity.this.f16254a != null) {
                        LoginUserChooseActivity.this.processTokenExpired();
                        LoginUserChooseActivity.this.finish();
                    }
                }
                LoginUserChooseActivity.this.processLoginFail();
            }
        };
    }
}
