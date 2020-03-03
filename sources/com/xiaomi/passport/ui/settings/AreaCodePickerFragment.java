package com.xiaomi.passport.ui.settings;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.settings.utils.PhoneNumUtil;

public class AreaCodePickerFragment extends Fragment {
    public static final String KEY_COUNTRY_ISO = "country_iso";
    public static final String TAG = "AreaCodePickerFragment";
    protected Activity mActivity;
    protected AreaCodePickerAdapter mAdapter;
    /* access modifiers changed from: private */
    public AlphabetFastIndexer mFastIndexer;
    protected ListView mListView;
    protected String mPreviousThumb;
    protected View mRoot;

    /* access modifiers changed from: protected */
    public void setupPickerUi(View view, Bundle bundle) {
        prepareAdapter(view, bundle);
        prepareListView(view);
        setupListView(view);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        PhoneNumUtil.initializeCountryPhoneData(getActivity());
        this.mRoot = layoutInflater.inflate(R.layout.passport_area_code_picker_fragment, viewGroup, false);
        setupPickerUi(this.mRoot, getArguments());
        return this.mRoot;
    }

    /* access modifiers changed from: protected */
    public void prepareListView(View view) {
        this.mListView = (ListView) view.findViewById(R.id.list);
    }

    /* access modifiers changed from: protected */
    public PhoneNumUtil.CountryPhoneNumData getItem(int i) {
        return this.mAdapter.getItem(i);
    }

    /* access modifiers changed from: protected */
    public void prepareAdapter(View view, Bundle bundle) {
        this.mAdapter = new AreaCodePickerAdapter(getActivity(), bundle);
    }

    /* access modifiers changed from: protected */
    public void setupListView(View view) {
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(getListViewItemOnClickListener());
        this.mFastIndexer = (AlphabetFastIndexer) view.findViewById(R.id.fast_indexer);
        this.mFastIndexer.attatch(this.mListView);
        this.mFastIndexer.setVisibility(0);
        this.mListView.setOnScrollListener(this.mFastIndexer.decorateScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (1 == i) {
                    View view = null;
                    if (absListView.getContext() instanceof Activity) {
                        view = ((Activity) absListView.getContext()).getCurrentFocus();
                    }
                    if (view != null) {
                        view.clearFocus();
                    }
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                String sectionTitleForPostion = AreaCodePickerFragment.this.mAdapter.getSectionTitleForPostion(i + 1);
                if (!TextUtils.equals(sectionTitleForPostion, AreaCodePickerFragment.this.mPreviousThumb)) {
                    AreaCodePickerFragment.this.mFastIndexer.drawThumb(sectionTitleForPostion);
                    AreaCodePickerFragment.this.mPreviousThumb = sectionTitleForPostion;
                }
            }
        }));
    }

    /* access modifiers changed from: protected */
    public AdapterView.OnItemClickListener getListViewItemOnClickListener() {
        return new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                PhoneNumUtil.CountryPhoneNumData item = AreaCodePickerFragment.this.getItem(i);
                Intent intent = new Intent();
                intent.putExtra(AreaCodePickerFragment.KEY_COUNTRY_ISO, item.countryISO);
                AreaCodePickerFragment.this.mActivity.setResult(-1, intent);
                AreaCodePickerFragment.this.mActivity.finish();
            }
        };
    }
}
