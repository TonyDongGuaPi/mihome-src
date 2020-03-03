package com.xiaomi.smarthome.library.common.widget.slidinglayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.slidinglayer.transformer.SlideJoyTransformer;

public class SceneRecommendSlidingLayer extends RelativeLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public SlidingLayer f19069a;

    public SceneRecommendSlidingLayer(Context context) {
        super(context);
        a();
    }

    public SceneRecommendSlidingLayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public SceneRecommendSlidingLayer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        View inflate = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.scene_recommend_sliding_layer_layout, this, false);
        addView(inflate);
        this.f19069a = (SlidingLayer) inflate.findViewById(R.id.sliding_layer);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f19069a.getLayoutParams();
        layoutParams.addRule(15);
        this.f19069a.setLayoutParams(layoutParams);
        this.f19069a.setStickTo(-1);
        this.f19069a.setLayerTransformer(new SlideJoyTransformer());
        this.f19069a.setOffsetDistance(DisplayUtils.a(50.0f));
        this.f19069a.closeLayer(false);
        this.f19069a.setChangeStateOnTap(false);
        this.f19069a.findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SceneRecommendSlidingLayer.this.f19069a.isOpened()) {
                    SceneRecommendSlidingLayer.this.f19069a.closeLayer(true);
                    SceneRecommendSlidingLayer.this.setClickable(false);
                    return;
                }
                SceneRecommendSlidingLayer.this.f19069a.openLayer(true);
                SceneRecommendSlidingLayer.this.setClickable(true);
            }
        });
        setClickable(false);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) == 1 && this.f19069a.isOpened()) {
            this.f19069a.closeLayer(true);
            setClickable(false);
        }
        return this.f19069a.isOpened();
    }
}
