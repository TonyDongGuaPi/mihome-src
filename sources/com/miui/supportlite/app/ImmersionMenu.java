package com.miui.supportlite.app;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.miui.supportlite.internal.view.menu.ImmersionMenuPopupWindow;
import com.miui.supportlite.internal.view.menu.MenuBuilder;
import com.miui.supportlite.internal.view.menu.PhoneImmersionMenuPopupWindow;

public class ImmersionMenu implements MenuBuilder.Callback {

    /* renamed from: a  reason: collision with root package name */
    private Context f8199a;
    private ImmersionMenuPopupWindow b;
    private Menu c;
    private ImmersionMenuListener d;

    public interface ImmersionMenuListener {
        void a(Menu menu);

        void a(Menu menu, MenuItem menuItem);

        boolean b(Menu menu);
    }

    public void a(MenuBuilder menuBuilder) {
    }

    public ImmersionMenu(Context context) {
        this.f8199a = context;
    }

    public void a(ImmersionMenuListener immersionMenuListener) {
        this.d = immersionMenuListener;
    }

    private MenuBuilder a(Context context) {
        MenuBuilder menuBuilder = new MenuBuilder(context);
        menuBuilder.a((MenuBuilder.Callback) this);
        return menuBuilder;
    }

    public void a(View view, ViewGroup viewGroup) {
        if (this.c == null) {
            this.c = a(this.f8199a);
            this.d.a(this.c);
        }
        if (this.d.b(this.c) && this.c.hasVisibleItems()) {
            if (this.b == null) {
                this.b = new PhoneImmersionMenuPopupWindow(this.f8199a, this.c, this.d);
            } else {
                this.b.a(this.c);
            }
            if (!this.b.isShowing()) {
                this.b.a(view, viewGroup);
            }
        }
    }

    public void a(boolean z) {
        if (this.b != null) {
            this.b.a(z);
        }
    }

    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        this.d.a(menuBuilder, menuItem);
        return false;
    }
}
