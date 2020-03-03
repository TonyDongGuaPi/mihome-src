package com.xiaomi.smarthome.miio.page.devicetag;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;

public class SortCommonChildViewHolder extends DeviceTagChildViewHolder {

    /* renamed from: a  reason: collision with root package name */
    public View f19834a;
    public View c;
    private TextView d;
    private View e;

    public SortCommonChildViewHolder(View view) {
        super(view);
        this.d = (TextView) view.findViewById(R.id.title);
        this.e = view.findViewById(R.id.next_btn);
        this.f19834a = view.findViewById(R.id.sort_icon);
        this.c = view.findViewById(R.id.divider_item);
    }

    public void a(final DeviceTagAdapter deviceTagAdapter, DeviceTagGroup deviceTagGroup, int i, int i2) {
        if (deviceTagGroup.w != null && i2 < deviceTagGroup.w.size()) {
            final DeviceTagChild deviceTagChild = deviceTagGroup.w.get(i2);
            this.c.setVisibility(i2 == deviceTagGroup.w.size() + -1 ? 8 : 0);
            this.d.setText(deviceTagChild.d);
            if (deviceTagGroup.t == 2) {
                this.e.setVisibility(0);
                this.e.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        final DeviceTagInterface<Device> b2 = SmartHomeDeviceHelper.a().b();
                        final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) LayoutInflater.from(deviceTagAdapter.f()).inflate(R.layout.client_remark_input_view, (ViewGroup) null);
                        MLAlertDialog b3 = new MLAlertDialog.Builder(deviceTagAdapter.f()).a((int) R.string.change_back_name).b((View) clientRemarkInputView).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                b2.a(deviceTagChild.e, clientRemarkInputView.getEditText().getText().toString());
                            }
                        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b();
                        String b4 = b2.b(deviceTagChild.e);
                        clientRemarkInputView.initialize((ClientRemarkInputView.RenameInterface) null, b3, b2.b(deviceTagChild.e, b4), b4, false);
                        b3.show();
                    }
                });
                return;
            }
            this.e.setVisibility(8);
        }
    }
}
