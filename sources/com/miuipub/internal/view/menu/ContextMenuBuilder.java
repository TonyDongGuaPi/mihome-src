package com.miuipub.internal.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.util.EventLog;
import android.view.ContextMenu;
import android.view.View;

public class ContextMenuBuilder extends MenuBuilder implements ContextMenu {
    public ContextMenuBuilder(Context context) {
        super(context);
    }

    public ContextMenu setHeaderIcon(Drawable drawable) {
        return (ContextMenu) super.a(drawable);
    }

    public ContextMenu setHeaderIcon(int i) {
        return (ContextMenu) super.f(i);
    }

    public ContextMenu setHeaderTitle(CharSequence charSequence) {
        return (ContextMenu) super.a(charSequence);
    }

    public ContextMenu setHeaderTitle(int i) {
        return (ContextMenu) super.e(i);
    }

    public ContextMenu setHeaderView(View view) {
        return (ContextMenu) super.a(view);
    }

    public MenuDialogHelper a(View view, IBinder iBinder) {
        if (view != null) {
            view.createContextMenu(this);
        }
        if (j().size() <= 0) {
            return null;
        }
        EventLog.writeEvent(50001, 1);
        MenuDialogHelper menuDialogHelper = new MenuDialogHelper(this);
        menuDialogHelper.a(iBinder);
        return menuDialogHelper;
    }
}
