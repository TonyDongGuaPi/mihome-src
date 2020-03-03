package com.xiaomi.smarthome.library.common.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import java.lang.ref.WeakReference;

public class PullDownRefreshListView extends XMBaseListView {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18698a = 0;
    private final int b = 16;
    private float c;
    private final float d = 1.5f;
    private int e;
    private boolean f = false;
    private float g = 0.0f;
    /* access modifiers changed from: private */
    public int h = 0;
    /* access modifiers changed from: private */
    public boolean i = false;
    private Animation j;
    private Animation k;
    /* access modifiers changed from: private */
    public BuncingHandler l = new BuncingHandler(this);
    private boolean m = false;
    /* access modifiers changed from: private */
    public OnRefreshListener n;
    private OnInterceptListener o;
    private View p = null;
    /* access modifiers changed from: private */
    public View q = null;
    private ImageView r = null;
    /* access modifiers changed from: private */
    public int s = 0;
    private int t = Integer.MAX_VALUE;
    private boolean u = true;
    private boolean v = true;
    /* access modifiers changed from: private */
    public boolean w = true;
    /* access modifiers changed from: private */
    public CharSequence x;
    private CharSequence y;

    public interface OnInterceptListener {
        void a();

        boolean b();
    }

    public interface OnRefreshListener {
        void a();

        void a(boolean z);

        boolean b();

        boolean c();
    }

    public PullDownRefreshListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    public PullDownRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public PullDownRefreshListView(Context context) {
        super(context);
        a();
    }

    public void setRefreshListener(OnRefreshListener onRefreshListener) {
        this.n = onRefreshListener;
    }

    public void setInterceptListener(OnInterceptListener onInterceptListener) {
        this.o = onInterceptListener;
    }

    public void setShowRefreshProgress(boolean z) {
        this.w = z;
    }

    public boolean isRefreshing() {
        return this.i;
    }

    public void doRefresh() {
        if (!this.i && this.n != null) {
            AsyncTaskUtils.a(new RefreshTask(), new Void[0]);
        }
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

    public void setPullDownEnabled(boolean z) {
        this.u = z;
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
    private void a() {
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
            addHeaderView(this.p);
        }
    }

    public void setCanPullDown(boolean z) {
        this.v = z;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.v) {
            return super.dispatchTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (this.u) {
                    this.m = false;
                    if (!this.i && getFirstVisiblePosition() <= 0) {
                        this.f = true;
                        this.g = motionEvent.getY();
                        if (this.n != null) {
                            this.n.a();
                            break;
                        }
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
                    if (this.u && !this.f && !this.i && getFirstVisiblePosition() <= 0 && this.p.getTop() >= 0) {
                        this.f = true;
                        this.g = motionEvent.getY();
                        this.m = false;
                        if (this.n != null) {
                            this.n.a();
                            break;
                        }
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

    public void resume() {
        onViewHide();
        doRefresh();
    }

    private class RefreshTask extends AsyncTask<Void, Void, Boolean> {
        private boolean b;

        private RefreshTask() {
            this.b = false;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            if (PullDownRefreshListView.this.i) {
                this.b = true;
            } else {
                boolean unused = PullDownRefreshListView.this.i = true;
                ((TextView) PullDownRefreshListView.this.findViewById(R.id.pull_header_txt)).setText(R.string.refreshing);
                if (PullDownRefreshListView.this.w) {
                    PullDownRefreshListView.this.findViewById(R.id.pull_header_prog).setVisibility(0);
                }
                View findViewById = PullDownRefreshListView.this.findViewById(R.id.pull_header_indc);
                findViewById.clearAnimation();
                findViewById.setVisibility(8);
                ViewGroup.LayoutParams layoutParams = PullDownRefreshListView.this.q.getLayoutParams();
                if (PullDownRefreshListView.this.h == 0) {
                    int unused2 = PullDownRefreshListView.this.h = PullDownRefreshListView.this.getContext().getResources().getDimensionPixelSize(R.dimen.pull_down_header_height);
                }
                layoutParams.height = PullDownRefreshListView.this.h + PullDownRefreshListView.this.s;
                PullDownRefreshListView.this.q.setLayoutParams(layoutParams);
                if (PullDownRefreshListView.this.n != null) {
                    this.b = !PullDownRefreshListView.this.n.b();
                }
            }
            super.onPreExecute();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Boolean doInBackground(Void... voidArr) {
            boolean z = true;
            if (this.b) {
                return true;
            }
            if (PullDownRefreshListView.this.n == null || !PullDownRefreshListView.this.n.c()) {
                z = false;
            }
            return Boolean.valueOf(z);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Boolean bool) {
            boolean unused = PullDownRefreshListView.this.i = false;
            ((TextView) PullDownRefreshListView.this.findViewById(R.id.pull_header_txt)).setText(PullDownRefreshListView.this.x);
            PullDownRefreshListView.this.findViewById(R.id.pull_header_prog).setVisibility(8);
            View findViewById = PullDownRefreshListView.this.findViewById(R.id.pull_header_indc);
            findViewById.clearAnimation();
            findViewById.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = PullDownRefreshListView.this.q.getLayoutParams();
            layoutParams.height = PullDownRefreshListView.this.s + PullDownRefreshListView.this.h;
            PullDownRefreshListView.this.q.setLayoutParams(layoutParams);
            if (PullDownRefreshListView.this.n != null) {
                PullDownRefreshListView.this.n.a(bool.booleanValue());
            }
            PullDownRefreshListView.this.l.sendEmptyMessageDelayed(0, 16);
            super.onPostExecute(bool);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
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
        private WeakReference<PullDownRefreshListView> f18699a;

        public BuncingHandler(PullDownRefreshListView pullDownRefreshListView) {
            this.f18699a = new WeakReference<>(pullDownRefreshListView);
        }

        public void handleMessage(Message message) {
            PullDownRefreshListView pullDownRefreshListView = (PullDownRefreshListView) this.f18699a.get();
            if (pullDownRefreshListView != null) {
                if (message.what == 0) {
                    pullDownRefreshListView.b();
                }
                super.handleMessage(message);
            }
        }
    }
}
