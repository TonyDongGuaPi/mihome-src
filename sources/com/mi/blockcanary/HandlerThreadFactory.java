package com.mi.blockcanary;

import android.os.Handler;
import android.os.HandlerThread;
import com.alipay.mobile.common.logging.EventCategory;

final class HandlerThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    private static HandlerThreadWrapper f6745a = new HandlerThreadWrapper("loop");
    private static HandlerThreadWrapper b = new HandlerThreadWrapper("writer");
    private static HandlerThreadWrapper c = new HandlerThreadWrapper(EventCategory.CATEGORY_UPLOAD);

    private HandlerThreadFactory() {
        throw new InstantiationError("Must not instantiate this class");
    }

    public static Handler a() {
        return f6745a.a();
    }

    public static Handler b() {
        return b.a();
    }

    public static Handler c() {
        return c.a();
    }

    private static class HandlerThreadWrapper {

        /* renamed from: a  reason: collision with root package name */
        private Handler f6746a = null;

        public HandlerThreadWrapper(String str) {
            HandlerThread handlerThread = new HandlerThread("BlockCanary-" + str);
            handlerThread.start();
            this.f6746a = new Handler(handlerThread.getLooper());
        }

        public Handler a() {
            return this.f6746a;
        }
    }
}
