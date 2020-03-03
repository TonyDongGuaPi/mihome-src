package com.xiaomi.smarthome.miio.page.devicetag;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;

public class EditorCommonChildViewHolder extends DeviceTagChildViewHolder {

    /* renamed from: a  reason: collision with root package name */
    private SimpleDraweeView f19820a;
    private TextView c;

    public EditorCommonChildViewHolder(View view) {
        super(view);
        this.f19820a = (SimpleDraweeView) view.findViewById(R.id.icon);
        this.c = (TextView) view.findViewById(R.id.title);
    }

    public void a(final DeviceTagAdapter deviceTagAdapter, DeviceTagGroup deviceTagGroup, final int i, final int i2) {
        if (deviceTagGroup.w != null && i2 < deviceTagGroup.w.size()) {
            DeviceTagChild deviceTagChild = deviceTagGroup.w.get(i2);
            this.c.setText(deviceTagChild.d);
            this.c.setCompoundDrawablesWithIntrinsicBounds(deviceTagAdapter.f().getResources().getDrawable(i == 0 ? R.drawable.client_all_column_remove : R.drawable.client_all_column_add), (Drawable) null, (Drawable) null, (Drawable) null);
            if (deviceTagChild.g != null && deviceTagChild.g.size() > 0) {
                Device b = SmartHomeDeviceManager.a().b(deviceTagChild.g.iterator().next());
                if (b == null) {
                    b = SmartHomeDeviceManager.a().l(deviceTagChild.g.iterator().next());
                }
                if (b != null) {
                    DeviceFactory.b(b.model, this.f19820a);
                }
            }
            this.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (deviceTagAdapter instanceof DeviceTagEditorAdapter) {
                        if (i == 0) {
                            ((DeviceTagEditorAdapter) deviceTagAdapter).d(i2);
                        } else {
                            ((DeviceTagEditorAdapter) deviceTagAdapter).e(i2);
                        }
                        LocalBroadcastManager.getInstance(deviceTagAdapter.f()).sendBroadcast(new Intent("editor_changed_action"));
                    }
                }
            });
        }
    }
}
