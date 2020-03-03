package com.miuipub.internal.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.miuipub.internal.view.menu.MenuBuilder;
import com.miuipub.internal.view.menu.MenuPresenter;
import com.miuipub.internal.widget.ActionBarContainer;
import com.miuipub.internal.widget.ActionBarContextView;
import com.miuipub.internal.widget.ActionBarOverlayLayout;
import com.miuipub.internal.widget.ActionBarView;
import miuipub.app.ActionBar;
import miuipub.app.OnStatusBarChangeListener;
import miuipub.reflect.Method;
import miuipub.util.AttributeResolver;
import miuipub.v6.R;

public class ActivityDelegate extends ActionBarDelegateImpl implements MenuBuilder.Callback, MenuPresenter.Callback {
    private static final String m = "miui:ActionBar";
    private static final Method n = Method.a(Activity.class, "onCreatePanelMenu", Boolean.TYPE, Integer.TYPE, Menu.class);
    private static final Method o = Method.a(Activity.class, "onPreparePanel", Boolean.TYPE, Integer.TYPE, View.class, Menu.class);
    private static final Method p = Method.a(Activity.class, "onMenuItemSelected", Boolean.TYPE, Integer.TYPE, MenuItem.class);
    private final Class<? extends Activity> q;
    private ActionBarOverlayLayout r;
    private ActionBarContainer s;
    private final Runnable t = new Runnable() {
        public void run() {
            MenuBuilder j = ActivityDelegate.this.j();
            if (ActivityDelegate.this.l() || !ActivityDelegate.this.b(0, (Menu) j) || !ActivityDelegate.this.b(0, (View) null, j)) {
                ActivityDelegate.this.a((MenuBuilder) null);
            } else {
                ActivityDelegate.this.a(j);
            }
        }
    };

    public ActivityDelegate(Activity activity, Class<? extends Activity> cls) {
        super(activity);
        this.q = cls;
    }

    public static int a(Window window) {
        int i;
        Context context = window.getContext();
        if (!AttributeResolver.a(context, R.attr.v6_windowActionBar, false)) {
            i = R.layout.v6_screen_simple;
        } else if (AttributeResolver.a(context, R.attr.v6_windowActionBarMovable, false)) {
            i = R.layout.v6_screen_action_bar_movable;
        } else {
            i = R.layout.v6_screen_action_bar;
        }
        if (!window.isFloating()) {
            boolean z = window.getCallback() instanceof Dialog;
        }
        return i;
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        n();
    }

    public ActionBar a() {
        return new ActionBarImpl(this.c);
    }

    public void b() {
        b(false);
        ActionBarImpl actionBarImpl = (ActionBarImpl) e();
        if (actionBarImpl != null) {
            actionBarImpl.i(false);
        }
    }

    public void c() {
        ActionBarImpl actionBarImpl = (ActionBarImpl) e();
        if (actionBarImpl != null) {
            actionBarImpl.i(true);
        }
    }

    /* access modifiers changed from: protected */
    public void n() {
        boolean z;
        if (!this.g) {
            this.g = true;
            TypedArray obtainStyledAttributes = this.c.obtainStyledAttributes(R.styleable.V6_Window);
            if (obtainStyledAttributes.getInt(R.styleable.V6_Window_v6_windowLayoutMode, 0) == 1) {
                this.c.getWindow().setGravity(80);
            }
            if (obtainStyledAttributes.hasValue(R.styleable.V6_Window_v6_windowActionBar)) {
                if (obtainStyledAttributes.getBoolean(R.styleable.V6_Window_v6_windowActionBar, false)) {
                    a(8);
                }
                if (obtainStyledAttributes.getBoolean(R.styleable.V6_Window_v6_windowActionBarOverlay, false)) {
                    a(9);
                }
                c(obtainStyledAttributes.getInt(R.styleable.V6_Window_v6_windowTranslucentStatus, 0));
                ViewGroup viewGroup = (ViewGroup) this.c.getWindow().getDecorView();
                View findViewById = viewGroup.findViewById(16908290);
                ((ViewGroup) findViewById.getParent()).removeView(findViewById);
                viewGroup.removeAllViews();
                View.inflate(this.c, a(this.c.getWindow()), viewGroup);
                ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(16908290);
                ViewGroup viewGroup3 = (ViewGroup) viewGroup2.getParent();
                int indexOfChild = viewGroup3.indexOfChild(viewGroup2);
                viewGroup3.removeView(viewGroup2);
                findViewById.setLayoutParams(viewGroup2.getLayoutParams());
                viewGroup3.addView(findViewById, indexOfChild);
                ((ActionBarOverlayLayout) viewGroup.getChildAt(0)).onFinishAdjustHierarchy();
                this.r = (ActionBarOverlayLayout) viewGroup.getChildAt(0);
                this.r.setCallback(this.c);
                this.r.setRootSubDecor(true);
                this.r.setTranslucentStatus(k());
                if (this.j) {
                    this.s = (ActionBarContainer) this.r.findViewById(R.id.action_bar_container);
                    this.r.setOverlayMode(this.k);
                    this.d = (ActionBarView) this.r.findViewById(R.id.action_bar);
                    this.d.setWindowCallback(this.c);
                    if (this.h) {
                        this.d.initProgress();
                    }
                    if (this.i) {
                        this.d.initIndeterminateProgress();
                    }
                    this.l = obtainStyledAttributes.getResourceId(R.styleable.V6_Window_v6_immersionMenuLayout, 0);
                    if (l()) {
                        this.d.initImmersionMore(this.l, this);
                    }
                    boolean equals = "splitActionBarWhenNarrow".equals(g());
                    if (equals) {
                        z = this.c.getResources().getBoolean(R.bool.v6_abc_split_action_bar_is_narrow);
                    } else {
                        z = obtainStyledAttributes.getBoolean(R.styleable.V6_Window_v6_windowSplitActionBar, false);
                    }
                    ActionBarContainer actionBarContainer = (ActionBarContainer) this.r.findViewById(R.id.split_action_bar);
                    if (actionBarContainer != null) {
                        this.d.setSplitView(actionBarContainer);
                        this.d.setSplitActionBar(z);
                        this.d.setSplitWhenNarrow(equals);
                        ActionBarContextView actionBarContextView = (ActionBarContextView) this.r.findViewById(R.id.action_context_bar);
                        actionBarContainer.setActionBarContextView(actionBarContextView);
                        actionBarContextView.setSplitView(actionBarContainer);
                        actionBarContextView.setSplitActionBar(z);
                        actionBarContextView.setSplitWhenNarrow(equals);
                    }
                    this.c.getWindow().getDecorView().post(this.t);
                }
                if (obtainStyledAttributes.getBoolean(R.styleable.V6_Window_v6_immersionMenuEnabled, false)) {
                    a(true);
                }
                obtainStyledAttributes.recycle();
                return;
            }
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a miui theme (or descendant) with this activity.");
        }
    }

