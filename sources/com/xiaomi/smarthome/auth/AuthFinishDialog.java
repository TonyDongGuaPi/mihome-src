package com.xiaomi.smarthome.auth;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class AuthFinishDialog extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    private TextView f13799a;
    private TextView b;

    public AuthFinishDialog(Context context) {
        this(context, 2131559365);
    }

    public AuthFinishDialog(Context context, int i) {
        super(context, i);
    }

    public AuthFinishDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.auth_finish_dialog_layout);
        this.f13799a = (TextView) findViewById(R.id.back_to_caller);
        this.b = (TextView) findViewById(R.id.go_smarthome);
    }

    private void a(View.OnClickListener onClickListener) {
        this.f13799a.setOnClickListener(onClickListener);
    }

    private void b(View.OnClickListener onClickListener) {
        this.b.setOnClickListener(onClickListener);
    }
}
