package com.xiaomi.smarthome.miio.page.devicetag;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class CommonGroupViewHolder extends DeviceTagGroupViewHolder {

    /* renamed from: a  reason: collision with root package name */
    private TextView f19794a;
    private TextView c;

    public CommonGroupViewHolder(View view) {
        super(view);
        this.f19794a = (TextView) view.findViewById(R.id.title_left);
        this.c = (TextView) view.findViewById(R.id.title_right);
    }

    public void a(DeviceTagAdapter deviceTagAdapter, DeviceTagGroup deviceTagGroup, int i) {
        this.f19794a.setText(deviceTagGroup.u);
        if (!TextUtils.isEmpty(deviceTagGroup.v)) {
            this.c.setVisibility(0);
            this.c.setText(deviceTagGroup.v);
            return;
        }
        this.c.setVisibility(4);
    }
}
