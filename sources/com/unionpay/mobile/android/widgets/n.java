package com.unionpay.mobile.android.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;

final class n extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ m f9798a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    n(m mVar, Context context) {
        super(context);
        this.f9798a = mVar;
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
