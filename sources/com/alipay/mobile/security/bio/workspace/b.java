package com.alipay.mobile.security.bio.workspace;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.alipay.mobile.security.bio.config.Constant;
import com.alipay.mobile.security.bio.utils.BioLog;

class b extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BioFragmentContainer f1032a;

    b(BioFragmentContainer bioFragmentContainer) {
        this.f1032a = bioFragmentContainer;
    }

    public void onReceive(Context context, Intent intent) {
        if (Constant.BIOLOGY_FLAG_AUTOCLOSE.equals(intent.getAction())) {
            this.f1032a.verifyCallBackEvent();
            BioLog.i("verifyCallBackEvent rev");
            this.f1032a.commandFinished();
        }
        this.f1032a.onReceiveAction(intent);
    }
}
