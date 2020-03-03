package com.mi.global.bbs.ui.column;

import android.content.Context;
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
import com.mi.global.bbs.adapter.OnFollowListener;
import com.mi.global.bbs.adapter.SubcriptionAdapter;
import com.mi.global.bbs.dao.SubcribtionDao;
import com.mi.global.bbs.db.DbUtil;
import com.mi.global.bbs.db.SubscripionHelper;
import com.mi.global.bbs.entity.Subcribtion;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.ColumnDetailModel;
import com.mi.global.bbs.model.ColumnSubData;
import com.mi.global.bbs.model.ColumnSubModel;
import com.mi.global.bbs.ui.HeaderViewPagerFragment;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.WhereCondition;

public class MySubcriptionFragment extends HeaderViewPagerFragment implements SwipeRefreshLayout.OnRefreshListener, OnFollowListener, OnShareListener {
    /* access modifiers changed from: private */
    public SubcriptionAdapter adapter;
    protected CallbackManager callbackManager;
    /* access modifiers changed from: private */
    public boolean canLoadMore = false;
    @BindView(2131493155)
    ObservableRecyclerView commonRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout commonRefreshView;
    private LoadMoreManager loadMoreManager;
    private SubscripionHelper mHelper;
    @BindView(2131493154)
    ProgressBar mProgress;
    /* access modifiers changed from: private */
    public int page = 1;
    private String pageName;
    private int pageSize = 10;

