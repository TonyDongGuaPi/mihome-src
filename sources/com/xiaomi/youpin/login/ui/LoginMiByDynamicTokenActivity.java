package com.xiaomi.youpin.login.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.youpin.login.R;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.manager.LoginManager;
import com.xiaomi.youpin.login.api.manager.callback.DynamicTokenLoginCallback;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.other.common.TitleBarUtil;
import com.xiaomi.youpin.login.ui.baseui.BaseActivity;
import com.xiaomi.youpin.login.ui.baseui.ToastManager;
import com.xiaomi.youpin.login.view.LoginCommonTitleBar;
import java.util.Map;

public class LoginMiByDynamicTokenActivity extends BaseActivity implements DynamicTokenLoginCallback {
    public static final String ACTION_TOKEN_COMPLETE = "action.mi.token.complete";
    public static final String BUNDLE_KEY_META_LOGIN_DATA = "key_meta_login_data";
    public static final String BUNDLE_KEY_STEP1TOKEN = "key_step1token";
    public static final String BUNDLE_KEY_USERNAME = "key_username";
    public static final String PARMA_ERRORCMSG = "action.mi.token.data.errormsg";
    public static final String PARMA_ERRORCODE = "action.mi.token.data.errorcode";
    public static final String PARMA_MIACCOUNT = "action.mi.token.data.account";
    public static final String PARMA_STATE = "action.mi.token.data.state";

    /* renamed from: a  reason: collision with root package name */
    private LoginCommonTitleBar f23598a;
    /* access modifiers changed from: private */
    public EditText b;
    private View c;
    /* access modifiers changed from: private */
    public CheckBox d;
    private ProgressDialog e;
    private MetaLoginData f;
    private String g;
    private String h;
    private LoginManager i;

    private void a(int i2, String str, Map<String, String> map) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.login_mi_dynamic_token_activity);
        this.f23598a = (LoginCommonTitleBar) findViewById(R.id.mishop_login_dt_title_bar);
        TitleBarUtil.a((View) this.f23598a);
        this.f23598a.setOnBackClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginMiByDynamicTokenActivity.this.onBackPressed();
            }
        });
        this.i = new LoginManager(this);
        this.b = (EditText) findViewById(R.id.token_editor);
        this.c = findViewById(R.id.login_button);
        this.d = (CheckBox) findViewById(R.id.trust_checkbox);
        this.e = new ProgressDialog(this);
        this.b.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                LoginMiByDynamicTokenActivity.this.a();
            }
        });
        ((ToggleButton) findViewById(R.id.token_toggle)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                int selectionStart = LoginMiByDynamicTokenActivity.this.b.getSelectionStart();
                if (z) {
                    LoginMiByDynamicTokenActivity.this.b.setInputType(144);
                } else {
                    LoginMiByDynamicTokenActivity.this.b.setInputType(129);
                }
                LoginMiByDynamicTokenActivity.this.b.setSelection(selectionStart);
            }
        });
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginMiByDynamicTokenActivity.this.a(LoginMiByDynamicTokenActivity.this.b.getText().toString(), LoginMiByDynamicTokenActivity.this.d.isChecked());
            }
        });
        a();
        Intent intent = getIntent();
        this.f = (MetaLoginData) intent.getParcelableExtra(BUNDLE_KEY_META_LOGIN_DATA);
        this.g = intent.getStringExtra(BUNDLE_KEY_USERNAME);
        this.h = intent.getStringExtra(BUNDLE_KEY_STEP1TOKEN);
    }

    /* access modifiers changed from: private */
    public void a() {
        String obj = this.b.getText().toString();
        if (TextUtils.isEmpty(obj) || obj.length() != 6) {
            this.c.setEnabled(false);
        } else {
            this.c.setEnabled(true);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, boolean z) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.b.getWindowToken(), 0);
        }
        this.e.setMessage(getString(R.string.milogin_passport_login_waiting));
        this.e.show();
        this.i.a(this.g, this.h, str, this.f, z, this);
    }

    public void onLoginSuccess(LoginMiAccount loginMiAccount) {
        b();
        a(loginMiAccount);
    }

    public void onLoginFail(int i2, String str, Map<String, String> map) {
        b();
        if (i2 == -999) {
            ToastManager.a((Context) this).a(R.string.milogin_passport_login_fail);
        } else if (i2 == -4001) {
            ToastManager.a((Context) this).a(R.string.milogin_dynamic_token_invalid_credential_exception);
        } else if (i2 == -4002) {
            ToastManager.a((Context) this).a(R.string.milogin_dynamic_token_access_denied_exception);
        } else if (i2 == -4000) {
            ToastManager.a((Context) this).a(R.string.milogin_dynamic_token_invalid_credential_exception);
        } else {
            ToastManager.a((Context) this).a(R.string.milogin_fail_patch_installed);
        }
        a(i2, str, map);
    }

    private void a(LoginMiAccount loginMiAccount) {
        b(loginMiAccount);
        finish();
    }

    private void b() {
        if (this.e != null && this.e.isShowing() && !isFinishing()) {
            this.e.dismiss();
        }
    }

    private void b(LoginMiAccount loginMiAccount) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(getApplicationContext());
        Intent intent = new Intent(ACTION_TOKEN_COMPLETE);
        intent.putExtra(PARMA_STATE, true);
        intent.putExtra(PARMA_MIACCOUNT, loginMiAccount);
        instance.sendBroadcast(intent);
    }

    private void a(int i2, String str) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(getApplicationContext());
        Intent intent = new Intent(ACTION_TOKEN_COMPLETE);
        intent.putExtra(PARMA_STATE, false);
        intent.putExtra(PARMA_ERRORCODE, i2);
        intent.putExtra(PARMA_ERRORCMSG, str);
        instance.sendBroadcast(intent);
    }

    public void onBackPressed() {
        a((int) LoginErrorCode.G, "动态Token登录已取消");
        super.onBackPressed();
    }
}
