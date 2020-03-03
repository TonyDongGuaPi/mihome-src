package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.views.view.ReactViewGroup;

public class RNGestureHandlerRootView extends ReactViewGroup {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private RNGestureHandlerRootHelper f8894a;

    public RNGestureHandlerRootView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f8894a == null) {
            this.f8894a = new RNGestureHandlerRootHelper((ReactContext) getContext(), this);
        }
    }

    public void tearDown() {
        if (this.f8894a != null) {
            this.f8894a.a();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (((RNGestureHandlerRootHelper) Assertions.assertNotNull(this.f8894a)).a(motionEvent)) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        ((RNGestureHandlerRootHelper) Assertions.assertNotNull(this.f8894a)).a(z);
        super.requestDisallowInterceptTouchEvent(z);
    }
}
