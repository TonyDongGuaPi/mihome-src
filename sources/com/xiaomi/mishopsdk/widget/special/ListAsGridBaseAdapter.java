package com.xiaomi.mishopsdk.widget.special;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import com.xiaomi.mishopsdk.util.DensityUtil;

public abstract class ListAsGridBaseAdapter extends BaseAdapter {
    private int mBackgroundResource = -1;
    protected Context mContext;
    private GridItemClickListener mGridItemClickListener;
    private int mNumColumns;

    public abstract int getItemCount();

    /* access modifiers changed from: protected */
    public abstract View getItemView(int i, View view, ViewGroup viewGroup);

    private class ListItemClickListener implements View.OnClickListener {
        private int mPosition;

        public ListItemClickListener(int i) {
            this.mPosition = i;
        }

        public void onClick(View view) {
            ListAsGridBaseAdapter.this.onGridItemClicked(view, this.mPosition);
        }
    }

    public ListAsGridBaseAdapter(Context context) {
        this.mContext = context;
        this.mNumColumns = 1;
    }

    public void setBackgroundResource(int i) {
        this.mBackgroundResource = i;
    }

    public final void setOnGridClickListener(GridItemClickListener gridItemClickListener) {
        this.mGridItemClickListener = gridItemClickListener;
    }

    /* access modifiers changed from: private */
    public final void onGridItemClicked(View view, int i) {
        if (this.mGridItemClickListener != null) {
            this.mGridItemClickListener.onGridItemClicked(view, i, getItemId(i));
        }
    }

    public final int getNumColumns() {
        return this.mNumColumns;
    }

    public final void setNumColumns(int i) {
        this.mNumColumns = i;
        notifyDataSetChanged();
    }

    public final int getCount() {
        return (int) Math.ceil((double) ((((float) getItemCount()) * 1.0f) / ((float) getNumColumns())));
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        int i2;
        if (viewGroup != null) {
            i2 = viewGroup.getWidth() / this.mNumColumns;
        } else {
            i2 = view != null ? view.getWidth() / this.mNumColumns : 0;
        }
        if (view == null) {
            return createItemRow(i, viewGroup, i2);
        }
        LinearLayout linearLayout = (LinearLayout) view;
        updateItemRow(i, viewGroup, linearLayout, i2);
        return linearLayout;
    }

    private LinearLayout createItemRow(int i, ViewGroup viewGroup, int i2) {
        View view;
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        if (this.mBackgroundResource > 0) {
            linearLayout.setBackgroundResource(this.mBackgroundResource);
        }
        int paddingLeft = i2 - ((linearLayout.getPaddingLeft() + linearLayout.getPaddingRight()) / this.mNumColumns);
        linearLayout.setOrientation(0);
        for (int i3 = 0; i3 < this.mNumColumns; i3++) {
            int i4 = (this.mNumColumns * i) + i3;
            if (i4 < getItemCount()) {
                view = getItemView(i4, (View) null, viewGroup);
                view.setVisibility(0);
                getItemView(i4, view, viewGroup);
                view.setOnClickListener(new ListItemClickListener(i4));
                if (i4 % 2 == 0) {
                    view.setPadding(0, 0, DensityUtil.dip2px(7.0f), 0);
                }
            } else {
                view = new View(this.mContext);
                view.setVisibility(4);
            }
            linearLayout.addView(view);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = paddingLeft;
            view.setLayoutParams(layoutParams);
        }
        linearLayout.setPadding(0, 0, 0, DensityUtil.dip2px(7.0f));
        return linearLayout;
    }

    private void updateItemRow(int i, ViewGroup viewGroup, LinearLayout linearLayout, int i2) {
        int paddingLeft = i2 - ((linearLayout.getPaddingLeft() + linearLayout.getPaddingRight()) / this.mNumColumns);
        for (int i3 = 0; i3 < this.mNumColumns; i3++) {
            int i4 = (this.mNumColumns * i) + i3;
            View childAt = linearLayout.getChildAt(i3);
            if (childAt == null) {
                childAt = getItemView(i4, (View) null, viewGroup);
                linearLayout.addView(childAt);
            }
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            layoutParams.width = paddingLeft;
            childAt.setLayoutParams(layoutParams);
            if (i4 < getItemCount()) {
                childAt.setVisibility(0);
                View itemView = getItemView(i4, childAt, viewGroup);
                itemView.setOnClickListener(new ListItemClickListener(i4));
                itemView.equals(childAt);
            } else {
                childAt.setVisibility(4);
            }
        }
    }
}
