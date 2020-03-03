package com.mi.global.bbs.ui.qa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.utils.Utils;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class QATrendingFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final int PER_PAGE = 10;
    private static final String QA_TRENDING_CACHE = "qa_trending_fragment_cache";
    private QATrendingAdapter adapter;
    private LinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public LoadMoreManager loadMoreManager;
    @BindView(2131493155)
    RecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;
    /* access modifiers changed from: private */
    public int page = 0;
    List<QAHomeTrendWrapper> qaHomeTrendItems = new ArrayList();
    /* access modifiers changed from: private */
    public int total = 0;

    static /* synthetic */ int access$610(QATrendingFragment qATrendingFragment) {
        int i = qATrendingFragment.page;
        qATrendingFragment.page = i - 1;
        return i;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_qa_home_list_layout, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        initView();
        getData();
        return inflate;
    }

    private void initView() {
        this.loadMoreManager = new LoadMoreManager();
        this.mRefreshView.setOnRefreshListener(this);
        this.mRefreshView.setVisibility(8);
        this.mRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.adapter = new QATrendingAdapter((BaseActivity) getActivity(), this.loadMoreManager);
        this.layoutManager = new LinearLayoutManager(getActivity());
        this.mRecycleView.setLayoutManager(this.layoutManager);
        this.mRecycleView.setAdapter(this.adapter);
        this.mRecycleView.addOnScrollListener(new InfiniteScrollListener(this.layoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (!QATrendingFragment.this.loadMoreManager.isDataLoading()) {
                    QATrendingFragment.this.loadMore();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void loadMore() {
        if (this.total > 0) {
            this.page++;
            getData();
        }
    }

    public void setPadding(int i) {
        if (this.mRecycleView != null) {
            this.mRecycleView.setPadding(0, 0, 0, i);
        }
    }

    private void checkIfHaveCache() {
        String stringPref = Utils.Preference.getStringPref(getActivity(), QA_TRENDING_CACHE, "");
        if (!TextUtils.isEmpty(stringPref)) {
            dismissProgress();
            handleResponse((QAHomeTrendItem) JsonParser.parse(stringPref, QAHomeTrendItem.class));
        }
    }

    private void getData() {
        if (this.page > 0) {
            this.loadMoreManager.loadStarted();
        }
        ApiClient.getQATrendingList(this.page * 10, 10, bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<QAHomeTrendItem>() {
            public void accept(@NonNull QAHomeTrendItem qAHomeTrendItem) throws Exception {
                QATrendingFragment.this.dismissProgress();
                if (qAHomeTrendItem == null || qAHomeTrendItem.getErrno() != 0 || qAHomeTrendItem.data == null) {
                    int unused = QATrendingFragment.this.total = 0;
                    if (QATrendingFragment.this.page > 0) {
                        QATrendingFragment.access$610(QATrendingFragment.this);
                    }
                    QATrendingFragment.this.toast(qAHomeTrendItem.getErrmsg());
                    return;
                }
                QATrendingFragment.this.saveQAHomeTrendItemCache(new Gson().toJson((Object) qAHomeTrendItem));
                QATrendingFragment.this.handleResponse(qAHomeTrendItem);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                int unused = QATrendingFragment.this.total = 0;
                QATrendingFragment.this.dismissProgress();
            }
        });
    }

    /* access modifiers changed from: private */
    public void saveQAHomeTrendItemCache(String str) {
        Utils.Preference.setStringPref(getActivity(), QA_TRENDING_CACHE, str);
    }

    /* access modifiers changed from: private */
    public void handleResponse(QAHomeTrendItem qAHomeTrendItem) {
        if (qAHomeTrendItem == null || qAHomeTrendItem.data == null || qAHomeTrendItem.data.list == null || qAHomeTrendItem.data.list.size() <= 0) {
            this.total = 0;
            updateUI();
            return;
        }
        this.total = qAHomeTrendItem.data.list.size();
        for (int i = 0; i < this.total; i++) {
            QAHomeTrendWrapper qAHomeTrendWrapper = new QAHomeTrendWrapper();
            qAHomeTrendWrapper.tid = qAHomeTrendItem.data.list.get(i).tid;
            qAHomeTrendWrapper.subject = qAHomeTrendItem.data.list.get(i).subject;
            qAHomeTrendWrapper.views = qAHomeTrendItem.data.list.get(i).views;
            qAHomeTrendWrapper.replies = qAHomeTrendItem.data.list.get(i).replies;
            qAHomeTrendWrapper.fname = qAHomeTrendItem.data.list.get(i).fname;
            if (qAHomeTrendItem.data.list.get(i).answer != null) {
                qAHomeTrendWrapper.author = qAHomeTrendItem.data.list.get(i).answer.author;
                qAHomeTrendWrapper.dateline = qAHomeTrendItem.data.list.get(i).answer.dateline;
                qAHomeTrendWrapper.message = qAHomeTrendItem.data.list.get(i).answer.message;
                qAHomeTrendWrapper.avatar = qAHomeTrendItem.data.list.get(i).answer.avatar;
            } else {
                qAHomeTrendWrapper.author = "";
                qAHomeTrendWrapper.dateline = "";
                qAHomeTrendWrapper.message = "";
                qAHomeTrendWrapper.avatar = "";
            }
            qAHomeTrendWrapper.link = "";
            qAHomeTrendWrapper.pic_url = "";
            qAHomeTrendWrapper.type = 0;
            this.qaHomeTrendItems.add(qAHomeTrendWrapper);
            if ((i == 4 || i == 9) && (((this.page * 10) + i) / 4) - 1 < qAHomeTrendItem.data.banner.size()) {
                QAHomeTrendWrapper qAHomeTrendWrapper2 = new QAHomeTrendWrapper();
                qAHomeTrendWrapper2.tid = "";
                qAHomeTrendWrapper2.subject = "";
                qAHomeTrendWrapper2.views = "";
                qAHomeTrendWrapper2.replies = "";
                qAHomeTrendWrapper2.fname = "";
                qAHomeTrendWrapper2.author = "";
                qAHomeTrendWrapper2.dateline = "";
                qAHomeTrendWrapper2.message = "";
                qAHomeTrendWrapper2.avatar = "";
                qAHomeTrendWrapper2.link = qAHomeTrendItem.data.banner.get((((this.page * 10) + i) / 4) - 1).link;
                qAHomeTrendWrapper2.pic_url = qAHomeTrendItem.data.banner.get((((this.page * 10) + i) / 4) - 1).pic_url;
                qAHomeTrendWrapper2.type = 1;
                this.qaHomeTrendItems.add(qAHomeTrendWrapper2);
            }
        }
        this.adapter.addData(this.qaHomeTrendItems);
        this.adapter.notifyDataSetChanged();
        updateUI();
    }

    private void updateUI() {
        if (this.adapter.getDataItemCount() > 0) {
            this.mRecycleView.setVisibility(0);
        } else {
            this.mRecycleView.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void dismissProgress() {
        this.loadMoreManager.loadFinished();
        if (this.mRefreshView != null) {
            this.mRefreshView.setVisibility(0);
            this.mRefreshView.setRefreshing(false);
        }
    }

    public void onRefresh() {
        this.page = 0;
        this.qaHomeTrendItems.clear();
        try {
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_QA, Constants.ClickEvent.QA_CLICK_THREAD_NO, "" + this.page);
    }
}
