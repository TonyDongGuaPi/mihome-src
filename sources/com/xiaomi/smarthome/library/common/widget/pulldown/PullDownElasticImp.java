package com.xiaomi.smarthome.library.common.widget.pulldown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class PullDownElasticImp implements IPullDownElastic {
    private View e;
    private ImageView f;
    private int g;
    private ProgressBar h;
    private TextView i;
    private Context j;

    public void a(int i2, boolean z) {
    }

    public void b(String str) {
    }

    public void c(int i2) {
    }

    public PullDownElasticImp(Context context) {
        this.j = context;
        d();
    }

    private void d() {
        this.e = LayoutInflater.from(this.j).inflate(R.layout.pulldown_refresh_item, (ViewGroup) null);
        this.f = (ImageView) this.e.findViewById(R.id.head_arrowImageView);
        this.h = (ProgressBar) this.e.findViewById(R.id.head_progressBar);
        this.i = (TextView) this.e.findViewById(R.id.refresh_hint);
        this.g = DisplayUtils.d(this.j, 50.0f);
    }

    public View a() {
        return this.e;
    }

    public int b() {
        return this.g;
    }

    public void a(int i2) {
        this.f.setVisibility(i2);
    }

    public void a(Animation animation) {
        this.f.startAnimation(animation);
    }

    public void c() {
        this.f.clearAnimation();
    }

    public void b(int i2) {
        this.h.setVisibility(i2);
    }

    public void a(String str) {
        this.i.setText(str);
    }
}
