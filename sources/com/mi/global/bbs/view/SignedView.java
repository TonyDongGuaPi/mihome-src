package com.mi.global.bbs.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.SignCalendarView;
import java.util.Calendar;
import org.json.JSONArray;

public class SignedView extends LinearLayout implements SignCalendarView.OnMonthChangeListener {
    private SignCalendarView.OnMonthChangeListener onMonthChangeListener;
    @BindView(2131493996)
    SignCalendarView signedCalendarView;
    @BindView(2131493997)
    ImageView signedFeelIv;
    @BindView(2131493998)
    TextView signedPointsTv;
    @BindView(2131493999)
    TextView signedSameTv;

    public SignedView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SignedView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignedView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setGravity(1);
        setBackgroundColor(-1);
        setOrientation(1);
        LayoutInflater.from(context).inflate(R.layout.bbs_signed_layout, this, true);
        ButterKnife.bind((View) this);
        this.signedCalendarView.setOnMonthChangeListener(this);
    }

    public void setOnMonthChangeListener(SignCalendarView.OnMonthChangeListener onMonthChangeListener2) {
        this.onMonthChangeListener = onMonthChangeListener2;
    }

    public void onMonthChange(View view, Calendar calendar) {
        if (this.onMonthChangeListener != null) {
            this.onMonthChangeListener.onMonthChange(view, calendar);
        }
    }

    public void setStatText(String str, int i) {
        this.signedSameTv.setText(getResources().getString(R.string.feel_same, new Object[]{str, getStateString(i)}));
    }

    private String getStateString(int i) {
        switch (i) {
            case 1:
                return getResources().getString(R.string.awesome);
            case 2:
                return getResources().getString(R.string.happy);
            case 3:
                return getResources().getString(R.string.sad);
            default:
                return getResources().getString(R.string.happy);
        }
    }

    public void setStateIcon(int i) {
        switch (i) {
            case 1:
                this.signedFeelIv.setImageResource(R.drawable.happy_big);
                return;
            case 2:
                this.signedFeelIv.setImageResource(R.drawable.regular_big);
                return;
            case 3:
                this.signedFeelIv.setImageResource(R.drawable.sad_big);
                return;
            default:
                this.signedFeelIv.setImageResource(R.drawable.happy_big);
                return;
        }
    }

    public void setCurrentDate() {
        this.signedCalendarView.setCurrentDate(Calendar.getInstance());
    }

    public void setSignedData(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.signedCalendarView.setSignedData(str);
        }
    }

    public void setSignedData(JSONArray jSONArray) {
        this.signedCalendarView.setSignedData(jSONArray);
    }
}
