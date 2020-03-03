package com.miuipub.internal.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import miuipub.v6.R;

public class MenuBuilder implements Menu {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8305a = -65536;
    public static final int b = 16;
    public static final int c = 65535;
    private static final String g = "android:menu:presenters";
    private static final String h = "android:menu:actionviewstates";
    private static final String i = "android:menu:expandedactionview";
    private static final int[] j = {1, 4, 5, 3, 2, 0};
    private boolean A = false;
    private ArrayList<MenuItemImpl> B = new ArrayList<>();
    private CopyOnWriteArrayList<WeakReference<MenuPresenter>> C = new CopyOnWriteArrayList<>();
    private MenuItemImpl D;
    CharSequence d;
    Drawable e;
    View f;
    private final Context k;
    private final Resources l;
    private boolean m;
    private boolean n;
    private Callback o;
    private ArrayList<MenuItemImpl> p;
    private ArrayList<MenuItemImpl> q;
    private boolean r;
    private ArrayList<MenuItemImpl> s;
    private ArrayList<MenuItemImpl> t;
    private boolean u;
    private int v = 0;
    private ContextMenu.ContextMenuInfo w;
    private boolean x = false;
    private boolean y = false;
    private boolean z = false;

    public interface Callback {
        boolean a(MenuBuilder menuBuilder, MenuItem menuItem);

        void c(MenuBuilder menuBuilder);
    }

    public interface ItemInvoker {
        boolean invokeItem(MenuItemImpl menuItemImpl);
    }

    /* access modifiers changed from: protected */
    public String a() {
        return h;
    }

    public MenuBuilder q() {
        return this;
    }

    public MenuBuilder(Context context) {
        this.k = context;
        this.l = context.getResources();
        this.p = new ArrayList<>();
        this.q = new ArrayList<>();
        this.r = true;
        this.s = new ArrayList<>();
        this.t = new ArrayList<>();
        this.u = true;
        f(true);
    }

    public MenuBuilder a(int i2) {
        this.v = i2;
        return this;
    }

    public void a(MenuPresenter menuPresenter) {
        this.C.add(new WeakReference(menuPresenter));
        menuPresenter.a(this.k, this);
        this.u = true;
    }

    public void b(MenuPresenter menuPresenter) {
        Iterator<WeakReference<MenuPresenter>> it = this.C.iterator();
        while (it.hasNext()) {
            WeakReference next = it.next();
            MenuPresenter menuPresenter2 = (MenuPresenter) next.get();
            if (menuPresenter2 == null || menuPresenter2 == menuPresenter) {
                this.C.remove(next);
            }
        }
    }

    private void e(boolean z2) {
        if (!this.C.isEmpty()) {
            h();
            Iterator<WeakReference<MenuPresenter>> it = this.C.iterator();
            while (it.hasNext()) {
                WeakReference next = it.next();
                MenuPresenter menuPresenter = (MenuPresenter) next.get();
                if (menuPresenter == null) {
                    this.C.remove(next);
                } else {
                    menuPresenter.d(z2);
                }
            }
            i();
        }
    }

    private boolean a(SubMenuBuilder subMenuBuilder) {
        boolean z2 = false;
        if (this.C.isEmpty()) {
            return false;
        }
        Iterator<WeakReference<MenuPresenter>> it = this.C.iterator();
        while (it.hasNext()) {
            WeakReference next = it.next();
            MenuPresenter menuPresenter = (MenuPresenter) next.get();
            if (menuPresenter == null) {
                this.C.remove(next);
            } else if (!z2) {
                z2 = menuPresenter.a(subMenuBuilder);
            }
        }
        return z2;
    }

    private void e(Bundle bundle) {
        Parcelable f2;
        if (!this.C.isEmpty()) {
            SparseArray sparseArray = new SparseArray();
            Iterator<WeakReference<MenuPresenter>> it = this.C.iterator();
            while (it.hasNext()) {
                WeakReference next = it.next();
                MenuPresenter menuPresenter = (MenuPresenter) next.get();
                if (menuPresenter == null) {
                    this.C.remove(next);
                } else {
                    int g2 = menuPresenter.g();
                    if (g2 > 0 && (f2 = menuPresenter.f()) != null) {
                        sparseArray.put(g2, f2);
                    }
                }
            }
            bundle.putSparseParcelableArray(g, sparseArray);
        }
    }

