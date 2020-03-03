package com.mi.global.bbs.utils;

import android.os.SystemClock;
import android.view.View;

public class StatusBarClickListener implements View.OnClickListener {
    private static final long doubleInterval = 500;
    private OnDoubleClickListener mOnDoubleClickListener;
    private long prevTime = 0;

    public interface OnDoubleClickListener {
        void onDoubleClick();
    }

    public StatusBarClickListener(OnDoubleClickListener onDoubleClickListener) {
        this.mOnDoubleClickListener = onDoubleClickListener;
    }

    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - this.prevTime > doubleInterval) {
            this.prevTime = SystemClock.elapsedRealtime();
        } else if (this.mOnDoubleClickListener != null) {
            this.mOnDoubleClickListener.onDoubleClick();
        }
    }
}
