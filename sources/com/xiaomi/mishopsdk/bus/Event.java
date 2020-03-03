package com.xiaomi.mishopsdk.bus;

import android.os.Bundle;

public abstract class Event implements Runnable {
    static final int EVENT_TYPE_CUR_THREAD = 1;
    static final int EVENT_TYPE_MAIN_THREAD = 2;
    static final int EVENT_TYPE_UNINIT = 0;
    boolean mCanceled;
    final int mEventId;
    int mEventType = 0;
    final int mHostHashCode;
    long mParam1;
    Bundle mParam2;

    public abstract void exec(long j, Bundle bundle);

    public Event(Object obj, int i) {
        this.mHostHashCode = obj.hashCode();
        this.mEventId = i;
    }

    public final void run() {
        if (!this.mCanceled) {
            exec(this.mParam1, this.mParam2);
        }
        this.mParam2 = null;
    }

    public String toString() {
        return "mHostHashCode=" + this.mHostHashCode + ", mEventId=" + this.mEventId + ", mEventType=" + this.mEventType + ", mCanceled=" + this.mCanceled + ", mParam1=" + this.mParam1 + ", mParam2=" + this.mParam2;
    }
}
