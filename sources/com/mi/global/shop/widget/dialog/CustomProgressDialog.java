package com.mi.global.shop.widget.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.BaseActivity;

public class CustomProgressDialog extends ProgressDialog {

    /* renamed from: a  reason: collision with root package name */
    private Context f7201a;

    public CustomProgressDialog(Context context) {
        super(context);
        this.f7201a = context;
        c();
    }

    private void c() {
        setMessage(this.f7201a.getString(R.string.please_wait));
        setIndeterminate(true);
        setCancelable(false);
    }

    public void a() {
        if (BaseActivity.isActivityAlive(this.f7201a)) {
            show();
        }
    }

    public void b() {
        if (BaseActivity.isActivityAlive(this.f7201a)) {
            dismiss();
        }
    }
}
