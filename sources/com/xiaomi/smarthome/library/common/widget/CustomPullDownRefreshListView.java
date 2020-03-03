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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.XMBaseListView;
import java.lang.ref.WeakReference;

public class CustomPullDownRefreshListView extends XMBaseListView {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18804a = 0;
    private static final int b = 1;
    private CharSequence A;
    private CharSequence B;
    private int C = 0;
    private View D;
    private float E = -1.0f;
    private boolean F = false;
    private final int c = 16;
    private float d;
    private final float e = 1.5f;
    private int f;
    private boolean g = false;
    private boolean h = false;
    private float i = 0.0f;
    private int j = 0;
    private boolean k = false;
    private Animation l;
    private Animation m;
    private BuncingHandler n = new BuncingHandler(this);
    private boolean o = false;
    private OnRefreshListener p;
    private OnInterceptListener q;
    private View r = null;
    private View s = null;
    private View t = null;
    private ImageView u = null;
    private int v = 0;
    private int w = Integer.MAX_VALUE;
    private boolean x = true;
    private boolean y = true;
    private boolean z = true;

    public interface OnInterceptListener {
        void a();

        boolean b();
    }

    public interface OnRefreshListener {
        void startRefresh();
    }

    public CustomPullDownRefreshListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        b();
    }

    public CustomPullDownRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public CustomPullDownRefreshListView(Context context) {
        super(context);
        b();
    }

    public void setRefreshListener(OnRefreshListener onRefreshListener) {
        this.p = onRefreshListener;
    }

    public void setInterceptListener(OnInterceptListener onInterceptListener) {
        this.q = onInterceptListener;
    }

    public void setShowRefreshProgress(boolean z2) {
        this.z = z2;
    }

    public boolean isRefreshing() {
        return this.k;
    }

    public void doRefresh() {
        if (!this.k && this.p != null) {
            a();
            this.p.startRefresh();
        }
    }

    public static abstract class BottomBounceAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        CustomPullDownRefreshListView f18805a;

        public abstract CustomPullDownRefreshListView a();

        public int getCount() {
            return 0;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }

        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }
    }

    private void a() {
        this.k = true;
        ((TextView) findViewById(R.id.pull_header_txt)).setText(R.string.refreshing);
        if (this.z) {
            findViewById(R.id.pull_header_prog).setVisibility(0);
        }
        View findViewById = findViewById(R.id.pull_header_indc);
        findViewById.clearAnimation();
        findViewById.setVisibility(8);
        ViewGroup.LayoutParams layoutParams = this.s.getLayoutParams();
        if (this.j == 0) {
            this.j = getContext().getResources().getDimensionPixelSize(R.dimen.pull_down_header_height);
        }
        layoutParams.height = this.j + this.v;
        this.s.setLayoutParams(layoutParams);
    }

    public void setProgressDrawable(Drawable drawable) {
        ((ProgressBar) findViewById(R.id.pull_header_prog)).setIndeterminateDrawable(drawable);
    }

    public void postRefresh() {
        this.k = false;
        ((TextView) findViewById(R.id.pull_header_txt)).setText(this.A);
        findViewById(R.id.pull_header_prog).setVisibility(8);
        View findViewById = findViewById(R.id.pull_header_indc);
        findViewById.clearAnimation();
        findViewById.setVisibility(0);
        ViewGroup.LayoutParams layoutParams = this.s.getLayoutParams();
        layoutParams.height = this.v + this.j;
        this.s.setLayoutParams(layoutParams);
        this.n.sendEmptyMessageDelayed(0, 16);
    }

    public void setHeaderBackground(Drawable drawable) {
        if (this.u != null) {
            this.u.setImageDrawable(drawable);
            int minimumWidth = drawable.getMinimumWidth();
            int minimumHeight = drawable.getMinimumHeight();
            if (minimumWidth > 0) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int i2 = (minimumHeight * displayMetrics.widthPixels) / minimumWidth;
                ViewGroup.LayoutParams layoutParams = this.u.getLayoutParams();
                layoutParams.height = i2;
                this.w = i2;
                this.u.setLayoutParams(layoutParams);
            }
        }
    }

    public void setOriHeight(int i2) {
        this.v = i2;
        findViewById(R.id.pull_header).getLayoutParams().height = this.v;
        this.r.findViewById(R.id.empty_view).getLayoutParams().height = this.v;
    }

    public void setPullDownEnabled(boolean z2) {
        this.x = z2;
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
        this.A = charSequence;
        ((TextView) findViewById(R.id.pull_header_txt)).setText(this.A);
        this.B = charSequence2;
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
        this.w = getResources().getDimensionPixelSize(i2);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        refreshRooterView();
    }

    public void refreshRooterView() {
        int heightWithoutFooter = getHeightWithoutFooter();
        if (getHeight() - heightWithoutFooter > DisplayUtils.a(13.3f)) {
            this.C = getHeight() - heightWithoutFooter;
        } else {
            this.C = DisplayUtils.a(13.3f);
        }
        ViewGroup.LayoutParams layoutParams = this.t.getLayoutParams();
        layoutParams.height = this.C;
        this.t.setLayoutParams(layoutParams);
    }

    @SuppressLint({"NewApi"})
    private void b() {
        if (Build.VERSION.SDK_INT >= 14) {
            setOverScrollMode(2);
        }
        if (!isInEditMode()) {
            this.d = (getResources().getDisplayMetrics().density * 1.5f) + 0.5f;
            this.A = getContext().getString(R.string.pull_down_refresh);
            this.B = getContext().getString(R.string.release_to_refresh);
            this.f = getResources().getDimensionPixelSize(R.dimen.pull_down_refresh_threshold);
            this.r = LayoutInflater.from(getContext()).inflate(R.layout.pull_header, (ViewGroup) null);
            this.s = this.r.findViewById(R.id.pull_header);
            this.u = (ImageView) this.r.findViewById(R.id.img_bkg);
            addHeaderView(this.r);
            this.D = LayoutInflater.from(getContext()).inflate(R.layout.pull_footer, (ViewGroup) null);
            super.addFooterView(this.D);
            this.t = this.D.findViewById(R.id.pull_footer);
            this.C = DisplayUtils.a(13.3f);
        }
    }

    public void addFooterView(View view) {
        removeFooterView(this.D);
        super.addFooterView(this.D);
        super.addFooterView(view);
    }

    public void setCanPullDown(boolean z2) {
        this.y = z2;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.y) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (this.E == -1.0f) {
                    int heightWithoutFooter = getHeightWithoutFooter();
                    if (getHeight() - heightWithoutFooter > DisplayUtils.a(13.3f)) {
                        this.C = getHeight() - heightWithoutFooter;
                    } else {
                        this.C = DisplayUtils.a(13.3f);
                    }
                    this.E = motionEvent.getY();
                    break;
                }
                break;
            case 1:
                if (this.g) {
                    if (this.q == null || !this.q.b()) {
                        this.n.sendEmptyMessage(0);
                        if (this.o) {
                            doRefresh();
                        }
                        this.g = false;
                    } else if (this.o) {
                        this.q.a();
                    } else {
                        this.n.sendEmptyMessage(0);
                        this.g = false;
                    }
                } else if (this.h) {
                    this.n.sendEmptyMessage(1);
                    this.h = false;
                }
                this.E = -1.0f;
                break;
            case 2:
                if (this.E == -1.0f) {
                    int heightWithoutFooter2 = getHeightWithoutFooter();
                    if (getHeight() - heightWithoutFooter2 > DisplayUtils.a(13.3f)) {
                        this.C = getHeight() - heightWithoutFooter2;
                    } else {
                        this.C = DisplayUtils.a(13.3f);
                    }
                    this.E = motionEvent.getY();
                }
                if (!this.g) {
                    if (!this.h) {
                        if (!this.x || this.g || this.k || getFirstVisiblePosition() > 0 || motionEvent.getY() - this.E <= 0.0f || this.r.getTop() < 0) {
                            if (!this.h && !this.k && getLastVisiblePosition() >= getCount() - 1 && motionEvent.getY() - this.E < 0.0f) {
                                this.h = true;
                                this.i = motionEvent.getY();
                                this.o = false;
                                break;
                            }
                        } else {
                            this.g = true;
                            this.i = motionEvent.getY();
                            this.o = false;
                            break;
                        }
                    } else {
                        float y2 = motionEvent.getY();
                        if (y2 - this.i < -10.0f) {
                            this.j = (int) ((this.i - y2) / 3.0f);
                            ViewGroup.LayoutParams layoutParams = this.t.getLayoutParams();
                            layoutParams.height = this.j + this.C;
                            this.t.setLayoutParams(layoutParams);
                            setSelection(getBottom());
                            motionEvent.setAction(3);
                            super.onTouchEvent(motionEvent);
                            break;
                        }
                    }
                } else {
                    TextView textView = (TextView) findViewById(R.id.pull_header_txt);
                    ImageView imageView = (ImageView) findViewById(R.id.pull_header_indc);
                    float y3 = motionEvent.getY();
                    if (y3 - this.i > 10.0f) {
                        ViewGroup.LayoutParams layoutParams2 = this.s.getLayoutParams();
                        this.j = (int) ((y3 - this.i) / 2.0f);
                        if (this.j + this.v < this.w) {
                            layoutParams2.height = this.j + this.v;
                            this.s.setLayoutParams(layoutParams2);
                            if (this.j >= this.f) {
                                if (!this.o) {
                                    textView.setText(this.B);
                                    if (this.l == null) {
                                        this.l = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_180);
                                        this.l.setFillAfter(true);
                                    }
                                    imageView.startAnimation(this.l);
                                    this.o = true;
                                }
                            } else if (this.o) {
                                textView.setText(this.A);
                                if (this.m == null) {
                                    this.m = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_back_180);
                                    this.m.setFillAfter(true);
                                }
                                imageView.startAnimation(this.m);
                                this.o = false;
                            }
                        } else {
                            this.j = Math.max(0, this.w - this.v);
                        }
                        motionEvent.setAction(3);
                        super.onTouchEvent(motionEvent);
                        return true;
                    }
                }
                break;
            case 3:
                this.E = -1.0f;
                onViewHide();
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onViewHide() {
        if (this.g) {
            this.n.sendEmptyMessage(0);
        }
        this.g = false;
    }

    public boolean getIsDown() {
        if ((this.k || getFirstVisiblePosition() > 0 || this.r.getTop() < 0) && !this.k) {
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
        ViewGroup.LayoutParams layoutParams = this.s.getLayoutParams();
        if (this.j >= 0) {
            this.j = (int) (((float) this.j) - ((this.k ? this.d : this.d / 2.0f) * 16.0f));
            if (this.k && this.j <= this.f) {
                this.j = this.f;
                layoutParams.height = this.j + this.v;
                this.s.setLayoutParams(layoutParams);
                this.n.removeMessages(0);
                return;
            } else if (this.j <= 0) {
                this.j = 0;
                layoutParams.height = this.j + this.v;
                this.s.setLayoutParams(layoutParams);
                this.n.removeMessages(0);
                return;
            } else {
                layoutParams.height = this.j + this.v;
                this.s.setLayoutParams(layoutParams);
            }
        }
        this.n.sendEmptyMessageDelayed(0, 16);
    }

    /* access modifiers changed from: private */
    public void d() {
        ViewGroup.LayoutParams layoutParams = this.t.getLayoutParams();
        if (this.j >= 0) {
            this.j = (int) (((float) this.j) - ((this.k ? this.d : this.d / 2.0f) * 16.0f));
            if (this.j <= 0) {
                this.j = 0;
                layoutParams.height = this.j + this.C;
                this.t.setLayoutParams(layoutParams);
                this.n.removeMessages(1);
                setSelection(getBottom());
                return;
            }
            layoutParams.height = this.j + this.C;
            this.t.setLayoutParams(layoutParams);
            setSelection(getBottom());
        }
        this.n.sendEmptyMessageDelayed(1, 16);
    }

    private static final class BuncingHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<CustomPullDownRefreshListView> f18806a;

        public BuncingHandler(CustomPullDownRefreshListView customPullDownRefreshListView) {
            this.f18806a = new WeakReference<>(customPullDownRefreshListView);
        }

        public void handleMessage(Message message) {
            CustomPullDownRefreshListView customPullDownRefreshListView = (CustomPullDownRefreshListView) this.f18806a.get();
            if (customPullDownRefreshListView != null) {
                if (message.what == 0) {
                    customPullDownRefreshListView.c();
                } else if (message.what == 1) {
                    customPullDownRefreshListView.d();
                }
                super.handleMessage(message);
            }
        }
    }

    public int getHeightWithoutFooter() {
        if (getAdapter() == null) {
            return -1;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (!childAt.equals(this.D)) {
                i2 += childAt.getHeight();
            }
        }
        return i2;
    }
}
