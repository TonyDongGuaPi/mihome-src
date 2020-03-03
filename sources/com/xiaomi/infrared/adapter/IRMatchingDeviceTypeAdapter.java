package com.xiaomi.infrared.adapter;

import android.content.Context;
import com.xiaomi.infrared.bean.IRMatchingDeviceTypeData;
import com.xiaomi.smarthome.R;
import java.util.List;

public class IRMatchingDeviceTypeAdapter extends BaseListAdapter<IRMatchingDeviceTypeData> {
    public int a(int i) {
        return R.layout.item_ir_matching_device;
    }

    public IRMatchingDeviceTypeAdapter(Context context, List<IRMatchingDeviceTypeData> list) {
        super(context, list);
    }

    public void a(ViewHolder viewHolder, IRMatchingDeviceTypeData iRMatchingDeviceTypeData, int i) {
        viewHolder.a((int) R.id.ir_matching_device_type_text, this.f10217a.getResources().getString(iRMatchingDeviceTypeData.b()));
        viewHolder.a((int) R.id.ir_matching_device_type_icon, iRMatchingDeviceTypeData.c());
    }
}
