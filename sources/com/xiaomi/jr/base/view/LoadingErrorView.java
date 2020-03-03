package com.xiaomi.jr.base.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.jr.base.R;

public class LoadingErrorView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private TextView f10342a;
    private TextView b;
    private Button c;
    private ImageView d;
    private int e;

    public LoadingErrorView(Context context) {
        super(context);
        a(context);
    }

    public LoadingErrorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.e = super.getVisibility();
        View inflate = LayoutInflater.from(context).inflate(R.layout.loading_error_page, this, true);
        this.f10342a = (TextView) inflate.findViewById(R.id.error_page_msg);
        this.b = (TextView) inflate.findViewById(R.id.error_page_tip);
        this.c = (Button) inflate.findViewById(R.id.error_page_button);
        this.d = (ImageView) inflate.findViewById(R.id.error_page_image);
        animate().setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (LoadingErrorView.this.getAlpha() == 0.0f) {
                    LoadingErrorView.this.setVisibility(8);
                } else {
                    LoadingErrorView.this.setVisibility(0);
                }
            }
        });
    }

    public void startEnterAnimation() {
        if (getVisibility() != 0) {
            setVisibility(0);
        }
        setAlpha(0.0f);
        animate().cancel();
        animate().alpha(1.0f).setDuration(1000).start();
    }

    public void startExitAnimation() {
        if (getVisibility() != 8) {
            setAlpha(1.0f);
            animate().cancel();
            animate().alpha(0.0f).setDuration(1000).start();
        }
        this.e = 8;
    }

    public void setRetryButton(int i, View.OnClickListener onClickListener) {
        this.c.setText(i);
        this.c.setOnClickListener(onClickListener);
    }

    public void setRetryButtonVisibility(boolean z) {
        this.c.setVisibility(z ? 0 : 8);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        this.e = i;
    }

    public int getVisibility() {
        return this.e;
    }

    public void setMessage(String str) {
        this.f10342a.setText(str);
    }

    public void setTip(String str) {
        this.b.setText(str);
    }

    public void setErrorDrawable(int i) {
        this.d.setBackgroundResource(i);
    }
}
