package com.unionpay.mobile.android.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.unionpay.mobile.android.net.c;
import com.unionpay.mobile.android.net.d;
import java.lang.ref.WeakReference;

public final class q implements Handler.Callback, Runnable {

    /* renamed from: a  reason: collision with root package name */
    private d f9746a = null;
    private Handler b = null;
    private WeakReference<a> c = null;
    private Context d;

    public interface a {
        void a(int i, byte[] bArr);
    }

    public q(Context context, String str, a aVar) {
        this.f9746a = new d(0, str, (byte[]) null);
        this.b = new Handler(this);
        this.c = new WeakReference<>(aVar);
        this.d = context;
    }

    public final boolean handleMessage(Message message) {
        if (message.what != 0 || this.c == null || this.c.get() == null) {
            return true;
        }
        ((a) this.c.get()).a(message.arg1, message.obj != null ? (byte[]) message.obj : null);
        return true;
    }

    public final void run() {
        c cVar = new c(this.f9746a, this.d);
        int a2 = cVar.a();
        if (this.b != null) {
            Message obtainMessage = this.b.obtainMessage();
            obtainMessage.what = 0;
            obtainMessage.arg1 = a2;
            obtainMessage.obj = cVar.b();
            this.b.sendMessage(obtainMessage);
        }
    }
}
