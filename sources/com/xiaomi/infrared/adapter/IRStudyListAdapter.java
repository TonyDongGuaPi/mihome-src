package com.xiaomi.infrared.adapter;

import android.content.Context;
import android.widget.TextView;
import com.xiaomi.infrared.bean.IRKeyValue;
import com.xiaomi.infrared.bean.IRType;
import com.xiaomi.infrared.utils.CommUtil;
import com.xiaomi.smarthome.R;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IRStudyListAdapter extends BaseRecyclerAdapter<IRKeyValue> {
    protected Map<String, IRKeyValue> c = new LinkedHashMap();
    private IRType d;

    public int c(int i) {
        return R.layout.study_key_item;
    }

    public IRStudyListAdapter(Context context, List<IRKeyValue> list, Map<String, IRKeyValue> map, IRType iRType) {
        super(context, list);
        this.c = map;
        this.d = iRType;
    }

    public void a(ViewHolder viewHolder, IRKeyValue iRKeyValue, int i) {
        TextView textView = (TextView) viewHolder.a((int) R.id.ir_study_text_btn);
        viewHolder.a((int) R.id.ir_study_text_btn, CommUtil.a(iRKeyValue));
        if (this.d != IRType.Unknown) {
            if (this.c.containsKey(iRKeyValue.e())) {
                textView.setSelected(true);
                textView.setTextColor(this.f10218a.getResources().getColor(R.color.ir_study_btn_color));
                return;
            }
            textView.setSelected(false);
            textView.setTextColor(this.f10218a.getResources().getColor(R.color.main_theme_text_color));
        }
    }
}
