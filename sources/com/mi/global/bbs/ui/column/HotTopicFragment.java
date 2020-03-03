package com.mi.global.bbs.ui.column;

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
import com.mi.global.bbs.adapter.HotTopicsAdapter;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.ColumnArticleModel;
import com.mi.global.bbs.model.ColumnHomeData;
import com.mi.global.bbs.model.ColumnHomeModel;
import com.mi.global.bbs.model.HomeFormTitle;
import com.mi.global.bbs.ui.HeaderViewPagerFragment;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;

public class HotTopicFragment extends HeaderViewPagerFragment implements SwipeRefreshLayout.OnRefreshListener, OnShareListener {
    /* access modifiers changed from: private */
    public HotTopicsAdapter adapter;
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
    ColumnRefreshListener refreshListener;

    public interface ColumnRefreshListener {
        void onRefreshColumn();
    }

    static /* synthetic */ int access$108(HotTopicFragment hotTopicFragment) {
        int i = hotTopicFragment.page;
        hotTopicFragment.page = i + 1;
        return i;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_common_refresh_loading_layout, viewGroup, false);
        this.pageName = Constants.PageFragment.PAGE_COLUMN_INDEX;
        ButterKnife.bind((Object) this, inflate);
        this.callbackManager = CallbackManager.Factory.create();
        initView();
        return inflate;
    }

    public void setPadding(int i) {
        if (this.commonRecycleView != null) {
            this.commonRecycleView.setPadding(0, 0, 0, i);
        }
    }

    public void setOnRefreshColumnListener(ColumnRefreshListener columnRefreshListener) {
        this.refreshListener = columnRefreshListener;
    }

    public void setFirstPageData(ColumnHomeModel columnHomeModel) {
        dismissProgress();
        this.adapter.clear();
        ArrayList arrayList = new ArrayList();
        ColumnHomeData columnHomeData = new ColumnHomeData();
        columnHomeData.setFormTitle(new HomeFormTitle(getString(R.string.str_column), "http://c.mi.com"));
        arrayList.add(columnHomeData);
        ColumnHomeData columnHomeData2 = new ColumnHomeData();
        columnHomeData2.setTopicColumnBeenList(columnHomeModel.data.recommend);
        arrayList.add(columnHomeData2);
        ColumnHomeData columnHomeData3 = new ColumnHomeData();
        columnHomeData3.setFormTitle(new HomeFormTitle(getString(R.string.str_Articles), ""));
        arrayList.add(columnHomeData3);
        for (ColumnHomeModel.DataBean.ColumnArticle topicArticleBean : columnHomeModel.data.acticles) {
            ColumnHomeData columnHomeData4 = new ColumnHomeData();
            columnHomeData4.setTopicArticleBean(topicArticleBean);
            arrayList.add(columnHomeData4);
        }
        this.adapter.setData(arrayList);
        if (columnHomeModel.data.acticles == null || columnHomeModel.data.acticles.size() <= 0) {
            this.canLoadMore = false;
        } else {
            this.canLoadMore = true;
        }
    }

    /* access modifiers changed from: private */
    public void getData() {
        if (this.page > 0 && this.canLoadMore) {
            this.loadMoreManager.loadStarted();
        }
        ApiClient.getColumnArticles(this.page, this.pageSize, "", bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<ColumnArticleModel>() {
            public void accept(@NonNull ColumnArticleModel columnArticleModel) throws Exception {
                HotTopicFragment.this.dismissProgress();
                if (columnArticleModel == null || columnArticleModel.data == null || columnArticleModel.data.list == null || columnArticleModel.data.list.size() <= 0) {
                    boolean unused = HotTopicFragment.this.canLoadMore = false;
                    return;
                }
                if (HotTopicFragment.this.page == 1) {
                    HotTopicFragment.this.adapter.clear();
                }
                ArrayList arrayList = new ArrayList();
                new ColumnHomeData();
                for (ColumnHomeModel.DataBean.ColumnArticle topicArticleBean : columnArticleModel.data.list) {
                    ColumnHomeData columnHomeData = new ColumnHomeData();
                    columnHomeData.setTopicArticleBean(topicArticleBean);
                    arrayList.add(columnHomeData);
                }
                HotTopicFragment.this.adapter.setData(arrayList);
                if (columnArticleModel.data.list.size() > 0) {
                    boolean unused2 = HotTopicFragment.this.canLoadMore = true;
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                HotTopicFragment.this.dismissProgress();
                boolean unused = HotTopicFragment.this.canLoadMore = false;
            }
        });
    }

    private void initView() {
        this.commonRefreshView.setOnRefreshListener(this);
        this.commonRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(getActivity(), 1, false);
        this.commonRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.loadMoreManager = new LoadMoreManager();
        this.adapter = new HotTopicsAdapter(getActivity(), this.loadMoreManager, this.pageName);
        this.adapter.setOnShareListener(this);
        this.commonRecycleView.setAdapter(this.adapter);
        this.commonRecycleView.setOnScrollListener(new InfiniteScrollListener(wrapContentLinearLayoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (HotTopicFragment.this.canLoadMore) {
                    HotTopicFragment.access$108(HotTopicFragment.this);
                    HotTopicFragment.this.getData();
                }
            }
        });
    }

    public void onRefresh() {
        this.page = 1;
        if (this.refreshListener != null) {
            this.refreshListener.onRefreshColumn();
        }
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

    public View getScrollableView() {
        return this.commonRecycleView;
    }
}
