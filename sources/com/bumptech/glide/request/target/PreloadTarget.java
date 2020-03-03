package com.bumptech.glide.request.target;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.transition.Transition;

public final class PreloadTarget<Z> extends SimpleTarget<Z> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f5073a = 1;
    private static final Handler b = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((PreloadTarget) message.obj).b();
            return true;
        }
    });
    private final RequestManager d;

    public static <Z> PreloadTarget<Z> a(RequestManager requestManager, int i, int i2) {
        return new PreloadTarget<>(requestManager, i, i2);
    }

    private PreloadTarget(RequestManager requestManager, int i, int i2) {
        super(i, i2);
        this.d = requestManager;
    }

    public void a(@NonNull Z z, @Nullable Transition<? super Z> transition) {
        b.obtainMessage(1, this).sendToTarget();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.d.a((Target<?>) this);
    }
}