    public void a(CharSequence charSequence) {
        if (this.d != null) {
            this.d.setWindowTitle(charSequence);
        }
    }

    public View b(int i) {
        if (i == 0 && !l()) {
            boolean z = true;
            MenuBuilder menuBuilder = this.e;
            if (this.f == null) {
                if (menuBuilder == null) {
                    menuBuilder = j();
                    a(menuBuilder);
                    menuBuilder.h();
                    z = b(0, (Menu) menuBuilder);
                }
                if (z) {
                    menuBuilder.h();
                    z = b(0, (View) null, menuBuilder);
                }
            }
            if (z) {
                menuBuilder.i();
            } else {
                a((MenuBuilder) null);
            }
        }
        return null;
    }

    public boolean a(int i, Menu menu) {
        return i != 0 && b(i, menu);
    }

    public boolean a(int i, View view, Menu menu) {
        return i != 0 && b(i, view, menu);
    }

    public void b(Bundle bundle) {
        if (this.s != null) {
            SparseArray sparseArray = new SparseArray();
            this.s.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray(m, sparseArray);
        }
    }

    public void c(Bundle bundle) {
        SparseArray sparseParcelableArray;
        if (this.s != null && (sparseParcelableArray = bundle.getSparseParcelableArray(m)) != null) {
            this.s.restoreHierarchyState(sparseParcelableArray);
        }
    }

    public boolean a(int i, MenuItem menuItem) {
        boolean z;
        if (b(i, menuItem) || i != 0 || menuItem.getItemId() != 16908332 || e() == null || (e().getDisplayOptions() & 4) == 0) {
            return true;
        }
        if (this.c.getParent() == null) {
            z = this.c.onNavigateUp();
        } else {
            z = this.c.getParent().onNavigateUpFromChild(this.c);
        }
        if (z) {
            return true;
        }
        this.c.finish();
        return true;
    }

    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.c.onMenuItemSelected(0, menuItem);
    }

    public void d() {
        this.t.run();
    }

    public ActionMode a(ActionMode.Callback callback) {
        return this.r.startActionMode(callback);
    }

    public ActionMode b(ActionMode.Callback callback) {
        if (e() != null) {
            return ((ActionBarImpl) e()).a(callback);
        }
        return null;
    }

    public void a(ActionMode actionMode) {
        this.f = actionMode;
    }

    public void b(ActionMode actionMode) {
        this.f = null;
    }

    public boolean o() {
        if (this.f != null) {
            this.f.finish();
            return true;
        } else if (this.d == null || !this.d.hasExpandedActionView()) {
            return false;
        } else {
            this.d.collapseActionView();
            return true;
        }
    }

    /* access modifiers changed from: private */
    public boolean b(int i, Menu menu) {
        return n.b(this.q, i(), Integer.valueOf(i), menu);
    }

    /* access modifiers changed from: private */
    public boolean b(int i, View view, Menu menu) {
        return o.b(this.q, i(), Integer.valueOf(i), view, menu);
    }

    private boolean b(int i, MenuItem menuItem) {
        return p.b(this.q, i(), Integer.valueOf(i), menuItem);
    }

    /* access modifiers changed from: protected */
    public boolean d(MenuBuilder menuBuilder) {
        if (!this.c.onCreateOptionsMenu(menuBuilder)) {
            return false;
        }
        this.c.onPrepareOptionsMenu(menuBuilder);
        return true;
    }

    public void a(OnStatusBarChangeListener onStatusBarChangeListener) {
        if (this.r != null) {
            this.r.setOnStatusBarChangeListener(onStatusBarChangeListener);
        }
    }
}
