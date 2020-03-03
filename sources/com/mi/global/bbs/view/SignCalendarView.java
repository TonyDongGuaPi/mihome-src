package com.mi.global.bbs.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mi.global.bbs.R;
import java.util.Calendar;
import org.json.JSONArray;

public class SignCalendarView extends LinearLayout {
    @BindView(2131493172)
    TextView currentDateTv;
    int divide;
    private Paint mPaint;
    @BindView(2131493650)
    MonthView monthView;
    @BindView(2131493747)
    ImageView nextMonthIv;
    private OnControlMonthChangeListener onControlMonthChangeListener;
    private OnMonthChangeListener onMonthChangeListener;
    @BindView(2131493825)
    ImageView preMonthIv;

    public interface OnControlMonthChangeListener {
        boolean onControlRelease(View view, Calendar calendar, boolean z);
    }

    public interface OnMonthChangeListener {
        void onMonthChange(View view, Calendar calendar);
    }

    public SignCalendarView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SignCalendarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignCalendarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.divide = 0;
        setOrientation(1);
        setWillNotDraw(false);
        this.mPaint = new Paint(1);
        this.mPaint.setColor(getResources().getColor(R.color.day_divide_line_color));
        this.divide = getResources().getDimensionPixelOffset(R.dimen.divide_height);
        this.mPaint.setStrokeWidth((float) (this.divide * 3));
        LayoutInflater.from(context).inflate(R.layout.bbs_sign_layout, this, true);
        ButterKnife.bind((View) this);
        this.currentDateTv.setText(getCurrentDateString(this.monthView.getCurrentCalendar()));
        setOnControlMonthChangeListener(new DefaultControlMonthChangeListener());
        this.nextMonthIv.setEnabled(false);
    }

    public void setCurrentDate(Calendar calendar) {
        if (this.monthView != null) {
            this.monthView.setCurrentDate(calendar);
        }
    }

    @OnClick({2131493825, 2131493747})
    public void onClick(View view) {
        if (this.monthView != null) {
            Calendar currentCalendar = this.monthView.getCurrentCalendar();
            if (view.getId() == R.id.pre_month_iv) {
                if (this.onControlMonthChangeListener.onControlRelease(view, currentCalendar, true)) {
                    currentCalendar.add(2, -1);
                }
            } else if (view.getId() == R.id.next_month_iv && this.onControlMonthChangeListener.onControlRelease(view, currentCalendar, false)) {
                currentCalendar.add(2, 1);
            }
            this.monthView.setCurrentDate(currentCalendar);
            this.currentDateTv.setText(getCurrentDateString(currentCalendar));
            handleBoundWay(currentCalendar);
            if (this.onMonthChangeListener != null) {
                this.onMonthChangeListener.onMonthChange(view, currentCalendar);
            }
        }
    }

    private void handleBoundWay(Calendar calendar) {
        this.nextMonthIv.setEnabled(!isEqual(calendar));
    }

    /* access modifiers changed from: private */
    public boolean isEqual(Calendar calendar) {
        Calendar instance = Calendar.getInstance();
        if (calendar.get(1) == instance.get(1) && calendar.get(2) == instance.get(2)) {
            return true;
        }
        return false;
    }

    private String getCurrentDateString(Calendar calendar) {
        return DateHelper.getCurrentDateString(getContext(), calendar);
    }

    public void setOnMonthChangeListener(OnMonthChangeListener onMonthChangeListener2) {
        this.onMonthChangeListener = onMonthChangeListener2;
    }

    public void setOnControlMonthChangeListener(OnControlMonthChangeListener onControlMonthChangeListener2) {
        this.onControlMonthChangeListener = onControlMonthChangeListener2;
    }

    public class DefaultControlMonthChangeListener implements OnControlMonthChangeListener {
        public DefaultControlMonthChangeListener() {
        }

        public boolean onControlRelease(View view, Calendar calendar, boolean z) {
            if (z) {
                return true;
            }
            return true ^ SignCalendarView.this.isEqual(calendar);
        }
    }

    public void setCurrentDate() {
        this.monthView.setCurrentDate(Calendar.getInstance());
    }

    public void setSignedData(String str) {
        this.monthView.setSignedData(str);
    }

    public void setSignedData(JSONArray jSONArray) {
        this.monthView.setSignedData(jSONArray);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int height = getHeight();
        int width = getWidth();
        canvas.save();
        float f = (float) width;
        Canvas canvas2 = canvas;
        float f2 = f;
        canvas2.drawLine(0.0f, 0.0f, f2, 0.0f, this.mPaint);
        float f3 = (float) height;
        float f4 = f3;
        canvas2.drawLine(0.0f, f3, f2, f4, this.mPaint);
        canvas.drawLine(0.0f, 0.0f, 0.0f, f3, this.mPaint);
        canvas2.drawLine(f, 0.0f, f2, f4, this.mPaint);
        canvas.restore();
    }
}
