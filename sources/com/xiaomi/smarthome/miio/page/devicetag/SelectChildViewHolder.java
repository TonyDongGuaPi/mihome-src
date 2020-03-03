package com.xiaomi.smarthome.miio.page.devicetag;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class SelectChildViewHolder extends DeviceTagChildViewHolder {

    /* renamed from: a  reason: collision with root package name */
    public View f19828a;
    public View c;
    private CheckBox d;
    private TextView e;

    public SelectChildViewHolder(View view) {
        super(view);
        this.d = (CheckBox) view.findViewById(R.id.select_checkbox);
        this.e = (TextView) view.findViewById(R.id.tv_title);
        this.f19828a = view.findViewById(R.id.sort_icon);
        this.c = view.findViewById(R.id.divider_item);
    }

    public void a(DeviceTagAdapter deviceTagAdapter, DeviceTagGroup deviceTagGroup, int i, int i2) {
        if (deviceTagGroup.w != null && i2 < deviceTagGroup.w.size()) {
            DeviceTagChild deviceTagChild = deviceTagGroup.w.get(i2);
            this.c.setVisibility(i2 == deviceTagGroup.w.size() + -1 ? 8 : 0);
            final DeviceTagAdapter deviceTagAdapter2 = deviceTagAdapter;
            final DeviceTagChild deviceTagChild2 = deviceTagChild;
            final int i3 = i;
            final int i4 = i2;
            this.d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if ((deviceTagAdapter2 instanceof DeviceTagMgrAdapter) && z != deviceTagChild2.h) {
                        ((DeviceTagMgrAdapter) deviceTagAdapter2).a(i3, i4, z);
                    }
                }
            });
            this.e.setText(deviceTagChild.d);
            this.d.setChecked(deviceTagChild.h);
        }
    }
}
