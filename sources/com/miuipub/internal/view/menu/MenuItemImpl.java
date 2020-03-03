package com.miuipub.internal.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug;
import android.widget.LinearLayout;
import com.miuipub.internal.view.menu.MenuView;

public final class MenuItemImpl implements MenuItem {
    private static String F = null;
    private static String G = null;
    private static String H = null;
    private static String I = null;

    /* renamed from: a  reason: collision with root package name */
    static final int f8307a = 0;
    private static final String b = "MenuItemImpl";
    private static final int c = 3;
    private static final int t = 1;
    private static final int u = 2;
    private static final int v = 4;
    private static final int w = 8;
    private static final int x = 16;
    private static final int y = 32;
    private View A;
    private ActionProvider B;
    private MenuItem.OnActionExpandListener C;
    private boolean D = false;
    private ContextMenu.ContextMenuInfo E;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private CharSequence h;
    private CharSequence i;
    private Intent j;
    private char k;
    private char l;
    private Drawable m;
    private int n = 0;
    /* access modifiers changed from: private */
    public MenuBuilder o;
    private SubMenuBuilder p;
    private Runnable q;
    private MenuItem.OnMenuItemClickListener r;
    private int s = 16;
    private int z = 0;

    MenuItemImpl(MenuBuilder menuBuilder, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        this.o = menuBuilder;
        this.d = i3;
        this.e = i2;
        this.f = i4;
        this.g = i5;
        this.h = charSequence;
        this.z = i6;
    }

    public boolean a() {
        if ((this.r != null && this.r.onMenuItemClick(this)) || this.o.a(this.o.q(), (MenuItem) this)) {
            return true;
        }
        if (this.q != null) {
            this.q.run();
            return true;
        }
        if (this.j != null) {
            try {
                this.o.f().startActivity(this.j);
                return true;
            } catch (ActivityNotFoundException e2) {
                Log.e(b, "Can't find activity to handle intent; ignoring", e2);
            }
        }
        if (this.B == null || !this.B.onPerformDefaultAction()) {
            return false;
        }
        return true;
    }

    public boolean isEnabled() {
        return (this.s & 16) != 0;
    }

    public MenuItem setEnabled(boolean z2) {
        if (z2) {
            this.s |= 16;
        } else {
            this.s &= -17;
        }
        this.o.c(false);
        return this;
    }

