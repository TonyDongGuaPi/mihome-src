package com.mi.global.bbs.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.JsonObject;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.SearchThreadAdapter;
import com.mi.global.bbs.adapter.SearchUserAdapter;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.SearchThreadModel;
import com.mi.global.bbs.model.SearchUserModel;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.view.CheckedTextView;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.util.Utils;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BaseFragment implements SearchThreadAdapter.SortListener, CheckedTextView.OnCheckedChangeListener {
    public static final int FROM_QA_SEARCH = 2;
    public static final String FROM_QA_TYPE = "1";
    public static final String FUZZY = "fuzzy";
    private static final int SEARCH_THREAD_TYPE = 0;
    static String TYPE = "type";
    private static boolean isQa = false;
    private boolean firstLoading = true;
    private String fuzzy = "";
    private LoadMoreManager loadMoreManager;
    private TextView noResults;
    private int orderBy = 0;
    /* access modifiers changed from: private */
    public int page = 0;
    private int pageSize = 20;
    private String searchKey;
    @BindView(2131493934)
    ProgressBar searchProgress;
    @BindView(2131493936)
    RecyclerView searchResultsList;
    private String[] sorts;
    private SearchThreadAdapter tAdapter;
    private List<SearchThreadModel.DataBean.ResponseBean.DocsBean> tItems;
    int type = 0;
    private SearchUserAdapter uAdapter;
    private List<SearchUserModel.DataBean.ResponseBean.DocsBean> uItems;

    static /* synthetic */ int access$108(SearchFragment searchFragment) {
        int i = searchFragment.page;
        searchFragment.page = i + 1;
        return i;
    }

    static /* synthetic */ int access$110(SearchFragment searchFragment) {
        int i = searchFragment.page;
        searchFragment.page = i - 1;
        return i;
    }

    public static SearchFragment newInstance(int i) {
        if (i == 2) {
            isQa = true;
        } else {
            isQa = false;
        }
        SearchFragment searchFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, i);
        searchFragment.setArguments(bundle);
        return searchFragment;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.type = getArguments().getInt(TYPE);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_fragment_search, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(getActivity());
        wrapContentLinearLayoutManager.setOrientation(1);
        this.loadMoreManager = new LoadMoreManager();
        this.searchResultsList.setLayoutManager(wrapContentLinearLayoutManager);
        this.searchResultsList.addOnScrollListener(new InfiniteScrollListener(wrapContentLinearLayoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (SearchFragment.this.checkIfReachLoadMore()) {
                    SearchFragment.access$108(SearchFragment.this);
                    SearchFragment.this.search();
                }
            }
        });
        return inflate;
    }

    /* access modifiers changed from: private */
    public boolean checkIfReachLoadMore() {
        if (this.type == 0 && (this.tItems == null || this.tItems.size() < this.pageSize || this.tItems.size() == this.tAdapter.getTotalNum())) {
            return false;
        }
        if (this.type == 1 && (this.uItems == null || this.uItems.size() < this.pageSize || this.uItems.size() == this.uAdapter.getTotalNum())) {
            return false;
        }
        if (this.type == 2) {
            isQa = true;
            if (this.tItems == null || this.tItems.size() < this.pageSize || this.tItems.size() == this.tAdapter.getTotalNum()) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void search() {
        SearchActivity searchActivity;
        if (TextUtils.isEmpty(this.searchKey) && (searchActivity = (SearchActivity) getActivity()) != null) {
            this.searchKey = searchActivity.getSearchKey();
        }
        if (!TextUtils.isEmpty(this.searchKey)) {
            if (this.page > 0) {
                this.loadMoreManager.loadStarted();
            }
            if (this.page == 0) {
                showProgress(true);
            }
            if (isQa) {
                ApiClient.searchQA("1", this.searchKey, String.valueOf(0), "0", "", String.valueOf(this.orderBy), String.valueOf(this.page * this.pageSize), String.valueOf(this.pageSize), this.fuzzy, bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<JsonObject>() {
                    public void accept(@NonNull JsonObject jsonObject) throws Exception {
                        String jsonObject2 = jsonObject.toString();
                        BaseResult baseResult = (BaseResult) JsonParser.parse(jsonObject2, BaseResult.class);
                        if (baseResult.getErrno() != 0) {
                            SearchFragment.this.toast(baseResult.getErrmsg());
                        } else if (!TextUtils.isEmpty(jsonObject2)) {
                            SearchThreadModel searchThreadModel = (SearchThreadModel) JsonParser.parse(jsonObject2, SearchThreadModel.class);
                            if (searchThreadModel == null || searchThreadModel.data == null || searchThreadModel.data.response == null) {
                                SearchFragment.this.checkIfNeedShowNoResult();
                            } else {
                                SearchFragment.this.addThreadResult(searchThreadModel.data.response);
                                if (searchThreadModel.data.response.docs == null || searchThreadModel.data.response.docs.size() == 0) {
                                    SearchFragment.this.checkIfNeedShowNoResult();
                                }
                            }
                        }
                        SearchFragment.this.dismiss();
                    }
                }, new Consumer<Throwable>() {
                    public void accept(@NonNull Throwable th) throws Exception {
                        SearchFragment.this.resetFirstLoad();
                        if (SearchFragment.this.page > 0) {
                            SearchFragment.access$110(SearchFragment.this);
                        }
                        SearchFragment.this.dismiss();
                    }
                });
            } else {
                ApiClient.search(this.searchKey, String.valueOf(this.type), "0", "", String.valueOf(this.orderBy), String.valueOf(this.page * this.pageSize), String.valueOf(this.pageSize), this.fuzzy, bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<JsonObject>() {
                    public void accept(@NonNull JsonObject jsonObject) throws Exception {
                        String jsonObject2 = jsonObject.toString();
                        BaseResult baseResult = (BaseResult) JsonParser.parse(jsonObject2, BaseResult.class);
                        if (baseResult.getErrno() != 0) {
                            SearchFragment.this.toast(baseResult.getErrmsg());
                        } else if (!TextUtils.isEmpty(jsonObject2) && SearchFragment.this.type == 0) {
                            SearchThreadModel searchThreadModel = (SearchThreadModel) JsonParser.parse(jsonObject2, SearchThreadModel.class);
                            if (searchThreadModel == null || searchThreadModel.data == null || searchThreadModel.data.response == null) {
                                SearchFragment.this.checkIfNeedShowNoResult();
                            } else {
                                SearchFragment.this.addThreadResult(searchThreadModel.data.response);
                                if (searchThreadModel.data.response.docs == null || searchThreadModel.data.response.docs.size() == 0) {
                                    SearchFragment.this.checkIfNeedShowNoResult();
                                }
                            }
                        } else if (!TextUtils.isEmpty(jsonObject2) && SearchFragment.this.type == 1) {
                            SearchUserModel searchUserModel = (SearchUserModel) JsonParser.parse(jsonObject2, SearchUserModel.class);
                            if (searchUserModel == null || searchUserModel.data == null || searchUserModel.data.response == null) {
                                SearchFragment.this.checkIfNeedShowNoResult();
                            } else {
                                SearchFragment.this.addUserResult(searchUserModel.data.response);
                                if (searchUserModel.data.response.docs == null || searchUserModel.data.response.docs.size() == 0) {
                                    SearchFragment.this.checkIfNeedShowNoResult();
                                }
                            }
                        }
                        SearchFragment.this.dismiss();
                    }
                }, new Consumer<Throwable>() {
                    public void accept(@NonNull Throwable th) throws Exception {
                        SearchFragment.this.resetFirstLoad();
                        if (SearchFragment.this.page > 0) {
                            SearchFragment.access$110(SearchFragment.this);
                        }
                        SearchFragment.this.dismiss();
                    }
                });
            }
        }
    }

    public void load() {
        if (this.firstLoading) {
            this.firstLoading = false;
            this.page = 0;
            search();
        }
    }

    private void setNoResultsVisibility(int i) {
        if (i == 0) {
            if (this.noResults == null) {
                if (getView() != null) {
                    this.noResults = (TextView) ((ViewStub) getView().findViewById(R.id.stub_no_search_results)).inflate();
                    this.noResults.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                } else {
                    return;
                }
            }
            String format = String.format(getString(R.string.no_search_results), new Object[]{this.searchKey});
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
            spannableStringBuilder.setSpan(new StyleSpan(2), format.indexOf(8220) + 1, format.length() - 1, 33);
            this.noResults.setText(spannableStringBuilder);
        }
        if (this.noResults != null) {
            this.noResults.setVisibility(i);
        }
    }

    public void setSearchKey(String str) {
        this.searchKey = str;
    }

    public void load(String str) {
        setSearchKey(str);
        load();
    }

    public void clearResult() {
        this.firstLoading = true;
        this.searchKey = "";
        this.page = 0;
        setNoResultsVisibility(4);
        if (this.tAdapter != null) {
            this.tAdapter.clear();
        }
        if (this.uAdapter != null) {
            this.uAdapter.clear();
        }
        if (this.loadMoreManager != null) {
            this.loadMoreManager.resetLoadingCount();
        }
    }

    /* access modifiers changed from: private */
    public void checkIfNeedShowNoResult() {
        if (this.page == 0) {
            setNoResultsVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void resetFirstLoad() {
        if (this.page == 0) {
            this.firstLoading = true;
        }
    }

    /* access modifiers changed from: private */
    public void addUserResult(SearchUserModel.DataBean.ResponseBean responseBean) {
        if (this.searchResultsList != null) {
            this.searchResultsList.setVisibility(0);
            setNoResultsVisibility(4);
            if (this.uAdapter == null) {
                this.uItems = new ArrayList();
                this.uAdapter = new SearchUserAdapter(getActivity(), this.loadMoreManager, this.uItems, responseBean.numFound);
                this.uAdapter.setOnCheckedChangeListener(this);
                this.searchResultsList.setAdapter(this.uAdapter);
            }
            if (this.page == 0) {
                this.uAdapter.setTotalNum(responseBean.numFound);
                this.uAdapter.clear();
            }
            this.uAdapter.add(responseBean.docs);
        }
    }

    /* access modifiers changed from: private */
    public void addThreadResult(SearchThreadModel.DataBean.ResponseBean responseBean) {
        if (this.searchResultsList != null) {
            this.searchResultsList.setVisibility(0);
            setNoResultsVisibility(4);
            if (this.tAdapter == null) {
                this.tItems = new ArrayList();
                if (this.type == 2) {
                    this.tAdapter = new SearchThreadAdapter(getActivity(), this.loadMoreManager, this.tItems, responseBean.numFound, true);
                } else {
                    this.tAdapter = new SearchThreadAdapter(getActivity(), this.loadMoreManager, this.tItems, responseBean.numFound);
                }
                this.sorts = getResources().getStringArray(R.array.sort_str);
                this.tAdapter.setSortStr(this.sorts[this.orderBy]);
                this.tAdapter.setSortListener(this);
                this.searchResultsList.setAdapter(this.tAdapter);
            }
            if (this.page == 0) {
                this.tAdapter.setTotalNum(responseBean.numFound);
                this.tAdapter.clear();
            }
            this.tAdapter.add(responseBean.docs);
        }
    }

    private void handleError(Object obj) {
        if (obj != null && (obj instanceof String)) {
            Toast.makeText(getActivity(), String.valueOf(obj), 0).show();
        }
    }

    /* access modifiers changed from: private */
    public void dismiss() {
        if (this.loadMoreManager != null) {
            this.loadMoreManager.loadFinished();
        }
        showProgress(false);
    }

    private void showProgress(boolean z) {
        int i = 0;
        if (this.searchProgress != null) {
            this.searchProgress.setVisibility(z ? 0 : 4);
        }
        if (this.searchResultsList != null) {
            RecyclerView recyclerView = this.searchResultsList;
            if (z) {
                i = 4;
            }
            recyclerView.setVisibility(i);
        }
        if (z) {
            setNoResultsVisibility(4);
        }
    }

    public void onSort(int i) {
        this.tAdapter.setSortStr(this.sorts[i]);
        this.orderBy = i;
        this.page = 0;
        search();
    }

    public void setFuzzy(String str) {
        this.fuzzy = str;
    }

    public void onChecked(CheckedTextView checkedTextView, boolean z) {
        setFuzzy(z ? "1" : "");
        Utils.Preference.setStringPref(getActivity(), FUZZY, this.fuzzy);
        search();
    }
}
