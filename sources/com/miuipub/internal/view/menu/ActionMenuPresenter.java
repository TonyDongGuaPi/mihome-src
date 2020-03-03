package com.miuipub.internal.view.menu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import com.miuipub.internal.view.ActionBarPolicy;
import com.miuipub.internal.view.menu.ActionMenuView;
import com.miuipub.internal.view.menu.MenuBuilder;
import com.miuipub.internal.view.menu.MenuPresenter;
import com.miuipub.internal.view.menu.MenuView;
import java.util.ArrayList;
import miuipub.os.Build;
import miuipub.v6.R;

public class ActionMenuPresenter extends BaseMenuPresenter {
    /* access modifiers changed from: private */
    public ActionButtonSubMenu A;
    /* access modifiers changed from: private */
    public OpenOverflowRunnable B;

    /* renamed from: a  reason: collision with root package name */
    final PopupPresenterCallback f8289a;
    int b;
    private View i;
    private boolean j;
    private boolean k;
    private int l;
    private int m;
    private int n;
    /* access modifiers changed from: private */
    public int o;
    /* access modifiers changed from: private */
    public int p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    /* access modifiers changed from: private */
    public int u;
    private final SparseBooleanArray v;
    private View w;
    /* access modifiers changed from: private */
    public OverflowMenu x;
    private OverflowMenu y;
    private MenuItemImpl z;

    private interface OverflowMenu {
        void a(boolean z);

        boolean a();

        void b(MenuBuilder menuBuilder);

        boolean b();
    }

    public ActionMenuPresenter(Context context, int i2, int i3) {
        this(context, i2, i3, 0, 0);
    }

    public ActionMenuPresenter(Context context, int i2, int i3, int i4, int i5) {
        super(context, i2, i3);
        this.u = 16843510;
        this.v = new SparseBooleanArray();
        this.f8289a = new PopupPresenterCallback();
        this.p = i4;
        this.o = i5;
    }

