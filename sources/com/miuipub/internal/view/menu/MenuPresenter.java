package com.miuipub.internal.view.menu;

import android.content.Context;
import android.os.Parcelable;
import android.view.ViewGroup;

public interface MenuPresenter {

    public interface Callback {
        void b(MenuBuilder menuBuilder, boolean z);

        boolean b(MenuBuilder menuBuilder);
    }

    MenuView a(ViewGroup viewGroup);

    void a(Context context, MenuBuilder menuBuilder);

    void a(Parcelable parcelable);

    void a(MenuBuilder menuBuilder, boolean z);

    void a(Callback callback);

    boolean a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl);

    boolean a(SubMenuBuilder subMenuBuilder);

    boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl);

    void d(boolean z);

    boolean e();

    Parcelable f();

    int g();
}
