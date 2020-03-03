package com.xiaomi.smarthome.miio.page.devicetag;

import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class EditorNoneChildViewHolder extends DeviceTagChildViewHolder {

    /* renamed from: a  reason: collision with root package name */
    private TextView f19822a;

    public EditorNoneChildViewHolder(View view) {
        super(view);
        this.f19822a = (TextView) view.findViewById(R.id.title);
    }

    public void a(DeviceTagAdapter deviceTagAdapter, DeviceTagGroup deviceTagGroup, int i, int i2) {
        if (deviceTagGroup.w != null && i2 < deviceTagGroup.w.size()) {
            this.f19822a.setText(deviceTagGroup.w.get(i2).d);
        }
    }
}
