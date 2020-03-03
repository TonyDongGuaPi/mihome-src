package com.xiaomi.mipush.sdk;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.xiaomi.channel.commonutils.logger.b;
import java.lang.ref.WeakReference;

public abstract class BaseService extends Service {

    /* renamed from: a  reason: collision with root package name */
    private a f11501a;

    public static class a extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<BaseService> f11502a;

        public a(WeakReference<BaseService> weakReference) {
            this.f11502a = weakReference;
        }

        public void a() {
            if (hasMessages(1001)) {
                removeMessages(1001);
            }
            sendEmptyMessageDelayed(1001, 1000);
        }

        public void handleMessage(Message message) {
            BaseService baseService;
            if (message.what == 1001 && this.f11502a != null && (baseService = (BaseService) this.f11502a.get()) != null) {
                b.c("TimeoutHandler" + baseService.toString() + "  kill self");
                if (!baseService.a()) {
                    baseService.stopSelf();
                    return;
                }
                b.c("TimeoutHandler has job");
                sendEmptyMessageDelayed(1001, 1000);
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean a();

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        if (this.f11501a == null) {
            this.f11501a = new a(new WeakReference(this));
        }
        this.f11501a.a();
    }
}
