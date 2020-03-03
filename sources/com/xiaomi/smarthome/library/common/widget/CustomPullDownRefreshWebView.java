package com.xiaomi.smarthome.library.common.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import java.lang.ref.WeakReference;

public class CustomPullDownRefreshWebView extends WebView {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18807a = 0;
    private final int b = 16;
    private float c;
    private final float d = 1.5f;
    private int e;
    private boolean f = false;
    private float g = 0.0f;
    private int h = 0;
    private boolean i = false;
    private Animation j;
    private Animation k;
    private BuncingHandler l = new BuncingHandler(this);
    private boolean m = false;
    private OnRefreshListener n;
    private OnInterceptListener o;
    private View p = null;
    private View q = null;
    private ImageView r = null;
    private int s = 0;
    private int t = Integer.MAX_VALUE;
    private boolean u = true;
    private boolean v = true;
    private boolean w = true;
    private CharSequence x;
    private CharSequence y;
    private boolean z = false;

    public interface OnInterceptListener {
        void a();

        boolean b();
    }

    public interface OnRefreshListener {
        void a();
    }

    public CustomPullDownRefreshWebView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        b();
    }

    public CustomPullDownRefreshWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public CustomPullDownRefreshWebView(Context context) {
        super(context);
        b();
    }

    public void setRefreshListener(OnRefreshListener onRefreshListener) {
        this.n = onRefreshListener;
    }

    public void setInterceptListener(OnInterceptListener onInterceptListener) {
        this.o = onInterceptListener;
    }

    public void setShowRefreshProgress(boolean z2) {
        this.w = z2;
    }

    public boolean isRefreshing() {
        return this.i;
    }

    public void doRefresh() {
        if (!this.i && this.n != null) {
            a();
            this.n.a();
        }
    }

    private void a() {
        this.i = true;
        ((TextView) findViewById(R.id.pull_header_txt)).setText(R.string.refreshing);
        if (this.w) {
            findViewById(R.id.pull_header_prog).setVisibility(0);
        }
        View findViewById = findViewById(R.id.pull_header_indc);
        findViewById.clearAnimation();
        findViewById.setVisibility(8);
        ViewGroup.LayoutParams layoutParams = this.q.getLayoutParams();
        if (this.h == 0) {
            this.h = getContext().getResources().getDimensionPixelSize(R.dimen.pull_down_header_height);
        }
        layoutParams.height = this.h + this.s;
        this.q.setLayoutParams(layoutParams);
    }

    public void setProgressDrawable(Drawable drawable) {
        ((ProgressBar) findViewById(R.id.pull_header_prog)).setIndeterminateDrawable(drawable);
    }

    public void postRefresh() {
        this.i = false;
        ((TextView) findViewById(R.id.pull_header_txt)).setText(this.x);
        findViewById(R.id.pull_header_prog).setVisibility(8);
        View findViewById = findViewById(R.id.pull_header_indc);
        findViewById.clearAnimation();
        findViewById.setVisibility(0);
        ViewGroup.LayoutParams layoutParams = this.q.getLayoutParams();
        layoutParams.height = this.s + this.h;
        this.q.setLayoutParams(layoutParams);
        this.l.sendEmptyMessageDelayed(0, 16);
    }

    public void setHeaderBackground(Drawable drawable) {
        if (this.r != null) {
            this.r.setImageDrawable(drawable);
            int minimumWidth = drawable.getMinimumWidth();
            int minimumHeight = drawable.getMinimumHeight();
            if (minimumWidth > 0) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int i2 = (minimumHeight * displayMetrics.widthPixels) / minimumWidth;
                ViewGroup.LayoutParams layoutParams = this.r.getLayoutParams();
                layoutParams.height = i2;
                this.t = i2;
                this.r.setLayoutParams(layoutParams);
            }
        }
    }

    public void setOriHeight(int i2) {
        this.s = i2;
        findViewById(R.id.pull_header).getLayoutParams().height = this.s;
        this.p.findViewById(R.id.empty_view).getLayoutParams().height = this.s;
    }

    public void setPullDownEnabled(boolean z2) {
        this.u = z2;
    }

    public void setPullDownHeaderVisibility(int i2) {
        findViewById(R.id.pull_header_container).setVisibility(i2);
    }

    public void setPullDownTextColor(int i2) {
        ((TextView) findViewById(R.id.pull_header_txt)).setTextColor(i2);
    }

    public void setPullDownTextSize(int i2) {
        ((TextView) findViewById(R.id.pull_header_txt)).setTextSize((float) i2);
    }

    public void setPullDownText(CharSequence charSequence, CharSequence charSequence2) {
        this.x = charSequence;
        ((TextView) findViewById(R.id.pull_header_txt)).setText(this.x);
        this.y = charSequence2;
    }

    public void setPullDownTextColorLine2(int i2) {
        ((TextView) findViewById(R.id.pull_header_txt_line2)).setTextColor(i2);
    }

    public void setPullDownLine2Text(CharSequence charSequence) {
        TextView textView = (TextView) findViewById(R.id.pull_header_txt_line2);
        if (!TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(0);
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
    }

    public void setIndicatorDrawable(Drawable drawable) {
        ((ImageView) findViewById(R.id.pull_header_indc)).setImageDrawable(drawable);
    }

    public void setMaxPullDownFromRes(int i2) {
        this.t = getResources().getDimensionPixelSize(i2);
    }

    @SuppressLint({"NewApi"})
    private void b() {
        if (Build.VERSION.SDK_INT >= 14) {
            setOverScrollMode(2);
        }
        if (!isInEditMode()) {
            this.c = (getResources().getDisplayMetrics().density * 1.5f) + 0.5f;
            this.x = getContext().getString(R.string.pull_down_refresh);
            this.y = getContext().getString(R.string.release_to_refresh);
            this.e = getResources().getDimensionPixelSize(R.dimen.pull_down_refresh_threshold);
            this.p = LayoutInflater.from(getContext()).inflate(R.layout.pull_header, (ViewGroup) null);
            this.q = this.p.findViewById(R.id.pull_header);
            this.r = (ImageView) this.p.findViewById(R.id.img_bkg);
            addView(this.p);
        }
    }

    public void setCanPullDown(boolean z2) {
        this.v = z2;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        if (i3 <= this.p.getMeasuredHeight()) {
            this.z = true;
        } else {
            this.z = false;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.v) {
            return super.dispatchTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (this.u) {
                    this.m = false;
                    if (!this.i && this.z) {
                        this.f = true;
                        this.g = motionEvent.getY();
                        break;
                    }
                }
                break;
            case 1:
                if (this.f) {
                    if (this.o != null && this.o.b()) {
                        if (!this.m) {
                            this.l.sendEmptyMessage(0);
                            this.f = false;
                            break;
                        } else {
                            this.o.a();
                            break;
                        }
                    } else {
                        this.l.sendEmptyMessage(0);
                        if (this.m) {
                            doRefresh();
                        }
                        this.f = false;
                        break;
                    }
                }
                break;
            case 2:
                if (!this.f) {
                    if (this.u && !this.f && !this.i && this.z && this.p.getTop() >= 0) {
                        this.f = true;
                        this.g = motionEvent.getY();
                        this.m = false;
                        break;
                    }
                } else {
                    TextView textView = (TextView) findViewById(R.id.pull_header_txt);
                    ImageView imageView = (ImageView) findViewById(R.id.pull_header_indc);
                    float y2 = motionEvent.getY();
                    if (y2 - this.g > 10.0f) {
                        ViewGroup.LayoutParams layoutParams = this.q.getLayoutParams();
                        this.h = (int) ((y2 - this.g) / 2.0f);
                        if (this.h + this.s < this.t) {
                            layoutParams.height = this.h + this.s;
                            this.q.setLayoutParams(layoutParams);
                            ViewGroup.LayoutParams layoutParams2 = getLayoutParams();
                            layoutParams2.height = this.h + this.s;
                            setLayoutParams(layoutParams2);
                            if (this.h >= this.e) {
                                if (!this.m) {
                                    textView.setText(this.y);
                                    if (this.j == null) {
                                        this.j = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_180);
                                        this.j.setFillAfter(true);
                                    }
                                    imageView.startAnimation(this.j);
                                    this.m = true;
                                }
                            } else if (this.m) {
                                textView.setText(this.x);
                                if (this.k == null) {
                                    this.k = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_back_180);
                                    this.k.setFillAfter(true);
                                }
                                imageView.startAnimation(this.k);
                                this.m = false;
                            }
                        } else {
                            this.h = Math.max(0, this.t - this.s);
                        }
                        motionEvent.setAction(3);
                        super.dispatchTouchEvent(motionEvent);
                        return true;
                    }
                }
                break;
            case 3:
                onViewHide();
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onViewHide() {
        if (this.f) {
            this.l.sendEmptyMessage(0);
        }
        this.f = false;
    }

    public boolean getIsDown() {
        if ((this.i || !this.z || this.p.getTop() < 0) && !this.i) {
            return false;
        }
        return true;
    }

    public void resume() {
        onViewHide();
        doRefresh();
    }

    /* access modifiers changed from: private */
    public void c() {
        ViewGroup.LayoutParams layoutParams = this.q.getLayoutParams();
        if (this.h >= 0) {
            this.h = (int) (((float) this.h) - ((this.i ? this.c : this.c / 2.0f) * 16.0f));
            if (this.i && this.h <= this.e) {
                this.h = this.e;
                layoutParams.height = this.h + this.s;
                this.q.setLayoutParams(layoutParams);
                this.l.removeMessages(0);
                return;
            } else if (this.h <= 0) {
                this.h = 0;
                layoutParams.height = this.h + this.s;
                this.q.setLayoutParams(layoutParams);
                this.l.removeMessages(0);
                return;
            } else {
                layoutParams.height = this.h + this.s;
                this.q.setLayoutParams(layoutParams);
            }
        }
        this.l.sendEmptyMessageDelayed(0, 16);
    }

    private static final class BuncingHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<CustomPullDownRefreshWebView> f18808a;

        public BuncingHandler(CustomPullDownRefreshWebView customPullDownRefreshWebView) {
            this.f18808a = new WeakReference<>(customPullDownRefreshWebView);
        }

        public void handleMessage(Message message) {
            CustomPullDownRefreshWebView customPullDownRefreshWebView = (CustomPullDownRefreshWebView) this.f18808a.get();
            if (customPullDownRefreshWebView != null) {
                if (message.what == 0) {
                    customPullDownRefreshWebView.c();
                }
                super.handleMessage(message);
            }
        }
    }
}
