package com.mi.global.bbs.ui.qa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.google.gson.JsonObject;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.SearchUserModel;
import com.mi.global.bbs.ui.qa.QAInviteAdapter;
import com.mi.global.bbs.ui.qa.QAInviteItem;
import com.mi.global.bbs.utils.ImeUtils;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.view.Editor.ClearEditText;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class QAInviteActivity extends BaseActivity implements QAInviteAdapter.OnClickAddListener {
    private static final int IS_GET_USER = 1;
    public static final int PER_PAGE = 10;
    public static final String QA_INVITE_COUNT_QARAM = "count";
    public static final String QA_INVITE_FID_QARAM = "fid";
    public static final String QA_INVITE_IDS_QARAM = "ids";
    public static final String QA_INVITE_LIST_QARAM = "list";
    public static final String QA_INVITE_PRE_FID_QARAM = "prefid";
    private static final String SEARCH_OPTION = "1";
    private static final int SEARCH_ORDER_BY = 0;
    private static final int SEARCH_USER_TYPE = 1;
    private QAInviteAdapter adapter;
    @BindView(2131493856)
    TextView countTextView;
    @BindView(2131493858)
    RelativeLayout doneLayout;
    private String fid = "";
    @BindView(2131493354)
    RelativeLayout frameRecycleView;
    private int inviteCount = 0;
    private List<String> inviteIds = new ArrayList();
    private List<QAInviteItem.DataBean> inviteList = new ArrayList();
    private LinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public LoadMoreManager loadMoreManager;
    @BindView(2131493355)
    ProgressBar mProgress;
    @BindView(2131493155)
    ObservableRecyclerView mRecycleView;
    @BindView(2131493941)
    View mSearchToolbarAgent;
    @BindView(2131494034)
    ViewStub noResult;
    /* access modifiers changed from: private */
    public int page = 0;
    @BindView(2131493946)
    ImageView searchBack;
    @BindView(2131493944)
    ClearEditText searchView;
    /* access modifiers changed from: private */
    public int total = 0;

    static /* synthetic */ int access$208(QAInviteActivity qAInviteActivity) {
        int i = qAInviteActivity.page;
        qAInviteActivity.page = i + 1;
        return i;
    }

    static /* synthetic */ int access$210(QAInviteActivity qAInviteActivity) {
        int i = qAInviteActivity.page;
        qAInviteActivity.page = i - 1;
        return i;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getParam();
        setContentView(R.layout.bbs_fragment_qa_invite);
        ButterKnife.bind((Activity) this);
        adjustStatusBar(this.mSearchToolbarAgent);
        initView();
        getData();
    }

    private void getParam() {
        String stringExtra = getIntent().getStringExtra("ids");
        if (!TextUtils.isEmpty(stringExtra)) {
            for (String add : stringExtra.split(",")) {
                this.inviteIds.add(add);
            }
            this.inviteCount = this.inviteIds.size();
        }
        this.fid = getIntent().getStringExtra("fid");
        this.inviteList = getIntent().getParcelableArrayListExtra("list");
    }

    private void initView() {
        this.loadMoreManager = new LoadMoreManager();
        this.adapter = new QAInviteAdapter(this, this.loadMoreManager);
        this.adapter.setOnClickAddListener(this);
        this.layoutManager = new LinearLayoutManager(this);
        this.mRecycleView.setLayoutManager(this.layoutManager);
        this.mRecycleView.setAdapter(this.adapter);
        this.countTextView.setText(getQuantityString(R.plurals.qa_question_invite_count, this.inviteCount));
        this.mRecycleView.addOnScrollListener(new InfiniteScrollListener(this.layoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (QAInviteActivity.this.total > 0 && !QAInviteActivity.this.loadMoreManager.isDataLoading()) {
                    QAInviteActivity.access$208(QAInviteActivity.this);
                    QAInviteActivity.this.search(QAInviteActivity.this.searchView.getText().toString());
                }
            }
        });
    }

    private void getData() {
        if (!TextUtils.isEmpty(this.fid)) {
            this.mProgress.setVisibility(0);
            ApiClient.getInviteList(1, Integer.parseInt(this.fid), bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<QAInviteItem>() {
                public void accept(@NonNull QAInviteItem qAInviteItem) throws Exception {
                    QAInviteActivity.this.dismissProgress();
                    if (qAInviteItem == null || qAInviteItem.getErrno() != 0 || qAInviteItem.data == null) {
                        QAInviteActivity.this.handleResponse((QAInviteItem) null);
                    } else {
                        QAInviteActivity.this.handleResponse(qAInviteItem);
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    QAInviteActivity.this.dismissProgress();
                    if (LoginManager.getInstance().hasLogin()) {
                        QAInviteActivity.this.frameRecycleView.setVisibility(0);
                    } else {
                        QAInviteActivity.this.frameRecycleView.setVisibility(8);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void handleResponse(QAInviteItem qAInviteItem) {
        if (qAInviteItem != null && qAInviteItem.data != null && qAInviteItem.data.size() > 0) {
            ArrayList arrayList = new ArrayList();
            if (this.inviteList != null && this.inviteList.size() > 0) {
                for (QAInviteItem.DataBean next : this.inviteList) {
                    next.checked = true;
                    arrayList.add(next);
                }
            }
            for (QAInviteItem.DataBean next2 : qAInviteItem.data) {
                if (!this.inviteIds.contains(next2.uid)) {
                    arrayList.add(next2);
                }
            }
            this.adapter.addData(arrayList);
            this.adapter.notifyDataSetChanged();
        }
    }

    private String getInviteIds() {
        String str = "";
        for (String str2 : this.inviteIds) {
            str = str + str2 + ",";
        }
        return str.length() > 0 ? str.substring(0, str.length() - 1) : str;
    }

    /* access modifiers changed from: private */
    public void dismissProgress() {
        this.loadMoreManager.loadFinished();
        if (this.mProgress != null && this.mProgress.getVisibility() == 0) {
            this.mProgress.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void search(String str) {
        ImeUtils.hideIme(this.searchView);
        if (this.page > 0) {
            this.loadMoreManager.loadStarted();
        }
        this.mProgress.setVisibility(0);
        ApiClient.search(str, String.valueOf(1), "0", "", String.valueOf(0), String.valueOf(this.page * 10), String.valueOf(10), "1", bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
                QAInviteActivity.this.dismissProgress();
                String jsonObject2 = jsonObject.toString();
                BaseResult baseResult = (BaseResult) JsonParser.parse(jsonObject2, BaseResult.class);
                if (baseResult.getErrno() == 0) {
                    SearchUserModel searchUserModel = (SearchUserModel) JsonParser.parse(jsonObject2, SearchUserModel.class);
                    if (searchUserModel != null && searchUserModel.data != null && searchUserModel.data.response != null) {
                        QAInviteActivity.this.handleSearchResponse(searchUserModel.data.response);
                    } else if (QAInviteActivity.this.page > 0) {
                        QAInviteActivity.access$210(QAInviteActivity.this);
                        int unused = QAInviteActivity.this.total = 0;
                    }
                } else {
                    QAInviteActivity.this.toast(baseResult.getErrmsg());
                    int unused2 = QAInviteActivity.this.total = 0;
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                if (QAInviteActivity.this.page > 0) {
                    QAInviteActivity.access$210(QAInviteActivity.this);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleSearchResponse(SearchUserModel.DataBean.ResponseBean responseBean) {
        if (this.page == 0) {
            this.adapter.clear();
        }
        ArrayList arrayList = new ArrayList();
        if (responseBean == null || responseBean.docs == null || responseBean.docs.size() <= 0) {
            this.total = 0;
        } else {
            this.total = responseBean.docs.size();
            for (int i = 0; i < this.total; i++) {
                QAInviteItem.DataBean dataBean = new QAInviteItem.DataBean();
                dataBean.checked = false;
                dataBean.icon = responseBean.docs.get(i).icon;
                dataBean.groupname = responseBean.docs.get(i).groupid;
                dataBean.username = responseBean.docs.get(i).username;
                dataBean.uid = responseBean.docs.get(i).id;
                arrayList.add(dataBean);
            }
            this.adapter.addData(arrayList);
            this.adapter.notifyDataSetChanged();
        }
        updateEmptyUi();
    }

    private void updateEmptyUi() {
        if (this.adapter.getItemCount() > 0) {
            this.frameRecycleView.setVisibility(0);
            this.doneLayout.setVisibility(0);
            this.noResult.setVisibility(8);
            return;
        }
        this.frameRecycleView.setVisibility(8);
        this.doneLayout.setVisibility(8);
        this.noResult.setVisibility(0);
    }

    @OnClick({2131493946, 2131493857, 2131493634})
    public void onClick(View view) {
        if (view.getId() == R.id.menu_search) {
            search(this.searchView.getText().toString());
        } else if (view.getId() == R.id.searchback) {
            finish();
        } else if (view.getId() == R.id.qa_invite_done) {
            Intent intent = new Intent();
            intent.putExtra("ids", getInviteIds());
            intent.putExtra("count", this.inviteCount);
            intent.putExtra(QA_INVITE_PRE_FID_QARAM, Integer.parseInt(this.fid));
            intent.putParcelableArrayListExtra("list", (ArrayList) this.inviteList);
            setResult(-1, intent);
            finish();
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onClickAdd(int i, String str, int i2, QAInviteAdapter.QAInviteHolder qAInviteHolder) {
        if (i2 == 0) {
            if (this.inviteCount < 10) {
                this.inviteIds.add(str);
                if (this.inviteList == null) {
                    this.inviteList = new ArrayList();
                }
                this.inviteList.add(this.adapter.getDataItemByPosition(i));
                this.inviteCount++;
                this.countTextView.setText(getQuantityString(R.plurals.qa_question_invite_count, this.inviteCount));
                this.adapter.getDataItemByPosition(i).checked = !this.adapter.getDataItemByPosition(i).checked;
                qAInviteHolder.itemInvited.setVisibility(0);
                qAInviteHolder.itemInvite.setVisibility(8);
                this.adapter.setCanInvite(true);
            } else {
                toast(Integer.valueOf(R.string.qa_invite_most_ten));
                this.adapter.setCanInvite(false);
            }
        }
        if (i2 == 1) {
            this.inviteIds.remove(str);
            this.inviteList.remove(this.adapter.getDataItemByPosition(i));
            this.inviteCount--;
            this.countTextView.setText(getQuantityString(R.plurals.qa_question_invite_count, this.inviteCount));
            this.adapter.getDataItemByPosition(i).checked = !this.adapter.getDataItemByPosition(i).checked;
            qAInviteHolder.itemInvited.setVisibility(8);
            qAInviteHolder.itemInvite.setVisibility(0);
            this.countTextView.setText(getQuantityString(R.plurals.qa_question_invite_count, this.inviteCount));
            if (this.inviteCount < 10) {
                this.adapter.setCanInvite(true);
            }
        }
    }
}
