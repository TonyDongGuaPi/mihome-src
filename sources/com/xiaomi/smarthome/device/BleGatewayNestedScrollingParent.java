package com.xiaomi.smarthome.device;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.react.uimanager.ViewProps;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class BleGatewayNestedScrollingParent extends LinearLayout implements NestedScrollingParent {

    /* renamed from: a  reason: collision with root package name */
    private static final float f14756a = 0.5f;
    private static int b = 0;
    private static int c = 0;
    private static int d = 0;
    private static final int s = 100;
    private boolean e = false;
    private LinearLayout f;
    private RecyclerView g;
    private FrameLayout h;
    private FrameLayout i;
    private TextView j;
    private SimpleDraweeView k;
    private ImageView l;
    private HeightChangeableView m;
    private ImageView n;
    /* access modifiers changed from: private */
    public RefreshListener o;
    private OverScroller p;
    private ObjectAnimator q;
    private RelativeLayout r;
    private Handler t = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 100) {
                BleGatewayNestedScrollingParent.this.o.a();
            }
        }
    };
    private boolean u = false;

    public interface RefreshListener {
        void a();
    }

    public int getNestedScrollAxes() {
        return 0;
    }

    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return false;
    }

    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
    }

    public void onNestedScrollAccepted(View view, View view2, int i2) {
    }

    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return (i2 & 2) != 0;
    }

    public void setOnRefreshListener(RefreshListener refreshListener) {
        this.o = refreshListener;
    }

    public BleGatewayNestedScrollingParent(Context context) {
        super(context);
        a();
    }

    public BleGatewayNestedScrollingParent(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public BleGatewayNestedScrollingParent(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    private void a() {
        this.p = new OverScroller(getContext());
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        c = (int) ((displayMetrics.density * 40.0f) + 0.5f);
        d = (int) ((displayMetrics.density * 50.0f) + 0.5f);
        b = (int) ((displayMetrics.density * 162.33f) + 0.5f);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f = (LinearLayout) findViewById(R.id.head);
        this.r = (RelativeLayout) findViewById(R.id.content);
        this.h = (FrameLayout) findViewById(R.id.head_view_extend);
        this.i = (FrameLayout) findViewById(R.id.head_view_shrink);
        this.j = (TextView) findViewById(R.id.desc);
        this.k = (SimpleDraweeView) findViewById(R.id.head_image_extend);
        this.l = (ImageView) findViewById(R.id.head_anim_image);
        this.m = (HeightChangeableView) findViewById(R.id.refresh_wrapper);
        this.n = (ImageView) findViewById(R.id.progress_image);
        c();
    }

    public void onStopNestedScroll(View view) {
        if (this.u) {
            this.u = false;
        } else if (this.m.getHeight() > 0) {
            if (this.m.getHeight() >= c && !this.e) {
                this.n.setVisibility(0);
                if (this.q == null || !this.q.isRunning()) {
                    this.q = ObjectAnimator.ofFloat(this.n, ViewProps.ROTATION, new float[]{0.0f, 3600.0f});
                    this.q.setDuration(10000);
                    this.q.setInterpolator(new LinearInterpolator());
                    this.q.setRepeatCount(-1);
                    this.q.start();
                }
                this.e = true;
                this.t.sendEmptyMessageDelayed(100, 500);
            }
            ObjectAnimator ofInt = ObjectAnimator.ofInt(this.m, "height", new int[]{this.m.getHeight(), 0});
            ofInt.setDuration(300);
            ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
            ofInt.start();
        } else if (getScrollY() != 0 && getScrollY() != b && getScrollY() != 0) {
            if (getScrollY() > DisplayUtils.a(100.0f)) {
                this.p.startScroll(0, getScrollY(), 0, b - getScrollY(), 300);
                invalidate();
                return;
            }
            this.p.startScroll(0, getScrollY(), 0, -getScrollY(), 300);
            invalidate();
        }
    }

    public boolean onNestedPreFling(View view, float f2, float f3) {
        if (f3 <= 1500.0f || getScrollY() >= b) {
            return false;
        }
        this.u = true;
        this.p.startScroll(0, getScrollY(), 0, b - getScrollY(), 300);
        invalidate();
        return true;
    }

    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        if (this.f != null && this.f.getHeight() != 0) {
            boolean z = i3 > 0 && getScrollY() < b;
            boolean z2 = i3 < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(view, -1);
            if (z) {
                if (this.m.getHeight() > 0) {
                    int height = this.m.getHeight() > i3 ? i3 : this.m.getHeight();
                    if (height == this.m.getHeight()) {
                        this.m.setHeight(0);
                    } else {
                        this.m.setHeight(this.m.getHeight() - ((int) (((float) height) * 0.5f)));
                    }
                    if (height < i3) {
                        scrollBy(0, i3 - height);
                        this.f.scrollBy(0, height - i3);
                        b();
                    } else {
                        i3 = height;
                    }
                } else {
                    if (i3 > b - getScrollY()) {
                        i3 = b - getScrollY();
                    }
                    scrollBy(0, i3);
                    this.f.scrollBy(0, -i3);
                    b();
                }
            } else if (z2) {
                if (getScrollY() == 0) {
                    int height2 = this.m.getHeight();
                    if (height2 < d) {
                        this.m.setHeight(height2 - ((int) (((float) i3) * 0.5f)));
                    } else {
                        i3 = 0;
                    }
                } else {
                    if (Math.abs(i3) > getScrollY()) {
                        i3 = -getScrollY();
                    }
                    scrollBy(0, i3);
                    this.f.scrollBy(0, -i3);
                    b();
                }
                i3 = -i3;
            } else {
                i3 = 0;
            }
            iArr[1] = i3;
        }
    }

    private void b() {
        this.h.setAlpha(1.0f - ((((float) getScrollY()) / ((float) b)) * 2.0f));
        this.j.setAlpha(1.0f - (((float) getScrollY()) / ((float) b)));
        float scrollY = ((float) getScrollY()) / ((float) b);
        this.i.setAlpha(scrollY * scrollY);
        this.k.getLayoutParams().height = (int) (((float) DisplayUtils.d(getContext(), 112.0f)) * (1.0f - (((float) getScrollY()) / ((float) b))));
        this.k.getLayoutParams().width = (int) (((float) DisplayUtils.d(getContext(), 112.0f)) * (1.0f - (((float) getScrollY()) / ((float) b))));
        this.k.requestLayout();
        this.l.getLayoutParams().height = (int) (((float) DisplayUtils.d(getContext(), 168.0f)) * (1.0f - (((float) getScrollY()) / ((float) b))));
        this.l.getLayoutParams().width = (int) (((float) DisplayUtils.d(getContext(), 278.0f)) * (1.0f - (((float) getScrollY()) / ((float) b))));
        this.l.requestLayout();
    }

    public void refreshFinish() {
        this.e = false;
        this.n.setVisibility(8);
        if (this.q != null && this.q.isRunning()) {
            this.q.end();
        }
    }

    private void c() {
        int i2 = DisplayUtils.a(getContext()).y;
        this.r.getLayoutParams().height = ((i2 - DisplayUtils.d(getContext(), 318.33f)) + b) - DisplayUtils.d(getContext(), 20.0f);
        this.r.requestLayout();
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.p.computeScrollOffset()) {
            scrollTo(0, this.p.getCurrY());
            this.f.scrollTo(0, -this.p.getCurrY());
            b();
            invalidate();
        }
    }
}
