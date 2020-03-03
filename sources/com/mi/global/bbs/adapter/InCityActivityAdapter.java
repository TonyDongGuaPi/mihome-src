package com.mi.global.bbs.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.bumptech.glide.request.RequestOptions;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.model.InCityActivityResult;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.view.CircleImageView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InCityActivityAdapter extends BaseLoadMoreAdapter {
    private static final int TYPE_DIVIDED = 1;
    /* access modifiers changed from: private */
    public BaseActivity context;
    public List<InCityActivityResult.InCityActivityBean.InCityActivity> inCityActivities = new ArrayList();
    OnDividedClickListener onDividedClickListener;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout refreshLayout;

    public interface OnDividedClickListener {
        void onDividedClick(int i);
    }

    public class ActivityDividedHolder_ViewBinding implements Unbinder {
        private ActivityDividedHolder target;

        @UiThread
        public ActivityDividedHolder_ViewBinding(ActivityDividedHolder activityDividedHolder, View view) {
            this.target = activityDividedHolder;
            activityDividedHolder.dividedLayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.incity_divided_layout, "field 'dividedLayout'", RelativeLayout.class);
        }

        @CallSuper
        public void unbind() {
            ActivityDividedHolder activityDividedHolder = this.target;
            if (activityDividedHolder != null) {
                this.target = null;
                activityDividedHolder.dividedLayout = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
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
            activityHolder.activityItemStartTime = (TextView) Utils.findRequiredViewAsType(view, R.id.activity_item_start_time, "field 'activityItemStartTime'", TextView.class);
            activityHolder.activityItemAddress = (TextView) Utils.findRequiredViewAsType(view, R.id.activity_item_address, "field 'activityItemAddress'", TextView.class);
            activityHolder.activityItemProcess = (TextView) Utils.findRequiredViewAsType(view, R.id.activity_process, "field 'activityItemProcess'", TextView.class);
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
                activityHolder.activityItemStartTime = null;
                activityHolder.activityItemAddress = null;
                activityHolder.activityItemProcess = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public InCityActivityAdapter(BaseActivity baseActivity, InfiniteScrollListener.DataLoading dataLoading, SwipeRefreshLayout swipeRefreshLayout) {
        super(baseActivity, dataLoading);
        this.context = baseActivity;
        this.refreshLayout = swipeRefreshLayout;
    }

    public void addData(List<InCityActivityResult.InCityActivityBean.InCityActivity> list) {
        if (list != null) {
            this.inCityActivities.clear();
            this.inCityActivities.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.inCityActivities.clear();
    }

    public int getDataItemCount() {
        return this.inCityActivities.size();
    }

    public int getNormalViewType(int i) {
        InCityActivityResult.InCityActivityBean.InCityActivity inCityActivity = this.inCityActivities.get(i);
        return (!inCityActivity.fid.equals("-1") || !inCityActivity.tid.equals("-1")) ? 0 : 1;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new ActivityHolder(this.layoutInflater.inflate(R.layout.bbs_in_city_activity_item, viewGroup, false));
            case 1:
                return new ActivityDividedHolder(this.layoutInflater.inflate(R.layout.bbs_activity_incity_divided_item, viewGroup, false));
            default:
                return super.onCreateViewHolder(viewGroup, i);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        switch (getItemViewType(i)) {
            case 0:
                bindActivityHolder((ActivityHolder) viewHolder, i);
                return;
            case 1:
                bindActivityDividedHolder((ActivityDividedHolder) viewHolder, i);
                return;
            default:
                return;
        }
    }

    private void bindActivityHolder(ActivityHolder activityHolder, int i) {
        handleCommonPart(activityHolder, this.inCityActivities.get(i));
    }

    private void bindActivityDividedHolder(ActivityDividedHolder activityDividedHolder, final int i) {
        activityDividedHolder.dividedLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!InCityActivityAdapter.this.refreshLayout.isRefreshing()) {
                    InCityActivityAdapter.this.inCityActivities.remove(i);
                    InCityActivityAdapter.this.notifyItemRemoved(i);
                    if (i != InCityActivityAdapter.this.inCityActivities.size() - 1) {
                        InCityActivityAdapter.this.notifyItemRangeChanged(i, InCityActivityAdapter.this.inCityActivities.size() - i);
                    }
                    InCityActivityAdapter.this.onDividedClickListener.onDividedClick(i);
                }
            }
        });
    }

    private void handleCommonPart(ActivityHolder activityHolder, InCityActivityResult.InCityActivityBean.InCityActivity inCityActivity) {
        ImageLoader.showHeadIcon(activityHolder.activityItemIcon, inCityActivity.authimg);
        activityHolder.activityItemDes.setText(inCityActivity.subject);
        activityHolder.activityItemName.setText(inCityActivity.author);
        activityHolder.activityItemTime.setText(inCityActivity.dateline);
        String str = null;
        boolean z = true;
        if (!TextUtils.isEmpty(inCityActivity.link)) {
            boolean z2 = !TextUtils.isEmpty(inCityActivity.ufield);
            ImageView imageView = activityHolder.activityItemPic;
            if (z2) {
                str = inCityActivity.ufield;
            }
            ImageLoader.showImage(imageView, str, (RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().a(R.drawable.bbs_sub_forum_icon)).c(R.drawable.bbs_activity_default_img)).b(R.drawable.bbs_activity_default_img));
        } else {
            if (inCityActivity.showpiclist == null || inCityActivity.showpiclist.size() <= 0 || TextUtils.isEmpty(inCityActivity.showpiclist.get(0))) {
                z = false;
            }
            ImageView imageView2 = activityHolder.activityItemPic;
            if (z) {
                str = inCityActivity.showpiclist.get(0);
            }
            ImageLoader.showImage(imageView2, str, (RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().a(R.drawable.bbs_sub_forum_icon)).c(R.drawable.bbs_activity_default_img)).b(R.drawable.bbs_activity_default_img));
        }
        activityHolder.activityItemStartTime.setText(inCityActivity.starttimefrom);
        activityHolder.activityItemAddress.setText(inCityActivity.place);
        if (checkEnded(inCityActivity)) {
            activityHolder.activityItemProcess.setText(this.context.getResources().getString(R.string.activity_item_process_end));
            activityHolder.activityItemProcess.setBackground(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.activity_item_end_background, this.context.getTheme()));
        } else {
            activityHolder.activityItemProcess.setText(this.context.getResources().getString(R.string.activity_item_process_in));
            activityHolder.activityItemProcess.setBackground(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.activity_item_in_process_background, this.context.getTheme()));
        }
        setCommonPartClickListener(activityHolder, inCityActivity);
    }

    private boolean checkEnded(InCityActivityResult.InCityActivityBean.InCityActivity inCityActivity) {
        try {
            if (System.currentTimeMillis() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inCityActivity.expiration).getTime()) {
                return true;
            }
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setCommonPartClickListener(ActivityHolder activityHolder, final InCityActivityResult.InCityActivityBean.InCityActivity inCityActivity) {
        activityHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!TextUtils.isEmpty(inCityActivity.link)) {
                    WebActivity.jumpDirectly(InCityActivityAdapter.this.context, inCityActivity.link);
                    return;
                }
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_ACTIVITIES, Constants.ClickEvent.CLICK_OFFLINE_ACTIVITY, inCityActivity.tid + "");
                WebActivity.jump(InCityActivityAdapter.this.context, ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{inCityActivity.tid})));
            }
        });
        activityHolder.activityItemIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(InCityActivityAdapter.this.context, inCityActivity.authorid);
            }
        });
    }

    public class ActivityHolder extends RecyclerView.ViewHolder {
        @BindView(2131492936)
        TextView activityItemAddress;
        @BindView(2131492937)
        TextView activityItemDes;
        @BindView(2131492939)
        CircleImageView activityItemIcon;
        @BindView(2131492941)
        TextView activityItemName;
        @BindView(2131492942)
        ImageView activityItemPic;
        @BindView(2131492950)
        TextView activityItemProcess;
        @BindView(2131492943)
        TextView activityItemStartTime;
        @BindView(2131492944)
        TextView activityItemTime;

        public ActivityHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ActivityDividedHolder extends RecyclerView.ViewHolder {
        @BindView(2131493454)
        RelativeLayout dividedLayout;

        public ActivityDividedHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public void setOnClickAddListener(OnDividedClickListener onDividedClickListener2) {
        this.onDividedClickListener = onDividedClickListener2;
    }
}
