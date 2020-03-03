package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.camera;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.fastvideo.VideoView;
import java.util.HashMap;
import java.util.Map;

public class RNVideoViewEx extends FrameLayout {
    public static final String TAG = "RNVideoView";

    /* renamed from: a  reason: collision with root package name */
    private static int f17609a;
    private static Map<String, Integer> b = new HashMap();
    private boolean c;
    private boolean d;
    private VideoView.IVideoViewListener e;
    private boolean f;
    private float g;
    private float h;
    private XmVideoViewGl i;
    private boolean j;
    private boolean k = false;
    private String l;
    Runnable measureLayout = new Runnable() {
        public void run() {
            RNVideoViewEx.this.measure(View.MeasureSpec.makeMeasureSpec(RNVideoViewEx.this.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(RNVideoViewEx.this.getHeight(), 1073741824));
            RNVideoViewEx.this.layout(RNVideoViewEx.this.getLeft(), RNVideoViewEx.this.getTop(), RNVideoViewEx.this.getRight(), RNVideoViewEx.this.getBottom());
        }
    };

    public void setVideoFrameSize(int i2, int i3) {
    }

    public RNVideoViewEx(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundColor(Color.parseColor("#000000"));
    }

    public RNVideoViewEx(Context context) {
        super(context);
        setBackgroundColor(Color.parseColor("#000000"));
    }

    public void setDid(String str) {
        this.l = str;
        if (getWindowToken() != null) {
            Integer num = b.get(this.l);
            if (num == null || num.intValue() == 0) {
                num = 1;
                b.put(this.l, num);
            }
            b.put(this.l, num);
        }
    }

    public void initInVideoView(boolean z, int i2) {
        if (this.i == null) {
            this.i = XmPluginHostApi.instance().createVideoViewOnFront(getContext(), this, z, i2);
            this.i.initial();
            this.i.clearQueue();
            this.i.setIsFull(false);
        }
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
    }

    public void requestLayout() {
        super.requestLayout();
        post(this.measureLayout);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode == 1073741824 && mode2 == 1073741824 && this.i != null) {
            this.i.setWidth(size);
            this.i.setHeight(size2);
        }
        super.onMeasure(i2, i3);
    }

    public void disableTouch() {
        this.f = true;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Integer num = b.get(this.l);
        if (num == null) {
            num = 0;
            b.put(this.l, num);
        }
        Integer valueOf = Integer.valueOf(num.intValue() + 1);
        LogUtil.a("rn camera", "attached to window view count:" + valueOf);
        b.put(this.l, valueOf);
    }

    public void setVideoFrameSize(int i2, int i3, boolean z) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(i2, i3);
        }
        layoutParams.width = i2;
        layoutParams.height = i3;
        setLayoutParams(layoutParams);
    }

    public void setDistort(float f2, float f3, float f4) {
        if (this.i != null) {
            this.i.setDistort(f2, f3, f4);
        }
    }

    public void setScale(float f2) {
        if (this.i != null) {
            this.i.setScale(f2, false);
        }
    }

    public void releaseSelf() {
        if (this.i != null) {
            Integer num = b.get(this.l);
            LogUtil.a("rn camera", "begin to release, current view count:" + num);
            if (num != null && num.intValue() == 1) {
                this.i.releaseOnlySelf();
                LogUtil.a("rn camera", "do release view");
                this.i = null;
            }
        }
    }

    public void release() {
        if (this.i != null) {
            this.i.release();
            this.i = null;
        }
    }

    public void drawVideoFrame(VideoFrame videoFrame) {
        if (this.k && this.i != null) {
            this.i.drawVideoFrame(videoFrame);
        }
    }

    public void setFullscreenState(boolean z) {
        Context context;
        this.j = z;
        if (this.i != null) {
            this.i.setIsFullForRN(z);
            if (z && (context = getContext()) != null && (context instanceof Activity)) {
                ((Activity) context).getWindow().clearFlags(2048);
            }
        }
    }

    public void startRender() {
        this.k = true;
        if (this.i != null) {
            this.i.onResume();
        }
    }

    public void stopRender() {
        this.k = false;
        if (this.i != null) {
            this.i.onPause();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Integer num = b.get(this.l);
        LogUtil.a("rn camera", "deattached from window view count:" + num);
        if (num != null && num.intValue() != 0) {
            if (num.intValue() == 1) {
                RNCameraManager.a().a(this.l);
                b.remove(this.l);
                return;
            }
            b.put(this.l, Integer.valueOf(num.intValue() - 1));
            if (this.i != null) {
                this.i.releaseOnlySelf();
            }
        }
    }
}
