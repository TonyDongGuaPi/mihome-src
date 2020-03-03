package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class SingleWaveView extends View {
    static final int MSG_REAL_STOP = 4;
    static final int MSG_REFRESH = 2;
    static final int MSG_START = 1;
    static final int MSG_STOP = 3;
    long CIRCLE_INTERVAL = 300;
    int COLOR = 0;
    int END_R = 0;
    long REFRESH_TIME = 16;
    long SPEAD_TIME = 1200;
    int START_R = 0;
    long curTime = 0;
    Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    SingleWaveView.this.mHandler.sendEmptyMessage(2);
                    return;
                case 2:
                    SingleWaveView.this.invalidate();
                    SingleWaveView.this.mHandler.sendEmptyMessageDelayed(2, SingleWaveView.this.REFRESH_TIME);
                    return;
                case 3:
                    SingleWaveView.this.mStop = true;
                    SingleWaveView.this.stopTime = System.currentTimeMillis();
                    SingleWaveView.this.mHandler.sendEmptyMessageDelayed(4, SingleWaveView.this.SPEAD_TIME - ((SingleWaveView.this.stopTime - SingleWaveView.this.startTime) % SingleWaveView.this.CIRCLE_INTERVAL));
                    SingleWaveView.this.invalidate();
                    return;
                case 4:
                    SingleWaveView.this.mRunning = false;
                    SingleWaveView.this.mHandler.removeMessages(2);
                    SingleWaveView.this.invalidate();
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

    public SingleWaveView(Context context) {
        super(context);
        init();
    }

    public SingleWaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SingleWaveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth((float) DisplayUtils.a(1.0f));
        this.mPaint.setStyle(Paint.Style.STROKE);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mRunning) {
            if (this.mWidth == 0) {
                this.mWidth = getMeasuredWidth();
            }
            if (this.mHeight == 0) {
                this.mHeight = getMeasuredHeight();
            }
            this.curTime = System.currentTimeMillis();
            float sqrt = (float) Math.sqrt((double) (((float) (this.curTime - this.startTime)) / ((float) this.SPEAD_TIME)));
            if (sqrt >= 1.0f) {
                this.startTime = System.currentTimeMillis();
                return;
            }
            float f = 1.0f - sqrt;
            this.mPaint.setColor(((((int) (255.0f * f)) & 255) << 24) | this.COLOR);
            this.mPaint.setAlpha((int) (f * 100.0f));
            canvas.drawCircle(((float) this.mWidth) / 2.0f, ((float) this.mHeight) / 2.0f, (float) ((int) ((((float) (this.END_R - this.START_R)) * sqrt) + ((float) this.START_R))), this.mPaint);
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

    public static class DeletableTextView extends TextView implements Checkable {
        private static final int[] CHECK_STATE = {16842912};
        private boolean isChecked;
        private boolean isDeletable = true;
        private Rect mBounds;
        private Context mContext;
        private Drawable mDrawableRight;
        private OnTextClearListener mOnTextClearListener;

        public interface OnTextClearListener {
            void a(DeletableTextView deletableTextView);
        }

        public DeletableTextView(Context context) {
            super(context);
            initialize(context);
        }

        public DeletableTextView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            initialize(context);
        }

        private void initialize(Context context) {
            this.mContext = context;
            this.mDrawableRight = this.mContext.getResources().getDrawable(R.drawable.mijia_icon_delete_selector);
            this.mDrawableRight.setBounds(0, 0, this.mDrawableRight.getMinimumWidth(), this.mDrawableRight.getMinimumWidth());
            setCompoundDrawables((Drawable) null, (Drawable) null, this.mDrawableRight, (Drawable) null);
            setClickable(true);
            setMinWidth(DisplayUtils.a(70.0f));
            setMinHeight(DisplayUtils.a(30.0f));
            setGravity(17);
            setPadding(8, 8, 8, 8);
            setCompoundDrawablePadding(8);
            setBackgroundResource(R.drawable.mijia_button_bg_sec);
            setTextColor(getResources().getColor(R.color.white));
            setTextSize(13.0f);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(DisplayUtils.d(context, 4.0f), 0, 0, DisplayUtils.d(context, 11.0f));
            setLayoutParams(layoutParams);
        }

        public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
            if (drawable3 != null) {
                this.mDrawableRight = drawable3;
            }
            super.setCompoundDrawables(drawable, drawable2, this.mDrawableRight, drawable4);
        }

        public void setDeletable(boolean z) {
            this.isDeletable = z;
            if (z) {
                setBackgroundResource(R.drawable.mijia_button_bg_add);
                setCompoundDrawables((Drawable) null, (Drawable) null, this.mDrawableRight, (Drawable) null);
                return;
            }
            setBackgroundResource(R.drawable.mijia_button_bg_sec);
            super.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                this.mBounds = this.mDrawableRight.getBounds();
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (x < (getWidth() - this.mBounds.width()) - getPaddingLeft() || x > getWidth() - getPaddingRight() || y < getPaddingTop() || y > getHeight() - getPaddingBottom()) {
                    return false;
                }
            }
            if (motionEvent.getAction() == 1 && this.mDrawableRight != null) {
                this.mBounds = this.mDrawableRight.getBounds();
                int x2 = (int) motionEvent.getX();
                int y2 = (int) motionEvent.getY();
                if (x2 >= (getWidth() - this.mBounds.width()) - getPaddingLeft() && x2 <= getWidth() - getPaddingRight() && y2 >= getPaddingTop() && y2 <= getHeight() - getPaddingBottom()) {
                    clear();
                    motionEvent.setAction(3);
                }
            }
            return super.onTouchEvent(motionEvent);
        }

        private void clear() {
            if (this.mOnTextClearListener != null && this.isDeletable) {
                this.mOnTextClearListener.a(this);
            }
            super.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        }

        public void setOnTextClearListener(OnTextClearListener onTextClearListener) {
            this.mOnTextClearListener = onTextClearListener;
        }

        /* access modifiers changed from: protected */
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.mContext = null;
        }

        /* access modifiers changed from: protected */
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.mContext = getContext();
        }

        public void finalize() throws Throwable {
            this.mDrawableRight = null;
            this.mBounds = null;
            super.finalize();
        }

        public void setChecked(boolean z) {
            if (this.isChecked != z) {
                this.isChecked = z;
                refreshDrawableState();
            }
        }

        public boolean isChecked() {
            return this.isChecked;
        }

        public void toggle() {
            setChecked(!this.isChecked);
        }
    }
}
