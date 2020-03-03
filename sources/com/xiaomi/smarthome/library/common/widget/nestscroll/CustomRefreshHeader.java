package com.xiaomi.smarthome.library.common.widget.nestscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.nestscroll.PullHeaderLayout;

public class CustomRefreshHeader extends FrameLayout implements RefreshHeader {

    /* renamed from: a  reason: collision with root package name */
    private Animation f19023a;
    private Animation b;
    private Animation c;
    private TextView d;
    private View e;
    private View f;
    private View g;
    private RefreshHeader h;
    private boolean i;

    public CustomRefreshHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public CustomRefreshHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = true;
        this.f19023a = AnimationUtils.loadAnimation(context, R.anim.rotate_180);
        this.b = AnimationUtils.loadAnimation(context, R.anim.rotate_back_180);
        this.c = AnimationUtils.loadAnimation(context, R.anim.rotate_infinite);
        inflate(context, R.layout.pull_to_refresh_header_nestscrolling, this);
        this.d = (TextView) findViewById(R.id.text);
        this.e = findViewById(R.id.arrowIcon);
        this.f = findViewById(R.id.successIcon);
        this.g = findViewById(R.id.loadingIcon);
    }

    public void setRefreshHeader(RefreshHeader refreshHeader) {
        this.h = refreshHeader;
    }

    public void reset() {
        this.d.setText(getResources().getText(R.string.pull_down_refresh));
        this.d.setVisibility(8);
        this.f.setVisibility(8);
        this.e.setVisibility(8);
        this.e.clearAnimation();
        this.g.setVisibility(8);
        this.g.clearAnimation();
        setVisibility(8);
        if (this.h != null) {
            this.h.reset();
        }
    }

    public void pull() {
        this.d.setVisibility(8);
        this.e.setVisibility(8);
        this.g.setVisibility(8);
        if (this.h != null) {
            this.h.pull();
        }
    }

    public void refreshing() {
        if (this.i) {
            this.i = false;
            this.d.setVisibility(8);
            this.e.setVisibility(8);
            this.g.setVisibility(8);
            if (this.h != null) {
                this.h.refreshing();
                return;
            }
            return;
        }
        this.d.setVisibility(8);
        this.e.setVisibility(8);
        this.g.setVisibility(8);
        this.d.setText(getResources().getText(R.string.refreshing));
        if (this.h != null) {
            this.h.refreshing();
        }
    }

    public void onPositionChange(float f2, float f3, float f4, boolean z, PullHeaderLayout.State state) {
        if (f2 >= f4 || f3 < f4) {
            if (f2 > f4 && f3 <= f4 && z && state == PullHeaderLayout.State.PULL) {
                this.d.setText(getResources().getText(R.string.release_to_refresh));
                this.e.clearAnimation();
            }
        } else if (z && state == PullHeaderLayout.State.PULL) {
            this.d.setText(getResources().getText(R.string.pull_down_refresh));
            this.e.clearAnimation();
        }
        if (z && state == PullHeaderLayout.State.PULL) {
            this.e.setRotation(f2);
        }
        if (this.h != null) {
            this.h.onPositionChange(f2, f3, f4, z, state);
        }
    }

    public void complete() {
        this.e.clearAnimation();
        this.f.setVisibility(8);
        this.e.setVisibility(8);
        this.d.setVisibility(8);
        this.d.setText(getResources().getText(R.string.refresh_complete));
        if (this.h != null) {
            this.h.complete();
        }
    }
}
