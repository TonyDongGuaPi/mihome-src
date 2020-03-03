package com.andview.refreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class XWebView extends WebView {

    /* renamed from: a  reason: collision with root package name */
    private OnScrollBottomListener f4779a;
    private int b;

    public interface OnScrollBottomListener {
        void a();
    }

    public void registerOnBottomListener(OnScrollBottomListener onScrollBottomListener) {
        this.f4779a = onScrollBottomListener;
    }

    public void unRegisterOnBottomListener() {
        this.f4779a = null;
    }

    public XWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        if (getHeight() + getScrollY() == getHeight()) {
            this.b++;
            if (this.b == 1 && this.f4779a != null) {
                this.f4779a.a();
                return;
            }
            return;
        }
        this.b = 0;
    }
}
