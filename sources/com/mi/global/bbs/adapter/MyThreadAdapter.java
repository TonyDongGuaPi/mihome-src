package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.MyThreadModel;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import java.util.List;

public class MyThreadAdapter extends BaseLoadMoreAdapter {
    private List<MyThreadModel.DataBean.ListBean> items;
    OnShareListener onShareListener;

    public int getNormalViewType(int i) {
        return 0;
    }

    public class ThreadtemHolder_ViewBinding implements Unbinder {
        private ThreadtemHolder target;

        @UiThread
        public ThreadtemHolder_ViewBinding(ThreadtemHolder threadtemHolder, View view) {
            this.target = threadtemHolder;
            threadtemHolder.myFavorItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.my_thread_item_title, "field 'myFavorItemTitle'", TextView.class);
            threadtemHolder.myFavorItemAuthor = (TextView) Utils.findRequiredViewAsType(view, R.id.my_thread_item_author, "field 'myFavorItemAuthor'", TextView.class);
            threadtemHolder.myFavorItemIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.my_thread_item_icon, "field 'myFavorItemIcon'", ImageView.class);
            threadtemHolder.myFavorItemContainer = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.my_thread_item_container, "field 'myFavorItemContainer'", RelativeLayout.class);
            threadtemHolder.myFavorItemViews = (TextView) Utils.findRequiredViewAsType(view, R.id.my_thread_item_views, "field 'myFavorItemViews'", TextView.class);
            threadtemHolder.myFavorItemComments = (TextView) Utils.findRequiredViewAsType(view, R.id.my_thread_item_comments, "field 'myFavorItemComments'", TextView.class);
            threadtemHolder.myFavorItemShare = (TextView) Utils.findRequiredViewAsType(view, R.id.my_thread_item_share, "field 'myFavorItemShare'", TextView.class);
            threadtemHolder.relThreadDelete = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_thread_delete, "field 'relThreadDelete'", RelativeLayout.class);
            threadtemHolder.viewLine = (TextView) Utils.findRequiredViewAsType(view, R.id.view_line, "field 'viewLine'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ThreadtemHolder threadtemHolder = this.target;
            if (threadtemHolder != null) {
                this.target = null;
                threadtemHolder.myFavorItemTitle = null;
                threadtemHolder.myFavorItemAuthor = null;
                threadtemHolder.myFavorItemIcon = null;
                threadtemHolder.myFavorItemContainer = null;
                threadtemHolder.myFavorItemViews = null;
                threadtemHolder.myFavorItemComments = null;
                threadtemHolder.myFavorItemShare = null;
                threadtemHolder.relThreadDelete = null;
                threadtemHolder.viewLine = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public MyThreadAdapter(Activity activity, InfiniteScrollListener.DataLoading dataLoading, List<MyThreadModel.DataBean.ListBean> list) {
        super(activity, dataLoading);
        this.items = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 0) {
            return super.onCreateViewHolder(viewGroup, i);
        }
        return createFavorItemHolder(viewGroup);
    }

    private ThreadtemHolder createFavorItemHolder(ViewGroup viewGroup) {
        return new ThreadtemHolder(this.layoutInflater.inflate(R.layout.bbs_my_thread_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        if (getItemViewType(i) == 0) {
            bindThreadDataHolder((ThreadtemHolder) viewHolder, i);
        }
    }

    private void bindThreadDataHolder(ThreadtemHolder threadtemHolder, int i) {
        MyThreadModel.DataBean.ListBean listBean = this.items.get(i);
        threadtemHolder.myFavorItemAuthor.setText(listBean.dateline);
        threadtemHolder.myFavorItemComments.setText(listBean.replies);
        threadtemHolder.relThreadDelete.setVisibility(8);
        if ("-1".equals(listBean.displayorder)) {
            threadtemHolder.relThreadDelete.setVisibility(0);
            threadtemHolder.myFavorItemAuthor.setTextColor(Color.parseColor("#000000"));
            threadtemHolder.myFavorItemComments.setTextColor(Color.parseColor("#000000"));
            threadtemHolder.myFavorItemViews.setTextColor(Color.parseColor("#000000"));
            threadtemHolder.viewLine.setBackgroundColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        }
        final String str = listBean.thread;
        threadtemHolder.myFavorItemContainer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jump(view.getContext(), str);
            }
        });
        if (listBean.pics == null || listBean.pics.size() <= 0) {
            threadtemHolder.myFavorItemIcon.setVisibility(8);
        } else {
            String str2 = listBean.pics.get(0);
            if (!TextUtils.isEmpty(str2)) {
                ImageLoader.showImage(threadtemHolder.myFavorItemIcon, str2);
                threadtemHolder.myFavorItemIcon.setVisibility(0);
            }
        }
        threadtemHolder.myFavorItemViews.setText(listBean.views);
        final String str3 = listBean.title;
        threadtemHolder.myFavorItemTitle.setText(str3);
        threadtemHolder.myFavorItemShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MyThreadAdapter.this.onShareListener != null) {
                    MyThreadAdapter.this.onShareListener.onShare(str3, str);
                }
            }
        });
    }

    public void clear() {
        if (this.items != null && this.items.size() > 0) {
            this.items.clear();
            notifyDataSetChanged();
        }
    }

    public void add(List<MyThreadModel.DataBean.ListBean> list) {
        if (list != null && list.size() > 0) {
            this.items.addAll(list);
            notifyDataSetChanged();
        }
    }

    public int getDataItemCount() {
        return this.items.size();
    }

    public void setOnShareListener(OnShareListener onShareListener2) {
        this.onShareListener = onShareListener2;
    }

    static class ThreadtemHolder extends RecyclerView.ViewHolder {
        @BindView(2131493720)
        TextView myFavorItemAuthor;
        @BindView(2131493721)
        TextView myFavorItemComments;
        @BindView(2131493722)
        RelativeLayout myFavorItemContainer;
        @BindView(2131493723)
        ImageView myFavorItemIcon;
        @BindView(2131493724)
        TextView myFavorItemShare;
        @BindView(2131493725)
        TextView myFavorItemTitle;
        @BindView(2131493726)
        TextView myFavorItemViews;
        @BindView(2131493900)
        RelativeLayout relThreadDelete;
        @BindView(2131494249)
        TextView viewLine;

        public ThreadtemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
