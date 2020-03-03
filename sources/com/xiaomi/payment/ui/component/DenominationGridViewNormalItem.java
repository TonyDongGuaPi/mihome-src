package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.mibi.common.component.CommonGridViewItem;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.platform.R;

public class DenominationGridViewNormalItem extends CommonGridViewItem {

    /* renamed from: a  reason: collision with root package name */
    private static final double f12463a = 1.15d;
    private TextView b;
    private TextView c;
    private boolean d = false;

    public DenominationGridViewNormalItem(Context context) {
        super(context);
    }

    public DenominationGridViewNormalItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.b = (TextView) findViewById(R.id.item_unit);
        this.c = (TextView) findViewById(R.id.item_value);
    }

    public void setChecked(boolean z) {
        a(z || this.d);
        super.setChecked(z);
    }

    public void setDenomination(long j) {
        this.c.setText(Utils.a(j));
    }

    public void setUnit(String str) {
        this.b.setText(str);
        a(true);
    }

    public void setUnitAlwaysVisible(boolean z) {
        this.d = z;
    }

    private void a(boolean z) {
        if (!z || (((double) getResources().getConfiguration().fontScale) > f12463a && !this.d)) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
        }
    }
}
