package com.xiaomi.push;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.push.al;

class am extends Handler {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ al f12630a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    am(al alVar, Looper looper) {
        super(looper);
        this.f12630a = alVar;
    }

    public void handleMessage(Message message) {
        al.b bVar = (al.b) message.obj;
        if (message.what == 0) {
            bVar.a();
        } else if (message.what == 1) {
            bVar.c();
        }
        super.handleMessage(message);
    }
}
