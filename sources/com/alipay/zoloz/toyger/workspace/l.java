package com.alipay.zoloz.toyger.workspace;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.alipay.mobile.security.bio.utils.BioLog;

class l implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Bitmap f1229a;
    final /* synthetic */ ToygerWorkspace b;

    l(ToygerWorkspace toygerWorkspace, Bitmap bitmap) {
        this.b = toygerWorkspace;
        this.f1229a = bitmap;
    }

    public void run() {
        try {
            if (this.b.mToygerCirclePattern != null && this.f1229a != null) {
                this.b.mToygerCirclePattern.getGuassianBackground().setVisibility(0);
                this.b.mToygerCirclePattern.getGuassianBackground().setBackgroundDrawable(new BitmapDrawable(this.b.mBioServiceManager.getBioApplicationContext().getResources(), this.f1229a));
            }
        } catch (Throwable th) {
            BioLog.e(th);
        }
    }
}
