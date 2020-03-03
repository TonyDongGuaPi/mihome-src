package com.mi.global.bbs.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.CheckedTextView;

public class SimplePermissionAdapter extends RecyclerView.Adapter {
    private int currentPosition;
    private LayoutInflater inflater;
    private Context mContext;
    private String[] mList;
    OnPermissionItemClickListener onPermissionItemClickListener;

    public interface OnPermissionItemClickListener {
        void onClick(int i);
    }

    public SimplePermissionAdapter(Context context, int i) {
        this.mContext = context;
        this.mList = context.getResources().getStringArray(R.array.user_permission);
        this.inflater = LayoutInflater.from(context);
        this.currentPosition = i;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PermissionHolder(this.inflater.inflate(R.layout.bbs_simple_dialog_text_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        PermissionHolder permissionHolder = (PermissionHolder) viewHolder;
        permissionHolder.textView.setText(this.mList[i]);
        if (i == 0) {
            setLeftDrawable(permissionHolder, R.drawable.bbs_permission_visible);
        } else if (i == 1) {
            setLeftDrawable(permissionHolder, R.drawable.bbs_permission_freinds);
        } else {
            setLeftDrawable(permissionHolder, R.drawable.bbs_permission_disable);
        }
        if (i == this.currentPosition) {
            permissionHolder.textView.setChecked(true);
        } else {
            permissionHolder.textView.setChecked(false);
        }
        permissionHolder.textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((CheckedTextView) view).setChecked(true);
                if (SimplePermissionAdapter.this.onPermissionItemClickListener != null) {
                    SimplePermissionAdapter.this.onPermissionItemClickListener.onClick(i);
                }
            }
        });
    }

    private void setLeftDrawable(PermissionHolder permissionHolder, int i) {
        Drawable drawable = this.mContext.getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        permissionHolder.textView.setCompoundDrawables(drawable, (Drawable) null, permissionHolder.textView.getCompoundDrawables()[2], (Drawable) null);
    }

    public int getItemCount() {
        if (this.mList == null) {
            return 0;
        }
        return this.mList.length;
    }

    public void setOnPermissionItemClickListener(OnPermissionItemClickListener onPermissionItemClickListener2) {
        this.onPermissionItemClickListener = onPermissionItemClickListener2;
    }

    public class PermissionHolder extends RecyclerView.ViewHolder {
        CheckedTextView textView;

        public PermissionHolder(View view) {
            super(view);
            this.textView = (CheckedTextView) view;
        }
    }
}
