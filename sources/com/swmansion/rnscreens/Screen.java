package com.swmansion.rnscreens;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.UIManagerModule;

public class Screen extends ViewGroup implements ReactPointerEventsView {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static View.OnAttachStateChangeListener f8945a = new View.OnAttachStateChangeListener() {
        public void onViewDetachedFromWindow(View view) {
        }

        public void onViewAttachedToWindow(View view) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
            view.removeOnAttachStateChangeListener(Screen.f8945a);
        }
    };
    @Nullable
    private Fragment b;
    @Nullable
    private ScreenContainer c;
    private boolean d;
    private boolean e;
    private StackPresentation f = StackPresentation.PUSH;
    private StackAnimation g = StackAnimation.DEFAULT;

    public enum StackAnimation {
        DEFAULT,
        NONE,
        FADE
    }

    public enum StackPresentation {
        PUSH,
        MODAL,
        TRANSPARENT_MODAL
    }

    public void setLayerType(int i, @Nullable Paint paint) {
    }

    public Screen(ReactContext reactContext) {
        super(reactContext);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            final int i5 = i3 - i;
            final int i6 = i4 - i2;
            ReactContext reactContext = (ReactContext) getContext();
            final ReactContext reactContext2 = reactContext;
            reactContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactContext) {
                public void runGuarded() {
                    ((UIManagerModule) reactContext2.getNativeModule(UIManagerModule.class)).updateNodeSize(Screen.this.getId(), i5, i6);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearDisappearingChildren();
    }

    /* access modifiers changed from: protected */
    @RequiresApi(api = 21)
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        View focusedChild = getFocusedChild();
        if (focusedChild != null) {
            while (focusedChild instanceof ViewGroup) {
                focusedChild = ((ViewGroup) focusedChild).getFocusedChild();
            }
            if (focusedChild instanceof TextView) {
                TextView textView = (TextView) focusedChild;
                if (textView.getShowSoftInputOnFocus()) {
                    textView.addOnAttachStateChangeListener(f8945a);
                }
            }
        }
    }

    public void setTransitioning(boolean z) {
        if (this.e != z) {
            this.e = z;
            super.setLayerType(z ? 2 : 0, (Paint) null);
        }
    }

    public void setStackPresentation(StackPresentation stackPresentation) {
        this.f = stackPresentation;
    }

    public void setStackAnimation(StackAnimation stackAnimation) {
        this.g = stackAnimation;
    }

    public StackAnimation getStackAnimation() {
        return this.g;
    }

    public StackPresentation getStackPresentation() {
        return this.f;
    }

    public PointerEvents getPointerEvents() {
        return this.e ? PointerEvents.NONE : PointerEvents.AUTO;
    }

    /* access modifiers changed from: protected */
    public void setContainer(@Nullable ScreenContainer screenContainer) {
        this.c = screenContainer;
    }

    /* access modifiers changed from: protected */
    public void setFragment(Fragment fragment) {
        this.b = fragment;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Fragment getFragment() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public ScreenContainer getContainer() {
        return this.c;
    }

    public void setActive(boolean z) {
        if (z != this.d) {
            this.d = z;
            if (this.c != null) {
                this.c.notifyChildUpdate();
            }
        }
    }

    public boolean isActive() {
        return this.d;
    }
}
