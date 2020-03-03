package com.dianping.logan;

import android.text.TextUtils;
import java.io.File;

public abstract class SendLogRunnable implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public static final int f5173a = 10001;
    public static final int b = 10002;
    private SendAction c;
    private OnSendLogCallBackListener d;

    interface OnSendLogCallBackListener {
        void a(int i);
    }

    public abstract void a(File file);

    /* access modifiers changed from: package-private */
    public void a(SendAction sendAction) {
        this.c = sendAction;
    }

    public void run() {
        if (this.c == null || TextUtils.isEmpty(this.c.b)) {
            a();
        } else if (TextUtils.isEmpty(this.c.c)) {
            a();
        } else {
            a(new File(this.c.c));
        }
    }

    /* access modifiers changed from: package-private */
    public void a(OnSendLogCallBackListener onSendLogCallBackListener) {
        this.d = onSendLogCallBackListener;
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (this.d != null) {
            this.d.a(10002);
        }
    }
}
