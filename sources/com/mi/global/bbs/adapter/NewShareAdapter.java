package com.mi.global.bbs.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.NewShareInfo;
import java.util.List;

public class NewShareAdapter extends RecyclerView.Adapter {
    private List<NewShareInfo> data;
    private LayoutInflater inflater;
    private Context mcontext;
    onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onItemClick(int i);
    }

    public class ShareHolder_ViewBinding implements Unbinder {
        private ShareHolder target;

        @UiThread
        public ShareHolder_ViewBinding(ShareHolder shareHolder, View view) {
            this.target = shareHolder;
            shareHolder.shareAppIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.share_item_icon, "field 'shareAppIcon'", ImageView.class);
            shareHolder.shareAppName = (TextView) Utils.findRequiredViewAsType(view, R.id.share_item_text, "field 'shareAppName'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ShareHolder shareHolder = this.target;
            if (shareHolder != null) {
                this.target = null;
                shareHolder.shareAppIcon = null;
                shareHolder.shareAppName = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public NewShareAdapter(Context context, List<NewShareInfo> list) {
        this.data = list;
        this.mcontext = context;
        this.inflater = LayoutInflater.from(context);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ShareHolder(this.inflater.inflate(R.layout.new_share_item_layout, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        ShareHolder shareHolder = (ShareHolder) viewHolder;
        shareHolder.shareAppIcon.setImageDrawable(this.mcontext.getResources().getDrawable(this.data.get(i).icon));
        shareHolder.shareAppName.setText(this.mcontext.getResources().getString(this.data.get(i).labelRes));
        shareHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (NewShareAdapter.this.onItemClickListener != null) {
                    NewShareAdapter.this.onItemClickListener.onItemClick(i);
                }
            }
        });
    }

    public int getItemCount() {
        if (this.data == null) {
            return 0;
        }
        return this.data.size();
    }

    public void setOnItemClickListener(onItemClickListener onitemclicklistener) {
        this.onItemClickListener = onitemclicklistener;
    }

    public class ShareHolder extends RecyclerView.ViewHolder {
        @BindView(2131493964)
        ImageView shareAppIcon;
        @BindView(2131493965)
        TextView shareAppName;

        public ShareHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
