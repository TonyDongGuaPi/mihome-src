package com.miuipub.internal.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import com.miuipub.internal.view.menu.MenuPresenter;
import com.miuipub.internal.view.menu.MenuView;
import java.util.ArrayList;
import miuipub.v6.R;
import miuipub.widget.ListPopupWindow;

public class MenuPopupHelper implements View.OnKeyListener, ViewTreeObserver.OnGlobalLayoutListener, AdapterView.OnItemClickListener, PopupWindow.OnDismissListener, MenuPresenter {

    /* renamed from: a  reason: collision with root package name */
    private static final int f8309a = R.layout.v6_popup_menu_item_layout;
    boolean b;
    private Context c;
    /* access modifiers changed from: private */
    public LayoutInflater d;
    private ListPopupWindow e;
    /* access modifiers changed from: private */
    public MenuBuilder f;
    private int g;
    private View h;
    /* access modifiers changed from: private */
    public boolean i;
    private ViewTreeObserver j;
    private MenuAdapter k;
    private MenuPresenter.Callback l;
    private ViewGroup m;
    private int n;
    /* access modifiers changed from: private */
    public int o;

    public void a(Context context, MenuBuilder menuBuilder) {
    }

    public void a(Parcelable parcelable) {
    }

    public boolean a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean e() {
        return false;
    }

    public Parcelable f() {
        return null;
    }

    public int g() {
        return 0;
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder) {
        this(context, menuBuilder, (View) null, false);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view) {
        this(context, menuBuilder, view, false);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z) {
        this.o = f8309a;
        this.c = context;
        this.d = LayoutInflater.from(context);
        this.f = menuBuilder;
        this.i = z;
        Resources resources = context.getResources();
        this.g = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.v6_config_prefDialogWidth));
        this.h = view;
        menuBuilder.a((MenuPresenter) this);
    }

    public void a(View view) {
        this.h = view;
    }

    public void b(boolean z) {
        this.b = z;
    }

    public void a(int i2) {
        this.n = i2;
    }

    public void b(int i2) {
        this.o = i2;
    }

    public void c() {
        if (!a()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean a() {
        this.e = new ListPopupWindow(this.c, (AttributeSet) null, 16843520);
        this.e.a((PopupWindow.OnDismissListener) this);
        this.e.a((AdapterView.OnItemClickListener) this);
        this.e.e(this.n);
        this.k = new MenuAdapter(this.f);
        this.e.a((ListAdapter) this.k);
        this.e.b(true);
        View view = this.h;
        boolean z = false;
        if (view == null) {
            return false;
        }
        if (this.j == null) {
            z = true;
        }
        this.j = view.getViewTreeObserver();
        if (z) {
            this.j.addOnGlobalLayoutListener(this);
        }
        this.e.a(view);
        this.e.g(Math.min(a((ListAdapter) this.k), this.g));
        this.e.i(2);
        this.e.n();
        this.e.w().setOnKeyListener(this);
        return true;
    }

    public void a(boolean z) {
        if (b()) {
            this.e.a(z);
        }
    }

    public void onDismiss() {
        this.e = null;
        this.f.close();
        if (this.j != null) {
            if (!this.j.isAlive()) {
                this.j = this.h.getViewTreeObserver();
            }
            this.j.removeOnGlobalLayoutListener(this);
            this.j = null;
        }
    }

    public boolean b() {
        return this.e != null && this.e.isShowing();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        MenuAdapter menuAdapter = this.k;
        menuAdapter.b.a((MenuItem) menuAdapter.getItem(i2), 0);
    }

    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        a(false);
        return true;
    }

    private int a(ListAdapter listAdapter) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.g, Integer.MIN_VALUE);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(this.g, Integer.MIN_VALUE);
        int count = listAdapter.getCount();
        View view = null;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < count; i4++) {
            int itemViewType = listAdapter.getItemViewType(i4);
            if (itemViewType != i2) {
                view = null;
                i2 = itemViewType;
            }
            if (this.m == null) {
                this.m = new FrameLayout(this.c);
            }
            view = listAdapter.getView(i4, view, this.m);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i3 = Math.max(i3, view.getMeasuredWidth());
        }
        return i3;
    }

    public void onGlobalLayout() {
        if (b()) {
            View view = this.h;
            if (view == null || !view.isShown()) {
                a(false);
            } else if (b()) {
                this.e.g(Math.min(a((ListAdapter) this.k), this.g));
                this.e.n();
            }
        }
    }

    public MenuView a(ViewGroup viewGroup) {
        throw new UnsupportedOperationException("MenuPopupHelpers manage their own views");
    }

    public void d(boolean z) {
        if (this.k != null) {
            this.k.notifyDataSetChanged();
        }
    }

    public void a(MenuPresenter.Callback callback) {
        this.l = callback;
    }

    public boolean a(SubMenuBuilder subMenuBuilder) {
        boolean z;
        if (subMenuBuilder.hasVisibleItems()) {
            MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.c, subMenuBuilder, this.h, false);
            menuPopupHelper.a(this.l);
            int size = subMenuBuilder.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    z = false;
                    break;
                }
                MenuItem item = subMenuBuilder.getItem(i2);
                if (item.isVisible() && item.getIcon() != null) {
                    z = true;
                    break;
                }
                i2++;
            }
            menuPopupHelper.b(z);
            if (menuPopupHelper.a()) {
                if (this.l != null) {
                    this.l.b(subMenuBuilder);
                }
                return true;
            }
        }
        return false;
    }

    public void a(MenuBuilder menuBuilder, boolean z) {
        if (menuBuilder == this.f) {
            a(true);
            if (this.l != null) {
                this.l.b(menuBuilder, z);
            }
        }
    }

    private class MenuAdapter extends BaseAdapter {
        /* access modifiers changed from: private */
        public MenuBuilder b;
        private int c = -1;

        public long getItemId(int i) {
            return (long) i;
        }

        public MenuAdapter(MenuBuilder menuBuilder) {
            this.b = menuBuilder;
            a();
        }

        public int getCount() {
            ArrayList<MenuItemImpl> m = MenuPopupHelper.this.i ? this.b.m() : this.b.j();
            if (this.c < 0) {
                return m.size();
            }
            return m.size() - 1;
        }

        /* renamed from: a */
        public MenuItemImpl getItem(int i) {
            ArrayList<MenuItemImpl> m = MenuPopupHelper.this.i ? this.b.m() : this.b.j();
            if (this.c >= 0 && i >= this.c) {
                i++;
            }
            return m.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = MenuPopupHelper.this.d.inflate(MenuPopupHelper.this.o, viewGroup, false);
            }
            MenuView.ItemView itemView = (MenuView.ItemView) view;
            if (MenuPopupHelper.this.b) {
                ((ListMenuItemView) view).setForceShowIcon(true);
            }
            itemView.initialize(getItem(i), 0);
            return view;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            MenuItemImpl s = MenuPopupHelper.this.f.s();
            if (s != null) {
                ArrayList<MenuItemImpl> m = MenuPopupHelper.this.f.m();
                int size = m.size();
                for (int i = 0; i < size; i++) {
                    if (m.get(i) == s) {
                        this.c = i;
                        return;
                    }
                }
            }
            this.c = -1;
        }

        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }
}
