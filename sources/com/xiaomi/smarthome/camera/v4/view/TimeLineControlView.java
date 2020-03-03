package com.xiaomi.smarthome.camera.v4.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Paint;
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
import com.xiaomi.smarthome.camera.v4.view.ImageDrawable;
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
                    if (!TimeLineControlView.this.mIsNextLongPress) {
                        TimeLineControlView.this.moveNext();
                    }
                    TimeLineControlView.this.mIsNextLongPress = false;
                    TimeLineControlView.this.notifyUpdate();
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
    public void moveNext() {
        if (this.mTimeItems.size() != 0) {
            long selectTime = getSelectTime() + ((long) ((int) (30000.0f / this.mWidthScaleFators)));
            if (selectTime <= this.mTimeItems.get(this.mTimeItems.size() - 1).b()) {
                if (selectTime >= this.mTimeItems.get(this.mTimeItems.size() - 1).f8098a) {
                    setLivePlay();
                    return;
                }
                TimeItem needItem = getNeedItem(selectTime, true);
                if (needItem != null) {
                    setPlayTime(needItem.f8098a);
                } else {
                    setLivePlay();
                }
            }
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
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00d4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchDraw(android.graphics.Canvas r23) {
        /*
            r22 = this;
            r0 = r22
            r7 = r23
            super.dispatchDraw(r23)
            int r8 = r22.getWidth()
            int r1 = r22.getHeight()
            int r2 = r0.mBottomPadding
            int r9 = r1 - r2
            long r1 = java.lang.System.currentTimeMillis()
            long r3 = r0.mOffsetCurrentTime
            long r1 = r1 + r3
            r0.mCurrentTime = r1
            android.graphics.drawable.Drawable r1 = r0.mTimelineBg
            r10 = 0
            r1.setBounds(r10, r10, r8, r9)
            android.graphics.drawable.Drawable r1 = r0.mTimelineBg
            r1.draw(r7)
            r23.save()
            android.graphics.Rect r1 = new android.graphics.Rect
            int r2 = r0.mLastDrawableWidth
            int r2 = r2 + r10
            int r3 = r0.mLastDrawableWidth
            int r3 = r8 - r3
            int r4 = r9 + 1
            r1.<init>(r2, r10, r3, r4)
            r7.clipRect(r1)
            r1 = -52
            long r1 = r0.getTime(r1)
            com.xiaomi.smarthome.camera.v4.view.TimeLineControlView$TimeDay r3 = new com.xiaomi.smarthome.camera.v4.view.TimeLineControlView$TimeDay
            r3.<init>(r1)
            int r1 = r3.minute
            int r1 = r1 / 5
            int r1 = r1 * 5
            r3.minute = r1
            r3.second = r10
            r3.updateTimeInMillis()
            long r1 = r3.millis
            long r4 = r3.millis
            int r4 = r0.getPos(r4)
            int r4 = r4 + r10
            int r5 = r3.hour
            r6 = 1
            int r5 = r5 + r6
            int r3 = r3.minute
            r11 = -20
            r12 = 0
            r13 = r12
            r11 = 0
            r12 = -20
        L_0x006a:
            java.util.List<com.mijia.model.sdcard.TimeItem> r15 = r0.mTimeItems
            int r15 = r15.size()
            if (r11 >= r15) goto L_0x010a
            java.util.List<com.mijia.model.sdcard.TimeItem> r15 = r0.mTimeItems
            java.lang.Object r15 = r15.get(r11)
            com.mijia.model.sdcard.TimeItem r15 = (com.mijia.model.sdcard.TimeItem) r15
            long r6 = r15.f8098a
            int r6 = r0.getPos(r6)
            int r6 = r6 + r10
            r16 = r11
            long r10 = r15.f8098a
            r17 = r1
            long r1 = r15.b
            long r10 = r10 + r1
            long r1 = r0.mCurrentTime
            int r7 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r7 > 0) goto L_0x0092
            long r10 = r0.mCurrentTime
        L_0x0092:
            int r1 = r0.getPos(r10)
            if (r6 > r8) goto L_0x00f3
            if (r1 >= 0) goto L_0x009b
            goto L_0x00f3
        L_0x009b:
            if (r1 >= r12) goto L_0x009e
            goto L_0x00f3
        L_0x009e:
            r19 = r3
            long r2 = r15.f8098a
            int r7 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r7 <= 0) goto L_0x00b9
            long r2 = r15.f8098a
            long r2 = r2 - r13
            int r7 = r0.mLineLen
            r20 = r4
            r21 = r5
            long r4 = (long) r7
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 >= 0) goto L_0x00bd
            int r2 = r0.getPos(r13)
            goto L_0x00be
        L_0x00b9:
            r20 = r4
            r21 = r5
        L_0x00bd:
            r2 = r6
        L_0x00be:
            if (r2 >= r12) goto L_0x00c1
            r2 = r12
        L_0x00c1:
            int r3 = r15.d
            r4 = 1
            if (r3 != r4) goto L_0x00d4
            android.graphics.drawable.Drawable r3 = r0.mTimelineSaveSelBg
            r5 = 0
            r3.setBounds(r2, r5, r1, r9)
            android.graphics.drawable.Drawable r2 = r0.mTimelineSaveSelBg
            r7 = r23
            r2.draw(r7)
            goto L_0x00f0
        L_0x00d4:
            r5 = 0
            r7 = r23
            boolean r3 = r15.e
            if (r3 == 0) goto L_0x00e6
            android.graphics.drawable.Drawable r3 = r0.mTimelMotionBg
            r3.setBounds(r2, r5, r1, r9)
            android.graphics.drawable.Drawable r2 = r0.mTimelMotionBg
            r2.draw(r7)
            goto L_0x00f0
        L_0x00e6:
            android.graphics.drawable.Drawable r3 = r0.mTimelineSelBg
            r3.setBounds(r2, r5, r1, r9)
            android.graphics.drawable.Drawable r2 = r0.mTimelineSelBg
            r2.draw(r7)
        L_0x00f0:
            r12 = r1
            r13 = r10
            goto L_0x00fc
        L_0x00f3:
            r19 = r3
            r20 = r4
            r21 = r5
            r4 = 1
            r7 = r23
        L_0x00fc:
            int r11 = r16 + 1
            r1 = r17
            r3 = r19
            r4 = r20
            r5 = r21
            r6 = 1
            r10 = 0
            goto L_0x006a
        L_0x010a:
            r17 = r1
            r19 = r3
            r20 = r4
            r21 = r5
            android.graphics.Paint r1 = r0.mPaint
            r10 = 30
            float r2 = (float) r10
            r1.setTextSize(r2)
            android.graphics.Paint r1 = r0.mPaint
            r2 = 1065353216(0x3f800000, float:1.0)
            r1.setStrokeWidth(r2)
            r12 = r19
            r11 = r20
            r13 = r21
        L_0x0127:
            int r1 = r8 + 50
            if (r11 >= r1) goto L_0x021b
            r1 = 60
            r14 = -8353911(0xffffffffff808789, float:NaN)
            r2 = -5852989(0xffffffffffa6b0c3, float:NaN)
            if (r12 != r1) goto L_0x01a4
            android.graphics.Paint r1 = r0.mPaint
            r1.setColor(r2)
            float r4 = (float) r11
            int r1 = r0.mTopH
            int r1 = r1 * 3
            r2 = 0
            int r1 = r1 + r2
            float r3 = (float) r1
            int r12 = r2 + r9
            int r1 = r0.mTopH
            int r1 = r1 * 2
            int r1 = r12 - r1
            float r5 = (float) r1
            android.graphics.Paint r6 = r0.mPaint
            r1 = r23
            r2 = r4
            r1.drawLine(r2, r3, r4, r5, r6)
            r1 = 24
            if (r13 != r1) goto L_0x0158
            r13 = 0
        L_0x0158:
            if (r13 == 0) goto L_0x015e
            r1 = 23
            if (r13 != r1) goto L_0x0175
        L_0x015e:
            android.graphics.Paint r1 = r0.mPaint
            r2 = -8355712(0xffffffffff808080, float:NaN)
            r1.setColor(r2)
            java.lang.String r1 = com.Utils.TimeUtils.c(r17)
            int r2 = r11 + 5
            float r2 = (float) r2
            r3 = 50
            float r3 = (float) r3
            android.graphics.Paint r4 = r0.mPaint
            r7.drawText(r1, r2, r3, r4)
        L_0x0175:
            android.graphics.Paint r1 = r0.mPaint
            r1.setColor(r14)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ""
            r1.append(r2)
            r1.append(r13)
            java.lang.String r2 = ":00"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            int r2 = r11 + 10
            float r2 = (float) r2
            int r3 = r0.mTopH
            int r3 = r3 * 2
            int r12 = r12 - r3
            int r12 = r12 + -10
            float r3 = (float) r12
            android.graphics.Paint r4 = r0.mPaint
            r7.drawText(r1, r2, r3, r4)
            int r13 = r13 + 1
            r12 = 0
            goto L_0x020f
        L_0x01a4:
            if (r12 != r10) goto L_0x01f2
            android.graphics.Paint r1 = r0.mPaint
            r1.setColor(r2)
            float r4 = (float) r11
            int r1 = r0.mTopH
            int r1 = r1 * 3
            r2 = 0
            int r1 = r1 + r2
            float r3 = (float) r1
            int r15 = r2 + r9
            int r1 = r0.mTopH
            int r1 = r1 * 2
            int r1 = r15 - r1
            float r5 = (float) r1
            android.graphics.Paint r6 = r0.mPaint
            r1 = r23
            r2 = r4
            r1.drawLine(r2, r3, r4, r5, r6)
            android.graphics.Paint r1 = r0.mPaint
            r1.setColor(r14)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ""
            r1.append(r2)
            int r2 = r13 + -1
            r1.append(r2)
            java.lang.String r2 = ":30"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            int r2 = r11 + 10
            float r2 = (float) r2
            int r3 = r0.mTopH
            int r3 = r3 * 2
            int r15 = r15 - r3
            int r15 = r15 + -10
            float r3 = (float) r15
            android.graphics.Paint r4 = r0.mPaint
            r7.drawText(r1, r2, r3, r4)
            goto L_0x020f
        L_0x01f2:
            android.graphics.Paint r1 = r0.mPaint
            r1.setColor(r2)
            float r4 = (float) r11
            int r1 = r0.mTopH
            int r1 = r1 * 9
            r2 = 0
            int r1 = r1 + r2
            float r3 = (float) r1
            int r1 = r2 + r9
            int r2 = r0.mTopH
            int r2 = r2 * 10
            int r1 = r1 - r2
            float r5 = (float) r1
            android.graphics.Paint r6 = r0.mPaint
            r1 = r23
            r2 = r4
            r1.drawLine(r2, r3, r4, r5, r6)
        L_0x020f:
            int r12 = r12 + 5
            r1 = 300000(0x493e0, double:1.482197E-318)
            long r17 = r17 + r1
            int r1 = r0.mWidthPer5Minutes
            int r11 = r11 + r1
            goto L_0x0127
        L_0x021b:
            android.graphics.drawable.Drawable r1 = r0.mTimelinePointer
            int r8 = r8 / 2
            r2 = 0
            int r10 = r2 + r8
            int r3 = r0.mTimeBarW
            int r3 = r3 / 2
            int r3 = r10 - r3
            int r4 = r0.mTimeBarW
            int r4 = r4 / 2
            int r10 = r10 + r4
            int r4 = r2 + r9
            int r5 = r0.mBottomPadding
            int r4 = r4 + r5
            r1.setBounds(r3, r2, r10, r4)
            android.graphics.drawable.Drawable r1 = r0.mTimelinePointer
            r1.draw(r7)
            r23.restore()
            boolean r1 = r0.mMediaPlayer
            if (r1 != 0) goto L_0x0257
            java.util.ArrayList<com.xiaomi.smarthome.camera.v4.view.ImageDrawable> r1 = r0.mImageDrawables
            java.util.Iterator r1 = r1.iterator()
        L_0x0247:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0257
            java.lang.Object r2 = r1.next()
            com.xiaomi.smarthome.camera.v4.view.ImageDrawable r2 = (com.xiaomi.smarthome.camera.v4.view.ImageDrawable) r2
            r2.draw(r7)
            goto L_0x0247
        L_0x0257:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.v4.view.TimeLineControlView.dispatchDraw(android.graphics.Canvas):void");
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
