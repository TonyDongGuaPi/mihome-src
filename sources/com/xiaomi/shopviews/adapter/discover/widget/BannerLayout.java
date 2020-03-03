package com.xiaomi.shopviews.adapter.discover.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.libra.Color;
import com.xiaomi.shopviews.widget.R;

public class BannerLayout extends FrameLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public int f13118a;
    private boolean b;
    private RecyclerView c;
    float centerScale;
    /* access modifiers changed from: private */
    public Drawable d;
    /* access modifiers changed from: private */
    public Drawable e;
    private IndicatorAdapter f;
    /* access modifiers changed from: private */
    public int g;
    /* access modifiers changed from: private */
    public RecyclerView h;
    /* access modifiers changed from: private */
    public BannerLayoutManager i;
    int itemSpace;
    /* access modifiers changed from: private */
    public int j;
    private boolean k;
    /* access modifiers changed from: private */
    public int l;
    /* access modifiers changed from: private */
    public int m;
    protected Handler mHandler;
    float moveSpeed;
    private boolean n;
    private boolean o;

    public interface OnBannerItemClickListener {
        void a(int i);
    }

    static /* synthetic */ int access$104(BannerLayout bannerLayout) {
        int i2 = bannerLayout.m + 1;
        bannerLayout.m = i2;
        return i2;
    }

    public BannerLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public BannerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BannerLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.j = 1000;
        this.l = 1;
        this.n = false;
        this.o = true;
        this.mHandler = new Handler(new Handler.Callback() {
            public boolean handleMessage(Message message) {
                if (message.what != BannerLayout.this.j || BannerLayout.this.m != BannerLayout.this.i.n()) {
                    return false;
                }
                BannerLayout.access$104(BannerLayout.this);
                BannerLayout.this.h.smoothScrollToPosition(BannerLayout.this.m);
                BannerLayout.this.mHandler.sendEmptyMessageDelayed(BannerLayout.this.j, (long) BannerLayout.this.f13118a);
                BannerLayout.this.refreshIndicator();
                return false;
            }
        });
        initView(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void initView(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BannerLayout);
        int i2 = 1;
        this.b = obtainStyledAttributes.getBoolean(R.styleable.BannerLayout_showIndicator, true);
        this.f13118a = obtainStyledAttributes.getInt(R.styleable.BannerLayout_interval, 4000);
        this.o = obtainStyledAttributes.getBoolean(R.styleable.BannerLayout_autoPlaying, true);
        this.itemSpace = obtainStyledAttributes.getInt(R.styleable.BannerLayout_itemSpace, 20);
        this.centerScale = obtainStyledAttributes.getFloat(R.styleable.BannerLayout_centerScale, 1.2f);
        this.moveSpeed = obtainStyledAttributes.getFloat(R.styleable.BannerLayout_moveSpeed, 1.0f);
        if (this.d == null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(1);
            gradientDrawable.setColor(-65536);
            gradientDrawable.setSize(dp2px(5), dp2px(5));
            gradientDrawable.setCornerRadius((float) (dp2px(5) / 2));
            this.d = new LayerDrawable(new Drawable[]{gradientDrawable});
        }
        if (this.e == null) {
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setShape(1);
            gradientDrawable2.setColor(Color.c);
            gradientDrawable2.setSize(dp2px(5), dp2px(5));
            gradientDrawable2.setCornerRadius((float) (dp2px(5) / 2));
            this.e = new LayerDrawable(new Drawable[]{gradientDrawable2});
        }
        this.g = dp2px(4);
        int dp2px = dp2px(16);
        int dp2px2 = dp2px(0);
        int dp2px3 = dp2px(11);
        int i3 = obtainStyledAttributes.getInt(R.styleable.BannerLayout_orientation, 0);
        if (i3 == 0 || i3 != 1) {
            i2 = 0;
        }
        obtainStyledAttributes.recycle();
        this.h = new RecyclerView(context);
        addView(this.h, new FrameLayout.LayoutParams(-1, -1));
        this.i = new BannerLayoutManager(getContext(), i2);
        this.i.a(this.itemSpace);
        this.i.a(this.centerScale);
        this.i.b(this.moveSpeed);
        this.h.setLayoutManager(this.i);
        new CenterSnapHelper().a(this.h);
        this.c = new RecyclerView(context);
        this.c.setLayoutManager(new LinearLayoutManager(context, i2, false));
        this.f = new IndicatorAdapter();
        this.c.setAdapter(this.f);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388691;
        layoutParams.setMargins(dp2px, 0, dp2px2, dp2px3);
        addView(this.c, layoutParams);
        if (!this.b) {
            this.c.setVisibility(8);
        }
    }

    public void setAutoPlaying(boolean z) {
        this.o = z;
        setPlaying(this.o);
    }

    public boolean isPlaying() {
        return this.n;
    }

    public void setShowIndicator(boolean z) {
        this.b = z;
        this.c.setVisibility(z ? 0 : 8);
    }

    public void setCenterScale(float f2) {
        this.centerScale = f2;
        this.i.a(f2);
    }

    public void setMoveSpeed(float f2) {
        this.moveSpeed = f2;
        this.i.b(f2);
    }

    public void setItemSpace(int i2) {
        this.itemSpace = i2;
        this.i.a(i2);
    }

    public void setAutoPlayDuration(int i2) {
        this.f13118a = i2;
    }

    public void setOrientation(int i2) {
        this.i.b(i2);
    }

    /* access modifiers changed from: protected */
    public synchronized void setPlaying(boolean z) {
        if (this.o && this.k) {
            if (!this.n && z) {
                this.mHandler.sendEmptyMessageDelayed(this.j, (long) this.f13118a);
                this.n = true;
            } else if (this.n && !z) {
                this.mHandler.removeMessages(this.j);
                this.n = false;
            }
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        boolean z = false;
        this.k = false;
        this.h.setAdapter(adapter);
        this.l = adapter.getItemCount();
        BannerLayoutManager bannerLayoutManager = this.i;
        if (this.l >= 3) {
            z = true;
        }
        bannerLayoutManager.c(z);
        setPlaying(true);
        this.h.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                if (i != 0) {
                    BannerLayout.this.setPlaying(false);
                }
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                int n = BannerLayout.this.i.n();
                Log.d("xxx", "onScrollStateChanged");
                if (BannerLayout.this.m != n) {
                    int unused = BannerLayout.this.m = n;
                }
                if (i == 0) {
                    BannerLayout.this.setPlaying(true);
                }
                BannerLayout.this.refreshIndicator();
            }
        });
        this.k = true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 3) {
            switch (action) {
                case 0:
                    setPlaying(false);
                    break;
                case 1:
                    break;
            }
        }
        setPlaying(true);
        return super.dispatchTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setPlaying(true);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setPlaying(false);
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 == 0) {
            setPlaying(true);
        } else {
            setPlaying(false);
        }
    }

    protected class IndicatorAdapter extends RecyclerView.Adapter {

        /* renamed from: a  reason: collision with root package name */
        int f13121a = 0;

        protected IndicatorAdapter() {
        }

        public void a(int i) {
            this.f13121a = i;
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            ImageView imageView = new ImageView(BannerLayout.this.getContext());
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-2, -2);
            layoutParams.setMargins(BannerLayout.this.g, BannerLayout.this.g, BannerLayout.this.g, BannerLayout.this.g);
            imageView.setLayoutParams(layoutParams);
            return new RecyclerView.ViewHolder(imageView) {
            };
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            ((ImageView) viewHolder.itemView).setImageDrawable(this.f13121a == i ? BannerLayout.this.d : BannerLayout.this.e);
        }

        public int getItemCount() {
            return BannerLayout.this.l;
        }
    }

    /* access modifiers changed from: protected */
    public int dp2px(int i2) {
        return (int) TypedValue.applyDimension(1, (float) i2, Resources.getSystem().getDisplayMetrics());
    }

    /* access modifiers changed from: protected */
    public synchronized void refreshIndicator() {
        if (this.b && this.l > 1) {
            this.f.a(this.m % this.l);
            this.f.notifyDataSetChanged();
        }
    }
}
