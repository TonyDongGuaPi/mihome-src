package com.mi.global.bbs.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.ImageCheckView;
import java.util.ArrayList;
import java.util.List;

public class SettingsItemContainerView extends LinearLayout {
    private List<SettingsItemView> childrenViews;
    private LinearLayout.LayoutParams defaultLayoutParams;
    /* access modifiers changed from: private */
    public OnItemCheckedChangedListener onItemCheckedChangedListener;

    public interface OnItemCheckedChangedListener {
        void onItemCheckedChangedListener(int i, boolean z);
    }

    public SettingsItemContainerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SettingsItemContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.defaultLayoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.childrenViews = new ArrayList();
        setOrientation(1);
    }

    public void addSettingsItem(String str, boolean z, final int i, LinearLayout.LayoutParams layoutParams) {
        SettingsItemView settingsItemView = new SettingsItemView(getContext());
        settingsItemView.setTitle(str);
        settingsItemView.setChecked(z);
        settingsItemView.setOnCheckedChangedListener(new ImageCheckView.OnCheckedChangedListener() {
            public void onCheckedChanged(ImageCheckView imageCheckView, boolean z) {
                if (SettingsItemContainerView.this.onItemCheckedChangedListener != null) {
                    SettingsItemContainerView.this.onItemCheckedChangedListener.onItemCheckedChangedListener(i, z);
                }
            }
        });
        this.childrenViews.add(settingsItemView);
        addView(settingsItemView, layoutParams);
    }

    private void addDivider() {
        View view = new View(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.divide_height));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.settings_margin_lr);
        layoutParams.rightMargin = dimensionPixelSize;
        layoutParams.leftMargin = dimensionPixelSize;
        view.setBackgroundColor(getResources().getColor(R.color.divide_color));
        addView(view, layoutParams);
    }

    public void setOnItemCheckedChangedListener(OnItemCheckedChangedListener onItemCheckedChangedListener2) {
        this.onItemCheckedChangedListener = onItemCheckedChangedListener2;
    }

    public void createItems(String[] strArr) {
        if (strArr != null) {
            for (int i = 0; i < strArr.length; i++) {
                addSettingsItem(strArr[i], false, i, this.defaultLayoutParams);
                if (i != strArr.length - 1) {
                    addDivider();
                }
            }
        }
    }

    public void setItemState(int i, boolean z) {
        this.childrenViews.get(i).setChecked(z);
    }
}
