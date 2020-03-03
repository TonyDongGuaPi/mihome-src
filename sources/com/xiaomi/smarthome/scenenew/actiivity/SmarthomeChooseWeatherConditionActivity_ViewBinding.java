package com.xiaomi.smarthome.scenenew.actiivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class SmarthomeChooseWeatherConditionActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SmarthomeChooseWeatherConditionActivity f21836a;

    @UiThread
    public SmarthomeChooseWeatherConditionActivity_ViewBinding(SmarthomeChooseWeatherConditionActivity smarthomeChooseWeatherConditionActivity) {
        this(smarthomeChooseWeatherConditionActivity, smarthomeChooseWeatherConditionActivity.getWindow().getDecorView());
    }

    @UiThread
    public SmarthomeChooseWeatherConditionActivity_ViewBinding(SmarthomeChooseWeatherConditionActivity smarthomeChooseWeatherConditionActivity, View view) {
        this.f21836a = smarthomeChooseWeatherConditionActivity;
        smarthomeChooseWeatherConditionActivity.mListView = (ListView) Utils.findRequiredViewAsType(view, R.id.list_view, "field 'mListView'", ListView.class);
        smarthomeChooseWeatherConditionActivity.mReturnIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mReturnIV'", ImageView.class);
        smarthomeChooseWeatherConditionActivity.mTitleTV = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleTV'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        SmarthomeChooseWeatherConditionActivity smarthomeChooseWeatherConditionActivity = this.f21836a;
        if (smarthomeChooseWeatherConditionActivity != null) {
            this.f21836a = null;
            smarthomeChooseWeatherConditionActivity.mListView = null;
            smarthomeChooseWeatherConditionActivity.mReturnIV = null;
            smarthomeChooseWeatherConditionActivity.mTitleTV = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
