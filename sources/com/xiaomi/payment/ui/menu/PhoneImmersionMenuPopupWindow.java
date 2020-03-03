package com.xiaomi.payment.ui.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import miuipub.util.AttributeResolver;
import miuipub.v6.R;
import miuipub.widget.ImmersionListPopupWindow;

public class PhoneImmersionMenuPopupWindow extends ImmersionListPopupWindow implements ImmersionMenuPopupWindow {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public MenuItemClickListener f12533a;
    /* access modifiers changed from: private */
    public Drawable b;

    public PhoneImmersionMenuPopupWindow(Context context) {
        super(context);
        a((AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (PhoneImmersionMenuPopupWindow.this.f12533a != null) {
                    PhoneImmersionMenuPopupWindow.this.f12533a.a(adapterView, view, i, j);
                }
            }
        });
        this.b = AttributeResolver.b(context, R.attr.v6_immersionWindowBackground);
        setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                MenuItemClickListener unused = PhoneImmersionMenuPopupWindow.this.f12533a = null;
                Drawable unused2 = PhoneImmersionMenuPopupWindow.this.b = null;
            }
        });
    }

    /* access modifiers changed from: protected */
    public Drawable a(Context context, View view) {
        if (this.b != null) {
            return this.b;
        }
        return super.a(context, view);
    }

    public void a(BaseAdapter baseAdapter) {
        super.a((ListAdapter) baseAdapter);
    }

    public void a(MenuItemClickListener menuItemClickListener) {
        this.f12533a = menuItemClickListener;
    }
}
