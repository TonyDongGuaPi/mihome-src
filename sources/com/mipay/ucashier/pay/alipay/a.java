package com.mipay.ucashier.pay.alipay;

import android.os.Handler;
import android.os.Message;
import com.alipay.sdk.app.PayTask;
import com.mipay.common.base.l;

class a extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ l f8184a;
    final /* synthetic */ String b;
    final /* synthetic */ Handler c;
    final /* synthetic */ AlipayEntry d;

    a(AlipayEntry alipayEntry, l lVar, String str, Handler handler) {
        this.d = alipayEntry;
        this.f8184a = lVar;
        this.b = str;
        this.c = handler;
    }

    public void run() {
        String pay = new PayTask(this.f8184a.getActivity()).pay(this.b);
        Message message = new Message();
        message.what = 1;
        message.obj = pay;
        this.c.sendMessage(message);
    }
}