    public void a(Context context, MenuBuilder menuBuilder) {
        super.a(context, menuBuilder);
        context.getResources();
        ActionBarPolicy a2 = ActionBarPolicy.a(context);
        if (!this.k) {
            this.j = a2.b();
        }
        if (!this.s) {
            this.l = a2.c();
        }
        if (!this.q) {
            this.n = a2.a();
        }
        int i2 = this.l;
        if (this.j) {
            if (this.i == null) {
                this.i = a(this.c);
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.i.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i2 -= this.i.getMeasuredWidth();
        } else {
            this.i = null;
        }
        this.m = i2;
        this.w = null;
    }

    public void a(Configuration configuration) {
        if (!this.q) {
            this.n = this.d.getResources().getInteger(R.integer.v6_abc_max_action_buttons);
        }
        if (this.e != null) {
            this.e.c(true);
        }
    }

    public void a(int i2, boolean z2) {
        this.l = i2;
        this.r = z2;
        this.s = true;
    }

    public void a(boolean z2) {
        this.j = z2;
        this.k = true;
    }

    public void a(int i2) {
        this.n = i2;
        this.q = true;
    }

    public void b(boolean z2) {
        this.t = z2;
    }

    public void c(boolean z2) {
        if (z2) {
            this.u = R.attr.v6_actionModeOverflowButtonStyle;
        }
    }

    public MenuView a(ViewGroup viewGroup) {
        MenuView a2 = super.a(viewGroup);
        ((ActionMenuView) a2).setPresenter(this);
        return a2;
    }

    public View a(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        View actionView = menuItemImpl.getActionView();
        if (actionView == null || menuItemImpl.o()) {
            if (!(view instanceof ActionMenuItemView)) {
                view = null;
            }
            actionView = super.a(menuItemImpl, view, viewGroup);
        }
        actionView.setVisibility(menuItemImpl.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    public void a(MenuItemImpl menuItemImpl, MenuView.ItemView itemView) {
        itemView.initialize(menuItemImpl, 0);
        itemView.setItemInvoker((MenuBuilder.ItemInvoker) this.h);
    }

    public boolean a(int i2, MenuItemImpl menuItemImpl) {
        return menuItemImpl.j();
    }

    public void d(boolean z2) {
        super.d(z2);
        if (this.h != null) {
            ArrayList<MenuItemImpl> m2 = this.e != null ? this.e.m() : null;
            boolean z3 = false;
            if (this.j && m2 != null) {
                int size = m2.size();
                if (size == 1) {
                    z3 = !m2.get(0).isActionViewExpanded();
                } else if (size > 0) {
                    z3 = true;
                }
            }
            if (z3) {
                if (this.i == null) {
                    this.i = a(this.c);
                } else {
                    this.i.setTranslationY(0.0f);
                }
                ViewGroup viewGroup = (ViewGroup) this.i.getParent();
                if (viewGroup != this.h) {
                    if (viewGroup != null) {
                        viewGroup.removeView(this.i);
                    }
                    ActionMenuView actionMenuView = (ActionMenuView) this.h;
                    actionMenuView.addView(this.i, actionMenuView.generateOverflowButtonLayoutParams());
                }
            } else if (this.i != null && this.i.getParent() == this.h) {
                ((ViewGroup) this.h).removeView(this.i);
            }
            ((ActionMenuView) this.h).setOverflowReserved(this.j);
            if (!Build.W) {
                h().b(this.e);
            }
        }
    }

    public boolean a(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (subMenuBuilder2.t() != this.e) {
            subMenuBuilder2 = (SubMenuBuilder) subMenuBuilder2.t();
        }
        if (a(subMenuBuilder2.getItem()) == null) {
            if (this.i == null) {
                return false;
            }
            View view = this.i;
        }
        this.b = subMenuBuilder.getItem().getItemId();
        this.A = new ActionButtonSubMenu(subMenuBuilder);
        this.A.a((IBinder) null);
        super.a(subMenuBuilder);
        return true;
    }

    private View a(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.h;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if ((childAt instanceof MenuView.ItemView) && ((MenuView.ItemView) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public boolean a() {
        if (!this.j || c() || this.e == null || this.h == null || this.B != null) {
            return false;
        }
        this.B = new OpenOverflowRunnable(h());
        ((View) this.h).post(this.B);
        super.a((SubMenuBuilder) null);
        this.i.setSelected(true);
        return true;
    }

    private OverflowMenu h() {
        if (Build.W) {
            return new PopupOverflowMenu(this.d, this.e, this.i, true);
        }
        if (this.y == null) {
            this.y = new ListOverflowMenu();
        }
        return this.y;
    }

    /* access modifiers changed from: private */
    public MenuItemImpl i() {
        if (this.z == null) {
            this.z = new MenuItemImpl(this.e, 0, R.id.more, 0, 0, this.d.getString(R.string.v6_more), 0);
        }
        return this.z;
    }

    public boolean e(boolean z2) {
        this.i.setSelected(false);
        if (this.B != null && this.h != null) {
            ((View) this.h).removeCallbacks(this.B);
            this.B = null;
            return true;
        } else if (this.x == null) {
            return false;
        } else {
            boolean b2 = this.x.b();
            this.x.a(z2);
            return b2;
        }
    }

    public boolean f(boolean z2) {
        return e(z2);
    }

    public boolean b() {
        if (this.A == null) {
            return false;
        }
        this.A.a();
        return true;
    }

    public boolean c() {
        return this.x != null && this.x.b();
    }

    public boolean d() {
        return this.j;
    }

    public boolean e() {
        ArrayList<MenuItemImpl> j2 = this.e.j();
        int size = j2.size();
        int i2 = this.n;
        int i3 = 0;
        while (true) {
            boolean z2 = true;
            if (i3 < size && i2 > 0) {
                MenuItemImpl menuItemImpl = j2.get(i3);
                if (!menuItemImpl.k() && !menuItemImpl.l()) {
                    z2 = false;
                }
                menuItemImpl.d(z2);
                if (z2) {
                    i2--;
                }
                i3++;
            }
        }
        while (i3 < size) {
            j2.get(i3).d(false);
            i3++;
        }
        return true;
    }

    public void a(MenuBuilder menuBuilder, boolean z2) {
        f(true);
        super.a(menuBuilder, z2);
    }

    public Parcelable f() {
        SavedState savedState = new SavedState();
        savedState.f8295a = this.b;
        return savedState;
    }

    public void a(Parcelable parcelable) {
        MenuItem findItem;
        SavedState savedState = (SavedState) parcelable;
        if (savedState.f8295a > 0 && (findItem = this.e.findItem(savedState.f8295a)) != null) {
            a((SubMenuBuilder) findItem.getSubMenu());
        }
    }

    public void g(boolean z2) {
        if (z2) {
            super.a((SubMenuBuilder) null);
        } else {
            this.e.b(false);
        }
    }

    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public int f8295a;

        public int describeContents() {
            return 0;
        }

        SavedState() {
        }

        SavedState(Parcel parcel) {
            this.f8295a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f8295a);
        }
    }

    private class OverflowMenuButton extends Button implements ActionMenuView.ActionMenuChildView {
        public boolean needsDividerAfter() {
            return false;
        }

        public boolean needsDividerBefore() {
            return false;
        }

        public OverflowMenuButton(Context context) {
            super(context, (AttributeSet) null, ActionMenuPresenter.this.u);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
        }

        private boolean isVisible() {
            View view = this;
            while (view != null && view.getVisibility() == 0) {
                ViewParent parent = view.getParent();
                view = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            }
            return view == null;
        }

        public boolean performClick() {
            if (super.performClick() || !isVisible()) {
                return true;
            }
            if (ActionMenuPresenter.this.e != null) {
                ActionMenuPresenter.this.e.a(ActionMenuPresenter.this.e.q(), (MenuItem) ActionMenuPresenter.this.i());
            }
            playSoundEffect(0);
            if (isSelected()) {
                ActionMenuPresenter.this.e(true);
            } else {
                ActionMenuPresenter.this.a();
            }
            return true;
        }
    }

    private class ListOverflowMenu implements OverflowMenu {
        private ListMenuPresenter b;

        private ListOverflowMenu() {
        }

        private ListMenuPresenter c(MenuBuilder menuBuilder) {
            if (this.b == null) {
                this.b = new ListMenuPresenter(ActionMenuPresenter.this.d, ActionMenuPresenter.this.p, ActionMenuPresenter.this.o);
            }
            menuBuilder.a((MenuPresenter) this.b);
            return this.b;
        }

        public View a(MenuBuilder menuBuilder) {
            if (menuBuilder == null || menuBuilder.m().size() <= 0) {
                return null;
            }
            return (View) c(menuBuilder).a((ViewGroup) ActionMenuPresenter.this.h);
        }

        public boolean a() {
            return ((PhoneActionMenuView) ActionMenuPresenter.this.h).showOverflowMenu();
        }

        public boolean b() {
            return ((PhoneActionMenuView) ActionMenuPresenter.this.h).isOverflowMenuShowing();
        }

        public void a(boolean z) {
            ((PhoneActionMenuView) ActionMenuPresenter.this.h).hideOverflowMenu();
        }

        public void b(MenuBuilder menuBuilder) {
            ((PhoneActionMenuView) ActionMenuPresenter.this.h).setOverflowMenuView(a(menuBuilder));
        }
    }

    private class PopupOverflowMenu extends MenuPopupHelper implements OverflowMenu {
        public void b(MenuBuilder menuBuilder) {
        }

        public PopupOverflowMenu(Context context, MenuBuilder menuBuilder, View view, boolean z) {
            super(context, menuBuilder, view, z);
            a((MenuPresenter.Callback) ActionMenuPresenter.this.f8289a);
            b(R.layout.v6_overflow_popup_menu_item_layout);
        }

        public void onDismiss() {
            super.onDismiss();
            ActionMenuPresenter.this.e.close();
            OverflowMenu unused = ActionMenuPresenter.this.x = null;
        }
    }

    private class ActionButtonSubMenu extends MenuDialogHelper {
        public ActionButtonSubMenu(SubMenuBuilder subMenuBuilder) {
            super(subMenuBuilder);
            ActionMenuPresenter.this.a((MenuPresenter.Callback) ActionMenuPresenter.this.f8289a);
        }

        public void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
            ActionButtonSubMenu unused = ActionMenuPresenter.this.A = null;
            ActionMenuPresenter.this.b = 0;
        }
    }

    private class PopupPresenterCallback implements MenuPresenter.Callback {
        private PopupPresenterCallback() {
        }

        public boolean b(MenuBuilder menuBuilder) {
            if (menuBuilder == null) {
                return false;
            }
            ActionMenuPresenter.this.b = ((SubMenuBuilder) menuBuilder).getItem().getItemId();
            return false;
        }

        public void b(MenuBuilder menuBuilder, boolean z) {
            if (menuBuilder instanceof SubMenuBuilder) {
                menuBuilder.q().b(false);
            }
        }
    }

    private class OpenOverflowRunnable implements Runnable {
        private OverflowMenu b;

        public OpenOverflowRunnable(OverflowMenu overflowMenu) {
            this.b = overflowMenu;
        }

        public void run() {
            ActionMenuPresenter.this.e.g();
            View view = (View) ActionMenuPresenter.this.h;
            if (!(view == null || view.getWindowToken() == null || !this.b.a())) {
                OverflowMenu unused = ActionMenuPresenter.this.x = this.b;
            }
            OpenOverflowRunnable unused2 = ActionMenuPresenter.this.B = null;
        }
    }

    /* access modifiers changed from: protected */
    public View a(Context context) {
        return new OverflowMenuButton(context);
    }
}
