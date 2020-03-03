package com.mi.global.bbs.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.HomeColumnList;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.util.Coder;
import com.mi.util.Device;
import java.util.List;

public class HomeRdColumnAdapter extends RecyclerView.Adapter {
    private List<HomeColumnList.ColumnArticleBean> postBeen;

    public class HomeHotPostHolder_ViewBinding implements Unbinder {
        private HomeHotPostHolder target;

        @UiThread
        public HomeHotPostHolder_ViewBinding(HomeHotPostHolder homeHotPostHolder, View view) {
            this.target = homeHotPostHolder;
            homeHotPostHolder.flColumnItem = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.home_rd_column_list_item_layout, "field 'flColumnItem'", FrameLayout.class);
            homeHotPostHolder.ivColumnBg = (ImageView) Utils.findRequiredViewAsType(view, R.id.home_rd_column_list_item_img, "field 'ivColumnBg'", ImageView.class);
            homeHotPostHolder.tvColumnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.home_rd_column_list_item_title, "field 'tvColumnTitle'", TextView.class);
            homeHotPostHolder.vColumnLeftFix = Utils.findRequiredView(view, R.id.home_rd_column_list_item_fix_left, "field 'vColumnLeftFix'");
            homeHotPostHolder.vColumnRightFix = Utils.findRequiredView(view, R.id.home_rd_column_list_item_fix_right, "field 'vColumnRightFix'");
        }

        @CallSuper
        public void unbind() {
            HomeHotPostHolder homeHotPostHolder = this.target;
            if (homeHotPostHolder != null) {
                this.target = null;
                homeHotPostHolder.flColumnItem = null;
                homeHotPostHolder.ivColumnBg = null;
                homeHotPostHolder.tvColumnTitle = null;
                homeHotPostHolder.vColumnLeftFix = null;
                homeHotPostHolder.vColumnRightFix = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public HomeRdColumnAdapter(List<HomeColumnList.ColumnArticleBean> list) {
        this.postBeen = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HomeHotPostHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bbs_home_item_rd_column_rv_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final HomeHotPostHolder homeHotPostHolder = (HomeHotPostHolder) viewHolder;
        final HomeColumnList.ColumnArticleBean columnArticleBean = this.postBeen.get(i);
        homeHotPostHolder.tvColumnTitle.setText(columnArticleBean.subject);
        ImageLoader.showImage(homeHotPostHolder.ivColumnBg, columnArticleBean.bgimg);
        homeHotPostHolder.vColumnLeftFix.setVisibility(8);
        homeHotPostHolder.vColumnRightFix.setVisibility(8);
        if (i == 0) {
            homeHotPostHolder.vColumnLeftFix.setVisibility(0);
        }
        if (i == this.postBeen.size() - 1) {
            homeHotPostHolder.vColumnRightFix.setVisibility(0);
        }
        homeHotPostHolder.flColumnItem.getLayoutParams().width = Device.f1349a - Coder.a(32.0f);
        homeHotPostHolder.flColumnItem.requestLayout();
        homeHotPostHolder.flColumnItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent("home", "click_ad_column", "click_ad_column_" + columnArticleBean.tid);
                WebActivity.jumpWithTid(homeHotPostHolder.itemView.getContext(), columnArticleBean.tid);
            }
        });
    }

    public int getItemCount() {
        if (this.postBeen == null) {
            return 0;
        }
        return this.postBeen.size();
    }

    public class HomeHotPostHolder extends RecyclerView.ViewHolder {
        @BindView(2131493420)
        FrameLayout flColumnItem;
        @BindView(2131493419)
        ImageView ivColumnBg;
        @BindView(2131493421)
        TextView tvColumnTitle;
        @BindView(2131493417)
        View vColumnLeftFix;
        @BindView(2131493418)
        View vColumnRightFix;

        public HomeHotPostHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
