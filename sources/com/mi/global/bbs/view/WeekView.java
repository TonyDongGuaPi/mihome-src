package com.mi.global.bbs.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.Editor.FontTextView;

public class WeekView extends LinearLayout {
    private String[] weeks;

    public WeekView(Context context) {
        this(context, (AttributeSet) null);
    }

    public WeekView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WeekView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOrientation(0);
        this.weeks = getResources().getStringArray(R.array.weeks);
        addWeekView(new LinearLayout.LayoutParams(0, -1, 1.0f));
    }

    private void addWeekView(LinearLayout.LayoutParams layoutParams) {
        for (String text : this.weeks) {
            FontTextView fontTextView = new FontTextView(getContext());
            fontTextView.setText(text);
            fontTextView.setTextSize(2, 13.0f);
            fontTextView.setTextColor(getResources().getColor(R.color.main_tab_normal_color));
            fontTextView.setGravity(17);
            addView(fontTextView, layoutParams);
        }
    }
}
