package com.miuipub.internal.view.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class ActionMenuItem implements MenuItem {
    private static final int n = 1;
    private static final int o = 2;
    private static final int p = 4;
    private static final int q = 8;
    private static final int r = 16;

    /* renamed from: a  reason: collision with root package name */
    private final int f8288a;
    private final int b;
    private final int c;
    private final int d;
    private CharSequence e;
    private CharSequence f;
    private Intent g;
    private char h;
    private char i;
    private Drawable j;
    private Context k;
    private MenuItem.OnMenuItemClickListener l;
    private int m = 16;

    public MenuItem a(MenuItem.OnActionExpandListener onActionExpandListener) {
        return this;
    }

    public ActionProvider b() {
        return null;
    }

    public boolean collapseActionView() {
        return false;
    }

    public boolean expandActionView() {
        return false;
    }

    public View getActionView() {
        return null;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isActionViewExpanded() {
        return false;
    }

    public void setShowAsAction(int i2) {
    }

    public ActionMenuItem(Context context, int i2, int i3, int i4, int i5, CharSequence charSequence) {
        this.k = context;
        this.f8288a = i3;
        this.b = i2;
        this.c = i4;
        this.d = i5;
        this.e = charSequence;
    }

    public char getAlphabeticShortcut() {
        return this.i;
    }

    public int getGroupId() {
        return this.b;
    }

    public Drawable getIcon() {
        return this.j;
    }

    public Intent getIntent() {
        return this.g;
    }

    public int getItemId() {
        return this.f8288a;
    }

    public char getNumericShortcut() {
        return this.h;
    }

    public int getOrder() {
        return this.d;
    }

    public CharSequence getTitle() {
        return this.e;
    }

    public CharSequence getTitleCondensed() {
        return this.f;
    }

    public boolean isCheckable() {
        return (this.m & 1) != 0;
    }

    public boolean isChecked() {
        return (this.m & 2) != 0;
    }

    public boolean isEnabled() {
        return (this.m & 16) != 0;
    }

    public boolean isVisible() {
        return (this.m & 8) == 0;
    }

    public MenuItem setAlphabeticShortcut(char c2) {
        this.i = c2;
        return this;
    }

    public MenuItem setCheckable(boolean z) {
        this.m = z | (this.m & true) ? 1 : 0;
        return this;
    }

    public ActionMenuItem a(boolean z) {
        this.m = (z ? 4 : 0) | (this.m & -5);
        return this;
    }

    public MenuItem setChecked(boolean z) {
        this.m = (z ? 2 : 0) | (this.m & -3);
        return this;
    }

    public MenuItem setEnabled(boolean z) {
        this.m = (z ? 16 : 0) | (this.m & -17);
        return this;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.j = drawable;
        return this;
    }

    public MenuItem setIcon(int i2) {
        this.j = this.k.getResources().getDrawable(i2);
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.g = intent;
        return this;
    }

    public MenuItem setNumericShortcut(char c2) {
        this.h = c2;
        return this;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.l = onMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char c2, char c3) {
        this.h = c2;
        this.i = c3;
        return this;
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.e = charSequence;
        return this;
    }

    public MenuItem setTitle(int i2) {
        this.e = this.k.getResources().getString(i2);
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f = charSequence;
        return this;
    }

    public MenuItem setVisible(boolean z) {
        int i2 = 8;
        int i3 = this.m & 8;
        if (z) {
            i2 = 0;
        }
        this.m = i3 | i2;
        return this;
    }

    public boolean a() {
        if (this.l != null && this.l.onMenuItemClick(this)) {
            return true;
        }
        if (this.g == null) {
            return false;
        }
        this.k.startActivity(this.g);
        return true;
    }

    public MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    public MenuItem setActionView(int i2) {
        throw new UnsupportedOperationException();
    }

    public MenuItem a(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }
}
