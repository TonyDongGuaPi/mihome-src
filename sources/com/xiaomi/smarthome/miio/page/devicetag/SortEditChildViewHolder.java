package com.xiaomi.smarthome.miio.page.devicetag;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;

public class SortEditChildViewHolder extends DeviceTagChildViewHolder {

    /* renamed from: a  reason: collision with root package name */
    public View f19837a;
    public View c;
    public View d;
    private TextView e;
    private View f;

    public SortEditChildViewHolder(View view) {
        super(view);
        this.e = (TextView) view.findViewById(R.id.title);
        this.f = view.findViewById(R.id.next_btn);
        this.f19837a = view.findViewById(R.id.sort_icon);
        this.c = view.findViewById(R.id.delete_btn);
        this.d = view.findViewById(R.id.divider_item);
    }

    public void a(final DeviceTagAdapter deviceTagAdapter, DeviceTagGroup deviceTagGroup, int i, int i2) {
        if (deviceTagGroup.w != null && i2 < deviceTagGroup.w.size()) {
            final DeviceTagChild deviceTagChild = deviceTagGroup.w.get(i2);
            this.e.setText(deviceTagChild.d);
            this.d.setVisibility(i2 == deviceTagGroup.w.size() + -1 ? 8 : 0);
            this.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    new MLAlertDialog.Builder(deviceTagAdapter.f()).a((CharSequence) String.format(deviceTagAdapter.f().getString(R.string.tag_remove_confirm), new Object[]{deviceTagChild.d})).a((int) R.string.tag_remove, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SmartHomeDeviceHelper.a().b().a(deviceTagChild.d);
                        }
                    }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a(deviceTagAdapter.f().getResources().getColor(R.color.std_dialog_button_red), -1).d();
                }
            });
            this.f.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(deviceTagAdapter.f(), DeviceTagEditorActivity.class);
                    intent.putExtra(DeviceTagEditorActivity.TAG_PARAM, deviceTagChild.d);
                    deviceTagAdapter.f().startActivity(intent);
                }
            });
        }
    }
}
