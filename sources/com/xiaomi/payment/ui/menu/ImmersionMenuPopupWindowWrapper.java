package com.xiaomi.payment.ui.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.mibi.common.data.Utils;

public class ImmersionMenuPopupWindowWrapper {

    /* renamed from: a  reason: collision with root package name */
    private ImmersionMenuPopupWindow f12530a;

    public ImmersionMenuPopupWindowWrapper(Context context) {
        if (Utils.b()) {
            this.f12530a = new PadImmersionMenuPopupWindow(context);
        } else {
            this.f12530a = new PhoneImmersionMenuPopupWindow(context);
        }
    }

    public void a(ImmersionMenuPopupWindow immersionMenuPopupWindow) {
        this.f12530a = immersionMenuPopupWindow;
    }

    public boolean a() {
        return this.f12530a.isShowing();
    }

    public void a(View view, ViewGroup viewGroup) {
        this.f12530a.a(view, viewGroup);
    }

    public void b() {
        a(true);
    }

    public void a(boolean z) {
        this.f12530a.a(z);
    }

    public void a(BaseAdapter baseAdapter) {
        this.f12530a.a(baseAdapter);
    }

    public void a(MenuItemClickListener menuItemClickListener) {
        this.f12530a.a(menuItemClickListener);
    }
}
