package com.miuipub.internal.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.miuipub.internal.view.menu.MenuBuilder;
import com.miuipub.internal.view.menu.MenuView;
import miuipub.util.AttributeResolver;
import miuipub.v6.R;

public class ListMenuItemView extends LinearLayout implements MenuView.ItemView {

    /* renamed from: a  reason: collision with root package name */
    private MenuItemImpl f8302a;
    private ImageView b;
    private RadioButton c;
    private TextView d;
    private CheckBox e;
    private TextView f;
    private View g;
    private Drawable h;
    private int i;
    private Context j;
    private boolean k;
    private Context l;
    private LayoutInflater m;
    private boolean n;

    public boolean prefersCondensedTitle() {
        return false;
    }

    public void setItemInvoker(MenuBuilder.ItemInvoker itemInvoker) {
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        this.l = context;
        this.h = null;
        this.i = -1;
        this.k = false;
        this.j = context;
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        setBackgroundDrawable(this.h);
        this.d = (TextView) findViewById(R.id.title);
        if (this.i != -1) {
            this.d.setTextAppearance(this.j, this.i);
        }
        this.f = (TextView) findViewById(R.id.shortcut);
        this.g = getChildAt(0);
    }

    public void initialize(MenuItemImpl menuItemImpl, int i2) {
        this.f8302a = menuItemImpl;
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        setTitle(menuItemImpl.a((MenuView.ItemView) this));
        setCheckable(menuItemImpl.isCheckable());
        setShortcut(menuItemImpl.f(), menuItemImpl.d());
        setIcon(menuItemImpl.getIcon());
        setEnabled(menuItemImpl.isEnabled());
    }

    public void setForceShowIcon(boolean z) {
        this.n = z;
        this.k = z;
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence != null) {
            this.d.setText(charSequence);
            if (this.d.getVisibility() != 0) {
                this.d.setVisibility(0);
            }
        } else if (this.d.getVisibility() != 8) {
            this.d.setVisibility(8);
        }
    }

    public MenuItemImpl getItemData() {
        return this.f8302a;
    }

    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (z || this.c != null || this.e != null) {
            if (this.f8302a.g()) {
                if (this.c == null) {
                    b();
                }
                compoundButton2 = this.c;
                compoundButton = this.e;
            } else {
                if (this.e == null) {
                    c();
                }
                compoundButton2 = this.e;
                compoundButton = this.c;
            }
            int i2 = 0;
            if (z) {
                compoundButton2.setChecked(this.f8302a.isChecked());
                if (compoundButton2.getVisibility() != 0) {
                    compoundButton2.setVisibility(0);
                }
                if (!(compoundButton == null || compoundButton.getVisibility() == 8)) {
                    compoundButton.setVisibility(8);
                }
            } else {
                if (this.e != null) {
                    this.e.setVisibility(8);
                }
                if (this.c != null) {
                    this.c.setVisibility(8);
                }
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.g.getLayoutParams();
            if (!z || !this.f8302a.g()) {
                i2 = AttributeResolver.e(getContext(), 16843683);
            }
            marginLayoutParams.leftMargin = i2;
            this.g.setLayoutParams(marginLayoutParams);
            setActivated(this.f8302a.isChecked());
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.f8302a.g()) {
            if (this.c == null) {
                b();
            }
            compoundButton = this.c;
        } else {
            if (this.e == null) {
                c();
            }
            compoundButton = this.e;
        }
        compoundButton.setChecked(z);
        setActivated(z);
    }

    public void setShortcut(boolean z, char c2) {
        int i2 = (!z || !this.f8302a.f()) ? 8 : 0;
        if (i2 == 0) {
            this.f.setText(this.f8302a.e());
        }
        if (this.f.getVisibility() != i2) {
            this.f.setVisibility(i2);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z = this.f8302a.i() || this.n;
        if (!z && !this.k) {
            return;
        }
        if (this.b != null || drawable != null || this.k) {
            if (this.b == null) {
                a();
            }
            if (drawable != null || this.k) {
                ImageView imageView = this.b;
                if (!z) {
                    drawable = null;
                }
                imageView.setImageDrawable(drawable);
                if (this.b.getVisibility() != 0) {
                    this.b.setVisibility(0);
                    return;
                }
                return;
            }
            this.b.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (this.b != null && this.k) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.b.getLayoutParams();
            if (layoutParams.height > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = layoutParams.height;
            }
        }
        super.onMeasure(i2, i3);
    }

    private void a() {
        this.b = (ImageView) getInflater().inflate(R.layout.v6_list_menu_item_icon, this, false);
        addView(this.b, 0);
    }

    private void b() {
        this.c = (RadioButton) getInflater().inflate(R.layout.v6_list_menu_item_radio, this, false);
        addView(this.c, 0);
    }

    private void c() {
        this.e = (CheckBox) getInflater().inflate(R.layout.v6_list_menu_item_checkbox, this, false);
        addView(this.e);
    }

    public boolean showsIcon() {
        return this.n;
    }

    private LayoutInflater getInflater() {
        if (this.m == null) {
            this.m = LayoutInflater.from(this.l);
        }
        return this.m;
    }
}
