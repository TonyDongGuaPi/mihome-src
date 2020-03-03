package com.miuipub.internal.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import com.miuipub.internal.view.menu.MenuPresenter;
import com.miuipub.internal.view.menu.MenuView;
import java.util.ArrayList;
import miuipub.v6.R;

public class ListMenuPresenter implements AdapterView.OnItemClickListener, MenuPresenter {
    public static final String i = "android:menu:list";

    /* renamed from: a  reason: collision with root package name */
    Context f8303a;
    LayoutInflater b;
    MenuBuilder c;
    ExpandedMenuView d;
    int e;
    int f;
    int g;
    MenuAdapter h;
    /* access modifiers changed from: private */
    public int j;
    private MenuPresenter.Callback k;
    private int l;

    public boolean a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean e() {
        return false;
    }

    public ListMenuPresenter(Context context, int i2, int i3) {
        this(i2, i3, 0);
        this.f8303a = context;
        this.b = LayoutInflater.from(this.f8303a);
    }

    public ListMenuPresenter(int i2, int i3) {
        this(R.layout.v6_expanded_menu_layout, i2, i3);
    }

    public ListMenuPresenter(int i2, int i3, int i4) {
        this.f = i3;
        this.g = i2;
        this.e = i4;
    }

    public void a(Context context, MenuBuilder menuBuilder) {
        if (this.e != 0) {
            this.f8303a = new ContextThemeWrapper(context, this.e);
            this.b = LayoutInflater.from(this.f8303a);
        } else if (this.f8303a != null) {
            this.f8303a = context;
            if (this.b == null) {
                this.b = LayoutInflater.from(this.f8303a);
            }
        }
        if (this.c != null) {
            this.c.b((MenuPresenter) this);
        }
        this.c = menuBuilder;
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    public MenuView a(ViewGroup viewGroup) {
        if (this.h == null) {
            this.h = new MenuAdapter();
        }
        if (this.h.isEmpty()) {
            return null;
        }
        if (this.d == null) {
            this.d = (ExpandedMenuView) this.b.inflate(this.g, viewGroup, false);
            this.d.setAdapter(this.h);
            this.d.setOnItemClickListener(this);
        }
        return this.d;
    }

    public ListAdapter a() {
        if (this.h == null) {
            this.h = new MenuAdapter();
        }
        return this.h;
    }

    public void d(boolean z) {
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    public void a(MenuPresenter.Callback callback) {
        this.k = callback;
    }

    public boolean a(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        new MenuDialogHelper(subMenuBuilder).a((IBinder) null);
        if (this.k == null) {
            return true;
        }
        this.k.b(subMenuBuilder);
        return true;
    }

    public void a(MenuBuilder menuBuilder, boolean z) {
        if (this.k != null) {
            this.k.b(menuBuilder, z);
        }
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.j;
    }

    public void a(int i2) {
        this.j = i2;
        if (this.d != null) {
            d(false);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        this.c.a((MenuItem) this.h.getItem(i2), 0);
    }

    public void a(Bundle bundle) {
        SparseArray sparseArray = new SparseArray();
        if (this.d != null) {
            this.d.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    }

    public void b(Bundle bundle) {
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.d.restoreHierarchyState(sparseParcelableArray);
        }
    }

    public void b(int i2) {
        this.l = i2;
    }

    public int g() {
        return this.l;
    }

    public Parcelable f() {
        if (this.d == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        a(bundle);
        return bundle;
    }

    public void a(Parcelable parcelable) {
        b((Bundle) parcelable);
    }

    private class MenuAdapter extends BaseAdapter {
        private int b = -1;

        public long getItemId(int i) {
            return (long) i;
        }

        public MenuAdapter() {
            a();
        }

        public int getCount() {
            int size = ListMenuPresenter.this.c.m().size() - ListMenuPresenter.this.j;
            return this.b < 0 ? size : size - 1;
        }

        /* renamed from: a */
        public MenuItemImpl getItem(int i) {
            ArrayList<MenuItemImpl> m = ListMenuPresenter.this.c.m();
            int a2 = i + ListMenuPresenter.this.j;
            if (this.b >= 0 && a2 >= this.b) {
                a2++;
            }
            return m.get(a2);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ListMenuPresenter.this.b.inflate(ListMenuPresenter.this.f, viewGroup, false);
            }
            ((MenuView.ItemView) view).initialize(getItem(i), 0);
            return view;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            MenuItemImpl s = ListMenuPresenter.this.c.s();
            if (s != null) {
                ArrayList<MenuItemImpl> m = ListMenuPresenter.this.c.m();
                int size = m.size();
                for (int i = 0; i < size; i++) {
                    if (m.get(i) == s) {
                        this.b = i;
                        return;
                    }
                }
            }
            this.b = -1;
        }

        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }
}
