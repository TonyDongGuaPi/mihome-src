package com.andview.refreshview;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.andview.refreshview.callback.IHeaderCallBack;
import com.andview.refreshview.utils.Utils;
import java.util.Calendar;

public class XRefreshViewHeader extends LinearLayout implements IHeaderCallBack {

    /* renamed from: a  reason: collision with root package name */
    private ViewGroup f4775a;
    private ImageView b;
    private ImageView c;
    private ProgressBar d;
    private TextView e;
    private TextView f;
    private Animation g;
    private Animation h;
    private final int i = 180;

    public void onHeaderMove(double d2, int i2, int i3) {
    }

    public XRefreshViewHeader(Context context) {
        super(context);
        a(context);
    }

    public XRefreshViewHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.f4775a = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.xrefreshview_header, this);
        this.b = (ImageView) findViewById(R.id.xrefreshview_header_arrow);
        this.c = (ImageView) findViewById(R.id.xrefreshview_header_ok);
        this.e = (TextView) findViewById(R.id.xrefreshview_header_hint_textview);
        this.f = (TextView) findViewById(R.id.xrefreshview_header_time);
        this.d = (ProgressBar) findViewById(R.id.xrefreshview_header_progressbar);
        this.g = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.g.setDuration(180);
        this.g.setFillAfter(true);
        this.h = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.h.setDuration(0);
        this.h.setFillAfter(true);
    }

    public void setRefreshTime(long j) {
        String str;
        int timeInMillis = (int) (((Calendar.getInstance().getTimeInMillis() - j) / 1000) / 60);
        Resources resources = getContext().getResources();
        if (timeInMillis < 1) {
            str = resources.getString(R.string.xrefreshview_refresh_justnow);
        } else if (timeInMillis < 60) {
            str = Utils.a(resources.getString(R.string.xrefreshview_refresh_minutes_ago), timeInMillis);
        } else if (timeInMillis < 1440) {
            str = Utils.a(resources.getString(R.string.xrefreshview_refresh_hours_ago), timeInMillis / 60);
        } else {
            str = Utils.a(resources.getString(R.string.xrefreshview_refresh_days_ago), (timeInMillis / 60) / 24);
        }
        this.f.setText(str);
    }

    public void hide() {
        setVisibility(8);
    }

    public void show() {
        setVisibility(0);
    }

    public void onStateNormal() {
        this.d.setVisibility(8);
        this.b.setVisibility(0);
        this.c.setVisibility(8);
        this.b.startAnimation(this.h);
        this.e.setText(R.string.xrefreshview_header_hint_normal);
    }

    public void onStateReady() {
        this.d.setVisibility(8);
        this.c.setVisibility(8);
        this.b.setVisibility(0);
        this.b.clearAnimation();
        this.b.startAnimation(this.g);
        this.e.setText(R.string.xrefreshview_header_hint_ready);
        this.f.setVisibility(0);
    }

    public void onStateRefreshing() {
        this.b.clearAnimation();
        this.b.setVisibility(8);
        this.c.setVisibility(8);
        this.d.setVisibility(0);
        this.e.setText(R.string.xrefreshview_header_hint_loading);
    }

    public void onStateFinish(boolean z) {
        this.b.setVisibility(8);
        this.c.setVisibility(0);
        this.d.setVisibility(8);
        this.e.setText(z ? R.string.xrefreshview_header_hint_loaded : R.string.xrefreshview_header_hint_loaded_fail);
        this.f.setVisibility(8);
    }

    public int getHeaderHeight() {
        return getMeasuredHeight();
    }
}
