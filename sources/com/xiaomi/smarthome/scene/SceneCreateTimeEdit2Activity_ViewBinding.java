package com.xiaomi.smarthome.scene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.library.common.widget.TimePicker;

public class SceneCreateTimeEdit2Activity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SceneCreateTimeEdit2Activity f21300a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;

    @UiThread
    public SceneCreateTimeEdit2Activity_ViewBinding(SceneCreateTimeEdit2Activity sceneCreateTimeEdit2Activity) {
        this(sceneCreateTimeEdit2Activity, sceneCreateTimeEdit2Activity.getWindow().getDecorView());
    }

    @UiThread
    public SceneCreateTimeEdit2Activity_ViewBinding(final SceneCreateTimeEdit2Activity sceneCreateTimeEdit2Activity, View view) {
        this.f21300a = sceneCreateTimeEdit2Activity;
        sceneCreateTimeEdit2Activity.mTitleView = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleView'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_right_btn, "field 'mConfirmView' and method 'onClick'");
        sceneCreateTimeEdit2Activity.mConfirmView = (ImageView) Utils.castView(findRequiredView, R.id.module_a_3_right_btn, "field 'mConfirmView'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sceneCreateTimeEdit2Activity.onClick(view);
            }
        });
        sceneCreateTimeEdit2Activity.mRepeatDayHint = (TextView) Utils.findRequiredViewAsType(view, R.id.smarthome_scene_timer_day_hint, "field 'mRepeatDayHint'", TextView.class);
        sceneCreateTimeEdit2Activity.mIsAlldaySwitch = (SwitchButton) Utils.findRequiredViewAsType(view, R.id.all_day_switch, "field 'mIsAlldaySwitch'", SwitchButton.class);
        sceneCreateTimeEdit2Activity.mExecuteFromTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.execute_from_title, "field 'mExecuteFromTitle'", TextView.class);
        sceneCreateTimeEdit2Activity.mExecuteFrom = (TextView) Utils.findRequiredViewAsType(view, R.id.execute_from_hint, "field 'mExecuteFrom'", TextView.class);
        sceneCreateTimeEdit2Activity.mExecuteTo = (TextView) Utils.findRequiredViewAsType(view, R.id.execute_to_hint, "field 'mExecuteTo'", TextView.class);
        sceneCreateTimeEdit2Activity.mExecuteToTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.execute_to_title, "field 'mExecuteToTitle'", TextView.class);
        sceneCreateTimeEdit2Activity.mExecuteFromPicker = (TimePicker) Utils.findRequiredViewAsType(view, R.id.execute_from_picker, "field 'mExecuteFromPicker'", TimePicker.class);
        sceneCreateTimeEdit2Activity.mExecuteToPicker = (TimePicker) Utils.findRequiredViewAsType(view, R.id.execute_to_picker, "field 'mExecuteToPicker'", TimePicker.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "method 'onClick'");
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sceneCreateTimeEdit2Activity.onClick(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.repeat_setting, "method 'onClick'");
        this.d = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sceneCreateTimeEdit2Activity.onClick(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.execute_from_layout, "method 'onClick'");
        this.e = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sceneCreateTimeEdit2Activity.onClick(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.execute_to_layout, "method 'onClick'");
        this.f = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sceneCreateTimeEdit2Activity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        SceneCreateTimeEdit2Activity sceneCreateTimeEdit2Activity = this.f21300a;
        if (sceneCreateTimeEdit2Activity != null) {
            this.f21300a = null;
            sceneCreateTimeEdit2Activity.mTitleView = null;
            sceneCreateTimeEdit2Activity.mConfirmView = null;
            sceneCreateTimeEdit2Activity.mRepeatDayHint = null;
            sceneCreateTimeEdit2Activity.mIsAlldaySwitch = null;
            sceneCreateTimeEdit2Activity.mExecuteFromTitle = null;
            sceneCreateTimeEdit2Activity.mExecuteFrom = null;
            sceneCreateTimeEdit2Activity.mExecuteTo = null;
            sceneCreateTimeEdit2Activity.mExecuteToTitle = null;
            sceneCreateTimeEdit2Activity.mExecuteFromPicker = null;
            sceneCreateTimeEdit2Activity.mExecuteToPicker = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            this.c.setOnClickListener((View.OnClickListener) null);
            this.c = null;
            this.d.setOnClickListener((View.OnClickListener) null);
            this.d = null;
            this.e.setOnClickListener((View.OnClickListener) null);
            this.e = null;
            this.f.setOnClickListener((View.OnClickListener) null);
            this.f = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
