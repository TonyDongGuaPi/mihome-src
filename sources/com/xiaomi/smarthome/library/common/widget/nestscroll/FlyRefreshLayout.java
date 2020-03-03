package com.xiaomi.smarthome.library.common.widget.nestscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.nestscroll.PullHeaderLayout;
import com.xiaomi.smarthome.library.common.widget.nestscroll.internal.WeatherHeaderView;

public class FlyRefreshLayout extends PullHeaderLayout {

    /* renamed from: a  reason: collision with root package name */
    private OnPullRefreshListener f19024a;

    public interface OnPullRefreshListener {
        void a(FlyRefreshLayout flyRefreshLayout);

        void b(FlyRefreshLayout flyRefreshLayout);
    }

    public FlyRefreshLayout(Context context) {
        super(context);
        a(context);
    }

    public FlyRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public FlyRefreshLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        setHeaderView(new WeatherHeaderView(getContext()), new PullHeaderLayout.LayoutParams(-1, this.mHeaderController.a()));
    }

    public WeatherHeaderView getWeatherHeaderView() {
        return (WeatherHeaderView) this.mHeaderView;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        if (getIconView() == null) {
            setActionDrawable(getResources().getDrawable(R.drawable.common_loading_circle_white));
        }
    }

    public void startRefresh() {
        if (this.f19024a != null) {
            this.f19024a.a(this);
        }
    }

    public void setOnPullRefreshListener(OnPullRefreshListener onPullRefreshListener) {
        this.f19024a = onPullRefreshListener;
    }

    /* access modifiers changed from: protected */
    public void onMoveHeader(int i, float f, float f2) {
        super.onMoveHeader(i, f, f2);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }
}
