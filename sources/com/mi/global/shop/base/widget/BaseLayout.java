package com.mi.global.shop.base.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import java.lang.ref.WeakReference;

public class BaseLayout extends RelativeLayout {
    protected WeakReference<Activity> mActivityRef;
    protected WeakReference<BackKeyListener> mListener;

    public interface BackKeyListener {
        boolean a();

        void b();
    }

    public BaseLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public BaseLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BaseLayout(Context context) {
        super(context);
    }

    public void setActivity(Activity activity) {
        this.mActivityRef = new WeakReference<>(activity);
    }

    public void setBackKeyListener(BackKeyListener backKeyListener) {
        this.mListener = new WeakReference<>(backKeyListener);
    }

    /* access modifiers changed from: protected */
    public void hideInputMethod() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        }
    }

    public boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        KeyEvent.DispatcherState keyDispatcherState;
        BackKeyListener backKeyListener = null;
        Activity activity = this.mActivityRef == null ? null : (Activity) this.mActivityRef.get();
        if (this.mListener != null) {
            backKeyListener = (BackKeyListener) this.mListener.get();
        }
        if (!(backKeyListener == null || !backKeyListener.a() || activity == null || keyEvent.getKeyCode() != 4 || (keyDispatcherState = getKeyDispatcherState()) == null)) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                keyDispatcherState.startTracking(keyEvent, this);
                return true;
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && keyDispatcherState.isTracking(keyEvent)) {
                hideInputMethod();
                backKeyListener.b();
                return true;
            }
        }
        return super.dispatchKeyEventPreIme(keyEvent);
    }
}
