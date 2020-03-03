package miuipub.widget;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.miuipub.internal.view.menu.MenuBuilder;
import com.miuipub.internal.view.menu.MenuPopupHelper;
import com.miuipub.internal.view.menu.MenuPresenter;
import com.miuipub.internal.view.menu.SubMenuBuilder;

public class PopupMenu {

    /* renamed from: a  reason: collision with root package name */
    private Context f3101a;
    private MenuBuilder b;
    private View c;
    private MenuPopupHelper d;
    private OnMenuItemClickListener e;
    private OnDismissListener f;

    public interface OnDismissListener {
        void a(PopupMenu popupMenu);
    }

    public interface OnMenuItemClickListener {
        boolean a(MenuItem menuItem);
    }

    public void a(SubMenuBuilder subMenuBuilder) {
    }

    public void b(MenuBuilder menuBuilder) {
    }

    public PopupMenu(Context context, View view) {
        this.f3101a = context;
        this.b = new MenuBuilder(context);
        this.b.a((MenuBuilder.Callback) new MenuBuilder.Callback() {
            public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
                return PopupMenu.this.a(menuBuilder, menuItem);
            }

            public void c(MenuBuilder menuBuilder) {
                PopupMenu.this.b(menuBuilder);
            }
        });
        this.c = view;
        this.d = new MenuPopupHelper(context, this.b, view);
        this.d.a((MenuPresenter.Callback) new MenuPresenter.Callback() {
            public void b(MenuBuilder menuBuilder, boolean z) {
                PopupMenu.this.a(menuBuilder, z);
            }

            public boolean b(MenuBuilder menuBuilder) {
                return PopupMenu.this.a(menuBuilder);
            }
        });
    }

    public Menu a() {
        return this.b;
    }

    public MenuInflater b() {
        return new MenuInflater(this.f3101a);
    }

    public void a(int i) {
        b().inflate(i, this.b);
    }

    public void c() {
        this.d.c();
    }

    public void d() {
        this.d.a(false);
    }

    public void a(OnMenuItemClickListener onMenuItemClickListener) {
        this.e = onMenuItemClickListener;
    }

    public void a(OnDismissListener onDismissListener) {
        this.f = onDismissListener;
    }

    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        if (this.e != null) {
            return this.e.a(menuItem);
        }
        return false;
    }

    public void a(MenuBuilder menuBuilder, boolean z) {
        if (this.f != null) {
            this.f.a(this);
        }
    }

    public boolean a(MenuBuilder menuBuilder) {
        if (menuBuilder == null) {
            return false;
        }
        if (!menuBuilder.hasVisibleItems()) {
            return true;
        }
        new MenuPopupHelper(this.f3101a, menuBuilder, this.c).c();
        return true;
    }
}
