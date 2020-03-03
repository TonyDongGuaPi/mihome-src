package com.mi.global.bbs.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.ForYouCustomizeAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.ForYouCustomizeModel;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class ForYouCustomizeActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, ForYouCustomizeAdapter.OnFollowListener {
    private static final String ForYou_Customize_CACHE = "for_you_customize_cache";
    /* access modifiers changed from: private */
    public ForYouCustomizeAdapter adapter;
    List<ForYouCustomizeModel.DataBean> mList;
    @BindView(2131492947)
    ProgressBar mProgress;
    @BindView(2131493155)
    RecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;

    public static void show(Context context) {
        context.startActivity(new Intent(context, ForYouCustomizeActivity.class));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_my_favor);
        ButterKnife.bind((Activity) this);
        initView();
        getData();
    }

    private void initView() {
        setTitleAndBack("", this.titleBackListener);
        setTitle(R.string.for_you_customize_title);
        this.mRefreshView.setOnRefreshListener(this);
        this.mRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.mList = new ArrayList();
        this.adapter = new ForYouCustomizeAdapter(this, this.mList);
        this.adapter.setOnFollowListener(this);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this);
        wrapContentLinearLayoutManager.setOrientation(1);
        this.mRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.mRecycleView.setAdapter(this.adapter);
        this.mRecycleView.setHasFixedSize(true);
        checkIfHaveCache();
    }

    private void checkIfHaveCache() {
        String stringPref = Utils.Preference.getStringPref(this, ForYou_Customize_CACHE, "");
        if (!TextUtils.isEmpty(stringPref)) {
            dismissProgress();
            handleResponse((ForYouCustomizeModel) JsonParser.parse(stringPref, ForYouCustomizeModel.class));
        }
    }

    private void getData() {
        ApiClient.getForYouCustomize(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<ForYouCustomizeModel>() {
            public void accept(@NonNull ForYouCustomizeModel forYouCustomizeModel) throws Exception {
                ForYouCustomizeActivity.this.dismissProgress();
                if (forYouCustomizeModel.getErrno() == 0) {
                    ForYouCustomizeActivity.this.saveCache(new Gson().toJson((Object) forYouCustomizeModel));
                    ForYouCustomizeActivity.this.handleResponse(forYouCustomizeModel);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                ForYouCustomizeActivity.this.dismissProgress();
            }
        });
    }

    /* access modifiers changed from: private */
    public void saveCache(String str) {
        Utils.Preference.setStringPref(this, ForYou_Customize_CACHE, str);
    }

    /* access modifiers changed from: private */
    public void handleResponse(ForYouCustomizeModel forYouCustomizeModel) {
        if (forYouCustomizeModel != null && forYouCustomizeModel.data != null && forYouCustomizeModel.data.size() > 0) {
            this.adapter.addData(forYouCustomizeModel.data);
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

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getData();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onFollow(String str, String str2) {
        ApiClient.followForum(str, str2).subscribe(new Consumer<BaseResult>() {
            public void accept(@NonNull BaseResult baseResult) throws Exception {
                if (baseResult != null && baseResult.getErrno() == 0) {
                    ForYouCustomizeActivity.this.adapter.notifyDataSetChanged();
                    ForYouCustomizeActivity.this.setResult(-1, new Intent());
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                th.printStackTrace();
            }
        });
    }
}
