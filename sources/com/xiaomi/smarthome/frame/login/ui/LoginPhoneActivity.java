package com.xiaomi.smarthome.frame.login.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;
import com.xiaomi.passport.uicontroller.PhoneLoginController;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.login.MjLoginRouter;
import com.xiaomi.smarthome.frame.login.logic.LoginHelper;
import com.xiaomi.smarthome.frame.login.ui.view.CaptchaDialog;
import com.xiaomi.smarthome.frame.login.ui.view.DualSimChooseDialog;
import com.xiaomi.smarthome.frame.login.ui.view.LoginPhoneView;
import com.xiaomi.smarthome.frame.login.ui.view.OnSmsCodeLoginListener;
import com.xiaomi.smarthome.frame.login.util.LoginIntentUtil;
import com.xiaomi.smarthome.frame.login.util.LoginUtil;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.manager.callback.PhoneLoginBaseCallback;
import com.xiaomi.youpin.login.api.manager.callback.RegisterCallback;
import com.xiaomi.youpin.login.api.phone.LocalPhoneDetailInfo;
import com.xiaomi.youpin.login.api.stat.LoginType;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginPhoneActivity extends LoginHomeBaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private DualSimChooseDialog f16218a;
    /* access modifiers changed from: private */
    public LocalPhoneDetailInfo b;
    /* access modifiers changed from: private */
    public LoginPhoneView c;
    /* access modifiers changed from: private */
    public boolean d = false;
    private OnSmsCodeLoginListener e = new OnSmsCodeLoginListener() {
        public void a(String str, String str2) {
            if (!NetworkUtils.a()) {
                ToastManager.a().a((int) R.string.login_verify_code_network_unavailable);
                return;
            }
            LoginPhoneActivity.this.getWindow().setSoftInputMode(3);
            LoginPhoneActivity.this.vLoadingDialog.setMessage(LoginPhoneActivity.this.getString(R.string.login_send_ticket_loading));
            LoginPhoneActivity.this.vLoadingDialog.show();
            LoginPhoneActivity.this.mPhoneQueryManager.a(LoginPhoneActivity.this.b.f23476a, (PhoneLoginController.SendPhoneTicketCallback) new PhoneLoginController.SendPhoneTicketCallback() {
                public void onSentSuccess(int i) {
                    boolean unused = LoginPhoneActivity.this.d = true;
                    a();
                    ToastManager.a().a((int) R.string.login_send_ticket_success);
                    LoginPhoneActivity.this.c.showSmsCodeSendSuccess(i);
                }

                public void onNeedCaptchaCode(String str) {
                    a();
                    if (LoginPhoneActivity.this.vCaptchaDialog.a()) {
                        LoginPhoneActivity.this.vCaptchaDialog.c();
                    }
                    LoginPhoneActivity.this.vCaptchaDialog.a(str);
                    LoginPhoneActivity.this.vCaptchaDialog.a((CaptchaDialog.OnCaptchaSureClickListener) new CaptchaDialog.OnCaptchaSureClickListener() {
                        public void a(String str, String str2) {
                            LoginPhoneActivity.this.vLoadingDialog.setMessage(LoginPhoneActivity.this.getString(R.string.login_send_ticket_loading));
                            LoginPhoneActivity.this.vLoadingDialog.show();
                            AnonymousClass1.this.a(str, str2);
                        }

                        public void a() {
                            AnonymousClass1.this.a();
                        }
                    });
                }

                public void onActivatorTokenExpired() {
                    a();
                    ToastManager.a().a(LoginPhoneActivity.this.mContext.getString(R.string.login_send_ticket_fail_token_expired));
                    LoginPhoneActivity.this.a();
                }

                public void onSMSReachLimit() {
                    a();
                    new MLAlertDialog.Builder(LoginPhoneActivity.this).b((CharSequence) LoginPhoneActivity.this.getString(R.string.login_send_ticket_fail_sms_reach_limit)).a((int) R.string.login_send_ticket_fail_sms_reach_limit_positive, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MjLoginRouter.a(LoginPhoneActivity.this.mContext, (LocalPhoneDetailInfo) null);
                        }
                    }).b((int) R.string.ok_button, (DialogInterface.OnClickListener) null).b().show();
                }

                public void onPhoneNumInvalid() {
                    a();
                    ToastManager.a().a(LoginPhoneActivity.this.mContext.getString(R.string.login_send_ticket_fail_phone_format_wrong));
                    LoginPhoneActivity.this.a();
                }

                public void onSentFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                    a();
                    ToastManager a2 = ToastManager.a();
                    a2.a(LoginPhoneActivity.this.mContext.getString(R.string.login_send_ticket_fail) + Operators.BRACKET_START_STR + errorCode + Operators.BRACKET_END_STR + str);
                }

                /* access modifiers changed from: private */
                public void a() {
                    if (!LoginPhoneActivity.this.isFinishing()) {
                        LoginPhoneActivity.this.dismissLoginDialog();
                    }
                }
            });
        }

        public void a(final String str, String str2, String str3) {
            if (!NetworkUtils.a()) {
                ToastManager.a().a((int) R.string.login_verify_code_network_unavailable);
                return;
            }
            LoginPhoneActivity.this.getWindow().setSoftInputMode(3);
            LoginPhoneActivity.this.vLoadingDialog.setMessage(LoginPhoneActivity.this.getString(R.string.login_send_ticket_loading));
            LoginPhoneActivity.this.vLoadingDialog.show();
            LoginPhoneActivity.this.mPhoneQueryManager.a(str, str2, str3, (PhoneLoginController.SendPhoneTicketCallback) new PhoneLoginController.SendPhoneTicketCallback() {
                public void onSentSuccess(int i) {
                    boolean unused = LoginPhoneActivity.this.d = true;
                    a();
                    ToastManager.a().a((int) R.string.login_send_ticket_success);
                    LoginPhoneActivity.this.c.showSmsCodeSendSuccess(i);
                }

                public void onNeedCaptchaCode(String str) {
                    if (LoginPhoneActivity.this.vLoadingDialog != null && LoginPhoneActivity.this.vLoadingDialog.isShowing()) {
                        LoginPhoneActivity.this.vLoadingDialog.dismiss();
                    }
                    if (LoginPhoneActivity.this.vCaptchaDialog.a()) {
                        LoginPhoneActivity.this.vCaptchaDialog.c();
                    }
                    LoginPhoneActivity.this.vCaptchaDialog.a(str);
                    LoginPhoneActivity.this.vCaptchaDialog.a((CaptchaDialog.OnCaptchaSureClickListener) new CaptchaDialog.OnCaptchaSureClickListener() {
                        public void a(String str, String str2) {
                            LoginPhoneActivity.this.vLoadingDialog.setMessage(LoginPhoneActivity.this.getString(R.string.login_send_ticket_loading));
                            LoginPhoneActivity.this.vLoadingDialog.show();
                            AnonymousClass1.this.a(str, str, str2);
                        }

                        public void a() {
                            AnonymousClass2.this.a();
                        }
                    });
                }

                public void onActivatorTokenExpired() {
                    a();
                    ToastManager.a().a("回调错误");
                }

                public void onSMSReachLimit() {
                    a();
                    new MLAlertDialog.Builder(LoginPhoneActivity.this).b((CharSequence) LoginPhoneActivity.this.getString(R.string.login_send_ticket_fail_sms_reach_limit)).a((int) R.string.login_send_ticket_fail_sms_reach_limit_positive, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MjLoginRouter.a(LoginPhoneActivity.this.mContext, (LocalPhoneDetailInfo) null);
                        }
                    }).b((int) R.string.ok_button, (DialogInterface.OnClickListener) null).b().show();
                }

                public void onPhoneNumInvalid() {
                    a();
                    ToastManager.a().a(LoginPhoneActivity.this.mContext.getString(R.string.login_send_ticket_fail_phone_format_wrong));
                }

                public void onSentFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                    LoginPhoneActivity.this.dismissLoginDialog();
                    ToastManager a2 = ToastManager.a();
                    a2.a(LoginPhoneActivity.this.mContext.getString(R.string.login_send_ticket_fail) + Operators.BRACKET_START_STR + errorCode + Operators.BRACKET_END_STR + str);
                }

                /* access modifiers changed from: private */
                public void a() {
                    if (!LoginPhoneActivity.this.isFinishing()) {
                        LoginPhoneActivity.this.dismissLoginDialog();
                    }
                }
            });
        }

        public void a() {
            LoginUtil.a((Activity) LoginPhoneActivity.this);
            if (!NetworkUtils.a()) {
                ToastManager.a().a((int) R.string.login_network_not_available);
                return;
            }
            MiLoginApi.a(LoginPhoneActivity.this.getApplicationContext());
            LoginPhoneActivity.this.getWindow().setSoftInputMode(3);
            LoginPhoneActivity.this.vLoadingDialog.setMessage(LoginPhoneActivity.this.getString(R.string.login_passport_login_waiting));
            LoginPhoneActivity.this.vLoadingDialog.show();
            LoginPhoneActivity.this.mLoginManager.a(LoginPhoneActivity.this.b.f23476a, (RegisterCallback) new RegisterCallback() {
                public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                    LoginPhoneActivity.this.dismissLoginDialog();
                    LoginPhoneActivity.this.processLoginSuccess(9);
                }

                public void onLoginFail(int i, String str, Map<String, String> map) {
                    LoginPhoneActivity.this.dismissLoginDialog();
                    if (i == -5201) {
                        ToastManager.a().a(LoginPhoneActivity.this.mContext.getString(R.string.login_phone_register_limit));
                    } else if (i == -5203) {
                        ToastManager.a().a(LoginPhoneActivity.this.mContext.getString(R.string.login_phone_token_expired));
                        LoginPhoneActivity.this.a();
                    } else {
                        ToastManager.a().a(LoginHelper.a(LoginPhoneActivity.this.getString(R.string.login_fail_patch_installed), i, str));
                    }
                    LoginPhoneActivity.this.processLoginFail();
                    LoginBaseActivity.logLoginFailEvent(LoginType.e, i, str, map);
                }
            });
        }

        public void a(String str) {
            LoginUtil.a((Activity) LoginPhoneActivity.this);
            if (!NetworkUtils.a()) {
                ToastManager.a().a((int) R.string.login_network_not_available);
            } else if (LoginPhoneActivity.this.b.c == 3) {
                MjLoginRouter.a(LoginPhoneActivity.this.mContext, str, LoginPhoneActivity.this.b);
            } else {
                MiLoginApi.a(LoginPhoneActivity.this.getApplicationContext());
                LoginPhoneActivity.this.vLoadingDialog.setMessage(LoginPhoneActivity.this.getString(R.string.login_passport_login_waiting));
                LoginPhoneActivity.this.vLoadingDialog.show();
                LoginPhoneActivity.this.mLoginManager.a(LoginPhoneActivity.this.b.f23476a, str, (PhoneLoginBaseCallback) new PhoneLoginBaseCallback() {
                    public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                        LoginPhoneActivity.this.dismissLoginDialog();
                        LoginPhoneActivity.this.processLoginSuccess(9);
                    }

                    public void onLoginFail(int i, String str, Map<String, String> map) {
                        LoginPhoneActivity.this.dismissLoginDialog();
                        if (i == -5203) {
                            ToastManager.a().a(LoginPhoneActivity.this.mContext.getString(R.string.login_phone_token_expired));
                            LoginPhoneActivity.this.a();
                        } else if (i == -5101) {
                            ToastManager.a().a((int) R.string.login_verify_code_error);
                        } else {
                            ToastManager.a().a(LoginHelper.a(LoginPhoneActivity.this.getString(R.string.login_fail_patch_installed), i, str));
                        }
                        LoginPhoneActivity.this.processLoginFail();
                        LoginBaseActivity.logLoginFailEvent(LoginType.f, i, str, map);
                    }
                });
            }
        }

        public void b(final String str, String str2) {
            LoginUtil.a((Activity) LoginPhoneActivity.this);
            if (!NetworkUtils.a()) {
                ToastManager.a().a((int) R.string.login_network_not_available);
                return;
            }
            MiLoginApi.a(LoginPhoneActivity.this.getApplicationContext());
            LoginPhoneActivity.this.vLoadingDialog.setMessage(LoginPhoneActivity.this.getString(R.string.login_passport_login_waiting));
            LoginPhoneActivity.this.vLoadingDialog.show();
            LoginPhoneActivity.this.mPhoneQueryManager.a(str, str2, (PhoneLoginController.PhoneUserInfoCallback) new PhoneLoginController.PhoneUserInfoCallback() {
                public void onRecycledOrNotRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    LoginPhoneActivity.this.mLoginManager.a(str, registerUserInfo.ticketToken, (RegisterCallback) new RegisterCallback() {
                        public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                            LoginPhoneActivity.this.dismissLoginDialog();
                            LoginPhoneActivity.this.processLoginSuccess(9);
                        }

                        public void onLoginFail(int i, String str, Map<String, String> map) {
                            LoginPhoneActivity.this.dismissLoginDialog();
                            ToastManager.a().a(LoginHelper.a(LoginPhoneActivity.this.getString(R.string.login_fail_patch_installed), i, str));
                            LoginPhoneActivity.this.processLoginFail();
                            LoginBaseActivity.logLoginFailEvent(LoginType.e, i, str, map);
                        }
                    });
                }

                public void onNotRecycledRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    LoginPhoneActivity.this.mLoginManager.a(str, registerUserInfo.ticketToken, (PhoneLoginBaseCallback) new PhoneLoginBaseCallback() {
                        public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                            LoginPhoneActivity.this.dismissLoginDialog();
                            LoginPhoneActivity.this.processLoginSuccess(9);
                        }

                        public void onLoginFail(int i, String str, Map<String, String> map) {
                            LoginPhoneActivity.this.dismissLoginDialog();
                            if (i == -5202 || i == -5101) {
                                ToastManager.a().a((int) R.string.login_verify_code_error);
                            } else {
                                ToastManager.a().a(LoginHelper.a(LoginPhoneActivity.this.getString(R.string.login_fail_patch_installed), i, str));
                            }
                            LoginBaseActivity.logLoginFailEvent(LoginType.f, i, str, map);
                        }
                    });
                }

                public void onProbablyRecycleRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    LoginPhoneActivity.this.dismissLoginDialog();
                    MjLoginRouter.a((Context) LoginPhoneActivity.this, str, registerUserInfo);
                }

                public void onPhoneNumInvalid() {
                    LoginPhoneActivity.this.dismissLoginDialog();
                    ToastManager.a().a(LoginPhoneActivity.this.mContext.getString(R.string.login_phone_login_fail_format_invalid));
                    LoginBaseActivity.logLoginFailEvent(LoginType.f, -1, "onPhoneNumInvalid", (Map<String, String>) new HashMap());
                }

                public void onTicketOrTokenInvalid() {
                    LoginPhoneActivity.this.dismissLoginDialog();
                    ToastManager.a().a((int) R.string.login_verify_code_error);
                    LoginBaseActivity.logLoginFailEvent(LoginType.f, -1, "onTicketOrTokenInvalid", (Map<String, String>) new HashMap());
                }

                public void onQueryFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                    LoginPhoneActivity.this.dismissLoginDialog();
                    ToastManager a2 = ToastManager.a();
                    a2.a(LoginPhoneActivity.this.mContext.getString(R.string.login_phone_login_fail) + Operators.BRACKET_START_STR + errorCode + Operators.BRACKET_END_STR);
                    LoginBaseActivity.logLoginFailEvent(LoginType.f, errorCode.toString(), str, (Map<String, String>) new HashMap());
                }
            });
        }
    };

    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return R.layout.smarthome_login_phone_act;
    }

    /* access modifiers changed from: protected */
    public boolean isCheckAuthLoginTime() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.c = (LoginPhoneView) findViewById(R.id.sh_login_pwd_input);
        this.c.setActivity(this);
        if (DarkModeCompat.a((Context) this)) {
            findViewById(R.id.sh_login_home_group).setBackground((Drawable) null);
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.f16218a = new DualSimChooseDialog(this);
        if (!LoginIntentUtil.d(this.mIntent)) {
            this.b = LoginIntentUtil.e(this.mIntent);
            if (this.b != null) {
                switch (this.b.c) {
                    case 1:
                        this.c.showLocalPhoneNew(this.b.f23476a.phone, this.b.f23476a.copyWriter, this.b.f23476a.operatorLink, this.e);
                        break;
                    case 2:
                        b();
                        break;
                    case 3:
                        b();
                        break;
                }
            } else {
                a();
            }
        } else {
            List<LocalPhoneDetailInfo> b2 = MiLoginApi.b();
            if (b2 == null || b2.isEmpty()) {
                a();
            } else if (b2.size() == 1) {
                a(b2.get(0));
            } else {
                this.c.showPhoneSmsCodeLogin(this.e);
                this.f16218a.a(b2, new DualSimChooseDialog.OnSimChooseListener() {
                    public void a(LocalPhoneDetailInfo localPhoneDetailInfo) {
                        LoginPhoneActivity.this.a(localPhoneDetailInfo);
                    }

                    public void a() {
                        LoginPhoneActivity.this.a();
                    }
                });
            }
        }
        this.vPwdLogin.setVisibility(0);
        this.vPwdLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MjLoginRouter.a(LoginPhoneActivity.this.mContext, LoginPhoneActivity.this.b);
            }
        });
        this.vPhoneLogin.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: private */
    public void a(LocalPhoneDetailInfo localPhoneDetailInfo) {
        this.b = localPhoneDetailInfo;
        switch (this.b.c) {
            case 1:
                this.c.showLocalPhoneNew(localPhoneDetailInfo.f23476a.phone, localPhoneDetailInfo.f23476a.copyWriter, localPhoneDetailInfo.f23476a.operatorLink, this.e);
                return;
            case 2:
                if (localPhoneDetailInfo.b.hasPwd) {
                    MjLoginRouter.a(this.mContext, this.b);
                    finish();
                    return;
                }
                b();
                return;
            case 3:
                b();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        this.c.showPhoneSmsCodeLogin(this.e);
    }

    private void b() {
        this.c.showLocalPhoneOld(this.b.f23476a.phone, this.b.f23476a.copyWriter, this.b.f23476a.operatorLink, this.e);
    }
}
