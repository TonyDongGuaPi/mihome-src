package com.miui.supportlite.internal.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import com.miui.supportlite.R;
import com.miui.supportlite.app.ImmersionMenu;
import com.miui.supportlite.util.AttributeResolver;
import com.miui.supportlite.widget.ImmersionListPopupWindow;

public class PhoneImmersionMenuPopupWindow extends ImmersionListPopupWindow implements ImmersionMenuPopupWindow {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ImmersionMenuAdapter f8208a;
    /* access modifiers changed from: private */
    public View b;
    /* access modifiers changed from: private */
    public ViewGroup c;
    private Drawable d;
    /* access modifiers changed from: private */
    public ImmersionMenu.ImmersionMenuListener e;

    public PhoneImmersionMenuPopupWindow(Context context, final Menu menu, ImmersionMenu.ImmersionMenuListener immersionMenuListener) {
        super(context);
        if (immersionMenuListener != null) {
            this.f8208a = new ImmersionMenuAdapter(context, menu);
            this.e = immersionMenuListener;
            a((ListAdapter) this.f8208a);
            a((AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    MenuItem a2 = PhoneImmersionMenuPopupWindow.this.f8208a.getItem(i);
                    if (a2.hasSubMenu()) {
                        final SubMenu subMenu = a2.getSubMenu();
                        PhoneImmersionMenuPopupWindow.this.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            public void onDismiss() {
                                PhoneImmersionMenuPopupWindow.this.setOnDismissListener((PopupWindow.OnDismissListener) null);
                                PhoneImmersionMenuPopupWindow.this.a((Menu) subMenu);
                                PhoneImmersionMenuPopupWindow.this.b(PhoneImmersionMenuPopupWindow.this.b, PhoneImmersionMenuPopupWindow.this.c);
                            }
                        });
                    } else {
                        PhoneImmersionMenuPopupWindow.this.e.a(menu, a2);
                    }
                    PhoneImmersionMenuPopupWindow.this.a(true);
                }
            });
            this.d = AttributeResolver.b(context, R.attr.miuisupport_immersionWindowBackground);
            return;
        }
        throw new IllegalArgumentException("ImmersionMenuListener should not be null.");
    }

    public void a(Menu menu) {
        this.f8208a.a(menu);
    }

    public void a(View view, ViewGroup viewGroup) {
        this.b = view;
        this.c = viewGroup;
        super.a(view, viewGroup);
    }

    /* access modifiers changed from: protected */
    public Drawable a(Context context, View view) {
        if (this.d != null) {
            return this.d;
        }
        return super.a(context, view);
    }
}