    public int getGroupId() {
        return this.e;
    }

    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.d;
    }

    public int getOrder() {
        return this.f;
    }

    public int b() {
        return this.g;
    }

    public Intent getIntent() {
        return this.j;
    }

    public MenuItem setIntent(Intent intent) {
        this.j = intent;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Runnable c() {
        return this.q;
    }

    public MenuItem a(Runnable runnable) {
        this.q = runnable;
        return this;
    }

    public char getAlphabeticShortcut() {
        return this.l;
    }

    public MenuItem setAlphabeticShortcut(char c2) {
        if (this.l == c2) {
            return this;
        }
        this.l = Character.toLowerCase(c2);
        this.o.c(false);
        return this;
    }

    public char getNumericShortcut() {
        return this.k;
    }

    public MenuItem setNumericShortcut(char c2) {
        if (this.k == c2) {
            return this;
        }
        this.k = c2;
        this.o.c(false);
        return this;
    }

    public MenuItem setShortcut(char c2, char c3) {
        this.k = c2;
        this.l = Character.toLowerCase(c3);
        this.o.c(false);
        return this;
    }

    /* access modifiers changed from: package-private */
    public char d() {
        return this.l;
    }

    /* access modifiers changed from: package-private */
    public String e() {
        char d2 = d();
        if (d2 == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(F);
        if (d2 == 8) {
            sb.append(H);
        } else if (d2 == 10) {
            sb.append(G);
        } else if (d2 != ' ') {
            sb.append(d2);
        } else {
            sb.append(I);
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return this.o.d() && d() != 0;
    }

    public SubMenu getSubMenu() {
        return this.p;
    }

    public boolean hasSubMenu() {
        return this.p != null;
    }

    /* access modifiers changed from: package-private */
    public void a(SubMenuBuilder subMenuBuilder) {
        this.p = subMenuBuilder;
        subMenuBuilder.setHeaderTitle(getTitle());
    }

    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public CharSequence a(MenuView.ItemView itemView) {
        if (itemView == null || !itemView.prefersCondensedTitle()) {
            return getTitle();
        }
        return getTitleCondensed();
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.h = charSequence;
        this.o.c(false);
        if (this.p != null) {
            this.p.setHeaderTitle(charSequence);
        }
        return this;
    }

    public MenuItem setTitle(int i2) {
        return setTitle((CharSequence) this.o.f().getString(i2));
    }

    public CharSequence getTitleCondensed() {
        return this.i != null ? this.i : this.h;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.i = charSequence;
        this.o.c(false);
        return this;
    }

    public Drawable getIcon() {
        if (this.m != null) {
            return this.m;
        }
        if (this.n == 0) {
            return null;
        }
        Drawable drawable = this.o.e().getDrawable(this.n);
        this.n = 0;
        this.m = drawable;
        return drawable;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.n = 0;
        this.m = drawable;
        this.o.c(false);
        return this;
    }

    public MenuItem setIcon(int i2) {
        this.m = null;
        this.n = i2;
        this.o.c(false);
        return this;
    }

    public boolean isCheckable() {
        return (this.s & 1) == 1;
    }

    public MenuItem setCheckable(boolean z2) {
        int i2 = this.s;
        this.s = z2 | (this.s & true) ? 1 : 0;
        if (i2 != this.s) {
            this.o.c(false);
        }
        return this;
    }

    public void a(boolean z2) {
        this.s = (z2 ? 4 : 0) | (this.s & -5);
    }

    public boolean g() {
        return (this.s & 4) != 0;
    }

    public boolean isChecked() {
        return (this.s & 2) == 2;
    }

    public MenuItem setChecked(boolean z2) {
        if ((this.s & 4) != 0) {
            this.o.a((MenuItem) this);
        } else {
            b(z2);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z2) {
        int i2 = this.s;
        this.s = (z2 ? 2 : 0) | (this.s & -3);
        if (i2 != this.s) {
            this.o.c(false);
        }
    }

    public boolean isVisible() {
        if (this.B == null || !this.B.overridesItemVisibility()) {
            if ((this.s & 8) == 0) {
                return true;
            }
            return false;
        } else if ((this.s & 8) != 0 || !this.B.isVisible()) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean c(boolean z2) {
        int i2 = this.s;
        this.s = (z2 ? 0 : 8) | (this.s & -9);
        if (i2 != this.s) {
            return true;
        }
        return false;
    }

    public MenuItem setVisible(boolean z2) {
        if (c(z2)) {
            this.o.a(this);
        }
        return this;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.r = onMenuItemClickListener;
        return this;
    }

    public String toString() {
        return this.h.toString();
    }

    /* access modifiers changed from: package-private */
    public void a(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.E = contextMenuInfo;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.E;
    }

    public void h() {
        this.o.b(this);
    }

    public boolean i() {
        return this.o.r();
    }

    public boolean j() {
        return (this.s & 32) == 32;
    }

    public boolean k() {
        return (this.z & 1) == 1;
    }

    public boolean l() {
        return (this.z & 2) == 2;
    }

    public void d(boolean z2) {
        if (z2) {
            this.s |= 32;
        } else {
            this.s &= -33;
        }
    }

    public boolean m() {
        return (this.z & 4) == 4;
    }

    public void setShowAsAction(int i2) {
        switch (i2 & 3) {
            case 0:
            case 1:
            case 2:
                this.z = i2;
                this.o.b(this);
                return;
            default:
                throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
    }

    public MenuItem setActionView(View view) {
        this.A = view;
        this.B = null;
        if (view != null && view.getId() == -1 && this.d > 0) {
            view.setId(this.d);
        }
        this.o.b(this);
        return this;
    }

    public MenuItem setActionView(int i2) {
        Context f2 = this.o.f();
        setActionView(LayoutInflater.from(f2).inflate(i2, new LinearLayout(f2), false));
        return this;
    }

    public View getActionView() {
        if (this.A != null) {
            return this.A;
        }
        if (this.B == null) {
            return null;
        }
        this.A = this.B.onCreateActionView(this);
        return this.A;
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("Implementation should use setSupportActionProvider!");
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("Implementation should use getSupportActionProvider!");
    }

    public ActionProvider n() {
        return this.B;
    }

    public MenuItem a(ActionProvider actionProvider) {
        if (this.B == actionProvider) {
            return this;
        }
        this.A = null;
        if (this.B != null) {
            this.B.setVisibilityListener((ActionProvider.VisibilityListener) null);
        }
        this.B = actionProvider;
        this.o.c(true);
        if (actionProvider != null) {
            actionProvider.setVisibilityListener(new ActionProvider.VisibilityListener() {
                public void onActionProviderVisibilityChanged(boolean z) {
                    MenuItemImpl.this.o.a(MenuItemImpl.this);
                }
            });
        }
        return this;
    }

    public MenuItem setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    public boolean expandActionView() {
        return ((this.z & 8) == 0 || this.A == null || (this.C != null && !this.C.onMenuItemActionExpand(this)) || !this.o.c(this)) ? false : true;
    }

    public boolean collapseActionView() {
        return (this.z & 8) != 0 && (this.A == null || ((this.C == null || this.C.onMenuItemActionCollapse(this)) && this.o.d(this)));
    }

    public MenuItem a(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.C = onActionExpandListener;
        return this;
    }

    public boolean o() {
        return ((this.z & 8) == 0 || this.A == null) ? false : true;
    }

    public void e(boolean z2) {
        this.D = z2;
        this.o.c(false);
    }

    public boolean isActionViewExpanded() {
        return this.D;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException("Implementation should use setSupportOnActionExpandListener!");
    }
}
