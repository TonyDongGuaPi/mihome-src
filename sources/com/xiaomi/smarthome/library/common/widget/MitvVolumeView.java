package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.R;

public class MitvVolumeView extends FrameLayout {
    View mVolumeAddView;
    Animation mVolumeAddViewAnim;
    Animation mVolumeCirceViewAnim;
    View mVolumeCircleView;
    View mVolumeSubView;
    Animation mVolumeSubViewAnim;
    View mVolumeView;

    public MitvVolumeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mVolumeCirceViewAnim = AnimationUtils.loadAnimation(getContext(), R.anim.mitv_volume_circle);
        this.mVolumeAddViewAnim = AnimationUtils.loadAnimation(getContext(), R.anim.mitv_volume_add);
        this.mVolumeSubViewAnim = AnimationUtils.loadAnimation(getContext(), R.anim.mitv_volume_sub);
        this.mVolumeAddView = findViewById(R.id.mitv_volume_add);
        this.mVolumeSubView = findViewById(R.id.mitv_volume_sub);
        this.mVolumeCircleView = findViewById(R.id.mitv_volume_circle);
        this.mVolumeView = findViewById(R.id.mitv_volume);
    }

    public void show(int i, int i2) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mVolumeView.getLayoutParams();
        layoutParams.setMargins(i - (this.mVolumeView.getWidth() / 2), i2 - (this.mVolumeView.getHeight() / 2), 0, 0);
        this.mVolumeView.setLayoutParams(layoutParams);
        setVisibility(0);
        this.mVolumeCircleView.startAnimation(this.mVolumeCirceViewAnim);
        this.mVolumeAddView.startAnimation(this.mVolumeAddViewAnim);
        this.mVolumeSubView.startAnimation(this.mVolumeSubViewAnim);
    }

    public boolean isVolumeAdd(int i, int i2) {
        Rect rect = new Rect();
        this.mVolumeAddView.getGlobalVisibleRect(rect);
        rect.inset(-100, -100);
        return rect.contains(i, i2);
    }

    public boolean isVolumeSub(int i, int i2) {
        Rect rect = new Rect();
        this.mVolumeSubView.getGlobalVisibleRect(rect);
        rect.inset(-100, -100);
        return rect.contains(i, i2);
    }

    public void hidden() {
        setVisibility(4);
    }
}
