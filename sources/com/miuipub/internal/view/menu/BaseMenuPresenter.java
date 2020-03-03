package com.miuipub.internal.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miuipub.internal.view.menu.MenuPresenter;
import com.miuipub.internal.view.menu.MenuView;
import java.util.Iterator;

public abstract class BaseMenuPresenter implements MenuPresenter {

    /* renamed from: a  reason: collision with root package name */
    private MenuPresenter.Callback f8299a;
    private int b;
    protected Context c;
    protected Context d;
    protected MenuBuilder e;
    protected LayoutInflater f;
    protected LayoutInflater g;
    protected MenuView h;
    private int i;
    private int j;

    public abstract void a(MenuItemImpl menuItemImpl, MenuView.ItemView itemView);

    public boolean a(int i2, MenuItemImpl menuItemImpl) {
        return true;
    }

    public boolean a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean e() {
        return false;
    }

    public BaseMenuPresenter(Context context, int i2, int i3) {
        this.c = context;
        this.f = LayoutInflater.from(context);
        this.b = i2;
        this.i = i3;
    }

    public void a(Context context, MenuBuilder menuBuilder) {
        this.d = context;
        this.g = LayoutInflater.from(this.d);
        this.e = menuBuilder;
    }

    public MenuView a(ViewGroup viewGroup) {
        if (this.h == null) {
            this.h = (MenuView) this.f.inflate(this.b, viewGroup, false);
            this.h.initialize(this.e);
            d(true);
        }
        return this.h;
    }

    public void d(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.h;
        if (viewGroup != null) {
            int i2 = 0;
            if (this.e != null) {
                this.e.k();
                Iterator<MenuItemImpl> it = this.e.j().iterator();
                int i3 = 0;
                while (it.hasNext()) {
                    MenuItemImpl next = it.next();
                    if (a(i3, next)) {
                        View childAt = viewGroup.getChildAt(i3);
                        MenuItemImpl itemData = childAt instanceof MenuView.ItemView ? ((MenuView.ItemView) childAt).getItemData() : null;
                        View a2 = a(next, childAt, viewGroup);
                        if (next != itemData) {
                            a2.setPressed(false);
                        }
                        if (a2 != childAt) {
                            a(a2, i3);
                        }
                        i3++;
                    }
                }
                i2 = i3;
            }
            while (i2 < viewGroup.getChildCount()) {
                if (!this.h.filterLeftoverView(i2)) {
                    i2++;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(View view, int i2) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.h).addView(view, i2);
    }

    public void a(MenuPresenter.Callback callback) {
        this.f8299a = callback;
    }

    public MenuView.ItemView b(ViewGroup viewGroup) {
        return (MenuView.ItemView) this.f.inflate(this.i, viewGroup, false);
    }

    public View a(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        MenuView.ItemView itemView;
        if (view instanceof MenuView.ItemView) {
            itemView = (MenuView.ItemView) view;
        } else {
            itemView = b(viewGroup);
        }
        a(menuItemImpl, itemView);
        return (View) itemView;
    }

    public void a(MenuBuilder menuBuilder, boolean z) {
        if (this.f8299a != null) {
            this.f8299a.b(menuBuilder, z);
        }
    }

    public boolean a(SubMenuBuilder subMenuBuilder) {
        return this.f8299a != null && this.f8299a.b(subMenuBuilder);
    }

    public int g() {
        return this.j;
    }

    public void b(int i2) {
        this.j = i2;
    }
}