    private void f(Bundle bundle) {
        Parcelable parcelable;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(g);
        if (sparseParcelableArray != null && !this.C.isEmpty()) {
            Iterator<WeakReference<MenuPresenter>> it = this.C.iterator();
            while (it.hasNext()) {
                WeakReference next = it.next();
                MenuPresenter menuPresenter = (MenuPresenter) next.get();
                if (menuPresenter == null) {
                    this.C.remove(next);
                } else {
                    int g2 = menuPresenter.g();
                    if (g2 > 0 && (parcelable = (Parcelable) sparseParcelableArray.get(g2)) != null) {
                        menuPresenter.a(parcelable);
                    }
                }
            }
        }
    }

    public void a(Bundle bundle) {
        e(bundle);
    }

    public void b(Bundle bundle) {
        f(bundle);
    }

    public void c(Bundle bundle) {
        int size = size();
        SparseArray sparseArray = null;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
            View actionView = item.getActionView();
            if (!(actionView == null || actionView.getId() == -1)) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt(i, item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((SubMenuBuilder) item.getSubMenu()).c(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(a(), sparseArray);
        }
    }

    public void d(Bundle bundle) {
        MenuItem findItem;
        if (bundle != null) {
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(a());
            int size = size();
            for (int i2 = 0; i2 < size; i2++) {
                MenuItem item = getItem(i2);
                View actionView = item.getActionView();
                if (!(actionView == null || actionView.getId() == -1)) {
                    actionView.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((SubMenuBuilder) item.getSubMenu()).d(bundle);
                }
            }
            int i3 = bundle.getInt(i);
            if (i3 > 0 && (findItem = findItem(i3)) != null) {
                findItem.expandActionView();
            }
        }
    }

    public void a(Callback callback) {
        this.o = callback;
    }

    private MenuItem a(int i2, int i3, int i4, CharSequence charSequence) {
        int g2 = g(i4);
        MenuItemImpl menuItemImpl = new MenuItemImpl(this, i2, i3, i4, g2, charSequence, this.v);
        if (this.w != null) {
            menuItemImpl.a(this.w);
        }
        this.p.add(a(this.p, g2), menuItemImpl);
        c(true);
        return menuItemImpl;
    }

    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    public MenuItem add(int i2) {
        return a(0, 0, 0, this.l.getString(i2));
    }

    public MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return a(i2, i3, i4, charSequence);
    }

    public MenuItem add(int i2, int i3, int i4, int i5) {
        return a(i2, i3, i4, this.l.getString(i5));
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public SubMenu addSubMenu(int i2) {
        return addSubMenu(0, 0, 0, (CharSequence) this.l.getString(i2));
    }

    public SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        MenuItemImpl menuItemImpl = (MenuItemImpl) a(i2, i3, i4, charSequence);
        SubMenuBuilder subMenuBuilder = new SubMenuBuilder(this.k, this, menuItemImpl);
        menuItemImpl.a(subMenuBuilder);
        return subMenuBuilder;
    }

