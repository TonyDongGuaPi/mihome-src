package com.xiaomi.yp_ui.widget.zoomable;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.xiaomi.youpin.log.LogUtils;

public class ZoomableDraweeViewSupport extends ZoomableDraweeView {
    private static final Class<?> TAG = ZoomableDraweeViewSupport.class;

    public ZoomableDraweeViewSupport(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context, genericDraweeHierarchy);
    }

    public ZoomableDraweeViewSupport(Context context) {
        super(context);
    }

    public ZoomableDraweeViewSupport(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ZoomableDraweeViewSupport(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public Class<?> getLogTag() {
        return TAG;
    }

    /* access modifiers changed from: protected */
    public ZoomableController createZoomableController() {
        AnimatedZoomableControllerSupport i = AnimatedZoomableControllerSupport.i();
        i.b(3.0f);
        i.a(0.8f);
        return i;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        } catch (Exception unused) {
            LogUtils.postCatchedException(new RuntimeException("Failed on drawing picture with url: " + getTag()));
        }
    }
}
