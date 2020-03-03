package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.RedPointManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.framework.redpoint.RedPointManagerNew;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.widget.TabPageIndicator;
import com.xiaomi.smarthome.library.common.widget.ViewPager;
import com.xiaomi.smarthome.newui.RoomPagerAdapter;
import com.xiaomi.smarthome.newui.widget.LinearViewPager;
import java.util.ArrayList;

public class TabPageIndicatorNew extends LinearLayout implements PageIndicator {
    public static final String PREF_KEY_MY_HOME_RED_DOT_CLICKED = "my_home_red_dot_clicked";

    /* renamed from: a  reason: collision with root package name */
    private static final CharSequence f18947a = "";
    private Runnable b;
    private int c;
    /* access modifiers changed from: private */
    public OnClickInterceptListener d;
    private final View.OnClickListener e;
    /* access modifiers changed from: private */
    public LinearViewPager f;
    private ViewPager.OnPageChangeListener g;
    /* access modifiers changed from: private */
    public int h;
    private int i;
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public TabPageIndicator.OnTabReselectedListener k;
    private Bitmap l;
    private Object m;
    private ArrayList<TabView> n;
    /* access modifiers changed from: private */
    public TabView o;
    private TabView p;

    public interface OnClickInterceptListener {
        boolean onIntercept(TabView tabView);
    }

    public interface OnTabReselectedListener {
        void a(int i);
    }

    public void setViewPager(ViewPager viewPager) {
    }

