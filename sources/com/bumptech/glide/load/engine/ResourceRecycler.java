package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class ResourceRecycler {

    /* renamed from: a  reason: collision with root package name */
    private boolean f4884a;
    private final Handler b = new Handler(Looper.getMainLooper(), new ResourceRecyclerCallback());

    ResourceRecycler() {
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(Resource<?> resource) {
        if (this.f4884a) {
            this.b.obtainMessage(1, resource).sendToTarget();
        } else {
            this.f4884a = true;
            resource.f();
            this.f4884a = false;
        }
    }

    private static final class ResourceRecyclerCallback implements Handler.Callback {

        /* renamed from: a  reason: collision with root package name */
        static final int f4885a = 1;

        ResourceRecyclerCallback() {
        }

        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((Resource) message.obj).f();
            return true;
        }
    }
}
