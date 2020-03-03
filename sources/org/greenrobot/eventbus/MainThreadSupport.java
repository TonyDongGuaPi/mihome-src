package org.greenrobot.eventbus;

import android.os.Looper;

public interface MainThreadSupport {
    Poster a(EventBus eventBus);

    boolean a();

    public static class AndroidHandlerMainThreadSupport implements MainThreadSupport {

        /* renamed from: a  reason: collision with root package name */
        private final Looper f3489a;

        public AndroidHandlerMainThreadSupport(Looper looper) {
            this.f3489a = looper;
        }

        public boolean a() {
            return this.f3489a == Looper.myLooper();
        }

        public Poster a(EventBus eventBus) {
            return new HandlerPoster(eventBus, this.f3489a, 10);
        }
    }
}
