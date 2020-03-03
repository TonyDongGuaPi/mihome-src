package com.miui.supportlite.internal.view.menu;

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

public final class MenuItemImpl implements MenuItem {

    /* renamed from: a  reason: collision with root package name */
    static final int f8207a = 0;
    private static final String b = "MenuItemImpl";
    private static final int c = 3;
    private static final int s = 1;
    private static final int t = 2;
    private static final int u = 4;
    private static final int v = 8;
    private static final int w = 16;
    private static final int x = 32;
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
    private MenuBuilder o;
    private SubMenuBuilder p;
    private MenuItem.OnMenuItemClickListener q;
    private int r = 16;
    private int y = 0;
    private View z;

    public boolean collapseActionView() {
        return false;
    }

    public boolean expandActionView() {
        return false;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    public boolean isActionViewExpanded() {
        return false;
    }

    public MenuItem setVisible(boolean z2) {
        return this;
    }

    MenuItemImpl(MenuBuilder menuBuilder, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        this.o = menuBuilder;
        this.d = i3;
        this.e = i2;
        this.f = i4;
        this.g = i5;
        this.h = charSequence;
        this.y = i6;
    }

    public boolean a() {
        if ((this.q != null && this.q.onMenuItemClick(this)) || this.o.a(this.o.g(), (MenuItem) this)) {
            return true;
        }
        if (this.j == null) {
            return false;
        }
        try {
            this.o.b().startActivity(this.j);
            return true;
        } catch (ActivityNotFoundException e2) {
            Log.e(b, "Can't find activity to handle intent; ignoring", e2);
            return false;
        }
    }

    public boolean isEnabled() {
        return (this.r & 16) != 0;
    }

    public MenuItem setEnabled(boolean z2) {
        if (z2) {
            this.r |= 16;
        } else {
            this.r &= -17;
        }
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

    public char getAlphabeticShortcut() {
        return this.l;
    }

    public MenuItem setAlphabeticShortcut(char c2) {
        if (this.l == c2) {
            return this;
        }
        this.l = Character.toLowerCase(c2);
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
        return this;
    }

    public MenuItem setShortcut(char c2, char c3) {
        this.k = c2;
        this.l = Character.toLowerCase(c3);
        return this;
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

    public MenuItem setTitle(CharSequence charSequence) {
        this.h = charSequence;
        if (this.p != null) {
            this.p.setHeaderTitle(charSequence);
        }
        return this;
    }

    public MenuItem setTitle(int i2) {
        return setTitle((CharSequence) this.o.b().getString(i2));
    }

    public CharSequence getTitleCondensed() {
        return this.i != null ? this.i : this.h;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.i = charSequence;
        return this;
    }

    public Drawable getIcon() {
        if (this.m != null) {
            return this.m;
        }
        if (this.n == 0) {
            return null;
        }
        Drawable drawable = this.o.a().getDrawable(this.n);
        this.n = 0;
        this.m = drawable;
        return drawable;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.n = 0;
        this.m = drawable;
        return this;
    }

    public MenuItem setIcon(int i2) {
        this.m = null;
        this.n = i2;
        return this;
    }

    public boolean isCheckable() {
        return (this.r & 1) == 1;
    }

    public MenuItem setCheckable(boolean z2) {
        this.r = z2 | (this.r & true) ? 1 : 0;
        return this;
    }

    public void a(boolean z2) {
        this.r = (z2 ? 4 : 0) | (this.r & -5);
    }

    public boolean c() {
        return (this.r & 4) != 0;
    }

    public boolean isChecked() {
        return (this.r & 2) == 2;
    }

    public MenuItem setChecked(boolean z2) {
        if ((this.r & 4) != 0) {
            this.o.a((MenuItem) this);
        } else {
            b(z2);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z2) {
        this.r = (z2 ? 2 : 0) | (this.r & -3);
    }

    public boolean isVisible() {
        return (this.r & 8) == 0;
    }

    /* access modifiers changed from: package-private */
    public boolean c(boolean z2) {
        int i2 = this.r;
        this.r = (z2 ? 0 : 8) | (this.r & -9);
        if (i2 != this.r) {
            return true;
        }
        return false;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.q = onMenuItemClickListener;
        return this;
    }

    public String toString() {
        return this.h.toString();
    }

    public void setShowAsAction(int i2) {
        switch (i2 & 3) {
            case 0:
            case 1:
            case 2:
                this.y = i2;
                return;
            default:
                throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
    }

    public MenuItem setActionView(View view) {
        this.z = view;
        if (view != null && view.getId() == -1 && this.d > 0) {
            view.setId(this.d);
        }
        return this;
    }

    public MenuItem setActionView(int i2) {
        Context b2 = this.o.b();
        setActionView(LayoutInflater.from(b2).inflate(i2, new LinearLayout(b2), false));
        return this;
    }

    public View getActionView() {
        if (this.z != null) {
            return this.z;
        }
        return null;
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("Implementation should use setSupportActionProvider!");
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("Implementation should use getSupportActionProvider!");
    }

    public MenuItem setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException("Implementation should use setSupportOnActionExpandListener!");
    }
}
