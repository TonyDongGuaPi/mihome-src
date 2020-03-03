package com.miui.tsmclientsdk.internal;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.miui.tsmclientsdk.MiTsmConstants;
import com.miui.tsmclientsdk.MiTsmFuture;
import com.miui.tsmclientsdk.OnProgressUpdateListener;
import com.miui.tsmclientsdk.OperationCanceledException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractServiceTask<T extends IInterface, R> extends FutureTask<Bundle> implements ServiceConnection, MiTsmFuture<Bundle> {
    private static final String TASK = "serviceTask:";
    private static final AtomicLong sTaskId = new AtomicLong();
    protected final String TAG;
    /* access modifiers changed from: private */
    public Context mContext;
    protected String mId;
    private boolean mIsBound;
    /* access modifiers changed from: private */
    public boolean mIsLaunchActivity;
    private CountDownLatch mLatch;
    /* access modifiers changed from: private */
    public OnProgressUpdateListener mProgressListener;
    protected R mResponse;
    protected T mService;
    private List<? super AbstractServiceTask> mTaskList;

    /* access modifiers changed from: protected */
    public abstract T asInterface(IBinder iBinder);

    /* access modifiers changed from: protected */
    public abstract void doWork() throws RemoteException;

    /* access modifiers changed from: protected */
    public abstract R getResponse();

    /* access modifiers changed from: protected */
    public abstract Intent getServiceIntent();

    protected AbstractServiceTask(Context context) {
        this(context, false);
    }

    protected AbstractServiceTask(Context context, boolean z) {
        this(context, z, (OnProgressUpdateListener) null);
    }

    protected AbstractServiceTask(Context context, boolean z, OnProgressUpdateListener onProgressUpdateListener) {
        this(context, (String) null, z, onProgressUpdateListener, (List<? super AbstractServiceTask>) null);
    }

    protected AbstractServiceTask(Context context, String str, boolean z, OnProgressUpdateListener onProgressUpdateListener, List<? super AbstractServiceTask> list) {
        super(new Callable<Bundle>() {
            public Bundle call() throws Exception {
                throw new IllegalStateException("this should never be called");
            }
        });
        String str2;
        this.TAG = getClass().getSimpleName();
        this.mIsBound = false;
        this.mIsLaunchActivity = false;
        this.mLatch = new CountDownLatch(1);
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        if (str == null) {
            str2 = "";
        } else {
            str2 = "-" + str;
        }
        sb.append(str2);
        sb.append("-");
        sb.append(sTaskId.getAndIncrement());
        sb.append("-");
        sb.append(System.currentTimeMillis());
        this.mId = sb.toString();
        this.mContext = context;
        this.mResponse = getResponse();
        this.mIsLaunchActivity = z;
        this.mProgressListener = onProgressUpdateListener;
        this.mTaskList = list;
    }

    public final MiTsmFuture<Bundle> start() {
        String str = this.TAG;
        Log.v(str, TASK + this.mId + " start");
        if (this.mTaskList != null) {
            this.mTaskList.add(this);
        }
        try {
            bind();
            this.mLatch.await();
            doWork();
        } catch (InterruptedException e) {
            String str2 = this.TAG;
            Log.e(str2, TASK + this.mId + " start is interrupted", e);
            Bundle bundle = new Bundle();
            bundle.putInt(MiTsmConstants.KEY_RESULT_CODE, 3);
            bundle.putString(MiTsmConstants.KEY_RESULT_MSG, "start serviceTask is interrupted");
            set(bundle);
        } catch (Exception e2) {
            Log.e(this.TAG, "start serviceTask error occurred", e2);
            setException(e2);
        }
        return this;
    }

    public boolean cancel(boolean z) {
        String str = this.TAG;
        Log.v(str, TASK + this.mId + " is canceling");
        T t = this.mService;
        if (t != null && !TextUtils.isEmpty(this.mId)) {
            try {
                Method method = t.getClass().getMethod("cancelTask", new Class[]{String.class});
                method.setAccessible(true);
                method.invoke(t, new Object[]{this.mId});
            } catch (NoSuchMethodException unused) {
                Log.d(this.TAG, "cancel task failed, task no cancel method.");
            } catch (Exception e) {
                Log.e(this.TAG, "cancel task failed", e);
            }
        }
        if (this.mTaskList != null) {
            this.mTaskList.remove(this);
        }
        return super.cancel(true);
    }

    public Bundle getResult() throws OperationCanceledException, IOException, ExecutionException {
        return internalGetResult((Long) null, (TimeUnit) null);
    }

    public Bundle getResult(long j, TimeUnit timeUnit) throws OperationCanceledException, IOException, ExecutionException {
        return internalGetResult(Long.valueOf(j), timeUnit);
    }

    /* access modifiers changed from: protected */
    public void set(Bundle bundle) {
        super.set(bundle);
        unbind();
    }

    /* access modifiers changed from: protected */
    public void setException(Throwable th) {
        super.setException(th);
        unbind();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        String str = this.TAG;
        Log.v(str, TASK + this.mId + " onServiceConnected, component:" + componentName);
        this.mService = asInterface(iBinder);
        this.mLatch.countDown();
    }

    public void onServiceDisconnected(ComponentName componentName) {
        String str = this.TAG;
        Log.v(str, TASK + this.mId + " onServiceDisconnected");
        if (!isDone()) {
            Log.v(this.TAG, "task is not completed");
        }
        this.mService = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004b, code lost:
        if (r2.mTaskList == null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0050, code lost:
        if (r2.mTaskList == null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0055, code lost:
        if (r2.mTaskList == null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0057, code lost:
        r2.mTaskList.remove(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0061, code lost:
        throw new com.miui.tsmclientsdk.OperationCanceledException();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.os.Bundle internalGetResult(java.lang.Long r3, java.util.concurrent.TimeUnit r4) throws com.miui.tsmclientsdk.OperationCanceledException, java.io.IOException, java.util.concurrent.ExecutionException {
        /*
            r2 = this;
            boolean r0 = r2.isDone()
            if (r0 != 0) goto L_0x0009
            r2.ensureNotOnMainThread()
        L_0x0009:
            if (r4 != 0) goto L_0x001f
            java.lang.Object r3 = r2.get()     // Catch:{ InterruptedException -> 0x0053, TimeoutException -> 0x004e, CancellationException -> 0x0049, ExecutionException -> 0x001d }
            android.os.Bundle r3 = (android.os.Bundle) r3     // Catch:{ InterruptedException -> 0x0053, TimeoutException -> 0x004e, CancellationException -> 0x0049, ExecutionException -> 0x001d }
            java.util.List<? super com.miui.tsmclientsdk.internal.AbstractServiceTask> r4 = r2.mTaskList
            if (r4 == 0) goto L_0x001a
            java.util.List<? super com.miui.tsmclientsdk.internal.AbstractServiceTask> r4 = r2.mTaskList
            r4.remove(r2)
        L_0x001a:
            return r3
        L_0x001b:
            r3 = move-exception
            goto L_0x003f
        L_0x001d:
            r3 = move-exception
            goto L_0x0033
        L_0x001f:
            long r0 = r3.longValue()     // Catch:{ InterruptedException -> 0x0053, TimeoutException -> 0x004e, CancellationException -> 0x0049, ExecutionException -> 0x001d }
            java.lang.Object r3 = r2.get(r0, r4)     // Catch:{ InterruptedException -> 0x0053, TimeoutException -> 0x004e, CancellationException -> 0x0049, ExecutionException -> 0x001d }
            android.os.Bundle r3 = (android.os.Bundle) r3     // Catch:{ InterruptedException -> 0x0053, TimeoutException -> 0x004e, CancellationException -> 0x0049, ExecutionException -> 0x001d }
            java.util.List<? super com.miui.tsmclientsdk.internal.AbstractServiceTask> r4 = r2.mTaskList
            if (r4 == 0) goto L_0x0032
            java.util.List<? super com.miui.tsmclientsdk.internal.AbstractServiceTask> r4 = r2.mTaskList
            r4.remove(r2)
        L_0x0032:
            return r3
        L_0x0033:
            java.lang.Throwable r4 = r3.getCause()     // Catch:{ all -> 0x001b }
            boolean r0 = r4 instanceof java.io.IOException     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x003e
            java.io.IOException r4 = (java.io.IOException) r4     // Catch:{ all -> 0x001b }
            throw r4     // Catch:{ all -> 0x001b }
        L_0x003e:
            throw r3     // Catch:{ all -> 0x001b }
        L_0x003f:
            java.util.List<? super com.miui.tsmclientsdk.internal.AbstractServiceTask> r4 = r2.mTaskList
            if (r4 == 0) goto L_0x0048
            java.util.List<? super com.miui.tsmclientsdk.internal.AbstractServiceTask> r4 = r2.mTaskList
            r4.remove(r2)
        L_0x0048:
            throw r3
        L_0x0049:
            java.util.List<? super com.miui.tsmclientsdk.internal.AbstractServiceTask> r3 = r2.mTaskList
            if (r3 == 0) goto L_0x005c
            goto L_0x0057
        L_0x004e:
            java.util.List<? super com.miui.tsmclientsdk.internal.AbstractServiceTask> r3 = r2.mTaskList
            if (r3 == 0) goto L_0x005c
            goto L_0x0057
        L_0x0053:
            java.util.List<? super com.miui.tsmclientsdk.internal.AbstractServiceTask> r3 = r2.mTaskList
            if (r3 == 0) goto L_0x005c
        L_0x0057:
            java.util.List<? super com.miui.tsmclientsdk.internal.AbstractServiceTask> r3 = r2.mTaskList
            r3.remove(r2)
        L_0x005c:
            com.miui.tsmclientsdk.OperationCanceledException r3 = new com.miui.tsmclientsdk.OperationCanceledException
            r3.<init>()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclientsdk.internal.AbstractServiceTask.internalGetResult(java.lang.Long, java.util.concurrent.TimeUnit):android.os.Bundle");
    }

    private void bind() {
        if (this.mIsBound) {
            return;
        }
        if (bindService()) {
            this.mIsBound = true;
            String str = this.TAG;
            Log.v(str, TASK + this.mId + " bind service success");
            return;
        }
        String str2 = this.TAG;
        Log.e(str2, TASK + this.mId + " bind service failed");
        throw new IllegalStateException("no service is bond");
    }

    private boolean bindService() {
        return this.mContext.bindService(getServiceIntent(), this, 1);
    }

    private void unbind() {
        if (this.mIsBound) {
            this.mIsBound = false;
            this.mContext.unbindService(this);
            String str = this.TAG;
            Log.v(str, TASK + this.mId + " unbind service");
        }
    }

    private void ensureNotOnMainThread() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null && myLooper == this.mContext.getMainLooper()) {
            IllegalStateException illegalStateException = new IllegalStateException("calling this from your main thread can lead to deadlock");
            Log.e(this.TAG, illegalStateException.getMessage(), illegalStateException);
            throw illegalStateException;
        }
    }

    protected class SimpleResponse {
        private final String mTaskId;

        public SimpleResponse(AbstractServiceTask abstractServiceTask) {
            this((String) null);
        }

        public SimpleResponse(String str) {
            this.mTaskId = str;
        }

        public void onResult(Bundle bundle) throws RemoteException {
            bundle.setClassLoader(AbstractServiceTask.class.getClassLoader());
            bundle.putInt(MiTsmConstants.KEY_RESULT_CODE, 0);
            Intent intent = (Intent) bundle.getParcelable("key_intent");
            if (intent == null || !AbstractServiceTask.this.mIsLaunchActivity) {
                AbstractServiceTask.this.set(bundle);
                return;
            }
            if (!(AbstractServiceTask.this.mContext instanceof Activity)) {
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
            }
            AbstractServiceTask.this.mContext.startActivity(intent);
        }

        public void onError(int i, String str) throws RemoteException {
            String str2 = AbstractServiceTask.this.TAG;
            Log.w(str2, "on Error: code=" + i + " msg=" + str);
            Bundle bundle = new Bundle();
            bundle.putInt(MiTsmConstants.KEY_RESULT_CODE, i);
            bundle.putString(MiTsmConstants.KEY_RESULT_MSG, str);
            AbstractServiceTask.this.set(bundle);
        }

        public void onProgress(int i) throws RemoteException {
            String str = AbstractServiceTask.this.TAG;
            Log.v(str, "on progress: " + i);
            if (AbstractServiceTask.this.mProgressListener != null) {
                AbstractServiceTask.this.mProgressListener.onProgress(i);
            }
        }

        public String getId() throws RemoteException {
            return this.mTaskId;
        }
    }
}
