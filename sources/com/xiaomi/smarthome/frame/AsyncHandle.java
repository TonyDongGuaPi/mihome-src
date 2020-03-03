package com.xiaomi.smarthome.frame;

import android.os.RemoteException;
import com.xiaomi.smarthome.core.server.IServerHandle;
import java.lang.ref.WeakReference;
import okhttp3.Call;

public class AsyncHandle<H> {
    protected WeakReference<H> mHandle;

    public interface Handle {
        void cancel();
    }

    public AsyncHandle(H h) {
        this.mHandle = new WeakReference<>(h);
    }

    public void cancel() {
        Object obj;
        if (this.mHandle != null && (obj = this.mHandle.get()) != null) {
            if (obj instanceof Handle) {
                ((Handle) obj).cancel();
            } else if (obj instanceof IServerHandle) {
                try {
                    ((IServerHandle) obj).cancel();
                } catch (RemoteException unused) {
                }
            } else if (obj instanceof Call) {
                ((Call) obj).cancel();
            }
        }
    }

    public static class AsyncHandleWrap extends AsyncHandle {

        /* renamed from: a  reason: collision with root package name */
        private boolean f15989a;

        public AsyncHandleWrap() {
            super(null);
        }

        public void a(AsyncHandle asyncHandle) {
            if (asyncHandle != null) {
                this.mHandle = asyncHandle.mHandle;
            }
        }

        public boolean a() {
            return this.f15989a;
        }

        public void cancel() {
            this.f15989a = true;
            AsyncHandle.super.cancel();
        }
    }
}
