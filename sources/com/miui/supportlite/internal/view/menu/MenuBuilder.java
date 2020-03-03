package com.miui.supportlite.internal.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuBuilder implements Menu {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8206a = -65536;
    public static final int b = 16;
    public static final int c = 65535;
    private static final int[] d = {1, 4, 5, 3, 2, 0};
    private final Context e;
    private final Resources f;
    private Callback g;
    private ArrayList<MenuItemImpl> h;
    private int i = 0;
    private CharSequence j;
    private Drawable k;
    private View l;

    public interface Callback {
        void a(MenuBuilder menuBuilder);

        boolean a(MenuBuilder menuBuilder, MenuItem menuItem);
    }

    /* access modifiers changed from: package-private */
    public final void a(boolean z) {
    }

    public MenuBuilder g() {
        return this;
    }

    public boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return false;
    }

    public boolean performIdentifierAction(int i2, int i3) {
        return false;
    }

    public boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        return false;
    }

    public void setQwertyMode(boolean z) {
    }

    public MenuBuilder(Context context) {
        this.e = context;
        this.f = context.getResources();
        this.h = new ArrayList<>();
    }

    public void a(Callback callback) {
        this.g = callback;
    }

    private MenuItem a(int i2, int i3, int i4, CharSequence charSequence) {
        int f2 = f(i4);
        MenuItemImpl menuItemImpl = new MenuItemImpl(this, i2, i3, i4, f2, charSequence, this.i);
        this.h.add(a(this.h, f2), menuItemImpl);
        return menuItemImpl;
    }

    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    public MenuItem add(int i2) {
        return a(0, 0, 0, this.f.getString(i2));
    }

    public MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return a(i2, i3, i4, charSequence);
    }

    public MenuItem add(int i2, int i3, int i4, int i5) {
        return a(i2, i3, i4, this.f.getString(i5));
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public SubMenu addSubMenu(int i2) {
        return addSubMenu(0, 0, 0, (CharSequence) this.f.getString(i2));
    }

    public SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        MenuItemImpl menuItemImpl = (MenuItemImpl) a(i2, i3, i4, charSequence);
        SubMenuBuilder subMenuBuilder = new SubMenuBuilder(this.e, this, menuItemImpl);
        menuItemImpl.a(subMenuBuilder);
        return subMenuBuilder;
    }

    public SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return addSubMenu(i2, i3, i4, (CharSequence) this.f.getString(i5));
    }

    public int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        PackageManager packageManager = this.e.getPackageManager();
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
        e(a(i2));
    }

    public void removeGroup(int i2) {
        int b2 = b(i2);
        if (b2 >= 0) {
            int size = this.h.size() - b2;
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                if (i3 < size && this.h.get(b2).getGroupId() == i2) {
                    e(b2);
                    i3 = i4;
                } else {
                    return;
                }
            }
        }
    }

    private void e(int i2) {
        if (i2 >= 0 && i2 < this.h.size()) {
            this.h.remove(i2);
        }
    }

    public void clear() {
        this.h.clear();
    }

    /* access modifiers changed from: package-private */
    public void a(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        Iterator<MenuItemImpl> it = this.h.iterator();
        while (it.hasNext()) {
            MenuItemImpl next = it.next();
            if (next.getGroupId() == groupId && next.c() && next.isCheckable()) {
                next.b(next == menuItem);
            }
        }
    }

    public void setGroupCheckable(int i2, boolean z, boolean z2) {
        Iterator<MenuItemImpl> it = this.h.iterator();
        while (it.hasNext()) {
            MenuItemImpl next = it.next();
            if (next.getGroupId() == i2) {
                next.a(z2);
                next.setCheckable(z);
            }
        }
    }

    public void setGroupVisible(int i2, boolean z) {
        Iterator<MenuItemImpl> it = this.h.iterator();
        while (it.hasNext()) {
            MenuItemImpl next = it.next();
            if (next.getGroupId() == i2) {
                next.c(z);
            }
        }
    }

    public void setGroupEnabled(int i2, boolean z) {
        Iterator<MenuItemImpl> it = this.h.iterator();
        while (it.hasNext()) {
            MenuItemImpl next = it.next();
            if (next.getGroupId() == i2) {
                next.setEnabled(z);
            }
        }
    }

    public boolean hasVisibleItems() {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.h.get(i2).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public MenuItem findItem(int i2) {
        MenuItem findItem;
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItemImpl menuItemImpl = this.h.get(i3);
            if (menuItemImpl.getItemId() == i2) {
                return menuItemImpl;
            }
            if (menuItemImpl.hasSubMenu() && (findItem = menuItemImpl.getSubMenu().findItem(i2)) != null) {
                return findItem;
            }
        }
        return null;
    }

    public int a(int i2) {
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (this.h.get(i3).getItemId() == i2) {
                return i3;
            }
        }
        return -1;
    }

    public int b(int i2) {
        return a(i2, 0);
    }

    public int a(int i2, int i3) {
        int size = size();
        if (i3 < 0) {
            i3 = 0;
        }
        while (i3 < size) {
            if (this.h.get(i3).getGroupId() == i2) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    public int size() {
        return this.h.size();
    }

    public MenuItem getItem(int i2) {
        return this.h.get(i2);
    }

    private static int f(int i2) {
        int i3 = (-65536 & i2) >> 16;
        if (i3 < 0 || i3 >= d.length) {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        return (i2 & 65535) | (d[i3] << 16);
    }

    /* access modifiers changed from: package-private */
    public Resources a() {
        return this.f;
    }

    public Context b() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.g != null && this.g.a(menuBuilder, menuItem);
    }

    public void c() {
        if (this.g != null) {
            this.g.a(this);
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

    public void close() {
        a(true);
    }

    public void clearHeader() {
        this.k = null;
        this.j = null;
        this.l = null;
    }

    private void a(int i2, CharSequence charSequence, int i3, Drawable drawable, View view) {
        Resources a2 = a();
        if (view != null) {
            this.l = view;
            this.j = null;
            this.k = null;
            return;
        }
        if (i2 > 0) {
            this.j = a2.getText(i2);
        } else if (charSequence != null) {
            this.j = charSequence;
        }
        if (i3 > 0) {
            this.k = a2.getDrawable(i3);
        } else if (drawable != null) {
            this.k = drawable;
        }
        this.l = null;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder a(CharSequence charSequence) {
        a(0, charSequence, 0, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder c(int i2) {
        a(i2, (CharSequence) null, 0, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder a(Drawable drawable) {
        a(0, (CharSequence) null, 0, drawable, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder d(int i2) {
        a(0, (CharSequence) null, i2, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder a(View view) {
        a(0, (CharSequence) null, 0, (Drawable) null, view);
        return this;
    }

    public CharSequence d() {
        return this.j;
    }

    public Drawable e() {
        return this.k;
    }

    public View f() {
        return this.l;
    }
}
