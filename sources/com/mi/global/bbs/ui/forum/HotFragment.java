package com.mi.global.bbs.ui.forum;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.CallbackManager;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.NewsForumAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.SubForumItem;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class HotFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnShareListener {
    BaseActivity activity;
    /* access modifiers changed from: private */
    public NewsForumAdapter adapter;
    protected CallbackManager callbackManager;
    /* access modifiers changed from: private */
    public boolean canLoadMore = false;
    @BindView(2131493155)
    ObservableRecyclerView commonRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout commonRefreshView;
    private LoadMoreManager loadMoreManager;
    @BindView(2131493154)
    ProgressBar mProgress;
    /* access modifiers changed from: private */
    public int page = 1;
    private String pageName;
    private int pageSize = 10;

    static /* synthetic */ int access$108(HotFragment hotFragment) {
        int i = hotFragment.page;
        hotFragment.page = i + 1;
        return i;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_common_refresh_loading_layout, viewGroup, false);
        this.pageName = Constants.PageFragment.PAGE_HOT;
        ButterKnife.bind((Object) this, inflate);
        this.callbackManager = CallbackManager.Factory.create();
        initView();
        getData();
        return inflate;
    }

    /* access modifiers changed from: private */
    public void getData() {
        if (this.page > 1) {
            this.loadMoreManager.loadStarted();
        }
        ApiClient.getHotList(this.page, this.pageSize, bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<SubForumItem>() {
            public void accept(@NonNull SubForumItem subForumItem) throws Exception {
                HotFragment.this.dismissProgress();
                if (subForumItem != null && subForumItem.data != null) {
                    if (HotFragment.this.page == 0) {
                        HotFragment.this.adapter.clear();
                    }
                    HotFragment.this.adapter.setData(subForumItem.data);
                    if (subForumItem.data.threadlist == null || subForumItem.data.threadlist.size() <= 0) {
                        boolean unused = HotFragment.this.canLoadMore = false;
                    } else {
                        boolean unused2 = HotFragment.this.canLoadMore = true;
                    }
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                HotFragment.this.dismissProgress();
            }
        });
    }

    private void initView() {
        this.commonRefreshView.setOnRefreshListener(this);
        this.commonRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(getActivity());
        wrapContentLinearLayoutManager.setOrientation(1);
        this.commonRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.loadMoreManager = new LoadMoreManager();
        this.adapter = new NewsForumAdapter(getActivity(), this.loadMoreManager, this.pageName);
        this.adapter.setOnShareListener(this);
        this.adapter.setOnRecycleClickListener(new NewsForumAdapter.onRecycleClickListener() {
            public void onClickYoutube(String str) {
            }
        });
        this.commonRecycleView.setAdapter(this.adapter);
        this.commonRecycleView.setOnScrollListener(new InfiniteScrollListener(wrapContentLinearLayoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (HotFragment.this.canLoadMore) {
                    HotFragment.access$108(HotFragment.this);
                    HotFragment.this.getData();
                }
            }
        });
    }

    public void onAttach(Activity activity2) {
        super.onAttach(activity2);
        if (activity2 instanceof BaseActivity) {
            this.activity = (BaseActivity) activity2;
        }
    }

    public void onRefresh() {
        this.page = 1;
        getData();
    }

    /* access modifiers changed from: private */
    public void dismissProgress() {
        if (this.commonRefreshView != null) {
            this.commonRefreshView.setRefreshing(false);
        }
        if (this.mProgress != null && this.mProgress.getVisibility() == 0) {
            this.mProgress.setVisibility(8);
        }
        this.loadMoreManager.loadFinished();
    }

    private void share(String str, String str2) {
        new ShareDialog(getActivity()).setShareTitle(str).setShareUrl(str2).setCallbackManager(this.callbackManager).show();
    }

    public void onShare(String str, String str2) {
        share(str, str2);
    }
}
