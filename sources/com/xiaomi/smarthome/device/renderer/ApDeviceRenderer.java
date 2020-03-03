package com.xiaomi.smarthome.device.renderer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.ApDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.library.common.util.SpanParser;

public class ApDeviceRenderer extends DeviceRenderer {
    public void a(Context context, View view, Device device, boolean z, boolean z2) {
        if (view.getTag() == null || !(view.getTag() instanceof ViewHolder)) {
            view.setTag(new ViewHolder(view));
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        ApDevice apDevice = (ApDevice) device;
        DeviceFactory.b(device.model, viewHolder.avatar);
        if (device.isOnline) {
            viewHolder.name.setTextColor(context.getResources().getColor(R.color.black_80_transparent));
        } else {
            viewHolder.name.setTextColor(context.getResources().getColor(R.color.black_30_transparent));
        }
        viewHolder.name.setText(device.getName());
        viewHolder.name.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        TextView textView = viewHolder.nameStatus;
        boolean z3 = device.isOnline;
        textView.setTextColor(SpanParser.f18733a);
        viewHolder.nameStatus.setText(device.getStatusDescription(context));
        if (z) {
            viewHolder.mStatusImage.setVisibility(8);
            viewHolder.ckbEdit.setVisibility(0);
            viewHolder.ckbEdit.setChecked(z2);
            return;
        }
        viewHolder.ckbEdit.setVisibility(8);
    }
}
