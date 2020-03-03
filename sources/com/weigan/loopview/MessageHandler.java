package com.weigan.loopview;

import android.os.Handler;
import android.os.Message;
import com.weigan.loopview.LoopView;

final class MessageHandler extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public static final int f9868a = 1000;
    public static final int b = 2000;
    public static final int c = 3000;
    final LoopView d;

    MessageHandler(LoopView loopView) {
        this.d = loopView;
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1000) {
            this.d.invalidate();
        } else if (i == 2000) {
            this.d.smoothScroll(LoopView.ACTION.FLING);
        } else if (i == 3000) {
            this.d.onItemSelected();
        }
    }
}
