package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.TextView;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class MaskableTextView extends TextView {
    private static final float DEFAULT_MASK_MOVE_SPEED = (((float) DisplayUtils.a(1.0f)) / 20.0f);
    private static final int MSG_REDRAW = 1;
    private static final int MSG_TOP = 2;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    MaskableTextView.this.invalidate();
                    sendEmptyMessageDelayed(1, 10);
                    return;
                case 2:
                    MaskableTextView.this.mHandler.removeMessages(1);
                    return;
                default:
                    return;
            }
        }
    };
    private Animation.AnimationListener mListener = null;
    private float mMaskMoveSpeed = DEFAULT_MASK_MOVE_SPEED;
    private Rect mRect = new Rect();
    private boolean mRunning = false;
    private long mStartTime = 0;

    public MaskableTextView(Context context) {
        super(context);
    }

    public MaskableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MaskableTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mRunning) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.mStartTime == 0) {
                this.mStartTime = currentTimeMillis;
                if (this.mListener != null) {
                    this.mListener.onAnimationStart((Animation) null);
                }
            }
            float f = ((float) (currentTimeMillis - this.mStartTime)) * this.mMaskMoveSpeed;
            this.mRect.set(0, 0, (int) f, getHeight());
            if (f > ((float) getMeasuredWidth())) {
                this.mHandler.removeMessages(1);
                this.mRect.set(0, 0, getWidth(), getHeight());
                if (this.mListener != null) {
                    this.mListener.onAnimationEnd((Animation) null);
                }
                this.mRunning = false;
            }
        }
        canvas.clipRect(this.mRect);
        super.onDraw(canvas);
    }

    public void reset() {
        this.mRunning = false;
        this.mHandler.removeMessages(1);
        this.mRect.set(0, 0, 0, getHeight());
        this.mStartTime = 0;
        postInvalidate();
    }

    public void startAnim(Animation.AnimationListener animationListener) {
        this.mHandler.sendEmptyMessage(1);
        this.mRunning = true;
        this.mListener = animationListener;
    }

    public void setMaskSpeed(float f) {
        this.mMaskMoveSpeed = f;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mHandler.removeCallbacksAndMessages((Object) null);
    }
}
