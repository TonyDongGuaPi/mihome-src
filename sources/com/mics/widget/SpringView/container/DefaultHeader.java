package com.mics.widget.SpringView.container;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mics.R;

public class DefaultHeader extends BaseHeader {

    /* renamed from: a  reason: collision with root package name */
    private Context f7818a;
    private int b;
    private int c;
    private long d;
    private final int e;
    private RotateAnimation f;
    private RotateAnimation g;
    private TextView h;
    private TextView i;
    private ImageView j;
    private ProgressBar k;

    public void a(View view, int i2) {
    }

    public DefaultHeader(Context context) {
        this(context, R.drawable.mics_progress_small, R.drawable.mics_arrow);
    }

    public DefaultHeader(Context context, int i2, int i3) {
        this.e = 180;
        this.f7818a = context;
        this.b = i2;
        this.c = i3;
        this.f = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.f.setDuration(180);
        this.f.setFillAfter(true);
        this.g = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.g.setDuration(180);
        this.g.setFillAfter(true);
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.mics_default_header, viewGroup, true);
        this.h = (TextView) inflate.findViewById(R.id.default_header_title);
        this.i = (TextView) inflate.findViewById(R.id.default_header_time);
        this.j = (ImageView) inflate.findViewById(R.id.default_header_arrow);
        this.k = (ProgressBar) inflate.findViewById(R.id.default_header_progressbar);
        this.k.setIndeterminateDrawable(ContextCompat.getDrawable(this.f7818a, this.b));
        this.j.setImageResource(this.c);
        return inflate;
    }

    public void d(View view) {
        if (this.d == 0) {
            this.d = System.currentTimeMillis();
            return;
        }
        int currentTimeMillis = (int) (((System.currentTimeMillis() - this.d) / 1000) / 60);
        if (currentTimeMillis >= 1 && currentTimeMillis < 60) {
            TextView textView = this.i;
            textView.setText(currentTimeMillis + "分钟前");
        } else if (currentTimeMillis >= 60) {
            TextView textView2 = this.i;
            textView2.setText((currentTimeMillis / 60) + "小时前");
        } else if (currentTimeMillis > 1440) {
            TextView textView3 = this.i;
            textView3.setText((currentTimeMillis / 1440) + "天前");
        } else if (currentTimeMillis == 0) {
            this.i.setText("刚刚");
        }
    }

    public void a(View view, boolean z) {
        if (!z) {
            this.h.setText("松开刷新数据");
            if (this.j.getVisibility() == 0) {
                this.j.startAnimation(this.f);
                return;
            }
            return;
        }
        this.h.setText("下拉刷新");
        if (this.j.getVisibility() == 0) {
            this.j.startAnimation(this.g);
        }
    }

    public void a() {
        this.d = System.currentTimeMillis();
        this.h.setText("下拉刷新");
        this.j.setVisibility(4);
        this.j.clearAnimation();
        this.k.setVisibility(0);
    }

    public void b() {
        this.j.setVisibility(0);
        this.k.setVisibility(4);
    }
}
