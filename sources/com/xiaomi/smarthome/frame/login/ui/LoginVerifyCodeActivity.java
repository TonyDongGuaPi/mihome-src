package com.xiaomi.smarthome.frame.login.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;
import com.xiaomi.passport.uicontroller.PhoneLoginController;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.login.MjLoginRouter;
import com.xiaomi.smarthome.frame.login.ui.view.CaptchaDialog;
import com.xiaomi.smarthome.frame.login.util.LoginIntentUtil;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.youpin.login.api.manager.callback.PhoneLoginBaseCallback;
import com.xiaomi.youpin.login.api.manager.callback.RegisterCallback;
import com.xiaomi.youpin.login.api.phone.LocalPhoneDetailInfo;
import com.xiaomi.youpin.login.api.stat.LoginType;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.yanzhenjie.permission.Action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginVerifyCodeActivity extends AbstractVerifyCodeActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f16263a;
    private LocalPhoneDetailInfo b;

    /* access modifiers changed from: protected */
    public int getVerifyCodeBit() {
        return 4;
    }

    /* access modifiers changed from: private */
    public PhoneLoginController.SendPhoneTicketCallback a(final String str) {
        return new PhoneLoginController.SendPhoneTicketCallback() {
            public void onSentSuccess(int i) {
                LoginVerifyCodeActivity.this.dismissLoginDialog();
                ToastManager.a().a((int) R.string.login_send_ticket_success);
            }

            public void onNeedCaptchaCode(String str) {
                if (!LoginVerifyCodeActivity.this.isFinishing()) {
                    if (LoginVerifyCodeActivity.this.vLoadingDialog.isShowing()) {
                        LoginVerifyCodeActivity.this.vLoadingDialog.dismiss();
                    }
                    if (LoginVerifyCodeActivity.this.vCaptchaDialog.a()) {
                        LoginVerifyCodeActivity.this.vCaptchaDialog.c();
                    }
                    LoginVerifyCodeActivity.this.vCaptchaDialog.a(str);
                    LoginVerifyCodeActivity.this.vCaptchaDialog.a((CaptchaDialog.OnCaptchaSureClickListener) new CaptchaDialog.OnCaptchaSureClickListener() {
                        public void a(final String str, final String str2) {
                            PermissionHelper.f(LoginVerifyCodeActivity.this, true, new Action() {
                                public void onAction(List<String> list) {
                                    LoginVerifyCodeActivity.this.vLoadingDialog.setMessage(LoginVerifyCodeActivity.this.getString(R.string.login_send_ticket_loading));
                                    LoginVerifyCodeActivity.this.vLoadingDialog.show();
                                    LoginVerifyCodeActivity.this.mPhoneQueryManager.a(str, str, str2, LoginVerifyCodeActivity.this.a(str));
                                }
                            });
                        }

                        public void a() {
                            LoginVerifyCodeActivity.this.dismissLoginDialog();
                        }
                    });
                }
            }

            public void onActivatorTokenExpired() {
                LoginVerifyCodeActivity.this.dismissLoginDialog();
                ToastManager.a().a(LoginVerifyCodeActivity.this.mContext.getString(R.string.login_send_ticket_fail_token_expired));
                LoginVerifyCodeActivity.this.processTokenExpired();
                LoginVerifyCodeActivity.this.finish();
            }

            public void onSMSReachLimit() {
                if (!LoginVerifyCodeActivity.this.isFinishing()) {
                    LoginVerifyCodeActivity.this.dismissLoginDialog();
                    new MLAlertDialog.Builder(LoginVerifyCodeActivity.this).b((CharSequence) LoginVerifyCodeActivity.this.getString(R.string.login_send_ticket_fail_sms_reach_limit)).a((int) R.string.login_send_ticket_fail_sms_reach_limit_positive, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MjLoginRouter.a((Context) LoginVerifyCodeActivity.this, (LocalPhoneDetailInfo) null);
                        }
                    }).b((int) R.string.ok_button, (DialogInterface.OnClickListener) null).b().show();
                }
            }

            public void onPhoneNumInvalid() {
                LoginVerifyCodeActivity.this.dismissLoginDialog();
                ToastManager.a().a((int) R.string.login_send_ticket_fail_phone_format_wrong);
            }

            public void onSentFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                LoginVerifyCodeActivity.this.dismissLoginDialog();
                ToastManager a2 = ToastManager.a();
                a2.a(LoginVerifyCodeActivity.this.getString(R.string.login_send_ticket_fail) + Operators.BRACKET_START_STR + errorCode + Operators.BRACKET_END_STR + str);
            }
        };
    }

    /* access modifiers changed from: protected */
    public String getTitleText() {
        return getString(R.string.login_verify_code_title);
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        Intent intent = getIntent();
        this.b = LoginIntentUtil.e(intent);
        this.f16263a = LoginIntentUtil.g(intent);
        if (this.b == null) {
            this.vSendInfo.setText(getString(R.string.login_verify_code_sending_info, new Object[]{this.f16263a}));
            return;
        }
        this.vSendInfo.setText(getString(R.string.login_verify_code_sending_info, new Object[]{this.b.b.phone}));
    }

    /* access modifiers changed from: protected */
    public void onSendSmsVerifyCode() {
        if (c()) {
            this.mPhoneQueryManager.a(this.b.f23476a, a(""));
        } else {
            PermissionHelper.f(this, true, new Action() {
                public void onAction(List<String> list) {
                    LoginVerifyCodeActivity.this.mPhoneQueryManager.a(LoginVerifyCodeActivity.this.f16263a, "", "", LoginVerifyCodeActivity.this.a(LoginVerifyCodeActivity.this.f16263a));
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void checkVerifyCode(String str) {
        if (c()) {
            this.mLoginManager.a(this.b.f23476a, str, b());
        } else {
            this.mPhoneQueryManager.a(this.f16263a, str, (PhoneLoginController.PhoneUserInfoCallback) new PhoneLoginController.PhoneUserInfoCallback() {
                public void onRecycledOrNotRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    if (!LoginVerifyCodeActivity.this.isFinishing()) {
                        LoginVerifyCodeActivity.this.dismissLoginDialog();
                        LoginVerifyCodeActivity.this.vLoadingDialog.setMessage(LoginVerifyCodeActivity.this.getString(R.string.login_passport_login_waiting));
                        LoginVerifyCodeActivity.this.vLoadingDialog.show();
                        LoginVerifyCodeActivity.this.mLoginManager.a(LoginVerifyCodeActivity.this.f16263a, registerUserInfo.ticketToken, LoginVerifyCodeActivity.this.a());
                    }
                }

                public void onNotRecycledRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    LoginVerifyCodeActivity.this.dismissLoginDialog();
                    LoginVerifyCodeActivity.this.vLoadingDialog.setMessage(LoginVerifyCodeActivity.this.getString(R.string.login_passport_login_waiting));
                    LoginVerifyCodeActivity.this.vLoadingDialog.show();
                    LoginVerifyCodeActivity.this.mLoginManager.a(LoginVerifyCodeActivity.this.f16263a, registerUserInfo.ticketToken, LoginVerifyCodeActivity.this.b());
                }

                public void onProbablyRecycleRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    LoginVerifyCodeActivity.this.dismissLoginDialog();
                    MjLoginRouter.a(LoginVerifyCodeActivity.this.mContext, LoginVerifyCodeActivity.this.f16263a, registerUserInfo);
                    LoginVerifyCodeActivity.this.finish();
                }

                public void onPhoneNumInvalid() {
                    LoginBaseActivity.logLoginFailEvent(LoginType.f, -1, "onPhoneNumInvalid", (Map<String, String>) new HashMap());
                    LoginVerifyCodeActivity.this.dismissLoginDialog();
                    ToastManager.a().a(LoginVerifyCodeActivity.this.mContext.getString(R.string.login_phone_login_fail_format_invalid));
                    LoginVerifyCodeActivity.this.processTokenExpired();
                    LoginVerifyCodeActivity.this.finish();
                }

                public void onTicketOrTokenInvalid() {
                    LoginBaseActivity.logLoginFailEvent(LoginType.f, -1, "sms captcha error", (Map<String, String>) new HashMap());
                    LoginVerifyCodeActivity.this.dismissLoginDialog();
                    LoginVerifyCodeActivity.this.vVerifyCodeInputView.showError();
                    LoginVerifyCodeActivity.this.vErrorInfo.setVisibility(0);
                    LoginVerifyCodeActivity.this.vErrorInfo.setText(R.string.login_verify_code_error);
                }

                public void onQueryFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                    LoginBaseActivity.logLoginFailEvent(LoginType.f, errorCode.toString(), str, (Map<String, String>) new HashMap());
                    LoginVerifyCodeActivity.this.dismissLoginDialog();
                    TextView textView = LoginVerifyCodeActivity.this.vErrorInfo;
                    textView.setText(LoginVerifyCodeActivity.this.mContext.getString(R.string.login_phone_login_fail) + Operators.BRACKET_START_STR + errorCode + Operators.BRACKET_END_STR + str);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public RegisterCallback a() {
        return new RegisterCallback() {
            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginVerifyCodeActivity.this.onLoginFinish();
                LoginVerifyCodeActivity.this.processLoginSuccess(9);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                LoginVerifyCodeActivity.this.onLoginFinish();
                if (i == -5201) {
                    ToastManager.a().a(LoginVerifyCodeActivity.this.mContext.getString(R.string.login_phone_register_limit));
                    LoginVerifyCodeActivity.this.finish();
                } else if (i == -5203) {
                    ToastManager.a().a(LoginVerifyCodeActivity.this.mContext.getString(R.string.login_phone_token_expired));
                    LoginVerifyCodeActivity.this.processTokenExpired();
                    LoginVerifyCodeActivity.this.finish();
                } else if (i == -5100) {
                    ToastManager.a().a(LoginVerifyCodeActivity.this.mContext.getString(R.string.login_phone_login_fail_format_invalid));
                    LoginVerifyCodeActivity.this.finish();
                } else if (i == -5202 || i == -5101) {
                    LoginVerifyCodeActivity.this.vVerifyCodeInputView.showError();
                    LoginVerifyCodeActivity.this.vErrorInfo.setVisibility(0);
                    LoginVerifyCodeActivity.this.vErrorInfo.setText(R.string.login_verify_code_error);
                } else {
                    ToastManager a2 = ToastManager.a();
                    a2.a(LoginVerifyCodeActivity.this.mContext.getString(R.string.login_fail_patch_installed) + Operators.BRACKET_START_STR + i + Operators.BRACKET_END_STR + str);
                    if (LoginVerifyCodeActivity.this.c()) {
                        LoginVerifyCodeActivity.this.processTokenExpired();
                        LoginVerifyCodeActivity.this.finish();
                    }
                }
                FrameManager.b().g().b();
                LoginVerifyCodeActivity.this.processLoginFail();
                LoginBaseActivity.logLoginFailEvent(LoginType.e, i, str, map);
            }
        };
    }

    /* access modifiers changed from: private */
    public PhoneLoginBaseCallback b() {
        return new PhoneLoginBaseCallback() {
            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginVerifyCodeActivity.this.onLoginFinish();
                LoginVerifyCodeActivity.this.processLoginSuccess(9);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                LoginVerifyCodeActivity.this.onLoginFinish();
                if (i == -5100) {
                    ToastManager.a().a(LoginVerifyCodeActivity.this.mContext.getString(R.string.login_phone_login_fail_format_invalid));
                    LoginVerifyCodeActivity.this.finish();
                } else if (i == -5102) {
                    ToastManager.a().a(LoginVerifyCodeActivity.this.mContext.getString(R.string.login_phone_token_expired));
                    LoginVerifyCodeActivity.this.processTokenExpired();
                    LoginVerifyCodeActivity.this.finish();
                } else if (i == -5202 || i == -5101) {
                    LoginVerifyCodeActivity.this.vVerifyCodeInputView.showError();
                    LoginVerifyCodeActivity.this.vErrorInfo.setVisibility(0);
                    LoginVerifyCodeActivity.this.vErrorInfo.setText(R.string.login_verify_code_error);
                } else {
                    ToastManager a2 = ToastManager.a();
                    a2.a(LoginVerifyCodeActivity.this.mContext.getString(R.string.login_fail_patch_installed) + Operators.BRACKET_START_STR + i + Operators.BRACKET_END_STR + str);
                    if (LoginVerifyCodeActivity.this.c()) {
                        LoginVerifyCodeActivity.this.processTokenExpired();
                        LoginVerifyCodeActivity.this.finish();
                    }
                }
                FrameManager.b().g().b();
                LoginVerifyCodeActivity.this.processLoginFail();
                LoginBaseActivity.logLoginFailEvent(LoginType.f, i, str, map);
            }
        };
    }

    /* access modifiers changed from: private */
    public boolean c() {
        return this.b != null;
    }
}
