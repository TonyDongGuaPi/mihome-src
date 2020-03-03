package com.mi.global.bbs.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.mi.global.bbs.R;
import java.util.Calendar;

public class DayView extends LinearLayout {
    private boolean isToday;
    private Calendar mCalendar;
    private ImageView mDayIconView = ((ImageView) findViewById(R.id.feel_icon));
    private TextView mDayTextView = ((TextView) findViewById(R.id.day));

    public DayView(Context context) {
        super(context);
        setOrientation(1);
        setGravity(1);
        LayoutInflater.from(context).inflate(R.layout.bbs_day_view, this, true);
    }

    public void setTextSize(float f) {
        this.mDayTextView.setTextSize(f);
    }

    public void setTextColor(int i) {
        this.mDayTextView.setTextColor(i);
    }

    public void setText(CharSequence charSequence) {
        this.mDayTextView.setText(charSequence);
        this.mDayIconView.setVisibility(0);
        setTag(charSequence);
        if (this.mCalendar == null) {
            return;
        }
        if (isSameDate(Calendar.getInstance(), this.mCalendar)) {
            this.mDayTextView.setTextColor(getResources().getColor(R.color.today_text_color));
            setBackgroundColor(getResources().getColor(R.color.today_bg_color));
            this.isToday = true;
            return;
        }
        this.isToday = false;
    }

    public void setIconSource(int i) {
        this.mDayIconView.setImageResource(i);
    }

    public void setCurrentState(String str) {
        Glide.a((View) this.mDayIconView).a(str).b((BaseRequestOptions<?>) ((RequestOptions) new RequestOptions().k()).a(R.drawable.bbs_feel_icon)).a(this.mDayIconView);
    }

    public void clear() {
        this.mDayIconView.setImageResource(R.drawable.bbs_feel_icon);
        this.mDayIconView.setVisibility(4);
        this.mDayTextView.setText("");
        this.mDayTextView.setTextColor(getResources().getColor(R.color.day_text_color));
        setBackgroundColor(getResources().getColor(R.color.white));
        this.mCalendar = null;
        setTag((Object) null);
        this.isToday = false;
    }

    private boolean isSameDate(Calendar calendar, Calendar calendar2) {
        if (calendar.get(2) == calendar2.get(2) && calendar.get(1) == calendar2.get(1) && calendar.get(5) == calendar2.get(5)) {
            return true;
        }
        return false;
    }

    public Calendar getCalendar() {
        return this.mCalendar;
    }

    public void setCalendar(Calendar calendar) {
        this.mCalendar = calendar;
    }

    public boolean isToday() {
        return this.isToday;
    }
}
