package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.RedPointManager;
import com.xiaomi.smarthome.TabRedPointListener;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.widget.ViewPager;
import com.xiaomi.smarthome.messagecenter.MessageCenterListener;
import java.util.ArrayList;

public class TabPageIndicator extends HorizontalScrollView implements TabRedPointListener, PageIndicator, MessageCenterListener {
    public static final String PREF_KEY_MY_HOME_RED_DOT_CLICKED = "my_home_red_dot_clicked";

    /* renamed from: a  reason: collision with root package name */
    private static final CharSequence f18937a = "";
    /* access modifiers changed from: private */
    public Runnable b;
    private int c;
    private final View.OnClickListener d;
    private final IcsLinearLayout e;
    /* access modifiers changed from: private */
    public ViewPager f;
    private ViewPager.OnPageChangeListener g;
    /* access modifiers changed from: private */
    public int h;
    private int i;
    private int j;
    /* access modifiers changed from: private */
    public OnTabReselectedListener k;
    private Bitmap l;
    private Object m;
    boolean mShowFeedbackDot;
    boolean mShowMessageDot;
    boolean mShowSignNotifyDot;
    boolean mShowUpdateDot;
    private RedPointManager.RedPointListener n;
    private RedPointManager.RedPointListener o;
    private RedPointManager.RedPointListener p;
    private RedPointManager.RedPointListener q;
    private ArrayList<TabView> r;
    /* access modifiers changed from: private */
    public TabView s;
    private TabView t;

    public interface OnTabReselectedListener {
        void a(int i);
    }

    /* access modifiers changed from: package-private */
    public Bitmap getPointBitmap() {
        Bitmap bitmap;
        synchronized (this.m) {
            if (this.l == null) {
                this.l = BitmapFactory.decodeResource(getResources(), R.drawable.common_point_now);
            }
            bitmap = this.l;
        }
        return bitmap;
    }

    public TabPageIndicator(Context context) {
        this(context, (AttributeSet) null);
    }

