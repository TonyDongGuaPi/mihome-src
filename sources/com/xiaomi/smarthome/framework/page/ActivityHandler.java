package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

public class ActivityHandler extends Handler {

    /* renamed from: a  reason: collision with root package name */
    WeakReference<HandleMessage> f16689a;

    public interface HandleMessage {
        void handleMessage(Message message);
    }

    public ActivityHandler(HandleMessage handleMessage) {
        this.f16689a = new WeakReference<>(handleMessage);
    }

    public void handleMessage(Message message) {
        if (this.f16689a != null) {
            HandleMessage handleMessage = (HandleMessage) this.f16689a.get();
            if ((handleMessage instanceof Activity) && !((Activity) handleMessage).isFinishing()) {
                handleMessage.handleMessage(message);
            }
        }
    }
}
