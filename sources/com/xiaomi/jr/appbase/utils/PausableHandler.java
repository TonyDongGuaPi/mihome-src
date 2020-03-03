package com.xiaomi.jr.appbase.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

public class PausableHandler extends Handler {
    private static final String b = "PausableHandler";

    /* renamed from: a  reason: collision with root package name */
    final List<Message> f10324a = new ArrayList();
    private boolean c;

    public PausableHandler(Looper looper) {
        super(looper);
    }

    public final void a() {
        this.c = false;
        for (Message sendMessage : this.f10324a) {
            sendMessage(sendMessage);
        }
        this.f10324a.clear();
    }

    public final void b() {
        this.c = true;
    }

    public final void c() {
        b();
        this.f10324a.clear();
    }

    public final void dispatchMessage(Message message) {
        if (this.c) {
            this.f10324a.add(Message.obtain(message));
        } else {
            super.dispatchMessage(message);
        }
    }
}
