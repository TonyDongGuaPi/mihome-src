package com.miui.supportlite.internal.view.menu;

import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public interface ImmersionMenuPopupWindow {
    void a(Menu menu);

    void a(View view, ViewGroup viewGroup);

    void a(boolean z);

    boolean isShowing();
}
