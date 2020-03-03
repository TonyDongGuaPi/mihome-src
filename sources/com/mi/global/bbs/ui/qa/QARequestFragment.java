package com.mi.global.bbs.ui.qa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.qa.QARequestItem;
import com.mi.global.bbs.ui.qa.QARequestsAdapter;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.view.CircleImageView;
import com.mi.global.bbs.view.Editor.FontTextView;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class QARequestFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, QARequestsAdapter.OnClickIgnoreListener {
    public static final int DELETE_QUESTION_THREAD_TYPE = 1;
    public static final int PER_PAGE = 20;
    public static final int QA_REQUEST_BY_OTHER = 0;
    public static final int QA_REQUEST_BY_SUBFORM = 1;
    private QARequestsAdapter adapter;
    @BindView(2131492976)
    TextView answer;
    @BindView(2131492978)
    LinearLayout answerImgLayout;
    @BindView(2131493444)
    TextView ignore;
    @BindView(2131493484)
    TextView itemContent;
    @BindView(2131493492)
    CircleImageView itemIcon;
    @BindView(2131493501)
    TextView itemName;
    @BindView(2131494092)
    TextView itemTime;
    private LinearLayoutManager layoutManager;
    @BindView(2131493585)
    LinearLayout listLayout;
    /* access modifiers changed from: private */
    public LoadMoreManager loadMoreManager;
    @BindView(2131493155)
    RecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;
    @BindView(2131493267)
    TextView noLoginBtn;
    @BindView(2131493749)
    RelativeLayout noLoginLayout;
    /* access modifiers changed from: private */
    public OnLoginClickListener onLoginClickListener;
    /* access modifiers changed from: private */
    public int page = 0;
    @BindView(2131493866)
    LinearLayout qaRequestItemLayout;
    @BindView(2131493865)
    View qaRequestItemLayoutDivider;
    @BindView(2131493868)
    View qaRequestViewMoreDivider;
    @BindView(2131493870)
    LinearLayout questionEmptyLayout;
    /* access modifiers changed from: private */
    public AtomicInteger requentCount = new AtomicInteger(0);
    @BindView(2131493889)
    LinearLayout requestEmptyLayout;
    private List<QARequestItem.RequestItem> requestItemList = new ArrayList();
    @BindView(2131493864)
    FontTextView requestMore;
    /* access modifiers changed from: private */
    public int total = 0;

    public interface OnLoginClickListener {
        void onClick();
    }

    static /* synthetic */ int access$910(QARequestFragment qARequestFragment) {
        int i = qARequestFragment.page;
        qARequestFragment.page = i - 1;
        return i;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_qa_request_list_layout, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        initView();
        return inflate;
    }

    private void initView() {
        this.loadMoreManager = new LoadMoreManager();
        this.mRefreshView.setOnRefreshListener(this);
        this.mRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.adapter = new QARequestsAdapter((BaseActivity) getActivity(), this.loadMoreManager, QARequestsAdapter.FROM_QAREQUEST_FRAGMENT);
        this.adapter.setClickIgnoreListener(this);
        this.layoutManager = new LinearLayoutManager(getActivity());
        this.mRecycleView.setLayoutManager(this.layoutManager);
        this.mRecycleView.setAdapter(this.adapter);
        this.mRecycleView.addOnScrollListener(new InfiniteScrollListener(this.layoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (!QARequestFragment.this.loadMoreManager.isDataLoading()) {
                    QARequestFragment.this.loadMore();
                }
            }
        });
        setOnClickListener();
    }

    private void loginState() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                QARequestFragment.this.noLoginLayout.setVisibility(8);
                QARequestFragment.this.listLayout.setVisibility(0);
            }
        });
    }

    private void notLogState() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                QARequestFragment.this.listLayout.setVisibility(8);
                QARequestFragment.this.noLoginLayout.setVisibility(0);
                QARequestFragment.this.noLoginBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        QARequestFragment.this.onLoginClickListener.onClick();
                    }
                });
            }
        });
    }

    private void setOnClickListener() {
        this.requestMore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QARequestMoreActivity.jump(QARequestFragment.this.getActivity());
            }
        });
    }

    public void setPadding(int i) {
        if (this.mRecycleView != null) {
            this.mRecycleView.setPadding(0, 0, 0, i);
        }
    }

    /* access modifiers changed from: private */
    public void loadMore() {
        if (this.total > 0) {
            this.page++;
            getQuestionData();
        }
    }

    public void getData() {
        if (this.page > 0) {
            this.loadMoreManager.loadStarted();
        }
        getRequestList();
        getQuestionData();
        updateLoginUI();
    }

    private void updateLoginUI() {
        if (LoginManager.getInstance().hasLogin()) {
            loginState();
        } else {
            notLogState();
        }
    }

    /* access modifiers changed from: private */
    public void getRequestList() {
        this.requentCount.incrementAndGet();
        ApiClient.getRequestList(0, 0, bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<QARequestItem>() {
            public void accept(@NonNull QARequestItem qARequestItem) throws Exception {
                QARequestFragment.this.requentCount.decrementAndGet();
                QARequestFragment.this.dismissProgress();
                QARequestFragment.this.handleRequestResponse(qARequestItem);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                QARequestFragment.this.requentCount.decrementAndGet();
                QARequestFragment.this.dismissProgress();
                QARequestFragment.this.updateRequestUI((QARequestItem.RequestItem) null, 0);
            }
        });
    }

    private void getQuestionData() {
        this.requentCount.incrementAndGet();
        ApiClient.getRequestList(1, this.page * 20, bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<QARequestItem>() {
            public void accept(@NonNull QARequestItem qARequestItem) throws Exception {
                QARequestFragment.this.requentCount.decrementAndGet();
                QARequestFragment.this.dismissProgress();
                if (qARequestItem == null || qARequestItem.getErrno() != 0 || qARequestItem.data == null || qARequestItem.data == null || qARequestItem.data.size() <= 0) {
                    int unused = QARequestFragment.this.total = 0;
                    if (QARequestFragment.this.page > 0) {
                        QARequestFragment.access$910(QARequestFragment.this);
                    }
                    QARequestFragment.this.updateQuestionUI();
                    return;
                }
                QARequestFragment.this.handleQuestionResponse(qARequestItem);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                QARequestFragment.this.requentCount.decrementAndGet();
                int unused = QARequestFragment.this.total = 0;
                if (QARequestFragment.this.page > 0) {
                    QARequestFragment.access$910(QARequestFragment.this);
                }
                QARequestFragment.this.dismissProgress();
                QARequestFragment.this.updateQuestionUI();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleRequestResponse(QARequestItem qARequestItem) {
        if (qARequestItem.getErrno() != 0 || qARequestItem == null || qARequestItem.data == null || qARequestItem.data == null || qARequestItem.data.size() <= 0) {
            updateRequestUI((QARequestItem.RequestItem) null, 0);
        } else {
            updateRequestUI(qARequestItem.data.get(0), qARequestItem.data.size());
        }
    }

    /* access modifiers changed from: private */
    public void updateRequestUI(QARequestItem.RequestItem requestItem, int i) {
        if (i == 0) {
            this.qaRequestItemLayout.setVisibility(8);
            this.qaRequestItemLayoutDivider.setVisibility(8);
            this.requestMore.setVisibility(8);
            this.qaRequestViewMoreDivider.setVisibility(0);
            this.requestEmptyLayout.setVisibility(0);
        } else if (i == 1) {
            this.qaRequestItemLayout.setVisibility(0);
            this.qaRequestItemLayoutDivider.setVisibility(0);
            this.requestMore.setVisibility(8);
            this.requestEmptyLayout.setVisibility(8);
            if (requestItem.icon != null) {
                ImageLoader.showImage(this.itemIcon, requestItem.icon, (RequestOptions) new RequestOptions().a(R.drawable.bbs_icon_default_head));
            } else {
                Glide.a(getActivity()).a(Integer.valueOf(R.drawable.bbs_icon_default_head)).a((ImageView) this.itemIcon);
            }
            TextView textView = this.itemName;
            textView.setText(requestItem.fromname + " " + getResources().getString(R.string.qa_question_invite_name_str));
            this.itemContent.setText(requestItem.subject);
            this.qaRequestViewMoreDivider.setVisibility(0);
            this.itemTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Long.valueOf(Long.parseLong(requestItem.dateline) * 1000)));
            if (requestItem.ignore.equals("1")) {
                this.answer.setVisibility(8);
                this.ignore.setVisibility(0);
            } else {
                this.answer.setVisibility(0);
                this.ignore.setVisibility(8);
            }
        } else {
            this.qaRequestItemLayout.setVisibility(0);
            this.qaRequestItemLayoutDivider.setVisibility(0);
            this.requestMore.setVisibility(0);
            this.requestEmptyLayout.setVisibility(8);
            this.qaRequestViewMoreDivider.setVisibility(8);
            if (requestItem.icon != null) {
                ImageLoader.showImage(this.itemIcon, requestItem.icon, (RequestOptions) new RequestOptions().a(R.drawable.bbs_icon_default_head));
            } else {
                Glide.a(getActivity()).a(Integer.valueOf(R.drawable.bbs_icon_default_head)).a((ImageView) this.itemIcon);
            }
            TextView textView2 = this.itemName;
            textView2.setText(requestItem.fromname + " " + getResources().getString(R.string.qa_question_invite_name_str));
            this.itemContent.setText(requestItem.subject);
            this.itemTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Long.valueOf(Long.parseLong(requestItem.dateline) * 1000)));
            if (requestItem.ignore.equals("1")) {
                this.answer.setVisibility(8);
                this.ignore.setVisibility(0);
            } else {
                this.answer.setVisibility(0);
                this.ignore.setVisibility(8);
            }
        }
        setRequestItemOnClickListener(requestItem);
    }

    private void setRequestItemOnClickListener(final QARequestItem.RequestItem requestItem) {
        this.qaRequestItemLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jump(QARequestFragment.this.getActivity(), ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{requestItem.tid})));
            }
        });
        this.answerImgLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (QARequestFragment.this.answer.getVisibility() == 0) {
                    QARequestFragment.this.ignore.setVisibility(0);
                    QARequestFragment.this.answer.setVisibility(8);
                    return;
                }
                QARequestFragment.this.ignore.setVisibility(8);
                QARequestFragment.this.answer.setVisibility(0);
            }
        });
        this.answer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jump(QARequestFragment.this.getActivity(), ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{requestItem.tid})), true);
            }
        });
        this.ignore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QARequestFragment.this.deleteThread(requestItem.id);
            }
        });
    }

    /* access modifiers changed from: private */
    public void deleteThread(String str) {
        ApiClient.deleteQuestionThread(1, Integer.parseInt(str), bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<QARequestItem>() {
            public void accept(@NonNull QARequestItem qARequestItem) throws Exception {
                QARequestFragment.this.getRequestList();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                QARequestFragment.this.requestEmptyLayout.setVisibility(0);
                QARequestFragment.this.requestMore.setVisibility(8);
                QARequestFragment.this.qaRequestItemLayout.setVisibility(8);
                QARequestFragment.this.qaRequestItemLayoutDivider.setVisibility(8);
            }
        });
    }

    private void deleteQuestionThread(int i) {
        ApiClient.deleteQuestionThread(1, i, bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<QARequestItem>() {
            public void accept(@NonNull QARequestItem qARequestItem) throws Exception {
                QARequestFragment.this.updateQuestionUI();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                QARequestFragment.this.updateQuestionUI();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleQuestionResponse(QARequestItem qARequestItem) {
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
            this.questionEmptyLayout.setVisibility(8);
            return;
        }
        this.mRecycleView.setVisibility(8);
        this.questionEmptyLayout.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void dismissProgress() {
        if (this.requentCount.get() == 0) {
            this.loadMoreManager.loadFinished();
            if (this.mRefreshView != null) {
                this.mRefreshView.setVisibility(0);
                this.mRefreshView.setRefreshing(false);
            }
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

    public void setOnLoginClickListener(OnLoginClickListener onLoginClickListener2) {
        this.onLoginClickListener = onLoginClickListener2;
    }

    public void onResume() {
        super.onResume();
        getData();
    }
}
