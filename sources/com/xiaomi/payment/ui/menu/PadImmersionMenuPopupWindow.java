package com.xiaomi.payment.ui.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import miuipub.v6.R;
import miuipub.widget.ListPopupWindow;

public class PadImmersionMenuPopupWindow extends ListPopupWindow implements ImmersionMenuPopupWindow {
    /* access modifiers changed from: private */
    public MenuItemClickListener i;

    public PadImmersionMenuPopupWindow(Context context) {
        super(context);
        a(context.getResources().getDrawable(R.color.v6_transparent));
        f(context.getResources().getDimensionPixelSize(com.xiaomi.payment.platform.R.dimen.mibi_immersion_menu_window_width));
        a((AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (PadImmersionMenuPopupWindow.this.i != null) {
                    PadImmersionMenuPopupWindow.this.i.a(adapterView, view, i, j);
                }
            }
        });
        a((PopupWindow.OnDismissListener) new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                MenuItemClickListener unused = PadImmersionMenuPopupWindow.this.i = null;
                PadImmersionMenuPopupWindow.this.a((View) null);
            }
        });
    }

    public void a(View view, ViewGroup viewGroup) {
        a(view);
        n();
    }

    public void a(BaseAdapter baseAdapter) {
        super.a((ListAdapter) baseAdapter);
    }

    public void a(MenuItemClickListener menuItemClickListener) {
        this.i = menuItemClickListener;
    }
}
