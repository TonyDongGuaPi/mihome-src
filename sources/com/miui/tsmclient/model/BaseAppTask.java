package com.miui.tsmclient.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.miui.tsmclient.util.LogUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BaseAppTask implements ServiceConnection {
    private static final String TAG = "BaseAppTask";
    protected AtomicBoolean mConnected;
    protected Context mContext;
    protected final CountDownLatch mLatch = new CountDownLatch(1);
    protected boolean mNeedUnbind;

    /* access modifiers changed from: protected */
    public abstract void bindAppService();

    /* access modifiers changed from: protected */
    public abstract BaseResponse doInBackground();

    public abstract Object getService();

    public BaseAppTask(Context context) {
        this.mContext = context.getApplicationContext();
        this.mConnected = new AtomicBoolean(false);
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        doServiceConnected(iBinder);
    }

    /* access modifiers changed from: protected */
    public void doServiceConnected(IBinder iBinder) {
        LogUtils.d("BaseAppTask: onServiceConnected()");
        this.mConnected.set(true);
        this.mLatch.countDown();
    }

    public void onServiceDisconnected(ComponentName componentName) {
        LogUtils.d("BaseAppTask: onServiceDisconnected()");
        unBindAppService();
    }

    public BaseResponse execute() {
        onPreExecute();
        try {
            this.mLatch.await();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
        synchronized (this) {
            if (this.mNeedUnbind) {
                unBindAppService();
                BaseResponse baseResponse = new BaseResponse(6, new Object[0]);
                return baseResponse;
            }
            BaseResponse doInBackground = doInBackground();
            LogUtils.i("BaseAppTask: Execute finished, the result code is:" + doInBackground.mResultCode);
            onPostExecute();
            return doInBackground;
        }
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        bindAppService();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute() {
        unBindAppService();
    }

    public void terminate() {
        unBindAppService();
    }

    /* access modifiers changed from: protected */
    public synchronized void unBindAppService() {
        if (this.mConnected.get()) {
            LogUtils.d("BaseAppTask: unbind service");
            doUnBindAppService();
            this.mConnected.set(false);
        }
        this.mNeedUnbind = true;
    }

    /* access modifiers changed from: protected */
    public void doUnBindAppService() {
        this.mContext.unbindService(this);
    }
}
