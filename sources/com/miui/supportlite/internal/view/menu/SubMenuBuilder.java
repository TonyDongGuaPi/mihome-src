package com.miui.supportlite.internal.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import com.miui.supportlite.internal.view.menu.MenuBuilder;

public class SubMenuBuilder extends MenuBuilder implements SubMenu {
    private MenuBuilder d;
    private MenuItemImpl e;

    public void clearHeader() {
    }

    public SubMenuBuilder(Context context, MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        super(context);
        this.d = menuBuilder;
        this.e = menuItemImpl;
    }

    public void setQwertyMode(boolean z) {
        this.d.setQwertyMode(z);
    }

    public MenuItem getItem() {
        return this.e;
    }

    public void a(MenuBuilder.Callback callback) {
        this.d.a(callback);
    }

    public MenuBuilder g() {
        return this.d;
    }

    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return super.a(menuBuilder, menuItem) || this.d.a(menuBuilder, menuItem);
    }

    public SubMenu setIcon(Drawable drawable) {
        this.e.setIcon(drawable);
        return this;
    }

    public SubMenu setIcon(int i) {
        this.e.setIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        super.a(drawable);
        return this;
    }

    public SubMenu setHeaderIcon(int i) {
        super.a(b().getResources().getDrawable(i));
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        super.a(charSequence);
        return this;
    }

    public SubMenu setHeaderTitle(int i) {
        super.a((CharSequence) b().getResources().getString(i));
        return this;
    }

    public SubMenu setHeaderView(View view) {
        super.a(view);
        return this;
    }
}
