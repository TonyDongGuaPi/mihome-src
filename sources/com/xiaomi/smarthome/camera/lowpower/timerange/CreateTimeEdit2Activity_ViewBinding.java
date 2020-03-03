package com.xiaomi.smarthome.camera.lowpower.timerange;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class CreateTimeEdit2Activity_ViewBinding implements Unbinder {
    private CreateTimeEdit2Activity target;
    private View view7f0b06d5;
    private View view7f0b06d9;
    private View view7f0b0e39;
    private View view7f0b0e44;
    private View view7f0b11a7;

    @UiThread
    public CreateTimeEdit2Activity_ViewBinding(CreateTimeEdit2Activity createTimeEdit2Activity) {
        this(createTimeEdit2Activity, createTimeEdit2Activity.getWindow().getDecorView());
    }

    @UiThread
    public CreateTimeEdit2Activity_ViewBinding(final CreateTimeEdit2Activity createTimeEdit2Activity, View view) {
        this.target = createTimeEdit2Activity;
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "method 'onClick'");
        this.view7f0b0e39 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createTimeEdit2Activity.onClick(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.module_a_3_right_btn, "method 'onClick'");
        this.view7f0b0e44 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createTimeEdit2Activity.onClick(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.repeat_setting, "method 'onClick'");
        this.view7f0b11a7 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createTimeEdit2Activity.onClick(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.execute_from_layout, "method 'onClick'");
        this.view7f0b06d5 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createTimeEdit2Activity.onClick(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.execute_to_layout, "method 'onClick'");
        this.view7f0b06d9 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createTimeEdit2Activity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        if (this.target != null) {
            this.target = null;
            this.view7f0b0e39.setOnClickListener((View.OnClickListener) null);
            this.view7f0b0e39 = null;
            this.view7f0b0e44.setOnClickListener((View.OnClickListener) null);
            this.view7f0b0e44 = null;
            this.view7f0b11a7.setOnClickListener((View.OnClickListener) null);
            this.view7f0b11a7 = null;
            this.view7f0b06d5.setOnClickListener((View.OnClickListener) null);
            this.view7f0b06d5 = null;
            this.view7f0b06d9.setOnClickListener((View.OnClickListener) null);
            this.view7f0b06d9 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
