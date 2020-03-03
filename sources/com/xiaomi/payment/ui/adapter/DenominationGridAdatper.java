package com.xiaomi.payment.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mibi.common.component.CommonGridViewItem;
import com.mibi.common.data.CheckableArrayAdapter;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.component.DenominationEditText;
import com.xiaomi.payment.ui.component.DenominationGridViewEditItem;
import com.xiaomi.payment.ui.component.DenominationGridViewNormalItem;
import java.util.ArrayList;

public class DenominationGridAdatper extends CheckableArrayAdapter<Long> {
    private static final int e = 0;
    private static final int f = 1;
    private static final int g = 2;
    private final int d = 3;
    private LayoutInflater h;
    private String i;
    private DenominationEditText.OnDenominationEditChangedListener j;
    private long k;
    private long l;
    private long m;
    private boolean n;
    private boolean o = false;
    private int p;
    private ArrayList<Long> q;
    private ArrayList<Integer> r;

    public int getViewTypeCount() {
        return 3;
    }

    public DenominationGridAdatper(Context context) {
        super(context);
        this.h = LayoutInflater.from(context);
        this.n = false;
        this.p = context.getResources().getInteger(R.integer.mibi_num_grid_columns);
    }

    public void a(String str) {
        this.i = str;
    }

    public void a(boolean z) {
        this.o = z;
    }

    public void b(boolean z) {
        this.n = z;
    }

    public void a(long j2) {
        if (this.n) {
            this.k = j2;
        } else {
            this.k = 0;
        }
    }

    public void a(long j2, long j3) {
        this.l = j2;
        this.m = j3;
    }

    public void a(DenominationEditText.OnDenominationEditChangedListener onDenominationEditChangedListener) {
        this.j = onDenominationEditChangedListener;
    }

    private int e() {
        return this.r.indexOf(2);
    }

    public void a(ArrayList<Long> arrayList) {
        a(arrayList, false);
    }

    public void a(ArrayList<Long> arrayList, boolean z) {
        int i2;
        if (arrayList != null) {
            if (this.q == null) {
                this.q = new ArrayList<>();
            } else if (!z) {
                this.q.clear();
            }
            this.q.addAll(arrayList);
            if (this.b == null) {
                this.b = new ArrayList();
            } else {
                this.b.clear();
            }
            if (this.r == null) {
                this.r = new ArrayList<>();
            } else {
                this.r.clear();
            }
            for (int i3 = 0; i3 < this.q.size(); i3++) {
                this.b.add(this.q.get(i3));
                this.r.add(0);
            }
            int size = this.q.size() % this.p;
            int size2 = this.q.size() / this.p;
            if (size == 0) {
                i2 = this.q.size();
            } else {
                i2 = this.p * (size2 + 1);
            }
            for (int i4 = 0; i4 < i2 - this.q.size(); i4++) {
                this.b.add(0L);
                this.r.add(1);
            }
            if (this.n) {
                this.b.set(this.b.size() - 1, 0L);
                this.r.set(this.r.size() - 1, 2);
            }
            b();
        }
    }

    public int getItemViewType(int i2) {
        if (i2 < 0 || i2 > this.r.size()) {
            return 1;
        }
        return this.r.get(i2).intValue();
    }

    public View a(Context context, int i2, Long l2, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i2);
        if (itemViewType == 2) {
            DenominationGridViewEditItem denominationGridViewEditItem = (DenominationGridViewEditItem) this.h.inflate(R.layout.mibi_grid_item_edit, viewGroup, false);
            denominationGridViewEditItem.setUnitAlwaysVisible(this.o);
            denominationGridViewEditItem.setUnit(this.i);
            denominationGridViewEditItem.setMaxMinDenomination(this.l, this.m);
            denominationGridViewEditItem.setDenomination(this.k);
            return denominationGridViewEditItem;
        } else if (itemViewType == 0) {
            DenominationGridViewNormalItem denominationGridViewNormalItem = (DenominationGridViewNormalItem) this.h.inflate(R.layout.mibi_grid_item_normal, viewGroup, false);
            denominationGridViewNormalItem.setUnitAlwaysVisible(this.o);
            denominationGridViewNormalItem.setUnit(this.i);
            return denominationGridViewNormalItem;
        } else {
            CommonGridViewItem commonGridViewItem = (CommonGridViewItem) this.h.inflate(R.layout.mibi_grid_item_empty, viewGroup, false);
            commonGridViewItem.setEnabled(false);
            return commonGridViewItem;
        }
    }

    public void a(View view, int i2, Long l2, boolean z) {
        int itemViewType = getItemViewType(i2);
        if (itemViewType == 0) {
            DenominationGridViewNormalItem denominationGridViewNormalItem = (DenominationGridViewNormalItem) view;
            denominationGridViewNormalItem.setDenomination(l2.longValue());
            denominationGridViewNormalItem.setChecked(z);
        } else if (itemViewType == 2) {
            DenominationGridViewEditItem denominationGridViewEditItem = (DenominationGridViewEditItem) view;
            denominationGridViewEditItem.setChecked(z);
            denominationGridViewEditItem.setEditChangedListener(this.j);
        }
    }

    /* renamed from: a */
    public void b(Long l2) {
        int a2 = a(l2);
        if (-1 == a2) {
            a2 = e();
            a(l2.longValue());
        }
        a(a2);
    }

    public void a(int i2) {
        int itemViewType = getItemViewType(i2);
        if (itemViewType != 1) {
            if (itemViewType == 2) {
                this.c = i2;
                notifyDataSetChanged();
                return;
            }
            super.a(i2);
        }
    }
}
