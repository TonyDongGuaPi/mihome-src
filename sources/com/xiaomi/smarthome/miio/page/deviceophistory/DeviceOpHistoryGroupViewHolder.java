package com.xiaomi.smarthome.miio.page.deviceophistory;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class DeviceOpHistoryGroupViewHolder extends DeviceOpHistoryBaseViewHolder {
    public TextView b;
    public TextView c;
    public TextView d;

    public DeviceOpHistoryGroupViewHolder(View view) {
        super(view);
        this.b = (TextView) view.findViewById(R.id.day);
        this.c = (TextView) view.findViewById(R.id.month);
        this.d = (TextView) view.findViewById(R.id.weekday);
    }

    public void a(Context context, DeviceOpHistoryGroupData deviceOpHistoryGroupData) {
        TextView textView = this.b;
        textView.setText("" + deviceOpHistoryGroupData.f19739a);
        TextView textView2 = this.c;
        textView2.setText("" + deviceOpHistoryGroupData.b + context.getString(R.string.month));
        this.d.setText(deviceOpHistoryGroupData.c);
    }
}
