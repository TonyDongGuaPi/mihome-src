package com.miuipub.internal.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.SpinnerAdapter;
import com.miuipub.internal.view.ActionBarPolicy;
import com.miuipub.internal.view.ActionModeImpl;
import com.miuipub.internal.view.EditActionModeImpl;
import com.miuipub.internal.view.menu.PhoneActionMenuView;
import com.miuipub.internal.widget.ActionBarContainer;
import com.miuipub.internal.widget.ActionBarContextView;
import com.miuipub.internal.widget.ActionBarOverlayLayout;
import com.miuipub.internal.widget.ActionBarView;
import com.miuipub.internal.widget.ActionModeView;
import com.miuipub.internal.widget.ScrollingTabContainerView;
import java.util.ArrayList;
import miuipub.app.ActionBar;
import miuipub.v6.R;

public class ActionBarImpl extends ActionBar {
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = -1;
    /* access modifiers changed from: private */
    public static ActionBar.TabListener e = new ActionBar.TabListener() {
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            TabImpl tabImpl = (TabImpl) tab;
            if (tabImpl.c != null) {
                tabImpl.c.onTabSelected(tab, fragmentTransaction);
            }
            if (tabImpl.b != null) {
                tabImpl.b.onTabSelected(tab, fragmentTransaction);
            }
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            TabImpl tabImpl = (TabImpl) tab;
            if (tabImpl.c != null) {
                tabImpl.c.onTabUnselected(tab, fragmentTransaction);
            }
            if (tabImpl.b != null) {
                tabImpl.b.onTabUnselected(tab, fragmentTransaction);
            }
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            TabImpl tabImpl = (TabImpl) tab;
            if (tabImpl.c != null) {
                tabImpl.c.onTabReselected(tab, fragmentTransaction);
            }
            if (tabImpl.b != null) {
                tabImpl.b.onTabReselected(tab, fragmentTransaction);
            }
        }
    };
    private int A = 0;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E = true;
    private boolean F;
    private ActionModeImpl.ActionModeCallback G = new ActionModeImpl.ActionModeCallback() {
        public void a(ActionMode actionMode) {
            ActionBarImpl.this.b(false);
            ActionBarImpl.this.f8221a = null;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    ActionMode f8221a;
    /* access modifiers changed from: private */
    public Context f;
    private Context g;
    private ActionBarOverlayLayout h;
    private ActionBarContainer i;
    private ActionBarView j;
    private ActionBarContextView k;
    private ActionBarContainer l;
    /* access modifiers changed from: private */
    public PhoneActionMenuView m;
    private View n;
    private View.OnClickListener o;
    private ActionBarViewPagerController p;
    /* access modifiers changed from: private */
    public ScrollingTabContainerView q;
    private ActionModeView r;
    private ArrayList<TabImpl> s = new ArrayList<>();
    private TabImpl t;
    private FragmentManager u;
    private int v = -1;
    private boolean w;
    private ArrayList<ActionBar.OnMenuVisibilityListener> x = new ArrayList<>();
    private int y;
    private boolean z;

    private static boolean a(boolean z2, boolean z3, boolean z4) {
        if (z4) {
            return true;
        }
        return !z2 && !z3;
    }

    public void c(boolean z2) {
    }

    public ActionBarImpl(Activity activity) {
        this.f = activity;
        this.u = activity.getFragmentManager();
        a((ViewGroup) activity.getWindow().getDecorView().findViewById(R.id.action_bar_overlay_layout));
        setTitle(activity.getTitle());
    }

    public ActionBarImpl(Fragment fragment) {
        this.f = ((IFragment) fragment).P();
        this.u = fragment.getFragmentManager();
        a((ViewGroup) fragment.getView());
        Activity activity = fragment.getActivity();
        setTitle(activity != null ? activity.getTitle() : null);
    }

    public ActionBarImpl(Dialog dialog) {
        this.f = dialog.getContext();
        a((ViewGroup) dialog.getWindow().getDecorView().findViewById(R.id.action_bar_overlay_layout));
    }

    public static ActionBarImpl a(View view) {
        while (view != null) {
            if (view instanceof ActionBarOverlayLayout) {
                return (ActionBarImpl) ((ActionBarOverlayLayout) view).getActionBar();
            }
            view = view.getParent() instanceof View ? (View) view.getParent() : null;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void a(ViewGroup viewGroup) {
        this.h = (ActionBarOverlayLayout) viewGroup;
        this.h.setActionBar(this);
        this.j = (ActionBarView) viewGroup.findViewById(R.id.action_bar);
        this.k = (ActionBarContextView) viewGroup.findViewById(R.id.action_context_bar);
        this.i = (ActionBarContainer) viewGroup.findViewById(R.id.action_bar_container);
        this.l = (ActionBarContainer) viewGroup.findViewById(R.id.split_action_bar);
        this.n = viewGroup.findViewById(R.id.content_mask);
        if (this.n != null) {
            this.o = new View.OnClickListener() {
                public void onClick(View view) {
                    if (ActionBarImpl.this.m != null && ActionBarImpl.this.m.isOverflowMenuShowing()) {
                        ActionBarImpl.this.m.getPresenter().e(true);
                    }
                }
            };
        }
        if (this.j == null || this.k == null || this.i == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.y = this.j.isSplitActionBar() ? 1 : 0;
        boolean z2 = false;
        boolean z3 = (this.j.getDisplayOptions() & 4) != 0;
        if (z3) {
            this.w = true;
        }
        ActionBarPolicy a2 = ActionBarPolicy.a(this.f);
        if (a2.g() || z3) {
            z2 = true;
        }
        setHomeButtonEnabled(z2);
        j(a2.d());
    }

    public void a(Configuration configuration) {
        j(ActionBarPolicy.a(this.f).d());
    }

    private void j(boolean z2) {
        this.z = z2;
        if (!this.z) {
            this.j.setEmbeddedTabView((ScrollingTabContainerView) null);
            this.i.setTabContainer(this.q);
        } else {
            this.i.setTabContainer((ScrollingTabContainerView) null);
            this.j.setEmbeddedTabView(this.q);
        }
        boolean z3 = true;
        boolean z4 = getNavigationMode() == 2;
        if (this.q != null) {
            if (z4) {
                this.q.setVisibility(0);
            } else {
                this.q.setVisibility(8);
            }
        }
        ActionBarView actionBarView = this.j;
        if (this.z || !z4) {
            z3 = false;
        }
        actionBarView.setCollapsable(z3);
    }

    public boolean a() {
        return !this.z && getNavigationMode() == 2;
    }

    public void a(boolean z2) {
        j(z2);
    }

    public void setCustomView(View view) {
        this.j.setCustomNavigationView(view);
    }

    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
        view.setLayoutParams(layoutParams);
        this.j.setCustomNavigationView(view);
    }

    public void setIcon(int i2) {
        this.j.setIcon(i2);
    }

    public void setIcon(Drawable drawable) {
        this.j.setIcon(drawable);
    }

    public void setLogo(int i2) {
        this.j.setLogo(i2);
    }

    public void setLogo(Drawable drawable) {
        this.j.setLogo(drawable);
    }

    public void setListNavigationCallbacks(SpinnerAdapter spinnerAdapter, ActionBar.OnNavigationListener onNavigationListener) {
        this.j.setDropdownAdapter(spinnerAdapter);
        this.j.setCallback(onNavigationListener);
    }

    public void setSelectedNavigationItem(int i2) {
        switch (this.j.getNavigationMode()) {
            case 1:
                this.j.setDropdownSelectedPosition(i2);
                return;
            case 2:
                selectTab(this.s.get(i2));
                return;
            default:
                throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
        }
    }

    public int getSelectedNavigationIndex() {
        switch (this.j.getNavigationMode()) {
            case 1:
                return this.j.getDropdownSelectedPosition();
            case 2:
                if (this.t != null) {
                    return this.t.getPosition();
                }
                return -1;
            default:
                return -1;
        }
    }

    public int getNavigationItemCount() {
        switch (this.j.getNavigationMode()) {
            case 1:
                SpinnerAdapter dropdownAdapter = this.j.getDropdownAdapter();
                if (dropdownAdapter != null) {
                    return dropdownAdapter.getCount();
                }
                return 0;
            case 2:
                return this.s.size();
            default:
                return 0;
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.j.setTitle(charSequence);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.j.setSubtitle(charSequence);
    }

    public void setDisplayOptions(int i2, int i3) {
        int displayOptions = this.j.getDisplayOptions();
        if ((i3 & 4) != 0) {
            this.w = true;
        }
        this.j.setDisplayOptions((i2 & i3) | ((i3 ^ -1) & displayOptions));
    }

    public void setDisplayUseLogoEnabled(boolean z2) {
        setDisplayOptions(z2 ? 1 : 0, 1);
    }

    public void setDisplayShowHomeEnabled(boolean z2) {
        setDisplayOptions(z2 ? 2 : 0, 2);
    }

    public void setDisplayHomeAsUpEnabled(boolean z2) {
        setDisplayOptions(z2 ? 4 : 0, 4);
    }

    public void setDisplayShowTitleEnabled(boolean z2) {
        setDisplayOptions(z2 ? 8 : 0, 8);
    }

    public void setDisplayShowCustomEnabled(boolean z2) {
        setDisplayOptions(z2 ? 16 : 0, 16);
    }

    public void setHomeButtonEnabled(boolean z2) {
        this.j.setHomeButtonEnabled(z2);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.i.setPrimaryBackground(drawable);
    }

    public View getCustomView() {
        return this.j.getCustomNavigationView();
    }

    public void setCustomView(int i2) {
        setCustomView(LayoutInflater.from(getThemedContext()).inflate(i2, this.j, false));
    }

    public CharSequence getTitle() {
        return this.j.getTitle();
    }

    public void setTitle(int i2) {
        setTitle((CharSequence) this.f.getString(i2));
    }

    public CharSequence getSubtitle() {
        return this.j.getSubtitle();
    }

    public void setSubtitle(int i2) {
        setSubtitle((CharSequence) this.f.getString(i2));
    }

    public int getNavigationMode() {
        return this.j.getNavigationMode();
    }

    public void setNavigationMode(int i2) {
        if (this.j.getNavigationMode() == 2) {
            this.v = getSelectedNavigationIndex();
            selectTab((ActionBar.Tab) null);
            this.q.setVisibility(8);
        }
        this.j.setNavigationMode(i2);
        boolean z2 = false;
        if (i2 == 2) {
            l();
            this.q.setVisibility(0);
            if (this.v != -1) {
                setSelectedNavigationItem(this.v);
                this.v = -1;
            }
        }
        ActionBarView actionBarView = this.j;
        if (i2 == 2 && !this.z) {
            z2 = true;
        }
        actionBarView.setCollapsable(z2);
    }

    public int getDisplayOptions() {
        return this.j.getDisplayOptions();
    }

    public void setDisplayOptions(int i2) {
        if ((i2 & 4) != 0) {
            this.w = true;
        }
        this.j.setDisplayOptions(i2);
    }

    public ActionBar.Tab newTab() {
        return new TabImpl();
    }

    public void addTab(ActionBar.Tab tab) {
        addTab(tab, this.s.isEmpty());
    }

    public void addTab(ActionBar.Tab tab, boolean z2) {
        if (!f()) {
            a(tab, z2);
            return;
        }
        throw new IllegalStateException("Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab().");
    }

    public void addTab(ActionBar.Tab tab, int i2) {
        addTab(tab, i2, this.s.isEmpty());
    }

    public void addTab(ActionBar.Tab tab, int i2, boolean z2) {
        if (!f()) {
            a(tab, i2, z2);
            return;
        }
        throw new IllegalStateException("Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab().");
    }

    /* access modifiers changed from: package-private */
    public ActionBarOverlayLayout b() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public void a(ActionBar.Tab tab) {
        a(tab, getTabCount() == 0);
    }

    /* access modifiers changed from: package-private */
    public void a(ActionBar.Tab tab, boolean z2) {
        l();
        this.q.addTab(tab, z2);
        b(tab, this.s.size());
        if (z2) {
            selectTab(tab);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ActionBar.Tab tab, int i2) {
        a(tab, i2, i2 == getTabCount());
    }

    /* access modifiers changed from: package-private */
    public void a(ActionBar.Tab tab, int i2, boolean z2) {
        l();
        this.q.addTab(tab, i2, z2);
        b(tab, i2);
        if (z2) {
            selectTab(tab);
        }
    }

    public void removeTab(ActionBar.Tab tab) {
        if (!f()) {
            b(tab);
            return;
        }
        throw new IllegalStateException("Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab().");
    }

    public void removeTabAt(int i2) {
        if (!f()) {
            a(i2);
            return;
        }
        throw new IllegalStateException("Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab().");
    }

    public void removeAllTabs() {
        if (!f()) {
            c();
            return;
        }
        throw new IllegalStateException("Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab().");
    }

    /* access modifiers changed from: package-private */
    public void b(ActionBar.Tab tab) {
        a(tab.getPosition());
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        TabImpl tabImpl;
        if (this.q != null) {
            int position = this.t != null ? this.t.getPosition() : this.v;
            this.q.removeTabAt(i2);
            TabImpl remove = this.s.remove(i2);
            if (remove != null) {
                remove.a(-1);
            }
            int size = this.s.size();
            for (int i3 = i2; i3 < size; i3++) {
                this.s.get(i3).a(i3);
            }
            if (position == i2) {
                if (this.s.isEmpty()) {
                    tabImpl = null;
                } else {
                    tabImpl = this.s.get(Math.max(0, i2 - 1));
                }
                selectTab(tabImpl);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        m();
    }

    public void selectTab(ActionBar.Tab tab) {
        int i2 = -1;
        if (getNavigationMode() != 2) {
            if (tab != null) {
                i2 = tab.getPosition();
            }
            this.v = i2;
            return;
        }
        FragmentTransaction disallowAddToBackStack = this.u.beginTransaction().disallowAddToBackStack();
        if (this.t != tab) {
            ScrollingTabContainerView scrollingTabContainerView = this.q;
            if (tab != null) {
                i2 = tab.getPosition();
            }
            scrollingTabContainerView.setTabSelected(i2);
            if (this.t != null) {
                this.t.a().onTabUnselected(this.t, disallowAddToBackStack);
            }
            this.t = (TabImpl) tab;
            if (this.t != null) {
                this.t.a().onTabSelected(this.t, disallowAddToBackStack);
            }
        } else if (this.t != null) {
            this.t.a().onTabReselected(this.t, disallowAddToBackStack);
            this.q.animateToTab(tab.getPosition());
        }
        if (!disallowAddToBackStack.isEmpty()) {
            disallowAddToBackStack.commit();
        }
    }

    public ActionBar.Tab getSelectedTab() {
        return this.t;
    }

    public ActionBar.Tab getTabAt(int i2) {
        return this.s.get(i2);
    }

    public int getTabCount() {
        return this.s.size();
    }

    public Context getThemedContext() {
        if (this.g == null) {
            TypedValue typedValue = new TypedValue();
            this.f.getTheme().resolveAttribute(16843671, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.g = new ContextThemeWrapper(this.f, i2);
            } else {
                this.g = this.f;
            }
        }
        return this.g;
    }

    public int getHeight() {
        return this.i.getHeight();
    }

    public void show() {
        if (this.B) {
            this.B = false;
            k(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        if (!this.D) {
            this.D = true;
            k(false);
        }
    }

    public void hide() {
        if (!this.B) {
            this.B = true;
            k(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void e() {
        if (this.D) {
            this.D = false;
            k(false);
        }
    }

    public boolean isShowing() {
        return this.E;
    }

    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.x.add(onMenuVisibilityListener);
    }

    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.x.remove(onMenuVisibilityListener);
    }

    public ActionMode a(ActionMode.Callback callback) {
        if (this.f8221a != null) {
            this.f8221a.finish();
        }
        ActionMode c2 = c(callback);
        this.r = b(callback);
        if (!(c2 instanceof ActionModeImpl)) {
            return null;
        }
        ActionModeImpl actionModeImpl = (ActionModeImpl) c2;
        actionModeImpl.a(this.r);
        actionModeImpl.a(this.G);
        if (!actionModeImpl.a()) {
            return null;
        }
        c2.invalidate();
        this.r.initForMode(c2);
        b(true);
        if (!(this.l == null || this.y != 1 || this.l.getVisibility() == 0)) {
            this.l.setVisibility(0);
        }
        if (this.r instanceof ActionBarContextView) {
            ((ActionBarContextView) this.r).sendAccessibilityEvent(32);
        }
        this.f8221a = c2;
        return c2;
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z2) {
        if (z2) {
            d();
        } else {
            e();
        }
        this.r.animateToVisibility(z2);
        if (this.q != null && !this.j.hasEmbeddedTabs() && this.j.isCollapsed()) {
            this.q.setVisibility(z2 ? 8 : 0);
        }
    }

    private ActionMode c(ActionMode.Callback callback) {
        return new EditActionModeImpl(this.f, callback);
    }

    public ActionModeView b(ActionMode.Callback callback) {
        return this.k;
    }

    public void a(boolean z2, boolean z3) {
        if (!this.j.isSplitActionBar()) {
            return;
        }
        if (z2) {
            this.l.show(z3);
        } else {
            this.l.hide(z3);
        }
    }

    public boolean f() {
        return this.p != null;
    }

    public void a(Context context, FragmentManager fragmentManager) {
        a(context, fragmentManager, true);
    }

    public void a(Context context, FragmentManager fragmentManager, boolean z2) {
        if (!f()) {
            removeAllTabs();
            setNavigationMode(2);
            this.p = new ActionBarViewPagerController(this, fragmentManager, z2);
            this.q.setFragmentViewPagerMode(f());
            a((ActionBar.FragmentViewPagerChangeListener) this.q);
            a((ActionBar.FragmentViewPagerChangeListener) this.l);
            this.l.setFragmentViewPagerMode(true);
        }
    }

    public void a(ActionBar.FragmentViewPagerChangeListener fragmentViewPagerChangeListener) {
        this.p.a(fragmentViewPagerChangeListener);
    }

    public void b(ActionBar.FragmentViewPagerChangeListener fragmentViewPagerChangeListener) {
        this.p.b(fragmentViewPagerChangeListener);
    }

    public int g() {
        return this.p.b();
    }

    public Fragment b(int i2) {
        return this.p.b(i2);
    }

    public int a(String str, ActionBar.Tab tab, Class<? extends Fragment> cls, Bundle bundle, boolean z2) {
        return this.p.a(str, tab, cls, bundle, z2);
    }

    public int a(String str, ActionBar.Tab tab, int i2, Class<? extends Fragment> cls, Bundle bundle, boolean z2) {
        return this.p.a(str, tab, i2, cls, bundle, z2);
    }

    public void c(int i2) {
        this.p.a(i2);
    }

    public void a(String str) {
        this.p.a(str);
    }

    public void c(ActionBar.Tab tab) {
        this.p.a(tab);
    }

    public void a(Fragment fragment) {
        this.p.a(fragment);
    }

    public void h() {
        this.p.a();
    }

    public void a(int i2, boolean z2) {
        this.p.a(i2, z2);
    }

    public void d(boolean z2) {
        this.j.setProgressBarVisibility(z2);
    }

    public void e(boolean z2) {
        this.j.setProgressBarIndeterminateVisibility(z2);
    }

    public void f(boolean z2) {
        this.j.setProgressBarIndeterminate(z2);
    }

    public void d(int i2) {
        this.j.setProgress(i2);
    }

    public int i() {
        return this.p.c();
    }

    public void e(int i2) {
        this.p.c(i2);
    }

    private void l() {
        if (this.q == null) {
            ScrollingTabContainerView scrollingTabContainerView = new ScrollingTabContainerView(this.f);
            if (this.z) {
                scrollingTabContainerView.setVisibility(0);
                this.j.setEmbeddedTabView(scrollingTabContainerView);
            } else {
                if (getNavigationMode() == 2) {
                    scrollingTabContainerView.setVisibility(0);
                } else {
                    scrollingTabContainerView.setVisibility(8);
                }
                this.i.setTabContainer(scrollingTabContainerView);
            }
            this.q = scrollingTabContainerView;
        }
    }

    private void b(ActionBar.Tab tab, int i2) {
        TabImpl tabImpl = (TabImpl) tab;
        if (tabImpl.a() != null) {
            tabImpl.a(i2);
            this.s.add(i2, tabImpl);
            int size = this.s.size();
            while (true) {
                i2++;
                if (i2 < size) {
                    this.s.get(i2).a(i2);
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        }
    }

    private void m() {
        if (this.t != null) {
            selectTab((ActionBar.Tab) null);
        }
        this.s.clear();
        if (this.q != null) {
            this.q.removeAllTabs();
        }
        this.v = -1;
    }

    private void k(boolean z2) {
        if (a(this.B, this.C, this.D)) {
            if (!this.E) {
                this.E = true;
                g(z2);
            }
        } else if (this.E) {
            this.E = false;
            h(z2);
        }
    }

    public void g(boolean z2) {
        this.i.clearAnimation();
        if (this.i.getVisibility() != 0) {
            boolean z3 = j() || z2;
            if (z3) {
                this.i.startAnimation(AnimationUtils.loadAnimation(this.f, R.anim.v6_action_bar_slide_in_top));
            }
            this.i.setVisibility(0);
            if (this.l != null && this.l.getVisibility() != 0) {
                if (z3) {
                    this.l.startAnimation(AnimationUtils.loadAnimation(this.f, R.anim.v6_action_bar_slide_in_bottom));
                }
                this.l.setVisibility(0);
                l(true);
            }
        }
    }

    public void h(boolean z2) {
        this.i.clearAnimation();
        if (this.i.getVisibility() != 8) {
            boolean z3 = j() || z2;
            if (z3) {
                this.i.startAnimation(AnimationUtils.loadAnimation(this.f, R.anim.v6_action_bar_slide_out_top));
            }
            this.i.setVisibility(8);
            if (this.l != null && this.l.getVisibility() != 8) {
                if (z3) {
                    this.l.startAnimation(AnimationUtils.loadAnimation(this.f, R.anim.v6_action_bar_slide_out_bottom));
                }
                this.l.setVisibility(8);
                l(false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean j() {
        return this.F;
    }

    public void i(boolean z2) {
        this.F = z2;
        if (!z2) {
            this.i.clearAnimation();
            if (this.l != null) {
                this.l.clearAnimation();
            }
        }
    }

    private void l(boolean z2) {
        if (this.l.getChildCount() == 1 && (this.l.getChildAt(0) instanceof PhoneActionMenuView)) {
            this.m = (PhoneActionMenuView) this.l.getChildAt(0);
            if (this.m.isOverflowMenuShowing() && this.n != null) {
                if (z2) {
                    this.h.getContentMaskAnimator(this.o).a().start();
                } else {
                    this.h.getContentMaskAnimator((View.OnClickListener) null).b().start();
                }
            }
        }
    }

    public class TabImpl extends ActionBar.Tab {
        /* access modifiers changed from: private */
        public ActionBar.TabListener b;
        /* access modifiers changed from: private */
        public ActionBar.TabListener c;
        private Object d;
        private Drawable e;
        private CharSequence f;
        private CharSequence g;
        private int h = -1;
        private View i;

        public TabImpl() {
        }

        public Object getTag() {
            return this.d;
        }

        public ActionBar.Tab setTag(Object obj) {
            this.d = obj;
            return this;
        }

        public ActionBar.TabListener a() {
            return ActionBarImpl.e;
        }

        public ActionBar.Tab setTabListener(ActionBar.TabListener tabListener) {
            this.b = tabListener;
            return this;
        }

        public ActionBar.Tab a(ActionBar.TabListener tabListener) {
            this.c = tabListener;
            return this;
        }

        public View getCustomView() {
            return this.i;
        }

        public ActionBar.Tab setCustomView(View view) {
            this.i = view;
            if (this.h >= 0) {
                ActionBarImpl.this.q.updateTab(this.h);
            }
            return this;
        }

        public ActionBar.Tab setCustomView(int i2) {
            return setCustomView(LayoutInflater.from(ActionBarImpl.this.getThemedContext()).inflate(i2, (ViewGroup) null));
        }

        public Drawable getIcon() {
            return this.e;
        }

        public int getPosition() {
            return this.h;
        }

        public void a(int i2) {
            this.h = i2;
        }

        public CharSequence getText() {
            return this.f;
        }

        public ActionBar.Tab setIcon(Drawable drawable) {
            this.e = drawable;
            if (this.h >= 0) {
                ActionBarImpl.this.q.updateTab(this.h);
            }
            return this;
        }

        public ActionBar.Tab setIcon(int i2) {
            return setIcon(ActionBarImpl.this.f.getResources().getDrawable(i2));
        }

        public ActionBar.Tab setText(CharSequence charSequence) {
            this.f = charSequence;
            if (this.h >= 0) {
                ActionBarImpl.this.q.updateTab(this.h);
            }
            return this;
        }

        public ActionBar.Tab setText(int i2) {
            return setText(ActionBarImpl.this.f.getResources().getText(i2));
        }

        public void select() {
            ActionBarImpl.this.selectTab(this);
        }

        public ActionBar.Tab setContentDescription(int i2) {
            return setContentDescription(ActionBarImpl.this.f.getResources().getText(i2));
        }

        public ActionBar.Tab setContentDescription(CharSequence charSequence) {
            this.g = charSequence;
            if (this.h >= 0) {
                ActionBarImpl.this.q.updateTab(this.h);
            }
            return this;
        }

        public CharSequence getContentDescription() {
            return this.g;
        }
    }
}
