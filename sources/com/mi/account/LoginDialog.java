package com.mi.account;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class LoginDialog extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    private Context f6695a;
    /* access modifiers changed from: private */
    public IOnButtonClickListener b;

    public interface IOnButtonClickListener {
        void a();

        void b();
    }

    public LoginDialog(@NonNull Context context) {
        this(context, R.style.LoginDialogStyle);
    }

    public void a(IOnButtonClickListener iOnButtonClickListener) {
        this.b = iOnButtonClickListener;
    }

    private LoginDialog(@NonNull Context context, int i) {
        super(context, i);
        this.f6695a = context;
        setContentView(R.layout.dialog_login);
        Window window = getWindow();
        if (window != null) {
            int a2 = a();
            window.getDecorView().setPadding(a2, a2, a2, a2);
            window.setLayout(-1, -2);
            window.setGravity(80);
        }
        ((TextView) findViewById(R.id.tv_login_dialog_content)).setText(String.format(context.getResources().getString(R.string.autologin_summary), new Object[]{LoginManager.b().k()}));
        findViewById(R.id.tv_system_login).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginDialog.this.b != null) {
                    LoginDialog.this.b.a();
                    LoginDialog.this.dismiss();
                }
            }
        });
        findViewById(R.id.tv_login_local).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginDialog.this.b != null) {
                    LoginDialog.this.b.b();
                    LoginDialog.this.dismiss();
                }
            }
        });
    }

    private int a() {
        return (int) TypedValue.applyDimension(1, 8.0f, this.f6695a.getResources().getDisplayMetrics());
    }
}
