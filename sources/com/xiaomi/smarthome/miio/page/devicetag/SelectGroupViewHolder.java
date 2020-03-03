package com.xiaomi.smarthome.miio.page.devicetag;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.ExpandableItemIndicator;

public class SelectGroupViewHolder extends DeviceTagGroupViewHolder {

    /* renamed from: a  reason: collision with root package name */
    protected ExpandableItemIndicator f19830a;
    private CheckBox c;
    private TextView d;

    public SelectGroupViewHolder(View view) {
        super(view);
        this.c = (CheckBox) view.findViewById(R.id.select_checkbox);
        this.d = (TextView) view.findViewById(R.id.count);
        this.f19830a = (ExpandableItemIndicator) view.findViewById(R.id.btn_expand_indicator);
    }

    public void a(final DeviceTagAdapter deviceTagAdapter, final DeviceTagGroup deviceTagGroup, final int i) {
        int i2;
        int i3;
        this.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (deviceTagGroup.x != z && (deviceTagAdapter instanceof DeviceTagBatchAdapter)) {
                    ((DeviceTagBatchAdapter) deviceTagAdapter).c(i, z);
                }
            }
        });
        this.c.setText(deviceTagGroup.u);
        if (deviceTagGroup.w == null || deviceTagGroup.w.isEmpty()) {
            i2 = 0;
            i3 = 0;
        } else {
            i3 = 0;
            for (DeviceTagChild deviceTagChild : deviceTagGroup.w) {
                if (deviceTagChild.h) {
                    i3++;
                }
            }
            i2 = deviceTagGroup.w.size();
        }
        TextView textView = this.d;
        textView.setText(Operators.BRACKET_START_STR + i3 + "/" + i2 + Operators.BRACKET_END_STR);
        boolean z = true;
        this.c.setChecked(i3 > 0);
        if (this.f19830a != null) {
            int K_ = K_();
            if ((Integer.MIN_VALUE & K_) != 0) {
                boolean z2 = (K_ & 8) != 0;
                if ((K_ & 4) == 0) {
                    z = false;
                }
                this.f19830a.setExpandedState(z, z2);
            }
        }
    }
}
