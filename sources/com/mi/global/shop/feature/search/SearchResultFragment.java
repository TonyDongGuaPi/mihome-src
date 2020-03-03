package com.mi.global.shop.feature.search;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.volley.Request;
import com.mi.global.shop.base.MiShopStatInterface;
import com.mi.global.shop.base.request.SimpleCallback;
import com.mi.global.shop.base.request.SimpleJsonRequest;
import com.mi.global.shop.base.request.SimpleProtobufRequest;
import com.mi.global.shop.base.service.ConnectionHelperService;
import com.mi.global.shop.base.service.GlobalConfigService;
import com.mi.global.shop.feature.search.newmodel.SearchResult;
import com.mi.global.shop.feature.search.widget.TagsLayout;
import com.mi.util.Coder;
import com.mi.util.RequestQueueUtil;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import com.xiaomi.shopviews.widget.recycleview.FullyGridLayoutManager;
import com.xiaomi.shopviews.widget.recycleview.RecycleViewDivider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route(path = "/featureGlobalshopSearch/SearchResultFragment")
public class SearchResultFragment extends Fragment {
    /* access modifiers changed from: private */
    public String adaptId = "0";
    /* access modifiers changed from: private */
    public List<SearchResult.AllCategoriesBean> allCategories = new ArrayList();
    /* access modifiers changed from: private */
    public String catId = "0";
    /* access modifiers changed from: private */
    public List<SearchResult.CommodityDetailBean> commodities = new ArrayList();
    @Autowired
    ConnectionHelperService connectionHelperService;
    /* access modifiers changed from: private */
    public List<SearchResult.AllAdapt> deviceFilters = new ArrayList();
    @Autowired
    GlobalConfigService globalConfigService;
    /* access modifiers changed from: private */
    public boolean isInStock = false;
    /* access modifiers changed from: private */
    public boolean isOnSale = false;
    @BindView(2131493019)
    public DropDownMenu mDropDownMenu;
    @BindView(2131493475)
    public CustomTextView mNoResult;
    private CustomTextView modelTitleView;
    private List<View> popupViews = new ArrayList();
    private SearchResultAdapter searchResultAdapter;
    private GirdDropDownAdapter sortAdapter;
    /* access modifiers changed from: private */
    public List<SearchResult.AllCategoriesBean> sortCategories;
    /* access modifiers changed from: private */
    public String sortType = "0";
    private TagsLayout tagsDeviceLayout;
    private GirdDropDownAdapter typeAdapter;
    private View view;

    public static SearchResultFragment newInstance(String str) {
        SearchResultFragment searchResultFragment = new SearchResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString("keyword", str);
        searchResultFragment.setArguments(bundle);
        return searchResultFragment;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ARouter.a().a((Object) this);
        if (this.connectionHelperService == null || this.globalConfigService == null) {
            throw new RuntimeException("调用方需要实现 globalShopBase 组件的 Service 包的所有接口。");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.feature_search_search_result_fragment_view, viewGroup, false);
        ButterKnife.bind((Object) this, this.view);
        return this.view;
    }

    public void onViewCreated(View view2, @Nullable Bundle bundle) {
        super.onViewCreated(view2, bundle);
        initView();
        initData();
    }

    /* access modifiers changed from: private */
    public void initData() {
        Request request;
        showLoading();
        Uri.Builder buildUpon = Uri.parse(this.connectionHelperService.a(getArguments() != null ? getArguments().getString("keyword") : null, this.adaptId, this.sortType, this.isOnSale ? "1" : "0", "0", this.catId, this.isInStock ? "1" : "0")).buildUpon();
        AnonymousClass1 r1 = new SimpleCallback<SearchResult>() {
            public void success(SearchResult searchResult) {
                SearchResultFragment.this.hideLoading();
                if (searchResult == null || searchResult.data == null) {
                    SearchResultFragment.this.mNoResult.setVisibility(0);
                    return;
                }
                List unused = SearchResultFragment.this.allCategories = searchResult.data.allCategories;
                if (SearchResultFragment.this.allCategories != null && SearchResultFragment.this.isActivityAlive()) {
                    SearchResultFragment.this.allCategories.add(0, new SearchResult.AllCategoriesBean("0", SearchResultFragment.this.getString(R.string.search_result_type)));
                }
                if (searchResult.data.dataProvider != null) {
                    if (searchResult.data.dataProvider.all_adapt != null) {
                        List unused2 = SearchResultFragment.this.deviceFilters = searchResult.data.dataProvider.all_adapt;
                    }
                    SearchResultFragment.this.commodities.clear();
                    if (searchResult.data.dataProvider.data == null || searchResult.data.dataProvider.data.size() <= 0) {
                        SearchResultFragment.this.mNoResult.setVisibility(0);
                    } else {
                        for (SearchResult.DataBean next : searchResult.data.dataProvider.data) {
                            if (next.commodity != null) {
                                SearchResultFragment.this.commodities.addAll(next.commodity);
                            }
                        }
                        SearchResultFragment.this.mNoResult.setVisibility(8);
                    }
                }
                SearchResultFragment.this.renderView();
            }

            public void error(String str) {
                super.error(str);
                SearchResultFragment.this.hideLoading();
            }
        };
        if (this.globalConfigService.b()) {
            request = new SimpleProtobufRequest(buildUpon.toString(), SearchResult.class, r1);
        } else {
            request = new SimpleJsonRequest(buildUpon.toString(), SearchResult.class, r1);
        }
        RequestQueueUtil.a().add(request);
    }

