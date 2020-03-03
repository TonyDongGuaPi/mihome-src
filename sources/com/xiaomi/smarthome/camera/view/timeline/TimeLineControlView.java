package com.xiaomi.smarthome.camera.view.timeline;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import com.Utils.TimeUtils;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.sdcard.TimeItem;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.view.timeline.ImageDrawable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

public class TimeLineControlView extends View implements ScaleGestureDetector.OnScaleGestureListener {
    static final int LONG_PRESS_MOVE = 30000;
    static final float MAX_SCALE = 10.0f;
    static final long MINITUES = 60000;
    static final float MIN_SCALE = 0.5f;
    static final int MSG_LAST_LONG_PRESS = 3;
    static final int MSG_NEXT_LONG_PRESS = 2;
    static final int MSG_NOTIFY_UPDATE = 1;
    static final int TIME_LINE_MINITUES = 5;
    long mBeforeScaleTime;
    int mBottomPadding;
    long mCurrentTime;
    int mHalfW;
    Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (TimeLineControlView.this.mTimeLineCallback != null) {
                        TimeLineControlView.this.mCurrentTime = System.currentTimeMillis() + TimeLineControlView.this.mOffsetCurrentTime;
                        if (Math.abs(TimeLineControlView.this.mCurrentTime - TimeLineControlView.this.getSelectTime()) < 15000) {
                            TimeLineControlView.this.setLivePlay();
                            SDKLog.e(Tag.e, " selectTime near now ");
                        } else if (TimeLineControlView.this.mTimeItems.isEmpty()) {
                            TimeLineControlView.this.updatePlayTime(System.currentTimeMillis());
                            if (TimeLineControlView.this.mTimeLineCallback != null) {
                                TimeLineControlView.this.mTimeLineCallback.onCancel();
                            }
                        } else if (TimeLineControlView.this.mTimeLineCallback != null) {
                            TimeLineControlView.this.mTimeLineCallback.onSelectTime(TimeLineControlView.this.getSelectTime());
                        }
                        TimeLineControlView.this.mIsPress = false;
                        return;
                    }
                    return;
                case 2:
                    if (TimeLineControlView.this.mIsNextPress) {
                        TimeLineControlView.this.mIsNextLongPress = true;
                        TimeLineControlView.this.movePressNext();
                        TimeLineControlView.this.mHandler.sendEmptyMessageDelayed(2, 50);
                        return;
                    }
                    TimeLineControlView.this.mHandler.removeMessages(2);
                    return;
                case 3:
                    if (TimeLineControlView.this.mIsLastPress) {
                        TimeLineControlView.this.mIsLastLongPress = true;
                        TimeLineControlView.this.movePressLast();
                        TimeLineControlView.this.mHandler.sendEmptyMessageDelayed(3, 50);
                        return;
                    }
                    TimeLineControlView.this.mHandler.removeMessages(3);
                    return;
                default:
                    return;
            }
        }
    };
    ArrayList<ImageDrawable> mImageDrawables = new ArrayList<>();
    boolean mIsLastLongPress = false;
    boolean mIsLastPress = false;
    boolean mIsNextLongPress = false;
    boolean mIsNextPress = false;
    boolean mIsPress = false;
    ImageDrawable mLastDrawable;
    int mLastDrawableWidth;
    private int mLineLen = 5001;
    boolean mMediaPlayer = false;
    boolean mNeedNextButton = true;
    ImageDrawable mNextDrawable;
    long mOffsetCurrentTime;
    int mOffsetPos;
    boolean mOnscaleBegin = false;
    Paint mPaint;
    ScaleGestureDetector mScaleGestureDetector;
    int mTimeBarW;
    List<TimeItem> mTimeItems = new ArrayList();
    TimeLineCallback mTimeLineCallback;
    Drawable mTimelMotionBg;
    Drawable mTimelineBg;
    Drawable mTimelinePointer;
    Drawable mTimelineSaveSelBg;
    Drawable mTimelineSelBg;
    int mTopH;
    int mTouchStartX;
    int mWidthPer5Minutes;
    int mWidthPer5MinutesBase;
    float mWidthScaleFators = 1.0f;

    public interface TimeLineCallback {
        void onCancel();

        void onPlayLive();

        void onSelectTime(long j);

        void onUpdateTime(long j);
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
    }

    public void setNeedNextButton(boolean z) {
        this.mNeedNextButton = z;
    }

    public void setTimeLineCallback(TimeLineCallback timeLineCallback) {
        this.mTimeLineCallback = timeLineCallback;
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        this.mWidthScaleFators *= scaleGestureDetector.getScaleFactor();
        scale(this.mWidthScaleFators);
        return true;
    }

    public void scale(float f) {
        this.mWidthScaleFators = f;
        if (this.mWidthScaleFators > 10.0f) {
            this.mWidthScaleFators = 10.0f;
        }
        if (this.mWidthScaleFators < 0.5f) {
            this.mWidthScaleFators = 0.5f;
        }
        this.mWidthPer5Minutes = (int) (((float) this.mWidthPer5MinutesBase) * this.mWidthScaleFators);
        this.mOffsetPos = (int) (((this.mCurrentTime - this.mBeforeScaleTime) * ((long) this.mWidthPer5Minutes)) / 300000);
        invalidate();
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        this.mOnscaleBegin = true;
        this.mBeforeScaleTime = getSelectTime();
        return true;
    }

    public void setTimeItems(List<TimeItem> list) {
        this.mTimeItems = list;
        invalidate();
    }

    public TimeLineControlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScaleGestureDetector = new ScaleGestureDetector(context, this);
        Resources resources = getContext().getResources();
        this.mTopH = resources.getDimensionPixelSize(R.dimen.time_line_topbar_h);
        this.mTimeBarW = resources.getDimensionPixelSize(R.dimen.time_line_timebar_w);
        this.mWidthPer5MinutesBase = resources.getDimensionPixelSize(R.dimen.time_line_time_5m_w);
        this.mWidthPer5Minutes = (int) (((float) this.mWidthPer5MinutesBase) * this.mWidthScaleFators);
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mTimelineBg = resources.getDrawable(R.drawable.progress_bg_01);
        this.mTimelineSelBg = resources.getDrawable(R.drawable.progress_bg_02);
        this.mTimelMotionBg = resources.getDrawable(R.drawable.progress_bg_02);
        this.mTimelineSaveSelBg = resources.getDrawable(R.drawable.progress_bg_03);
        this.mBottomPadding = 0;
        this.mTimelinePointer = resources.getDrawable(R.drawable.progress_components_pointer_nor);
        if (this.mNeedNextButton) {
            this.mLastDrawable = new ImageDrawable(resources.getDrawable(R.drawable.progress_button_last_nor), resources.getDrawable(R.drawable.progress_button_last_pres));
            this.mNextDrawable = new ImageDrawable(resources.getDrawable(R.drawable.progress_button_next_nor), resources.getDrawable(R.drawable.progress_button_next_pres));
            this.mLastDrawableWidth = resources.getDimensionPixelOffset(R.dimen.time_line_last_w);
            this.mImageDrawables.add(this.mLastDrawable);
            this.mImageDrawables.add(this.mNextDrawable);
            this.mLastDrawable.setOnTouchListener(new ImageDrawable.OnTouchListener() {
                public void onActionDown() {
                    SDKLog.b(Tag.e, "last down");
                    TimeLineControlView.this.mIsPress = true;
                    TimeLineControlView.this.mIsLastLongPress = false;
                    TimeLineControlView.this.mIsLastPress = true;
                    TimeLineControlView.this.mHandler.sendEmptyMessageDelayed(3, 1000);
                }

                public void onActionUp() {
                    SDKLog.b(Tag.e, "last up");
                    TimeLineControlView.this.mIsLastPress = false;
                    TimeLineControlView.this.mHandler.removeMessages(3);
                    if (!TimeLineControlView.this.mIsLastLongPress) {
                        TimeLineControlView.this.movePrev();
                    }
                    TimeLineControlView.this.mIsLastLongPress = false;
                    TimeLineControlView.this.notifyUpdate();
                }
            });
            this.mNextDrawable.setOnTouchListener(new ImageDrawable.OnTouchListener() {
                public void onActionDown() {
                    SDKLog.b(Tag.e, "next down");
                    TimeLineControlView.this.mIsPress = true;
                    TimeLineControlView.this.mIsNextLongPress = false;
                    TimeLineControlView.this.mIsNextPress = true;
                    TimeLineControlView.this.mHandler.sendEmptyMessageDelayed(2, 1000);
                }

                public void onActionUp() {
                    SDKLog.b(Tag.e, "next up");
                    TimeLineControlView.this.mIsNextPress = false;
                    TimeLineControlView.this.mHandler.removeMessages(2);
                    boolean moveNext = !TimeLineControlView.this.mIsNextLongPress ? TimeLineControlView.this.moveNext() : true;
                    TimeLineControlView.this.mIsNextLongPress = false;
                    if (moveNext) {
                        TimeLineControlView.this.notifyUpdate();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void movePrev() {
        if (this.mTimeItems.size() != 0) {
            long selectTime = getSelectTime() - ((long) ((int) (30000.0f / this.mWidthScaleFators)));
            if (selectTime >= this.mTimeItems.get(0).f8098a) {
                if (selectTime < this.mTimeItems.get(0).b()) {
                    setPlayTime(this.mTimeItems.get(0).f8098a);
                    return;
                }
                TimeItem needItem = getNeedItem(selectTime, false);
                if (needItem != null) {
                    setPlayTime(needItem.f8098a);
                } else {
                    setPlayTime(this.mTimeItems.get(0).f8098a);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void movePressLast() {
        if (this.mTimeItems.size() != 0) {
            long j = (long) ((int) (30000.0f / this.mWidthScaleFators));
            long selectTime = getSelectTime() - j;
            if (selectTime >= this.mTimeItems.get(0).f8098a) {
                if (selectTime < this.mTimeItems.get(0).b()) {
                    setPlayTime(selectTime);
                    return;
                }
                int size = this.mTimeItems.size();
                int i = 1;
                while (i < size) {
                    if (selectTime < this.mTimeItems.get(i).f8098a) {
                        int i2 = i - 1;
                        if (selectTime < this.mTimeItems.get(i2).b() - j) {
                            setPlayTime(selectTime);
                            return;
                        } else {
                            setPlayTime(this.mTimeItems.get(i2).b() - j);
                            return;
                        }
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean moveNext() {
        if (this.mTimeItems.size() == 0) {
            return false;
        }
        long selectTime = getSelectTime() + ((long) ((int) (30000.0f / this.mWidthScaleFators)));
        if (selectTime > this.mTimeItems.get(this.mTimeItems.size() - 1).b()) {
            setLivePlay();
            return false;
        } else if (selectTime >= this.mTimeItems.get(this.mTimeItems.size() - 1).f8098a) {
            setLivePlay();
            return false;
        } else {
            TimeItem needItem = getNeedItem(selectTime, true);
            if (needItem != null) {
                setPlayTime(needItem.f8098a);
                return true;
            }
            setLivePlay();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void movePressNext() {
        if (this.mTimeItems.size() != 0) {
            long selectTime = getSelectTime() + ((long) ((int) (30000.0f / this.mWidthScaleFators)));
            if (selectTime <= this.mTimeItems.get(this.mTimeItems.size() - 1).b()) {
                if (selectTime >= this.mTimeItems.get(this.mTimeItems.size() - 1).f8098a) {
                    setPlayTime(selectTime);
                    return;
                }
                int size = this.mTimeItems.size() - 2;
                while (size >= 0) {
                    TimeItem timeItem = this.mTimeItems.get(size);
                    if (selectTime <= timeItem.f8098a) {
                        size--;
                    } else if (selectTime < timeItem.b()) {
                        setPlayTime(selectTime);
                        return;
                    } else {
                        setPlayTime(this.mTimeItems.get(size + 1).f8098a);
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyUpdate() {
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, 1200);
    }

    /* access modifiers changed from: package-private */
    public void setLivePlay() {
        this.mHandler.removeMessages(1);
        setPlayTime(this.mCurrentTime);
        if (this.mTimeLineCallback != null) {
            this.mTimeLineCallback.onPlayLive();
        }
    }

    public void setMediaPlayer(boolean z) {
        this.mMediaPlayer = z;
    }

    public void synCurrentTime(long j) {
        this.mOffsetCurrentTime = j - System.currentTimeMillis();
        this.mCurrentTime = j;
        postInvalidate();
    }

    public void updatePlayTime(long j) {
        if (j == 0) {
            j = this.mCurrentTime;
        }
        this.mOffsetPos -= (int) (((j - getSelectTime()) * ((long) this.mWidthPer5Minutes)) / 300000);
        postInvalidate();
    }

    private void setPlayTime(long j) {
        this.mOffsetPos -= (int) (((j - getSelectTime()) * ((long) this.mWidthPer5Minutes)) / 300000);
        postInvalidate();
        if (this.mTimeLineCallback != null) {
            this.mTimeLineCallback.onUpdateTime(j);
        }
    }

    public void setJustPlayTime(long j) {
        this.mOffsetPos -= (int) (((j - getSelectTime()) * ((long) this.mWidthPer5Minutes)) / 300000);
        postInvalidate();
    }

    public boolean isPlayRealTime() {
        return this.mTimeItems != null && this.mTimeItems.size() > 0 && getSelectTime() > this.mTimeItems.get(this.mTimeItems.size() - 1).b();
    }

    public long getSelectTime() {
        return getTime(this.mHalfW);
    }

    /* access modifiers changed from: package-private */
    public long getTime(int i) {
        return this.mCurrentTime + ((((long) ((i - this.mHalfW) - this.mOffsetPos)) * 300000) / ((long) this.mWidthPer5Minutes));
    }

    /* access modifiers changed from: package-private */
    public int getPos(long j) {
        return (int) ((((j - this.mCurrentTime) * ((long) this.mWidthPer5Minutes)) / 300000) + ((long) this.mHalfW) + ((long) this.mOffsetPos));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = i3 - i;
        this.mHalfW = i5 / 2;
        if (this.mNeedNextButton) {
            int i6 = i4 - i2;
            this.mLastDrawable.setRect(0, 3, this.mLastDrawableWidth, (i6 - this.mBottomPadding) - 3);
            this.mNextDrawable.setRect(i5 - this.mLastDrawableWidth, 3, i5, (i6 - this.mBottomPadding) - 3);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.mMediaPlayer) {
            Iterator<ImageDrawable> it = this.mImageDrawables.iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (it.next().onTouchEvent(motionEvent)) {
                    z = true;
                }
            }
            if (z) {
                invalidate();
                return true;
            }
        }
        this.mScaleGestureDetector.onTouchEvent(motionEvent);
        if (this.mOnscaleBegin) {
            if (motionEvent.getAction() == 3 || motionEvent.getAction() == 1) {
                this.mOnscaleBegin = false;
                notifyUpdate();
            }
            return true;
        }
        if (motionEvent.getAction() == 0) {
            this.mIsPress = true;
            this.mHandler.removeMessages(1);
            this.mTouchStartX = (int) motionEvent.getX();
        } else if (motionEvent.getAction() == 2) {
            this.mOffsetPos += (int) (motionEvent.getX() - ((float) this.mTouchStartX));
            long selectTime = getSelectTime();
            this.mCurrentTime = System.currentTimeMillis() + this.mOffsetCurrentTime;
            if (selectTime > this.mCurrentTime) {
                setJustPlayTime(System.currentTimeMillis());
                SDKLog.d(Tag.e, "only update currentTime");
            } else if (this.mCurrentTime - selectTime > 2000 && this.mTimeLineCallback != null) {
                this.mTimeLineCallback.onUpdateTime(getSelectTime());
            }
            this.mTouchStartX = (int) motionEvent.getX();
        } else if (motionEvent.getAction() == 3 || motionEvent.getAction() == 1) {
            this.mTouchStartX = 0;
            notifyUpdate();
        }
        invalidate();
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean getIsPress() {
        return this.mIsPress;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        Canvas canvas2 = canvas;
        super.dispatchDraw(canvas);
        int width = getWidth();
        int height = getHeight() - this.mBottomPadding;
        long j = this.mCurrentTime;
        this.mCurrentTime = System.currentTimeMillis() + this.mOffsetCurrentTime;
        this.mOffsetPos += (int) (((this.mCurrentTime - j) * ((long) this.mWidthPer5Minutes)) / 300000);
        int i4 = 0;
        this.mTimelineBg.setBounds(0, 0, width, height);
        this.mTimelineBg.draw(canvas2);
        canvas.save();
        canvas2.clipRect(new Rect(this.mLastDrawableWidth + 0, 0, width - this.mLastDrawableWidth, height + 1));
        TimeDay timeDay = new TimeDay(getTime(-52));
        timeDay.minute = (timeDay.minute / 5) * 5;
        timeDay.second = 0;
        timeDay.updateTimeInMillis();
        long j2 = timeDay.millis;
        int pos = getPos(timeDay.millis) + 0;
        int i5 = timeDay.hour + 1;
        int i6 = timeDay.minute;
        long j3 = 0;
        int i7 = 0;
        int i8 = -20;
        while (i7 < this.mTimeItems.size()) {
            TimeItem timeItem = this.mTimeItems.get(i7);
            long j4 = j2;
            int pos2 = getPos(timeItem.f8098a) + i4;
            int i9 = i8;
            int i10 = i6;
            long j5 = timeItem.f8098a + timeItem.b;
            if (this.mCurrentTime <= j5) {
                j5 = this.mCurrentTime;
            }
            int pos3 = getPos(j5);
            if (pos2 > width || pos3 < 0) {
                i2 = pos;
                i = i5;
                i3 = i9;
            } else {
                i3 = i9;
                if (pos3 < i3) {
                    i2 = pos;
                    i = i5;
                } else {
                    i2 = pos;
                    i = i5;
                    if (timeItem.f8098a > j3 && timeItem.f8098a - j3 < ((long) this.mLineLen)) {
                        pos2 = getPos(j3);
                    }
                    if (pos2 < i3) {
                        pos2 = i3;
                    }
                    this.mTimelineSelBg.setBounds(pos2, 0, pos3, height);
                    this.mTimelineSelBg.draw(canvas2);
                    j3 = j5;
                    i8 = pos3;
                    i7++;
                    j2 = j4;
                    i6 = i10;
                    pos = i2;
                    i5 = i;
                    i4 = 0;
                }
            }
            i8 = i3;
            i7++;
            j2 = j4;
            i6 = i10;
            pos = i2;
            i5 = i;
            i4 = 0;
        }
        long j6 = j2;
        this.mPaint.setTextSize((float) 30);
        this.mPaint.setStrokeWidth(1.0f);
        int i11 = i6;
        int i12 = i5;
        for (int i13 = pos; i13 < width + 50; i13 += this.mWidthPer5Minutes) {
            if (i11 == 60) {
                this.mPaint.setColor(-5852989);
                float f = (float) i13;
                int i14 = 0 + height;
                canvas.drawLine(f, (float) (0 + (this.mTopH * 3)), f, (float) (i14 - (this.mTopH * 2)), this.mPaint);
                if (i12 == 24) {
                    i12 = 0;
                }
                if (i12 == 0 || i12 == 23) {
                    this.mPaint.setColor(-8355712);
                    canvas2.drawText(TimeUtils.c(j6), (float) (i13 + 5), (float) 50, this.mPaint);
                }
                this.mPaint.setColor(-8353911);
                canvas2.drawText("" + i12 + ":00", (float) (i13 + 10), (float) ((i14 - (this.mTopH * 2)) - 10), this.mPaint);
                i12++;
                i11 = 0;
            } else if (i11 == 30) {
                this.mPaint.setColor(-5852989);
                float f2 = (float) i13;
                int i15 = 0 + height;
                canvas.drawLine(f2, (float) ((this.mTopH * 3) + 0), f2, (float) (i15 - (this.mTopH * 2)), this.mPaint);
                this.mPaint.setColor(-8353911);
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(i12 - 1);
                sb.append(":30");
                canvas2.drawText(sb.toString(), (float) (i13 + 10), (float) ((i15 - (this.mTopH * 2)) - 10), this.mPaint);
            } else {
                this.mPaint.setColor(-5852989);
                float f3 = (float) i13;
                canvas.drawLine(f3, (float) ((this.mTopH * 9) + 0), f3, (float) ((0 + height) - (this.mTopH * 10)), this.mPaint);
            }
            i11 += 5;
            j6 += 300000;
        }
        int i16 = 0 + (width / 2);
        this.mTimelinePointer.setBounds(i16 - (this.mTimeBarW / 2), 0, i16 + (this.mTimeBarW / 2), 0 + height + this.mBottomPadding);
        this.mTimelinePointer.draw(canvas2);
        canvas.restore();
        if (!this.mMediaPlayer) {
            Iterator<ImageDrawable> it = this.mImageDrawables.iterator();
            while (it.hasNext()) {
                it.next().draw(canvas2);
            }
        }
    }

    public static class TimeDay {
        public int day;
        public int hour;
        public long millis;
        public int minute;
        public int month;
        public int second;
        public int year;

        public TimeDay(long j) {
            updateTime(j);
        }

        public void updateTime(long j) {
            this.millis = j;
            GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeUtils.a());
            gregorianCalendar.setTimeInMillis(this.millis);
            this.year = gregorianCalendar.get(1);
            this.month = gregorianCalendar.get(2) + 1;
            this.day = gregorianCalendar.get(5);
            this.hour = gregorianCalendar.get(11);
            this.minute = gregorianCalendar.get(12);
            this.second = gregorianCalendar.get(13);
        }

        public void updateTimeInMillis() {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getDefault());
            gregorianCalendar.set(this.year, this.month - 1, this.day, this.hour, this.minute, this.second);
            this.millis = gregorianCalendar.getTimeInMillis();
        }
    }

    private TimeItem getNeedItem(long j, boolean z) {
        if (z) {
            int size = this.mTimeItems.size() - 1;
            TimeItem timeItem = null;
            boolean z2 = false;
            for (int i = 0; i <= size; i++) {
                TimeItem timeItem2 = this.mTimeItems.get(i);
                if (!z2) {
                    timeItem = timeItem2;
                }
                if (i < size && this.mTimeItems.get(i + 1).f8098a - timeItem2.c <= ((long) this.mLineLen)) {
                    z2 = true;
                } else if (i == size - 1 && z2) {
                    return null;
                } else {
                    if (timeItem.f8098a >= j) {
                        return timeItem;
                    }
                    z2 = false;
                }
            }
            return null;
        }
        for (int size2 = this.mTimeItems.size() - 1; size2 >= 0; size2--) {
            TimeItem timeItem3 = this.mTimeItems.get(size2);
            if ((size2 <= 0 || timeItem3.f8098a - this.mTimeItems.get(size2 - 1).c > ((long) this.mLineLen)) && timeItem3.f8098a <= j) {
                return timeItem3;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            this.mLastDrawable.mPressedDrawable = getResources().getDrawable(R.drawable.progress_button_last_land_pres);
            this.mNextDrawable.mPressedDrawable = getResources().getDrawable(R.drawable.progress_button_next_land_pres);
            return;
        }
        this.mLastDrawable.mPressedDrawable = getResources().getDrawable(R.drawable.progress_button_last_pres);
        this.mNextDrawable.mPressedDrawable = getResources().getDrawable(R.drawable.progress_button_next_pres);
    }
}
