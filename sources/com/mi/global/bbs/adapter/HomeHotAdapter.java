package com.mi.global.bbs.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.HomePostBean;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.util.Coder;
import com.mi.util.Device;
import java.util.List;

public class HomeHotAdapter extends RecyclerView.Adapter {
    private List<HomePostBean> postBeen;

    public class HomeHotPostHolder_ViewBinding implements Unbinder {
        private HomeHotPostHolder target;

        @UiThread
        public HomeHotPostHolder_ViewBinding(HomeHotPostHolder homeHotPostHolder, View view) {
            this.target = homeHotPostHolder;
            homeHotPostHolder.homeHotItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.home_hot_list_item_layout, "field 'homeHotItem'", LinearLayout.class);
            homeHotPostHolder.homeHotItemImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.home_hot_item_img, "field 'homeHotItemImg'", ImageView.class);
            homeHotPostHolder.homeHotItemVideoBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.home_hot_item_video_btn, "field 'homeHotItemVideoBtn'", ImageView.class);
            homeHotPostHolder.homeHotItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.home_hot_item_title, "field 'homeHotItemTitle'", TextView.class);
            homeHotPostHolder.homeHotItemDes = (TextView) Utils.findRequiredViewAsType(view, R.id.home_hot_item_des, "field 'homeHotItemDes'", TextView.class);
            homeHotPostHolder.homeHotItemFixLeft = Utils.findRequiredView(view, R.id.home_hot_list_item_fix_left, "field 'homeHotItemFixLeft'");
            homeHotPostHolder.homeHotItemFixRight = Utils.findRequiredView(view, R.id.home_hot_list_item_fix_right, "field 'homeHotItemFixRight'");
        }

        @CallSuper
        public void unbind() {
            HomeHotPostHolder homeHotPostHolder = this.target;
            if (homeHotPostHolder != null) {
                this.target = null;
                homeHotPostHolder.homeHotItem = null;
                homeHotPostHolder.homeHotItemImg = null;
                homeHotPostHolder.homeHotItemVideoBtn = null;
                homeHotPostHolder.homeHotItemTitle = null;
                homeHotPostHolder.homeHotItemDes = null;
                homeHotPostHolder.homeHotItemFixLeft = null;
                homeHotPostHolder.homeHotItemFixRight = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public HomeHotAdapter(List<HomePostBean> list) {
        this.postBeen = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HomeHotPostHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bbs_home_hot_post_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final HomeHotPostHolder homeHotPostHolder = (HomeHotPostHolder) viewHolder;
        final HomePostBean homePostBean = this.postBeen.get(i);
        final boolean equals = "1".equals(homePostBean.getType());
        homeHotPostHolder.homeHotItemTitle.setText(homePostBean.getTitle());
        homeHotPostHolder.homeHotItemDes.setText(homePostBean.getContent());
        List<String> picurls = homePostBean.getPicurls();
        if (picurls != null && picurls.size() > 0) {
            ImageLoader.showImage(homeHotPostHolder.homeHotItemImg, homePostBean.getPicurls().get(0));
        }
        homeHotPostHolder.homeHotItemFixLeft.setVisibility(8);
        homeHotPostHolder.homeHotItemFixRight.setVisibility(8);
        if (i == 0) {
            homeHotPostHolder.homeHotItemFixLeft.setVisibility(0);
        }
        if (i == this.postBeen.size() - 1) {
            homeHotPostHolder.homeHotItemFixRight.setVisibility(0);
        }
        homeHotPostHolder.homeHotItem.getLayoutParams().width = (Device.f1349a - Coder.a(40.0f)) / 2;
        homeHotPostHolder.homeHotItem.requestLayout();
        homeHotPostHolder.homeHotItemVideoBtn.setVisibility(8);
        if (equals) {
            homeHotPostHolder.homeHotItemVideoBtn.setVisibility(0);
        }
        homeHotPostHolder.homeHotItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent("home", "click_hot", "click_hot" + homePostBean.getTid());
                if (!equals) {
                    WebActivity.jump(homeHotPostHolder.itemView.getContext(), homePostBean.getThreadurl());
                }
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
        @BindView(2131493408)
        LinearLayout homeHotItem;
        @BindView(2131493401)
        TextView homeHotItemDes;
        @BindView(2131493406)
        View homeHotItemFixLeft;
        @BindView(2131493407)
        View homeHotItemFixRight;
        @BindView(2131493402)
        ImageView homeHotItemImg;
        @BindView(2131493403)
        TextView homeHotItemTitle;
        @BindView(2131493404)
        ImageView homeHotItemVideoBtn;

        public HomeHotPostHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
