package com.mi.global.bbs.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.ImageCheckView;

public class SettingsItemView extends LinearLayout {
    @BindView(2131493956)
    ImageCheckView settingsItemCheckbox;
    @BindView(2131493957)
    TextView settingsItemTitle;

    public SettingsItemView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SettingsItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(0);
        LayoutInflater.from(context).inflate(R.layout.bbs_settings_item, this, true);
        ButterKnife.bind((View) this);
        if (Build.VERSION.SDK_INT >= 19) {
            this.settingsItemCheckbox.getDrawable().setAutoMirrored(true);
        }
    }

    public SettingsItemView(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public SettingsItemView(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i);
    }

    public void setTitle(String str) {
        this.settingsItemTitle.setText(str);
    }

    public boolean isChecked() {
        return this.settingsItemCheckbox.isChecked();
    }

    public void setChecked(boolean z) {
        this.settingsItemCheckbox.setChecked(z);
    }

    public void setOnCheckedChangedListener(ImageCheckView.OnCheckedChangedListener onCheckedChangedListener) {
        this.settingsItemCheckbox.setOnCheckedChangedListener(onCheckedChangedListener);
    }
}
