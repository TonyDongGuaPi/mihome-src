package com.xiaomi.infrared.adapter;

import android.content.Context;
import com.xiaomi.infrared.bean.IRBrandType;
import com.xiaomi.infrared.utils.CommUtil;
import com.xiaomi.smarthome.R;
import java.util.List;

public class IRSearchAdapter extends BaseListAdapter<IRBrandType> {
    private final boolean c = CommUtil.a();

    public int a(int i) {
        return R.layout.item_ir_select_brand;
    }

    public IRSearchAdapter(Context context, List<IRBrandType> list) {
        super(context, list);
    }

    public void a(ViewHolder viewHolder, IRBrandType iRBrandType, int i) {
        String str;
        if (this.c) {
            str = iRBrandType.d();
        } else {
            str = iRBrandType.e();
        }
        viewHolder.a((int) R.id.ir_select_tv_brand_text, str);
    }
}
