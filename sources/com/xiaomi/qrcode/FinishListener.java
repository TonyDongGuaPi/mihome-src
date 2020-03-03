package com.xiaomi.qrcode;

import android.app.Activity;
import android.content.DialogInterface;

public final class FinishListener implements DialogInterface.OnCancelListener, DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private final Activity f12961a;

    public FinishListener(Activity activity) {
        this.f12961a = activity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        a();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        a();
    }

    private void a() {
        this.f12961a.finish();
    }
}
