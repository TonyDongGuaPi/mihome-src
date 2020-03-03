package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.mibi.common.component.CommonGridViewItem;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.component.DenominationEditText;

public class DenominationGridViewEditItem extends CommonGridViewItem {

    /* renamed from: a  reason: collision with root package name */
    private static final double f12461a = 1.15d;
    private TextView b;
    /* access modifiers changed from: private */
    public DenominationEditText c;
    private TextView d;
    private boolean e = false;
    private boolean f = false;

    public DenominationGridViewEditItem(Context context) {
        super(context);
    }

    public DenominationGridViewEditItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.b = (TextView) findViewById(R.id.item_unit);
        this.c = (DenominationEditText) findViewById(R.id.item_edit);
        this.d = (TextView) findViewById(R.id.other_value);
    }

    public void setDenomination(long j) {
        this.c.setDenomination(j);
    }

    public void setMaxMinDenomination(long j, long j2) {
        this.c.setMaxDenomination(j);
        this.c.setMinDenomination(j2);
    }

    public void setChecked(boolean z) {
        a(z || this.e);
        if (z) {
            a();
        } else {
            b();
        }
    }

    public void setUnit(String str) {
        this.b.setText(str);
        a(true);
    }

    public void setEditChangedListener(DenominationEditText.OnDenominationEditChangedListener onDenominationEditChangedListener) {
        this.c.setDenominationEditChangedListener(onDenominationEditChangedListener);
    }

    public void setUnitAlwaysVisible(boolean z) {
        this.e = z;
    }

    private void a(boolean z) {
        if (!z || (((double) getResources().getConfiguration().fontScale) > f12461a && !this.e)) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
        }
    }

    private void a() {
        this.d.setVisibility(8);
        this.c.setVisibility(0);
        this.c.post(new Runnable() {
            public void run() {
                DenominationGridViewEditItem.this.c.clearFocus();
                DenominationGridViewEditItem.this.c.requestFocus();
                Utils.a(DenominationGridViewEditItem.this.getContext(), (View) DenominationGridViewEditItem.this.c, true);
            }
        });
        this.c.clearFocus();
        this.c.requestFocus();
        if (this.f || this.c.getDenomination() == 0) {
            this.c.clearContent();
        }
        this.f = true;
    }

    private void b() {
        a(false);
        this.c.setVisibility(8);
        this.d.setVisibility(0);
        Utils.a(getContext(), (View) this.c, false);
    }
}
