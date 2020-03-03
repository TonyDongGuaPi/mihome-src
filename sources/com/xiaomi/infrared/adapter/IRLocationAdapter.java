package com.xiaomi.infrared.adapter;

import android.content.Context;
import com.xiaomi.infrared.bean.NameIdEntity;
import com.xiaomi.infrared.utils.CharacterParser;
import com.xiaomi.smarthome.R;
import java.util.List;

public class IRLocationAdapter extends BaseListAdapter<NameIdEntity> {
    public static final int c = 101;
    public static final int d = 102;
    public static final int e = 103;

    public int a(int i) {
        return R.layout.item_ir_select_brand;
    }

    public IRLocationAdapter(Context context, List<NameIdEntity> list, int i) {
        super(context, list);
    }

    public void a(ViewHolder viewHolder, NameIdEntity nameIdEntity, int i) {
        viewHolder.a((int) R.id.ir_select_tv_brand_text, nameIdEntity.c());
    }

    public int b(int i) {
        for (int i2 = 0; i2 < getCount(); i2++) {
            String upperCase = CharacterParser.a().c(((NameIdEntity) this.b.get(i2)).c()).substring(0, 1).toUpperCase();
            if ((upperCase.matches("[A-Z]") ? upperCase.toUpperCase() : "#").toUpperCase().charAt(0) == i) {
                return i2;
            }
        }
        return -1;
    }
}
