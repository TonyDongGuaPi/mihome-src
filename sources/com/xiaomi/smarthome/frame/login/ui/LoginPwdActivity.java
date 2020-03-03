package com.xiaomi.smarthome.frame.login.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatHelper;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.MjLoginRouter;
import com.xiaomi.smarthome.frame.login.logic.LoginHelper;
import com.xiaomi.smarthome.frame.login.ui.view.CaptchaDialog;
import com.xiaomi.smarthome.frame.login.ui.view.LoginPwdView;
import com.xiaomi.smarthome.frame.login.ui.view.OnPwdLoginListener;
import com.xiaomi.smarthome.frame.login.util.LoginIntentUtil;
import com.xiaomi.smarthome.frame.login.util.LoginUtil;
import com.xiaomi.smarthome.frame.login.util.ServerUtil;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.manager.callback.PhonePwdLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.PwdLoginCallback;
import com.xiaomi.youpin.login.api.phone.LocalPhoneDetailInfo;
import com.xiaomi.youpin.login.api.stat.LoginType;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import java.util.HashMap;
import java.util.Map;

public class LoginPwdActivity extends LoginHomeBaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final int f16233a = 101;
    private static final int b = 102;
    /* access modifiers changed from: private */
    public LoginPwdView c;
    /* access modifiers changed from: private */
    public LocalPhoneDetailInfo d;
    private boolean e = false;

    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return R.layout.smarthome_login_pwd_act;
    }

    /* access modifiers changed from: protected */
    public boolean isCheckAuthLoginTime() {
        return true;
    }

    private void a() {
        if (!this.e) {
            this.e = true;
            try {
                getWindow().getDecorView().postDelayed(new Runnable() {
                    public void run() {
                        if (LoginPwdActivity.this.isValid()) {
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName(LoginPwdActivity.this.getPackageName(), "com.xiaomi.smarthome.BlankActivity"));
                            intent.addFlags(C.ENCODING_PCM_MU_LAW);
                            try {
                                LoginPwdActivity.this.startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, 1000);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean isValid() {
        if (isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 || !isDestroyed()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (CoreApi.a().D()) {
            this.vPhoneLogin.setVisibility(8);
        } else {
            this.vPhoneLogin.setVisibility(0);
        }
        a();
        b();
    }

    private void b() {
        EditText editText;
        View findViewById = this.c.findViewById(R.id.sh_login_username_group);
        if (findViewById != null && (editText = (EditText) findViewById.findViewById(R.id.login_view_input_edit)) != null) {
            if (HomeManager.A()) {
                editText.setHint(R.string.login_account_intl_hint);
            } else {
                editText.setHint(R.string.login_account_hint);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.c = (LoginPwdView) findViewById(R.id.sh_login_pwd_input);
        if (DarkModeCompat.a((Context) this)) {
            findViewById(R.id.sh_login_home_group).setBackground((Drawable) null);
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        findViewById(R.id.sh_login_register).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                StatHelper.b();
                MiLoginApi.a((Activity) LoginPwdActivity.this, 102, CoreApi.a().I(), LoginPwdActivity.this.getString(R.string.login_new_user_register));
            }
        });
        findViewById(R.id.sh_login_forget_password).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                StatHelper.a();
                MiLoginApi.b(LoginPwdActivity.this, 101, CoreApi.a().I(), LoginPwdActivity.this.getString(R.string.login_forget_password));
            }
        });
        if (ServerUtil.a()) {
            this.vPhoneLogin.setVisibility(0);
            this.vPhoneLogin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MjLoginRouter.c(LoginPwdActivity.this.mContext, LoginPwdActivity.this.d);
                }
            });
        } else {
            this.vPhoneLogin.setVisibility(0);
        }
        this.vPwdLogin.setVisibility(8);
        this.c.setOnPwdLoginListener(new OnPwdLoginListener() {
            public void a(final String str, String str2, String str3) {
                LoginUtil.a((Activity) LoginPwdActivity.this);
                if (!NetworkUtils.a()) {
                    ToastManager.a().a((int) R.string.login_network_not_available);
                    return;
                }
                SHApplication.initAccount();
                MiLoginApi.a(LoginPwdActivity.this.getApplicationContext());
                LoginPwdActivity.this.vLoadingDialog.setMessage(LoginPwdActivity.this.getString(R.string.login_passport_login_waiting));
                LoginPwdActivity.this.vLoadingDialog.show();
                LoginPwdActivity.this.mLoginManager.a(LoginPwdActivity.this.d.f23476a, str, str2, str3, (PhonePwdLoginCallback) new PhonePwdLoginCallback() {
                    public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                        try {
                            LoginPwdActivity.this.dismissLoginDialog();
                            LoginPwdActivity.this.processLoginSuccess(10);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void onLoginFail(int i, String str, Map<String, String> map) {
                        try {
                            LoginPwdActivity.this.dismissLoginDialog();
                            if (i == -5203) {
                                ToastManager.a().a((int) R.string.milogin_fail_token_expired);
                                LoginPwdActivity.this.c.showUserName();
                            } else {
                                if (i != -5008) {
                                    if (i != -5009) {
                                        if (i == -4003) {
                                            ToastManager.a().a((int) R.string.login_cancel);
                                        } else {
                                            ToastManager.a().a(LoginHelper.a(LoginPwdActivity.this.getString(R.string.login_fail_patch_installed), i, str));
                                        }
                                    }
                                }
                                ToastManager.a().a((int) R.string.login_passport_input_fail);
                            }
                            FrameManager.b().g().b();
                            LoginPwdActivity.this.processLoginFail();
                            LoginBaseActivity.logLoginFailEvent(LoginType.i, i, str, map);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }

                    public void a(String str, boolean z) {
                        try {
                            if (!LoginPwdActivity.this.isFinishing()) {
                                if (!z || !LoginPwdActivity.this.vCaptchaDialog.a()) {
                                    LoginBaseActivity.logLoginFailEvent(LoginType.i, -1, "onPwdLoginNeedCaptcha captcha error", (Map<String, String>) new HashMap());
                                    if (LoginPwdActivity.this.vLoadingDialog != null && LoginPwdActivity.this.vLoadingDialog.isShowing()) {
                                        LoginPwdActivity.this.vLoadingDialog.dismiss();
                                    }
                                    if (LoginPwdActivity.this.vCaptchaDialog.a()) {
                                        LoginPwdActivity.this.vCaptchaDialog.c();
                                    }
                                    LoginPwdActivity.this.vCaptchaDialog.a(str);
                                    LoginPwdActivity.this.vCaptchaDialog.a((CaptchaDialog.OnCaptchaSureClickListener) new CaptchaDialog.OnCaptchaSureClickListener() {
                                        public void a(String str, String str2) {
                                            LoginPwdActivity.this.vLoadingDialog.setMessage(LoginPwdActivity.this.getString(R.string.login_send_ticket_loading));
                                            LoginPwdActivity.this.vLoadingDialog.show();
                                            AnonymousClass5.this.a(str, str, str2);
                                        }

                                        public void a() {
                                            LoginPwdActivity.this.dismissLoginDialog();
                                            ToastManager.a().a((int) R.string.login_cancel);
                                            LoginBaseActivity.logLoginFailEvent(LoginType.i, -1, "onPwdLoginNeedCaptcha captcha cancel", (Map<String, String>) new HashMap());
                                        }
                                    });
                                    return;
                                }
                                LoginPwdActivity.this.onLoginFinish();
                                LoginBaseActivity.logLoginFailEvent(LoginType.i, -1, "onPwdLoginNeedCaptcha account id or pwd error", (Map<String, String>) new HashMap());
                                ToastManager.a().a((int) R.string.login_passport_input_fail);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void a(String str, String str2, String str3, String str4) {
                try {
                    LoginUtil.a((Activity) LoginPwdActivity.this);
                    if (!NetworkUtils.a()) {
                        ToastManager.a().a((int) R.string.login_network_not_available);
                        return;
                    }
                    LoginPwdActivity.this.vLoadingDialog.setCancelable(true);
                    LoginPwdActivity.this.vLoadingDialog.setMessage(LoginPwdActivity.this.getString(R.string.login_passport_login_waiting));
                    LoginPwdActivity.this.vLoadingDialog.show();
                    LoginPwdActivity.this.mLoginManager.a(str, str2, str3, str4, (PwdLoginCallback) new PwdLoginCallback() {
                        public void a(String str, boolean z) {
                            try {
                                if (!LoginPwdActivity.this.isFinishing()) {
                                    if (!z || !LoginPwdActivity.this.vCaptchaDialog.a()) {
                                        LoginBaseActivity.logLoginFailEvent(LoginType.b, -1, "onPwdLoginNeedCaptcha captcha error", (Map<String, String>) new HashMap());
                                        if (LoginPwdActivity.this.vLoadingDialog != null && LoginPwdActivity.this.vLoadingDialog.isShowing()) {
                                            LoginPwdActivity.this.vLoadingDialog.dismiss();
                                        }
                                        if (LoginPwdActivity.this.vCaptchaDialog.a()) {
                                            LoginPwdActivity.this.vCaptchaDialog.c();
                                        }
                                        LoginPwdActivity.this.vCaptchaDialog.a(str);
                                        LoginPwdActivity.this.vCaptchaDialog.a((CaptchaDialog.OnCaptchaSureClickListener) new CaptchaDialog.OnCaptchaSureClickListener() {
                                            public void a(String str, String str2) {
                                                LoginPwdActivity.this.vLoadingDialog.setMessage(LoginPwdActivity.this.getString(R.string.login_passport_login_waiting));
                                                LoginPwdActivity.this.vLoadingDialog.show();
                                                AnonymousClass5.this.a(LoginPwdActivity.this.c.getUserName(), LoginPwdActivity.this.c.getPwd(), str, str2);
                                            }

                                            public void a() {
                                                LoginPwdActivity.this.dismissLoginDialog();
                                                ToastManager.a().a((int) R.string.login_cancel);
                                                LoginBaseActivity.logLoginFailEvent(LoginType.b, -1, "onPwdLoginNeedCaptcha captcha cancel", (Map<String, String>) new HashMap());
                                            }
                                        });
                                        return;
                                    }
                                    LoginPwdActivity.this.onLoginFinish();
                                    LoginBaseActivity.logLoginFailEvent(LoginType.b, -1, "onPwdLoginNeedCaptcha onPwdLoginNeedCaptcha account id or pwd error", (Map<String, String>) new HashMap());
                                    ToastManager.a().a((int) R.string.login_passport_input_fail);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                            try {
                                LoginPwdActivity.this.dismissLoginDialog();
                                LoginPwdActivity.this.processLoginSuccess(3);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        public void onLoginFail(int i, String str, Map<String, String> map) {
                            try {
                                LoginPwdActivity.this.dismissLoginDialog();
                                FrameManager.b().m();
                                if (i == -3) {
                                    LoginHelper.a((Activity) LoginPwdActivity.this, i);
                                } else {
                                    if (i != -5008) {
                                        if (i != -5009) {
                                            if (i == -4003) {
                                                ToastManager.a().a((int) R.string.login_cancel);
                                            } else {
                                                ToastManager.a().a(LoginHelper.a(LoginPwdActivity.this.getString(R.string.login_passport_login_fail), i, str));
                                            }
                                        }
                                    }
                                    ToastManager.a().a(LoginPwdActivity.this.getString(R.string.login_passport_input_fail));
                                }
                                FrameManager.b().g().b();
                                LoginPwdActivity.this.processLoginFail();
                                LoginBaseActivity.logLoginFailEvent(LoginType.b, i, str, map);
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.d = LoginIntentUtil.e(this.mIntent);
        if (this.d == null || this.d.c != 2 || !this.d.b.hasPwd) {
            String c2 = LoginIntentUtil.c(this.mIntent);
            if (TextUtils.isEmpty(c2)) {
                this.c.showUserName();
            } else {
                this.c.showUserName(c2);
            }
        } else {
            this.c.showLocalPhone(this.d.f23476a.phone, this.d.f23476a.copyWriter, this.d.f23476a.operatorLink);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mIsLoginingFB) {
            super.onActivityResult(i, i2, intent);
            return;
        }
        switch (i) {
            case 101:
                if (i2 == -1) {
                    ToastManager.a().a((int) R.string.login_pwd_forget_success);
                    return;
                }
                return;
            case 102:
                if (i2 == -1) {
                    ToastManager.a().a((int) R.string.login_pwd_register_success);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
