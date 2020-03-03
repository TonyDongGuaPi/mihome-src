package com.miuipub.internal.widget;

import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.miuipub.internal.view.ActionBarPolicy;
import miuipub.app.ActionBar;
import miuipub.v6.R;

public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemClickListener, ActionBar.FragmentViewPagerChangeListener {

    /* renamed from: a  reason: collision with root package name */
    private static final int f8348a = 300;
    private final LayoutInflater b;
    private TabClickListener c;
    /* access modifiers changed from: private */
    public LinearLayout d;
    private Spinner e;
    private boolean f;
    private int g;
    private int h;
    private Bitmap i;
    private boolean j;
    private float k;
    private Paint l = new Paint();
    private boolean m;
    int mMaxTabWidth;
    int mStackedTabMaxWidth;
    Runnable mTabSelector;
    private ObjectAnimator n;

    public void onPageScrollStateChanged(int i2) {
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        this.b = LayoutInflater.from(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, R.styleable.V6_ActionBar, 16843470, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.V6_ActionBar_v6_tabIndicator);
        this.j = obtainStyledAttributes.getBoolean(R.styleable.V6_ActionBar_v6_translucentTabIndicator, true);
        this.i = a(drawable);
        obtainStyledAttributes.recycle();
        if (this.j) {
            this.l.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }
        setHorizontalScrollBarEnabled(false);
        ActionBarPolicy a2 = ActionBarPolicy.a(context);
        setContentHeight(a2.f());
        this.mStackedTabMaxWidth = a2.h();
        this.d = (LinearLayout) this.b.inflate(R.layout.v6_action_bar_tabbar, this, false);
        addView(this.d, new FrameLayout.LayoutParams(-2, -1));
    }

    public void setFragmentViewPagerMode(boolean z) {
        this.m = z;
    }

    private Bitmap a(Drawable drawable) {
        Bitmap bitmap;
        if (drawable == null) {
            return null;
        }
        if (this.j) {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ALPHA_8);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public void draw(Canvas canvas) {
        canvas.saveLayer(0.0f, 0.0f, (float) (getWidth() + getScrollX()), (float) getHeight(), (Paint) null, 31);
        super.draw(canvas);
        if (this.i != null) {
            canvas.drawBitmap(this.i, this.k, (float) (getHeight() - this.i.getHeight()), this.l);
        }
        canvas.restore();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (this.d.getChildAt(this.h) != null) {
            setTabIndicatorPosition(this.h);
        }
    }

    public void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        boolean z = true;
        boolean z2 = mode == 1073741824;
        setFillViewport(z2);
        int childCount = this.d.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.mMaxTabWidth = -1;
        } else {
            if (childCount > 2) {
                this.mMaxTabWidth = (int) (((float) View.MeasureSpec.getSize(i2)) * 0.4f);
            } else {
                this.mMaxTabWidth = (int) (((float) View.MeasureSpec.getSize(i2)) * 0.6f);
            }
            this.mMaxTabWidth = Math.min(this.mMaxTabWidth, this.mStackedTabMaxWidth);
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.g, 1073741824);
        if (z2 || !this.f) {
            z = false;
        }
        if (z) {
            this.d.measure(0, makeMeasureSpec);
            if (this.d.getMeasuredWidth() > View.MeasureSpec.getSize(i2)) {
                b();
            } else {
                c();
            }
        } else {
            c();
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i2, makeMeasureSpec);
        int measuredWidth2 = getMeasuredWidth();
        if (z2 && measuredWidth != measuredWidth2) {
            setTabSelected(this.h);
        }
    }

    public float getTabIndicatorPosition() {
        return this.k;
    }

    public void setTabIndicatorPosition(int i2) {
        setTabIndicatorPosition(i2, 0.0f);
    }

    public void setTabIndicatorPosition(int i2, float f2) {
        float f3;
        if (this.i != null) {
            View childAt = this.d.getChildAt(i2);
            View childAt2 = this.d.getChildAt(i2 + 1);
            if (childAt2 == null) {
                f3 = (float) childAt.getWidth();
            } else {
                f3 = ((float) (childAt.getWidth() + childAt2.getWidth())) / 2.0f;
            }
            this.k = ((float) (childAt.getLeft() + ((childAt.getWidth() - this.i.getWidth()) / 2))) + (f3 * f2);
            invalidate();
        }
    }

    private boolean a() {
        return this.e != null && this.e.getParent() == this;
    }

    public void setAllowCollapse(boolean z) {
        this.f = z;
    }

    private void b() {
        if (!a()) {
            if (this.e == null) {
                this.e = d();
            }
            removeView(this.d);
            addView(this.e, new ViewGroup.LayoutParams(-2, -1));
            if (this.e.getAdapter() == null) {
                this.e.setAdapter(new TabAdapter());
            }
            if (this.mTabSelector != null) {
                removeCallbacks(this.mTabSelector);
                this.mTabSelector = null;
            }
            this.e.setSelection(this.h);
        }
    }

    private boolean c() {
        if (!a()) {
            return false;
        }
        removeView(this.e);
        addView(this.d, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.e.getSelectedItemPosition());
        return false;
    }

    public void setTabSelected(int i2) {
        this.h = i2;
        int childCount = this.d.getChildCount();
        int i3 = 0;
        while (i3 < childCount) {
            View childAt = this.d.getChildAt(i3);
            boolean z = i3 == i2;
            childAt.setSelected(z);
            if (z) {
                animateToTab(i2);
            }
            i3++;
        }
    }

    public void setContentHeight(int i2) {
        this.g = i2;
        requestLayout();
    }

    private Spinner d() {
        Spinner spinner = new Spinner(getContext(), (AttributeSet) null, 16843479);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        return spinner;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        ActionBarPolicy a2 = ActionBarPolicy.a(getContext());
        setContentHeight(a2.f());
        this.mStackedTabMaxWidth = a2.h();
    }

    public void animateToTab(int i2) {
        final View childAt = this.d.getChildAt(i2);
        if (this.mTabSelector != null) {
            removeCallbacks(this.mTabSelector);
        }
        this.mTabSelector = new Runnable() {
            public void run() {
                ScrollingTabContainerView.this.smoothScrollTo(childAt.getLeft() - ((ScrollingTabContainerView.this.getWidth() - childAt.getWidth()) / 2), 0);
                ScrollingTabContainerView.this.mTabSelector = null;
            }
        };
        post(this.mTabSelector);
    }

    private void a(int i2) {
        a(this.d.getChildAt(i2));
    }

    private void a(View view) {
        if (this.i != null && view != null && view.getWidth() > 0 && !this.m) {
            if (this.n != null) {
                this.n.cancel();
                this.n = null;
            }
            this.n = ObjectAnimator.ofFloat(this, "IndicatorPosition", new float[]{(float) (view.getLeft() + ((view.getWidth() - this.i.getWidth()) / 2))});
            this.n.setDuration(300);
            this.n.start();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mTabSelector != null) {
            post(this.mTabSelector);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mTabSelector != null) {
            removeCallbacks(this.mTabSelector);
        }
    }

    /* access modifiers changed from: private */
    public TabView a(ActionBar.Tab tab, boolean z) {
        TabView tabView = (TabView) this.b.inflate(R.layout.v6_action_bar_tab, this.d, false);
        tabView.attach(this, tab, z);
        if (z) {
            tabView.setBackgroundDrawable((Drawable) null);
            tabView.setLayoutParams(new AbsListView.LayoutParams(-1, this.g));
        } else {
            tabView.setFocusable(true);
            if (this.c == null) {
                this.c = new TabClickListener();
            }
            tabView.setOnClickListener(this.c);
        }
        return tabView;
    }

    public void addTab(ActionBar.Tab tab, boolean z) {
        TabView a2 = a(tab, false);
        this.d.addView(a2);
        if (this.e != null) {
            ((TabAdapter) this.e.getAdapter()).notifyDataSetChanged();
        }
        if (z) {
            a2.setSelected(true);
        }
        if (this.f) {
            requestLayout();
        }
    }

    public void addTab(ActionBar.Tab tab, int i2, boolean z) {
        TabView a2 = a(tab, false);
        this.d.addView(a2, i2);
        if (this.e != null) {
            ((TabAdapter) this.e.getAdapter()).notifyDataSetChanged();
        }
        if (z) {
            a2.setSelected(true);
        }
        if (this.f) {
            requestLayout();
        }
    }

    public void updateTab(int i2) {
        ((TabView) this.d.getChildAt(i2)).update();
        if (this.e != null) {
            ((TabAdapter) this.e.getAdapter()).notifyDataSetChanged();
        }
        if (this.f) {
            requestLayout();
        }
    }

    public void removeTabAt(int i2) {
        this.d.removeViewAt(i2);
        if (this.e != null) {
            ((TabAdapter) this.e.getAdapter()).notifyDataSetChanged();
        }
        if (this.f) {
            requestLayout();
        }
    }

    public void removeAllTabs() {
        this.d.removeAllViews();
        if (this.e != null) {
            ((TabAdapter) this.e.getAdapter()).notifyDataSetChanged();
        }
        if (this.f) {
            requestLayout();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        ((TabView) view).getTab().select();
    }

    public void onPageScrolled(int i2, float f2, boolean z, boolean z2) {
        setTabIndicatorPosition(i2, f2);
    }

    public void onPageSelected(int i2) {
        View childAt = this.d.getChildAt(i2);
        if (childAt != null) {
            childAt.sendAccessibilityEvent(4);
        }
        setTabIndicatorPosition(i2);
    }

    public static class TabView extends LinearLayout {

        /* renamed from: a  reason: collision with root package name */
        private ActionBar.Tab f8352a;
        private TextView b;
        private ImageView c;
        private View d;
        private ScrollingTabContainerView e;

        public TabView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* access modifiers changed from: package-private */
        public void attach(ScrollingTabContainerView scrollingTabContainerView, ActionBar.Tab tab, boolean z) {
            this.e = scrollingTabContainerView;
            this.f8352a = tab;
            if (z) {
                setGravity(19);
            }
            update();
        }

        public void bindTab(ActionBar.Tab tab) {
            this.f8352a = tab;
            update();
        }

        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            int i3 = this.e != null ? this.e.mMaxTabWidth : 0;
            if (i3 > 0 && getMeasuredWidth() > i3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
            }
        }

        public void update() {
            ActionBar.Tab tab = this.f8352a;
            View customView = tab.getCustomView();
            if (customView != null) {
                ViewParent parent = customView.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(customView);
                    }
                    addView(customView);
                }
                this.d = customView;
                if (this.b != null) {
                    this.b.setVisibility(8);
                }
                if (this.c != null) {
                    this.c.setVisibility(8);
                    this.c.setImageDrawable((Drawable) null);
                    return;
                }
                return;
            }
            if (this.d != null) {
                removeView(this.d);
                this.d = null;
            }
            Drawable icon = tab.getIcon();
            CharSequence text = tab.getText();
            if (icon != null) {
                if (this.c == null) {
                    ImageView imageView = new ImageView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    imageView.setLayoutParams(layoutParams);
                    addView(imageView, 0);
                    this.c = imageView;
                }
                this.c.setImageDrawable(icon);
                this.c.setVisibility(0);
            } else if (this.c != null) {
                this.c.setVisibility(8);
                this.c.setImageDrawable((Drawable) null);
            }
            if (text != null) {
                if (this.b == null) {
                    TextView textView = new TextView(getContext(), (AttributeSet) null, 16843509);
                    textView.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    textView.setLayoutParams(layoutParams2);
                    addView(textView);
                    this.b = textView;
                }
                this.b.setText(text);
                this.b.setVisibility(0);
            } else if (this.b != null) {
                this.b.setVisibility(8);
                this.b.setText((CharSequence) null);
            }
            if (this.c != null) {
                this.c.setContentDescription(tab.getContentDescription());
            }
        }

        public ActionBar.Tab getTab() {
            return this.f8352a;
        }
    }

    private class TabAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        private TabAdapter() {
        }

        public int getCount() {
            return ScrollingTabContainerView.this.d.getChildCount();
        }

        public Object getItem(int i) {
            return ((TabView) ScrollingTabContainerView.this.d.getChildAt(i)).getTab();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                return ScrollingTabContainerView.this.a((ActionBar.Tab) getItem(i), true);
            }
            ((TabView) view).bindTab((ActionBar.Tab) getItem(i));
            return view;
        }
    }

    private class TabClickListener implements View.OnClickListener {
        private TabClickListener() {
        }

        public void onClick(View view) {
            ((TabView) view).getTab().select();
            int childCount = ScrollingTabContainerView.this.d.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = ScrollingTabContainerView.this.d.getChildAt(i);
                childAt.setSelected(childAt == view);
            }
        }
    }
}
