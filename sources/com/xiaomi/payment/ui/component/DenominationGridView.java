package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import com.mibi.common.component.CommonGridView;
import com.xiaomi.payment.ui.adapter.DenominationGridAdatper;
import com.xiaomi.payment.ui.component.DenominationEditText;
import java.util.ArrayList;

public class DenominationGridView extends CommonGridView<Long> {
    protected DenominationGridAdatper mAdatper;

    public DenominationGridView(Context context) {
        this(context, (AttributeSet) null);
    }

    public DenominationGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAdatper = new DenominationGridAdatper(context);
        setAdapter((ListAdapter) this.mAdatper);
    }

    public void setData(long[] jArr) {
        ArrayList arrayList = new ArrayList();
        for (long valueOf : jArr) {
            arrayList.add(Long.valueOf(valueOf));
        }
        setData(arrayList);
    }

    public void setData(long[] jArr, long j) {
        setData(jArr);
        setItemSelected(Long.valueOf(j));
    }

    public void setUnit(String str) {
        this.mAdatper.a(str);
    }

    public void setUnitAlwaysVisible(boolean z) {
        this.mAdatper.a(z);
    }

    public void setOnEditChangedListener(DenominationEditText.OnDenominationEditChangedListener onDenominationEditChangedListener) {
        this.mAdatper.a(onDenominationEditChangedListener);
    }

    public void setEditable(boolean z) {
        this.mAdatper.b(z);
    }

    public void setEditItemMaxMinDenomination(long j, long j2) {
        this.mAdatper.a(j, j2);
    }
}
