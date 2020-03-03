package com.mi.global.bbs.ui.qa;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.SearchKeyAdapter;
import com.mi.global.bbs.adapter.SearchPagerAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.SearchHotModel;
import com.mi.global.bbs.ui.SearchFragment;
import com.mi.global.bbs.utils.BasePageChangeListener;
import com.mi.global.bbs.utils.DefaultTextWatcher;
import com.mi.global.bbs.utils.ImeUtils;
import com.mi.global.bbs.utils.SlideInItemAnimator;
import com.mi.global.bbs.view.Editor.ClearEditText;
import com.mi.global.bbs.view.PagerSlidingTabStrip;
import com.mi.util.Utils;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QASearchActivity extends BaseActivity implements SearchKeyAdapter.OnSearchListener {
    private static final String HISTORY_WORD = "history_word";
    @BindView(2131493160)
    FrameLayout container;
    private List<String> historyWords;
    @BindView(2131493891)
    FrameLayout mResultsContainer;
    @BindView(2131493941)
    View mSearchToolbarAgent;
    @BindView(2131493942)
    LinearLayout mSearchToolbarContainer;
    String prevSearchStr = "";
    @BindView(2131493225)
    ProgressBar progress;
    @BindView(2131493946)
    ImageView searchBack;
    @BindView(2131493928)
    RecyclerView searchHistoryListView;
    /* access modifiers changed from: private */
    public SearchKeyAdapter searchKeyAdapter;
    @BindView(2131493932)
    ViewPager searchPager;
    @BindView(2131493935)
    LinearLayout searchResultsContent;
    @BindView(2131493937)
    PagerSlidingTabStrip searchResultsTab;
    @BindView(2131493938)
    View searchResultsTabDivide;
    @BindView(2131493944)
    ClearEditText searchView;
    /* access modifiers changed from: private */
    public SearchFragment threadFragment;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRequestedOrientation(1);
        setContentView(R.layout.bbs_activity_search);
        ButterKnife.bind((Activity) this);
        adjustStatusBar(this.mSearchToolbarAgent);
        setSearchEditView();
        getSearchHistoryAndHot();
        setHistoryView();
        initResultView();
    }

    private void initResultView() {
        ArrayList arrayList = new ArrayList();
        this.threadFragment = SearchFragment.newInstance(2);
        arrayList.add(this.threadFragment);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.searchPager.setAdapter(new SearchPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.search_tab), arrayList));
        this.searchResultsTab.setViewPager(this.searchPager);
        this.searchResultsTab.setUnderlineHeight((int) TypedValue.applyDimension(1, 0.0f, displayMetrics));
        this.searchResultsTab.setIndicatorColor(getResources().getColor(R.color.main_yellow));
        this.searchResultsTab.setIndicatorHeight((int) TypedValue.applyDimension(1, 2.0f, displayMetrics));
        this.searchResultsTab.setTextColor(Color.parseColor("#404040"));
        this.searchResultsTab.setTextSize((int) TypedValue.applyDimension(2, 15.0f, displayMetrics));
        this.searchResultsTab.setDividerColor(0);
        this.searchResultsTab.setAllCaps(false);
        this.searchResultsTab.setShouldExpand(false);
        this.searchResultsTab.setOnPageChangeListener(new BasePageChangeListener() {
            public void onPageSelected(int i) {
                QASearchActivity.this.threadFragment.load();
            }
        });
        this.searchResultsTab.setVisibility(8);
        this.searchResultsTabDivide.setVisibility(8);
        this.container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                QASearchActivity.this.container.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ((FrameLayout.LayoutParams) QASearchActivity.this.mResultsContainer.getLayoutParams()).topMargin = QASearchActivity.this.mSearchToolbarContainer.getHeight();
                QASearchActivity.this.mResultsContainer.requestLayout();
            }
        });
    }

    private void setHistoryView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.searchHistoryListView.setLayoutManager(linearLayoutManager);
        this.searchKeyAdapter = new SearchKeyAdapter(this);
        this.searchHistoryListView.setAdapter(this.searchKeyAdapter);
        this.historyWords = this.searchKeyAdapter.getHistoryList();
        getHistoryWords();
        this.searchKeyAdapter.setOnSearchListener(this);
        this.searchHistoryListView.setItemAnimator(new SlideInItemAnimator());
    }

    private void getHistoryWords() {
        String[] split;
        String stringPref = Utils.Preference.getStringPref(this, HISTORY_WORD, "");
        if (!TextUtils.isEmpty(stringPref) && (split = stringPref.split("#")) != null && split.length > 0) {
            this.searchKeyAdapter.addHistoryList(Arrays.asList(split));
        }
    }

    private void getSearchHistoryAndHot() {
        this.progress.setVisibility(0);
        ApiClient.getHotSearchKey(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<SearchHotModel>() {
            public void accept(@NonNull SearchHotModel searchHotModel) throws Exception {
                QASearchActivity.this.progress.setVisibility(8);
                if (searchHotModel.getErrno() != 0) {
                    QASearchActivity.this.toast(searchHotModel.getErrmsg());
                    QASearchActivity.this.searchHistoryListView.setVisibility(0);
                } else if (searchHotModel != null && searchHotModel.getData() != null) {
                    QASearchActivity.this.searchHistoryListView.setVisibility(0);
                    QASearchActivity.this.searchKeyAdapter.addHotList(searchHotModel.getData().getWords());
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                QASearchActivity.this.handleNetworkError(th);
                QASearchActivity.this.searchHistoryListView.setVisibility(0);
                QASearchActivity.this.progress.setVisibility(8);
            }
        });
    }

    private void setSearchEditView() {
        this.searchView.setImeOptions(3);
        this.searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 3) {
                    return false;
                }
                QASearchActivity.this.search(QASearchActivity.this.getSearchKey());
                return false;
            }
        });
        this.searchView.addTextChangedListener(new DefaultTextWatcher() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TextUtils.isEmpty(QASearchActivity.this.searchView.getText().toString())) {
                    QASearchActivity.this.clearResults();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void search(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.prevSearchStr)) {
            addToHistoryAndSave(str);
            if (!TextUtils.isEmpty(this.prevSearchStr)) {
                this.threadFragment.clearResult();
            }
            this.prevSearchStr = str;
            ImeUtils.hideIme(this.searchView);
            showResult(str);
        }
    }

    private void addToHistoryAndSave(String str) {
        if (alreadyHave(str)) {
            return;
        }
        if (this.historyWords.size() < 5) {
            this.historyWords.add(str);
            saveHistory(this.historyWords);
            return;
        }
        this.historyWords.remove(0);
        this.historyWords.add(str);
        saveHistory(this.historyWords);
    }

    private boolean alreadyHave(String str) {
        for (String equals : this.historyWords) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void saveHistory(List<String> list) {
        if (list.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String append : list) {
                sb.append(append);
                sb.append("#");
            }
            sb.deleteCharAt(sb.length() - 1);
            Utils.Preference.setStringPref(this, HISTORY_WORD, sb.toString());
            return;
        }
        Utils.Preference.setStringPref(this, HISTORY_WORD, "");
    }

    public String getSearchKey() {
        return this.searchView.getText().toString();
    }

    private void showResult(String str) {
        if (this.searchResultsContent.getVisibility() == 0) {
            this.progress.setVisibility(8);
            this.searchHistoryListView.setVisibility(8);
            this.threadFragment.load(str);
            return;
        }
        this.searchResultsContent.setVisibility(0);
        this.progress.setVisibility(8);
        this.searchHistoryListView.setVisibility(8);
        this.searchPager.setCurrentItem(0, false);
        this.threadFragment.load(str);
    }

    public void onBackPressed() {
        back();
    }

    @OnClick({2131493946, 2131493634, 2131493912})
    public void onClick(View view) {
        if (view.getId() == R.id.searchback) {
            back();
        } else if (view.getId() == R.id.scrim) {
            ImeUtils.hideIme(this.searchView);
        } else if (view.getId() == R.id.menu_search) {
            search(getSearchKey());
        }
    }

    private void back() {
        if (this.searchHistoryListView.getVisibility() == 0) {
            close();
            return;
        }
        this.searchView.setText("");
        clearResults();
    }

    private void close() {
        saveHistory(this.historyWords);
        finish();
    }

    /* access modifiers changed from: private */
    public void clearResults() {
        this.progress.setVisibility(8);
        this.searchResultsContent.setVisibility(8);
        this.prevSearchStr = "";
        this.threadFragment.clearResult();
        this.searchHistoryListView.setVisibility(0);
        this.searchKeyAdapter.notifyDataSetChanged();
    }

    public void onSearch(String str) {
        this.searchView.setText(str);
        this.searchView.setSelection(str.length());
        search(str);
    }
}
