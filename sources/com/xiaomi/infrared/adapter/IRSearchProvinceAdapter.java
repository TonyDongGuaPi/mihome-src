package com.xiaomi.infrared.adapter;

import android.content.Context;
import com.xiaomi.infrared.bean.NameIdEntity;
import com.xiaomi.smarthome.R;
import java.util.List;

public class IRSearchProvinceAdapter extends BaseListAdapter<NameIdEntity> {
    public int a(int i) {
        return R.layout.item_ir_select_brand;
    }

    public IRSearchProvinceAdapter(Context context, List<NameIdEntity> list) {
        super(context, list);
    }

    public void a(ViewHolder viewHolder, NameIdEntity nameIdEntity, int i) {
        viewHolder.a((int) R.id.ir_select_tv_brand_text, nameIdEntity.c());
    }
}
