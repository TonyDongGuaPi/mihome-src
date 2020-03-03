package com.xiaomi.smarthome.camera.view.timeline;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.Utils.TimeUtils;
import com.mijia.model.sdcard.TimeItem;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.view.timeline.TimeLineControlView;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class TimeLineWithDatePickView extends LinearLayout implements TimeLineControlView.TimeLineCallback {
    private static final String TAG = "TimeLineWithDatePickView";
    public CAMERA_FUNC_STATUS cameraFuncStatus = CAMERA_FUNC_STATUS.STATUS_DEFAULT;
    TextView mCurrentSelectView;
    ArrayList<DateItem> mDateItems = new ArrayList<>();
    LinearLayout mDatePick;
    HorizontalScrollView mHorizontalScrollView;
    boolean mIsRealPlay = true;
    long mLastRecordTime;
    LayoutInflater mLayoutInflater;
    int mOrientation = 1;
    TimeLineControlView.TimeLineCallback mTimeLineCallback;
    TimeLineControlView mTimeLineControlView;

    public enum CAMERA_FUNC_STATUS {
        STATUS_DEFAULT,
        STATUS_SPEAK,
        STATUS_LISTEN
    }

    public boolean isPlayRealTime() {
        return false;
    }

    public TimeLineWithDatePickView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onUpdateTime(long j) {
        if (this.mTimeLineCallback != null) {
            this.mTimeLineCallback.onUpdateTime(j);
        }
        setSeletciontTime(j);
    }

    public void onSelectTime(long j) {
        if (this.mTimeLineCallback != null) {
            this.mTimeLineCallback.onSelectTime(j);
        }
        setSeletciontTime(j);
    }

    public void onPlayLive() {
        if (this.mTimeLineCallback != null) {
            this.mTimeLineCallback.onPlayLive();
        }
        setSeletction(this.mDateItems.size() - 1);
    }

    public void onCancel() {
        if (this.mTimeLineCallback != null) {
            this.mTimeLineCallback.onCancel();
        }
    }

    static class DateItem {
        String dateStr;
        long startTime;

        DateItem() {
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mLayoutInflater = LayoutInflater.from(getContext());
        this.mTimeLineControlView = (TimeLineControlView) findViewById(R.id.time_line_control);
        this.mTimeLineControlView.setTimeLineCallback(this);
        this.mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.date_pick_scroll);
        this.mDatePick = (LinearLayout) findViewById(R.id.date_pick);
    }

    /* access modifiers changed from: package-private */
    public void refreshDate() {
        this.mDatePick.removeAllViews();
        for (int i = 0; i < this.mDateItems.size(); i++) {
            DateItem dateItem = this.mDateItems.get(i);
            View inflate = this.mLayoutInflater.inflate(R.layout.time_line_date_pick_item, (ViewGroup) null);
            final TextView textView = (TextView) inflate.findViewById(R.id.date_txt);
            if (this.mOrientation == 1) {
                textView.setBackgroundResource(R.drawable.player_button_02);
            } else {
                textView.setBackgroundResource(R.drawable.player_button_02_land);
            }
            textView.setTag(dateItem);
            textView.setText(dateItem.dateStr);
            textView.setTextColor(ColorStateList.valueOf(-5066062));
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    TimeLineWithDatePickView.this.setSeletctionView(textView);
                    Object tag = TimeLineWithDatePickView.this.mCurrentSelectView.getTag();
                    if (tag != null && (tag instanceof DateItem)) {
                        TimeLineWithDatePickView.this.selectDate(TimeLineWithDatePickView.this.mCurrentSelectView, (DateItem) tag);
                    }
                }
            });
            this.mDatePick.addView(inflate);
        }
        setSeletciontTime(getSelectTime());
    }

    /* access modifiers changed from: package-private */
    public void selectDate(View view, DateItem dateItem) {
        updatePlayTime(dateItem.startTime, false);
        if (this.mTimeLineCallback != null) {
            this.mTimeLineCallback.onSelectTime(dateItem.startTime);
        }
    }

    /* access modifiers changed from: package-private */
    public void setSeletciontTime(long j) {
        if (isPlayRealTime() || j == 0) {
            setSeletction(this.mDateItems.size() - 1);
            return;
        }
        String c = TimeUtils.c(j);
        for (int i = 0; i < this.mDateItems.size(); i++) {
            if (c.equals(this.mDateItems.get(i).dateStr)) {
                setSeletction(i);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setSeletctionView(TextView textView) {
        if (this.mCurrentSelectView != textView) {
            if (this.mCurrentSelectView != null) {
                if (this.mOrientation == 1) {
                    this.mCurrentSelectView.setBackgroundResource(R.drawable.player_button_02);
                } else {
                    this.mCurrentSelectView.setBackgroundResource(R.drawable.player_button_02_land);
                }
                this.mCurrentSelectView.setTextColor(ColorStateList.valueOf(-5066062));
            }
            this.mCurrentSelectView = textView;
            if (this.mOrientation == 1) {
                this.mCurrentSelectView.setBackgroundResource(R.drawable.player_button_01);
            } else {
                this.mCurrentSelectView.setBackgroundResource(R.drawable.player_button_01_land);
            }
            this.mCurrentSelectView.setTextColor(ColorStateList.valueOf(-1));
        }
    }

    /* access modifiers changed from: package-private */
    public void setSeletction(int i) {
        if (i >= 0 && i < this.mDatePick.getChildCount()) {
            View findViewById = this.mDatePick.getChildAt(i).findViewById(R.id.date_txt);
            setSeletctionView((TextView) findViewById);
            this.mHorizontalScrollView.requestChildFocus(this.mDatePick, findViewById);
        }
    }

    public void setTimeLineCallback(TimeLineControlView.TimeLineCallback timeLineCallback) {
        this.mTimeLineCallback = timeLineCallback;
    }

    public void setTimeItems(List<TimeItem> list) {
        if (list.size() > 0) {
            this.mLastRecordTime = list.get(list.size() - 1).c;
        }
        this.mTimeLineControlView.setTimeItems(list);
        long j = 0;
        this.mDateItems.clear();
        for (int i = 0; i < list.size(); i++) {
            TimeItem timeItem = list.get(i);
            if (timeItem.f8098a >= j) {
                DateItem dateItem = new DateItem();
                dateItem.startTime = timeItem.f8098a;
                dateItem.dateStr = TimeUtils.c(timeItem.f8098a);
                this.mDateItems.add(dateItem);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTimeZone(TimeUtils.a());
                gregorianCalendar.setTimeInMillis(timeItem.f8098a);
                GregorianCalendar gregorianCalendar2 = new GregorianCalendar(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5));
                gregorianCalendar2.setTimeZone(TimeUtils.a());
                gregorianCalendar.setTimeInMillis(gregorianCalendar2.getTimeInMillis() + 86400010);
                j = gregorianCalendar.getTimeInMillis();
            }
        }
        refreshDate();
    }

    public void synCurrentTime(long j) {
        if (j != 0) {
            this.mTimeLineControlView.synCurrentTime(j);
        }
    }

    public void updatePlayTime(long j, boolean z) {
        this.mTimeLineControlView.updatePlayTime(j);
        setSeletciontTime(j);
    }

    public long getSelectTime() {
        return this.mTimeLineControlView.getSelectTime();
    }

    public boolean getIsPress() {
        return this.mTimeLineControlView.getIsPress();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.cameraFuncStatus != CAMERA_FUNC_STATUS.STATUS_SPEAK) {
            return super.dispatchTouchEvent(motionEvent);
        }
        ToastUtil.a(getContext(), (int) R.string.speaking_block);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mOrientation = configuration.orientation;
        refreshDate();
    }
}
