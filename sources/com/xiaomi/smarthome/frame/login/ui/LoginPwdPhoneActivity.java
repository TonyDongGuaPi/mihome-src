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
import android.widget.TextView;
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
import com.xiaomi.smarthome.frame.login.ui.view.LoginInputView;
import com.xiaomi.smarthome.frame.login.ui.view.LoginPwdView;
import com.xiaomi.smarthome.frame.login.ui.view.OnPwdLoginListener;
import com.xiaomi.smarthome.frame.login.util.LoginIntentUtil;
import com.xiaomi.smarthome.frame.login.util.LoginUtil;
import com.xiaomi.smarthome.frame.login.util.ServerUtil;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.SmartHomePhoneNumUtil;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.manager.callback.PhonePwdLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.PwdLoginCallback;
import com.xiaomi.youpin.login.api.phone.LocalPhoneDetailInfo;
import com.xiaomi.youpin.login.api.stat.LoginType;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import java.util.HashMap;
import java.util.Map;

public class LoginPwdPhoneActivity extends LoginHomeBaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final int f16243a = 101;
    private static final int b = 102;
    private LoginPwdView c;
    /* access modifiers changed from: private */
    public LoginPwdView d;
    /* access modifiers changed from: private */
    public LocalPhoneDetailInfo e;
    private boolean f = false;
    private TextView g;
    private boolean h;

    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return R.layout.smarthome_login_pwd_act;
    }

    /* access modifiers changed from: protected */
    public boolean isCheckAuthLoginTime() {
        return true;
    }

    private void a() {
        if (!this.f) {
            this.f = true;
            try {
                getWindow().getDecorView().postDelayed(new Runnable() {
                    public void run() {
                        if (LoginPwdPhoneActivity.this.isValid()) {
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName(LoginPwdPhoneActivity.this.getPackageName(), "com.xiaomi.smarthome.BlankActivity"));
                            intent.addFlags(C.ENCODING_PCM_MU_LAW);
                            try {
                                LoginPwdPhoneActivity.this.startActivity(intent);
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
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.h = DarkModeCompat.a((Context) this);
        if (this.h) {
            findViewById(R.id.sh_login_home_group).setBackground((Drawable) null);
        }
        this.d = (LoginPwdView) findViewById(R.id.sh_login_pwd_input);
        LoginInputView userNameGroup = this.d.getUserNameGroup();
        userNameGroup.findViewById(R.id.country_phone_code_ll).setVisibility(0);
        EditText editText = (EditText) userNameGroup.findViewById(R.id.login_view_input_edit);
        editText.setHint(R.string.login_account_phone_pwd_hint);
        editText.setInputType(3);
        this.g = (TextView) userNameGroup.findViewById(R.id.country_phone_code);
        SmartHomePhoneNumUtil.CountryPhoneNumData a2 = SmartHomePhoneNumUtil.a(ServerCompact.c((Context) this).getCountry());
        if (a2 == null) {
            this.g.setText("");
            this.g.setVisibility(8);
        } else {
            TextView textView = this.g;
            textView.setText("+" + a2.b);
            this.g.setVisibility(0);
        }
        userNameGroup.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LoginPwdPhoneActivity.this.a(view);
            }
        });
        View findViewById = findViewById(R.id.sh_login_phone_pwd_way);
        findViewById.setVisibility(0);
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginPwdPhoneActivity.this.startActivity(new Intent(LoginPwdPhoneActivity.this, LoginPwdActivity.class));
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        startActivityForResult(new Intent(this, CountryPhoneCodePickerActivity.class), 1101);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mIsLoginingFB) {
            super.onActivityResult(i, i2, intent);
        } else if (i != 1101) {
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
        } else if (i2 == -1 && intent != null) {
            String stringExtra = intent.getStringExtra("country_phone_code");
            if (!TextUtils.isEmpty(stringExtra)) {
                TextView textView = this.g;
                textView.setText("+" + stringExtra);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        findViewById(R.id.sh_login_register).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                StatHelper.b();
                MiLoginApi.a((Activity) LoginPwdPhoneActivity.this, 102, CoreApi.a().I(), LoginPwdPhoneActivity.this.getString(R.string.login_new_user_register));
            }
        });
        findViewById(R.id.sh_login_forget_password).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                StatHelper.a();
                MiLoginApi.b(LoginPwdPhoneActivity.this, 101, CoreApi.a().I(), LoginPwdPhoneActivity.this.getString(R.string.login_forget_password));
            }
        });
        if (ServerUtil.a()) {
            this.vPhoneLogin.setVisibility(0);
            this.vPhoneLogin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MjLoginRouter.c(LoginPwdPhoneActivity.this.mContext, LoginPwdPhoneActivity.this.e);
                }
            });
        } else {
            this.vPhoneLogin.setVisibility(0);
        }
        this.vPwdLogin.setVisibility(8);
        this.d.setOnPwdLoginListener(new OnPwdLoginListener() {
            public void a(final String str, String str2, String str3) {
                LoginUtil.a((Activity) LoginPwdPhoneActivity.this);
                if (!NetworkUtils.a()) {
                    ToastManager.a().a((int) R.string.login_network_not_available);
                    return;
                }
                SHApplication.initAccount();
                MiLoginApi.a(LoginPwdPhoneActivity.this.getApplicationContext());
                LoginPwdPhoneActivity.this.vLoadingDialog.setMessage(LoginPwdPhoneActivity.this.getString(R.string.login_passport_login_waiting));
                LoginPwdPhoneActivity.this.vLoadingDialog.show();
                LoginPwdPhoneActivity.this.mLoginManager.a(LoginPwdPhoneActivity.this.e.f23476a, str, str2, str3, (PhonePwdLoginCallback) new PhonePwdLoginCallback() {
                    public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                        try {
                            LoginPwdPhoneActivity.this.dismissLoginDialog();
                            LoginPwdPhoneActivity.this.processLoginSuccess(10);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void onLoginFail(int i, String str, Map<String, String> map) {
                        try {
                            LoginPwdPhoneActivity.this.dismissLoginDialog();
                            if (i == -5203) {
                                ToastManager.a().a((int) R.string.milogin_fail_token_expired);
                                LoginPwdPhoneActivity.this.d.showUserName();
                            } else {
                                if (i != -5008) {
                                    if (i != -5009) {
                                        if (i == -4003) {
                                            ToastManager.a().a((int) R.string.login_cancel);
                                        } else {
                                            ToastManager.a().a(LoginHelper.a(LoginPwdPhoneActivity.this.getString(R.string.login_fail_patch_installed), i, str));
                                        }
                                    }
                                }
                                ToastManager.a().a((int) R.string.login_passport_input_fail);
                            }
                            FrameManager.b().g().b();
                            LoginPwdPhoneActivity.this.processLoginFail();
                            LoginBaseActivity.logLoginFailEvent(LoginType.i, i, str, map);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }

                    public void a(String str, boolean z) {
                        try {
                            if (!LoginPwdPhoneActivity.this.isFinishing()) {
                                if (!z || !LoginPwdPhoneActivity.this.vCaptchaDialog.a()) {
                                    LoginBaseActivity.logLoginFailEvent(LoginType.i, -1, "onPwdLoginNeedCaptcha captcha error", (Map<String, String>) new HashMap());
                                    if (LoginPwdPhoneActivity.this.vLoadingDialog != null && LoginPwdPhoneActivity.this.vLoadingDialog.isShowing()) {
                                        LoginPwdPhoneActivity.this.vLoadingDialog.dismiss();
                                    }
                                    if (LoginPwdPhoneActivity.this.vCaptchaDialog.a()) {
                                        LoginPwdPhoneActivity.this.vCaptchaDialog.c();
                                    }
                                    LoginPwdPhoneActivity.this.vCaptchaDialog.a(str);
                                    LoginPwdPhoneActivity.this.vCaptchaDialog.a((CaptchaDialog.OnCaptchaSureClickListener) new CaptchaDialog.OnCaptchaSureClickListener() {
                                        public void a(String str, String str2) {
                                            LoginPwdPhoneActivity.this.vLoadingDialog.setMessage(LoginPwdPhoneActivity.this.getString(R.string.login_send_ticket_loading));
                                            LoginPwdPhoneActivity.this.vLoadingDialog.show();
                                            AnonymousClass6.this.a(str, str, str2);
                                        }

                                        public void a() {
                                            LoginPwdPhoneActivity.this.dismissLoginDialog();
                                            ToastManager.a().a((int) R.string.login_cancel);
                                            LoginBaseActivity.logLoginFailEvent(LoginType.i, -1, "onPwdLoginNeedCaptcha captcha cancel", (Map<String, String>) new HashMap());
                                        }
                                    });
                                    return;
                                }
                                LoginPwdPhoneActivity.this.onLoginFinish();
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
                    LoginUtil.a((Activity) LoginPwdPhoneActivity.this);
                    if (!NetworkUtils.a()) {
                        ToastManager.a().a((int) R.string.login_network_not_available);
                        return;
                    }
                    LoginPwdPhoneActivity.this.vLoadingDialog.setCancelable(true);
                    LoginPwdPhoneActivity.this.vLoadingDialog.setMessage(LoginPwdPhoneActivity.this.getString(R.string.login_passport_login_waiting));
                    LoginPwdPhoneActivity.this.vLoadingDialog.show();
                    LoginPwdPhoneActivity.this.mLoginManager.a(str, str2, str3, str4, (PwdLoginCallback) new PwdLoginCallback() {
                        public void a(String str, boolean z) {
                            try {
                                if (!LoginPwdPhoneActivity.this.isFinishing()) {
                                    if (!z || !LoginPwdPhoneActivity.this.vCaptchaDialog.a()) {
                                        LoginBaseActivity.logLoginFailEvent(LoginType.b, -1, "onPwdLoginNeedCaptcha captcha error", (Map<String, String>) new HashMap());
                                        if (LoginPwdPhoneActivity.this.vLoadingDialog != null && LoginPwdPhoneActivity.this.vLoadingDialog.isShowing()) {
                                            LoginPwdPhoneActivity.this.vLoadingDialog.dismiss();
                                        }
                                        if (LoginPwdPhoneActivity.this.vCaptchaDialog.a()) {
                                            LoginPwdPhoneActivity.this.vCaptchaDialog.c();
                                        }
                                        LoginPwdPhoneActivity.this.vCaptchaDialog.a(str);
                                        LoginPwdPhoneActivity.this.vCaptchaDialog.a((CaptchaDialog.OnCaptchaSureClickListener) new CaptchaDialog.OnCaptchaSureClickListener() {
                                            public void a(String str, String str2) {
                                                LoginPwdPhoneActivity.this.vLoadingDialog.setMessage(LoginPwdPhoneActivity.this.getString(R.string.login_passport_login_waiting));
                                                LoginPwdPhoneActivity.this.vLoadingDialog.show();
                                                AnonymousClass6.this.a(LoginPwdPhoneActivity.this.d.getUserName(), LoginPwdPhoneActivity.this.d.getPwd(), str, str2);
                                            }

                                            public void a() {
                                                LoginPwdPhoneActivity.this.dismissLoginDialog();
                                                ToastManager.a().a((int) R.string.login_cancel);
                                                LoginBaseActivity.logLoginFailEvent(LoginType.b, -1, "onPwdLoginNeedCaptcha captcha cancel", (Map<String, String>) new HashMap());
                                            }
                                        });
                                        return;
                                    }
                                    LoginPwdPhoneActivity.this.onLoginFinish();
                                    LoginBaseActivity.logLoginFailEvent(LoginType.b, -1, "onPwdLoginNeedCaptcha onPwdLoginNeedCaptcha account id or pwd error", (Map<String, String>) new HashMap());
                                    ToastManager.a().a((int) R.string.login_passport_input_fail);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                            try {
                                LoginPwdPhoneActivity.this.dismissLoginDialog();
                                LoginPwdPhoneActivity.this.processLoginSuccess(3);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        public void onLoginFail(int i, String str, Map<String, String> map) {
                            try {
                                LoginPwdPhoneActivity.this.dismissLoginDialog();
                                FrameManager.b().m();
                                if (i == -3) {
                                    LoginHelper.a((Activity) LoginPwdPhoneActivity.this, i);
                                } else {
                                    if (i != -5008) {
                                        if (i != -5009) {
                                            if (i == -4003) {
                                                ToastManager.a().a((int) R.string.login_cancel);
                                            } else {
                                                ToastManager.a().a(LoginHelper.a(LoginPwdPhoneActivity.this.getString(R.string.login_passport_login_fail), i, str));
                                            }
                                        }
                                    }
                                    ToastManager.a().a(LoginPwdPhoneActivity.this.getString(R.string.login_passport_input_fail));
                                }
                                FrameManager.b().g().b();
                                LoginPwdPhoneActivity.this.processLoginFail();
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
        this.e = LoginIntentUtil.e(this.mIntent);
        if (this.e == null || this.e.c != 2 || !this.e.b.hasPwd) {
            String c2 = LoginIntentUtil.c(this.mIntent);
            if (TextUtils.isEmpty(c2)) {
                this.d.showUserName();
            } else {
                this.d.showUserName(c2);
            }
        } else {
            this.d.showLocalPhone(this.e.f23476a.phone, this.e.f23476a.copyWriter, this.e.f23476a.operatorLink);
        }
    }
}
