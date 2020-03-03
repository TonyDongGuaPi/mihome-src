package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
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
import com.mi.global.bbs.model.MyFavorModel;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.view.Editor.FontTextView;
import java.util.List;

public class MyFavorAdapter extends BaseLoadMoreAdapter {
    private static final int TYPE_NORMAL = 0;
    private List<MyFavorModel.DataBean.ListBean> items;
    OnShareListener onShareListener;

    public int getNormalViewType(int i) {
        return 0;
    }

    public class FavorItemHolder_ViewBinding implements Unbinder {
        private FavorItemHolder target;

        @UiThread
        public FavorItemHolder_ViewBinding(FavorItemHolder favorItemHolder, View view) {
            this.target = favorItemHolder;
            favorItemHolder.myFavorItemTitle = (FontTextView) Utils.findRequiredViewAsType(view, R.id.my_favor_item_title, "field 'myFavorItemTitle'", FontTextView.class);
            favorItemHolder.myFavorItemAuthor = (TextView) Utils.findRequiredViewAsType(view, R.id.my_favor_item_author, "field 'myFavorItemAuthor'", TextView.class);
            favorItemHolder.myFavorItemIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.my_favor_item_icon, "field 'myFavorItemIcon'", ImageView.class);
            favorItemHolder.myFavorItemContainer = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.my_favor_item_container, "field 'myFavorItemContainer'", RelativeLayout.class);
            favorItemHolder.myFavorItemViews = (TextView) Utils.findRequiredViewAsType(view, R.id.my_favor_item_views, "field 'myFavorItemViews'", TextView.class);
            favorItemHolder.myFavorItemComments = (TextView) Utils.findRequiredViewAsType(view, R.id.my_favor_item_comments, "field 'myFavorItemComments'", TextView.class);
            favorItemHolder.myFavorItemShare = (TextView) Utils.findRequiredViewAsType(view, R.id.my_favor_item_share, "field 'myFavorItemShare'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            FavorItemHolder favorItemHolder = this.target;
            if (favorItemHolder != null) {
                this.target = null;
                favorItemHolder.myFavorItemTitle = null;
                favorItemHolder.myFavorItemAuthor = null;
                favorItemHolder.myFavorItemIcon = null;
                favorItemHolder.myFavorItemContainer = null;
                favorItemHolder.myFavorItemViews = null;
                favorItemHolder.myFavorItemComments = null;
                favorItemHolder.myFavorItemShare = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public MyFavorAdapter(Activity activity, InfiniteScrollListener.DataLoading dataLoading, List<MyFavorModel.DataBean.ListBean> list) {
        super(activity, dataLoading);
        this.items = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 0) {
            return super.onCreateViewHolder(viewGroup, i);
        }
        return createFavorItemHolder(viewGroup);
    }

    private FavorItemHolder createFavorItemHolder(ViewGroup viewGroup) {
        return new FavorItemHolder(this.layoutInflater.inflate(R.layout.bbs_my_favor_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        if (getItemViewType(i) == 0) {
            bindFavorDataHolder((FavorItemHolder) viewHolder, i);
        }
    }

    private void bindFavorDataHolder(FavorItemHolder favorItemHolder, int i) {
        MyFavorModel.DataBean.ListBean listBean = this.items.get(i);
        favorItemHolder.myFavorItemAuthor.setText(listBean.author);
        favorItemHolder.myFavorItemComments.setText(listBean.replies);
        final String str = listBean.thread;
        favorItemHolder.myFavorItemContainer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jump(view.getContext(), str);
            }
        });
        if (listBean.pics != null && listBean.pics.size() > 0) {
            ImageLoader.showImage(favorItemHolder.myFavorItemIcon, listBean.pics.get(0));
        }
        favorItemHolder.myFavorItemViews.setText(listBean.views);
        final String str2 = listBean.title;
        favorItemHolder.myFavorItemTitle.setText(str2);
        favorItemHolder.myFavorItemShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MyFavorAdapter.this.onShareListener != null) {
                    MyFavorAdapter.this.onShareListener.onShare(str2, str);
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

    public void add(List<MyFavorModel.DataBean.ListBean> list) {
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

    static class FavorItemHolder extends RecyclerView.ViewHolder {
        @BindView(2131493703)
        TextView myFavorItemAuthor;
        @BindView(2131493704)
        TextView myFavorItemComments;
        @BindView(2131493705)
        RelativeLayout myFavorItemContainer;
        @BindView(2131493706)
        ImageView myFavorItemIcon;
        @BindView(2131493707)
        TextView myFavorItemShare;
        @BindView(2131493708)
        FontTextView myFavorItemTitle;
        @BindView(2131493709)
        TextView myFavorItemViews;

        public FavorItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
