package com.unionpay.mobile.android.nocard.views;

import android.view.MotionEvent;
import android.view.View;

final class bj implements View.OnTouchListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ bi f9616a;

    bj(bi biVar) {
        this.f9616a = biVar;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
            case 1:
                if (view.hasFocus()) {
                    return false;
                }
                view.requestFocus();
                return false;
            default:
                return false;
        }
    }
}
