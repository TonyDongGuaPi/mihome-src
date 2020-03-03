package com.xiaomi.smarthome.frame.login.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.baseui.BaseActivity;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.frame.login.ui.view.LoginBaseTitleBar;
import com.xiaomi.smarthome.frame.login.util.LoginUtil;
import com.xiaomi.smarthome.frame.plugin.runtime.util.TitleBarUtil;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.callback.GetCaptchaImageCallback;
import com.xiaomi.youpin.login.api.callback.PwdChangeCallback;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;

public class PwdChangeActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private TextView f16272a;
    /* access modifiers changed from: private */
    public EditText b;
    private EditText c;
    private EditText d;
    /* access modifiers changed from: private */
    public View e;
    /* access modifiers changed from: private */
    public ImageView f;
    /* access modifiers changed from: private */
    public ProgressDialog g;
    private CheckBox h;
    /* access modifiers changed from: private */
    public String i = "";

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smarthome_login_pwd_change_act);
        LoginBaseTitleBar loginBaseTitleBar = (LoginBaseTitleBar) findViewById(R.id.title_bar);
        loginBaseTitleBar.setOnBackClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PwdChangeActivity.this.onBackPressed();
            }
        });
        TitleBarUtil.enableTranslucentStatus((Activity) this);
        TitleBarUtil.setTitleBar(this, loginBaseTitleBar);
        this.b = (EditText) findViewById(R.id.old_password);
        this.c = (EditText) findViewById(R.id.new_password);
        this.h = (CheckBox) findViewById(R.id.show_pwd_cb);
        a(this.h.isChecked());
        this.h.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PwdChangeActivity.this.a(z);
            }
        });
        this.f16272a = (TextView) findViewById(R.id.confirm);
        this.f16272a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginUtil.a((Activity) PwdChangeActivity.this);
                PwdChangeActivity.this.a();
            }
        });
        this.e = findViewById(R.id.cacha_container);
        this.d = (EditText) findViewById(R.id.capcha_input);
        this.f = (ImageView) findViewById(R.id.capcha_image);
        this.b.requestFocus();
        LoginUtil.a((Context) this, (View) this.b);
    }

    public void onDestroy() {
        LoginUtil.a((Activity) this);
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public void a() {
        String trim = this.b.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            ToastManager.a().a((int) R.string.login_pwd_change_current_hint);
            this.b.requestFocus();
            return;
        }
        String trim2 = this.c.getText().toString().trim();
        if (TextUtils.isEmpty(trim2)) {
            ToastManager.a().a((int) R.string.login_pwd_change_new_hint);
            this.c.requestFocus();
            return;
        }
        int b2 = LoginUtil.b(trim2);
        if (b2 > 0) {
            ToastManager.a().a(b2);
            this.c.requestFocus();
        } else if (trim2.equals(trim)) {
            ToastManager.a().a((int) R.string.login_pwd_change_cannot_same);
            this.c.requestFocus();
        } else {
            this.g = ProgressDialog.show(this, (CharSequence) null, getResources().getText(R.string.login_pwd_change_loading));
            this.g.setCancelable(false);
            String str = "";
            if (b()) {
                str = this.d.getText().toString();
            }
            MiLoginApi.a(CoreApi.a().y(), trim, trim2, str, this.i, new PwdChangeCallback() {
                public void a(String str) {
                    if (!PwdChangeActivity.this.isFinishing()) {
                        PwdChangeActivity.this.g.dismiss();
                    }
                    if (PwdChangeActivity.this.b()) {
                        ToastManager.a().a((int) R.string.login_captcha_error_hint);
                    }
                    PwdChangeActivity.this.a(str);
                }

                public void a(Void voidR) {
                    LoginApi.a().a(true, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            if (!PwdChangeActivity.this.isFinishing()) {
                                PwdChangeActivity.this.g.dismiss();
                            }
                            ToastManager.a().a((int) R.string.login_pwd_change_logout_success);
                            PwdChangeActivity.this.setResult(-1);
                            PwdChangeActivity.this.finish();
                        }

                        public void onFailure(Error error) {
                            if (!PwdChangeActivity.this.isFinishing()) {
                                PwdChangeActivity.this.g.dismiss();
                            }
                            ToastManager.a().a((int) R.string.login_pwd_change_failed);
                        }
                    });
                }

                public void a(int i, String str) {
                    if (!PwdChangeActivity.this.isFinishing()) {
                        PwdChangeActivity.this.g.dismiss();
                    }
                    if (i == -101) {
                        ToastManager.a().a((int) R.string.login_pwd_change_current_error);
                        PwdChangeActivity.this.b.requestFocus();
                        return;
                    }
                    ToastManager.a().a((int) R.string.login_pwd_change_failed);
                }

                public void a(MiServiceTokenInfo miServiceTokenInfo) {
                    if (CoreApi.a().q()) {
                        CoreApi.a().a(miServiceTokenInfo, (AsyncCallback<Void, Error>) null);
                    }
                }
            });
        }
    }

    public void onBackPressed() {
        LoginUtil.a((Activity) this);
        super.onBackPressed();
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            this.b.setTransformationMethod((TransformationMethod) null);
            this.c.setTransformationMethod((TransformationMethod) null);
            this.h.setContentDescription(getString(R.string.smart_config_hide_passwd));
            return;
        }
        this.b.setTypeface(Typeface.DEFAULT);
        this.b.setTransformationMethod(new PasswordTransformationMethod());
        this.c.setTypeface(Typeface.DEFAULT);
        this.c.setTransformationMethod(new PasswordTransformationMethod());
        this.h.setContentDescription(getString(R.string.smart_config_show_passwd));
    }

    /* access modifiers changed from: private */
    public void a(final String str) {
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PwdChangeActivity.this.a(str);
            }
        });
        MiLoginApi.a(str, (GetCaptchaImageCallback) new GetCaptchaImageCallback() {
            public void a(Bitmap bitmap, String str) {
                PwdChangeActivity.this.e.setVisibility(0);
                String unused = PwdChangeActivity.this.i = str;
                int a2 = DisplayUtils.a(PwdChangeActivity.this.getApplicationContext(), 40.0f);
                int width = (bitmap.getWidth() * a2) / bitmap.getHeight();
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) PwdChangeActivity.this.f.getLayoutParams();
                layoutParams.width = width;
                layoutParams.height = a2;
                PwdChangeActivity.this.f.setLayoutParams(layoutParams);
                PwdChangeActivity.this.f.setScaleType(ImageView.ScaleType.FIT_XY);
                PwdChangeActivity.this.f.setImageBitmap(bitmap);
            }

            public void a() {
                ToastManager.a().a(PwdChangeActivity.this.getString(R.string.login_pwd_change_get_captcha_error));
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean b() {
        return this.e != null && this.e.getVisibility() == 0;
    }
}
