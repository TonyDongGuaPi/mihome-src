package com.miuipub.internal.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import com.miuipub.internal.view.menu.MenuBuilder;

public class SubMenuBuilder extends MenuBuilder implements SubMenu {
    private MenuBuilder g;
    private MenuItemImpl h;

    public void clearHeader() {
    }

    public SubMenuBuilder(Context context, MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        super(context);
        this.g = menuBuilder;
        this.h = menuItemImpl;
    }

    public void setQwertyMode(boolean z) {
        this.g.setQwertyMode(z);
    }

    public boolean c() {
        return this.g.c();
    }

    public void a(boolean z) {
        this.g.a(z);
    }

    public boolean d() {
        return this.g.d();
    }

    public Menu t() {
        return this.g;
    }

    public MenuItem getItem() {
        return this.h;
    }

    public void a(MenuBuilder.Callback callback) {
        this.g.a(callback);
    }

    public MenuBuilder q() {
        return this.g;
    }

    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return super.a(menuBuilder, menuItem) || this.g.a(menuBuilder, menuItem);
    }

    public SubMenu setIcon(Drawable drawable) {
        this.h.setIcon(drawable);
        return this;
    }

    public SubMenu setIcon(int i) {
        this.h.setIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        super.a(drawable);
        return this;
    }

    public SubMenu setHeaderIcon(int i) {
        super.a(f().getResources().getDrawable(i));
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        super.a(charSequence);
        return this;
    }

    public SubMenu setHeaderTitle(int i) {
        super.a((CharSequence) f().getResources().getString(i));
        return this;
    }

    public SubMenu setHeaderView(View view) {
        super.a(view);
        return this;
    }

    public boolean c(MenuItemImpl menuItemImpl) {
        return this.g.c(menuItemImpl);
    }

    public boolean d(MenuItemImpl menuItemImpl) {
        return this.g.d(menuItemImpl);
    }

    public String a() {
        int itemId = this.h != null ? this.h.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.a() + ":" + itemId;
    }
}
