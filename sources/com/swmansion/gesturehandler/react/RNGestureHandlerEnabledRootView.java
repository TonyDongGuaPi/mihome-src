package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;

public class RNGestureHandlerEnabledRootView extends ReactRootView {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private ReactInstanceManager f8886a;
    @Nullable
    private RNGestureHandlerRootHelper b;

    public RNGestureHandlerEnabledRootView(Context context) {
        super(context);
    }

    public RNGestureHandlerEnabledRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (this.b != null) {
            this.b.a(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.b == null || !this.b.a(motionEvent)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    public void initialize() {
        if (this.b == null) {
            this.b = new RNGestureHandlerRootHelper(this.f8886a.getCurrentReactContext(), this);
            return;
        }
        throw new IllegalStateException("GestureHandler already initialized for root view " + this);
    }

    public void tearDown() {
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
    }

    public void startReactApplication(ReactInstanceManager reactInstanceManager, String str, @Nullable Bundle bundle) {
        super.startReactApplication(reactInstanceManager, str, bundle);
        this.f8886a = reactInstanceManager;
    }
}
