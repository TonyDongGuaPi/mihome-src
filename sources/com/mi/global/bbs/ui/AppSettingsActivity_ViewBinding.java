package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.AppSettingsItem;

public class AppSettingsActivity_ViewBinding implements Unbinder {
    private AppSettingsActivity target;
    private View view2131493954;
    private View view2131493955;
    private View view2131493958;
    private View view2131493959;
    private View view2131493960;
    private View view2131493961;

    @UiThread
    public AppSettingsActivity_ViewBinding(AppSettingsActivity appSettingsActivity) {
        this(appSettingsActivity, appSettingsActivity.getWindow().getDecorView());
    }

    @UiThread
    public AppSettingsActivity_ViewBinding(final AppSettingsActivity appSettingsActivity, View view) {
        this.target = appSettingsActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.settings_clear, "field 'settingsClear' and method 'onClick'");
        appSettingsActivity.settingsClear = (AppSettingsItem) Utils.castView(findRequiredView, R.id.settings_clear, "field 'settingsClear'", AppSettingsItem.class);
        this.view2131493954 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                appSettingsActivity.onClick(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.settings_log, "field 'settingsLog' and method 'onClick'");
        appSettingsActivity.settingsLog = (TextView) Utils.castView(findRequiredView2, R.id.settings_log, "field 'settingsLog'", TextView.class);
        this.view2131493958 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                appSettingsActivity.onClick(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.settings_notifications, "method 'onClick'");
        this.view2131493959 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                appSettingsActivity.onClick(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.settings_rate, "method 'onClick'");
        this.view2131493960 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                appSettingsActivity.onClick(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.settings_share, "method 'onClick'");
        this.view2131493961 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                appSettingsActivity.onClick(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.settings_feedback, "method 'onClick'");
        this.view2131493955 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                appSettingsActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        AppSettingsActivity appSettingsActivity = this.target;
        if (appSettingsActivity != null) {
            this.target = null;
            appSettingsActivity.settingsClear = null;
            appSettingsActivity.settingsLog = null;
            this.view2131493954.setOnClickListener((View.OnClickListener) null);
            this.view2131493954 = null;
            this.view2131493958.setOnClickListener((View.OnClickListener) null);
            this.view2131493958 = null;
            this.view2131493959.setOnClickListener((View.OnClickListener) null);
            this.view2131493959 = null;
            this.view2131493960.setOnClickListener((View.OnClickListener) null);
            this.view2131493960 = null;
            this.view2131493961.setOnClickListener((View.OnClickListener) null);
            this.view2131493961 = null;
            this.view2131493955.setOnClickListener((View.OnClickListener) null);
            this.view2131493955 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
