package com.alipay.zoloz.toyger.workspace;

import android.graphics.Bitmap;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.workspace.BioFragmentResponse;
import com.alipay.zoloz.toyger.face.ToygerFaceAttr;
import com.alipay.zoloz.toyger.util.Avatar;

class k implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Bitmap f1228a;
    final /* synthetic */ ToygerFaceAttr b;
    final /* synthetic */ BioFragmentResponse c;
    final /* synthetic */ ToygerWorkspace d;

    k(ToygerWorkspace toygerWorkspace, Bitmap bitmap, ToygerFaceAttr toygerFaceAttr, BioFragmentResponse bioFragmentResponse) {
        this.d = toygerWorkspace;
        this.f1228a = bitmap;
        this.b = toygerFaceAttr;
        this.c = bioFragmentResponse;
    }

    public void run() {
        try {
            Thread.sleep((long) ToygerWorkspace.AUTH_IN_BACKGROUND_GEN_AVATAR_DELAY_TIME);
            BioLog.i("zolozTime", "gen avatar!");
            this.c.ext.put("avatar", Avatar.genAvatar(this.f1228a, this.b));
            this.c.token = this.d.mToygerCallback.getAppDescription().getBistoken();
            this.d.mToygerCallback.sendAvatarResponse(this.c);
            BioLog.i("zolozTime", "send avatar!");
        } catch (Exception e) {
            BioLog.w((Throwable) e);
        }
    }
}
