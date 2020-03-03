package com.xiaomi.smarthome.camera.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.libra.Color;
import com.mijia.model.sdcard.TimeItemDay;
import com.mijia.model.sdcard.TimeItemHour;
import com.xiaomi.smarthome.R;

public class HourOfDayView extends View {
    static final int MSG_LONG_PRESS = 1;
    public static final String TAG = "HourOfDayView";
    private int mDay = 0;
    Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                HourOfDayView.this.mHandler.removeMessages(1);
                HourOfDayView.this.onLongClick();
            }
        }
    };
    boolean mIsMultiSelect = false;
    int mLineColor = Color.d;
    HourOfDayViewListener mOnClickListener;
    Paint mPaint;
    int mRedPointMargin;
    int mRedPointSize;
    int mSaveBgColor = -4891;
    boolean[] mSelecled = new boolean[24];
    Drawable mSelectedDrawable;
    int mTextColor = -8355712;
    int mTextSaveColor = -38610;
    int mTextSize;
    int mTextVideoColor = -14840321;
    TimeItemDay mTimeItemDay;
    TimeItemHour[] mTimeItemHours = new TimeItemHour[24];
    int mVideoBgColor = -1707265;

    public interface HourOfDayViewListener {
        void onClick(int i, int i2);

        void onLongClick();

        void onSelectChanged();
    }

    public void setHourOfDayViewListener(HourOfDayViewListener hourOfDayViewListener) {
        this.mOnClickListener = hourOfDayViewListener;
    }

    public void setMultiSelect(boolean z) {
        this.mIsMultiSelect = z;
        postInvalidate();
    }

    public void setTimeItemDay(TimeItemDay timeItemDay, int i) {
        this.mDay = i;
        this.mTimeItemDay = timeItemDay;
        this.mTimeItemHours = new TimeItemHour[24];
        this.mSelecled = this.mTimeItemDay.f;
        for (int i2 = 0; i2 < this.mTimeItemDay.e.size(); i2++) {
            TimeItemHour timeItemHour = this.mTimeItemDay.e.get(i2);
            if (timeItemHour.f8100a < 0 || timeItemHour.f8100a >= 24) {
                Log.e(TAG, "setTimeItemDay error hour:" + timeItemHour.f8100a);
            } else {
                this.mTimeItemHours[timeItemHour.f8100a] = timeItemHour;
            }
        }
        postInvalidate();
    }

    public HourOfDayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRedPointSize = context.getResources().getDimensionPixelSize(R.dimen.hour_of_day_hour_red_point_size);
        this.mTextSize = context.getResources().getDimensionPixelSize(R.dimen.hour_of_day_hour_txt_size);
        this.mRedPointMargin = context.getResources().getDimensionPixelOffset(R.dimen.hour_of_day_hour_red_point_margin);
        this.mSelectedDrawable = context.getResources().getDrawable(R.drawable.camera_day_choose_pres);
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int i = width / 6;
        int i2 = height / 4;
        Rect rect = new Rect();
        for (int i3 = 0; i3 < 4; i3++) {
            for (int i4 = 0; i4 < 6; i4++) {
                rect.left = (i4 * i) + paddingLeft;
                rect.top = (i3 * i2) + paddingTop;
                rect.right = rect.left + i;
                rect.bottom = rect.top + i2;
                drawItem((i3 * 6) + i4, rect, canvas);
            }
            Canvas canvas2 = canvas;
        }
        Canvas canvas3 = canvas;
        this.mPaint.setColor(this.mLineColor);
        this.mPaint.setStrokeWidth(1.0f);
        int i5 = 1;
        while (i5 < 4) {
            float f = (float) ((i5 * i2) + paddingTop);
            Canvas canvas4 = canvas;
            canvas4.drawLine((float) paddingLeft, f, (float) (paddingLeft + width), f, this.mPaint);
            i5++;
            Canvas canvas5 = canvas;
        }
        float f2 = (float) (paddingTop + 2);
        int i6 = width + paddingLeft;
        Canvas canvas6 = canvas;
        float f3 = (float) paddingLeft;
        float f4 = (float) i6;
        canvas6.drawLine(f3, f2, f4, f2, this.mPaint);
        int i7 = height + paddingTop;
        float f5 = (float) (i7 - 2);
        canvas6.drawLine(f3, f5, f4, f5, this.mPaint);
        for (int i8 = 1; i8 < 6; i8++) {
            float f6 = (float) ((i8 * i) + paddingLeft);
            canvas.drawLine(f6, (float) paddingTop, f6, (float) i7, this.mPaint);
        }
        float f7 = (float) (paddingLeft + 2);
        Canvas canvas7 = canvas;
        float f8 = (float) paddingTop;
        float f9 = (float) i7;
        canvas7.drawLine(f7, f8, f7, f9, this.mPaint);
        float f10 = (float) (i6 - 2);
        canvas7.drawLine(f10, f8, f10, f9, this.mPaint);
    }

    /* access modifiers changed from: package-private */
    public void drawItem(int i, Rect rect, Canvas canvas) {
        String format = String.format("%1$02d:00", new Object[]{Integer.valueOf(i)});
        this.mPaint.setTextSize((float) this.mTextSize);
        if (!hasVideo(i)) {
            this.mPaint.setColor(this.mTextColor);
        } else if (!hasSave(i)) {
            this.mPaint.setColor(this.mSaveBgColor);
            this.mPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(rect, this.mPaint);
            this.mPaint.setColor(this.mTextSaveColor);
        } else {
            this.mPaint.setColor(this.mVideoBgColor);
            this.mPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(rect, this.mPaint);
            this.mPaint.setColor(this.mTextVideoColor);
        }
        Rect rect2 = new Rect();
        this.mPaint.getTextBounds(format, 0, format.length(), rect2);
        canvas.drawText(format, (float) (rect.left + ((rect.width() - rect2.width()) / 2)), (float) (rect.top + (((int) ((((float) rect.height()) - this.mPaint.descent()) - this.mPaint.ascent())) / 2)), this.mPaint);
        if (hasVideo(i)) {
            int i2 = (rect.right - this.mRedPointMargin) - (this.mRedPointSize / 2);
            int i3 = rect.top + this.mRedPointMargin + (this.mRedPointSize / 2);
            if (this.mIsMultiSelect && this.mSelecled[i]) {
                this.mSelectedDrawable.setBounds(i2 - this.mRedPointSize, i3 - this.mRedPointSize, i2 + this.mRedPointSize, i3 + this.mRedPointSize);
                this.mSelectedDrawable.draw(canvas);
            }
        }
    }

    public boolean hasVideo(int i) {
        return this.mTimeItemHours[i] != null && this.mTimeItemHours[i].b.size() > 0;
    }

    public boolean hasSave(int i) {
        TimeItemHour timeItemHour = this.mTimeItemHours[i];
        if (timeItemHour == null || timeItemHour.b.isEmpty()) {
            return false;
        }
        return timeItemHour.a();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessageDelayed(1, 1000);
        } else if (motionEvent.getAction() == 1) {
            if (this.mHandler.hasMessages(1)) {
                this.mHandler.removeMessages(1);
                onClick(motionEvent);
            }
        } else if (motionEvent.getAction() == 3) {
            this.mHandler.removeMessages(1);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void onClick(MotionEvent motionEvent) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int x = ((int) motionEvent.getX()) - paddingLeft;
        int y = (((int) motionEvent.getY()) - paddingTop) / (((getHeight() - getPaddingTop()) - getPaddingBottom()) / 4);
        int i = x / (width / 6);
        if (y >= 0 && y < 4 && i >= 0 && i < 6) {
            int i2 = (y * 6) + i;
            if (this.mIsMultiSelect) {
                if (this.mTimeItemHours[i2] != null) {
                    this.mSelecled[i2] = !this.mSelecled[i2];
                    if (this.mOnClickListener != null && hasVideo(i2)) {
                        this.mOnClickListener.onSelectChanged();
                    }
                    postInvalidate();
                }
            } else if (this.mOnClickListener != null && hasVideo(i2)) {
                this.mOnClickListener.onClick(this.mDay, i2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onLongClick() {
        if (this.mOnClickListener != null) {
            this.mOnClickListener.onLongClick();
        }
    }
}
