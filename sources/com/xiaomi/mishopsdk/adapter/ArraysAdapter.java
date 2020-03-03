package com.xiaomi.mishopsdk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import com.xiaomi.mishopsdk.utils.ExposeHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class ArraysAdapter<T> extends BaseAdapter {
    private Context mContext;
    private ArraysAdapter<T>.ArrayFilter mFilter;
    private LayoutInflater mInflater;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private boolean mNotifyOnChange = true;
    /* access modifiers changed from: private */
    public List<T> mObjects = new ArrayList();
    /* access modifiers changed from: private */
    public ArrayList<T> mOriginalValues;

    /* access modifiers changed from: protected */
    public abstract void bindView(View view, int i, T t);

    public long getItemId(int i) {
        return (long) i;
    }

    /* access modifiers changed from: protected */
    public abstract View newView(int i, T t, ViewGroup viewGroup);

    /* access modifiers changed from: protected */
    public boolean uploadExposeLog() {
        return true;
    }

    public ArraysAdapter(Context context) {
        this.mContext = context;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public void add(T t) {
        synchronized (this.mLock) {
            if (this.mOriginalValues != null) {
                this.mOriginalValues.add(t);
            } else {
                this.mObjects.add(t);
            }
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void addAll(Collection<? extends T> collection) {
        synchronized (this.mLock) {
            if (this.mOriginalValues != null) {
                this.mOriginalValues.addAll(collection);
            } else {
                this.mObjects.addAll(collection);
            }
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void addAll(T... tArr) {
        synchronized (this.mLock) {
            if (this.mOriginalValues != null) {
                Collections.addAll(this.mOriginalValues, tArr);
            } else {
                Collections.addAll(this.mObjects, tArr);
            }
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void insert(T t, int i) {
        synchronized (this.mLock) {
            if (this.mOriginalValues != null) {
                this.mOriginalValues.add(i, t);
            } else {
                this.mObjects.add(i, t);
            }
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void remove(T t) {
        synchronized (this.mLock) {
            if (this.mOriginalValues != null) {
                this.mOriginalValues.remove(t);
            } else {
                this.mObjects.remove(t);
            }
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void clear() {
        synchronized (this.mLock) {
            if (this.mOriginalValues != null) {
                this.mOriginalValues.clear();
            } else {
                this.mObjects.clear();
            }
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void sort(Comparator<? super T> comparator) {
        synchronized (this.mLock) {
            if (this.mOriginalValues != null) {
                Collections.sort(this.mOriginalValues, comparator);
            } else {
                Collections.sort(this.mObjects, comparator);
            }
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.mNotifyOnChange = true;
    }

    public void setNotifyOnChange(boolean z) {
        this.mNotifyOnChange = z;
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getCount() {
        return this.mObjects.size();
    }

    public T getItem(int i) {
        return this.mObjects.get(i);
    }

    public int getPosition(T t) {
        return this.mObjects.indexOf(t);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i < 0 || i >= this.mObjects.size()) {
            throw new IllegalStateException("couldn't get view at this position " + i);
        }
        T t = this.mObjects.get(i);
        if (view == null) {
            view = newView(i, t, viewGroup);
        }
        if (uploadExposeLog()) {
            ExposeHelper.getInstance().addExposeItem(t, i);
        }
        bindView(view, i, t);
        return view;
    }

    public Filter getFilter() {
        if (this.mFilter == null) {
            this.mFilter = new ArrayFilter();
        }
        return this.mFilter;
    }

    private class ArrayFilter extends Filter {
        private ArrayFilter() {
        }

        /* access modifiers changed from: protected */
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            ArrayList arrayList;
            ArrayList arrayList2;
            Filter.FilterResults filterResults = new Filter.FilterResults();
            if (ArraysAdapter.this.mOriginalValues == null) {
                synchronized (ArraysAdapter.this.mLock) {
                    ArrayList unused = ArraysAdapter.this.mOriginalValues = new ArrayList(ArraysAdapter.this.mObjects);
                }
            }
            if (charSequence == null || charSequence.length() == 0) {
                synchronized (ArraysAdapter.this.mLock) {
                    arrayList = new ArrayList(ArraysAdapter.this.mOriginalValues);
                }
                filterResults.values = arrayList;
                filterResults.count = arrayList.size();
            } else {
                String lowerCase = charSequence.toString().toLowerCase();
                synchronized (ArraysAdapter.this.mLock) {
                    arrayList2 = new ArrayList(ArraysAdapter.this.mOriginalValues);
                }
                int size = arrayList2.size();
                ArrayList arrayList3 = new ArrayList();
                for (int i = 0; i < size; i++) {
                    Object obj = arrayList2.get(i);
                    String lowerCase2 = obj.toString().toLowerCase();
                    if (lowerCase2.startsWith(lowerCase)) {
                        arrayList3.add(obj);
                    } else {
                        String[] split = lowerCase2.split(" ");
                        int length = split.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                break;
                            } else if (split[i2].startsWith(lowerCase)) {
                                arrayList3.add(obj);
                                break;
                            } else {
                                i2++;
                            }
                        }
                    }
                }
                filterResults.values = arrayList3;
                filterResults.count = arrayList3.size();
            }
            return filterResults;
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            List unused = ArraysAdapter.this.mObjects = (List) filterResults.values;
            if (filterResults.count > 0) {
                ArraysAdapter.this.notifyDataSetChanged();
            } else {
                ArraysAdapter.this.notifyDataSetInvalidated();
            }
        }
    }
}
