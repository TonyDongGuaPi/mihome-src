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

public class SmarthomeChooseWeatherConditionDetailActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SmarthomeChooseWeatherConditionDetailActivity f21842a;

    @UiThread
    public SmarthomeChooseWeatherConditionDetailActivity_ViewBinding(SmarthomeChooseWeatherConditionDetailActivity smarthomeChooseWeatherConditionDetailActivity) {
        this(smarthomeChooseWeatherConditionDetailActivity, smarthomeChooseWeatherConditionDetailActivity.getWindow().getDecorView());
    }

    @UiThread
    public SmarthomeChooseWeatherConditionDetailActivity_ViewBinding(SmarthomeChooseWeatherConditionDetailActivity smarthomeChooseWeatherConditionDetailActivity, View view) {
        this.f21842a = smarthomeChooseWeatherConditionDetailActivity;
        smarthomeChooseWeatherConditionDetailActivity.mListView = (ListView) Utils.findRequiredViewAsType(view, R.id.list_view, "field 'mListView'", ListView.class);
        smarthomeChooseWeatherConditionDetailActivity.mReturnIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mReturnIV'", ImageView.class);
        smarthomeChooseWeatherConditionDetailActivity.mTitleTV = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleTV'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        SmarthomeChooseWeatherConditionDetailActivity smarthomeChooseWeatherConditionDetailActivity = this.f21842a;
        if (smarthomeChooseWeatherConditionDetailActivity != null) {
            this.f21842a = null;
            smarthomeChooseWeatherConditionDetailActivity.mListView = null;
            smarthomeChooseWeatherConditionDetailActivity.mReturnIV = null;
            smarthomeChooseWeatherConditionDetailActivity.mTitleTV = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
