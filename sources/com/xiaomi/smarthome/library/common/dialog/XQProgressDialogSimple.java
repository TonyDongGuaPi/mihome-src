package com.xiaomi.smarthome.library.common.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.library.R;

public class XQProgressDialogSimple extends AlertDialog {

    /* renamed from: a  reason: collision with root package name */
    private TextView f18620a;
    private CharSequence b;
    private Context c;

    public void a(boolean z) {
    }

    public boolean a() {
        return true;
    }

    public XQProgressDialogSimple(Context context) {
        this(context, R.style.XQProgressDialogSimple);
    }

    public XQProgressDialogSimple(Context context, int i) {
        super(context, i);
        this.c = context;
        a(true);
    }

    public static XQProgressDialogSimple a(Context context) {
        return a(context, context.getString(R.string.refreshing_no_point));
    }

    public static XQProgressDialogSimple a(Context context, CharSequence charSequence) {
        return a(context, charSequence, true);
    }

    public static XQProgressDialogSimple a(Context context, CharSequence charSequence, boolean z) {
        return a(context, charSequence, z, (DialogInterface.OnCancelListener) null);
    }

    public static XQProgressDialogSimple a(Context context, CharSequence charSequence, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        XQProgressDialogSimple xQProgressDialogSimple = new XQProgressDialogSimple(context);
        xQProgressDialogSimple.setMessage(charSequence);
        xQProgressDialogSimple.setCancelable(z);
        xQProgressDialogSimple.setOnCancelListener(onCancelListener);
        xQProgressDialogSimple.setCanceledOnTouchOutside(false);
        xQProgressDialogSimple.show();
        return xQProgressDialogSimple;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.xq_progress_dialog_simple, (ViewGroup) null);
        setContentView(inflate);
        this.f18620a = (TextView) inflate.findViewById(R.id.progress_message);
        setCanceledOnTouchOutside(false);
        if (this.b != null) {
            setMessage(this.b);
        }
    }

    public void setMessage(CharSequence charSequence) {
        if (this.f18620a != null) {
            this.f18620a.setText(charSequence);
        } else {
            this.b = charSequence;
        }
    }
}
