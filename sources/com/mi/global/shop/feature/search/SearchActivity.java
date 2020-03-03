package com.mi.global.shop.feature.search;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.volley.Request;
import com.mi.global.shop.base.BaseActivity;
import com.mi.global.shop.base.MiShopStatInterface;
import com.mi.global.shop.base.request.SimpleCallback;
import com.mi.global.shop.base.request.SimpleJsonRequest;
import com.mi.global.shop.base.request.SimpleProtobufRequest;
import com.mi.global.shop.base.service.GlobalConfigService;
import com.mi.global.shop.base.utils.SaveObjectUtils;
import com.mi.global.shop.base.widget.CustomEditTextView;
import com.mi.global.shop.feature.search.newmodel.SearchRecommendResult;
import com.mi.global.shop.feature.search.newmodel.SearchWordResult;
import com.mi.global.shop.feature.search.widget.TagsLayout;
import com.mi.global.shop.router.RouterConfig;
import com.mi.util.Coder;
import com.mi.util.RequestQueueUtil;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Route(path = "/featureGlobalshopSearch/searchActivity")
public class SearchActivity extends BaseActivity {
    private static final String TAG = SearchActivity.class.getCanonicalName();
    private static int sDarkModeFlag = 0;
    private static Method sExtraFlagField;
    public static int statusBarHeight = 0;
    /* access modifiers changed from: private */
    public SearchResultListAdapter adapter;
    @BindView(2131492937)
    ImageButton btnHistoryClear;
    @BindView(2131493473)
    CustomTextView etvSearch;
    @BindView(2131493474)
    CustomTextView etvSearchCancel;
    @BindView(2131493032)
    CustomEditTextView etvSearchInput;
    @Autowired
    GlobalConfigService globalConfigService;
    /* access modifiers changed from: private */
    public boolean isResultShowed = false;
    public LinearLayout layoutSearchContainer;
    @BindView(2131493144)
    LinearLayout layoutSearchHot;
    @BindView(2131493145)
    RelativeLayout layoutSearchRecord;
    @BindView(2131493296)
    FrameLayout rootView;
    @BindView(2131493300)
    ListView rvSearchResult;
    /* access modifiers changed from: private */
    public ArrayList<SearchWordResult.DataBean> searchData = new ArrayList<>();
    private SearchResultFragment searchResultFragment;
    @BindView(2131493373)
    TagsLayout tagsHistoryGroup;
    @BindView(2131493374)
    TagsLayout tagsHotGroup;
    TextWatcher textWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(SearchActivity.this.etvSearchInput.getText().toString())) {
                SearchActivity.this.showSearchResultView(false);
            } else {
                SearchActivity.this.requestSearchResult(SearchActivity.this.etvSearchInput.getText().toString());
            }
            SearchActivity.this.showSearchResultPage(false, "");
        }
    };
    @BindView(2131493475)
    CustomTextView tvSearchNoResult;
    @BindView(2131493476)
    CustomTextView tvSearchRecord;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        try {
            requestWindowFeature(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate(bundle);
        ARouter.a().a((Object) this);
        if (this.globalConfigService != null) {
            setCustomContentView(R.layout.feature_search_activity_search_sub_content);
            ButterKnife.bind((Activity) this);
            initView();
            initRecommendData();
            return;
        }
        throw new RuntimeException("调用方需要实现 globalShopBase 组件的 Service 包的所有接口。");
    }

    private void initView() {
        setCartViewVisible(false);
        this.layoutSearchContainer = (LinearLayout) findViewById(R.id.layout_search_container);
        this.layoutSearchContainer.setVisibility(0);
        this.etvSearchCancel.setVisibility(0);
        getSearchHistory();
        this.etvSearchInput.addTextChangedListener(this.textWatcher);
        this.etvSearchCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiShopStatInterface.a("cancel_click", SearchActivity.this.isResultShowed ? "search_landing" : "search_jump");
                SearchActivity.this.finish();
            }
        });
        this.btnHistoryClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveObjectUtils.a((Context) SearchActivity.this, "pref_key_search_history_list", (Object) new ArrayList());
                SearchActivity.this.tagsHistoryGroup.removeAllViews();
                SearchActivity.this.layoutSearchRecord.setVisibility(8);
                MiShopStatInterface.a("delete_click", "search_jump");
            }
        });
        this.rvSearchResult.setDivider((Drawable) null);
        this.rvSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (SearchActivity.this.searchData.get(i) != null) {
                    if (!TextUtils.isEmpty(((SearchWordResult.DataBean) SearchActivity.this.searchData.get(i)).tag)) {
                        ARouter.a().a(RouterConfig.b).withString("url", SearchActivity.this.connectionHelperService.b() + SearchActivity.this.connectionHelperService.c() + "/" + ((SearchWordResult.DataBean) SearchActivity.this.searchData.get(i)).tag).navigation();
                    } else {
                        SearchActivity.this.showSearchResultPage(true, ((SearchWordResult.DataBean) SearchActivity.this.searchData.get(i)).name);
                        MiShopStatInterface.a("%s_click", "search_associative", "asssociative", ((SearchWordResult.DataBean) SearchActivity.this.searchData.get(i)).name);
                    }
                    SearchActivity.this.etvSearchInput.removeTextChangedListener(SearchActivity.this.textWatcher);
                    SearchActivity.this.etvSearchInput.setText(((SearchWordResult.DataBean) SearchActivity.this.searchData.get(i)).name);
                    SearchActivity.this.etvSearchInput.setSelection(SearchActivity.this.etvSearchInput.getText().length());
                    SearchActivity.this.etvSearchInput.addTextChangedListener(SearchActivity.this.textWatcher);
                    ArrayList arrayList = (ArrayList) SaveObjectUtils.a((Context) SearchActivity.this, "pref_key_search_history_list", ArrayList.class);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(((SearchWordResult.DataBean) SearchActivity.this.searchData.get(i)).name);
                    SaveObjectUtils.a((Context) SearchActivity.this, "pref_key_search_history_list", (Object) arrayList);
                    MiShopStatInterface.a(String.format("%s_click", new Object[]{SearchActivity.this.searchData.get(i)}), "search_associative");
                    SearchActivity.this.adapter.clear();
                    SearchActivity.this.adapter.notifyDataSetChanged();
                }
            }
        });
        this.etvSearchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 3 && i != 4 && i != 6 && i != 0) {
                    return false;
                }
                InputMethodManager inputMethodManager = (InputMethodManager) textView.getContext().getSystemService("input_method");
                if (inputMethodManager.isActive()) {
                    inputMethodManager.hideSoftInputFromWindow(textView.getApplicationWindowToken(), 0);
                }
                if (!TextUtils.isEmpty(SearchActivity.this.etvSearchInput.getText())) {
                    MiShopStatInterface.a("search_click", "search_jump", "search", SearchActivity.this.etvSearchInput.getText().toString(), (String) null);
                    SearchActivity.this.showSearchResultPage(true, SearchActivity.this.etvSearchInput.getText().toString());
                }
                return true;
            }
        });
    }

    /* access modifiers changed from: private */
    public void showSearchResultPage(boolean z, String str) {
        if (isActivityAlive(this)) {
            this.isResultShowed = z;
            if (z) {
                this.tvSearchNoResult.setVisibility(8);
                this.searchResultFragment = SearchResultFragment.newInstance(str);
                getSupportFragmentManager().beginTransaction().replace(R.id.root_view, this.searchResultFragment, TAG).commitAllowingStateLoss();
            } else if (this.searchResultFragment != null && this.searchResultFragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().remove(this.searchResultFragment).commitAllowingStateLoss();
            }
        }
    }

    private void getSearchHistory() {
        ArrayList arrayList = (ArrayList) SaveObjectUtils.a((Context) this, "pref_key_search_history_list", ArrayList.class);
        if (arrayList == null || arrayList.size() <= 0) {
            this.layoutSearchRecord.setVisibility(8);
            return;
        }
        fillSearchHistoryLabel(this.tagsHistoryGroup, arrayList);
        this.layoutSearchRecord.setVisibility(0);
    }

    private void initRecommendData() {
        Request request;
        Uri.Builder buildUpon = Uri.parse(this.connectionHelperService.a()).buildUpon();
        buildUpon.appendQueryParameter("type", "android");
        AnonymousClass6 r1 = new SimpleCallback<SearchRecommendResult>() {
            public void success(SearchRecommendResult searchRecommendResult) {
                if (searchRecommendResult == null || searchRecommendResult.data == null || searchRecommendResult.data.recommend == null || searchRecommendResult.data.recommend.size() <= 0) {
                    SearchActivity.this.layoutSearchHot.setVisibility(8);
                } else {
                    SearchActivity.this.fillSearchRecommendLabel(SearchActivity.this.tagsHotGroup, searchRecommendResult.data.recommend);
                }
            }

            public void error(String str) {
                super.error(str);
            }
        };
        if (this.globalConfigService.b()) {
            request = new SimpleProtobufRequest(buildUpon.toString(), SearchRecommendResult.class, r1);
        } else {
            request = new SimpleJsonRequest(buildUpon.toString(), SearchRecommendResult.class, r1);
        }
        RequestQueueUtil.a().add(request);
    }

    /* access modifiers changed from: private */
    public void requestSearchResult(final String str) {
        Request request;
        Uri.Builder buildUpon = Uri.parse(this.connectionHelperService.a(str)).buildUpon();
        AnonymousClass7 r1 = new SimpleCallback<SearchWordResult>() {
            public void success(SearchWordResult searchWordResult) {
                if (TextUtils.equals(SearchActivity.this.etvSearchInput.getText().toString(), str)) {
                    if (searchWordResult != null && searchWordResult.data != null) {
                        SearchActivity.this.searchData.clear();
                        if (searchWordResult.data.item != null) {
                            SearchActivity.this.searchData.addAll(searchWordResult.data.item);
                        }
                        if (searchWordResult.data.list != null && searchWordResult.data.list.size() > 0) {
                            for (int i = 0; i < searchWordResult.data.list.size(); i++) {
                                SearchWordResult.DataBean dataBean = new SearchWordResult.DataBean();
                                dataBean.name = searchWordResult.data.list.get(i);
                                SearchActivity.this.searchData.add(dataBean);
                            }
                        }
                        if (SearchActivity.this.adapter == null) {
                            SearchResultListAdapter unused = SearchActivity.this.adapter = new SearchResultListAdapter(SearchActivity.this, str, SearchActivity.this.searchData);
                            SearchActivity.this.rvSearchResult.setAdapter(SearchActivity.this.adapter);
                        }
                        SearchActivity.this.adapter.setKeyWord(str);
                        SearchActivity.this.adapter.notifyDataSetChanged();
                        SearchActivity.this.showSearchResultView(true);
                    } else if (searchWordResult.data == null) {
                        SearchActivity.this.rvSearchResult.setVisibility(8);
                        SearchActivity.this.tvSearchNoResult.setVisibility(0);
                    }
                }
            }

            public void error(String str) {
                super.error(str);
            }
        };
        if (this.globalConfigService.b()) {
            request = new SimpleProtobufRequest(buildUpon.toString(), SearchWordResult.class, r1);
        } else {
            request = new SimpleJsonRequest(buildUpon.toString(), SearchWordResult.class, r1);
        }
        RequestQueueUtil.a().add(request);
    }

    /* access modifiers changed from: private */
    public void showSearchResultView(boolean z) {
        if (z) {
            this.tvSearchNoResult.setVisibility(8);
            this.rvSearchResult.setVisibility(0);
            this.layoutSearchRecord.setVisibility(8);
            this.layoutSearchHot.setVisibility(8);
            return;
        }
        this.tvSearchNoResult.setVisibility(8);
        this.rvSearchResult.setVisibility(8);
        this.layoutSearchHot.setVisibility(0);
        getSearchHistory();
    }

    public void fillSearchHistoryLabel(TagsLayout tagsLayout, List<String> list) {
        if (tagsLayout != null && list != null) {
            if (tagsLayout.getChildCount() > 0) {
                tagsLayout.removeAllViews();
            }
            for (final String next : list) {
                if (!TextUtils.isEmpty(next)) {
                    addTextView(tagsLayout, next, new View.OnClickListener() {
                        public void onClick(View view) {
                            SearchActivity.this.etvSearchInput.removeTextChangedListener(SearchActivity.this.textWatcher);
                            SearchActivity.this.etvSearchInput.setText(next);
                            SearchActivity.this.etvSearchInput.setSelection(SearchActivity.this.etvSearchInput.getText().length());
                            SearchActivity.this.etvSearchInput.addTextChangedListener(SearchActivity.this.textWatcher);
                            SearchActivity.this.showSearchResultPage(true, next);
                            MiShopStatInterface.a(String.format("%s_click", new Object[]{next}), "search_jump");
                        }
                    });
                }
            }
        }
    }

    public void fillSearchRecommendLabel(TagsLayout tagsLayout, List<SearchRecommendResult.Recommend> list) {
        if (tagsLayout != null && list != null) {
            if (tagsLayout.getChildCount() > 0) {
                tagsLayout.removeAllViews();
            }
            for (final SearchRecommendResult.Recommend next : list) {
                if (!TextUtils.isEmpty(next.name)) {
                    addTextView(tagsLayout, next.name, new View.OnClickListener() {
                        public void onClick(View view) {
                            if (!TextUtils.isEmpty(next.link)) {
                                MiShopStatInterface.a(String.format("%s_click", new Object[]{next.name}), "search_jump");
                                ARouter.a().a(RouterConfig.b).withString("url", next.link).navigation();
                            }
                        }
                    });
                }
            }
        }
    }

    private void addTextView(TagsLayout tagsLayout, String str, View.OnClickListener onClickListener) {
        TagsLayout.LayoutParams layoutParams = new TagsLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(Coder.a(10.0f), Coder.a(10.0f), 0, 0);
        TextView textView = new TextView(tagsLayout.getContext());
        textView.setText(str);
        textView.setTextSize(13.0f);
        textView.setGravity(17);
        textView.setIncludeFontPadding(false);
        textView.setPadding(Coder.a(11.0f), 0, Coder.a(11.0f), 0);
        textView.setSingleLine();
        textView.setTextColor(Color.parseColor("#424242"));
        textView.setHeight(Coder.a(30.0f));
        textView.setBackgroundResource(R.drawable.feature_search_search_label_bg);
        textView.setOnClickListener(onClickListener);
        tagsLayout.addView(textView, layoutParams);
    }
}
