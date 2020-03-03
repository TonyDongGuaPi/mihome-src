package com.mi.global.bbs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mi.global.bbs.R;

public class DialogTextAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private String[] mList;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onClick(int i);
    }

    public DialogTextAdapter(Context context, String[] strArr) {
        this.mList = strArr;
        this.inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TextHolder(this.inflater.inflate(R.layout.bbs_text_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        TextHolder textHolder = (TextHolder) viewHolder;
        textHolder.textView.setText(this.mList[i]);
        textHolder.textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DialogTextAdapter.this.onItemClickListener != null) {
                    DialogTextAdapter.this.onItemClickListener.onClick(i);
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

    public class TextHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TextHolder(View view) {
            super(view);
            this.textView = (TextView) view;
        }
    }
}
