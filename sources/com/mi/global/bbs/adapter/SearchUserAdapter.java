package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.model.SearchUserModel;
import com.mi.global.bbs.ui.SearchActivity;
import com.mi.global.bbs.ui.SearchFragment;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.CheckUtil;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.view.CheckedTextView;
import com.mi.global.bbs.view.CircleImageView;
import com.mi.util.Utils;
import java.util.List;

public class SearchUserAdapter extends RecyclerView.Adapter implements InfiniteScrollListener.DataLoading.DataLoadingCallbacks {
    private static final int TYPE_LOADING_MORE = -1;
    private static final int TYPE_NORMAL = 0;
    private InfiniteScrollListener.DataLoading dataLoading;
    private List<SearchUserModel.DataBean.ResponseBean.DocsBean> items;
    private final LayoutInflater layoutInflater;
    /* access modifiers changed from: private */
    public Activity mContext;
    private CheckedTextView.OnCheckedChangeListener mOnCheckedChangeListener;
    private boolean showLoadingMore = false;
    private int totalNum = 0;

    public class UserItemHolder_ViewBinding implements Unbinder {
        private UserItemHolder target;

        @UiThread
        public UserItemHolder_ViewBinding(UserItemHolder userItemHolder, View view) {
            this.target = userItemHolder;
            userItemHolder.userItemTopTotal = (TextView) Utils.findRequiredViewAsType(view, R.id.user_item_top_total, "field 'userItemTopTotal'", TextView.class);
            userItemHolder.userItemTop = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.user_item_top, "field 'userItemTop'", LinearLayout.class);
            userItemHolder.userItemIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.user_item_icon, "field 'userItemIcon'", CircleImageView.class);
            userItemHolder.userItemMsg = (ImageView) Utils.findRequiredViewAsType(view, R.id.user_item_msg, "field 'userItemMsg'", ImageView.class);
            userItemHolder.userItemName = (TextView) Utils.findRequiredViewAsType(view, R.id.user_item_name, "field 'userItemName'", TextView.class);
            userItemHolder.userItemDes = (TextView) Utils.findRequiredViewAsType(view, R.id.user_item_des, "field 'userItemDes'", TextView.class);
            userItemHolder.userItemFuzzy = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.user_item_top_fuzzy, "field 'userItemFuzzy'", CheckedTextView.class);
            userItemHolder.mTopDivider = Utils.findRequiredView(view, R.id.top_divider, "field 'mTopDivider'");
        }

        @CallSuper
        public void unbind() {
            UserItemHolder userItemHolder = this.target;
            if (userItemHolder != null) {
                this.target = null;
                userItemHolder.userItemTopTotal = null;
                userItemHolder.userItemTop = null;
                userItemHolder.userItemIcon = null;
                userItemHolder.userItemMsg = null;
                userItemHolder.userItemName = null;
                userItemHolder.userItemDes = null;
                userItemHolder.userItemFuzzy = null;
                userItemHolder.mTopDivider = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public SearchUserAdapter(Activity activity, InfiniteScrollListener.DataLoading dataLoading2, List<SearchUserModel.DataBean.ResponseBean.DocsBean> list, int i) {
        this.layoutInflater = LayoutInflater.from(activity);
        this.mContext = activity;
        this.items = list;
        this.dataLoading = dataLoading2;
        dataLoading2.registerCallback(this);
        this.totalNum = i;
    }

    public void setOnCheckedChangeListener(CheckedTextView.OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }

    public void setTotalNum(int i) {
        this.totalNum = i;
    }

    public int getTotalNum() {
        return this.totalNum;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case -1:
                return new LoadingMoreHolder(this.layoutInflater.inflate(R.layout.bbs_infinite_loading, viewGroup, false));
            case 0:
                return createThreadItemHolder(viewGroup);
            default:
                return null;
        }
    }

    private UserItemHolder createThreadItemHolder(ViewGroup viewGroup) {
        return new UserItemHolder(this.layoutInflater.inflate(R.layout.bbs_search_user_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case -1:
                bindLoadingViewHolder((LoadingMoreHolder) viewHolder, i);
                return;
            case 0:
                bindUserDataHolder((UserItemHolder) viewHolder, i);
                return;
            default:
                return;
        }
    }

    private void bindUserDataHolder(UserItemHolder userItemHolder, int i) {
        final SearchUserModel.DataBean.ResponseBean.DocsBean docsBean = this.items.get(i);
        int i2 = 8;
        userItemHolder.userItemTop.setVisibility(i == 0 ? 0 : 8);
        View view = userItemHolder.mTopDivider;
        if (i == 0) {
            i2 = 0;
        }
        view.setVisibility(i2);
        TextHelper.setQuantityString(this.layoutInflater.getContext(), userItemHolder.userItemTopTotal, R.plurals._result_found, String.valueOf(this.totalNum));
        if (i == 0) {
            userItemHolder.userItemFuzzy.setToggleAble(true);
            userItemHolder.userItemFuzzy.setChecked(!TextUtils.isEmpty(Utils.Preference.getStringPref(this.mContext, SearchFragment.FUZZY, "")));
            userItemHolder.userItemFuzzy.setOnCheckedChangeListener(this.mOnCheckedChangeListener);
        }
        String str = docsBean.icon;
        if (!str.startsWith("http")) {
            str = "http:" + str;
        }
        ImageLoader.showHeadIcon(userItemHolder.userItemIcon, str);
        userItemHolder.userItemDes.setText(docsBean.groupid);
        userItemHolder.userItemName.setText(docsBean.username);
        userItemHolder.userItemIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(SearchUserAdapter.this.mContext, docsBean.id);
            }
        });
        final String userId = LoginManager.getInstance().getUserId();
        final String str2 = ConnectionHelper.getAppIndexUrl() + String.format(UrlAction.MSG_URL, new Object[]{CheckUtil.getCheckAes(docsBean.id)});
        userItemHolder.userItemMsg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!TextUtils.isEmpty(userId)) {
                    WebActivity.jump(SearchUserAdapter.this.mContext, str2);
                } else {
                    ((SearchActivity) SearchUserAdapter.this.mContext).gotoAccount();
                }
            }
        });
    }

    private void bindLoadingViewHolder(LoadingMoreHolder loadingMoreHolder, int i) {
        loadingMoreHolder.progress.setVisibility((i <= 0 || !this.dataLoading.isDataLoading()) ? 4 : 0);
    }

    public int getItemViewType(int i) {
        return (i >= getDataItemCount() || getDataItemCount() <= 0) ? -1 : 0;
    }

    public void clear() {
        if (this.items != null && this.items.size() > 0) {
            this.items.clear();
            notifyDataSetChanged();
        }
    }

    public void add(List<SearchUserModel.DataBean.ResponseBean.DocsBean> list) {
        if (list != null && list.size() > 0) {
            this.items.addAll(list);
            notifyDataSetChanged();
        }
    }

    public int getItemCount() {
        return getDataItemCount() + (this.showLoadingMore ? 1 : 0);
    }

    public int getDataItemCount() {
        return this.items.size();
    }

    private int getLoadingMoreItemPosition() {
        if (this.showLoadingMore) {
            return getItemCount() - 1;
        }
        return -1;
    }

    public void dataStartedLoading() {
        if (!this.showLoadingMore) {
            this.showLoadingMore = true;
            notifyItemInserted(getLoadingMoreItemPosition());
        }
    }

    public void dataFinishedLoading() {
        if (this.showLoadingMore) {
            int loadingMoreItemPosition = getLoadingMoreItemPosition();
            this.showLoadingMore = false;
            notifyItemRemoved(loadingMoreItemPosition);
        }
    }

    static class LoadingMoreHolder extends RecyclerView.ViewHolder {
        ProgressBar progress;

        LoadingMoreHolder(View view) {
            super(view);
            this.progress = (ProgressBar) view;
        }
    }

    static class UserItemHolder extends RecyclerView.ViewHolder {
        @BindView(2131494106)
        View mTopDivider;
        @BindView(2131494217)
        TextView userItemDes;
        @BindView(2131494222)
        CheckedTextView userItemFuzzy;
        @BindView(2131494218)
        CircleImageView userItemIcon;
        @BindView(2131494219)
        ImageView userItemMsg;
        @BindView(2131494220)
        TextView userItemName;
        @BindView(2131494221)
        LinearLayout userItemTop;
        @BindView(2131494223)
        TextView userItemTopTotal;

        public UserItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
