package com.alipay.zoloz.toyger.workspace;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.alipay.mobile.security.bio.utils.BioLog;

class j implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Bitmap f1227a;
    final /* synthetic */ ToygerWorkspace b;

    j(ToygerWorkspace toygerWorkspace, Bitmap bitmap) {
        this.b = toygerWorkspace;
        this.f1227a = bitmap;
    }

    public void run() {
        try {
            if (this.b.mToygerCirclePattern != null) {
                this.b.mToygerCirclePattern.getGuassianBackground().setVisibility(0);
                this.b.mToygerCirclePattern.getGuassianBackground().setBackgroundDrawable(new BitmapDrawable(this.b.mBioServiceManager.getBioApplicationContext().getResources(), this.f1227a));
            }
            BioLog.i("onHighQualityFrame 显示成功");
        } catch (Throwable th) {
            BioLog.e("显示HighQualityFrame出现异常！", th);
        }
    }
}
