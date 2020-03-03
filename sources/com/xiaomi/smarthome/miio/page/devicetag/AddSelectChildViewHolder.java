package com.xiaomi.smarthome.miio.page.devicetag;

import android.content.DialogInterface;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class AddSelectChildViewHolder extends DeviceTagChildViewHolder {

    /* renamed from: a  reason: collision with root package name */
    public View f19788a;
    private TextView c;
    private RadioButton d;

    public AddSelectChildViewHolder(View view) {
        super(view);
        this.c = (TextView) view.findViewById(R.id.title);
        this.d = (RadioButton) view.findViewById(R.id.select_radio);
        this.f19788a = view.findViewById(R.id.divider_item);
    }

    public void a(final DeviceTagAdapter deviceTagAdapter, DeviceTagGroup deviceTagGroup, int i, int i2) {
        if (deviceTagGroup.w != null && i2 < deviceTagGroup.w.size()) {
            final DeviceTagChild deviceTagChild = deviceTagGroup.w.get(i2);
            this.c.setText(deviceTagChild.d);
            this.d.setChecked(deviceTagChild.h);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f19788a.getLayoutParams();
            if (i2 == deviceTagGroup.w.size() - 1) {
                layoutParams.leftMargin = 0;
            } else {
                layoutParams.leftMargin = DisplayUtils.a(deviceTagAdapter.f(), 13.0f);
            }
            this.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    new MLAlertDialog.Builder(deviceTagAdapter.f()).b((CharSequence) String.format(deviceTagAdapter.f().getString(R.string.tag_remove_confirm), new Object[]{deviceTagChild.d})).a((int) R.string.tag_remove, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SmartHomeDeviceHelper.a().b().a(deviceTagChild.d);
                            if (deviceTagAdapter instanceof DeviceTagAddAdapter) {
                                ((DeviceTagAddAdapter) deviceTagAdapter).c(deviceTagChild.d);
                            }
                        }
                    }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).d();
                }
            });
            this.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (deviceTagAdapter instanceof DeviceTagAddAdapter) {
                        ((DeviceTagAddAdapter) deviceTagAdapter).b(deviceTagChild.d);
                    }
                }
            });
        }
    }
}
