package com.xiaomi.smarthome.miio.page.deviceophistory;

import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class DeviceOpHistoryChildViewHolder extends DeviceOpHistoryBaseViewHolder {
    public TextView b;
    public TextView c;
    public TextView d;
    public TextView e;

    public DeviceOpHistoryChildViewHolder(View view) {
        super(view);
        this.b = (TextView) view.findViewById(R.id.hourminute);
        this.c = (TextView) view.findViewById(R.id.action);
        this.d = (TextView) view.findViewById(R.id.result);
        this.e = (TextView) view.findViewById(R.id.source);
    }

    public void a(DeviceOpHistoryChildData deviceOpHistoryChildData) {
        this.b.setText(deviceOpHistoryChildData.b);
        this.c.setText(deviceOpHistoryChildData.c);
        this.d.setText(deviceOpHistoryChildData.d);
        this.e.setText(deviceOpHistoryChildData.e);
    }
}
