package com.xiaomi.infrared.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint({"AppCompatCustomView"})
public class LongPressButton extends ImageView {
    public static final int DELAY_MILLIS_INTERVAL = 800;
    public static final int DELAY_MILLIS_LONG = 200;
    private boolean isRunning;
    private ILongPressChangeListener mLongPressChangeListener;
    private Timer mTimer;
    Runnable runnable = new Runnable() {
        public void run() {
            LongPressButton.this.startTimerInterval();
        }
    };

    public interface ILongPressChangeListener {
        void a();

        void b();

        void c();
    }

    public LongPressButton(Context context) {
        super(context);
    }

    public LongPressButton(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LongPressButton(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 3) {
            switch (action) {
                case 0:
                    startLongPress();
                    break;
                case 1:
                    break;
            }
        }
        stopLongPress();
        return super.onTouchEvent(motionEvent);
    }

    private void startLongPress() {
        notifyStartPress();
        startTimerInterval();
        postDelayed(this.runnable, 200);
    }

    private void stopLongPress() {
        notifyFinish();
        stopIntervalTimer();
        removeCallbacks(this.runnable);
    }

    public synchronized void stopIntervalTimer() {
        if (this.isRunning) {
            this.isRunning = false;
            if (this.mTimer != null) {
                try {
                    this.mTimer.cancel();
                } catch (Exception unused) {
                }
            }
        }
    }

    public void setLongPressChangeListener(ILongPressChangeListener iLongPressChangeListener) {
        this.mLongPressChangeListener = iLongPressChangeListener;
    }

    private void notifyFinish() {
        if (this.mLongPressChangeListener != null) {
            this.mLongPressChangeListener.c();
        }
    }

    /* access modifiers changed from: private */
    public void notifyChangeInterVal() {
        if (this.mLongPressChangeListener != null) {
            this.mLongPressChangeListener.b();
        }
    }

    private void notifyStartPress() {
        if (this.mLongPressChangeListener != null) {
            this.mLongPressChangeListener.a();
        }
    }

    /* access modifiers changed from: private */
    public void startTimerInterval() {
        if (!this.isRunning) {
            this.isRunning = true;
            this.mTimer = new Timer();
            this.mTimer.schedule(new TimerTask() {
                public void run() {
                    LongPressButton.this.notifyChangeInterVal();
                }
            }, 0, 800);
        }
    }
}
