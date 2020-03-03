package com.xiaomi.smarthome.miio.page.devicetag;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.xiaomi.smarthome.R;

public class SelectIndentChildViewHolder extends DeviceTagChildViewHolder {

    /* renamed from: a  reason: collision with root package name */
    private CheckBox f19832a;

    public SelectIndentChildViewHolder(View view) {
        super(view);
        this.f19832a = (CheckBox) view.findViewById(R.id.select_checkbox);
    }

    public void a(DeviceTagAdapter deviceTagAdapter, DeviceTagGroup deviceTagGroup, int i, int i2) {
        if (deviceTagGroup.w != null && i2 < deviceTagGroup.w.size()) {
            DeviceTagChild deviceTagChild = deviceTagGroup.w.get(i2);
            final DeviceTagChild deviceTagChild2 = deviceTagChild;
            final DeviceTagAdapter deviceTagAdapter2 = deviceTagAdapter;
            final int i3 = i;
            final int i4 = i2;
            this.f19832a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z != deviceTagChild2.h && (deviceTagAdapter2 instanceof DeviceTagBatchAdapter)) {
                        ((DeviceTagBatchAdapter) deviceTagAdapter2).a(i3, i4, z);
                    }
                }
            });
            this.f19832a.setText(deviceTagChild.d);
            this.f19832a.setChecked(deviceTagChild.h);
        }
    }
}
