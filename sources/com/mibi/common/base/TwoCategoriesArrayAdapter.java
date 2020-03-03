package com.mibi.common.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class TwoCategoriesArrayAdapter<G, I> extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7476a = 2;
    public static final int b = 0;
    public static final int c = 1;
    protected Context d;
    protected ArrayList<Integer> e = new ArrayList<>();
    protected ArrayList<TwoCategoriesArrayAdapter<G, I>.TwoCategoriesGroup> f = new ArrayList<>();
    protected int g;
    protected int h;
    private ArrayList<Boolean> i = new ArrayList<>();

    public abstract View a(Context context, ViewGroup viewGroup);

    public abstract void a(View view, int i2, G g2);

    public abstract View b(Context context, ViewGroup viewGroup);

    public abstract void b(View view, int i2, I i3);

    public long getItemId(int i2) {
        return (long) i2;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public TwoCategoriesArrayAdapter(Context context) {
        this.d = context;
    }

    public void a() {
        if (this.e != null) {
            this.e.clear();
        }
        if (this.f != null) {
            this.f.clear();
        }
        this.i.clear();
    }

    public void a(ArrayList<TwoCategoriesArrayAdapter<G, I>.TwoCategoriesGroup> arrayList) {
        if (arrayList != null) {
            this.f.addAll(arrayList);
            Iterator<TwoCategoriesArrayAdapter<G, I>.TwoCategoriesGroup> it = arrayList.iterator();
            while (it.hasNext()) {
                TwoCategoriesGroup next = it.next();
                this.e.add(Integer.valueOf(next.a()));
                b(next);
            }
            notifyDataSetChanged();
        }
    }

    public void a(TwoCategoriesArrayAdapter<G, I>.TwoCategoriesGroup twoCategoriesGroup) {
        if (twoCategoriesGroup != null) {
            this.f.add(twoCategoriesGroup);
            this.e.add(Integer.valueOf(twoCategoriesGroup.a()));
            b(twoCategoriesGroup);
            notifyDataSetChanged();
        }
    }

    private void b(TwoCategoriesArrayAdapter<G, I>.TwoCategoriesGroup twoCategoriesGroup) {
        int a2 = twoCategoriesGroup.a();
        if (twoCategoriesGroup.b() != null) {
            this.i.add(true);
            a2--;
        }
        for (int i2 = 0; i2 < a2; i2++) {
            this.i.add(false);
        }
    }

    private void a(int i2) {
        int i3 = i2 + 1;
        int i4 = 0;
        while (i4 < this.e.size()) {
            int intValue = i3 - this.e.get(i4).intValue();
            if (intValue > 0) {
                i4++;
                i3 = intValue;
            } else {
                this.h = i4;
                this.g = i3 - 1;
                return;
            }
        }
    }

    public int getItemViewType(int i2) {
        a(i2);
        return this.i.get(i2).booleanValue() ^ true ? 1 : 0;
    }

    public Object getItem(int i2) {
        if (i2 < 0 || i2 >= getCount()) {
            throw new IllegalStateException("couldn't get view at this position " + i2);
        }
        switch (getItemViewType(i2)) {
            case 0:
                return this.f.get(this.h).b();
            case 1:
                return this.f.get(this.h).a(this.g);
            default:
                return null;
        }
    }

    public int getCount() {
        return this.i.size();
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (this.f == null || this.e == null) {
            throw new IllegalStateException("this should only be called when the data is valid");
        } else if (i2 < 0 || i2 >= getCount()) {
            throw new IllegalStateException("couldn't get view at this position " + i2);
        } else {
            switch (getItemViewType(i2)) {
                case 0:
                    Object b2 = this.f.get(this.h).b();
                    if (view == null) {
                        view = a(this.d, viewGroup);
                    }
                    a(view, i2, b2);
                    return view;
                case 1:
                    Object a2 = this.f.get(this.h).a(this.g);
                    if (view == null) {
                        view = b(this.d, viewGroup);
                    }
                    b(view, i2, a2);
                    return view;
                default:
                    return null;
            }
        }
    }

    public class TwoCategoriesGroup implements IListViewGroup<G, I> {

        /* renamed from: a  reason: collision with root package name */
        G f7477a;
        ArrayList<I> b = new ArrayList<>();
        boolean c = false;

        public TwoCategoriesGroup() {
        }

        public int a() {
            int size = this.b.size();
            return this.f7477a == null ? size : size + 1;
        }

        public I a(int i) {
            if (this.f7477a != null) {
                i--;
            }
            return this.b.get(i);
        }

        public G b() {
            return this.f7477a;
        }

        public void a(ArrayList<I> arrayList) {
            if (arrayList != null) {
                this.b.addAll(arrayList);
            }
        }

        public void b(I i) {
            if (i != null) {
                this.b.add(i);
            }
        }

        public void a(G g) {
            this.f7477a = g;
        }
    }
}
