package com.xiaomi.smarthome.frame.login.ui;

import com.xiaomi.passport.uicontroller.PhoneLoginController;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.frame.login.util.LoginEventUtil;
import com.xiaomi.smarthome.frame.login.util.LoginIntentUtil;
import com.xiaomi.youpin.login.api.manager.callback.ReLoginAfterSetPwdCallback;
import com.xiaomi.youpin.login.api.stat.LoginType;
import com.xiaomi.youpin.login.entity.account.AccountParam;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.util.Map;

public class SetPwdVerifyCodeActivity extends AbstractVerifyCodeActivity {

    /* renamed from: a  reason: collision with root package name */
    private String f16288a;

    /* access modifiers changed from: protected */
    public int getVerifyCodeBit() {
        return 6;
    }

    /* access modifiers changed from: protected */
    public String getTitleText() {
        return getString(R.string.set_pwd);
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.f16288a = LoginIntentUtil.j(getIntent());
        this.vSendInfo.setText(R.string.login_set_password_verify_code_sending_info);
    }

    /* access modifiers changed from: protected */
    public void onSendSmsVerifyCode() {
        MiServiceTokenInfo a2 = CoreApi.a().a("passportapi");
        this.mPhoneQueryManager.a(new AccountParam(CoreApi.a().s(), CoreApi.a().u(), a2 == null ? "" : a2.c, a2 == null ? "" : a2.d), (PhoneLoginController.SendSetPwdTicketCallback) new PhoneLoginController.SendSetPwdTicketCallback() {
            public void onSentSuccess(String str) {
                SetPwdVerifyCodeActivity.this.dismissLoginDialog();
                ToastManager.a().a((int) R.string.login_send_ticket_success);
            }

            public void onSMSReachLimit() {
                SetPwdVerifyCodeActivity.this.dismissLoginDialog();
                ToastManager.a().a((int) R.string.login_send_ticket_fail_sms_reach_limit);
            }

            public void onSentFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                SetPwdVerifyCodeActivity.this.dismissLoginDialog();
                ToastManager.a().a((int) R.string.login_send_ticket_fail);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void checkVerifyCode(String str) {
        MiServiceTokenInfo a2 = CoreApi.a().a("passportapi");
        AccountParam accountParam = new AccountParam(CoreApi.a().s(), CoreApi.a().u(), a2 == null ? "" : a2.c, a2 == null ? "" : a2.d);
        final String s = CoreApi.a().s();
        this.mPhoneQueryManager.a(accountParam, CoreApi.a().w(), this.f16288a, str, new PhoneLoginController.SetPasswordCallback() {
            public void onSetSuccess(final String str) {
                LoginApi.a().a(true, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        SetPwdVerifyCodeActivity.this.mLoginManager.a(s, str, (ReLoginAfterSetPwdCallback) new ReLoginAfterSetPwdCallback() {
                            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                                SetPwdVerifyCodeActivity.this.dismissLoginDialog();
                                LoginEventUtil.a(SetPwdVerifyCodeActivity.this, true);
                                ToastManager.a().a((int) R.string.set_pwd_succeed);
                                SetPwdVerifyCodeActivity.this.setResult(-1);
                                SetPwdVerifyCodeActivity.this.finish();
                            }

                            public void onLoginFail(int i, String str, Map<String, String> map) {
                                LoginBaseActivity.logLoginFailEvent(LoginType.g, i, str, map);
                                SetPwdVerifyCodeActivity.this.dismissLoginDialog();
                                ToastManager.a().a((int) R.string.set_pwd_succeed_but_login_fail);
                                SetPwdVerifyCodeActivity.this.finish();
                            }
                        });
                    }

                    public void onFailure(Error error) {
                        SetPwdVerifyCodeActivity.this.dismissLoginDialog();
                        ToastManager.a().a((int) R.string.set_pwd_succeed_but_login_fail);
                        SetPwdVerifyCodeActivity.this.finish();
                    }
                });
            }

            public void onHasPassword() {
                SetPwdVerifyCodeActivity.this.dismissLoginDialog();
                ToastManager.a().a((int) R.string.set_pwd_err_has_password);
                SetPwdVerifyCodeActivity.this.finish();
            }

            public void onNeedTicketOrTicketInvalid() {
                SetPwdVerifyCodeActivity.this.dismissLoginDialog();
                SetPwdVerifyCodeActivity.this.onSendSmsVerifyCode();
            }

            public void onPassTokenInvalid() {
                SetPwdVerifyCodeActivity.this.dismissLoginDialog();
                ToastManager.a().a((int) R.string.set_pwd_err_passtoken_invalid);
            }

            public void onSetFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                SetPwdVerifyCodeActivity.this.dismissLoginDialog();
                ToastManager.a().a((int) R.string.set_pwd_err_passtoken_invalid);
            }
        });
    }
}
