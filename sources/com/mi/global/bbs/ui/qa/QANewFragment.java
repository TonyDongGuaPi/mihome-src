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
import com.mi.global.bbs.ui.qa.QANewItem;
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

public class QANewFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final int PER_PAGE = 10;
    private static final String QA_NEW_CACHE = "qa_new_fragment_cache";
    private QANewAdapter adapter;
    private LinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public LoadMoreManager loadMoreManager;
    @BindView(2131493155)
    RecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;
    /* access modifiers changed from: private */
    public int page = 0;
    private List<QANewItem.DataBean.NewItem> qaHomeNewItems = new ArrayList();
    /* access modifiers changed from: private */
    public int total = 0;

    static /* synthetic */ int access$610(QANewFragment qANewFragment) {
        int i = qANewFragment.page;
        qANewFragment.page = i - 1;
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
        this.adapter = new QANewAdapter((BaseActivity) getActivity(), this.loadMoreManager);
        this.layoutManager = new LinearLayoutManager(getActivity());
        this.mRecycleView.setLayoutManager(this.layoutManager);
        this.mRecycleView.setAdapter(this.adapter);
        this.mRecycleView.addOnScrollListener(new InfiniteScrollListener(this.layoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (!QANewFragment.this.loadMoreManager.isDataLoading()) {
                    QANewFragment.this.loadMore();
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
        String stringPref = Utils.Preference.getStringPref(getActivity(), QA_NEW_CACHE, "");
        if (!TextUtils.isEmpty(stringPref)) {
            dismissProgress();
            handleResponse((QANewItem) JsonParser.parse(stringPref, QANewItem.class));
        }
    }

    private void getData() {
        if (this.page > 0) {
            this.loadMoreManager.loadStarted();
        }
        ApiClient.getQANewList(this.page * 10, 10, bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<QANewItem>() {
            public void accept(@NonNull QANewItem qANewItem) throws Exception {
                QANewFragment.this.dismissProgress();
                if (qANewItem == null || qANewItem.getErrno() != 0 || qANewItem.data == null) {
                    int unused = QANewFragment.this.total = 0;
                    if (QANewFragment.this.page > 0) {
                        QANewFragment.access$610(QANewFragment.this);
                    }
                    QANewFragment.this.toast(qANewItem.getErrmsg());
                    return;
                }
                QANewFragment.this.saveQAHomeTrendItemCache(new Gson().toJson((Object) qANewItem));
                QANewFragment.this.handleResponse(qANewItem);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                int unused = QANewFragment.this.total = 0;
                QANewFragment.this.dismissProgress();
            }
        });
    }

    /* access modifiers changed from: private */
    public void saveQAHomeTrendItemCache(String str) {
        Utils.Preference.setStringPref(getActivity(), QA_NEW_CACHE, str);
    }

    /* access modifiers changed from: private */
    public void handleResponse(QANewItem qANewItem) {
        if (qANewItem == null || qANewItem.data == null || qANewItem.data.list == null || qANewItem.data.list.size() <= 0) {
            this.total = 0;
            updateUI();
            return;
        }
        this.total = qANewItem.data.list.size();
        for (int i = 0; i < this.total; i++) {
            this.qaHomeNewItems.add(qANewItem.data.list.get(i));
        }
        this.adapter.addData(this.qaHomeNewItems);
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
        this.qaHomeNewItems.clear();
        try {
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_QA, Constants.ClickEvent.QA_CLICK_NEW_NO, "" + this.page);
    }
}
