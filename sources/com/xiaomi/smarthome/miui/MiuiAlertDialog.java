package com.xiaomi.smarthome.miui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import miui.app.AlertDialog;

public class MiuiAlertDialog extends AlertDialog implements DialogInterface {

    /* renamed from: a  reason: collision with root package name */
    private View f20127a;
    private Context b;

    public MiuiAlertDialog(Context context, int i) {
        super(context, i);
        this.b = context;
    }

    public void dismiss() {
        try {
            a();
            MiuiAlertDialog.super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a() {
        InputMethodManager inputMethodManager;
        if (this.f20127a != null && (inputMethodManager = (InputMethodManager) this.b.getSystemService("input_method")) != null) {
            inputMethodManager.hideSoftInputFromWindow(this.f20127a.getWindowToken(), 0);
        }
    }

    public void setView(View view) {
        MiuiAlertDialog.super.setView(view);
        this.f20127a = view;
    }
}
