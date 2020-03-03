package com.miuipub.internal.app;

import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.miuipub.internal.view.SimpleWindowCallback;
import com.miuipub.internal.view.menu.MenuBuilder;
import com.miuipub.internal.widget.ActionBarContainer;
import com.miuipub.internal.widget.ActionBarContextView;
import com.miuipub.internal.widget.ActionBarOverlayLayout;
import com.miuipub.internal.widget.ActionBarView;
import miuipub.app.ActionBar;
import miuipub.app.OnStatusBarChangeListener;
import miuipub.util.AttributeResolver;
import miuipub.v6.R;

public class FragmentDelegate extends ActionBarDelegateImpl {
    public static final int m = 1;
    private static final int n = 16;
    /* access modifiers changed from: private */
    public Fragment o;
    private View p;
    private int q;
    private Context r;
    /* access modifiers changed from: private */
    public MenuBuilder s;
    /* access modifiers changed from: private */
    public byte t;
    private final Runnable u = new Runnable() {
        public void run() {
            boolean z = true;
            if ((FragmentDelegate.this.t & 1) == 1) {
                MenuBuilder unused = FragmentDelegate.this.s = null;
            }
            if (FragmentDelegate.this.s == null) {
                MenuBuilder unused2 = FragmentDelegate.this.s = FragmentDelegate.this.j();
                z = FragmentDelegate.this.a(0, (Menu) FragmentDelegate.this.s);
            }
            if (z) {
                z = FragmentDelegate.this.a(0, (View) null, (Menu) FragmentDelegate.this.s);
            }
            if (z) {
                FragmentDelegate.this.a(FragmentDelegate.this.s);
            } else {
                FragmentDelegate.this.a((MenuBuilder) null);
                MenuBuilder unused3 = FragmentDelegate.this.s = null;
            }
            byte unused4 = FragmentDelegate.this.t = (byte) (FragmentDelegate.this.t & -18);
        }
    };
    private final Window.Callback v = new SimpleWindowCallback() {
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return FragmentDelegate.this.b(callback);
        }

        public void onActionModeStarted(ActionMode actionMode) {
            ((IFragment) FragmentDelegate.this.o).a(actionMode);
        }

        public void onActionModeFinished(ActionMode actionMode) {
            ((IFragment) FragmentDelegate.this.o).b(actionMode);
        }

