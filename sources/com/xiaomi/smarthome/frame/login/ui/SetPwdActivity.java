package com.xiaomi.smarthome.frame.login.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.xiaomi.passport.uicontroller.PhoneLoginController;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.baseui.BaseActivity;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.frame.login.MijiaLoginManager;
import com.xiaomi.smarthome.frame.login.MjLoginRouter;
import com.xiaomi.smarthome.frame.login.ui.view.LoginBaseTitleBar;
import com.xiaomi.smarthome.frame.login.util.LoginEventUtil;
import com.xiaomi.smarthome.frame.login.util.LoginUtil;
import com.xiaomi.smarthome.frame.plugin.runtime.util.TitleBarUtil;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.youpin.login.api.manager.callback.ReLoginAfterSetPwdCallback;
import com.xiaomi.youpin.login.api.phone.PhoneQueryManager;
import com.xiaomi.youpin.login.entity.account.AccountParam;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.util.Map;

public class SetPwdActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private PhoneQueryManager f16280a;
    private TextView b;
    private EditText c;
    /* access modifiers changed from: private */
    public XQProgressDialog d;
    /* access modifiers changed from: private */
    public MijiaLoginManager e;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smarthome_login_pwd_set_act);
        LoginBaseTitleBar loginBaseTitleBar = (LoginBaseTitleBar) findViewById(R.id.title_bar);
        loginBaseTitleBar.setOnBackClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SetPwdActivity.this.onBackPressed();
            }
        });
        TitleBarUtil.enableTranslucentStatus((Activity) this);
        TitleBarUtil.setTitleBar(this, loginBaseTitleBar);
        this.c = (EditText) findViewById(R.id.new_password);
        this.c.requestFocus();
        LoginUtil.a((Context) this);
        CheckBox checkBox = (CheckBox) findViewById(R.id.show_pwd_cb);
        a(checkBox.isChecked());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SetPwdActivity.this.a(z);
            }
        });
        this.b = (TextView) findViewById(R.id.confirm);
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginUtil.a((Activity) SetPwdActivity.this);
                SetPwdActivity.this.a();
            }
        });
        this.e = new MijiaLoginManager(getApplicationContext());
        this.f16280a = new PhoneQueryManager(getApplicationContext());
    }

    public void onDestroy() {
        LoginUtil.a((Activity) this);
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public void a() {
        String obj = this.c.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            ToastManager.a().a((int) R.string.set_pwd_hint);
            this.c.requestFocus();
            return;
        }
        int b2 = LoginUtil.b(obj);
        if (b2 > 0) {
            ToastManager.a().a(b2);
            this.c.requestFocus();
            return;
        }
        this.d = XQProgressDialog.a(this, (CharSequence) null, getResources().getText(R.string.set_pwd_loading));
        this.d.setCancelable(false);
        a(obj);
    }

    private void a(final String str) {
        MiServiceTokenInfo a2 = CoreApi.a().a("passportapi");
        AccountParam accountParam = new AccountParam(CoreApi.a().s(), CoreApi.a().u(), a2 == null ? "" : a2.c, a2 == null ? "" : a2.d);
        final String s = CoreApi.a().s();
        this.f16280a.a(accountParam, CoreApi.a().w(), str, (String) null, new PhoneLoginController.SetPasswordCallback() {
            public void onSetSuccess(final String str) {
                LoginApi.a().a(true, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        SetPwdActivity.this.e.a(s, str, (ReLoginAfterSetPwdCallback) new ReLoginAfterSetPwdCallback() {
                            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                                SetPwdActivity.this.b();
                                LoginEventUtil.a(SetPwdActivity.this, true);
                                ToastManager.a().a((int) R.string.set_pwd_succeed);
                                SetPwdActivity.this.setResult(-1);
                                SetPwdActivity.this.finish();
                            }

                            public void onLoginFail(int i, String str, Map<String, String> map) {
                                SetPwdActivity.this.b();
                                ToastManager.a().a((int) R.string.set_pwd_succeed_but_login_fail);
                                SetPwdActivity.this.finish();
                            }
                        });
                    }

                    public void onFailure(Error error) {
                        SetPwdActivity.this.b();
                        ToastManager.a().a((int) R.string.set_pwd_succeed_but_login_fail);
                        SetPwdActivity.this.finish();
                    }
                });
            }

            public void onHasPassword() {
                if (!SetPwdActivity.this.isFinishing()) {
                    SetPwdActivity.this.d.dismiss();
                }
                ToastManager.a().a((int) R.string.set_pwd_err_has_password);
                SetPwdActivity.this.finish();
            }

            public void onNeedTicketOrTicketInvalid() {
                if (!SetPwdActivity.this.isFinishing()) {
                    SetPwdActivity.this.d.dismiss();
                }
                SetPwdActivity.this.onSendSmsVerifyCode(str);
            }

            public void onPassTokenInvalid() {
                if (!SetPwdActivity.this.isFinishing()) {
                    SetPwdActivity.this.d.dismiss();
                }
                ToastManager.a().a((int) R.string.set_pwd_err_passtoken_invalid);
            }

            public void onSetFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                if (!SetPwdActivity.this.isFinishing()) {
                    SetPwdActivity.this.d.dismiss();
                }
                ToastManager.a().a((int) R.string.set_pwd_err_passtoken_invalid);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.d.isShowing() && !isFinishing()) {
            this.d.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onSendSmsVerifyCode(final String str) {
        MiServiceTokenInfo a2 = CoreApi.a().a("passportapi");
        this.f16280a.a(new AccountParam(CoreApi.a().s(), CoreApi.a().u(), a2 == null ? "" : a2.c, a2 == null ? "" : a2.d), (PhoneLoginController.SendSetPwdTicketCallback) new PhoneLoginController.SendSetPwdTicketCallback() {
            public void onSentSuccess(String str) {
                SetPwdActivity.this.b();
                ToastManager.a().a((int) R.string.login_send_ticket_success);
                MjLoginRouter.b((Context) SetPwdActivity.this, str);
                SetPwdActivity.this.finish();
            }

            public void onSMSReachLimit() {
                SetPwdActivity.this.b();
                ToastManager.a().a((int) R.string.login_send_ticket_fail_sms_reach_limit);
            }

            public void onSentFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                SetPwdActivity.this.b();
                ToastManager.a().a((int) R.string.login_send_ticket_fail);
            }
        });
    }

    public void onBackPressed() {
        LoginUtil.a((Activity) this);
        super.onBackPressed();
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            this.c.setInputType(144);
        } else {
            this.c.setInputType(129);
        }
    }
}
