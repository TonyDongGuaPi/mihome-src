package com.youpin.weex.app.extend.component.fresco_image;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.facebook.drawee.view.SimpleDraweeView;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;

public class FrescoImageView extends SimpleDraweeView implements WXGestureObservable {
    private WXGesture wxGesture;

    public WXGesture getGestureListener() {
        return null;
    }

    public FrescoImageView(Context context) {
        super(context);
    }

    public FrescoImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FrescoImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public FrescoImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void registerGestureListener(WXGesture wXGesture) {
        this.wxGesture = wXGesture;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        return this.wxGesture != null ? onTouchEvent | this.wxGesture.onTouch(this, motionEvent) : onTouchEvent;
    }
}
