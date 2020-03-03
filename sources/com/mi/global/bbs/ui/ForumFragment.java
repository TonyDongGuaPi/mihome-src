package com.mi.global.bbs.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.SubForumAdapter;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.SubForumModel;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.utils.Utils;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class ForumFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String SUB_FORUM_CACHE = "subforum_cache";
    /* access modifiers changed from: private */
    public SubForumAdapter adapter;
    @BindInt(2131558413)
    int columns;
    private GridLayoutManager gridLayoutManager;
    @BindView(2131493352)
    LinearLayout mFragmentForumRoot;
    List<SubForumModel.DataBean> mList;
    @BindView(2131493351)
    ProgressBar mProgress;
    @BindView(2131493155)
    RecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_fragment_forum, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        initView();
        getData();
        return inflate;
    }

    private void initView() {
        this.mRefreshView.setOnRefreshListener(this);
        this.mRefreshView.setVisibility(8);
        this.mRefreshView.setBackgroundColor(-1);
        this.mRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.mList = new ArrayList();
        this.adapter = new SubForumAdapter(getActivity(), this.mList);
        this.gridLayoutManager = new GridLayoutManager(getActivity(), this.columns);
        this.gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanSize(int i) {
                return ForumFragment.this.adapter.getItemColumnSpan(i);
            }
        });
        this.mRecycleView.setLayoutManager(this.gridLayoutManager);
        this.mRecycleView.setAdapter(this.adapter);
        this.mRecycleView.setHasFixedSize(true);
        checkIfHaveCache();
    }

    private void checkIfHaveCache() {
        String stringPref = Utils.Preference.getStringPref(getActivity(), SUB_FORUM_CACHE, "");
        if (!TextUtils.isEmpty(stringPref)) {
            dismissProgress();
            handleResponse((SubForumModel) JsonParser.parse(stringPref, SubForumModel.class));
        }
    }

    private void getData() {
        ApiClient.getSubForum(bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<SubForumModel>() {
            public void accept(@NonNull SubForumModel subForumModel) throws Exception {
                ForumFragment.this.dismissProgress();
                if (subForumModel.getErrno() == 0) {
                    ForumFragment.this.saveCache(new Gson().toJson((Object) subForumModel));
                    ForumFragment.this.handleResponse(subForumModel);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                ForumFragment.this.dismissProgress();
            }
        });
    }

    /* access modifiers changed from: private */
    public void saveCache(String str) {
        Utils.Preference.setStringPref(getActivity(), SUB_FORUM_CACHE, str);
    }

    /* access modifiers changed from: private */
    public void handleResponse(SubForumModel subForumModel) {
        if (subForumModel != null && subForumModel.data != null && subForumModel.data.size() > 0) {
            this.adapter.addData(subForumModel.data);
        }
    }

    /* access modifiers changed from: private */
    public void dismissProgress() {
        if (this.mProgress != null && this.mProgress.getVisibility() == 0) {
            this.mProgress.setVisibility(8);
        }
        if (this.mRefreshView != null) {
            this.mRefreshView.setVisibility(0);
            this.mRefreshView.setRefreshing(false);
        }
    }

    public void onRefresh() {
        getData();
    }

    public void setRootBottomMargin(int i) {
        if (this.mFragmentForumRoot != null) {
            ((FrameLayout.LayoutParams) this.mFragmentForumRoot.getLayoutParams()).bottomMargin = i;
            this.mFragmentForumRoot.requestLayout();
        }
    }
}
