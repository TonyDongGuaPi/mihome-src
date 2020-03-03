package com.xiaomi.smarthome.miio.page.msgcentersetting;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.TimePicker;

public class InterruptPushTimerSettingActiviy_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private InterruptPushTimerSettingActiviy f19856a;
    private View b;
    private View c;
    private View d;
    private View e;

    @UiThread
    public InterruptPushTimerSettingActiviy_ViewBinding(InterruptPushTimerSettingActiviy interruptPushTimerSettingActiviy) {
        this(interruptPushTimerSettingActiviy, interruptPushTimerSettingActiviy.getWindow().getDecorView());
    }

    @UiThread
    public InterruptPushTimerSettingActiviy_ViewBinding(final InterruptPushTimerSettingActiviy interruptPushTimerSettingActiviy, View view) {
        this.f19856a = interruptPushTimerSettingActiviy;
        interruptPushTimerSettingActiviy.mExecuteFromPicker = (TimePicker) Utils.findRequiredViewAsType(view, R.id.execute_from_picker, "field 'mExecuteFromPicker'", TimePicker.class);
        interruptPushTimerSettingActiviy.mExecuteToPicker = (TimePicker) Utils.findRequiredViewAsType(view, R.id.execute_to_picker, "field 'mExecuteToPicker'", TimePicker.class);
        interruptPushTimerSettingActiviy.mExecuteFrom = (TextView) Utils.findRequiredViewAsType(view, R.id.execute_from_hint, "field 'mExecuteFrom'", TextView.class);
        interruptPushTimerSettingActiviy.mExecuteTo = (TextView) Utils.findRequiredViewAsType(view, R.id.execute_to_hint, "field 'mExecuteTo'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_right_text_btn, "field 'mConfirmView' and method 'onClick'");
        interruptPushTimerSettingActiviy.mConfirmView = (TextView) Utils.castView(findRequiredView, R.id.module_a_3_right_text_btn, "field 'mConfirmView'", TextView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                interruptPushTimerSettingActiviy.onClick(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "method 'onClick'");
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                interruptPushTimerSettingActiviy.onClick(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.execute_from_layout, "method 'onClick'");
        this.d = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                interruptPushTimerSettingActiviy.onClick(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.execute_to_layout, "method 'onClick'");
        this.e = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                interruptPushTimerSettingActiviy.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        InterruptPushTimerSettingActiviy interruptPushTimerSettingActiviy = this.f19856a;
        if (interruptPushTimerSettingActiviy != null) {
            this.f19856a = null;
            interruptPushTimerSettingActiviy.mExecuteFromPicker = null;
            interruptPushTimerSettingActiviy.mExecuteToPicker = null;
            interruptPushTimerSettingActiviy.mExecuteFrom = null;
            interruptPushTimerSettingActiviy.mExecuteTo = null;
            interruptPushTimerSettingActiviy.mConfirmView = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            this.c.setOnClickListener((View.OnClickListener) null);
            this.c = null;
            this.d.setOnClickListener((View.OnClickListener) null);
            this.d = null;
            this.e.setOnClickListener((View.OnClickListener) null);
            this.e = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
