package com.xiaomi.infrared.adapter;

import android.content.Context;
import com.xiaomi.infrared.bean.IRSTBData;
import com.xiaomi.smarthome.R;
import java.util.List;

public class IRSetTopBranchAdapter extends BaseListAdapter<IRSTBData> {
    public int a(int i) {
        return R.layout.item_ir_select_stb_center;
    }

    public IRSetTopBranchAdapter(Context context, List<IRSTBData> list) {
        super(context, list);
    }

    public void a(ViewHolder viewHolder, IRSTBData iRSTBData, int i) {
        viewHolder.a((int) R.id.ir_select_stb_brand_text, iRSTBData.f());
    }
}