    public void setOnClickInterceptListener(OnClickInterceptListener onClickInterceptListener) {
        this.d = onClickInterceptListener;
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

    public TabPageIndicatorNew(Context context) {
        this(context, (AttributeSet) null);
    }

    public TabPageIndicatorNew(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new View.OnClickListener() {
            public void onClick(View view) {
                TabView tabView = (TabView) view;
                if (TabPageIndicatorNew.this.d == null || !TabPageIndicatorNew.this.d.onIntercept(tabView)) {
                    RedPointManagerNew.a().c(tabView.mViewTag);
                    if (tabView == TabPageIndicatorNew.this.o) {
                        PreferenceUtils.c(SHApplication.getAppContext(), "my_home_red_dot_clicked", true);
                        TabPageIndicatorNew.this.o.invalidate();
                    }
                    int currentItem = TabPageIndicatorNew.this.f.getCurrentItem();
                    int index = tabView.getIndex();
                    TabPageIndicatorNew.this.setCurrentItem(index);
                    if (currentItem == index && TabPageIndicatorNew.this.k != null) {
                        TabPageIndicatorNew.this.k.a(index);
                    }
                    try {
                        StatHelper.a(TabPageIndicatorNew.this.f.getAdapter().a(), index);
                    } catch (Exception unused) {
                    }
                }
            }
        };
        this.m = new Object();
        this.n = new ArrayList<>();
        setHorizontalScrollBarEnabled(false);
        if (!DarkModeCompat.a(context)) {
            setBackgroundResource(R.drawable.main_bottombar_bg);
        }
    }

    public void setOnTabReselectedListener(TabPageIndicator.OnTabReselectedListener onTabReselectedListener) {
        this.k = onTabReselectedListener;
    }

    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.i != 0) {
            this.j = this.i;
            this.i = 0;
        }
        setCurrentItem(this.j);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.b != null) {
            post(this.b);
        }
        RedPointManager.a().b("red_point_setting_page");
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.b != null) {
            removeCallbacks(this.b);
        }
        RedPointManager.a().a("red_point_setting_page");
        RedPointManager.a().a("red_point_shop_page");
        RedPointManager.a().b();
    }

    private void a(int i2, CharSequence charSequence, int i3, int i4, String str) {
        TabView tabView = new TabView(getContext(), str);
        int unused = tabView.f18953a = i2;
        tabView.setFocusable(true);
        tabView.setOnClickListener(this.e);
        tabView.d.setText(charSequence);
        try {
            if (Settings.System.getFloat(getContext().getContentResolver(), "font_scale") > 1.1f) {
                ViewGroup.LayoutParams layoutParams = tabView.d.getLayoutParams();
                if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    ((RelativeLayout.LayoutParams) layoutParams).bottomMargin = 0;
                    tabView.d.setLayoutParams(layoutParams);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        tabView.d.setVisibility(0);
        tabView.c.setVisibility(0);
        if (i3 != 0) {
            tabView.c.setImageResource(i3);
        }
        if (i2 == 0) {
            final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
                public boolean onDoubleTap(MotionEvent motionEvent) {
                    if (TabPageIndicatorNew.this.j != 0) {
                        return false;
                    }
                    LocalBroadcastManager.getInstance(TabPageIndicatorNew.this.getContext()).sendBroadcast(new Intent(RoomPagerAdapter.f20366a));
                    return true;
                }
            });
            tabView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return gestureDetector.onTouchEvent(motionEvent);
                }
            });
        }
        if (i2 == 1) {
            this.p = tabView;
            RedPointManager.a().a("red_point_shop_page", (RedPointManager.RedPointAction) new RedPointManager.RedPointAction() {
                public void a(boolean z) {
                    TabPageIndicatorNew.this.updateTabviewShopDot(z);
                }
            });
        }
        if (i2 == i4 - 1) {
            this.o = tabView;
            RedPointManager.a().a("red_point_setting_page", (RedPointManager.RedPointAction) new RedPointManager.RedPointAction() {
                public void a(boolean z) {
                    TabPageIndicatorNew.this.updateTabViewSettingDot(z);
                }
            });
        }
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        layoutParams2.gravity = 17;
        addView(tabView, layoutParams2);
        this.n.add(i2, tabView);
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

    public void setViewPager(LinearViewPager linearViewPager) {
        if (this.f != linearViewPager) {
            if (linearViewPager.getAdapter() != null) {
                this.f = linearViewPager;
                this.f.setOnPageChangeListener(this);
                notifyDataSetChanged();
                return;
            }
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
    }

    public void notifyDataSetChanged() {
        removeAllViews();
        PagerAdapter adapter = this.f.getAdapter();
        IconPagerAdapter iconPagerAdapter = adapter instanceof IconPagerAdapter ? (IconPagerAdapter) adapter : null;
        int a2 = adapter.a();
        for (int i2 = 0; i2 < a2; i2++) {
            CharSequence b2 = adapter.b(i2);
            if (b2 == null) {
                b2 = f18947a;
            }
            CharSequence charSequence = b2;
            int c2 = iconPagerAdapter != null ? iconPagerAdapter.c(i2) : 0;
            String str = "";
            if (iconPagerAdapter != null) {
                str = iconPagerAdapter.d(i2);
            }
            a(i2, charSequence, c2, a2, str);
        }
        if (this.j > a2) {
            this.j = a2 - 1;
        }
        setCurrentItem(this.j);
    }

    public void setViewPager(ViewPager viewPager, int i2) {
        setViewPager(viewPager);
        setCurrentItem(i2);
    }

    public void setCurrentItem(int i2) {
        if (this.f == null) {
            this.i = i2;
            return;
        }
        this.i = 0;
        if (this.j != i2) {
            this.j = i2;
            if (this.g != null) {
                this.g.onPageSelected(i2);
            }
        }
        this.f.setCurrentItem(i2);
        int childCount = getChildCount();
        int i3 = 0;
        while (i3 < childCount) {
            getChildAt(i3).setSelected(i3 == i2);
            i3++;
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.g = onPageChangeListener;
    }

    public TabView getTabView(int i2) {
        return this.n.get(i2);
    }

    public ArrayList<TabView> getTabViewList() {
        return this.n;
    }

    public class TabView extends FrameLayout implements RedPointManagerNew.RedPointAction {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f18953a;
        private boolean b = false;
        /* access modifiers changed from: private */
        public ImageView c;
        /* access modifiers changed from: private */
        public TextView d;
        /* access modifiers changed from: private */
        public ImageView e;
        public boolean mIsUserTabInfo = false;
        public String mViewTag;

        public TabView(Context context, String str) {
            super(context);
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.bottom_bar_tab_view, this, false);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            addView(inflate, layoutParams);
            this.c = (ImageView) inflate.findViewById(R.id.tab_img);
            this.d = (TextView) inflate.findViewById(R.id.tab_text);
            this.e = (ImageView) inflate.findViewById(R.id.red_dot_iv);
            if (this.e != null) {
                this.e.setVisibility(4);
            }
            this.mViewTag = str;
        }

        /* access modifiers changed from: protected */
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            LogUtil.a(OpenApi.e, "onAttachedToWindow mViewTag" + this.mViewTag);
            RedPointManagerNew.a().a(this.mViewTag, (RedPointManagerNew.RedPointAction) this);
        }

        /* access modifiers changed from: protected */
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            RedPointManagerNew.a().a(this.mViewTag);
        }

        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (TabPageIndicatorNew.this.h > 0 && getMeasuredWidth() > TabPageIndicatorNew.this.h) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(TabPageIndicatorNew.this.h, 1073741824), i2);
            }
            setPadding(0, 0, 0, 0);
        }

        /* access modifiers changed from: protected */
        public void drawableStateChanged() {
            super.drawableStateChanged();
            this.d.setSelected(isSelected());
            postInvalidate();
        }

        public int getIndex() {
            return this.f18953a;
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
            Bitmap pointBitmap = TabPageIndicatorNew.this.getPointBitmap();
            canvas.drawBitmap(pointBitmap, new Rect(0, 0, pointBitmap.getWidth(), pointBitmap.getHeight()), new RectF((float) ((getWidth() - i) + 10), (float) (paddingTop - (pointBitmap.getHeight() / 2)), (float) ((getWidth() - i) + 10 + pointBitmap.getWidth()), (float) (paddingTop + (pointBitmap.getHeight() / 2))), new Paint(1));
        }

        public void showRedPoint(final boolean z) {
            if (this.e != null) {
                this.e.post(new Runnable() {
                    public void run() {
                        TabView.this.e.setVisibility(z ? 0 : 8);
                    }
                });
            }
        }
    }

    public int getSelectedTabIndex() {
        return this.j;
    }

    public int getLastTabIndex() {
        return this.i;
    }

    public void updateTabViewSettingDot(boolean z) {
        if (this.o != null) {
            if (z) {
                this.o.showRedPoint(true);
            } else {
                this.o.showRedPoint(false);
            }
            this.o.postInvalidate();
            this.o.requestLayout();
        }
    }

    public void updateTabviewShopDot(boolean z) {
        if (this.p != null) {
            this.p.showRedPoint(z);
            this.p.postInvalidate();
            this.p.requestLayout();
        }
    }

    public void clearRedPoint() {
        postInvalidate();
        requestLayout();
    }
}
