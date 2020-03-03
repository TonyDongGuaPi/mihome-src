package com.unionpay;

import android.os.Message;
import com.unionpay.a.c;

final class d extends Thread {
    d() {
    }

    public final void run() {
        UPPayAssistEx.R.sendEmptyMessageDelayed(1001, 800);
        c cVar = new c(UPPayAssistEx.Q, UPPayAssistEx.G);
        cVar.a();
        String b = cVar.b();
        if (UPPayAssistEx.R != null) {
            Message obtainMessage = UPPayAssistEx.R.obtainMessage();
            obtainMessage.what = 1002;
            obtainMessage.obj = b;
            UPPayAssistEx.R.removeMessages(1001);
            UPPayAssistEx.R.sendMessage(obtainMessage);
        }
    }
}
