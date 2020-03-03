package com.mi.global.shop.feature.search;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.libra.Color;
import com.mi.global.shop.base.MiShopStatInterface;
import java.util.List;

public class DropDownMenu extends LinearLayout {
    private FrameLayout containerView;
    private int current_tab_position;
    private int dividerColor;
    private int maskColor;
    private View maskView;
    private float menuHeighPercent;
    private int menuSelectedIcon;
    private int menuTextSize;
    private int menuUnselectedIcon;
    private FrameLayout popupMenuViews;
    private LinearLayout tabMenuView;
    private int textSelectedColor;
    private int textUnselectedColor;

    public DropDownMenu(Context context) {
        super(context, (AttributeSet) null);
        this.current_tab_position = -1;
        this.dividerColor = Color.d;
        this.textSelectedColor = -7795579;
        this.textUnselectedColor = -15658735;
        this.maskColor = -2004318072;
        this.menuTextSize = 14;
        this.menuHeighPercent = 0.5f;
    }

    public DropDownMenu(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DropDownMenu(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.current_tab_position = -1;
        this.dividerColor = Color.d;
        this.textSelectedColor = -7795579;
        this.textUnselectedColor = -15658735;
        this.maskColor = -2004318072;
        this.menuTextSize = 14;
        this.menuHeighPercent = 0.5f;
        setOrientation(1);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DropDownMenu);
        int color = obtainStyledAttributes.getColor(R.styleable.DropDownMenu_ddunderlineColor, Color.d);
        this.dividerColor = obtainStyledAttributes.getColor(R.styleable.DropDownMenu_dddividerColor, this.dividerColor);
        this.textSelectedColor = obtainStyledAttributes.getColor(R.styleable.DropDownMenu_ddtextSelectedColor, this.textSelectedColor);
        this.textUnselectedColor = obtainStyledAttributes.getColor(R.styleable.DropDownMenu_ddtextUnselectedColor, this.textUnselectedColor);
        int color2 = obtainStyledAttributes.getColor(R.styleable.DropDownMenu_ddmenuBackgroundColor, -1);
        this.maskColor = obtainStyledAttributes.getColor(R.styleable.DropDownMenu_ddmaskColor, this.maskColor);
        this.menuTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DropDownMenu_ddmenuTextSize, this.menuTextSize);
        this.menuSelectedIcon = obtainStyledAttributes.getResourceId(R.styleable.DropDownMenu_ddmenuSelectedIcon, this.menuSelectedIcon);
        this.menuUnselectedIcon = obtainStyledAttributes.getResourceId(R.styleable.DropDownMenu_ddmenuUnselectedIcon, this.menuUnselectedIcon);
        this.menuHeighPercent = obtainStyledAttributes.getFloat(R.styleable.DropDownMenu_ddmenuMenuHeightPercent, this.menuHeighPercent);
        obtainStyledAttributes.recycle();
        this.tabMenuView = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.tabMenuView.setOrientation(0);
        this.tabMenuView.setBackgroundColor(color2);
        this.tabMenuView.setLayoutParams(layoutParams);
        addView(this.tabMenuView, 0);
        View view = new View(getContext());
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, dpTpPx(1.0f)));
        view.setBackgroundColor(color);
        addView(view, 1);
        this.containerView = new FrameLayout(context);
        this.containerView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.containerView, 2);
    }

    public void setDropDownMenu(@NonNull List<String> list, @NonNull List<View> list2, @NonNull View view) {
        if (list.size() == list2.size()) {
            for (int i = 0; i < list.size(); i++) {
                addTab(list, i);
            }
            this.containerView.addView(view, 0);
            this.maskView = new View(getContext());
            this.maskView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            this.maskView.setBackgroundColor(this.maskColor);
            this.maskView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DropDownMenu.this.closeMenu();
                }
            });
            this.containerView.addView(this.maskView, 1);
            this.maskView.setVisibility(8);
            if (this.containerView.getChildAt(2) != null) {
                this.containerView.removeViewAt(2);
            }
            this.popupMenuViews = new FrameLayout(getContext());
            this.popupMenuViews.setLayoutParams(new FrameLayout.LayoutParams(-1, (int) (((float) DeviceUtils.getScreenSize(getContext()).y) * this.menuHeighPercent)));
            this.popupMenuViews.setVisibility(8);
            this.containerView.addView(this.popupMenuViews, 2);
            for (int i2 = 0; i2 < list2.size(); i2++) {
                list2.get(i2).setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                this.popupMenuViews.addView(list2.get(i2), i2);
            }
            return;
        }
        throw new IllegalArgumentException("params not match, tabTexts.size() should be equal popupViews.size()");
    }

    private void addTab(@NonNull List<String> list, final int i) {
        final TextView textView = new TextView(getContext());
        textView.setSingleLine();
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setGravity(17);
        textView.setTextSize(0, (float) this.menuTextSize);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, dpTpPx(50.0f));
        layoutParams.leftMargin = dpTpPx(20.0f);
        textView.setTextColor(this.textUnselectedColor);
        textView.setCompoundDrawablePadding(dpTpPx(5.0f));
        textView.setMaxWidth(dpTpPx(120.0f));
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getResources().getDrawable(this.menuUnselectedIcon), (Drawable) null);
        textView.setText(list.get(i));
        textView.setPadding(dpTpPx(5.0f), dpTpPx(12.0f), dpTpPx(12.0f), dpTpPx(12.0f));
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DropDownMenu.this.switchMenu(textView);
                String str = "category_click";
                switch (i) {
                    case 0:
                        str = "category_click";
                        break;
                    case 1:
                        str = "popularity_click";
                        break;
                    case 2:
                        str = "filter_click";
                        break;
                }
                MiShopStatInterface.a(str, "search_landing");
            }
        });
        this.tabMenuView.addView(textView, layoutParams);
        if (i < list.size() - 1) {
            View view = new View(getContext());
            view.setLayoutParams(new LinearLayout.LayoutParams(dpTpPx(0.5f), -1));
            view.setBackgroundColor(this.dividerColor);
            this.tabMenuView.addView(view);
        }
    }

    public void setTabText(String str) {
        if (this.current_tab_position != -1) {
            ((TextView) this.tabMenuView.getChildAt(this.current_tab_position)).setText(str);
        }
    }

    public void setTabClickable(boolean z) {
        for (int i = 0; i < this.tabMenuView.getChildCount(); i += 2) {
            this.tabMenuView.getChildAt(i).setClickable(z);
        }
    }

    public void closeMenu() {
        if (this.current_tab_position != -1) {
            ((TextView) this.tabMenuView.getChildAt(this.current_tab_position)).setTextColor(this.textUnselectedColor);
            ((TextView) this.tabMenuView.getChildAt(this.current_tab_position)).setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getResources().getDrawable(this.menuUnselectedIcon), (Drawable) null);
            this.popupMenuViews.setVisibility(8);
            this.popupMenuViews.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_out));
            this.maskView.setVisibility(8);
            this.maskView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_mask_out));
            this.current_tab_position = -1;
        }
    }

    public boolean isShowing() {
        return this.current_tab_position != -1;
    }

    /* access modifiers changed from: private */
    public void switchMenu(View view) {
        System.out.println(this.current_tab_position);
        for (int i = 0; i < this.tabMenuView.getChildCount(); i += 2) {
            if (view != this.tabMenuView.getChildAt(i)) {
                ((TextView) this.tabMenuView.getChildAt(i)).setTextColor(this.textUnselectedColor);
                ((TextView) this.tabMenuView.getChildAt(i)).setCompoundDrawablePadding(dpTpPx(5.0f));
                ((TextView) this.tabMenuView.getChildAt(i)).setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getResources().getDrawable(this.menuUnselectedIcon), (Drawable) null);
                this.popupMenuViews.getChildAt(i / 2).setVisibility(8);
            } else if (this.current_tab_position == i) {
                closeMenu();
            } else {
                if (this.current_tab_position == -1) {
                    this.popupMenuViews.setVisibility(0);
                    this.popupMenuViews.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_in));
                    this.maskView.setVisibility(0);
                    this.maskView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_mask_in));
                    this.popupMenuViews.getChildAt(i / 2).setVisibility(0);
                } else {
                    this.popupMenuViews.getChildAt(i / 2).setVisibility(0);
                }
                this.current_tab_position = i;
                ((TextView) this.tabMenuView.getChildAt(i)).setTextColor(this.textSelectedColor);
                ((TextView) this.tabMenuView.getChildAt(i)).setCompoundDrawablePadding(dpTpPx(5.0f));
                ((TextView) this.tabMenuView.getChildAt(i)).setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getResources().getDrawable(this.menuSelectedIcon), (Drawable) null);
            }
        }
    }

    public int dpTpPx(float f) {
        double applyDimension = (double) TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
        Double.isNaN(applyDimension);
        return (int) (applyDimension + 0.5d);
    }
}
