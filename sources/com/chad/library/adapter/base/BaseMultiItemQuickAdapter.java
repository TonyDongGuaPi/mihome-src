package com.chad.library.adapter.base;

import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import java.util.List;

public abstract class BaseMultiItemQuickAdapter<T extends MultiItemEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f5118a = -404;
    private static final int c = -255;
    private SparseIntArray b;

    public BaseMultiItemQuickAdapter(List<T> list) {
        super(list);
    }

    /* access modifiers changed from: protected */
    public int a(int i) {
        MultiItemEntity multiItemEntity = (MultiItemEntity) this.s.get(i);
        return multiItemEntity != null ? multiItemEntity.a() : c;
    }

    /* access modifiers changed from: protected */
    public void b(@LayoutRes int i) {
        a((int) c, i);
    }

    /* access modifiers changed from: protected */
    public K a(ViewGroup viewGroup, int i) {
        return c(viewGroup, p(i));
    }

    private int p(int i) {
        return this.b.get(i, f5118a);
    }

    /* access modifiers changed from: protected */
    public void a(int i, @LayoutRes int i2) {
        if (this.b == null) {
            this.b = new SparseIntArray();
        }
        this.b.put(i, i2);
    }

    public void c(@IntRange(from = 0) int i) {
        if (this.s != null && i >= 0 && i < this.s.size()) {
            MultiItemEntity multiItemEntity = (MultiItemEntity) this.s.get(i);
            if (multiItemEntity instanceof IExpandable) {
                a((IExpandable) multiItemEntity, i);
            }
            a(multiItemEntity);
            super.c(i);
        }
    }

    /* access modifiers changed from: protected */
    public void a(IExpandable iExpandable, int i) {
        List b2;
        if (iExpandable.a() && (b2 = iExpandable.b()) != null && b2.size() != 0) {
            int size = b2.size();
            for (int i2 = 0; i2 < size; i2++) {
                c(i + 1);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(T t) {
        int c2 = c(t);
        if (c2 >= 0) {
            ((IExpandable) this.s.get(c2)).b().remove(t);
        }
    }
}
