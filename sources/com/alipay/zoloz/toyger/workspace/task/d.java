package com.alipay.zoloz.toyger.workspace.task;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Bitmap f1237a;
    final /* synthetic */ ToygerBaseTask b;

    d(ToygerBaseTask toygerBaseTask, Bitmap bitmap) {
        this.b = toygerBaseTask;
        this.f1237a = bitmap;
    }

    public void run() {
        if (this.b.mToygerCirclePattern != null) {
            this.b.mToygerCirclePattern.getGuassianBackground().setVisibility(0);
            this.b.mToygerCirclePattern.getGuassianBackground().setBackgroundDrawable(new BitmapDrawable(this.b.mContext.getResources(), this.f1237a));
        }
    }
}
