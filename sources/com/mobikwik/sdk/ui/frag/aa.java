package com.mobikwik.sdk.ui.frag;

import android.view.MotionEvent;
import android.view.View;

class aa implements View.OnTouchListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ y f8382a;

    aa(y yVar) {
        this.f8382a = yVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.f8382a.h.isChecked() || this.f8382a.i != 1) {
            return false;
        }
        if (motionEvent.getAction() == 1) {
            this.f8382a.c(false);
        }
        return true;
    }
}
