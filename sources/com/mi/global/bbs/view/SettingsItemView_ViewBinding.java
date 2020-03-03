package com.mi.global.bbs.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class SettingsItemView_ViewBinding implements Unbinder {
    private SettingsItemView target;

    @UiThread
    public SettingsItemView_ViewBinding(SettingsItemView settingsItemView) {
        this(settingsItemView, settingsItemView);
    }

    @UiThread
    public SettingsItemView_ViewBinding(SettingsItemView settingsItemView, View view) {
        this.target = settingsItemView;
        settingsItemView.settingsItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.settings_item_title, "field 'settingsItemTitle'", TextView.class);
        settingsItemView.settingsItemCheckbox = (ImageCheckView) Utils.findRequiredViewAsType(view, R.id.settings_item_checkbox, "field 'settingsItemCheckbox'", ImageCheckView.class);
    }

    @CallSuper
    public void unbind() {
        SettingsItemView settingsItemView = this.target;
        if (settingsItemView != null) {
            this.target = null;
            settingsItemView.settingsItemTitle = null;
            settingsItemView.settingsItemCheckbox = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