    public SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return addSubMenu(i2, i3, i4, (CharSequence) this.l.getString(i5));
    }

    public int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        PackageManager packageManager = this.k.getPackageManager();
        List<ResolveInfo> queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i5 & 1) == 0) {
            removeGroup(i2);
        }
        for (int i6 = 0; i6 < size; i6++) {
            ResolveInfo resolveInfo = queryIntentActivityOptions.get(i6);
            Intent intent2 = new Intent(resolveInfo.specificIndex < 0 ? intent : intentArr[resolveInfo.specificIndex]);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            MenuItem intent3 = add(i2, i3, i4, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && resolveInfo.specificIndex >= 0) {
                menuItemArr[resolveInfo.specificIndex] = intent3;
            }
        }
        return size;
    }

    public void removeItem(int i2) {
        a(c(i2), true);
    }

    public void removeGroup(int i2) {
        int d2 = d(i2);
        if (d2 >= 0) {
            int size = this.p.size() - d2;
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                if (i3 >= size || this.p.get(d2).getGroupId() != i2) {
                    c(true);
                } else {
                    a(d2, false);
                    i3 = i4;
                }
            }
            c(true);
        }
    }

    private void a(int i2, boolean z2) {
        if (i2 >= 0 && i2 < this.p.size()) {
            this.p.remove(i2);
            if (z2) {
                c(true);
            }
        }
    }

    public void b(int i2) {
        a(i2, true);
    }

    public void b() {
        this.x = true;
        clear();
        clearHeader();
        this.x = false;
        this.y = false;
        c(true);
    }

    public void clear() {
        if (this.D != null) {
            d(this.D);
        }
        this.p.clear();
        c(true);
    }

    /* access modifiers changed from: package-private */
    public void a(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        Iterator<MenuItemImpl> it = this.p.iterator();
        while (it.hasNext()) {
            MenuItemImpl next = it.next();
            if (next.getGroupId() == groupId && next.g() && next.isCheckable()) {
                next.b(next == menuItem);
            }
        }
    }

    public void setGroupCheckable(int i2, boolean z2, boolean z3) {
        Iterator<MenuItemImpl> it = this.p.iterator();
        while (it.hasNext()) {
            MenuItemImpl next = it.next();
            if (next.getGroupId() == i2) {
                next.a(z3);
                next.setCheckable(z2);
            }
        }
    }

    public void setGroupVisible(int i2, boolean z2) {
        Iterator<MenuItemImpl> it = this.p.iterator();
        boolean z3 = false;
        while (it.hasNext()) {
            MenuItemImpl next = it.next();
            if (next.getGroupId() == i2 && next.c(z2)) {
                z3 = true;
            }
        }
        if (z3) {
            c(true);
        }
    }

    public void setGroupEnabled(int i2, boolean z2) {
        Iterator<MenuItemImpl> it = this.p.iterator();
        while (it.hasNext()) {
            MenuItemImpl next = it.next();
            if (next.getGroupId() == i2) {
                next.setEnabled(z2);
            }
        }
    }

    public boolean hasVisibleItems() {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.p.get(i2).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public MenuItem findItem(int i2) {
        MenuItem findItem;
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItemImpl menuItemImpl = this.p.get(i3);
            if (menuItemImpl.getItemId() == i2) {
                return menuItemImpl;
            }
            if (menuItemImpl.hasSubMenu() && (findItem = menuItemImpl.getSubMenu().findItem(i2)) != null) {
                return findItem;
            }
        }
        return null;
    }

    public int c(int i2) {
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (this.p.get(i3).getItemId() == i2) {
                return i3;
            }
        }
        return -1;
    }

    public int d(int i2) {
        return a(i2, 0);
    }

    public int a(int i2, int i3) {
        int size = size();
        if (i3 < 0) {
            i3 = 0;
        }
        while (i3 < size) {
            if (this.p.get(i3).getGroupId() == i2) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    public int size() {
        return this.p.size();
    }

    public MenuItem getItem(int i2) {
        return this.p.get(i2);
    }

    public boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return a(i2, keyEvent) != null;
    }

    public void setQwertyMode(boolean z2) {
        this.m = z2;
        c(false);
    }

    private static int g(int i2) {
        int i3 = (-65536 & i2) >> 16;
        if (i3 < 0 || i3 >= j.length) {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        return (i2 & 65535) | (j[i3] << 16);
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.m;
    }

    public void a(boolean z2) {
        if (this.n != z2) {
            f(z2);
            c(false);
        }
    }

    private void f(boolean z2) {
        boolean z3 = true;
        if (!z2 || this.l.getConfiguration().keyboard == 1 || !this.l.getBoolean(R.bool.v6_abc_config_showMenuShortcutsWhenKeyboardPresent)) {
            z3 = false;
        }
        this.n = z3;
    }

    public boolean d() {
        return this.n;
    }

    /* access modifiers changed from: package-private */
    public Resources e() {
        return this.l;
    }

    public Context f() {
        return this.k;
    }

    /* access modifiers changed from: package-private */
    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.o != null && this.o.a(menuBuilder, menuItem);
    }

    public void g() {
        if (this.o != null) {
            this.o.c(this);
        }
    }

    private static int a(ArrayList<MenuItemImpl> arrayList, int i2) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).b() <= i2) {
                return size + 1;
            }
        }
        return 0;
    }

    public boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        MenuItemImpl a2 = a(i2, keyEvent);
        boolean a3 = a2 != null ? a((MenuItem) a2, i3) : false;
        if ((i3 & 2) != 0) {
            b(true);
        }
        return a3;
    }

    /* access modifiers changed from: package-private */
    public void a(List<MenuItemImpl> list, int i2, KeyEvent keyEvent) {
        char c2;
        boolean c3 = c();
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i2 == 67) {
            Iterator<MenuItemImpl> it = this.p.iterator();
            while (it.hasNext()) {
                MenuItemImpl next = it.next();
                if (next.hasSubMenu()) {
                    ((MenuBuilder) next.getSubMenu()).a(list, i2, keyEvent);
                }
                if (c3) {
                    c2 = next.getAlphabeticShortcut();
                } else {
                    c2 = next.getNumericShortcut();
                }
                if ((metaState & 5) == 0 && c2 != 0) {
                    if ((c2 == keyData.meta[0] || c2 == keyData.meta[2] || (c3 && c2 == 8 && i2 == 67)) && next.isEnabled()) {
                        list.add(next);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public MenuItemImpl a(int i2, KeyEvent keyEvent) {
        char c2;
        ArrayList<MenuItemImpl> arrayList = this.B;
        arrayList.clear();
        a(arrayList, i2, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        if (arrayList.size() == 1) {
            return arrayList.get(0);
        }
        boolean c3 = c();
        Iterator<MenuItemImpl> it = arrayList.iterator();
        while (it.hasNext()) {
            MenuItemImpl next = it.next();
            if (c3) {
                c2 = next.getAlphabeticShortcut();
            } else {
                c2 = next.getNumericShortcut();
            }
            if ((c2 == keyData.meta[0] && (metaState & 2) == 0) || ((c2 == keyData.meta[2] && (metaState & 2) != 0) || (c3 && c2 == 8 && i2 == 67))) {
                return next;
            }
        }
        return null;
    }

    public boolean performIdentifierAction(int i2, int i3) {
        return a(findItem(i2), i3);
    }

    public boolean a(MenuItem menuItem, int i2) {
        MenuItemImpl menuItemImpl = (MenuItemImpl) menuItem;
        if (menuItemImpl == null || !menuItemImpl.isEnabled()) {
            return false;
        }
        boolean a2 = menuItemImpl.a();
        ActionProvider n2 = menuItemImpl.n();
        boolean z2 = n2 != null && n2.hasSubMenu();
        if (menuItemImpl.o()) {
            a2 |= menuItemImpl.expandActionView();
            if (a2) {
                b(true);
            }
        } else if (menuItemImpl.hasSubMenu() || z2) {
            b(false);
            if (!menuItemImpl.hasSubMenu()) {
                menuItemImpl.a(new SubMenuBuilder(f(), this, menuItemImpl));
            }
            SubMenuBuilder subMenuBuilder = (SubMenuBuilder) menuItemImpl.getSubMenu();
            if (z2) {
                n2.onPrepareSubMenu(subMenuBuilder);
            }
            a2 |= a(subMenuBuilder);
            if (!a2) {
                b(true);
            }
        } else if ((i2 & 1) == 0) {
            b(true);
        }
        return a2;
    }

    /* access modifiers changed from: package-private */
    public final void b(boolean z2) {
        if (!this.A) {
            this.A = true;
            Iterator<WeakReference<MenuPresenter>> it = this.C.iterator();
            while (it.hasNext()) {
                WeakReference next = it.next();
                MenuPresenter menuPresenter = (MenuPresenter) next.get();
                if (menuPresenter == null) {
                    this.C.remove(next);
                } else {
                    menuPresenter.a(this, z2);
                }
            }
            this.A = false;
        }
    }

    public void close() {
        b(true);
    }

    /* access modifiers changed from: package-private */
    public void c(boolean z2) {
        if (!this.x) {
            if (z2) {
                this.r = true;
                this.u = true;
            }
            e(z2);
            return;
        }
        this.y = true;
    }

    public void h() {
        if (!this.x) {
            this.x = true;
            this.y = false;
        }
    }

    public void i() {
        this.x = false;
        if (this.y) {
            this.y = false;
            c(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(MenuItemImpl menuItemImpl) {
        this.r = true;
        c(true);
    }

    /* access modifiers changed from: package-private */
    public void b(MenuItemImpl menuItemImpl) {
        this.u = true;
        c(true);
    }

    public ArrayList<MenuItemImpl> j() {
        if (!this.r) {
            return this.q;
        }
        this.q.clear();
        Iterator<MenuItemImpl> it = this.p.iterator();
        while (it.hasNext()) {
            MenuItemImpl next = it.next();
            if (next.isVisible()) {
                this.q.add(next);
            }
        }
        this.r = false;
        this.u = true;
        return this.q;
    }

    public void k() {
        if (this.u) {
            Iterator<WeakReference<MenuPresenter>> it = this.C.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                WeakReference next = it.next();
                MenuPresenter menuPresenter = (MenuPresenter) next.get();
                if (menuPresenter == null) {
                    this.C.remove(next);
                } else {
                    z2 |= menuPresenter.e();
                }
            }
            if (z2) {
                this.s.clear();
                this.t.clear();
                Iterator<MenuItemImpl> it2 = j().iterator();
                while (it2.hasNext()) {
                    MenuItemImpl next2 = it2.next();
                    if (next2.j()) {
                        this.s.add(next2);
                    } else {
                        this.t.add(next2);
                    }
                }
            } else {
                this.s.clear();
                this.t.clear();
                this.t.addAll(j());
            }
            this.u = false;
        }
    }

    public ArrayList<MenuItemImpl> l() {
        k();
        return this.s;
    }

    public ArrayList<MenuItemImpl> m() {
        k();
        return this.t;
    }

    public void clearHeader() {
        this.e = null;
        this.d = null;
        this.f = null;
        c(false);
    }

    private void a(int i2, CharSequence charSequence, int i3, Drawable drawable, View view) {
        Resources e2 = e();
        if (view != null) {
            this.f = view;
            this.d = null;
            this.e = null;
        } else {
            if (i2 > 0) {
                this.d = e2.getText(i2);
            } else if (charSequence != null) {
                this.d = charSequence;
            }
            if (i3 > 0) {
                this.e = e2.getDrawable(i3);
            } else if (drawable != null) {
                this.e = drawable;
            }
            this.f = null;
        }
        c(false);
    }

    /* access modifiers changed from: protected */
    public MenuBuilder a(CharSequence charSequence) {
        a(0, charSequence, 0, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder e(int i2) {
        a(i2, (CharSequence) null, 0, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder a(Drawable drawable) {
        a(0, (CharSequence) null, 0, drawable, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder f(int i2) {
        a(0, (CharSequence) null, i2, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder a(View view) {
        a(0, (CharSequence) null, 0, (Drawable) null, view);
        return this;
    }

    public CharSequence n() {
        return this.d;
    }

    public Drawable o() {
        return this.e;
    }

    public View p() {
        return this.f;
    }

    public void a(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.w = contextMenuInfo;
    }

    /* access modifiers changed from: package-private */
    public void d(boolean z2) {
        this.z = z2;
    }

    /* access modifiers changed from: package-private */
    public boolean r() {
        return this.z;
    }

    public boolean c(MenuItemImpl menuItemImpl) {
        boolean z2 = false;
        if (this.C.isEmpty()) {
            return false;
        }
        h();
        Iterator<WeakReference<MenuPresenter>> it = this.C.iterator();
        while (it.hasNext()) {
            WeakReference next = it.next();
            MenuPresenter menuPresenter = (MenuPresenter) next.get();
            if (menuPresenter == null) {
                this.C.remove(next);
            } else {
                z2 = menuPresenter.a(this, menuItemImpl);
                if (z2) {
                    break;
                }
            }
        }
        i();
        if (z2) {
            this.D = menuItemImpl;
        }
        return z2;
    }

    public boolean d(MenuItemImpl menuItemImpl) {
        boolean z2 = false;
        if (this.C.isEmpty() || this.D != menuItemImpl) {
            return false;
        }
        h();
        Iterator<WeakReference<MenuPresenter>> it = this.C.iterator();
        while (it.hasNext()) {
            WeakReference next = it.next();
            MenuPresenter menuPresenter = (MenuPresenter) next.get();
            if (menuPresenter == null) {
                this.C.remove(next);
            } else {
                z2 = menuPresenter.b(this, menuItemImpl);
                if (z2) {
                    break;
                }
            }
        }
        i();
        if (z2) {
            this.D = null;
        }
        return z2;
    }

    public MenuItemImpl s() {
        return this.D;
    }
}
