package com.biubiu.kit.core;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class KitBaseAdapter extends RecyclerView.Adapter {

    /* renamed from: a  reason: collision with root package name */
    private IKitFactory f4797a;
    private List<Object> b;
    private SparseArray<String> c;
    private int d = 9;
    private OnItemClickListener e;

    public int a(int i) {
        return i;
    }

    public KitBaseAdapter(Context context, List<Object> list) {
        this.b = list;
        this.c = new SparseArray<>();
        this.f4797a = KitFactory.b(context.getApplicationInfo().processName);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AbsKit absKit = (AbsKit) this.f4797a.create(this.c.get(i));
        absKit.a(this);
        return new Holder(absKit, viewGroup);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof Holder) {
            ((Holder) viewHolder).a().a(a(i), this.b.get(a(i)));
        }
    }

    public int getItemViewType(int i) {
        String a2 = KitFactory.a(this.b.get(a(i)).getClass());
        int indexOfValue = this.c.indexOfValue(a2);
        if (indexOfValue != -1) {
            return this.c.keyAt(indexOfValue);
        }
        this.c.put(this.d, a2);
        int i2 = this.d;
        this.d++;
        return i2;
    }

    public int getItemCount() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    public void a(OnItemClickListener onItemClickListener) {
        this.e = onItemClickListener;
    }

    /* access modifiers changed from: package-private */
    public OnItemClickListener a() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.e != null;
    }

    private static class Holder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        private AbsKit f4798a;

        private Holder(View view) {
            super(view);
        }

        Holder(AbsKit absKit, ViewGroup viewGroup) {
            this(absKit.a(viewGroup));
            this.f4798a = absKit;
        }

        /* access modifiers changed from: package-private */
        public AbsKit a() {
            return this.f4798a;
        }
    }
}
