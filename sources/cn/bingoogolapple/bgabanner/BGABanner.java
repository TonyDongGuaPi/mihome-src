package cn.bingoogolapple.bgabanner;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.bingoogolapple.bgabanner.BGAViewPager;
import cn.bingoogolapple.bgabanner.transformer.BGAPageTransformer;
import cn.bingoogolapple.bgabanner.transformer.TransitionEffect;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BGABanner extends RelativeLayout implements ViewPager.OnPageChangeListener, BGAViewPager.AutoPlayDelegate {
    private static final ImageView.ScaleType[] V = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};

    /* renamed from: a  reason: collision with root package name */
    private static final int f532a = -1;
    private static final int b = -2;
    private static final int c = -2;
    private static final int d = -1;
    private static final int e = 400;
    private ImageView A;
    private ImageView.ScaleType B;
    private int C;
    /* access modifiers changed from: private */
    public List<? extends Object> D;
    /* access modifiers changed from: private */
    public Delegate E;
    /* access modifiers changed from: private */
    public Adapter F;
    private int G;
    private ViewPager.OnPageChangeListener H;
    private boolean I;
    private TextView J;
    private int K;
    private int L;
    private Drawable M;
    private boolean N;
    private int O;
    private float P;
    private boolean Q;
    private View R;
    private View S;
    /* access modifiers changed from: private */
    public GuideDelegate T;
    private boolean U;
    private BGAOnNoDoubleClickListener W;
    /* access modifiers changed from: private */
    public BGAViewPager f;
    /* access modifiers changed from: private */
    public List<View> g;
    /* access modifiers changed from: private */
    public List<View> h;
    private List<String> i;
    private LinearLayout j;
    private TextView k;
    /* access modifiers changed from: private */
    public boolean l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private Drawable v;
    private AutoPlayTask w;
    private int x;
    private float y;
    private TransitionEffect z;

    public interface Adapter<V extends View, M> {
        void a(BGABanner bGABanner, V v, @Nullable M m, int i);
    }

    public interface Delegate<V extends View, M> {
        void a(BGABanner bGABanner, V v, @Nullable M m, int i);
    }

    public interface GuideDelegate {
        void a();
    }

    public BGABanner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BGABanner(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.l = true;
        this.m = 3000;
        this.n = 800;
        this.o = 81;
        this.t = -1;
        this.u = R.drawable.bga_banner_selector_point_solid;
        this.B = ImageView.ScaleType.CENTER_CROP;
        this.C = -1;
        this.G = 2;
        this.I = false;
        this.K = -1;
        this.Q = true;
        this.U = true;
        this.W = new BGAOnNoDoubleClickListener() {
            public void a(View view) {
                if (BGABanner.this.T != null) {
                    BGABanner.this.T.a();
                }
            }
        };
        a(context);
        a(context, attributeSet);
        b(context);
    }

    private void a(Context context) {
        this.w = new AutoPlayTask();
        this.p = BGABannerUtil.a(context, 3.0f);
        this.q = BGABannerUtil.a(context, 6.0f);
        this.r = BGABannerUtil.a(context, 10.0f);
        this.s = BGABannerUtil.b(context, 10.0f);
        this.v = new ColorDrawable(Color.parseColor("#44aaaaaa"));
        this.z = TransitionEffect.Default;
        this.L = BGABannerUtil.b(context, 10.0f);
        this.O = 0;
        this.P = 0.0f;
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BGABanner);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            a(obtainStyledAttributes.getIndex(i2), obtainStyledAttributes);
        }
        obtainStyledAttributes.recycle();
    }

    private void a(int i2, TypedArray typedArray) {
        int i3;
        if (i2 == R.styleable.BGABanner_banner_pointDrawable) {
            this.u = typedArray.getResourceId(i2, R.drawable.bga_banner_selector_point_solid);
        } else if (i2 == R.styleable.BGABanner_banner_pointContainerBackground) {
            this.v = typedArray.getDrawable(i2);
        } else if (i2 == R.styleable.BGABanner_banner_pointLeftRightMargin) {
            this.p = typedArray.getDimensionPixelSize(i2, this.p);
        } else if (i2 == R.styleable.BGABanner_banner_pointContainerLeftRightPadding) {
            this.r = typedArray.getDimensionPixelSize(i2, this.r);
        } else if (i2 == R.styleable.BGABanner_banner_pointTopBottomMargin) {
            this.q = typedArray.getDimensionPixelSize(i2, this.q);
        } else if (i2 == R.styleable.BGABanner_banner_indicatorGravity) {
            this.o = typedArray.getInt(i2, this.o);
        } else if (i2 == R.styleable.BGABanner_banner_pointAutoPlayAble) {
            this.l = typedArray.getBoolean(i2, this.l);
        } else if (i2 == R.styleable.BGABanner_banner_pointAutoPlayInterval) {
            this.m = typedArray.getInteger(i2, this.m);
        } else if (i2 == R.styleable.BGABanner_banner_pageChangeDuration) {
            this.n = typedArray.getInteger(i2, this.n);
        } else if (i2 == R.styleable.BGABanner_banner_transitionEffect) {
            this.z = TransitionEffect.values()[typedArray.getInt(i2, TransitionEffect.Accordion.ordinal())];
        } else if (i2 == R.styleable.BGABanner_banner_tipTextColor) {
            this.t = typedArray.getColor(i2, this.t);
        } else if (i2 == R.styleable.BGABanner_banner_tipTextSize) {
            this.s = typedArray.getDimensionPixelSize(i2, this.s);
        } else if (i2 == R.styleable.BGABanner_banner_placeholderDrawable) {
            this.C = typedArray.getResourceId(i2, this.C);
        } else if (i2 == R.styleable.BGABanner_banner_isNumberIndicator) {
            this.I = typedArray.getBoolean(i2, this.I);
        } else if (i2 == R.styleable.BGABanner_banner_numberIndicatorTextColor) {
            this.K = typedArray.getColor(i2, this.K);
        } else if (i2 == R.styleable.BGABanner_banner_numberIndicatorTextSize) {
            this.L = typedArray.getDimensionPixelSize(i2, this.L);
        } else if (i2 == R.styleable.BGABanner_banner_numberIndicatorBackground) {
            this.M = typedArray.getDrawable(i2);
        } else if (i2 == R.styleable.BGABanner_banner_isNeedShowIndicatorOnOnlyOnePage) {
            this.N = typedArray.getBoolean(i2, this.N);
        } else if (i2 == R.styleable.BGABanner_banner_contentBottomMargin) {
            this.O = typedArray.getDimensionPixelSize(i2, this.O);
        } else if (i2 == R.styleable.BGABanner_banner_aspectRatio) {
            this.P = typedArray.getFloat(i2, this.P);
        } else if (i2 == R.styleable.BGABanner_android_scaleType && (i3 = typedArray.getInt(i2, -1)) >= 0 && i3 < V.length) {
            this.B = V[i3];
        }
    }

    private void b(Context context) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        if (Build.VERSION.SDK_INT >= 16) {
            relativeLayout.setBackground(this.v);
        } else {
            relativeLayout.setBackgroundDrawable(this.v);
        }
        relativeLayout.setPadding(this.r, this.q, this.r, this.q);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        if ((this.o & 112) == 48) {
            layoutParams.addRule(10);
        } else {
            layoutParams.addRule(12);
        }
        addView(relativeLayout, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        if (this.I) {
            this.J = new TextView(context);
            this.J.setId(R.id.banner_indicatorId);
            this.J.setGravity(16);
            this.J.setSingleLine(true);
            this.J.setEllipsize(TextUtils.TruncateAt.END);
            this.J.setTextColor(this.K);
            this.J.setTextSize(0, (float) this.L);
            this.J.setVisibility(4);
            if (this.M != null) {
                if (Build.VERSION.SDK_INT >= 16) {
                    this.J.setBackground(this.M);
                } else {
                    this.J.setBackgroundDrawable(this.M);
                }
            }
            relativeLayout.addView(this.J, layoutParams2);
        } else {
            this.j = new LinearLayout(context);
            this.j.setId(R.id.banner_indicatorId);
            this.j.setOrientation(0);
            this.j.setGravity(16);
            relativeLayout.addView(this.j, layoutParams2);
        }
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(15);
        this.k = new TextView(context);
        this.k.setGravity(16);
        this.k.setSingleLine(true);
        this.k.setEllipsize(TextUtils.TruncateAt.END);
        this.k.setTextColor(this.t);
        this.k.setTextSize(0, (float) this.s);
        relativeLayout.addView(this.k, layoutParams3);
        int i2 = this.o & 7;
        if (i2 == 3) {
            layoutParams2.addRule(9);
            layoutParams3.addRule(1, R.id.banner_indicatorId);
            this.k.setGravity(21);
        } else if (i2 == 5) {
            layoutParams2.addRule(11);
            layoutParams3.addRule(0, R.id.banner_indicatorId);
        } else {
            layoutParams2.addRule(14);
            layoutParams3.addRule(0, R.id.banner_indicatorId);
        }
        showPlaceholder();
    }

    public void showPlaceholder() {
        if (this.A == null && this.C != -1) {
            this.A = BGABannerUtil.a(getContext(), this.C, new BGALocalImageSize(720, 360, 640.0f, 320.0f), this.B);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.setMargins(0, 0, 0, this.O);
            addView(this.A, layoutParams);
        }
    }

    public void setPageChangeDuration(int i2) {
        if (i2 >= 0 && i2 <= 2000) {
            this.n = i2;
            if (this.f != null) {
                this.f.setPageChangeDuration(i2);
            }
        }
    }

    public void setAutoPlayAble(boolean z2) {
        this.l = z2;
        stopAutoPlay();
        if (this.f != null && this.f.getAdapter() != null) {
            this.f.getAdapter().notifyDataSetChanged();
        }
    }

    public void setAutoPlayInterval(int i2) {
        this.m = i2;
    }

    public void setData(List<View> list, List<? extends Object> list2, List<String> list3) {
        if (BGABannerUtil.a((Collection) list, new Collection[0])) {
            this.l = false;
            list = new ArrayList<>();
            list2 = new ArrayList<>();
            list3 = new ArrayList<>();
        }
        if (this.l && list.size() < 3 && this.g == null) {
            this.l = false;
        }
        this.D = list2;
        this.h = list;
        this.i = list3;
        a();
        b();
        removePlaceholder();
    }

    public void setData(@LayoutRes int i2, List<? extends Object> list, List<String> list2) {
        this.h = new ArrayList();
        if (list == null) {
            list = new ArrayList<>();
            list2 = new ArrayList<>();
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            this.h.add(View.inflate(getContext(), i2, (ViewGroup) null));
        }
        if (this.l && this.h.size() < 3) {
            this.g = new ArrayList(this.h);
            this.g.add(View.inflate(getContext(), i2, (ViewGroup) null));
            if (this.g.size() == 2) {
                this.g.add(View.inflate(getContext(), i2, (ViewGroup) null));
            }
        }
        setData(this.h, list, list2);
    }

    public void setData(List<? extends Object> list, List<String> list2) {
        setData(R.layout.bga_banner_item_image, list, list2);
    }

    public void setData(List<View> list) {
        setData(list, (List<? extends Object>) null, (List<String>) null);
    }

    public void setData(@Nullable BGALocalImageSize bGALocalImageSize, @Nullable ImageView.ScaleType scaleType, @DrawableRes int... iArr) {
        if (bGALocalImageSize == null) {
            bGALocalImageSize = new BGALocalImageSize(720, 1280, 320.0f, 640.0f);
        }
        if (scaleType != null) {
            this.B = scaleType;
        }
        ArrayList arrayList = new ArrayList();
        for (int a2 : iArr) {
            arrayList.add(BGABannerUtil.a(getContext(), a2, bGALocalImageSize, this.B));
        }
        setData(arrayList);
    }

    public void setAllowUserScrollable(boolean z2) {
        this.Q = z2;
        if (this.f != null) {
            this.f.setAllowUserScrollable(this.Q);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.H = onPageChangeListener;
    }

    public void setEnterSkipViewId(int i2, int i3) {
        if (i2 != 0) {
            this.S = ((Activity) getContext()).findViewById(i2);
        }
        if (i3 != 0) {
            this.R = ((Activity) getContext()).findViewById(i3);
        }
    }

    public void setEnterSkipViewIdAndDelegate(int i2, int i3, GuideDelegate guideDelegate) {
        if (guideDelegate != null) {
            this.T = guideDelegate;
            if (i2 != 0) {
                this.S = ((Activity) getContext()).findViewById(i2);
                this.S.setOnClickListener(this.W);
            }
            if (i3 != 0) {
                this.R = ((Activity) getContext()).findViewById(i3);
                this.R.setOnClickListener(this.W);
            }
        }
    }

    public int getCurrentItem() {
        if (this.f == null || BGABannerUtil.a((Collection) this.h, new Collection[0])) {
            return -1;
        }
        return this.f.getCurrentItem() % this.h.size();
    }

    public int getItemCount() {
        if (this.h == null) {
            return 0;
        }
        return this.h.size();
    }

    public List<? extends View> getViews() {
        return this.h;
    }

    public <VT extends View> VT getItemView(int i2) {
        if (this.h == null) {
            return null;
        }
        return (View) this.h.get(i2);
    }

    public ImageView getItemImageView(int i2) {
        return (ImageView) getItemView(i2);
    }

    public List<String> getTips() {
        return this.i;
    }

    public BGAViewPager getViewPager() {
        return this.f;
    }

    public void setOverScrollMode(int i2) {
        this.G = i2;
        if (this.f != null) {
            this.f.setOverScrollMode(this.G);
        }
    }

    private void a() {
        if (this.j != null) {
            this.j.removeAllViews();
            if (this.N || (!this.N && this.h.size() > 1)) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.setMargins(this.p, 0, this.p, 0);
                for (int i2 = 0; i2 < this.h.size(); i2++) {
                    ImageView imageView = new ImageView(getContext());
                    imageView.setLayoutParams(layoutParams);
                    imageView.setImageResource(this.u);
                    this.j.addView(imageView);
                }
            }
        }
        if (this.J == null) {
            return;
        }
        if (this.N || (!this.N && this.h.size() > 1)) {
            this.J.setVisibility(0);
        } else {
            this.J.setVisibility(4);
        }
    }

    private void b() {
        if (this.f != null && equals(this.f.getParent())) {
            removeView(this.f);
            this.f = null;
        }
        this.f = new BGAViewPager(getContext());
        this.f.setOffscreenPageLimit(1);
        this.f.setAdapter(new PageAdapter());
        this.f.addOnPageChangeListener(this);
        this.f.setOverScrollMode(this.G);
        this.f.setAllowUserScrollable(this.Q);
        this.f.setPageTransformer(true, BGAPageTransformer.a(this.z));
        setPageChangeDuration(this.n);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, 0, 0, this.O);
        addView(this.f, 0, layoutParams);
        if (this.l) {
            this.f.setAutoPlayDelegate(this);
            this.f.setCurrentItem(1073741823 - (1073741823 % this.h.size()));
            startAutoPlay();
            return;
        }
        a(0);
    }

    public void removePlaceholder() {
        if (this.A != null && equals(this.A.getParent())) {
            removeView(this.A);
            this.A = null;
        }
    }

    public void setAspectRatio(float f2) {
        this.P = f2;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (this.P > 0.0f) {
            i3 = View.MeasureSpec.makeMeasureSpec((int) (((float) View.MeasureSpec.getSize(i2)) / this.P), 1073741824);
        }
        super.onMeasure(i2, i3);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.l) {
            int action = motionEvent.getAction();
            if (action != 3) {
                switch (action) {
                    case 0:
                        stopAutoPlay();
                        break;
                    case 1:
                        break;
                }
            }
            startAutoPlay();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setIsNeedShowIndicatorOnOnlyOnePage(boolean z2) {
        this.N = z2;
    }

    public void setCurrentItem(int i2) {
        if (this.f != null && this.h != null) {
            if (i2 <= getItemCount() - 1) {
                if (this.l) {
                    int currentItem = this.f.getCurrentItem();
                    int size = i2 - (currentItem % this.h.size());
                    if (size < 0) {
                        for (int i3 = -1; i3 >= size; i3--) {
                            this.f.setCurrentItem(currentItem + i3, false);
                        }
                    } else if (size > 0) {
                        for (int i4 = 1; i4 <= size; i4++) {
                            this.f.setCurrentItem(currentItem + i4, false);
                        }
                    }
                    startAutoPlay();
                    return;
                }
                this.f.setCurrentItem(i2, false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i2) {
        super.onVisibilityChanged(view, i2);
        if (i2 == 0) {
            startAutoPlay();
        } else if (i2 == 4 || i2 == 8) {
            c();
        }
    }

    private void c() {
        stopAutoPlay();
        if (!this.U && this.l && this.f != null && getItemCount() > 0 && this.y != 0.0f) {
            this.f.setCurrentItem(this.f.getCurrentItem() - 1);
            this.f.setCurrentItem(this.f.getCurrentItem() + 1);
        }
        this.U = false;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAutoPlay();
    }

    public void startAutoPlay() {
        stopAutoPlay();
        if (this.l) {
            postDelayed(this.w, (long) this.m);
        }
    }

    public void stopAutoPlay() {
        if (this.w != null) {
            removeCallbacks(this.w);
        }
    }

    private void a(int i2) {
        if (this.k != null) {
            if (this.i == null || this.i.size() < 1 || i2 >= this.i.size()) {
                this.k.setVisibility(8);
            } else {
                this.k.setVisibility(0);
                this.k.setText(this.i.get(i2));
            }
        }
        if (this.j != null) {
            if (this.h == null || this.h.size() <= 0 || i2 >= this.h.size() || (!this.N && (this.N || this.h.size() <= 1))) {
                this.j.setVisibility(8);
            } else {
                this.j.setVisibility(0);
                int i3 = 0;
                while (i3 < this.j.getChildCount()) {
                    this.j.getChildAt(i3).setEnabled(i3 == i2);
                    this.j.getChildAt(i3).requestLayout();
                    i3++;
                }
            }
        }
        if (this.J == null) {
            return;
        }
        if (this.h == null || this.h.size() <= 0 || i2 >= this.h.size() || (!this.N && (this.N || this.h.size() <= 1))) {
            this.J.setVisibility(8);
            return;
        }
        this.J.setVisibility(0);
        TextView textView = this.J;
        textView.setText((i2 + 1) + "/" + this.h.size());
    }

    public void setTransitionEffect(TransitionEffect transitionEffect) {
        this.z = transitionEffect;
        if (this.f != null) {
            b();
            if (this.g == null) {
                BGABannerUtil.a(this.h);
            } else {
                BGABannerUtil.a(this.g);
            }
        }
    }

    public void setPageTransformer(ViewPager.PageTransformer pageTransformer) {
        if (pageTransformer != null && this.f != null) {
            this.f.setPageTransformer(true, pageTransformer);
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.f != null) {
            this.f.setCurrentItem(this.f.getCurrentItem() + 1);
        }
    }

    public void handleAutoPlayActionUpOrCancel(float f2) {
        if (this.f == null) {
            return;
        }
        if (this.x < this.f.getCurrentItem()) {
            if (f2 > 400.0f || (this.y < 0.7f && f2 > -400.0f)) {
                this.f.setBannerCurrentItemInternal(this.x, true);
            } else {
                this.f.setBannerCurrentItemInternal(this.x + 1, true);
            }
        } else if (f2 < -400.0f || (this.y > 0.3f && f2 < 400.0f)) {
            this.f.setBannerCurrentItemInternal(this.x + 1, true);
        } else {
            this.f.setBannerCurrentItemInternal(this.x, true);
        }
    }

    public void onPageSelected(int i2) {
        int size = i2 % this.h.size();
        a(size);
        if (this.H != null) {
            this.H.onPageSelected(size);
        }
    }

    public void onPageScrolled(int i2, float f2, int i3) {
        a(i2, f2);
        this.x = i2;
        this.y = f2;
        if (this.k != null) {
            if (BGABannerUtil.b((Collection) this.i, new Collection[0])) {
                this.k.setVisibility(0);
                int size = i2 % this.i.size();
                int size2 = (i2 + 1) % this.i.size();
                if (size2 < this.i.size() && size < this.i.size()) {
                    if (((double) f2) > 0.5d) {
                        this.k.setText(this.i.get(size2));
                        ViewCompat.setAlpha(this.k, f2);
                    } else {
                        ViewCompat.setAlpha(this.k, 1.0f - f2);
                        this.k.setText(this.i.get(size));
                    }
                }
            } else {
                this.k.setVisibility(8);
            }
        }
        if (this.H != null) {
            this.H.onPageScrolled(i2 % this.h.size(), f2, i3);
        }
    }

    public void onPageScrollStateChanged(int i2) {
        if (this.H != null) {
            this.H.onPageScrollStateChanged(i2);
        }
    }

    private void a(int i2, float f2) {
        if (this.S != null || this.R != null) {
            if (i2 == getItemCount() - 2) {
                if (this.S != null) {
                    ViewCompat.setAlpha(this.S, f2);
                }
                if (this.R != null) {
                    ViewCompat.setAlpha(this.R, 1.0f - f2);
                }
                if (f2 > 0.5f) {
                    if (this.S != null) {
                        this.S.setVisibility(0);
                    }
                    if (this.R != null) {
                        this.R.setVisibility(8);
                        return;
                    }
                    return;
                }
                if (this.S != null) {
                    this.S.setVisibility(8);
                }
                if (this.R != null) {
                    this.R.setVisibility(0);
                }
            } else if (i2 == getItemCount() - 1) {
                if (this.R != null) {
                    this.R.setVisibility(8);
                }
                if (this.S != null) {
                    this.S.setVisibility(0);
                    ViewCompat.setAlpha(this.S, 1.0f);
                }
            } else {
                if (this.R != null) {
                    this.R.setVisibility(0);
                    ViewCompat.setAlpha(this.R, 1.0f);
                }
                if (this.S != null) {
                    this.S.setVisibility(8);
                }
            }
        }
    }

    public void setDelegate(Delegate delegate) {
        this.E = delegate;
    }

    public void setAdapter(Adapter adapter) {
        this.F = adapter;
    }

    private class PageAdapter extends PagerAdapter {
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        private PageAdapter() {
        }

        public int getCount() {
            if (BGABanner.this.h == null) {
                return 0;
            }
            if (BGABanner.this.l) {
                return Integer.MAX_VALUE;
            }
            return BGABanner.this.h.size();
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view;
            if (BGABannerUtil.a((Collection) BGABanner.this.h, new Collection[0])) {
                return null;
            }
            int size = i % BGABanner.this.h.size();
            if (BGABanner.this.g == null) {
                view = (View) BGABanner.this.h.get(size);
            } else {
                view = (View) BGABanner.this.g.get(i % BGABanner.this.g.size());
            }
            if (BGABanner.this.E != null) {
                view.setOnClickListener(new BGAOnNoDoubleClickListener() {
                    public void a(View view) {
                        int currentItem = BGABanner.this.f.getCurrentItem() % BGABanner.this.h.size();
                        if (BGABannerUtil.a(currentItem, (Collection) BGABanner.this.D)) {
                            BGABanner.this.E.a(BGABanner.this, view, BGABanner.this.D.get(currentItem), currentItem);
                        } else if (BGABannerUtil.a((Collection) BGABanner.this.D, new Collection[0])) {
                            BGABanner.this.E.a(BGABanner.this, view, null, currentItem);
                        }
                    }
                });
            }
            if (BGABanner.this.F != null) {
                if (BGABannerUtil.a(size, (Collection) BGABanner.this.D)) {
                    BGABanner.this.F.a(BGABanner.this, view, BGABanner.this.D.get(size), size);
                } else if (BGABannerUtil.a((Collection) BGABanner.this.D, new Collection[0])) {
                    BGABanner.this.F.a(BGABanner.this, view, null, size);
                }
            }
            ViewParent parent = view.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(view);
            }
            viewGroup.addView(view);
            return view;
        }
    }

    private static class AutoPlayTask implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<BGABanner> f534a;

        private AutoPlayTask(BGABanner bGABanner) {
            this.f534a = new WeakReference<>(bGABanner);
        }

        public void run() {
            BGABanner bGABanner = (BGABanner) this.f534a.get();
            if (bGABanner != null) {
                bGABanner.d();
                bGABanner.startAutoPlay();
            }
        }
    }
}
