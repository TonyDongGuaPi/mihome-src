package com.xiaomi.smarthome.device.renderer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;

public class ZhilianDeviceRenderer extends DeviceRenderer {
    public void a(Context context, View view, Device device, boolean z, boolean z2) {
        if (view.getTag() == null || !(view.getTag() instanceof ViewHolder)) {
            view.setTag(new ViewHolder(view));
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        DeviceFactory.b(device.model, viewHolder.avatar);
        viewHolder.name.setTextColor(context.getResources().getColor(R.color.black_80_transparent));
        viewHolder.name.setText(device.getName());
        viewHolder.name.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        viewHolder.nameStatus.setVisibility(8);
        if (z) {
            viewHolder.mStatusImage.setVisibility(8);
            viewHolder.ckbEdit.setVisibility(0);
            viewHolder.ckbEdit.setChecked(z2);
        } else {
            viewHolder.ckbEdit.setVisibility(8);
        }
        a(context, device, viewHolder);
    }

    public void a(View view, Handler handler, Device device, Context context) {
        super.a(view, handler, device, context);
    }
}