    private void showLoading() {
        if (isActivityAlive()) {
            ((SearchActivity) getActivity()).showLoading();
        }
    }

    /* access modifiers changed from: private */
    public void hideLoading() {
        if (isActivityAlive()) {
            ((SearchActivity) getActivity()).hideLoading();
        }
    }

    private void initView() {
        this.sortCategories = new ArrayList<SearchResult.AllCategoriesBean>() {
            {
                add(new SearchResult.AllCategoriesBean("0", SearchResultFragment.this.getString(R.string.search_result_sort)));
                add(new SearchResult.AllCategoriesBean("1", SearchResultFragment.this.getString(R.string.search_result_sort_latest)));
                add(new SearchResult.AllCategoriesBean("8", SearchResultFragment.this.getString(R.string.search_result_sort_to_high)));
                add(new SearchResult.AllCategoriesBean("10", SearchResultFragment.this.getString(R.string.search_result_sort_to_low)));
            }
        };
        ListView listView = new ListView(getActivity());
        this.typeAdapter = new GirdDropDownAdapter(getActivity(), this.allCategories);
        listView.setDividerHeight(0);
        listView.setAdapter(this.typeAdapter);
        ListView listView2 = new ListView(getActivity());
        listView2.setDividerHeight(0);
        this.sortAdapter = new GirdDropDownAdapter(getActivity(), this.sortCategories);
        listView2.setAdapter(this.sortAdapter);
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.feature_search_custom_layout, (ViewGroup) null);
        this.modelTitleView = (CustomTextView) inflate.findViewById(R.id.tv_model_title);
        this.tagsDeviceLayout = (TagsLayout) inflate.findViewById(R.id.tags_model_group);
        final CustomTextView customTextView = (CustomTextView) inflate.findViewById(R.id.tv_on_sale);
        final CustomTextView customTextView2 = (CustomTextView) inflate.findViewById(R.id.tv_in_stock);
        customTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean unused = SearchResultFragment.this.isOnSale = !SearchResultFragment.this.isOnSale;
                SearchResultFragment.this.updateSelectView(SearchResultFragment.this.isOnSale, customTextView);
            }
        });
        customTextView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean unused = SearchResultFragment.this.isInStock = !SearchResultFragment.this.isInStock;
                SearchResultFragment.this.updateSelectView(SearchResultFragment.this.isInStock, customTextView2);
            }
        });
        ((TextView) inflate.findViewById(R.id.tv_confirm)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SearchResultFragment.this.deviceFilters != null) {
                    for (SearchResult.AllAdapt allAdapt : SearchResultFragment.this.deviceFilters) {
                        if (allAdapt.isChecked) {
                            String unused = SearchResultFragment.this.adaptId = allAdapt.adapt_id;
                        }
                    }
                    SearchResultFragment.this.initData();
                }
                SearchResultFragment.this.mDropDownMenu.closeMenu();
            }
        });
        this.popupViews.add(listView);
        this.popupViews.add(listView2);
        this.popupViews.add(inflate);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (!(SearchResultFragment.this.allCategories == null || SearchResultFragment.this.allCategories.get(i) == null)) {
                    SearchResultFragment.this.mDropDownMenu.setTabText(((SearchResult.AllCategoriesBean) SearchResultFragment.this.allCategories.get(i)).cat_name);
                }
                SearchResultFragment.this.mDropDownMenu.closeMenu();
                String unused = SearchResultFragment.this.catId = ((SearchResult.AllCategoriesBean) SearchResultFragment.this.allCategories.get(i)).cat_id;
                SearchResultFragment.this.initData();
                MiShopStatInterface.a(String.format("%s_click", new Object[]{((SearchResult.AllCategoriesBean) SearchResultFragment.this.allCategories.get(i)).cat_name}), "search_filter");
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (!(SearchResultFragment.this.sortCategories == null || SearchResultFragment.this.sortCategories.get(i) == null)) {
                    SearchResultFragment.this.mDropDownMenu.setTabText(((SearchResult.AllCategoriesBean) SearchResultFragment.this.sortCategories.get(i)).cat_name);
                }
                SearchResultFragment.this.mDropDownMenu.closeMenu();
                String unused = SearchResultFragment.this.sortType = ((SearchResult.AllCategoriesBean) SearchResultFragment.this.sortCategories.get(i)).cat_id;
                SearchResultFragment.this.initData();
            }
        });
        RecyclerView recyclerView = new RecyclerView(getActivity());
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        recyclerView.setLayoutManager(new FullyGridLayoutManager(getActivity(), 2));
        this.searchResultAdapter = new SearchResultAdapter(getActivity());
        recyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), 10000, Coder.a(0.5f), getResources().getColor(R.color.divider_color)));
        recyclerView.setAdapter(this.searchResultAdapter);
        this.mDropDownMenu.setDropDownMenu(Arrays.asList(new String[]{getString(R.string.search_result_type), getString(R.string.search_result_sort), getString(R.string.search_result_filter)}), this.popupViews, recyclerView);
    }

    /* access modifiers changed from: private */
    public void renderView() {
        this.typeAdapter.setData(this.allCategories);
        fillSearchLabel(this.tagsDeviceLayout, this.deviceFilters);
        this.searchResultAdapter.setData(this.commodities);
    }

    public void fillSearchLabel(final TagsLayout tagsLayout, final List<SearchResult.AllAdapt> list) {
        if (list == null || list.size() == 0) {
            this.modelTitleView.setVisibility(8);
            tagsLayout.setVisibility(8);
            return;
        }
        this.modelTitleView.setVisibility(0);
        tagsLayout.setVisibility(0);
        if (tagsLayout.getChildCount() > 0) {
            tagsLayout.removeAllViews();
        }
        for (final SearchResult.AllAdapt next : list) {
            if (!TextUtils.isEmpty(next.adapt_name)) {
                TagsLayout.LayoutParams layoutParams = new TagsLayout.LayoutParams(-2, -2);
                layoutParams.setMargins(Coder.a(10.0f), Coder.a(10.0f), 0, 0);
                CustomTextView customTextView = new CustomTextView(tagsLayout.getContext());
                customTextView.setText(next.adapt_name);
                customTextView.setTextSize(13.0f);
                customTextView.setGravity(17);
                customTextView.setIncludeFontPadding(false);
                customTextView.setPadding(Coder.a(20.0f), 0, Coder.a(20.0f), 0);
                customTextView.setSingleLine();
                customTextView.setHeight(Coder.a(30.0f));
                updateSelectView(next.isChecked, customTextView);
                customTextView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        for (SearchResult.AllAdapt allAdapt : list) {
                            if (allAdapt.equals(next)) {
                                allAdapt.isChecked = !allAdapt.isChecked;
                            } else {
                                allAdapt.isChecked = false;
                            }
                        }
                        SearchResultFragment.this.fillSearchLabel(tagsLayout, list);
                    }
                });
                tagsLayout.addView(customTextView, layoutParams);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateSelectView(boolean z, CustomTextView customTextView) {
        if (z) {
            customTextView.setTextColor(Color.parseColor("#ffffff"));
            customTextView.setBackgroundResource(R.drawable.feature_search_check_orange_solid_bg);
            return;
        }
        customTextView.setTextColor(Color.parseColor("#FF6700"));
        customTextView.setBackgroundResource(R.drawable.feature_search_check_orange_border_bg);
    }

    public boolean isActivityAlive() {
        FragmentActivity activity = getActivity();
        if (activity == null || !isAdded()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17) {
            return !activity.isFinishing();
        }
        if (activity.isFinishing() || activity.isDestroyed()) {
            return false;
        }
        return true;
    }
}
