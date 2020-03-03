package com.xiaomi.infrared.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.xiaomi.infrared.bean.IRBrandType;
import com.xiaomi.infrared.utils.CommUtil;
import com.xiaomi.smarthome.R;
import java.util.List;

public class IRMatchingBrandAdapter extends BaseListAdapter<IRBrandType> {
    private static final String c = "IRMatchingBrandAdapter";
    private final boolean d = CommUtil.a();

    public int a(int i) {
        return R.layout.item_ir_select_brand_title;
    }

    public IRMatchingBrandAdapter(Context context, List<IRBrandType> list) {
        super(context, list);
    }

    public void a(ViewHolder viewHolder, IRBrandType iRBrandType, int i) {
        String str;
        viewHolder.a((int) R.id.ir_select_tv_brand_text, iRBrandType.d());
        View a2 = viewHolder.a((int) R.id.brand_title_root);
        if (this.d) {
            str = iRBrandType.d();
        } else {
            str = iRBrandType.e();
        }
        char charAt = iRBrandType.f().charAt(0);
        Log.d(c, "convert: " + this.d + " mBrandName  " + str);
        if (b(charAt) == i) {
            a2.setVisibility(0);
            viewHolder.a((int) R.id.ir_select_tv_brand_position_text, iRBrandType.f());
            viewHolder.a((int) R.id.ir_select_tv_brand_text, str);
            return;
        }
        a2.setVisibility(8);
        viewHolder.a((int) R.id.ir_select_tv_brand_text, str);
    }

    public int b(int i) {
        for (int i2 = 0; i2 < getCount(); i2++) {
            if (((IRBrandType) this.b.get(i2)).f().toUpperCase().charAt(0) == i) {
                return i2;
            }
        }
        return -1;
    }
}
