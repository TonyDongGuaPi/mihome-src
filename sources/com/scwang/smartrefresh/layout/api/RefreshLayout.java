package com.scwang.smartrefresh.layout.api;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public interface RefreshLayout {
    boolean autoLoadMore();

    boolean autoLoadMore(int i, int i2, float f);

    boolean autoRefresh();

    boolean autoRefresh(int i, int i2, float f);

    RefreshLayout finishLoadMore();

    RefreshLayout finishLoadMore(int i);

    RefreshLayout finishLoadMore(int i, boolean z, boolean z2);

    RefreshLayout finishLoadMore(boolean z);

    RefreshLayout finishLoadMoreWithNoMoreData();

    RefreshLayout finishRefresh();

    RefreshLayout finishRefresh(int i);

    RefreshLayout finishRefresh(int i, boolean z);

    RefreshLayout finishRefresh(boolean z);

    ViewGroup getLayout();

    @Nullable
    RefreshFooter getRefreshFooter();

    @Nullable
    RefreshHeader getRefreshHeader();

    RefreshState getState();

    boolean isEnableLoadMore();

    boolean isEnableRefresh();

    RefreshLayout setDisableContentWhenLoading(boolean z);

    RefreshLayout setDisableContentWhenRefresh(boolean z);

    RefreshLayout setDragRate(@FloatRange(from = 0.0d, to = 1.0d) float f);

    RefreshLayout setEnableAutoLoadMore(boolean z);

    RefreshLayout setEnableClipFooterWhenFixedBehind(boolean z);

    RefreshLayout setEnableClipHeaderWhenFixedBehind(boolean z);

    RefreshLayout setEnableFooterFollowWhenLoadFinished(boolean z);

    RefreshLayout setEnableFooterTranslationContent(boolean z);

    RefreshLayout setEnableHeaderTranslationContent(boolean z);

    RefreshLayout setEnableLoadMore(boolean z);

    RefreshLayout setEnableLoadMoreWhenContentNotFull(boolean z);

    RefreshLayout setEnableNestedScroll(boolean z);

    RefreshLayout setEnableOverScrollBounce(boolean z);

    RefreshLayout setEnableOverScrollDrag(boolean z);

    RefreshLayout setEnablePureScrollMode(boolean z);

    RefreshLayout setEnableRefresh(boolean z);

    RefreshLayout setEnableScrollContentWhenLoaded(boolean z);

    RefreshLayout setEnableScrollContentWhenRefreshed(boolean z);

    RefreshLayout setFooterHeight(float f);

    RefreshLayout setFooterInsetStart(float f);

    RefreshLayout setFooterMaxDragRate(@FloatRange(from = 1.0d, to = 100.0d) float f);

    RefreshLayout setFooterTriggerRate(@FloatRange(from = 0.0d, to = 1.0d) float f);

    RefreshLayout setHeaderHeight(float f);

    RefreshLayout setHeaderInsetStart(float f);

    RefreshLayout setHeaderMaxDragRate(@FloatRange(from = 1.0d, to = 100.0d) float f);

    RefreshLayout setHeaderTriggerRate(@FloatRange(from = 0.0d, to = 1.0d) float f);

    RefreshLayout setNoMoreData(boolean z);

    RefreshLayout setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener);

    RefreshLayout setOnMultiPurposeListener(OnMultiPurposeListener onMultiPurposeListener);

    RefreshLayout setOnRefreshListener(OnRefreshListener onRefreshListener);

    RefreshLayout setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener onRefreshLoadMoreListener);

    RefreshLayout setPrimaryColors(@ColorInt int... iArr);

    RefreshLayout setPrimaryColorsId(@ColorRes int... iArr);

    RefreshLayout setReboundDuration(int i);

    RefreshLayout setReboundInterpolator(@NonNull Interpolator interpolator);

    RefreshLayout setRefreshContent(@NonNull View view);

    RefreshLayout setRefreshContent(@NonNull View view, int i, int i2);

    RefreshLayout setRefreshFooter(@NonNull RefreshFooter refreshFooter);

    RefreshLayout setRefreshFooter(@NonNull RefreshFooter refreshFooter, int i, int i2);

    RefreshLayout setRefreshHeader(@NonNull RefreshHeader refreshHeader);

    RefreshLayout setRefreshHeader(@NonNull RefreshHeader refreshHeader, int i, int i2);

    RefreshLayout setScrollBoundaryDecider(ScrollBoundaryDecider scrollBoundaryDecider);
}
