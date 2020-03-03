package com.chad.library.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import java.util.List;

public abstract class BaseSectionQuickAdapter<T extends SectionEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    protected static final int b = 1092;

    /* renamed from: a  reason: collision with root package name */
    protected int f5127a;

    /* access modifiers changed from: protected */
    public abstract void a(K k, T t);

    public BaseSectionQuickAdapter(int i, int i2, List<T> list) {
        super(i, list);
        this.f5127a = i2;
    }

    /* access modifiers changed from: protected */
    public int a(int i) {
        if (((SectionEntity) this.s.get(i)).isHeader) {
            return b;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public K a(ViewGroup viewGroup, int i) {
        if (i == b) {
            return a(b(this.f5127a, viewGroup));
        }
        return super.a(viewGroup, i);
    }

    /* access modifiers changed from: protected */
    public boolean i(int i) {
        return super.i(i) || i == b;
    }

    /* renamed from: a */
    public void onBindViewHolder(K k, int i) {
        if (k.getItemViewType() != b) {
            super.onBindViewHolder(k, i);
            return;
        }
        g((RecyclerView.ViewHolder) k);
        a(k, (SectionEntity) h(i - t()));
    }
}
