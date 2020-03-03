package com.mi.global.bbs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.CheckedTextView;

public class SimpleCheckedAdapter extends RecyclerView.Adapter {
    private int currentPosition;
    private LayoutInflater inflater;
    private String[] mList;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onClick(int i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }

    public SimpleCheckedAdapter(Context context, String[] strArr, int i) {
        this.mList = strArr;
        this.inflater = LayoutInflater.from(context);
        this.currentPosition = i;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SelectHolder(this.inflater.inflate(R.layout.bbs_simple_dialog_text_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        SelectHolder selectHolder = (SelectHolder) viewHolder;
        selectHolder.textView.setText(this.mList[i]);
        if (i == this.currentPosition) {
            selectHolder.textView.setChecked(true);
        } else {
            selectHolder.textView.setChecked(false);
        }
        selectHolder.textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((CheckedTextView) view).setChecked(true);
                if (SimpleCheckedAdapter.this.onItemClickListener != null) {
                    SimpleCheckedAdapter.this.onItemClickListener.onClick(i);
                }
            }
        });
    }

    public int getItemCount() {
        if (this.mList == null) {
            return 0;
        }
        return this.mList.length;
    }

    public class SelectHolder extends RecyclerView.ViewHolder {
        CheckedTextView textView;

        public SelectHolder(View view) {
            super(view);
            this.textView = (CheckedTextView) view;
        }
    }
}