        public boolean onMenuItemSelected(int i, MenuItem menuItem) {
            return FragmentDelegate.this.a(i, menuItem);
        }
    };

    public View b(int i) {
        return null;
    }

    public FragmentDelegate(Fragment fragment) {
        super(fragment.getActivity());
        this.o = fragment;
    }

    public ActionBar a() {
        return new ActionBarImpl(this.o);
    }

    public View a(ViewGroup viewGroup, Bundle bundle) {
        TypedArray obtainStyledAttributes = o().obtainStyledAttributes(R.styleable.V6_Window);
        if (obtainStyledAttributes.hasValue(R.styleable.V6_Window_v6_windowActionBar)) {
            if (obtainStyledAttributes.getBoolean(R.styleable.V6_Window_v6_windowActionBar, false)) {
                a(8);
            }
            if (obtainStyledAttributes.getBoolean(R.styleable.V6_Window_v6_windowActionBarOverlay, false)) {
                a(9);
            }
            c(obtainStyledAttributes.getInt(R.styleable.V6_Window_v6_windowTranslucentStatus, 0));
            a(obtainStyledAttributes.getBoolean(R.styleable.V6_Window_v6_immersionMenuEnabled, false));
            this.l = obtainStyledAttributes.getResourceId(R.styleable.V6_Window_v6_immersionMenuLayout, 0);
            obtainStyledAttributes.recycle();
            LayoutInflater from = LayoutInflater.from(o());
            if (this.j) {
                a(o(), viewGroup, from);
                ViewGroup viewGroup2 = (ViewGroup) this.p.findViewById(16908290);
                View b = ((IFragment) this.o).b(from, viewGroup2, bundle);
                if (!(b == null || b.getParent() == viewGroup2)) {
                    if (b.getParent() != null) {
                        ((ViewGroup) b.getParent()).removeView(b);
                    }
                    viewGroup2.removeAllViews();
                    viewGroup2.addView(b);
                }
            } else {
                this.p = ((IFragment) this.o).b(from, viewGroup, bundle);
            }
            return this.p;
        }
        obtainStyledAttributes.recycle();
        throw new IllegalStateException("You need to use a miui theme (or descendant) with this fragment.");
    }

    /* access modifiers changed from: package-private */
    public final void a(Context context, ViewGroup viewGroup, LayoutInflater layoutInflater) {
        boolean z;
        if (!this.g) {
            this.g = true;
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) layoutInflater.inflate(R.layout.v6_screen_action_bar, viewGroup, false);
            actionBarOverlayLayout.setCallback(this.v);
            actionBarOverlayLayout.setOverlayMode(this.k);
            actionBarOverlayLayout.setTranslucentStatus(k());
            if (this.q != 0) {
                actionBarOverlayLayout.setBackgroundDrawable(AttributeResolver.b(context, 16842836));
            }
            this.d = (ActionBarView) actionBarOverlayLayout.findViewById(R.id.action_bar);
            this.d.setWindowCallback(this.v);
            if (this.h) {
                this.d.initProgress();
            }
            if (this.i) {
                this.d.initIndeterminateProgress();
            }
            if (l()) {
                this.d.initImmersionMore(this.l, this);
            }
            boolean equals = "splitActionBarWhenNarrow".equals(g());
            if (equals) {
                z = context.getResources().getBoolean(R.bool.v6_abc_split_action_bar_is_narrow);
            } else {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(R.styleable.V6_Window);
                boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.V6_Window_v6_windowSplitActionBar, false);
                obtainStyledAttributes.recycle();
                z = z2;
            }
            ActionBarContainer actionBarContainer = (ActionBarContainer) actionBarOverlayLayout.findViewById(R.id.split_action_bar);
            if (actionBarContainer != null) {
                this.d.setSplitView(actionBarContainer);
                this.d.setSplitActionBar(z);
                this.d.setSplitWhenNarrow(equals);
                ActionBarContextView actionBarContextView = (ActionBarContextView) actionBarOverlayLayout.findViewById(R.id.action_context_bar);
                actionBarContainer.setActionBarContextView(actionBarContextView);
                actionBarContextView.setSplitView(actionBarContainer);
                actionBarContextView.setSplitActionBar(z);
                actionBarContextView.setSplitWhenNarrow(equals);
            }
            d(1);
            d();
            this.p = actionBarOverlayLayout;
        } else if (this.p.getParent() != null && (this.p.getParent() instanceof ViewGroup)) {
            ViewGroup viewGroup2 = (ViewGroup) this.p.getParent();
            if (viewGroup2.getChildCount() == 0) {
                viewGroup2.endViewTransition(this.p);
            }
        }
    }

    public View n() {
        return this.p;
    }

    public boolean a(int i, Menu menu) {
        if (i == 0) {
            return ((IFragment) this.o).a(i, menu);
        }
        return false;
    }

    public boolean a(int i, MenuItem menuItem) {
        if (i == 0) {
            return this.o.onOptionsItemSelected(menuItem);
        }
        return false;
    }

    public boolean a(int i, View view, Menu menu) {
        if (i != 0) {
            return false;
        }
        ((IFragment) this.o).a(i, (View) null, menu);
        return true;
    }

    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return a(0, menuItem);
    }

    public void d() {
        if ((this.t & 16) == 0) {
            this.t = (byte) (this.t | 16);
            this.o.getActivity().getWindow().getDecorView().post(this.u);
        }
    }

    public void d(int i) {
        this.t = (byte) ((i & 1) | this.t);
    }

    public ActionMode a(ActionMode.Callback callback) {
        return this.p.startActionMode(callback);
    }

    public ActionMode b(ActionMode.Callback callback) {
        if (e() != null) {
            return ((ActionBarImpl) e()).a(callback);
        }
        return null;
    }

    public void e(int i) {
        this.q = i;
    }

    public Context o() {
        if (this.r == null) {
            this.r = this.c;
            if (this.q != 0) {
                this.r = new ContextThemeWrapper(this.r, this.q);
            }
        }
        return this.r;
    }

    /* access modifiers changed from: protected */
    public boolean d(MenuBuilder menuBuilder) {
        if (!(this.o instanceof IFragment) || !((IFragment) this.o).a((Menu) menuBuilder)) {
            return false;
        }
        this.o.onPrepareOptionsMenu(menuBuilder);
        return true;
    }

    public void a(OnStatusBarChangeListener onStatusBarChangeListener) {
        if (this.p != null && (this.p instanceof ActionBarOverlayLayout)) {
            ((ActionBarOverlayLayout) this.p).setOnStatusBarChangeListener(onStatusBarChangeListener);
        }
    }
}