    public TabPageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new View.OnClickListener() {
            public void onClick(View view) {
                TabView tabView = (TabView) view;
                if (tabView == TabPageIndicator.this.s) {
                    PreferenceUtils.b("my_home_red_dot_clicked", true);
                    TabPageIndicator.this.s.invalidate();
                }
                int currentItem = TabPageIndicator.this.f.getCurrentItem();
                int index = tabView.getIndex();
                TabPageIndicator.this.f.setCurrentItem(index, false);
                if (currentItem == index && TabPageIndicator.this.k != null) {
                    TabPageIndicator.this.k.a(index);
                }
                try {
                    StatHelper.a(TabPageIndicator.this.f.getAdapter().a(), index);
                } catch (Exception unused) {
                }
            }
        };
        this.m = new Object();
        this.n = new RedPointManager.RedPointListener() {
            public boolean a() {
                return TabPageIndicator.this.mShowMessageDot;
            }
        };
        this.o = new RedPointManager.RedPointListener() {
            public boolean a() {
                return TabPageIndicator.this.mShowUpdateDot;
            }
        };
        this.p = new RedPointManager.RedPointListener() {
            public boolean a() {
                return TabPageIndicator.this.mShowFeedbackDot;
            }
        };
        this.q = new RedPointManager.RedPointListener() {
            public boolean a() {
                return TabPageIndicator.this.mShowSignNotifyDot;
            }
        };
        this.r = new ArrayList<>();
        this.mShowMessageDot = false;
        this.mShowUpdateDot = false;
        this.mShowFeedbackDot = false;
        this.mShowSignNotifyDot = false;
        setHorizontalScrollBarEnabled(false);
        this.e = new IcsLinearLayout(context, R.attr.vpiTabPageIndicatorStyle);
        addView(this.e, new ViewGroup.LayoutParams(-2, -1));
    }

    public void setOnTabReselectedListener(OnTabReselectedListener onTabReselectedListener) {
        this.k = onTabReselectedListener;
    }

    public void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        int childCount = this.e.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.h = -1;
        } else if (childCount > 2) {
            this.h = (int) (((float) View.MeasureSpec.getSize(i2)) * 0.25f);
        } else {
            this.h = View.MeasureSpec.getSize(i2) / 2;
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i2, i3);
        int measuredWidth2 = getMeasuredWidth();
        if (z && measuredWidth != measuredWidth2) {
            setCurrentItem(this.j);
        }
    }

    private void a(int i2) {
        final View childAt = this.e.getChildAt(i2);
        if (this.b != null) {
            removeCallbacks(this.b);
        }
        this.b = new Runnable() {
            public void run() {
                TabPageIndicator.this.smoothScrollTo(childAt.getLeft() - ((TabPageIndicator.this.getWidth() - childAt.getWidth()) / 2), 0);
                Runnable unused = TabPageIndicator.this.b = null;
            }
        };
        post(this.b);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.b != null) {
            post(this.b);
        }
        RedPointManager.a().a("red_point_setting_page", this.n);
        RedPointManager.a().a("red_point_setting_page", this.o);
        RedPointManager.a().a("red_point_setting_page", this.p);
        RedPointManager.a().a("red_point_setting_page", this.q);
        RedPointManager.a().b("red_point_setting_page");
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.b != null) {
            removeCallbacks(this.b);
        }
        RedPointManager.a().b("red_point_setting_page", this.n);
        RedPointManager.a().b("red_point_setting_page", this.o);
        RedPointManager.a().b("red_point_setting_page", this.p);
        RedPointManager.a().b("red_point_setting_page", this.q);
        RedPointManager.a().a("red_point_setting_page");
        RedPointManager.a().a("red_point_shop_page");
        RedPointManager.a().b();
    }

    private void a(int i2, CharSequence charSequence, int i3, int i4) {
        TabView tabView = new TabView(getContext());
        int unused = tabView.f18946a = i2;
        tabView.setFocusable(true);
        tabView.setOnClickListener(this.d);
        tabView.d.setText(charSequence);
        tabView.d.setVisibility(0);
        tabView.c.setVisibility(8);
        if (i3 != 0) {
            tabView.d.setCompoundDrawablesWithIntrinsicBounds(0, i3, 0, 0);
            tabView.d.setCompoundDrawablePadding(-12);
        }
        if (i2 == 1) {
            this.t = tabView;
            RedPointManager.a().a("red_point_shop_page", (RedPointManager.RedPointAction) new RedPointManager.RedPointAction() {
                public void a(boolean z) {
                    TabPageIndicator.this.updateTabviewShopDot(z);
                }
            });
        }
        if (i2 == i4 - 1) {
            this.s = tabView;
            RedPointManager.a().a("red_point_setting_page", (RedPointManager.RedPointAction) new RedPointManager.RedPointAction() {
                public void a(boolean z) {
                    TabPageIndicator.this.updateTabViewSettingDot(z);
                }
            });
        }
        this.e.addView(tabView, new LinearLayout.LayoutParams(0, -1, 1.0f));
        this.r.add(i2, tabView);
    }

    public void onPageScrollStateChanged(int i2) {
        if (this.g != null) {
            this.g.onPageScrollStateChanged(i2);
        }
    }

    public void onPageScrolled(int i2, float f2, int i3) {
        if (this.g != null) {
            this.g.onPageScrolled(i2, f2, i3);
        }
    }

    public void onPageSelected(int i2) {
        setCurrentItem(i2);
        if (this.g != null) {
            this.g.onPageSelected(i2);
        }
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.f != viewPager) {
            if (this.f != null) {
                this.f.setOnPageChangeListener((ViewPager.OnPageChangeListener) null);
            }
            if (viewPager.getAdapter() != null) {
                this.f = viewPager;
                viewPager.setOnPageChangeListener(this);
                notifyDataSetChanged();
                return;
            }
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
    }

    public void notifyDataSetChanged() {
        this.e.removeAllViews();
        PagerAdapter adapter = this.f.getAdapter();
        IconPagerAdapter iconPagerAdapter = adapter instanceof IconPagerAdapter ? (IconPagerAdapter) adapter : null;
        int a2 = adapter.a();
        for (int i2 = 0; i2 < a2; i2++) {
            CharSequence b2 = adapter.b(i2);
            if (b2 == null) {
                b2 = f18937a;
            }
            a(i2, b2, iconPagerAdapter != null ? iconPagerAdapter.c(i2) : 0, a2);
        }
        if (this.j > a2) {
            this.j = a2 - 1;
        }
        setCurrentItem(this.j);
        requestLayout();
    }

    public void setViewPager(ViewPager viewPager, int i2) {
        setViewPager(viewPager);
        setCurrentItem(i2);
    }

    public void setCurrentItem(int i2) {
        if (this.f != null) {
            this.i = this.j;
            this.j = i2;
            this.f.setCurrentItem(i2);
            int childCount = this.e.getChildCount();
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = this.e.getChildAt(i3);
                boolean z = i3 == i2;
                childAt.setSelected(z);
                if (z) {
                    a(i2);
                }
                i3++;
            }
            return;
        }
        throw new IllegalStateException("ViewPager has not been bound.");
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.g = onPageChangeListener;
    }

    public TabView getTabView(int i2) {
        return this.r.get(i2);
    }

    public class TabView extends FrameLayout {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f18946a;
        private boolean b = false;
        /* access modifiers changed from: private */
        public ImageView c;
        /* access modifiers changed from: private */
        public TextView d;
        public boolean mIsUserTabInfo = false;

        public TabView(Context context) {
            super(context);
            this.c = new ImageView(context);
            addView(this.c);
            this.d = new TextView(context, (AttributeSet) null, R.attr.vpiTabPageIndicatorStyle);
            addView(this.d);
        }

        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (TabPageIndicator.this.h > 0 && getMeasuredWidth() > TabPageIndicator.this.h) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(TabPageIndicator.this.h, 1073741824), i2);
            }
            setPadding(0, 0, 0, 0);
        }

        /* access modifiers changed from: protected */
        public void drawableStateChanged() {
            super.drawableStateChanged();
            this.d.setSelected(isSelected());
        }

        public int getIndex() {
            return this.f18946a;
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            getPaddingLeft();
            getPaddingRight();
            if (this.b) {
                drawUnreadTag(canvas);
            }
        }

        public void setShowPoint(boolean z) {
            this.b = z;
        }

        public boolean getShowPoint() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public void drawUnreadTag(Canvas canvas) {
            int i;
            int paddingTop = getPaddingTop();
            if (this.mIsUserTabInfo) {
                i = this.c.getMeasuredWidth();
            } else {
                i = (getWidth() - this.d.getCompoundDrawables()[1].getBounds().width()) / 2;
            }
            Bitmap pointBitmap = TabPageIndicator.this.getPointBitmap();
            canvas.drawBitmap(pointBitmap, new Rect(0, 0, pointBitmap.getWidth(), pointBitmap.getHeight()), new RectF((float) ((getWidth() - i) + 10), (float) (paddingTop - (pointBitmap.getHeight() / 2)), (float) ((getWidth() - i) + 10 + pointBitmap.getWidth()), (float) (paddingTop + (pointBitmap.getHeight() / 2))), new Paint(1));
        }
    }

    public void onCheckNewMessageFinished(int i2) {
        if (this.s != null) {
            boolean a2 = PreferenceUtils.a("my_home_red_dot_clicked", true);
            if (i2 > 0) {
                this.mShowMessageDot = true;
                PreferenceUtils.b("my_home_red_dot_clicked", false);
            } else if (!a2) {
                this.mShowMessageDot = true;
            } else {
                this.mShowMessageDot = false;
            }
            RedPointManager.a().b("red_point_setting_page");
        }
    }

    public void onCheckUpdateFinished(boolean z, boolean z2) {
        if (!z) {
            this.mShowUpdateDot = false;
        } else if (!z2) {
            this.mShowUpdateDot = true;
        } else {
            this.mShowUpdateDot = false;
        }
        RedPointManager.a().b("red_point_setting_page");
    }

    public void onCheckNewFeedbackFinished(int i2) {
        this.mShowFeedbackDot = i2 > 0;
        RedPointManager.a().b("red_point_setting_page");
    }

    public void onCheckSignNotifyFinished(boolean z, boolean z2) {
        if (!z) {
            this.mShowSignNotifyDot = false;
        } else if (!z2) {
            this.mShowSignNotifyDot = true;
        } else {
            this.mShowSignNotifyDot = false;
        }
        RedPointManager.a().b("red_point_setting_page");
    }

    public int getSelectedTabIndex() {
        return this.j;
    }

    public int getLastTabIndex() {
        return this.i;
    }

    public void updateTabViewSettingDot(boolean z) {
        if (this.s != null) {
            if (z) {
                this.s.setShowPoint(true);
            } else {
                this.s.setShowPoint(false);
            }
            this.s.postInvalidate();
            this.s.requestLayout();
        }
    }

    public void updateTabviewShopDot(boolean z) {
        if (this.t != null && this.t.getShowPoint() != z) {
            this.t.setShowPoint(z);
            this.t.postInvalidate();
            this.t.requestLayout();
        }
    }
}
