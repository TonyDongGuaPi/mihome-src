package com.mi.global.bbs.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.request.RequestOptions;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.model.OnlineActivityResult;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.view.CircleImageView;
import java.util.ArrayList;
import java.util.List;

public class OnlineActivityAdapter extends BaseLoadMoreAdapter {
    public static final int GRID_LAYOUT_COLUMN_ONE_VALUE = 1;
    public static final int GRID_LAYOUT_COLUMN_TWO_VALUE = 2;
    private static final int ONLINE_ACTIVITY_BIG_PIC = 0;
    public static final int ONLINE_ACTIVITY_COMPLETE = 1;
    private static final int ONLINE_ACTIVITY_SMALL_PIC = 1;
    public static final int ONLINE_ACTIVITY_UNCOMPLETE = 0;
    /* access modifiers changed from: private */
    public BaseActivity context;
    public List<OnlineActivityResult.OnlineActivityBean.OnlineActivity> onlineActivities = new ArrayList();

    public int getNormalViewType(int i) {
        return i == 0 ? 0 : 1;
    }

    public class ActivityHolder_ViewBinding implements Unbinder {
        private ActivityHolder target;

        @UiThread
        public ActivityHolder_ViewBinding(ActivityHolder activityHolder, View view) {
            this.target = activityHolder;
            activityHolder.activityItemPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.activity_item_pic, "field 'activityItemPic'", ImageView.class);
            activityHolder.activityItemTime = (TextView) Utils.findRequiredViewAsType(view, R.id.activity_item_time, "field 'activityItemTime'", TextView.class);
            activityHolder.activityItemIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.activity_item_icon, "field 'activityItemIcon'", CircleImageView.class);
            activityHolder.activityItemName = (TextView) Utils.findRequiredViewAsType(view, R.id.activity_item_name, "field 'activityItemName'", TextView.class);
            activityHolder.activityItemDes = (TextView) Utils.findRequiredViewAsType(view, R.id.activity_item_des, "field 'activityItemDes'", TextView.class);
            activityHolder.activityBgCover = Utils.findRequiredView(view, R.id.activity_bg_cover, "field 'activityBgCover'");
            activityHolder.activityImgCover = (ImageView) Utils.findRequiredViewAsType(view, R.id.activity_img_cover, "field 'activityImgCover'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            ActivityHolder activityHolder = this.target;
            if (activityHolder != null) {
                this.target = null;
                activityHolder.activityItemPic = null;
                activityHolder.activityItemTime = null;
                activityHolder.activityItemIcon = null;
                activityHolder.activityItemName = null;
                activityHolder.activityItemDes = null;
                activityHolder.activityBgCover = null;
                activityHolder.activityImgCover = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public OnlineActivityAdapter(BaseActivity baseActivity, InfiniteScrollListener.DataLoading dataLoading) {
        super(baseActivity, dataLoading);
        this.context = baseActivity;
    }

    public void addData(List<OnlineActivityResult.OnlineActivityBean.OnlineActivity> list) {
        if (list != null) {
            this.onlineActivities.clear();
            this.onlineActivities.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.onlineActivities.clear();
    }

    public int getDataItemCount() {
        return this.onlineActivities.size();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new ActivityHolder(this.layoutInflater.inflate(R.layout.bbs_activity_item_big_pic, viewGroup, false));
            case 1:
                return new ActivityHolder(this.layoutInflater.inflate(R.layout.bbs_activity_item_small_pic, viewGroup, false));
            default:
                return super.onCreateViewHolder(viewGroup, i);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        switch (getItemViewType(i)) {
            case 0:
            case 1:
                bindActivityHolder((ActivityHolder) viewHolder, i);
                return;
            default:
                return;
        }
    }

    public int getItemColumnSpan(int i) {
        return getItemViewType(i) != 1 ? 2 : 1;
    }

    private void bindActivityHolder(ActivityHolder activityHolder, int i) {
        handleCommonPart(activityHolder, this.onlineActivities.get(i));
    }

    private void handleCommonPart(ActivityHolder activityHolder, OnlineActivityResult.OnlineActivityBean.OnlineActivity onlineActivity) {
        ImageLoader.showHeadIcon(activityHolder.activityItemIcon, onlineActivity.authimg);
        activityHolder.activityItemDes.setText(onlineActivity.subject);
        activityHolder.activityItemName.setText(onlineActivity.author);
        activityHolder.activityItemTime.setText(onlineActivity.dateline);
        ImageLoader.showImage(activityHolder.activityItemPic, onlineActivity.showpiclist != null && onlineActivity.showpiclist.size() > 0 && !TextUtils.isEmpty(onlineActivity.showpiclist.get(0)) ? onlineActivity.showpiclist.get(0) : null, (RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().a(R.drawable.bbs_sub_forum_icon)).c(R.drawable.bbs_activity_default_img)).b(R.drawable.bbs_activity_default_img));
        if (onlineActivity.finish == 1) {
            activityHolder.activityBgCover.setVisibility(0);
            activityHolder.activityImgCover.setVisibility(0);
        } else {
            activityHolder.activityBgCover.setVisibility(8);
            activityHolder.activityImgCover.setVisibility(8);
        }
        showImgCover(activityHolder);
        setCommonPartClickListener(activityHolder, onlineActivity);
    }

    private void setCommonPartClickListener(ActivityHolder activityHolder, final OnlineActivityResult.OnlineActivityBean.OnlineActivity onlineActivity) {
        activityHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_ACTIVITIES, Constants.ClickEvent.CLICK_ONLINE_ACTIVITY, onlineActivity.tid);
                WebActivity.jump(OnlineActivityAdapter.this.context, ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{onlineActivity.tid})));
            }
        });
        activityHolder.activityItemIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(OnlineActivityAdapter.this.context, onlineActivity.authorid);
            }
        });
    }

    private void showImgCover(ActivityHolder activityHolder) {
        activityHolder.activityImgCover.setBackground(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.bbs_activity_online_end_cover_en, this.context.getTheme()));
    }

    public class ActivityHolder extends RecyclerView.ViewHolder {
        @BindView(2131492900)
        View activityBgCover;
        @BindView(2131492935)
        ImageView activityImgCover;
        @BindView(2131492937)
        TextView activityItemDes;
        @BindView(2131492939)
        CircleImageView activityItemIcon;
        @BindView(2131492941)
        TextView activityItemName;
        @BindView(2131492942)
        ImageView activityItemPic;
        @BindView(2131492944)
        TextView activityItemTime;

        public ActivityHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
