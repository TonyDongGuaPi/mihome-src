package com.xiaomi.payment.ui.menu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public interface ImmersionMenuPopupWindow {
    void a(View view, ViewGroup viewGroup);

    void a(BaseAdapter baseAdapter);

    void a(MenuItemClickListener menuItemClickListener);

    void a(boolean z);

    boolean isShowing();
}
