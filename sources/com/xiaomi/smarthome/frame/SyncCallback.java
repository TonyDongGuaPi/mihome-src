package com.xiaomi.smarthome.frame;

import com.xiaomi.smarthome.frame.Error;

public abstract class SyncCallback<R, E extends Error> extends AsyncCallback<R, E> {
    private E mError;
    private boolean mIsSuccess = false;
    private R mResult;
    private Object mSyncLock = null;

    public boolean isSuccess() {
        return this.mIsSuccess;
    }

    public R getResult() {
        return this.mResult;
    }

    public E getError() {
        return this.mError;
    }

    public Object getSyncLock() {
        return this.mSyncLock;
    }

    public void setUseSyncLockMode() {
        this.mSyncLock = new Object();
    }

    public boolean isSyncLockMode() {
        return this.mSyncLock != null;
    }

    public void sendSuccessMessage(R r) {
        if (isSyncLockMode()) {
            this.mIsSuccess = true;
            this.mResult = r;
            synchronized (this.mSyncLock) {
                this.mSyncLock.notify();
            }
            return;
        }
        onSuccess(r);
    }

    public void sendFailureMessage(E e) {
        if (isSyncLockMode()) {
            this.mIsSuccess = false;
            this.mError = e;
            synchronized (this.mSyncLock) {
                this.mSyncLock.notify();
            }
            return;
        }
        onFailure(e);
    }
}
