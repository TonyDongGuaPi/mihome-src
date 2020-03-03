package com.mi.global.bbs.utils;

import android.os.CountDownTimer;

public class CountDownHelper {
    private static long DEFAULT_COUNTDOWN_INTERVAL = 1000;
    private static long DEFAULT_COUNTDOWN_SECONDS = 3;
    /* access modifiers changed from: private */
    public long countDownInterval = DEFAULT_COUNTDOWN_INTERVAL;
    /* access modifiers changed from: private */
    public CountDownListener countDownListener;
    private long countDownSeconds = DEFAULT_COUNTDOWN_SECONDS;
    private InnerCountDownTimer countDownTimer;

    public interface CountDownListener {
        void onCountDown(long j, long j2);
    }

    public void start() {
        cancel();
        if (this.countDownListener != null) {
            this.countDownListener.onCountDown(this.countDownSeconds, 0);
        }
        this.countDownTimer = new InnerCountDownTimer(this.countDownSeconds * this.countDownInterval, this.countDownInterval);
        this.countDownTimer.start();
    }

    public void cancel() {
        if (this.countDownTimer != null) {
            this.countDownTimer.cancel();
            this.countDownTimer = null;
        }
    }

    public void setCountDownListener(CountDownListener countDownListener2) {
        this.countDownListener = countDownListener2;
    }

    public void setCountDownSeconds(long j) {
        this.countDownSeconds = j;
    }

    public void setCountDownInterval(long j) {
        this.countDownInterval = j;
    }

    private class InnerCountDownTimer extends CountDownTimer {
        private long mTotalMillis = 0;

        public InnerCountDownTimer(long j, long j2) {
            super(j, j2);
            this.mTotalMillis = j;
        }

        public void onTick(long j) {
            if (CountDownHelper.this.countDownListener != null) {
                CountDownHelper.this.countDownListener.onCountDown(j / CountDownHelper.this.countDownInterval, (this.mTotalMillis - j) / CountDownHelper.this.countDownInterval);
            }
        }

        public void onFinish() {
            if (CountDownHelper.this.countDownListener != null) {
                CountDownHelper.this.countDownListener.onCountDown(0, this.mTotalMillis);
            }
        }
    }
}
