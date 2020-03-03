package com.mi.global.bbs.ui.qa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.ui.qa.QARequestItem;
import com.mi.global.bbs.ui.qa.QARequestsAdapter;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class QARequestMoreActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, QARequestsAdapter.OnClickIgnoreListener {
    private QARequestsAdapter adapter;
    private LinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public LoadMoreManager loadMoreManager;
    @BindView(2131493155)
    RecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;
    /* access modifiers changed from: private */
    public int page = 0;
    private List<QARequestItem.RequestItem> requestItemList = new ArrayList();
    /* access modifiers changed from: private */
    public int total = 0;

    static /* synthetic */ int access$510(QARequestMoreActivity qARequestMoreActivity) {
        int i = qARequestMoreActivity.page;
        qARequestMoreActivity.page = i - 1;
        return i;
    }

    public static void jump(Context context) {
        context.startActivity(new Intent(context, QARequestMoreActivity.class));
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        this.contentNeedMargin = true;
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_qa_home_list_layout);
        ButterKnife.bind((Activity) this);
        setTitleAndBack(getResources().getString(R.string.qa_request_top_request), this.titleBackListener);
        initView();
        getData();
    }

    private void initView() {
        this.loadMoreManager = new LoadMoreManager();
        this.mRefreshView.setOnRefreshListener(this);
        this.mRefreshView.setVisibility(8);
        this.mRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.adapter = new QARequestsAdapter(this, this.loadMoreManager, QARequestsAdapter.FROM_QAREQUEST_MORE_FRAGMENT);
        this.adapter.setClickIgnoreListener(this);
        this.layoutManager = new LinearLayoutManager(this);
        this.mRecycleView.setLayoutManager(this.layoutManager);
        this.mRecycleView.setAdapter(this.adapter);
        this.mRecycleView.addOnScrollListener(new InfiniteScrollListener(this.layoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (!QARequestMoreActivity.this.loadMoreManager.isDataLoading()) {
                    QARequestMoreActivity.this.loadMore();
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

    private void getData() {
        if (this.page > 0) {
            this.loadMoreManager.loadStarted();
        }
        ApiClient.getRequestList(0, this.page * 20, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<QARequestItem>() {
            public void accept(@NonNull QARequestItem qARequestItem) throws Exception {
                QARequestMoreActivity.this.dismissProgress();
                if (qARequestItem == null || qARequestItem.getErrno() != 0 || qARequestItem.data == null) {
                    QARequestMoreActivity.this.handleQuestionResponse(qARequestItem);
                    int unused = QARequestMoreActivity.this.total = 0;
                    if (QARequestMoreActivity.this.page > 0) {
                        QARequestMoreActivity.access$510(QARequestMoreActivity.this);
                        return;
                    }
                    return;
                }
                QARequestMoreActivity.this.handleQuestionResponse(qARequestItem);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                int unused = QARequestMoreActivity.this.total = 0;
                QARequestMoreActivity.this.dismissProgress();
                QARequestMoreActivity.this.handleQuestionResponse((QARequestItem) null);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleQuestionResponse(QARequestItem qARequestItem) {
        if (qARequestItem == null || qARequestItem.data == null || qARequestItem.data == null || qARequestItem.data.size() <= 0) {
            this.total = 0;
            updateQuestionUI();
            return;
        }
        this.total = qARequestItem.data.size();
        for (int i = 0; i < this.total; i++) {
            this.requestItemList.add(qARequestItem.data.get(i));
        }
        this.adapter.addData(this.requestItemList);
        this.adapter.notifyDataSetChanged();
        updateQuestionUI();
    }

    /* access modifiers changed from: private */
    public void updateQuestionUI() {
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
        this.requestItemList.clear();
        try {
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickIgnore(int i) {
        deleteQuestionThread(i);
    }

    private void deleteQuestionThread(int i) {
        ApiClient.deleteQuestionThread(1, i, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<QARequestItem>() {
            public void accept(@NonNull QARequestItem qARequestItem) throws Exception {
                QARequestMoreActivity.this.updateQuestionUI();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                QARequestMoreActivity.this.updateQuestionUI();
            }
        });
    }
}
