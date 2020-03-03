package com.xiaomi.smarthome.mibrain.viewutil;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;

public class MiBrainExecutingView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10717a = 800;
    GenericDraweeHierarchy hierarchy;
    Context mContext;
    RadarLayout mRadarLayout;
    RadarLayout mRingLayout;
    SimpleDraweeView mSimpDraweeView;

    public MiBrainExecutingView(Context context) {
        super(context);
        this.mContext = context;
    }

    public MiBrainExecutingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public MiBrainExecutingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mSimpDraweeView = (SimpleDraweeView) findViewById(R.id.mi_brain_executing_simple_dv);
        this.mRadarLayout = (RadarLayout) findViewById(R.id.mi_brain_executing_radar_layout);
        this.mRingLayout = (RadarLayout) findViewById(R.id.mi_brain_executing_radar_layout_ring);
        a();
    }

    private void a() {
        this.hierarchy = new GenericDraweeHierarchyBuilder(this.mRadarLayout.getResources()).setFadeDuration(200).setPlaceholderImage(this.mRadarLayout.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build();
        this.mSimpDraweeView.setHierarchy(this.hierarchy);
        this.mRadarLayout.setCount(1);
        this.mRadarLayout.setColor(getResources().getColor(R.color.mi_brain_executing_circle_view_green));
        this.mRadarLayout.setRepeat(0);
        this.mRadarLayout.setDuration(800);
        this.mRadarLayout.setUseRing(false);
        this.mRadarLayout.setScale(0.95f, 1.0f);
        this.mRadarLayout.setRepeat(100);
        this.mRingLayout.setCount(1);
        this.mRingLayout.setColor(getResources().getColor(R.color.mi_brain_circle_view_green));
        this.mRingLayout.setRepeat(0);
        this.mRingLayout.setDuration(800);
        this.mRingLayout.setUseRing(true);
        this.mRingLayout.setScale(0.5f, 1.0f);
        this.mRingLayout.setRepeat(100);
        this.mRingLayout.setAlpha(1.0f, 0.0f);
    }

    public void init(String str) {
        this.mSimpDraweeView.setHierarchy(this.hierarchy);
        if (!TextUtils.isEmpty(str)) {
            this.mSimpDraweeView.setImageURI(Uri.parse(str));
        }
        this.mRadarLayout.start();
        this.mRingLayout.start();
    }

    public void stop() {
        this.mRadarLayout.stop();
        this.mRingLayout.stop();
    }
}
