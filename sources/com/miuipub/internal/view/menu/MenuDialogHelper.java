package com.miuipub.internal.view.menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.miuipub.internal.view.menu.MenuPresenter;
import miuipub.v6.R;

public class MenuDialogHelper implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener, DialogInterface.OnKeyListener, MenuPresenter.Callback {

    /* renamed from: a  reason: collision with root package name */
    private MenuBuilder f8306a;
    ListMenuPresenter b;
    private AlertDialog c;
    private MenuPresenter.Callback d;

    public MenuDialogHelper(MenuBuilder menuBuilder) {
        this.f8306a = menuBuilder;
    }

    public void a(IBinder iBinder) {
        MenuBuilder menuBuilder = this.f8306a;
        AlertDialog.Builder builder = new AlertDialog.Builder(menuBuilder.f());
        this.b = new ListMenuPresenter(R.layout.v6_list_menu_item_layout, R.style.V6_Theme_MenuDialog_Light);
        this.b.a((MenuPresenter.Callback) this);
        this.f8306a.a((MenuPresenter) this.b);
        builder.setAdapter(this.b.a(), this);
        View p = menuBuilder.p();
        if (p != null) {
            builder.setCustomTitle(p);
        } else {
            builder.setIcon(menuBuilder.o()).setTitle(menuBuilder.n());
        }
        builder.setOnKeyListener(this);
        this.c = builder.create();
        this.c.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.c.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.c.show();
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.c.getWindow();
                if (!(window2 == null || (decorView2 = window2.getDecorView()) == null || (keyDispatcherState2 = decorView2.getKeyDispatcherState()) == null)) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.c.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f8306a.b(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f8306a.performShortcut(i, keyEvent, 0);
    }

    public void a(MenuPresenter.Callback callback) {
        this.d = callback;
    }

    public void a() {
        if (this.c != null) {
            this.c.dismiss();
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.b.a(this.f8306a, true);
    }

    public void b(MenuBuilder menuBuilder, boolean z) {
        if (z || menuBuilder == this.f8306a) {
            a();
        }
        if (this.d != null) {
            this.d.b(menuBuilder, z);
        }
    }

    public boolean b(MenuBuilder menuBuilder) {
        return this.d != null && this.d.b(menuBuilder);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f8306a.a((MenuItem) (MenuItemImpl) this.b.a().getItem(i), 0);
    }
}
