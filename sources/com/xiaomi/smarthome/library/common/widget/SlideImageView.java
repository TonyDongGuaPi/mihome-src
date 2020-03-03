package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class SlideImageView extends ImageView {
    private static final int MSG_REFRESH = 100;
    private static final int MSG_REFRESH_ONCE = 101;
    private static final int SLIDE_SPEED = 5;
    private Paint mBlackPaint;
    private int mCurrentBlack = 0;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 100:
                    SlideImageView.this.invalidate();
                    SlideImageView.this.mHandler.sendEmptyMessageDelayed(100, 300);
                    return;
                case 101:
                    SlideImageView.this.invalidate();
                    return;
                default:
                    return;
            }
        }
    };
    private Paint mNormalPaint;
    private float[] mPointXs = new float[6];

    public SlideImageView(Context context) {
        super(context);
        init();
    }

    public SlideImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SlideImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.mNormalPaint = new Paint(5);
        this.mNormalPaint.setColor(-3421237);
        this.mBlackPaint = new Paint(5);
        this.mBlackPaint.setColor(-7368817);
        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                this.mPointXs[i] = 18.0f;
            } else {
                this.mPointXs[i] = this.mPointXs[i - 1] + 7.0f;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(DisplayUtils.a(68.0f), DisplayUtils.a(5.0f));
    }

    public void startConnection() {
        this.mHandler.sendEmptyMessage(100);
    }

    public void stopConnection() {
        this.mHandler.removeMessages(100);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 6; i++) {
            if (i == this.mCurrentBlack) {
                canvas.drawCircle((float) DisplayUtils.a(this.mPointXs[i]), (float) DisplayUtils.a(1.5f), (float) DisplayUtils.a(1.5f), this.mBlackPaint);
            } else {
                canvas.drawCircle((float) DisplayUtils.a(this.mPointXs[i]), (float) DisplayUtils.a(1.5f), (float) DisplayUtils.a(1.5f), this.mNormalPaint);
            }
        }
        int i2 = this.mCurrentBlack + 1;
        this.mCurrentBlack = i2;
        this.mCurrentBlack = i2 % 6;
    }
}
