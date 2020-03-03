package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class WaveView extends View {
    static final int MSG_REAL_STOP = 4;
    static final int MSG_REFRESH = 2;
    static final int MSG_START = 1;
    static final int MSG_STOP = 3;
    long CIRCLE_INTERVAL = 300;
    int COLOR = 0;
    int END_R = 0;
    long REFRESH_TIME = 16;
    long SPEAD_TIME = 800;
    int START_R = 0;
    long curTime = 0;
    Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    WaveView.this.mHandler.sendEmptyMessage(2);
                    return;
                case 2:
                    WaveView.this.invalidate();
                    WaveView.this.mHandler.sendEmptyMessageDelayed(2, WaveView.this.REFRESH_TIME);
                    return;
                case 3:
                    WaveView.this.mStop = true;
                    WaveView.this.stopTime = System.currentTimeMillis();
                    WaveView.this.mHandler.sendEmptyMessageDelayed(4, WaveView.this.SPEAD_TIME - ((WaveView.this.stopTime - WaveView.this.startTime) % WaveView.this.CIRCLE_INTERVAL));
                    WaveView.this.invalidate();
                    return;
                case 4:
                    WaveView.this.mRunning = false;
                    WaveView.this.mHandler.removeMessages(2);
                    WaveView.this.invalidate();
                    return;
                default:
                    return;
            }
        }
    };
    int mHeight = 0;
    Paint mPaint = new Paint();
    boolean mRunning = false;
    boolean mStop = true;
    int mWidth = 0;
    long startTime = 0;
    long stopTime = 0;

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public WaveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        if (this.mRunning) {
            this.curTime = System.currentTimeMillis();
            long j = this.curTime - this.startTime;
            if (!this.mStop) {
                double d = (double) j;
                double d2 = (double) this.CIRCLE_INTERVAL;
                Double.isNaN(d);
                Double.isNaN(d2);
                i = ((int) Math.floor(d / d2)) + 1;
            } else {
                double d3 = (double) (this.stopTime - this.startTime);
                double d4 = (double) this.CIRCLE_INTERVAL;
                Double.isNaN(d3);
                Double.isNaN(d4);
                i = ((int) Math.floor(d3 / d4)) + 1;
            }
            double d5 = (double) (this.END_R - this.START_R);
            double d6 = (double) this.SPEAD_TIME;
            Double.isNaN(d5);
            Double.isNaN(d6);
            double d7 = d5 / d6;
            while (i > 0) {
                double d8 = (double) (j - (this.CIRCLE_INTERVAL * ((long) (i - 1))));
                Double.isNaN(d8);
                int i2 = (int) (d8 * d7);
                if (i2 < this.END_R - this.START_R) {
                    int i3 = ((((int) ((1.0f - (((float) i2) / ((float) (this.END_R - this.START_R)))) * 255.0f)) & 255) << 24) | this.COLOR;
                    int i4 = i2 + this.START_R;
                    this.mPaint.setColor(i3);
                    canvas.drawCircle(((float) this.mWidth) / 2.0f, ((float) this.mHeight) / 2.0f, (float) i4, this.mPaint);
                    i--;
                } else {
                    return;
                }
            }
        }
    }

    public void start(int i, int i2, int i3) {
        start(i, i2, i3, 0);
    }

    public void start(int i, int i2, int i3, int i4) {
        if (!this.mRunning) {
            this.START_R = i;
            this.END_R = i2;
            this.COLOR = i3;
            this.mWidth = getMeasuredWidth();
            this.mHeight = getMeasuredHeight();
            this.startTime = System.currentTimeMillis();
            this.stopTime = 0;
            this.mRunning = true;
            this.mStop = false;
            this.mHandler.sendEmptyMessage(1);
            if (i4 > 0) {
                this.mHandler.sendEmptyMessageDelayed(3, (long) i4);
            }
        }
    }

    public void stop() {
        if (this.mRunning) {
            this.mHandler.sendEmptyMessage(3);
        }
    }
}
