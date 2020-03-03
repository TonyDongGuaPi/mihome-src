package com.mi.global.bbs.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.SignHomeData;
import com.mi.global.bbs.utils.Utils;
import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MonthView extends LinearLayout {
    private int childCount;
    HashMap<Integer, String> emoHash;
    private Calendar mCurrentCalendar;
    private DayView[][] mDayViewArrays;
    private Paint mPaint;
    private Calendar mPreCalendar;
    private Point todayPoint;

    public MonthView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MonthView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MonthView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.childCount = 0;
        setWillNotDraw(false);
        setOrientation(1);
        this.mPaint = new Paint(1);
        this.mPaint.setColor(getResources().getColor(R.color.day_divide_line_color));
        this.mPaint.setStrokeWidth((float) getResources().getDimensionPixelOffset(R.dimen.divide_height));
        this.todayPoint = new Point();
        addChildView(6, 7);
        setCurrentDate(Calendar.getInstance(Locale.getDefault()));
        String stringPref = Utils.Preference.getStringPref(context, "emo", "");
        if (!TextUtils.isEmpty(stringPref)) {
            try {
                List<SignHomeData.SignBean.EmotionBean> list = (List) new Gson().fromJson(stringPref, new TypeToken<List<SignHomeData.SignBean.EmotionBean>>() {
                }.getType());
                if (list != null) {
                    this.emoHash = new HashMap<>();
                    for (SignHomeData.SignBean.EmotionBean emotionBean : list) {
                        this.emoHash.put(Integer.valueOf(emotionBean.id), emotionBean.enableurl);
                    }
                }
            } catch (JsonSyntaxException unused) {
            }
        }
    }

    private static Calendar getCalendarForLocale(Calendar calendar, Locale locale) {
        if (calendar == null) {
            return Calendar.getInstance(locale);
        }
        long timeInMillis = calendar.getTimeInMillis();
        Calendar instance = Calendar.getInstance(locale);
        instance.setTimeInMillis(timeInMillis);
        return instance;
    }

    private void addChildView(int i, int i2) {
        this.mDayViewArrays = (DayView[][]) Array.newInstance(DayView.class, new int[]{i, i2});
        for (int i3 = 1; i3 <= i; i3++) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            for (int i4 = 1; i4 <= i2; i4++) {
                DayView dayView = new DayView(getContext());
                this.mDayViewArrays[i3 - 1][i4 - 1] = dayView;
                linearLayout.addView(dayView, new LinearLayout.LayoutParams(0, -2, 1.0f));
            }
            addView(linearLayout, new LinearLayout.LayoutParams(-1, -2));
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
        int width = getWidth();
        int height = getHeight();
        for (int i = 1; i < this.childCount; i++) {
            float top = (float) getChildAt(i).getTop();
            canvas.drawLine(0.0f, top, (float) width, top, this.mPaint);
        }
        LinearLayout linearLayout = (LinearLayout) getChildAt(0);
        for (int i2 = 1; i2 < 7; i2++) {
            float left = (float) linearLayout.getChildAt(i2).getLeft();
            canvas.drawLine(left, 0.0f, left, (float) height, this.mPaint);
        }
        canvas.drawLine(0.0f, 0.0f, (float) width, 0.0f, this.mPaint);
        if (hasTodayPoint()) {
            this.mPaint.setColor(getResources().getColor(R.color.today_divide_line_color));
            this.mPaint.setStyle(Paint.Style.STROKE);
            int left2 = linearLayout.getChildAt(this.todayPoint.y).getLeft();
            int right = linearLayout.getChildAt(this.todayPoint.y).getRight();
            Canvas canvas2 = canvas;
            canvas2.drawRect((float) left2, (float) getChildAt(this.todayPoint.x).getTop(), (float) right, (float) getChildAt(this.todayPoint.x).getBottom(), this.mPaint);
            this.mPaint.setColor(getResources().getColor(R.color.day_divide_line_color));
        }
        canvas.restore();
    }

    private boolean hasTodayPoint() {
        return (this.todayPoint.x == 0 || this.todayPoint.y == 0) ? false : true;
    }

    public void setCurrentDate(Calendar calendar) {
        if (this.mPreCalendar != null) {
            clear();
        }
        this.mCurrentCalendar = calendar;
        this.mPreCalendar = calendar;
        calendar.set(calendar.get(1), calendar.get(2), 1);
        setChildrenDayText(calendar.get(7), DateHelper.getDaysInMonth(calendar.get(2), calendar.get(1)));
        invalidate();
    }

    private void setChildrenDayText(int i, int i2) {
        int i3;
        int i4;
        int i5 = 0;
        this.todayPoint.set(0, 0);
        int i6 = i - 1;
        boolean z = true;
        int i7 = 1;
        while (true) {
            i3 = i2 + i;
            i4 = 5;
            if (i6 >= i3 - 1) {
                break;
            }
            int i8 = i6 / 7;
            int i9 = i6 % 7;
            DayView dayView = this.mDayViewArrays[i8][i9];
            dayView.setCalendar(this.mCurrentCalendar);
            int i10 = i7 + 1;
            dayView.setText(String.valueOf(i7));
            if (dayView.isToday()) {
                this.todayPoint.set(i8, i9);
            }
            this.mCurrentCalendar.roll(5, true);
            i6++;
            i7 = i10;
        }
        if ((i3 - 2) / 7 == 5) {
            z = false;
        }
        View childAt = getChildAt(5);
        if (z) {
            i5 = 8;
        }
        childAt.setVisibility(i5);
        if (!z) {
            i4 = 6;
        }
        this.childCount = i4;
    }

    private void clear() {
        for (DayView[] dayViewArr : this.mDayViewArrays) {
            for (DayView clear : this.mDayViewArrays[r2]) {
                clear.clear();
            }
        }
    }

    public Calendar getCurrentCalendar() {
        return this.mCurrentCalendar;
    }

    public void setSignedData(String str) {
        try {
            setSignedData(new JSONObject(str).optJSONArray("emotions"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setSignedData(JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                int length = jSONArray.length();
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            String next = optJSONObject.keys().next();
                            DayView dayView = (DayView) findViewWithTag(next);
                            if (dayView != null) {
                                int i2 = optJSONObject.getInt(next);
                                if (this.emoHash != null) {
                                    dayView.setCurrentState(this.emoHash.get(Integer.valueOf(i2)));
                                }
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
