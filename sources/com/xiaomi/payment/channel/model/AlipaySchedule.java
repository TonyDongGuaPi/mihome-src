package com.xiaomi.payment.channel.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.payment.alipay.ResultChecker;

public class AlipaySchedule extends Handler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12181a = "AlipaySchedule";
    private static final AlipaySchedule b = new AlipaySchedule();
    private static volatile AlipayResultEvent c;
    private static volatile DoAlipayListenerEvent d;
    private static volatile boolean e = false;

    private AlipaySchedule() {
        super(Looper.getMainLooper());
    }

    private static class AlipayResultEvent {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f12182a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public long c;
        /* access modifiers changed from: private */
        public long d;
        /* access modifiers changed from: private */
        public String e;

        private AlipayResultEvent(String str, String str2, long j, long j2, String str3) {
            this.f12182a = str;
            this.b = str2;
            this.c = j;
            this.d = j2;
            this.e = str3;
        }
    }

    private static class DoAlipayListenerEvent {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f12183a;
        /* access modifiers changed from: private */
        public IPaytoolTaskListener b;

        private DoAlipayListenerEvent(String str, IPaytoolTaskListener iPaytoolTaskListener) {
            this.f12183a = str;
            this.b = iPaytoolTaskListener;
        }
    }

    static void a(String str, String str2, long j, long j2, String str3) {
        Message message = new Message();
        message.obj = new AlipayResultEvent(str, str2, j, j2, str3);
        b.sendMessage(message);
        e = true;
    }

    static void a(String str, IPaytoolTaskListener iPaytoolTaskListener) {
        Message message = new Message();
        message.obj = new DoAlipayListenerEvent(str, iPaytoolTaskListener);
        b.sendMessage(message);
        e = true;
    }

    public void handleMessage(Message message) {
        if (e) {
            Object obj = message.obj;
            if (obj instanceof AlipayResultEvent) {
                Log.d(f12181a, "aquire AlipayResultEvent");
                c = (AlipayResultEvent) obj;
            } else if (obj instanceof DoAlipayListenerEvent) {
                Log.d(f12181a, "aquire DoAlipayListenerEvent");
                d = (DoAlipayListenerEvent) obj;
            } else {
                throw new IllegalStateException();
            }
            synchronized (this) {
                if (!(c == null || d == null || !TextUtils.equals(c.f12182a, d.f12183a))) {
                    b();
                    a();
                }
            }
        }
    }

    private void b() {
        Log.d(f12181a, "executeCallback");
        try {
            ResultChecker resultChecker = new ResultChecker(c.b);
            String a2 = resultChecker.a();
            String b2 = resultChecker.b();
            if (TextUtils.equals(a2, "{9000}") && TextUtils.equals(b2, "true")) {
                c();
            } else if (TextUtils.equals(a2, "{6001}")) {
                d();
            } else {
                e();
            }
        } catch (Exception unused) {
            e();
        }
    }

    private void c() {
        Log.d(f12181a, "alipay success");
        d.b.a(c.c, c.d);
    }

    private void d() {
        Log.d(f12181a, "alipay canceled");
        d.b.b();
    }

    private void e() {
        Log.d(f12181a, "alipay failed");
        d.b.a(11, c.e, (Throwable) null);
    }

    public static void a() {
        Log.d(f12181a, "release");
        e = false;
        c = null;
        d = null;
    }
}
