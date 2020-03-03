package com.miuipub.internal.widget;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.CollapsibleActionView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.miuipub.internal.app.ActionBarDelegateImpl;
import com.miuipub.internal.view.ActionBarPolicy;
import com.miuipub.internal.view.menu.ActionMenuItem;
import com.miuipub.internal.view.menu.ActionMenuPresenter;
import com.miuipub.internal.view.menu.ActionMenuView;
import com.miuipub.internal.view.menu.MenuBuilder;
import com.miuipub.internal.view.menu.MenuItemImpl;
import com.miuipub.internal.view.menu.MenuPresenter;
import com.miuipub.internal.view.menu.MenuView;
import com.miuipub.internal.view.menu.SubMenuBuilder;
import miuipub.os.Build;
import miuipub.v6.R;

public class ActionBarView extends AbsActionBarView {
    public static final int DISPLAY_DEFAULT = 0;

    /* renamed from: a  reason: collision with root package name */
    private static final String f8330a = "ActionBarView";
    private static final int b = 31;
    private static final int c = 19;
    private static final int d = 1;
    private static final int e = 2;
    private ProgressBar A;
    private ProgressBar B;
    private View C;
    private int D;
    private int E;
    private int F;
    private int G;
    private boolean H;
    private boolean I;
    private boolean J;
    private boolean K;
    private MenuBuilder L;
    /* access modifiers changed from: private */
    public ActionMenuItem M;
    private SpinnerAdapter N;
    /* access modifiers changed from: private */
    public ActionBar.OnNavigationListener O;
    /* access modifiers changed from: private */
    public ExpandedActionViewMenuPresenter P;
    private final AdapterView.OnItemSelectedListener Q = new AdapterView.OnItemSelectedListener() {
        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (ActionBarView.this.O != null) {
                ActionBarView.this.O.onNavigationItemSelected(i, j);
            }
        }
    };
    private final View.OnClickListener R = new View.OnClickListener() {
        public void onClick(View view) {
            MenuItemImpl menuItemImpl = ActionBarView.this.P.b;
            if (menuItemImpl != null) {
                menuItemImpl.collapseActionView();
            }
        }
    };
    private final View.OnClickListener S = new View.OnClickListener() {
        public void onClick(View view) {
            ActionBarView.this.mWindowCallback.onMenuItemSelected(0, ActionBarView.this.M);
        }
    };
    /* access modifiers changed from: private */
    public int f;
    /* access modifiers changed from: private */
    public int g = -1;
    private CharSequence h;
    private CharSequence i;
    private int j;
    private Drawable k;
    private Drawable l;
    private Context m;
    View mExpandedActionView;
    Window.Callback mWindowCallback;
    private final int n;
    private Drawable o;
    private int p;
    /* access modifiers changed from: private */
    public HomeView q;
    /* access modifiers changed from: private */
    public HomeView r;
    /* access modifiers changed from: private */
    public LinearLayout s;
    private TextView t;
    private TextView u;
    private View v;
    /* access modifiers changed from: private */
    public Spinner w;
    private LinearLayout x;
    /* access modifiers changed from: private */
    public ScrollingTabContainerView y;
    /* access modifiers changed from: private */
    public View z;

    public void setCollapsable(boolean z2) {
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public /* bridge */ /* synthetic */ void animateToVisibility(int i2) {
        super.animateToVisibility(i2);
    }

    public /* bridge */ /* synthetic */ void dismissPopupMenus() {
        super.dismissPopupMenus();
    }

    public /* bridge */ /* synthetic */ ActionMenuView getActionMenuView() {
        return super.getActionMenuView();
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public /* bridge */ /* synthetic */ ActionMenuView getMenuView() {
        return super.getMenuView();
    }

    public /* bridge */ /* synthetic */ boolean hideOverflowMenu() {
        return super.hideOverflowMenu();
    }

    public /* bridge */ /* synthetic */ boolean isOverflowMenuShowing() {
        return super.isOverflowMenuShowing();
    }

    public /* bridge */ /* synthetic */ boolean isOverflowReserved() {
        return super.isOverflowReserved();
    }

    public /* bridge */ /* synthetic */ void postShowOverflowMenu() {
        super.postShowOverflowMenu();
    }

    public /* bridge */ /* synthetic */ void setContentHeight(int i2) {
        super.setContentHeight(i2);
    }

    public /* bridge */ /* synthetic */ void setSplitView(ActionBarContainer actionBarContainer) {
        super.setSplitView(actionBarContainer);
    }

    public /* bridge */ /* synthetic */ void setSplitWhenNarrow(boolean z2) {
        super.setSplitWhenNarrow(z2);
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i2) {
        super.setVisibility(i2);
    }

    public /* bridge */ /* synthetic */ boolean showOverflowMenu() {
        return super.showOverflowMenu();
    }

    public ActionBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = context;
        setBackgroundResource(0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.V6_ActionBar, 16843470, 0);
        this.f = obtainStyledAttributes.getInt(R.styleable.V6_ActionBar_android_navigationMode, 0);
        this.h = obtainStyledAttributes.getText(R.styleable.V6_ActionBar_android_title);
        this.i = obtainStyledAttributes.getText(R.styleable.V6_ActionBar_android_subtitle);
        this.K = obtainStyledAttributes.getBoolean(R.styleable.V6_ActionBar_v6_titleCenter, false);
        this.l = obtainStyledAttributes.getDrawable(R.styleable.V6_ActionBar_android_logo);
        this.k = obtainStyledAttributes.getDrawable(R.styleable.V6_ActionBar_android_icon);
        LayoutInflater from = LayoutInflater.from(context);
        this.n = obtainStyledAttributes.getResourceId(R.styleable.V6_ActionBar_android_homeLayout, R.layout.v6_action_bar_home);
        this.F = obtainStyledAttributes.getResourceId(R.styleable.V6_ActionBar_android_titleTextStyle, 0);
        this.G = obtainStyledAttributes.getResourceId(R.styleable.V6_ActionBar_android_subtitleTextStyle, 0);
        this.D = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.V6_ActionBar_android_progressBarPadding, 0);
        this.E = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.V6_ActionBar_android_itemPadding, 0);
        setDisplayOptions(obtainStyledAttributes.getInt(R.styleable.V6_ActionBar_android_displayOptions, 0));
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.V6_ActionBar_android_customNavigationLayout, 0);
        if (resourceId != 0) {
            this.z = from.inflate(resourceId, this, false);
            this.f = 0;
            setDisplayOptions(this.g | 16);
        }
        this.mContentHeight = obtainStyledAttributes.getLayoutDimension(R.styleable.V6_ActionBar_android_height, 0);
        obtainStyledAttributes.recycle();
        this.M = new ActionMenuItem(context, 0, 16908332, 0, 0, this.h);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        ViewGroup.LayoutParams layoutParams;
        super.onConfigurationChanged(configuration);
        this.t = null;
        this.u = null;
        this.v = null;
        if (this.s != null && this.s.getParent() == this) {
            removeView(this.s);
        }
        this.s = null;
        if ((this.g & 8) != 0) {
            a();
        }
        if (this.y != null && this.I && (layoutParams = this.y.getLayoutParams()) != null) {
            layoutParams.width = -2;
            layoutParams.height = -1;
        }
    }

    public void setWindowCallback(Window.Callback callback) {
        this.mWindowCallback = callback;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.e(false);
            this.mActionMenuPresenter.b();
        }
    }

    public void initProgress() {
        this.A = new ProgressBar(this.m, (AttributeSet) null, R.attr.v6_actionBarProgressStyle);
        this.A.setId(R.id.v6_progress_horizontal);
        this.A.setMax(10000);
        this.A.setVisibility(8);
        addView(this.A);
    }

    public void initIndeterminateProgress() {
        this.B = new ProgressBar(this.m, (AttributeSet) null, R.attr.v6_actionBarIndeterminateProgressStyle);
        this.B.setId(R.id.v6_progress_circular);
        this.B.setVisibility(8);
        this.B.setIndeterminate(true);
        addView(this.B);
    }

    public void initImmersionMore(int i2, final ActionBarDelegateImpl actionBarDelegateImpl) {
        if (i2 <= 0) {
            Log.w(f8330a, "Try to initialize invalid layout for immersion more button: " + i2);
        } else if ((this.g & 16) != 0) {
            Log.d(f8330a, "Don't show immersion menu button for custom action bar");
        } else if (this.g == 0) {
            Log.d(f8330a, "Don't show immersion menu button for null display option");
        } else {
            this.C = LayoutInflater.from(getContext()).inflate(i2, this, false);
            addView(this.C);
            final View findViewById = this.C.findViewById(R.id.more);
            if (findViewById != null) {
                findViewById.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        actionBarDelegateImpl.a(findViewById, (ViewGroup) ActionBarView.this);
                    }
                });
            }
        }
    }

    public boolean showImmersionMore() {
        if (this.C == null) {
            return false;
        }
        this.C.setVisibility(0);
        return true;
    }

    public boolean hideImmersionMore() {
        if (this.C == null) {
            return false;
        }
        this.C.setVisibility(8);
        return true;
    }

    public void setSplitActionBar(boolean z2) {
        if (this.mSplitActionBar != z2) {
            if (this.mMenuView != null) {
                ViewGroup viewGroup = (ViewGroup) this.mMenuView.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(this.mMenuView);
                }
                if (z2) {
                    if (this.mSplitView != null) {
                        this.mSplitView.addView(this.mMenuView);
                    }
                    this.mMenuView.getLayoutParams().width = -1;
                } else {
                    addView(this.mMenuView);
                    this.mMenuView.getLayoutParams().width = -2;
                }
                this.mMenuView.requestLayout();
            }
            if (this.mSplitView != null) {
                this.mSplitView.setVisibility(z2 ? 0 : 8);
            }
            if (this.mActionMenuPresenter != null) {
                if (!z2) {
                    this.mActionMenuPresenter.b(getResources().getBoolean(R.bool.v6_abc_action_bar_expanded_action_views_exclusive));
                } else {
                    this.mActionMenuPresenter.b(false);
                    this.mActionMenuPresenter.a(getContext().getResources().getDisplayMetrics().widthPixels, true);
                }
            }
            super.setSplitActionBar(z2);
        }
    }

    public boolean isSplitActionBar() {
        return this.mSplitActionBar;
    }

    public int getSplitActionBarHeight(boolean z2) {
        if (z2) {
            if (this.mSplitView != null) {
                return this.mSplitView.getContentHeight();
            }
            return 0;
        } else if (this.mSplitActionBar) {
            return this.mSplitView.getHeight();
        } else {
            return 0;
        }
    }

    public boolean hasEmbeddedTabs() {
        return this.I;
    }

    public void setEmbeddedTabView(ScrollingTabContainerView scrollingTabContainerView) {
        if (this.y != null) {
            removeView(this.y);
        }
        this.y = scrollingTabContainerView;
        this.I = scrollingTabContainerView != null;
        if (this.I && this.f == 2) {
            addView(this.y);
            ViewGroup.LayoutParams layoutParams = this.y.getLayoutParams();
            layoutParams.width = -2;
            layoutParams.height = -1;
            b();
        }
    }

    public void setCallback(ActionBar.OnNavigationListener onNavigationListener) {
        this.O = onNavigationListener;
    }

    public void setMenu(Menu menu, MenuPresenter.Callback callback) {
        ActionMenuView actionMenuView;
        ViewGroup viewGroup;
        if (menu != this.L) {
            if (this.L != null) {
                this.L.b((MenuPresenter) this.mActionMenuPresenter);
                this.L.b((MenuPresenter) this.P);
            }
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            this.L = menuBuilder;
            if (!(this.mMenuView == null || (viewGroup = (ViewGroup) this.mMenuView.getParent()) == null)) {
                viewGroup.removeView(this.mMenuView);
            }
            if (this.mActionMenuPresenter == null) {
                this.mActionMenuPresenter = createActionMenuPresenter(callback);
                this.P = createExpandedActionViewMenuPresenter();
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -1);
            if (!this.mSplitActionBar) {
                this.mActionMenuPresenter.b(getResources().getBoolean(R.bool.v6_abc_action_bar_expanded_action_views_exclusive));
                a(menuBuilder);
                actionMenuView = (ActionMenuView) this.mActionMenuPresenter.a((ViewGroup) this);
                ViewGroup viewGroup2 = (ViewGroup) actionMenuView.getParent();
                if (!(viewGroup2 == null || viewGroup2 == this)) {
                    viewGroup2.removeView(actionMenuView);
                }
                addView(actionMenuView, layoutParams);
            } else {
                this.mActionMenuPresenter.b(false);
                this.mActionMenuPresenter.a(getContext().getResources().getDisplayMetrics().widthPixels, true);
                layoutParams.width = -1;
                layoutParams.height = -2;
                layoutParams.gravity = Build.W ? 17 : 80;
                a(menuBuilder);
                actionMenuView = (ActionMenuView) this.mActionMenuPresenter.a((ViewGroup) this);
                if (this.mSplitView != null) {
                    ViewGroup viewGroup3 = (ViewGroup) actionMenuView.getParent();
                    if (!(viewGroup3 == null || viewGroup3 == this.mSplitView)) {
                        viewGroup3.removeView(actionMenuView);
                    }
                    actionMenuView.setVisibility(getAnimatedVisibility());
                    this.mSplitView.addView(actionMenuView, 0, layoutParams);
                    View findViewById = actionMenuView.findViewById(R.id.expanded_menu);
                    if (findViewById != null) {
                        findViewById.requestLayout();
                    }
                } else {
                    actionMenuView.setLayoutParams(layoutParams);
                }
            }
            this.mMenuView = actionMenuView;
        }
    }

    private void a(MenuBuilder menuBuilder) {
        if (menuBuilder != null) {
            menuBuilder.a((MenuPresenter) this.mActionMenuPresenter);
            menuBuilder.a((MenuPresenter) this.P);
        } else {
            this.mActionMenuPresenter.a(this.m, (MenuBuilder) null);
            this.P.a(this.m, (MenuBuilder) null);
        }
        this.mActionMenuPresenter.d(true);
        this.P.d(true);
    }

    public boolean hasExpandedActionView() {
        return (this.P == null || this.P.b == null) ? false : true;
    }

    public void collapseActionView() {
        MenuItemImpl menuItemImpl = this.P == null ? null : this.P.b;
        if (menuItemImpl != null) {
            menuItemImpl.collapseActionView();
        }
    }

    public void setCustomNavigationView(View view) {
        boolean z2 = (this.g & 16) != 0;
        if (this.z != null && z2) {
            removeView(this.z);
        }
        this.z = view;
        if (this.z != null && z2) {
            addView(this.z);
        }
    }

    public CharSequence getTitle() {
        return this.h;
    }

    public void setTitle(CharSequence charSequence) {
        this.H = true;
        setTitleImpl(charSequence);
    }

    public void setWindowTitle(CharSequence charSequence) {
        if (!this.H) {
            setTitleImpl(charSequence);
        }
    }

    private void setTitleImpl(CharSequence charSequence) {
        this.h = charSequence;
        if (this.t != null) {
            this.t.setText(charSequence);
            int i2 = 0;
            boolean z2 = this.mExpandedActionView == null && (this.g & 8) != 0 && (!TextUtils.isEmpty(this.h) || !TextUtils.isEmpty(this.i));
            LinearLayout linearLayout = this.s;
            if (!z2) {
                i2 = 8;
            }
            linearLayout.setVisibility(i2);
        }
        if (this.M != null) {
            this.M.setTitle(charSequence);
        }
    }

    public CharSequence getSubtitle() {
        return this.i;
    }

    public void setSubtitle(CharSequence charSequence) {
        this.i = charSequence;
        if (this.u != null) {
            this.u.setText(charSequence);
            int i2 = 8;
            this.u.setVisibility(charSequence != null ? 0 : 8);
            boolean z2 = this.mExpandedActionView == null && (this.g & 8) != 0 && (!TextUtils.isEmpty(this.h) || !TextUtils.isEmpty(this.i));
            LinearLayout linearLayout = this.s;
            if (z2) {
                i2 = 0;
            }
            linearLayout.setVisibility(i2);
        }
    }

    public void setHomeButtonEnabled(boolean z2) {
        if (this.q != null) {
            this.q.setEnabled(z2);
            this.q.setFocusable(z2);
            if (!z2) {
                this.q.setContentDescription((CharSequence) null);
            } else if ((this.g & 4) != 0) {
                this.q.setContentDescription(this.m.getResources().getText(R.string.v6_abc_action_bar_up_description));
            } else {
                this.q.setContentDescription(this.m.getResources().getText(R.string.v6_abc_action_bar_home_description));
            }
        }
    }

    public void setDisplayOptions(int i2) {
        int i3 = -1;
        if (this.g != -1) {
            i3 = i2 ^ this.g;
        }
        this.g = i2;
        if ((i3 & 31) != 0) {
            boolean z2 = false;
            boolean z3 = (i2 & 2) != 0;
            int i4 = 8;
            if (z3) {
                g();
                this.q.setVisibility(this.mExpandedActionView == null ? 0 : 8);
                if ((i3 & 4) != 0) {
                    boolean z4 = (i2 & 4) != 0;
                    this.q.a(z4);
                    if (z4) {
                        setHomeButtonEnabled(true);
                    }
                }
                if ((i3 & 1) != 0) {
                    Drawable logo = getLogo();
                    boolean z5 = (logo == null || (i2 & 1) == 0) ? false : true;
                    HomeView homeView = this.q;
                    if (!z5) {
                        logo = getIcon();
                    }
                    homeView.a(logo);
                }
            } else if (this.q != null) {
                removeView(this.q);
            }
            if ((i3 & 8) != 0) {
                if ((i2 & 8) != 0) {
                    a();
                } else {
                    removeView(this.s);
                }
            }
            if (!(this.s == null || (i3 & 6) == 0)) {
                boolean z6 = (this.g & 4) != 0;
                View view = this.v;
                if (!z3) {
                    i4 = z6 ? 0 : 4;
                }
                view.setVisibility(i4);
                LinearLayout linearLayout = this.s;
                if (!z3 && z6) {
                    z2 = true;
                }
                linearLayout.setEnabled(z2);
            }
            if (!((i3 & 16) == 0 || this.z == null)) {
                if ((i2 & 16) != 0) {
                    addView(this.z);
                } else {
                    removeView(this.z);
                }
            }
            requestLayout();
        } else {
            invalidate();
        }
        if (this.q == null) {
            return;
        }
        if (!this.q.isEnabled()) {
            this.q.setContentDescription((CharSequence) null);
        } else if ((i2 & 4) != 0) {
            this.q.setContentDescription(this.m.getResources().getText(R.string.v6_abc_action_bar_up_description));
        } else {
            this.q.setContentDescription(this.m.getResources().getText(R.string.v6_abc_action_bar_home_description));
        }
    }

    public void setIcon(Drawable drawable) {
        this.k = drawable;
        this.j |= 1;
        if (drawable != null && (((this.g & 1) == 0 || getLogo() == null) && this.q != null)) {
            this.q.a(drawable);
        }
        if (this.mExpandedActionView != null) {
            this.r.a(this.k.getConstantState().newDrawable(getResources()));
        }
    }

    public void setIcon(int i2) {
        setIcon(this.m.getResources().getDrawable(i2));
    }

    public void setLogo(Drawable drawable) {
        this.l = drawable;
        this.j |= 2;
        if (drawable != null && (this.g & 1) != 0 && this.q != null) {
            this.q.a(drawable);
        }
    }

    public void setLogo(int i2) {
        setLogo(this.m.getResources().getDrawable(i2));
    }

    public void setNavigationMode(int i2) {
        int i3 = this.f;
        if (i2 != i3) {
            switch (i3) {
                case 1:
                    if (this.x != null) {
                        removeView(this.x);
                        break;
                    }
                    break;
                case 2:
                    if (this.y != null && this.I) {
                        removeView(this.y);
                        break;
                    }
            }
            switch (i2) {
                case 1:
                    if (this.w == null) {
                        this.w = new Spinner(this.m, (AttributeSet) null, 16843479);
                        this.x = (LinearLayout) LayoutInflater.from(this.m).inflate(R.layout.v6_action_bar_view_list_nav_layout, (ViewGroup) null);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
                        layoutParams.gravity = 17;
                        this.x.addView(this.w, layoutParams);
                    }
                    if (this.w.getAdapter() != this.N) {
                        this.w.setAdapter(this.N);
                    }
                    this.w.setOnItemSelectedListener(this.Q);
                    addView(this.x);
                    break;
                case 2:
                    if (this.y != null && this.I) {
                        addView(this.y);
                        break;
                    }
            }
            this.f = i2;
            requestLayout();
        }
    }

    public void setDropdownAdapter(SpinnerAdapter spinnerAdapter) {
        this.N = spinnerAdapter;
        if (this.w != null) {
            this.w.setAdapter(spinnerAdapter);
        }
    }

    public SpinnerAdapter getDropdownAdapter() {
        return this.N;
    }

    public void setDropdownSelectedPosition(int i2) {
        this.w.setSelection(i2);
    }

    public int getDropdownSelectedPosition() {
        return this.w.getSelectedItemPosition();
    }

    public View getCustomNavigationView() {
        return this.z;
    }

    public int getNavigationMode() {
        return this.f;
    }

    public int getDisplayOptions() {
        return this.g;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ActionBar.LayoutParams(19);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        ViewParent parent;
        super.onFinishInflate();
        if (this.z != null && (this.g & 16) != 0 && (parent = this.z.getParent()) != this) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.z);
            }
            addView(this.z);
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.s == null) {
            boolean z2 = false;
            this.s = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.v6_action_bar_title_item, this, false);
            this.t = (TextView) this.s.findViewById(R.id.action_bar_title);
            this.u = (TextView) this.s.findViewById(R.id.action_bar_subtitle);
            this.v = this.s.findViewById(R.id.up);
            this.s.setOnClickListener(this.S);
            if (this.F != 0) {
                this.t.setTextAppearance(this.m, this.F);
            }
            if (this.h != null) {
                this.t.setText(this.h);
            }
            if (this.G != 0) {
                this.u.setTextAppearance(this.m, this.G);
            }
            if (this.i != null) {
                this.u.setText(this.i);
                this.u.setVisibility(0);
            }
            int i2 = 4;
            boolean z3 = (this.g & 4) != 0;
            boolean z4 = (this.g & 2) != 0;
            View view = this.v;
            if (z4) {
                i2 = 8;
            } else if (z3) {
                i2 = 0;
            }
            view.setVisibility(i2);
            LinearLayout linearLayout = this.s;
            if (z3 && !z4) {
                z2 = true;
            }
            linearLayout.setEnabled(z2);
            b();
        }
        addView(this.s);
        if (this.mExpandedActionView != null || (TextUtils.isEmpty(this.h) && TextUtils.isEmpty(this.i))) {
            this.s.setVisibility(8);
        }
    }

    public boolean isCollapsed() {
        return this.J;
    }

    private void b() {
        int i2 = (!hasEmbeddedTabs() || !ActionBarPolicy.a(this.m).e()) ? 0 : 8;
        if (this.t != null && this.t.getVisibility() == 0) {
            this.t.setVisibility(i2);
        }
        if (this.u != null && this.u.getVisibility() == 0) {
            this.u.setVisibility(i2);
        }
    }

    private boolean c() {
        if (this.z == null || this.z.getVisibility() != 0) {
            return true;
        }
        ViewGroup.LayoutParams layoutParams = this.z.getLayoutParams();
        ActionBar.LayoutParams layoutParams2 = layoutParams instanceof ActionBar.LayoutParams ? (ActionBar.LayoutParams) layoutParams : null;
        if (layoutParams2 != null && (layoutParams2.gravity & 7) == 5) {
            return true;
        }
        return false;
    }

    private boolean d() {
        return this.K && this.v.getVisibility() != 0 && c() && (this.q == null || this.q.getVisibility() == 8) && !hasEmbeddedTabs();
    }

    private boolean e() {
        return (this.s == null || this.s.getVisibility() == 8 || (this.g & 8) == 0) ? false : true;
    }

    private void f() {
        boolean d2 = d();
        ViewGroup viewGroup = (ViewGroup) this.t.getParent();
        int i2 = 3;
        if (viewGroup instanceof LinearLayout) {
            ((LinearLayout) viewGroup).setGravity((d2 ? 1 : 3) | 16);
        }
        this.t.setGravity((d2 ? 1 : 3) | 16);
        this.t.setEllipsize(d2 ? TextUtils.TruncateAt.MIDDLE : TextUtils.TruncateAt.END);
        if (this.u != null) {
            TextView textView = this.u;
            if (d2) {
                i2 = 1;
            }
            textView.setGravity(i2 | 16);
            this.u.setEllipsize(d2 ? TextUtils.TruncateAt.MIDDLE : TextUtils.TruncateAt.END);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r20, int r21) {
        /*
            r19 = this;
            r0 = r19
            int r1 = r19.getChildCount()
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0009:
            r5 = 8
            if (r3 >= r1) goto L_0x0028
            android.view.View r6 = r0.getChildAt(r3)
            int r7 = r6.getVisibility()
            if (r7 == r5) goto L_0x0025
            com.miuipub.internal.view.menu.ActionMenuView r5 = r0.mMenuView
            if (r6 != r5) goto L_0x0023
            com.miuipub.internal.view.menu.ActionMenuView r5 = r0.mMenuView
            int r5 = r5.getChildCount()
            if (r5 == 0) goto L_0x0025
        L_0x0023:
            int r4 = r4 + 1
        L_0x0025:
            int r3 = r3 + 1
            goto L_0x0009
        L_0x0028:
            r3 = 1
            if (r4 != 0) goto L_0x0031
            r0.setMeasuredDimension(r2, r2)
            r0.J = r3
            return
        L_0x0031:
            r0.J = r2
            int r4 = android.view.View.MeasureSpec.getSize(r20)
            int r6 = r0.mContentHeight
            if (r6 <= 0) goto L_0x003e
            int r6 = r0.mContentHeight
            goto L_0x0042
        L_0x003e:
            int r6 = android.view.View.MeasureSpec.getSize(r21)
        L_0x0042:
            int r7 = r19.getPaddingTop()
            int r8 = r19.getPaddingBottom()
            int r7 = r7 + r8
            int r8 = r19.getPaddingLeft()
            int r9 = r19.getPaddingRight()
            int r10 = r6 - r7
            r11 = -2147483648(0xffffffff80000000, float:-0.0)
            int r12 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r11)
            int r8 = r4 - r8
            int r8 = r8 - r9
            int r9 = r8 / 2
            android.view.View r13 = r0.mExpandedActionView
            if (r13 == 0) goto L_0x0067
            com.miuipub.internal.widget.ActionBarView$HomeView r13 = r0.r
            goto L_0x0069
        L_0x0067:
            com.miuipub.internal.widget.ActionBarView$HomeView r13 = r0.q
        L_0x0069:
            r14 = 1073741824(0x40000000, float:2.0)
            if (r13 == 0) goto L_0x00a2
            int r15 = r13.getVisibility()
            if (r15 == r5) goto L_0x00a2
            android.view.ViewGroup$LayoutParams r15 = r13.getLayoutParams()
            int r3 = r15.width
            if (r3 >= 0) goto L_0x0080
            int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r11)
            goto L_0x0086
        L_0x0080:
            int r3 = r15.width
            int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r14)
        L_0x0086:
            int r15 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r14)
            r13.measure(r3, r15)
            int r3 = r13.getMeasuredWidth()
            int r13 = r13.a()
            int r3 = r3 + r13
            int r8 = r8 - r3
            int r8 = java.lang.Math.max(r2, r8)
            int r3 = r8 - r3
            int r3 = java.lang.Math.max(r2, r3)
            goto L_0x00a3
        L_0x00a2:
            r3 = r9
        L_0x00a3:
            com.miuipub.internal.view.menu.ActionMenuView r13 = r0.mMenuView
            if (r13 == 0) goto L_0x00c0
            com.miuipub.internal.view.menu.ActionMenuView r13 = r0.mMenuView
            android.view.ViewParent r13 = r13.getParent()
            if (r13 != r0) goto L_0x00c0
            com.miuipub.internal.view.menu.ActionMenuView r13 = r0.mMenuView
            int r8 = r0.measureChildView(r13, r8, r12, r2)
            com.miuipub.internal.view.menu.ActionMenuView r13 = r0.mMenuView
            int r13 = r13.getMeasuredWidth()
            int r9 = r9 - r13
            int r9 = java.lang.Math.max(r2, r9)
        L_0x00c0:
            android.widget.ProgressBar r13 = r0.B
            if (r13 == 0) goto L_0x00e6
            android.widget.ProgressBar r13 = r0.B
            int r13 = r13.getVisibility()
            if (r13 == r5) goto L_0x00e6
            android.widget.ProgressBar r13 = r0.B
            int r15 = r0.D
            int r15 = r15 * 2
            int r8 = r0.measureChildView(r13, r8, r12, r15)
            android.widget.ProgressBar r13 = r0.B
            int r13 = r13.getMeasuredWidth()
            int r9 = r9 - r13
            int r13 = r0.D
            int r13 = r13 * 2
            int r9 = r9 - r13
            int r9 = java.lang.Math.max(r2, r9)
        L_0x00e6:
            android.view.View r13 = r0.C
            if (r13 == 0) goto L_0x0103
            android.view.View r13 = r0.C
            int r13 = r13.getVisibility()
            if (r13 == r5) goto L_0x0103
            android.view.View r13 = r0.C
            int r8 = r0.measureChildView(r13, r8, r12, r2)
            android.view.View r12 = r0.C
            int r12 = r12.getMeasuredWidth()
            int r9 = r9 - r12
            int r9 = java.lang.Math.max(r2, r9)
        L_0x0103:
            boolean r12 = r19.e()
            if (r12 == 0) goto L_0x010c
            r19.f()
        L_0x010c:
            android.view.View r13 = r0.mExpandedActionView
            if (r13 == 0) goto L_0x0113
            android.view.View r13 = r0.mExpandedActionView
            goto L_0x0121
        L_0x0113:
            int r13 = r0.g
            r13 = r13 & 16
            if (r13 == 0) goto L_0x0120
            android.view.View r13 = r0.z
            if (r13 == 0) goto L_0x0120
            android.view.View r13 = r0.z
            goto L_0x0121
        L_0x0120:
            r13 = 0
        L_0x0121:
            if (r13 == 0) goto L_0x01ae
            android.view.ViewGroup$LayoutParams r15 = r13.getLayoutParams()
            android.view.ViewGroup$LayoutParams r15 = r0.generateLayoutParams((android.view.ViewGroup.LayoutParams) r15)
            boolean r5 = r15 instanceof android.app.ActionBar.LayoutParams
            if (r5 == 0) goto L_0x0133
            r5 = r15
            android.app.ActionBar$LayoutParams r5 = (android.app.ActionBar.LayoutParams) r5
            goto L_0x0134
        L_0x0133:
            r5 = 0
        L_0x0134:
            if (r5 == 0) goto L_0x0141
            int r11 = r5.leftMargin
            int r14 = r5.rightMargin
            int r11 = r11 + r14
            int r14 = r5.topMargin
            int r2 = r5.bottomMargin
            int r2 = r2 + r14
            goto L_0x0143
        L_0x0141:
            r2 = 0
            r11 = 0
        L_0x0143:
            int r14 = r0.mContentHeight
            r16 = r6
            r6 = -2
            if (r14 > 0) goto L_0x014d
        L_0x014a:
            r14 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x0153
        L_0x014d:
            int r14 = r15.height
            if (r14 == r6) goto L_0x014a
            r14 = 1073741824(0x40000000, float:2.0)
        L_0x0153:
            int r6 = r15.height
            if (r6 < 0) goto L_0x015e
            int r6 = r15.height
            int r6 = java.lang.Math.min(r6, r10)
            goto L_0x015f
        L_0x015e:
            r6 = r10
        L_0x015f:
            int r6 = r6 - r2
            r2 = 0
            int r6 = java.lang.Math.max(r2, r6)
            int r2 = r15.width
            r17 = r4
            r4 = -2
            if (r2 == r4) goto L_0x016f
            r2 = 1073741824(0x40000000, float:2.0)
            goto L_0x0171
        L_0x016f:
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x0171:
            int r4 = r15.width
            if (r4 < 0) goto L_0x017c
            int r4 = r15.width
            int r4 = java.lang.Math.min(r4, r8)
            goto L_0x017d
        L_0x017c:
            r4 = r8
        L_0x017d:
            int r4 = r4 - r11
            r18 = r7
            r7 = 0
            int r4 = java.lang.Math.max(r7, r4)
            if (r5 == 0) goto L_0x018a
            int r5 = r5.gravity
            goto L_0x018c
        L_0x018a:
            r5 = 19
        L_0x018c:
            r5 = r5 & 7
            r7 = 1
            if (r5 != r7) goto L_0x019c
            int r5 = r15.width
            r7 = -1
            if (r5 != r7) goto L_0x019c
            int r3 = java.lang.Math.min(r3, r9)
            int r4 = r3 * 2
        L_0x019c:
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r2)
            int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r14)
            r13.measure(r2, r3)
            int r2 = r13.getMeasuredWidth()
            int r11 = r11 + r2
            int r8 = r8 - r11
            goto L_0x01b4
        L_0x01ae:
            r17 = r4
            r16 = r6
            r18 = r7
        L_0x01b4:
            android.view.View r2 = r0.mExpandedActionView
            if (r2 != 0) goto L_0x01c9
            if (r12 == 0) goto L_0x01c9
            android.widget.LinearLayout r2 = r0.s
            int r3 = r0.mContentHeight
            r4 = 1073741824(0x40000000, float:2.0)
            int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r4)
            r4 = 0
            int r8 = r0.measureChildView(r2, r8, r3, r4)
        L_0x01c9:
            android.view.View r2 = r0.mExpandedActionView
            if (r2 != 0) goto L_0x022e
            int r2 = r0.f
            switch(r2) {
                case 1: goto L_0x0209;
                case 2: goto L_0x01d3;
                default: goto L_0x01d2;
            }
        L_0x01d2:
            goto L_0x022e
        L_0x01d3:
            com.miuipub.internal.widget.ScrollingTabContainerView r2 = r0.y
            if (r2 == 0) goto L_0x022e
            if (r12 == 0) goto L_0x01de
            int r2 = r0.E
            int r2 = r2 * 2
            goto L_0x01e0
        L_0x01de:
            int r2 = r0.E
        L_0x01e0:
            int r8 = r8 - r2
            r2 = 0
            int r3 = java.lang.Math.max(r2, r8)
            android.content.Context r2 = r0.m
            android.content.res.Resources r2 = r2.getResources()
            int r4 = miuipub.v6.R.integer.v6_action_bar_tab_layout_weight
            int r2 = r2.getInteger(r4)
            com.miuipub.internal.widget.ScrollingTabContainerView r4 = r0.y
            if (r2 != 0) goto L_0x01f9
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x01fb
        L_0x01f9:
            r2 = 1073741824(0x40000000, float:2.0)
        L_0x01fb:
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r2)
            r3 = 1073741824(0x40000000, float:2.0)
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r3)
            r4.measure(r2, r5)
            goto L_0x022e
        L_0x0209:
            android.widget.LinearLayout r2 = r0.x
            if (r2 == 0) goto L_0x022e
            if (r12 == 0) goto L_0x0214
            int r2 = r0.E
            int r2 = r2 * 2
            goto L_0x0216
        L_0x0214:
            int r2 = r0.E
        L_0x0216:
            int r8 = r8 - r2
            r2 = 0
            int r3 = java.lang.Math.max(r2, r8)
            android.widget.LinearLayout r4 = r0.x
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r5)
            r5 = 1073741824(0x40000000, float:2.0)
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r5)
            r4.measure(r3, r6)
            goto L_0x022f
        L_0x022e:
            r2 = 0
        L_0x022f:
            int r3 = r0.mContentHeight
            if (r3 > 0) goto L_0x024c
            r3 = 0
        L_0x0234:
            if (r2 >= r1) goto L_0x0246
            android.view.View r4 = r0.getChildAt(r2)
            int r4 = r4.getMeasuredHeight()
            int r4 = r4 + r18
            if (r4 <= r3) goto L_0x0243
            r3 = r4
        L_0x0243:
            int r2 = r2 + 1
            goto L_0x0234
        L_0x0246:
            r2 = r17
            r0.setMeasuredDimension(r2, r3)
            goto L_0x0253
        L_0x024c:
            r6 = r16
            r2 = r17
            r0.setMeasuredDimension(r2, r6)
        L_0x0253:
            android.widget.ProgressBar r1 = r0.A
            if (r1 == 0) goto L_0x027c
            android.widget.ProgressBar r1 = r0.A
            int r1 = r1.getVisibility()
            r3 = 8
            if (r1 == r3) goto L_0x027c
            android.widget.ProgressBar r1 = r0.A
            int r3 = r0.D
            int r3 = r3 * 2
            int r4 = r2 - r3
            r2 = 1073741824(0x40000000, float:2.0)
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r2)
            int r3 = r19.getMeasuredHeight()
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r4)
            r1.measure(r2, r3)
        L_0x027c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miuipub.internal.widget.ActionBarView.onMeasure(int, int):void");
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [android.view.ViewGroup$LayoutParams] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0220, code lost:
        if (r10 == -1) goto L_0x0219;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0225  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x0230  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x023b  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x023f  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x025f  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            r18 = this;
            r0 = r18
            int r1 = r18.getPaddingLeft()
            int r2 = r18.getPaddingTop()
            int r3 = r23 - r21
            int r4 = r18.getPaddingTop()
            int r3 = r3 - r4
            int r4 = r18.getPaddingBottom()
            int r3 = r3 - r4
            if (r3 > 0) goto L_0x0019
            return
        L_0x0019:
            android.view.View r4 = r0.mExpandedActionView
            if (r4 == 0) goto L_0x0020
            com.miuipub.internal.widget.ActionBarView$HomeView r4 = r0.r
            goto L_0x0022
        L_0x0020:
            com.miuipub.internal.widget.ActionBarView$HomeView r4 = r0.q
        L_0x0022:
            r5 = 8
            if (r4 == 0) goto L_0x0038
            int r6 = r4.getVisibility()
            if (r6 == r5) goto L_0x0038
            int r6 = r4.a()
            int r7 = r1 + r6
            int r4 = r0.positionChild(r4, r7, r2, r3)
            int r4 = r4 + r6
            int r1 = r1 + r4
        L_0x0038:
            android.view.View r4 = r0.mExpandedActionView
            r6 = 0
            r7 = 0
            r8 = 2
            if (r4 != 0) goto L_0x0171
            boolean r4 = r18.e()
            if (r4 == 0) goto L_0x00ee
            boolean r9 = r18.d()
            if (r9 == 0) goto L_0x00e7
            android.widget.TextView r9 = r0.t
            int r9 = r9.getMeasuredWidth()
            int r10 = r18.getWidth()
            int r10 = r10 - r1
            int r11 = r18.getPaddingRight()
            int r10 = r10 - r11
            int r10 = r10 - r9
            int r10 = r10 / r8
            android.view.View r9 = r0.v
            int r9 = r9.getMeasuredWidth()
            android.view.View r11 = r0.v
            android.view.ViewGroup$LayoutParams r11 = r11.getLayoutParams()
            boolean r12 = r11 instanceof android.widget.LinearLayout.LayoutParams
            if (r12 == 0) goto L_0x0075
            android.widget.LinearLayout$LayoutParams r11 = (android.widget.LinearLayout.LayoutParams) r11
            int r12 = r11.leftMargin
            int r11 = r11.rightMargin
            int r12 = r12 + r11
            int r9 = r9 + r12
        L_0x0075:
            android.widget.TextView r11 = r0.t
            android.view.ViewGroup$LayoutParams r11 = r11.getLayoutParams()
            boolean r12 = r11 instanceof android.widget.LinearLayout.LayoutParams
            if (r12 == 0) goto L_0x0084
            android.widget.LinearLayout$LayoutParams r11 = (android.widget.LinearLayout.LayoutParams) r11
            int r11 = r11.leftMargin
            int r9 = r9 + r11
        L_0x0084:
            int r10 = r10 - r9
            int r11 = r1 + r10
            android.widget.LinearLayout r12 = r0.s
            int r12 = r12.getMeasuredWidth()
            int r11 = r11 + r12
            android.view.View r12 = r0.z
            if (r12 == 0) goto L_0x00b2
            android.view.View r12 = r0.z
            android.view.ViewGroup$LayoutParams r12 = r12.getLayoutParams()
            boolean r13 = r12 instanceof android.app.ActionBar.LayoutParams
            if (r13 == 0) goto L_0x009f
            android.app.ActionBar$LayoutParams r12 = (android.app.ActionBar.LayoutParams) r12
            goto L_0x00a0
        L_0x009f:
            r12 = r6
        L_0x00a0:
            android.view.View r13 = r0.z
            int r13 = r13.getMeasuredWidth()
            if (r12 == 0) goto L_0x00b0
            int r14 = r12.leftMargin
            int r12 = r12.rightMargin
            int r14 = r14 + r12
            int r12 = r13 + r14
            goto L_0x00b3
        L_0x00b0:
            r12 = r13
            goto L_0x00b3
        L_0x00b2:
            r12 = 0
        L_0x00b3:
            int r13 = r18.getWidth()
            int r14 = r18.getPaddingRight()
            int r13 = r13 - r14
            int r12 = r13 - r12
            if (r11 <= r12) goto L_0x00d6
            int r11 = r11 + r10
            int r11 = r11 / r8
            if (r12 >= r11) goto L_0x00c6
            int r10 = r1 - r9
        L_0x00c6:
            android.widget.LinearLayout r9 = r0.s
            int r11 = r12 - r10
            int r13 = r0.mContentHeight
            r14 = 1073741824(0x40000000, float:2.0)
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r13, r14)
            r0.measureChildView(r9, r11, r13, r7)
            r11 = r12
        L_0x00d6:
            android.widget.LinearLayout r9 = r0.s
            int r9 = r9.getMeasuredHeight()
            int r12 = r3 - r9
            int r12 = r12 / r8
            int r12 = r12 + r2
            android.widget.LinearLayout r13 = r0.s
            int r9 = r9 + r12
            r13.layout(r10, r12, r11, r9)
            goto L_0x00ee
        L_0x00e7:
            android.widget.LinearLayout r9 = r0.s
            int r9 = r0.positionChild(r9, r1, r2, r3)
            int r1 = r1 + r9
        L_0x00ee:
            int r9 = r0.f
            switch(r9) {
                case 0: goto L_0x0171;
                case 1: goto L_0x015e;
                case 2: goto L_0x00f5;
                default: goto L_0x00f3;
            }
        L_0x00f3:
            goto L_0x0171
        L_0x00f5:
            com.miuipub.internal.widget.ScrollingTabContainerView r9 = r0.y
            if (r9 == 0) goto L_0x0171
            if (r4 == 0) goto L_0x00fe
            int r4 = r0.E
            int r1 = r1 + r4
        L_0x00fe:
            com.miuipub.internal.widget.ScrollingTabContainerView r4 = r0.y
            int r4 = r4.getMeasuredWidth()
            com.miuipub.internal.widget.ScrollingTabContainerView r9 = r0.y
            int r9 = r9.getMeasuredHeight()
            int r10 = r18.getWidth()
            int r11 = r18.getPaddingLeft()
            int r10 = r10 - r11
            int r11 = r18.getPaddingRight()
            int r10 = r10 - r11
            int r10 = r10 - r4
            int r10 = r10 / r8
            if (r1 <= r10) goto L_0x011d
            r10 = r1
        L_0x011d:
            int r4 = r4 + r10
            android.view.View r11 = r0.z
            if (r11 == 0) goto L_0x0142
            android.view.View r11 = r0.z
            android.view.ViewGroup$LayoutParams r11 = r11.getLayoutParams()
            boolean r12 = r11 instanceof android.app.ActionBar.LayoutParams
            if (r12 == 0) goto L_0x012f
            android.app.ActionBar$LayoutParams r11 = (android.app.ActionBar.LayoutParams) r11
            goto L_0x0130
        L_0x012f:
            r11 = r6
        L_0x0130:
            android.view.View r12 = r0.z
            int r12 = r12.getMeasuredWidth()
            if (r11 == 0) goto L_0x0140
            int r13 = r11.leftMargin
            int r11 = r11.rightMargin
            int r13 = r13 + r11
            int r11 = r12 + r13
            goto L_0x0143
        L_0x0140:
            r11 = r12
            goto L_0x0143
        L_0x0142:
            r11 = 0
        L_0x0143:
            int r12 = r18.getWidth()
            int r13 = r18.getPaddingRight()
            int r12 = r12 - r13
            int r12 = r12 - r11
            if (r4 <= r12) goto L_0x0156
            int r4 = r4 + r10
            int r4 = r4 / r8
            if (r12 >= r4) goto L_0x0154
            r10 = r1
        L_0x0154:
            r1 = r12
            goto L_0x0157
        L_0x0156:
            r1 = r4
        L_0x0157:
            com.miuipub.internal.widget.ScrollingTabContainerView r4 = r0.y
            int r9 = r9 + r2
            r4.layout(r10, r2, r1, r9)
            goto L_0x0171
        L_0x015e:
            android.widget.LinearLayout r9 = r0.x
            if (r9 == 0) goto L_0x0171
            if (r4 == 0) goto L_0x0167
            int r4 = r0.E
            int r1 = r1 + r4
        L_0x0167:
            android.widget.LinearLayout r4 = r0.x
            int r4 = r0.positionChild(r4, r1, r2, r3)
            int r9 = r0.E
            int r4 = r4 + r9
            int r1 = r1 + r4
        L_0x0171:
            r9 = r1
            int r1 = r22 - r20
            int r4 = r18.getPaddingRight()
            int r1 = r1 - r4
            com.miuipub.internal.view.menu.ActionMenuView r4 = r0.mMenuView
            if (r4 == 0) goto L_0x0191
            com.miuipub.internal.view.menu.ActionMenuView r4 = r0.mMenuView
            android.view.ViewParent r4 = r4.getParent()
            if (r4 != r0) goto L_0x0191
            com.miuipub.internal.view.menu.ActionMenuView r4 = r0.mMenuView
            r0.positionChildInverse(r4, r1, r2, r3)
            com.miuipub.internal.view.menu.ActionMenuView r4 = r0.mMenuView
            int r4 = r4.getMeasuredWidth()
            int r1 = r1 - r4
        L_0x0191:
            android.widget.ProgressBar r4 = r0.B
            if (r4 == 0) goto L_0x01b2
            android.widget.ProgressBar r4 = r0.B
            int r4 = r4.getVisibility()
            if (r4 == r5) goto L_0x01b2
            android.widget.ProgressBar r4 = r0.B
            int r10 = r0.D
            int r10 = r1 - r10
            r0.positionChildInverse(r4, r10, r2, r3)
            android.widget.ProgressBar r4 = r0.B
            int r4 = r4.getMeasuredWidth()
            int r10 = r0.D
            int r10 = r10 * 2
            int r4 = r4 - r10
            int r1 = r1 - r4
        L_0x01b2:
            android.view.View r4 = r0.C
            if (r4 == 0) goto L_0x01ca
            android.view.View r4 = r0.C
            int r4 = r4.getVisibility()
            if (r4 == r5) goto L_0x01ca
            android.view.View r4 = r0.C
            r0.positionChildInverse(r4, r1, r2, r3)
            android.view.View r2 = r0.C
            int r2 = r2.getMeasuredWidth()
            int r1 = r1 - r2
        L_0x01ca:
            android.view.View r2 = r0.mExpandedActionView
            r3 = 16
            if (r2 == 0) goto L_0x01d3
            android.view.View r2 = r0.mExpandedActionView
            goto L_0x01e0
        L_0x01d3:
            int r2 = r0.g
            r2 = r2 & r3
            if (r2 == 0) goto L_0x01df
            android.view.View r2 = r0.z
            if (r2 == 0) goto L_0x01df
            android.view.View r2 = r0.z
            goto L_0x01e0
        L_0x01df:
            r2 = r6
        L_0x01e0:
            r4 = 1
            if (r2 == 0) goto L_0x0281
            android.view.ViewGroup$LayoutParams r10 = r2.getLayoutParams()
            boolean r11 = r10 instanceof android.app.ActionBar.LayoutParams
            if (r11 == 0) goto L_0x01ee
            r6 = r10
            android.app.ActionBar$LayoutParams r6 = (android.app.ActionBar.LayoutParams) r6
        L_0x01ee:
            if (r6 == 0) goto L_0x01f3
            int r10 = r6.gravity
            goto L_0x01f5
        L_0x01f3:
            r10 = 19
        L_0x01f5:
            int r11 = r2.getMeasuredWidth()
            if (r6 == 0) goto L_0x0206
            int r12 = r6.leftMargin
            int r9 = r9 + r12
            int r12 = r6.rightMargin
            int r1 = r1 - r12
            int r12 = r6.topMargin
            int r6 = r6.bottomMargin
            goto L_0x0208
        L_0x0206:
            r6 = 0
            r12 = 0
        L_0x0208:
            r13 = r10 & 7
            r14 = 5
            r15 = -1
            r7 = 3
            if (r13 != r4) goto L_0x0220
            int r17 = r18.getWidth()
            int r17 = r17 - r11
            int r5 = r17 / 2
            if (r5 >= r9) goto L_0x021b
        L_0x0219:
            r13 = 3
            goto L_0x0223
        L_0x021b:
            int r5 = r5 + r11
            if (r5 <= r1) goto L_0x0223
            r13 = 5
            goto L_0x0223
        L_0x0220:
            if (r10 != r15) goto L_0x0223
            goto L_0x0219
        L_0x0223:
            if (r13 == r4) goto L_0x0230
            if (r13 == r7) goto L_0x022e
            if (r13 == r14) goto L_0x022b
            r7 = 0
            goto L_0x0237
        L_0x022b:
            int r7 = r1 - r11
            goto L_0x0237
        L_0x022e:
            r7 = r9
            goto L_0x0237
        L_0x0230:
            int r1 = r18.getWidth()
            int r1 = r1 - r11
            int r7 = r1 / 2
        L_0x0237:
            r1 = r10 & 112(0x70, float:1.57E-43)
            if (r10 != r15) goto L_0x023d
            r1 = 16
        L_0x023d:
            if (r1 == r3) goto L_0x025f
            r3 = 48
            if (r1 == r3) goto L_0x0259
            r3 = 80
            if (r1 == r3) goto L_0x0249
            r1 = 0
            goto L_0x0274
        L_0x0249:
            int r1 = r18.getHeight()
            int r3 = r18.getPaddingBottom()
            int r1 = r1 - r3
            int r3 = r2.getMeasuredHeight()
            int r1 = r1 - r3
            int r1 = r1 - r6
            goto L_0x0274
        L_0x0259:
            int r1 = r18.getPaddingTop()
            int r1 = r1 + r12
            goto L_0x0274
        L_0x025f:
            int r1 = r18.getPaddingTop()
            int r3 = r18.getHeight()
            int r5 = r18.getPaddingBottom()
            int r3 = r3 - r5
            int r3 = r3 - r1
            int r1 = r2.getMeasuredHeight()
            int r3 = r3 - r1
            int r1 = r3 / 2
        L_0x0274:
            int r3 = r2.getMeasuredWidth()
            int r3 = r3 + r7
            int r5 = r2.getMeasuredHeight()
            int r5 = r5 + r1
            r2.layout(r7, r1, r3, r5)
        L_0x0281:
            android.widget.ProgressBar r1 = r0.A
            if (r1 == 0) goto L_0x02a2
            android.widget.ProgressBar r1 = r0.A
            r1.bringToFront()
            android.widget.ProgressBar r1 = r0.A
            int r1 = r1.getMeasuredHeight()
            int r1 = r1 / r8
            android.widget.ProgressBar r2 = r0.A
            int r3 = r0.D
            int r5 = -r1
            int r6 = r0.D
            android.widget.ProgressBar r7 = r0.A
            int r7 = r7.getMeasuredWidth()
            int r6 = r6 + r7
            r2.layout(r3, r5, r6, r1)
        L_0x02a2:
            android.view.View r1 = r0.v
            if (r1 == 0) goto L_0x02f3
            android.view.View r1 = r0.v
            int r1 = r1.getVisibility()
            r2 = 8
            if (r1 == r2) goto L_0x02f3
            android.view.View r1 = r0.v
            int r1 = r1.getHeight()
            int[] r2 = new int[r8]
            android.widget.TextView r3 = r0.t
            r3.getLocationInWindow(r2)
            android.widget.TextView r3 = r0.t
            int r3 = r3.getVisibility()
            if (r3 != 0) goto L_0x02ce
            android.widget.TextView r3 = r0.t
            int r7 = r3.getHeight()
            r16 = r7
            goto L_0x02d0
        L_0x02ce:
            r16 = 0
        L_0x02d0:
            int[] r3 = new int[r8]
            android.view.View r5 = r0.v
            android.view.ViewParent r5 = r5.getParent()
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            r5.getLocationInWindow(r3)
            r2 = r2[r4]
            int r16 = r16 - r1
            int r16 = r16 / 2
            int r2 = r2 + r16
            r3 = r3[r4]
            int r2 = r2 - r3
            android.view.View r3 = r0.v
            r3.setTop(r2)
            android.view.View r3 = r0.v
            int r2 = r2 + r1
            r3.setBottom(r2)
        L_0x02f3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miuipub.internal.widget.ActionBarView.onLayout(boolean, int, int, int, int):void");
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ActionBar.LayoutParams(getContext(), attributeSet);
    }

    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams == null ? generateDefaultLayoutParams() : layoutParams;
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (!(this.P == null || this.P.b == null)) {
            savedState.f8337a = this.P.b.getItemId();
        }
        savedState.b = isOverflowMenuShowing();
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem findItem;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (!(savedState.f8337a == 0 || this.P == null || this.L == null || (findItem = this.L.findItem(savedState.f8337a)) == null)) {
            findItem.expandActionView();
        }
        if (savedState.b) {
            postShowOverflowMenu();
        }
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        if (this.q != null) {
            this.q.b(drawable);
            return;
        }
        this.o = drawable;
        this.p = 0;
    }

    public void setHomeAsUpIndicator(int i2) {
        if (this.q != null) {
            this.q.a(i2);
            return;
        }
        this.o = null;
        this.p = i2;
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        int f8337a;
        boolean b;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f8337a = parcel.readInt();
            this.b = parcel.readInt() != 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f8337a);
            parcel.writeInt(this.b ? 1 : 0);
        }
    }

    private static class HomeView extends FrameLayout {

        /* renamed from: a  reason: collision with root package name */
        private ImageView f8336a;
        private ImageView b;
        private int c;
        private int d;
        private Drawable e;

        public int a() {
            return 0;
        }

        public HomeView(Context context) {
            this(context, (AttributeSet) null);
        }

        public HomeView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void a(boolean z) {
            this.f8336a.setVisibility(z ? 0 : 8);
        }

        public void a(Drawable drawable) {
            this.b.setImageDrawable(drawable);
        }

        public void b(Drawable drawable) {
            ImageView imageView = this.f8336a;
            if (drawable == null) {
                drawable = this.e;
            }
            imageView.setImageDrawable(drawable);
            this.d = 0;
        }

        public void a(int i) {
            this.d = i;
            this.f8336a.setImageDrawable(i != 0 ? getResources().getDrawable(i) : null);
        }

        /* access modifiers changed from: protected */
        public void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            if (this.d != 0) {
                a(this.d);
            }
        }

        public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            CharSequence contentDescription = getContentDescription();
            if (TextUtils.isEmpty(contentDescription)) {
                return true;
            }
            accessibilityEvent.getText().add(contentDescription);
            return true;
        }

        /* access modifiers changed from: protected */
        public void onFinishInflate() {
            this.f8336a = (ImageView) findViewById(R.id.up);
            this.b = (ImageView) findViewById(R.id.home);
            this.e = this.f8336a.getDrawable();
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            measureChildWithMargins(this.f8336a, i, 0, i2, 0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f8336a.getLayoutParams();
            this.c = layoutParams.leftMargin + this.f8336a.getMeasuredWidth() + layoutParams.rightMargin;
            int i3 = this.f8336a.getVisibility() == 8 ? 0 : this.c;
            int measuredHeight = layoutParams.bottomMargin + layoutParams.topMargin + this.f8336a.getMeasuredHeight();
            measureChildWithMargins(this.b, i, i3, i2, 0);
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.b.getLayoutParams();
            int measuredWidth = i3 + layoutParams2.leftMargin + this.b.getMeasuredWidth() + layoutParams2.rightMargin;
            int max = Math.max(measuredHeight, layoutParams2.topMargin + this.b.getMeasuredHeight() + layoutParams2.bottomMargin);
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i);
            int size2 = View.MeasureSpec.getSize(i2);
            if (mode == Integer.MIN_VALUE) {
                measuredWidth = Math.min(measuredWidth, size);
            } else if (mode == 1073741824) {
                measuredWidth = size;
            }
            if (mode2 == Integer.MIN_VALUE) {
                max = Math.min(max, size2);
            } else if (mode2 == 1073741824) {
                max = size2;
            }
            setMeasuredDimension(measuredWidth, max);
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            int i5 = (i4 - i2) / 2;
            int i6 = 0;
            if (this.f8336a.getVisibility() != 8) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f8336a.getLayoutParams();
                int measuredHeight = this.f8336a.getMeasuredHeight();
                int measuredWidth = this.f8336a.getMeasuredWidth();
                int i7 = i5 - (measuredHeight / 2);
                this.f8336a.layout(0, i7, measuredWidth, measuredHeight + i7);
                i6 = layoutParams.leftMargin + measuredWidth + layoutParams.rightMargin;
                i += i6;
            }
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.b.getLayoutParams();
            int measuredHeight2 = this.b.getMeasuredHeight();
            int measuredWidth2 = this.b.getMeasuredWidth();
            int max = i6 + Math.max(layoutParams2.leftMargin, ((i3 - i) / 2) - (measuredWidth2 / 2));
            int max2 = Math.max(layoutParams2.topMargin, i5 - (measuredHeight2 / 2));
            this.b.layout(max, max2, measuredWidth2 + max, measuredHeight2 + max2);
        }
    }

    private class ExpandedActionViewMenuPresenter implements MenuPresenter {

        /* renamed from: a  reason: collision with root package name */
        MenuBuilder f8335a;
        MenuItemImpl b;

        public MenuView a(ViewGroup viewGroup) {
            return null;
        }

        public void a(Parcelable parcelable) {
        }

        public void a(MenuBuilder menuBuilder, boolean z) {
        }

        public void a(MenuPresenter.Callback callback) {
        }

        public boolean a(SubMenuBuilder subMenuBuilder) {
            return false;
        }

        public boolean e() {
            return false;
        }

        public Parcelable f() {
            return null;
        }

        public int g() {
            return 0;
        }

        private ExpandedActionViewMenuPresenter() {
        }

        public void a(Context context, MenuBuilder menuBuilder) {
            if (!(this.f8335a == null || this.b == null)) {
                this.f8335a.d(this.b);
            }
            this.f8335a = menuBuilder;
        }

        public void d(boolean z) {
            if (this.b != null) {
                boolean z2 = false;
                if (this.f8335a != null) {
                    int size = this.f8335a.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        } else if (this.f8335a.getItem(i) == this.b) {
                            z2 = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                if (!z2) {
                    b(this.f8335a, this.b);
                }
            }
        }

        public boolean a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            ActionBarView.this.mExpandedActionView = menuItemImpl.getActionView();
            ActionBarView.this.h();
            ActionBarView.this.r.a(ActionBarView.this.getIcon().getConstantState().newDrawable(ActionBarView.this.getResources()));
            this.b = menuItemImpl;
            if (ActionBarView.this.mExpandedActionView.getParent() != ActionBarView.this) {
                ActionBarView.this.addView(ActionBarView.this.mExpandedActionView);
            }
            if (ActionBarView.this.r.getParent() != ActionBarView.this) {
                ActionBarView.this.addView(ActionBarView.this.r);
            }
            if (ActionBarView.this.q != null) {
                ActionBarView.this.q.setVisibility(8);
            }
            if (ActionBarView.this.s != null) {
                ActionBarView.this.s.setVisibility(8);
            }
            if (ActionBarView.this.y != null) {
                ActionBarView.this.y.setVisibility(8);
            }
            if (ActionBarView.this.w != null) {
                ActionBarView.this.w.setVisibility(8);
            }
            if (ActionBarView.this.z != null) {
                ActionBarView.this.z.setVisibility(8);
            }
            ActionBarView.this.requestLayout();
            menuItemImpl.e(true);
            if (ActionBarView.this.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView) ActionBarView.this.mExpandedActionView).onActionViewExpanded();
            }
            return true;
        }

        public boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            if (ActionBarView.this.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView) ActionBarView.this.mExpandedActionView).onActionViewCollapsed();
            }
            ActionBarView.this.removeView(ActionBarView.this.mExpandedActionView);
            ActionBarView.this.removeView(ActionBarView.this.r);
            ActionBarView.this.mExpandedActionView = null;
            if ((ActionBarView.this.g & 2) != 0) {
                ActionBarView.this.q.setVisibility(0);
            }
            if ((ActionBarView.this.g & 8) != 0) {
                if (ActionBarView.this.s == null) {
                    ActionBarView.this.a();
                } else {
                    ActionBarView.this.s.setVisibility(0);
                }
            }
            if (ActionBarView.this.y != null && ActionBarView.this.f == 2) {
                ActionBarView.this.y.setVisibility(0);
            }
            if (ActionBarView.this.w != null && ActionBarView.this.f == 1) {
                ActionBarView.this.w.setVisibility(0);
            }
            if (!(ActionBarView.this.z == null || (ActionBarView.this.g & 16) == 0)) {
                ActionBarView.this.z.setVisibility(0);
            }
            ActionBarView.this.r.a((Drawable) null);
            this.b = null;
            ActionBarView.this.requestLayout();
            menuItemImpl.e(false);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public ActionMenuPresenter createActionMenuPresenter(MenuPresenter.Callback callback) {
        ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.m, R.layout.v6_action_menu_layout, R.layout.v6_action_menu_item_layout, R.layout.v6_action_bar_expanded_menu_layout, R.layout.v6_action_bar_list_menu_item_layout);
        actionMenuPresenter.a(callback);
        actionMenuPresenter.b(R.id.v6_action_menu_presenter);
        return actionMenuPresenter;
    }

    /* access modifiers changed from: protected */
    public ExpandedActionViewMenuPresenter createExpandedActionViewMenuPresenter() {
        return new ExpandedActionViewMenuPresenter();
    }

    public void onWindowShow() {
        this.mSplitView.onWindowShow();
    }

    public void onWindowHide() {
        this.mSplitView.onWindowHide();
    }

    public void setProgressBarVisibility(boolean z2) {
        a(z2 ? -1 : -2);
    }

    public void setProgressBarIndeterminateVisibility(boolean z2) {
        a(z2 ? -1 : -2);
    }

    public void setProgressBarIndeterminate(boolean z2) {
        a(z2 ? -3 : -4);
    }

    public void setProgress(int i2) {
        a(i2 + 0);
    }

    private void a(int i2) {
        ProgressBar circularProgressBar = getCircularProgressBar();
        ProgressBar horizontalProgressBar = getHorizontalProgressBar();
        if (i2 == -1) {
            if (horizontalProgressBar != null) {
                horizontalProgressBar.setVisibility((horizontalProgressBar.isIndeterminate() || horizontalProgressBar.getProgress() < 10000) ? 0 : 4);
            }
            if (circularProgressBar != null) {
                circularProgressBar.setVisibility(0);
            }
        } else if (i2 == -2) {
            if (horizontalProgressBar != null) {
                horizontalProgressBar.setVisibility(8);
            }
            if (circularProgressBar != null) {
                circularProgressBar.setVisibility(8);
            }
        } else if (i2 == -3) {
            horizontalProgressBar.setIndeterminate(true);
        } else if (i2 == -4) {
            horizontalProgressBar.setIndeterminate(false);
        } else if (i2 >= 0 && i2 <= 10000) {
            horizontalProgressBar.setProgress(i2 + 0);
            if (i2 < 10000) {
                a(horizontalProgressBar, circularProgressBar);
            } else {
                b(horizontalProgressBar, circularProgressBar);
            }
        }
    }

    private void a(ProgressBar progressBar, ProgressBar progressBar2) {
        if (progressBar2 != null && progressBar2.getVisibility() == 4) {
            progressBar2.setVisibility(0);
        }
        if (progressBar != null && progressBar.getProgress() < 10000) {
            progressBar.setVisibility(0);
        }
    }

    private void b(ProgressBar progressBar, ProgressBar progressBar2) {
        if (progressBar2 != null && progressBar2.getVisibility() == 0) {
            progressBar2.setVisibility(4);
        }
        if (progressBar != null && progressBar.getVisibility() == 0) {
            progressBar.setVisibility(4);
        }
    }

    private ProgressBar getCircularProgressBar() {
        ProgressBar progressBar = this.B;
        if (progressBar != null) {
            progressBar.setVisibility(4);
        }
        return progressBar;
    }

    private ProgressBar getHorizontalProgressBar() {
        ProgressBar progressBar = this.A;
        if (progressBar != null) {
            progressBar.setVisibility(4);
        }
        return progressBar;
    }

    /* access modifiers changed from: private */
    public Drawable getIcon() {
        if ((this.j & 1) != 1) {
            if (this.m instanceof Activity) {
                try {
                    this.k = this.m.getPackageManager().getActivityIcon(((Activity) this.m).getComponentName());
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.e(f8330a, "Activity component name not found!", e2);
                }
            }
            if (this.k == null) {
                this.k = this.m.getApplicationInfo().loadIcon(this.m.getPackageManager());
            }
            this.j |= 1;
        }
        return this.k;
    }

    private Drawable getLogo() {
        if ((this.j & 2) != 2) {
            if (Build.VERSION.SDK_INT >= 9) {
                if (this.m instanceof Activity) {
                    try {
                        this.l = this.m.getPackageManager().getActivityLogo(((Activity) this.m).getComponentName());
                    } catch (PackageManager.NameNotFoundException e2) {
                        Log.e(f8330a, "Activity component name not found!", e2);
                    }
                }
                if (this.l == null) {
                    this.l = this.m.getApplicationInfo().loadLogo(this.m.getPackageManager());
                }
            }
            this.j |= 2;
        }
        return this.l;
    }

    private void g() {
        if (this.q == null) {
            this.q = (HomeView) LayoutInflater.from(this.m).inflate(this.n, this, false);
            this.q.setOnClickListener(this.S);
            this.q.setClickable(true);
            this.q.setFocusable(true);
            if (this.p != 0) {
                this.q.a(this.p);
                this.p = 0;
            }
            if (this.o != null) {
                this.q.b(this.o);
                this.o = null;
            }
            addView(this.q);
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.r == null) {
            this.r = (HomeView) LayoutInflater.from(this.m).inflate(this.n, this, false);
            this.r.a(true);
            this.r.setOnClickListener(this.R);
        }
    }
}
