package com.xiaomi.smarthome.notificationquickop;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.taobao.weex.el.parse.Operators;

public abstract class CustomIntentService extends Service {

    /* renamed from: a  reason: collision with root package name */
    private volatile Looper f20962a;
    private volatile ServiceHandler b;
    private String c;
    private boolean d;

    public IBinder onBind(Intent intent) {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void onHandleIntent(Intent intent);

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            CustomIntentService.this.onHandleIntent((Intent) message.obj);
        }
    }

    public CustomIntentService(String str) {
        this.c = str;
    }

    public void setIntentRedelivery(boolean z) {
        this.d = z;
    }

    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("IntentService[" + this.c + Operators.ARRAY_END_STR);
        handlerThread.start();
        this.f20962a = handlerThread.getLooper();
        this.b = new ServiceHandler(this.f20962a);
    }

    public void onStart(Intent intent, int i) {
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.arg1 = i;
        obtainMessage.obj = intent;
        this.b.sendMessage(obtainMessage);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        onStart(intent, i2);
        return this.d ? 3 : 2;
    }

    public void onDestroy() {
        this.f20962a.quit();
    }
}