    static /* synthetic */ int access$108(MySubcriptionFragment mySubcriptionFragment) {
        int i = mySubcriptionFragment.page;
        mySubcriptionFragment.page = i + 1;
        return i;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_subscrib_refresh_loading_layout, viewGroup, false);
        this.pageName = Constants.PageFragment.PAGE_COLUMN_SUBSCRIPTION;
        ButterKnife.bind((Object) this, inflate);
        this.callbackManager = CallbackManager.Factory.create();
        this.mHelper = DbUtil.getDriverHelper();
        initView();
        getData();
        return inflate;
    }

    /* access modifiers changed from: private */
    public void getData() {
        if (this.page > 0) {
            this.loadMoreManager.loadStarted();
        }
        ApiClient.getColumnSub(this.page, this.pageSize, bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<ColumnSubModel>() {
            public void accept(@NonNull ColumnSubModel columnSubModel) throws Exception {
                MySubcriptionFragment.this.dismissProgress();
                if (columnSubModel == null || columnSubModel.data == null) {
                    boolean unused = MySubcriptionFragment.this.canLoadMore = false;
                    return;
                }
                if (MySubcriptionFragment.this.page == 1) {
                    MySubcriptionFragment.this.adapter.clear();
                }
                ArrayList arrayList = new ArrayList();
                if (columnSubModel.data.List != null && columnSubModel.data.List.size() > 0) {
                    MySubcriptionFragment.this.saveColumnDao(columnSubModel.data.List);
                    for (ColumnDetailModel.DataBean.ColumnInfo subColumn : columnSubModel.data.List) {
                        ColumnSubData columnSubData = new ColumnSubData();
                        columnSubData.setSubColumn(subColumn);
                        arrayList.add(columnSubData);
                    }
                    if (columnSubModel.data.List.size() > 0) {
                        boolean unused2 = MySubcriptionFragment.this.canLoadMore = true;
                    } else {
                        boolean unused3 = MySubcriptionFragment.this.canLoadMore = false;
                    }
                } else if (columnSubModel.data.recommend != null && columnSubModel.data.recommend.size() > 0) {
                    ColumnSubData columnSubData2 = new ColumnSubData();
                    columnSubData2.setType(1);
                    arrayList.add(columnSubData2);
                    for (ColumnDetailModel.DataBean.ColumnInfo subSuggest : columnSubModel.data.recommend) {
                        ColumnSubData columnSubData3 = new ColumnSubData();
                        columnSubData3.setSubSuggest(subSuggest);
                        arrayList.add(columnSubData3);
                    }
                    if (columnSubModel.data.recommend.size() > 0) {
                        boolean unused4 = MySubcriptionFragment.this.canLoadMore = true;
                    } else {
                        boolean unused5 = MySubcriptionFragment.this.canLoadMore = false;
                    }
                }
                MySubcriptionFragment.this.adapter.setData(arrayList);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                MySubcriptionFragment.this.dismissProgress();
                boolean unused = MySubcriptionFragment.this.canLoadMore = false;
            }
        });
    }

    public void setPadding(int i) {
        if (this.commonRecycleView != null) {
            this.commonRecycleView.setPadding(0, 0, 0, i);
        }
    }

    /* access modifiers changed from: private */
    public void saveColumnDao(List<ColumnDetailModel.DataBean.ColumnInfo> list) {
        for (ColumnDetailModel.DataBean.ColumnInfo next : list) {
            if (!hasData(next.columnID)) {
                Subcribtion subcribtion = new Subcribtion();
                subcribtion.setColumnID(Long.valueOf(next.columnID));
                subcribtion.setLastAppendTime(Long.valueOf(next.lastAppendTime));
                this.mHelper.save(subcribtion);
            }
        }
    }

    private boolean hasData(long j) {
        Query c = this.mHelper.queryBuilder().a(SubcribtionDao.Properties.ColumnID.a((Object) Long.valueOf(j)), new WhereCondition[0]).c();
        if (c.c() == null || c.c().size() <= 0) {
            return false;
        }
        return true;
    }

    private void initView() {
        this.commonRefreshView.setOnRefreshListener(this);
        this.commonRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(getActivity(), 1, false);
        this.commonRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.loadMoreManager = new LoadMoreManager();
        this.adapter = new SubcriptionAdapter(getActivity(), this.loadMoreManager, this.pageName);
        this.commonRecycleView.setAdapter(this.adapter);
        this.adapter.setOnFollowListener(this);
        this.commonRecycleView.setOnScrollListener(new InfiniteScrollListener(wrapContentLinearLayoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (MySubcriptionFragment.this.canLoadMore) {
                    MySubcriptionFragment.access$108(MySubcriptionFragment.this);
                    MySubcriptionFragment.this.getData();
                }
            }
        });
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

    public View getScrollableView() {
        return this.commonRecycleView;
    }

    public void onFollow(final int i, final String str, boolean z) {
        if (!z) {
            ApiClient.followColumn(str, 1, bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    if (baseResult.getErrno() == 0) {
                        MySubcriptionFragment.this.toast(R.string.follow_success);
                        if (MySubcriptionFragment.this.adapter.getThreadlist() != null && i < MySubcriptionFragment.this.adapter.getThreadlist().size()) {
                            MySubcriptionFragment.this.adapter.getThreadlist().get(i).getSubColumn().subscribeStatus = true;
                            MySubcriptionFragment.this.adapter.notifyDataSetChanged();
                            return;
                        }
                        return;
                    }
                    MySubcriptionFragment.this.toast(baseResult.getErrmsg());
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    MySubcriptionFragment.this.toast(R.string.bbs_error_server);
                }
            });
        } else {
            DialogFactory.showAlert((Context) getActivity(), getString(R.string.unfollow_column_tip), getString(R.string.bbs_yes), getString(R.string.bbs_cancel), (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
                public void onOkClick(View view) {
                    ApiClient.followColumn(str, 0, MySubcriptionFragment.this.bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                        public void accept(@NonNull BaseResult baseResult) throws Exception {
                            if (baseResult.getErrno() != 0) {
                                MySubcriptionFragment.this.toast(baseResult.getErrmsg());
                            } else if (MySubcriptionFragment.this.adapter.getThreadlist() != null && i < MySubcriptionFragment.this.adapter.getThreadlist().size()) {
                                MySubcriptionFragment.this.adapter.getThreadlist().get(i).getSubColumn().subscribeStatus = false;
                                MySubcriptionFragment.this.adapter.notifyDataSetChanged();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        public void accept(@NonNull Throwable th) throws Exception {
                        }
                    });
                }
            });
        }
    }
}
