package com.xiaomi.smarthome.device.renderer;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.shop.utils.ShopLauncher;

public class MiioVirtualDeviceRenderer extends DeviceRenderer {
    public void a(final Context context, ViewHolder viewHolder, final Device device) {
        a(context, device, viewHolder);
        if (viewHolder.f15412a == null || viewHolder.f15412a.getVisibility() != 0) {
            viewHolder.addBtn.setVisibility(0);
            viewHolder.addBtn.setText(R.string.bought_device_go);
            viewHolder.addBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ShopLauncher.a(context, Uri.parse("https://home.mi.com/shop/search?action=detail&keyword=" + device.getName() + "&source=" + SHApplication.getAppContext().getPackageName()).toString(), false);
                }
            });
        }
    }

    public void b(Context context, TextView textView, Device device, boolean z) {
        if (TextUtils.isEmpty(device.desc)) {
            textView.setText(R.string.exp_device_desc);
        } else {
            textView.setText(device.desc);
        }
        textView.setVisibility(0);
    }
}
