package com.miuipub.internal.view.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.miuipub.internal.view.menu.MenuBuilder;

public final class ExpandedMenuView extends ListView implements AdapterView.OnItemClickListener, MenuBuilder.ItemInvoker, MenuView {

    /* renamed from: a  reason: collision with root package name */
    private MenuBuilder f8300a;
    private int b;

    public boolean filterLeftoverView(int i) {
        return false;
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnItemClickListener(this);
    }

    public void initialize(MenuBuilder menuBuilder) {
        this.f8300a = menuBuilder;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    public boolean invokeItem(MenuItemImpl menuItemImpl) {
        return this.f8300a.a((MenuItem) menuItemImpl, 0);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        invokeItem((MenuItemImpl) getAdapter().getItem(i));
    }

    public int getWindowAnimations() {
        return this.b;
    }
}
