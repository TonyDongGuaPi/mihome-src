package com.mibi.common.component;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.mibi.common.data.CheckableArrayAdapter;
import java.util.ArrayList;

public class CommonGridView<Type> extends GridView {

    /* renamed from: a  reason: collision with root package name */
    private CommonGridView<Type>.WrapperCommonGridAdapter f7480a;

    public CommonGridView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CommonGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        setSelector(17170445);
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter == null || !CheckableArrayAdapter.class.isInstance(listAdapter)) {
            throw new IllegalArgumentException("adapter should be an instance of CheckableArrayAdapter");
        }
        this.f7480a = new WrapperCommonGridAdapter((CheckableArrayAdapter) listAdapter);
        super.setAdapter(this.f7480a);
    }

    public void setData(ArrayList<Type> arrayList) {
        a();
        this.f7480a.a(arrayList);
    }

    public void setData(ArrayList<Type> arrayList, Type type) {
        setData(arrayList);
        setItemSelected(type);
    }

    public int indexOf(Type type) {
        return this.f7480a.a(type);
    }

    public void setItemSelected(Type type) {
        a();
        this.f7480a.b(type);
    }

    public void setPositionSelected(int i) {
        a();
        this.f7480a.a(i);
    }

    public void clearSelection() {
        a();
        this.f7480a.a();
    }

    public Type getSelectedItem() {
        a();
        return this.f7480a.b();
    }

    public int getSelectedPosition() {
        a();
        return this.f7480a.c();
    }

    public void setOnItemSelectedListener(CheckableArrayAdapter.OnItemSelectedListener<Type> onItemSelectedListener) {
        a();
        this.f7480a.a(onItemSelectedListener);
    }

    private void a() {
        if (this.f7480a == null) {
            throw new IllegalStateException("you should set an adapter first!");
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(16777215, Integer.MIN_VALUE));
    }

    private class WrapperCommonGridAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        protected CheckableArrayAdapter<Type> f7481a;

        public WrapperCommonGridAdapter(CheckableArrayAdapter<Type> checkableArrayAdapter) {
            this.f7481a = checkableArrayAdapter;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = this.f7481a.getView(i, view, viewGroup);
            view2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    WrapperCommonGridAdapter.this.a(a(view));
                }

                private int a(View view) {
                    int firstVisiblePosition = CommonGridView.this.getFirstVisiblePosition();
                    int lastVisiblePosition = CommonGridView.this.getLastVisiblePosition();
                    for (int i = firstVisiblePosition; i <= lastVisiblePosition; i++) {
                        if (CommonGridView.this.getChildAt(i - firstVisiblePosition) == view) {
                            return i;
                        }
                    }
                    return -1;
                }
            });
            return view2;
        }

        public void notifyDataSetChanged() {
            this.f7481a.notifyDataSetChanged();
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            this.f7481a.registerDataSetObserver(dataSetObserver);
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            this.f7481a.unregisterDataSetObserver(dataSetObserver);
        }

        public int getCount() {
            return this.f7481a.getCount();
        }

        public Object getItem(int i) {
            return this.f7481a.getItem(i);
        }

        public long getItemId(int i) {
            return this.f7481a.getItemId(i);
        }

        public int getItemViewType(int i) {
            return this.f7481a.getItemViewType(i);
        }

        public int getViewTypeCount() {
            return this.f7481a.getViewTypeCount();
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            return this.f7481a.getDropDownView(i, view, viewGroup);
        }

        public boolean isEmpty() {
            return this.f7481a.isEmpty();
        }

        public boolean hasStableIds() {
            return this.f7481a.hasStableIds();
        }

        public void notifyDataSetInvalidated() {
            this.f7481a.notifyDataSetInvalidated();
        }

        public boolean areAllItemsEnabled() {
            return this.f7481a.areAllItemsEnabled();
        }

        public boolean isEnabled(int i) {
            return this.f7481a.isEnabled(i);
        }

        public void a(ArrayList<Type> arrayList) {
            this.f7481a.a(arrayList);
        }

        public int a(Type type) {
            return this.f7481a.a(type);
        }

        public void b(Type type) {
            this.f7481a.b(type);
        }

        public void a(int i) {
            this.f7481a.a(i);
        }

        public void a() {
            this.f7481a.b();
        }

        public Type b() {
            return this.f7481a.c();
        }

        public int c() {
            return this.f7481a.d();
        }

        public void a(CheckableArrayAdapter.OnItemSelectedListener<Type> onItemSelectedListener) {
            this.f7481a.a(onItemSelectedListener);
        }
    }
}
