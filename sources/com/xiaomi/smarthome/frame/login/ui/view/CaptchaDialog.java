package com.xiaomi.smarthome.frame.login.ui.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.login.util.LoginUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.callback.GetCaptchaImageCallback;
import java.util.Timer;
import java.util.TimerTask;

public class CaptchaDialog {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Activity f16293a;
    private MLAlertDialog b;
    private View c;
    /* access modifiers changed from: private */
    public EditText d;
    /* access modifiers changed from: private */
    public ImageView e;
    private TextView f;
    /* access modifiers changed from: private */
    public OnCaptchaSureClickListener g;
    /* access modifiers changed from: private */
    public String h;

    public interface OnCaptchaSureClickListener {
        void a();

        void a(String str, String str2);
    }

    public CaptchaDialog(Activity activity) {
        this.f16293a = activity;
    }

    public void a(String str) {
        if (this.b == null) {
            this.c = LayoutInflater.from(this.f16293a).inflate(R.layout.smarthome_login_captcha_input, (ViewGroup) null);
            this.d = (EditText) this.c.findViewById(R.id.login_captcha_input);
            this.e = (ImageView) this.c.findViewById(R.id.login_captcha_img);
            this.f = (TextView) this.c.findViewById(R.id.login_captcha_error);
            this.d.requestFocus();
            this.b = new MLAlertDialog.Builder(this.f16293a).b(this.c).a((int) R.string.login_captcha_hint).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    String trim = CaptchaDialog.this.d.getText().toString().trim();
                    if (TextUtils.isEmpty(trim)) {
                        ToastManager.a().a((int) R.string.login_captcha_empty_hint);
                    } else if (CaptchaDialog.this.g != null) {
                        CaptchaDialog.this.g.a(trim, CaptchaDialog.this.h);
                    }
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CaptchaDialog.this.b();
                    if (CaptchaDialog.this.g != null) {
                        CaptchaDialog.this.g.a();
                    }
                }
            }).d(false).a(false).b();
        }
        if (!this.b.isShowing()) {
            this.b.show();
            new Timer().schedule(new TimerTask() {
                public void run() {
                    LoginUtil.a(CaptchaDialog.this.f16293a);
                }
            }, 200);
        }
        b(str);
    }

    /* access modifiers changed from: private */
    public void b(final String str) {
        this.d.setText("");
        MiLoginApi.a(str, (GetCaptchaImageCallback) new GetCaptchaImageCallback() {
            public void a() {
            }

            public void a(Bitmap bitmap, String str) {
                String unused = CaptchaDialog.this.h = str;
                CaptchaDialog.this.e.setImageBitmap(bitmap);
                CaptchaDialog.this.e.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        CaptchaDialog.this.b(str);
                    }
                });
            }
        });
    }

    public void a(OnCaptchaSureClickListener onCaptchaSureClickListener) {
        this.g = onCaptchaSureClickListener;
    }

    public boolean a() {
        return this.b != null && this.b.isShowing();
    }

    public void b() {
        if (this.b != null && this.b.isShowing()) {
            this.d.setText("");
            this.f.setVisibility(4);
            this.b.dismiss();
        }
    }

    public void c() {
        if (this.c != null) {
            this.f.setVisibility(0);
        }
    }
}
