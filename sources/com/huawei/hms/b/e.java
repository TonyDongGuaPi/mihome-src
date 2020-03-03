package com.huawei.hms.b;

import android.content.DialogInterface;
import android.view.KeyEvent;

class e implements DialogInterface.OnKeyListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f5866a;

    e(a aVar) {
        this.f5866a = aVar;
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (4 != i || keyEvent.getAction() != 1) {
            return false;
        }
        this.f5866a.a();
        return true;
    }
}
